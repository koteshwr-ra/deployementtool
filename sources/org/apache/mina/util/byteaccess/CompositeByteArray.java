package org.apache.mina.util.byteaccess;

import com.alibaba.android.arouter.utils.Consts;
import com.google.common.base.Ascii;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Collections;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.util.byteaccess.ByteArray;
import org.apache.mina.util.byteaccess.ByteArrayList;

public final class CompositeByteArray extends AbstractByteArray {
    /* access modifiers changed from: private */
    public final ByteArrayList bas;
    /* access modifiers changed from: private */
    public final ByteArrayFactory byteArrayFactory;
    /* access modifiers changed from: private */
    public ByteOrder order;

    public interface CursorListener {
        void enteredFirstComponent(int i, ByteArray byteArray);

        void enteredLastComponent(int i, ByteArray byteArray);

        void enteredNextComponent(int i, ByteArray byteArray);

        void enteredPreviousComponent(int i, ByteArray byteArray);
    }

    public CompositeByteArray() {
        this((ByteArrayFactory) null);
    }

    public CompositeByteArray(ByteArrayFactory byteArrayFactory2) {
        this.bas = new ByteArrayList();
        this.byteArrayFactory = byteArrayFactory2;
    }

    public ByteArray getFirst() {
        if (this.bas.isEmpty()) {
            return null;
        }
        return this.bas.getFirst().getByteArray();
    }

    public void addFirst(ByteArray byteArray) {
        addHook(byteArray);
        this.bas.addFirst(byteArray);
    }

    public ByteArray removeFirst() {
        ByteArrayList.Node removeFirst = this.bas.removeFirst();
        if (removeFirst == null) {
            return null;
        }
        return removeFirst.getByteArray();
    }

    public ByteArray removeTo(int i) {
        if (i < first() || i > last()) {
            throw new IndexOutOfBoundsException();
        }
        CompositeByteArray compositeByteArray = new CompositeByteArray(this.byteArrayFactory);
        int first = first();
        while (true) {
            i -= first;
            while (i > 0) {
                final ByteArray removeFirst = removeFirst();
                if (removeFirst.last() <= i) {
                    compositeByteArray.addLast(removeFirst);
                    first = removeFirst.last();
                } else {
                    IoBuffer singleIoBuffer = removeFirst.getSingleIoBuffer();
                    int limit = singleIoBuffer.limit();
                    singleIoBuffer.position(0);
                    singleIoBuffer.limit(i);
                    IoBuffer slice = singleIoBuffer.slice();
                    singleIoBuffer.position(i);
                    singleIoBuffer.limit(limit);
                    IoBuffer slice2 = singleIoBuffer.slice();
                    AnonymousClass1 r3 = new BufferByteArray(slice) {
                        public void free() {
                        }
                    };
                    compositeByteArray.addLast(r3);
                    i -= r3.last();
                    addFirst(new BufferByteArray(slice2) {
                        public void free() {
                            removeFirst.free();
                        }
                    });
                }
            }
            return compositeByteArray;
        }
    }

    public void addLast(ByteArray byteArray) {
        addHook(byteArray);
        this.bas.addLast(byteArray);
    }

    public ByteArray removeLast() {
        ByteArrayList.Node removeLast = this.bas.removeLast();
        if (removeLast == null) {
            return null;
        }
        return removeLast.getByteArray();
    }

    public void free() {
        while (!this.bas.isEmpty()) {
            this.bas.getLast().getByteArray().free();
            this.bas.removeLast();
        }
    }

    /* access modifiers changed from: private */
    public void checkBounds(int i, int i2) {
        int i3 = i2 + i;
        if (i < first()) {
            throw new IndexOutOfBoundsException("Index " + i + " less than start " + first() + Consts.DOT);
        } else if (i3 > last()) {
            throw new IndexOutOfBoundsException("Index " + i3 + " greater than length " + last() + Consts.DOT);
        }
    }

    public Iterable<IoBuffer> getIoBuffers() {
        if (this.bas.isEmpty()) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        ByteArrayList.Node first = this.bas.getFirst();
        for (IoBuffer add : first.getByteArray().getIoBuffers()) {
            arrayList.add(add);
        }
        while (first.hasNextNode()) {
            first = first.getNextNode();
            for (IoBuffer add2 : first.getByteArray().getIoBuffers()) {
                arrayList.add(add2);
            }
        }
        return arrayList;
    }

