package org.apache.commons.collections4.bidimap;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.Comparable;
import java.util.AbstractSet;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import org.apache.commons.collections4.KeyValue;
import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.OrderedBidiMap;
import org.apache.commons.collections4.OrderedIterator;
import org.apache.commons.collections4.OrderedMapIterator;
import org.apache.commons.collections4.iterators.EmptyOrderedMapIterator;
import org.apache.commons.collections4.keyvalue.UnmodifiableMapEntry;

public class TreeBidiMap<K extends Comparable<K>, V extends Comparable<V>> implements OrderedBidiMap<K, V>, Serializable {
    private static final long serialVersionUID = 721969328361807L;
    private transient Set<Map.Entry<K, V>> entrySet;
    private transient TreeBidiMap<K, V>.Inverse inverse;
    private transient Set<K> keySet;
    /* access modifiers changed from: private */
    public transient int modifications;
    /* access modifiers changed from: private */
    public transient int nodeCount;
    /* access modifiers changed from: private */
    public transient Node<K, V>[] rootNode;
    private transient Set<V> valuesSet;

    enum DataElement {
        KEY("key"),
        VALUE("value");
        
        private final String description;

        private DataElement(String str) {
            this.description = str;
        }

        public String toString() {
            return this.description;
        }
    }

    public TreeBidiMap() {
        this.nodeCount = 0;
        this.modifications = 0;
        this.inverse = null;
        this.rootNode = new Node[2];
    }

    public TreeBidiMap(Map<? extends K, ? extends V> map) {
        this();
        putAll(map);
    }

    public int size() {
        return this.nodeCount;
    }

    public boolean isEmpty() {
        return this.nodeCount == 0;
    }

    public boolean containsKey(Object obj) {
        checkKey(obj);
        return lookupKey(obj) != null;
    }

    public boolean containsValue(Object obj) {
        checkValue(obj);
        return lookupValue(obj) != null;
    }

    public V get(Object obj) {
        checkKey(obj);
        Node lookupKey = lookupKey(obj);
        if (lookupKey == null) {
            return null;
        }
        return lookupKey.getValue();
    }

    public V put(K k, V v) {
        V v2 = get((Object) k);
        doPut(k, v);
        return v2;
    }

    public void putAll(Map<? extends K, ? extends V> map) {
        for (Map.Entry next : map.entrySet()) {
            put((Comparable) next.getKey(), (Comparable) next.getValue());
        }
    }

    public V remove(Object obj) {
        return doRemoveKey(obj);
    }

    public void clear() {
        modify();
        this.nodeCount = 0;
        this.rootNode[DataElement.KEY.ordinal()] = null;
        this.rootNode[DataElement.VALUE.ordinal()] = null;
    }

    public K getKey(Object obj) {
        checkValue(obj);
        Node lookupValue = lookupValue(obj);
        if (lookupValue == null) {
            return null;
        }
        return lookupValue.getKey();
    }

    public K removeValue(Object obj) {
        return doRemoveValue(obj);
    }

    public K firstKey() {
        if (this.nodeCount != 0) {
            return leastNode(this.rootNode[DataElement.KEY.ordinal()], DataElement.KEY).getKey();
        }
        throw new NoSuchElementException("Map is empty");
    }

    public K lastKey() {
        if (this.nodeCount != 0) {
            return greatestNode(this.rootNode[DataElement.KEY.ordinal()], DataElement.KEY).getKey();
        }
        throw new NoSuchElementException("Map is empty");
    }

    public K nextKey(K k) {
        checkKey(k);
        Node nextGreater = nextGreater(lookupKey(k), DataElement.KEY);
        if (nextGreater == null) {
            return null;
        }
        return nextGreater.getKey();
    }

    public K previousKey(K k) {
        checkKey(k);
        Node nextSmaller = nextSmaller(lookupKey(k), DataElement.KEY);
        if (nextSmaller == null) {
            return null;
        }
        return nextSmaller.getKey();
    }

    public Set<K> keySet() {
        if (this.keySet == null) {
            this.keySet = new KeyView(DataElement.KEY);
        }
        return this.keySet;
    }

    public Set<V> values() {
        if (this.valuesSet == null) {
            this.valuesSet = new ValueView(DataElement.KEY);
        }
        return this.valuesSet;
    }

    public Set<Map.Entry<K, V>> entrySet() {
        if (this.entrySet == null) {
            this.entrySet = new EntryView();
        }
        return this.entrySet;
    }

    public OrderedMapIterator<K, V> mapIterator() {
        if (isEmpty()) {
            return EmptyOrderedMapIterator.emptyOrderedMapIterator();
        }
        return new ViewMapIterator(DataElement.KEY);
    }

    public OrderedBidiMap<V, K> inverseBidiMap() {
        if (this.inverse == null) {
            this.inverse = new Inverse();
        }
        return this.inverse;
    }

    public boolean equals(Object obj) {
        return doEquals(obj, DataElement.KEY);
    }

    public int hashCode() {
        return doHashCode(DataElement.KEY);
    }

    public String toString() {
        return doToString(DataElement.KEY);
    }

