package com.example.store.controller;

import com.example.store.entity.BgUser;
import com.example.store.entity.Vo.BgUserListVo;
import com.example.store.entity.Vo.BgUserVo;
import com.example.store.service.impl.BgUserService;
import com.example.store.service.impl.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description: 我是工具类并且我不喜欢被继承 final 保护了我免于继承，private 保护我被创建
 * @author: 舒晓朵
 * @create: 2020/12/6
 **/

@RestController
@RequestMapping("/api")


public class UserController {
    @Resource
    BgUserService userService;
    @Autowired
    RoleService roleService;

    @RequestMapping("/getResource")
    public List<com.example.store.entity.Resource> getResource(@RequestParam(value = "role_name") String name) {
        int id = roleService.getRoleIdByName(name);
        return userService.getResourceByRoleId(id);
    }
    @RequestMapping("/getBgUserList")
    public List<BgUserListVo> getBgUserList(@RequestBody BgUserListVo bgUser){
        return userService.getBgUserList(bgUser);
    }

}
