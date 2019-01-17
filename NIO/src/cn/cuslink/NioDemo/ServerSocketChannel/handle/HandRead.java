package cn.cuslink.NioDemo.ServerSocketChannel.handle;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

/**
 * @Author:zhangchundong
 * @Date:Create in 10:26 2019/1/17
 * 处理NIO中的OP_READ事件
 */

public class HandRead {

   public static void handRead(SelectionKey key) throws Exception {
      // 拿到监听的通道
      SocketChannel channel = (SocketChannel) key.channel();
      // 拿到附加对象
      ByteBuffer byteBuffer = (ByteBuffer) key.attachment();
      // 将通道的数据写入缓存
      channel.read(byteBuffer);
      // 重置缓存中的位置
      byteBuffer.flip();
      System.out.println("拿到的数据：" + Charset.forName("UTF-8").newDecoder().decode(byteBuffer).toString());
      // 清空缓存
      byteBuffer.clear();
      // 写回给客户端
      byteBuffer.put("服务器给客户端".getBytes());
      byteBuffer.flip();
      // 写回给客户端
      channel.write(byteBuffer);
      byteBuffer.clear();
   }
}
