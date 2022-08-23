package com.zzuli.shy2;

import java.util.Scanner;

/**
 * @auther pony
 * @create 2022-04-06 20:54
 */
class JCB2{
    String name;            //作业名
    double arrivalTime;     //到达时间
    double serviceTime;     //服务时间
    double serviceTime1;
    double completeTime;    //完成时间
    double turnTime;        //周转时间
    double wTurnTime;       //带权周转时间
    double priority;        //优先权
    String state;           //状态 W:等待 R:运行 F:完成

    //通过无参构造器赋初值，只要用这个构造器了 new 对象了就把对象的状态赋成 "W" （等待）
    public JCB2() {
        state = "W";
    }

    public JCB2(String name, double arrivalTime, double serviceTime, double completeTime, double turnTime, double wTurnTime, double priority, String state) {
        this.name = name;
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
        this.completeTime = completeTime;
        this.turnTime = turnTime;
        this.wTurnTime = wTurnTime;
        this.priority = priority;
        this.state = state;
    }

    @Override
    public String toString() {
        return name + "\t\t\t" + arrivalTime + "\t\t\t" + serviceTime
                + "\t\t\t" + completeTime + "\t\t\t" + turnTime + "\t\t\t" +
                wTurnTime +"\t\t\t" + priority + "\t\t\t" + state;
    }
}

public class JCBTest2 {
    //打印表格
    public void JCBPrint2(JCB2[] jcbs){
        System.out.println("进程名" + "\t" + "到达时间" + "\t\t" + "服务时间" + "\t\t" +  "完成时间" + "\t\t" + "周转时间" + "\t\t" +"带权周转时间" + "\t\t" + "优先权"+ "\t\t" + "状态");


        for (int i = 0; i < jcbs.length; i++) {
            System.out.println(jcbs[i].toString());
        }
    }

    //进行计算，填入数据
    public void setData(JCB2[] jcbs){
        //填表过程
        for (int i = 0; i < jcbs.length; i++) {
            double trunTime = Double.parseDouble(String.format("%.2f", (jcbs[i].completeTime - jcbs[i].arrivalTime)));
            jcbs[i].turnTime =trunTime;

            double wtrunTime = Double.parseDouble(String.format("%.2f", (jcbs[i].turnTime / jcbs[i].serviceTime1)));
            jcbs[i].wTurnTime = wtrunTime;
        }

    }

