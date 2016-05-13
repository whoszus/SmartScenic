package com.scenic.baseUitl;

import com.scenic.service.impl.RealTimeDataServiceImpl;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

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
        Date date = new Date();
        //每天凌晨两点执行
        if((date.getHours()==2 && date.getMinutes()>0) || (date.getHours()>2)){
            date.setDate(date.getDate()+1);
        }
        date.setHours(2);
        date.setMinutes(0);
        date.setSeconds(0);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                System.out.println("***********计时器*****************");
                System.out.println("***********计时器*****************");
                System.out.println("***********计时器*****************");
                System.out.println("***********计时器*****************");
                System.out.println("***********计时器*****************");
                System.out.println("***********计时器*****************");
                System.out.println("***********计时器*****************");
                RealTimeDataServiceImpl realTimeDataService = SpringContextUtil.getBean("realTimeDataService");
                realTimeDataService.caculatAllSpot();
            }
        }, date, 1000*60*60*24);

    }
}
