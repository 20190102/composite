package com.clx.composite.model.VO;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.stereotype.Component;

/**
 * 所有VO都要返回的信息
 */
@Component
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class BaseVO {
    private int code;
    private String token;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
