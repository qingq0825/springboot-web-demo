package com.bm.commont.handle;

import com.bm.commont.dto.Result;
import com.bm.commont.enums.IAppEnum;
import lombok.Data;

/**
 * 描述:
 * 统一接口返回数据，工厂类
 *
 * @author 北明软件
 * @create 2020-07-29 14:34
 */
@Data
@SuppressWarnings("ALL")
public class ResultFactory<T> {
    public ResultFactory() {
        super();
    }

    /**
     * 请求成功，无返回数据
     *
     * @param iAppEnum
     * @return
     */
    public static <T> Result<T> success() {
        return new Result(IAppEnum.SUCCESS);
    }

    /**
     * 请求成功，返回数据
     *
     * @param iAppEnum
     * @return
     */
    public static <T> Result<T> success(T data) {
        return new Result(IAppEnum.SUCCESS, data);
    }

    /**
     * 请求成功，自定义状态码，返回数据
     *
     * @param iAppEnum
     * @param data
     * @return
     */
    public static <T> Result<T> success(IAppEnum iAppEnum, T data) {
        return new Result(iAppEnum, data);
    }

    /**
     * 请求失败
     *
     * @return
     */
    public static Result failed() {
        return new Result(IAppEnum.FAILED);
    }

    /**
     * 请求失败,自定义失败信息
     *
     * @param iAppEnum
     * @return
     */
    public static <T> Result<T> failed(Integer code, String message) {
        return new Result(code, message);
    }
}
