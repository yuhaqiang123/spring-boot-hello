package com.muppet;

import java.io.Serializable;

public class EmailResponse implements Serializable {
    private Boolean success;

    public Boolean getSuccess() {
        return success;
    }

    public EmailResponse setSuccess(Boolean success) {
        this.success = success;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public EmailResponse setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    private String msg;


}
