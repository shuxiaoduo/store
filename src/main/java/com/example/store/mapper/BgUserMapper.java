package com.example.store.mapper;

import com.example.store.entity.BgUser;
import com.example.store.entity.Resource;
import com.example.store.entity.Vo.BgUserListVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @description: 我是工具类并且我不喜欢被继承 final 保护了我免于继承，private 保护我被创建
 * @author: 舒晓朵
 * @create: 2020/12/6
 **/
@Mapper
public interface BgUserMapper extends CommonMapper<BgUser> {
    //根据用户名和密码查询
    BgUser findUser(BgUser bgUser);
    BgUser selectUserByName(String username);
    List<Resource> selectResourceByRoleId(int id);
    List<BgUserListVo> getBgUserList(String code, String userName, int roleId);
    Boolean deleteBgUserById(int id);
    public Boolean updateFlag(int id, int flag);
    public Boolean addBgUser(BgUser bgUser);
    public Boolean updateBgUser(BgUser bgUser);
}
