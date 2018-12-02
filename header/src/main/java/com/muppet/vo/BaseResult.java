package com.muppet.vo;

import com.muppet.vo.error.ErrorCode;

public class BaseResult {
    protected Boolean success = true;

    protected ErrorCode errorCode;

    public <T extends BaseResult> T cast() {
        return (T) this;
    }

    public Boolean getSuccess() {
        return success;
    }

    public BaseResult setSuccess(Boolean success) {
        this.success = success;
        return this;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public BaseResult setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
        return this;
    }
}
