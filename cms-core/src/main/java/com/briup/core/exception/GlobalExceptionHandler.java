package com.briup.core.exception;

import com.briup.core.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Object handleException(Exception e) {
        e.printStackTrace();
        Result result = null;
        if (e instanceof ServiceException) {
            log.error(e.getMessage());
            result = Result.failure(((ServiceException) e).getResultCode());
        } else if (e instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException me = (MethodArgumentNotValidException) e;
            // 存储错误结果
            BindingResult bindingResult = me.getBindingResult();
            if (bindingResult.hasFieldErrors()) {
                String defaultMessage = bindingResult.getFieldErrors().get(0).getDefaultMessage();
                return Result.failure(2, defaultMessage);
            }
            return Result.failure(2, "参数异常！");
        } else if (e instanceof DuplicateKeyException) {
            result = Result.failure(500, "该数据已存在,请检查后重新输入!");
        } else {
            log.error(e.getMessage());
            result = Result.failure(500, "服务器意外错误：" + e.getMessage());
        }
        return result;
    }
}
