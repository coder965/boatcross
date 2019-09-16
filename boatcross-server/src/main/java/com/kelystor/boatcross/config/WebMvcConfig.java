package com.kelystor.boatcross.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kelystor.boatcross.converter.WebApiResponseJsonConverter;
import com.kelystor.boatcross.interceptor.DeployEnvironmentInterceptor;
import com.kelystor.boatcross.resolver.CurrentDeployEnvironmentMethodArgumentResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(0, new WebApiResponseJsonConverter(objectMapper));
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new DeployEnvironmentInterceptor()).addPathPatterns("/**");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new CurrentDeployEnvironmentMethodArgumentResolver());
    }
}
