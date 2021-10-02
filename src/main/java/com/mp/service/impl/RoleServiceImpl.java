package com.mp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mp.entity.Role;
import com.mp.mapper.RoleMapper;
import com.mp.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * <p>
 * 角色 服务实现类
 * </p>
 *
 * @author Ho
 * @since 2021-09-27
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
    @Autowired
    RoleMapper roleMapper;
    @Override
    public Set<Role> queryRolesByName(String userName) {
        return roleMapper.queryRolesByName(userName);
    }
}
