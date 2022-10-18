package com.cxx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cxx.domain.entity.Menu;

import java.util.List;

/**
* @author 陈喜喜
* @description 针对表【sys_menu(菜单权限表)】的数据库操作Mapper
* @createDate 2022-10-11 09:59:06
* @Entity com.cxx.entity.Menu
*/
public interface MenuMapper extends BaseMapper<Menu> {

    List<String> selectPermsByUserId(Long userId);

    List<Menu> selectAllRouterMenu();

    List<Menu> selectRouterMenuTreeByUserId(Long userId);

    List<Long> selectMenuListByRoleId(Long roleId);

}




