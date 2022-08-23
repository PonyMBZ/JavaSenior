package com.zzuli2.dao;

import com.zzuli.util.JDBCUtils;
import com.zzuli2.bean.Customers;
import org.junit.Test;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @auther pony
 * @create 2022-04-18 20:25
 */
public class CustomerDAOImplTest {
    private CustomerDAOImpl dao = new CustomerDAOImpl();

    @Test
    public void insert(){
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            //对日期格式化
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            //对日期解析
            Date parse = sdf.parse("1992-05-19");
            Customers customers = new Customers(1, "泠鸢yousa", "yousa@qq.com", new java.sql.Date(parse.getTime()));
            dao.insert(conn, customers);
            System.out.println("添加成功");
        } catch (Exception e) {
            e.printStackTrace();
        }  finally {
            JDBCUtils.closeResources(conn,null);
        }
    }

    @Test
    public void deleteById() {
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            dao.deleteById(conn, 27);
            System.out.println("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
        }  finally {
            JDBCUtils.closeResources(conn,null);
        }
    }

    @Test
    public void update() {
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date parse = sdf.parse("1992-05-15");
            Customers cust = new Customers(26, "hanser", "hanser@qq.com", new java.sql.Date(parse.getTime()));
            dao.update(conn, cust);
            System.out.println("修改成功");
        } catch (Exception e) {
            e.printStackTrace();
        }  finally {
            JDBCUtils.closeResources(conn,null);
        }
    }

    @Test
    public void getCustomerById() {
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            Customers customer = dao.getCustomerById(conn, 26);
            System.out.println(customer);
        } catch (Exception e) {
            e.printStackTrace();
        }  finally {
            JDBCUtils.closeResources(conn,null);
        }
    }

    @Test
    public void getAll() {
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            List<Customers> list = dao.getAll(conn);
            list.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }  finally {
            JDBCUtils.closeResources(conn,null);
        }
    }

    @Test
    public void getCount() {
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            Long count = dao.getCount(conn);
            System.out.println("表中的记录数为：" + count);
        } catch (Exception e) {
            e.printStackTrace();
        }  finally {
            JDBCUtils.closeResources(conn,null);
        }
    }

    @Test
    public void getMaxBirth() {
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            java.sql.Date maxBirth = dao.getMaxBirth(conn);
            System.out.println("列表中最大生日为：" + maxBirth);
        } catch (Exception e) {
            e.printStackTrace();
        }  finally {
            JDBCUtils.closeResources(conn,null);
        }
    }
}