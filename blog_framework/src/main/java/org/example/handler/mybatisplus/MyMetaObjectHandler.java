package org.example.handler.mybatisplus;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.example.utils.SecurityUtils;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author 35238
 * @date 2023/7/26 0026 20:52
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    //只要对数据库执行了插入语句，那么就会执行到这个方法
    public void insertFill(MetaObject metaObject) {
        Long userId = null;
        try {
            //获取用户id
            userId = SecurityUtils.getUserId();
        } catch (Exception e) {
            e.printStackTrace();
            userId = -1L;//如果异常了，就说明该用户还没注册，我们就把该用户的userid字段赋值d为-1
        }

        this.setFieldValByName("createTime", new Date(), metaObject);
        this.setFieldValByName("createBy",userId , metaObject);
        this.setFieldValByName("updateTime", new Date(), metaObject);
        this.setFieldValByName("updateBy", userId, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateTime", new Date(), metaObject);
        this.setFieldValByName(" ", SecurityUtils.getUserId(), metaObject);
    }
}