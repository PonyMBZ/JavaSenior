package com.zzuli.blob;

import com.zzuli.bean.Customers;
import com.zzuli.util.JDBCUtils;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.*;

/**
 * @auther pony
 * @create 2022-04-14 17:33
 */
public class BlobTest {
//    测试使用preparedStatement操作blob类型的数据
    @Test
    public void testInsert() throws Exception{
        Connection conn = JDBCUtils.getConnection();
        String sql = "insert into customers(name, email, birth, photo) values(?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setObject(1, "hanser");
        ps.setObject(2, "hanser@qq.com");
        ps.setObject(3,"1992-5-15");
        FileInputStream is = new FileInputStream(new File("hanser1.jpg"));
        ps.setBlob(4, is);

        ps.execute();
        JDBCUtils.closeResource(conn,ps);

    }

    //查询数据表的customer中，blob类型的数据
    @Test
    public void testQuery() throws Exception{
        InputStream is = null;
        FileOutputStream fos = null;
        Connection conn = JDBCUtils.getConnection();
        String sql = "select id, name, email, birth, photo from customers where id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setObject(1, 26);

        ResultSet rs = ps.executeQuery();

        if (rs.next()){
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String email = rs.getString("email");
            Date birth = rs.getDate("birth");

            Customers hanser = new Customers(id, name, email, birth);
            System.out.println(hanser);

            //将blob类型的图片下载到本地(流的处理)
            Blob photo = rs.getBlob("photo");

            is = photo.getBinaryStream();
            fos = new FileOutputStream("hanser.jpg");
            byte[] buffer = new byte[1024];
            int len;
            while((len = is.read(buffer)) != -1){
                fos.write(buffer,0,len);
            }

        }

        JDBCUtils.closeResource(conn,ps,rs);
        is.close();
        fos.close();

    }
}
