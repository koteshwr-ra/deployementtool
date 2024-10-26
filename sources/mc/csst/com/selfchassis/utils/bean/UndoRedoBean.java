package mc.csst.com.selfchassis.utils.bean;

import mc.csst.com.selfchassis.utils.bean.UndoRedoLinkedList;

public class UndoRedoBean<T> implements UndoRedoLinkedList.Entry {
    private T mData = null;

    public void onDestroy() {
    }

    public void setData(T t) {
        this.mData = t;
    }

    public T getData() {
        return this.mData;
    }
}
