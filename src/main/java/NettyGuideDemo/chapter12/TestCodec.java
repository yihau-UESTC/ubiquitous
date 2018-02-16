package NettyGuideDemo.chapter12;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TestCodec {
    private MarshallingDecoder marshallingDecoder;
    private MarshallingEncoder marshallingEncoder;

    public TestCodec() throws IOException {
        marshallingDecoder = new MarshallingDecoder();
        marshallingEncoder = new MarshallingEncoder();
    }

    public NettyMessage buildMessage(){
        NettyMessage nettyMessage = new NettyMessage();
        Header header = new Header();
        header.setLength(68);
        header.setSessionID(99999);
        header.setType((byte) 1);
        header.setPriority((byte) 7);
        Map<String, Object> attachment = new HashMap<String, Object>();
        for (int i = 0; i < 1; i++) {
            attachment.put("city", "chengdu");
        }
        header.setAttachment(attachment);
        nettyMessage.setHeader(header);
        nettyMessage.setBody("123456789");
        return nettyMessage;
    }

    public ByteBuf encode(NettyMessage message) throws IOException {
        ByteBuf sendBuf = Unpooled.buffer();
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
        sendBuf.setInt(4, sendBuf.readableBytes());
        return sendBuf;
    }

    public NettyMessage decode(ByteBuf frame) throws IOException, ClassNotFoundException {
        NettyMessage message = new NettyMessage();
        Header header = new Header();
        header.setCrcCode(frame.readInt());
        header.setLength(frame.readInt());
        header.setSessionID(frame.readLong());
        header.setType(frame.readByte());
        header.setPriority(frame.readByte());
        int size = frame.readInt();
        if (size > 0){
            Map<String, Object> attachment = new HashMap<>();
            String key = null;
            byte[] keyArray = null;
            int keySize = 0;
            for (int i = 0; i < size; i++){
                keySize = frame.readInt();
                keyArray = new byte[keySize];
                frame.readBytes(keyArray);
                key = new String(keyArray, "UTF-8");
                attachment.put(key, marshallingDecoder.decode(frame));
            }
            keyArray = null;
            key = null;
            header.setAttachment(attachment);
        }
        if (frame.readableBytes() > 4){
            message.setBody(marshallingDecoder.decode(frame));
        }
        message.setHeader(header);
        return message;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        TestCodec testC = new TestCodec();
        NettyMessage message = testC.buildMessage();
        System.out.println(message + "[body ] " + message.getBody());

        for (int i = 0; i < 5; i++) {
            ByteBuf buf = testC.encode(message);
            NettyMessage decodeMsg = testC.decode(buf);
            System.out.println(decodeMsg + "[body ] " + decodeMsg.getBody());
            System.out
                    .println("-------------------------------------------------");

        }
    }

    @Test
    public void test(){
        System.out.println("city".getBytes().length);
    }
}
