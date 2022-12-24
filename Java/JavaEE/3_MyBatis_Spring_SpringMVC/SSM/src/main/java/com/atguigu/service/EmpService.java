package com.atguigu.service;

import com.atguigu.pojo.Emp;
import com.github.pagehelper.PageInfo;

public interface EmpService {
    PageInfo<Emp> getEmployeeList(Integer pageNum);
}
