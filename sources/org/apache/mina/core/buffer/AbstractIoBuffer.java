package org.apache.mina.core.buffer;

import com.google.common.base.Ascii;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamClass;
import java.io.OutputStream;
import java.io.Serializable;
import java.io.StreamCorruptedException;
import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.ShortBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;
import java.util.EnumSet;
import java.util.Set;
import org.apache.commons.lang3.CharEncoding;

public abstract class AbstractIoBuffer extends IoBuffer {
    private static final long BYTE_MASK = 255;
    private static final long INT_MASK = 4294967295L;
    private static final long SHORT_MASK = 65535;
    private boolean autoExpand;
    private boolean autoShrink;
    private final boolean derived;
    private int mark = -1;
    private int minimumCapacity;
    private boolean recapacityAllowed = true;

    private int getMediumInt(byte b, byte b2, byte b3) {
        byte b4 = ((b2 << 8) & 65280) | ((b << Ascii.DLE) & 16711680) | (b3 & 255);
        return (b & 128) == 128 ? b4 | -16777216 : b4;
    }

    /* access modifiers changed from: protected */
    public abstract IoBuffer asReadOnlyBuffer0();

    /* access modifiers changed from: protected */
    public abstract void buf(ByteBuffer byteBuffer);

    /* access modifiers changed from: protected */
    public abstract IoBuffer duplicate0();

    /* access modifiers changed from: protected */
    public abstract IoBuffer slice0();

    protected AbstractIoBuffer(IoBufferAllocator ioBufferAllocator, int i) {
        setAllocator(ioBufferAllocator);
        this.recapacityAllowed = true;
        this.derived = false;
        this.minimumCapacity = i;
    }

    protected AbstractIoBuffer(AbstractIoBuffer abstractIoBuffer) {
        setAllocator(getAllocator());
        this.recapacityAllowed = false;
        this.derived = true;
        this.minimumCapacity = abstractIoBuffer.minimumCapacity;
    }

    public final boolean isDirect() {
        return buf().isDirect();
    }

    public final boolean isReadOnly() {
        return buf().isReadOnly();
    }

    public final int minimumCapacity() {
        return this.minimumCapacity;
    }

    public final IoBuffer minimumCapacity(int i) {
        if (i >= 0) {
            this.minimumCapacity = i;
            return this;
        }
        throw new IllegalArgumentException("minimumCapacity: " + i);
    }

    public final int capacity() {
        return buf().capacity();
    }

    public final IoBuffer capacity(int i) {
        if (this.recapacityAllowed) {
            if (i > capacity()) {
                int position = position();
                int limit = limit();
                ByteOrder order = order();
                ByteBuffer buf = buf();
                ByteBuffer allocateNioBuffer = getAllocator().allocateNioBuffer(i, isDirect());
                buf.clear();
                allocateNioBuffer.put(buf);
                buf(allocateNioBuffer);
                buf().limit(limit);
                if (this.mark >= 0) {
                    buf().position(this.mark);
                    buf().mark();
                }
                buf().position(position);
                buf().order(order);
            }
            return this;
        }
        throw new IllegalStateException("Derived buffers and their parent can't be expanded.");
    }

    public final boolean isAutoExpand() {
        return this.autoExpand && this.recapacityAllowed;
    }

    public final boolean isAutoShrink() {
        return this.autoShrink && this.recapacityAllowed;
    }

    public final boolean isDerived() {
        return this.derived;
    }

    public final IoBuffer setAutoExpand(boolean z) {
        if (this.recapacityAllowed) {
            this.autoExpand = z;
            return this;
        }
        throw new IllegalStateException("Derived buffers and their parent can't be expanded.");
    }

    public final IoBuffer setAutoShrink(boolean z) {
        if (this.recapacityAllowed) {
            this.autoShrink = z;
            return this;
        }
        throw new IllegalStateException("Derived buffers and their parent can't be shrinked.");
    }

    public final IoBuffer expand(int i) {
        return expand(position(), i, false);
    }

    private IoBuffer expand(int i, boolean z) {
        return expand(position(), i, z);
    }

    public final IoBuffer expand(int i, int i2) {
        return expand(i, i2, false);
    }

    private IoBuffer expand(int i, int i2, boolean z) {
        if (this.recapacityAllowed) {
            int i3 = i + i2;
            int normalizeCapacity = z ? IoBuffer.normalizeCapacity(i3) : i3;
            if (normalizeCapacity > capacity()) {
                capacity(normalizeCapacity);
            }
            if (i3 > limit()) {
                buf().limit(i3);
            }
            return this;
        }
        throw new IllegalStateException("Derived buffers and their parent can't be expanded.");
    }

    public final IoBuffer shrink() {
        if (this.recapacityAllowed) {
            int position = position();
            int capacity = capacity();
            int limit = limit();
            if (capacity == limit) {
                return this;
            }
            int max = Math.max(this.minimumCapacity, limit);
            int i = capacity;
            while (true) {
                int i2 = i >>> 1;
                if (i2 < max) {
                    break;
                }
                i = i2;
            }
            int max2 = Math.max(max, i);
            if (max2 == capacity) {
                return this;
            }
            ByteOrder order = order();
            ByteBuffer buf = buf();
            ByteBuffer allocateNioBuffer = getAllocator().allocateNioBuffer(max2, isDirect());
            buf.position(0);
            buf.limit(limit);
            allocateNioBuffer.put(buf);
            buf(allocateNioBuffer);
            buf().position(position);
            buf().limit(limit);
            buf().order(order);
            this.mark = -1;
            return this;
        }
        throw new IllegalStateException("Derived buffers and their parent can't be expanded.");
    }

    public final int position() {
        return buf().position();
    }

    public final IoBuffer position(int i) {
        autoExpand(i, 0);
        buf().position(i);
        if (this.mark > i) {
            this.mark = -1;
        }
        return this;
    }

    public final int limit() {
        return buf().limit();
    }

    public final IoBuffer limit(int i) {
        autoExpand(i, 0);
        buf().limit(i);
        if (this.mark > i) {
            this.mark = -1;
        }
        return this;
    }

    public final IoBuffer mark() {
        buf().mark();
        this.mark = position();
        return this;
    }

    public final int markValue() {
        return this.mark;
    }

    public final IoBuffer reset() {
        buf().reset();
        return this;
    }

    public final IoBuffer clear() {
        buf().clear();
        this.mark = -1;
        return this;
    }

