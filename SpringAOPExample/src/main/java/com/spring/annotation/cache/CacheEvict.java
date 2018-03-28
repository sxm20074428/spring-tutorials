package com.spring.annotation.cache;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 清除缓存
 */
@Target(value = ElementType.METHOD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface CacheEvict {


    /**
     * 缓存中的 key
     * eg:@CacheEvict(key = "userById", field = "#id")
     *
     * @return
     * @author 苏晓蒙
     * @time 2015年4月26日 上午9:29:56
     * @version 0.1
     * @since 0.1
     */
    String key();

    /**
     * 属性:指定参数为常量字符或SpringEL表达式
     *
     * @return
     * @author 苏晓蒙
     * @time 2015年4月26日 上午11:34:57
     * @version 0.1
     * @since 0.1
     */
    String[] field() default "";
}
