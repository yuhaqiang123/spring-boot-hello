package com.muppet.auth.controller;

import com.muppet.auth.common.MS;
import com.muppet.auth.common.NetUtil;
import com.muppet.service.UserService;
import com.muppet.auth.transfer.AppResult;
import com.muppet.vo.cs.EmailVo;
import com.muppet.vo.cs.UserLoginVo;
import com.muppet.vo.cs.UserRegisterVo;
import com.muppet.vo.sc.UserToken;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;


/***
 * <p>用户操作相关的Controller.
 *
 * @author yuhaiqiang  yuhaiqiangvip@sina.com
 * @time 2017年5月8日 下午7:04:06
 *
 * @see UserService
 */
@CrossOrigin(origins = "*", maxAge = 3600, allowCredentials = "true", methods = {RequestMethod.GET, RequestMethod.POST})
@RequestMapping("/user")
@Controller
public class UserController {

    @Autowired
    private MS ms;

    private Logger logger = Logger.getLogger(this.getClass());

    @Autowired()
    private UserService userService;

    /**
     * <p>分解 {@link Errors},解析出验证失败的字段.
     *
     * @param ms
     * @param errors
     * @return
     */
    private Map<String, Object> parseError(MS ms, Errors errors, HttpServletRequest request) {
        if (errors == null || errors.hasErrors() == false) {
            return null;
        }

        Map<String, Object> map = new HashMap<String, Object>();
        for (FieldError error : errors.getFieldErrors()) {
            map.put(error.getField(), ms.getMessage(error.getDefaultMessage(), null, request));
        }
        return map;
    }

    private Map<String, Object> parseError(MS ms, BindingResult error, HttpServletRequest request) {
        logger.debug(error);
        if (error == null || error.hasErrors() == false) {
            return null;
        }

        Map<String, Object> map = new HashMap<String, Object>();
        map.put(error.getFieldError().getField(), ms.getMessage(error.getFieldError().getDefaultMessage(), request));
        return map;
    }


    /**
     * <p>判断邮箱是否被注册过.如果被注册,返回错误信息
     * 要注意,即使验证邮箱尚未被验证,用户实际注册时,也需要
     *
     * @param emailVo 被验证的email
     * @return <p> 先验证邮箱.如果验证失败.
     * 返回 status=0,msg=验证失败的消息
     * <p> 如果邮箱不存在 那么返回 成功
     * status=1,result=""
     * <p>如果邮箱存在,那么返回 失败
     * status=0, result=""
     */
    @ResponseBody
    @RequestMapping("/email/validate/unique")
    public AppResult emailValidateUnique(
            @Valid EmailVo emailVo
            , Errors emailError
            , HttpServletRequest request) {
        Object object = parseError(ms, emailError, request);
        if (null != object) {
            return new AppResult(0, ms.getMessage("user.register.email.unlegal", request), object);
        }
        String email = emailVo.getEmail();
        boolean success = userService.emailUnique(email);
        if (success) {
            return new AppResult(1, ms.getMessage("user.register.email.unexisted", request));
        } else {
            return new AppResult(0, ms.getMessage("user.register.email.existed", request));
        }
    }

    /**
     * <p>用户登录账户时需要获取验证码
     * 获取并返回给前台,并在session保存.
     *
     * <p> 之后可以考虑使用redis保存
     *
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping("/apply/validatecode")
    public AppResult applyRegisterValidateCode(HttpSession session) {
        String code = userService.generateValidateCode(session.getId());
        return new AppResult(1, "ok", code);
    }


    /**
     * <p>用户注册服务
     * <p>return 当参数验证失败 code 0: msg:user.register.failed. result:validateCode
     * <p>return 当验证码验证失败时, code:2 msg:user.validateCode.incorrect. result:validateCode
     * <p>return 当email验证失败时, code:3, msg:user.register.failed. result:
     * <li>email:user.register.email.existed.</li>
     * <li>validateCode:code</li>
     *
     * <p>return 当出现其他失败 code: -1, msg:user.register.failed
     * nickName,email,password
     * email地址必须唯一.
     *
     * @param userVo <li>email 邮箱<li>password 密码
     * @param errors 验证错误信息
     * @return
     */
    @RequestMapping("/register")
    @ResponseBody
    public AppResult userRegister(@Valid @ModelAttribute UserRegisterVo userVo
            , Errors errors
            , @RequestParam(name = "clientCode", required = true) String clientCode
            , HttpSession session
            , HttpServletRequest request) {

        Map map = parseError(ms, errors, request);
        if (null != map) {
            String code = userService.generateValidateCode(session.getId());
            map.put("validateCode", code);
            return new AppResult(0, ms.getMessage("user.register.failed", request), map);
        } else {
            AppResult result = validateCode(clientCode, session, request);
            if (result != null) {
                return result;
            }

            /**
             * 设置Ip
             */
            Long ip = NetUtil.getIpLong(request);
            userVo.setRegisterIp(ip);

            Map<String, String> registerError = userService.register(userVo);
            if (registerError == null) {

                //没有错误,注册成功
                return new AppResult(1
                        , ms.getMessage("user.register.success", request));
            } else {
                if (registerError.containsKey("email")) {
                    String msgCode = registerError.get("email");
                    registerError.put("email", ms.getMessage(msgCode, request));

                    //重新生成validateCode
                    String code = userService.generateValidateCode(session.getId());
                    registerError.put("validateCode", code);

                    return new AppResult(3, ms.getMessage("user.register.failed", request), registerError);
                }
                return new AppResult(-1, ms.getMessage("user.register.failed", request));
            }
        }
    }

    private AppResult validateCode(String clientCode, HttpSession session, HttpServletRequest request) {
        String serverCode = userService.removeValidateCode(session.getId());
        if (serverCode == null || !serverCode.equals(clientCode)) {
            //logger.debug(serverCode + ":" + clientCode);
            /**
             * <p>服务器端验证码不存在,或过期,或不匹配
             * 重新生成验证码,返回前端
             */
            Map result = new HashMap<String, Object>();
            String code = userService.generateValidateCode(session.getId());
            result.put("validateCode", code);
            return new AppResult(2, ms.getMessage("user.validateCode.incorrect", request), result);
        }
        return null;
    }

    /**
     * @param userLogin
     * @param errors
     * @param request
     * @return
     */
    @RequestMapping("/login")
    @ResponseBody
    public AppResult userLogin(@Valid UserLoginVo userLogin, Errors errors
            , @RequestParam String clientCode
            , HttpServletRequest request
            , HttpSession session) {

        AppResult result = validateCode(clientCode, session, request);
        if (result != null) {
            return result;
        }

        Map map = parseError(ms, errors, request);
        if (null != map) {
            String code = userService.generateValidateCode(session.getId());
            map.put("validateCode", code);
            return new AppResult(0, ms.getMessage("user.login.failed", request), map);
        } else {
            userLogin.setLoginIp(NetUtil.getRemortIP(request));
            UserToken info = userService.login(userLogin);

            if (info == null) {
                String code = userService.generateValidateCode(session.getId());
                return new AppResult(0, ms.getMessage("user.login.failed.info", request),
                        code);
            } else {

                return new AppResult(1, ms.getMessage("user.login.success", request), info);
            }
        }
    }


}
