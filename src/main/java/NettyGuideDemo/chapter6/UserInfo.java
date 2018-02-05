package NettyGuideDemo.chapter6;

import org.msgpack.annotation.Message;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.nio.ByteBuffer;

@Message
public class UserInfo {

    //    private static final long serialVersionUID = 1458783023984684894L;
    private String userName;
    private int userID;

    public UserInfo buildUserName(String name) {
        this.userName = name;
        return this;
    }

    public UserInfo buildUserID(int id) {
        this.userID = id;
        return this;
    }

    public final String getUserName() {
        return this.userName;
    }

    public final int getUserID() {
        return this.userID;
    }

    public final void setUserName(String name) {
        this.userName = name;
    }

    public final void setUserID(int id) {
        this.userID = id;
    }

    public byte[] codeUserInfo() {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        byte[] value = this.getUserName().getBytes();
        buffer.putInt(value.length);
        buffer.put(value);
        buffer.putInt(this.getUserID());
        buffer.flip();
        byte[] result = new byte[buffer.remaining()];
        buffer.get(result);
        return result;
    }

    public byte[] codeC(ByteBuffer buffer) {
        buffer.clear();
        byte[] value = this.userName.getBytes();
        buffer.putInt(value.length);
        buffer.put(value);
        buffer.putInt(this.userID);
        buffer.flip();
        byte[] result = new byte[buffer.remaining()];
        buffer.get(result);
        return result;
    }
}
