package com.bm.commont.exception;

import com.bm.commont.enums.IAppEnum;
import lombok.Data;

/**
 * 描述:
 * 自定义返回结果异常
 *
 * @author 北明软件
 * @create 2020-07-29 14:03
 */
@Data
@SuppressWarnings("ALL")
public class IResultException extends IAppException {
    public IResultException() {
        super();
    }

    private Integer code;

    private String message;

    public IResultException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public IResultException(IAppEnum iAppEnum) {
        this.code = iAppEnum.getCode();
        this.message = iAppEnum.getMessage();
    }

}
