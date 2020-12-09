package com.example.store.service.impl;

import com.example.store.entity.User;
import com.example.store.mapper.UserMapper;
import com.example.store.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @description: 我是工具类并且我不喜欢被继承 final 保护了我免于继承，private 保护我被创建
 * @author: 舒晓朵
 * @create: 2020/12/6
 **/
@Service
public class UserService implements IUserService {
    //定义mapper数据库访问对象
    @Resource
    private UserMapper userMapper;
    @Override
    public User userLogin(User user) {
        return userMapper.findUser(user);
    }
}
