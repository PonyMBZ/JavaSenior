package com.zzuli.connection;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 练习：数据库的连接
 * @auther pony
 * @create 2022-04-11 15:11
 */
public class ConnectionTest {
    @Test
    public void ContestionTest() throws IOException, ClassNotFoundException, SQLException {
        //导入Mysql数据库连接jar包
        //1.四个必要的连接信息，写到了配置文件（jdbc.properties）里
        //2.读取配置文件，获取连接信息
        InputStream is = ConnectionTest.class.getClassLoader().getResourceAsStream("jdbc.properties");
        Properties properties = new Properties();
        properties.load(is);

        String url = properties.getProperty("url");
        String driverClass = properties.getProperty("driverClass");
        String name = properties.getProperty("name");
        String password = properties.getProperty("password");

        //3.加载驱动（①创建驱动实例对象，②注册驱动）
        Class.forName(driverClass);

        //4.获取连接
        Connection conn = DriverManager.getConnection(url, name, password);
        System.out.println(conn);
    }
}
