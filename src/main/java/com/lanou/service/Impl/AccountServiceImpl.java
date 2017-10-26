package com.lanou.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lanou.bean.Account;
import com.lanou.mapper.AccountMapper;
import com.lanou.service.AccountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by dllo on 17/10/23.
 */
@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountMapper accountMapper;

    public int addAccount(Account account) {
        System.out.println(account);
        return accountMapper.insertSelective(account);

    }

    public int delAccount(Integer accountId) {
        return accountMapper.deleteByPrimaryKey(accountId);
    }

    public int updataAccount(Account account) {
        return accountMapper.updateByPrimaryKey(account);
    }

    public Account findAccountById(Integer AccountId) {

        return accountMapper.selectByPrimaryKey(AccountId);

    }

    public List<Account> findidcardNo(String idcardNo) {
        return accountMapper.findAccountIdCard(idcardNo);
    }


    public PageInfo<Account> accountPageinfo(Integer pageNo, Integer pageSize) {

//       获取PageInfo对象

        PageInfo<Account> pageInfo = queryStudentBuPage(pageNo,pageSize);

        return pageInfo;
    }


    public PageInfo<Account> pageinfo1(Integer pagesize) {

        return queryStudentBuPage(null,pagesize);

    }


    public PageInfo<Account> queryStudentBuPage(Integer pageNo, Integer pageSize) {

//        判断参数的合法性
        pageNo = pageNo == null ? 1: pageNo;
        pageSize = pageSize == null ? 5 : pageSize;

        PageHelper.startPage(pageNo,pageSize);

//        获取全部Cost信息
        List<Account> accountList =  accountMapper.findAccount();

        System.out.println(1231241);
        System.out.println(accountList);

//        使用PageInfo对结果进行包装
        PageInfo<Account> pageInfo = new PageInfo<Account>(accountList);

        System.out.println(pageInfo);

        return pageInfo;
    }
}
