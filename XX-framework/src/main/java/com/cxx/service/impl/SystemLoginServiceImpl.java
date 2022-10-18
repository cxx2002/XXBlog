package com.cxx.service.impl;

import com.cxx.domain.ResponseResult;
import com.cxx.domain.entity.LoginUser;
import com.cxx.domain.entity.User;
import com.cxx.service.LoginService;
import com.cxx.utils.JwtUtil;
import com.cxx.utils.RedisCache;
import com.cxx.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author 陈喜喜
 * @date 2022-10-02 22:40
 */
@Service
@Slf4j
public class SystemLoginServiceImpl implements LoginService {
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
        redisCache.setCacheObject("login:"+userId,loginUser,2L, TimeUnit.HOURS);
        log.info("login:"+userId+"已创建");
        //把token和userInfo封装 返回
        Map<String, String> map = new HashMap<>();
        map.put("token", jwt);
        return ResponseResult.okResult(map);
    }

    @Override
    public ResponseResult logout() {
        //获取当前用户id
        Long userId = SecurityUtils.getUserId();
        //删除redis中对应的值
        redisCache.deleteObject("login" + userId);
        log.info("login:"+userId+"已刪除");
        return ResponseResult.okResult();
    }
}
