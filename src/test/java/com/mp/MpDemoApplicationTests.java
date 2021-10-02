package com.mp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mp.entity.User;
import com.mp.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MpDemoApplicationTests {
    @Autowired
    UserMapper userMapper;

    @Test
    void contextLoads() {
        String password = userMapper.selectOne(new QueryWrapper<User>().eq("user_name", "lisi")).getPassword();
//        System.out.println(defaultPasswordEncoder.matches("123", password));
    }

}
