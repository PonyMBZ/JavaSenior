package com.zzuli.java;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @auther pony
 * @create 2022-03-21 15:18
 */
public class URLTest {
    public static void main(String[] args){
        HttpURLConnection urlConnection = null;
        InputStream is = null;
        FileOutputStream fos = null;
        try {
            URL url = new URL("http://localhost:8080/examples/Hiiro.webp");

            urlConnection = (HttpURLConnection) url.openConnection();

            //获取连接
            urlConnection.connect();

            is = urlConnection.getInputStream();
            fos = new FileOutputStream("day10\\Hiiro3.webp");

            byte[] bytes = new byte[5];
            int len;
            while ((len = is.read(bytes)) != -1){
                fos.write(bytes, 0, len);
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
            if (urlConnection != null){
                urlConnection.disconnect();
            }
        }
    }
}
