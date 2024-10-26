package mc.csst.com.selfchassis.utils.bean;

import java.io.Serializable;
import mc.csst.com.selfchassis.utils.bean.UndoRedoLinkedList.Entry;

public class UndoRedoLinkedList<T extends Entry> implements Serializable {
    private static final String TAG = UndoRedoLinkedList.class.getSimpleName();
    private static final long serialVersionUID = -276760562121245410L;
    private volatile int mCount = 5;
    private volatile UndoRedoLinkedList<T> mCurrentNode;
    private T mData;
    private UndoRedoLinkedList<T> mHead;
    private volatile UndoRedoLinkedList<T> mNext;
    private volatile UndoRedoLinkedList<T> mPrevious;
    private UndoRedoLinkedList<T> mTail;

    public interface Entry {
        void onDestroy();
    }

    public UndoRedoLinkedList(T t) {
        this.mData = t;
    }

    public void setCount(int i) {
        this.mCount = i;
    }

    public void put(T t) {
        synchronized (this) {
            deleteAfterNode(this.mCurrentNode);
            if (size() >= this.mCount) {
                insertInTail(t);
                replaceCurrentHead();
                return;
            }
            insertInTail(t);
        }
    }

    public synchronized T undo() {
        return getPreNode();
    }

    public synchronized T redo() {
        return getNextNode();
    }

    public synchronized void removeAll() {
        if (this.mHead != null) {
            UndoRedoLinkedList<T> undoRedoLinkedList = this.mHead;
            while (undoRedoLinkedList != this.mHead.mPrevious) {
                UndoRedoLinkedList<T> undoRedoLinkedList2 = undoRedoLinkedList.mNext;
                undoRedoLinkedList.mData.onDestroy();
                undoRedoLinkedList.mNext = null;
                undoRedoLinkedList.mPrevious = null;
                undoRedoLinkedList = undoRedoLinkedList2;
            }
            this.mHead = null;
            this.mTail = null;
            this.mCurrentNode = null;
        }
    }

    private void replaceCurrentHead() {
        UndoRedoLinkedList<T> undoRedoLinkedList = this.mHead;
        this.mHead = undoRedoLinkedList.mNext;
        undoRedoLinkedList.mData.onDestroy();
        undoRedoLinkedList.mNext = null;
        undoRedoLinkedList.mPrevious = null;
        this.mTail.mNext = this.mHead;
        this.mHead.mPrevious = this.mTail;
    }

    private synchronized int size() {
        if (this.mTail == null) {
            return 0;
        }
        int i = 1;
        for (UndoRedoLinkedList<T> undoRedoLinkedList = this.mTail; undoRedoLinkedList != this.mTail.mNext; undoRedoLinkedList = undoRedoLinkedList.mPrevious) {
            i++;
        }
        return i;
    }

    private void insertInTail(T t) {
        UndoRedoLinkedList<T> undoRedoLinkedList = new UndoRedoLinkedList<>(t);
        this.mCurrentNode = undoRedoLinkedList;
        UndoRedoLinkedList<T> undoRedoLinkedList2 = this.mTail;
        if (undoRedoLinkedList2 == null) {
            this.mHead = undoRedoLinkedList;
            this.mTail = undoRedoLinkedList;
            undoRedoLinkedList.mNext = undoRedoLinkedList;
            this.mHead.mPrevious = this.mTail;
            return;
        }
        undoRedoLinkedList.mPrevious = undoRedoLinkedList2;
        this.mTail.mNext = undoRedoLinkedList;
        this.mTail = undoRedoLinkedList;
        undoRedoLinkedList.mNext = this.mHead;
        this.mHead.mPrevious = this.mTail;
    }

    private void deleteAfterNode(UndoRedoLinkedList<T> undoRedoLinkedList) {
        if (undoRedoLinkedList != null) {
            UndoRedoLinkedList<T> undoRedoLinkedList2 = undoRedoLinkedList.mNext;
            while (true) {
                UndoRedoLinkedList<T> undoRedoLinkedList3 = this.mHead;
                if (undoRedoLinkedList2 != undoRedoLinkedList3) {
                    UndoRedoLinkedList<T> undoRedoLinkedList4 = undoRedoLinkedList2.mNext;
                    undoRedoLinkedList2.mData.onDestroy();
                    undoRedoLinkedList2.mNext = null;
                    undoRedoLinkedList2.mPrevious = null;
                    undoRedoLinkedList2 = undoRedoLinkedList4;
                } else {
                    this.mTail = undoRedoLinkedList;
                    undoRedoLinkedList.mNext = undoRedoLinkedList3;
                    this.mHead.mPrevious = this.mTail;
                    return;
                }
            }
        }
    }

    private synchronized T getPreNode() {
        if (this.mHead == null) {
            return null;
        }
        if (isLeftBound()) {
            return this.mHead.mData;
        }
        this.mCurrentNode = this.mCurrentNode.mPrevious;
        return this.mCurrentNode.mData;
    }

    private synchronized T getNextNode() {
        if (this.mTail == null) {
            return null;
        }
        if (isRightBound()) {
            return this.mTail.mData;
        }
        this.mCurrentNode = this.mCurrentNode.mNext;
        return this.mCurrentNode.mData;
    }

    public synchronized boolean isLeftBound() {
        return this.mCurrentNode == this.mHead || this.mCurrentNode == null;
    }

    public synchronized boolean isRightBound() {
        return this.mCurrentNode == this.mTail || this.mCurrentNode == null;
    }
}
