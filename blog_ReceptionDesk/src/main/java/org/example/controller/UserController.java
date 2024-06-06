package org.example.controller;

import org.example.annotation.SystemLog;
import org.example.domain.ResponseResult;
import org.example.domain.enity.User;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;


    @GetMapping("/userInfo")
    public ResponseResult userinfo(){
       return userService.userInfo();
    }


    @PutMapping("/userInfo")
    public  ResponseResult updateUserInfo(@RequestBody User user){
        return userService.updateUserInfo(user);
    }

    @PostMapping("/register")
    @SystemLog
    public  ResponseResult register(@RequestBody  User user){
        return  userService.register(user);
    }

}
