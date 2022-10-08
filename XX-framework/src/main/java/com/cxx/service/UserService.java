package com.cxx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cxx.domain.ResponseResult;
import com.cxx.domain.entity.User;

/**
* @author 陈喜喜
* @description 针对表【sys_user(用户表)】的数据库操作Service
* @createDate 2022-10-02 22:19:56
*/
public interface UserService extends IService<User> {

    ResponseResult userInfo();

    ResponseResult updateUserInfo(User user);

    ResponseResult registerUser(User user);
}
