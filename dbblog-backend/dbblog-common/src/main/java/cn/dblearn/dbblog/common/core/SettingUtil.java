package cn.dblearn.dbblog.common.core;

import cn.dblearn.dbblog.common.Constant;
import cn.dblearn.dbblog.common.context.ApplicationContextHolder;
import cn.dblearn.dbblog.common.enums.CacheKeyStore;
import cn.dblearn.dbblog.common.support.redis.RedisClient;
import cn.dblearn.dbblog.common.util.DictUtil;
import cn.dblearn.dbblog.common.util.SerializeUtil;
import cn.dblearn.dbblog.common.util.StringUtilEx;
import com.beust.jcommander.internal.Maps;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConvertUtilsBean;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.converters.ArrayConverter;
import org.apache.commons.beanutils.converters.DateConverter;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;


/**
 * 系统设置工具类
 * @author developer001
 *
 */
public class SettingUtil {

	/** BeanUtilsBean */
	private static final BeanUtilsBean beanUtils;

	//设置字典
	private static Map<String,String> settingDicts = null;

	/** 缓存名称 */
	public static final byte[] CACHE_NAME = CacheKeyStore.SETTING.getValue().getBytes();

	static {
		ConvertUtilsBean convertUtilsBean = new ConvertUtilsBean() {
			@Override
			public String convert(Object value) {
				if (value != null) {
					Class<?> type = value.getClass();
					if (type.isEnum() && super.lookup(type) == null) {
						super.register(new EnumConverter(type), type);
					} else if (type.isArray() && type.getComponentType().isEnum()) {
						if (super.lookup(type) == null) {
							ArrayConverter arrayConverter = new ArrayConverter(type, new EnumConverter(type.getComponentType()), 0);
							arrayConverter.setOnlyFirstToString(false);
							super.register(arrayConverter, type);
						}
						Converter converter = super.lookup(type);
						return ((String) converter.convert(String.class, value));
					}
				}
				return super.convert(value);
			}

			@SuppressWarnings("rawtypes")
			@Override
			public Object convert(String value, Class clazz) {
				if (clazz.isEnum() && super.lookup(clazz) == null) {
					super.register(new EnumConverter(clazz), clazz);
				}
				return super.convert(value, clazz);
			}

			@SuppressWarnings("rawtypes")
			@Override
			public Object convert(String[] values, Class clazz) {
				if (clazz.isArray() && clazz.getComponentType().isEnum() && super.lookup(clazz.getComponentType()) == null) {
					super.register(new EnumConverter(clazz.getComponentType()), clazz.getComponentType());
				}
				return super.convert(values, clazz);
			}

			@SuppressWarnings("rawtypes")
			@Override
			public Object convert(Object value, Class targetType) {
				if (super.lookup(targetType) == null) {
					if (targetType.isEnum()) {
						super.register(new EnumConverter(targetType), targetType);
					} else if (targetType.isArray() && targetType.getComponentType().isEnum()) {
						ArrayConverter arrayConverter = new ArrayConverter(targetType, new EnumConverter(targetType.getComponentType()), 0);
						arrayConverter.setOnlyFirstToString(false);
						super.register(arrayConverter, targetType);
					}
				}
				return super.convert(value, targetType);
			}
		};

		DateConverter dateConverter = new DateConverter();
		dateConverter.setPatterns(Constant.DATE_PATTERNS);
		convertUtilsBean.register(dateConverter, Date.class);
		beanUtils = new BeanUtilsBean(convertUtilsBean);
	}

	private SettingUtil(){}

	/**
	 * 获取系统设置
	 *
	 * @return 系统设置
	 */
	public static Setting get() {
		 RedisClient redisClient = ApplicationContextHolder.getBean(RedisClient.class);
		 byte[] data = redisClient.get(CACHE_NAME);
		 Setting setting;
		 if(data!=null){
			 setting = (Setting) SerializeUtil.unserialize(data);
		 }else{
			setting = buildSetting();
			redisClient.set(CACHE_NAME, SerializeUtil.serialize(setting));
		 }

		return setting;
	}

	private static Setting buildSetting(){
		Setting setting = new Setting();
		Set<Entry<String, String>> entrySet = getSettingDicts().entrySet();
		for(Entry<String, String> entry:entrySet){
			String name = StringUtilEx.substringAfterLast(entry.getKey(), ".");
			String value = entry.getValue();
			try {
				beanUtils.setProperty(setting, name, value);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		return setting;
	}

	/**
	 * 设置系统设置
	 *
	 * @param setting
	 *            系统设置
	 */
	public static void set(Setting setting) {
		RedisClient redisClient = ApplicationContextHolder.getBean(RedisClient.class);
		redisClient.set(CACHE_NAME, SerializeUtil.serialize(setting));
	}

	public static Map<String, String> getSettingDicts(){
		if(settingDicts==null){
			Map<String, String> dicts = DictUtil.getCacheDicts();
			if(dicts!=null&&dicts.size()>0){
				settingDicts = Maps.newHashMap();
				Set<Entry<String, String>> entrySet = dicts.entrySet();
				for(Entry<String, String> entry:entrySet){
					if(entry.getKey().startsWith(DictKeyStore.SETTING_ATTRIBUTE_PREFIX)){
						settingDicts.put(entry.getKey(), entry.getValue());
					}
				}
			}
		}
		return settingDicts;
	}

	public static void init(Map<String, String> dicts,ApplicationContext applicationContext){
		settingDicts = Maps.newHashMap();
		Set<Entry<String, String>> entrySet = dicts.entrySet();
		for(Entry<String, String> entry:entrySet){
			if(entry.getKey().startsWith(DictKeyStore.SETTING_ATTRIBUTE_PREFIX)){
				settingDicts.put(entry.getKey(), entry.getValue());
			}
		}

		RedisClient redisClient = applicationContext.getBean(RedisClient.class);
		Setting setting = buildSetting();
		redisClient.set(CACHE_NAME, SerializeUtil.serialize(setting));
	}

	/**
	 * 获取系统加工处理后的金额
	 *
	 * @param amount
	 *            金额值
	 * @return 金额值
	 */
	public static BigDecimal getProcessAmount(BigDecimal amount) {
		if (amount == null) {
			return null;
		}
		Setting setting = get();
		int roundingMode;
		if (setting.getAmountRoundType() == Setting.RoundType.roundUp) {
			roundingMode = BigDecimal.ROUND_UP;
		} else if (setting.getAmountRoundType() == Setting.RoundType.roundDown) {
			roundingMode = BigDecimal.ROUND_DOWN;
		} else {
			roundingMode = BigDecimal.ROUND_HALF_UP;
		}
		return amount.setScale(setting.getAmountScale(), roundingMode);
	}

}
