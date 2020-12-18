package com.example.store.service;

import com.example.store.entity.BgUser;
import com.example.store.entity.Resource;
import com.example.store.entity.Vo.BgUserListVo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * @description: 我是工具类并且我不喜欢被继承 final 保护了我免于继承，private 保护我被创建
 * @author: 舒晓朵
 * @create: 2020/12/6
 **/
public interface IBgUserService extends UserDetailsService {
    BgUser userLogin(BgUser user);
    List<Resource> getResourceByRoleId(int id);
    List<BgUserListVo> getBgUserList(BgUserListVo bgUser);
}
