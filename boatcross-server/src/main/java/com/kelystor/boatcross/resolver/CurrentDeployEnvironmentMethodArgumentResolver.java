package com.kelystor.boatcross.resolver;

import com.kelystor.boatcross.annotation.CurrentDeployEnvironment;
import com.kelystor.boatcross.enums.DeployEnvironment;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * 增加方法注入，将含有 @CurrentDeployEnvironment 注解的方法参数注入用户当前选择的环境
 */
public class CurrentDeployEnvironmentMethodArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().isAssignableFrom(DeployEnvironment.class)
                && parameter.hasParameterAnnotation(CurrentDeployEnvironment.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        return webRequest.getAttribute("currentDeployEnvironment", RequestAttributes.SCOPE_REQUEST);
    }
}