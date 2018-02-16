package NettyGuideDemo.chapter12;

import java.io.Serializable;

public final class NettyMessage {
    private Header header;
    private Object object;

    public final Header getHeader() {
        return header;
    }

    public final void setHeader(Header header) {
        this.header = header;
    }

    public final void setBody(Object object) {
        this.object = object;
    }

    public final Object getBody() {
        return object;
    }

    @Override
    public String toString() {
        return "NettyMessage [header = " + header + ", object = " + object + "]";
    }
}
