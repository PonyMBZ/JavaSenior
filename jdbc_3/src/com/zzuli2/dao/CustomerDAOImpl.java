package com.zzuli2.dao;

import com.zzuli2.bean.Customers;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

/**
 * @auther pony
 * @create 2022-04-18 19:59
 */
public class CustomerDAOImpl extends BaseDao implements CustomerDAO{

    /** @Author: pony
     * @Description: 将cust对象添加到数据库中
     * @Date: 15:40 2022/4/25
     * @param: conn
     * @param: cust
     * @return: void
     * @Version: 1.0
     */
    @Override
    public void insert(Connection conn, Customers cust) {
        String sql = "insert into customers(name, email, birth)value(?,?,?)";
        update(conn, sql, cust.getName(), cust.getEmail(), cust.getBirth());
    }

    /** @Author: pony
     * @Description: 针对指定id,删除表中数据
     * @Date: 15:41 2022/4/25
     * @param: conn
     * @param: id
     * @return: void
     * @Version: 1.0
     */
    @Override
    public void deleteById(Connection conn, int id) {
        String sql = "delete from customers where id = ?";
        update(conn, sql, id);
    }

    @Override
    public void update(Connection conn, Customers cust) {
        String sql = "update customers set name = ?, email = ?, birth = ? where id = ?";
        update(conn, sql, cust.getName(), cust.getEmail(), cust.getBirth(), cust.getId());
    }

    @Override
    public Customers getCustomerById(Connection conn, int id) {
        String sql = "select id, name, email, birth from customers where id = ?";
        Customers customers = getInstance(conn, Customers.class, sql, id);
        return customers;
    }

    @Override
    public List<Customers> getAll(Connection conn) {
        String sql = "select id, name, email, birth from customers";
        List<Customers> list = getForList(conn, Customers.class, sql);
        return list;
    }

    @Override
    public Long getCount(Connection conn) {
        String sql = "select count(*) from customers";
        return getValue(conn, sql);
    }

    @Override
    public Date getMaxBirth(Connection conn) {
        String sql = "select Max(birth) from customers";
        return getValue(conn, sql);
    }
}
