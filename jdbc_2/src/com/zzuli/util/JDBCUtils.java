package com.zzuli.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @auther pony
 * @create 2022-04-11 16:44
 */
public class JDBCUtils {
    /** @Author: pony
     * @Description: 获取连接
     * @Date: 16:47 2022/4/11
     * @param:
     * @return: java.sql.Connection
     * @Version: 1.0
     */
    public static Connection getConnection() throws IOException, ClassNotFoundException, SQLException {
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
        Connection connection = DriverManager.getConnection(url, name, password);

        return connection;
    }

    /** @Author: pony
     * @Description:释放资源
     * @Date: 16:50 2022/4/11
     * @param: ps
     * @param: connection
     * @return: void
     * @Version: 1.0
     */
    public static void closeResource(PreparedStatement ps, Connection connection){
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

    public static void closeResource(PreparedStatement ps, Connection connection, ResultSet rs){
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

        try {
            if (rs != null){
                rs.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
