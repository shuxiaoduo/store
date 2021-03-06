package com.example.store.mapper;

import com.example.store.entity.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @description: 我是工具类并且我不喜欢被继承 final 保护了我免于继承，private 保护我被创建
 * @author: 舒晓朵
 * @create: 2020/12/15
 **/
@Mapper
public interface RoleMapper extends CommonMapper<Role>{
    int getRoleIdByName(String name);
    public List<Role> getRoleList(String duty);
}
