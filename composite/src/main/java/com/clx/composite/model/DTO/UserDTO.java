package com.clx.composite.model.DTO;

import com.clx.composite.utils.ConvertUtil;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class UserDTO {
    private int id;
    private String email;
    private String password;
    private String username;
    private String signUpDay = ConvertUtil.dataFormat(new Date());
    private String sex="1";
    private String birthday="1980-01-01";
    private String permission="1";
    private String code;    //验证码
    private boolean remember;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSignUpDay() {
        return signUpDay;
    }

    public void setSignUpDay(String signUpDay) {
        this.signUpDay = signUpDay;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isRemember() {
        return remember;
    }

    public void setRemember(boolean remember) {
        this.remember = remember;
    }

}
