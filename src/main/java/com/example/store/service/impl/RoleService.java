package com.example.store.service.impl;

import com.example.store.entity.Role;
import com.example.store.mapper.RoleMapper;
import com.example.store.service.IRoleService;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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

    @Override
    public List<Role> getRoleList(String duty, int ps, int pn) {
        PageHelper.startPage(pn, ps);
        return roleMapper.getRoleList(duty);
    }
}
