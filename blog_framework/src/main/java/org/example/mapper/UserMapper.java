package org.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.domain.enity.User;


/**
 * 用户表(User)表数据库访问层
 *
 * @author makejava
 * @since 2024-01-19 11:42:57
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
