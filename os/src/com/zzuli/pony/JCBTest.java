package com.zzuli.pony;

import java.util.Scanner;

/**
 * @auther pony
 * @create 2022-03-29 12:25
 */
class JCB{
    private String name;            //作业名
    private double arrivalTime;     //到达时间
    private double serviceTime;     //服务时间
    private double startTime;       //开始执行时间
    private double completeTime;    //完成时间
    private double turnTime;        //周转时间
    private double wTurnTime;       //带权周转时间
    private double Rp;              //相应比

    //无参的构造方法
    public JCB() {
    }

    //带参的构造方法
    public JCB(String name, double arrivalTime, double serviceTime, double startTime, double completeTime, double turnTime, double wTurnTime, double rp) {
        this.name = name;
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
        this.startTime = startTime;
        this.completeTime = completeTime;
        this.turnTime = turnTime;
        this.wTurnTime = wTurnTime;
        this.Rp = rp;
    }

    public JCB(String name, double arrivalTime, double serviceTime) {
        this.name = name;
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
    }

    //get()、set()方法；
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(double arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public double getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(double serviceTime) {
        this.serviceTime = serviceTime;
    }

    public double getStartTime() {
        return startTime;
    }

    public void setStartTime(double startTime) {
        this.startTime = startTime;
    }

    public double getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(double completeTime) {
        this.completeTime = completeTime;
    }

    public double getTurnTime() {
        return turnTime;
    }

    public void setTurnTime(double turnTime) {
        this.turnTime = turnTime;
    }

    public double getwTurnTime() {
        return wTurnTime;
    }

    public void setwTurnTime(double wTurnTime) {
        this.wTurnTime = wTurnTime;
    }

    public double getRp() {
        return Rp;
    }

    public void setRp(double rp) {
        Rp = rp;
    }

    //重写toString()方法，防止输出的是地址
    @Override
    public String toString() {
        return name + "\t\t\t" + arrivalTime + "\t\t\t" + serviceTime + "\t\t\t" +
                startTime + "\t\t\t" + completeTime + "\t\t\t" + turnTime + "\t\t\t" +
                wTurnTime +"\t\t\t";
    }
}
public class JCBTest {

    //打印表格
    public void JCBPrint(JCB[] jcbs){
        System.out.println("进程名" + "\t" + "到达时间" + "\t\t" + "服务时间" + "\t\t" + "执行时间" + "\t\t" + "完成时间" + "\t\t" + "周转时间" + "\t\t" +"带权周转时间");

        for (int i = 0; i < jcbs.length; i++) {
            System.out.println(jcbs[i].toString());
        }
    }

    //排序
    public void JCBSort(JCB[] jcbs, String str){
        if ("fcfs".equals(str)) {
            fcfsSort(jcbs);
        } else if ("sjf".equals(str)){
            sjfSort(jcbs);
        }else if ("hrrn".equals(str)){
            hrrnSort(jcbs);
        }else {
            System.out.println("输入错误请重新输入!");
        }
    }

    //先来先服务
    public void fcfsSort(JCB[] jcbs){
        JCB mid;
        for(int i=jcbs.length-1; i>0; i--) {
            for(int j=0; j<i; j++) {
                if(jcbs[j].getArrivalTime()>jcbs[j+1].getArrivalTime()) {
                    mid = jcbs[j];
                    jcbs[j] = jcbs[j+1];
                    jcbs[j+1]= mid;
                }
            }
        }
    }

    /* 短作业优先
     * 注意 sjf 策略的第一个调度的还是最先到达的那个 JCB，
     * 所以在排序时因该将最先到达的JCB找到并排到第 0 个位置，（直接用fcfs先排一下）
     * 并且它不再参与后面的排序，所以下方第二个for(j.....)循环的j = 1
     *  */
    public void sjfSort(JCB[] jcbs){
        JCB mid;
        fcfsSort(jcbs);

        for(int i=jcbs.length-1; i>0; i--) {
            for(int j=1; j<i; j++) {
                    if(jcbs[j].getServiceTime()>jcbs[j+1].getServiceTime()) {
                        mid = jcbs[j];
                        jcbs[j] = jcbs[j+1];
                        jcbs[j+1] = mid;
                    }
            }

        }
    }

    public void hrrnSort(JCB[] jcbs){
        JCB mid;
        //1.先调用 fcfs 排序，将对象数组第 0 个位置确定下来
        fcfsSort(jcbs);
        int maxIndex;
        double waitTime;
        for (int i = 1; i < jcbs.length; i++) {
            //因为计算 响应比(Rp) 要用到上一个已经执行的作业的 完成时间 ，所以每次循环都要计算一次数据。
            setData(jcbs);
            //2.对下标 i 到 最后 计算最大的响应比，并找到其下标
            for (int j = i; j < jcbs.length; j++) {
                waitTime = jcbs[i-1].getCompleteTime() - jcbs[j].getArrivalTime();
                if(waitTime < 0){
                    waitTime = 0;
                }
                jcbs[j].setRp((waitTime + jcbs[j].getServiceTime())
                        / jcbs[j].getServiceTime());
            }
            JCB maxRp = new JCB();
            maxIndex = 0;
            for(int j = i; j < jcbs.length; j++){
                if(jcbs[j].getRp() > maxRp.getRp()) {
                    maxRp = jcbs[j];
                    maxIndex = j;
                }else if(jcbs[j].getRp() == maxRp.getRp() && jcbs[j].getArrivalTime() < maxRp.getArrivalTime()){
                    maxRp = jcbs[j];
                    maxIndex = j;
                }
            }
            /*3.找到最大响应比后，交换 i 与 最大响应比位置，这样第 i 个要执行的作业就确定了
            当循环全部结束，数组的顺序就是作业的调度顺序*/
            mid = jcbs[i];
            jcbs[i] = jcbs[maxIndex];
            jcbs[maxIndex] = mid;
        }
    }

