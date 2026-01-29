package com.lnreddy.friendlyecommerce.shared.config;


import com.lnreddy.friendlyecommerce.shared.filters.LoggingFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@RequiredArgsConstructor
public class FilterBeanConfig {

    private final CorsConfiguration corsConfiguration;


    @Bean
    public FilterRegistrationBean<LoggingFilter> loggingFilter(){
     FilterRegistrationBean<LoggingFilter> frb = new FilterRegistrationBean<>();
     frb.setFilter(new LoggingFilter());
     frb.addUrlPatterns("/*");
     frb.setOrder(Ordered.HIGHEST_PRECEDENCE); // runs early
     return frb;
    // Servlet Container → LoggingFilter → Security → Controller

 }



    @Bean
    public FilterRegistrationBean<CorsFilter> simpleCorsFilter(){
        UrlBasedCorsConfigurationSource source=new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",corsConfiguration);

        FilterRegistrationBean<CorsFilter> registrationBean=new FilterRegistrationBean<>(new CorsFilter(source));
        registrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);

        return registrationBean;
    }

}
