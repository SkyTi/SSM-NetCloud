package com.lanou.mapper;

import com.lanou.bean.Cost;

import java.util.List;


public interface CostMapper {
    int deleteByPrimaryKey(Integer costId);

    int insert(Cost record);

    int insertSelective(Cost record);

    Cost selectByPrimaryKey(Integer costId);

    int updateByPrimaryKeySelective(Cost record);

    int updateByPrimaryKey(Cost record);

//    boolean update(Cost cost);

    Cost findCostById(Integer costId);

    List<Cost> findFee();
}