package com.ciot.base.livedatabus;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class BusMutableLiveData<T> extends MutableLiveData<T> {
    private Map<Observer, Observer> observerMap = new HashMap();

    public void observe(LifecycleOwner lifecycleOwner, Observer<? super T> observer) {
        super.observe(lifecycleOwner, observer);
        try {
            hook(observer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void observeForever(Observer<? super T> observer) {
        if (!this.observerMap.containsKey(observer)) {
            this.observerMap.put(observer, new ObserverWrapper(observer));
        }
        super.observeForever(this.observerMap.get(observer));
    }

    public void removeObserver(Observer<? super T> observer) {
        if (this.observerMap.containsKey(observer)) {
            observer = this.observerMap.remove(observer);
        }
        super.removeObserver(observer);
    }

    private void hook(Observer<? super T> observer) throws Exception {
        Class<LiveData> cls = LiveData.class;
        Field declaredField = cls.getDeclaredField("mObservers");
        declaredField.setAccessible(true);
        Object obj = declaredField.get(this);
        Method declaredMethod = obj.getClass().getDeclaredMethod("get", new Class[]{Object.class});
        declaredMethod.setAccessible(true);
        Object invoke = declaredMethod.invoke(obj, new Object[]{observer});
        Object value = invoke instanceof Map.Entry ? ((Map.Entry) invoke).getValue() : null;
        if (value != null) {
            Field declaredField2 = value.getClass().getSuperclass().getDeclaredField("mLastVersion");
            declaredField2.setAccessible(true);
            Field declaredField3 = cls.getDeclaredField("mVersion");
            declaredField3.setAccessible(true);
            declaredField2.set(value, declaredField3.get(this));
            return;
        }
        throw new NullPointerException("Wrapper can not be bull!");
    }
}
