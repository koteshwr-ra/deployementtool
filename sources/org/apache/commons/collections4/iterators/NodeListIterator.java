package org.apache.commons.collections4.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class NodeListIterator implements Iterator<Node> {
    private int index = 0;
    private final NodeList nodeList;

    public NodeListIterator(Node node) {
        if (node != null) {
            this.nodeList = node.getChildNodes();
            return;
        }
        throw new NullPointerException("Node must not be null.");
    }

    public NodeListIterator(NodeList nodeList2) {
        if (nodeList2 != null) {
            this.nodeList = nodeList2;
            return;
        }
        throw new NullPointerException("NodeList must not be null.");
    }

    public boolean hasNext() {
        NodeList nodeList2 = this.nodeList;
        return nodeList2 != null && this.index < nodeList2.getLength();
    }

    public Node next() {
        NodeList nodeList2 = this.nodeList;
        if (nodeList2 == null || this.index >= nodeList2.getLength()) {
            throw new NoSuchElementException("underlying nodeList has no more elements");
        }
        NodeList nodeList3 = this.nodeList;
        int i = this.index;
        this.index = i + 1;
        return nodeList3.item(i);
    }

    public void remove() {
        throw new UnsupportedOperationException("remove() method not supported for a NodeListIterator.");
    }
}
