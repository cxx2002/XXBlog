package com.cxx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cxx.domain.ResponseResult;
import com.cxx.domain.entity.User;
import com.cxx.domain.vo.UserInfoVo;
import com.cxx.enums.AppHttpCodeEnum;
import com.cxx.exception.SystemException;
import com.cxx.mapper.UserMapper;
import com.cxx.service.UserService;
import com.cxx.utils.BeanCopyUtils;
import com.cxx.utils.SecurityUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
* @author 陈喜喜
* @description 针对表【sys_user(用户表)】的数据库操作Service实现
* @createDate 2022-10-02 22:19:56
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Override
    public ResponseResult userInfo() {
        //获取当前用户id  根据用户id查询用户信息  封装成userInfoVo
        Long userId = SecurityUtils.getUserId();
        User user = super.getById(userId);
        UserInfoVo vo = BeanCopyUtils.copyBean(user, UserInfoVo.class);
        return ResponseResult.okResult(vo);
    }

    @Override
    public ResponseResult updateUserInfo(User user) {
        //这里最好使用updateWrapper来更新部分字段更为安全
        super.updateById(user);
        return ResponseResult.okResult();
    }

    @Resource
    private PasswordEncoder passwordEncoder;
    @Override
    public ResponseResult registerUser(User user) {
        //对数据进行非空判断以及不重复的判断  对密码进行加密处理  存入数据库
        if(!StringUtils.hasText(user.getUserName())) {
            throw new SystemException(AppHttpCodeEnum.USERNAME_NOT_NULL);
        }
        if(!StringUtils.hasText(user.getPassword())) {
            throw new SystemException(AppHttpCodeEnum.PASSWORD_NOT_NULL);
        }
        if(!StringUtils.hasText(user.getNickName())) {
            throw new SystemException(AppHttpCodeEnum.NICKNAME_NOT_NULL);
        }
        if(!StringUtils.hasText(user.getEmail())) {
            throw new SystemException(AppHttpCodeEnum.EMAIL_NOT_NULL);
        }
        if(userNameExist(user.getUserName())){
            throw new SystemException(AppHttpCodeEnum.USERNAME_EXIST);
        }
        if(nickNameExist(user.getNickName())){
            throw new SystemException(AppHttpCodeEnum.NICKNAME_EXIST);
        }
        String encodePassword1 = passwordEncoder.encode(user.getPassword());
        System.out.println(encodePassword1);
        String encodePassword2=new BCryptPasswordEncoder().encode(user.getPassword());//加密
        System.out.println(encodePassword2);
        user.setPassword(encodePassword1);
        super.save(user);
        return ResponseResult.okResult();
    }

    private boolean nickNameExist(String nickName) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getNickName, nickName);
        return super.count(queryWrapper)>0;
    }

    private boolean userNameExist(String userName) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName, userName);
        return super.count(queryWrapper)>0;
    }
}




