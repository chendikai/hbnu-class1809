package cn.dingli.edu.net;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * 客户端发送一次数据，服务端接收一次数据
 *
 * @author 陈迪凯
 * @date 2020-11-06 14:29
 */
public class TcpClient01 {
    public static void main(String[] args) {
        try {
            // 1、建立socket服务，指定服务器端的IP和端口port
            Socket socket = new Socket("127.0.0.1", 8888);

            // 2、获取socket的输出流
            OutputStream out = socket.getOutputStream(); //  向服务器端发送数据的输出流

            // 3、准备数据，发送给服务器端
            byte[] buf = "hbnu jixin 1809".getBytes();
            out.write(buf);

            // 4、关闭资源
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
