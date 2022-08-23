package com.zzuli.preparedstatement.crud;

import com.zzuli.bean.Customers;
import com.zzuli.util.JDBCUtils;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.*;

/**
 * 针对 Customer 表的查询操作
 * @auther pony
 * @create 2022-04-05 17:40
 */
public class CustomerForQuery {
    @Test
    public void testQueryForCustomers(){
        String sql = "select id, name, birth, email from customers where id = ?";
        Customers customers = queryForCustomers(sql, 13);
        System.out.println(customers);
        String sql1 = "select name, email from customers where name = ?";
        Customers customer1 = queryForCustomers(sql1, "成龙");
        System.out.println(customer1);
    }

    /** @Author: pony
     * @Description: 查询通用操作
     * @Date: 20:36 2022/4/5
     * @param: sql : sql语句
     * @param: args : 要查询的字段
     * @return: com.zzuli.bean.Customers
     * @Version: 1.0
     */
    public Customers queryForCustomers(String sql, Object ...args){
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
                Customers customers = new Customers();
                //处理结果集一行数据的每一个列
                for (int i = 0; i < columnCount; i++) {
                    Object columnValue = resultSet.getObject(i + 1);

                    //获取每个列的列名
                    String columnName = rsmd.getColumnName(i + 1);

                    //给 cust 对象指定的某个属性，赋值为 value,通过反射
                    /*
                    * 需要找 Customers 叫 columnName 属性的，把那个属性对应的值赋值为 columnValue
                    * */
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
            JDBCUtils.closeResource(connection, ps, resultSet);
        }
        return null;
    }


    @Test
    public void testQuery1(){
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtils.getConnection();
            String sql = "select id, name, email, birth from customers where id = ?";
            ps = connection.prepareStatement(sql);
            //填充占位符
            ps.setInt(1,1);

            //执行并返回结果集
            resultSet = ps.executeQuery();

            //处理结果集
            //resultSet.next():判断结果集的下一条是否有数据，如果有就返回true，并指针下移，没有返回false.
            //有点类似与集合的迭代器iterator.hasNext()，返回下一个是否有元素，指针下移的事由iterator.next()执行。
            if(resultSet.next()){

                //获取当前这条数据的各个字段值
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String email = resultSet.getString(3);
                Date birth = resultSet.getDate(4);

    //            //方式一：直接输出
    //            System.out.println("id = " + id + ", name = " + name + ", email = " + email + ", birth = " + birth);
    //
    //            //方式二：数组
    //            Object[] data = new Object[]{id, name, email, birth};

                //方式三：将数据封装成一个对象推荐使用
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
            //关闭资源
            JDBCUtils.closeResource(connection, ps, resultSet);
        }

    }
}
