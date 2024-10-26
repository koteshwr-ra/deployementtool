package org.apache.mina.filter.codec.serialization;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.OutputStream;
import org.apache.mina.core.buffer.IoBuffer;

public class ObjectSerializationOutputStream extends OutputStream implements ObjectOutput {
    private int maxObjectSize = Integer.MAX_VALUE;
    private final DataOutputStream out;

    public ObjectSerializationOutputStream(OutputStream outputStream) {
        if (outputStream == null) {
            throw new IllegalArgumentException("out");
        } else if (outputStream instanceof DataOutputStream) {
            this.out = (DataOutputStream) outputStream;
        } else {
            this.out = new DataOutputStream(outputStream);
        }
    }

    public int getMaxObjectSize() {
        return this.maxObjectSize;
    }

    public void setMaxObjectSize(int i) {
        if (i > 0) {
            this.maxObjectSize = i;
            return;
        }
        throw new IllegalArgumentException("maxObjectSize: " + i);
    }

    public void close() throws IOException {
        this.out.close();
    }

    public void flush() throws IOException {
        this.out.flush();
    }

    public void write(int i) throws IOException {
        this.out.write(i);
    }

    public void write(byte[] bArr) throws IOException {
        this.out.write(bArr);
    }

    public void write(byte[] bArr, int i, int i2) throws IOException {
        this.out.write(bArr, i, i2);
    }

    public void writeObject(Object obj) throws IOException {
        IoBuffer allocate = IoBuffer.allocate(64, false);
        allocate.setAutoExpand(true);
        allocate.putObject(obj);
        int position = allocate.position() - 4;
        if (position <= this.maxObjectSize) {
            this.out.write(allocate.array(), 0, allocate.position());
            return;
        }
        throw new IllegalArgumentException("The encoded object is too big: " + position + " (> " + this.maxObjectSize + ')');
    }

    public void writeBoolean(boolean z) throws IOException {
        this.out.writeBoolean(z);
    }

    public void writeByte(int i) throws IOException {
        this.out.writeByte(i);
    }

    public void writeBytes(String str) throws IOException {
        this.out.writeBytes(str);
    }

    public void writeChar(int i) throws IOException {
        this.out.writeChar(i);
    }

    public void writeChars(String str) throws IOException {
        this.out.writeChars(str);
    }

    public void writeDouble(double d) throws IOException {
        this.out.writeDouble(d);
    }

    public void writeFloat(float f) throws IOException {
        this.out.writeFloat(f);
    }

    public void writeInt(int i) throws IOException {
        this.out.writeInt(i);
    }

    public void writeLong(long j) throws IOException {
        this.out.writeLong(j);
    }

    public void writeShort(int i) throws IOException {
        this.out.writeShort(i);
    }

    public void writeUTF(String str) throws IOException {
        this.out.writeUTF(str);
    }
}
