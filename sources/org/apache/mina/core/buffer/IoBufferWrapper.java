package org.apache.mina.core.buffer;

import java.io.InputStream;
import java.io.OutputStream;
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
import java.util.EnumSet;
import java.util.Set;

public class IoBufferWrapper extends IoBuffer {
    private final IoBuffer buf;

    protected IoBufferWrapper(IoBuffer ioBuffer) {
        if (ioBuffer != null) {
            this.buf = ioBuffer;
            return;
        }
        throw new IllegalArgumentException("buf");
    }

    public IoBuffer getParentBuffer() {
        return this.buf;
    }

    public boolean isDirect() {
        return this.buf.isDirect();
    }

    public ByteBuffer buf() {
        return this.buf.buf();
    }

    public int capacity() {
        return this.buf.capacity();
    }

    public int position() {
        return this.buf.position();
    }

    public IoBuffer position(int i) {
        this.buf.position(i);
        return this;
    }

    public int limit() {
        return this.buf.limit();
    }

    public IoBuffer limit(int i) {
        this.buf.limit(i);
        return this;
    }

    public IoBuffer mark() {
        this.buf.mark();
        return this;
    }

    public IoBuffer reset() {
        this.buf.reset();
        return this;
    }

    public IoBuffer clear() {
        this.buf.clear();
        return this;
    }

    public IoBuffer sweep() {
        this.buf.sweep();
        return this;
    }

    public IoBuffer sweep(byte b) {
        this.buf.sweep(b);
        return this;
    }

    public IoBuffer flip() {
        this.buf.flip();
        return this;
    }

    public IoBuffer rewind() {
        this.buf.rewind();
        return this;
    }

    public int remaining() {
        return this.buf.remaining();
    }

    public boolean hasRemaining() {
        return this.buf.hasRemaining();
    }

    public byte get() {
        return this.buf.get();
    }

    public short getUnsigned() {
        return this.buf.getUnsigned();
    }

    public IoBuffer put(byte b) {
        this.buf.put(b);
        return this;
    }

    public byte get(int i) {
        return this.buf.get(i);
    }

    public short getUnsigned(int i) {
        return this.buf.getUnsigned(i);
    }

    public IoBuffer put(int i, byte b) {
        this.buf.put(i, b);
        return this;
    }

    public IoBuffer get(byte[] bArr, int i, int i2) {
        this.buf.get(bArr, i, i2);
        return this;
    }

    public IoBuffer getSlice(int i, int i2) {
        return this.buf.getSlice(i, i2);
    }

    public IoBuffer getSlice(int i) {
        return this.buf.getSlice(i);
    }

    public IoBuffer get(byte[] bArr) {
        this.buf.get(bArr);
        return this;
    }

    public IoBuffer put(IoBuffer ioBuffer) {
        this.buf.put(ioBuffer);
        return this;
    }

    public IoBuffer put(ByteBuffer byteBuffer) {
        this.buf.put(byteBuffer);
        return this;
    }

    public IoBuffer put(byte[] bArr, int i, int i2) {
        this.buf.put(bArr, i, i2);
        return this;
    }

    public IoBuffer put(byte[] bArr) {
        this.buf.put(bArr);
        return this;
    }

    public IoBuffer compact() {
        this.buf.compact();
        return this;
    }

    public String toString() {
        return this.buf.toString();
    }

    public int hashCode() {
        return this.buf.hashCode();
    }

    public boolean equals(Object obj) {
        return this.buf.equals(obj);
    }

    public int compareTo(IoBuffer ioBuffer) {
        return this.buf.compareTo(ioBuffer);
    }

    public ByteOrder order() {
        return this.buf.order();
    }

    public IoBuffer order(ByteOrder byteOrder) {
        this.buf.order(byteOrder);
        return this;
    }

    public char getChar() {
        return this.buf.getChar();
    }

    public IoBuffer putChar(char c) {
        this.buf.putChar(c);
        return this;
    }

    public char getChar(int i) {
        return this.buf.getChar(i);
    }