    public final IoBuffer sweep() {
        clear();
        return fillAndReset(remaining());
    }

    public final IoBuffer sweep(byte b) {
        clear();
        return fillAndReset(b, remaining());
    }

    public final IoBuffer flip() {
        buf().flip();
        this.mark = -1;
        return this;
    }

    public final IoBuffer rewind() {
        buf().rewind();
        this.mark = -1;
        return this;
    }

    public final int remaining() {
        return limit() - position();
    }

    public final boolean hasRemaining() {
        return limit() > position();
    }

    public final byte get() {
        return buf().get();
    }

    public final short getUnsigned() {
        return (short) (get() & 255);
    }

    public final IoBuffer put(byte b) {
        autoExpand(1);
        buf().put(b);
        return this;
    }

    public IoBuffer putUnsigned(byte b) {
        autoExpand(1);
        buf().put((byte) (b & 255));
        return this;
    }

    public IoBuffer putUnsigned(int i, byte b) {
        autoExpand(i, 1);
        buf().put(i, (byte) (b & 255));
        return this;
    }

    public IoBuffer putUnsigned(short s) {
        autoExpand(1);
        buf().put((byte) (s & 255));
        return this;
    }

    public IoBuffer putUnsigned(int i, short s) {
        autoExpand(i, 1);
        buf().put(i, (byte) (s & 255));
        return this;
    }

    public IoBuffer putUnsigned(int i) {
        autoExpand(1);
        buf().put((byte) (i & 255));
        return this;
    }

    public IoBuffer putUnsigned(int i, int i2) {
        autoExpand(i, 1);
        buf().put(i, (byte) (i2 & 255));
        return this;
    }

    public IoBuffer putUnsigned(long j) {
        autoExpand(1);
        buf().put((byte) ((int) (j & BYTE_MASK)));
        return this;
    }

    public IoBuffer putUnsigned(int i, long j) {
        autoExpand(i, 1);
        buf().put(i, (byte) ((int) (j & BYTE_MASK)));
        return this;
    }

    public final byte get(int i) {
        return buf().get(i);
    }

    public final short getUnsigned(int i) {
        return (short) (get(i) & 255);
    }

    public final IoBuffer put(int i, byte b) {
        autoExpand(i, 1);
        buf().put(i, b);
        return this;
    }

    public final IoBuffer get(byte[] bArr, int i, int i2) {
        buf().get(bArr, i, i2);
        return this;
    }

    public final IoBuffer put(ByteBuffer byteBuffer) {
        autoExpand(byteBuffer.remaining());
        buf().put(byteBuffer);
        return this;
    }

    public final IoBuffer put(byte[] bArr, int i, int i2) {
        autoExpand(i2);
        buf().put(bArr, i, i2);
        return this;
    }

    public final IoBuffer compact() {
        int i;
        int remaining = remaining();
        int capacity = capacity();
        if (capacity == 0) {
            return this;
        }
        if (!isAutoShrink() || remaining > (capacity >>> 2) || capacity <= (i = this.minimumCapacity)) {
            buf().compact();
        } else {
            int max = Math.max(i, remaining << 1);
            int i2 = capacity;
            while (true) {
                int i3 = i2 >>> 1;
                if (i3 < max) {
                    break;
                }
                i2 = i3;
            }
            int max2 = Math.max(max, i2);
            if (max2 == capacity) {
                return this;
            }
            ByteOrder order = order();
            if (remaining <= max2) {
                ByteBuffer buf = buf();
                ByteBuffer allocateNioBuffer = getAllocator().allocateNioBuffer(max2, isDirect());
                allocateNioBuffer.put(buf);
                buf(allocateNioBuffer);
                buf().order(order);
            } else {
                throw new IllegalStateException("The amount of the remaining bytes is greater than the new capacity.");
            }
        }
        this.mark = -1;
        return this;
    }

    public final ByteOrder order() {
        return buf().order();
    }

    public final IoBuffer order(ByteOrder byteOrder) {
        buf().order(byteOrder);
        return this;
    }

    public final char getChar() {
        return buf().getChar();
    }

    public final IoBuffer putChar(char c) {
        autoExpand(2);
        buf().putChar(c);
        return this;
    }

    public final char getChar(int i) {
        return buf().getChar(i);
    }

    public final IoBuffer putChar(int i, char c) {
        autoExpand(i, 2);
        buf().putChar(i, c);
        return this;
    }

    public final CharBuffer asCharBuffer() {
        return buf().asCharBuffer();
    }

    public final short getShort() {
        return buf().getShort();
    }

    public final IoBuffer putShort(short s) {
        autoExpand(2);
        buf().putShort(s);
        return this;
    }

    public final short getShort(int i) {
        return buf().getShort(i);
    }

    public final IoBuffer putShort(int i, short s) {
        autoExpand(i, 2);
        buf().putShort(i, s);
        return this;
    }

    public final ShortBuffer asShortBuffer() {
        return buf().asShortBuffer();
    }

    public final int getInt() {
        return buf().getInt();
    }

    public final IoBuffer putInt(int i) {
        autoExpand(4);
        buf().putInt(i);
        return this;
    }

    public final IoBuffer putUnsignedInt(byte b) {
        autoExpand(4);
        buf().putInt(((short) b) & 255);
        return this;
    }

    public final IoBuffer putUnsignedInt(int i, byte b) {
        autoExpand(i, 4);
        buf().putInt(i, ((short) b) & 255);
        return this;
    }

    public final IoBuffer putUnsignedInt(short s) {
        autoExpand(4);
        buf().putInt(s & 65535);
        return this;
    }

    public final IoBuffer putUnsignedInt(int i, short s) {
        autoExpand(i, 4);
        buf().putInt(i, s & 65535);
        return this;
    }

    public final IoBuffer putUnsignedInt(int i) {
        autoExpand(4);
        buf().putInt(i);
        return this;
    }

    public final IoBuffer putUnsignedInt(int i, int i2) {
        autoExpand(i, 4);
        buf().putInt(i, i2);
        return this;
    }

    public final IoBuffer putUnsignedInt(long j) {
        autoExpand(4);
        buf().putInt((int) (j & -1));
        return this;
    }

    public final IoBuffer putUnsignedInt(int i, long j) {
        autoExpand(i, 4);
        buf().putInt(i, (int) (j & INT_MASK));
        return this;
    }

    public final IoBuffer putUnsignedShort(byte b) {
        autoExpand(2);
        buf().putShort((short) (((short) b) & 255));
        return this;
    }

