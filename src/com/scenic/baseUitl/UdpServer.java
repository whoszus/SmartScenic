package com.scenic.baseUitl;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * Created by Zus on 5/9/16.
 */
@Service
public class UdpServer implements InitializingBean {
//static {
//    UdpServer s=new  UdpServer();
//    try {
//        s.afterPropertiesSet();
//    } catch (Exception e) {
//        e.printStackTrace();
//    }
//}
//    public static void main(String[] args)throws IOException {
//        byte[] buf = new byte[14];
//        DatagramSocket ds = new DatagramSocket(10008);
//        DatagramPacket dp_receive = new DatagramPacket(buf, 14);
//        System.out.println("server is on，waiting for client to send data......");
//        boolean f = true;
////        RealTimeData realTimeData = new RealTimeData();
//        while(f){
//            ds.receive(dp_receive);
//            //收到udp请求，交给线程处理
////            Thread thread = new Thread(new UdpDataHandler(dp_receive));
////            thread.start();
//            Handler handler = Handler.getHandler(dp_receive);
//            handler.handUpData();
////            realTimeData = handler.handUpData(realTimeData);
////            realTimeData =
//
//
//        }
//        ds.close();
//
//    }


    @Override
    public void afterPropertiesSet() throws Exception {

        Thread thread = new Thread(new UdpThread());
        thread.start();
    }
}