    public IoBuffer putChar(int i, char c) {
        this.buf.putChar(i, c);
        return this;
    }

    public CharBuffer asCharBuffer() {
        return this.buf.asCharBuffer();
    }

    public short getShort() {
        return this.buf.getShort();
    }

    public int getUnsignedShort() {
        return this.buf.getUnsignedShort();
    }

    public IoBuffer putShort(short s) {
        this.buf.putShort(s);
        return this;
    }

    public short getShort(int i) {
        return this.buf.getShort(i);
    }

    public int getUnsignedShort(int i) {
        return this.buf.getUnsignedShort(i);
    }

    public IoBuffer putShort(int i, short s) {
        this.buf.putShort(i, s);
        return this;
    }

    public ShortBuffer asShortBuffer() {
        return this.buf.asShortBuffer();
    }

    public int getInt() {
        return this.buf.getInt();
    }

    public long getUnsignedInt() {
        return this.buf.getUnsignedInt();
    }

    public IoBuffer putInt(int i) {
        this.buf.putInt(i);
        return this;
    }

    public IoBuffer putUnsignedInt(byte b) {
        this.buf.putUnsignedInt(b);
        return this;
    }

    public IoBuffer putUnsignedInt(int i, byte b) {
        this.buf.putUnsignedInt(i, b);
        return this;
    }

    public IoBuffer putUnsignedInt(short s) {
        this.buf.putUnsignedInt(s);
        return this;
    }

    public IoBuffer putUnsignedInt(int i, short s) {
        this.buf.putUnsignedInt(i, s);
        return this;
    }

    public IoBuffer putUnsignedInt(int i) {
        this.buf.putUnsignedInt(i);
        return this;
    }

    public IoBuffer putUnsignedInt(int i, int i2) {
        this.buf.putUnsignedInt(i, i2);
        return this;
    }

    public IoBuffer putUnsignedInt(long j) {
        this.buf.putUnsignedInt(j);
        return this;
    }

    public IoBuffer putUnsignedInt(int i, long j) {
        this.buf.putUnsignedInt(i, j);
        return this;
    }

    public IoBuffer putUnsignedShort(byte b) {
        this.buf.putUnsignedShort(b);
        return this;
    }

    public IoBuffer putUnsignedShort(int i, byte b) {
        this.buf.putUnsignedShort(i, b);
        return this;
    }

    public IoBuffer putUnsignedShort(short s) {
        this.buf.putUnsignedShort(s);
        return this;
    }

    public IoBuffer putUnsignedShort(int i, short s) {
        this.buf.putUnsignedShort(i, s);
        return this;
    }

    public IoBuffer putUnsignedShort(int i) {
        this.buf.putUnsignedShort(i);
        return this;
    }

    public IoBuffer putUnsignedShort(int i, int i2) {
        this.buf.putUnsignedShort(i, i2);
        return this;
    }

    public IoBuffer putUnsignedShort(long j) {
        this.buf.putUnsignedShort(j);
        return this;
    }

    public IoBuffer putUnsignedShort(int i, long j) {
        this.buf.putUnsignedShort(i, j);
        return this;
    }

    public int getInt(int i) {
        return this.buf.getInt(i);
    }

    public long getUnsignedInt(int i) {
        return this.buf.getUnsignedInt(i);
    }

    public IoBuffer putInt(int i, int i2) {
        this.buf.putInt(i, i2);
        return this;
    }

    public IntBuffer asIntBuffer() {
        return this.buf.asIntBuffer();
    }

    public long getLong() {
        return this.buf.getLong();
    }

    public IoBuffer putLong(long j) {
        this.buf.putLong(j);
        return this;
    }

    public long getLong(int i) {
        return this.buf.getLong(i);
    }

    public IoBuffer putLong(int i, long j) {
        this.buf.putLong(i, j);
        return this;
    }

    public LongBuffer asLongBuffer() {
        return this.buf.asLongBuffer();
    }

