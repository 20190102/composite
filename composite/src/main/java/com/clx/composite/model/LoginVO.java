package com.clx.composite.model;

import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Component;

/**
 * 向页面传递的数据
 */
@Component
public class LoginVO {
    private PageInfo<UserDO> info;
    private String token="";
    private int code=0;//状态码
    private String msg="";

    public PageInfo<UserDO> getInfo() {
        return info;
    }

    public void setInfo(PageInfo<UserDO> info) {
        this.info = info;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
