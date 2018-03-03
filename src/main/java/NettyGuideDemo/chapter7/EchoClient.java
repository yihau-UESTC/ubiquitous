package NettyGuideDemo.chapter7;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class EchoClient {

    public static void main(String[] args) throws InterruptedException {
        new EchoClient().connect(8888, "127.0.0.1");
    }

    public void connect(int port, String host) throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
//                            socketChannel.pipeline().addLast("frameDecoder", new LengthFieldBasedFrameDecoder(65535, 0, 2, 0, 2));
                            socketChannel.pipeline().addLast("msgpack decoder", new MsgpackDecoder());
//                            socketChannel.pipeline().addLast("frameEncoder", new LengthFieldPrepender(2));
                            socketChannel.pipeline().addLast("msgpack encoder", new MsgpackEncoder());
                            socketChannel.pipeline().addLast(new EchoClientHandler());
                        }
                    });
            ChannelFuture f = bootstrap.connect(host, port).sync();
            f.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }
    }

    private class EchoClientHandler extends ChannelInboundHandlerAdapter {

        public EchoClientHandler() {

        }

        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            UserInfo[] userInfos = buildUserInfos();
            for (UserInfo userInfo : userInfos) {
                ctx.write(userInfo);
            }
            ctx.flush();
        }

        private UserInfo[] buildUserInfos() {
            UserInfo[] userInfos = new UserInfo[10];
            UserInfo userInfo = null;
            for (int i = 0; i < 10; i++) {
                userInfo = new UserInfo();
                userInfo.setUserID(i);
                userInfo.setUserName("yihau--->" + i);
                userInfos[i] = userInfo;
            }
            return userInfos;
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        }

        @Override
        public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
            ctx.flush();
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            cause.printStackTrace();
            ctx.close();
        }
    }
}
