package com.lanou.service;

import com.github.pagehelper.PageInfo;
import com.lanou.bean.RoleInfo;


public interface RoleInfoService {

   // 分页显示账户信息
    PageInfo<RoleInfo> pageinfo(Integer pageNo, Integer pageSize);

    // 通过条件查找角色

    boolean findByName(String name);

    // 添加

    void insertRole(RoleInfo roleInfo);

    // 添加信息到中间表

    void insertIntoRoleModule(Integer roleId,Integer moduleId);

    // 修改角色信息

    void updateRole(RoleInfo role);

   // 根据id删除中间表的内容

    void deleteRoleModule(Integer roleId);

    // 通过id查找角色

    RoleInfo findRoleByRoleId(Integer roleId);

    // 删除角色

    void delRole(Integer roleId);
}
