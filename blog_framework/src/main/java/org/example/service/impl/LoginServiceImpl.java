package org.example.service.impl;

import org.example.domain.ResponseResult;
import org.example.domain.enity.LoginUser;
import org.example.domain.enity.User;
import org.example.domain.vo.UserInfoVo;
import org.example.domain.vo.UserLoginVo;
import org.example.service.loginService;
import org.example.utils.BeancopyUtils;
import org.example.utils.JwtUtil;
import org.example.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Objects;


@Service
public class LoginServiceImpl implements loginService {

    @Autowired
    private AuthenticationManager manager;


    @Autowired
    private RedisCache redisCache;
    @Override
    public ResponseResult login(User user) {
        UsernamePasswordAuthenticationToken
                token=new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword());
        Authentication authentication
                =manager.authenticate(token);
        if (Objects.isNull(authentication)){
            throw new RuntimeException("用户名或密码错误");
        }
        LoginUser loginUser=(LoginUser) authentication.getPrincipal();
        String id=loginUser.getUser().getId().toString();
        String jwt= JwtUtil.createJWT(id);

//        将用户完整信息存入redis中
        redisCache.setCacheObject("user: "+id,loginUser);


//        返回响应体
        UserInfoVo infoVo= BeancopyUtils.copyBean(loginUser.getUser(), UserInfoVo.class);
        UserLoginVo loginVo=new UserLoginVo(jwt,infoVo);
        return ResponseResult.okResult(loginVo);
    }

    @Override
    public ResponseResult logout() {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser=(LoginUser) authentication.getPrincipal();//获取userdetals
        Long id=loginUser.getUser().getId();
        redisCache.deleteObject("user: "+id);
        return ResponseResult.okResult();
    }
}
