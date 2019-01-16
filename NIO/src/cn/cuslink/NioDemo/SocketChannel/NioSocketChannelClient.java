package cn.cuslink.NioDemo.SocketChannel;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.concurrent.TimeUnit;

/**
 * @Author:zhangchundong
 * @Date:Create in 14:32 2019/1/16
 * Client
 */

public class NioSocketChannelClient
{
   public static void main(String[] args)  throws Exception{
      // 1：打开socketChannel通道
      SocketChannel socketChannel = SocketChannel.open();
      // 2：配置为非阻塞式
      socketChannel.configureBlocking(false);
      // 3：连接服务端
      socketChannel.bind(new InetSocketAddress("127.0.0.1",8009));
      // 4：得到buffer
      ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
      if(socketChannel.finishConnect()){
         while (true){
            // 清buffer的缓存
            byteBuffer.clear();
            // 往buffer中写数据
            byteBuffer.put(("这是socketChannel").getBytes());
            // 重置position
            byteBuffer.flip();
            // 从buffer中写入通道
            while (byteBuffer.hasRemaining()){
               System.out.println("buffer："+byteBuffer);
               // write()方法无法保证能写多少字节到SocketChannel。
               // 我们重复调用write()直到Buffer没有要写的字节为止。
               socketChannel.write(byteBuffer);
            }
         }
      }
      socketChannel.close();
   }
}
