package com.mp.mapper;

import com.mp.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author Ho
 * @since 2021-09-27
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

}
