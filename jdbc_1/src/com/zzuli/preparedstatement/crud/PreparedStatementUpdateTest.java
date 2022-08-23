package com.zzuli.preparedstatement.crud;

import com.zzuli.util.JDBCUtils;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Properties;

/**
 * 使用PreparedStatement 替换 Statement，实现对数据表的增删改查操作
 * @auther pony
 * @create 2022-04-04 16:28
 */
public class PreparedStatementUpdateTest {

    @Test
    public void testCommonUpdate(){
//        String sql = "delete from customers where id = ?";
//        update(sql, 3);
        String sql = "update `order` set order_name = ? where order_id = ?";
        update(sql, "DD", "2");
    }


    //通用的增删改操作(可变形参)
    //sql中占位符的个数与可变形参的长度一致
    public void update(String sql, Object ...args) {
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            //1.获取数据库连接
            connection = JDBCUtils.getConnection();
            //2.预编译sql语句，返回PrepareStatement();
            ps = connection.prepareStatement(sql);
            //3.填充占位符
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i+1, args[i]);
            }
            //4.执行
            ps.execute();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            //5.资源的关闭
            JDBCUtils.closeResource(connection, ps);
        }
    }

    //向customers 表中添加一行数据
    @Test
    public void testUpdate(){
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            //一、连接数据库
            connection = JDBCUtils.getConnection();
            //二、编写sql语句，对数据库进行修改
            //1、预编译sql语句，返回 PreparedStatement 的实例
            String sql = "UPDATE customers Set birth = ? WHERE name = ?";
            //2、填充占位符
            ps = connection.prepareStatement(sql);
            //格式化
            SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");
            //解析
            java.util.Date parse = SDF.parse("1992-05-15");
            //填充占位符
            ps.setDate(1, new Date(parse.getTime()));
            ps.setString(2, "hanser");
            //3、执行sql语句
            ps.execute();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            //三、关闭资源
            JDBCUtils.closeResource(connection, ps);
        }

    }

    //向customers 表中添加一行数据
    @Test
    public void testInsert(){
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            //一、获取连接
            //1.四个基本数据：写到了jdbc.properties中
            //2.读取配置文件
            InputStream is = PreparedStatementUpdateTest.class.getClassLoader().getResourceAsStream("jdbc.properties");
            Properties properties = new Properties();
            properties.load(is);

            String user = properties.getProperty("user");
            String password = properties.getProperty("password");
            String url = properties.getProperty("url");
            String driverClass = properties.getProperty("driverClass");

            //3.加载驱动(①利用反射获取实现类的对象 ②注册驱动)
            Class.forName(driverClass);

            //4.获取数据库连接
            connection = DriverManager.getConnection(url, user, password);
            System.out.println(connection);

            //5.预编译sql语句，返回 PreparedStatement 的实例
            String sql = "insert into customers(name, email, birth) value(?,?,?)";//?占位符
            ps = connection.prepareStatement(sql);
            //6.填充占位符
            ps.setString(1, "hanser");
            ps.setString(2, "hanser@gmail.com");
            //格式化
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            //解析
            java.util.Date date = sdf.parse("1992-05-15");
            ps.setDate(3, new Date(date.getTime()));

            //7.执行sql
            ps.execute();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            //8.资源关闭
            try {
                if (ps != null){
                    ps.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            try {
                if (connection != null){
                    connection.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
