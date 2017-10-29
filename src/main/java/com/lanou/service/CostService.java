package com.lanou.service;


import com.github.pagehelper.PageInfo;
import com.lanou.bean.Cost;

import java.util.List;


public interface CostService {

    // 查找所有资费

    List<Cost> findAllCost();

    // 增加资费信息

    boolean addCost(Cost cost);

    // 修改资费信息

    boolean updateCost(Cost cost);

    // 通过id删除

    boolean deleteCost(int costId);

   // 通过id查询

    Cost selectById(int costId);

    // 分页显示资费信息

    PageInfo<Cost> pageinfo(Integer pageNo, Integer pageSize);


     // 修改状态

    boolean updateStatus(Cost cost);

    List<Cost> sortCost(Integer type, Integer order);

    PageInfo<Cost> sortCostWithPage(Integer type, Integer order,Integer pageNo, Integer pageSize);

    //查找所有资费类型
    List<Cost> findAllCostTypes();

}
