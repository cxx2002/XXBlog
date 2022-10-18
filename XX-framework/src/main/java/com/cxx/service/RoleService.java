package com.cxx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cxx.domain.ResponseResult;
import com.cxx.domain.entity.Role;

import java.util.List;

/**
* @author 陈喜喜
* @description 针对表【sys_role(角色信息表)】的数据库操作Service
* @createDate 2022-10-11 10:04:29
*/
public interface RoleService extends IService<Role> {

    List<String> selectRoleKeyByUserId(Long id);

    List<Role> selectRoleAll();

    void updateRole(Role role);

    void insertRole(Role role);

    ResponseResult selectRolePage(Role role, Integer pageNum, Integer pageSize);

    List<Long> selectRoleIdByUserId(Long userId);
}
