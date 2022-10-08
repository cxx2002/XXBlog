package com.cxx.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @author 陈喜喜
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_role_menu")
public class RoleMenu {
    /** 角色ID */
    private Long roleId;

    /** 菜单ID */
    private Long menuId;
}