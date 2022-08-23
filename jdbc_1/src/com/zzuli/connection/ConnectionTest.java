package com.zzuli.connection;

import org.junit.Test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionTest {

    @Test
    public void testConnection() throws SQLException {

        Driver driver = new com.mysql.cj.jdbc.Driver();

        String url = "jdbc:mysql://localhost:3306/test";
                    //"主协议:子协议://IP地址:端口号/数据库"

        //将用户名和密码封装到Properties中
        Properties info = new Properties();
        info.setProperty("user", "root");
        info.setProperty("password", "123456");

        Connection connect = driver.connect(url, info);

        System.out.println(connect);
    }

    //方式二：对方式一的迭代：为了程序不出现第三方的 Api,使得程序具有更好的移植性
    @Test
    public void test() throws Exception {
        //使用反射
        //1.获取Driver的实现类对象
        Class clazz = Class.forName("com.mysql.cj.jdbc.Driver");
        Driver driver = (Driver) clazz.newInstance();
        //2.提供要连接的数据库
        String url = "jdbc:mysql://localhost:3306/test";
        //3.提供连接需要的用户名和密码
        Properties info = new Properties();
        info.setProperty("user", "root");
        info.setProperty("password", "123456");

        //4.获取连接
        Connection connect = driver.connect(url, info);
        System.out.println(connect);
    }

    //
    @Test
    public void test3() throws Exception {
//        1.提供另外三个连接的基本信息
        String url = "jdbc:mysql://localhost:3306/test";
        String user = "root";
        String password = "123456";

//        2.加载Driver(在mysql中加载Driver也可以省略, 因为在数据库连接驱动中META-INF的services中就有路径,但是在oracle数据库就不行,所以还是不要省略)
        Class.forName("com.mysql.cj.jdbc.Driver");
//        Driver driver = (Driver) clazz.newInstance();
//        //注册驱动
//        DriverManager.deregisterDriver(driver);
//        以上的代码都可以省略
//        因为在mysql的实现类(com.mysql.cj.jdbc.Driver)中,声明了相关的静态方法随着实现类的加载而运行
        //获取连接
        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println(connection);
    }

    //方式五:将数据库连接需要的4个基本信息声明在配置文件中
    @Test
    public void getConnections() throws Exception {
//        1.读取配置文件的加载器(这种默认识别的是src下的配置文件,还有一种方式是默认识别工程下的配置文件)
        InputStream is = ConnectionTest.class.getClassLoader().getResourceAsStream("jdbc.properties");
        Properties properties = new Properties();
        properties.load(is);

        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String url = properties.getProperty("url");
        String driverClass = properties.getProperty("driverClass");

//      2.加载驱动
        Class.forName(driverClass);

//      3.获取连接
        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println(connection);
    }
}
