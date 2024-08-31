package org.example.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.Literally.SystemLiterally;
import org.example.domain.enity.SysMenu;
import org.example.mapper.SysMenuMapper;
import org.example.service.SysMenuService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;


/**
 * 菜单权限表(SysMenu)表服务实现类
 *
 * @author makejava
 * @since 2024-02-23 10:45:11
 */
@Service("sysMenuService")
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    @Override
    public List<String> get_List_Menu(Long id) {

//      如果id为1，返回全部菜单
        if (id==1L){
            LambdaQueryWrapper<SysMenu> wrapper=new LambdaQueryWrapper<>();
            wrapper.in(SysMenu::getMenuType, SystemLiterally.TYPE_MENU,SystemLiterally.TYPE_Button);
            wrapper.eq(SysMenu::getStatus,SystemLiterally.MENU_Static);
            List<SysMenu> menus=list(wrapper);
            List<String> res=menus.stream()
                    .map(SysMenu::getPerms).collect(Collectors.toList());
            return  res;
        }



        return getBaseMapper().getPerms(id);
    }

    @Override
    public List<SysMenu> getRoutersTree(Long userid) {

        SysMenuMapper mapper=getBaseMapper();
        List<SysMenu> menus=null;


        if (userid.equals(1L)){
            menus=mapper.selectAll_Routers();
        }else {
            menus=mapper.select_Routers(userid);
        }


        List<SysMenu> RoutersTree=toRoutersTree(menus,0L);
        return RoutersTree;



    }

    private List<SysMenu> toRoutersTree(List<SysMenu> menus, long l) {
        List<SysMenu> menuList = menus.stream()
                .filter(sysMenu -> sysMenu.getParentId().equals(l)).
                map(menu -> menu.setChildren(getChildren(menu,menus))).
                collect(Collectors.toList());
        return menuList;

    }

    private List<SysMenu> getChildren(SysMenu menu, List<SysMenu> menus) {
        List<SysMenu> childrenList = menus.stream()
                //通过过滤得到子菜单
                .filter(m -> m.getParentId().equals(menu.getId()))
                //如果有三层菜单的话，也就是子菜单的子菜单，我们就用下面那行递归(自己调用自己)来处理
                .map(m -> m.setChildren(getChildren(m,menus)))
//                这里停止递归的菜单上的设计，必须保证没有循环的id和parentId，否则就会陷入无限递归。
                .collect(Collectors.toList());
        return childrenList;
    }
}
