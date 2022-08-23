package com.zzuli.utils;

import org.junit.Test;

import java.sql.Connection;

import static org.junit.Assert.*;

/**
 * @auther pony
 * @create 2022-05-02 21:15
 */
public class JDBCUtilsTest {

    @Test
    public void getConnection2() throws Exception {
        Connection conn = JDBCUtils.getConnection2();
        System.out.println(conn);
    }
}