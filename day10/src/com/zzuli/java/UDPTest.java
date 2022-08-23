package com.zzuli.java;

import org.junit.Test;

import java.io.IOException;
import java.net.*;

/**
 * @auther pony
 * @create 2022-03-20 17:38
 */
public class UDPTest {

    //发送端
    @Test
    public void sender() throws IOException {

        DatagramSocket socket = new DatagramSocket();

        String str = "我是 UDP 方式发送的导弹";
        byte[] data = str.getBytes();
        InetAddress inet = InetAddress.getLocalHost();
        DatagramPacket packet = new DatagramPacket(data, 0, data.length, inet, 8899);

        socket.send(packet);

        socket.close();
    }


    //接收端
    @Test
    public void receiver() throws IOException {

        DatagramSocket socket = new DatagramSocket(8899);

        byte[] bytes = new byte[100];

        DatagramPacket packet = new DatagramPacket(bytes, 0,bytes.length);

        socket.receive(packet);

        System.out.println(new String(packet.getData(), 0,packet.getLength()));

        socket.close();
    }
}
