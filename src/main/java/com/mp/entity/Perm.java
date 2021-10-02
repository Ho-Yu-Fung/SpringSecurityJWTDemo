package com.mp.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;

/**
 * <p>
 * 权限表
 * </p>
 *
 * @author Ho
 * @since 2021-09-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_perm")
public class Perm implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "perm_id", type = IdType.AUTO)
    private Integer permId;

    /**
     * 权限名
     */
    private String permName;

}
