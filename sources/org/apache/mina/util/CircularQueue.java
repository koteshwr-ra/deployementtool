package org.apache.mina.util;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Queue;

public class CircularQueue<E> extends AbstractList<E> implements Queue<E>, Serializable {
    private static final int DEFAULT_CAPACITY = 4;
    private static final long serialVersionUID = 3993421269224511264L;
    private int first;
    private boolean full;
    private final int initialCapacity;
    private volatile Object[] items;
    private int last;
    private int mask;
    private int shrinkThreshold;

    private static int normalizeCapacity(int i) {
        int i2 = 1;
        while (i2 < i) {
            i2 <<= 1;
            if (i2 < 0) {
                return 1073741824;
            }
        }
        return i2;
    }

    public CircularQueue() {
        this(4);
    }

    public CircularQueue(int i) {
        this.first = 0;
        this.last = 0;
        int normalizeCapacity = normalizeCapacity(i);
        this.items = new Object[normalizeCapacity];
        this.mask = normalizeCapacity - 1;
        this.initialCapacity = normalizeCapacity;
        this.shrinkThreshold = 0;
    }

    public int capacity() {
        return this.items.length;
    }

    public void clear() {
        if (!isEmpty()) {
            Arrays.fill(this.items, (Object) null);
            this.first = 0;
            this.last = 0;
            this.full = false;
            shrinkIfNeeded();
        }
    }

    public E poll() {
        if (isEmpty()) {
            return null;
        }
        E e = this.items[this.first];
        this.items[this.first] = null;
        decreaseSize();
        if (this.first == this.last) {
            this.last = 0;
            this.first = 0;
        }
        shrinkIfNeeded();
        return e;
    }

    public boolean offer(E e) {
        if (e != null) {
            expandIfNeeded();
            this.items[this.last] = e;
            increaseSize();
            return true;
        }
        throw new IllegalArgumentException("item");
    }

    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return this.items[this.first];
    }

    public E get(int i) {
        checkIndex(i);
        return this.items[getRealIndex(i)];
    }

    public boolean isEmpty() {
        return this.first == this.last && !this.full;
    }

    public int size() {
        if (this.full) {
            return capacity();
        }
        int i = this.last;
        int i2 = this.first;
        if (i >= i2) {
            return i - i2;
        }
        return (i - i2) + capacity();
    }

    public String toString() {
        return "first=" + this.first + ", last=" + this.last + ", size=" + size() + ", mask = " + this.mask;
    }

    private void checkIndex(int i) {
        if (i < 0 || i >= size()) {
            throw new IndexOutOfBoundsException(String.valueOf(i));
        }
    }

    private int getRealIndex(int i) {
        return this.mask & (this.first + i);
    }

    private void increaseSize() {
        boolean z = true;
        int i = (this.last + 1) & this.mask;
        this.last = i;
        if (this.first != i) {
            z = false;
        }
        this.full = z;
    }

    private void decreaseSize() {
        this.first = (this.first + 1) & this.mask;
        this.full = false;
    }

    private void expandIfNeeded() {
        if (this.full) {
            int length = this.items.length;
            int i = length << 1;
            Object[] objArr = new Object[i];
            if (this.first < this.last) {
                Object[] objArr2 = this.items;
                int i2 = this.first;
                System.arraycopy(objArr2, i2, objArr, 0, this.last - i2);
            } else {
                Object[] objArr3 = this.items;
                int i3 = this.first;
                System.arraycopy(objArr3, i3, objArr, 0, length - i3);
                System.arraycopy(this.items, 0, objArr, length - this.first, this.last);
            }
            this.first = 0;
            this.last = length;
            this.items = objArr;
            this.mask = i - 1;
            int i4 = i >>> 3;
            if (i4 > this.initialCapacity) {
                this.shrinkThreshold = i4;
            }
        }
    }

    private void shrinkIfNeeded() {
        int size = size();
        if (size <= this.shrinkThreshold) {
            int length = this.items.length;
            int normalizeCapacity = normalizeCapacity(size);
            if (size == normalizeCapacity) {
                normalizeCapacity <<= 1;
            }
            if (normalizeCapacity < length) {
                int i = this.initialCapacity;
                if (normalizeCapacity < i) {
                    if (length != i) {
                        normalizeCapacity = i;
                    } else {
                        return;
                    }
                }
                Object[] objArr = new Object[normalizeCapacity];
                if (size > 0) {
                    if (this.first < this.last) {
                        Object[] objArr2 = this.items;
                        int i2 = this.first;
                        System.arraycopy(objArr2, i2, objArr, 0, this.last - i2);
                    } else {
                        Object[] objArr3 = this.items;
                        int i3 = this.first;
                        System.arraycopy(objArr3, i3, objArr, 0, length - i3);
                        System.arraycopy(this.items, 0, objArr, length - this.first, this.last);
                    }
                }
                this.first = 0;
                this.last = size;
                this.items = objArr;
                this.mask = normalizeCapacity - 1;
                this.shrinkThreshold = 0;
            }
        }
    }

    public boolean add(E e) {
        return offer(e);
    }

    public E set(int i, E e) {
        checkIndex(i);
        int realIndex = getRealIndex(i);
        E e2 = this.items[realIndex];
        this.items[realIndex] = e;
        return e2;
    }

    public void add(int i, E e) {
        if (i == size()) {
            offer(e);
            return;
        }
        checkIndex(i);
        expandIfNeeded();
        int realIndex = getRealIndex(i);
        int i2 = this.first;
        if (i2 < this.last) {
            System.arraycopy(this.items, realIndex, this.items, realIndex + 1, this.last - realIndex);
        } else if (realIndex >= i2) {
            System.arraycopy(this.items, 0, this.items, 1, this.last);
            this.items[0] = this.items[this.items.length - 1];
            System.arraycopy(this.items, realIndex, this.items, realIndex + 1, (this.items.length - realIndex) - 1);
        } else {
            System.arraycopy(this.items, realIndex, this.items, realIndex + 1, this.last - realIndex);
        }
        this.items[realIndex] = e;
        increaseSize();
    }

    public E remove(int i) {
        if (i == 0) {
            return poll();
        }
        checkIndex(i);
        int realIndex = getRealIndex(i);
        E e = this.items[realIndex];
        int i2 = this.first;
        if (i2 < this.last) {
            Object[] objArr = this.items;
            int i3 = this.first;
            Object[] objArr2 = this.items;
            int i4 = this.first;
            System.arraycopy(objArr, i3, objArr2, i4 + 1, realIndex - i4);
        } else if (realIndex >= i2) {
            Object[] objArr3 = this.items;
            int i5 = this.first;
            Object[] objArr4 = this.items;
            int i6 = this.first;
            System.arraycopy(objArr3, i5, objArr4, i6 + 1, realIndex - i6);
        } else {
            System.arraycopy(this.items, 0, this.items, 1, realIndex);
            this.items[0] = this.items[this.items.length - 1];
            System.arraycopy(this.items, this.first, this.items, this.first + 1, (this.items.length - this.first) - 1);
        }
        this.items[this.first] = null;
        decreaseSize();
        shrinkIfNeeded();
        return e;
    }

    public E remove() {
        if (!isEmpty()) {
            return poll();
        }
        throw new NoSuchElementException();
    }

    public E element() {
        if (!isEmpty()) {
            return peek();
        }
        throw new NoSuchElementException();
    }
}
