package com.lanou.service;

import com.github.pagehelper.PageInfo;
import com.lanou.bean.Account;
import com.lanou.bean.Service;


public interface AccountService {

    PageInfo<Account> pageinfo(Integer pageNo, Integer pageSize);

    // 增加

    int addAccount(Account account);

    // 通过id查询

    Account selectById(int accountId);

    // 修改状态

    boolean updateAccount(Account account);

    // 模糊查询

    PageInfo<Account> queryAccountByCondition(Integer pageNo, Integer pageSize,Account account);

    // 通过身份号查找账务账户信息

    Account findAccountByIdCardNo(String idcardNo);




}
