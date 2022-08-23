package com.zzuli.pony;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * @auther pony
 * @create 2022-05-17 15:43
 */
public class PagingStorage1 {
    public static int len, alllen;//len代表矩阵的行数和列数；alllen代表矩阵的总大小
    public static int reminder;//reminder表示剩下的物理块是多少
    public static int num[][];
    public static String copynum[][];
    public static HashSet<String> hashSet;
    public static ArrayList<page> listpage;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("请输入内存中的物理块的大小 （代表矩阵的行数和列数）：");
        len = input.nextInt();
        alllen = len * len;
        num = new int[len][len];
        copynum = new String[len][len];

        hashSet = new HashSet<>();
        listpage = new ArrayList<>();
        //物理块的初始化
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                num[i][j] = (int) (Math.random() * 2);
                copynum[i][j] = num[i][j] + "";
                if (num[i][j] == 0) {
                    reminder++;
                }
            }
        }
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                System.out.print(num[i][j] + " ");
            }
            System.out.println();
        }
        while (true) {
            System.out.println("请输入下一步的操作 1-分配，2-回收");
            int p = input.nextInt();
            switch (p) {
                case 1://分配
                    System.out.println("请输入作业名及作业号及作业大小");
                    String name = input.next();
                    int size = input.nextInt();
                    allocative(name, size);
                    show();
                    break;
                case 2://回收
                    System.out.println("请输入要回收的作业号");
                    String name1 = input.next();
                    setFree(name1);
                    show();
                    break;
                default:
                    break;
            }
        }
    }

    private static void show() {
        for (int i = 0; i < num.length; i++) {
            for (int j = 0; j < num.length; j++) {
                System.out.print(num[i][j] + " ");
            }
            System.out.print("              ");
            for (int j = 0; j < num.length; j++) {
                System.out.print(copynum[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void setFree(String name) {
        if (hashSet.contains(name) == false) {
            //说明当前这个作业名不存在
            System.out.println("当前作业不存在，释放失败");
            return;
        }
        for (page tem : listpage) {
            if (tem.name.equals(name)) {
                reminder += tem.list.size();
                for (Integer ans : tem.list) {
                    num[(ans - 1) / len][(ans - 1) % len] = 0;
                    copynum[(ans - 1) / len][(ans - 1) % len] = 0 + "";
                }
                listpage.remove(tem);

            }
            hashSet.remove(name);
            System.out.println("释放成功");
            return;
        }

    }

    private static void allocative(String name, int size) {
        if (reminder < size) {
            System.out.println("剩余空间不足，分配失败");
            return;
        }
        if (hashSet.contains(name)) {
            System.out.println("已经存在，分配失败");
            return;
        }
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < num.length; i++) {
            for (int j = 0; j < num.length; j++) {
                if (num[i][j] == 0) {
                    num[i][j] = 1;
                    copynum[i][j] = name;
                    list.add(i * len + j + 1);//将这一个物理空间加入进去
                    reminder--;
                    size--;
                    if (size == 0) {
                        System.out.println("分配成功");
                        hashSet.add(name);
                        listpage.add(new page(name, list));
                        return;
                    }
                }
            }
        }
    }
}

/*
 * name代表作业的名称
 * list代表是每一个作业所占的物理块的位置
 */
class page {
    String name;
    LinkedList<Integer> list;

    public page(String name, LinkedList<Integer> list) {
        super();
        this.name = name;
        this.list = list;
    }
}

