package com.lanou.service;

import com.github.pagehelper.PageInfo;
import com.lanou.bean.Service;


public interface ServiceService {
    // 分页显示信息

    PageInfo<Service> pageinfo(Integer pageNo, Integer pageSize);


    PageInfo<Service> queryServiceByPage(Integer pageNo,Integer pageSize);

    // 通过id查询

    Service selectById(int serviceId);


    // 修改状态

    boolean updateService(Service service);


    // 添加信息

    boolean addService(Service service);

   // 模糊查询

    PageInfo<Service> queryServiceByCondition(Integer pageNo,Integer pageSize, String idcardNo,String osUsername,
                                              String unixHost,String status);
}