    public IoBuffer getSingleIoBuffer() {
        if (this.byteArrayFactory == null) {
            throw new IllegalStateException("Can't get single buffer from CompositeByteArray unless it has a ByteArrayFactory.");
        } else if (this.bas.isEmpty()) {
            return this.byteArrayFactory.create(1).getSingleIoBuffer();
        } else {
            int last = last() - first();
            ByteArray byteArray = this.bas.getFirst().getByteArray();
            if (byteArray.last() == last) {
                return byteArray.getSingleIoBuffer();
            }
            ByteArray create = this.byteArrayFactory.create(last);
            IoBuffer singleIoBuffer = create.getSingleIoBuffer();
            cursor().put(singleIoBuffer);
            while (!this.bas.isEmpty()) {
                ByteArray byteArray2 = this.bas.getLast().getByteArray();
                this.bas.removeLast();
                byteArray2.free();
            }
            this.bas.addLast(create);
            return singleIoBuffer;
        }
    }

    public ByteArray.Cursor cursor() {
        return new CursorImpl(this);
    }

    public ByteArray.Cursor cursor(int i) {
        return new CursorImpl(this, i);
    }

    public ByteArray.Cursor cursor(CursorListener cursorListener) {
        return new CursorImpl(this, cursorListener);
    }

    public ByteArray.Cursor cursor(int i, CursorListener cursorListener) {
        return new CursorImpl(i, cursorListener);
    }

    public ByteArray slice(int i, int i2) {
        return cursor(i).slice(i2);
    }

    public byte get(int i) {
        return cursor(i).get();
    }

    public void put(int i, byte b) {
        cursor(i).put(b);
    }

    public void get(int i, IoBuffer ioBuffer) {
        cursor(i).get(ioBuffer);
    }

    public void put(int i, IoBuffer ioBuffer) {
        cursor(i).put(ioBuffer);
    }

    public int first() {
        return this.bas.firstByte();
    }

    public int last() {
        return this.bas.lastByte();
    }

    private void addHook(ByteArray byteArray) {
        if (byteArray.first() == 0) {
            ByteOrder byteOrder = this.order;
            if (byteOrder == null) {
                this.order = byteArray.order();
            } else if (!byteOrder.equals(byteArray.order())) {
                throw new IllegalArgumentException("Cannot add byte array with different byte order: " + byteArray.order());
            }
        } else {
            throw new IllegalArgumentException("Cannot add byte array that doesn't start from 0: " + byteArray.first());
        }
    }

    public ByteOrder order() {
        ByteOrder byteOrder = this.order;
        if (byteOrder != null) {
            return byteOrder;
        }
        throw new IllegalStateException("Byte order not yet set.");
    }

    public void order(ByteOrder byteOrder) {
        if (byteOrder == null || !byteOrder.equals(this.order)) {
            this.order = byteOrder;
            if (!this.bas.isEmpty()) {
                for (ByteArrayList.Node first = this.bas.getFirst(); first.hasNextNode(); first = first.getNextNode()) {
                    first.getByteArray().order(byteOrder);
                }
            }
        }
    }

    public short getShort(int i) {
        return cursor(i).getShort();
    }

    public void putShort(int i, short s) {
        cursor(i).putShort(s);
    }

    public int getInt(int i) {
        return cursor(i).getInt();
    }

    public void putInt(int i, int i2) {
        cursor(i).putInt(i2);
    }

    public long getLong(int i) {
        return cursor(i).getLong();
    }

    public void putLong(int i, long j) {
        cursor(i).putLong(j);
    }

    public float getFloat(int i) {
        return cursor(i).getFloat();
    }

    public void putFloat(int i, float f) {
        cursor(i).putFloat(f);
    }

    public double getDouble(int i) {
        return cursor(i).getDouble();
    }

    public void putDouble(int i, double d) {
        cursor(i).putDouble(d);
    }

    public char getChar(int i) {
        return cursor(i).getChar();
    }

    public void putChar(int i, char c) {
        cursor(i).putChar(c);
    }

