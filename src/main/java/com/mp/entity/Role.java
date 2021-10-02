package com.mp.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Set;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;

/**
 * <p>
 * 角色
 * </p>
 *
 * @author Ho
 * @since 2021-09-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_role")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "role_id", type = IdType.AUTO)
    private Integer roleId;

    /**
     * 角色名
     */
    private String roleName;
    /**
     * 角色描述
     */
    private String roleDes;

    /**
     * 角色对应权限
     */
    @TableField(exist = false)
    private Set<Perm> perms;

}
