package cn.cuslink.NioDemo.ServerSocketChannel.handle;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @Author:zhangchundong
 * @Date:Create in 10:16 2019/1/17
 * 用于处理NIO中OP_ACCEPT感兴趣的事件
 */

public class HandAccept {
   /**
    * OP_ACCEPT感兴趣的事件
    * @param key
    * @throws Exception
    */
   public static void handleAceept(SelectionKey key) throws Exception{
      System.out.println("开始处理Accept事件");
      // 拿到监听的通道
      ServerSocketChannel serversocketChannel = (ServerSocketChannel) key.channel();
      // 进行建立连接
      SocketChannel socketChannel = serversocketChannel.accept();
      // 配置为非阻塞
      socketChannel.configureBlocking(false);
      // 进行注册
      socketChannel.register(key.selector(),SelectionKey.OP_READ, ByteBuffer.allocate(1024));
   }
}
