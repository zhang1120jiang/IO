package cn.cuslink;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.junit.Test;

/**
 * @Author:zhangchundong
 * @Date:Create in 16:36 2019/1/17
 * 使用之前定义好的协议
 */

public class UseProtocol {

   // NioEventLoopGroup 是用来处理I/O操作的多线程事件循环器
   // 不同的EventLoopGroup的实现用来处理不同传输协议
   public void use() throws Exception {
      // 创建两组EventLoopGroup，当接收到连接，就会把连接信息注册到处理请求上
      EventLoopGroup parent = new NioEventLoopGroup();// 用于接收请求
      EventLoopGroup child = new NioEventLoopGroup();  // 用于处理请求
      // 使用channel
      ServerBootstrap bootstrap = new ServerBootstrap();
      // 设置组
      bootstrap.group(parent, child);
      // 接收新连接
      bootstrap.channel(NioServerSocketChannel.class);
      // 处理一个最近的已经接收的Channel
      bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
         @Override
         protected void initChannel(SocketChannel socketChannel) throws Exception {
            // 将之前定义的规则放进来
            socketChannel.pipeline().addLast(new NettyDemo());
         }
      });
      // 设置参数
      bootstrap.option(ChannelOption.SO_BACKLOG, 128);
      // option() 是提供给NioServerSocketChannel用来接收进来的连接。
      // childOption() 是提供给由父管道ServerChannel接收到的连接，
      bootstrap.childOption(ChannelOption.SO_KEEPALIVE,true);
      // 绑定端口
      ChannelFuture sync = bootstrap.bind(8008).sync();
      /**
       * 这里会一直等待，直到socket被关闭
       */
      sync.channel().closeFuture().sync();
   }
   @Test
   public void test(){
      try {
         use();
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
}
