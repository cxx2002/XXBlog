package com.cxx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cxx.domain.entity.Role;

import java.util.List;

/**
* @author 陈喜喜
* @description 针对表【sys_role(角色信息表)】的数据库操作Mapper
* @createDate 2022-10-11 10:04:29
* @Entity com.cxx.entity.Role
*/
public interface RoleMapper extends BaseMapper<Role> {

    List<String> selectRoleKeyByUserId(Long userId);

    List<Long> selectRoleIdByUserId(Long userId);

}




