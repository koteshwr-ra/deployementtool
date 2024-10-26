package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.tencent.smtt.sdk.TbsListener;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.ByteBuffer;

public class ByteBufferCodec implements ObjectSerializer, ObjectDeserializer {
    public static final ByteBufferCodec instance = new ByteBufferCodec();

    public int getFastMatchToken() {
        return 14;
    }

    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        return ((ByteBufferBean) defaultJSONParser.parseObject(ByteBufferBean.class)).byteBuffer();
    }

    public void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type, int i) throws IOException {
        ByteBuffer byteBuffer = (ByteBuffer) obj;
        byte[] array = byteBuffer.array();
        SerializeWriter serializeWriter = jSONSerializer.out;
        serializeWriter.write((int) TbsListener.ErrorCode.DOWNLOAD_RETRYTIMES302_EXCEED);
        serializeWriter.writeFieldName("array");
        serializeWriter.writeByteArray(array);
        serializeWriter.writeFieldValue(',', "limit", byteBuffer.limit());
        serializeWriter.writeFieldValue(',', RequestParameters.POSITION, byteBuffer.position());
        serializeWriter.write((int) TbsListener.ErrorCode.DOWNLOAD_THROWABLE);
    }

    public static class ByteBufferBean {
        public byte[] array;
        public int limit;
        public int position;

        public ByteBuffer byteBuffer() {
            ByteBuffer wrap = ByteBuffer.wrap(this.array);
            wrap.limit(this.limit);
            wrap.position(this.position);
            return wrap;
        }
    }
}
