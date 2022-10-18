package com.cxx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cxx.domain.entity.LoginUser;
import com.cxx.domain.entity.User;
import com.cxx.mapper.MenuMapper;
import com.cxx.mapper.UserMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;


/**
 * @author 陈喜喜
 * @date 2022-10-02 22:57
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private MenuMapper menuMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //根据用户名查询用户信息
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName, username);
        User user = userMapper.selectOne(queryWrapper);
        //判断是否查到用户   如果没查到就抛出异常
        if (Objects.isNull(user)) {
            throw new RuntimeException("用户不存在");
        }

        //密码进行加密/解密  如果mysql的密码加密了就不用这两行代码
        // \对admin特殊照顾\因为只有admin用户在mysql的密码没有加密/框架会默认都解密
        if ("admin".equals(username)) {
            String a = new BCryptPasswordEncoder().encode(user.getPassword());
            user.setPassword(a);
        }

        //返回用户信息
        //todo 查询权限信息封装
        List<String> list = menuMapper.selectPermsByUserId(user.getId());
        return new LoginUser(user, list);
        //if(user.getType().equals(SystemConstants.ADMAIN)){
        //     List<String> list = menuMapper.selectPermsByUserId(user.getId());
        //     return new LoginUser(user, list);
        // }

        //return new LoginUser(user, null);
    }
}
