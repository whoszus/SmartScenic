package com.scenic.baseUitl;

import com.scenic.repo.interf.impl.IDetectionPointRespository;
import com.scenic.repo.interf.impl.IRealTimeDataRespository;
import com.scenic.repo.pojo.DetectionPoint;
import com.scenic.repo.pojo.RealTimeData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.DatagramPacket;
import java.util.Date;

/**
 * Created by linyanying on 2016/5/11.
 */
@Service("handler")
public class Handler {
    @Autowired
    private IRealTimeDataRespository realTimeDataRespository;
    @Autowired
    private IDetectionPointRespository detectionPointRespository;

    private  static Handler handler;
    private static RealTimeData realTimeData;

    public Handler() {
    }
//    //单例模式
//    public static Handler getHandler(DatagramPacket datagramPacket){
//        Handler.datagramPacket = datagramPacket;
//        if(handler==null){
//            handler= new Handler();
//        }
//
//    }

    public void handUpData(DatagramPacket datagramPacket){
        if(realTimeData ==null){
            realTimeData = new RealTimeData();
        }
        byte udpDataBytes [] = datagramPacket.getData();
        if(checkSum(udpDataBytes)){
            String udpStringData = bytesToHex(udpDataBytes);  //获取udpdata并转化为字符串
            //把数据转化为float类型数组
//            System.out.println(udpStringData + "    " + System.currentTimeMillis());

            float [] fdata = hexStringTointArray(udpStringData);
            //判断是哪部分的数据，true代表前端，false代表后段
            /*
            hex = AA &&  int = 170
            hex = 55 &&  int = 85   头部
            hex = 0B &&  int = 11   长度
            hex = 11 &&  int = 17   主节点编号
            hex = 00 &&  int = 0    子节点编号
            hex = 00 &&  int = 0    紫外线整数
            hex = 00 &&  int = 0    紫外线小叔
            hex = 03 &&  int = 3    灰尘高位
            hex = 84 &&  int = 132  灰尘低位
            hex = 00 &&  int = 0    风速整数
            hex = 00 &&  int = 0    风速小数
            hex = 1A &&  int = 26   温度值
            hex = 21 &&  int = 33   湿度值
            hex = DE &&  int = 222  校验和

             */
            /*
            j = 0 hex = AA &&  int = 170
            j = 1 hex = 5B &&  int = 91  尾部
            j = 2 hex = 0B &&  int = 11
            j = 3 hex = 11 &&  int = 17 主节点编号
            j = 4 hex = 00 &&  int = 0  子节点
            j = 5 hex = 00 &&  int = 0  SO2 高位
            j = 6 hex = 09 &&  int = 9  SO2 低位
            j = 7 hex = 00 &&  int = 0  CO 高位
            j = 8 hex = 05 &&  int = 5  低位
            j = 9 hex = 00 &&  int = 0  O3高位
            j = 10 hex = 2C &&  int = 44   低位
            j = 11 hex = 00 &&  int = 0  NO2 高位
            j = 12 hex = 00 &&  int = 0  NO2 低位
            j = 13 hex = 56 &&  int = 86 校验和
             */
            if(isHead(fdata[1])){
                int point = (int) (Math.random()*10);
                if(point==0){
                    point = 9;
                }
                DetectionPoint detectionPoint =  detectionPointRespository.findOne(point);
                realTimeData.setDetectionPoint(detectionPoint);
                realTimeData.setRtdAirSpeed(fdata[9]+fdata[10]/10); //风速
                realTimeData.setRtdTemperature(fdata[11]); //温度
                realTimeData.setRtdHumidity(fdata[12]); //湿度
//                realTimeData.setRtdUltraviolet(fdata[5]); //紫外线无小数部分
                realTimeData.setRtdPm10((fdata[5]*256+fdata[6])/10);
                realTimeData.setRtdPm25((fdata[7]*256+fdata[8])/10);
                realTimeData.setRtdTime(new Date());
                float salt = (float) Math.random();
                realTimeData.setRtdNo2(3+salt);
            }else{
                realTimeData.setRtdSo2(fdata[5]*256+fdata[6]);
                realTimeData.setRtdCo((fdata[7]*256 + fdata[8])/10);
                realTimeData.setRtdO3(fdata[9]*256+fdata[10]);
                realTimeData.setRtdNo2(fdata[11]*256 +fdata[12]);
            }
            if(isComplete(realTimeData)){
                //持久化pojo
                System.out.println(realTimeData);
//                System.out.println("  " + realTimeData.getRtdPm25()  + " " );
                realTimeDataRespository.save(realTimeData);
                realTimeData = null;
            }

//            System.out.println("fasdf");

        }else{
            System.out.println(" checksum  false ");
        }

//        return realTimeData;
    }



    public String bytesToHex(byte[] bytes) {
        final char[] hexArray = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        char[] hexChars = new char[bytes.length * 2];
        int v;
        for ( int j = 0; j < bytes.length; j++ ) {
            v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

    public boolean checkSum(byte[] bytes){
        return true;
    }


    public boolean isComplete(RealTimeData realTimeData){
        if(realTimeData.getRtdTime()!=null){
            if(realTimeData.getRtdO3()!=null){
                return true;
            }
        }

        return false;
    }

    /**
     * 把十六进制的String转化为int型数组
     * @param s 十六进制string
     * @return int 数组
     */
    public float [] hexStringTointArray(String s){
        int lenth = s.length()/2;
        float[] ary = new float[lenth];

        for (int i = 0,j=0 ;i<s.length();i=i+2,j++){
            String sub = s.substring(i,i+2);
//            System.out.println("j = " + j + " hex = " + sub + " &&  int = "+Integer.parseInt(sub,16));
            ary[j] =Integer.parseInt(sub,16);
        }
        return ary;
    }

    /**
     * 判断是哪部分的数据，true代表前端，false代表后段
     * @param reg
     * @return boolean
     */
    public boolean isHead(float reg ){
        if (reg == 85.0){
            return true;
        }else {
            return false;
        }
    }


}
