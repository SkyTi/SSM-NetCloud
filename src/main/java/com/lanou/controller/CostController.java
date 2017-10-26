package com.lanou.controller;

import com.github.pagehelper.PageInfo;
import com.lanou.bean.Cost;
import com.lanou.mapper.CostMapper;
import com.lanou.service.CostService;
import com.lanou.utils.AjaxResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * Created by dllo on 17/10/20.
 */
@Controller
public class CostController {

    @Resource
    private CostService costService;

    @RequestMapping(value = "/login")
    public String test(){

        return "login";
    }

    @RequestMapping(value = "/feeList")
    public String feeList(){

        return "fee/fee_list";
    }

    @RequestMapping(value = "/feeAdd")
    public String feeAdd(){

        return "/fee/fee_add";
    }

    @RequestMapping(value = "/updFee")
    public String updFee(){

        return "/fee/fee_modi";
    }

    @ResponseBody
    @RequestMapping(value = "/addFee")
    public AjaxResult addFee(Cost cost){

        cost.setCreatime(new Date());

        costService.addfee(cost);

//        System.out.println(cost);
        return new AjaxResult(cost);

    }

    @ResponseBody
    @RequestMapping(value = "/delFee")
    public AjaxResult delFee(@RequestParam("cid") Integer costId){

        costService.delfee(costId);

        return new AjaxResult(1);

    }

    @Resource
    private CostMapper costMapper;

    @ResponseBody
    @RequestMapping(value = "/findFee")
    public AjaxResult findFee(){

        List<Cost> costList = costMapper.findFee();

//        System.out.println(costList);

        return new AjaxResult(costList);
    }


    @ResponseBody
    @RequestMapping(value = "/updataFeeCost")
    public String updataFeeCost(HttpServletRequest request,@RequestParam("updataId") Integer costId){

//        System.out.println();

        HttpSession session = request.getSession();

        session.setAttribute("costid",costId);

        System.out.println("获取的ID为:"+costId);

        costService.findCostById(costId);

        return ("");
    }

    @ResponseBody
    @RequestMapping(value = "/modifyCostById")
    public AjaxResult modifyCostById(HttpServletRequest request){

        HttpSession session = request.getSession();
        Integer costId = (Integer) session.getAttribute("updataFeeCost");
        Cost costById = costService.findCostById(costId);
        return new AjaxResult("根据id查找到的资费条目信息",0,costById);
//        return "redirect:costpage_modi";
    }

    @ResponseBody
    @RequestMapping(value = "/updataFeeCostId")
    public AjaxResult updateCostById(HttpServletRequest request,Cost cost){
        HttpSession session = request.getSession();
        Integer costId = (Integer) session.getAttribute("updataFeeCost");
        cost.setCostId(costId);
        costService.updateCost(cost);
        return new AjaxResult("修改资费项目成功!");
    }




    @ResponseBody
    @RequestMapping(value = "/cost")
    public PageInfo<Cost> costList(@RequestParam("no") Integer pageNo,
                               @RequestParam("size") Integer pageSize){

        return costService.costPageinfo(pageNo,pageSize);
    }

//    获取分页信息
    @ResponseBody
    @RequestMapping(value = "/pageInfo",method = RequestMethod.POST)
    public PageInfo<Cost> pageInfo(@RequestParam("pagesize")Integer pageSize){

        return costService.pageinfo(pageSize);
    }


    @ResponseBody
    @RequestMapping(value = "/costpageinfo")
    public AjaxResult costPageInfo(@RequestParam("no") Integer pageNo, //第几页
                                   @RequestParam("size") Integer pageSize){

        System.out.println(pageNo);

        PageInfo<Cost> costPageInfo = (PageInfo<Cost>) costService.queryStudentByPage(pageNo, pageSize);


//        costStartOrNot(costList);

        return new AjaxResult("将资费列表进行分类,当前返回第一页",0,costPageInfo);

    }


////    practice
//    @ResponseBody
//    @RequestMapping(value = "/pageInfo1",method = RequestMethod.POST)
//    public PageInfo<Cost> pageInfo1(@RequestParam("pagesize") Integer pageSize){
//
//        return costService.pageinfo(pageSize);
//
//    }
////    获取分页信息
//    @ResponseBody
//    @RequestMapping(value = "/costpageinfo1")
//    public AjaxResult costPageInfo1(@RequestParam("no") Integer pageNo,
//                                    @RequestParam("size") Integer pageSize){
//
//        PageInfo<Cost> costPageInfo = (PageInfo<Cost>) costService.costPageinfo(pageNo,pageSize);
//
//        return new AjaxResult("将资费列表进行分类,当前返回第一页",0,costPageInfo);
//
//    }



}
