package org.apache.commons.collections4.list;

import com.alibaba.android.arouter.utils.Consts;
import java.util.AbstractList;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import org.apache.commons.collections4.OrderedIterator;

public class TreeList<E> extends AbstractList<E> {
    /* access modifiers changed from: private */
    public AVLNode<E> root;
    private int size;

    public TreeList() {
    }

    public TreeList(Collection<? extends E> collection) {
        if (!collection.isEmpty()) {
            this.root = new AVLNode<>(collection);
            this.size = collection.size();
        }
    }

    public E get(int i) {
        checkInterval(i, 0, size() - 1);
        return this.root.get(i).getValue();
    }

    public int size() {
        return this.size;
    }

    public Iterator<E> iterator() {
        return listIterator(0);
    }

    public ListIterator<E> listIterator() {
        return listIterator(0);
    }

    public ListIterator<E> listIterator(int i) {
        checkInterval(i, 0, size());
        return new TreeListIterator(this, i);
    }

    public int indexOf(Object obj) {
        AVLNode<E> aVLNode = this.root;
        if (aVLNode == null) {
            return -1;
        }
        return aVLNode.indexOf(obj, aVLNode.relativePosition);
    }

    public boolean contains(Object obj) {
        return indexOf(obj) >= 0;
    }

    public Object[] toArray() {
        Object[] objArr = new Object[size()];
        AVLNode<E> aVLNode = this.root;
        if (aVLNode != null) {
            aVLNode.toArray(objArr, aVLNode.relativePosition);
        }
        return objArr;
    }

    public void add(int i, E e) {
        this.modCount++;
        checkInterval(i, 0, size());
        AVLNode<E> aVLNode = this.root;
        if (aVLNode == null) {
            this.root = new AVLNode(i, e, (AVLNode) null, (AVLNode) null);
        } else {
            this.root = aVLNode.insert(i, e);
        }
        this.size++;
    }

    public boolean addAll(Collection<? extends E> collection) {
        if (collection.isEmpty()) {
            return false;
        }
        this.modCount += collection.size();
        AVLNode<E> aVLNode = new AVLNode<>(collection);
        AVLNode<E> aVLNode2 = this.root;
        if (aVLNode2 != null) {
            aVLNode = aVLNode2.addAll(aVLNode, this.size);
        }
        this.root = aVLNode;
        this.size += collection.size();
        return true;
    }

    public E set(int i, E e) {
        checkInterval(i, 0, size() - 1);
        AVLNode<E> aVLNode = this.root.get(i);
        E access$400 = aVLNode.value;
        aVLNode.setValue(e);
        return access$400;
    }

    public E remove(int i) {
        this.modCount++;
        checkInterval(i, 0, size() - 1);
        E e = get(i);
        this.root = this.root.remove(i);
        this.size--;
        return e;
    }

    public void clear() {
        this.modCount++;
        this.root = null;
        this.size = 0;
    }

    private void checkInterval(int i, int i2, int i3) {
        if (i < i2 || i > i3) {
            throw new IndexOutOfBoundsException("Invalid index:" + i + ", size=" + size());
        }
    }

    static class AVLNode<E> {
        private int height;
        private AVLNode<E> left;
        private boolean leftIsPrevious;
        /* access modifiers changed from: private */
        public int relativePosition;
        private AVLNode<E> right;
        private boolean rightIsNext;
        /* access modifiers changed from: private */
        public E value;

        private AVLNode(int i, E e, AVLNode<E> aVLNode, AVLNode<E> aVLNode2) {
            this.relativePosition = i;
            this.value = e;
            this.rightIsNext = true;
            this.leftIsPrevious = true;
            this.right = aVLNode;
            this.left = aVLNode2;
        }

        private AVLNode(Collection<? extends E> collection) {
            this(collection.iterator(), 0, collection.size() - 1, 0, (AVLNode) null, (AVLNode) null);
        }

        private AVLNode(Iterator<? extends E> it, int i, int i2, int i3, AVLNode<E> aVLNode, AVLNode<E> aVLNode2) {
            int i4 = i;
            int i5 = i2;
            int i6 = i4 + ((i5 - i4) / 2);
            if (i4 < i6) {
                this.left = new AVLNode(it, i, i6 - 1, i6, aVLNode, this);
            } else {
                this.leftIsPrevious = true;
                this.left = aVLNode;
            }
            this.value = it.next();
            this.relativePosition = i6 - i3;
            if (i6 < i5) {
                this.right = new AVLNode(it, i6 + 1, i2, i6, this, aVLNode2);
            } else {
                this.rightIsNext = true;
                this.right = aVLNode2;
            }
            recalcHeight();
        }

