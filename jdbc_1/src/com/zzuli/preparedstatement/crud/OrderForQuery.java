package com.zzuli.preparedstatement.crud;

import com.zzuli.bean.Order;
import com.zzuli.util.JDBCUtils;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.Properties;

/**
 * 针对 Order 表的通用查询操作
 * @auther pony
 * @create 2022-04-05 21:33
 */
public class OrderForQuery {
    /*
     * 针对于表的字段名与类的属性名不相同的情况
     * 1. 必须声明sql时，使用类的属性名来命名字段的别名
     * 2. 使用ResultSetMetaData时，需要使用getColumnLabel()来替换getColumnName(),
     * 获取列的别名
     * 说明，如果 sql,中没有给字段其别名，getColumnLabel()获取的就是别名
     */
    @Test
    public void testOrderForQurey(){
        String sql = "select order_id orderId, order_name orderName, order_date orderDate from `order` where order_id = ?";
        Order order = orderForQuery(sql, 1);
        System.out.println(order);
    }
    /** @Author: pony
     * @Description: 通用的 Order 表的查询操作
     * @Date: 15:15 2022/4/8
     * @param:
     * @return: com.zzuli.bean.Order
     * @Version: 1.0
     */
    public Order orderForQuery(String sql, Object ...args){
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = JDBCUtils.getConnection();
            ps = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }

            //执行获取结果集
            rs = ps.executeQuery();
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
                if (rs.next()){
                    Order order = new Order();
                    for (int i = 0; i < columnCount; i++) {
                        //获取列值：通过ResultSet
                        Object columnValue = rs.getObject(i + 1);
                        //获取列名：通过ResultSetMetaData :不推荐使用
//                        String columnName = metaData.getColumnName(i + 1);
                        //获取列的别名：没有别名就获取列名
                        String columnLabel = metaData.getColumnLabel(i + 1);
                        //通过反射：将对象指定名 columnName 的属性赋值为指定的值 columnValue
                        Field field = Order.class.getDeclaredField(columnLabel);
                        field.setAccessible(true);
                        field.set(order, columnValue);
                    }
                    return order;
                }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection, ps, rs);
        }

        return null;
    }

    @Test
    public void testQuery1() throws IOException, ClassNotFoundException, SQLException {

        //一、获取连接
        //1.四个基本信息，写到了jdbc.properties配置文件中
        //2.读取配置文件，获取信息
        InputStream is = OrderForQuery.class.getClassLoader().getResourceAsStream("jdbc.properties");
        Properties properties = new Properties();
        properties.load(is);

        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String url = properties.getProperty("url");
        String driverClass = properties.getProperty("driverClass");
        //3.加载驱动(①创建Driver实体类 ②注册驱动)
        Class.forName(driverClass);
        //4.获取连接
        Connection connection = DriverManager.getConnection(url, user, password);

//        System.out.println(connection);
        //二、执行sql语句
        //1.预编译sql语句
        String sql = "SELECT order_id, order_name, order_date FROM `order` WHERE order_id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setObject(1,1);
        ResultSet rs = ps.executeQuery();
        if (rs.next()){
            int id = (int) rs.getObject(1);
            String name = (String) rs.getObject(2);
            Date date = (Date) rs.getObject(3);

            Order order = new Order(id, name, date);
            System.out.println(order);
        }

        connection.close();
        ps.close();
        rs.close();

    }


}
