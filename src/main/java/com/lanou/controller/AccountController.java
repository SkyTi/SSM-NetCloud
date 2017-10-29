package com.lanou.controller;

import com.github.pagehelper.PageInfo;
import com.lanou.bean.Account;
import com.lanou.service.AccountService;
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


@Controller
public class AccountController {

    @Resource
    private AccountService accountService;



     // 账户列表

    @RequestMapping(value = "/account/account_list")
    public String account() {
        return "/account/account_list";
    }


     // 添加

    @RequestMapping(value = "/account/account_add")
    public String add() {
        return "/account/account_add";
    }


     // 详情
    @RequestMapping(value = "/account/account_detail")
    public String accountDetail() {
        return "/account/account_detail";
    }


     // 修改页面

    @RequestMapping(value = "/account/account_modi")
    public String accountModi() {
        return "/account/account_modi";
    }


     // 显示全部信息

    @ResponseBody
    @RequestMapping(value = "/accountpage", method = RequestMethod.POST)
    public PageInfo<Account> getFeeByPage(@RequestParam("no") Integer pageNo,
                                          @RequestParam("size") Integer pageSize) {
        return accountService.pageinfo(pageNo, pageSize);
    }


     // 添加信息

    @ResponseBody
    @RequestMapping(value = "/addaccount", method = RequestMethod.POST)
    public AjaxResult addAccount(Account account) {
        accountService.addAccount(account);
        return new AjaxResult(1);
    }



     // 显示详情(存session部分)

    @ResponseBody
    @RequestMapping(value = "/saveaccountid", method = RequestMethod.POST)
    public AjaxResult saveAccountId(HttpServletRequest request, HttpServletResponse response,
                             @RequestParam("accountId") Integer id) {
        HttpSession session = request.getSession();
        session.setAttribute("accountId", id);
        return new AjaxResult(1);
    }


     // 显示资费详情

    @ResponseBody
    @RequestMapping("/selectbyaccountid")
    public AjaxResult selectById(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        int accountId = (Integer) session.getAttribute("accountId");
        Account account = accountService.selectById(accountId);
        return new AjaxResult(account);
    }


     // 通过id修改

    @ResponseBody
    @RequestMapping(value = "/updataccountbyid", method = RequestMethod.POST)
    public AjaxResult updatAccountById(HttpServletRequest request, HttpServletResponse response,
                                       @RequestParam("realName") String realName,
                                       @RequestParam("telephone") String telephone,
                                       @RequestParam("email") String email,
                                       @RequestParam("gender") String gender,
                                       @RequestParam("mailaddress") String mailaddress,
                                       @RequestParam("zipcode") String zipcode,
                                       @RequestParam("qq") String qq,
                                       @RequestParam("occupation") String occupation
    ) {
        HttpSession session = request.getSession();
        int accountId = (Integer) session.getAttribute("accountId");
        Account account = new Account();
        account.setAccountId(accountId);
        account.setRealName(realName);
        account.setTelephone(telephone);
        account.setEmail(email);
        account.setGender(gender);
        account.setMailaddress(mailaddress);
        account.setZipcode(zipcode);
        account.setQq(qq);
        account.setOccupation(occupation);
        accountService.updateAccount(account);
        return new AjaxResult(1);
    }



    //模糊查询
    @ResponseBody
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public PageInfo<Account> fuzzyquery(@RequestParam("no") Integer pageNo,
                                        @RequestParam("size") Integer pageSize,
                                        @RequestParam("idcardNo") String idCard,
                                        @RequestParam("realname") String name,
                                        @RequestParam("loginName") String loginName,
                                        @RequestParam("status") String status) {
        if (idCard.equals("")) {
            idCard = null;
        }
        if (loginName.equals("")) {
            loginName = null;
        }
        if (name.equals("")) {
            name = null;
        }
        if (status.equals("")) {
            status = null;
        }
        Account account = new Account();
        account.setIdcardNo(idCard);
        account.setLoginName(loginName);
        account.setRealName(name);
        account.setStatus(status);
        PageInfo<Account> pageInfo = accountService.queryAccountByCondition(pageNo, pageSize, account);

        return pageInfo;
    }



     // 删除账务账户信息

    @ResponseBody
    @RequestMapping(value = "/delaccount")
    public AjaxResult delete(@RequestParam("accountId") Integer aid) {
        Account account = accountService.selectById(aid);

        account.setCloseDate(new Date());
        account.setStatus("删除");

        accountService.updateAccount(account);
        return new AjaxResult(account);

    }


     // 开启账户

    @ResponseBody
    @RequestMapping(value = "/open")
    public AjaxResult open(@RequestParam("accountId") Integer aid) {
        Account account = accountService.selectById(aid);

        account.setPauseDate(null);

        account.setStatus("开通");

        accountService.updateAccount(account);

        return new AjaxResult(account);
    }


     // 暂停账户

    @ResponseBody
    @RequestMapping(value = "/pause")
    public AjaxResult pause(@RequestParam("accountId") Integer aid) {
        Account account = accountService.selectById(aid);

        account.setPauseDate(new Date());
        account.setStatus("暂停");

        accountService.updateAccount(account);
        return new AjaxResult(account);
    }



     // 通过身份号查找账务账户信息

    @ResponseBody
    @RequestMapping(value = "/getaccountbyidcardNo")
    public AjaxResult findAccountByIdCardNo(@RequestParam("idcardNo") String idcardNo){
        Account account = accountService.findAccountByIdCardNo(idcardNo);
        if (account == null){
            return new AjaxResult(0);
        }
        return new AjaxResult(account);
    }


}