    public float getFloat() {
        return this.buf.getFloat();
    }

    public IoBuffer putFloat(float f) {
        this.buf.putFloat(f);
        return this;
    }

    public float getFloat(int i) {
        return this.buf.getFloat(i);
    }

    public IoBuffer putFloat(int i, float f) {
        this.buf.putFloat(i, f);
        return this;
    }

    public FloatBuffer asFloatBuffer() {
        return this.buf.asFloatBuffer();
    }

    public double getDouble() {
        return this.buf.getDouble();
    }

    public IoBuffer putDouble(double d) {
        this.buf.putDouble(d);
        return this;
    }

    public double getDouble(int i) {
        return this.buf.getDouble(i);
    }

    public IoBuffer putDouble(int i, double d) {
        this.buf.putDouble(i, d);
        return this;
    }

    public DoubleBuffer asDoubleBuffer() {
        return this.buf.asDoubleBuffer();
    }

    public String getHexDump() {
        return this.buf.getHexDump();
    }

    public String getString(int i, CharsetDecoder charsetDecoder) throws CharacterCodingException {
        return this.buf.getString(i, charsetDecoder);
    }

    public String getString(CharsetDecoder charsetDecoder) throws CharacterCodingException {
        return this.buf.getString(charsetDecoder);
    }

    public String getPrefixedString(CharsetDecoder charsetDecoder) throws CharacterCodingException {
        return this.buf.getPrefixedString(charsetDecoder);
    }

    public String getPrefixedString(int i, CharsetDecoder charsetDecoder) throws CharacterCodingException {
        return this.buf.getPrefixedString(i, charsetDecoder);
    }

    public IoBuffer putString(CharSequence charSequence, int i, CharsetEncoder charsetEncoder) throws CharacterCodingException {
        this.buf.putString(charSequence, i, charsetEncoder);
        return this;
    }

    public IoBuffer putString(CharSequence charSequence, CharsetEncoder charsetEncoder) throws CharacterCodingException {
        this.buf.putString(charSequence, charsetEncoder);
        return this;
    }

    public IoBuffer putPrefixedString(CharSequence charSequence, CharsetEncoder charsetEncoder) throws CharacterCodingException {
        this.buf.putPrefixedString(charSequence, charsetEncoder);
        return this;
    }

    public IoBuffer putPrefixedString(CharSequence charSequence, int i, CharsetEncoder charsetEncoder) throws CharacterCodingException {
        this.buf.putPrefixedString(charSequence, i, charsetEncoder);
        return this;
    }

    public IoBuffer putPrefixedString(CharSequence charSequence, int i, int i2, CharsetEncoder charsetEncoder) throws CharacterCodingException {
        this.buf.putPrefixedString(charSequence, i, i2, charsetEncoder);
        return this;
    }

    public IoBuffer putPrefixedString(CharSequence charSequence, int i, int i2, byte b, CharsetEncoder charsetEncoder) throws CharacterCodingException {
        this.buf.putPrefixedString(charSequence, i, i2, b, charsetEncoder);
        return this;
    }

    public IoBuffer skip(int i) {
        this.buf.skip(i);
        return this;
    }

    public IoBuffer fill(byte b, int i) {
        this.buf.fill(b, i);
        return this;
    }

    public IoBuffer fillAndReset(byte b, int i) {
        this.buf.fillAndReset(b, i);
        return this;
    }

    public IoBuffer fill(int i) {
        this.buf.fill(i);
        return this;
    }

    public IoBuffer fillAndReset(int i) {
        this.buf.fillAndReset(i);
        return this;
    }

    public boolean isAutoExpand() {
        return this.buf.isAutoExpand();
    }

    public IoBuffer setAutoExpand(boolean z) {
        this.buf.setAutoExpand(z);
        return this;
    }

    public IoBuffer expand(int i, int i2) {
        this.buf.expand(i, i2);
        return this;
    }

    public IoBuffer expand(int i) {
        this.buf.expand(i);
        return this;
    }

