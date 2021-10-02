package com.mp.filter;

import com.mp.entity.Role;
import com.mp.mapper.PermMapper;
import com.mp.mapper.RoleMapper;
import com.mp.utils.JWTUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/***
 * Token 授权类
 * 通过上游过滤器UsernamePasswordAuthenticationFilter
 * 传的user信息获取对应权限进行授权
 *
 */
@Slf4j
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    private PermMapper permMapper;

    private RoleMapper roleMapper;

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager,PermMapper permMapper,RoleMapper roleMapper) {
        super(authenticationManager);
        this.permMapper = permMapper;
        this.roleMapper = roleMapper;
    }
    /**
     * 得到请求头信息 授权
     * @param request
     * @param response
     * @param chain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        //得到请求头中的token
        String token = request.getHeader(JWTUtils.TOKEN_HEADER_KEY);
        System.out.println(token);
        if (StringUtils.isEmpty(token)){
            chain.doFilter(request,response);
            return;
        }
        UsernamePasswordAuthenticationToken auth = getAuthFromToken(token);
        //为空即无权限
        if(!ObjectUtils.isEmpty(auth)){
            //通过上下文存储用户信息
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        super.doFilterInternal(request,response,chain);
    }

    /**
     * 通过Token中的userName得到对应的用户权限信息
     *
     * token无效(无username) 抛异常
     * @param token
     * @return
     */
    private UsernamePasswordAuthenticationToken getAuthFromToken(String token){
        try{
            //name方法为空抛异常
            String nameFromToken = JWTUtils.getUserNameFromToken(token);
            System.out.println("BasicAuthenticationFilter授权.....");
            //获取权限列表
            Set<Role> roles = roleMapper.queryRolesByName(nameFromToken);
            Set<GrantedAuthority> authorities = new HashSet<>();
            roles.forEach(role -> (permMapper.queryPermsByRoleName(role.getRoleName()))
                            .forEach(perm -> authorities.add(new SimpleGrantedAuthority(perm.getPermName()))));
            System.out.println(authorities);
            if(!ObjectUtils.isEmpty(authorities)){
                return new UsernamePasswordAuthenticationToken(nameFromToken,null,authorities);
            }
        }catch (Exception e){
            log.error("token: {} Exception:{}",token,e.getMessage());
        }
        return null;
    }
}
