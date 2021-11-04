package com.bayee.political.configuration;

import com.bayee.political.exception.HandlerException;
import com.bayee.political.utils.DataListReturn;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 控制器统一异常处理
 *
 * @author xxl
 * @date 2021/4/7
 */
@ControllerAdvice
public class RestControllerExceptionHandler {

    /**
     * 全局异常捕捉处理
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Throwable.class)
    public ResponseEntity<?> errorHandler(Throwable e) {
        logErrorMessage(e);

        if (e instanceof HandlerException) {
            return new ResponseEntity<>(DataListReturn.error(((HandlerException)e).getMessage()), HttpStatus.OK);
        }

        return new ResponseEntity<>(DataListReturn.error("系统异常"), HttpStatus.OK);
    }

    private void logErrorMessage(Throwable e) {
        e.printStackTrace();
    }

}
