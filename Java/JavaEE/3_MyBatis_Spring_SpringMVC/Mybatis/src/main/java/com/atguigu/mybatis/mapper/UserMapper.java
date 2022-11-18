package com.atguigu.mybatis.mapper;

import com.atguigu.mybatis.pojo.Dept;
import com.atguigu.mybatis.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Date:2021/11/26
 * Author:ybc
 * Description:
 */
public interface UserMapper {

    /**
     * MyBatis面向接口编程的两个一致：
     * 1、映射文件的namespace要和mapper接口的全类名保持一致
     * 2、映射文件中SQL语句的id要和mapper接口中的方法名一致
     *
     * 表--实体类--mapper接口--映射文件
     */

    /**
     * 添加用户信息
     */
    int insertUser(User user);

    /**
     * 修改用户信息
     */
    void updateUser();

    /**
     * 删除用户信息
     */
    void deleteUser();

    /**
     * 根据id查询用户信息
     */
    User getUserById(@Param("id") int id);

    /**
     * 查询所有的用户信息
     */
    // List<User> getAllUser(String tableName);
    // List<Map> getAllUser(@Param("tableName") String tableName, @Param("col") String col);
    List<User> getAllUser(@Param("id") String id);

}
