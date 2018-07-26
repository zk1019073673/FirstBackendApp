package com.advantech.homework.dao;

import com.advantech.homework.model.UserInfoPO;

import java.util.List;
public interface IUserDao {

    List<UserInfoPO> getUserInfo(Integer length, Integer offset);
    List<UserInfoPO> getUserInfoByUserName(String username,Integer length, Integer offset);
    Integer getUserInfocount();
    Integer getUserInfoByNameCount(String username);

    Integer createUserInfo(UserInfoPO userInfo);

    Boolean deleteUser(UserInfoPO userInfo);

    Boolean updateUserInfo(UserInfoPO userInfo);

    List<UserInfoPO> getUserInfoByName(String username);
}
