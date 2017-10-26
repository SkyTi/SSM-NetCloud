package com.lanou.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import com.lanou.mapper.ServiceMapper;
import com.lanou.service.ServiceService;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by dllo on 17/10/26.
 */
@Service
public class ServiceServiceImpl implements ServiceService {

    @Resource
    private ServiceMapper serviceMapper;

    public PageInfo<Service> pageinfo2(Integer pagesize) {
        return queryStudentBuPage(null,pagesize);
    }

    public PageInfo<Service> queryStudentBuPage(Integer pageNo, Integer pageSize) {

//        判断参数的合法性

        pageNo = pageNo == null ? 1: pageNo;

        pageSize = pageSize == null ? 5 :pageSize;

        PageHelper.startPage(pageNo,pageSize);

//        获取全部Service信息

        List<Service> serviceList = serviceMapper.findServicelist();

        System.out.println(serviceList);

//        使用PageInfo2对结果进行包装

        PageInfo<Service> pageInfo = new PageInfo<Service>(serviceList);

        return null;
    }
}
