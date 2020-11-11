package cn.dingli.edu.net;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

/**
 * 图片并发上传客户端
 *
 * @author 陈迪凯
 * @date 2020-11-11 16:25
 */
public class TcpClient07 {
    public static void main(String[] args) {
        try {

            if (args.length != 1) {
                System.out.println("参数有问题，只能传一个参数");
                return;
            }

            File file = new File(args[0]);

            if (!(file.exists() && file.isFile())) {
                System.out.println("你小子传进来的不是文件，或者文件不存在");
                return;
            }

            if (!(file.getName().endsWith(".jpg"))) {
                System.out.println("图片格式错误，支支持.jpg格式的图片");
                return;
            }

            if (file.length() > 1024 * 1024 * 5) {
                System.out.println("你小子没安好心，图片文件过大");
                return;
            }

            // 1、创建socket服务，并指定服务器端的ip和端口port
            Socket socket = new Socket("127.0.0.1", 6666);

            // 2、获取流对象
            // 文件读取流，用于获取图片文件的内容
            FileInputStream inputStream = new FileInputStream(file);
            // socket输出流，用于向服务器端发送数据
            OutputStream out = socket.getOutputStream();
            // socket输入流，用于接收服务器端返回的数据
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));


            // 3、读取文件内容，并将文件内容发送给服务器端
            byte[] buf = new byte[1024];
            int len = 0;
            while ((len = inputStream.read(buf)) != -1) {
                // 4、将图片文件的内容发送给服务器端
                out.write(buf, 0, len);
            }

            socket.shutdownOutput(); // 发送结束标记

            // 5、接收服务器端返回的结果
            String result = in.readLine();
            System.out.println(result);

            // 6、关闭资源
            inputStream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
