package com.zzuli.java;

import org.junit.Test;

/**
 * @auther pony
 * @create 2022-03-13 18:08
 */
public class DaoTest {
    @Test
    public void test1(){
        CustomerDAO dao1 = new CustomerDAO();

        dao1.add(new Customer());
        Customer index = dao1.getIndex(1);

        StudentDao dao2 = new StudentDao();
        dao2.add(new Student());
        dao2.getIndex(2);
    }
}
