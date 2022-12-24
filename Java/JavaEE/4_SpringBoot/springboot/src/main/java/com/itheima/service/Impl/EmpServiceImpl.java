package com.itheima.service.Impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.Emp;
import com.itheima.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;

    @Override
    public PageInfo<Emp> getEmployeeList(Integer pageNum) {
        PageHelper.startPage(pageNum, 4);
        List<Emp> list = empMapper.getEmployeeList();
        PageInfo<Emp> page = new PageInfo<>(list, 5);
        return page;
    }
}