    private class CursorImpl implements ByteArray.Cursor {
        private ByteArray.Cursor componentCursor;
        private int componentIndex;
        private ByteArrayList.Node componentNode;
        private int index;
        private final CursorListener listener;

        public CursorImpl(CompositeByteArray compositeByteArray) {
            this(0, (CursorListener) null);
        }

        public CursorImpl(CompositeByteArray compositeByteArray, int i) {
            this(i, (CursorListener) null);
        }

        public CursorImpl(CompositeByteArray compositeByteArray, CursorListener cursorListener) {
            this(0, cursorListener);
        }

        public CursorImpl(int i, CursorListener cursorListener) {
            this.index = i;
            this.listener = cursorListener;
        }

        public int getIndex() {
            return this.index;
        }

        public void setIndex(int i) {
            CompositeByteArray.this.checkBounds(i, 0);
            this.index = i;
        }

        public void skip(int i) {
            setIndex(this.index + i);
        }

        public ByteArray slice(int i) {
            CompositeByteArray compositeByteArray = new CompositeByteArray(CompositeByteArray.this.byteArrayFactory);
            while (i > 0) {
                prepareForAccess(i);
                int min = Math.min(i, this.componentCursor.getRemaining());
                compositeByteArray.addLast(this.componentCursor.slice(min));
                this.index += min;
                i -= min;
            }
            return compositeByteArray;
        }

        public ByteOrder order() {
            return CompositeByteArray.this.order();
        }

        private void prepareForAccess(int i) {
            ByteArrayList.Node node = this.componentNode;
            if (node != null && node.isRemoved()) {
                this.componentNode = null;
                this.componentCursor = null;
            }
            CompositeByteArray.this.checkBounds(this.index, i);
            ByteArrayList.Node node2 = this.componentNode;
            if (node2 == null) {
                if (this.index <= ((CompositeByteArray.this.last() - CompositeByteArray.this.first()) / 2) + CompositeByteArray.this.first()) {
                    this.componentNode = CompositeByteArray.this.bas.getFirst();
                    int first = CompositeByteArray.this.first();
                    this.componentIndex = first;
                    CursorListener cursorListener = this.listener;
                    if (cursorListener != null) {
                        cursorListener.enteredFirstComponent(first, this.componentNode.getByteArray());
                    }
                } else {
                    this.componentNode = CompositeByteArray.this.bas.getLast();
                    int last = CompositeByteArray.this.last() - this.componentNode.getByteArray().last();
                    this.componentIndex = last;
                    CursorListener cursorListener2 = this.listener;
                    if (cursorListener2 != null) {
                        cursorListener2.enteredLastComponent(last, this.componentNode.getByteArray());
                    }
                }
            }
            while (this.index < this.componentIndex) {
                ByteArrayList.Node previousNode = this.componentNode.getPreviousNode();
                this.componentNode = previousNode;
                int last2 = this.componentIndex - previousNode.getByteArray().last();
                this.componentIndex = last2;
                CursorListener cursorListener3 = this.listener;
                if (cursorListener3 != null) {
                    cursorListener3.enteredPreviousComponent(last2, this.componentNode.getByteArray());
                }
            }
            while (this.index >= this.componentIndex + this.componentNode.getByteArray().length()) {
                this.componentIndex += this.componentNode.getByteArray().last();
                ByteArrayList.Node nextNode = this.componentNode.getNextNode();
                this.componentNode = nextNode;
                CursorListener cursorListener4 = this.listener;
                if (cursorListener4 != null) {
                    cursorListener4.enteredNextComponent(this.componentIndex, nextNode.getByteArray());
                }
            }
            int i2 = this.index - this.componentIndex;
            ByteArrayList.Node node3 = this.componentNode;
            if (node3 == node2) {
                this.componentCursor.setIndex(i2);
            } else {
                this.componentCursor = node3.getByteArray().cursor(i2);
            }
        }

        public int getRemaining() {
            return (CompositeByteArray.this.last() - this.index) + 1;
        }

        public boolean hasRemaining() {
            return getRemaining() > 0;
        }

        public byte get() {
            prepareForAccess(1);
            byte b = this.componentCursor.get();
            this.index++;
            return b;
        }

