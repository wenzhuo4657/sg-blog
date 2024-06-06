package org.example.handler.exception;


import lombok.extern.slf4j.Slf4j;
import org.example.domain.AppHttpCodeEnum;
import org.example.domain.ResponseResult;
import org.example.exception.SystemException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler   {


    @ExceptionHandler(SystemException.class)
    public ResponseResult systemExceptionHandler(SystemException e){
        log.error("自定义异常",e);
        return  ResponseResult.errorResult(e.getCode(),e.getMsg());

    }

    @ExceptionHandler(Exception.class)
    public  ResponseResult ExceptionHandler(Exception e){
        log.error("异常",e);
        return  ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR.getCode(),e.getMessage());
    }
}