    /* access modifiers changed from: private */
    public void doPut(K k, V v) {
        checkKeyAndValue(k, v);
        doRemoveKey(k);
        doRemoveValue(v);
        Node<K, V> node = this.rootNode[DataElement.KEY.ordinal()];
        if (node == null) {
            Node<K, V> node2 = new Node<>(k, v);
            this.rootNode[DataElement.KEY.ordinal()] = node2;
            this.rootNode[DataElement.VALUE.ordinal()] = node2;
            grow();
            return;
        }
        while (true) {
            int compare = compare(k, node.getKey());
            if (compare == 0) {
                throw new IllegalArgumentException("Cannot store a duplicate key (\"" + k + "\") in this Map");
            } else if (compare < 0) {
                if (node.getLeft(DataElement.KEY) != null) {
                    node = node.getLeft(DataElement.KEY);
                } else {
                    Node node3 = new Node(k, v);
                    insertValue(node3);
                    node.setLeft(node3, DataElement.KEY);
                    node3.setParent(node, DataElement.KEY);
                    doRedBlackInsert(node3, DataElement.KEY);
                    grow();
                    return;
                }
            } else if (node.getRight(DataElement.KEY) != null) {
                node = node.getRight(DataElement.KEY);
            } else {
                Node node4 = new Node(k, v);
                insertValue(node4);
                node.setRight(node4, DataElement.KEY);
                node4.setParent(node, DataElement.KEY);
                doRedBlackInsert(node4, DataElement.KEY);
                grow();
                return;
            }
        }
    }

    /* access modifiers changed from: private */
    public V doRemoveKey(Object obj) {
        Node lookupKey = lookupKey(obj);
        if (lookupKey == null) {
            return null;
        }
        doRedBlackDelete(lookupKey);
        return lookupKey.getValue();
    }

    /* access modifiers changed from: private */
    public K doRemoveValue(Object obj) {
        Node lookupValue = lookupValue(obj);
        if (lookupValue == null) {
            return null;
        }
        doRedBlackDelete(lookupValue);
        return lookupValue.getKey();
    }

    /* access modifiers changed from: private */
    public <T extends Comparable<T>> Node<K, V> lookup(Object obj, DataElement dataElement) {
        Node<K, V> node = this.rootNode[dataElement.ordinal()];
        while (node != null) {
            int compare = compare((Comparable) obj, (Comparable) node.getData(dataElement));
            if (compare == 0) {
                return node;
            }
            node = compare < 0 ? node.getLeft(dataElement) : node.getRight(dataElement);
        }
        return null;
    }

    /* access modifiers changed from: private */
    public Node<K, V> lookupKey(Object obj) {
        return lookup(obj, DataElement.KEY);
    }

    /* access modifiers changed from: private */
    public Node<K, V> lookupValue(Object obj) {
        return lookup(obj, DataElement.VALUE);
    }

    /* access modifiers changed from: private */
    public Node<K, V> nextGreater(Node<K, V> node, DataElement dataElement) {
        if (node == null) {
            return null;
        }
        if (node.getRight(dataElement) != null) {
            return leastNode(node.getRight(dataElement), dataElement);
        }
        Node<K, V> access$600 = node.getParent(dataElement);
        while (true) {
            Node<K, V> node2 = access$600;
            Node<K, V> node3 = node;
            node = node2;
            if (node == null || node3 != node.getRight(dataElement)) {
                return node;
            }
            access$600 = node.getParent(dataElement);
        }
    }

    /* access modifiers changed from: private */
    public Node<K, V> nextSmaller(Node<K, V> node, DataElement dataElement) {
        if (node == null) {
            return null;
        }
        if (node.getLeft(dataElement) != null) {
            return greatestNode(node.getLeft(dataElement), dataElement);
        }
        Node<K, V> access$600 = node.getParent(dataElement);
        while (true) {
            Node<K, V> node2 = access$600;
            Node<K, V> node3 = node;
            node = node2;
            if (node == null || node3 != node.getLeft(dataElement)) {
                return node;
            }
            access$600 = node.getParent(dataElement);
        }
    }

    private static <T extends Comparable<T>> int compare(T t, T t2) {
        return t.compareTo(t2);
    }

    /* access modifiers changed from: private */
    public Node<K, V> leastNode(Node<K, V> node, DataElement dataElement) {
        if (node != null) {
            while (node.getLeft(dataElement) != null) {
                node = node.getLeft(dataElement);
            }
        }
        return node;
    }

    /* access modifiers changed from: private */
    public Node<K, V> greatestNode(Node<K, V> node, DataElement dataElement) {
        if (node != null) {
            while (node.getRight(dataElement) != null) {
                node = node.getRight(dataElement);
            }
        }
        return node;
    }

    private void copyColor(Node<K, V> node, Node<K, V> node2, DataElement dataElement) {
        if (node2 == null) {
            return;
        }
        if (node == null) {
            node2.setBlack(dataElement);
        } else {
            node2.copyColor(node, dataElement);
        }
    }

    private static boolean isRed(Node<?, ?> node, DataElement dataElement) {
        return node != null && node.isRed(dataElement);
    }

    private static boolean isBlack(Node<?, ?> node, DataElement dataElement) {
        return node == null || node.isBlack(dataElement);
    }

    private static void makeRed(Node<?, ?> node, DataElement dataElement) {
        if (node != null) {
            node.setRed(dataElement);
        }
    }

    private static void makeBlack(Node<?, ?> node, DataElement dataElement) {
        if (node != null) {
            node.setBlack(dataElement);
        }
    }

    private Node<K, V> getGrandParent(Node<K, V> node, DataElement dataElement) {
        return getParent(getParent(node, dataElement), dataElement);
    }

    private Node<K, V> getParent(Node<K, V> node, DataElement dataElement) {
        if (node == null) {
            return null;
        }
        return node.getParent(dataElement);
    }

    private Node<K, V> getRightChild(Node<K, V> node, DataElement dataElement) {
        if (node == null) {
            return null;
        }
        return node.getRight(dataElement);
    }

    private Node<K, V> getLeftChild(Node<K, V> node, DataElement dataElement) {
        if (node == null) {
            return null;
        }
        return node.getLeft(dataElement);
    }