        /* access modifiers changed from: package-private */
        public E getValue() {
            return this.value;
        }

        /* access modifiers changed from: package-private */
        public void setValue(E e) {
            this.value = e;
        }

        /* access modifiers changed from: package-private */
        public AVLNode<E> get(int i) {
            int i2 = i - this.relativePosition;
            if (i2 == 0) {
                return this;
            }
            AVLNode leftSubTree = i2 < 0 ? getLeftSubTree() : getRightSubTree();
            if (leftSubTree == null) {
                return null;
            }
            return leftSubTree.get(i2);
        }

        /* access modifiers changed from: package-private */
        public int indexOf(Object obj, int i) {
            if (getLeftSubTree() != null) {
                AVLNode<E> aVLNode = this.left;
                int indexOf = aVLNode.indexOf(obj, aVLNode.relativePosition + i);
                if (indexOf != -1) {
                    return indexOf;
                }
            }
            E e = this.value;
            if (e != null ? e.equals(obj) : e == obj) {
                return i;
            }
            if (getRightSubTree() == null) {
                return -1;
            }
            AVLNode<E> aVLNode2 = this.right;
            return aVLNode2.indexOf(obj, i + aVLNode2.relativePosition);
        }

        /* access modifiers changed from: package-private */
        public void toArray(Object[] objArr, int i) {
            objArr[i] = this.value;
            if (getLeftSubTree() != null) {
                AVLNode<E> aVLNode = this.left;
                aVLNode.toArray(objArr, aVLNode.relativePosition + i);
            }
            if (getRightSubTree() != null) {
                AVLNode<E> aVLNode2 = this.right;
                aVLNode2.toArray(objArr, i + aVLNode2.relativePosition);
            }
        }

        /* access modifiers changed from: package-private */
        public AVLNode<E> next() {
            AVLNode<E> aVLNode;
            if (this.rightIsNext || (aVLNode = this.right) == null) {
                return this.right;
            }
            return aVLNode.min();
        }

        /* access modifiers changed from: package-private */
        public AVLNode<E> previous() {
            AVLNode<E> aVLNode;
            if (this.leftIsPrevious || (aVLNode = this.left) == null) {
                return this.left;
            }
            return aVLNode.max();
        }

        /* access modifiers changed from: package-private */
        public AVLNode<E> insert(int i, E e) {
            int i2 = i - this.relativePosition;
            if (i2 <= 0) {
                return insertOnLeft(i2, e);
            }
            return insertOnRight(i2, e);
        }

        private AVLNode<E> insertOnLeft(int i, E e) {
            if (getLeftSubTree() == null) {
                setLeft(new AVLNode(-1, e, this, this.left), (AVLNode) null);
            } else {
                setLeft(this.left.insert(i, e), (AVLNode<E>) null);
            }
            int i2 = this.relativePosition;
            if (i2 >= 0) {
                this.relativePosition = i2 + 1;
            }
            AVLNode<E> balance = balance();
            recalcHeight();
            return balance;
        }

        private AVLNode<E> insertOnRight(int i, E e) {
            if (getRightSubTree() == null) {
                setRight(new AVLNode(1, e, this.right, this), (AVLNode) null);
            } else {
                setRight(this.right.insert(i, e), (AVLNode<E>) null);
            }
            int i2 = this.relativePosition;
            if (i2 < 0) {
                this.relativePosition = i2 - 1;
            }
            AVLNode<E> balance = balance();
            recalcHeight();
            return balance;
        }

        private AVLNode<E> getLeftSubTree() {
            if (this.leftIsPrevious) {
                return null;
            }
            return this.left;
        }

        private AVLNode<E> getRightSubTree() {
            if (this.rightIsNext) {
                return null;
            }
            return this.right;
        }

        private AVLNode<E> max() {
            return getRightSubTree() == null ? this : this.right.max();
        }

        private AVLNode<E> min() {
            return getLeftSubTree() == null ? this : this.left.min();
        }

