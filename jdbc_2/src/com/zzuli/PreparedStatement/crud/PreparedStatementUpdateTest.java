package com.zzuli.PreparedStatement.crud;

import com.zzuli.util.JDBCUtils;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Properties;

/**
 * 针对customers表的 增删改 练习
 * @auther pony
 * @create 2022-04-11 15:51
 */
public class PreparedStatementUpdateTest {
    /*
    * 通用 Update 方法
    * */
    public void update(String sql, Object ...args){
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            //1、获取连接
            conn = JDBCUtils.getConnection();
            //2、预处理sql
            ps = conn.prepareStatement(sql);
            //3、填充占位符
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i+1, args[i]);
            }
            //4、执行
            ps.execute();
        }catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            //5、关闭资源
            JDBCUtils.closeResource(ps, conn);
        }

    }

    //测试通用的 Update 方法
    @Test
    public void testUpdate(){
        String sql = "UPDATE customers SET email = ? WHERE id = ?";
        update(sql, "hanser@126.com", "21");
    }

    /*
    * 对 customer表 修改一条数据
    **/
    @Test
    public void CustomersForUpdateTest() throws SQLException, IOException, ClassNotFoundException, ParseException {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            //获取连接
            connection = JDBCUtils.getConnection();

            //预处理sql语句
            String sql = "UPDATE customers SET birth = ? WHERE `name` = ?";
            ps = connection.prepareStatement(sql);
            //填充占位符
            //格式化时间
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            //解析
            java.util.Date parse = sdf.parse("1992-05-15");
            ps.setDate(1,new Date(parse.getTime()));
            ps.setString(2,"hanser");

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
            JDBCUtils.closeResource(ps,connection);
        }


    }



    /**
     * 对 customers表 添加一条数据
     */
    @Test
    public void CustomersForInsert(){
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            //一、连接数据库
            //1.四个必要信息
            //2.读取配置文件
            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties");
            Properties properties = new Properties();
            properties.load(is);

            String name = properties.getProperty("name");
            String password = properties.getProperty("password");
            String url = properties.getProperty("url");
            String driverClass = properties.getProperty("driverClass");

            //3.加载驱动
            Class.forName(driverClass);

            //4.获取连接
            connection = DriverManager.getConnection(url, name, password);

            //5.预处理Sql语句
            String sql = "INSERT INTO customers(name, email, birth) VALUES(?, ?, ?)";
            ps = connection.prepareStatement(sql);

            //6.填充占位符
            ps.setString(1,"hanser");
            ps.setString(2,"hanser@qq.com");
            //时间比较特殊要格式化和解析
            //格式化
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            //解析
            java.util.Date parse = sdf.parse("1992-05-15");
            ps.setDate(3,new Date(parse.getTime()));

            //执行sql
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }  finally {
            //释放资源
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
