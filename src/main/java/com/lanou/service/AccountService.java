package com.lanou.service;

import com.github.pagehelper.PageInfo;
import com.lanou.bean.Account;


/**
 * Created by dllo on 17/10/23.
 */
public interface AccountService {

    int addAccount(Account account);

    int delAccount(Integer accountId);

    int updataAccount(Account account);

    Account findAccountById(Integer AccountId);

    PageInfo<Account> accountPageinfo(Integer pageNo,Integer pageSize);

    PageInfo<Account> pageinfo1(Integer pagesize);

    PageInfo<Account> queryStudentBuPage(Integer pageNo, Integer pageSize);



}
