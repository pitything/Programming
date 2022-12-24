package com.itheima.service;


import com.github.pagehelper.PageInfo;
import com.itheima.pojo.Emp;

public interface EmpService {
    PageInfo<Emp> getEmployeeList(Integer pageNum);
}
