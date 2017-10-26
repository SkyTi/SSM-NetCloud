package com.lanou.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lanou.bean.Cost;
import com.lanou.mapper.CostMapper;
import com.lanou.service.CostService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by dllo on 17/10/20.
 */
@Service
@Transactional
public class CostServiceImpl implements CostService {

    @Resource
    private CostMapper costMapper;

    public int addfee(Cost cost) {

        return costMapper.insert(cost);

    }

    public int delfee(Integer costId) {
        return costMapper.deleteByPrimaryKey(costId);
    }

    public int updatafee(Cost cost) {
        return costMapper.updateByPrimaryKey(cost);
    }

    public int updateCost(Cost cost) {
        return costMapper.updateByPrimaryKey(cost);
    }

    public Cost findCostById(Integer id) {

        Cost cost = costMapper.findCostById(id);

        return cost;
    }


    public PageInfo<Cost> costPageinfo(Integer pageNo, Integer pageSize) {

//        目标:获取PageInfo对象

        PageInfo<Cost> pageInfo = queryStudentByPage(pageNo,pageSize);

        return pageInfo;
    }
////practice
//    public List<Cost> costPageinfo1(Integer pageNo, Integer pageSize) {
//
////        目标:获取PageInfo对象
//
//        PageInfo<Cost> pageInfo = queryStudentByPage(pageNo,pageSize);
//
//        return pageInfo.getList();
//    }


    public PageInfo<Cost> pageinfo(Integer pagesize) {
        return queryStudentByPage(null,pagesize);
    }

//  practice
//    public PageInfo<Cost> pageinfo1(Integer pagesize) {
//        return queryStudentByPage(null,pagesize);
//    }


    public PageInfo<Cost> queryStudentByPage(Integer pageNo,Integer pageSize){

//        判断参数的合法性
        pageNo = pageNo == null?1 : pageNo;
        pageSize = pageSize == null?5 : pageSize;

        PageHelper.startPage(pageNo,pageSize);

//        获取全部的Cost信息
        List<Cost> costList = costMapper.findFee();
        System.out.println("029350934257u09357");
        System.out.println(costList);

//        使用PageInfo对结果进行包装
        PageInfo<Cost> pageInfo = new PageInfo<Cost>(costList);

        System.out.println(pageInfo);

        return pageInfo;

    }

}
