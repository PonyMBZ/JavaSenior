package com.zzuli.java;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * @auther pony
 * @create 2022-03-19 20:07
 */
public class FileUtilsTest {
    public static void main(String[] args) {
        File srcFile = new File("day09\\Hiiro.webp");
        File destFile = new File("day09\\Hiiro2.webp");
        try {
            FileUtils.copyFile(srcFile,destFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
