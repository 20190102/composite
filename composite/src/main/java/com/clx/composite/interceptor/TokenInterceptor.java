package com.clx.composite.interceptor;

import com.clx.composite.model.LoginVO;
import com.clx.composite.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class TokenInterceptor implements HandlerInterceptor {
    @Autowired
    private LoginVO loginVO;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        //从头信息获取token
        String token = request.getHeader("Authorization");
        loginVO.setToken("");
        loginVO.setCode(HttpStatus.UNAUTHORIZED.value());

        if (token != null&&token!="undefined") {
            //如果token信息存在，校验有效性
            String t = JwtUtil.parser(token);
            if (!"".equals(t)) {
                loginVO.setToken(t);
                loginVO.setCode(HttpStatus.OK.value());
            }
        }
        return true;
    }

}