        /* access modifiers changed from: package-private */
        public AVLNode<E> remove(int i) {
            int i2 = i - this.relativePosition;
            if (i2 == 0) {
                return removeSelf();
            }
            if (i2 > 0) {
                setRight(this.right.remove(i2), this.right.right);
                int i3 = this.relativePosition;
                if (i3 < 0) {
                    this.relativePosition = i3 + 1;
                }
            } else {
                setLeft(this.left.remove(i2), this.left.left);
                int i4 = this.relativePosition;
                if (i4 > 0) {
                    this.relativePosition = i4 - 1;
                }
            }
            recalcHeight();
            return balance();
        }

        private AVLNode<E> removeMax() {
            if (getRightSubTree() == null) {
                return removeSelf();
            }
            setRight(this.right.removeMax(), this.right.right);
            int i = this.relativePosition;
            if (i < 0) {
                this.relativePosition = i + 1;
            }
            recalcHeight();
            return balance();
        }

        private AVLNode<E> removeMin() {
            if (getLeftSubTree() == null) {
                return removeSelf();
            }
            setLeft(this.left.removeMin(), this.left.left);
            int i = this.relativePosition;
            if (i > 0) {
                this.relativePosition = i - 1;
            }
            recalcHeight();
            return balance();
        }

        private AVLNode<E> removeSelf() {
            if (getRightSubTree() == null && getLeftSubTree() == null) {
                return null;
            }
            int i = 0;
            if (getRightSubTree() == null) {
                int i2 = this.relativePosition;
                if (i2 > 0) {
                    AVLNode<E> aVLNode = this.left;
                    int i3 = aVLNode.relativePosition;
                    if (i2 <= 0) {
                        i = 1;
                    }
                    aVLNode.relativePosition = i3 + i2 + i;
                }
                this.left.max().setRight((AVLNode) null, this.right);
                return this.left;
            } else if (getLeftSubTree() == null) {
                AVLNode<E> aVLNode2 = this.right;
                int i4 = aVLNode2.relativePosition;
                int i5 = this.relativePosition;
                if (i5 >= 0) {
                    i = 1;
                }
                aVLNode2.relativePosition = i4 + (i5 - i);
                this.right.min().setLeft((AVLNode) null, this.left);
                return this.right;
            } else {
                if (heightRightMinusLeft() > 0) {
                    AVLNode<E> min = this.right.min();
                    this.value = min.value;
                    if (this.leftIsPrevious) {
                        this.left = min.left;
                    }
                    this.right = this.right.removeMin();
                    int i6 = this.relativePosition;
                    if (i6 < 0) {
                        this.relativePosition = i6 + 1;
                    }
                } else {
                    AVLNode<E> max = this.left.max();
                    this.value = max.value;
                    if (this.rightIsNext) {
                        this.right = max.right;
                    }
                    AVLNode<E> aVLNode3 = this.left;
                    AVLNode<E> aVLNode4 = aVLNode3.left;
                    AVLNode<E> removeMax = aVLNode3.removeMax();
                    this.left = removeMax;
                    if (removeMax == null) {
                        this.left = aVLNode4;
                        this.leftIsPrevious = true;
                    }
                    int i7 = this.relativePosition;
                    if (i7 > 0) {
                        this.relativePosition = i7 - 1;
                    }
                }
                recalcHeight();
                return this;
            }
        }

        private AVLNode<E> balance() {
            int heightRightMinusLeft = heightRightMinusLeft();
            if (heightRightMinusLeft == -2) {
                if (this.left.heightRightMinusLeft() > 0) {
                    setLeft(this.left.rotateLeft(), (AVLNode<E>) null);
                }
                return rotateRight();
            } else if (heightRightMinusLeft == -1 || heightRightMinusLeft == 0 || heightRightMinusLeft == 1) {
                return this;
            } else {
                if (heightRightMinusLeft == 2) {
                    if (this.right.heightRightMinusLeft() < 0) {
                        setRight(this.right.rotateRight(), (AVLNode<E>) null);
                    }
                    return rotateLeft();
                }
                throw new RuntimeException("tree inconsistent!");
            }
        }

        private int getOffset(AVLNode<E> aVLNode) {
            if (aVLNode == null) {
                return 0;
            }
            return aVLNode.relativePosition;
        }

