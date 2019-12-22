package com.muker.config;

import com.muker.filter.ACorsFilter;
import com.muker.filter.AuthFilter;
import com.muker.filter.SmsFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName FilterConfig
 * @Description
 * @Author JavaQ
 * @Date 2019/12/17 21:57
 **/
@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean createFilter() {
        FilterRegistrationBean bean=new FilterRegistrationBean();
        bean.setFilter(new ACorsFilter());
        bean.addUrlPatterns("/*");
        return bean;
    }
    @Bean
    public FilterRegistrationBean createAuFilter() {
        FilterRegistrationBean bean=new FilterRegistrationBean();
        bean.setFilter(new AuthFilter());
        bean.addUrlPatterns("/*");
        return bean;
    }
    @Bean
    public FilterRegistrationBean createSmsFilter() {
        FilterRegistrationBean bean=new FilterRegistrationBean();
        bean.setFilter(new SmsFilter());
        bean.addUrlPatterns("/sms/sendcode.do");
        return bean;
    }
}