    private void rotateLeft(Node<K, V> node, DataElement dataElement) {
        Node<K, V> access$300 = node.getRight(dataElement);
        node.setRight(access$300.getLeft(dataElement), dataElement);
        if (access$300.getLeft(dataElement) != null) {
            access$300.getLeft(dataElement).setParent(node, dataElement);
        }
        access$300.setParent(node.getParent(dataElement), dataElement);
        if (node.getParent(dataElement) == null) {
            this.rootNode[dataElement.ordinal()] = access$300;
        } else if (node.getParent(dataElement).getLeft(dataElement) == node) {
            node.getParent(dataElement).setLeft(access$300, dataElement);
        } else {
            node.getParent(dataElement).setRight(access$300, dataElement);
        }
        access$300.setLeft(node, dataElement);
        node.setParent(access$300, dataElement);
    }

    private void rotateRight(Node<K, V> node, DataElement dataElement) {
        Node<K, V> access$000 = node.getLeft(dataElement);
        node.setLeft(access$000.getRight(dataElement), dataElement);
        if (access$000.getRight(dataElement) != null) {
            access$000.getRight(dataElement).setParent(node, dataElement);
        }
        access$000.setParent(node.getParent(dataElement), dataElement);
        if (node.getParent(dataElement) == null) {
            this.rootNode[dataElement.ordinal()] = access$000;
        } else if (node.getParent(dataElement).getRight(dataElement) == node) {
            node.getParent(dataElement).setRight(access$000, dataElement);
        } else {
            node.getParent(dataElement).setLeft(access$000, dataElement);
        }
        access$000.setRight(node, dataElement);
        node.setParent(access$000, dataElement);
    }

    private void doRedBlackInsert(Node<K, V> node, DataElement dataElement) {
        makeRed(node, dataElement);
        while (node != null && node != this.rootNode[dataElement.ordinal()] && isRed(node.getParent(dataElement), dataElement)) {
            if (node.isLeftChild(dataElement)) {
                Node<K, V> rightChild = getRightChild(getGrandParent(node, dataElement), dataElement);
                if (isRed(rightChild, dataElement)) {
                    makeBlack(getParent(node, dataElement), dataElement);
                    makeBlack(rightChild, dataElement);
                    makeRed(getGrandParent(node, dataElement), dataElement);
                    node = getGrandParent(node, dataElement);
                } else {
                    if (node.isRightChild(dataElement)) {
                        node = getParent(node, dataElement);
                        rotateLeft(node, dataElement);
                    }
                    makeBlack(getParent(node, dataElement), dataElement);
                    makeRed(getGrandParent(node, dataElement), dataElement);
                    if (getGrandParent(node, dataElement) != null) {
                        rotateRight(getGrandParent(node, dataElement), dataElement);
                    }
                }
            } else {
                Node<K, V> leftChild = getLeftChild(getGrandParent(node, dataElement), dataElement);
                if (isRed(leftChild, dataElement)) {
                    makeBlack(getParent(node, dataElement), dataElement);
                    makeBlack(leftChild, dataElement);
                    makeRed(getGrandParent(node, dataElement), dataElement);
                    node = getGrandParent(node, dataElement);
                } else {
                    if (node.isLeftChild(dataElement)) {
                        node = getParent(node, dataElement);
                        rotateRight(node, dataElement);
                    }
                    makeBlack(getParent(node, dataElement), dataElement);
                    makeRed(getGrandParent(node, dataElement), dataElement);
                    if (getGrandParent(node, dataElement) != null) {
                        rotateLeft(getGrandParent(node, dataElement), dataElement);
                    }
                }
            }
        }
        makeBlack(this.rootNode[dataElement.ordinal()], dataElement);
    }

    /* access modifiers changed from: private */
    public void doRedBlackDelete(Node<K, V> node) {
        for (DataElement dataElement : DataElement.values()) {
            if (!(node.getLeft(dataElement) == null || node.getRight(dataElement) == null)) {
                swapPosition(nextGreater(node, dataElement), node, dataElement);
            }
            Node<K, V> access$000 = node.getLeft(dataElement) != null ? node.getLeft(dataElement) : node.getRight(dataElement);
            if (access$000 != null) {
                access$000.setParent(node.getParent(dataElement), dataElement);
                if (node.getParent(dataElement) == null) {
                    this.rootNode[dataElement.ordinal()] = access$000;
                } else if (node == node.getParent(dataElement).getLeft(dataElement)) {
                    node.getParent(dataElement).setLeft(access$000, dataElement);
                } else {
                    node.getParent(dataElement).setRight(access$000, dataElement);
                }
                node.setLeft((Node<K, V>) null, dataElement);
                node.setRight((Node<K, V>) null, dataElement);
                node.setParent((Node<K, V>) null, dataElement);
                if (isBlack(node, dataElement)) {
                    doRedBlackDeleteFixup(access$000, dataElement);
                }
            } else if (node.getParent(dataElement) == null) {
                this.rootNode[dataElement.ordinal()] = null;
            } else {
                if (isBlack(node, dataElement)) {
                    doRedBlackDeleteFixup(node, dataElement);
                }
                if (node.getParent(dataElement) != null) {
                    if (node == node.getParent(dataElement).getLeft(dataElement)) {
                        node.getParent(dataElement).setLeft((Node) null, dataElement);
                    } else {
                        node.getParent(dataElement).setRight((Node) null, dataElement);
                    }
                    node.setParent((Node<K, V>) null, dataElement);
                }
            }
        }
        shrink();
    }

