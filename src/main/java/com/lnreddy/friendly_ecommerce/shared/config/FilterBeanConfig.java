package com.lnreddy.friendly_ecommerce.shared.config;


import com.lnreddy.friendly_ecommerce.shared.filters.LoggingFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

@Configuration
public class FilterBeanConfig {

 public FilterRegistrationBean<LoggingFilter> loggingFilter(){
     FilterRegistrationBean<LoggingFilter> frb = new FilterRegistrationBean<>();
     frb.setFilter(new LoggingFilter());
     frb.addUrlPatterns("/*");
     frb.setOrder(Ordered.HIGHEST_PRECEDENCE); // runs early
     return frb;
    // Servlet Container → LoggingFilter → Security → Controller

 }

}
