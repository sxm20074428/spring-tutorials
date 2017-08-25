package com.sxm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.resource.GzipResourceResolver;
import org.springframework.web.servlet.resource.VersionResourceResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.concurrent.TimeUnit;

/**
 * @author 苏晓蒙
 * @version 0.1
 * @time 2017/5/17 12:46
 * @since 0.1
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.sxm.spring.controller"})
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    /**
     * 配置视图解析器
     *
     * @return
     */
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        // 视图前缀
        resolver.setPrefix("/WEB-INF/views/");
        // 视图后缀,文件类型
        resolver.setSuffix(".jsp");
        resolver.setExposeContextBeansAsAttributes(true);
        return resolver;
    }

    /**
     * 配置静态资源的处理
     *
     * @param
     * @return
     * @author 苏晓蒙
     * @time 2017/5/17 16:36
     * @version 0.1
     * @since 0.1
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/static/**")   //映射 /static 的请求到 classpath 下的 static 目录
                .addResourceLocations("/static/")//
                .setCacheControl(CacheControl.maxAge(10, TimeUnit.MINUTES)//Http 缓存
                        .cachePrivate())//
                .resourceChain(false)// resourceChain(false) 的作用关闭 chain cache,方便调试
                .addResolver(new GzipResourceResolver())//gzip 压缩
                .addResolver(new VersionResourceResolver().addContentVersionStrategy("/**"))// 添加 VersionResourceResolver
        ;
    }

    /**
     * 访问到静态资源，交给默认的 Servlet
     *
     * @param
     * @return
     * @author 苏晓蒙
     * @time 2017/8/24 11:18
     * @version 0.1
     * @since 0.1
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

}
