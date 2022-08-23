package com.zzuli3.dao;

import com.zzuli2.bean.Customers;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

/**
 * 此接口用于规范针对于customer表的常用操作
 * @auther pony
 * @create 2022-04-18 19:36
 */
public interface CustomerDAO {
    //将 cust 对象添加到数据库中
    void insert(Connection conn, Customers cust);
    //针对指定的 id ,删除表中的一条记录
    void deleteById(Connection conn, int id);
    //针对内存中的 cust 对象，修改数据表中指定的记录
    void update(Connection conn, Customers cust);
    //针对指定的id查询得到对应的Customer对象
    Customers getCustomerById(Connection conn, int id);
    //查询表中所有记录对应的集合
    List<Customers> getAll(Connection conn);
    //返回数据表中的条目数
    Long getCount(Connection conn);
    //返回数据表中最大的生日
    Date getMaxBirth(Connection conn);
}
