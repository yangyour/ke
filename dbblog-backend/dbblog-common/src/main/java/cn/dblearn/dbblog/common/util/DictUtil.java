package cn.dblearn.dbblog.common.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.StringUtils;
import cn.dblearn.dbblog.common.core.DictKeyStore;
import cn.dblearn.dbblog.common.context.ApplicationContextHolder;
import cn.dblearn.dbblog.common.enums.CacheKeyStore;
import cn.dblearn.dbblog.common.support.redis.RedisClient;

import com.google.common.collect.Maps;

/**
 * 字典工具
 * @author developer001
 */
public final class DictUtil {

	private static final byte[] CACHE_KEY = CacheKeyStore.DICT_DATA.getValue().getBytes();

	private static Map<String, String> dictMap = Maps.newConcurrentMap();

	public static Map<String, String> getDictMap() {
        return dictMap;
    }

	@SuppressWarnings("unchecked")
	public static Map<String, String> getCacheDicts() {
    	RedisClient redisClient = ApplicationContextHolder.getBean(RedisClient.class);
    	if(redisClient!=null){
    		byte[] data = redisClient.get(CACHE_KEY);
        	if(data!=null){
        		dictMap = (ConcurrentHashMap<String, String>)SerializeUtil.unserialize(data);
        	}
    	}
        return dictMap;
    }

	@SuppressWarnings("unchecked")
	public static Map<String, String> getCacheDicts(RedisClient redisClient) {
    	if(redisClient!=null){
    		byte[] data = redisClient.get(CACHE_KEY);
        	if(data!=null){
        		dictMap = (ConcurrentHashMap<String, String>)SerializeUtil.unserialize(data);
        	}
    	}
        return dictMap;
    }

	/**
	 * 刷新缓存
	 */
	public static void refreshCacheDicts() {
    	RedisClient redisClient = ApplicationContextHolder.getBean(RedisClient.class);
    	if(redisClient!=null){
    		redisClient.set(CACHE_KEY,SerializeUtil.serialize(dictMap));
    	}
    }

	/**
	 * 刷新缓存
	 */
	public static void refreshCacheDicts(RedisClient redisClient) {
    	if(redisClient!=null){
    		redisClient.set(CACHE_KEY,SerializeUtil.serialize(dictMap));
    	}
    }

    /**
     * 根据key获取字符串值
     * @param key
     * @return
     */
    public static String getString(String key) {
    	String value = dictMap.get(key);
    	if(StringUtilEx.isNotEmpty(value)){
    		return value;
    	}
        return getCacheDicts().get(key);
    }

    /**
     * 根据key获取字符串值
     * @param key
     * @param defaultValue 为空时默认值
     * @return
     */
    public static String getString(String key, String defaultValue) {
        String value = getString(key);
        if (StringUtils.isEmpty(value)) {
            return defaultValue;
        }
        return value;
    }

    /**
     * 根据key获取值
     *
     * @param key
     * @return
     */
    public static Integer getInt(String key) {
        String value = getString(key);
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        return Integer.parseInt(value);
    }

    /**
     * 根据key获取值
     *
     * @param key
     * @param defaultValue
     * @return
     */
    public static int getInt(String key, int defaultValue) {
        String value = getString(key);
        if (StringUtils.isEmpty(value)) {
            return defaultValue;
        }
        return Integer.parseInt(value);
    }

    /**
     * 取得 long 值，若无（或解析异常）则返回默认值
     * @param keyName 属性名
     * @param defaultValue 默认值
     * @return 属性值
     */
    public static long getLong(String keyName, long defaultValue) {
        String value = getString(keyName);
        if (StringUtils.isEmpty(value)) {
            return defaultValue;
        }
        try {
            return Long.parseLong(value.trim());
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public static boolean getBoolean(String key) {
        String value = getString(key);
        if (StringUtils.isEmpty(value)) {
            return false;
        }
        return new Boolean(value.trim());
    }

    /**
     * 根据key获取值
     *
     * @param key
     * @param defaultValue
     * @return
     */
    public static boolean getBoolean(String key, boolean defaultValue) {
        String value = getString(key);
        if (StringUtils.isEmpty(value)) {
            return defaultValue;
        }
        return new Boolean(value.trim());
    }

    /**
     * 根据枚举获取值
     *
     * @param enumObject
     * @param defaultValue
     * @return
     */
    public static String getEnumValue(Enum<?> enumObject) {
    	if(enumObject==null){
    		return null;
    	}
        String key = DictKeyStore.ENUMDICT_PREFIX+enumObject.getClass().getSimpleName()+"."+enumObject.name();
        return getString(key, null);
    }
}
