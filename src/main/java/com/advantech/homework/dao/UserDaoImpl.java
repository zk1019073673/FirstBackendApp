package com.advantech.homework.dao;

import com.advantech.homework.model.UserInfoPO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Integer, UserInfoPO> implements IUserDao {
    private Logger logger = LoggerFactory.getLogger(DeviceDaoImpl.class);
    @Override
    public List<UserInfoPO> getUserInfo(Integer length, Integer offset) {
        try {
            String hql = String.format("from UserInfoPO order by type,id");
            return queryForPage(hql,offset,length);
        }
        catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<UserInfoPO> getUserInfoByUserName(String username, Integer length, Integer offset) {
        try {
            String hql = "from UserInfoPO where username like '%"+username+"%' order by id,type asc";
            logger.info("===============================用户模糊查询:"+hql);
//            System.out.println(queryForPage(hql,0,50));

            return queryForPage(hql,0,50);
        } catch (Exception e){
            throw e;
        }
    }

    @Override
    public Integer getUserInfocount() {
        try {
            String hql = String.format("from UserInfoPO");
            return getCount(hql);
        }
        catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Integer getUserInfoByNameCount(String username) {
        try {
            String hql = "from UserInfoPO where username like '%"+username+"%' order by id";
            return getCount(hql);
        }
        catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Integer createUserInfo(UserInfoPO userInfo) {
        persist(userInfo);
        return userInfo.getId();
    }

    @Override
    public Boolean deleteUser(UserInfoPO userInfo) {
        try {
            String hql = String.format("FROM UserInfoPO where id = '%d'",userInfo.getId());
            UserInfoPO userInfoPO = queryForPage(hql, -1, -1).get(0);
            delete(userInfoPO);
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean updateUserInfo(UserInfoPO userInfo) {
        try {
//            String hql = String.format("FROM UserInfoPO where id = '%d'",userInfo.getId());
//            UserInfoPO userInfoPO = queryForPage(hql, -1, -1).get(0);
//            userInfo.setPassword(userInfoPO.getPassword());

            UserInfoPO userInfo0 = merge(userInfo);
            logger.info("=======================================updateUserInfo:"+userInfo0);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<UserInfoPO> getUserInfoByName(String username) {

        try {
            String hql = String.format("from UserInfoPO where username = '%s'",username);
//            System.out.println(hql);
            return query(hql);
        } catch (Exception e) {
            throw e;
        }
    }
}