        public void put(byte b) {
            prepareForAccess(1);
            this.componentCursor.put(b);
            this.index++;
        }

        public void get(IoBuffer ioBuffer) {
            while (ioBuffer.hasRemaining()) {
                int remaining = ioBuffer.remaining();
                prepareForAccess(remaining);
                this.componentCursor.get(ioBuffer);
                this.index += remaining - ioBuffer.remaining();
            }
        }

        public void put(IoBuffer ioBuffer) {
            while (ioBuffer.hasRemaining()) {
                int remaining = ioBuffer.remaining();
                prepareForAccess(remaining);
                this.componentCursor.put(ioBuffer);
                this.index += remaining - ioBuffer.remaining();
            }
        }

        public short getShort() {
            int i;
            prepareForAccess(2);
            if (this.componentCursor.getRemaining() >= 4) {
                short s = this.componentCursor.getShort();
                this.index += 2;
                return s;
            }
            byte b = get();
            byte b2 = get();
            if (CompositeByteArray.this.order.equals(ByteOrder.BIG_ENDIAN)) {
                i = (b << 8) | (b2 << 0);
            } else {
                i = (b << 0) | (b2 << 8);
            }
            return (short) i;
        }

        public void putShort(short s) {
            int i;
            byte b;
            prepareForAccess(2);
            if (this.componentCursor.getRemaining() >= 4) {
                this.componentCursor.putShort(s);
                this.index += 2;
                return;
            }
            if (CompositeByteArray.this.order.equals(ByteOrder.BIG_ENDIAN)) {
                b = (byte) ((s >> 8) & 255);
                i = s >> 0;
            } else {
                b = (byte) ((s >> 0) & 255);
                i = s >> 8;
            }
            put(b);
            put((byte) (i & 255));
        }

        public int getInt() {
            prepareForAccess(4);
            if (this.componentCursor.getRemaining() >= 4) {
                int i = this.componentCursor.getInt();
                this.index += 4;
                return i;
            }
            byte b = get();
            byte b2 = get();
            byte b3 = get();
            byte b4 = get();
            if (CompositeByteArray.this.order.equals(ByteOrder.BIG_ENDIAN)) {
                return (b << Ascii.CAN) | (b2 << Ascii.DLE) | (b3 << 8) | (b4 << 0);
            }
            return (b << 0) | (b2 << 8) | (b3 << Ascii.DLE) | (b4 << Ascii.CAN);
        }

        public void putInt(int i) {
            int i2;
            byte b;
            byte b2;
            byte b3;
            prepareForAccess(4);
            if (this.componentCursor.getRemaining() >= 4) {
                this.componentCursor.putInt(i);
                this.index += 4;
                return;
            }
            if (CompositeByteArray.this.order.equals(ByteOrder.BIG_ENDIAN)) {
                b3 = (byte) ((i >> 24) & 255);
                b2 = (byte) ((i >> 16) & 255);
                b = (byte) ((i >> 8) & 255);
                i2 = i >> 0;
            } else {
                b3 = (byte) ((i >> 0) & 255);
                b2 = (byte) ((i >> 8) & 255);
                b = (byte) ((i >> 16) & 255);
                i2 = i >> 24;
            }
            put(b3);
            put(b2);
            put(b);
            put((byte) (i2 & 255));
        }

        public long getLong() {
            prepareForAccess(8);
            if (this.componentCursor.getRemaining() >= 4) {
                long j = this.componentCursor.getLong();
                this.index += 8;
                return j;
            }
            byte b = get();
            byte b2 = get();
            byte b3 = get();
            byte b4 = get();
            byte b5 = get();
            byte b6 = get();
            byte b7 = get();
            byte b8 = get();
            if (CompositeByteArray.this.order.equals(ByteOrder.BIG_ENDIAN)) {
                return ((((long) b) & 255) << 56) | ((((long) b2) & 255) << 48) | ((((long) b3) & 255) << 40) | ((((long) b4) & 255) << 32) | ((((long) b5) & 255) << 24) | ((((long) b6) & 255) << 16) | ((((long) b7) & 255) << 8) | ((((long) b8) & 255) << 0);
            }
            return ((((long) b) & 255) << 0) | ((((long) b8) & 255) << 56) | ((((long) b7) & 255) << 48) | ((((long) b6) & 255) << 40) | ((((long) b5) & 255) << 32) | ((((long) b4) & 255) << 24) | ((((long) b3) & 255) << 16) | ((((long) b2) & 255) << 8);
        }

