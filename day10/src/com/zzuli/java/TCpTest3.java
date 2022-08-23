package com.zzuli.java;

import org.junit.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 例题3：客户端发送文件给服务器端，服务器将文件保存到本地，并返回 “发送成功” 给客户端，并关闭相应的连接
 * @auther pony
 * @create 2022-03-20 16:54
 */
public class TCpTest3 {

    @Test
    public void client() {

        Socket socket = null;
        OutputStream os = null;
        FileInputStream fis = null;
        InputStream is = null;
        ByteArrayOutputStream baops = null;
        try {
            InetAddress inet = InetAddress.getByName("127.0.0.1");
            socket = new Socket(inet, 8899);

            os = socket.getOutputStream();

            fis = new FileInputStream(new File("Hiiro.webp"));

            byte[] cbuf = new byte[1024];
            int len;
            while ((len = fis.read(cbuf)) != -1){
                os.write(cbuf, 0, len);
            }

            //关闭数据输出
            socket.shutdownOutput();

            is = socket.getInputStream();
            baops = new ByteArrayOutputStream();
            byte[] bytes = new byte[20];
            int len1;
            while ((len1 = is.read(bytes)) != -1){
                baops.write(bytes,0, len1);
            }

            System.out.println(baops);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fis != null){
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
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
            try {
                if(is != null){
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(baops != null){
                    baops.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void server() {

        ServerSocket ss = null;
        Socket accept = null;
        InputStream is = null;
        FileOutputStream fos = null;
        OutputStream os = null;
        try {
            ss = new ServerSocket(8899);
            accept = ss.accept();

            is = accept.getInputStream();

            fos = new FileOutputStream(new File("Hiiro2.webp"));

            byte[] cbuf = new byte[1024];
            int len;
            while((len = is.read(cbuf)) != -1){
                fos.write(cbuf, 0, len);
            }

            os = accept.getOutputStream();
            os.write("我已经收到照片".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null){
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (is != null){
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (accept != null){
                    accept.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (ss != null){
                    ss.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (os != null){
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
