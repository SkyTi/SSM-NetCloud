package com.lanou.controller;

import com.github.pagehelper.PageInfo;
import com.lanou.bean.Account;
import com.lanou.mapper.AccountMapper;
import com.lanou.service.AccountService;
import com.lanou.utils.AjaxResult;

import com.sun.org.apache.bcel.internal.generic.IADD;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Created by dllo on 17/10/23.
 */
@Controller
@Scope("prototype")
public class AccountController {


    @RequestMapping(value = "/accountList")
    public String account(){
        return "/account/account_list";
    }

    @RequestMapping(value = "/modifyAccount")
    public String modifyAccount(){
        return "/account/account_modi";
    }

    @RequestMapping(value = "/accountAdd")
    public String AccountAdd(){

        return "/account/account_add";
    }


    @RequestMapping(value = "/td2realName")
    public String td2realName(){

        return "/account/account_detail";
    }


    @Resource
    private AccountService accountService;


    @ResponseBody
    @RequestMapping(value = "/addAccount")
    public AjaxResult addAccount(Account account){

        System.out.println(account+"qweqwe");

        account.setCreateDate(new Timestamp(System.currentTimeMillis()));

        accountService.addAccount(account);

        System.out.println(account);

        return new AjaxResult(account);
    }

    @ResponseBody
    @RequestMapping(value = "/delAccount")
    public AjaxResult delAccount(@RequestParam("aid") Integer accountId){

        accountService.delAccount(accountId) ;

        return new AjaxResult("删除成功");
    }


    @ResponseBody
    @RequestMapping(value = "/startAccount")
    public AjaxResult startAccount(@RequestParam("sid") Integer accountId){

        accountService.findAccountById(accountId);

        return new AjaxResult("开通");
    }

    @ResponseBody
    @RequestMapping(value = "/openAccount")
    public AjaxResult openAccount(@RequestParam("sid") Integer accountId){

        accountService.findAccountById(accountId);

        return new AjaxResult("暂停");
    }


    @Resource
    private AccountMapper accountMapper;

    @ResponseBody
    @RequestMapping(value = "/findAccount")
    public AjaxResult findAccount(){

    List<Account> accountList = (List<Account>) accountMapper.findAccount();

        return new AjaxResult(accountList);
    }




    @ResponseBody
    @RequestMapping(value = "/account")
    public PageInfo<Account> accountList(@RequestParam("no") Integer pageNo,
                                         @RequestParam("size") Integer pageSize){

        return accountService.accountPageinfo(pageNo,pageSize);
    }

//    获取分页信息

    @ResponseBody
    @RequestMapping(value = "/pageInfo1",method = RequestMethod.POST)
    public PageInfo<Account> pageInfo1(@RequestParam("pagesize") Integer pageSize){
        return accountService.pageinfo1(pageSize);
    }

    @ResponseBody
    @RequestMapping(value = "/accountpageinfo")
    public AjaxResult accountPageInfo(@RequestParam("no") Integer pageNo,
                                      @RequestParam("size") Integer pageSize){
        System.out.println(pageNo);
        System.out.println(pageSize);

        PageInfo<Account> accountPageInfo = accountService.queryStudentBuPage(pageNo,pageSize);

        return new AjaxResult("将资费列表进行分类,当前返回第一页",0,accountPageInfo);
    }


//    根据ID查找信息
    @ResponseBody
    @RequestMapping(value = "/saveIdInSession")
    public AjaxResult saveIdInSession(HttpServletRequest request,@RequestParam("updataAId") Integer id){

        request.getSession().setAttribute("dsaoifjoasdifjopa",id);

        System.out.println(id);

        return new AjaxResult("hahaha成功");
    }


    @ResponseBody
    @RequestMapping("/saveIdInGetSesstion")
    public AjaxResult saveIdInSession(HttpServletRequest request,Account account){

        HttpSession session = request.getSession();

        Integer accountId = (Integer) session.getAttribute("12d1carq");

        System.out.println(accountId);

        Account accountById = accountService.findAccountById(accountId);

        System.out.println(accountById);

        return new AjaxResult(accountById);
    }



}