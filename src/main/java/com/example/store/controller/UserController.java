package com.example.store.controller;

import com.example.store.entity.User;
import com.example.store.service.IUserService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @description: 我是工具类并且我不喜欢被继承 final 保护了我免于继承，private 保护我被创建
 * @author: 舒晓朵
 * @create: 2020/12/6
 **/

@RestController
@RequestMapping("/api")


public class UserController {
    @Resource
    IUserService userService;
    @RequestMapping("/login")
    public String userLogin(@RequestBody User user){
        if(userService.userLogin(user)!=null) {
            return "登陆成功";
        }
        else{
            return "登陆失败";
        }


    }
}
