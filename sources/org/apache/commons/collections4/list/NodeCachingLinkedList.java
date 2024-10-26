package org.apache.commons.collections4.list;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
import org.apache.commons.collections4.list.AbstractLinkedList;

public class NodeCachingLinkedList<E> extends AbstractLinkedList<E> implements Serializable {
    private static final int DEFAULT_MAXIMUM_CACHE_SIZE = 20;
    private static final long serialVersionUID = 6897789178562232073L;
    private transient int cacheSize;
    private transient AbstractLinkedList.Node<E> firstCachedNode;
    private int maximumCacheSize;

    public NodeCachingLinkedList() {
        this(20);
    }

    public NodeCachingLinkedList(Collection<? extends E> collection) {
        super(collection);
        this.maximumCacheSize = 20;
    }

    public NodeCachingLinkedList(int i) {
        this.maximumCacheSize = i;
        init();
    }

    /* access modifiers changed from: protected */
    public int getMaximumCacheSize() {
        return this.maximumCacheSize;
    }

    /* access modifiers changed from: protected */
    public void setMaximumCacheSize(int i) {
        this.maximumCacheSize = i;
        shrinkCacheToMaximumSize();
    }

    /* access modifiers changed from: protected */
    public void shrinkCacheToMaximumSize() {
        while (this.cacheSize > this.maximumCacheSize) {
            getNodeFromCache();
        }
    }

    /* access modifiers changed from: protected */
    public AbstractLinkedList.Node<E> getNodeFromCache() {
        if (this.cacheSize == 0) {
            return null;
        }
        AbstractLinkedList.Node<E> node = this.firstCachedNode;
        this.firstCachedNode = node.next;
        node.next = null;
        this.cacheSize--;
        return node;
    }

    /* access modifiers changed from: protected */
    public boolean isCacheFull() {
        return this.cacheSize >= this.maximumCacheSize;
    }

    /* access modifiers changed from: protected */
    public void addNodeToCache(AbstractLinkedList.Node<E> node) {
        if (!isCacheFull()) {
            AbstractLinkedList.Node<E> node2 = this.firstCachedNode;
            node.previous = null;
            node.next = node2;
            node.setValue(null);
            this.firstCachedNode = node;
            this.cacheSize++;
        }
    }

    /* access modifiers changed from: protected */
    public AbstractLinkedList.Node<E> createNode(E e) {
        AbstractLinkedList.Node<E> nodeFromCache = getNodeFromCache();
        if (nodeFromCache == null) {
            return super.createNode(e);
        }
        nodeFromCache.setValue(e);
        return nodeFromCache;
    }

    /* access modifiers changed from: protected */
    public void removeNode(AbstractLinkedList.Node<E> node) {
        super.removeNode(node);
        addNodeToCache(node);
    }

    /* access modifiers changed from: protected */
    public void removeAllNodes() {
        int min = Math.min(this.size, this.maximumCacheSize - this.cacheSize);
        AbstractLinkedList.Node<E> node = this.header.next;
        int i = 0;
        while (i < min) {
            AbstractLinkedList.Node<E> node2 = node.next;
            addNodeToCache(node);
            i++;
            node = node2;
        }
        super.removeAllNodes();
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        doWriteObject(objectOutputStream);
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        doReadObject(objectInputStream);
    }
}
