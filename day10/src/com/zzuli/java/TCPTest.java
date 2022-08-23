package com.zzuli.java;

import org.junit.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 实现 TCP 的网络编程
 * 例一：客户端发送信息给服务器端，服务器端将信息打印到控制台
 * @auther pony
 * @create 2022-03-20 14:00
 */
public class TCPTest {

    //客户端
    @Test
    public void client(){

        Socket socket = null;
        OutputStream os = null;
        try {
//          1、创建一个 Socket 对象，指明服务器端的 IP 和 端口号
            InetAddress inet = InetAddress.getByName("10.62.87.143");
            socket = new Socket(inet, 8899);

//          2、获取一个输出流，用于输出数据
            os = socket.getOutputStream();
//          3、写出数据的操作
            os.write("你好，我是小马,收到请回答！".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
//          4、资源的关闭
            try {
                if (os != null){
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (socket != null){
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //服务器
    @Test
    public void server() {
        ServerSocket ss = null;
        Socket socket = null;
        InputStream is = null;
        InputStreamReader isr = null;
        try {
//          1、创建服务器端的ServerSocket，指明自己的端口号
            ss = new ServerSocket(8899);

//          2、调用accept(),表示接收来自于客户端的socket
            socket = ss.accept();

//          3、获取输入流
            is = socket.getInputStream();
//        会乱码
//        byte[] cbuf = new byte[20];
//        int len;
//        while ((len = is.read(cbuf)) != -1){
//            String str = new String(cbuf, 0, len);
//            System.out.println(str);
//        }

            //方式一：利用转化流解决乱码问题
//          4、读取输入流当中的数据
            isr = new InputStreamReader(is,"UTF-8");
            char[] chars = new char[5];
            int len;
            while ((len = isr.read(chars)) != -1){
                String str = new String(chars, 0, len);
                System.out.print(str);
            }

            //方式一：
            //ByteArrayOutputStream 先读，byte[5] 一次读 5 个，自动扩容，读完后再统一写
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        byte[] buffer = new byte[5];
//        int len;
//        while ((len = is.read(buffer)) != -1){
//            baos.write(buffer, 0, len);
//        }
//
//        System.out.println(baos.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
//          5、资源关闭
            try {
                if (isr != null){
                    isr.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(is != null){
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(socket != null){
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(ss != null){
                    ss.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
