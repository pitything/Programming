package com.atguigu.mapper;


import com.atguigu.pojo.Emp;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpMapper {
    List<Emp> getEmployeeList();
}