    //进行计算，填入数据
    public void setData(JCB[] jcbs){
        //第 0 个位置有点特殊，我就单独进行计算了
        jcbs[0].setStartTime(jcbs[0].getArrivalTime());

        double commpleteTime0 = Double.parseDouble(String.format("%.2f", (jcbs[0].getStartTime() + jcbs[0].getServiceTime())));
        jcbs[0].setCompleteTime(commpleteTime0);
        double trunTime0 = Double.parseDouble(String.format("%.2f", (jcbs[0].getCompleteTime() - jcbs[0].getArrivalTime())));
        jcbs[0].setTurnTime(trunTime0);
        //结果保存两位小数
        double wtrunTime0 = Double.parseDouble(String.format("%.2f", (jcbs[0].getTurnTime() / jcbs[0].getServiceTime())));
        jcbs[0].setwTurnTime(wtrunTime0);

        //填表过程
        for (int i = 1; i < jcbs.length; i++) {
            //如果上一个JCB的完成时间要小于下一个的到达时间，那么下一个的开始时间就不是上一个的完成时间，而是它自己的到达时间
            if(jcbs[i-1].getCompleteTime() >= jcbs[i].getArrivalTime()){
                jcbs[i].setStartTime(jcbs[i-1].getCompleteTime());
            }else{
                jcbs[i].setStartTime(jcbs[i].getArrivalTime());
            }
            double commpleteTime = Double.parseDouble(String.format("%.2f", (jcbs[i].getStartTime() + jcbs[i].getServiceTime())));
            jcbs[i].setCompleteTime(commpleteTime);
            double trunTime = Double.parseDouble(String.format("%.2f", (jcbs[i].getCompleteTime() - jcbs[i].getArrivalTime())));
            jcbs[i].setTurnTime(trunTime);

            double wtrunTime = Double.parseDouble(String.format("%.2f", (jcbs[i].getTurnTime() / jcbs[i].getServiceTime())));
            jcbs[i].setwTurnTime(wtrunTime);
        }

    }

    //打印调度过程
    public void JCBProcess(JCB[] jcbs){
        for (int i = 0; i < jcbs.length; i++) {
            System.out.println("第" + (i + 1) + "个调用的进程名称是：" + jcbs[i].getName());
            System.out.println("到达时间为：" + jcbs[i].getArrivalTime());
            System.out.println("服务时间为：" + jcbs[i].getServiceTime());
            System.out.println("执行时间为：" + jcbs[i].getStartTime());
            System.out.println("完成时间：" + jcbs[i].getCompleteTime());
            System.out.println("周转时间；" + jcbs[i].getTurnTime());
            System.out.println("带权周转时间；" + jcbs[i].getwTurnTime());
            System.out.println("----------------------------------------------------------------");
        }
    }

    public static void main(String[] args) {
        JCBTest jcbTest = new JCBTest();
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入作业数量：n = ");
        int n = scanner.nextInt();
        JCB[] jcbs = new JCB[n];

        //1.输入必要的数据
        for(int i = 0; i < jcbs.length; i++) {
            jcbs[i] = new JCB();
            System.out.print("请输入作业的名字：name = ");
            jcbs[i].setName(scanner.next());
            System.out.print("请输入作业" + jcbs[i].getName() + "的到达时间：arrivalTime = ");
            jcbs[i].setArrivalTime(scanner.nextDouble());
            System.out.print("请输入作业" + jcbs[i].getName() + "的服务时间：serviceTime = ");
            jcbs[i].setServiceTime(scanner.nextDouble());
            System.out.println();
        }

        //2.输出初始作业状态（未调度时）
        System.out.println("——————————————————————————————————初始作业状态表——————————————————————————————————");
        jcbTest.JCBPrint(jcbs);
        System.out.println();

        //3.进行排序
        boolean flag = true;
        //如果用户输入的不规范，就会一直提示用户输入，以免用户输入错误程序直接结束体验不好
        while (flag) {
            System.out.print("请输入作业调度算法（'fcfs' or 'sjf' or 'hrrn' 注意小写）: ");
            String str = scanner.next();
            jcbTest.JCBSort(jcbs, str);
            if("fcfs".equals(str)||"sjf".equals(str)||"hrrn".equals(str)){
                flag = false;
            }
        }
        System.out.println();

        //4.将初始表空缺的数据补充完整
        jcbTest.setData(jcbs);

        //5.展示进程调度过程
        System.out.println("——————————————————————————————————作业调度过程——————————————————————————————————");
        jcbTest.JCBProcess(jcbs);
        System.out.println();

        //6.输出最终的作业状态表
        System.out.println("——————————————————————————————————最终作业状态表——————————————————————————————————");
        jcbTest.JCBPrint(jcbs);
        System.out.println();
    }
}
