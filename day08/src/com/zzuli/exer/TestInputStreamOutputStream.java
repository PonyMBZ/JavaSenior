package com.zzuli.exer;

import org.junit.Test;

import java.io.*;

/**
 * 关于图片的复制
 * @auther pony
 * @create 2022-03-17 15:57
 */
public class TestInputStreamOutputStream {
    @Test
    public void test() {
        InputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            File fileInput = new File("Hiiro.webp");
            File fileOutput = new File("Hiiro1.webp");

            fileInputStream = new FileInputStream(fileInput);
            fileOutputStream = new FileOutputStream(fileOutput);

            byte[] bytes = new byte[10];
            int len;
            while ((len = fileInputStream.read(bytes)) != -1){
                fileOutputStream.write(bytes, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileOutputStream != null)
                    fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (fileInputStream != null)
                    fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //指定路径下的文件的复制
    public void copyFile(String srcPath, String destPath){
        InputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            File fileInput = new File(srcPath);
            File fileOutput = new File(destPath);

            fileInputStream = new FileInputStream(fileInput);
            fileOutputStream = new FileOutputStream(fileOutput);

            byte[] bytes = new byte[1024];
            int len;
            while ((len = fileInputStream.read(bytes)) != -1){
                fileOutputStream.write(bytes, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileOutputStream != null)
                    fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (fileInputStream != null)
                    fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void test2(){
        long start = System.currentTimeMillis();

        String srcPath = "C:\\Users\\DEII\\Desktop\\01-视频.wmv";
        String dectPath = "C:\\Users\\DEII\\Desktop\\02-视频.wmv";
        copyFile(srcPath, dectPath);

        long end = System.currentTimeMillis();

        System.out.println("复制文件所花费的时间为：" + (end - start));
    }
}
