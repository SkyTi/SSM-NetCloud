package com.lanou.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by dllo on 17/10/26.
 */
@Controller
public class ServiceController {


    @RequestMapping(value = "/serviceList")
    public String serviceList(){

        return "/service/service_list";
    }
}
