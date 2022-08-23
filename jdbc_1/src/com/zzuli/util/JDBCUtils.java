package com.zzuli.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;


/**
 * 操作数据库的工具类
 * @auther pony
 * @create 2022-04-04 17:40
 */

public class JDBCUtils {

    /** @Author: pony
     * @Description:
     * @Date: 15:09 2022/4/5
     * @param: 返回一个连接
     * @return: java.sql.Connection
     * @Version: 1.0
     */
    public static Connection getConnection() throws IOException, ClassNotFoundException, SQLException {
        //1.四个基本信息，写到了jdbc.properties
        //2.获取四个基本信息
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties");
        Properties properties = new Properties();
        properties.load(is);

        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String url = properties.getProperty("url");
        String driverClass = properties.getProperty("driverClass");

        //3.加载驱动
        Class.forName(driverClass);

        //4.获取数据库连接
        Connection connection = DriverManager.getConnection(url, user, password);
        return connection;
    }

    /**
     * @Description: 关闭资源
     * @Author: pony
     * @Date: 2022/4/4 20:53
     * @param connection:
     * @param ps:
     * @return: void
     */
    public static void closeResource(Connection connection, Statement ps){
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

    /* * @Author: pony
     * @Description: 关闭资源的操作
     * @Date: 19:45 2022/4/5
     * @param: connection
     * @param: ps
     * @param: resultSet
     * @return: void
     * @Version: 1.0
     */
    public static void closeResource(Connection connection, Statement ps, ResultSet resultSet){
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
            if (resultSet != null){
                resultSet.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