        private int setOffset(AVLNode<E> aVLNode, int i) {
            if (aVLNode == null) {
                return 0;
            }
            int offset = getOffset(aVLNode);
            aVLNode.relativePosition = i;
            return offset;
        }

        private void recalcHeight() {
            int i = -1;
            int i2 = getLeftSubTree() == null ? -1 : getLeftSubTree().height;
            if (getRightSubTree() != null) {
                i = getRightSubTree().height;
            }
            this.height = Math.max(i2, i) + 1;
        }

        private int getHeight(AVLNode<E> aVLNode) {
            if (aVLNode == null) {
                return -1;
            }
            return aVLNode.height;
        }

        private int heightRightMinusLeft() {
            return getHeight(getRightSubTree()) - getHeight(getLeftSubTree());
        }

        private AVLNode<E> rotateLeft() {
            AVLNode<E> aVLNode = this.right;
            AVLNode leftSubTree = getRightSubTree().getLeftSubTree();
            int offset = this.relativePosition + getOffset(aVLNode);
            setRight(leftSubTree, aVLNode);
            aVLNode.setLeft(this, (AVLNode<E>) null);
            setOffset(aVLNode, offset);
            setOffset(this, -aVLNode.relativePosition);
            setOffset(leftSubTree, getOffset(aVLNode) + getOffset(leftSubTree));
            return aVLNode;
        }

        private AVLNode<E> rotateRight() {
            AVLNode<E> aVLNode = this.left;
            AVLNode rightSubTree = getLeftSubTree().getRightSubTree();
            int offset = this.relativePosition + getOffset(aVLNode);
            setLeft(rightSubTree, aVLNode);
            aVLNode.setRight(this, (AVLNode<E>) null);
            setOffset(aVLNode, offset);
            setOffset(this, -aVLNode.relativePosition);
            setOffset(rightSubTree, getOffset(aVLNode) + getOffset(rightSubTree));
            return aVLNode;
        }

        private void setLeft(AVLNode<E> aVLNode, AVLNode<E> aVLNode2) {
            boolean z = aVLNode == null;
            this.leftIsPrevious = z;
            if (z) {
                aVLNode = aVLNode2;
            }
            this.left = aVLNode;
            recalcHeight();
        }

        private void setRight(AVLNode<E> aVLNode, AVLNode<E> aVLNode2) {
            boolean z = aVLNode == null;
            this.rightIsNext = z;
            if (z) {
                aVLNode = aVLNode2;
            }
            this.right = aVLNode;
            recalcHeight();
        }

        /* access modifiers changed from: private */
        public AVLNode<E> addAll(AVLNode<E> aVLNode, int i) {
            AVLNode<E> max = max();
            AVLNode<E> min = aVLNode.min();
            if (aVLNode.height > this.height) {
                AVLNode removeMax = removeMax();
                ArrayDeque arrayDeque = new ArrayDeque();
                AVLNode<E> aVLNode2 = aVLNode;
                int i2 = aVLNode.relativePosition + i;
                int i3 = 0;
                while (aVLNode2 != null && aVLNode2.height > getHeight(removeMax)) {
                    arrayDeque.push(aVLNode2);
                    aVLNode2 = aVLNode2.left;
                    if (aVLNode2 != null) {
                        i3 = i2;
                        i2 = aVLNode2.relativePosition + i2;
                    } else {
                        i3 = i2;
                    }
                }
                max.setLeft(removeMax, (AVLNode<E>) null);
                max.setRight(aVLNode2, min);
                if (removeMax != null) {
                    removeMax.max().setRight((AVLNode) null, max);
                    removeMax.relativePosition -= i - 1;
                }
                if (aVLNode2 != null) {
                    aVLNode2.min().setLeft((AVLNode) null, max);
                    aVLNode2.relativePosition = (i2 - i) + 1;
                }
                max.relativePosition = (i - 1) - i3;
                aVLNode.relativePosition += i;
                while (!arrayDeque.isEmpty()) {
                    AVLNode aVLNode3 = (AVLNode) arrayDeque.pop();
                    aVLNode3.setLeft(max, (AVLNode<E>) null);
                    max = aVLNode3.balance();
                }
                return max;
            }
            AVLNode<E> removeMin = aVLNode.removeMin();
            ArrayDeque arrayDeque2 = new ArrayDeque();
            AVLNode aVLNode4 = this;
            int i4 = this.relativePosition;
            int i5 = 0;
            while (aVLNode4 != null && aVLNode4.height > getHeight(removeMin)) {
                arrayDeque2.push(aVLNode4);
                aVLNode4 = aVLNode4.right;
                if (aVLNode4 != null) {
                    int i6 = i4;
                    i4 = aVLNode4.relativePosition + i4;
                    i5 = i6;
                } else {
                    i5 = i4;
                }
            }
            min.setRight(removeMin, (AVLNode<E>) null);
            min.setLeft(aVLNode4, max);
            if (removeMin != null) {
                removeMin.min().setLeft((AVLNode) null, min);
                removeMin.relativePosition++;
            }
            if (aVLNode4 != null) {
                aVLNode4.max().setRight((AVLNode) null, min);
                aVLNode4.relativePosition = i4 - i;
            }
            min.relativePosition = i - i5;
            while (!arrayDeque2.isEmpty()) {
                AVLNode aVLNode5 = (AVLNode) arrayDeque2.pop();
                aVLNode5.setRight(min, (AVLNode<E>) null);
                min = aVLNode5.balance();
            }
            return min;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("AVLNode(");
            sb.append(this.relativePosition);
            sb.append(',');
            boolean z = true;
            sb.append(this.left != null);
            sb.append(',');
            sb.append(this.value);
            sb.append(',');
            if (getRightSubTree() == null) {
                z = false;
            }
            sb.append(z);
            sb.append(", faedelung ");
            sb.append(this.rightIsNext);
            sb.append(" )");
            return sb.toString();
        }
    }

