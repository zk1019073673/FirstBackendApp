package com.advantech.homework.service;
import com.advantech.homework.dao.IDeviceDao;
import com.advantech.homework.dao.IUserDeviceDao;
import com.advantech.homework.model.DeviceInfoPO;
import com.advantech.homework.model.UserDeviceInfoPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service("userDeviceService")
public class UserDeviceServiceImpl implements IUserDeviceService {

    @Autowired
    private IUserDeviceDao userDeviceDao;
    @Autowired
    private IDeviceDao deviceDao;

    @Override
    public List<UserDeviceInfoPO> getUserDeviceIds(Integer userId, Integer offset, Integer length) {
        UserDeviceInfoPO userDeviceInfoPO = new UserDeviceInfoPO();
        userDeviceInfoPO.setUserId(userId);
        return userDeviceDao.getUserDeviceIds(userDeviceInfoPO,50,0);
    }

    @Override
    public List<DeviceInfoPO> getDeviceInfosById(Integer deviceId, Integer offset, Integer length) {
        return deviceDao.getDeviceInfosById(deviceId,offset,length);
    }

    @Override
    public Integer shareDevicesForUser(UserDeviceInfoPO userDeviceInfoPO) {
        Integer deviceId = userDeviceDao.shareDevicesForUser(userDeviceInfoPO);
        return deviceId;
    }

    @Override
    public Boolean delDevByDevId(UserDeviceInfoPO userDeviceInfoPO) {
        Boolean isDeleted = userDeviceDao.delDevByUserDevice(userDeviceInfoPO);
        return isDeleted;
    }
}
