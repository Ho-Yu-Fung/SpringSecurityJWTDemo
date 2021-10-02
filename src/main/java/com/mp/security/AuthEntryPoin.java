package com.mp.security;

import com.alibaba.fastjson.JSON;
import com.mp.common.response.ResponseUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * 未授权的处理类
 */
public class AuthEntryPoin implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        HashMap<String, Object> info = new HashMap<>();
        info.put("code",400);
        info.put("msg","Unauthorized");
        ResponseUtils.transmitMsg(httpServletResponse,info);
    }
}
