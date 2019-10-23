package cn.dblearn.dbblog.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 *
 * 系统操作日志注解
 * @author developer001
 *
 */
@Documented
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Target(java.lang.annotation.ElementType.METHOD)
public @interface SysOptLog {

	 /**
     * 操作名称,例如:"修改菜单"
     */
    String opt() default "";
}
