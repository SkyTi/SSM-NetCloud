package com.lanou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lanou.bean.Service;
import com.lanou.mapper.ServiceMapper;
import com.lanou.service.ServiceService;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;


@org.springframework.stereotype.Service
public class ServiceServiceImpl implements ServiceService {

    @Resource
    private ServiceMapper serviceMapper;

    // 页面分页信息

    @Override
    public PageInfo<Service> pageinfo(Integer pageNo, Integer pageSize) {
        return queryServiceByPage(pageNo, pageSize);
    }


    //查询
    @Override
    public  PageInfo<Service> queryServiceByPage(Integer pageNo, Integer pageSize){
        // 判断参数的合法性
        pageNo = pageNo == null ? 1 : pageNo;

        pageSize = pageSize == null ? 5 : pageSize;

        PageHelper.startPage(pageNo, pageSize);

        // 获取全部的信息
        List<Service> serviceList = serviceMapper.findAllService();

        System.out.println(serviceList);

        // 使用PageInfo对结果进行包装
        return new PageInfo<Service>(serviceList);
    }

    // 通过id查询详细

    @Override
    public Service selectById(int serviceId) {
        return serviceMapper.selectByPrimaryKey(serviceId);
    }


    // 修改状态

    @Override
    public boolean updateService(Service service) {
        serviceMapper.updateByPrimaryKeySelective(service);
        return true;
    }

    // 添加信息

    @Override
    public boolean addService(Service service) {
        service.setStatus("开通");
        service.setCreateDate(new Timestamp(System.currentTimeMillis()));
        serviceMapper.insertSelective(service);
        return true;
    }

   // 模糊查询

    @Override
    public PageInfo<Service> queryServiceByCondition(Integer pageNo, Integer pageSize, String idcardNo, String osUsername, String unixHost, String status) {
        pageNo = pageNo == null ? 1 : pageNo;
        pageSize = pageSize == null ? 5 : pageSize;

        PageHelper.startPage(pageNo, pageSize);

        List<Service> serviceList = serviceMapper.findServiceByCondition(status,osUsername,unixHost,idcardNo);

        return new PageInfo<Service>(serviceList);
    }

}
