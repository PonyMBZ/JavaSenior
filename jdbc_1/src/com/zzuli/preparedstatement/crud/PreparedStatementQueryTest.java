package com.zzuli.preparedstatement.crud;

import com.zzuli.bean.Customers;
import com.zzuli.bean.Order;
import com.zzuli.util.JDBCUtils;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @auther pony
 * @create 2022-04-14 16:13
 */
public class PreparedStatementQueryTest {
    @Test
    public void testGetForList(){
        String sql = "select id, name, email, birth from customers where id < ?";
        List<Customers> list = getForList(Customers.class, sql, 12);
        //方法引用
        list.forEach(System.out::println);
    }
    public <T>List<T> getForList(Class<T> clazz, String sql, Object ...args){

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            //1.获取连接
            connection = JDBCUtils.getConnection();

            //2.sql语句的预编译
            ps = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i+1, args[i]);
            }

            resultSet = ps.executeQuery();
            /*获取结果集的元数据
             * (元数据：就是用来修饰这条数据的数据，比如String name = "pony";
             * String 和 name 都是用来修饰 “pony” 的)
             * 这里是为了从元数据中获取数据的列数
             * */
            ResultSetMetaData rsmd = resultSet.getMetaData();
            int columnCount = rsmd.getColumnCount();
            //创建集合对象
            ArrayList<T> list = new ArrayList<>();
            while (resultSet.next()){
                T t = clazz.newInstance();
                //处理结果集一行数据的每一个列,给t对象指定的属性赋值的一个过程
                for (int i = 0; i < columnCount; i++) {
                    Object columnValue = resultSet.getObject(i + 1);

                    //获取每个列的列名
                    String columnName = rsmd.getColumnLabel(i + 1);

                    //给 cust 对象指定的某个属性，赋值为 value,通过反射
                    /*
                     * 需要找 Customers 叫 columnName 属性的，把那个属性对应的值赋值为 columnValue
                     * */
                    Field field = clazz.getDeclaredField(columnName);
                    field.setAccessible(true);
                    field.set(t, columnValue);

                }
                list.add(t);
            }

            return list;
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
        } catch (InstantiationException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection, ps, resultSet);
        }
        return null;
    }
    @Test
    public void testGetInstance(){
        String sql = "select id, name, email, birth from customers where name = ?";
        Customers hanser = getInstance(Customers.class, sql, "hanser");
        System.out.println(hanser);
        String sql2 = "select order_id orderId, order_name orderName from `order` where order_id = ?";
        Order order = getInstance(Order.class, sql2, 1);
        System.out.println(order);
    }
/*
* 针对于不同表的通用查询操作，返回一条记录
* */
    public <T> T getInstance(Class<T> clazz, String sql, Object ...args){
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            //1.获取连接
            connection = JDBCUtils.getConnection();

            //2.sql语句的预编译
            ps = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i+1, args[i]);
            }

            resultSet = ps.executeQuery();
            /*获取结果集的元数据
             * (元数据：就是用来修饰这条数据的数据，比如String name = "pony";
             * String 和 name 都是用来修饰 “pony” 的)
             * 这里是为了从元数据中获取数据的列数
             * */
            ResultSetMetaData rsmd = resultSet.getMetaData();
            int columnCount = rsmd.getColumnCount();
            if (resultSet.next()){
                T t = clazz.newInstance();
                //处理结果集一行数据的每一个列
                for (int i = 0; i < columnCount; i++) {
                    Object columnValue = resultSet.getObject(i + 1);

                    //获取每个列的列名
                    String columnName = rsmd.getColumnLabel(i + 1);

                    //给 cust 对象指定的某个属性，赋值为 value,通过反射
                    /*
                     * 需要找 Customers 叫 columnName 属性的，把那个属性对应的值赋值为 columnValue
                     * */
                    Field field = clazz.getDeclaredField(columnName);
                    field.setAccessible(true);
                    field.set(t, columnValue);

                }
                return t;
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
        } catch (InstantiationException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection, ps, resultSet);
        }
        return null;
    }
}
