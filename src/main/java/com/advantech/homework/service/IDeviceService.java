package com.advantech.homework.service;


import com.advantech.homework.model.DeviceInfoPO;
import java.util.List;

public interface IDeviceService {

    List<DeviceInfoPO> getDeviceInfo(Integer length, Integer offset);
    List<DeviceInfoPO> getDeviceInfoByDeviceName(String deviceName, Integer length, Integer offset);
    Integer getDeviceInfocount();
    Integer getDeviceInfoByNameCount(String name);

    Integer createDeviceInfo(DeviceInfoPO deviceInfo);

    Boolean deleteDevice(DeviceInfoPO deviceInfo);

    Boolean updateDeviceInfo(DeviceInfoPO deviceInfo);
}
