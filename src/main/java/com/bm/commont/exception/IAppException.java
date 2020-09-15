package com.bm.commont.exception;

import com.bm.commont.enums.IAppEnum;
import lombok.Data;

/**
 * 描述:
 * 全局异常
 *
 * @author 北明软件
 * @create 2020-07-29 14:05
 */
@Data
@SuppressWarnings("ALL")
public class IAppException extends RuntimeException {
    public IAppException() {
        super();
    }

    private Integer code;

    private String message;

    public IAppException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public IAppException(IAppEnum iAppEnum) {
        this.code = iAppEnum.getCode();
        this.message = iAppEnum.getMessage();
    }
}
