package com.zzuli.exer;

import org.junit.Test;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 获取文本上每个字符的个数
 * 思路：1.遍历文本每一个字符
 *      2.字符出现的次数存在 Map 中
 *      3.把 Map 中的数据写如文件中
 * @auther pony
 * @create 2022-03-18 14:39
 */
public class PicTest {
    @Test
    public void test1() {
        BufferedReader br = null;
        BufferedWriter bw = null;

        try {
//        1.创建Map集合
            HashMap<Character, Integer> map = new HashMap<>();

//        2.遍历每一个字符，每一个字符出现的次数存到 map 中
            br = new BufferedReader(new FileReader("dbcp.txt"));

            int c;
            while ((c = br.read()) != -1) {
                char ch = (char) c;
                //判断ch是否是第一次出现
                if (map.get(ch) == null) {
                    map.put(ch, 1);
                } else {
                    map.put(ch, map.get(ch) + 1);
                }
            }

//        3.把 map 中的数据存到文件 count.txt
            bw = new BufferedWriter(new FileWriter("count.txt"));
//        遍历 Map,写入数据
            Set<Map.Entry<Character, Integer>> entrySet = map.entrySet();
            for (Map.Entry<Character, Integer> entry : entrySet) {
                switch (entry.getKey()) {
                    case ' ':
                        bw.write("空格=" + entry.getValue());
                        break;
                    case '\t':
                        bw.write("tab键=" + entry.getValue());
                        break;
                    case '\r':
                        bw.write("回车=" + entry.getValue());
                        break;
                    case '\n':
                        bw.write("换行=" + entry.getValue());
                        break;
                    default:
                        bw.write(entry.getKey() + "=" + entry.getValue());
                        break;
                }
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)
                    br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (bw != null)
                    bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
