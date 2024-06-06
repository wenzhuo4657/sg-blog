package org.example.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.domain.enity.SysRole;
import org.example.mapper.SysRoleMapper;
import org.example.service.SysRoleService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 角色信息表(SysRole)表服务实现类
 *
 * @author makejava
 * @since 2024-02-23 10:45:51
 */
@Service("sysRoleService")
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {
    @Override
    public List<String> get_list_role(Long id) {
        if (id==1L){
            List<String> roles=new ArrayList<>();
            roles.add("admin");
            return roles;
        }

        return getBaseMapper().get_roles(id);
    }
}
