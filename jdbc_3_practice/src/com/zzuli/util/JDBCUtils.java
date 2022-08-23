package com.zzuli.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @auther pony
 * @create 2022-04-25 15:56
 */
public class JDBCUtils {
    public static Connection getConnection() throws IOException, ClassNotFoundException, SQLException {
        //1、导入数据库连接驱动，放到模块下的lib文件下
        //2、提供连接数据库的一些基本数据，写到了 src 下的配置文件(jdbc.properties)下
        //3、读取配置文件的信息
        InputStream ras = JDBCUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
        Properties properties = new Properties();
        properties.load(ras);

        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String url = properties.getProperty("url");
        String driverClass = properties.getProperty("driverClass");

        /*4、加载数据库驱动
         *（分为两步 1、获取数据库驱动的实例对象 2、注册驱动）
         * 注册驱动：写在在 Driver 里的一个静态方法里，所以就不需要自己写了
         * */
        Class.forName(driverClass);

        //5、获取数据库连接
        Connection conn = DriverManager.getConnection(url,user,password);
        return conn;
    }

    //资源回收
    public static void closeResources(Connection conn, PreparedStatement ps){
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
