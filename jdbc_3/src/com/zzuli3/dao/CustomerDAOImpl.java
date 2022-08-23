package com.zzuli3.dao;

import com.zzuli2.bean.Customers;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

/**
 * 优化：将参数 Customers.class 去掉，因为这里很明确的要操作 Customers，也不可能是传别的类。
 *
 * @auther pony
 * @create 2022-04-18 19:59
 */
public class CustomerDAOImpl extends BaseDao<Customers> implements CustomerDAO {
    @Override
    public void insert(Connection conn, Customers cust) {
        String sql = "insert into customers(name, email, birth)value(?,?,?)";
        update(conn, sql, cust.getName(), cust.getEmail(), cust.getBirth());
    }

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
        Customers customers = getInstance(conn, sql, id);
        return customers;
    }

    @Override
    public List<Customers> getAll(Connection conn) {
        String sql = "select id, name, email, birth from customers";
        List<Customers> list = getForList(conn, sql);
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