    public void hrrnSort(JCB2[] jcbs){
        JCB2 mid;
        double time = 0;
        boolean flag = true;
        double minArrivalTime = jcbs[0].arrivalTime;

        //1.找到到达时间最小的值
        for (int i = 1; i < jcbs.length; i++) {
            if (jcbs[i].arrivalTime <= minArrivalTime){
                minArrivalTime = jcbs[i].arrivalTime;
            }
        }
        /*2.while() 相当于有一个表在不停的走,然后记录每一个时刻（time）,所有作业的状态
         *  当作业全部执行完了（即作业的服务时间都为0，或者所有作业的状态都是 "F" (完成)，表就停了，循环终止）
         * */
        while (flag){
            //3.如果到达时间最早的那个作业的都没到，那么其他的作业肯定也没到，没到就都等着
            if (time < minArrivalTime) {
                System.out.println("第" + time + "个时间单位的各个作业的状态为" );
                JCBPrint2(jcbs);
            } else {
                /*
                 * 4.我将数组分成了两半，将满足调度条件的一个作业放到数组的第 0 个位置，并将其状态置为 "R" ，
                 * 位置 0 后面的（下标 i 是从1开始的）其他的作业都是等待状态，状态置为 "W"，每个时刻（time）所有作业的状态只有一个，
                 * 这个for循环就是为确定这一状态
                 * */
                for (int i = 1; i < jcbs.length; i++) {
                    //如果有作业已经完成了（"F"）,这个作业就不再考虑了
                    if (!("F".equals(jcbs[i].state))) {
                        /* 如果后面有一个作业的优先值要 大于 第 0 个位置的优先值，
                         * 并且后面的作业要到了才行，即作业的到达时间要小于当前时刻的时间
                         * 或者发现第 0 个位置的作业已经完成了，那就赶快把它换到后面去，不要占着位置
                         * 把它和第 0 个位置的作业换位置（该它调度了，体现抢占式）
                         * */
                        if ((jcbs[0].priority < jcbs[i].priority) && (jcbs[i].arrivalTime <= time) || "F".equals(jcbs[0].state)){
                            mid = jcbs[0];
                            jcbs[0] = jcbs[i];
                            jcbs[i] = mid;
                            /*
                             * 如果两个优先权相等时，谁先到就先执行谁
                             * */
                        }else if(((jcbs[0].priority == jcbs[i].priority) && (jcbs[0].arrivalTime > jcbs[i].arrivalTime)) || "F".equals(jcbs[0].state)){
                            mid = jcbs[0];
                            jcbs[0] = jcbs[i];
                            jcbs[i] = mid;
                        }
                        //每次循环都将第 0 个位置的作业状态变成 R，其他作业状态都是 W
                        jcbs[0].state = "R";
                        jcbs[i].state = "W";
                    }
                }
                for (int i = 0; i < jcbs.length; i++) {
                    //如果一个作业的服务时间没有了（小于等于0），那说明它的状态就为 F (完成了)
                    if (jcbs[i].serviceTime <= 0){
                        jcbs[i].state = "F";
                        //把作业第一次状态变为 F 的时刻给完成时间，后面算周转时间要用
                        if (jcbs[i].completeTime == 0){
                            jcbs[i].completeTime = time;
                        }
                    }
                }
                System.out.println("第" + time + "个时间单位的各个作业的状态为" );
                JCBPrint2(jcbs);
            }
            time++;
            flag = true;
            int count = 0;
            //5.统计作业状态为 F 的个数，当全部的作业状态都为 F 时，表就停了（循环结束）
            for (int i = 0; i < jcbs.length; i++) {
                if ("F".equals(jcbs[i].state)){
                    count++;
                }
            }
            if (count == jcbs.length){
                flag = false;
            }
            //6.遍历数组，作业状态为 R 的，其优先权降低，如果它的服务时间大于0，就减减，服务过一分钟了，总的服务时间不就要少一分钟了
            for (int i = 0; i < jcbs.length; i++) {
                if ("R".equals(jcbs[i].state)){
                    jcbs[i].priority--;
                    if (jcbs[i].serviceTime > 0){
                        jcbs[i].serviceTime--;
                    }
                }
                //如果一个作业的服务时间没有了（小于等于0），那说明它的状态就为 F (完成了)
                if (jcbs[i].serviceTime <= 0){
                    jcbs[i].state = "F";
                    //把作业第一次状态变为 F 的时刻给完成时间，后面算周转时间要用
                    if (jcbs[i].completeTime == 0){
                        jcbs[i].completeTime = time;
                    }
                }
            }
        }

    }

    public static void main(String[] args) {
        JCBTest2 jcbTest = new JCBTest2();
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入作业数量：n = ");
        int n = scanner.nextInt();
        JCB2[] jcbs = new JCB2[n];

        //1.输入必要的数据
        for(int i = 0; i < jcbs.length; i++) {
            System.out.print("请输入作业的名字：name = ");
            jcbs[i] = new JCB2();
            jcbs[i].name = scanner.next();
            System.out.print("请输入作业" + jcbs[i].name + "的到达时间：arrivalTime = ");
            jcbs[i].arrivalTime = scanner.nextDouble();
            System.out.print("请输入作业" + jcbs[i].name + "的服务时间：serviceTime = ");
            jcbs[i].serviceTime = scanner.nextDouble();
            jcbs[i].serviceTime1 = jcbs[i].serviceTime;
            System.out.print("请指定作业" + jcbs[i].priority + "的优先权 ：priority = " );
            jcbs[i].priority = scanner.nextDouble();
            System.out.println();
        }

        //2.输出初始作业状态（未调度时）
        System.out.println("——————————————————————————————————初始作业状态表——————————————————————————————————");
        jcbTest.JCBPrint2(jcbs);
        System.out.println();

        //3.进行排序
        System.out.println("——————————————————————————————————作业调度过程——————————————————————————————————");
        jcbTest.hrrnSort(jcbs);
        System.out.println();

        //4.计算周转时间和带权周转时间
        jcbTest.setData(jcbs);
        System.out.println();

        //5.输出最终结果
        System.out.println("——————————————————————————————————最终作业状态表——————————————————————————————————");
        jcbTest.JCBPrint2(jcbs);
        System.out.println();

    }
}
