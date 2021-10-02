package com.mp.service;

import com.mp.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Set;

/**
 * <p>
 * 角色 服务类
 * </p>
 *
 * @author Ho
 * @since 2021-09-27
 */
public interface RoleService extends IService<Role> {
    Set<Role> queryRolesByName(String userName);

}
