package com.itheima.mapper;


import com.itheima.pojo.Emp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
// @Repository
public interface EmpMapper {
    // @Select("select * from t_emp")
    List<Emp> getEmployeeList();
}
