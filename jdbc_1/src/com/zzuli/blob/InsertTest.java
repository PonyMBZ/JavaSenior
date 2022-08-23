package com.zzuli.blob;

import com.zzuli.util.JDBCUtils;
import org.junit.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 使用 PreparedStatement 的批量操作
 * 题目：向 goods 表中插入 20000 条数据
 *
 * CREATE TABLE goods(
 * id INT PRIMARY KEY AUTO_INCREMENT,
 * NAME VARCHAR(25)
 * );
 * 方式一：使用 Statement
 * 1、获取连接                       Connection conn = JDBCUtils.getConnection();
 * 2、获取一个 Statement 实例        Statement st = coon.createStatement();
 * for(int i = 1; i <= 20000; i++){
 *     String sql = "insert into goods(name) values('name_ " + i + "' )";
 *     st.execute(sql);
 * }
 * 方式二：
 * @auther pony
 * @create 2022-04-17 16:16
 */
public class InsertTest {

    //方式二：使用 prepareStatement 替换Statement
    @Test
    public void testInsert1(){
        Connection coon = null;
        PreparedStatement ps = null;
        try {
            long start = System.currentTimeMillis();
            coon = JDBCUtils.getConnection();
            String sql = "insert into goods(name) values(?)";
            ps = coon.prepareStatement(sql);

            for (int i = 0; i < 20000; i++) {
                ps.setObject(1, "name_" + i);
                ps.execute();
            }
            long end = System.currentTimeMillis();

            System.out.println("花费的时间为：" + (end - start));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtils.closeResource(coon,ps);
        }
    }

    /*
    * 批量插入的方式三：
    * 1、addBatch()、executeBatch()、clearBatch()
    * 2、mysql服务器默认是不支持批处理的，我们需要通过一个参数，让 mysql 开启批处理的支持
    *    ?rewriteBatchedStatements=true 写在配置文件url的后面
    * 3、低版本的数据库连接 jar包不支持
    * */
    @Test
    public void testInsert2(){
        Connection coon = null;
        PreparedStatement ps = null;
        try {
            long start = System.currentTimeMillis();
            coon = JDBCUtils.getConnection();
            String sql = "insert into goods(name) values(?)";
            ps = coon.prepareStatement(sql);

            for (int i = 1; i <= 1000000; i++) {
                ps.setObject(1, "name_" + i);

                //1、"攒"sql
                ps.addBatch();

                if (i % 500 == 0){
                    //2、执行batch
                    ps.executeBatch();

                    //3、清空batch
                    ps.clearBatch();
                }
            }
            long end = System.currentTimeMillis();

            System.out.println("花费的时间为：" + (end - start));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtils.closeResource(coon,ps);
        }
    }

    //批量插入的方式四：设置不允许提交数据
    @Test
    public void testInsert3(){
        Connection coon = null;
        PreparedStatement ps = null;
        try {
            long start = System.currentTimeMillis();
            coon = JDBCUtils.getConnection();
            //设置不允许自动提交数据
            coon.setAutoCommit(false);
            String sql = "insert into goods(name) values(?)";
            ps = coon.prepareStatement(sql);

            for (int i = 1; i <= 1000000; i++) {
                ps.setObject(1, "name_" + i);

                //1、"攒"sql
                ps.addBatch();

                //每五百次填一次sql
                if (i % 500 == 0){
                    //2、执行batch
                    ps.executeBatch();

                    //3、清空batch
                    ps.clearBatch();
                }
            }
            //全部执行完后，统一提交数据
            coon.commit();
            long end = System.currentTimeMillis();

            System.out.println("花费的时间为：" + (end - start));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtils.closeResource(coon,ps);
        }
    }
}
