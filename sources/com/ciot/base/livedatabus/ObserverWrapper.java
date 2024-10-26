package com.ciot.base.livedatabus;

import androidx.lifecycle.Observer;

public class ObserverWrapper<T> implements Observer<T> {
    private Observer<T> observer;

    public ObserverWrapper(Observer<T> observer2) {
        this.observer = observer2;
    }

    public void onChanged(T t) {
        if (this.observer != null && !isCallOnObserve()) {
            this.observer.onChanged(t);
        }
    }

    private boolean isCallOnObserve() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        if (stackTrace != null && stackTrace.length > 0) {
            for (StackTraceElement stackTraceElement : stackTrace) {
                if ("android.arch.lifecycle.LiveData".equals(stackTraceElement.getClassName()) && "observeForever".equals(stackTraceElement.getMethodName())) {
                    return true;
                }
            }
        }
        return false;
    }
}
