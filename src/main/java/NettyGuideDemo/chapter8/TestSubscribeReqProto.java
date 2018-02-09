package NettyGuideDemo.chapter8;

import com.google.protobuf.InvalidProtocolBufferException;

import java.util.ArrayList;
import java.util.List;

public class TestSubscribeReqProto {
    private static byte[] encode(SubscribeReqProto.SubscribeReq req){
        return req.toByteArray();
    }

    private static SubscribeReqProto.SubscribeReq decode(byte[] body) throws InvalidProtocolBufferException {
        return SubscribeReqProto.SubscribeReq.parseFrom(body);
    }

    private static SubscribeReqProto.SubscribeReq creatSubscribeReq(){
        SubscribeReqProto.SubscribeReq.Builder builder = SubscribeReqProto.SubscribeReq
                .newBuilder();
        builder.setSubReqID(1);
        builder.setUserName("Wangyihao");
        builder.setProductName("Netty Book");
        List<String> address = new ArrayList<>();
        address.add("Nanjing");
        address.add("Chengdu");
        address.add("Hangzhou");
        builder.addAllAddress(address);
        return builder.build();
    }

    public static void main(String[] args) throws InvalidProtocolBufferException {
        SubscribeReqProto.SubscribeReq  req = creatSubscribeReq();
        System.out.printf("Before encode : " + req.toString());
        SubscribeReqProto.SubscribeReq req2 = decode(encode(req));
        System.out.printf("After decode : " + req.toString());
        System.out.printf("Assert equal : --> " + req2.equals(req));
    }
}
