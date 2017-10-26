package com.lanou.service;

import com.github.pagehelper.PageInfo;
import com.lanou.bean.Account;

import java.util.List;


/**
 * Created by dllo on 17/10/23.
 */
public interface AccountService {

    int addAccount(Account account);

    int delAccount(Integer accountId);

    int updataAccount(Account account);

    Account findAccountById(Integer AccountId);

    List<Account> findidcardNo(String idcardNo);

    PageInfo<Account> accountPageinfo(Integer pageNo,Integer pageSize);

    PageInfo<Account> pageinfo1(Integer pagesize);

    PageInfo<Account> queryStudentBuPage(Integer pageNo, Integer pageSize);



}
