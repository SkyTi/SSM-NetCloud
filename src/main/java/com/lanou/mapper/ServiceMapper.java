package com.lanou.mapper;

import com.lanou.bean.Service;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ServiceMapper {
    int deleteByPrimaryKey(Integer serviceId);

    int insert(Service record);

    int insertSelective(Service record);

    Service selectByPrimaryKey(Integer serviceId);

    int updateByPrimaryKeySelective(Service record);

    int updateByPrimaryKey(Service record);

    // 查找所有业务账户信息

//    List<Service> findAllService();

    // 查询全部业务账号

    List<Service> findAllService();

    // 条件查询业务账号

    List<Service> findServiceByCondition(
            @Param("status") String status,
            @Param("osUsername") String osUsername,
            @Param("unixHost") String unixHost,
            @Param("idcardNo") String idcardNo
    );

}