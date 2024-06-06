package org.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.domain.enity.SysMenu;

import java.util.List;


/**
 * 菜单权限表(SysMenu)表服务接口
 *
 * @author makejava
 * @since 2024-02-23 10:45:11
 */
public interface SysMenuService extends IService<SysMenu> {

    List<String> get_List_Menu(Long id);

    List<SysMenu> getRoutersTree(Long userid);
}