    private void doRedBlackDeleteFixup(Node<K, V> node, DataElement dataElement) {
        while (node != this.rootNode[dataElement.ordinal()] && isBlack(node, dataElement)) {
            if (node.isLeftChild(dataElement)) {
                Node<K, V> rightChild = getRightChild(getParent(node, dataElement), dataElement);
                if (isRed(rightChild, dataElement)) {
                    makeBlack(rightChild, dataElement);
                    makeRed(getParent(node, dataElement), dataElement);
                    rotateLeft(getParent(node, dataElement), dataElement);
                    rightChild = getRightChild(getParent(node, dataElement), dataElement);
                }
                if (!isBlack(getLeftChild(rightChild, dataElement), dataElement) || !isBlack(getRightChild(rightChild, dataElement), dataElement)) {
                    if (isBlack(getRightChild(rightChild, dataElement), dataElement)) {
                        makeBlack(getLeftChild(rightChild, dataElement), dataElement);
                        makeRed(rightChild, dataElement);
                        rotateRight(rightChild, dataElement);
                        rightChild = getRightChild(getParent(node, dataElement), dataElement);
                    }
                    copyColor(getParent(node, dataElement), rightChild, dataElement);
                    makeBlack(getParent(node, dataElement), dataElement);
                    makeBlack(getRightChild(rightChild, dataElement), dataElement);
                    rotateLeft(getParent(node, dataElement), dataElement);
                    node = this.rootNode[dataElement.ordinal()];
                } else {
                    makeRed(rightChild, dataElement);
                    node = getParent(node, dataElement);
                }
            } else {
                Node<K, V> leftChild = getLeftChild(getParent(node, dataElement), dataElement);
                if (isRed(leftChild, dataElement)) {
                    makeBlack(leftChild, dataElement);
                    makeRed(getParent(node, dataElement), dataElement);
                    rotateRight(getParent(node, dataElement), dataElement);
                    leftChild = getLeftChild(getParent(node, dataElement), dataElement);
                }
                if (!isBlack(getRightChild(leftChild, dataElement), dataElement) || !isBlack(getLeftChild(leftChild, dataElement), dataElement)) {
                    if (isBlack(getLeftChild(leftChild, dataElement), dataElement)) {
                        makeBlack(getRightChild(leftChild, dataElement), dataElement);
                        makeRed(leftChild, dataElement);
                        rotateLeft(leftChild, dataElement);
                        leftChild = getLeftChild(getParent(node, dataElement), dataElement);
                    }
                    copyColor(getParent(node, dataElement), leftChild, dataElement);
                    makeBlack(getParent(node, dataElement), dataElement);
                    makeBlack(getLeftChild(leftChild, dataElement), dataElement);
                    rotateRight(getParent(node, dataElement), dataElement);
                    node = this.rootNode[dataElement.ordinal()];
                } else {
                    makeRed(leftChild, dataElement);
                    node = getParent(node, dataElement);
                }
            }
        }
        makeBlack(node, dataElement);
    }

    private void swapPosition(Node<K, V> node, Node<K, V> node2, DataElement dataElement) {
        Node<K, V> access$600 = node.getParent(dataElement);
        Node access$000 = node.getLeft(dataElement);
        Node access$300 = node.getRight(dataElement);
        Node<K, V> access$6002 = node2.getParent(dataElement);
        Node access$0002 = node2.getLeft(dataElement);
        Node access$3002 = node2.getRight(dataElement);
        boolean z = true;
        boolean z2 = node.getParent(dataElement) != null && node == node.getParent(dataElement).getLeft(dataElement);
        if (node2.getParent(dataElement) == null || node2 != node2.getParent(dataElement).getLeft(dataElement)) {
            z = false;
        }
        if (node == access$6002) {
            node.setParent(node2, dataElement);
            if (z) {
                node2.setLeft(node, dataElement);
                node2.setRight(access$300, dataElement);
            } else {
                node2.setRight(node, dataElement);
                node2.setLeft(access$000, dataElement);
            }
        } else {
            node.setParent(access$6002, dataElement);
            if (access$6002 != null) {
                if (z) {
                    access$6002.setLeft(node, dataElement);
                } else {
                    access$6002.setRight(node, dataElement);
                }
            }
            node2.setLeft(access$000, dataElement);
            node2.setRight(access$300, dataElement);
        }
        if (node2 == access$600) {
            node2.setParent(node, dataElement);
            if (z2) {
                node.setLeft(node2, dataElement);
                node.setRight(access$3002, dataElement);
            } else {
                node.setRight(node2, dataElement);
                node.setLeft(access$0002, dataElement);
            }
        } else {
            node2.setParent(access$600, dataElement);
            if (access$600 != null) {
                if (z2) {
                    access$600.setLeft(node2, dataElement);
                } else {
                    access$600.setRight(node2, dataElement);
                }
            }
            node.setLeft(access$0002, dataElement);
            node.setRight(access$3002, dataElement);
        }
        if (node.getLeft(dataElement) != null) {
            node.getLeft(dataElement).setParent(node, dataElement);
        }
        if (node.getRight(dataElement) != null) {
            node.getRight(dataElement).setParent(node, dataElement);
        }
        if (node2.getLeft(dataElement) != null) {
            node2.getLeft(dataElement).setParent(node2, dataElement);
        }
        if (node2.getRight(dataElement) != null) {
            node2.getRight(dataElement).setParent(node2, dataElement);
        }
        node.swapColors(node2, dataElement);
        if (this.rootNode[dataElement.ordinal()] == node) {
            this.rootNode[dataElement.ordinal()] = node2;
        } else if (this.rootNode[dataElement.ordinal()] == node2) {
            this.rootNode[dataElement.ordinal()] = node;
        }
    }

    /* access modifiers changed from: private */
    public static void checkNonNullComparable(Object obj, DataElement dataElement) {
        if (obj == null) {
            throw new NullPointerException(dataElement + " cannot be null");
        } else if (!(obj instanceof Comparable)) {
            throw new ClassCastException(dataElement + " must be Comparable");
        }
    }

