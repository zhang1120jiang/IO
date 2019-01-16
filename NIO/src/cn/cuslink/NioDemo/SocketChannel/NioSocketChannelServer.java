package cn.cuslink.NioDemo.SocketChannel;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.SocketChannel;

/**
 * @Author:zhangchundong
 * @Date:Create in 14:33 2019/1/16
 */

public class NioSocketChannelServer {
   public static void main(String[] args) throws IOException {
      // 1：得到ServerSocket
      ServerSocket serverSocket = new ServerSocket(8009);
      byte[] data = new byte[1024];
      while (true) {
         // 2：调用accept方法
         Socket socket = serverSocket.accept();
         // 3：的到input
         InputStream inputStream = socket.getInputStream();
         // 4：进行输出
         int len = 0;
         if(inputStream.read(data) != -1){
            len = inputStream.read(data);
            System.out.println("ServerSocket："+new String(data,0,len));
         }
         inputStream.close();
      }
   }
}
