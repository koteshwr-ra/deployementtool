package org.slf4j.helpers;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.slf4j.spi.MDCAdapter;

public class BasicMDCAdapter implements MDCAdapter {
    private InheritableThreadLocal inheritableThreadLocal = new InheritableThreadLocal();

    public void put(String str, String str2) {
        if (str != null) {
            HashMap hashMap = (HashMap) this.inheritableThreadLocal.get();
            if (hashMap == null) {
                hashMap = new HashMap();
                this.inheritableThreadLocal.set(hashMap);
            }
            hashMap.put(str, str2);
            return;
        }
        throw new IllegalArgumentException("key cannot be null");
    }

    public String get(String str) {
        HashMap hashMap = (HashMap) this.inheritableThreadLocal.get();
        if (hashMap == null || str == null) {
            return null;
        }
        return (String) hashMap.get(str);
    }

    public void remove(String str) {
        HashMap hashMap = (HashMap) this.inheritableThreadLocal.get();
        if (hashMap != null) {
            hashMap.remove(str);
        }
    }

    public void clear() {
        HashMap hashMap = (HashMap) this.inheritableThreadLocal.get();
        if (hashMap != null) {
            hashMap.clear();
            this.inheritableThreadLocal.remove();
        }
    }

    public Set getKeys() {
        HashMap hashMap = (HashMap) this.inheritableThreadLocal.get();
        if (hashMap != null) {
            return hashMap.keySet();
        }
        return null;
    }

    public Map getCopyOfContextMap() {
        HashMap hashMap = (HashMap) this.inheritableThreadLocal.get();
        if (hashMap != null) {
            return new HashMap(hashMap);
        }
        return null;
    }

    public void setContextMap(Map map) {
        HashMap hashMap = (HashMap) this.inheritableThreadLocal.get();
        if (hashMap != null) {
            hashMap.clear();
            hashMap.putAll(map);
            return;
        }
        this.inheritableThreadLocal.set(new HashMap(map));
    }
}
