package com.muppet.vo.error;

import java.io.Serializable;

public class ErrorCode implements Serializable {
    private Integer code;

    private String message;


    public Integer getCode() {
        return code;
    }

    public ErrorCode setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ErrorCode setMessage(String message) {
        this.message = message;
        return this;
    }
}
