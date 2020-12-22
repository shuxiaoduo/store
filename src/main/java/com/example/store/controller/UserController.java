package com.example.store.controller;

import com.example.store.entity.BgUser;
import com.example.store.entity.Vo.BgUserListVo;
import com.example.store.entity.Vo.BgUserVo;
import com.example.store.service.impl.BgUserService;
import com.example.store.service.impl.RoleService;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.xpath.internal.operations.Bool;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
    public Object getBgUserList(@RequestParam(value="code",required = false) String code,@RequestParam(value="userName") String userName,@RequestParam(value = "ps") int ps, @RequestParam(value = "pn") int pn){
        List<BgUserListVo> list=userService.getBgUserList(code,userName,ps,pn);
        return new PageInfo<>(list,pn);
    }
    @RequestMapping("/deleteBgUserById")
    public Boolean deleteBgUserById(@RequestParam("userId") int id)
    {
        return userService.deleteBgUserById(id);
    }
    @RequestMapping("/updateFlag")
    public Boolean updateFlag(@RequestParam("userId") int id,@RequestParam("flag") int flag){
        return userService.updateFlag(id,flag);
    }
    @RequestMapping("/addBgUser")
    public Boolean addBgUser(@RequestBody BgUser bgUser){
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        Calendar calendar = Calendar.getInstance();
        String dateName = df.format(calendar.getTime());
        bgUser.setCreate_time(dateName);
        System.out.println(bgUser);
        return userService.addBgUser(bgUser);
    }
    @RequestMapping("/updateBgUser")
    public Boolean updateBgUser(@RequestBody BgUser bgUser){
        return userService.updateBgUser(bgUser);
    }
    @RequestMapping("/deleteBgUserByIds")
    public Boolean deleteBgUserByIds(@RequestBody Integer []userIds){
        return userService.deleteBgUserByIds(userIds);
    }
}
