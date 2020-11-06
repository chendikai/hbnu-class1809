package cn.dingli.edu.net;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 客户端发送一次数据，服务端接收一次数据
 *
 * @author 陈迪凯
 * @date 2020-11-06 14:37
 */
public class TcpServer01 {
    public static void main(String[] args) {
        try {
            // 1、建立socket服务，并监听端口
            ServerSocket serverSocket = new ServerSocket(8888);

            // 2、获取连接
            Socket socket = serverSocket.accept();  // 阻塞式方法

            // 3、获取socket服务的输入流
            InputStream in = socket.getInputStream();

            // 4、接收客户端发送过来的数据
            byte[] bytes = new byte[1024];
            int len = in.read(bytes);   // read()方法是一个阻塞式方法
            System.out.println(new String(bytes,0, len));

            // 5、关闭资源
            socket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
