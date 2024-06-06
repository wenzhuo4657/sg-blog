package org.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.domain.ResponseResult;
import org.example.domain.enity.User;


/**
 * 用户表(User)表服务接口
 *
 * @author makejava
 * @since 2024-01-19 11:42:59
 */
public interface UserService extends IService<User> {

    ResponseResult userInfo();

    ResponseResult updateUserInfo(User user);

    ResponseResult register(User user);
}
