package com.cxx.controller;

import com.cxx.domain.ResponseResult;
import com.cxx.domain.entity.User;
import com.cxx.enums.AppHttpCodeEnum;
import com.cxx.exception.SystemException;
import com.cxx.service.BlogLoginService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 陈喜喜
 * @date 2022-10-02 22:34
 */
@RestController
@RequestMapping
public class BlogLoginController {
    @Resource
    private BlogLoginService blogLoginService;

    @PostMapping("/login")
    public ResponseResult login(@RequestBody User user){
        if(!StringUtils.hasText(user.getUserName())){
            //提示必须要用户名
            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
        }
        return blogLoginService.login(user);
    }

    @PostMapping("/logout")
    public ResponseResult logout(){
        return blogLoginService.logout();
    }

}
