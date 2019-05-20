package com.clx.composite.web;


import com.clx.composite.exception.DataInvalidException;
import com.clx.composite.exception.UserException;
import com.clx.composite.model.DTO.UserDTO;
import com.clx.composite.model.VO.LoginVO;
import com.clx.composite.service.UserService;
import com.clx.composite.utils.CheckUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private LoginVO loginVO;


    /**
     * 使用token登录
     *
     * @return
     */
    @RequestMapping("/loginInit")
    public LoginVO loginInit() {

        loginVO.setCode(HttpStatus.OK.value());
        loginVO.setMsg("token验证成功");

        return loginVO;
    }

    /**
     * 登录
     *
     * @param userDTO
     * @return
     */
    @PostMapping(value = "/login")
    public LoginVO login(@RequestBody UserDTO userDTO) throws DataInvalidException, UserException {

        CheckUtil.checkEmail(userDTO.getEmail());
        CheckUtil.checkPassword(userDTO.getPassword());

        return userService.login(userDTO);
    }

    /**
     * 注册
     *
     * @param userDTO
     * @return
     */
    @PostMapping(value = "/signup")
    public LoginVO register(@RequestBody UserDTO userDTO) throws DataInvalidException, UserException {

        CheckUtil.checkEmail(userDTO.getEmail());
        CheckUtil.checkUsername(userDTO.getUsername());
        CheckUtil.checkPassword(userDTO.getPassword());
        CheckUtil.checkCode(userDTO.getCode());

        return userService.signUp(userDTO);
    }

    /**
     * 发送验证码
     *
     * @param userDTO
     * @return
     * @throws AddressException
     */
    @PostMapping(value = "/sendCode")
    public LoginVO sendCode(@RequestBody UserDTO userDTO) throws DataInvalidException, MessagingException, UserException {

        CheckUtil.checkEmail(userDTO.getEmail());

        LoginVO loginVO = userService.sendCode(userDTO);

        return loginVO;
    }
}
