package com.mp.mapper;

import com.mp.entity.Perm;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * <p>
 * 权限表 Mapper 接口
 * </p>
 *
 * @author Ho
 * @since 2021-09-27
 */
@Repository
public interface PermMapper extends BaseMapper<Perm> {
    /**
     * 通过角色名查询权限
     * 由业务层代替多表连接
     * @return
     */
    Set<Perm> queryPermsByRoleName(String roleName);

}
