package com.zzuli.java;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * 
 * @auther pony
 * @create 2022-03-12 9:46
 */
public class PropertiseTest {
    public static void main(String[] args){
        FileInputStream fis = null;
        try {
            Properties properties = new Properties();
            fis = new FileInputStream("jdbc.properties");
            properties.load(fis);//加载流对应的文件

            String name = properties.getProperty("name");
            String pwd = properties.getProperty("password");
            System.out.println("name = " + name + "\t, password = " + pwd);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (fis != null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }
}
