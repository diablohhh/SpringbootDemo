package com.example.framework.exception;


import com.example.framework.response.error.ErrorDto;
import com.example.framework.response.error.FieldError;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;

/**
 * 校验自定义异常
 */
public class ValidException extends Exception {
    private String message;
    private ErrorDto errorDto;

    public ValidException() {
    }

    /**
     * 构建校验异常信息
     *
     * @param bindingResult BindingResult
     */
    public ValidException(BindingResult bindingResult) {
        List<FieldError> fieldErrorList = new ArrayList<>();
        bindingResult.getFieldErrors().forEach(fieldError -> {
            String field = fieldError.getField();
            String defaultMessage = fieldError.getDefaultMessage();
            Object value = fieldError.getRejectedValue();
            FieldError itemError = new FieldError();
            itemError.setFieldName(field);
            itemError.setFieldValue(value);
            itemError.setMessage(defaultMessage);

            fieldErrorList.add(itemError);
        });
        this.setMessage("ValidException ERROR");
        ErrorDto errorDto = new ErrorDto();
        errorDto.setErrorMsg(this.getMessage());
        errorDto.setFieldErrors(fieldErrorList);
        this.setErrorDto(errorDto);
    }

    /**
     * 功能描述: 重载参数校验异常，自定义message属性
     */
    public ValidException(BindingResult bindingResult, String msg) {
        List<FieldError> fieldErrorList = new ArrayList<>();
        bindingResult.getFieldErrors().forEach(fieldError -> {
            String field = fieldError.getField();
            String defaultMessage = fieldError.getDefaultMessage();
            Object value = fieldError.getRejectedValue();
            FieldError itemError = new FieldError();
            itemError.setFieldName(field);
            itemError.setFieldValue(value);
            itemError.setMessage(defaultMessage);
            fieldErrorList.add(itemError);
        });
        this.setMessage(msg);
        ErrorDto errorDto = new ErrorDto();
        errorDto.setErrorMsg(this.getMessage());
        errorDto.setFieldErrors(fieldErrorList);
        this.setErrorDto(errorDto);
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ErrorDto getErrorDto() {
        return errorDto;
    }

    public void setErrorDto(ErrorDto errorDto) {
        this.errorDto = errorDto;
    }
}
