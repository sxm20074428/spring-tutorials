package com.sxm.config;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

/**
 * 在Servlet 3.0环境中,容器会在类路径中查找实现javax.servlet.ServletContainerInitializer 接口的类,如果能发现的话,就会用来配置Servlet容器
 * Spring提供了这个接口的实现SpringServletContainerInitializer,这个类反过来又会查找实现WebApplicationInitializer 的类,并将配置的任务交给他们来完成,
 * Spring3.2 引入了一个遍历的WebApplicationInitializer基础实现也就是 AbstractAnnotationConfigDispatcherServletInitializer
 * 因为本类扩展了AbstractAnnotationConfigDispatcherServletInitializer,因此当部署到Servlet3.0容器中的时候,容器就会自动
 * 发现本类,并用本类来配置Servlet上下文
 *
 * @author 苏晓蒙
 * @version 0.1
 * @time 2017/5/17 11:36
 * @since 0.1
 */
public class SpringMvcInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    /**
     * 该方法返回的带有@Configuration注解的的类将会用来配置
     * ContextLoaderListener创建的bean(通常是驱动应用后端的中间层和数据层组件)
     *
     * @author 苏晓蒙
     * @time 2017/5/17 12:38
     * @version 0.1
     * @since 0.1
     */

    @Override
    protected Class[] getRootConfigClasses() {
        return new Class<?>[]{RootConfig.class};
    }

    /**
     * 指定配置类
     * 该方法中要求DispatcherServlet加载应用上下文时,
     * 使用定义在WebConfig配置类中的bean
     * 包括 控制器,视图解析器,处理器映射等bean
     *
     * @author 苏晓蒙
     * @time 2017/5/17 12:40
     * @version 0.1
     * @since 0.1
     */
    @Override
    protected Class[] getServletConfigClasses() {
        return new Class<?>[]{WebMvcConfig.class};
    }

    /**
     * 将DispatcherServlet映射到 "/"
     * 这表示该方法会是应用默认的Servlet,他会处理进入应用的所有请求
     *
     * @author 苏晓蒙
     * @time 2017/5/17 12:39
     * @version 0.1
     * @since 0.1
     */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        return new Filter[]{characterEncodingFilter};
    }
}