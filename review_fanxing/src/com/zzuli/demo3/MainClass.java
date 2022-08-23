package com.zzuli.demo3;

/**
 * 泛型类子类
 * @auther pony
 * @create 2022-04-24 18:41
 */
public class MainClass {
    public static void main(String[] args) {
        //公司一,年终奖喜欢抽物品
        ProductGetter<String> stringProductGetter = new ProductGetter<>();
        String[] strings = {"手机", "照相机", "彩电", "冰箱"};
        for (int i = 0; i < strings.length; i++) {
            stringProductGetter.addProduct(strings[i]);
        }

        //抽奖
        String product1 = stringProductGetter.getProduct();
        System.out.println("恭喜你抽到了：" + product1);
        System.out.println("***************************************************************");
        //公司二,年终奖喜欢抽现金
        ProductGetter<Integer> integerProductGetter = new ProductGetter<>();
        int[] ints = {100, 1000, 3000, 10000};
        for (int i = 0; i < strings.length; i++) {
            integerProductGetter.addProduct(ints[i]);
        }

        //抽奖
        int product2 = integerProductGetter.getProduct();
        System.out.println("恭喜你抽到了：" + product2 + "元现金！");
    }
}
