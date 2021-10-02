package com.mp.mapper;

import com.mp.entity.Perm;
import com.mp.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * <p>
 * 角色 Mapper 接口
 * </p>
 *
 * @author Ho
 * @since 2021-09-27
 */
@Repository
public interface RoleMapper extends BaseMapper<Role> {

    Set<Role> queryRolesByName(String userName);


}
