package com.advantech.homework.dao;

import com.advantech.homework.model.DeviceInfoPO;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IDeviceDao {
//    public interface IDeviceDao extends CrudRepository<DeviceInfoPO,Integer> {
    List<DeviceInfoPO> getDeviceInfo(Integer length, Integer offset);
//    @Query("select id,deviceName,deviceType,location,status from DeviceInfoPO")
//    List<DeviceInfoPO> getDeviceInfo(Integer length, Integer offset);

    List<DeviceInfoPO> getDeviceInfoByDeviceName(String deviceName,Integer length, Integer offset);
    Integer getDeviceInfocount();
    Integer getDeviceInfoByNameCount(String deviceName);

    Integer createDeviceInfo(DeviceInfoPO deviceInfo);

    Boolean deleteDevice(DeviceInfoPO deviceInfo);

    Boolean updateDeviceInfo(DeviceInfoPO deviceInfo);
    List<DeviceInfoPO> getDeviceInfosById(Integer deviceId, Integer length, Integer offset);
}
