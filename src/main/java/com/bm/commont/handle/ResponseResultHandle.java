package com.bm.commont.handle;

import com.alibaba.fastjson.JSONObject;
import com.bm.commont.dto.Result;
import com.bm.commont.enums.IAppEnum;
import com.bm.commont.exception.IResultException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.reflect.Type;

/**
 * 描述:
 * 全局统一接口返回数据结构
 *
 * @author 北明软件
 * @create 2020-07-30 10:01
 */
@Slf4j
@RestControllerAdvice
@SuppressWarnings("ALL")
public class ResponseResultHandle implements ResponseBodyAdvice {
    public ResponseResultHandle() {
        super();
    }

    /**
     * 在controller将数据进行返回前进行增强操作，当supports方法返回true时才会执行beforeBodyWrite方法
     * 所以对于controller方法本身返回的类型如果时Result，直接返回false，不做处理
     *
     * @param returnType
     * @param converterType
     * @return
     */
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        // 如果返回的是Result则无须进行封装
        return !returnType.getGenericParameterType().equals(Result.class);
    }

    /**
     * 增强接口返回数据格式处理
     *
     * @param body
     * @param returnType
     * @param selectedContentType
     * @param selectedConverterType
     * @param request
     * @param response
     * @return
     */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {

        // 获取接口返回值类型名称
        Type parameterType = returnType.getGenericParameterType();
        String typeName = parameterType.getTypeName();
        // 排除swagger2相关接口返回值类型，否则会出现swaagger-ui无法正常展示
        if (typeName.contains("org.springframework.http.ResponseEntity")) {
            return body;
        }
        // 如果返回的接口没有数据包，则直接返回成功
        if (body == null) {
            return ResultFactory.success();
        }
        // string类型无法直接封装，需要特殊处理成json字符串返回
        if (parameterType.equals(String.class)) {
            try {
                return JSONObject.toJSONString(ResultFactory.success(body));
            } catch (Exception e) {
                // 处理string 字符串异常
                throw new IResultException(IAppEnum.STRING_CONVERT_EXCEPTION);
            }
        }

        return ResultFactory.success(body);
    }
}
