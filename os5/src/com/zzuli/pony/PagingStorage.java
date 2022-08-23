package com.zzuli.pony;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * @auther pony
 * @create 2022-05-17 15:51
 * 1.这次实验使用的语言是 JAVA
 * 2.设计的一些数据结构
 *   2.1. 内存物理块的状态 ：二维数组
 *   2.2. 存放 Job 的双向链表，通过泛型指定 链表上的节点存储的是 一个个 Job 对象
 *   2.3. Job 类：
 *        作业名
 *        作业大小
 *        map LinkedHashMap<Integer, XY> 用于存放因添加而更改内存物理块的状态的坐标
 *           Integer：下标，从 0 开始到 作业大小-1
 *           XY：一个坐标内部类
 */
class Job{
    private String name; //作业名
    private int size;    //作业大小
    LinkedHashMap map = new LinkedHashMap<Integer, XY>(); //坐标

    public Job() {
    }

    public Job(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public Job(String name, int size, LinkedHashMap<Integer, XY> map) {
        this.name = name;
        this.size = size;
        this.map = map;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Job{" +
                "name='" + name + '\'' +
                ", size=" + size +
                '}';
    }

    //内部类 坐标
    static class XY{
        int x; //横坐标
        int y; //列坐标

        public XY() {
        }

        public XY(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}

public class PagingStorage {
    Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        PagingStorage ps = new PagingStorage();
        String[][] memory = ps.memoryInit();//内存初始化
        LinkedList<Job> jobList = new LinkedList<>();
        ps.show(memory, jobList);//进入系统，开始操作
    }

    //展示系统的功能
    public void show(String[][] memory, LinkedList<Job> jobList){
        boolean isFlag = true;
        do {
            System.out.println("*********基本分页存储系统管理*********");
            System.out.println("\t\t\t1.作业分配");
            System.out.println("\t\t\t2.内存显示");
            System.out.println("\t\t\t3.作业回收");
            System.out.println("\t\t\t0.退出系统");
            System.out.println("***********************************");
            System.out.print("请输入你想使用的功能 : ");
            String num = scanner.next();
            switch (num) {
                case "1":
                    distribute(memory, jobList);
                    break;
                case "2":
                    showMemory(memory);
                    break;
                case "3":
                    recovery(memory, jobList);
                    break;
                case "0":
                    System.out.print("是否要退出（y/n）:");
                    String yn = scanner.next();
                    isFlag = "y".equals(yn)? false : true;
                    break;
            }
        }while (isFlag);
    }

    //内存初始化
    //用户输入内存大小，使用随机数，生成一个 n*10 的二维 0/1 矩阵
    private String[][] memoryInit(){
        System.out.print("请输入内存的大小 : ");
        //1.内存大小，每行显示 10 个物理块的情况
        int mSize = scanner.nextInt();

        //2.计算行数，用户输入的大小不一定是 10 的倍数
        int h = (mSize + 9)/10;

        //3.因为初始化时部分物理块已分配，所以使用范围为 [0,1] 的随机数，对数组进赋值
        String[][] memory = new String[h][10];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < 10; j++) {
                int value = (int) (Math.random() * 2);
                memory[i][j] = String.valueOf(value);
            }
        }

        //4.对于不是内存的部分，使用 "/" 代替
        if (mSize % 10 != 0) {
            for (int i = 9; i >= mSize % 10; i--) {
                memory[h-1][i] = "/";
            }
        }

        //5.显示一下初始化内存的情况
        System.out.println("内存初始化完成");
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print(memory[i][j] + "\t");
            }
            System.out.println("\n");
        }
        return memory;
    }

    //作业分配
    private void distribute(String[][] memory, LinkedList<Job> jobList){

        System.out.println("************作业分配************");
        String jobName = null;       //用户输入的作业名
        int jobSize = 0;          //用户输入的作业大小
        LinkedHashMap<Integer, Job.XY> jobMap = new LinkedHashMap<>();
        boolean flag = true;  //用户输入作业名同名，内存空闲空间不足，给出提示

        //1.计算内存中空闲的物理块的个数 freeCount
        int freeCount = 0;
        for (int i = 0; i < memory.length; i++) {
            for (int j = 0; j < memory[0].length; j++) {
                if ("0".equals(memory[i][j])) freeCount++;
            }
        }

        //2.对于用户输入的作业，无法分配的，给出提示信息，并重新输入
        while (flag) {
            boolean isFlag = false;
            System.out.print("请输入作业名 : ");
            jobName = scanner.next();

            //遍历 job 链表
            for (Job job: jobList) {
                //输入的作业名已经在job链表里存在了，isFlag = true，
                isFlag = jobName.equals(job.getName());
                if (isFlag) break;
            }

            if (isFlag){
                System.out.println("已有相同的作业名，请重新输入");
            }else {
                System.out.print("请输入作业大小 : ");
                jobSize = scanner.nextInt();
                //输入的作业太大，内存剩余空闲空间不足以分配
                if (jobSize > freeCount){
                    System.out.println("内存剩余空间不足，请重新输入");
                }else {
                    flag = false;
                }
            }
        }

        //程序能执行到这，说明输入的作业没问题，开始分配作业

        //3.更新内存物理块状态
        int count = jobSize;
        out:for (int i = 0; i < memory.length; i++) {
            for (int j = 0; j < memory[0].length; j++) {
                if ("0".equals(memory[i][j])){
                    count--;
                    memory[i][j] = "1";
                    //把变动的坐标储存一下
                    jobMap.put(count, new Job.XY(i, j));
                    if (count==0){
                        break out;
                    }
                }
            }
        }

        //4. 用户输入的作业，链到链表上
        jobList.add(new Job(jobName, jobSize, jobMap));

        System.out.println("作业分配成功");
    }

    //内存显示
    private void showMemory(String[][] memory){
        System.out.println();
        System.out.println("************内存分配情况************");
        for (int i = 0; i < memory.length; i++) {
            for (int j = 0; j < memory[0].length; j++) {
                System.out.print(memory[i][j] + "\t");
            }
            System.out.println("\n");
        }
        System.out.println();
    }

    private void recovery(String[][] memory, LinkedList<Job> jobList){
        System.out.println("************作业回收************");
        System.out.print("请输入要回收的作业名 : ");
        String jobName = scanner.next();
        boolean isFlag = false; //在作业链表上是否找到了要回收的作业

        //1. 遍历Job链表
        for (Job job: jobList) {
            //如果在 Job 链表找到了作业名 与 输入的作业名 一样的，即找到了
            if (jobName.equals(job.getName())){
                isFlag = true;
                //2. 更新内存物理块状态
                for (int i = 0; i < job.getSize(); i++) {
                    //获取作业的 坐标 map
                    Object xyObj = job.map.get(i);
                    //Object -> Job.XY 向下转型，强转之前先用 instanceof 判断一下，防止报错
                    if (xyObj instanceof Job.XY){
                        Job.XY xy = (Job.XY) xyObj;
                        memory[xy.x][xy.y] = "0";
                    }else {
                        throw new RuntimeException("传入的数据类型不匹配");
                    }
                }

                //3. 修改完状态后将 job 从 作业链表上移除，至此回收成功
                jobList.remove(job);
            }
        }

        if (isFlag){
            System.out.println("作业回收成功");
        }else {
            System.out.println("作业名不存在，作业回收失败");
        }
    }
}

