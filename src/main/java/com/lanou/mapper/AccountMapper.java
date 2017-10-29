package com.lanou.mapper;

import com.lanou.bean.Account;

import java.util.List;


public interface AccountMapper {
    int deleteByPrimaryKey(Integer accountId);

    int insert(Account record);


     // 添加

    int insertSelective(Account record);


     // 通过id查询

    Account selectByPrimaryKey(Integer accountId);


     // 修改

    int updateByPrimaryKeySelective(Account record);

    int updateByPrimaryKey(Account record);


     // 查找所有

    List<Account> findAllAccount();


     // 条件查询

    List<Account> findByCondition(Account account);


     // 通过身份证号查询

    Account findAccountByIdCardNo(String idcardNo);

}