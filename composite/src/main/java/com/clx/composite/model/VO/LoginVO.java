package com.clx.composite.model.VO;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.stereotype.Component;

/**
 * 登录和注册页面返回的信息
 */
@Component
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class LoginVO extends BaseVO{

}
