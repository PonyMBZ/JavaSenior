package com.zzuli.connection;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @auther pony
 * @create 2022-04-28 18:48
 */
public class DBCPTest {
    @Test
    public void testGetConnection() throws SQLException {
        //创建了 dbcp 的数据库连接池
        BasicDataSource source = new BasicDataSource();

        //设置基本信息
        source.setDriverClassName("com.mysql.cj.jdbc.Driver");
        source.setUrl("jdbc:mysql://localhost:3306/test");
        source.setUsername("root");
        source.setPassword("123456");

        //设置数据库连接池管理的相关属性
        //初始化最大是10个
        source.setInitialSize(10);
        //最大活跃数
        source.setMaxActive(10);
        //........

        //获取连接
        Connection conn = source.getConnection();
        System.out.println(conn);
    }

    @Test
    public void testGetConnection1() throws Exception {

        Properties properties = new Properties();
        //方式一：
        //InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("dbcp.properties");
        //方式二：
        FileInputStream is = new FileInputStream(new File("src/dbcp.properties"));
        properties.load(is);
        DataSource source = BasicDataSourceFactory.createDataSource(properties);

        Connection conn = source.getConnection();
        System.out.println(conn);
    }
}
