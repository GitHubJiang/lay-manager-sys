package com.lay.shop.common.exception;

public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 665112595508596601L;

    private ErrorCodes errorCode;
    
    private String value;

    private Object[] args;

    private String message;
    
    private BusinessException linkedException;

    public BusinessException(ErrorCodes errorCode) {
        super();
        this.setErrorCode(errorCode);
        this.message = errorCode.getMsg();
    }

    public BusinessException(String value) {
        this.value = value;
    }

    public BusinessException(String value, String message) {
        super();
        this.value = value;
        this.message = message;
    }

    public BusinessException(ErrorCodes errorCode, Object[] args) {
        super();
        this.setErrorCode(errorCode);
        this.args = args;
    }

    public BusinessException(ErrorCodes errorCode, String message) {
        this.setErrorCode(errorCode);
        this.message = message;
    }

    public ErrorCodes getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ErrorCodes errorCode) {
        this.errorCode = errorCode;
        this.value = errorCode.getValue();
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public BusinessException getLinkedException() {
        return linkedException;
    }

    public void setLinkedException(BusinessException linkedException) {
        this.linkedException = linkedException;
    }

    
}
