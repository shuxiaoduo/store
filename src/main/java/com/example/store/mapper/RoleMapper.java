package com.example.store.mapper;

import org.apache.ibatis.annotations.Mapper;

/**
 * @description: 我是工具类并且我不喜欢被继承 final 保护了我免于继承，private 保护我被创建
 * @author: 舒晓朵
 * @create: 2020/12/15
 **/
@Mapper
public interface RoleMapper {
    int getRoleIdByName(String name);
}
