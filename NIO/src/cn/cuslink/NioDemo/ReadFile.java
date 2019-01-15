package cn.cuslink.NioDemo;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Author:zhangchundong
 * @Date:Create in 19:11 2019/1/15
 * NIO的三个要素：
 * 1：Channel
 * Channel所有去任何地方（或者来自任何地方）的数据都必须通过Channel对象。
 * 可以从Channel中读取数据，也可以从Channel中写入数据。
 * Channel就如同Stream。
 * 2：Buffer
 * Buffer本质上说是一个容器对象。是一个字节数组
 * 任何发送到Channel的数据都必须先放进Buffer，
 * 任何从Channel中读出的数据都先读进Buffer。
 * 3:
 */
// NIO 读取文件
public class ReadFile {
   /**
    * 1. 从FileInputStream中获取Channel
    * 2. 创建Buffer
    * 3. 从Channel中把数据读入Buffer
    */
   public static void main(String[] args) throws Exception {
//      1. 从FileInputStream中获取Channel
      FileInputStream inputStream = new FileInputStream("C:\\Users\\dong\\Desktop\\springBoot.txt");
      FileChannel channel = inputStream.getChannel();
//      2. 创建Buffer
      ByteBuffer buffer = ByteBuffer.allocate(1024);
//      3. 从Channel中把数据读入Buffer
      int read = channel.read(buffer);
      System.out.println("read：" + read);
      // 重设buffer，将limit设置为position，position设置为0
      buffer.flip();
      while (buffer.hasRemaining()) {
         byte b = buffer.get();
         System.out.println("b：" + (char)b);
      }
      // 释放资源
      inputStream.close();
   }
}
