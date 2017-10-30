package com.lanou.controller;

import com.github.pagehelper.PageInfo;
import com.lanou.bean.Cost;
import com.lanou.service.CostService;
import com.lanou.utils.AjaxResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;



@Controller
public class CostController {

    @Resource
    private CostService costService;

    @RequestMapping("/")
    public String home() {
        return "index";
    }


     // 跳转资费列表页面

    @RequestMapping("/fee/fee_list")
    public String feeList() {

        return "fee/fee_list";
    }


    // 跳转资费信息添加页面

    @RequestMapping("/fee/fee_add")
    public String feeAdd() {
        return "fee/fee_add";
    }


     // 跳转资费修改页面

    @RequestMapping("/fee/fee_modi")
    public String feeModi() {
        return "fee/fee_modi";
    }


     // 跳转资费详情页面

    @RequestMapping("/fee/fee_detail")
    public String feeDetail() {
        return "fee/fee_detail";
    }


     // 删除资费信息

    @ResponseBody
    @RequestMapping("/deleteCost")
    public AjaxResult deleteCost(@RequestParam("costId") Integer id) {
        costService.deleteCost(id);
        return new AjaxResult(id);
    }



     // 添加资费信息

    @ResponseBody
    @RequestMapping(value = "/addcost")
    public AjaxResult addCost(Cost cost) {
        cost.setCreatime(new Date());
        costService.addCost(cost);
        System.out.println(cost);
        return new AjaxResult(1);
    }


     // 显示资费详情(存session部分)

    @ResponseBody
    @RequestMapping(value = "/saveid", method = RequestMethod.POST)
    public AjaxResult saveId(HttpServletRequest request, HttpServletResponse response,
                             @RequestParam("costId") Integer id) {
        HttpSession session = request.getSession();
        session.setAttribute("costId", id);
        return new AjaxResult(1);

    }


     // 显示资费详情(fee_detail.html请求数据)

    @ResponseBody
    @RequestMapping("/selectbyid")
    public AjaxResult selectById(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        int costId = (Integer) session.getAttribute("costId");
        Cost cost = costService.selectById(costId);
        return new AjaxResult(cost);
    }


     // 通过id修改

    @ResponseBody
    @RequestMapping(value = "/updatbyid", method = RequestMethod.POST)
    public AjaxResult updatById(HttpServletRequest request, HttpServletResponse response,
                                @RequestParam("name") String name,
                                @RequestParam("baseDuration") Integer baseDuration,
                                @RequestParam("baseCost") Integer baseCost,
                                @RequestParam("unitCost") Integer unitCost,
                                @RequestParam("descr") String descr) {
        HttpSession session = request.getSession();
        int costId = (Integer) session.getAttribute("costId");
        Cost cost = new Cost();
        cost.setCostId(costId);
        cost.setName(name);
        cost.setBaseDuration(baseDuration);
        cost.setBaseCost(baseCost);
        cost.setUnitCost(unitCost);
        cost.setDescr(descr);
        costService.updateCost(cost);
        return new AjaxResult(1);
    }


     // 显示全部信息

    @ResponseBody
    @RequestMapping(value = "/getbypage")
    public PageInfo<Cost> getFeeByPage(@RequestParam("no") Integer pageNo,
                                       @RequestParam("size") Integer pageSize) {
        return costService.pageinfo(pageNo, pageSize);
    }


     // 修改状态

    @ResponseBody
    @RequestMapping(value = "/updatestatus")
    public AjaxResult updateStatus(Cost cost) {
        costService.updateStatus(cost);
        return new AjaxResult(1);
    }



    @RequestMapping("/index")
    public String index() {
        return "index";
    }



     // 排序加分页

    @ResponseBody
    @RequestMapping(value = "/sortCost")
    public AjaxResult sortCost(@RequestParam("sortType") Integer type,
                               @RequestParam("sortOrder") Integer order){
        System.out.print(type);
        System.out.println(" "+order);
        List<Cost> costListSorted = costService.sortCost(type, order);

        return new AjaxResult(costListSorted);
    }

    @ResponseBody
    @RequestMapping(value = "/sortCostWithPage")
    public AjaxResult sortCostWithPage(@RequestParam("sortType") Integer type,
                                       @RequestParam("sortOrder") Integer order,
                                       @RequestParam("no") Integer pageNo,
                                       @RequestParam("size") Integer pageSize){

        PageInfo<Cost> costPageInfo = costService.sortCostWithPage(type, order,pageNo,pageSize);
        return new AjaxResult(costPageInfo);
    }

    //查找所有资费类型
    @ResponseBody
    @RequestMapping(value = "/getAllCostType")
    public AjaxResult getAllCostType(){
        List<Cost> allCost = costService.findAllCostTypes();
        return new AjaxResult(allCost);
    }


}
