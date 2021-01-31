package com.example.store.service.impl;

import com.example.store.entity.Resource;
import com.example.store.mapper.ResourceMapper;
import com.example.store.service.IResourceService;
import com.example.store.util.Assert;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: ResourceImpl
 * @author: czj
 * @create: 2021-01-30 22:20
 **/
@Service
public class ResourceService implements IResourceService {

    @javax.annotation.Resource
    ResourceMapper resourceMapper;

    @Override
    public boolean createResource(Resource resource) {
        Assert.isTrue(resourceMapper.insert(resource) > 0, "资源添加失败");
        return true;
    }

    @Override
    public boolean updateResource(Resource resource) {
        Assert.isTrue(resourceMapper.updateByPrimaryKey(resource) > 0, "资源更新失败");
        return true;
    }

    @Override
    public boolean deleteResource(int id) {
        Resource resource = new Resource();
        resource.setId(id);
        Assert.isTrue(resourceMapper.deleteByPrimaryKey(resource) > 0, "资源删除失败");
        return true;
    }

    @Override
    public List<Resource> listResource(int ps, int pn, String name) {
        PageHelper.startPage(pn, ps);
        List<Resource> resources = resourceMapper.listResource(name);
        return resources;
    }
}
