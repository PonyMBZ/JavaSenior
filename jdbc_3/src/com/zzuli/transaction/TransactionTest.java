package com.zzuli.transaction;

import com.zzuli.util.JDBCUtils;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.*;

/**
 * 1、什么是数据库事务
 * 事务：一组逻辑操作单元，使数据从一种状态变成另一种状态
 *
 * 2、事务处理的原则
 *
 * 3、数据一旦提交就不可回滚
 *
 * 4、哪些操作会导致数据的自动提交
 *         (1) DDL 操作一旦执行，就会导致自动提交
 *                 >setAutoCommit = false; 对DDL的操作无效
 *         (2) DML 操作 默认情况下，一旦执行，也会自动提交
 *                 >可以通过setAutoCommit = false; 的方式取消DML的自动提交
 *         (3) 在关闭连接时，没提交的数据也会自动提交
 *  5、事务的 ACID 属性
 *  >原子性
 *  >一致性
 *  >隔离性
 *  >持久性
 * @auther pony
 * @create 2022-04-17 20:01
 */
public class TransactionTest {

    //数据库连接测试
    @Test
    public void test() throws SQLException, IOException, ClassNotFoundException {
        Connection coon = JDBCUtils.getConnection();
        System.out.println(coon);
    }

    //***************************************考虑数据库事务后的转账操作***************************************

    /*
     * 对于数据表user_table来说，
     * AA 用户给 BB 用户转账了100
     *
     * update user_table set balance = balance - 100 where user = "AA";
     * update user_table set balance = balance + 100 where user = "BB";
     * */
    @Test
    public void testUpdate(){
        String sql1 = "update user_table set balance = balance - 100 where user = ?";

        update(sql1 , "AA");

        //模拟网络异常
        System.out.println(10 / 0);

        String sql2 = "update user_table set balance = balance + 100 where user = ?";
        update(sql2 , "BB");

        System.out.println("转账成功!");
    }

    //通用的增删改操作 --- version 1.0
    public int update(String sql, Object ...args){
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            //1、获取数据库连接
            conn = JDBCUtils.getConnection();
            //2、预处理sql
            ps = conn.prepareStatement(sql);
            //3、填充占位符
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i+1, args[i]);
            }
            //4、执行sql
            return ps.executeUpdate();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            //5、释放资源
            JDBCUtils.closeResources(conn, ps);
        }
        return 0;
    }

    //***************************************考虑数据库事务后的转账操作***************************************

    @Test
    public void testUpdateTX(){
        Connection coon = null;
        try {
            coon = JDBCUtils.getConnection();

            //设置取消数据的自动提交
            coon.setAutoCommit(false);
            String sql1 = "update user_table set balance = balance - 100 where user = ?";

            update(coon, sql1 , "AA");

            //模拟网络异常
            System.out.println(10 / 0);

            String sql2 = "update user_table set balance = balance + 100 where user = ?";
            update(coon, sql2 , "BB");

            System.out.println("转账成功!");

            //提交数据
            coon.commit();
        } catch (Exception e) {
            e.printStackTrace();
            //如果有异常回滚数据
            try {
                coon.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } finally {

            //修改为自动提交数据
            //主要针对于使用数据库连接池的使用。
            try {
                coon.setAutoCommit(true);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            JDBCUtils.closeResources(coon, null);
        }

    }
    //通用的增删改操作 --- version 2.0
    public int update(Connection conn, String sql, Object ...args){
        PreparedStatement ps = null;
        try {
            //2、预处理sql
            ps = conn.prepareStatement(sql);
            //3、填充占位符
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i+1, args[i]);
            }
            //4、执行sql
            return ps.executeUpdate();
        }  catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            //5、释放资源(连接先不要关，操作完后在统一关闭)
            JDBCUtils.closeResources(null, ps);
        }
        return 0;
    }

    //********************************************************
    //设置隔离级别

    @Test
    public void testTransactionSelect() throws SQLException, IOException, ClassNotFoundException {
        Connection coon = JDBCUtils.getConnection();
        //获取当前连接的隔离级别
        System.out.println(coon.getTransactionIsolation());
        //设置数据库的隔离级别
        coon.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        //取消自动提交数据
        coon.setAutoCommit(false);
        String sql = "select user, password, balance from user_table where user = ?";
        User user = getInstance(coon, User.class, sql, "CC");

        System.out.println(user);

    }

    @Test
    public void testTransactionUpdate() throws SQLException, IOException, ClassNotFoundException, InterruptedException {
        Connection coon = JDBCUtils.getConnection();

        //取消自动提交数据
        coon.setAutoCommit(false);
        String sql = "update user_table set balance = ? where user = ?";
        update(coon, sql, 4000, "CC");

        Thread.sleep(15000);
        System.out.println("修改结束！");
    }

    //通用的查询操作,用于返回数据表的一条记录（version 2.0）,考虑到事务了
    public <T> T getInstance(Connection conn, Class<T> clazz, String sql, Object ...args){
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //2、预处理sql语句
            ps = conn.prepareStatement(sql);
            //3、填充占位符
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i+1, args[i]);
            }

            //4、执行sql并返回结果集
            rs = ps.executeQuery();

            //5、处理结果集
            //5-1、获取元数据
            ResultSetMetaData metaData = rs.getMetaData();
            //5-2、通过元数据获取有几列
            int columnCount = metaData.getColumnCount();
            if (rs.next()){
                T t = clazz.newInstance();
                for (int i = 0; i < columnCount; i++) {
                    //获取列的别名
                    String columnName = metaData.getColumnLabel(i + 1);
                    //获取列值
                    Object columnValue = rs.getObject(i + 1);

                    //通过反射将实例中叫columnName那个属性，赋上columnValue值
                    Field field = clazz.getDeclaredField(columnName);
                    field.setAccessible(true);
                    field.set(t, columnValue);
                }
                return t;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //6、释放资源
            JDBCUtils.closeResources(null, ps, rs);
        }
        return null;
    }
}
