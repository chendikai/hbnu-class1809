package cn.dingli.edu.net;

import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * 聊天室
 *
 * @author 陈迪凯
 * @date 2020-11-06 9:10
 */
public class Chat {
    public static void main(String[] args) {
        try {
            // 创建发送端和接收端的udp socket服务，接收端socket服务需要监听端口
            DatagramSocket sendSocket = new DatagramSocket();
            DatagramSocket receiveSocket = new DatagramSocket(6666);

            /*
            // 第一种启动线程方式：
            // 创建线程对象
            ChatSend chatSend = new ChatSend(sendSocket); // 发送端线程对象
            ChatReceive chatReceive = new ChatReceive(receiveSocket); // 接收端线程对象

            // 启动线程
            new Thread(chatSend).start(); // 启动发送端线程
            new Thread(chatReceive).start(); // 启动接收端线程
            */

            // 第二种启动线程方式
            new Thread(new ChatSend(sendSocket)).start();
            new Thread(new ChatReceive(receiveSocket)).start();
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }
}
