package com.bayee.political.exception;

/**
 * 操作异常
 * @author xxl
 * @date 2021/4/7
 */
public class HandlerException extends RuntimeException {

    public HandlerException() {
        super();
    }

    /**
     * 指定描述信息异常
     * @param desc
     */
    public HandlerException(String desc) {
        super(desc);
    }

    /**
     * 用指定的详细信息和原因构造一个新的异常
     * @param message
     * @param cause
     */
    public HandlerException(String message, Throwable cause){
        super(message, cause);
    }

}
