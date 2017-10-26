package com.lanou.service;

import com.github.pagehelper.PageInfo;
import com.lanou.bean.Cost;

import java.util.List;

/**
 * Created by dllo on 17/10/20.
 */
public interface CostService {

    int addfee(Cost cost);

    int delfee(Integer costId);

    int updatafee(Cost cost);

    int updateCost(Cost cost);

//    Cost finById(Integer id);

    Cost findCostById(Integer costId);



    PageInfo<Cost> costPageinfo(Integer pageNo, Integer pageSize);

    PageInfo<Cost>pageinfo(Integer pagesize);

    PageInfo<Cost> queryStudentByPage(Integer pageNo, Integer pageSize);

//    practice
//    PageInfo<Cost>pageinfo1(Integer pagesize);

}
