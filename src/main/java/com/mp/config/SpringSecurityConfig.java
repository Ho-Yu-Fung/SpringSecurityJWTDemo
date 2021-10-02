package com.mp.config;

import com.mp.filter.JWTAuthorizationFilter;
import com.mp.filter.JWTLoginFilter;
import com.mp.mapper.PermMapper;
import com.mp.mapper.RoleMapper;
import com.mp.security.AuthEntryPoin;
import com.mp.security.CustAccessDeniedHandler;
import com.mp.security.SecurityDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{
    @Autowired
    SecurityDetailsService securityDetailsService;
    @Autowired
    PermMapper permMapper;
    @Autowired
    RoleMapper roleMapper;

    @Bean
    public PasswordEncoder passwordEncoderBean() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 资源权限配置
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //开启跨域支持
                .cors().and()
                //基于Token 无需防csrf支持
                .csrf().disable()
                //基于token授权认证 关闭session支持
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                //请求配置
                .and().authorizeRequests()
                //preflighted复杂请求的OPTION支持
//                .antMatchers("/auth/login").permitAll()
//                .antMatchers(HttpMethod.OPTIONS,"/**").permitAll()
                .anyRequest().permitAll()
                .and()
                .addFilter(new JWTAuthorizationFilter(authenticationManager(),permMapper,roleMapper))
                .addFilter(new JWTLoginFilter(authenticationManager()))
                //鉴权异常处理
                .exceptionHandling().
                authenticationEntryPoint(new AuthEntryPoin())
                .accessDeniedHandler(new CustAccessDeniedHandler());
        //禁用缓存
        http.headers().cacheControl();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * 对前端传来的对象与数据库比较
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        System.out.println("WebSecurityConfigurerAdapter->configure");
        auth
                .userDetailsService(securityDetailsService)
                .passwordEncoder(new BCryptPasswordEncoder());

    }
}
