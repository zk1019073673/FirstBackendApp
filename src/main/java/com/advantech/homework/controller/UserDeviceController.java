package com.advantech.homework.controller;
import com.advantech.homework.controller.apidata.PageModel;
import com.advantech.homework.model.DeviceInfoPO;
import com.advantech.homework.model.UserDeviceInfoPO;
import com.advantech.homework.model.UserInfoPO;
import com.advantech.homework.service.IUserDeviceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("v1.0/userDevice")
public class UserDeviceController {

    @Autowired
    private IUserDeviceService userDeviceService;
    private Logger logger = LoggerFactory.getLogger(UserDeviceController.class);

    @RequestMapping(value="/ids",method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> getUserDeviceIds(
            @RequestParam(value = "userId") Integer userId,
            @RequestParam(value = "length", defaultValue = "50") Integer length,
            @RequestParam(value = "offset", defaultValue = "0") Integer offset ) {
        logger.info("=====================getUserDeviceIds======================");
        ResponseEntity<?> respEntity = null;
        List<UserDeviceInfoPO> userDeviceInfoPOS = userDeviceService.getUserDeviceIds(userId,offset,length);
        List<Integer> deviceIds = new ArrayList<>();
        for (UserDeviceInfoPO userDevice : userDeviceInfoPOS) {
            logger.info("============================DeviceId:"+userDevice.getDeviceId());
            deviceIds.add(userDevice.getDeviceId());
        }
        List<DeviceInfoPO> deviceInfoPOS = new ArrayList<>();
        for (int i=0;i<deviceIds.size();i++) {
//            deviceInfoPOS.add((DeviceInfoPO) userDeviceService.getDeviceInfosById(deviceIds.get(i),offset,length));
            for (DeviceInfoPO deviceInfo : userDeviceService.getDeviceInfosById(deviceIds.get(i),offset,length)) {
                deviceInfoPOS.add(deviceInfo);
            }
        }
        int total_count = deviceIds.size();
        PageModel obj = new PageModel();
        obj.setData(deviceInfoPOS);
        obj.setTotalCount(total_count);
        respEntity = new ResponseEntity<>(obj, HttpStatus.OK);
        return respEntity;
    }

    @RequestMapping(value="/info",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> shareDevicesForUser(
            @RequestBody List<UserDeviceInfoPO> userDeviceInfoPOList){
        logger.info("==========================shareDevicesForUser============================");
        ResponseEntity<?> respEntity = null;
        List<Integer> deviceIds = new ArrayList<>();
        for (UserDeviceInfoPO userDeviceInfoPO : userDeviceInfoPOList) {
            deviceIds.add(userDeviceService.shareDevicesForUser(userDeviceInfoPO));
        }
//        Integer deviceId = userDeviceService.shareDevicesForUser(userDeviceInfoPO);
        respEntity = new ResponseEntity<>(new HashMap() {{put("deviceIds", deviceIds);}}, HttpStatus.OK);
        return respEntity;
    }

    @RequestMapping(value="/device",method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<?> deleteUserInfo(
            @RequestBody UserDeviceInfoPO userDeviceInfoPO
    ){
        logger.info("===============================deleteUserDevice===============================");
        ResponseEntity<?> respEntity = null;
        Boolean bool = userDeviceService.delDevByDevId(userDeviceInfoPO);
        respEntity = new ResponseEntity<>(new HashMap() {{put("result", bool);}}, HttpStatus.OK);
        return respEntity;
    }

}
