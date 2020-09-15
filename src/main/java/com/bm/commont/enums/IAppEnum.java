package com.bm.commont.enums;

import lombok.Getter;

/**
 * 描述:
 * 全局公共枚举类
 *
 * @author 北明软件
 * @create 2020-07-29 14:06
 */
@Getter
public enum IAppEnum {

    /**
     * 接口请求成功
     */
    SUCCESS(1, "请求成功"),
    /**
     * 接口请求失败
     */
    FAILED(0, "请求失败"),

    /**
     * 传参异常
     */
    PARAMS_EXCEPTION(10001, "参数异常"),
    /**
     * 查询无数据
     */
    QUERY_RESULT_IS_EMPTY_EXCEPTION(10002, "查询没有数据"),

    /**
     * 字符串转换异常
     */
    STRING_CONVERT_EXCEPTION(10003, "字符串转换异常"),

    ;

    /**
     * code 码
     */
    private Integer code;

    /**
     * 说明
     */
    private String message;

    IAppEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }


}
