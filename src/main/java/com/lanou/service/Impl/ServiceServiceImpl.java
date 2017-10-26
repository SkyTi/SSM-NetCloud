package com.lanou.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.lanou.mapper.ServiceMapper;
import com.lanou.service.ServiceService;
;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by dllo on 17/10/26.
 */
@Service
@Transactional
public class ServiceServiceImpl implements ServiceService {

    @Resource
    private ServiceMapper serviceMapper;

    public int addService(com.lanou.bean.Service service) {
        return serviceMapper.insertSelective(service);
    }

    public int delService(Integer serivceId) {
        return serviceMapper.deleteByPrimaryKey(serivceId);
    }

    public int updataService(com.lanou.bean.Service service) {
        return serviceMapper.updateByPrimaryKey(service);
    }

    public com.lanou.bean.Service findServiceById(Integer ServiceId) {
        return serviceMapper.selectByPrimaryKey(ServiceId);
    }
}
