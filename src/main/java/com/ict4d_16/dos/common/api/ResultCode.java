package com.ict4d_16.dos.common.api;

/**
 * 枚举了一些常用API操作码
 * Created by macro on 2019/4/19.
 */
public enum ResultCode implements IErrorCode {
    SUCCESS(200, "Operation success"),
    FAILED(500, "Operation failed"),
    VALIDATE_FAILED(404, "Parameter validation failed"),
    UNAUTHORIZED(401, "Not logged in or token expired"),
    FORBIDDEN(403, "No related permissions");
    private long code;
    private String message;

    private ResultCode(long code, String message) {
        this.code = code;
        this.message = message;
    }

    public long getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
