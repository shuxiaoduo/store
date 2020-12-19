package com.example.store.entity.Vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @description: 我是工具类并且我不喜欢被继承 final 保护了我免于继承，private 保护我被创建
 * @author: 舒晓朵
 * @create: 2020/12/18
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BgUserListVo {
    //用户id
    private int userId;
    //用户名
    private String username;
    //用户密码
    private String password;
    //性别
    private String sex;
    //出生日期

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")

    private Date birthday;
    //电话号码
    private String phone;
    //电子邮箱
    private String email;
    //创建时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private String create_time;
    //用户角色
    private String role;
    //用户状态
    private int flag;
}
