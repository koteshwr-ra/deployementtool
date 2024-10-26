package org.apache.mina.filter.codec.demux;

public class MessageDecoderResult {
    public static MessageDecoderResult NEED_DATA = new MessageDecoderResult("NEED_DATA");
    public static MessageDecoderResult NOT_OK = new MessageDecoderResult("NOT_OK");
    public static MessageDecoderResult OK = new MessageDecoderResult("OK");
    private final String name;

    private MessageDecoderResult(String str) {
        this.name = str;
    }

    public String toString() {
        return this.name;
    }
}
