package com.zzuli.java;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * file 的使用
 * @auther pony
 * @create 2022-03-15 17:34
 */
public class FileTest {
//  构造器
    @Test
    public void test1(){
//      构造器1:
        //相对路径
        File file1 = new File("he1.txt");
        //绝对路径
        File file2 = new File("D:\\DevelopmentStudy\\practice\\IdeaWorkspace\\JavaSenior\\day08\\he2.txt");
        System.out.println(file1);
        System.out.println(file2);

//      构造器2
        File file3 = new File("D:\\DevelopmentStudy", "practice\\IdeaWorkspace\\JavaSenior\\day08");
        System.out.println(file3);
//      构造器3
        File file4 = new File(file3, "he4.txt");
        System.out.println(file4);
    }

//  常用方法1
    @Test
    public void test2(){
        File file1 = new File("hello.txt");
        File file2 = new File("D:\\io\\hi.txt");
        //获取绝对路径
        System.out.println(file1.getAbsoluteFile());
        //获取路径
        System.out.println(file1.getPath());
        //获取名字
        System.out.println(file1.getName());
        //获取上层文件目录路径，如果没有，返回 null
        System.out.println(file1.getParent());
        //获取文件长度（即 字节数）。不能获取目录长度
        System.out.println(file1.length());
        //获取最后一次修改时间，单位：毫秒值
        System.out.println(new Date(file1.lastModified()));

        System.out.println();

        System.out.println(file2.getAbsoluteFile());
        System.out.println(file2.getPath());
        System.out.println(file2.getName());
        System.out.println(file2.getParent());
        System.out.println(file2.length());
        System.out.println(file2.lastModified());

    }

    @Test
    public void test3(){
//      文件必须存在
        File file = new File("D:\\DevelopmentStudy\\practice\\IdeaWorkspace\\JavaSenior");

        String[] list = file.list();
        for (String str: list) {
            System.out.println(str);
        }

        System.out.println("*****************");

        File[] files = file.listFiles();
        for (File f: files) {
            System.out.println(f);
        }
    }

    //重命名 要想成功 file1 必须存在， file2 不能存在。
    //类似于剪切
    @Test
    public void test4(){
        File file1 = new File("hello.txt");
        File file2 = new File("D:\\io\\hi.txt");

        boolean renameTo = file2.renameTo(file1);
        System.out.println(renameTo);
    }

    @Test
    public void test5(){
        File file1 = new File("hello.txt");

        //判断是否是一个文件目录
        System.out.println(file1.isDirectory());
        //判断是否是文件
        System.out.println(file1.isFile());
        //判断是否存在
        System.out.println(file1.exists());
        //判断是否可读
        System.out.println(file1.canRead());
        //判断是否可写
        System.out.println(file1.canWrite());
        //判断是否隐藏
        System.out.println(file1.isHidden());

        System.out.println("***************");

        File file2 = new File("D:\\io");

        System.out.println(file2.isDirectory());
        System.out.println(file2.isFile());
        System.out.println(file2.exists());
        System.out.println(file2.canRead());
        System.out.println(file2.canWrite());
        System.out.println(file2.isHidden());

    }
    //创建硬盘中对应的文件或文件目录
    @Test
    public void test6() throws IOException {

//      文件的创建
        File file1 = new File("hi.txt");

        if (!file1.exists()){
            file1.createNewFile();
            System.out.println("创建成功");
        } else {
          file1.delete();
            System.out.println("删除成功");
        }
    }
    @Test
    public void test7(){
        File file1 = new File("D:\\io\\io1\\io3");

        boolean mkdir1 = file1.mkdir();
        if (mkdir1){
            System.out.println("创建成功1");
        }

        File file2 = new File("D:\\io\\io1\\io4");

        boolean mkdir2 = file2.mkdirs();
        if (mkdir2){
            System.out.println("创建成功2");
        }
        //删除指定文件,要想成功,目录下不能有子目录或文件
        File file = new File("D:\\io\\io1\\hello.txt");
        boolean delete = file.delete();
        System.out.println(delete);
    }

}