        public void putLong(long j) {
            byte b;
            byte b2;
            byte b3;
            byte b4;
            byte b5;
            byte b6;
            byte b7;
            byte b8;
            long j2 = j;
            prepareForAccess(8);
            if (this.componentCursor.getRemaining() >= 4) {
                this.componentCursor.putLong(j2);
                this.index += 8;
                return;
            }
            if (CompositeByteArray.this.order.equals(ByteOrder.BIG_ENDIAN)) {
                b6 = (byte) ((int) ((j2 >> 56) & 255));
                b5 = (byte) ((int) ((j2 >> 48) & 255));
                b4 = (byte) ((int) ((j2 >> 40) & 255));
                b3 = (byte) ((int) ((j2 >> 32) & 255));
                b2 = (byte) ((int) ((j2 >> 24) & 255));
                b = (byte) ((int) ((j2 >> 16) & 255));
                b7 = (byte) ((int) ((j2 >> 8) & 255));
                b8 = (byte) ((int) ((j2 >> 0) & 255));
            } else {
                b6 = (byte) ((int) ((j2 >> 0) & 255));
                b2 = (byte) ((int) ((j2 >> 32) & 255));
                b8 = (byte) ((int) ((j2 >> 56) & 255));
                b5 = (byte) ((int) ((j2 >> 8) & 255));
                b7 = (byte) ((int) ((j2 >> 48) & 255));
                b4 = (byte) ((int) ((j2 >> 16) & 255));
                b = (byte) ((int) ((j2 >> 40) & 255));
                b3 = (byte) ((int) ((j2 >> 24) & 255));
            }
            put(b6);
            put(b5);
            put(b4);
            put(b3);
            put(b2);
            put(b);
            put(b7);
            put(b8);
        }

        public float getFloat() {
            prepareForAccess(4);
            if (this.componentCursor.getRemaining() < 4) {
                return Float.intBitsToFloat(getInt());
            }
            float f = this.componentCursor.getFloat();
            this.index += 4;
            return f;
        }

        public void putFloat(float f) {
            prepareForAccess(4);
            if (this.componentCursor.getRemaining() >= 4) {
                this.componentCursor.putFloat(f);
                this.index += 4;
                return;
            }
            putInt(Float.floatToIntBits(f));
        }

        public double getDouble() {
            prepareForAccess(8);
            if (this.componentCursor.getRemaining() < 4) {
                return Double.longBitsToDouble(getLong());
            }
            double d = this.componentCursor.getDouble();
            this.index += 8;
            return d;
        }

        public void putDouble(double d) {
            prepareForAccess(8);
            if (this.componentCursor.getRemaining() >= 4) {
                this.componentCursor.putDouble(d);
                this.index += 8;
                return;
            }
            putLong(Double.doubleToLongBits(d));
        }

        public char getChar() {
            int i;
            prepareForAccess(2);
            if (this.componentCursor.getRemaining() >= 4) {
                char c = this.componentCursor.getChar();
                this.index += 2;
                return c;
            }
            byte b = get();
            byte b2 = get();
            if (CompositeByteArray.this.order.equals(ByteOrder.BIG_ENDIAN)) {
                i = (b << 8) | (b2 << 0);
            } else {
                i = (b << 0) | (b2 << 8);
            }
            return (char) i;
        }

        public void putChar(char c) {
            int i;
            byte b;
            prepareForAccess(2);
            if (this.componentCursor.getRemaining() >= 4) {
                this.componentCursor.putChar(c);
                this.index += 2;
                return;
            }
            if (CompositeByteArray.this.order.equals(ByteOrder.BIG_ENDIAN)) {
                b = (byte) ((c >> 8) & 255);
                i = c >> 0;
            } else {
                b = (byte) ((c >> 0) & 255);
                i = c >> 8;
            }
            put(b);
            put((byte) (i & 255));
        }
    }
}
