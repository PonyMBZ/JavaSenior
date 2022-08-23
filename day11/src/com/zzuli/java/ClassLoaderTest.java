package com.zzuli.java;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 了解类的加载器
 * @auther pony
 * @create 2022-03-21 20:35
 */
public class ClassLoaderTest {
    @Test
    public void test1(){
        //对于自定义类，使用系统类加载器进行加载
        ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();

        //getParent():上一层， 扩展类加载器
        ClassLoader parent1 = classLoader.getParent();

        //无法获取引导类加载器, 主要加载JAVA 的核心类库无法加载自定义类
        ClassLoader parent2 = parent1.getParent();
        System.out.println(parent2);
    }
    
    /*
    * Propertises : 读取配置文件信息
    * */
    
    @Test
    public void test2() throws IOException {
        Properties pros = new Properties();
        //读取配置文件方式一:配置文件默认为当前 module
//        FileInputStream fis = new FileInputStream("jdbc.properties");
//        pros.load(fis);

        //读取配置文件方式二:配置文件默认为当前 module 下的 src 下
        ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();
//        getResourceAsStream():以流的方式获取一个资源，得到一个输入流
        InputStream is = classLoader.getResourceAsStream("jdbc1.properties");
        pros.load(is);

        String name = pros.getProperty("user");
        String password = pros.getProperty("password");

        System.out.println("name =" + name + ",\tpassword =" + password);
    }
    
}
