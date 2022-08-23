package com.zzuli.pony;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * @auther pony
 * @create 2022-04-20 15:12
 * 这次实验，所使用的语言是JAVA,整体上是通过链表实现的
 * 1、使用的是 Java 定义好的 LinkedList 实现的，下面是过程中使用的一些已经写好的方法
 *    查询：get(int index)：按照下标获取元素；
 *    添加：add(E e)：在链表后添加一个元素；
 *         add(int index, E element)：在指定位置插入一个元素。
 *    删除：remove(E e)：移除指定元素；
 * 2、LinkedList<Partition> partitions = new LinkedList<>()
 *    通过泛型指定链表上每一个节点存放的是 Partition 类型的对象
 */
//内存分区类
class Partition {
    private int pStart;     //分区开始地址
    private int pSize;      //分区大小
    private boolean pState; //分区状态
    /**
     * 不可分割的最小剩余区大小，
     * 如果分割后剩下的小于等于 pMinSize 就将剩余的都分给它
     * 否则就要，将要分配的大小分割出去，剩下的部分任留下，等待下一次的分配
     */
    public final int pMinSize = 2;

    public Partition() {
    }

    public Partition(int pStart, int pSize) {
        this.pStart = pStart;
        this.pSize = pSize;
        this.pState = true;
    }

    public int getpStart() {
        return pStart;
    }

    public void setpStart(int pStart) {
        this.pStart = pStart;
    }

    public int getpSize() {
        return pSize;
    }

    public void setpSize(int pSize) {
        this.pSize = pSize;
    }

    public boolean ispState() {
        return pState;
    }

    public void setpState(boolean pState) {
        this.pState = pState;
    }

    @Override
    public String toString() {
        return "Partition{" +
                "pStart=" + pStart +
                ", pSize=" + pSize +
                ", pState=" + pState +
                ", pinSize=" + pMinSize +
                '}';
    }
}

public class DynamicPartitionTest {

    public static void main(String[] args) {
        DynamicPartitionTest dpt = new DynamicPartitionTest();
        //1、初始化内存(用户指定分区的大小)
        Scanner scanner = new Scanner(System.in);
        System.out.print("请你指定内存的大小(单位KB)：mSize = ");
        int mSize = scanner.nextInt();
        LinkedList<Partition> partitions = new LinkedList<>();
        partitions.add(new Partition(0, mSize));
        //2、进入动态分区系统进行相关操作
        dpt.show(partitions);
    }

