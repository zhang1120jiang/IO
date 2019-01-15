package cn.cuslink.NioDemo;

import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Author:zhangchundong
 * @Date:Create in 19:34 2019/1/15
 * 这里模拟存入buffer中
 */
// NIO 写文件
public class WriteFile {

   public static byte arr[] ={1,2,3,4,5,6,7,8,9,10};
   //   1.从FileOutputStream获取Channel
   //   2.创建Buffer并且把数据放到Buffer中
   //   3.将Buffer中的数据写入Channel
   public static void main(String[] args) throws Exception{
      //   1.从FileOutputStream获取Channel
      FileOutputStream outputStream = new FileOutputStream("C:\\Users\\dong\\Desktop\\NIOWrite.txt");
      FileChannel channel = outputStream.getChannel();
      //   2.创建Buffer并且把数据放到Buffer中
      ByteBuffer buffer = ByteBuffer.allocate(1024);
      // 模拟数据存入buffer中
      for (byte data : arr ) {
         buffer.put(data);
      }
      // 重设buffer，将limit设置为position，position设置为0
      buffer.flip();
      //   3.将Buffer中的数据写入Channel
      int write = channel.write(buffer);
      System.out.println("write："+write);
      // 释放资源
      outputStream.close();
   }
}
