package com.zzuli.exer;

import org.junit.Test;

/**
 * 获取两个字符串的最大相同字串，例：str1 = "abcwerthelloyuidefabcdef". str2 = "cvhellobnm"
 * @auther pony
 * @create 2022-03-05 15:52
 */
public class exer3 {
    public String getMinSameString(String str1, String str2){
        String maxStr = (str1.length() >= str2.length())? str1 : str2;
        String minStr = (str1.length() < str2.length())? str1 : str2;
        int length = minStr.length();
        for (int i = 0; i < length; i++) {
            for (int x = 0, y = length - i; y <= length; x++,y++) {
                String subStr = minStr.substring(x,y);
                if (maxStr.contains(subStr)){
                    return subStr;
                }
            }
        }
        return null;
    }
    @Test
    public void test1(){
        String str1 = "abcwerthelloyuidefabcdef";
        String str2 = "cvhellobnm";
        String maxSameStr = getMinSameString(str1, str2);
        System.out.println(maxSameStr);
    }
}
