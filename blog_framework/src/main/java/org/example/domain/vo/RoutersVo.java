package org.example.domain.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.domain.enity.SysMenu;

import java.util.List;

/**
 * @author 35238
 * @date 2023/8/4 0004 19:41
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoutersVo {
    private List<SysMenu> menus;
}