package com.zzuli.PreparedStatement.crud;

import com.zzuli.bean.Customers;
import com.zzuli.util.JDBCUtils;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.*;

/**
 * @auther pony
 * @create 2022-04-14 14:54
 */
public class CustomerForQuery {

    @Test
    public void testQuery(){
        String sql1 = "SELECT id, `name`, email, birth FROM customers WHERE `name` = ?";
        Customers hanser = query(sql1, "hanser");
        System.out.println(hanser);
        String sql2 = "select name, email from customers where name = ?";
        Customers customers = query(sql2, "成龙");
        System.out.println(customers);
    }
    /**
     * 通用的查询操作
     */
    public Customers query(String sql, Object... args) {
        Connection coon = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //1、获取数据库连接
            coon = JDBCUtils.getConnection();
            //2、预处理sql语句
            ps = coon.prepareStatement(sql);
            //3、填充占位符
            for (int i = 0; i < args.length; i++) {
                ps.setString(i + 1, (String) args[i]);
            }
            //4、执行并返回结果集
            rs = ps.executeQuery();
            //5、处理结果集
            //5-1、获取结果集的元数据
            ResultSetMetaData rsmd = rs.getMetaData();
            //5-2、获取列数
            int columnCount = rsmd.getColumnCount();
            if (rs.next()) {
                Customers customers = new Customers();
                for (int i = 0; i < columnCount; i++) {
                    //获取列值
                    Object columnValue = rs.getObject(i + 1);
                    //获取列名
                    String columnName = rsmd.getColumnLabel(i + 1);

                    //通过反射找到 Customers 对象的叫 columnName 的属性，并将 columnValue 赋给这个属性
                    Field field = Customers.class.getDeclaredField(columnName);
                    field.setAccessible(true);
                    field.set(customers, columnValue);
                }
                return customers;
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(ps, coon, rs);
        }
        return null;
    }




    /**
     * 普通查询
     */

    @Test
    public void customerForQueryTest(){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //1、获取连接
            conn = JDBCUtils.getConnection();
            //2、预处理sql
            String sql = "SELECT id, `name`, email, birth FROM customers WHERE `name` = ?";
            ps = conn.prepareStatement(sql);
            //3、填充占位符
            ps.setString(1,"hanser");
            //4、执行并返回结果集
            rs = ps.executeQuery();
            while (rs.next()){
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String email = rs.getString(3);
                Date birth = rs.getDate(4);

                Customers customers = new Customers(id, name, email, birth);
                System.out.println(customers);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            //5、释放资源
            JDBCUtils.closeResource(ps, conn, rs);
        }
    }
}
