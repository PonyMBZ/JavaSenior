package com.zzuli.java;

import org.junit.Test;

import java.io.*;

/**
 * 处理流之一： 缓冲流的使用
 * @auther pony
 * @create 2022-03-17 20:03
 */
public class BufferedTest {

    /*
    * 实现非文本文件的复制
    * */
    @Test
    public void test() throws IOException {
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        BufferedInputStream bufferedInputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        try {
            File srcFile = new File("薇尔莉特.伊芙加登.jpg");
            File destFile = new File("薇尔莉特.伊芙加登1.jpg");

            fileInputStream = new FileInputStream(srcFile);
            fileOutputStream = new FileOutputStream(destFile);

            bufferedInputStream = new BufferedInputStream(fileInputStream);
            bufferedOutputStream = new BufferedOutputStream(fileOutputStream);

            byte[] bytes = new byte[10];
            int len;
            while ((len = bufferedInputStream.read(bytes)) != -1){
                bufferedOutputStream.write(bytes, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //        4.资源关闭：
            /*要求：先关闭外层的流，在关闭内层的流
             *
             * 关闭外层流的同时，内层流也会自动关闭，内层流的关闭可以省略
             *
             * */
            try {
                if (bufferedInputStream != null){
                    bufferedInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                if (bufferedOutputStream != null){
                    bufferedOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                if (fileInputStream != null){
                    fileInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                if (fileOutputStream != null){
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void copyFileWithBuffer(String srcPath, String destPath){
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        BufferedInputStream bufferedInputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        try {
            File srcFile = new File(srcPath);
            File destFile = new File(destPath);

            fileInputStream = new FileInputStream(srcFile);
            fileOutputStream = new FileOutputStream(destFile);

            bufferedInputStream = new BufferedInputStream(fileInputStream);
            bufferedOutputStream = new BufferedOutputStream(fileOutputStream);

            byte[] bytes = new byte[1024];
            int len;
            while ((len = bufferedInputStream.read(bytes)) != -1){
                bufferedOutputStream.write(bytes, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //        4.资源关闭：
            /*要求：先关闭外层的流，在关闭内层的流
             *
             * 关闭外层流的同时，内层流也会自动关闭，内层流的关闭可以省略
             *
             * */
            try {
                if (bufferedInputStream != null){
                    bufferedInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                if (bufferedOutputStream != null){
                    bufferedOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                if (fileInputStream != null){
                    fileInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                if (fileOutputStream != null){
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void test2(){
        long start = System.currentTimeMillis();

        String srcPath = "C:\\Users\\DEII\\Desktop\\01-视频.wmv";
        String destPath = "C:\\Users\\DEII\\Desktop\\02-视频.wmv";
        copyFileWithBuffer(srcPath, destPath);

        long end = System.currentTimeMillis();

        System.out.println("复制非文本文件所用的时间为：" + (end - start)); //不用缓冲：3471 - 用缓冲902
    }

    @Test
    public void test3(){
        BufferedReader br = null;
        BufferedWriter bw = null;
        try {
            br = new BufferedReader(new FileReader(new File("dbcp.txt")));
            bw = new BufferedWriter(new FileWriter(new File("dbcp1.txt")));

//            char[] cbuf = new char[1024];
//            int len;
//            while ((len = br.read(cbuf)) != -1){
//                bw.write(cbuf, 0, len);
//            }

//            方法二：
            String data;
            //一次读一行，不包含换行符，遇到 null 结束
            while((data = br.readLine()) != null){
//                bw.write(data + "\n");
                bw.write(data);
                bw.newLine();//提供换行‘
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(br != null){
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                if (bw != null){
                    bw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
