package org.example.service;


import com.baomidou.mybatisplus.extension.service.IService;
import org.example.domain.enity.SysRole;

import java.util.List;

/**
 * 角色信息表(SysRole)表服务接口
 *
 * @author makejava
 * @since 2024-02-23 10:45:51
 */
public interface SysRoleService extends IService<SysRole> {

    List<String> get_list_role(Long id);
}
