package cn.dingli.edu.net;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 图片上传服务端
 *
 * @author 陈迪凯
 * @date 2020-11-11 16:34
 */
public class TcpServer06 {
    public static void main(String[] args) {
        try {
            // 1、创建socket服务，并监听端口
            ServerSocket serverSocket = new ServerSocket(9999);

            // 2、获取socket连接对象
            Socket socket = serverSocket.accept();

            // 3、获取流对象
            // socket输入流，用于接收客户端发送过来的数据
            InputStream in = socket.getInputStream();
            // 文件输出流，用于将接收到的数据写入文件中
            FileOutputStream fos = new FileOutputStream("E:\\DingLi\\1809.jpg");
            // socket输出流，用于返回结果给客户端
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // 4、读取客户端发送过来的数据，并将数据写入到文件中
            byte[] buf = new byte[1024];
            int len = 0;
            while ((len = in.read(buf)) != -1) {
                // 5、将数据写入文件
                fos.write(buf, 0, len);
            }

            // 6、向客户端反馈结果
            out.println("图片上传成功");

            // 7、关闭资源
            fos.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
