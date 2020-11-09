package cn.dingli.edu.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * 将对象序列化，并通过网络进行传输
 *
 * @author 陈迪凯
 * @date 2020-11-09 15:17
 */
public class TcpClient05 {
    public static void main(String[] args) {
        try {
            // 1、创建socket服务，指定服务器端的ip和端口
            Socket socket = new Socket("127.0.0.1", 8888);

            // 2、获取流对象
            // socket输出流，用于将序列化后的对象发送给服务器端
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            // socket输入流，用于接收服务器端返回的结果
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // 3、发送序列化后的对象至服务器端
            User user = new User();
            user.setName("jixin1809");
            user.setAge(18);
            out.writeObject(user);

            // 4、接收服务器端返回的结果
            String result = in.readLine();
            System.out.println(result);

            // 5、关闭资源
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
