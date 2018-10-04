package com.muppet.auth.vo.cs;

import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class UserRegisterVo {


    private long registerIp;

    public long getRegisterIp() {
        return registerIp;
    }

    public void setRegisterIp(long registerIp) {
        this.registerIp = registerIp;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //@Length(max=10, min=1, message="{user.nickName.length}")
    //@NotNull(message="{user.nickName.null}")
    private String nickName;


    @Email(message = "user.email.unlegal")
    @NotNull(message = "{user.email.null}")
    private String email;
	
	/*@NotNull(message="user.registerTime.null")
	private Date registerTime;*/
	
	/*@NotNull(message="user.lastLoginTime.null")
	private Date lastLoginTime;*/
	
	/*@NotNull(message="user.registerIp.null")
	private long registerIp;*/

    @Pattern(regexp = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,10}$", message = "user.password.unlegal")
    @NotNull(message = "{user.password.null}")
    private String password;

    @Override
    public String toString() {
        return "UserVo [nickName=" + nickName + ", email=" + email + ", password=" + password + "]";
    }
}
