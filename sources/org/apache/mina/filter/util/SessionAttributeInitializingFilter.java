package org.apache.mina.filter.util;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.mina.core.filterchain.IoFilter;
import org.apache.mina.core.filterchain.IoFilterAdapter;
import org.apache.mina.core.session.IoSession;

public class SessionAttributeInitializingFilter extends IoFilterAdapter {
    private final Map<String, Object> attributes = new ConcurrentHashMap();

    public SessionAttributeInitializingFilter() {
    }

    public SessionAttributeInitializingFilter(Map<String, ? extends Object> map) {
        setAttributes(map);
    }

    public Object getAttribute(String str) {
        return this.attributes.get(str);
    }

    public Object setAttribute(String str, Object obj) {
        if (obj == null) {
            return removeAttribute(str);
        }
        return this.attributes.put(str, obj);
    }

    public Object setAttribute(String str) {
        return this.attributes.put(str, Boolean.TRUE);
    }

    public Object removeAttribute(String str) {
        return this.attributes.remove(str);
    }

    /* access modifiers changed from: package-private */
    public boolean containsAttribute(String str) {
        return this.attributes.containsKey(str);
    }

    public Set<String> getAttributeKeys() {
        return this.attributes.keySet();
    }

    public void setAttributes(Map<String, ? extends Object> map) {
        if (map == null) {
            map = new ConcurrentHashMap<>();
        }
        this.attributes.clear();
        this.attributes.putAll(map);
    }

    public void sessionCreated(IoFilter.NextFilter nextFilter, IoSession ioSession) throws Exception {
        for (Map.Entry next : this.attributes.entrySet()) {
            ioSession.setAttribute(next.getKey(), next.getValue());
        }
        nextFilter.sessionCreated(ioSession);
    }
}
