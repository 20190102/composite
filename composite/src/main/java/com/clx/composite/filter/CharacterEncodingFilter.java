package com.clx.composite.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 拦截请求校验token
 */
public class CharacterEncodingFilter implements Filter {


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
            request.setCharacterEncoding("utf-8");
            response.setCharacterEncoding("utf-8");
            chain.doFilter(request,response);
    }
}
