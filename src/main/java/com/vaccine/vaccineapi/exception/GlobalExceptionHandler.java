package com.vaccine.vaccineapi.exception;

import com.vaccine.vaccineapi.domain.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {BusinessException.class})
    @ResponseBody
    public BaseResponse exceptionHandler(BusinessException e, HttpServletResponse response) {
        log.error("exceptionHandler.BusinessException", e);
        return BaseResponse.failed(e.getMessage());
    }

    @ExceptionHandler(value = {LogoutException.class})
    @ResponseBody
    public BaseResponse exceptionHandler(LogoutException e, HttpServletResponse response) {
        log.error("exceptionHandler.LogoutException", e);
        return BaseResponse.invalid(e.getMessage(), null);
    }

    @ExceptionHandler(value = {Exception.class})
    @ResponseBody
    public BaseResponse exceptionHandler(Exception e, HttpServletResponse response) {
        log.error("exceptionHandler.Exception", e);
        return BaseResponse.failed("网络有卡顿，正在处理中");
    }

    /**
     * 用来处理bean validation异常
     * @param ex
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public  BaseResponse resolveConstraintViolationException(ConstraintViolationException ex){
        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
        if(!CollectionUtils.isEmpty(constraintViolations)){
            StringBuilder msgBuilder = new StringBuilder();
            for(ConstraintViolation constraintViolation :constraintViolations){
                msgBuilder.append(constraintViolation.getMessage()).append(",");
            }
            String errorMessage = msgBuilder.toString();
            if(errorMessage.length()>1){
                errorMessage = errorMessage.substring(0,errorMessage.length()-1);
            }
            return BaseResponse.failed(errorMessage);

        }
        return BaseResponse.failed(ex.getMessage());

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public BaseResponse resolveMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        List<ObjectError> objectErrors = ex.getBindingResult().getAllErrors();
        if(!CollectionUtils.isEmpty(objectErrors)) {
            StringBuilder msgBuilder = new StringBuilder();
            for (ObjectError objectError : objectErrors) {
                msgBuilder.append(objectError.getDefaultMessage()).append(",");
            }
            String errorMessage = msgBuilder.toString();
            if (errorMessage.length() > 1) {
                errorMessage = errorMessage.substring(0, errorMessage.length() - 1);
            }
            return BaseResponse.failed(errorMessage);
        }
        return BaseResponse.failed(ex.getMessage());

    }

}
