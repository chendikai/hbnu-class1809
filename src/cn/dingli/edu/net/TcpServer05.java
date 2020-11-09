package cn.dingli.edu.net;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 将对象序列化，并通过网络进行传输
 *
 * @author 陈迪凯
 * @date 2020-11-09 15:24
 */
public class TcpServer05 {
    public static void main(String[] args) {
        try {
            // 1、创建socket服务，并监听端口
            ServerSocket serverSocket = new ServerSocket(8888);

            // 2、获取socket连接对象
            Socket socket = serverSocket.accept();

            // 3、获取流对象
            // socket输入流，用于接收客户端发送过来的对象
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            // socket输出流，用于返回结果给客户端
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // 4、获取客户答发送过来的数据（对象数据）
            User user = (User) in.readObject();
            System.out.println(user.getName() + ":" + user.getAge());

            // 5、返回结果给客户端
            out.println("对象序列化成功");

            // 6、关闭资源
            socket.close();
            serverSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
