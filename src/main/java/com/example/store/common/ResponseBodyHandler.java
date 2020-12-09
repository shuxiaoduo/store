package com.example.store.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 拦截Controller方法默认返回参数，统一处理返回值/响应体
 */

/**
 * 注解有@ControllerAdvice的类， 需要在具体方法上同时添加@ExceptionHandler和@ResponseBody注解；
 * 注解有@RestControllerAdvice的类，只需要在具体方法上添加@ExceptionHandler注解。
 */
@RestControllerAdvice(basePackages = {"com.example.store.controller"})
public class ResponseBodyHandler implements ResponseBodyAdvice {
    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        //返回true，执行beforeBodyWrite方法
        return true;
    }

    /**
     *
     * @param body      响应体
     * @param returnType   响应的数据类型
     * @param mediaType     响应的ContentType
     * @param aClass
     * @param request
     * @param response
     * @return  被修改后的响应体，可以为null，表示没有任何响应
     */
    @SneakyThrows
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType mediaType, Class aClass, ServerHttpRequest request, ServerHttpResponse response) {
        if(returnType.getParameterType().isInstance(Result.class)){
            return body;
        }
        if(returnType.getParameterType() == String.class){
            response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
            return new ObjectMapper().writeValueAsString(new Result().success().setMessage((String) body));
        }
        // 无包装，且返回体为空，设置404
        if (body == null) {
            response.setStatusCode(HttpStatus.NOT_FOUND);
            return new Result().setStatus(ResultStatus.NOT_FOUND)
                    .setMessage("找不到资源");
        }

        return new Result().success(body);
    }
}
