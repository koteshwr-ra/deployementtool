package com.example.sroslibrary.sros;

import android.util.Log;
import com.ciot.base.util.ByteUtils;
import com.example.sroslibrary.GsonUtils;
import com.example.sroslibrary.bean.ProtocolBean;

public class SrosRequestUtils {
    public static byte[] bean2Bytes(ProtocolBean protocolBean) {
        int head = protocolBean.getHead();
        byte qa = protocolBean.getQa();
        int seq = protocolBean.getSeq();
        short cmd = protocolBean.getCmd();
        short ver = protocolBean.getVer();
        String id = protocolBean.getId();
        short cflag = protocolBean.getCflag();
        short rflag = protocolBean.getRflag();
        short result = protocolBean.getResult();
        String json = GsonUtils.getGsonR(cmd, protocolBean.getResult() == 0).toJson(protocolBean.getBody(), GsonUtils.getType(cmd, qa));
        int length = json.getBytes().length;
        byte[] bArr = new byte[(length + 51)];
        System.arraycopy(ByteUtils.int2bytes(head), 0, bArr, 0, 4);
        bArr[4] = qa;
        System.arraycopy(ByteUtils.int2bytes(seq), 0, bArr, 5, 4);
        System.arraycopy(ByteUtils.short2bytes(cmd), 0, bArr, 9, 2);
        System.arraycopy(ByteUtils.short2bytes(ver), 0, bArr, 11, 2);
        System.arraycopy(id.getBytes(), 0, bArr, 13, 28);
        System.arraycopy(ByteUtils.short2bytes(cflag), 0, bArr, 41, 2);
        System.arraycopy(ByteUtils.short2bytes(rflag), 0, bArr, 43, 2);
        System.arraycopy(ByteUtils.short2bytes(result), 0, bArr, 45, 2);
        System.arraycopy(ByteUtils.int2bytes(length), 0, bArr, 47, 4);
        byte[] bytes = json.getBytes();
        System.arraycopy(bytes, 0, bArr, 51, bytes.length);
        return bArr;
    }

    public static ProtocolBean bytes2Bean(byte[] bArr) {
        ProtocolBean protocolBean = new ProtocolBean();
        byte[] bArr2 = new byte[4];
        byte[] bArr3 = new byte[2];
        System.arraycopy(bArr, 47, bArr2, 0, 4);
        int bytes2int = ByteUtils.bytes2int(bArr2);
        protocolBean.setLen(bytes2int);
        byte[] bArr4 = new byte[bytes2int];
        System.arraycopy(bArr, 51, bArr4, 0, bytes2int);
        String str = new String(bArr4);
        System.arraycopy(bArr, 9, bArr3, 0, 2);
        short bytes2short = ByteUtils.bytes2short(bArr3);
        protocolBean.setCmd(bytes2short);
        Log.d("body_tag", "cmd:" + bytes2short + ";bytes2Bean: " + str);
        byte b = bArr[4];
        protocolBean.setQa(b);
        protocolBean.setBody(GsonUtils.getGson().fromJson(str, GsonUtils.getType(bytes2short, b)));
        System.arraycopy(bArr, 5, bArr2, 0, 4);
        protocolBean.setSeq(ByteUtils.bytes2int(bArr2));
        System.arraycopy(bArr, 11, bArr3, 0, 2);
        protocolBean.setVer(ByteUtils.bytes2short(bArr3));
        byte[] bArr5 = new byte[28];
        System.arraycopy(bArr, 13, bArr5, 0, 28);
        protocolBean.setId(new String(bArr5));
        System.arraycopy(bArr, 41, bArr3, 0, 2);
        protocolBean.setCflag(ByteUtils.bytes2short(bArr3));
        System.arraycopy(bArr, 43, bArr3, 0, 2);
        protocolBean.setCflag(ByteUtils.bytes2short(bArr3));
        System.arraycopy(bArr, 45, bArr3, 0, 2);
        protocolBean.setResult(ByteUtils.bytes2short(bArr3));
        return protocolBean;
    }
}
