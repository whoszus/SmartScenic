package com.scenic.baseUitl;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * Created by Zus on 3/17/16.
 */
public class UDP {


    public static void main(String[] args)throws IOException {
        String str_send = "Hello UDPclient";
        byte[] buf = new byte[14];
        DatagramSocket ds = new DatagramSocket(10008);
        DatagramPacket dp_receive = new DatagramPacket(buf, 14);
        System.out.println("server is on，waiting for client to send data......");
        boolean f = true;
        while(f){
            ds.receive(dp_receive);
            System.out.println("server received data from client：");
            String str_receive = new String(dp_receive.getData(),0,dp_receive.getLength()) +
                    " from " + dp_receive.getAddress().getHostAddress() + ":" + dp_receive.getPort();
            System.out.println(str_receive);

//            String s = bytesToHex(dp_receive.getData());
//            System.out.println(s);
            String str = bytesToHexString(dp_receive.getData());
            System.out.println(str);
//            byte[] hexStringToBytes = Utils.bytes2HexString(hexString);
//            String string = Utils.bytes2HexString(dp_receive.getData());

            String string = bytesToHex(dp_receive.getData());
            System.out.println(string);

        }
        ds.close();

    }

    public static String bytesToHex(byte[] bytes) {
        final char[] hexArray = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        char[] hexChars = new char[bytes.length * 2];
        int v;
        for ( int j = 0; j < bytes.length; j++ ) {
            v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);

//        int x = 0;
//            for (int i = 0, l = Math.min(bytes.length, 4); i < l; i++) {
//            x <<= 8;
//            x |= bytes[i];
//        }
//      String hex = Integer.toString(x, 16);
//        return hex;
    }

    /* Convert byte[] to hex string.这里我们可以将byte转换成int，然后利用Integer.toHexString(int)来转换成16进制字符串。
* @param src byte[] data
* @return hex string
*/
    public static String bytes2HexString(byte[] src){
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }


    /**
     * Convert byte[] to hex string. 把字节数组转化为字符串
     * 这里我们可以将byte转换成int，然后利用Integer.toHexString(int)来转换成16进制字符串。
     * @param src byte[] data
     * @return hex string
     */
    public static String bytesToHexString(byte[] src){
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv+" ");
        }
        return stringBuilder.toString();
    }





//    }
}
