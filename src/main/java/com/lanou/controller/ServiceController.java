package com.lanou.controller;

import com.github.pagehelper.PageInfo;
import com.lanou.bean.Service;
import com.lanou.service.ServiceService;
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

/**
 * 业务控制器
 * @author dllo
 * @date 2017/10/25
 */
@Controller
public class ServiceController {

    @Resource
    private ServiceService serviceService;

    /**
     * 跳转到业务账户列表
     * @return
     */
    @RequestMapping(value = "/service/service_list")
    public String account() {
        return "/service/service_list";
    }

    /**
     * 跳转到添加
     * @return
     */
    @RequestMapping(value = "/service/service_add")
    public String add() {
        return "/service/service_add";
    }

    /**
     * 跳转到详情
     * @return
     */
    @RequestMapping(value = "/service/service_detail")
    public String accountDetail(){
        return "/service/service_detail";
    }

    /**
     * 跳转到修改页面
     * @return
     */
    @RequestMapping(value = "/service/service_modi")
    public String accountModi() {
        return "/service/service_modi";
    }



    /**
     * 显示全部
     * @param pageNo
     * @param pageSize
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/getallservices")
    public PageInfo<Service> getAllService(@RequestParam("no") Integer pageNo,
                                            @RequestParam("size") Integer pageSize){
        return serviceService.queryServiceByPage(pageNo,pageSize);
    }

    /**
     * 显示详情(存session部分)
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/saveserviceid", method = RequestMethod.POST)
    public AjaxResult saveServiceId(HttpServletRequest request, HttpServletResponse response,
                             @RequestParam("serviceId") Integer id) {
        HttpSession session = request.getSession();
        session.setAttribute("serviceId", id);
        return new AjaxResult(1);
    }

    /**
     * 显示详情(service_detail.html请求数据)
     *
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping("/selectbyserviceid")
    public AjaxResult selectById(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        int serviceId = (Integer) session.getAttribute("serviceId");
        Service service = serviceService.selectById(serviceId);
        return new AjaxResult(service);
    }

    /**
     * 开启账户
     * @param sid
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/openservice")
    public AjaxResult openService(@RequestParam("serviceId") Integer sid) {
        Service service = serviceService.selectById(sid);

        service.setPauseDate(null);

        service.setStatus("开通");

        serviceService.updateService(service);

        return new AjaxResult(service);
    }


    /**
     * 暂停账户
     * @param sid
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/pauseservice")
    public AjaxResult pauseService(@RequestParam("serviceId") Integer sid) {
        Service service = serviceService.selectById(sid);

        service.setPauseDate(new Date());
        service.setStatus("暂停");

        serviceService.updateService(service);
        return new AjaxResult(service);
    }

    /**
     * 删除账务账户信息
     * @param sid
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delservice")
    public AjaxResult deleteService(@RequestParam("serviceId") Integer sid) {
        Service service = serviceService.selectById(sid);

        service.setCloseDate(new Date());
        service.setStatus("删除");

        serviceService.updateService(service);
        return new AjaxResult(service);

    }


    /**
     * 添加信息
     * @param service
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addservice", method = RequestMethod.POST)
    public AjaxResult addService(Service service) {
        serviceService.addService(service);
        return new AjaxResult(1);
    }

    /**
     * 模糊查询
     * @param pageNo
     * @param pageSize
     * @param idcardNo
     * @param osUsername
     * @param unixHost
     * @param status
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryservice", method = RequestMethod.POST)
    public PageInfo<Service> queryServiceByCondition(@RequestParam("no") Integer pageNo,
                                                     @RequestParam("size") Integer pageSize,
                                                     @RequestParam(value = "idcardNo",required = false) String idcardNo,
                                                     @RequestParam(value = "osUsername",required = false) String osUsername,
                                                     @RequestParam(value = "unixHost",required = false) String unixHost,
                                                     @RequestParam(value = "status",required = false) String status) {
        PageInfo<Service> pageInfo = serviceService.queryServiceByCondition(pageNo, pageSize,idcardNo,osUsername,unixHost,status);
        return pageInfo;
    }

}
