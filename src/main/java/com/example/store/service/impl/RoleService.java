package com.example.store.service.impl;

import com.example.store.mapper.RoleMapper;
import com.example.store.service.IRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @description: 我是工具类并且我不喜欢被继承 final 保护了我免于继承，private 保护我被创建
 * @author: 舒晓朵
 * @create: 2020/12/15
 **/
@Service
public class RoleService implements IRoleService {
    @Resource
    RoleMapper roleMapper;
    @Override
    public int getRoleIdByName(String name) {
        return roleMapper.getRoleIdByName(name);
    }
}
