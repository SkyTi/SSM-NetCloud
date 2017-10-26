package com.lanou.mapper;

import com.lanou.bean.Account;

import java.util.List;


public interface AccountMapper {
    int deleteByPrimaryKey(Integer accountId);

    int insert(Account record);

    int insertSelective(Account record);

    Account selectByPrimaryKey(Integer accountId);

    int updateByPrimaryKeySelective(Account record);

    int updateByPrimaryKey(Account record);

    List<Account> findAccount();

    List<Account> findAccountIdCard(String idcardNo);

//    Account findAccountById(Integer accountId);

}