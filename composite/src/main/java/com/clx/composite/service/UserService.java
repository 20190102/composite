package com.clx.composite.service;


import com.clx.composite.exception.UserException;
import com.clx.composite.mapper.UserMapper;
import com.clx.composite.model.DTO.UserDTO;
import com.clx.composite.model.VO.LoginVO;
import com.clx.composite.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private LoginVO loginVO;


    /**
     * 登录
     *
     * @param userDTO
     * @return
     */
    public LoginVO login(UserDTO userDTO) throws  UserException {

        if (userMapper.login(userDTO)) {

            //获取密钥
            SecretKey secretKey = JwtUtil.getSecretKey(redisUtil);
            //token有效期
            int timeout = 60 * 60 * 2;
            if (userDTO.isRemember()) timeout = 60 * 60 * 24 * 7;

            //传入密钥，创建token
            String token = JwtUtil.getToken(ConvertUtil.addTimeToDate(timeout), secretKey,userDTO.isRemember());

            loginVO.setCode(HttpStatus.OK.value());
            loginVO.setToken(token);
            loginVO.setMsg("登录成功");

            return loginVO;
        }
        throw new UserException("账号或密码错误");
    }


    /**
     * 用户注册
     *
     * @param userDTO
     * @return
     */
    public LoginVO signUp(UserDTO userDTO) throws UserException {
        //判断该key是否存在
        if (redisUtil.exists(userDTO.getEmail())) {

            //获取验证码，判断与传入的值是否相等
            String code = redisUtil.get(userDTO.getEmail()).toString();
            if (userDTO.getCode().equals(code) ) {

                //往数据库中插入数据
                if (userMapper.signUp(userDTO) > 0) {

                    loginVO.setCode(HttpStatus.OK.value());
                    loginVO.setMsg("注册成功");

                    return loginVO;
                }
            }
        }
        throw new UserException("验证码无效，请重新发送");

    }

    /**
     * 创建，保存，发送验证码
     *
     * @param userDTO
     * @return
     * @throws AddressException
     */
    public LoginVO sendCode(UserDTO userDTO) throws MessagingException, UserException {
        //判断邮箱是否存在
        if (userMapper.hasRepeatEmail(userDTO)==0) {

            //创建一个6个数字的验证码
            long random = new Random().nextLong();
            String code = String.valueOf(random).substring(1, 7);

            //向redis中保存验证码,有效期30分钟
            redisUtil.set(userDTO.getEmail(), code, 30, TimeUnit.MINUTES);

            //创建邮件内容
            String subject = "【composite】测试验证码";
            String Text = "验证码:" + code+"\n验证码30分钟内有效";

            //发送邮件
            EmailUtil.send(userDTO.getEmail(), subject, Text);

            loginVO.setCode(HttpStatus.OK.value());
            loginVO.setMsg("验证码发送成功");
            return loginVO;
        }
        throw new UserException("邮箱已存在");

    }


}
