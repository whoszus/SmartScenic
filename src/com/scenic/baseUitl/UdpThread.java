package com.scenic.baseUitl;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * Created by linyanying on 2016/5/13.
 */
public class UdpThread implements Runnable {

    @Override
    public void run() {
        byte[] buf = new byte[14];
        DatagramSocket ds = null;
        try {
            ds = new DatagramSocket(10008);
        } catch (SocketException e) {
            e.printStackTrace();
        }
        DatagramPacket dp_receive = new DatagramPacket(buf, 14);
        System.out.println("server is on，waiting for client to send data......");
        boolean f = true;

        while(f){
            try {
                ds.receive(dp_receive);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //收到udp请求，交给线程处理
//            Handler handler = Handler.getHandler(dp_receive);
//            ApplicationContext applicationContext = new ClassPathXmlApplicationContext("")

//            handler.handUpData();




        }
        ds.close();

    }
}