    public Object getObject() throws ClassNotFoundException {
        return this.buf.getObject();
    }

    public Object getObject(ClassLoader classLoader) throws ClassNotFoundException {
        return this.buf.getObject(classLoader);
    }

    public IoBuffer putObject(Object obj) {
        this.buf.putObject(obj);
        return this;
    }

    public InputStream asInputStream() {
        return this.buf.asInputStream();
    }

    public OutputStream asOutputStream() {
        return this.buf.asOutputStream();
    }

    public IoBuffer duplicate() {
        return this.buf.duplicate();
    }

    public IoBuffer slice() {
        return this.buf.slice();
    }

    public IoBuffer asReadOnlyBuffer() {
        return this.buf.asReadOnlyBuffer();
    }

    public byte[] array() {
        return this.buf.array();
    }

    public int arrayOffset() {
        return this.buf.arrayOffset();
    }

    public int minimumCapacity() {
        return this.buf.minimumCapacity();
    }

    public IoBuffer minimumCapacity(int i) {
        this.buf.minimumCapacity(i);
        return this;
    }

    public IoBuffer capacity(int i) {
        this.buf.capacity(i);
        return this;
    }

    public boolean isReadOnly() {
        return this.buf.isReadOnly();
    }

    public int markValue() {
        return this.buf.markValue();
    }

    public boolean hasArray() {
        return this.buf.hasArray();
    }

    public void free() {
        this.buf.free();
    }

    public boolean isDerived() {
        return this.buf.isDerived();
    }

    public boolean isAutoShrink() {
        return this.buf.isAutoShrink();
    }

    public IoBuffer setAutoShrink(boolean z) {
        this.buf.setAutoShrink(z);
        return this;
    }

    public IoBuffer shrink() {
        this.buf.shrink();
        return this;
    }

    public int getMediumInt() {
        return this.buf.getMediumInt();
    }

    public int getUnsignedMediumInt() {
        return this.buf.getUnsignedMediumInt();
    }

    public int getMediumInt(int i) {
        return this.buf.getMediumInt(i);
    }

    public int getUnsignedMediumInt(int i) {
        return this.buf.getUnsignedMediumInt(i);
    }

    public IoBuffer putMediumInt(int i) {
        this.buf.putMediumInt(i);
        return this;
    }

    public IoBuffer putMediumInt(int i, int i2) {
        this.buf.putMediumInt(i, i2);
        return this;
    }

    public String getHexDump(int i) {
        return this.buf.getHexDump(i);
    }

    public boolean prefixedDataAvailable(int i) {
        return this.buf.prefixedDataAvailable(i);
    }

    public boolean prefixedDataAvailable(int i, int i2) {
        return this.buf.prefixedDataAvailable(i, i2);
    }

    public int indexOf(byte b) {
        return this.buf.indexOf(b);
    }

    public <E extends Enum<E>> E getEnum(Class<E> cls) {
        return this.buf.getEnum(cls);
    }

    public <E extends Enum<E>> E getEnum(int i, Class<E> cls) {
        return this.buf.getEnum(i, cls);
    }

    public <E extends Enum<E>> E getEnumShort(Class<E> cls) {
        return this.buf.getEnumShort(cls);
    }

    public <E extends Enum<E>> E getEnumShort(int i, Class<E> cls) {
        return this.buf.getEnumShort(i, cls);
    }

    public <E extends Enum<E>> E getEnumInt(Class<E> cls) {
        return this.buf.getEnumInt(cls);
    }

    public <E extends Enum<E>> E getEnumInt(int i, Class<E> cls) {
        return this.buf.getEnumInt(i, cls);
    }

    public IoBuffer putEnum(Enum<?> enumR) {
        this.buf.putEnum(enumR);
        return this;
    }

    public IoBuffer putEnum(int i, Enum<?> enumR) {
        this.buf.putEnum(i, enumR);
        return this;
    }

    public IoBuffer putEnumShort(Enum<?> enumR) {
        this.buf.putEnumShort(enumR);
        return this;
    }

