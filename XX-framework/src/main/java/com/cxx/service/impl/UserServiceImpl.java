package com.cxx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cxx.domain.ResponseResult;
import com.cxx.domain.entity.User;
import com.cxx.domain.entity.UserRole;
import com.cxx.domain.vo.PageVo;
import com.cxx.domain.vo.UserInfoVo;
import com.cxx.domain.vo.UserVo;
import com.cxx.enums.AppHttpCodeEnum;
import com.cxx.exception.SystemException;
import com.cxx.mapper.UserMapper;
import com.cxx.service.UserRoleService;
import com.cxx.service.UserService;
import com.cxx.utils.BeanCopyUtils;
import com.cxx.utils.SecurityUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author 陈喜喜
* @description 针对表【sys_user(用户表)】的数据库操作Service实现
* @createDate 2022-10-02 22:19:56
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{
    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    private UserRoleService userRoleService;

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
        //两种加密方式是一样的，接口和实现类的关系
        //因为在SecurityConfig有配置，如下
        // @Bean
        //    public PasswordEncoder passwordEncoder(){
        //        return new BCryptPasswordEncoder();
        //    }
        //spring security中的BCryptPasswordEncoder方法采用SHA-256 +随机盐+密钥对密码进行加密
        //SHA系列是Hash算法，不是加密算法，使用加密算法意味着可以解密（这个与编码/解码一样），但是采用Hash处理，其过程是不可逆的
        //加密(encode)：注册用户时，使用SHA-256+随机盐+密钥把用户输入的密码进行hash处理，得到密码的hash值，然后将其存入数据库中
        //密码匹配(matches)：用户登录时，密码匹配阶段并没有进行密码解密（因为密码经过Hash处理，是不可逆的），而是使用相同的算法把
        //用户输入的密码进行hash处理,得到密码的hash值,然后将其与从数据库中查到的密码hash值进行比较.如果两者相同,用户输入的密码正确
        String encodePassword1 = passwordEncoder.encode(user.getPassword());//加密
        System.out.println(encodePassword1);
        String encodePassword2=new BCryptPasswordEncoder().encode(user.getPassword());//加密
        System.out.println(encodePassword2);
        //保存加密的密码
        user.setPassword(encodePassword1);
        super.save(user);
        //往用户-角色表里添加记录，默认为普通角色（写博文）
        UserRole userRole = new UserRole(user.getId(), 3L);
        userRoleService.save(userRole);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult selectUserPage(User user, Integer pageNum, Integer pageSize) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.like(StringUtils.hasText(user.getUserName()),User::getUserName,user.getUserName());
        queryWrapper.eq(StringUtils.hasText(user.getStatus()),User::getStatus,user.getStatus());
        queryWrapper.eq(StringUtils.hasText(user.getPhonenumber()),User::getPhonenumber,user.getPhonenumber());

        Page<User> page = new Page<>(pageNum,pageSize);

        page(page,queryWrapper);

        //转换成VO
        List<User> users = page.getRecords();
        List<UserVo> userVoList = users.stream()
                .map(u -> BeanCopyUtils.copyBean(u, UserVo.class))
                .collect(Collectors.toList());

        return ResponseResult.okResult(new PageVo(userVoList,page.getTotal()));
    }

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

    @Override
    @Transactional
    public void updateUser(User user) {
        // 删除用户与角色关联
        LambdaQueryWrapper<UserRole> userRoleUpdateWrapper = new LambdaQueryWrapper<>();
        userRoleUpdateWrapper.eq(UserRole::getUserId,user.getId());
        userRoleService.remove(userRoleUpdateWrapper);

        // 新增用户与角色管理
        this.insertUserRole(user);
        // 更新用户信息
        updateById(user);
    }

    @Override
    @Transactional
    public ResponseResult addUser(User user) {
        //密码加密处理
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        save(user);

        if(user.getRoleIds()!=null&&user.getRoleIds().length>0){
            this.insertUserRole(user);
        }
        return ResponseResult.okResult();
    }

    private void insertUserRole(User user) {
        List<UserRole> userRoles = Arrays.stream(user.getRoleIds())
                .map(roleId -> new UserRole(user.getId(), roleId)).collect(Collectors.toList());
        userRoleService.saveBatch(userRoles);
    }

    @Override
    public boolean checkUserNameUnique(String userName) {
        return count(Wrappers.<User>lambdaQuery().eq(User::getUserName,userName))==0;
    }

    @Override
    public boolean checkPhoneUnique(User user) {
        return count(Wrappers.<User>lambdaQuery().eq(User::getPhonenumber,user.getPhonenumber()))==0;
    }

    @Override
    public boolean checkEmailUnique(User user) {
        return count(Wrappers.<User>lambdaQuery().eq(User::getEmail,user.getEmail()))==0;
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




