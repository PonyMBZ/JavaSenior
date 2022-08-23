package com.zzuli.java;

import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 标准写入与写出
 * @auther pony
 * @create 2022-03-16 18:07
 */
public class FileReaderWriterTest {
//    public static void main(String[] args) {
//        File file = new File("hello.txt");//相对路径:相较于当前工程
//        System.out.println(file.getAbsoluteFile());
//
//
//    }
    @Test
    public void test1(){
        FileReader fr = null;
        try {
            File file = new File("hello.txt");//相对路径:相较于当前 Module
//      2.提供具体的流

            fr = new FileReader(file);

//      3.数据的读入
//        read():返回读入的一个字符,如果到了文件末尾返回 -1
//        int data1 = fr.read();
//
//        while (data1 != -1){
//            System.out.print((char)data1);
//            data1 = fr.read();
//        }

//      方式二:
            int data2;
            while ((data2 = fr.read()) != -1){
                System.out.print((char) data2);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4.流的关闭操作
            try {
                if (fr != null)
                  fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

//  对 read() 操作的升级,使用 read() 方法的重载
    @Test
    public void test() throws IOException {
        FileReader fr = null;
        try {
//        1.File类的实例化
            File file = new File("hello.txt");
//        2.FileRead 流的示例化
            fr = new FileReader(file);
//        3.读入操作
            char[] cbuff = new char[5];
            int len;
//          read(带参数) 返回每次取的个数,遇到 -1 结束,比如有 13 个元素 , 参数char[5],表明一次
//          取 5 个,前两次都返回 5 ,最后一次取 3 个,返回就是3
            while ((len = fr.read(cbuff)) != -1){
//              错误写法: 输出helloworld123ld 因为上一次数组存的是world 这一次只有123 3个元素,
//              但是要求输出5个(cbuff.length) 相当于将"wor" 替换为 "123" 组成新的数组 "123ld",
//              for (int i = 0; i < cbuff.length; i++){
//                  System.out.print(cbuff[i]);
//              }
                for (int i = 0; i < len; i++){
                    System.out.print(cbuff[i]);
                }
//              错误写法:对应方式一:
//                String str = new String(cbuff);
//                System.out.print(str);
//              正确的写法: String( , , )构造器. 相当于 每次从0开始取, 每次只取 len 个.
                String str1 = new String(cbuff,0,len);
                System.out.println(str1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
//        4.资源关闭
            if(fr != null)
            fr.close();
        }

    }

//  标准写出数据到硬盘文件的操作
    @Test
    public void test2() throws IOException {
        //1.提供File类的对象,指明写出到的文件.(在写出时如果文件不存在,会帮我们创建.
        //                         如果文件存在,用FileWriter fw = new FileWriter(file, false);
        //                         或FileWriter fw = new FileWriter(file);会对原有的文件进行覆盖
        //                         用FileWriter fw = new FileWriter(file, true);不会覆盖原有的文件
        //                         会在原有文件的基础上进行写入
        //                         )
        File file = new File("hello1.txt");

        //2.提供File的对象,用于数据的写出
        FileWriter fw = new FileWriter(file, true);
        //3.写入的操作
        fw.write("I have a dream !\n");
        fw.write("You need to have a dream !");
        //4.流的关闭
        fw.close();
    }
}
