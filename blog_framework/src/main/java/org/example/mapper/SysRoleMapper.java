package org.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.domain.enity.SysRole;

import java.util.List;


/**
 * 角色信息表(SysRole)表数据库访问层
 *
 * @author makejava
 * @since 2024-02-23 10:45:51
 */
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {

    List<String> get_roles(Long id);
}
