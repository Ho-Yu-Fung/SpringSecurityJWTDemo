package com.mp.filter;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mp.common.response.ResponseUtils;
import com.mp.entity.User;
import com.mp.utils.JWTUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

/**
 * 处理登录请求的过滤器
 * Token登录认证过滤器
 *AbstractAuthenticationProcessingFilter
 */

public class JWTLoginFilter extends UsernamePasswordAuthenticationFilter {
    /**授权管理类*/
    private AuthenticationManager authenticationManager;
    /**
     *
     * setFilterProcessesUrl 配置后端登录接口
     * 当调用该接口直接执行 attemptAuthentication 方法
     * @param authenticationManager
     */
    public JWTLoginFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        this.setPostOnly(false);
        this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/auth/login","POST"));
    }

    /**
     * 登录接口 AntPathRequestMatcher路径传参
     * @param request
     * @param response
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        try {
            System.out.println("UsernamePasswordAuthenticationFilter->attemptAuthentication获取请求传参");
            User user = new ObjectMapper().readValue(request.getInputStream(), User.class);
            System.out.println(user);
            return authenticationManager.
                    authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        } catch (IOException e) {
            e.printStackTrace();
            return super.attemptAuthentication(request,response);
        }
    }

    /**
     * 认证成功 调用该方法
     * Authentication即attemptAuthentication认证返回对象
     * @param request
     * @param response
     * @param chain
     * @param authResult
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult)
            throws IOException, ServletException {
        //验证通过后得到对应的对象信息
        User securityUser = (User) authResult.getPrincipal();
        //通过对象信息生成token
        String token = JWTUtils.createToken(securityUser.getUsername());
        //将token放入请求头 继续执行过滤器链
        response.setHeader(JWTUtils.TOKEN_HEADER_KEY,token);
        //响应给前端信息
        HashMap<String, Object> res = new HashMap<>();
        res.put("code",200);
        res.put("msg","OK!");
        res.put(JWTUtils.TOKEN_HEADER_KEY,token);
        ResponseUtils.transmitMsg(response,res);

    }

    /**
     * 认证失败 调用该方法 返回错误信息
     * @param request
     * @param response
     * @param failed
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed)
            throws IOException, ServletException {
        HashMap<String, Object> res = new HashMap<>();
        res.put("code",401);
        res.put("msg","Authentication failed");
        ResponseUtils.transmitMsg(response,res);
        super.unsuccessfulAuthentication(request, response, failed);
    }
}
