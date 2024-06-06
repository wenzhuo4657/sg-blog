package org.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.domain.AppHttpCodeEnum;
import org.example.domain.ResponseResult;
import org.example.domain.enity.User;
import org.example.domain.vo.UserInfoVo;
import org.example.exception.SystemException;
import org.example.mapper.UserMapper;
import org.example.service.UserService;
import org.example.utils.BeancopyUtils;
import org.example.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 用户表(User)表服务实现类
 *
 * @author makejava
 * @since 2024-01-19 11:42:59
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public ResponseResult userInfo() {
        Long userid= SecurityUtils.getUserId();
        User user=getById(userid);
        UserInfoVo vo = BeancopyUtils.copyBean(user, UserInfoVo.class);
        return ResponseResult.okResult(vo);
    }

    @Override
    public ResponseResult updateUserInfo(User user) {
        updateById(user);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult register(User user) {
//        检查非空
        if (!StringUtils.hasText(user.getUserName())){
            throw new SystemException(AppHttpCodeEnum.Username_not_NULL);
        }
        if (!StringUtils.hasText(user.getEmail()) ){
            throw new SystemException(AppHttpCodeEnum.Email_not_NULL);
        }
        if (!StringUtils.hasText(user.getPassword())) {
            throw new SystemException(AppHttpCodeEnum.Password_not_NULL);
        }
//        检查是否与数据库中信息重复
//        此处先不写，

//        添加

        String endcode=passwordEncoder.encode(user.getPassword());
        user.setPassword(endcode);
        save(user);
        return ResponseResult.okResult();
    }


}
