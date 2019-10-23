package cn.dblearn.blog.portal.common.annotation;

import java.lang.annotation.*;

/**
 * ViewLog
 *
 * @description
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogLike {

     String type();
}
