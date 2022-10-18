package com.cxx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cxx.domain.entity.RoleMenu;

/**
* @author 陈喜喜
* @description 针对表【sys_role_menu(角色和菜单关联表)】的数据库操作Service
* @createDate 2022-10-15 10:53:18
*/
public interface RoleMenuService extends IService<RoleMenu> {

    void deleteRoleMenuByRoleId(Long id);
}
