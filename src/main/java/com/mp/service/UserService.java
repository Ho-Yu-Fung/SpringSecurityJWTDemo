package com.mp.service;

import com.mp.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.Serializable;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author Ho
 * @since 2021-09-27
 */
public interface UserService extends IService<User> {
    User queryUserByrName(String userName);
}
