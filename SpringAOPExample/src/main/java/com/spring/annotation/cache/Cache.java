package com.spring.annotation.cache;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用于处理缓存读取 以及保存数据到缓存中
 * key和field共同决定了缓存中的实际key
 * @Cache(key = "userById", field = "#id",expire=10,condition="#id<100")
 * public User queryById(Integer id) {
 * return userDao.getUserById(id);
 * }
 */
@Target(value = ElementType.METHOD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface Cache {
    /**
     * 缓存中的 key
     *
     * @return
     * @author 苏晓蒙
     * @time 2015年4月26日 上午9:29:56
     * @version 0.1
     * @since 0.1
     */
    String key();

    /**
     * 属性
     * 动态值，指定参数为常量字符或SpringEL表达式,eg:field = "#id"
     *
     * @return
     * @author 苏晓蒙
     * @time 2015年4月26日 上午11:34:57
     * @version 0.1
     * @since 0.1
     */
    String field() default "";

    /**
     * 缓存过期时间 默认 3600 根据具体业务（不改变的数据）可以设置更长 ，如 系统中的区域信息
     *
     * @return
     * @author 苏晓蒙
     * @time 2015年4月26日 上午9:31:02
     * @version 0.1
     * @since 0.1
     */
    int expire() default 3600;

    /**
     * 缓存条件:设置是否使用缓存结果为【SpringEL表达式】
     * eg:condition="#id<100"
     *
     * @return
     * @author 苏晓蒙
     * @time 2015年7月4日 上午8:33:06
     * @version 0.1
     * @since 0.1
     */
    String condition() default "";

}
