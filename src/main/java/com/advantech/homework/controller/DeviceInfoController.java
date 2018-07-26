package com.advantech.homework.controller;

import com.advantech.homework.controller.apidata.PageModel;
import com.advantech.homework.model.DeviceInfoPO;
import com.advantech.homework.service.IDeviceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("v1.0/device")
public class DeviceInfoController {
    @Autowired
    private IDeviceService deviceService;
    private Logger logger = LoggerFactory.getLogger(DeviceInfoController.class);

    @RequestMapping(value="/info",method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> getDeviceInfo(
            @RequestParam(value = "length", defaultValue = "2147483647") Integer length,
            @RequestParam(value = "offset", defaultValue = "0") Integer offset ) {
        logger.info("===========================getDeviceInfo=================================");

        ResponseEntity<?> respEntity = null;
        List<DeviceInfoPO> deviceInfoList = deviceService.getDeviceInfo(length,offset);
        int total_count = deviceService.getDeviceInfocount();
        PageModel obj = new PageModel();
        obj.setData(deviceInfoList);
        obj.setTotalCount(total_count);
        respEntity = new ResponseEntity<>(obj, HttpStatus.OK);
        return respEntity;
    }

    @RequestMapping(value="/deviceInfo",method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> getDeviceInfoByDeviceName(
            @RequestParam(value = "deviceName") String deviceName,
            @RequestParam(value = "length", defaultValue = "50") Integer length,
            @RequestParam(value = "offset", defaultValue = "0") Integer offset ) {
        logger.info("===================getDeviceInfoByDeviceName=====================");
        ResponseEntity<?> respEntity = null;
        List<DeviceInfoPO> deviceInfoList = deviceService.getDeviceInfoByDeviceName(deviceName,length,offset);
        int total_count = deviceService.getDeviceInfoByNameCount(deviceName);
        PageModel obj = new PageModel();
        obj.setData(deviceInfoList);
        obj.setTotalCount(total_count);
        respEntity = new ResponseEntity<>(obj, HttpStatus.OK);
        return respEntity;
    }

    @RequestMapping(value="/info",method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<?> deleteDeviceInfo(
            @RequestBody DeviceInfoPO deviceInfo
    ){
        logger.info("===============================deleteDeviceInfo=====================================");
        ResponseEntity<?> respEntity = null;
        Boolean bool = deviceService.deleteDevice(deviceInfo);
        respEntity = new ResponseEntity<>(new HashMap() {{put("result", bool);}}, HttpStatus.OK);
        return respEntity;
    }

    @RequestMapping(value="/info",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> createDeviceInfo(
            @RequestBody DeviceInfoPO deviceInfo
    ){
        logger.info("============================createDeviceInfo====================================");
        ResponseEntity<?> respEntity = null;

        Integer id = deviceService.createDeviceInfo(deviceInfo);
        respEntity = new ResponseEntity<>(new HashMap() {{put("id", id);}}, HttpStatus.OK);

        return respEntity;
    }

    @RequestMapping(value="/info",method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<?> updateDeviceInfo(
            @RequestBody DeviceInfoPO deviceInfo
    ){
        logger.info("===============================updateDeviceInfo================================");
        ResponseEntity<?> respEntity = null;
        Boolean bool = deviceService.updateDeviceInfo(deviceInfo);
        respEntity = new ResponseEntity<>(new HashMap() {{put("result", bool);}}, HttpStatus.OK);

        return respEntity;
    }
}
