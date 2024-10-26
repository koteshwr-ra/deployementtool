package com.ciot.base.livedatabus;

import androidx.lifecycle.MutableLiveData;
import java.util.HashMap;
import java.util.Map;

public class LiveDatabus {
    private final Map<String, BusMutableLiveData> bus;
    private final Map<String, MutableLiveData> stickyBus;

    private LiveDatabus() {
        this.stickyBus = new HashMap();
        this.bus = new HashMap();
    }

    private static class singleHolder {
        /* access modifiers changed from: private */
        public static final LiveDatabus SINGLE_BUS = new LiveDatabus();

        private singleHolder() {
        }
    }

    public static LiveDatabus getInstance() {
        return singleHolder.SINGLE_BUS;
    }

    public MutableLiveData<Object> with(String str) {
        return with(str, Object.class);
    }

    public <T> MutableLiveData<T> with(String str, Class<T> cls) {
        if (!this.bus.containsKey(str)) {
            this.bus.put(str, new BusMutableLiveData());
        }
        return this.bus.get(str);
    }

    public MutableLiveData<Object> withSticky(String str) {
        return withSticky(str, Object.class);
    }

    public <T> MutableLiveData<T> withSticky(String str, Class<T> cls) {
        if (!this.stickyBus.containsKey(str)) {
            this.stickyBus.put(str, new MutableLiveData());
        }
        return this.stickyBus.get(str);
    }
}
