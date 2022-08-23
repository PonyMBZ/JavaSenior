package com.zzuli.exer;

import org.junit.Test;

/**
 * 将一个String指定开始和结束位置的一段字符串反转
 * @auther pony
 * @create 2022-03-05 14:29
 */

public class exer1 {
    //方式一：
    public String reverse(String str, int startIndex, int endIndex){

        char[] arr = str.toCharArray();
        for (int i = startIndex, j = endIndex ;i<j; i++, j--) {
            char temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
        return new String(arr);
    }
    public String reverse1(String str, int startIndex, int endIndex){
        String reverseStr = str.substring(0,startIndex);

        for(int i = endIndex; i >= startIndex; i--){
            reverseStr += str.charAt(i);
        }

        reverseStr += str.substring(endIndex + 1);
        return reverseStr;
    }

    public String reverse2(String str, int startIndex, int endIndex){
        StringBuilder builder = new StringBuilder(str.length());
        builder.append(str.substring(0, startIndex));
        for(int i = endIndex; i >= startIndex; i--){
            builder.append(str.charAt(i));
        }
        builder.append(str.substring(endIndex));
        return builder.toString();
    }
    @Test
    public void test1(){
        String str = "abcdefg";
        String reverse = reverse1(str, 2, 5);
        System.out.println(reverse);
    }
}
