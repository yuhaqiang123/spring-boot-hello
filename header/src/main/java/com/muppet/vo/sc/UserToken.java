package com.muppet.vo.sc;

import com.muppet.vo.BaseResult;
import com.muppet.vo.error.ErrorCode;

import java.io.Serializable;
import java.util.Date;

public class UserToken extends BaseResult implements Serializable {
	private String userId;

	private String nickName;

	private Date loginTime;

	private String loginIp;

	private String tokenId;

	public static UserToken errorUserToken() {
		return new UserToken().setSuccess(false)
				.setErrorCode(new ErrorCode().setCode(1).setMessage("error")).cast();
	}

	public String getUserId() {
		return userId;
	}

	public UserToken setUserId(String userId) {
		this.userId = userId;
		return this;
	}

	public String getNickName() {
		return nickName;
	}

	public UserToken setNickName(String nickName) {
		this.nickName = nickName;
		return this;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public UserToken setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
		return this;
	}

	public String getLoginIp() {
		return loginIp;
	}

	public UserToken setLoginIp(String loginIp) {
		this.loginIp = loginIp;
		return this;
	}

	public String getTokenId() {
		return tokenId;
	}

	public UserToken setTokenId(String tokenId) {
		this.tokenId = tokenId;
		return this;
	}
}
