package cn.dingli.edu.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * 聊天程序发送端
 *
 * @author 陈迪凯
 * @date 2020-11-06 8:54
 */
public class ChatSend implements Runnable {
    private DatagramSocket datagramSocket;

    public ChatSend(DatagramSocket datagramSocket) {
        this.datagramSocket = datagramSocket;
    }

    @Override
    public void run() {
        try {
            // 2、从键盘录入数据
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                // 3、封装数据到数据报包中
                DatagramPacket datagramPacket = new DatagramPacket(line.getBytes(), line.getBytes().length,
                        InetAddress.getByName("192.168.8.121"), 6666);

                // 4、发送数据
                datagramSocket.send(datagramPacket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 6、关闭资源
            datagramSocket.close();
        }
    }
}
