package com.cxx.service;

import com.cxx.domain.ResponseResult;
import com.cxx.domain.entity.User;

/**
 * @author 陈喜喜
 * @date 2022-10-02 22:40
 */
public interface BlogLoginService {
    ResponseResult login(User user);

    ResponseResult logout();
}
