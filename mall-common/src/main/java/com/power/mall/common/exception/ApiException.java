package com.power.mall.common.exception;

import com.power.mall.common.api.IErrorCode;

/**
 * @Name 未命名
 * @Author SCH
 * @Date 2024/9/12 19:41
 */
public class ApiException extends RuntimeException {

    private IErrorCode errorCode;

    public ApiException(IErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ApiException(String message) {
        super(message);
    }
    public ApiException(Throwable cause){super( cause);}

    public ApiException(String message,Throwable cause){super(message,cause);}

    public IErrorCode getErrorCode() {return errorCode;}
}
