package com.spring.annotation.log;

import java.lang.annotation.*;

/**
 * 操作日志
 *
 * @author 苏晓蒙
 * @version 0.1
 * @time 2017/7/20 12:54
 * @since 0.1
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {

    /**
     * 操作事件：删除，新增，修改，查询，登录，退出等
     */
    String value();

    /**
     * 字段组装描述内容，
     * 如{"name=名称","status=状态,1=成功;2=失败"}，
     * 表单参数为：name=张三&status=1
     * 这样生成的描述信息为：名称=张三,状态=成功
     */
    String[] entry() default {};

}