    //循环打印系统的功能
    public void show(LinkedList<Partition> partitions) {
        boolean isFlag = true;//循环打印
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("**************动态分区系统**************");
            System.out.println("\t\t\t1.分区分配");
            System.out.println("\t\t\t2.分区回收");
            System.out.println("\t\t\t3.分区显示");
            System.out.println("\t\t\t0.退出系统");
            System.out.println("***************************************");
            System.out.print("请输入你想使用的功能 : ");
            String f = scanner.next();
            switch (f) {
                case "1":
                    distribution(partitions);
                    break;
                case "2":
                    recovery(partitions);
                    break;
                case "3":
                    showPartition(partitions);
                    break;
                case "0":
                    boolean isFlag1 = true;//如果用户输入有问题，就给出提示并提醒用户重新输入
                    while (isFlag1) {
                        System.out.print("是否要退出（y/n）:");
                        String yn = scanner.next();
                        if ("y".equals(yn)) {
                            isFlag = false;      //"y"退出系统
                            isFlag1 = false;     //用户输入没问题，就不再提示‘是否要退出（y/n）’
                        } else if ("n".equals(yn)) {
                            isFlag = true;      //"n"继续打印功能表
                            isFlag1 = false;    //用户输入没问题，就不再提示‘是否要退出（y/n）’
                        } else {
                            System.out.println("输入非法，请重新输入！");
                            System.out.println("***********************************");
                        }
                    }
                    break;
            }
        } while (isFlag);
    }

    //分区匹配
    public void distribution(LinkedList<Partition> partitions) {
        boolean flag = true; //用户输入有问题就提示用户重新输入
        boolean flag1;       //询问用户是否继续添加作业 （true 继续添加，false 退出）
        do {
            Scanner scanner = new Scanner(System.in);
            System.out.println("请选择动态分区存储管理算法:");
            System.out.println("1.首次适应算法(First Fit，FF)");
            System.out.println("2.最佳适应算法(Best Fit，BF)");
            System.out.print("你的选择是: ");
            String i = scanner.next();
            switch (i) {
                case "1":
                    do {
                        FF(partitions);
                        System.out.print("是否继续添加作业?(y/n): ");
                        String yn = scanner.next();
                        //通过三元运算赋值
                        flag1 = "y".equals(yn) ? true : false;
                    } while (flag1);
                    flag = false;
                    break;
                case "2":
                    do {
                        BF(partitions);
                        System.out.print("是否继续添加作业?(y/n): ");
                        String yn = scanner.next();
                        flag1 = "y".equals(yn) ? true : false;
                    } while (flag1);
                    flag = false;
                    break;
                default:
                    System.out.println("输入非法，请重新输入！");
                    System.out.println("***********************************");
            }
        } while (flag);
    }

    //分区回收
    public void recovery(LinkedList<Partition> partitions) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("***************分区回收***************");
        System.out.print("请输入你想回收的分区号：PID = ");
        int PID = scanner.nextInt();
        //回收会遇见的情况:
        //情况一：要回收的分区不是第一个，且它上一个分区是空闲的
        boolean flag1 = PID != 0 && partitions.get(PID - 1).ispState();
        //情况二：要回收的分区不是最后一个，且它下一个分区是空闲的
        boolean flag2 = PID != partitions.size()-1 && partitions.get(PID + 1).ispState();
        //情况三：要回收的分区根本不存在
        boolean flag3 = PID >= partitions.size() || PID < 0;
        //情况四：要回收的分区的状态是空闲的，不需要回收
        boolean flag4 = partitions.get(PID).ispState();

        if (flag3) {
            System.out.println("没有此分区号！");
        } else if (flag4) {
            System.out.println("此分区状态为 ‘空闲’ ，无需回收！");
        } else if (flag1) {
            /*
            * 要回收的分区不是第一个，且它上一个分区是空闲的
            * 要将回收区和它上一个合并
            * 合并思路：上一个空闲的分区的分区大小 = 上一个空闲的分区的分区大小 + 回收的分区的分区大小，
            *          然后将回收区的链表删除（ partitions.remove(PID) ）
            * */
            partitions.get(PID - 1).setpSize(partitions.get(PID - 1).getpSize() + partitions.get(PID).getpSize());
            partitions.remove(PID);
            PID--;  //通过上面合并后，分区号减一
            if (flag2) {
                /*
                 * 回收区和上一个合并后形成一个空闲分区，继续判断这个空闲分区的下一个分区是否也是空闲的
                 * 如果是就把下面的也合并了
                 * 合并思路：下面空闲的分区的开始地址 = 它上面的空闲区的开始地址
                 *          下面空闲的分区的分区大小 = 下面空闲的分区的分区大小 + 它上面的空闲区的分区大小
                 *          然后将上面的空闲分区所在的链表删除（ partitions.remove(PID) ）
                 * */
                partitions.get(PID + 1).setpStart(partitions.get(PID).getpStart());
                partitions.get(PID + 1).setpSize(partitions.get(PID).getpSize() + partitions.get(PID + 1).getpSize());
                partitions.remove(PID);
            }
        } else if (flag2) {
            /*
             * 要回收的分区是第一个，但是它下面的分区是空闲的 或者 要回收的分区不是第一个，但是它上面的分区是繁忙的，下面的分区是空闲的
             * 要将回收区和它下面的分区一个合并
             * 合并思路：下面空闲的分区的开始地址 = 它上面的空闲区的开始地址
             *          下面空闲的分区的分区大小 = 下面空闲的分区的分区大小 + 它上面的空闲区的分区大小
             *          然后将上面的空闲分区所在的链表删除（ partitions.remove(PID) ）
             * */
            partitions.get(PID + 1).setpStart(partitions.get(PID).getpStart());
            partitions.get(PID + 1).setpSize(partitions.get(PID).getpSize() + partitions.get(PID + 1).getpSize());
            partitions.remove(PID);
        } else {
            /*
            * 要回收的分区是第一个，回收分区的下一个分区繁忙的
            * 要回收的分区是最后一个，回收分区的上一个分区繁忙的
            * 要回收的分区不是第一个也不是最后一个，回收分区的上一个和下一个分区都是繁忙的
            * 这些都是不需要合并的
            * 只需要将要回收的分区的状态由繁忙设为空闲就好（这样程序就模拟了回收分区）
            * */
            partitions.get(PID).setpState(true);
        }
        System.out.println("分区回收成功。");
        //分区回收完成后就显示一下分区的情况
        showPartition(partitions);
    }

    //分区显示
    public void showPartition(LinkedList<Partition> partitions) {
        System.out.println("***************分区显示***************");
        System.out.println("------------------------------------");
        System.out.println("分区号\t分区大小(KB)\t分区始址(K)\t状态");
        System.out.println("------------------------------------");
        for (int i = 0; i < partitions.size(); i++) {
            Partition partition = partitions.get(i);
            System.out.println("  " + i + "\t\t\t" + partition.getpSize() + "\t\t\t" +
                    partition.getpStart() + "\t\t" + (partition.ispState() ? "空闲" : "繁忙"));  //三元运算 true 空闲 ：false 繁忙
        }
        System.out.println("------------------------------------");
    }

    //首次适应算法
    public void FF(LinkedList<Partition> partitions) {
        boolean isFlag = false;  //用于判断是否分区了，如果没有就说明空间不够

        Scanner scanner = new Scanner(System.in);
        /*
        * 这个其实没啥用，让用户输着玩的，(实验上说要输入作业号，所以就显示一下)
        * 分区号是由循环打印，分区是由双向链表实现的，也没必要给每个节点在指定一个id
        * */
        System.out.print("请输入作业号：JCBId = ");
        int JCBId = scanner.nextInt();
        System.out.print("请输入作业大小: JCBSize = ");
        int JCBSize = scanner.nextInt();

        /*
        * 首次适应算法：循环链表，每次从头开始找合适分区（分区是空闲的，并且分区的大小要大于等于要存放作业的大小）
        * 分配过程如下:
        * 如果空闲分区大小 - 要存放作业的大小 <= 最小的不可再分的大小（提前指定过了）,
        *     就将整个空闲分区直接给它了
        * 如果不是，就要划分了，将这个空闲分区分成两部分
        *     用链表实现：new一个新的空闲分区节点并赋上值，新节点的开始地址 = 空闲链的开始地址 + 要存放作业的大小， 大小 = 空闲链的大小 - 要存放作业的大小，状态为空闲
        *     例：       然后将原本的空闲链的大小设为要存放作业的大小， 状态设为繁忙
        *                最后将那个新的空闲分区节点链到它的后面
        *                            ————————————
        *     1、   ...... <————>   |    空闲     | <————>.......
        *                            ————————————
        *
        *                            ——    ———————
        *     2、                   |忙 | |  空闲  |
        *                            ——    ———————
        *
        *                            ——           ———————
        *     3、    ...... <————>  |忙 | <————> |  空闲  |  <————>.......
        *                            ——           ———————
        * */
        for (int index = 0; index < partitions.size(); index++) {
            isFlag = false;
            Partition partition = partitions.get(index);
            if (partition.ispState() && partition.getpSize() >= JCBSize) {
                if (partition.getpSize() - JCBSize <= partition.pMinSize) {
                    partition.setpState(false);
                } else {
                    Partition newP = new Partition(partition.getpStart() + JCBSize, partition.getpSize() - JCBSize);
                    partitions.add(index + 1, newP);
                    partition.setpSize(JCBSize);
                    partition.setpState(false);
                }
                System.out.println("作业" + JCBId + "成功分配, 分配了" + JCBSize + "KB 内存!");
                System.out.println("------------------------------------");
                isFlag = true;
                break;
            }
        }
        if (!isFlag) {
            System.out.println("无可用内存空间!");
        }
    }

    //最佳适应算法
    public void BF(LinkedList<Partition> partitions) {
        boolean isFlag = false; //用于判断是否分区了，如果没有就说明空间不够

        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入作业号：JCBId = ");
        int JCBId = scanner.nextInt();
        System.out.print("请输入作业大小: JCBSize = ");
        int JCBSize = scanner.nextInt();

        //1、找到第一个的空闲分区节点的位置，给 minSize 和 minIndex 赋值用的
        int minSize = 0;
        int minIndex = 0;
        for (int index = 0; index < partitions.size(); index++) {
            if (partitions.get(index).ispState()){
                minSize = partitions.get(index).getpSize();
                minIndex = index;
                break;
            }
        }

        //2、找到合适大小的最小的空闲分区节点的位置
        for (int index = 0; index < partitions.size(); index++) {
            Partition partition = partitions.get(index);
            if (partition.ispState() && partition.getpSize() >= JCBSize) {
                if (minSize > partition.getpSize()) {
                    minSize = partition.getpSize();
                    minIndex = index;
                }
            }
        }

        //3、将作业分配到这个合适大小最小的空闲分区节点里（分配过程和FF的分配过程差不多，就不再赘述了）
        if (partitions.get(minIndex).ispState() && partitions.get(minIndex).getpSize() >= JCBSize) {
            isFlag = true;
            if (partitions.get(minIndex).getpSize() - JCBSize <= partitions.get(minIndex).pMinSize) {
                partitions.get(minIndex).setpState(false);
            } else {
                Partition newP = new Partition(partitions.get(minIndex).getpStart() + JCBSize, partitions.get(minIndex).getpSize() - JCBSize);
                partitions.add(minIndex + 1, newP);
                partitions.get(minIndex).setpSize(JCBSize);
                partitions.get(minIndex).setpState(false);
            }
            System.out.println("作业" + JCBId + "成功分配, 分配了" + JCBSize + "KB 内存!");
            System.out.println("------------------------------------");
        }
        if (!isFlag) {
            System.out.println("无可用内存空间!");
        }
    }
}
