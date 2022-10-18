package com.cxx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cxx.constants.SystemConstants;
import com.cxx.domain.entity.Menu;
import com.cxx.mapper.MenuMapper;
import com.cxx.service.MenuService;
import com.cxx.utils.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 陈喜喜
 * @description 针对表【sys_menu(菜单权限表)】的数据库操作Service实现
 * @createDate 2022-10-11 09:59:06
 */
@Service("menuService")
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu>
        implements MenuService {

    @Override
    public List<String> selectPermsByUserId(Long id) {
        //如果是管理员 返回所有权限
        if (SecurityUtils.isAdmin()) {
            LambdaQueryWrapper<Menu> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.in(Menu::getMenuType, SystemConstants.MENU, SystemConstants.BUTTON);
            queryWrapper.eq(Menu::getStatus, SystemConstants.STATUS_NORMAL);
            List<Menu> menus = super.list(queryWrapper);
            List<String> perms = menus.stream()
                    .map(Menu::getPerms)
                    .collect(Collectors.toList());
            return perms;
        }
        //否则返回其对应角色所具有的权限
        return this.getBaseMapper().selectPermsByUserId(id);
    }

    @Override
    public List<Menu> selectRouterMenuTreeByUserId(Long userId) {
        List<Menu> menus;
        //判断是否是管理员   如果是 返回所有符合要求的menu
        if (SecurityUtils.isAdmin()) {
            menus = this.getBaseMapper().selectAllRouterMenu();
        } else {
            //如果不是  返回当前用户所对应角色具有的menu
            menus = this.getBaseMapper().selectRouterMenuTreeByUserId(userId);
        }
        //构建tree
        //先找出第一层的子菜单  然后去找他们的子菜单设置到children属性中
        List<Menu> menuTree = this.builderMenuTree(menus,0L);
        return menuTree;
    }

    @Override
    public List<Menu> selectMenuList(Menu menu) {
        LambdaQueryWrapper<Menu> queryWrapper = new LambdaQueryWrapper<>();
        //menuName模糊查询
        queryWrapper.like(StringUtils.hasText(menu.getMenuName()),Menu::getMenuName,menu.getMenuName());
        queryWrapper.eq(StringUtils.hasText(menu.getStatus()),Menu::getStatus,menu.getStatus());
        //排序 parent_id和order_num
        queryWrapper.orderByAsc(Menu::getParentId,Menu::getOrderNum);
        List<Menu> menus = list(queryWrapper);;
        return menus;
    }

    @Override
    public boolean hasChild(Long menuId) {
        LambdaQueryWrapper<Menu> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Menu::getParentId,menuId);
        return count(queryWrapper) != 0;
    }

    @Override
    public List<Long> selectMenuListByRoleId(Long roleId) {
        return getBaseMapper().selectMenuListByRoleId(roleId);
    }

    //menus是查到改用户角色对应所用的菜单
    //先根据0L作为父菜单id筛选  再根据筛选出来的子菜单的id作为父菜单id继续筛选  以此递归
    //递归出口是.filter(m -> m.getParentId().equals(menu.getId()))为空
    //则stream流链式编程无法进行，就不进行下一步的递归
    //已封装到Util.SystemConverter
    private List<Menu> builderMenuTree(List<Menu> menus, long parentId) {
        List<Menu> menuTree = menus.stream()
                .filter(menu -> menu.getParentId().equals(parentId))
                .map(menu -> menu.setChildren(this.getChildren(menu, menus)))
                .collect(Collectors.toList());
        return menuTree;
    }

    //获取存入参数的子菜单集合
    private List<Menu> getChildren(Menu menu, List<Menu> menus) {
        List<Menu> childrenList = menus.stream()
                .filter(m -> m.getParentId().equals(menu.getId()))
                .map(m -> m.setChildren(this.getChildren(m, menus)))//递归填充children
                .collect(Collectors.toList());
        return childrenList;
    }

}




