package com.spring.annotation.cache;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 点击量缓存标签 用于当用户点击某篇文章时 点击量不更新数据库中 而是放入缓存
 *
 * @author 苏晓蒙
 * @version 0.1
 * @time 2015年5月18日 上午11:59:59
 * @since 0.1
 */
@Target(value = ElementType.METHOD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface VisitorCache {
    /**
     * 需要更新的表名
     *
     * @return
     * @author 苏晓蒙
     * @time 2015年5月18日 下午12:01:42
     * @version 0.1
     * @since 0.1
     */
    String tableName();

    /**
     * 需要累加的表字段
     *
     * @return
     * @author 苏晓蒙
     * @time 2015年5月18日 下午12:02:09
     * @version 0.1
     * @since 0.1
     */
    String tableColumn();

    /**
     * 操作的id值,常量或者springEl表达式
     * :@VisitorCache(tableName = "t_user", tableColumn = "visit_count", idName = "id", id = "#id")
     *
     * @return
     * @author 苏晓蒙
     * @time 2015年5月18日 下午3:31:50
     * @version 0.1
     * @since 0.1
     */

    String id();

    /**
     * 数据库表中主键的名称
     *
     * @return
     * @author 苏晓蒙
     * @time 2015年6月8日 上午11:28:30
     * @version 0.1
     * @since 0.1
     */
    String idName();

}
