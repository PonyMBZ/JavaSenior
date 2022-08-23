package com.zzuli.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * JDBC操作工具类
 * @auther pony
 * @create 2022-04-17 20:04
 */
public class JDBCUtils {
    public static Connection getConnection() throws IOException, ClassNotFoundException, SQLException {
        //1、四个基本信息
        //2、读取配置文件
        InputStream is = JDBCUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
        Properties properties = new Properties();
        properties.load(is);
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String url = properties.getProperty("url");
        String driverClass = properties.getProperty("driverClass");

        //3、加载驱动
        Class.forName(driverClass);

        //4、获取连接
        Connection coon = DriverManager.getConnection(url,user,password);
        return coon;
    }

    //关闭资源
    public static void closeResources(Connection conn, PreparedStatement ps){
        try {
            if (conn!= null){
                conn.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            if (ps != null){
                ps.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void closeResources(Connection conn, PreparedStatement ps, ResultSet rs){
        try {
            if (conn != null){
                conn.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            if (ps != null){
                ps.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            if (rs != null){
                rs.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
