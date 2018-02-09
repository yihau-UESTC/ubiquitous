package NettyGuideDemo.chapter8;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class SubReqServer {

    public static void main(String[] args) throws InterruptedException {
        int port = 8888;
        new SubReqServer().bind(port);
    }

    public void bind(int port) throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
       try {
           ServerBootstrap serverBootstrap = new ServerBootstrap();
           serverBootstrap.group(bossGroup, workerGroup)
                   .channel(NioServerSocketChannel.class)
                   .option(ChannelOption.SO_BACKLOG, 100)
                   .handler(new LoggingHandler(LogLevel.INFO))
                   .childHandler(new ChannelInitializer<SocketChannel>() {
                       @Override
                       protected void initChannel(SocketChannel socketChannel) throws Exception {
                           socketChannel.pipeline().addLast(new ProtobufVarint32FrameDecoder());
                           socketChannel.pipeline().addLast(new ProtobufDecoder(SubscribeReqProto
                                   .SubscribeReq.getDefaultInstance()));
                           socketChannel.pipeline().addLast(new ProtobufVarint32LengthFieldPrepender());
                           socketChannel.pipeline().addLast(new ProtobufEncoder());
                           socketChannel.pipeline().addLast(new SubReqServerHandler());
                       }
                   });
           ChannelFuture f = serverBootstrap.bind(port).sync();
           f.channel().closeFuture().sync();
       }finally {
           workerGroup.shutdownGracefully();
           bossGroup.shutdownGracefully();
       }
    }

    private class SubReqServerHandler extends ChannelInboundHandlerAdapter {
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            SubscribeReqProto.SubscribeReq req = (SubscribeReqProto.SubscribeReq) msg;
            if ("Wangyihao".equalsIgnoreCase(req.getUserName())){
                System.out.println("Service accept client subscribe req : [" + req.toString() + "]");
                ctx.writeAndFlush(resp(req.getSubReqID()));
            }
        }

        private SubscribeRespProto.SubscribeResp resp(int subReqID){
            SubscribeRespProto.SubscribeResp.Builder builder = SubscribeRespProto.SubscribeResp.newBuilder();
            builder.setSubReqID(subReqID);
            builder.setRespCode(0);
            builder.setDesc("Netty book order succeed, 3 days later, sent to your address");
            return builder.build();
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            cause.printStackTrace();
            ctx.close();
        }
    }
}
