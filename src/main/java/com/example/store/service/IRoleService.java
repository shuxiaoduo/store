package com.example.store.service;

import com.example.store.entity.Role;

import java.util.List;

/**
 * @description: 我是工具类并且我不喜欢被继承 final 保护了我免于继承，private 保护我被创建
 * @author: 舒晓朵
 * @create: 2020/12/15
 **/
public interface IRoleService {
    public int getRoleIdByName(String name);
    public List<Role> getRoleList(String duty,int ps,int pn);
}
