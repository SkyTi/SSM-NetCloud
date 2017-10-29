package com.lanou.mapper;

import com.lanou.bean.Cost;

import java.util.List;


public interface CostMapper {


     // 通过id删除

    int deleteByPrimaryKey(Integer costId);


     // 添加资费

    int insert(Cost record);

     // 添加资费套餐

    int insertSelective(Cost record);


     // 通过id查询

    Cost selectByPrimaryKey(Integer costId);


     // 修改

    int updateByPrimaryKeySelective(Cost record);

    // 修改资源套餐

    int updateByPrimaryKey(Cost record);


    // 查找所有资费信息

    List<Cost> findAllCost();


    List<Cost> sortWithBasecostDesc();


    List<Cost> sortWithBasecostAsc();


    List<Cost> sortWithBasedurationDesc();


    List<Cost> sortWithBasedurationAsc();



}