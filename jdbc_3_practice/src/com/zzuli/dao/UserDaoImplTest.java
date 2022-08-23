package com.zzuli.dao;

import com.zzuli.bean.User;
import com.zzuli.util.JDBCUtils;
import org.junit.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @auther pony
 * @create 2022-04-25 17:50
 */
public class UserDaoImplTest {
    private UserDaoImpl dao = new UserDaoImpl();
    @Test
    public void insert(){
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            User user = new User(1, "hanser", "板鸭123", "江西抚州", "17839940589");
            dao.insert(conn, user);
            System.out.println("添加成功！");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtils.closeResources(conn,null);
        }
    }

    @Test
    public void deleteById() {
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            dao.deleteById(conn, 4);
            System.out.println("删除成功！");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtils.closeResources(conn, null);
        }
    }

    @Test
    public void update() throws SQLException, IOException, ClassNotFoundException {
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            User user = new User(8, "hanser", "板鸭123", "江西", "13262016513");
            dao.update(conn, user);
            System.out.println("修改成功！");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtils.closeResources(conn,null);
        }

    }

    @Test
    public void getUserById() {
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            User user = dao.getUserById(conn, 8);
            System.out.println("查询成功，结果为：" + user);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtils.closeResources(conn,null);
        }
    }

    @Test
    public void getAll() {
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            List<User> list = dao.getAll(conn);
            System.out.println("user表的所有数据如下：");
            list.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtils.closeResources(conn,null);
        }
    }

    @Test
    public void getCount() {
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            Long count = dao.getCount(conn);
            System.out.println("user表的条目数如下：");
            System.out.println(count);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtils.closeResources(conn,null);
        }
    }

    @Test
    public void getMaxBirth() {

    }
}