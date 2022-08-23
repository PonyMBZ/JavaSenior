package com.zzuli.java1;

import com.zzuli.exer.Person;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 调用运行时类中指定的结构；属性、方法、构造器
 * @auther pony
 * @create 2022-03-24 19:08
 */
public class ReflectionTest {
    /*
    *
    * */
    @Test
    public void testField() throws ClassNotFoundException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        Class clazz = Class.forName("com.zzuli.exer.Person");

        //创建运行时类的对象
        Person p = (Person) clazz.newInstance();

        //获取指定的属性:要求运行时类中的对象声明为 public
        //开发中通常不使用
        Field id = clazz.getField("id");

        /*
        设置当前属性的值
        set(): 参数1：指明设置那个对象的属性   属性2：将此属性设置为多少
        */
        id.set(p, 1001);

        /*
        *获取但前属性值
        * get():参数1：获取哪个对象的当前属性值
        * */
        int pId = (int) id.get(p);
        System.out.println(pId);

    }
    //如何操作运行时类中指定的属性
    @Test
    public void testField1() throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        Class clazz = Class.forName("com.zzuli.exer.Person");
//创建运行时类的对象
        Person p = (Person) clazz.newInstance();

//        clazz.getDeclaredField():获取运行时类中指定变量名的属性
        Field name = clazz.getDeclaredField("name");

//      保证当前属性是可访问的
        name.setAccessible(true);

        name.set(p, "tom");

        System.out.println(name.get(p));

    }

    /*
    * 获取运行时类中指定的方法
    * */
    @Test
    public void testMethod() throws InstantiationException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException {
        Class clazz = Class.forName("com.zzuli.exer.Person");
        //创建运行时类的对象
        Person p = (Person) clazz.newInstance();

        Method show = clazz.getDeclaredMethod("show", String.class);
        show.setAccessible(true);
        Object chn = show.invoke(p, "CHN");
        System.out.println(chn);

        /*
        * 调用静态方法
        * */
        Method showDesc = clazz.getDeclaredMethod("showDesc");
        showDesc.setAccessible(true);

        Object invoke = showDesc.invoke(Person.class);
        System.out.println(invoke);
    }

    //调用运行时类指定的构造器
    @Test
    public void testConstructer() throws ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        Class clazz = Class.forName("com.zzuli.exer.Person");

        Constructor declaredConstructors = clazz.getDeclaredConstructor(String.class);
        declaredConstructors.setAccessible(true);
        Person p = (Person) declaredConstructors.newInstance("Tom");
        System.out.println(p);
    }

}
