package com.zzuli.connection;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @auther pony
 * @create 2022-04-28 15:04
 */
public class C3P0Test {
    //方式一：硬编码的方式，配置信息写到代码中了，不推荐
    @Test
    public void testGetConnection() throws Exception {
        //获取数据库连接池对象
        ComboPooledDataSource cpds = new ComboPooledDataSource();
        cpds.setDriverClass( "com.mysql.cj.jdbc.Driver" );
        cpds.setJdbcUrl( "jdbc:mysql://localhost:3306/test" );
        cpds.setUser("root");
        cpds.setPassword("123456");

        //通过设置相关的参数对数据库连接池进行管理
        //1、设置初始的数据库连接数
        cpds.setInitialPoolSize(10);

        Connection conn = cpds.getConnection();
        System.out.println(conn);

        //销毁c3p0数据库连接池(一般都不会关的)
        //DataSources.destroy(cpds);
    }

    //方式二：使用配置文件的方式
    @Test
    public void testGetConnections() throws SQLException {
        ComboPooledDataSource cpds = new ComboPooledDataSource("helloc3p0");
        Connection conn = cpds.getConnection();
        System.out.println(conn);
    }
}
