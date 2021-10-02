package com.mp.security;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mp.entity.Role;
import com.mp.entity.User;
import com.mp.mapper.PermMapper;
import com.mp.mapper.RoleMapper;
import com.mp.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Component
public class SecurityDetailsService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        User user = userMapper.selectOne(new QueryWrapper<User>().eq("user_name", userName));
        if (ObjectUtils.isEmpty(user)) {
            throw new UsernameNotFoundException("User No Exist!");
        }
//        SecurityUser securityUser = new SecurityUser(user);
//        //获取角色列表
//        Set<Role> roles = roleMapper.queryRolesByName(userName);
//        //获取权限列表
//        ArrayList<GrantedAuthority> authorities = new ArrayList<>();
//        roles.forEach(role -> authorities.addAll(permMapper.queryPermsByRoleName(role.getRoleName())));
//        //权限设置
        System.out.println("loadUserByUsername..."+user.toString());

        return user;
    }
}
