package com.kelystor.boatcross.interceptor;

import com.kelystor.boatcross.util.ContextUtil;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeployEnvironmentInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        // 从cookie获取DeployEnvironment设定
        request.setAttribute("currentDeployEnvironment", ContextUtil.currentDeployEnvironment());

        return true;
    }

}