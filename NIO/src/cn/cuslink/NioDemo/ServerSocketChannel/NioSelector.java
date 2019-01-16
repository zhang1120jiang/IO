package cn.cuslink.NioDemo.ServerSocketChannel;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Set;

/**
 * @Author:zhangchundong
 * @Date:Create in 21:20 2019/1/15
 * 非阻塞式的核心：selector
 *    作用：注册各种I/O事件地方，而且当那些事件发生时，就是这个对象告诉我们所发生的事件
 *          读或写等任何注册的事件发生时，可以从Selector中获得相应的SelectionKey，
 *          同时从 SelectionKey中可以找到发生的事件和该事件所发生的具体的SelectableChannel，
 *          以获得客户端发送过来的数据
 *
 *          1. 向Selector对象注册感兴趣的事件
 *          2. 从Selector中获取感兴趣的事件
 *          3. 根据不同的事件进行相应的处理
 */

public class NioSelector {
   /**
    * 注册事件
    */
   public Selector registEvent(){
      Selector selector = null;
      try {
         // 创建Selector对象
         selector = Selector.open();
         // 创建可选择通道，并配置为非阻塞模式
         ServerSocketChannel socketChannel = ServerSocketChannel.open();
         socketChannel.configureBlocking(false);
         // 绑定通道到指定端口
         ServerSocket serverSocket = socketChannel.socket();
         SocketAddress address = new InetSocketAddress(8888);
         serverSocket.bind(address);
         // 向Selector中注册感兴趣的事件
         socketChannel.register(selector, SelectionKey.OP_ACCEPT);
         // OP_ACCEPT，即指定我们想要监听accept事件，也就是新的连接发生时所产生的事件，
         // 对于ServerSocketChannel通道来说，我们唯一可以指定的参数就是OP_ACCEPT。
      }catch (Exception e){
         e.printStackTrace();
      }
      return selector;
   }

   /**
    * 监听
    * 从Selector中获取感兴趣的事件
    */
   public void listener(){
      try {
         while (true){
            // 获得Selector
            Selector selector = Selector.open();
            // 拿到selector中的selectionKey
            Set<SelectionKey> keys = selector.selectedKeys();
            // 遍历该key

         }
      }catch (Exception e){
         e.printStackTrace();
      }
   }

   /**
    * 事件处理
    */
//   public void handEvent(SelectionKey key){
//      try {
//         if (key.isAcceptable()) { // 处理请求
//            ServerSocketChannel server = (ServerSocketChannel) key.channel();
//            SocketChannel accept = server.accept();
//            accept.configureBlocking(false);
//            accept.register(selector,SelectionKey.OP_READ);
//         } else if (key.isReadable()) { // 读
//            SocketChannel channel = (SocketChannel) key.channel();
//            int count = channel.read(buffer);
//            if (count > 0) {
//               buffer.flip();
//               CharBuffer charBuffer = decoder.decode(buffer);
//               name = charBuffer.toString();
//               SelectionKey sKey = channel.register(selector, SelectionKey.OP_WRITE);
//               sKey.attach(name);
//            } else {
//               channel.close();
//            }
//            buffer.clear();
//         } else if (key.isWritable()) { // 写
//            SocketChannel channel = (SocketChannel) key.channel();
//            String name = (String) key.attachment();
//            ByteBuffer block = encoder.encode(CharBuffer.wrap("Hello " + name));
//            if(block != null)
//            {
//               channel.write(block);
//            }
//            else
//            {
//               channel.close();
//            }
//         }
//      }catch (Exception e){
//         e.printStackTrace();
//      }
//   }
}
