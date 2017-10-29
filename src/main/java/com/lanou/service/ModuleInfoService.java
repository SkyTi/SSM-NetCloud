package com.lanou.service;

import com.lanou.bean.ModuleInfo;

import java.util.List;

/**
 *
 * @author dllo
 * @date 2017/10/28
 */
public interface ModuleInfoService {

    /**
     * 获得所有权限
     * @return
     */
    List<ModuleInfo> findAllModules();

}
