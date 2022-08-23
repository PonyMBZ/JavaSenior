package com.zzuli.dao;

import com.zzuli.util.JDBCUtils;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @auther pony
 * @create 2022-04-25 16:29
 */
public abstract class BaseDao {
    /** @Author: pony
     * @Description: 通用的增删改方法
     * @Date: 16:43 2022/4/25
     * @param: conn 数据库连接
     * @param: sql  sql语句
     * @param: args 要填充展位符的内容
     * @return: int sql语句影响的行数
     * @Version: 1.0
     */
    public int update(Connection conn, String sql, Object ...args) {
        PreparedStatement ps = null;
        try {
            //1、预处理sql语句
            ps = conn.prepareStatement(sql);
            //2、填充占位符
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i+1, args[i]);
            }
            //3、执行sql语句
            return ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            //4、释放资源
            JDBCUtils.closeResources(null, ps);
        }
        return 0;
    }

    /** @Author: pony
     * @Description: 通用的查询方法，返回一条记录
     * @Date: 17:12 2022/4/25
     * @param: clazz 具体表的对象
     * @param: conn 数据库连接
     * @param: sql  sql语句
     * @param: args 要填充展位符的内容
     * @return: T 查询记录
     * @Version: 1.0
     */
    public <T> T getInstance(Class<T> clazz, Connection conn, String sql, Object ...args){
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //1、预处理SQL语句
            ps = conn.prepareStatement(sql);
            //2、填充占位符
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i+1, args[i]);
            }
            //3、执行sql语句，返回结果集
            rs = ps.executeQuery();
            //4、处理结果集
            //4-1、获取结果集的元数据
            ResultSetMetaData rsmd = rs.getMetaData();
            //4-2、获取结果集的列数
            int columnCount = rsmd.getColumnCount();
            //4-3、通过反射创建不同表实例对象
            T t = clazz.newInstance();
            if (rs.next()){
                for (int i = 0; i < columnCount; i++) {
                    //通过结果集获取列值
                    Object value = rs.getObject(i + 1);
                    //通过结果集的元数据获取列名
                    String name = rsmd.getColumnLabel(i + 1);

                    //通过反射将实例对象中叫做 name 的属性赋上值（value）
                    Field field = t.getClass().getDeclaredField(name);
                    field.setAccessible(true);
                    field.set(t, value);
                }
            }
            return t;
        } catch (Exception e) {
            e.printStackTrace();
        }  finally {
            JDBCUtils.closeResources(null, ps, rs);
        }
        return null;
    }

    /** @Author: pony
     * @Description: 通用的查询方法，返回多条记录
     * @Date: 17:31 2022/4/25
     * @param: clazz
     * @param: conn
     * @param: sql
     * @param: args
     * @return: java.util.List<T>
     * @Version: 1.0
     */
    public <T> List<T> getForList(Class<T> clazz, Connection conn, String sql, Object ...args){
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //1、预处理SQL语句
            ps = conn.prepareStatement(sql);
            //2、填充占位符
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i+1, args[i]);
            }
            //3、执行sql语句，返回结果集
            rs = ps.executeQuery();
            //4、处理结果集
            //4-1、获取结果集的元数据
            ResultSetMetaData rsmd = rs.getMetaData();
            //4-2、获取结果集的列数
            int columnCount = rsmd.getColumnCount();

            ArrayList<T> list = new ArrayList<>();
            while (rs.next()){
                //4-3、通过反射创建不同表实例对象
                T t = clazz.newInstance();
                for (int i = 0; i < columnCount; i++) {
                    //通过结果集获取列值
                    Object value = rs.getObject(i + 1);
                    //通过结果集的元数据获取列名
                    String name = rsmd.getColumnLabel(i + 1);

                    //通过反射将实例对象中叫做 name 的属性赋上值（value）
                    Field field = t.getClass().getDeclaredField(name);
                    field.setAccessible(true);
                    field.set(t, value);
                }
                list.add(t);
            }
            return list;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResources(null, ps, rs);
        }
        return null;
    }

    /** @Author: pony
     * @Description: 针对于组函数的查询
     * @Date: 17:32 2022/4/25
     * @param: conn
     * @param: sql
     * @param: args
     * @return: T
     * @Version: 1.0
     */
    public <T> T getValue(Connection conn, String sql, Object ...args){
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            rs = ps.executeQuery();
            if (rs.next()){
                return (T) rs.getObject(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtils.closeResources(null, ps, rs);
        }
        return null;
    }
}