    public final IoBuffer putUnsignedShort(int i, byte b) {
        autoExpand(i, 2);
        buf().putShort(i, (short) (((short) b) & 255));
        return this;
    }

    public final IoBuffer putUnsignedShort(short s) {
        autoExpand(2);
        buf().putShort(s);
        return this;
    }

    public final IoBuffer putUnsignedShort(int i, short s) {
        autoExpand(i, 2);
        buf().putShort(i, s);
        return this;
    }

    public final IoBuffer putUnsignedShort(int i) {
        autoExpand(2);
        buf().putShort((short) i);
        return this;
    }

    public final IoBuffer putUnsignedShort(int i, int i2) {
        autoExpand(i, 2);
        buf().putShort(i, (short) i2);
        return this;
    }

    public final IoBuffer putUnsignedShort(long j) {
        autoExpand(2);
        buf().putShort((short) ((int) j));
        return this;
    }

    public final IoBuffer putUnsignedShort(int i, long j) {
        autoExpand(i, 2);
        buf().putShort(i, (short) ((int) j));
        return this;
    }

    public final int getInt(int i) {
        return buf().getInt(i);
    }

    public final IoBuffer putInt(int i, int i2) {
        autoExpand(i, 4);
        buf().putInt(i, i2);
        return this;
    }

    public final IntBuffer asIntBuffer() {
        return buf().asIntBuffer();
    }

    public final long getLong() {
        return buf().getLong();
    }

    public final IoBuffer putLong(long j) {
        autoExpand(8);
        buf().putLong(j);
        return this;
    }

    public final long getLong(int i) {
        return buf().getLong(i);
    }

    public final IoBuffer putLong(int i, long j) {
        autoExpand(i, 8);
        buf().putLong(i, j);
        return this;
    }

    public final LongBuffer asLongBuffer() {
        return buf().asLongBuffer();
    }

    public final float getFloat() {
        return buf().getFloat();
    }

    public final IoBuffer putFloat(float f) {
        autoExpand(4);
        buf().putFloat(f);
        return this;
    }

    public final float getFloat(int i) {
        return buf().getFloat(i);
    }

    public final IoBuffer putFloat(int i, float f) {
        autoExpand(i, 4);
        buf().putFloat(i, f);
        return this;
    }

    public final FloatBuffer asFloatBuffer() {
        return buf().asFloatBuffer();
    }

    public final double getDouble() {
        return buf().getDouble();
    }

    public final IoBuffer putDouble(double d) {
        autoExpand(8);
        buf().putDouble(d);
        return this;
    }

    public final double getDouble(int i) {
        return buf().getDouble(i);
    }

    public final IoBuffer putDouble(int i, double d) {
        autoExpand(i, 8);
        buf().putDouble(i, d);
        return this;
    }

    public final DoubleBuffer asDoubleBuffer() {
        return buf().asDoubleBuffer();
    }

    public final IoBuffer asReadOnlyBuffer() {
        this.recapacityAllowed = false;
        return asReadOnlyBuffer0();
    }

    public final IoBuffer duplicate() {
        this.recapacityAllowed = false;
        return duplicate0();
    }

    public final IoBuffer slice() {
        this.recapacityAllowed = false;
        return slice0();
    }

    public final IoBuffer getSlice(int i, int i2) {
        if (i2 >= 0) {
            int limit = limit();
            if (i <= limit) {
                int i3 = i2 + i;
                if (capacity() >= i3) {
                    clear();
                    position(i);
                    limit(i3);
                    IoBuffer slice = slice();
                    position(i);
                    limit(limit);
                    return slice;
                }
                throw new IndexOutOfBoundsException("index + length (" + i3 + ") is greater " + "than capacity (" + capacity() + ").");
            }
            throw new IllegalArgumentException("index: " + i);
        }
        throw new IllegalArgumentException("length: " + i2);
    }

    public final IoBuffer getSlice(int i) {
        if (i >= 0) {
            int position = position();
            int limit = limit();
            int i2 = position + i;
            if (limit >= i2) {
                limit(i2);
                IoBuffer slice = slice();
                position(i2);
                limit(limit);
                return slice;
            }
            throw new IndexOutOfBoundsException("position + length (" + i2 + ") is greater " + "than limit (" + limit + ").");
        }
        throw new IllegalArgumentException("length: " + i);
    }

