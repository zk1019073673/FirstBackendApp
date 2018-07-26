package com.advantech.homework.service;

import com.advantech.homework.dao.IUserDao;
import com.advantech.homework.dao.IUserDeviceDao;
import com.advantech.homework.model.UserInfoPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service("userService")
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserDao userDao;
    @Autowired
    private IUserDeviceDao userDeviceDao;

    private String MD5(String s) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytes = md.digest(s.getBytes("utf-8"));
            return toHex(bytes);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private static String toHex(byte[] bytes) {

        final char[] HEX_DIGITS = "0123456789ABCDEF".toCharArray();
        StringBuilder ret = new StringBuilder(bytes.length * 2);
        for (int i=0; i<bytes.length; i++) {
            ret.append(HEX_DIGITS[(bytes[i] >> 4) & 0x0f]);
            ret.append(HEX_DIGITS[bytes[i] & 0x0f]);
        }
        return ret.toString();
    }

    @Override
    public List<UserInfoPO> getUserInfo(Integer length, Integer offset) {
        List<UserInfoPO> userInfoList = new ArrayList<UserInfoPO>();
        return userDao.getUserInfo(length, offset);
    }

    @Override
    public List<UserInfoPO> getUserInfoByUserName(String username, Integer length, Integer offset) {
        List<UserInfoPO> deviceInfoList = new ArrayList<UserInfoPO>();
        return userDao.getUserInfoByUserName(username, 50, 0);
    }

    @Override
    public Integer getUserInfocount() {
        return userDao.getUserInfocount();
    }

    @Override
    public Integer getUserInfoByNameCount(String username) {
        return userDao.getUserInfoByNameCount(username);
    }

    @Override
    public Integer createUserInfo(UserInfoPO userInfo) {
        userInfo.setPassword(MD5(userInfo.getPassword()));
        Integer id = userDao.createUserInfo(userInfo);
        return id;
    }

    @Override
    public Boolean deleteUser(UserInfoPO userInfo) {

        return userDeviceDao.devUserByUserId(userInfo.getId()) && userDao.deleteUser(userInfo);
    }

    @Override
    public Boolean updateUserInfo(UserInfoPO userInfo) {
        return userDao.updateUserInfo(userInfo);
    }

    @Override
    public List<UserInfoPO> validateUserInfo(String username, String password) {
        List<UserInfoPO> userInfoList = userDao.getUserInfoByName(username);
        if (userInfoList != null) {
//            System.out.println("userInfoList:"+userInfoList.size());
            for (UserInfoPO userInfo : userInfoList) {
                String name = userInfo.getUsername();
                String pwd = userInfo.getPassword();
                if (username.equals(name) && password.equals(pwd)) {
                    return userInfoList;
                } else {
                    return null;
                }
            }
        }
        return null;
    }
}