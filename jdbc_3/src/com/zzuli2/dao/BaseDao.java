package com.zzuli2.dao;

import com.zzuli.util.JDBCUtils;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO: data(base) access object 数据库访问对象
 * 封装了针对数据表的通用方法（version 2.0考虑了事务）
 * @auther pony
 * @create 2022-04-18 17:28
 */
public abstract class BaseDao {
    //1、通用的增删改方法
    public int update(Connection conn, String sql, Object ...args){
        PreparedStatement ps = null;
        try {
            //1、预处理sql语句
            ps = conn.prepareStatement(sql);
            //2、填充占位符
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i+1, args[i]);
            }
            //3、执行sql
            return ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            //4、释放资源
            JDBCUtils.closeResources(null, ps);
        }
        return 0;

    }

    //2、通用的查询方法,返回一条记录
    public <T> T getInstance(Connection conn, Class<T> clazz, String sql, Object ...args){
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //1、预处理sql
            ps = conn.prepareStatement(sql);
            //2、填充占位符
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i+1, args[i]);
            }
            //3、执行sql语句，返回结果集
            rs = ps.executeQuery();
            //4、处理结果集
            //4-1、获取元数据
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            T t = clazz.newInstance();
            if (rs.next()){
                for (int i = 0; i < columnCount; i++) {
                    //获取列值
                    Object columnValue = rs.getObject(i + 1);
                    //获取列别名
                    String columnName = rsmd.getColumnLabel(i+1);

                    //反射赋值
                    Field field = clazz.getDeclaredField(columnName);
                    field.setAccessible(true);
                    field.set(t, columnValue);
                }
            }
            return t;
        } catch (Exception e) {
            e.printStackTrace();
        }  finally {
            //5、释放资源
            JDBCUtils.closeResources(null, ps, rs);
        }
        return null;
    }

    //3、通用的查询方法,返回多条记录。
    public <T> List<T> getForList(Connection conn, Class<T> clazz, String sql, Object ...args){
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //1、预处理sql
            ps = conn.prepareStatement(sql);
            //2、填写占位符
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i+1, args[i]);
            }
            //3、执行并返回结果集
            rs = ps.executeQuery();
            //4、处理结果集
            //4-1、获取结果集的元数据
            ResultSetMetaData rsmd = rs.getMetaData();
            //4-2、通过元数据获取列数
            int columnCount = rsmd.getColumnCount();
            //将结果存到集合里 new一个集合
            ArrayList<T> list = new ArrayList<T>();

            while (rs.next()){
                //获取一个T的实例
                T t = clazz.newInstance();
                for (int i = 0; i < columnCount; i++) {
                    //4-3、获取列的别名
                    String columnName = rsmd.getColumnLabel(i + 1);
                    //4-4、获取列的值
                    Object columnValue = rs.getObject(i + 1);

                    //4-5、利用反射实例对象叫做 columnName 列的属性的赋值成 columnValue
                    Field field = clazz.getDeclaredField(columnName);
                    field.setAccessible(true);//获取私有属性的权限
                    field.set(t, columnValue);
                }
                list.add(t);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //5、释放资源
            JDBCUtils.closeResources(null, ps, rs);
        }
        return null;
    }

    //4、针对于组函数的查询
    public static <E> E getValue(Connection conn, String sql, Object ...args){
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i+1, args[i]);
            }
            rs = ps.executeQuery();

            if (rs.next()){
                return (E) rs.getObject(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResources(null, ps, rs);
        }
        return null;
    }

}
