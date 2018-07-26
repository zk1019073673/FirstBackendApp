package com.advantech.homework.dao;

import com.advantech.homework.model.DeviceInfoPO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository("deviceDao")
public class DeviceDaoImpl extends AbstractDao<Integer, DeviceInfoPO> implements IDeviceDao {
    private Logger logger = LoggerFactory.getLogger(DeviceDaoImpl.class);
    @Override
    public List<DeviceInfoPO> getDeviceInfo(Integer length, Integer offset) {
        try {
            String hql = String.format("from DeviceInfoPO order by id,deviceType asc");
            return queryForPage(hql,offset,length);

        } catch (Exception e){
            throw e;
        }
    }

//    @Override
//    public List<DeviceInfoPO> getDeviceInfo(Integer length, Integer offset) {
//        return null;
//    }

    @Override
    public List<DeviceInfoPO> getDeviceInfoByDeviceName(String deviceName, Integer length, Integer offset) {
        try {
            String hql = "from DeviceInfoPO where deviceName like '%"+deviceName+"%' order by id,deviceType asc";
            logger.info("==========================================设备模糊查询:"+hql);
//            System.out.println(queryForPage(hql,0,50));

            return queryForPage(hql,0,50);
        } catch (Exception e){
            throw e;
        }
    }

    @Override
    public Integer getDeviceInfocount() {
        try {
            String hql = String.format("from DeviceInfoPO");
            return getCount(hql);
        }
        catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Integer getDeviceInfoByNameCount(String deviceName) {
        try {
            String hql = "from DeviceInfoPO where deviceName like '%"+deviceName+"%' order by id";
            return getCount(hql);
        }
        catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Integer createDeviceInfo(DeviceInfoPO deviceInfo) {
        persist(deviceInfo);
        return deviceInfo.getId();
    }

    @Override
    public Boolean deleteDevice(DeviceInfoPO deviceInfo) {
        try {
            String hql = String.format("FROM DeviceInfoPO where id = '%d'",deviceInfo.getId());
            DeviceInfoPO deviceInfoPO = queryForPage(hql, -1, -1).get(0);
            delete(deviceInfoPO);
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean updateDeviceInfo(DeviceInfoPO deviceInfo) {
        try {
            DeviceInfoPO deviceInfoUpdated = merge(deviceInfo);
            logger.info("=========================updateDeviceInfo:"+deviceInfoUpdated.toString());
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<DeviceInfoPO> getDeviceInfosById(Integer deviceId, Integer length, Integer offset) {
        try {
            String hql = String.format("from DeviceInfoPO where id = '%d'",deviceId) ;
            logger.info("==============================================设备ID查询:"+hql);
//            System.out.println(queryForPage(hql,0,50));
            return queryForPage(hql,0,50);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

//    @Override
//    public <S extends DeviceInfoPO> S save(S entity) {
//        return null;
//    }
//
//    @Override
//    public <S extends DeviceInfoPO> Iterable<S> save(Iterable<S> entities) {
//        return null;
//    }
//
//    @Override
//    public DeviceInfoPO findOne(Integer integer) {
//        return null;
//    }
//
//    @Override
//    public boolean exists(Integer integer) {
//        return false;
//    }
//
//    @Override
//    public Iterable<DeviceInfoPO> findAll() {
//        return null;
//    }
//
//    @Override
//    public Iterable<DeviceInfoPO> findAll(Iterable<Integer> integers) {
//        return null;
//    }
//
//    @Override
//    public long count() {
//        return 0;
//    }
//
//    @Override
//    public void delete(Integer integer) {
//
//    }
//
//    @Override
//    public void delete(Iterable<? extends DeviceInfoPO> entities) {
//
//    }
//
//    @Override
//    public void deleteAll() {
//
//    }
}
