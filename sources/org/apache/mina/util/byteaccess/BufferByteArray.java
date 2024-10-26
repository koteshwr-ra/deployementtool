package org.apache.mina.util.byteaccess;

import java.nio.ByteOrder;
import java.util.Collections;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.util.byteaccess.ByteArray;

public abstract class BufferByteArray extends AbstractByteArray {
    protected IoBuffer bb;

    public int first() {
        return 0;
    }

    public abstract void free();

    public BufferByteArray(IoBuffer ioBuffer) {
        this.bb = ioBuffer;
    }

    public Iterable<IoBuffer> getIoBuffers() {
        return Collections.singletonList(this.bb);
    }

    public IoBuffer getSingleIoBuffer() {
        return this.bb;
    }

    public ByteArray slice(int i, int i2) {
        int limit = this.bb.limit();
        this.bb.position(i);
        this.bb.limit(i + i2);
        IoBuffer slice = this.bb.slice();
        this.bb.limit(limit);
        return new BufferByteArray(slice) {
            public void free() {
            }
        };
    }

    public ByteArray.Cursor cursor() {
        return new CursorImpl();
    }

    public ByteArray.Cursor cursor(int i) {
        return new CursorImpl(i);
    }

    public int last() {
        return this.bb.limit();
    }

    public ByteOrder order() {
        return this.bb.order();
    }

    public void order(ByteOrder byteOrder) {
        this.bb.order(byteOrder);
    }

    public byte get(int i) {
        return this.bb.get(i);
    }

    public void put(int i, byte b) {
        this.bb.put(i, b);
    }

    public void get(int i, IoBuffer ioBuffer) {
        this.bb.position(i);
        ioBuffer.put(this.bb);
    }

    public void put(int i, IoBuffer ioBuffer) {
        this.bb.position(i);
        this.bb.put(ioBuffer);
    }

    public short getShort(int i) {
        return this.bb.getShort(i);
    }

    public void putShort(int i, short s) {
        this.bb.putShort(i, s);
    }

    public int getInt(int i) {
        return this.bb.getInt(i);
    }

    public void putInt(int i, int i2) {
        this.bb.putInt(i, i2);
    }

    public long getLong(int i) {
        return this.bb.getLong(i);
    }

    public void putLong(int i, long j) {
        this.bb.putLong(i, j);
    }

    public float getFloat(int i) {
        return this.bb.getFloat(i);
    }

    public void putFloat(int i, float f) {
        this.bb.putFloat(i, f);
    }

    public double getDouble(int i) {
        return this.bb.getDouble(i);
    }

    public void putDouble(int i, double d) {
        this.bb.putDouble(i, d);
    }

    public char getChar(int i) {
        return this.bb.getChar(i);
    }

    public void putChar(int i, char c) {
        this.bb.putChar(i, c);
    }

    private class CursorImpl implements ByteArray.Cursor {
        private int index;

        public CursorImpl() {
        }

        public CursorImpl(int i) {
            setIndex(i);
        }

        public int getRemaining() {
            return BufferByteArray.this.last() - this.index;
        }

        public boolean hasRemaining() {
            return getRemaining() > 0;
        }

        public int getIndex() {
            return this.index;
        }

        public void setIndex(int i) {
            if (i < 0 || i > BufferByteArray.this.last()) {
                throw new IndexOutOfBoundsException();
            }
            this.index = i;
        }

        public void skip(int i) {
            setIndex(this.index + i);
        }

        public ByteArray slice(int i) {
            ByteArray slice = BufferByteArray.this.slice(this.index, i);
            this.index += i;
            return slice;
        }

        public ByteOrder order() {
            return BufferByteArray.this.order();
        }

        public byte get() {
            byte b = BufferByteArray.this.get(this.index);
            this.index++;
            return b;
        }

        public void put(byte b) {
            BufferByteArray.this.put(this.index, b);
            this.index++;
        }

        public void get(IoBuffer ioBuffer) {
            int min = Math.min(getRemaining(), ioBuffer.remaining());
            BufferByteArray.this.get(this.index, ioBuffer);
            this.index += min;
        }

        public void put(IoBuffer ioBuffer) {
            int remaining = ioBuffer.remaining();
            BufferByteArray.this.put(this.index, ioBuffer);
            this.index += remaining;
        }

        public short getShort() {
            short s = BufferByteArray.this.getShort(this.index);
            this.index += 2;
            return s;
        }

        public void putShort(short s) {
            BufferByteArray.this.putShort(this.index, s);
            this.index += 2;
        }

        public int getInt() {
            int i = BufferByteArray.this.getInt(this.index);
            this.index += 4;
            return i;
        }

        public void putInt(int i) {
            BufferByteArray.this.putInt(this.index, i);
            this.index += 4;
        }

        public long getLong() {
            long j = BufferByteArray.this.getLong(this.index);
            this.index += 8;
            return j;
        }

        public void putLong(long j) {
            BufferByteArray.this.putLong(this.index, j);
            this.index += 8;
        }

        public float getFloat() {
            float f = BufferByteArray.this.getFloat(this.index);
            this.index += 4;
            return f;
        }

        public void putFloat(float f) {
            BufferByteArray.this.putFloat(this.index, f);
            this.index += 4;
        }

        public double getDouble() {
            double d = BufferByteArray.this.getDouble(this.index);
            this.index += 8;
            return d;
        }

        public void putDouble(double d) {
            BufferByteArray.this.putDouble(this.index, d);
            this.index += 8;
        }

        public char getChar() {
            char c = BufferByteArray.this.getChar(this.index);
            this.index += 2;
            return c;
        }

        public void putChar(char c) {
            BufferByteArray.this.putChar(this.index, c);
            this.index += 2;
        }
    }
}
