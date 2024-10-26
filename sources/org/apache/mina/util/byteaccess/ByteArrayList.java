package org.apache.mina.util.byteaccess;

import java.util.NoSuchElementException;

class ByteArrayList {
    private int firstByte;
    /* access modifiers changed from: private */
    public final Node header = new Node();
    private int lastByte;

    protected ByteArrayList() {
    }

    public int lastByte() {
        return this.lastByte;
    }

    public int firstByte() {
        return this.firstByte;
    }

    public boolean isEmpty() {
        return this.header.next == this.header;
    }

    public Node getFirst() {
        return this.header.getNextNode();
    }

    public Node getLast() {
        return this.header.getPreviousNode();
    }

    public void addFirst(ByteArray byteArray) {
        addNode(new Node(byteArray), this.header.next);
        this.firstByte -= byteArray.last();
    }

    public void addLast(ByteArray byteArray) {
        addNode(new Node(byteArray), this.header);
        this.lastByte += byteArray.last();
    }

    public Node removeFirst() {
        Node nextNode = this.header.getNextNode();
        this.firstByte += nextNode.ba.last();
        return removeNode(nextNode);
    }

    public Node removeLast() {
        Node previousNode = this.header.getPreviousNode();
        this.lastByte -= previousNode.ba.last();
        return removeNode(previousNode);
    }

    /* access modifiers changed from: protected */
    public void addNode(Node node, Node node2) {
        Node unused = node.next = node2;
        Node unused2 = node.previous = node2.previous;
        Node unused3 = node2.previous.next = node;
        Node unused4 = node2.previous = node;
    }

    /* access modifiers changed from: protected */
    public Node removeNode(Node node) {
        Node unused = node.previous.next = node.next;
        Node unused2 = node.next.previous = node.previous;
        boolean unused3 = node.removed = true;
        return node;
    }

    public class Node {
        /* access modifiers changed from: private */
        public ByteArray ba;
        /* access modifiers changed from: private */
        public Node next;
        /* access modifiers changed from: private */
        public Node previous;
        /* access modifiers changed from: private */
        public boolean removed;

        private Node() {
            this.previous = this;
            this.next = this;
        }

        private Node(ByteArray byteArray) {
            if (byteArray != null) {
                this.ba = byteArray;
                return;
            }
            throw new IllegalArgumentException("ByteArray must not be null.");
        }

        public Node getPreviousNode() {
            if (hasPreviousNode()) {
                return this.previous;
            }
            throw new NoSuchElementException();
        }

        public Node getNextNode() {
            if (hasNextNode()) {
                return this.next;
            }
            throw new NoSuchElementException();
        }

        public boolean hasPreviousNode() {
            return this.previous != ByteArrayList.this.header;
        }

        public boolean hasNextNode() {
            return this.next != ByteArrayList.this.header;
        }

        public ByteArray getByteArray() {
            return this.ba;
        }

        public boolean isRemoved() {
            return this.removed;
        }
    }
}
