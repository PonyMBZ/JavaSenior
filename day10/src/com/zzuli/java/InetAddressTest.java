package com.zzuli.java;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @auther pony
 * @create 2022-03-19 20:46
 * Socket:端口号和IP地址的组合得出一个网络套接字
 */
public class InetAddressTest {
    public static void main(String[] args) {
        try {
            InetAddress inet1 = InetAddress.getByName("192.168.10.14");
            System.out.println(inet1);

            InetAddress inet2 = InetAddress.getByName("www.baidu.com");
            System.out.println(inet2);

            InetAddress inet3 = InetAddress.getByName("127.0.0.1");
            System.out.println(inet3);

            InetAddress localHost = InetAddress.getLocalHost();
            System.out.println(localHost);
//          两个常用方法
            System.out.println(inet2.getHostName());

            System.out.println(inet2.getHostAddress());

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

    }
}
