package com.cxx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cxx.domain.entity.UserRole;
import com.cxx.mapper.UserRoleMapper;
import com.cxx.service.UserRoleService;
import org.springframework.stereotype.Service;

/**
* @author 陈喜喜
* @description 针对表【sys_user_role(用户和角色关联表)】的数据库操作Service实现
* @createDate 2022-10-16 10:29:18
*/
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole>
    implements UserRoleService{

}




