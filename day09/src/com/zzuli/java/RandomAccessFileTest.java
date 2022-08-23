package com.zzuli.java;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * @auther pony
 * @create 2022-03-19 16:37
 * 随机存取文件流： RandomAccessFile 类
 *
 *
 */

public class RandomAccessFileTest {
    @Test
    public void test1() {

        RandomAccessFile raf1 = null;
        RandomAccessFile raf2 = null;
        try {
            raf1 = new RandomAccessFile(new File("Hiiro.webp"), "r");
            raf2 = new RandomAccessFile(new File("Hiiro1.webp"), "rw");

            byte[] bytes = new byte[1024];
            int len;
            while ((len = raf1.read(bytes)) != -1){
                raf2.write(bytes, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (raf1 != null){
                    raf1.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (raf2 != null){
                    raf2.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void test2() throws IOException {

        RandomAccessFile raf1 = new RandomAccessFile("hello.txt", "rw");

        raf1.seek(3);//将角标调到3的位置

        raf1.write("xyz".getBytes());

        raf1.close();
    }

    //        实现插入效果
    @Test
    public void test3() throws IOException {
        RandomAccessFile raf1 = new RandomAccessFile("hello.txt", "rw");
//        将指针调到角标为 3 的位置
        raf1.seek(3);
//        保存指针 3 后面的数据
        StringBuilder builder = new StringBuilder((int) new File("hello.txt").length());
        byte[] buffer = new byte[20];
        int len;
        while ((len = raf1.read(buffer)) != -1){
            builder.append(new String(buffer, 0, len));
        }

//        调回指针，写入“xyz”
        raf1.seek(3);
        raf1.write("xyz".getBytes());

//        将StringBuilder中的数据写入到文件中
        raf1.write(builder.toString().getBytes());
        raf1.close();
    }

}
