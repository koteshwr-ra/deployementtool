package org.apache.commons.collections4.list;

import com.alibaba.android.arouter.utils.Consts;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.util.AbstractList;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import okhttp3.HttpUrl;
import org.apache.commons.collections4.OrderedIterator;

public abstract class AbstractLinkedList<E> implements List<E> {
    transient Node<E> header;
    transient int modCount;
    transient int size;

    protected AbstractLinkedList() {
    }

    protected AbstractLinkedList(Collection<? extends E> collection) {
        init();
        addAll(collection);
    }

    /* access modifiers changed from: protected */
    public void init() {
        this.header = createHeaderNode();
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public E get(int i) {
        return getNode(i, false).getValue();
    }

    public Iterator<E> iterator() {
        return listIterator();
    }

    public ListIterator<E> listIterator() {
        return new LinkedListIterator(this, 0);
    }

    public ListIterator<E> listIterator(int i) {
        return new LinkedListIterator(this, i);
    }

    public int indexOf(Object obj) {
        int i = 0;
        for (Node<E> node = this.header.next; node != this.header; node = node.next) {
            if (isEqualValue(node.getValue(), obj)) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public int lastIndexOf(Object obj) {
        int i = this.size - 1;
        Node<E> node = this.header;
        while (true) {
            node = node.previous;
            if (node == this.header) {
                return -1;
            }
            if (isEqualValue(node.getValue(), obj)) {
                return i;
            }
            i--;
        }
    }

    public boolean contains(Object obj) {
        return indexOf(obj) != -1;
    }

    public boolean containsAll(Collection<?> collection) {
        for (Object contains : collection) {
            if (!contains(contains)) {
                return false;
            }
        }
        return true;
    }

    public Object[] toArray() {
        return toArray(new Object[this.size]);
    }

    public <T> T[] toArray(T[] tArr) {
        if (tArr.length < this.size) {
            tArr = (Object[]) Array.newInstance(tArr.getClass().getComponentType(), this.size);
        }
        int i = 0;
        Node<E> node = this.header.next;
        while (node != this.header) {
            tArr[i] = node.getValue();
            node = node.next;
            i++;
        }
        int length = tArr.length;
        int i2 = this.size;
        if (length > i2) {
            tArr[i2] = null;
        }
        return tArr;
    }

    public List<E> subList(int i, int i2) {
        return new LinkedSubList(this, i, i2);
    }

    public boolean add(E e) {
        addLast(e);
        return true;
    }

    public void add(int i, E e) {
        addNodeBefore(getNode(i, true), e);
    }

    public boolean addAll(Collection<? extends E> collection) {
        return addAll(this.size, collection);
    }

    public boolean addAll(int i, Collection<? extends E> collection) {
        Node node = getNode(i, true);
        for (Object addNodeBefore : collection) {
            addNodeBefore(node, addNodeBefore);
        }
        return true;
    }

    public E remove(int i) {
        Node node = getNode(i, false);
        E value = node.getValue();
        removeNode(node);
        return value;
    }

    public boolean remove(Object obj) {
        Node<E> node = this.header;
        do {
            node = node.next;
            if (node == this.header) {
                return false;
            }
        } while (!isEqualValue(node.getValue(), obj));
        removeNode(node);
        return true;
    }

    public boolean removeAll(Collection<?> collection) {
        Iterator it = iterator();
        boolean z = false;
        while (it.hasNext()) {
            if (collection.contains(it.next())) {
                it.remove();
                z = true;
            }
        }
        return z;
    }

    public boolean retainAll(Collection<?> collection) {
        Iterator it = iterator();
        boolean z = false;
        while (it.hasNext()) {
            if (!collection.contains(it.next())) {
                it.remove();
                z = true;
            }
        }
        return z;
    }

    public E set(int i, E e) {
        Node node = getNode(i, false);
        E value = node.getValue();
        updateNode(node, e);
        return value;
    }

    public void clear() {
        removeAllNodes();
    }

    public E getFirst() {
        Node<E> node = this.header.next;
        if (node != this.header) {
            return node.getValue();
        }
        throw new NoSuchElementException();
    }

    public E getLast() {
        Node<E> node = this.header.previous;
        if (node != this.header) {
            return node.getValue();
        }
        throw new NoSuchElementException();
    }

    public boolean addFirst(E e) {
        addNodeAfter(this.header, e);
        return true;
    }

    public boolean addLast(E e) {
        addNodeBefore(this.header, e);
        return true;
    }

    public E removeFirst() {
        Node<E> node = this.header.next;
        if (node != this.header) {
            E value = node.getValue();
            removeNode(node);
            return value;
        }
        throw new NoSuchElementException();
    }

    public E removeLast() {
        Node<E> node = this.header.previous;
        if (node != this.header) {
            E value = node.getValue();
            removeNode(node);
            return value;
        }
        throw new NoSuchElementException();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof List)) {
            return false;
        }
        List list = (List) obj;
        if (list.size() != size()) {
            return false;
        }
        ListIterator listIterator = listIterator();
        ListIterator listIterator2 = list.listIterator();
        while (listIterator.hasNext() && listIterator2.hasNext()) {
            Object next = listIterator.next();
            Object next2 = listIterator2.next();
            if (next == null) {
                if (next2 == null) {
                }
            } else if (!next.equals(next2)) {
            }
            return false;
        }
        if (listIterator.hasNext() || listIterator2.hasNext()) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int i;
        Iterator it = iterator();
        int i2 = 1;
        while (it.hasNext()) {
            Object next = it.next();
            int i3 = i2 * 31;
            if (next == null) {
                i = 0;
            } else {
                i = next.hashCode();
            }
            i2 = i3 + i;
        }
        return i2;
    }

    public String toString() {
        if (size() == 0) {
            return HttpUrl.PATH_SEGMENT_ENCODE_SET_URI;
        }
        StringBuilder sb = new StringBuilder(size() * 16);
        sb.append('[');
        Iterator it = iterator();
        boolean hasNext = it.hasNext();
        while (hasNext) {
            Object next = it.next();
            if (next == this) {
                next = "(this Collection)";
            }
            sb.append(next);
            hasNext = it.hasNext();
            if (hasNext) {
                sb.append(", ");
            }
        }
        sb.append(']');
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    public boolean isEqualValue(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    /* access modifiers changed from: protected */
    public void updateNode(Node<E> node, E e) {
        node.setValue(e);
    }

    /* access modifiers changed from: protected */
    public Node<E> createHeaderNode() {
        return new Node<>();
    }

    /* access modifiers changed from: protected */
    public Node<E> createNode(E e) {
        return new Node<>(e);
    }

    /* access modifiers changed from: protected */
    public void addNodeBefore(Node<E> node, E e) {
        addNode(createNode(e), node);
    }

    /* access modifiers changed from: protected */
    public void addNodeAfter(Node<E> node, E e) {
        addNode(createNode(e), node.next);
    }

    /* access modifiers changed from: protected */
    public void addNode(Node<E> node, Node<E> node2) {
        node.next = node2;
        node.previous = node2.previous;
        node2.previous.next = node;
        node2.previous = node;
        this.size++;
        this.modCount++;
    }

    /* access modifiers changed from: protected */
    public void removeNode(Node<E> node) {
        node.previous.next = node.next;
        node.next.previous = node.previous;
        this.size--;
        this.modCount++;
    }

    /* access modifiers changed from: protected */
    public void removeAllNodes() {
        Node<E> node = this.header;
        node.next = node;
        Node<E> node2 = this.header;
        node2.previous = node2;
        this.size = 0;
        this.modCount++;
    }

    /* access modifiers changed from: protected */
    public Node<E> getNode(int i, boolean z) throws IndexOutOfBoundsException {
        if (i < 0) {
            throw new IndexOutOfBoundsException("Couldn't get the node: index (" + i + ") less than zero.");
        } else if (z || i != this.size) {
            int i2 = this.size;
            if (i > i2) {
                throw new IndexOutOfBoundsException("Couldn't get the node: index (" + i + ") greater than the size of the " + "list (" + this.size + ").");
            } else if (i < i2 / 2) {
                Node<E> node = this.header.next;
                for (int i3 = 0; i3 < i; i3++) {
                    node = node.next;
                }
                return node;
            } else {
                Node<E> node2 = this.header;
                while (i2 > i) {
                    node2 = node2.previous;
                    i2--;
                }
                return node2;
            }
        } else {
            throw new IndexOutOfBoundsException("Couldn't get the node: index (" + i + ") is the size of the list.");
        }
    }

    /* access modifiers changed from: protected */
    public Iterator<E> createSubListIterator(LinkedSubList<E> linkedSubList) {
        return createSubListListIterator(linkedSubList, 0);
    }

    /* access modifiers changed from: protected */
    public ListIterator<E> createSubListListIterator(LinkedSubList<E> linkedSubList, int i) {
        return new LinkedSubListIterator(linkedSubList, i);
    }

    /* access modifiers changed from: protected */
    public void doWriteObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeInt(size());
        Iterator it = iterator();
        while (it.hasNext()) {
            objectOutputStream.writeObject(it.next());
        }
    }

    /* access modifiers changed from: protected */
    public void doReadObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        init();
        int readInt = objectInputStream.readInt();
        for (int i = 0; i < readInt; i++) {
            add(objectInputStream.readObject());
        }
    }

    protected static class Node<E> {
        protected Node<E> next;
        protected Node<E> previous;
        protected E value;

        protected Node() {
            this.previous = this;
            this.next = this;
        }

        protected Node(E e) {
            this.value = e;
        }

        protected Node(Node<E> node, Node<E> node2, E e) {
            this.previous = node;
            this.next = node2;
            this.value = e;
        }

        /* access modifiers changed from: protected */
        public E getValue() {
            return this.value;
        }

        /* access modifiers changed from: protected */
        public void setValue(E e) {
            this.value = e;
        }

        /* access modifiers changed from: protected */
        public Node<E> getPreviousNode() {
            return this.previous;
        }

        /* access modifiers changed from: protected */
        public void setPreviousNode(Node<E> node) {
            this.previous = node;
        }

        /* access modifiers changed from: protected */
        public Node<E> getNextNode() {
            return this.next;
        }

        /* access modifiers changed from: protected */
        public void setNextNode(Node<E> node) {
            this.next = node;
        }
    }

    protected static class LinkedListIterator<E> implements ListIterator<E>, OrderedIterator<E> {
        protected Node<E> current;
        protected int expectedModCount;
        protected Node<E> next;
        protected int nextIndex;
        protected final AbstractLinkedList<E> parent;

        protected LinkedListIterator(AbstractLinkedList<E> abstractLinkedList, int i) throws IndexOutOfBoundsException {
            this.parent = abstractLinkedList;
            this.expectedModCount = abstractLinkedList.modCount;
            this.next = abstractLinkedList.getNode(i, true);
            this.nextIndex = i;
        }

        /* access modifiers changed from: protected */
        public void checkModCount() {
            if (this.parent.modCount != this.expectedModCount) {
                throw new ConcurrentModificationException();
            }
        }

        /* access modifiers changed from: protected */
        public Node<E> getLastNodeReturned() throws IllegalStateException {
            Node<E> node = this.current;
            if (node != null) {
                return node;
            }
            throw new IllegalStateException();
        }

        public boolean hasNext() {
            return this.next != this.parent.header;
        }

        public E next() {
            checkModCount();
            if (hasNext()) {
                E value = this.next.getValue();
                Node<E> node = this.next;
                this.current = node;
                this.next = node.next;
                this.nextIndex++;
                return value;
            }
            throw new NoSuchElementException("No element at index " + this.nextIndex + Consts.DOT);
        }

        public boolean hasPrevious() {
            return this.next.previous != this.parent.header;
        }

        public E previous() {
            checkModCount();
            if (hasPrevious()) {
                Node<E> node = this.next.previous;
                this.next = node;
                E value = node.getValue();
                this.current = this.next;
                this.nextIndex--;
                return value;
            }
            throw new NoSuchElementException("Already at start of list.");
        }

        public int nextIndex() {
            return this.nextIndex;
        }

        public int previousIndex() {
            return nextIndex() - 1;
        }

        public void remove() {
            checkModCount();
            Node<E> node = this.current;
            Node<E> node2 = this.next;
            if (node == node2) {
                this.next = node2.next;
                this.parent.removeNode(getLastNodeReturned());
            } else {
                this.parent.removeNode(getLastNodeReturned());
                this.nextIndex--;
            }
            this.current = null;
            this.expectedModCount++;
        }

        public void set(E e) {
            checkModCount();
            getLastNodeReturned().setValue(e);
        }

        public void add(E e) {
            checkModCount();
            this.parent.addNodeBefore(this.next, e);
            this.current = null;
            this.nextIndex++;
            this.expectedModCount++;
        }
    }

    protected static class LinkedSubListIterator<E> extends LinkedListIterator<E> {
        protected final LinkedSubList<E> sub;

        protected LinkedSubListIterator(LinkedSubList<E> linkedSubList, int i) {
            super(linkedSubList.parent, i + linkedSubList.offset);
            this.sub = linkedSubList;
        }

        public boolean hasNext() {
            return nextIndex() < this.sub.size;
        }

        public boolean hasPrevious() {
            return previousIndex() >= 0;
        }

        public int nextIndex() {
            return super.nextIndex() - this.sub.offset;
        }

        public void add(E e) {
            super.add(e);
            this.sub.expectedModCount = this.parent.modCount;
            this.sub.size++;
        }

        public void remove() {
            super.remove();
            this.sub.expectedModCount = this.parent.modCount;
            LinkedSubList<E> linkedSubList = this.sub;
            linkedSubList.size--;
        }
    }

    protected static class LinkedSubList<E> extends AbstractList<E> {
        int expectedModCount;
        int offset;
        AbstractLinkedList<E> parent;
        int size;

        protected LinkedSubList(AbstractLinkedList<E> abstractLinkedList, int i, int i2) {
            if (i < 0) {
                throw new IndexOutOfBoundsException("fromIndex = " + i);
            } else if (i2 > abstractLinkedList.size()) {
                throw new IndexOutOfBoundsException("toIndex = " + i2);
            } else if (i <= i2) {
                this.parent = abstractLinkedList;
                this.offset = i;
                this.size = i2 - i;
                this.expectedModCount = abstractLinkedList.modCount;
            } else {
                throw new IllegalArgumentException("fromIndex(" + i + ") > toIndex(" + i2 + ")");
            }
        }

        public int size() {
            checkModCount();
            return this.size;
        }

        public E get(int i) {
            rangeCheck(i, this.size);
            checkModCount();
            return this.parent.get(i + this.offset);
        }

        public void add(int i, E e) {
            rangeCheck(i, this.size + 1);
            checkModCount();
            this.parent.add(i + this.offset, e);
            this.expectedModCount = this.parent.modCount;
            this.size++;
            this.modCount++;
        }

        public E remove(int i) {
            rangeCheck(i, this.size);
            checkModCount();
            E remove = this.parent.remove(i + this.offset);
            this.expectedModCount = this.parent.modCount;
            this.size--;
            this.modCount++;
            return remove;
        }

        public boolean addAll(Collection<? extends E> collection) {
            return addAll(this.size, collection);
        }

        public boolean addAll(int i, Collection<? extends E> collection) {
            rangeCheck(i, this.size + 1);
            int size2 = collection.size();
            if (size2 == 0) {
                return false;
            }
            checkModCount();
            this.parent.addAll(this.offset + i, collection);
            this.expectedModCount = this.parent.modCount;
            this.size += size2;
            this.modCount++;
            return true;
        }

        public E set(int i, E e) {
            rangeCheck(i, this.size);
            checkModCount();
            return this.parent.set(i + this.offset, e);
        }

        public void clear() {
            checkModCount();
            Iterator it = iterator();
            while (it.hasNext()) {
                it.next();
                it.remove();
            }
        }

        public Iterator<E> iterator() {
            checkModCount();
            return this.parent.createSubListIterator(this);
        }

        public ListIterator<E> listIterator(int i) {
            rangeCheck(i, this.size + 1);
            checkModCount();
            return this.parent.createSubListListIterator(this, i);
        }

        public List<E> subList(int i, int i2) {
            AbstractLinkedList<E> abstractLinkedList = this.parent;
            int i3 = this.offset;
            return new LinkedSubList(abstractLinkedList, i + i3, i2 + i3);
        }

        /* access modifiers changed from: protected */
        public void rangeCheck(int i, int i2) {
            if (i < 0 || i >= i2) {
                throw new IndexOutOfBoundsException("Index '" + i + "' out of bounds for size '" + this.size + "'");
            }
        }

        /* access modifiers changed from: protected */
        public void checkModCount() {
            if (this.parent.modCount != this.expectedModCount) {
                throw new ConcurrentModificationException();
            }
        }
    }
}