    /* access modifiers changed from: private */
    public static void checkKey(Object obj) {
        checkNonNullComparable(obj, DataElement.KEY);
    }

    private static void checkValue(Object obj) {
        checkNonNullComparable(obj, DataElement.VALUE);
    }

    private static void checkKeyAndValue(Object obj, Object obj2) {
        checkKey(obj);
        checkValue(obj2);
    }

    private void modify() {
        this.modifications++;
    }

    private void grow() {
        modify();
        this.nodeCount++;
    }

    private void shrink() {
        modify();
        this.nodeCount--;
    }

    private void insertValue(Node<K, V> node) throws IllegalArgumentException {
        Node<K, V> node2 = this.rootNode[DataElement.VALUE.ordinal()];
        while (true) {
            int compare = compare(node.getValue(), node2.getValue());
            if (compare == 0) {
                throw new IllegalArgumentException("Cannot store a duplicate value (\"" + node.getData(DataElement.VALUE) + "\") in this Map");
            } else if (compare < 0) {
                if (node2.getLeft(DataElement.VALUE) != null) {
                    node2 = node2.getLeft(DataElement.VALUE);
                } else {
                    node2.setLeft(node, DataElement.VALUE);
                    node.setParent(node2, DataElement.VALUE);
                    doRedBlackInsert(node, DataElement.VALUE);
                    return;
                }
            } else if (node2.getRight(DataElement.VALUE) != null) {
                node2 = node2.getRight(DataElement.VALUE);
            } else {
                node2.setRight(node, DataElement.VALUE);
                node.setParent(node2, DataElement.VALUE);
                doRedBlackInsert(node, DataElement.VALUE);
                return;
            }
        }
    }

