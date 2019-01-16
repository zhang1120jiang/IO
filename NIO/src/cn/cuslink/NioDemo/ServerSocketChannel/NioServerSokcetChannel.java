package cn.cuslink.NioDemo.ServerSocketChannel;

import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Set;

/**
 * @Author:zhangchundong
 * @Date:Create in 15:31 2019/1/16
 * NIO TCP 服务端
 */

public class NioServerSokcetChannel {

   public static void main(String[] args) throws Exception{
      // 1：创建selector
      Selector selector = Selector.open();
      // 2：创建ServerSocketChannel
      ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
      // 3：绑定端口
      serverSocketChannel.socket().bind(new InetSocketAddress(8009));
      // 4：配置为非阻塞式
      serverSocketChannel.configureBlocking(false);
      // 5：将selector注册到channel中
      SelectionKey register = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
      // 6：判断是否有准备好的通道
      while (true) {
         if (selector.select() == 0) {
            System.out.println("未监控到准备好的通道");
            // 继续监控
            continue;
         }
         // 7：有准备就绪的通道
         Set<SelectionKey> selectionKeys = selector.selectedKeys();
         // 遍历得到所有的key
         for (SelectionKey key : selectionKeys) {
            // 8：根据不同key监控的不同通道
            if (key.isAcceptable()) {// 对应OP_ACCEPT
               System.out.println("处理OP_ACCEPT");

            } else if (key.isReadable()) {// 对应OP_READ
               System.out.println("处理OP_READ");

            } else if (key.isWritable()) {// 对应OP_WRITE
               System.out.println("处理OP_WRITE");

            }
            // 8：清除key,这里必须手动清除
//            必须在处理完通道时自己移除。
//            下次该通道变成就绪时，Selector会再次将其放入已选择键集中
            selectionKeys.remove(key);
         }
      }
   }
}
