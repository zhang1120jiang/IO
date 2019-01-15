package cn.cuslink.BioDemo;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author:zhangchundong
 * @Date:Create in 14:51 2019/1/15
 * 传统BIO的服务端
 * 每隔一秒接收客户端发送的数据并打印
 */
public class ServerBio {
//   @Test
//   public  void  test(){
//      Server();
//   }
   /**
    * 服务端
    */
   public static void main(String[] args) {
      try {
         // 创建socket服务端
         ServerSocket socket = new ServerSocket(8008);
         new Thread(new Runnable() {
            @Override
            public void run() {
               while (true) {
                  try{
                     // 获得链接
                     Socket accept =  socket.accept();
                     // 匿名内部类创建线程
                     new Thread(new Runnable(){
                        @Override
                        public void run() {
                           InputStream inputStream = null;
                           try {
                              inputStream = accept.getInputStream();
                              byte[] bytes = new byte[1024];
                              int len = 0;
                              while (len != -1) {
                                 len = inputStream.read(bytes);
                                 String str = new String(bytes, 0, len);
                                 System.out.println(str);
                              }
                           }catch (IOException io){
                              io.printStackTrace();
                           }finally {// 释放空间
                              if(null != inputStream){
                                 try {
                                    inputStream.close();
                                 } catch (IOException e) {
                                    e.printStackTrace();
                                 }
                              }
                           }
                        }
                     }).start();
                  } catch (IOException e) {
                     e.printStackTrace();
                  }
               }
            }
         }).start();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
}
