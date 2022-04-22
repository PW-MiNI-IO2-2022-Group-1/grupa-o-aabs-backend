package com.example.SOPSbackend.controller;

import com.example.SOPSbackend.model.BasicUserEntity;
import com.example.SOPSbackend.security.BasicUserDetails;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class FakeAuthArgumentResolver implements HandlerMethodArgumentResolver {
    BasicUserDetails userDetails;

    public FakeAuthArgumentResolver(BasicUserEntity user) {
       userDetails = new BasicUserDetails(user);
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType() == BasicUserDetails.class;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        return userDetails;
    }
}
