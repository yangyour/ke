
package cn.dblearn.dbblog.common.base;

import cn.dblearn.dbblog.common.context.ContextThreadVariable;
import cn.dblearn.dbblog.common.context.log.CudLogManager;
import cn.dblearn.dbblog.common.context.log.CudType;
import cn.dblearn.dbblog.common.core.SettingUtil;
import cn.dblearn.dbblog.common.util.ClassUtil;
import cn.dblearn.dbblog.common.util.ReflectionUtil;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public abstract class BaseServiceImpl<T extends BaseEntity> extends ServiceImpl<BaseMapper<T>, T> implements BaseService<T> {

	protected static final Logger logger = LoggerFactory.getLogger(BaseServiceImpl.class);

	/** "创建日期"属性名称 */
	private static final String CREATED_AT_PROPERTY_NAME = "createdAt";

	/** "修改日期"属性名称 */
	private static final String UPDATED_AT_PROPERTY_NAME = "updatedAt";

	/** "排序"列名 */
	private static final String SORT_COLUMN_NAME = "sort";

	/**
	 * 金额系统加工处理类型名称
	 */
	private static final String AMOUNT_PROCESS_PROPERTY_TYPE_NAME = "java.math.BigDecimal";



    @Autowired
    protected Mapper<T> mapper;

    public Mapper<T> getMapper() {
        return mapper;
    }

	private Class<T> entityClass;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public BaseServiceImpl(){
		Class c = getClass();
        Type type = c.getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            Type[] parameterizedType = ((ParameterizedType) type).getActualTypeArguments();
            if(parameterizedType.length==1){
            	this.entityClass = (Class<T>) parameterizedType[0];
            }
        }
	}

    @Override
    public T selectByKey(Object key) {
        return mapper.selectByPrimaryKey(key);
    }

    public List<T> select(T entity){
    	return mapper.select(entity);
    }

    public List<T> selectByExample(Object example) {
    	if(isAutoSort()){
    		Example example2 = null;
    		if(example!=null&&example instanceof Example){
    			example2 = (Example)example;
    		}else{
    			example2 = new Example(entityClass);
    		}
    		example2.setOrderByClause(" "+SORT_COLUMN_NAME+" asc");
    		return mapper.selectByExample(example2);
    	}
        return mapper.selectByExample(example);
    }
    public T selectEntityOne(T entity){
    	return mapper.selectOne(entity);
    }
    public long selectCountByExample(Object example){
    	return mapper.selectCountByExample(example);
    }

    public boolean save(T entity) {
    	preSave(entity);
    	int result = mapper.insert(entity);
		Boolean b=false;
		if(result>0){
    		afterSave(entity);
    		b=true;
    	}
        return b;
    }

    public int saveSelective(T entity) {
    	preSave(entity);
    	int result = mapper.insertSelective(entity);
    	if(result>0){
    		afterSave(entity);
    	}
        return result;
    }

    public int delete(Object key) {
    	T entity = selectByKey(key);
        int result = mapper.deleteByPrimaryKey(key);
        if(result>0){
        	afterDelete(entity);
        }
        return result;
    }

    public int deleteByRecord(T entity) {
    	List<T> entityList = null;
    	if(entity.isCudLog()){
    		entityList = select(entity);
    	}
        int result = mapper.delete(entity);
        if(entityList!=null&&result>0){
        	afterBatchDelete(entityList);
        }
        return result;
    }

    public int updateAll(T entity) {
    	preUpdate(entity);
    	T before = null;
    	if(entity.getId()!=null&&entity.isCudLog()){
    		before = selectByKey(entity.getId());
    	}
        int result = mapper.updateByPrimaryKey(entity);
        if(result>0){
        	afterUpdate(before,entity);
        }
        return result;
    }

    public int updateNotNull(T entity) {
    	preUpdate(entity);
    	T before = null;
    	if(entity.getId()!=null&&entity.isCudLog()){
    		before = selectByKey(entity.getId());
    	}
        int result = mapper.updateByPrimaryKeySelective(entity);
        if(result>0){
        	afterUpdate(before,entity);
        }
        return result;
    }

    /**
	 * 获取实体属性
	 * @param entity
	 * @return
	 */
    /*
	private String[] getPropertyNames(T entity){
		Field[] fields = getFields(entity);
		if(fields!=null){
			String[] propertyNames = new String[fields.length];
			for (int i = 0; i < fields.length; i++) {
				String fieldName = fields[i].getName();
				propertyNames[i] = fieldName;
			}
			return propertyNames;
		}
		return null;
	}
	*/
	/**
	 * 获取
	 * @param entity
	 * @return
	 */
	private Field[] getFields(T entity){
		Class<?> userClass = ClassUtil.getUserClass(entity);
		return userClass.getDeclaredFields();
	}

	/**
	 * 是否自动排序
	 * @param entity
	 * @return
	 */
	private boolean isAutoSort(){
		if(entityClass!=null){
			Field[] fields = entityClass.getDeclaredFields();
			if(fields!=null){
				for (int i = 0; i < fields.length; i++) {
					String fieldName = fields[i].getName();
					if(SORT_COLUMN_NAME.equals(fieldName)){
						return true;
					}
				}
			}
		}
		return false;
	}

    /**
     * 保存前执行
     * @param entity
     */
    protected void preSave(T entity){
    	if (entity instanceof BaseEntity) {
			Field[] fields = getFields(entity);
			boolean isSetCreateAt = false;
			boolean isSetUpdateAt = false;
			for (int i = 0; i < fields.length; i++) {
				String propertyName = fields[i].getName();

				if (CREATED_AT_PROPERTY_NAME.equals(propertyName)) {
					Object value = ReflectionUtil.getFieldValue(entity, CREATED_AT_PROPERTY_NAME);
					if(value==null){
						isSetCreateAt = true;
						logger.debug(entity.getClass().getSimpleName()+" preSave isSetCreateAt = "+isSetCreateAt);
					}
					continue;
				}
				if (UPDATED_AT_PROPERTY_NAME.equals(propertyName)) {
					Object value = ReflectionUtil.getFieldValue(entity, UPDATED_AT_PROPERTY_NAME);
					if(value==null){
						isSetUpdateAt = true;
						logger.debug(entity.getClass().getSimpleName()+" preSave isSetUpdateAt = "+isSetUpdateAt);
					}
					continue;
				}

				if(entity.isAmountProcess()){
					//金额系统加工
					String propertyTypeName = fields[i].getType().getName();
					if(AMOUNT_PROCESS_PROPERTY_TYPE_NAME.equals(propertyTypeName)){
						BigDecimal amount = (BigDecimal)ReflectionUtil.getFieldValue(entity, propertyName);
						if(amount!=null){
							ReflectionUtil.invokeSetterMethod(entity, propertyName, SettingUtil.getProcessAmount(amount));
						}
					}
				}
			}

			Date currentDate = null;
			if(isSetCreateAt){
				currentDate = new Date();
				ReflectionUtil.invokeSetterMethod(entity, CREATED_AT_PROPERTY_NAME, currentDate);
			}

			if(isSetUpdateAt){
				currentDate = currentDate==null?new Date():currentDate;
				ReflectionUtil.invokeSetterMethod(entity, UPDATED_AT_PROPERTY_NAME, currentDate);
			}
		}
    }

    /**
     * 保存后执行
     * @param entity
     */
    protected void afterSave(T entity){
    	CudLogManager logManager = ContextThreadVariable.getCudLogManager();
    	if(logManager!=null&&entity.isCudLog()){
    		logManager.excuteLog(null,entity, CudType.create);
    	}
    }


	/**
	 * 删除后执行
	 * @param entity 删除前的实体对象
	 */
    protected void afterDelete(T entity){
    	CudLogManager logManager = ContextThreadVariable.getCudLogManager();
    	if(logManager!=null&&entity.isCudLog()){
    		logManager.excuteLog(entity,null,CudType.delete);
    	}
    }

    /**
	 * 删除后执行
	 * @param entity 删除前的实体对象
	 */
    protected void afterBatchDelete(List<T> entityList){
    	CudLogManager logManager = ContextThreadVariable.getCudLogManager();
    	for(T entity:entityList){
    		if(logManager!=null&&entity.isCudLog()){
        		logManager.excuteLog(entity,null,CudType.delete);
        	}
    	}
    }

    /**
     * 修改前执行
     * @param entity
     */
    protected void preUpdate(T entity){
    	Field[] fields = getFields(entity);
		for (int i = 0; i < fields.length; i++) {
			String propertyName = fields[i].getName();
			if (UPDATED_AT_PROPERTY_NAME.equals(propertyName)) {
				ReflectionUtil.invokeSetterMethod(entity, UPDATED_AT_PROPERTY_NAME, new Date());
			}

			if(entity.isAmountProcess()){
				//金额系统加工
				String propertyTypeName = fields[i].getType().getName();
				if(AMOUNT_PROCESS_PROPERTY_TYPE_NAME.equals(propertyTypeName)){
					BigDecimal amount = (BigDecimal)ReflectionUtil.getFieldValue(entity, propertyName);
					if(amount!=null){
						ReflectionUtil.invokeSetterMethod(entity, propertyName, SettingUtil.getProcessAmount(amount));
					}
				}
			}
		}
    }

    /**
     * 修改后执行
     * @param before
     * @param entity
     */
    protected void afterUpdate(T before,T entity){
    	CudLogManager logManager = ContextThreadVariable.getCudLogManager();
    	if(logManager!=null&&entity.isCudLog()){
    		logManager.excuteLog(before,entity,CudType.update);
    	}
    }

}
