package com.advantech.homework.dao;
import com.advantech.homework.model.UserDeviceInfoPO;

import java.util.List;

public interface IUserDeviceDao {

    List<UserDeviceInfoPO> getUserDeviceIds(UserDeviceInfoPO userDeviceInfoPO, Integer length, Integer offset);
    Integer shareDevicesForUser(UserDeviceInfoPO userDeviceInfoPO);
    Boolean delDevByDevId(Integer deviceId);
    Boolean delDevByUserDevice(UserDeviceInfoPO userDeviceInfoPO);
    Boolean devUserByUserId(Integer userId);
}
