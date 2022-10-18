package com.cxx.service.impl;

import com.cxx.domain.ResponseResult;
import com.cxx.domain.entity.LoginUser;
import com.cxx.domain.entity.User;
import com.cxx.domain.vo.BlogUserLoginVo;
import com.cxx.domain.vo.UserInfoVo;
import com.cxx.service.BlogLoginService;
import com.cxx.utils.BeanCopyUtils;
import com.cxx.utils.JwtUtil;
import com.cxx.utils.RedisCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author 陈喜喜
 * @date 2022-10-02 22:40
 */
@Service
@Slf4j
public class BlogLoginServiceImpl implements BlogLoginService {
    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    private RedisCache redisCache;
    @Resource
    private UserDetailsService userDetailsService;

    @Override
    public ResponseResult login(User user) {
        //UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(token);
        //判断是否通过
        //UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUserName());
        if (Objects.isNull(authenticate)){
            throw new RuntimeException("用户名或密码错误");
        }
        //获取userid 生成token
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);
        //把用户信息存入redis
        redisCache.setCacheObject("bloglogin:"+userId,loginUser,2L, TimeUnit.HOURS);
        log.info("bloglogin:"+userId+"已创建");
        //把token和userInfo封装vo 返回
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(loginUser, UserInfoVo.class);
        BlogUserLoginVo vo = new BlogUserLoginVo(jwt, userInfoVo);
        return ResponseResult.okResult(vo);
    }

    @Override
    public ResponseResult logout() {
        //获取token 解析获取token的userID  删除redis中的用户信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userId = loginUser.getUser().getId();
        redisCache.deleteObject("bloglogin:"+userId);
        log.info("bloglogin:"+userId+"已刪除");
        return ResponseResult.okResult();
    }
}
