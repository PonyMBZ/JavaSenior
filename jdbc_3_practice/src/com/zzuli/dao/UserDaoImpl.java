package com.zzuli.dao;

import com.zzuli.bean.User;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

/**
 * @auther pony
 * @create 2022-04-25 17:40
 */
public class UserDaoImpl extends BaseDao implements UserDAO{
    @Override
    public void insert(Connection conn, User user) {
        String sql = "insert into user(name, password, address, phone) value(?,?,?,?)";
        update(conn, sql, user.getName(), user.getPassword(), user.getAddress(), user.getPhone());
    }

    @Override
    public void deleteById(Connection conn, int id) {
        String sql = "delete from user where id = ?";
        update(conn, sql, id);
    }

    @Override
    public void update(Connection conn, User user) {
        String sql = "update user set name = ?, password = ?, address = ?, phone = ? where id = ?";
        update(conn, sql, user.getName(), user.getPassword(), user.getAddress(), user.getPhone(), user.getId());
    }

    @Override
    public User getUserById(Connection conn, int id) {
        String sql= "SELECT name, password, address, phone FROM user WHERE id = ?";
        User user = getInstance(User.class, conn, sql, id);
        return user;
    }

    @Override
    public List<User> getAll(Connection conn) {
        String sql = "SELECT id, name, password, address, phone FROM user";
        List<User> forList = getForList(User.class, conn, sql);
        return forList;
    }

    @Override
    public Long getCount(Connection conn) {
        String sql = "SELECT COUNT(*) FROM `user`";
        return getValue(conn, sql);
    }

    @Override
    public Date getMaxBirth(Connection conn) {
        return null;
    }
}
