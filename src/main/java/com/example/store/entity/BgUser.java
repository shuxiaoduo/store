package com.example.store.entity;

import com.example.store.common.Constant;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @description: 我是工具类并且我不喜欢被继承 final 保护了我免于继承，private 保护我被创建
 * @author: 舒晓朵
 * @create: 2020/12/6
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BgUser implements UserDetails, Serializable {
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
    /**
     * 登录成功时即进入这个方法获取权限
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        if(role.equals("ROLE_admin")) {
            authorities.add(Constant.GLOBAL_ADMIN);
        }
        return authorities;
    }


    //账户是否未过期
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //账户是否未被锁
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //证书是否未过期
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //账户是否有效
    @Override
    public boolean isEnabled() {
        return true;
    }
}
