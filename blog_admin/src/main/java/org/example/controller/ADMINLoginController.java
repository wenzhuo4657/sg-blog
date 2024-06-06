package org.example.controller;

import org.example.annotation.SystemLog;
import org.example.domain.AppHttpCodeEnum;
import org.example.domain.ResponseResult;
import org.example.domain.enity.LoginUser;
import org.example.domain.enity.SysMenu;
import org.example.domain.enity.User;
import org.example.domain.vo.AdminUserInfoVo;
import org.example.domain.vo.RoutersVo;
import org.example.domain.vo.UserInfoVo;
import org.example.exception.SystemException;
import org.example.service.ADIM_loginService;
import org.example.service.SysMenuService;
import org.example.service.SysRoleService;
import org.example.service.loginService;
import org.example.utils.BeancopyUtils;
import org.example.utils.RedisCache;
import org.example.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class ADMINLoginController {

    @Autowired
    private ADIM_loginService loginService;

    @PostMapping("/user/login")
    @SystemLog
    public ResponseResult login(@RequestBody User user){
        if (!StringUtils.hasText(user.getUserName())){
//            避免某些大佬直接从接口处登录,进行一个简单的校验
            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
        }
      return   loginService.login(user);
    }




    @Autowired
    private SysMenuService menuService;

    @Autowired
    private SysRoleService roleService;

    @GetMapping("/getInfo")
    @SystemLog
    public ResponseResult<AdminUserInfoVo>  getInfo(){

        LoginUser Login= SecurityUtils.getLoginUser();

//        获取权限信息
        List<String> list_Menu=menuService.get_List_Menu(Login.getUser().getId());

//        获取角色信息

        List<String> list_role=roleService.get_list_role(Login.getUser().getId());
//        封装返回
        UserInfoVo userInfoVo= BeancopyUtils.copyBean(Login.getUser(), UserInfoVo.class);
        return ResponseResult.okResult(new AdminUserInfoVo(list_Menu,list_role,userInfoVo));
    }


    @Autowired
    private RedisCache redisCache;

    @PostMapping("/user/logout")
    public ResponseResult logout(){
        Long userid=SecurityUtils.getUserId();

        redisCache.deleteObject("ADIM_user: "+userid);
        return  ResponseResult.okResult();


    }

    @GetMapping("getRouters")
    @SystemLog
    public ResponseResult<RoutersVo> getRoutersVo(){
        Long userid=SecurityUtils.getUserId();
        List<SysMenu> menus=menuService.getRoutersTree(userid);

        return  ResponseResult.okResult(new RoutersVo(menus));
    }

}
