package com.cxx.controller;

import com.cxx.domain.ResponseResult;
import com.cxx.domain.entity.LoginUser;
import com.cxx.domain.entity.Menu;
import com.cxx.domain.entity.User;
import com.cxx.domain.vo.AdminUserInfoVo;
import com.cxx.domain.vo.RoutersVo;
import com.cxx.domain.vo.UserInfoVo;
import com.cxx.enums.AppHttpCodeEnum;
import com.cxx.exception.SystemException;
import com.cxx.service.LoginService;
import com.cxx.service.MenuService;
import com.cxx.service.RoleService;
import com.cxx.utils.BeanCopyUtils;
import com.cxx.utils.RedisCache;
import com.cxx.utils.SecurityUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 陈喜喜
 * @date 2022-10-02 22:34
 */
@RestController
@RequestMapping
public class LoginController {
    @Resource
    private LoginService loginService;
    @Resource
    private MenuService menuService;
    @Resource
    private RoleService roleService;
    @Resource
    private RedisCache redisCache;

    @PostMapping("/user/login")
    public ResponseResult login(@RequestBody User user) {
        if (!StringUtils.hasText(user.getUserName())) {
            //提示必须要用户名
            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
        }
        return loginService.login(user);
    }

    @GetMapping("getRouters")
    public ResponseResult getRouters(){
        Long userId = SecurityUtils.getUserId();
        //查询menu  结果是tree的形式
        List<Menu> menus = menuService.selectRouterMenuTreeByUserId(userId);
        //封装结果返回
        return ResponseResult.okResult(new RoutersVo(menus));
    }

    @GetMapping("getInfo")
    public ResponseResult getInfo() {
        //获取当前登录用户信息
        LoginUser loginUser = SecurityUtils.getLoginUser();
        //根据当前登录用户id查询权限信息
        List<String> perms = menuService.selectPermsByUserId(loginUser.getUser().getId());
        //根据当前登录用户id查询角色信息
        List<String> roleKetList =roleService.selectRoleKeyByUserId(loginUser.getUser().getId());
        //根据当前登录用户id获取用户信息
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(loginUser.getUser(), UserInfoVo.class);
        //封装结果vo返回
        AdminUserInfoVo adminUserInfoVo = new AdminUserInfoVo(perms, roleKetList, userInfoVo);
        return ResponseResult.okResult(adminUserInfoVo);
    }

    @PostMapping("/user/logout")
    public ResponseResult logout() {
        return loginService.logout();
    }

}
