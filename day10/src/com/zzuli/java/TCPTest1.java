package com.zzuli.java;

import org.junit.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 例题2：客户端发送文件给服务器端，服务器将文件保存到本地
 * @auther pony
 * @create 2022-03-20 16:24
 */
public class TCPTest1 {

    @Test
    public void client() {

        Socket socket = null;
        OutputStream os = null;
        FileInputStream fis = null;
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
        }
    }

    @Test
    public void server() {

        ServerSocket ss = null;
        Socket accept = null;
        InputStream is = null;
        FileOutputStream fos = null;
        try {
            ss = new ServerSocket(8899);
            accept = ss.accept();

            is = accept.getInputStream();

            fos = new FileOutputStream(new File("Hiiro1.webp"));

            byte[] cbuf = new byte[1024];
            int len;
            while((len = is.read(cbuf)) != -1){
                fos.write(cbuf, 0, len);
            }
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
        }



    }
}
