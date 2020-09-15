package com.bm.commont.handle;

import com.bm.commont.dto.Result;
import com.bm.commont.enums.IAppEnum;
import com.bm.commont.exception.IAppException;
import com.bm.commont.exception.IParamsException;
import com.bm.commont.exception.IResultException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

/**
 * 描述:
 * 全局异常处理类
 *
 * @author 北明软件
 * @create 2020-07-29 14:32
 */
@Slf4j
@RestControllerAdvice
@SuppressWarnings("ALL")
public class GlobalExceptionHandle {
    public GlobalExceptionHandle() {
        super();
    }

    /**
     * 全局异常，统一返回异常数据
     *
     * @param e 异常信息
     * @return
     */
    @ExceptionHandler(IAppException.class)
    public Result iApiExceptionHandler(IAppException e) {
        log.error("统一自定义异常处理：异常信息.error==>", e);
        return ResultFactory.failed(e.getCode(), e.getMessage());
    }

    /**
     * 全局异常，参数异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(IParamsException.class)
    public Result iParamsException(IParamsException e) {
        log.error("统一自定义异常处理：参数异常信息.error==>", e);
        return ResultFactory.failed(e.getCode(), e.getMessage());
    }

    /**
     * 全局异常，返回结果
     *
     * @param e
     * @return
     */
    @ExceptionHandler(IResultException.class)
    public Result iResultException(IResultException e) {
        log.error("统一自定义异常处理：返回结果异常信息.error==>", e);
        return ResultFactory.success(e.getMessage());
    }

    /**
     * 全局异常，参数校验异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result iParamsException(MethodArgumentNotValidException e) {
        log.error("统一自定义异常处理：Valid参数校验异常信息.error==>", e);
        ObjectError objectError = e.getBindingResult().getAllErrors().get(0);
        return ResultFactory.failed(IAppEnum.PARAMS_EXCEPTION.getCode(), objectError.getDefaultMessage());
    }

    /**
     * 全局异常，参数校验异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public Result iParamsException(ConstraintViolationException e) {
        log.error("统一自定义异常处理：Valid参数校验异常信息.error==>", e);
        return ResultFactory.failed(IAppEnum.PARAMS_EXCEPTION.getCode(), e.getMessage());
    }


}