    public IoBuffer putEnumShort(int i, Enum<?> enumR) {
        this.buf.putEnumShort(i, enumR);
        return this;
    }

    public IoBuffer putEnumInt(Enum<?> enumR) {
        this.buf.putEnumInt(enumR);
        return this;
    }

    public IoBuffer putEnumInt(int i, Enum<?> enumR) {
        this.buf.putEnumInt(i, enumR);
        return this;
    }

    public <E extends Enum<E>> EnumSet<E> getEnumSet(Class<E> cls) {
        return this.buf.getEnumSet(cls);
    }

    public <E extends Enum<E>> EnumSet<E> getEnumSet(int i, Class<E> cls) {
        return this.buf.getEnumSet(i, cls);
    }

    public <E extends Enum<E>> EnumSet<E> getEnumSetShort(Class<E> cls) {
        return this.buf.getEnumSetShort(cls);
    }

    public <E extends Enum<E>> EnumSet<E> getEnumSetShort(int i, Class<E> cls) {
        return this.buf.getEnumSetShort(i, cls);
    }

    public <E extends Enum<E>> EnumSet<E> getEnumSetInt(Class<E> cls) {
        return this.buf.getEnumSetInt(cls);
    }

    public <E extends Enum<E>> EnumSet<E> getEnumSetInt(int i, Class<E> cls) {
        return this.buf.getEnumSetInt(i, cls);
    }

    public <E extends Enum<E>> EnumSet<E> getEnumSetLong(Class<E> cls) {
        return this.buf.getEnumSetLong(cls);
    }

    public <E extends Enum<E>> EnumSet<E> getEnumSetLong(int i, Class<E> cls) {
        return this.buf.getEnumSetLong(i, cls);
    }

    public <E extends Enum<E>> IoBuffer putEnumSet(Set<E> set) {
        this.buf.putEnumSet(set);
        return this;
    }

    public <E extends Enum<E>> IoBuffer putEnumSet(int i, Set<E> set) {
        this.buf.putEnumSet(i, set);
        return this;
    }

    public <E extends Enum<E>> IoBuffer putEnumSetShort(Set<E> set) {
        this.buf.putEnumSetShort(set);
        return this;
    }

    public <E extends Enum<E>> IoBuffer putEnumSetShort(int i, Set<E> set) {
        this.buf.putEnumSetShort(i, set);
        return this;
    }

    public <E extends Enum<E>> IoBuffer putEnumSetInt(Set<E> set) {
        this.buf.putEnumSetInt(set);
        return this;
    }

    public <E extends Enum<E>> IoBuffer putEnumSetInt(int i, Set<E> set) {
        this.buf.putEnumSetInt(i, set);
        return this;
    }

    public <E extends Enum<E>> IoBuffer putEnumSetLong(Set<E> set) {
        this.buf.putEnumSetLong(set);
        return this;
    }

    public <E extends Enum<E>> IoBuffer putEnumSetLong(int i, Set<E> set) {
        this.buf.putEnumSetLong(i, set);
        return this;
    }

    public IoBuffer putUnsigned(byte b) {
        this.buf.putUnsigned(b);
        return this;
    }

    public IoBuffer putUnsigned(int i, byte b) {
        this.buf.putUnsigned(i, b);
        return this;
    }

    public IoBuffer putUnsigned(short s) {
        this.buf.putUnsigned(s);
        return this;
    }

    public IoBuffer putUnsigned(int i, short s) {
        this.buf.putUnsigned(i, s);
        return this;
    }

    public IoBuffer putUnsigned(int i) {
        this.buf.putUnsigned(i);
        return this;
    }

    public IoBuffer putUnsigned(int i, int i2) {
        this.buf.putUnsigned(i, i2);
        return this;
    }

    public IoBuffer putUnsigned(long j) {
        this.buf.putUnsigned(j);
        return this;
    }

    public IoBuffer putUnsigned(int i, long j) {
        this.buf.putUnsigned(i, j);
        return this;
    }
}
