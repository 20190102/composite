package com.clx.composite.web;


import com.clx.composite.model.LoginVO;
import com.clx.composite.model.RequestDTO;
import com.clx.composite.service.UserService;

import com.clx.composite.utils.CaptchaUtil;
import com.clx.composite.utils.CheckUtil;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@ResponseBody
//开启跨域访问，方便测试
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private LoginVO loginVO;

    /**
     * 该方法是进入登录页面时，验证token，实现自动登录
     * @return
     */
    @RequestMapping("/loginInit")
    public LoginVO loginInit() {
        return loginVO;
    }

    /**
     *登录
     * @param requestDTO username email remember
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public LoginVO login(@RequestBody RequestDTO requestDTO) {
        loginVO.setToken("");
        loginVO.setCode(HttpStatus.UNAUTHORIZED.value());
        //校验数据
        if(CheckUtil.checkEmailAndPwd(requestDTO.getEmail(), requestDTO.getPassword())){

            String token =userService.login(requestDTO);
            if(!"".equals(token)){
                loginVO.setToken(token);
                loginVO.setCode(HttpStatus.OK.value());
            }
        }
        return loginVO;
    }

    /**
     * 用户注册
     * @param requestDTO
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public boolean register(@RequestBody RequestDTO requestDTO) {
        //校验邮箱与验证码是否匹配
        if(CaptchaUtil.validateCode(requestDTO.getEmail(), requestDTO.getVerificationCode())){
            return userService.register(requestDTO);
        }
        return false;

    }

    /**
     * 发送验证码
     * @param requestDTO email
     * @return
     */
    @RequestMapping(value = "/sendCode", method = RequestMethod.POST)
    public boolean sendCode(@RequestBody RequestDTO requestDTO) {
        return userService.sendCode(requestDTO);
    }
}
