package com.vaccine.vaccineapi.exception;

/**
 * @author lvhongye
 * @date 2019/07/23
 **/
public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
