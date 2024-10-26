package org.apache.mina.filter.codec.serialization;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.StreamCorruptedException;
import org.apache.mina.core.buffer.IoBuffer;

public class ObjectSerializationInputStream extends InputStream implements ObjectInput {
    private final ClassLoader classLoader;
    private final DataInputStream in;
    private int maxObjectSize;

    public ObjectSerializationInputStream(InputStream inputStream) {
        this(inputStream, (ClassLoader) null);
    }

    public ObjectSerializationInputStream(InputStream inputStream, ClassLoader classLoader2) {
        this.maxObjectSize = 1048576;
        if (inputStream != null) {
            classLoader2 = classLoader2 == null ? Thread.currentThread().getContextClassLoader() : classLoader2;
            if (inputStream instanceof DataInputStream) {
                this.in = (DataInputStream) inputStream;
            } else {
                this.in = new DataInputStream(inputStream);
            }
            this.classLoader = classLoader2;
            return;
        }
        throw new IllegalArgumentException("in");
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

    public int read() throws IOException {
        return this.in.read();
    }

    public Object readObject() throws ClassNotFoundException, IOException {
        int readInt = this.in.readInt();
        if (readInt <= 0) {
            throw new StreamCorruptedException("Invalid objectSize: " + readInt);
        } else if (readInt <= this.maxObjectSize) {
            int i = readInt + 4;
            IoBuffer allocate = IoBuffer.allocate(i, false);
            allocate.putInt(readInt);
            this.in.readFully(allocate.array(), 4, readInt);
            allocate.position(0);
            allocate.limit(i);
            return allocate.getObject(this.classLoader);
        } else {
            throw new StreamCorruptedException("ObjectSize too big: " + readInt + " (expected: <= " + this.maxObjectSize + ')');
        }
    }

    public boolean readBoolean() throws IOException {
        return this.in.readBoolean();
    }

    public byte readByte() throws IOException {
        return this.in.readByte();
    }

    public char readChar() throws IOException {
        return this.in.readChar();
    }

    public double readDouble() throws IOException {
        return this.in.readDouble();
    }

    public float readFloat() throws IOException {
        return this.in.readFloat();
    }

    public void readFully(byte[] bArr) throws IOException {
        this.in.readFully(bArr);
    }

    public void readFully(byte[] bArr, int i, int i2) throws IOException {
        this.in.readFully(bArr, i, i2);
    }

    public int readInt() throws IOException {
        return this.in.readInt();
    }

    @Deprecated
    public String readLine() throws IOException {
        return this.in.readLine();
    }

    public long readLong() throws IOException {
        return this.in.readLong();
    }

    public short readShort() throws IOException {
        return this.in.readShort();
    }

    public String readUTF() throws IOException {
        return this.in.readUTF();
    }

    public int readUnsignedByte() throws IOException {
        return this.in.readUnsignedByte();
    }

    public int readUnsignedShort() throws IOException {
        return this.in.readUnsignedShort();
    }

    public int skipBytes(int i) throws IOException {
        return this.in.skipBytes(i);
    }
}
