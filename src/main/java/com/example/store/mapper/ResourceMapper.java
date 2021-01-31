package com.example.store.mapper;

import com.example.store.entity.Resource;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ResourceMapper extends CommonMapper<Resource> {

    List<Resource> listResource(String name);

}
