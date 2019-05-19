package com.clx.composite.interceptor;

import com.clx.composite.model.VO.LoginVO;
import com.clx.composite.model.VO.UserListVO;
import com.clx.composite.utils.ConvertUtil;
import com.clx.composite.utils.JwtUtil;
import com.clx.composite.utils.RedisUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Date;

/**
 * 拦截token进行验证
 */

public class TokenInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private LoginVO loginVO;
    @Autowired
    private UserListVO userListVO;



    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        String token = request.getHeader("Authorization");

        try {
            //获取密钥
            SecretKey secretKey = JwtUtil.getSecretKey(redisUtil);
            //解析token
            Claims claims = JwtUtil.parser(token, secretKey);
            //token过期时间
            Date expiration = ConvertUtil.addTimeToDate(60 * 60 * 2);
            if ((Boolean) claims.get("remember")) expiration = claims.getExpiration();

            //创建一个新token
            token = JwtUtil.getToken(expiration, secretKey, (Boolean) claims.get("remember"));

            loginVO.setToken(token);
            userListVO.setToken(token);


        } catch (Exception e) {
            response.setContentType("application/json;charset=UTF-8");

            PrintWriter writer = response.getWriter();

            writer.print("{\"code\":\"401\",\"msg\":\"token无效\"}");
            return false;
        }

        return true;
    }
}
