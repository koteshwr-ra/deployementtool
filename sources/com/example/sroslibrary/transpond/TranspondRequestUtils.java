package com.example.sroslibrary.transpond;

import com.ciot.base.util.ByteUtils;
import com.example.sroslibrary.bean.TranspondProtocolBean;

public class TranspondRequestUtils {
    public static byte[] bean2Bytes(TranspondProtocolBean transpondProtocolBean) {
        short head = transpondProtocolBean.getHead();
        short type = transpondProtocolBean.getType();
        byte flow = transpondProtocolBean.getFlow();
        int length = transpondProtocolBean.getLength();
        String body = transpondProtocolBean.getBody();
        byte[] bArr = new byte[(length + 9)];
        byte[] short2Bytes = ByteUtils.short2Bytes(head);
        byte[] short2Bytes2 = ByteUtils.short2Bytes(type);
        byte[] int2bytes = ByteUtils.int2bytes(length);
        byte[] bytes = body.getBytes();
        System.arraycopy(short2Bytes, 0, bArr, 0, 2);
        System.arraycopy(short2Bytes2, 0, bArr, 2, 2);
        System.arraycopy(Byte.valueOf(flow), 0, bArr, 4, 1);
        System.arraycopy(int2bytes, 0, bArr, 5, 4);
        System.arraycopy(bytes, 0, bArr, 5, bytes.length);
        return bArr;
    }

    public static TranspondProtocolBean bytes2Bean(byte[] bArr) {
        TranspondProtocolBean transpondProtocolBean = new TranspondProtocolBean();
        byte[] bArr2 = new byte[2];
        System.arraycopy(bArr, 0, bArr2, 0, 2);
        short bytes2Short = ByteUtils.bytes2Short(bArr2);
        byte[] bArr3 = new byte[2];
        System.arraycopy(bArr, 2, bArr3, 0, 2);
        short bytes2short = ByteUtils.bytes2short(bArr3);
        byte b = bArr[4];
        byte[] bArr4 = new byte[4];
        System.arraycopy(bArr, 5, bArr4, 0, 2);
        int bytes2int = ByteUtils.bytes2int(bArr4);
        byte[] bArr5 = new byte[bytes2int];
        System.arraycopy(bArr, 9, bArr5, 0, bytes2int);
        String str = new String(bArr5);
        transpondProtocolBean.setHead(bytes2Short);
        transpondProtocolBean.setType(bytes2short);
        transpondProtocolBean.setFlow(b);
        transpondProtocolBean.setLength(bytes2int);
        transpondProtocolBean.setBody(str);
        return transpondProtocolBean;
    }
}
