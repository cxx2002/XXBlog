package com.cxx.controller;

import com.cxx.annotation.SystemLog;
import com.cxx.domain.ResponseResult;
import com.cxx.domain.entity.User;
import com.cxx.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author 陈喜喜
 * @date 2022-10-05 11:47
 */
@RestController
@RequestMapping("user")
public class UserController {
    @Resource
    private UserService userService;

    @GetMapping("userInfo")
    public ResponseResult userInfo() {
        return userService.userInfo();
    }

    @PutMapping("userInfo")
    @SystemLog(businessName = "更新用户信息")
    public ResponseResult updateUserInfo(@RequestBody User user) {
        return userService.updateUserInfo(user);
    }

    @PostMapping("register")
    public ResponseResult registerUser(@RequestBody User user) {
        return userService.registerUser(user);
    }
}
