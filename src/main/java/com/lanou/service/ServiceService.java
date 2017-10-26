package com.lanou.service;

import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

/**
 * Created by dllo on 17/10/26.
 */
public interface ServiceService {

    PageInfo<Service> pageinfo2(Integer pagesize);

    PageInfo<Service> queryStudentBuPage(Integer pageNo,Integer pageSize);
}
