package com.cxx.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cxx.domain.ResponseResult;
import com.cxx.domain.entity.Comment;
import com.cxx.domain.entity.Role;
import com.cxx.domain.entity.User;
import com.cxx.domain.vo.UserInfoAndRoleIdsVo;
import com.cxx.domain.vo.UserStatusVo;
import com.cxx.enums.AppHttpCodeEnum;
import com.cxx.exception.SystemException;
import com.cxx.service.CommentService;
import com.cxx.service.RoleService;
import com.cxx.service.UserRoleService;
import com.cxx.service.UserService;
import com.cxx.utils.SecurityUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 陈喜喜
 * @date 2022-10-15 14:38
 */
@RestController
@RequestMapping("system/user")
public class UserController {
    @Resource
    private UserService userService;
    @Resource
    private RoleService roleService;
    /**
     * 获取用户列表
     */
    @GetMapping("/list")
    public ResponseResult list(User user, Integer pageNum, Integer pageSize) {
        return userService.selectUserPage(user,pageNum,pageSize);
    }

    /**
     * 新增用户
     */
    @PostMapping
    public ResponseResult add(@RequestBody User user)
    {
        if(!StringUtils.hasText(user.getUserName())){
            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
        }
        if (!userService.checkUserNameUnique(user.getUserName())){
            throw new SystemException(AppHttpCodeEnum.USERNAME_EXIST);
        }
        if (!userService.checkPhoneUnique(user)){
            throw new SystemException(AppHttpCodeEnum.PHONENUMBER_EXIST);
        }
        if (!userService.checkEmailUnique(user)){
            throw new SystemException(AppHttpCodeEnum.EMAIL_EXIST);
        }
        return userService.addUser(user);
    }

    /**
     * 根据用户编号获取详细信息
     */
    @GetMapping(value = { "/{userId}" })
    public ResponseResult getUserInfoAndRoleIds(@PathVariable(value = "userId") Long userId)
    {
        List<Role> roles = roleService.selectRoleAll();
        User user = userService.getById(userId);
        //当前用户所具有的角色id列表
        List<Long> roleIds = roleService.selectRoleIdByUserId(userId);

        UserInfoAndRoleIdsVo vo = new UserInfoAndRoleIdsVo(user,roles,roleIds);
        return ResponseResult.okResult(vo);
    }

    /**
     * 修改用户
     */
    @PutMapping
    public ResponseResult edit(@RequestBody User user) {
        userService.updateUser(user);
        return ResponseResult.okResult();
    }

    @PutMapping("/changeStatus")
    public ResponseResult changeStatus(@RequestBody UserStatusVo userStatusVo){
        User user = new User();
        user.setId(userStatusVo.getId());user.setStatus(userStatusVo.getStatus());
        userService.updateById(user);
        return ResponseResult.okResult();
    }

    /**
     * 删除用户
     */
    @Resource
    private CommentService commentService;
    @Resource
    private UserRoleService userRoleService;
    @DeleteMapping("/{userIds}")
    public ResponseResult remove(@PathVariable List<Long> userIds) {
        if(userIds.contains(SecurityUtils.getUserId())){
            return ResponseResult.errorResult(500,"不能删除当前你正在使用的用户");
        }
        userService.removeByIds(userIds);

        for (Long userId : userIds) {
        //删除该用户对应的用户-角色表数据
            userRoleService.removeById(userId);
        //删除该用户的相关评论
            LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Comment::getCreateBy, userId)
                    .or()
                    .eq(Comment::getToCommentUserId, userId);

            commentService.remove(queryWrapper);
        }
        /*userIds.stream().map(userId -> {
            LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Comment::getCreateBy, userId)
                    .or()
                    .eq(Comment::getToCommentUserId, userId);

            commentService.remove(queryWrapper);
            return userId;
        });*/

        return ResponseResult.okResult();
    }

}
