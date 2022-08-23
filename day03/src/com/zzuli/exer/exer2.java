package com.zzuli.exer;

import org.junit.Test;

/**
 * 获取一个字符串在另一个字符串出现的次数
 * @auther pony
 * @create 2022-03-05 15:19
 */
public class exer2 {
    public int getCount(String mainStr , String subStr){

        int mainStrLength = mainStr.length();
        int subStrLength = subStr.length();
        int count = 0;
        int index;

        if(mainStrLength > subStrLength){
            while ((index = mainStr.indexOf(subStr)) != -1){

                count++;
                mainStr = mainStr.substring(index + subStr.length());
            }

            return count;
        }else {
            return 0;
        }

    }
    @Test
    public void test1(){

        String mainStr = "abdfsfdsfabdfsabdfsabdfs";
        String subStr = "ab";
        int count = getCount(mainStr, subStr);
        System.out.println(count);
    }
}
