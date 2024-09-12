package com.power.mall.common.exception;

import com.power.mall.common.api.IErrorCode;

/**
 * @Name 未命名
 * @Author SCH
 * @Date 2024/9/12 19:39
 */
public class Asserts {
    public static void fail(String message) {throw new ApiException(message);}
    public static void fail(IErrorCode errorCode) {throw new ApiException(errorCode);}
}