    static class TreeListIterator<E> implements ListIterator<E>, OrderedIterator<E> {
        private AVLNode<E> current;
        private int currentIndex;
        private int expectedModCount;
        private AVLNode<E> next;
        private int nextIndex;
        private final TreeList<E> parent;

        protected TreeListIterator(TreeList<E> treeList, int i) throws IndexOutOfBoundsException {
            this.parent = treeList;
            this.expectedModCount = treeList.modCount;
            this.next = treeList.root == null ? null : treeList.root.get(i);
            this.nextIndex = i;
            this.currentIndex = -1;
        }

        /* access modifiers changed from: protected */
        public void checkModCount() {
            if (this.parent.modCount != this.expectedModCount) {
                throw new ConcurrentModificationException();
            }
        }

        public boolean hasNext() {
            return this.nextIndex < this.parent.size();
        }

        public E next() {
            checkModCount();
            if (hasNext()) {
                if (this.next == null) {
                    this.next = this.parent.root.get(this.nextIndex);
                }
                E value = this.next.getValue();
                AVLNode<E> aVLNode = this.next;
                this.current = aVLNode;
                int i = this.nextIndex;
                this.nextIndex = i + 1;
                this.currentIndex = i;
                this.next = aVLNode.next();
                return value;
            }
            throw new NoSuchElementException("No element at index " + this.nextIndex + Consts.DOT);
        }

        public boolean hasPrevious() {
            return this.nextIndex > 0;
        }

        public E previous() {
            checkModCount();
            if (hasPrevious()) {
                AVLNode<E> aVLNode = this.next;
                if (aVLNode == null) {
                    this.next = this.parent.root.get(this.nextIndex - 1);
                } else {
                    this.next = aVLNode.previous();
                }
                E value = this.next.getValue();
                this.current = this.next;
                int i = this.nextIndex - 1;
                this.nextIndex = i;
                this.currentIndex = i;
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
            int i = this.currentIndex;
            if (i != -1) {
                this.parent.remove(i);
                int i2 = this.nextIndex;
                if (i2 != this.currentIndex) {
                    this.nextIndex = i2 - 1;
                }
                this.next = null;
                this.current = null;
                this.currentIndex = -1;
                this.expectedModCount++;
                return;
            }
            throw new IllegalStateException();
        }

        public void set(E e) {
            checkModCount();
            AVLNode<E> aVLNode = this.current;
            if (aVLNode != null) {
                aVLNode.setValue(e);
                return;
            }
            throw new IllegalStateException();
        }

        public void add(E e) {
            checkModCount();
            this.parent.add(this.nextIndex, e);
            this.current = null;
            this.currentIndex = -1;
            this.nextIndex++;
            this.expectedModCount++;
        }
    }
}
