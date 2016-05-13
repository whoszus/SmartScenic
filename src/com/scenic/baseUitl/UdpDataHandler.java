package com.scenic.baseUitl;

import java.net.DatagramPacket;

/**
 * Created by linyanying on 2016/5/10.
 */
public class UdpDataHandler implements Runnable {
    private static  DatagramPacket datagramPacket;
    public UdpDataHandler(DatagramPacket datagramPacket) {
        this.datagramPacket = datagramPacket;
    }
    @Override
    public void run() {
        byte udpDataBytes [] = datagramPacket.getData();
        //校验数据
        if(checkSum(udpDataBytes)){
            String udpData = bytesToHex(udpDataBytes);  //获取udpdata并转化为字符串
//            System.out.println(udpData + "    " + System.currentTimeMillis());
//            RealTimeData realTimeData =
            System.out.println(udpData + " " + System.currentTimeMillis() );
        }else{
            System.out.println(" checksum  false ");
        }

        //

    }

    /**
     * Convert byte[] to hex string. 把字节数组转化为字符串
     * 这里我们可以将byte转换成int，然后利用Integer.toHexString(int)来转换成16进制字符串。
     * @param bytes byte[] data
     * @return hex string
     */
    public  String bytesToHex(byte[] bytes) {
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

    public int[] stringToIntArray(String string ){
        int length = string.length()/2 + 1;
        int [] intarray = new int[length];
        string.split("",2);

        return intarray;
    }



}
