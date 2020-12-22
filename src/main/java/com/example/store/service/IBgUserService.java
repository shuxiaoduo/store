package com.example.store.service;

import com.example.store.entity.BgUser;
import com.example.store.entity.Resource;
import com.example.store.entity.Vo.BgUserListVo;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @description: 我是工具类并且我不喜欢被继承 final 保护了我免于继承，private 保护我被创建
 * @author: 舒晓朵
 * @create: 2020/12/6
 **/
public interface IBgUserService extends UserDetailsService {
    public BgUser userLogin(BgUser user);
    public List<Resource> getResourceByRoleId(int id);
    public List<BgUserListVo> getBgUserList(String code,String userName,int ps,int pn);
    public Boolean deleteBgUserById(int id);
    public Boolean updateFlag(int id,int flag);
    public Boolean addBgUser(BgUser bgUser);
    public Boolean updateBgUser(BgUser bgUser);
}
