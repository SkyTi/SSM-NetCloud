package com.lanou.service.impl;

import com.lanou.bean.ModuleInfo;
import com.lanou.mapper.ModuleInfoMapper;
import com.lanou.service.ModuleInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class ModuleInfoServiceImpl implements ModuleInfoService {
    @Resource
    private ModuleInfoMapper moduleInfoMapper;


    @Override
    public List<ModuleInfo> findAllModules() {
        return moduleInfoMapper.findAllModules();
    }
}
