package com.muppet.service;

import com.muppet.vo.cs.UserLoginVo;
import com.muppet.vo.cs.UserRegisterVo;
import com.muppet.vo.sc.UserToken;

import java.util.Map;

/**
 * 用户相关操作
 * @author yuhaiqiang  yuhaiqiangvip@sina.com
 * @time 2017年5月9日 下午9:01:18
 */
public interface UserService{

	public Map<String, String> register(UserRegisterVo user) ;
	
	public UserToken login(UserLoginVo user) ;
	
	public boolean emailUnique(String email);
	
	/**
	 * <p>1. 负责用户操作部分的验证码生成.
	 * <p>2. 将生成的验证码缓存 key: JSESSIONID + validateCode, value: validateCode.
	 * 
	 * @param sessionId 用户的JSESSIONID
	 * @return 验证码
	 */
	public String generateValidateCode(String sessionId);
	
	
	/**
	 * 根据JSESSIONID查找相关的验证码.如果验证码已过期,或者
	 * 
	 * @param sessionId 用户的JSESSIONID
	 * @return 与JSESSIONID绑定的验证码.如果没有为JSESSIONID生成过
	 * 验证码,或者验证码过期,返回 null
	 */
	public String getValidateCode(String sessionId);
	
	/**
	 * 
	 * @param sessionId
	 */
	public String removeValidateCode(String sessionId);
}
