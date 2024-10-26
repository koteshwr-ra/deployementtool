package org.slf4j.impl;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import org.apache.log4j.MDC;
import org.slf4j.spi.MDCAdapter;

public class Log4jMDCAdapter implements MDCAdapter {
    public void clear() {
        Hashtable context = MDC.getContext();
        if (context != null) {
            context.clear();
        }
    }

    public String get(String str) {
        return (String) MDC.get(str);
    }

    public void put(String str, String str2) {
        MDC.put(str, str2);
    }

    public void remove(String str) {
        MDC.remove(str);
    }

    public Map getCopyOfContextMap() {
        Hashtable context = MDC.getContext();
        if (context != null) {
            return new HashMap(context);
        }
        return null;
    }

    public void setContextMap(Map map) {
        Hashtable context = MDC.getContext();
        if (context == null) {
            for (Map.Entry entry : map.entrySet()) {
                MDC.put((String) entry.getKey(), entry.getValue());
            }
            return;
        }
        context.clear();
        context.putAll(map);
    }
}
