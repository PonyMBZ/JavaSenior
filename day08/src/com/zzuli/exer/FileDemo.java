package com.zzuli.exer;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * @auther pony
 * @create 2022-03-16 16:03
 */
public class FileDemo {
    @Test
    public void test1() throws IOException {
        File file = new File("D:\\io\\io1\\hello.txt");

        File file1 = new File("D:\\io\\io1\\haha.txt");
        file1.createNewFile();
        File file2 = new File(file.getParent(),"haha1.txt");
        boolean newFile = file2.createNewFile();
        if (newFile){
            System.out.println("创建成功");
        }


    }

}
