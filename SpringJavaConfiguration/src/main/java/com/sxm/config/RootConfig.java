package com.sxm.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.sxm.spring.service", "com.sxm.spring.dao"})
public class RootConfig {

}