    public int hashCode() {
        int position = position();
        int i = 1;
        for (int limit = limit() - 1; limit >= position; limit--) {
            i = (i * 31) + get(limit);
        }
        return i;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof IoBuffer)) {
            return false;
        }
        IoBuffer ioBuffer = (IoBuffer) obj;
        if (remaining() != ioBuffer.remaining()) {
            return false;
        }
        int position = position();
        int limit = limit() - 1;
        int limit2 = ioBuffer.limit() - 1;
        while (limit >= position) {
            if (get(limit) != ioBuffer.get(limit2)) {
                return false;
            }
            limit--;
            limit2--;
        }
        return true;
    }

    public int compareTo(IoBuffer ioBuffer) {
        int position = position() + Math.min(remaining(), ioBuffer.remaining());
        int position2 = position();
        int position3 = ioBuffer.position();
        while (position2 < position) {
            byte b = get(position2);
            byte b2 = ioBuffer.get(position3);
            if (b != b2) {
                return b < b2 ? -1 : 1;
            }
            position2++;
            position3++;
        }
        return remaining() - ioBuffer.remaining();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (isDirect()) {
            sb.append("DirectBuffer");
        } else {
            sb.append("HeapBuffer");
        }
        sb.append("[pos=");
        sb.append(position());
        sb.append(" lim=");
        sb.append(limit());
        sb.append(" cap=");
        sb.append(capacity());
        sb.append(": ");
        sb.append(getHexDump(16));
        sb.append(']');
        return sb.toString();
    }

    public IoBuffer get(byte[] bArr) {
        return get(bArr, 0, bArr.length);
    }

    public IoBuffer put(IoBuffer ioBuffer) {
        return put(ioBuffer.buf());
    }

    public IoBuffer put(byte[] bArr) {
        return put(bArr, 0, bArr.length);
    }

    public int getUnsignedShort() {
        return getShort() & 65535;
    }

    public int getUnsignedShort(int i) {
        return getShort(i) & 65535;
    }

    public long getUnsignedInt() {
        return ((long) getInt()) & INT_MASK;
    }

    public int getMediumInt() {
        byte b = get();
        byte b2 = get();
        byte b3 = get();
        if (ByteOrder.BIG_ENDIAN.equals(order())) {
            return getMediumInt(b, b2, b3);
        }
        return getMediumInt(b3, b2, b);
    }

    public int getUnsignedMediumInt() {
        short unsigned = getUnsigned();
        short unsigned2 = getUnsigned();
        short unsigned3 = getUnsigned();
        if (ByteOrder.BIG_ENDIAN.equals(order())) {
            return (unsigned << 16) | (unsigned2 << 8) | unsigned3;
        }
        return unsigned | (unsigned2 << 8) | (unsigned3 << 16);
    }

    public int getMediumInt(int i) {
        byte b = get(i);
        byte b2 = get(i + 1);
        byte b3 = get(i + 2);
        if (ByteOrder.BIG_ENDIAN.equals(order())) {
            return getMediumInt(b, b2, b3);
        }
        return getMediumInt(b3, b2, b);
    }

    public int getUnsignedMediumInt(int i) {
        short unsigned = getUnsigned(i);
        short unsigned2 = getUnsigned(i + 1);
        short unsigned3 = getUnsigned(i + 2);
        return ByteOrder.BIG_ENDIAN.equals(order()) ? unsigned3 | (unsigned << 16) | (unsigned2 << 8) : (unsigned3 << 16) | (unsigned2 << 8) | unsigned;
    }

    public IoBuffer putMediumInt(int i) {
        byte b = (byte) (i >> 16);
        byte b2 = (byte) (i >> 8);
        byte b3 = (byte) i;
        if (ByteOrder.BIG_ENDIAN.equals(order())) {
            put(b).put(b2).put(b3);
        } else {
            put(b3).put(b2).put(b);
        }
        return this;
    }

    public IoBuffer putMediumInt(int i, int i2) {
        byte b = (byte) (i2 >> 16);
        byte b2 = (byte) (i2 >> 8);
        byte b3 = (byte) i2;
        if (ByteOrder.BIG_ENDIAN.equals(order())) {
            put(i, b).put(i + 1, b2).put(i + 2, b3);
        } else {
            put(i, b3).put(i + 1, b2).put(i + 2, b);
        }
        return this;
    }

    public long getUnsignedInt(int i) {
        return ((long) getInt(i)) & INT_MASK;
    }

    public InputStream asInputStream() {
        return new InputStream() {
            public boolean markSupported() {
                return true;
            }

            public int available() {
                return AbstractIoBuffer.this.remaining();
            }

            public synchronized void mark(int i) {
                AbstractIoBuffer.this.mark();
            }

            public int read() {
                if (AbstractIoBuffer.this.hasRemaining()) {
                    return AbstractIoBuffer.this.get() & 255;
                }
                return -1;
            }

            public int read(byte[] bArr, int i, int i2) {
                int remaining = AbstractIoBuffer.this.remaining();
                if (remaining <= 0) {
                    return -1;
                }
                int min = Math.min(remaining, i2);
                AbstractIoBuffer.this.get(bArr, i, min);
                return min;
            }

            public synchronized void reset() {
                AbstractIoBuffer.this.reset();
            }

            public long skip(long j) {
                int i;
                if (j > 2147483647L) {
                    i = AbstractIoBuffer.this.remaining();
                } else {
                    i = Math.min(AbstractIoBuffer.this.remaining(), (int) j);
                }
                AbstractIoBuffer.this.skip(i);
                return (long) i;
            }
        };
    }

    public OutputStream asOutputStream() {
        return new OutputStream() {
            public void write(byte[] bArr, int i, int i2) {
                AbstractIoBuffer.this.put(bArr, i, i2);
            }

            public void write(int i) {
                AbstractIoBuffer.this.put((byte) i);
            }
        };
    }

    public String getHexDump() {
        return getHexDump(Integer.MAX_VALUE);
    }

    public String getHexDump(int i) {
        return IoBufferHexDumper.getHexdump(this, i);
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x0062  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0066  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String getString(java.nio.charset.CharsetDecoder r10) throws java.nio.charset.CharacterCodingException {
        /*
            r9 = this;
            boolean r0 = r9.hasRemaining()
            java.lang.String r1 = ""
            if (r0 != 0) goto L_0x0009
            return r1
        L_0x0009:
            java.nio.charset.Charset r0 = r10.charset()
            java.lang.String r0 = r0.name()
            java.lang.String r2 = "UTF-16"
            boolean r0 = r0.startsWith(r2)
            int r2 = r9.position()
            int r3 = r9.limit()
            r4 = -1
            r5 = 0
            r6 = 1
            if (r0 != 0) goto L_0x0031
            int r0 = r9.indexOf(r5)
            if (r0 >= 0) goto L_0x002d
            r0 = r3
        L_0x002b:
            r7 = r0
            goto L_0x0060
        L_0x002d:
            int r4 = r0 + 1
            r7 = r4
            goto L_0x0060
        L_0x0031:
            r0 = r2
        L_0x0032:
            byte r7 = r9.get((int) r0)
            if (r7 != 0) goto L_0x003a
            r7 = 1
            goto L_0x003b
        L_0x003a:
            r7 = 0
        L_0x003b:
            int r0 = r0 + 1
            if (r0 < r3) goto L_0x0040
            goto L_0x004f
        L_0x0040:
            byte r8 = r9.get((int) r0)
            if (r8 == 0) goto L_0x004b
            int r0 = r0 + 1
            if (r0 < r3) goto L_0x0032
            goto L_0x004f
        L_0x004b:
            if (r7 == 0) goto L_0x0032
            int r4 = r0 + -1
        L_0x004f:
            if (r4 >= 0) goto L_0x0057
            int r0 = r3 - r2
            r0 = r0 & -2
            int r0 = r0 + r2
            goto L_0x002b
        L_0x0057:
            int r0 = r4 + 2
            if (r0 > r3) goto L_0x005e
            r7 = r0
            r0 = r4
            goto L_0x0060
        L_0x005e:
            r0 = r4
            goto L_0x002b
        L_0x0060:
            if (r2 != r0) goto L_0x0066
            r9.position(r7)
            return r1
        L_0x0066:
            r9.limit(r0)
            r10.reset()
            int r0 = r9.remaining()
            float r0 = (float) r0
            float r1 = r10.averageCharsPerByte()
            float r0 = r0 * r1
            int r0 = (int) r0
            int r8 = r0 + 1
            java.nio.CharBuffer r0 = java.nio.CharBuffer.allocate(r8)
        L_0x007e:
            boolean r1 = r9.hasRemaining()
            if (r1 == 0) goto L_0x008d
            java.nio.ByteBuffer r1 = r9.buf()
            java.nio.charset.CoderResult r1 = r10.decode(r1, r0, r6)
            goto L_0x0091
        L_0x008d:
            java.nio.charset.CoderResult r1 = r10.flush(r0)
        L_0x0091:
            boolean r4 = r1.isUnderflow()
            if (r4 == 0) goto L_0x00a6
            r9.limit(r3)
            r9.position(r7)
            java.nio.Buffer r10 = r0.flip()
            java.lang.String r10 = r10.toString()
            return r10
        L_0x00a6:
            boolean r4 = r1.isOverflow()
            if (r4 == 0) goto L_0x00bd
            int r1 = r0.capacity()
            int r1 = r1 + r8
            java.nio.CharBuffer r1 = java.nio.CharBuffer.allocate(r1)
            r0.flip()
            r1.put(r0)
            r0 = r1
            goto L_0x007e
        L_0x00bd:
            boolean r4 = r1.isError()
            if (r4 == 0) goto L_0x007e
            r9.limit(r3)
            r9.position(r2)
            r1.throwException()
            goto L_0x007e
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.mina.core.buffer.AbstractIoBuffer.getString(java.nio.charset.CharsetDecoder):java.lang.String");
    }

    public String getString(int i, CharsetDecoder charsetDecoder) throws CharacterCodingException {
        CoderResult coderResult;
        checkFieldSize(i);
        if (i == 0 || !hasRemaining()) {
            return "";
        }
        boolean startsWith = charsetDecoder.charset().name().startsWith(CharEncoding.UTF_16);
        if (!startsWith || (i & 1) == 0) {
            int position = position();
            int limit = limit();
            int i2 = i + position;
            if (limit >= i2) {
                if (!startsWith) {
                    int i3 = position;
                    while (i3 < i2 && get(i3) != 0) {
                        i3++;
                    }
                    if (i3 == i2) {
                        limit(i2);
                    } else {
                        limit(i3);
                    }
                } else {
                    int i4 = position;
                    while (i4 < i2 && (get(i4) != 0 || get(i4 + 1) != 0)) {
                        i4 += 2;
                    }
                    if (i4 == i2) {
                        limit(i2);
                    } else {
                        limit(i4);
                    }
                }
                if (!hasRemaining()) {
                    limit(limit);
                    position(i2);
                    return "";
                }
                charsetDecoder.reset();
                int remaining = ((int) (((float) remaining()) * charsetDecoder.averageCharsPerByte())) + 1;
                CharBuffer allocate = CharBuffer.allocate(remaining);
                while (true) {
                    if (hasRemaining()) {
                        coderResult = charsetDecoder.decode(buf(), allocate, true);
                    } else {
                        coderResult = charsetDecoder.flush(allocate);
                    }
                    if (coderResult.isUnderflow()) {
                        limit(limit);
                        position(i2);
                        return allocate.flip().toString();
                    } else if (coderResult.isOverflow()) {
                        CharBuffer allocate2 = CharBuffer.allocate(allocate.capacity() + remaining);
                        allocate.flip();
                        allocate2.put(allocate);
                        allocate = allocate2;
                    } else if (coderResult.isError()) {
                        limit(limit);
                        position(position);
                        coderResult.throwException();
                    }
                }
            } else {
                throw new BufferUnderflowException();
            }
        } else {
            throw new IllegalArgumentException("fieldSize is not even.");
        }
    }

    public IoBuffer putString(CharSequence charSequence, CharsetEncoder charsetEncoder) throws CharacterCodingException {
        CoderResult coderResult;
        if (charSequence.length() == 0) {
            return this;
        }
        CharBuffer wrap = CharBuffer.wrap(charSequence);
        charsetEncoder.reset();
        int i = 0;
        while (true) {
            if (wrap.hasRemaining()) {
                coderResult = charsetEncoder.encode(wrap, buf(), true);
            } else {
                coderResult = charsetEncoder.flush(buf());
            }
            if (coderResult.isUnderflow()) {
                return this;
            }
            if (!coderResult.isOverflow()) {
                i = 0;
            } else if (isAutoExpand()) {
                if (i == 0) {
                    autoExpand((int) Math.ceil((double) (((float) wrap.remaining()) * charsetEncoder.averageBytesPerChar())));
                } else if (i == 1) {
                    autoExpand((int) Math.ceil((double) (((float) wrap.remaining()) * charsetEncoder.maxBytesPerChar())));
                } else {
                    throw new RuntimeException("Expanded by " + ((int) Math.ceil((double) (((float) wrap.remaining()) * charsetEncoder.maxBytesPerChar()))) + " but that wasn't enough for '" + charSequence + "'");
                }
                i++;
            }
            coderResult.throwException();
        }
    }

    public IoBuffer putString(CharSequence charSequence, int i, CharsetEncoder charsetEncoder) throws CharacterCodingException {
        CoderResult coderResult;
        checkFieldSize(i);
        if (i == 0) {
            return this;
        }
        autoExpand(i);
        boolean startsWith = charsetEncoder.charset().name().startsWith(CharEncoding.UTF_16);
        if (!startsWith || (i & 1) == 0) {
            int limit = limit();
            int position = position() + i;
            if (limit < position) {
                throw new BufferOverflowException();
            } else if (charSequence.length() == 0) {
                if (!startsWith) {
                    put((byte) 0);
                } else {
                    put((byte) 0);
                    put((byte) 0);
                }
                position(position);
                return this;
            } else {
                CharBuffer wrap = CharBuffer.wrap(charSequence);
                limit(position);
                charsetEncoder.reset();
                while (true) {
                    if (wrap.hasRemaining()) {
                        coderResult = charsetEncoder.encode(wrap, buf(), true);
                    } else {
                        coderResult = charsetEncoder.flush(buf());
                    }
                    if (coderResult.isUnderflow() || coderResult.isOverflow()) {
                        limit(limit);
                    } else {
                        coderResult.throwException();
                    }
                }
                limit(limit);
                if (position() < position) {
                    if (!startsWith) {
                        put((byte) 0);
                    } else {
                        put((byte) 0);
                        put((byte) 0);
                    }
                }
                position(position);
                return this;
            }
        } else {
            throw new IllegalArgumentException("fieldSize is not even.");
        }
    }

    public String getPrefixedString(CharsetDecoder charsetDecoder) throws CharacterCodingException {
        return getPrefixedString(2, charsetDecoder);
    }

    public String getPrefixedString(int i, CharsetDecoder charsetDecoder) throws CharacterCodingException {
        CoderResult coderResult;
        if (prefixedDataAvailable(i)) {
            int i2 = 0;
            if (i == 1) {
                i2 = getUnsigned();
            } else if (i == 2) {
                i2 = getUnsignedShort();
            } else if (i == 4) {
                i2 = getInt();
            }
            if (i2 == 0) {
                return "";
            }
            if (!charsetDecoder.charset().name().startsWith(CharEncoding.UTF_16) || (i2 & 1) == 0) {
                int limit = limit();
                int position = position() + i2;
                if (limit >= position) {
                    limit(position);
                    charsetDecoder.reset();
                    int remaining = ((int) (((float) remaining()) * charsetDecoder.averageCharsPerByte())) + 1;
                    CharBuffer allocate = CharBuffer.allocate(remaining);
                    while (true) {
                        if (hasRemaining()) {
                            coderResult = charsetDecoder.decode(buf(), allocate, true);
                        } else {
                            coderResult = charsetDecoder.flush(allocate);
                        }
                        if (coderResult.isUnderflow()) {
                            limit(limit);
                            position(position);
                            return allocate.flip().toString();
                        } else if (coderResult.isOverflow()) {
                            CharBuffer allocate2 = CharBuffer.allocate(allocate.capacity() + remaining);
                            allocate.flip();
                            allocate2.put(allocate);
                            allocate = allocate2;
                        } else {
                            coderResult.throwException();
                        }
                    }
                } else {
                    throw new BufferUnderflowException();
                }
            } else {
                throw new BufferDataException("fieldSize is not even for a UTF-16 string.");
            }
        } else {
            throw new BufferUnderflowException();
        }
    }

    public IoBuffer putPrefixedString(CharSequence charSequence, CharsetEncoder charsetEncoder) throws CharacterCodingException {
        return putPrefixedString(charSequence, 2, 0, charsetEncoder);
    }

    public IoBuffer putPrefixedString(CharSequence charSequence, int i, CharsetEncoder charsetEncoder) throws CharacterCodingException {
        return putPrefixedString(charSequence, i, 0, charsetEncoder);
    }

    public IoBuffer putPrefixedString(CharSequence charSequence, int i, int i2, CharsetEncoder charsetEncoder) throws CharacterCodingException {
        return putPrefixedString(charSequence, i, i2, (byte) 0, charsetEncoder);
    }

    public IoBuffer putPrefixedString(CharSequence charSequence, int i, int i2, byte b, CharsetEncoder charsetEncoder) throws CharacterCodingException {
        int i3;
        int i4;
        CoderResult coderResult;
        int i5;
        int i6 = i;
        int i7 = i2;
        CharsetEncoder charsetEncoder2 = charsetEncoder;
        int i8 = 4;
        int i9 = 2;
        boolean z = true;
        if (i6 == 1) {
            i3 = 255;
        } else if (i6 == 2) {
            i3 = 65535;
        } else if (i6 == 4) {
            i3 = Integer.MAX_VALUE;
        } else {
            throw new IllegalArgumentException("prefixLength: " + i6);
        }
        if (charSequence.length() > i3) {
            throw new IllegalArgumentException("The specified string is too long.");
        } else if (charSequence.length() == 0) {
            if (i6 == 1) {
                put((byte) 0);
            } else if (i6 == 2) {
                putShort(0);
            } else if (i6 == 4) {
                putInt(0);
            }
            return this;
        } else {
            if (i7 == 0 || i7 == 1) {
                i4 = 0;
            } else if (i7 == 2) {
                i4 = 1;
            } else if (i7 == 4) {
                i4 = 3;
            } else {
                throw new IllegalArgumentException("padding: " + i7);
            }
            CharBuffer wrap = CharBuffer.wrap(charSequence);
            skip(i6);
            int position = position();
            charsetEncoder.reset();
            int i10 = 0;
            while (true) {
                if (wrap.hasRemaining()) {
                    coderResult = charsetEncoder2.encode(wrap, buf(), z);
                } else {
                    coderResult = charsetEncoder2.flush(buf());
                }
                if (position() - position > i3) {
                    throw new IllegalArgumentException("The specified string is too long.");
                } else if (coderResult.isUnderflow()) {
                    fill(b, i7 - ((position() - position) & i4));
                    int position2 = position() - position;
                    if (i6 == z) {
                        put(position - (z ? 1 : 0), (byte) position2);
                    } else if (i6 == i9) {
                        putShort(position - i9, (short) position2);
                    } else if (i6 == i8) {
                        putInt(position - i8, position2);
                    }
                    return this;
                } else {
                    byte b2 = b;
                    if (!coderResult.isOverflow()) {
                        CharSequence charSequence2 = charSequence;
                        i5 = i3;
                        i10 = 0;
                    } else if (!isAutoExpand()) {
                        CharSequence charSequence3 = charSequence;
                        i5 = i3;
                    } else if (i10 == 0) {
                        CharSequence charSequence4 = charSequence;
                        i5 = i3;
                        autoExpand((int) Math.ceil((double) (((float) wrap.remaining()) * charsetEncoder.averageBytesPerChar())));
                        i10++;
                        i3 = i5;
                        i8 = 4;
                        i9 = 2;
                        z = true;
                    } else if (i10 == z) {
                        autoExpand((int) Math.ceil((double) (((float) wrap.remaining()) * charsetEncoder.maxBytesPerChar())));
                        i10++;
                        i8 = 4;
                        i9 = 2;
                    } else {
                        throw new RuntimeException("Expanded by " + ((int) Math.ceil((double) (((float) wrap.remaining()) * charsetEncoder.maxBytesPerChar()))) + " but that wasn't enough for '" + charSequence + "'");
                    }
                    coderResult.throwException();
                    i3 = i5;
                    i8 = 4;
                    i9 = 2;
                    z = true;
                }
            }
        }
    }

    public Object getObject() throws ClassNotFoundException {
        return getObject(Thread.currentThread().getContextClassLoader());
    }

    public Object getObject(final ClassLoader classLoader) throws ClassNotFoundException {
        if (prefixedDataAvailable(4)) {
            int i = getInt();
            if (i > 4) {
                int limit = limit();
                limit(position() + i);
                try {
                    Object readObject = new ObjectInputStream(asInputStream()) {
                        /* access modifiers changed from: protected */
                        public ObjectStreamClass readClassDescriptor() throws IOException, ClassNotFoundException {
                            int read = read();
                            if (read < 0) {
                                throw new EOFException();
                            } else if (read == 0) {
                                return super.readClassDescriptor();
                            } else {
                                if (read == 1) {
                                    return ObjectStreamClass.lookup(Class.forName(readUTF(), true, classLoader));
                                }
                                throw new StreamCorruptedException("Unexpected class descriptor type: " + read);
                            }
                        }

                        /* access modifiers changed from: protected */
                        public Class<?> resolveClass(ObjectStreamClass objectStreamClass) throws IOException, ClassNotFoundException {
                            try {
                                return Class.forName(objectStreamClass.getName(), false, classLoader);
                            } catch (ClassNotFoundException unused) {
                                return super.resolveClass(objectStreamClass);
                            }
                        }
                    }.readObject();
                    limit(limit);
                    return readObject;
                } catch (IOException e) {
                    throw new BufferDataException((Throwable) e);
                } catch (Throwable th) {
                    limit(limit);
                    throw th;
                }
            } else {
                throw new BufferDataException("Object length should be greater than 4: " + i);
            }
        } else {
            throw new BufferUnderflowException();
        }
    }

    public IoBuffer putObject(Object obj) {
        int position = position();
        skip(4);
        try {
            AnonymousClass4 r2 = new ObjectOutputStream(asOutputStream()) {
                /* access modifiers changed from: protected */
                public void writeClassDescriptor(ObjectStreamClass objectStreamClass) throws IOException {
                    try {
                        if (!Serializable.class.isAssignableFrom(Class.forName(objectStreamClass.getName()))) {
                            write(0);
                            super.writeClassDescriptor(objectStreamClass);
                            return;
                        }
                        write(1);
                        writeUTF(objectStreamClass.getName());
                    } catch (ClassNotFoundException unused) {
                        write(0);
                        super.writeClassDescriptor(objectStreamClass);
                    }
                }
            };
            r2.writeObject(obj);
            r2.flush();
            int position2 = position();
            position(position);
            putInt((position2 - position) - 4);
            position(position2);
            return this;
        } catch (IOException e) {
            throw new BufferDataException((Throwable) e);
        }
    }

    public boolean prefixedDataAvailable(int i) {
        return prefixedDataAvailable(i, Integer.MAX_VALUE);
    }

    public boolean prefixedDataAvailable(int i, int i2) {
        int i3;
        if (remaining() < i) {
            return false;
        }
        if (i == 1) {
            i3 = getUnsigned(position());
        } else if (i == 2) {
            i3 = getUnsignedShort(position());
        } else if (i == 4) {
            i3 = getInt(position());
        } else {
            throw new IllegalArgumentException("prefixLength: " + i);
        }
        if (i3 < 0 || i3 > i2) {
            throw new BufferDataException("dataLength: " + i3);
        } else if (remaining() - i >= i3) {
            return true;
        } else {
            return false;
        }
    }

    public int indexOf(byte b) {
        if (hasArray()) {
            int arrayOffset = arrayOffset();
            int limit = limit() + arrayOffset;
            byte[] array = array();
            for (int position = position() + arrayOffset; position < limit; position++) {
                if (array[position] == b) {
                    return position - arrayOffset;
                }
            }
            return -1;
        }
        int limit2 = limit();
        for (int position2 = position(); position2 < limit2; position2++) {
            if (get(position2) == b) {
                return position2;
            }
        }
        return -1;
    }

    public IoBuffer skip(int i) {
        autoExpand(i);
        return position(position() + i);
    }

    public IoBuffer fill(byte b, int i) {
        autoExpand(i);
        int i2 = i >>> 3;
        int i3 = i & 7;
        if (i2 > 0) {
            long j = (long) ((b << 8) | b | (b << Ascii.DLE) | (b << Ascii.CAN));
            long j2 = j | (j << 32);
            while (i2 > 0) {
                putLong(j2);
                i2--;
            }
        }
        int i4 = i3 >>> 2;
        int i5 = i3 & 3;
        if (i4 > 0) {
            putInt((b << 8) | b | (b << Ascii.DLE) | (b << Ascii.CAN));
        }
        int i6 = i5 >> 1;
        int i7 = i5 & 1;
        if (i6 > 0) {
            putShort((short) ((b << 8) | b));
        }
        if (i7 > 0) {
            put(b);
        }
        return this;
    }

    public IoBuffer fillAndReset(byte b, int i) {
        autoExpand(i);
        int position = position();
        try {
            fill(b, i);
            return this;
        } finally {
            position(position);
        }
    }

    public IoBuffer fill(int i) {
        autoExpand(i);
        int i2 = i & 7;
        for (int i3 = i >>> 3; i3 > 0; i3--) {
            putLong(0);
        }
        int i4 = i2 >>> 2;
        int i5 = i2 & 3;
        if (i4 > 0) {
            putInt(0);
        }
        int i6 = i5 >> 1;
        int i7 = i5 & 1;
        if (i6 > 0) {
            putShort(0);
        }
        if (i7 > 0) {
            put((byte) 0);
        }
        return this;
    }

    public IoBuffer fillAndReset(int i) {
        autoExpand(i);
        int position = position();
        try {
            fill(i);
            return this;
        } finally {
            position(position);
        }
    }

    public <E extends Enum<E>> E getEnum(Class<E> cls) {
        return (Enum) toEnum(cls, getUnsigned());
    }

    public <E extends Enum<E>> E getEnum(int i, Class<E> cls) {
        return (Enum) toEnum(cls, getUnsigned(i));
    }

    public <E extends Enum<E>> E getEnumShort(Class<E> cls) {
        return (Enum) toEnum(cls, getUnsignedShort());
    }

    public <E extends Enum<E>> E getEnumShort(int i, Class<E> cls) {
        return (Enum) toEnum(cls, getUnsignedShort(i));
    }

    public <E extends Enum<E>> E getEnumInt(Class<E> cls) {
        return (Enum) toEnum(cls, getInt());
    }

    public <E extends Enum<E>> E getEnumInt(int i, Class<E> cls) {
        return (Enum) toEnum(cls, getInt(i));
    }

    public IoBuffer putEnum(Enum<?> enumR) {
        if (((long) enumR.ordinal()) <= BYTE_MASK) {
            return put((byte) enumR.ordinal());
        }
        throw new IllegalArgumentException(enumConversionErrorMessage(enumR, "byte"));
    }

    public IoBuffer putEnum(int i, Enum<?> enumR) {
        if (((long) enumR.ordinal()) <= BYTE_MASK) {
            return put(i, (byte) enumR.ordinal());
        }
        throw new IllegalArgumentException(enumConversionErrorMessage(enumR, "byte"));
    }

    public IoBuffer putEnumShort(Enum<?> enumR) {
        if (((long) enumR.ordinal()) <= 65535) {
            return putShort((short) enumR.ordinal());
        }
        throw new IllegalArgumentException(enumConversionErrorMessage(enumR, "short"));
    }

    public IoBuffer putEnumShort(int i, Enum<?> enumR) {
        if (((long) enumR.ordinal()) <= 65535) {
            return putShort(i, (short) enumR.ordinal());
        }
        throw new IllegalArgumentException(enumConversionErrorMessage(enumR, "short"));
    }

    public IoBuffer putEnumInt(Enum<?> enumR) {
        return putInt(enumR.ordinal());
    }

    public IoBuffer putEnumInt(int i, Enum<?> enumR) {
        return putInt(i, enumR.ordinal());
    }

    private <E> E toEnum(Class<E> cls, int i) {
        E[] enumConstants = cls.getEnumConstants();
        if (i <= enumConstants.length) {
            return enumConstants[i];
        }
        throw new IndexOutOfBoundsException(String.format("%d is too large of an ordinal to convert to the enum %s", new Object[]{Integer.valueOf(i), cls.getName()}));
    }

    private String enumConversionErrorMessage(Enum<?> enumR, String str) {
        return String.format("%s.%s has an ordinal value too large for a %s", new Object[]{enumR.getClass().getName(), enumR.name(), str});
    }

    public <E extends Enum<E>> EnumSet<E> getEnumSet(Class<E> cls) {
        return toEnumSet(cls, ((long) get()) & BYTE_MASK);
    }

    public <E extends Enum<E>> EnumSet<E> getEnumSet(int i, Class<E> cls) {
        return toEnumSet(cls, ((long) get(i)) & BYTE_MASK);
    }

    public <E extends Enum<E>> EnumSet<E> getEnumSetShort(Class<E> cls) {
        return toEnumSet(cls, ((long) getShort()) & 65535);
    }

    public <E extends Enum<E>> EnumSet<E> getEnumSetShort(int i, Class<E> cls) {
        return toEnumSet(cls, ((long) getShort(i)) & 65535);
    }

    public <E extends Enum<E>> EnumSet<E> getEnumSetInt(Class<E> cls) {
        return toEnumSet(cls, ((long) getInt()) & INT_MASK);
    }

    public <E extends Enum<E>> EnumSet<E> getEnumSetInt(int i, Class<E> cls) {
        return toEnumSet(cls, ((long) getInt(i)) & INT_MASK);
    }

    public <E extends Enum<E>> EnumSet<E> getEnumSetLong(Class<E> cls) {
        return toEnumSet(cls, getLong());
    }

    public <E extends Enum<E>> EnumSet<E> getEnumSetLong(int i, Class<E> cls) {
        return toEnumSet(cls, getLong(i));
    }

    private <E extends Enum<E>> EnumSet<E> toEnumSet(Class<E> cls, long j) {
        EnumSet<E> noneOf = EnumSet.noneOf(cls);
        long j2 = 1;
        for (Enum enumR : (Enum[]) cls.getEnumConstants()) {
            if ((j2 & j) == j2) {
                noneOf.add(enumR);
            }
            j2 <<= 1;
        }
        return noneOf;
    }

    public <E extends Enum<E>> IoBuffer putEnumSet(Set<E> set) {
        long j = toLong(set);
        if ((-256 & j) == 0) {
            return put((byte) ((int) j));
        }
        throw new IllegalArgumentException("The enum set is too large to fit in a byte: " + set);
    }

    public <E extends Enum<E>> IoBuffer putEnumSet(int i, Set<E> set) {
        long j = toLong(set);
        if ((-256 & j) == 0) {
            return put(i, (byte) ((int) j));
        }
        throw new IllegalArgumentException("The enum set is too large to fit in a byte: " + set);
    }

    public <E extends Enum<E>> IoBuffer putEnumSetShort(Set<E> set) {
        long j = toLong(set);
        if ((-65536 & j) == 0) {
            return putShort((short) ((int) j));
        }
        throw new IllegalArgumentException("The enum set is too large to fit in a short: " + set);
    }

    public <E extends Enum<E>> IoBuffer putEnumSetShort(int i, Set<E> set) {
        long j = toLong(set);
        if ((-65536 & j) == 0) {
            return putShort(i, (short) ((int) j));
        }
        throw new IllegalArgumentException("The enum set is too large to fit in a short: " + set);
    }

    public <E extends Enum<E>> IoBuffer putEnumSetInt(Set<E> set) {
        long j = toLong(set);
        if ((-4294967296L & j) == 0) {
            return putInt((int) j);
        }
        throw new IllegalArgumentException("The enum set is too large to fit in an int: " + set);
    }

    public <E extends Enum<E>> IoBuffer putEnumSetInt(int i, Set<E> set) {
        long j = toLong(set);
        if ((-4294967296L & j) == 0) {
            return putInt(i, (int) j);
        }
        throw new IllegalArgumentException("The enum set is too large to fit in an int: " + set);
    }

    public <E extends Enum<E>> IoBuffer putEnumSetLong(Set<E> set) {
        return putLong(toLong(set));
    }

    public <E extends Enum<E>> IoBuffer putEnumSetLong(int i, Set<E> set) {
        return putLong(i, toLong(set));
    }

    private <E extends Enum<E>> long toLong(Set<E> set) {
        long j = 0;
        for (E e : set) {
            if (e.ordinal() < 64) {
                j |= 1 << e.ordinal();
            } else {
                throw new IllegalArgumentException("The enum set is too large to fit in a bit vector: " + set);
            }
        }
        return j;
    }

    private IoBuffer autoExpand(int i) {
        if (isAutoExpand()) {
            expand(i, true);
        }
        return this;
    }

    private IoBuffer autoExpand(int i, int i2) {
        if (isAutoExpand()) {
            expand(i, i2, true);
        }
        return this;
    }

    private static void checkFieldSize(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("fieldSize cannot be negative: " + i);
        }
    }
}
