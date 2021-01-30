package com.example.store.mapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;
/**
 * @description: 我是工具类并且我不喜欢被继承 final 保护了我免于继承，private 保护我被创建
 * @author: 舒晓朵
 * @create: 2021/1/30
 **/
public interface CommonMapper<T> extends Mapper<T>,MySqlMapper<T> {
}
