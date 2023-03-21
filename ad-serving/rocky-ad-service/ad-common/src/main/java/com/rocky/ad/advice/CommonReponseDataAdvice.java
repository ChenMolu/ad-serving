package com.rocky.ad.advice;

import com.rocky.ad.annotation.IgnoreResponseAdvice;
import com.rocky.ad.vo.CommonResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;


/**
 * 通用响应请求
 * 实现对响应的统一拦截
 */
@RestControllerAdvice
public class CommonReponseDataAdvice implements ResponseBodyAdvice<Object> {
    @Override
    @SuppressWarnings("all")
    public boolean supports(MethodParameter methodParameter,
                            Class<? extends HttpMessageConverter<?>> aClass) {
        // 通过methodParameter的声明方法拿到类的声明，
        // 如果类的声明被注解标识，我们就不想他被CommonResponse影响
        if (methodParameter.getDeclaringClass().isAnnotationPresent(
                IgnoreResponseAdvice.class
        )) {
            return false;
        }

        if (methodParameter.getMethod().isAnnotationPresent(
                IgnoreResponseAdvice.class
        )) {
            return false;
        }

        return true;
    }

    @Nullable
    @Override
    @SuppressWarnings("all")
    public Object beforeBodyWrite(@Nullable Object o,
                                  MethodParameter methodParameter,
                                  MediaType mediaType,
                                  Class<? extends HttpMessageConverter<?>> aClass,
                                  ServerHttpRequest serverHttpRequest,
                                  ServerHttpResponse serverHttpResponse) {
        // 默认情况code=0，message=""，代表没有问题的响应
        CommonResponse<Object> response = new CommonResponse<>(0, "");

        if (o == null) { //如果对象为null，直接返回，代表对象里面data为空
            return response;
        } else if (o instanceof CommonResponse) { //如果o直接是一个CommonResponse对象，就不需要多加处理
            response = (CommonResponse<Object>) o;
        } else { //o是一个普通的返回对象，需要包装成CommonResponse
            response.setData(o);
        }

        return response;
    }
}