    /* access modifiers changed from: private */
    public boolean doEquals(Object obj, DataElement dataElement) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Map)) {
            return false;
        }
        Map map = (Map) obj;
        if (map.size() != size()) {
            return false;
        }
        if (this.nodeCount > 0) {
            try {
                MapIterator<?, ?> mapIterator = getMapIterator(dataElement);
                while (mapIterator.hasNext()) {
                    if (!mapIterator.getValue().equals(map.get(mapIterator.next()))) {
                        return false;
                    }
                }
            } catch (ClassCastException | NullPointerException unused) {
            }
        }
        return true;
    }

    /* access modifiers changed from: private */
    public int doHashCode(DataElement dataElement) {
        int i = 0;
        if (this.nodeCount > 0) {
            MapIterator<?, ?> mapIterator = getMapIterator(dataElement);
            while (mapIterator.hasNext()) {
                i += mapIterator.next().hashCode() ^ mapIterator.getValue().hashCode();
            }
        }
        return i;
    }

    /* access modifiers changed from: private */
    public String doToString(DataElement dataElement) {
        int i = this.nodeCount;
        if (i == 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(i * 32);
        sb.append('{');
        MapIterator<?, ?> mapIterator = getMapIterator(dataElement);
        boolean hasNext = mapIterator.hasNext();
        while (hasNext) {
            Object next = mapIterator.next();
            Object value = mapIterator.getValue();
            if (next == this) {
                next = "(this Map)";
            }
            sb.append(next);
            sb.append('=');
            if (value == this) {
                value = "(this Map)";
            }
            sb.append(value);
            hasNext = mapIterator.hasNext();
            if (hasNext) {
                sb.append(", ");
            }
        }
        sb.append('}');
        return sb.toString();
    }

    /* renamed from: org.apache.commons.collections4.bidimap.TreeBidiMap$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$commons$collections4$bidimap$TreeBidiMap$DataElement;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                org.apache.commons.collections4.bidimap.TreeBidiMap$DataElement[] r0 = org.apache.commons.collections4.bidimap.TreeBidiMap.DataElement.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$apache$commons$collections4$bidimap$TreeBidiMap$DataElement = r0
                org.apache.commons.collections4.bidimap.TreeBidiMap$DataElement r1 = org.apache.commons.collections4.bidimap.TreeBidiMap.DataElement.KEY     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$apache$commons$collections4$bidimap$TreeBidiMap$DataElement     // Catch:{ NoSuchFieldError -> 0x001d }
                org.apache.commons.collections4.bidimap.TreeBidiMap$DataElement r1 = org.apache.commons.collections4.bidimap.TreeBidiMap.DataElement.VALUE     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.collections4.bidimap.TreeBidiMap.AnonymousClass1.<clinit>():void");
        }
    }

    private MapIterator<?, ?> getMapIterator(DataElement dataElement) {
        int i = AnonymousClass1.$SwitchMap$org$apache$commons$collections4$bidimap$TreeBidiMap$DataElement[dataElement.ordinal()];
        if (i == 1) {
            return new ViewMapIterator(DataElement.KEY);
        }
        if (i == 2) {
            return new InverseViewMapIterator(DataElement.VALUE);
        }
        throw new IllegalArgumentException();
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        this.rootNode = new Node[2];
        int readInt = objectInputStream.readInt();
        for (int i = 0; i < readInt; i++) {
            put((Comparable) objectInputStream.readObject(), (Comparable) objectInputStream.readObject());
        }
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeInt(size());
        for (Map.Entry entry : entrySet()) {
            objectOutputStream.writeObject(entry.getKey());
            objectOutputStream.writeObject(entry.getValue());
        }
    }

    abstract class View<E> extends AbstractSet<E> {
        final DataElement orderType;

        View(DataElement dataElement) {
            this.orderType = dataElement;
        }

        public int size() {
            return TreeBidiMap.this.size();
        }

        public void clear() {
            TreeBidiMap.this.clear();
        }
    }

    class KeyView extends TreeBidiMap<K, V>.View<K> {
        public KeyView(DataElement dataElement) {
            super(dataElement);
        }

        public Iterator<K> iterator() {
            return new ViewMapIterator(this.orderType);
        }

        public boolean contains(Object obj) {
            TreeBidiMap.checkNonNullComparable(obj, DataElement.KEY);
            return TreeBidiMap.this.lookupKey(obj) != null;
        }

        public boolean remove(Object obj) {
            return TreeBidiMap.this.doRemoveKey(obj) != null;
        }
    }

    class ValueView extends TreeBidiMap<K, V>.View<V> {
        public ValueView(DataElement dataElement) {
            super(dataElement);
        }

        public Iterator<V> iterator() {
            return new InverseViewMapIterator(this.orderType);
        }

        public boolean contains(Object obj) {
            TreeBidiMap.checkNonNullComparable(obj, DataElement.VALUE);
            return TreeBidiMap.this.lookupValue(obj) != null;
        }

        public boolean remove(Object obj) {
            return TreeBidiMap.this.doRemoveValue(obj) != null;
        }
    }

    class EntryView extends TreeBidiMap<K, V>.View<Map.Entry<K, V>> {
        EntryView() {
            super(DataElement.KEY);
        }

        public boolean contains(Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            Object value = entry.getValue();
            Node access$1600 = TreeBidiMap.this.lookupKey(entry.getKey());
            if (access$1600 == null || !access$1600.getValue().equals(value)) {
                return false;
            }
            return true;
        }

        public boolean remove(Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            Object value = entry.getValue();
            Node access$1600 = TreeBidiMap.this.lookupKey(entry.getKey());
            if (access$1600 == null || !access$1600.getValue().equals(value)) {
                return false;
            }
            TreeBidiMap.this.doRedBlackDelete(access$1600);
            return true;
        }

        public Iterator<Map.Entry<K, V>> iterator() {
            return new ViewMapEntryIterator();
        }
    }

    class InverseEntryView extends TreeBidiMap<K, V>.View<Map.Entry<V, K>> {
        InverseEntryView() {
            super(DataElement.VALUE);
        }

        public boolean contains(Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            Object value = entry.getValue();
            Node access$1800 = TreeBidiMap.this.lookupValue(entry.getKey());
            if (access$1800 == null || !access$1800.getKey().equals(value)) {
                return false;
            }
            return true;
        }

        public boolean remove(Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            Object value = entry.getValue();
            Node access$1800 = TreeBidiMap.this.lookupValue(entry.getKey());
            if (access$1800 == null || !access$1800.getKey().equals(value)) {
                return false;
            }
            TreeBidiMap.this.doRedBlackDelete(access$1800);
            return true;
        }

        public Iterator<Map.Entry<V, K>> iterator() {
            return new InverseViewMapEntryIterator();
        }
    }

    abstract class ViewIterator {
        private int expectedModifications;
        Node<K, V> lastReturnedNode = null;
        private Node<K, V> nextNode;
        private final DataElement orderType;
        private Node<K, V> previousNode = null;

        ViewIterator(DataElement dataElement) {
            this.orderType = dataElement;
            this.expectedModifications = TreeBidiMap.this.modifications;
            this.nextNode = TreeBidiMap.this.leastNode(TreeBidiMap.this.rootNode[dataElement.ordinal()], dataElement);
        }

        public final boolean hasNext() {
            return this.nextNode != null;
        }

        /* access modifiers changed from: protected */
        public Node<K, V> navigateNext() {
            if (this.nextNode == null) {
                throw new NoSuchElementException();
            } else if (TreeBidiMap.this.modifications == this.expectedModifications) {
                Node<K, V> node = this.nextNode;
                this.lastReturnedNode = node;
                this.previousNode = node;
                this.nextNode = TreeBidiMap.this.nextGreater(node, this.orderType);
                return this.lastReturnedNode;
            } else {
                throw new ConcurrentModificationException();
            }
        }

        public boolean hasPrevious() {
            return this.previousNode != null;
        }

        /* access modifiers changed from: protected */
        public Node<K, V> navigatePrevious() {
            if (this.previousNode == null) {
                throw new NoSuchElementException();
            } else if (TreeBidiMap.this.modifications == this.expectedModifications) {
                Node<K, V> node = this.lastReturnedNode;
                this.nextNode = node;
                if (node == null) {
                    this.nextNode = TreeBidiMap.this.nextGreater(this.previousNode, this.orderType);
                }
                Node<K, V> node2 = this.previousNode;
                this.lastReturnedNode = node2;
                this.previousNode = TreeBidiMap.this.nextSmaller(node2, this.orderType);
                return this.lastReturnedNode;
            } else {
                throw new ConcurrentModificationException();
            }
        }

        public final void remove() {
            if (this.lastReturnedNode == null) {
                throw new IllegalStateException();
            } else if (TreeBidiMap.this.modifications == this.expectedModifications) {
                TreeBidiMap.this.doRedBlackDelete(this.lastReturnedNode);
                this.expectedModifications++;
                this.lastReturnedNode = null;
                Node<K, V> node = this.nextNode;
                if (node == null) {
                    TreeBidiMap treeBidiMap = TreeBidiMap.this;
                    this.previousNode = treeBidiMap.greatestNode(treeBidiMap.rootNode[this.orderType.ordinal()], this.orderType);
                    return;
                }
                this.previousNode = TreeBidiMap.this.nextSmaller(node, this.orderType);
            } else {
                throw new ConcurrentModificationException();
            }
        }
    }

    class ViewMapIterator extends TreeBidiMap<K, V>.ViewIterator implements OrderedMapIterator<K, V> {
        ViewMapIterator(DataElement dataElement) {
            super(dataElement);
        }

        public K getKey() {
            if (this.lastReturnedNode != null) {
                return this.lastReturnedNode.getKey();
            }
            throw new IllegalStateException("Iterator getKey() can only be called after next() and before remove()");
        }

        public V getValue() {
            if (this.lastReturnedNode != null) {
                return this.lastReturnedNode.getValue();
            }
            throw new IllegalStateException("Iterator getValue() can only be called after next() and before remove()");
        }

        public V setValue(V v) {
            throw new UnsupportedOperationException();
        }

        public K next() {
            return navigateNext().getKey();
        }

        public K previous() {
            return navigatePrevious().getKey();
        }
    }

    class InverseViewMapIterator extends TreeBidiMap<K, V>.ViewIterator implements OrderedMapIterator<V, K> {
        public InverseViewMapIterator(DataElement dataElement) {
            super(dataElement);
        }

        public V getKey() {
            if (this.lastReturnedNode != null) {
                return this.lastReturnedNode.getValue();
            }
            throw new IllegalStateException("Iterator getKey() can only be called after next() and before remove()");
        }

        public K getValue() {
            if (this.lastReturnedNode != null) {
                return this.lastReturnedNode.getKey();
            }
            throw new IllegalStateException("Iterator getValue() can only be called after next() and before remove()");
        }

        public K setValue(K k) {
            throw new UnsupportedOperationException();
        }

        public V next() {
            return navigateNext().getValue();
        }

        public V previous() {
            return navigatePrevious().getValue();
        }
    }

    class ViewMapEntryIterator extends TreeBidiMap<K, V>.ViewIterator implements OrderedIterator<Map.Entry<K, V>> {
        ViewMapEntryIterator() {
            super(DataElement.KEY);
        }

        public Map.Entry<K, V> next() {
            return navigateNext();
        }

        public Map.Entry<K, V> previous() {
            return navigatePrevious();
        }
    }

    class InverseViewMapEntryIterator extends TreeBidiMap<K, V>.ViewIterator implements OrderedIterator<Map.Entry<V, K>> {
        InverseViewMapEntryIterator() {
            super(DataElement.VALUE);
        }

        public Map.Entry<V, K> next() {
            return createEntry(navigateNext());
        }

        public Map.Entry<V, K> previous() {
            return createEntry(navigatePrevious());
        }

        private Map.Entry<V, K> createEntry(Node<K, V> node) {
            return new UnmodifiableMapEntry(node.getValue(), node.getKey());
        }
    }

    static class Node<K extends Comparable<K>, V extends Comparable<V>> implements Map.Entry<K, V>, KeyValue<K, V> {
        private final boolean[] blackColor = {true, true};
        private boolean calculatedHashCode = false;
        private int hashcodeValue;
        private final K key;
        private final Node<K, V>[] leftNode = new Node[2];
        private final Node<K, V>[] parentNode = new Node[2];
        private final Node<K, V>[] rightNode = new Node[2];
        private final V value;

        Node(K k, V v) {
            this.key = k;
            this.value = v;
        }

        /* access modifiers changed from: private */
        public Object getData(DataElement dataElement) {
            int i = AnonymousClass1.$SwitchMap$org$apache$commons$collections4$bidimap$TreeBidiMap$DataElement[dataElement.ordinal()];
            if (i == 1) {
                return getKey();
            }
            if (i == 2) {
                return getValue();
            }
            throw new IllegalArgumentException();
        }

        /* access modifiers changed from: private */
        public void setLeft(Node<K, V> node, DataElement dataElement) {
            this.leftNode[dataElement.ordinal()] = node;
        }

        /* access modifiers changed from: private */
        public Node<K, V> getLeft(DataElement dataElement) {
            return this.leftNode[dataElement.ordinal()];
        }

        /* access modifiers changed from: private */
        public void setRight(Node<K, V> node, DataElement dataElement) {
            this.rightNode[dataElement.ordinal()] = node;
        }

        /* access modifiers changed from: private */
        public Node<K, V> getRight(DataElement dataElement) {
            return this.rightNode[dataElement.ordinal()];
        }

        /* access modifiers changed from: private */
        public void setParent(Node<K, V> node, DataElement dataElement) {
            this.parentNode[dataElement.ordinal()] = node;
        }

        /* access modifiers changed from: private */
        public Node<K, V> getParent(DataElement dataElement) {
            return this.parentNode[dataElement.ordinal()];
        }

        /* access modifiers changed from: private */
        public void swapColors(Node<K, V> node, DataElement dataElement) {
            boolean[] zArr = this.blackColor;
            int ordinal = dataElement.ordinal();
            zArr[ordinal] = zArr[ordinal] ^ node.blackColor[dataElement.ordinal()];
            boolean[] zArr2 = node.blackColor;
            int ordinal2 = dataElement.ordinal();
            zArr2[ordinal2] = zArr2[ordinal2] ^ this.blackColor[dataElement.ordinal()];
            boolean[] zArr3 = this.blackColor;
            int ordinal3 = dataElement.ordinal();
            zArr3[ordinal3] = node.blackColor[dataElement.ordinal()] ^ zArr3[ordinal3];
        }

        /* access modifiers changed from: private */
        public boolean isBlack(DataElement dataElement) {
            return this.blackColor[dataElement.ordinal()];
        }

        /* access modifiers changed from: private */
        public boolean isRed(DataElement dataElement) {
            return !this.blackColor[dataElement.ordinal()];
        }

        /* access modifiers changed from: private */
        public void setBlack(DataElement dataElement) {
            this.blackColor[dataElement.ordinal()] = true;
        }

        /* access modifiers changed from: private */
        public void setRed(DataElement dataElement) {
            this.blackColor[dataElement.ordinal()] = false;
        }

        /* access modifiers changed from: private */
        public void copyColor(Node<K, V> node, DataElement dataElement) {
            this.blackColor[dataElement.ordinal()] = node.blackColor[dataElement.ordinal()];
        }

        /* access modifiers changed from: private */
        public boolean isLeftChild(DataElement dataElement) {
            return this.parentNode[dataElement.ordinal()] != null && this.parentNode[dataElement.ordinal()].leftNode[dataElement.ordinal()] == this;
        }

        /* access modifiers changed from: private */
        public boolean isRightChild(DataElement dataElement) {
            return this.parentNode[dataElement.ordinal()] != null && this.parentNode[dataElement.ordinal()].rightNode[dataElement.ordinal()] == this;
        }

        public K getKey() {
            return this.key;
        }

        public V getValue() {
            return this.value;
        }

        public V setValue(V v) throws UnsupportedOperationException {
            throw new UnsupportedOperationException("Map.Entry.setValue is not supported");
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            if (!getKey().equals(entry.getKey()) || !getValue().equals(entry.getValue())) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            if (!this.calculatedHashCode) {
                this.hashcodeValue = getKey().hashCode() ^ getValue().hashCode();
                this.calculatedHashCode = true;
            }
            return this.hashcodeValue;
        }
    }

    class Inverse implements OrderedBidiMap<V, K> {
        private Set<Map.Entry<V, K>> inverseEntrySet;
        private Set<V> inverseKeySet;
        private Set<K> inverseValuesSet;

        Inverse() {
        }

        public int size() {
            return TreeBidiMap.this.size();
        }

        public boolean isEmpty() {
            return TreeBidiMap.this.isEmpty();
        }

        public K get(Object obj) {
            return TreeBidiMap.this.getKey(obj);
        }

        public V getKey(Object obj) {
            return TreeBidiMap.this.get(obj);
        }

        public boolean containsKey(Object obj) {
            return TreeBidiMap.this.containsValue(obj);
        }

        public boolean containsValue(Object obj) {
            return TreeBidiMap.this.containsKey(obj);
        }

        public V firstKey() {
            if (TreeBidiMap.this.nodeCount != 0) {
                TreeBidiMap treeBidiMap = TreeBidiMap.this;
                return treeBidiMap.leastNode(treeBidiMap.rootNode[DataElement.VALUE.ordinal()], DataElement.VALUE).getValue();
            }
            throw new NoSuchElementException("Map is empty");
        }

        public V lastKey() {
            if (TreeBidiMap.this.nodeCount != 0) {
                TreeBidiMap treeBidiMap = TreeBidiMap.this;
                return treeBidiMap.greatestNode(treeBidiMap.rootNode[DataElement.VALUE.ordinal()], DataElement.VALUE).getValue();
            }
            throw new NoSuchElementException("Map is empty");
        }

        public V nextKey(V v) {
            TreeBidiMap.checkKey(v);
            TreeBidiMap treeBidiMap = TreeBidiMap.this;
            Node access$2400 = treeBidiMap.nextGreater(treeBidiMap.lookup(v, DataElement.VALUE), DataElement.VALUE);
            if (access$2400 == null) {
                return null;
            }
            return access$2400.getValue();
        }

        public V previousKey(V v) {
            TreeBidiMap.checkKey(v);
            TreeBidiMap treeBidiMap = TreeBidiMap.this;
            Node access$2500 = treeBidiMap.nextSmaller(treeBidiMap.lookup(v, DataElement.VALUE), DataElement.VALUE);
            if (access$2500 == null) {
                return null;
            }
            return access$2500.getValue();
        }

        public K put(V v, K k) {
            K k2 = get((Object) v);
            TreeBidiMap.this.doPut(k, v);
            return k2;
        }

        public void putAll(Map<? extends V, ? extends K> map) {
            for (Map.Entry next : map.entrySet()) {
                put((Comparable) next.getKey(), (Comparable) next.getValue());
            }
        }

        public K remove(Object obj) {
            return TreeBidiMap.this.removeValue(obj);
        }

        public V removeValue(Object obj) {
            return TreeBidiMap.this.remove(obj);
        }

        public void clear() {
            TreeBidiMap.this.clear();
        }

        public Set<V> keySet() {
            if (this.inverseKeySet == null) {
                this.inverseKeySet = new ValueView(DataElement.VALUE);
            }
            return this.inverseKeySet;
        }

        public Set<K> values() {
            if (this.inverseValuesSet == null) {
                this.inverseValuesSet = new KeyView(DataElement.VALUE);
            }
            return this.inverseValuesSet;
        }

        public Set<Map.Entry<V, K>> entrySet() {
            if (this.inverseEntrySet == null) {
                this.inverseEntrySet = new InverseEntryView();
            }
            return this.inverseEntrySet;
        }

        public OrderedMapIterator<V, K> mapIterator() {
            if (isEmpty()) {
                return EmptyOrderedMapIterator.emptyOrderedMapIterator();
            }
            return new InverseViewMapIterator(DataElement.VALUE);
        }

        public OrderedBidiMap<K, V> inverseBidiMap() {
            return TreeBidiMap.this;
        }

        public boolean equals(Object obj) {
            return TreeBidiMap.this.doEquals(obj, DataElement.VALUE);
        }

        public int hashCode() {
            return TreeBidiMap.this.doHashCode(DataElement.VALUE);
        }

        public String toString() {
            return TreeBidiMap.this.doToString(DataElement.VALUE);
        }
    }
}
