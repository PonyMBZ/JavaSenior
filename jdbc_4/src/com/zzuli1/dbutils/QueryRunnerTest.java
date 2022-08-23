package com.zzuli1.dbutils;

import bean.Customers;
import com.zzuli.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.*;
import org.junit.Test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * commons-dbutils 是 Apache 组织提供的一个开源的 JDBC 工具类库,封装了对于数据库的增删改查操作
 *
 * @auther pony
 * @create 2022-04-28 20:17
 */
public class QueryRunnerTest {
    @Test
    public void testInsert() {
        Connection conn = null;
        try {
            QueryRunner runner = new QueryRunner();
            conn = JDBCUtils.getConnection2();
            String sql = "insert into customers(name, email, birth) value(?,?,?)";
            runner.update(conn, sql, "蔡徐坤", "caixukuen@126.com","1998-09-08");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn!=null){
                    conn.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    //测试查询
    /*
    * BeanHandler:是ResultSetHandler接口的实现类，用于封装表中的一条记录
    * */
    @Test
    public void testQuery1(){
        Connection conn = null;
        try {
            QueryRunner queryRunner = new QueryRunner();
            conn = JDBCUtils.getConnection2();
            String sql = "select id, name, birth from customers where id =?";
            BeanHandler<Customers> handler = new BeanHandler<>(Customers.class);
            Customers customers = queryRunner.query(conn, sql, handler, 26);
            System.out.println(customers);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn!=null){
                    conn.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    /*
    * BeanListHandler
    * */
    @Test
    public void testQuery2(){
        Connection conn = null;
        try {
            QueryRunner queryRunner = new QueryRunner();
            conn = JDBCUtils.getConnection2();
            String sql = "select id, name, birth from customers";

            BeanListHandler<Customers> handler = new BeanListHandler<>(Customers.class);

            List<Customers> customers = queryRunner.query(conn, sql, handler);
            customers.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn!=null){
                    conn.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    @Test
    public void testQuery3(){
        Connection conn = null;
        try {
            QueryRunner queryRunner = new QueryRunner();
            conn = JDBCUtils.getConnection2();
            String sql = "select id, name, birth from customers where id =?";
            MapHandler handler = new MapHandler();
            Map<String, Object> map = queryRunner.query(conn, sql, handler, 26);
            System.out.println(map);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn!=null){
                    conn.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    @Test
    public void testQuery4(){
        Connection conn = null;
        try {
            QueryRunner queryRunner = new QueryRunner();
            conn = JDBCUtils.getConnection2();
            String sql = "select id, name, birth from customers";

            MapListHandler mapListHandler = new MapListHandler();

            List<Map<String, Object>> mapList = queryRunner.query(conn, sql, mapListHandler);
            mapList.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn!=null){
                    conn.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }


    @Test
    public void testQuery5(){
        Connection conn = null;
        try {
            QueryRunner queryRunner = new QueryRunner();
            conn = JDBCUtils.getConnection2();
            String sql = "select count(*) from customers";
            ScalarHandler handler = new ScalarHandler();
            Long count = (Long)queryRunner.query(conn, sql, handler);
            System.out.println(count);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn!=null){
                    conn.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    @Test
    public void testQuery6(){
        Connection conn = null;
        try {
            QueryRunner queryRunner = new QueryRunner();
            conn = JDBCUtils.getConnection2();
            String sql = "select id, name, email, birth from customers where id =?";
            ResultSetHandler<Customers> handler = new ResultSetHandler<Customers>() {
                @Override
                public Customers handle(ResultSet rs) throws SQLException {
                    if (rs.next()){
                        int id = rs.getInt("id");
                        String name = rs.getString("name");
                        String email = rs.getString("email");
                        Date birth = rs.getDate("birth");
                        Customers customers = new Customers(id, name, email, birth);
                        return customers;
                    }
                    return null;
                }
            };

            //使用 lambda 表达式
            ResultSetHandler<Customers> handler1 = rs ->{
                if (rs.next()){
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String email = rs.getString("email");
                    Date birth = rs.getDate("birth");
                    Customers customers = new Customers(id, name, email, birth);
                    return customers;
                }
                return null;
            };

            Customers customers = queryRunner.query(conn, sql, handler1, 26);
            System.out.println(customers);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn!=null){
                    conn.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
