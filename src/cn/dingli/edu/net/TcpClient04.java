package cn.dingli.edu.net;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 文本文件上传至服务器端
 *
 * @author 陈迪凯
 * @date 2020-11-09 14:22
 */
public class TcpClient04 {
    public static void main(String[] args) {
        try {
            // 1、创建socket，并指定服务器端ip和端口
            Socket socket = new Socket("127.0.0.1", 8888);

            // 2、获取流对象
            // 文件读取流，用于读取需要上传的文件内容
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(".\\src\\jdbc.properties")));
            // socket输出流，用于将文件内容发送到服务器端
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            // socket输入流，用于获取服务器端返回的结果数据
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            // long time = System.currentTimeMillis(); // 获取时间戳
            // dos.writeLong(time);

            // 3、开始读文件内容，并将文件内容上传到服务器端
            String line = null; // 临时存储从文件中读到的一行数据
            while ((line = bufferedReader.readLine()) != null) {
                // 4、将读到的一行数据写入到服务器端
                out.println(line);
            }
            // out.println("over");
            // out.println(time + "");
            socket.shutdownOutput();

            // 5、获取服务器端返回的结果
            String result = in.readLine();
            System.out.println(result);

            // 6、关闭资源
            bufferedReader.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
