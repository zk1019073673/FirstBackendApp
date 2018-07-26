package com.advantech.homework.service;

import com.advantech.homework.model.UserInfoPO;

import java.util.List;

public interface IUserService {

    List<UserInfoPO> getUserInfo(Integer length, Integer offset);
    List<UserInfoPO> getUserInfoByUserName(String username, Integer length, Integer offset);
    Integer getUserInfocount();
    Integer getUserInfoByNameCount(String username);

    Integer createUserInfo(UserInfoPO userInfo);

    Boolean deleteUser(UserInfoPO userInfo);

    Boolean updateUserInfo(UserInfoPO userInfo);

    List<UserInfoPO> validateUserInfo(String username, String password);
}
