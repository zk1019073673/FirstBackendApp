package com.advantech.homework.controller;
import com.advantech.homework.controller.apidata.PageModel;
import com.advantech.homework.model.UserInfoPO;
import com.advantech.homework.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("v1.0/user")
public class UserInfoController {

    @Autowired
    private IUserService userService;
    private Logger logger = LoggerFactory.getLogger(UserInfoController.class);

    @RequestMapping(value="/info",method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> getUserInfo(
            @RequestParam(value = "length", defaultValue = "2147483647") Integer length,
            @RequestParam(value = "offset", defaultValue = "0") Integer offset ) {
        logger.info("=============================getUserInfo==============================");
        ResponseEntity<?> respEntity = null;
        List<UserInfoPO> userInfoList = userService.getUserInfo(length,offset);
        int total_count = userService.getUserInfocount();
        PageModel obj = new PageModel();
        obj.setData(userInfoList);
        obj.setTotalCount(total_count);
        respEntity = new ResponseEntity<>(obj, HttpStatus.OK);
        return respEntity;
    }

    @RequestMapping(value="/info",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> createUserInfo(
            @RequestBody UserInfoPO userInfo
    ){
        logger.info("===============================createUserInfo==================================");
        ResponseEntity<?> respEntity = null;

        Integer id = userService.createUserInfo(userInfo);
        respEntity = new ResponseEntity<>(new HashMap() {{put("id", id);}}, HttpStatus.OK);

        return respEntity;
    }

    @RequestMapping(value="/info",method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<?> deleteUserInfo(
            @RequestBody UserInfoPO userInfo
    ){
        logger.info("===============================deleteUserInfo===============================");
        ResponseEntity<?> respEntity = null;
        Boolean bool = userService.deleteUser(userInfo);
        respEntity = new ResponseEntity<>(new HashMap() {{put("result", bool);}}, HttpStatus.OK);
        return respEntity;
    }

    @RequestMapping(value="/info",method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<?> updateUserInfo(
            @RequestBody UserInfoPO userInfo
    ){
        logger.info("===============================updateUserInfo===============================");
        ResponseEntity<?> respEntity = null;
        Boolean bool = userService.updateUserInfo(userInfo);
        respEntity = new ResponseEntity<>(new HashMap() {{put("result", bool);}}, HttpStatus.OK);

        return respEntity;
    }

    @RequestMapping(value="/userInfo",method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> getUserInfoByUserName(
            @RequestParam(value = "username") String username,
            @RequestParam(value = "length", defaultValue = "50") Integer length,
            @RequestParam(value = "offset", defaultValue = "0") Integer offset ) {
        logger.info("================================getUserInfoByUserName==========================");
        ResponseEntity<?> respEntity = null;
        List<UserInfoPO> userInfoList = userService.getUserInfoByUserName(username,length,offset);
        int total_count = userService.getUserInfoByNameCount(username);
        PageModel obj = new PageModel();
        obj.setData(userInfoList);
        obj.setTotalCount(total_count);
        respEntity = new ResponseEntity<>(obj, HttpStatus.OK);
        return respEntity;
    }


}
