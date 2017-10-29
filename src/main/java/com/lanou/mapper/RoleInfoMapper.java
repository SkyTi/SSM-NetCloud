package com.lanou.mapper;

import com.lanou.bean.RoleInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleInfoMapper {

    //删除
    int deleteByPrimaryKey(Integer roleId);

    int insert(RoleInfo record);

    // 添加

    int insertSelective(RoleInfo record);

    // 查询

    RoleInfo selectByPrimaryKey(Integer roleId);

    int updateByPrimaryKeySelective(RoleInfo record);

    // 更新

    int updateByPrimaryKey(RoleInfo record);

    // 查找所有

    List<RoleInfo> findAllRoleInfo();

    List<RoleInfo>findRoleInfoListByModuleId(@Param("moduleId") Integer moduleId);

    // 通过条件查找角色

    RoleInfo findByName(String name);

    // 添加信息到中间表

    void insertIntoRoleModule(@Param("roleId") Integer roleId, @Param("moduleId") Integer moduleId);

    // 根据roleId删除中间表中的信息

    void deleteRoleModule(Integer roleId);
}