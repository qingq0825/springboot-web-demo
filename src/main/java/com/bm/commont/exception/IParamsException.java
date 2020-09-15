package com.bm.commont.exception;

import com.bm.commont.enums.IAppEnum;
import lombok.Data;

/**
 * 描述:
 * 自定义参数异常
 *
 * @author 北明软件
 * @create 2020-07-29 14:03
 */
@Data
@SuppressWarnings("ALL")
public class IParamsException extends IAppException {
    public IParamsException() {
        super();
    }

    private Integer code;

    private String message;

    public IParamsException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public IParamsException(IAppEnum iAppEnum) {
        this.code = iAppEnum.getCode();
        this.message = iAppEnum.getMessage();
    }

}
