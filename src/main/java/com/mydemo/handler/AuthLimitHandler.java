package com.mydemo.handler;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author wuwei
 * @create 2019/10/8 11:40
 * @desc
 **/
public class AuthLimitHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest req, HttpServletResponse res, AccessDeniedException e) throws IOException, ServletException {
        System.out.println("你没有权限访问地址:"+req.getRequestURI());
        res.sendRedirect("/limit");

    }
}
