package NettyGuideDemo.chapter12;

import io.netty.buffer.ByteBuf;
import org.jboss.marshalling.ByteInput;

import java.io.IOException;

public class ChannelBufferByteInput implements ByteInput {
    private final ByteBuf byteBuf;

    public ChannelBufferByteInput(ByteBuf byteBuf){
        this.byteBuf = byteBuf;
    }

    @Override
    public int read() throws IOException {
        if (byteBuf.isReadable()){
            return byteBuf.readByte() & 0xff;
        }
        return -1;
    }

    @Override
    public int read(byte[] bytes) throws IOException {
        return read(bytes, 0, bytes.length);
    }

    @Override
    public int read(byte[] bytes, int i, int i1) throws IOException {
        int available = byteBuf.readableBytes();
        if (available == 0){
            return -1;
        }
        i1 = i1 > available ? available : i1;
        byteBuf.readBytes(bytes, i, i1);
        return i1;
    }

    @Override
    public int available() throws IOException {
        return byteBuf.readableBytes();
    }

    @Override
    public long skip(long l) throws IOException {
        int available = byteBuf.readableBytes();
        if (available < l){
            l = available;
        }
        byteBuf.readerIndex((int) (byteBuf.readerIndex() + l));
        return l;
    }

    @Override
    public void close() throws IOException {

    }
}
