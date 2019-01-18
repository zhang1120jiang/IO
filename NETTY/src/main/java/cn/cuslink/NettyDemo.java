package cn.cuslink;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;

/**
 * @Author:zhangchundong
 * @Date:Create in 15:57 2019/1/17
 * 定义好接收消息的协议
 */

public class NettyDemo extends ChannelHandlerAdapter {

   /**
    * 从客户端收到新的数据时， 这个方法会在收到消息时被调用
    * @param ctx
    * @param msg
    * @throws Exception
    */
   @Override
   public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
      ByteBuf msg1 = (ByteBuf) msg;
      String string = msg1.toString(CharsetUtil.UTF_8);
      System.out.println("接收到的消息："+string);
      // 抛弃数据,这里必须手动释放
      ReferenceCountUtil.release(msg);
   }

   /**
    * 异常会触发该方法
    * @param ctx
    * @param cause
    * @throws Exception
    * 出现 Throwable 对象会被调用
    */
   @Override
   public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
      System.out.println("发生了异常");
      ctx.close();
      cause.printStackTrace();
   }
}
