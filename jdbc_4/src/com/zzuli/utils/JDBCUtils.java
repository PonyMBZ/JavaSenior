package com.zzuli.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @auther pony
 * @create 2022-04-28 16:44
 */
public class JDBCUtils {
    /** @Author: pony
     * @Description: 使用 C3P0 的数据库连接技术
     * @Date: 16:46 2022/4/28
     * @param:
     * @return: java.sql.Connection
     * @Version: 1.0
     */
    private static ComboPooledDataSource cpds = new ComboPooledDataSource("helloc3p0");
    public static Connection getConnection() throws SQLException {
//        数据库连接池只提供一个就行，所以这行代码放外边就行了，不然每回调用这个方法都造一个连接池
//        ComboPooledDataSource cpds = new ComboPooledDataSource("helloc3p0");
        Connection conn = cpds.getConnection();
        return conn;
    }

    //创建一个 DBCP 数据库连接池
    //为了生成 唯一 的 DataSource source，所以写外面了
    private static DataSource source;
    //使用静态代码块，随着类的加载就执行一次
    static {
        try {
            Properties properties = new Properties();
            FileInputStream is = new FileInputStream(new File("src/dbcp.properties"));
            properties.load(is);
            DataSource source = BasicDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Connection getConnection1() throws Exception {
        Connection conn = source.getConnection();
        return conn;
    }


    //创建一个 Druid 数据库连接池
    //为了生成 唯一 的 DataSource source，所以写外面了
    private static DataSource source1 = null;
    //使用静态代码块，随着类的加载就执行一次
    static {
        try {
            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("druid.properties");
            Properties properties = new Properties();
            properties.load(is);
            source1 = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection2() throws Exception {
        Connection conn = source1.getConnection();
        return conn;
    }

}


