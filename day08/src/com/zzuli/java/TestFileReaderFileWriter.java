package com.zzuli.java;

import org.junit.Test;

import java.io.*;

/**
 * 复制操作
 * @auther pony
 * @create 2022-03-17 15:19
 */
public class TestFileReaderFileWriter {
    @Test
    public void test(){
        FileReader fr = null;
        FileWriter fw = null;
        try {
//      1.创建File类的对象，指明读入和写出的文件
            File fileReader = new File("hello.txt");
            File fileWriter = new File("hello2.txt");

//      2.创建输入流和输出流
            fr = new FileReader(fileReader);
            fw = new FileWriter(fileWriter);

//      3.数据的读入和写出操作
            char[] cbuf = new char[5];
            int len;
            while ((len = fr.read(cbuf)) != -1){
                //每次写出 len 个字符
                fw.write(cbuf, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
//      4.关闭资源
            try {
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
