package com.example.store.service;

import com.example.store.entity.Resource;

import java.util.List;

public interface IResourceService {

    boolean createResource(Resource resource);

    boolean updateResource(Resource resource);

    boolean deleteResource(int id);

    List<Resource> listResource(int ps, int pn, String name);
}
