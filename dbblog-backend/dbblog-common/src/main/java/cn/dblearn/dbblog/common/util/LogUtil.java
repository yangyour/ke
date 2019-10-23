package cn.dblearn.dbblog.common.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import cn.dblearn.dbblog.common.warpper.LogData;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class LogUtil {

	private static final Log LOGGER = LogFactory.getLog(LogUtil.class);
	/**
	 * 默认不需要比较的属性集合
	 */
	private final static String[] DEFAULT_IGNOREPROPERTIES = new String[]{"serialVersionUID"};

	/**
	 * 对象转Json字符串
	 * @param object
	 * @return
	 */
	public static String objToJsonString(Object object){
		return objToJsonString(object,DEFAULT_IGNOREPROPERTIES);
	}

	/**
	 * 对象转Json字符串
	 * @param object
	 * @param ignoreProperties 排除属性
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String objToJsonString(Object object,String[] ignoreProperties){
		Map<String,Object> diffMap = Maps.newHashMap();
		List<String> ignoreList = (ignoreProperties != null ? Arrays.asList(ignoreProperties) : null);
		LinkedList<Field> list = Lists.newLinkedList();

        Class clazz = ClassUtil.getUserClass(object);
        Class supperClass = clazz.getSuperclass();
        while (supperClass!=null) {
			 Field[] beforeFields = supperClass.getDeclaredFields();
			  for (Field field : beforeFields) {
				  list.add(field);
			  }
			 supperClass = supperClass.getSuperclass();
		}
        Field[] fields = clazz.getDeclaredFields();
        list.addAll(Arrays.asList(fields));

        for (Field field : list) {
        	  if(ignoreList != null && ignoreList.contains(field.getName())){
         	    continue;
             }

        	 String tableField = getTableField(field);
        	 if(StringUtils.isEmpty(tableField)){
            	   continue;
               }
        	String fieldName = field.getName();
        	//LOGGER.info("fieldName :"+fieldName);
            Object valueObj = ReflectionUtil.getFieldValue(object, fieldName);
            if (valueObj instanceof Date) {
            	valueObj =DateFormatUtils.format((Date) valueObj, "yyyy-MM-dd HH:mm:ss");
            }
            diffMap.put(tableField,valueObj);

        }
	  if(diffMap.size()>0){
    	  return JsonUtil.toJson(diffMap);
      }
	  return null;
	}

	/**
     * 比较两个相同类型的对象
     * @param before
     * @param after
     * @return
     */
    public static String contrastIdenticalObj(Object before,Object after){
    	return contrastIdenticalObj(before, after, DEFAULT_IGNOREPROPERTIES);
    }

	 /**
     * 比较两个相同类型的对象
     * @param before
     * @param after
     * @param ignoreProperties
     * @return
     */
    @SuppressWarnings("rawtypes")
	public static String contrastIdenticalObj(Object before,Object after,String[] ignoreProperties){
    	if(before==null||after==null){
    		return null;
    	}

    	List<String> ignoreList = (ignoreProperties != null ? Arrays.asList(ignoreProperties) : null);
		Class beforeClazz = ClassUtil.getUserClass(before);
		LinkedList<Field> list = Lists.newLinkedList();

		Class supperClass = beforeClazz.getSuperclass();
		 while (supperClass!=null) {
			 Field[] beforeFields = supperClass.getDeclaredFields();
			  for (Field field : beforeFields) {
				  list.add(field);
			  }
			 supperClass = supperClass.getSuperclass();
		}
         Field[] beforeFields = beforeClazz.getDeclaredFields();
         list.addAll(Arrays.asList(beforeFields));

         Map<String,Object> beforeVo = Maps.newHashMap();
         Map<String,Object> afterVo = Maps.newHashMap();
         for (Field field : list) {
        	 String fieldName = field.getName();
        	 if(ignoreList != null && ignoreList.contains(fieldName)){
            	   continue;
              }

        	 String tableField = getTableField(field);
        	 if(StringUtils.isEmpty(tableField)){
            	   continue;
               }
        	 //System.out.println("fieldName = "+fieldName+"  type = "+field.getGenericType().getTypeName()+"  "+field.getType().isEnum());
        	 Object o1 = null;
        	 Object o2 = null;
			try {
				 o1 = ReflectionUtil.getFieldValue(before, fieldName);
                 o2 = ReflectionUtil.getFieldValue(after, fieldName);
			} catch (IllegalArgumentException e) {
				LOGGER.error(e.getMessage());
				continue;
			}
            if(o1==null&&o2==null){
            	continue;
            }
            if((o1!=null&&o2!=null)&&o1.equals(o2)){
            	continue;
            }
            //LOGGER.info(field.getName()+" tableField = "+tableField);
            beforeVo.put(tableField, o1);
            afterVo.put(tableField, o2);
         }
         LogData logData = new LogData(beforeVo,afterVo);
	     if(afterVo.size()>0){
	    	 return JsonUtil.toJson(logData);
	     }
		return null;
    }

	/**
     * 返回数据库表字段名
     * @param field
     * @return
     */
    @SuppressWarnings("unused")
	private static String getTableField(Field field){
    	Annotation[] annotations = field.getAnnotations();
    	if(annotations!=null&&annotations.length>0){
    		for (int i = 0; i < annotations.length;i++) {
    			Annotation annotation = annotations[i];
    			if(annotation instanceof Transient){
    				return null;
    			}else if(annotation instanceof Column){
    				Column tableField = (Column)annotation;
    				return tableField.name();
    			}else{
					return field.getName();
    			}
    			/*
    			if(annotation instanceof Transient){
    				return null;
    			}else if(annotation instanceof Id){
    				return field.getName();
    			}else if(annotation instanceof Column){
    				Column tableField = (Column)annotation;
					return tableField.name();
    			}else{
					return field.getName();
    			}
    			*/
			}
    	}

    	return null;
    }
}
