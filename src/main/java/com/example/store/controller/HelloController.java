package com.example.store.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 我是工具类并且我不喜欢被继承 final 保护了我免于继承，private 保护我被创建
 * @author: 舒晓朵
 * @create: 2020/12/6
 **/
@RestController
public class HelloController {
    @RequestMapping("/hello")
    public String HelloWorld(){
        return "Hello";
    }

}
