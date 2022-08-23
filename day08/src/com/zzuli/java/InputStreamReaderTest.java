package com.zzuli.java;

import org.junit.Test;

import java.io.*;

/**
 * 转换流
 * @auther pony
 * @create 2022-03-18 16:00
 */
public class InputStreamReaderTest {

    @Test
    public void test1() throws IOException {
        File file1 = new File("dbcp.txt");
        File file2 = new File("dbcp_gbk.txt");

        InputStream fis = new FileInputStream(file1);
        FileOutputStream fos = new FileOutputStream(file2);

        InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
        OutputStreamWriter osw = new OutputStreamWriter(fos,"gbk");

        char[] cbuf = new char[20];
        int len;
        while ((len = isr.read(cbuf)) != -1){
            osw.write(cbuf, 0, len);
        }

        isr.close();
        osw.close();

    }
    
}
