package cn.cuslink.BioDemo;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author:zhangchundong
 * @Date:Create in 14:52 2019/1/15
 * 传统BIO客户端
 */
public class ClientBio {

//   @Test
//   public void test(){
//      client();
//   }

   // 客户端
   public static void main(String[] args) {
      try {
         new Thread(new Runnable() {
            @Override
            public void run() {
               OutputStream outputStream = null;
               try {
                  // 链接上服务端
                  Socket socket = new Socket("127.0.0.1", 8008);
                  while (true) {
                     try {
                        outputStream = socket.getOutputStream();
                        outputStream.write(("hello," +
                                new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date())+
                                Thread.currentThread()).getBytes());
                        outputStream.flush();
                        // 每一秒向服务端发送消息
                        Thread.sleep(1000);
                     }catch (Exception er){
                        er.printStackTrace();
                     }
                  }
               }catch (Exception io){
                  io.printStackTrace();
               }finally {// 释放空间
                  if(null != outputStream){
                     try {
                        outputStream.close();
                     } catch (IOException e) {
                        e.printStackTrace();
                     }
                  }
               }
            }
         }).start();
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
}
