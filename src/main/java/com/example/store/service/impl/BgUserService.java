package com.example.store.service.impl;

import com.example.store.entity.BgUser;
import com.example.store.mapper.BgUserMapper;
import com.example.store.service.IBgUserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description: 我是工具类并且我不喜欢被继承 final 保护了我免于继承，private 保护我被创建
 * @author: 舒晓朵
 * @create: 2020/12/6
 **/
@Service
public class BgUserService implements IBgUserService {

    @Resource
    BgUserMapper bgUserMapper;

    @Override
    public BgUser userLogin(BgUser user) {
        return bgUserMapper.findUser(user);
    }

    @Override
    public List<com.example.store.entity.Resource> getResourceByRoleId(int id) {
        List<com.example.store.entity.Resource> list=bgUserMapper.selectResourceByRoleId(id);
        return list;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        BgUser bgUser = bgUserMapper.selectUserByName(username);
        if (bgUser == null){
            throw new UsernameNotFoundException(username);
        }
        //在SpringSecurity中指定了密码的加密方式时，这里就必须也指定加密方式
        bgUser.setPassword(new BCryptPasswordEncoder().encode(bgUser.getPassword()));
        return bgUser;
    }
}
