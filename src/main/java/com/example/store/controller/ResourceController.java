package com.example.store.controller;

import com.example.store.service.impl.ResourceService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description: ResourceController
 * @author: czj
 * @create: 2021-01-30 22:55
 **/

@RestController
@RequestMapping("/api")
public class ResourceController {

    @Resource
    ResourceService resourceService;

    @GetMapping("/resources")
    public Object listResources(@RequestParam int ps, @RequestParam int pn, @RequestParam(required = false) String name) {
        List<com.example.store.entity.Resource> list = resourceService.listResource(ps, pn, name);
        return new PageInfo<>(list, pn);
    }

    @PostMapping("/resource")
    public String createResource(@RequestBody com.example.store.entity.Resource resource) {
        resourceService.createResource(resource);
        return "资源添加成功";
    }

    @PutMapping("/resource")
    public String updateResource(@RequestBody com.example.store.entity.Resource resource) {
        resourceService.updateResource(resource);
        return "资源修改成功";
    }

    @DeleteMapping("/resource")
    public String deleteResource(@RequestParam int id) {
        resourceService.deleteResource(id);
        return "资源删除成功";
    }

}
