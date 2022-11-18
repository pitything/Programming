package com.atguigu.mybatis.mapper;

import com.atguigu.mybatis.pojo.Dept;
import org.apache.ibatis.annotations.Param;

public interface DeptMapper {
    /**
     * 根据id查询班级
     */
    Dept getDeptById(@Param("id")int id);
    /**
     * 根据id查询班级
     */
    Dept getDeptById2(@Param("id")int id);
}
