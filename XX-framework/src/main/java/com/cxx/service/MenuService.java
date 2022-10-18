package com.cxx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cxx.domain.entity.Menu;

import java.util.List;

/**
* @author 陈喜喜
* @description 针对表【sys_menu(菜单权限表)】的数据库操作Service
* @createDate 2022-10-11 09:59:06
*/
public interface MenuService extends IService<Menu> {

    List<String> selectPermsByUserId(Long id);

    List<Menu> selectRouterMenuTreeByUserId(Long userId);

    List<Menu> selectMenuList(Menu menu);

    boolean hasChild(Long menuId);

    List<Long> selectMenuListByRoleId(Long roleId);
}
