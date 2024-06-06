package org.example.controller;

import org.example.annotation.SystemLog;
import org.example.domain.AppHttpCodeEnum;
import org.example.domain.ResponseResult;
import org.example.domain.enity.User;
import org.example.exception.SystemException;
import org.example.service.loginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class LoginController {

    @Autowired
    private loginService loginService;

    @PostMapping("/login")
    @SystemLog
    public ResponseResult login(@RequestBody User user){
        if (!StringUtils.hasText(user.getUserName())){
//            避免某些大佬直接从接口处登录,进行一个简单的校验
            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
        }
      return   loginService.login(user);
    }

    @PostMapping("/logout")
    public ResponseResult logout(){
        return loginService.logout();
    }
}
