package com.bm.commont.dto;

import com.bm.commont.enums.IAppEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * 描述:
 * 统一返回数据结构
 *
 * @author 北明软件
 * @create 2020-07-29 14:34
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuppressWarnings("ALL")
public class Result<T> {
    public Result() {
        super();
    }

    private Integer code;

    private String message;

    private T data;

    public Result(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Result(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Result(IAppEnum iAppEnum) {
        this.code = iAppEnum.getCode();
        this.message = iAppEnum.getMessage();
    }

    public Result(IAppEnum iAppEnum, T data) {
        this.code = iAppEnum.getCode();
        this.message = iAppEnum.getMessage();
        this.data = data;
    }


}
