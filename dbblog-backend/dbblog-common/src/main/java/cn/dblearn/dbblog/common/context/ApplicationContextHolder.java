package cn.dblearn.dbblog.common.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * spring应用上下文环境
 * @author developer001
 *
 */
@Component
public class ApplicationContextHolder implements ApplicationContextAware{
	 private static ApplicationContext applicationContext;

	 @Override
     public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextHolder.applicationContext = applicationContext;
     }

    public static <T> T getBean(Class<T> t) {
    	if(applicationContext!=null){
    		return applicationContext.getBean(t);
    	}
        return null;
    }

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

}
