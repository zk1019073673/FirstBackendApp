package com.advantech.homework.dao;
import com.advantech.homework.model.UserDeviceInfoPO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userDeviceDao")
public class UserDeviceDaoImpl extends AbstractDao<Integer, UserDeviceInfoPO> implements IUserDeviceDao {
    private Logger logger = LoggerFactory.getLogger(DeviceDaoImpl.class);
    @Override
    public List<UserDeviceInfoPO> getUserDeviceIds(UserDeviceInfoPO userDeviceInfoPO, Integer length, Integer offset) {
        try {
            String hql = String.format("from UserDeviceInfoPO where userId = '%d'",userDeviceInfoPO.getUserId());
//            System.out.println(queryForPage(hql,0,50));
            return queryForPage(hql,offset,length);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Integer shareDevicesForUser(UserDeviceInfoPO userDeviceInfoPO) {
        try {
            persist(userDeviceInfoPO);
            logger.info("shareDevicesForUser:"+userDeviceInfoPO.getDeviceId());
            return userDeviceInfoPO.getDeviceId();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Boolean delDevByUserDevice(UserDeviceInfoPO userDeviceInfoPO) {
        try {
            String hql = "from UserDeviceInfoPO where deviceId = "+userDeviceInfoPO.getDeviceId()+" and userId = "+ userDeviceInfoPO.getUserId();
//            String hql = String.format("FROM UserDeviceInfoPO where deviceId = '%d'",deviceId);
            logger.info("查询关系表："+hql);
            List<UserDeviceInfoPO> userDeviceInfoList = queryForPage(hql, 0, 50);
            for (int i=0;i<userDeviceInfoList.size();i++) {
                delete(userDeviceInfoList.get(i));
            }
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean delDevByDevId(Integer deviceId) {
        try {
//            String hql = "from UserDeviceInfoPO where deviceId = "+userDeviceInfoPO.getDeviceId()+" and userId = "+ userDeviceInfoPO.getUserId();
            String hql = String.format("FROM UserDeviceInfoPO where deviceId = '%d'",deviceId);
            logger.info("查询关系表："+hql);
            List<UserDeviceInfoPO> userDeviceInfoList = queryForPage(hql, 0, 50);
            for (int i=0;i<userDeviceInfoList.size();i++) {
                delete(userDeviceInfoList.get(i));
            }
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean devUserByUserId(Integer userId) {
        try {
            String hql = String.format("FROM UserDeviceInfoPO where userId = '%d'",userId);
            logger.info("查询关系表："+hql);
            List<UserDeviceInfoPO> userDeviceInfoList = queryForPage(hql, 0, 50);
            for (int i=0;i<userDeviceInfoList.size();i++) {
                delete(userDeviceInfoList.get(i));
            }
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


}
