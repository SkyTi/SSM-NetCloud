package com.lanou.service;

import com.github.pagehelper.PageInfo;
import com.lanou.bean.Service;


/**
 * Created by dllo on 17/10/26.
 */
public interface ServiceService {

    int addService(Service service);

    int delService(Integer serivceId);

    int updataService(Service service);

    Service findServiceById(Integer ServiceId);



}
