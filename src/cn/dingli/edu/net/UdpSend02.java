package cn.dingli.edu.net;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * 从键盘录入数据，发送给接收端
 *
 * @author 陈迪凯
 * @date 2020-11-06 8:14
 */
public class UdpSend02 {
    public static void main(String[] args) {

        DatagramSocket datagramSocket = null;
        try {
            // 1、创建udp socket服务
            datagramSocket = new DatagramSocket();

            // 2、从键盘录入数据
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                // 3、将数据封装到数据报包中
                byte[] data = line.getBytes();
                DatagramPacket datagramPacket = new DatagramPacket(data, data.length,
                        InetAddress.getByName("127.0.0.1"), 8888);

                // 4、发送数据
                datagramSocket.send(datagramPacket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 5、关闭资源
            if (datagramSocket != null) {
                datagramSocket.close();
            }
        }
    }
}
