package cn.dingli.edu.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * 客户端和服务器端进行数据交互
 *
 * @author 陈迪凯
 * @date 2020-11-06 14:50
 */
public class TcpClient02 {
    public static void main(String[] args) {
        try {
            // 1、建立socket服务，并指定服务器端的ip和端口
            Socket socket = new Socket("127.0.0.1", 8888);

            // 2、获取socket的输入流和输出流
            OutputStream out = socket.getOutputStream(); // 用于给服务器端发送数据
            InputStream in = socket.getInputStream(); // 用于接收服务器端返回的数据

            // 3、准备数据，发送给服务器端
            out.write("服务器，你好！！！".getBytes());

            // 4、接收服务器端返回的数据
            byte[] buf = new byte[1024];
            int len = in.read(buf);
            String result = new String(buf, 0, len); // 服务器端返回的数据
            System.out.println("服务器端返回的数据：" + result);

            // 5、关闭资源
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
