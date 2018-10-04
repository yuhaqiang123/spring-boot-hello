package com.muppet.vo.cs;

import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class UserLoginVo implements Serializable {

	@NotNull(message = "user.login.email.null")
	@Email(message = "user.login.email.failed")
	private String email;

	@NotNull(message = "user.login.password.null")
	private String password;

	/*
	 * @NotNull(message="user.login.validateCode.null") private String
	 * validateCode;
	 */

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	private String loginIp;

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}
}
