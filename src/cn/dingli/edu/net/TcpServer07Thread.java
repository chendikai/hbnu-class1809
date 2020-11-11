package cn.dingli.edu.net;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author 陈迪凯
 * @date 2020-11-11 17:15
 */
public class TcpServer07Thread implements Runnable {
    private Socket socket;

    public TcpServer07Thread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        String ip = socket.getInetAddress().getHostAddress();
        int count = 1;
        try {
            File file = new File("E:\\DingLi\\" + ip + ".jpg");
            while (file.exists()) {
                file = new File("E:\\DingLi\\" + ip + "(" + (count++) + ").jpg");
            }

            // 3、获取流对象
            InputStream in = socket.getInputStream(); // 获取客户端数据
            FileOutputStream fos = new FileOutputStream(file); // 向文件写数据
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true); // 向客户端返回结果

            // 4、读取客户端发送过来的数据，并将数据写入文件
            byte[] buf = new byte[1024];
            int len = 0;
            while ((len = in.read(buf)) != -1) {
                // 5、向文件中写数据
                fos.write(buf, 0, len);
            }

            // 6、返回结果给客户端
            out.println(ip + "图片上传成功");

            // 7、关闭资源
            fos.close();
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(ip + "图片上传失败");
        }
    }
}
