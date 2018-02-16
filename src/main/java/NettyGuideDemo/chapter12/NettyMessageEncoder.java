package NettyGuideDemo.chapter12;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.io.IOException;
import java.util.Map;

/**
 * 编码器总是要将对象转换成byte才能传输，所以这里encode要将message-->bytebuf
 */
public final class NettyMessageEncoder extends MessageToByteEncoder<NettyMessage>{
    MarshallingEncoder marshallingEncoder;

    public NettyMessageEncoder() throws IOException {
        this.marshallingEncoder = new MarshallingEncoder();
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, NettyMessage message, ByteBuf sendBuf) throws Exception {
        if (message == null || message.getHeader() == null){
            throw new Exception("The encode message is null");
        }
        sendBuf.writeInt(message.getHeader().getCrcCode());
        sendBuf.writeInt(message.getHeader().getLength());
        sendBuf.writeLong(message.getHeader().getSessionID());
        sendBuf.writeByte(message.getHeader().getType());
        sendBuf.writeByte(message.getHeader().getPriority());
        sendBuf.writeInt(message.getHeader().getAttachment().size());
        String key = null;
        byte[] keyArray = null;
        Object value = null;
        for (Map.Entry<String, Object>param : message.getHeader().getAttachment().entrySet()){
            key = param.getKey();
            keyArray = key.getBytes("UTF-8");
            sendBuf.writeInt(keyArray.length);
            sendBuf.writeBytes(keyArray);
            value = param.getValue();
            marshallingEncoder.encode(value, sendBuf);
        }
        key = null;
        keyArray = null;
        value = null;
        if (message.getBody() != null){
            marshallingEncoder.encode(message.getBody(), sendBuf);
        }else {
            sendBuf.writeInt(0);
        }
        //减去开头的crcCode4个字节以及长度域所占的4个字节，为了配合LengthFieldBasedFrameDecoder
        sendBuf.setInt(4, sendBuf.readableBytes() - 8);
    }
}
