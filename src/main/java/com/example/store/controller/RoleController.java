package com.example.store.controller;

import com.example.store.entity.Role;
import com.example.store.entity.Vo.BgUserListVo;
import com.example.store.service.impl.RoleService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description: 我是工具类并且我不喜欢被继承 final 保护了我免于继承，private 保护我被创建
 * @author: 舒晓朵
 * @create: 2020/12/21
 **/
@RestController
@RequestMapping("/api")
public class RoleController {
    @Autowired
    RoleService roleService;
    @RequestMapping("/getRoleList")
    public Object getBgUserList(@RequestParam(value="duty",required = false) String duty,@RequestParam(value = "ps") int ps, @RequestParam(value = "pn") int pn){
        List<Role> list=roleService.getRoleList(duty,ps,pn);
        return new PageInfo<>(list,pn);
    }
}
