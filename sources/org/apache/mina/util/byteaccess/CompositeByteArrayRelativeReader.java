package org.apache.mina.util.byteaccess;

import java.nio.ByteOrder;
import org.apache.mina.core.buffer.IoBuffer;

public class CompositeByteArrayRelativeReader extends CompositeByteArrayRelativeBase implements IoRelativeReader {
    private final boolean autoFree;

    public /* bridge */ /* synthetic */ ByteOrder order() {
        return super.order();
    }

    public CompositeByteArrayRelativeReader(CompositeByteArray compositeByteArray, boolean z) {
        super(compositeByteArray);
        this.autoFree = z;
    }

    /* access modifiers changed from: protected */
    public void cursorPassedFirstComponent() {
        if (this.autoFree) {
            this.cba.removeFirst().free();
        }
    }

    public void skip(int i) {
        this.cursor.skip(i);
    }

    public ByteArray slice(int i) {
        return this.cursor.slice(i);
    }

    public byte get() {
        return this.cursor.get();
    }

    public void get(IoBuffer ioBuffer) {
        this.cursor.get(ioBuffer);
    }

    public short getShort() {
        return this.cursor.getShort();
    }

    public int getInt() {
        return this.cursor.getInt();
    }

    public long getLong() {
        return this.cursor.getLong();
    }

    public float getFloat() {
        return this.cursor.getFloat();
    }

    public double getDouble() {
        return this.cursor.getDouble();
    }

    public char getChar() {
        return this.cursor.getChar();
    }
}
