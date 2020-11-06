package cn.dingli.edu.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 客户端发送一次数据，服务端接收一次数据
 *
 * @author 陈迪凯
 * @date 2020-11-06 14:57
 */
public class TcpServer02 {
    public static void main(String[] args) {
        try {
            // 1、创建socket服务，并监听端口
            ServerSocket serverSocket = new ServerSocket(8888);

            // 2、获取连接
            Socket socket = serverSocket.accept();  // accept()方法是阻塞式方法

            // 3、获取socket的输入流和输出流
            InputStream in = socket.getInputStream(); // 用于接收客户端发送过来的数据
            OutputStream out = socket.getOutputStream(); // 用于反馈信息给客户端

            // 4、接收客户端的数据
            byte[] buf = new byte[1024];
            int len = in.read(buf);
            String data = new String(buf, 0, len);
            System.out.println("客户端发送过来的数据：" + data);

            // 5、反馈信息给客户端
            out.write("收到信息，客户端，你好".getBytes());

            // 6、关闭资源
            socket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
