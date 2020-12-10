package com.example.store.entity.Vo;

import com.example.store.entity.BgUser;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @description: 我是工具类并且我不喜欢被继承 final 保护了我免于继承，private 保护我被创建
 * @author: 舒晓朵
 * @create: 2020/12/10
 **/
@Data
@AllArgsConstructor
public class BgUserVo {
    private String username;

    private String password;

    private String role;

    private String token;

    public BgUserVo(BgUser bgUser){
        this.username = bgUser.getUsername();
        this.password = bgUser.getPassword();
        this.role = bgUser.getRole();
    }
}
