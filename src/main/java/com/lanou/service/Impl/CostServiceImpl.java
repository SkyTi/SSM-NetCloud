package com.lanou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lanou.bean.Cost;
import com.lanou.mapper.CostMapper;
import com.lanou.service.CostService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;


@Service
public class CostServiceImpl implements CostService {

    @Resource
    private CostMapper costMapper;


    @Override
    public List<Cost> findAllCost() {
        return costMapper.findAllCost();
    }

    @Override
    public boolean addCost(Cost cost) {

        cost.setStatus("暂停");
        cost.setCreatime(new Timestamp(System.currentTimeMillis()));
        costMapper.insertSelective(cost);
        return true;
    }

    // 修改

    @Override
    public boolean updateCost(Cost cost) {
        costMapper.updateByPrimaryKeySelective(cost);
        return false;
    }

    //  删除

    @Override
    public boolean deleteCost(int costId) {
        costMapper.deleteByPrimaryKey(costId);
        return true;
    }

    // 通过id查询资费详细

    @Override
    public Cost selectById(int costId) {
        return costMapper.selectByPrimaryKey(costId);
    }

    // 页面分页信息

    @Override
    public PageInfo<Cost> pageinfo(Integer pageNo, Integer pageSize) {
        return queryCostByPage(pageNo, pageSize);
    }

    // 修改状态
    @Override
    public boolean updateStatus(Cost cost) {
            cost.setStatus("开通");
            cost.setStartime(new Timestamp(System.currentTimeMillis()));
            costMapper.updateByPrimaryKeySelective(cost);

        return true;
    }

    private PageInfo<Cost> queryCostByPage(Integer pageNo, Integer pageSize) {

        // 判断参数的合法性
        pageNo = pageNo == null ? 1 : pageNo;
        pageSize = pageSize == null ? 5 : pageSize;

        PageHelper.startPage(pageNo, pageSize);

        // 获取全部的信息
        List<Cost> studentList = costMapper.findAllCost();

        // 使用PageInfo对结果进行包装
        return new PageInfo<>(studentList);

    }

    @Override
    public List<Cost> sortCost(Integer type, Integer order) {

        if (type==0&&order==0){
            return costMapper.sortWithBasecostDesc();
        }
        if (type==0&&order==1){
            return costMapper.sortWithBasecostAsc();
        }
        if (type==1&&order==0){
            return costMapper.sortWithBasedurationDesc();
        }
        if (type==1&&order==1){
            return costMapper.sortWithBasedurationAsc();
        }
        return null;
    }

    @Override
    public PageInfo<Cost> sortCostWithPage(Integer type, Integer order,Integer pageNo, Integer pageSize) {

        pageNo = pageNo == null?1:pageNo;
        pageSize = pageSize == null?6:pageSize;

        PageHelper.startPage(pageNo,pageSize);

        if (type==0&&order==0){
            List<Cost> costList = costMapper.sortWithBasecostDesc();
            return new PageInfo<>(costList);
        }
        if (type==0&&order==1){
            List<Cost> costList = costMapper.sortWithBasecostAsc();
            return new PageInfo<>(costList);
        }
        if (type==1&&order==0){
            List<Cost> costList = costMapper.sortWithBasedurationDesc();
            return new PageInfo<>(costList);
        }
        if (type==1&&order==1){
            List<Cost> costList = costMapper.sortWithBasedurationAsc();
            return new PageInfo<>(costList);
        }
        return null;
    }

    @Override
    public List<Cost> findAllCostTypes() {

        List<Cost> costTypes = costMapper.findAllCost();

        return costTypes;
    }
}
