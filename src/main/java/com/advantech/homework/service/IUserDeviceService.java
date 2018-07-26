package com.advantech.homework.service;

import com.advantech.homework.model.DeviceInfoPO;
import com.advantech.homework.model.UserDeviceInfoPO;

import java.util.List;

public interface IUserDeviceService {
    List<UserDeviceInfoPO> getUserDeviceIds (Integer userId, Integer offset, Integer length);
    List<DeviceInfoPO> getDeviceInfosById (Integer deviceId, Integer offset, Integer length);
    Integer shareDevicesForUser (UserDeviceInfoPO userDeviceInfoPO);
    Boolean delDevByDevId (UserDeviceInfoPO userDeviceInfoPO);
}
