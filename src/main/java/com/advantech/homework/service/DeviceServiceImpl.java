package com.advantech.homework.service;

import com.advantech.homework.dao.IDeviceDao;
import com.advantech.homework.dao.IUserDeviceDao;
import com.advantech.homework.model.DeviceInfoPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service("deviceService")
public class DeviceServiceImpl implements  IDeviceService {
    @Autowired
    private IDeviceDao deviceDao;
    @Autowired
    private IUserDeviceDao userDeviceDao;

    @Override
    public List<DeviceInfoPO> getDeviceInfo(Integer length, Integer offset) {
        List<DeviceInfoPO> deviceInfoList = new ArrayList<DeviceInfoPO>();
        return deviceDao.getDeviceInfo(length, offset);
    }

    @Override
    public List<DeviceInfoPO> getDeviceInfoByDeviceName(String deviceName, Integer length, Integer offset) {
        List<DeviceInfoPO> deviceInfoList = new ArrayList<DeviceInfoPO>();
        return deviceDao.getDeviceInfoByDeviceName(deviceName, 50, 0);
    }

    @Override
    public Integer getDeviceInfocount() {
        return deviceDao.getDeviceInfocount();
    }

    @Override
    public Integer getDeviceInfoByNameCount(String deviceName) {
        return deviceDao.getDeviceInfoByNameCount(deviceName);
    }

    @Override
    public Integer createDeviceInfo(DeviceInfoPO deviceInfo) {
        Integer id = deviceDao.createDeviceInfo(deviceInfo);
        return id;
    }

    @Override
    public Boolean deleteDevice(DeviceInfoPO deviceInfo) {

        return userDeviceDao.delDevByDevId(deviceInfo.getId()) && deviceDao.deleteDevice(deviceInfo);
    }

    @Override
    public Boolean updateDeviceInfo(DeviceInfoPO deviceInfo) {
        return deviceDao.updateDeviceInfo(deviceInfo);
    }
}
