package com.advantech.homework.controller;

import com.advantech.homework.authority.Audience;
import com.advantech.homework.authority.JwtHelper;
import com.advantech.homework.model.UserInfoPO;
import com.advantech.homework.model.UserPOJO;
import com.advantech.homework.service.IUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import java.security.Key;

import static com.advantech.homework.authority.JwtHelper.createJWT;

@RestController
@RequestMapping("v1.0/login")
public class LoginController {
    @Autowired
    private IUserService userService;
    @Autowired
    private Audience audience;

    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping(value="/info",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> validateUserInfo(
//            @RequestBody UserPOJO loginParams,
            HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        UserPOJO userInfo = new ObjectMapper().readValue(request.getInputStream(), UserPOJO.class);
        String username = userInfo.getUsername();
        String password = userInfo.getPassword();
        String tokenTs = userInfo.getTokenTs();

        System.out.println("TokenTs:" + tokenTs);
        ResponseEntity<?> responseEntity = null;
        if (username == null || password == null) {
            throw new IllegalArgumentException();
        }
        List<UserInfoPO> userInfoList = userService.validateUserInfo(username,password);
        //生成token
//        Key key = MacProvider.generateKey();
//        String compactJws = Jwts.builder()
//                .setSubject(username)
//                .setExpiration(new Date(System.currentTimeMillis() + 30 * 60 * 1000))
//                .signWith(SignatureAlgorithm.HS512, key)
//                .compact();
//        String token = compactJws;
        /**
         * String name,
         * String userId,
         * String role,
         * String audience,
         * String issuer,
         * long TTLMillis,
         * String base64Security
         */

        String token = null;
        token = JwtHelper.createJWT(
                userInfoList.get(0).getUsername(),
                userInfoList.get(0).getId().toString(),
                userInfoList.get(0).getType().toString(),
                userInfoList.get(0).getPhone(),
                userInfoList.get(0).getEmail(),
                audience.getClientId(),
                audience.getName(),
                audience.getExpiresSecond() * 1000,
                audience.getBase64Secret()
        );
//        System.out.println(userInfoList);
        Map<String,Object> map = new HashMap<>();
        if(userInfoList != null){
            map.put("userInfoList",userInfoList);
            map.put("Bearer",token);
        }
        responseEntity = new ResponseEntity<>(new HashMap() {{put("map", map);}}, HttpStatus.OK);
        System.out.println("Token:" + token);
        return responseEntity;
    }

}
