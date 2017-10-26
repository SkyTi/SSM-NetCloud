package com.lanou.controller;

import com.lanou.bean.Account;
import com.lanou.bean.Service;
import com.lanou.service.AccountService;
import com.lanou.service.ServiceService;
import com.lanou.utils.AjaxResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by dllo on 17/10/26.
 */
@Controller
public class ServiceController {


    @RequestMapping(value = "/serviceList")
    public String serviceList(){

        return "/service/service_list";
    }


    @RequestMapping(value = "/serviceAdd")
    public String ServiceAdd(){
        return "/service/service_add";
    }


    @Resource
    private ServiceService serviceService;

    @Resource
    private AccountService accountService;

    @ResponseBody
    @RequestMapping(value = "/addService")
    public AjaxResult addService(Service service){

        serviceService.addService(service);

        System.out.println(service);

        return new AjaxResult(service);
    }

//    @ResponseBody
//    @RequestMapping(value = "/queryServicesIdCard")
//    public AjaxResult queryServicesIdCard(HttpServletRequest request,
//                                          @RequestParam("queryIdCard") Account idcardNo,
//                                          @RequestParam("queryNewIdCard") Account idCardNew){
//
//        accountService.findidcardNo(idcardNo);
//
//        request.getSession().setAttribute("1111",idCardNew);
//
//        System.out.println(idCardNew);
//
//        System.out.println(idcardNo);
//
//        if (idcardNo == idCardNew){
//
//            return new AjaxResult("匹配身份证号成功");
//        }else {
//            return new AjaxResult("没有此身份证号，请重新录入。");
//        }
//    }

}
