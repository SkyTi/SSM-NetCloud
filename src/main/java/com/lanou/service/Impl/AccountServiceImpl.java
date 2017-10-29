package com.lanou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lanou.bean.Account;
import com.lanou.mapper.AccountMapper;
import com.lanou.service.AccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;


/**
 * @author dllo
 * @date 2017/10/24
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountMapper accountMapper;

    /**
     * 页面分页信息
     *
     * @param pageNo   第几页
     * @param pageSize 每页的条数
     * @return
     */
    @Override
    public PageInfo<Account> pageinfo(Integer pageNo, Integer pageSize) {
        return queryCostByPage(pageNo, pageSize);
    }

    /**
     * 增加
     * <p>
     * 状态:开通 创建后的状态,也是启动状态
     *
     * @param account
     * @return
     */
    @Override
    public boolean addAccount(Account account) {
        account.setStatus("开通");
        account.setCreateDate(new Timestamp(System.currentTimeMillis()));
        accountMapper.insertSelective(account);
        return true;
    }

    // 通过id查询详细

    @Override
    public Account selectById(int accountId) {
        return accountMapper.selectByPrimaryKey(accountId);
    }


   // 分页模糊查询

    @Override
    public PageInfo<Account> queryAccountByCondition(Integer pageNo, Integer pageSize, Account account) {
        //判断参数合法性
        pageNo = pageNo == null ? 1 : pageNo;
        pageSize = pageSize == null ? 5 : pageSize;

        PageHelper.startPage(pageNo, pageSize);

        //获取全部账号
        List<Account> accounts = accountMapper.findByCondition(account);

        //使用PageInfo对结果进行包装
        PageInfo<Account> pageInfo = new PageInfo<Account>(accounts);

        return pageInfo;
    }

    private PageInfo<Account> queryCostByPage(Integer pageNo, Integer pageSize) {

        // 判断参数的合法性
        pageNo = pageNo == null ? 1 : pageNo;
        pageSize = pageSize == null ? 5 : pageSize;

        PageHelper.startPage(pageNo, pageSize);

        // 获取全部的信息
        List<Account> studentList = accountMapper.findAllAccount();

        // 使用PageInfo对结果进行包装
        return new PageInfo<>(studentList);
    }

    // 修改状态

    @Override
    public boolean updateAccount(Account account) {
        accountMapper.updateByPrimaryKeySelective(account);
        return true;
    }

    // 通过身份号查找

    @Override
    public Account findAccountByIdCardNo(String idcardNo) {
        return accountMapper.findAccountByIdCardNo(idcardNo);
    }
}
