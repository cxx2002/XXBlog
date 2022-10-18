package com.cxx.controller;

import com.cxx.domain.ResponseResult;
import com.cxx.domain.dto.ChangeRoleStatusDto;
import com.cxx.domain.entity.Role;
import com.cxx.service.RoleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 陈喜喜
 * @date 2022-10-15 10:48
 */
@RestController
@RequestMapping("system/role")
public class RoleController {
    @Resource
    private RoleService roleService;

    @GetMapping("/listAllRole")
    public ResponseResult listAllRole(){
        List<Role> roles = roleService.selectRoleAll();
        return ResponseResult.okResult(roles);
    }
    /**
     * 根据角色编号获取详细信息
     */
    @GetMapping(value = "/{roleId}")
    public ResponseResult getInfo(@PathVariable Long roleId)
    {
        Role role = roleService.getById(roleId);
        return ResponseResult.okResult(role);
    }

    /**
     * 修改保存角色  先更新角色信息数据  然后再删除原有role--menu表数据  再新增
     */
    @PutMapping
    public ResponseResult edit(@RequestBody Role role)
    {
        roleService.updateRole(role);
        return ResponseResult.okResult();
    }

    /**
     * 删除角色
     * @param id
     */
    @DeleteMapping("/{id}")
    public ResponseResult remove(@PathVariable(name = "id") Long id) {
        roleService.removeById(id);
        return ResponseResult.okResult();
    }


    /**
     * 新增角色
     */
    @PostMapping
    public ResponseResult add( @RequestBody Role role)
    {
        roleService.insertRole(role);
        return ResponseResult.okResult();

    }
    @GetMapping("/list")
    public ResponseResult list(Role role, Integer pageNum, Integer pageSize) {
        return roleService.selectRolePage(role,pageNum,pageSize);
    }

    @PutMapping("/changeStatus")
    public ResponseResult changeStatus(@RequestBody ChangeRoleStatusDto roleStatusDto){
        Role role = new Role();
        role.setId(roleStatusDto.getRoleId());
        role.setStatus(roleStatusDto.getStatus());
        return ResponseResult.okResult(roleService.updateById(role));
    }
}
