package com.lanou.controller;

import com.lanou.bean.ModuleInfo;
import com.lanou.service.ModuleInfoService;
import com.lanou.utils.AjaxResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;


@Controller
public class ModuleInfoController {
    @Resource
    private ModuleInfoService moduleInfoService;

    @ResponseBody
    @RequestMapping(value = "/getAllCheckBox",method = RequestMethod.POST)
    public AjaxResult getAllCheckBox(){
        List<ModuleInfo> modules = moduleInfoService.findAllModules();
        return new AjaxResult(modules);
    }

}
