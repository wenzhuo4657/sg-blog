package org.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.domain.enity.SysMenu;

import java.util.List;


/**
 * 菜单权限表(SysMenu)表数据库访问层
 *
 * @author makejava
 * @since 2024-02-23 10:45:09
 */
@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    List<String> getPerms(Long id);

    List<SysMenu> selectAll_Routers();

    List<SysMenu> select_Routers(Long userid);
}
