package org.apache.mina.filter.logging;

import java.net.InetSocketAddress;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.mina.core.session.AttributeKey;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.util.CommonEventFilter;
import org.slf4j.MDC;

public class MdcInjectionFilter extends CommonEventFilter {
    private static final AttributeKey CONTEXT_KEY = new AttributeKey(MdcInjectionFilter.class, "context");
    private ThreadLocal<Integer> callDepth;
    private EnumSet<MdcKey> mdcKeys;

    public enum MdcKey {
        handlerClass,
        remoteAddress,
        localAddress,
        remoteIp,
        remotePort,
        localIp,
        localPort
    }

    public MdcInjectionFilter(EnumSet<MdcKey> enumSet) {
        this.callDepth = new ThreadLocal<Integer>() {
            /* access modifiers changed from: protected */
            public Integer initialValue() {
                return 0;
            }
        };
        this.mdcKeys = enumSet.clone();
    }

    public MdcInjectionFilter(MdcKey... mdcKeyArr) {
        this.callDepth = new ThreadLocal<Integer>() {
            /* access modifiers changed from: protected */
            public Integer initialValue() {
                return 0;
            }
        };
        this.mdcKeys = EnumSet.copyOf(new HashSet(Arrays.asList(mdcKeyArr)));
    }

    public MdcInjectionFilter() {
        this.callDepth = new ThreadLocal<Integer>() {
            /* access modifiers changed from: protected */
            public Integer initialValue() {
                return 0;
            }
        };
        this.mdcKeys = EnumSet.allOf(MdcKey.class);
    }

    /*  JADX ERROR: StackOverflow in pass: MarkFinallyVisitor
        jadx.core.utils.exceptions.JadxOverflowException: 
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    protected void filter(org.apache.mina.core.filterchain.IoFilterEvent r6) throws java.lang.Exception {
        /*
            r5 = this;
            java.lang.ThreadLocal<java.lang.Integer> r0 = r5.callDepth
            java.lang.Object r0 = r0.get()
            java.lang.Integer r0 = (java.lang.Integer) r0
            int r0 = r0.intValue()
            java.lang.ThreadLocal<java.lang.Integer> r1 = r5.callDepth
            int r2 = r0 + 1
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r1.set(r2)
            org.apache.mina.core.session.IoSession r1 = r6.getSession()
            java.util.Map r1 = r5.getAndFillContext(r1)
            if (r0 != 0) goto L_0x0045
            java.util.Set r2 = r1.entrySet()
            java.util.Iterator r2 = r2.iterator()
        L_0x0029:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x0045
            java.lang.Object r3 = r2.next()
            java.util.Map$Entry r3 = (java.util.Map.Entry) r3
            java.lang.Object r4 = r3.getKey()
            java.lang.String r4 = (java.lang.String) r4
            java.lang.Object r3 = r3.getValue()
            java.lang.String r3 = (java.lang.String) r3
            org.slf4j.MDC.put(r4, r3)
            goto L_0x0029
        L_0x0045:
            r6.fire()     // Catch:{ all -> 0x0072 }
            if (r0 != 0) goto L_0x0068
            java.util.Set r6 = r1.keySet()
            java.util.Iterator r6 = r6.iterator()
        L_0x0052:
            boolean r0 = r6.hasNext()
            if (r0 == 0) goto L_0x0062
            java.lang.Object r0 = r6.next()
            java.lang.String r0 = (java.lang.String) r0
            org.slf4j.MDC.remove(r0)
            goto L_0x0052
        L_0x0062:
            java.lang.ThreadLocal<java.lang.Integer> r6 = r5.callDepth
            r6.remove()
            goto L_0x0071
        L_0x0068:
            java.lang.ThreadLocal<java.lang.Integer> r6 = r5.callDepth
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r6.set(r0)
        L_0x0071:
            return
        L_0x0072:
            r6 = move-exception
            if (r0 != 0) goto L_0x0093
            java.util.Set r0 = r1.keySet()
            java.util.Iterator r0 = r0.iterator()
        L_0x007d:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x008d
            java.lang.Object r1 = r0.next()
            java.lang.String r1 = (java.lang.String) r1
            org.slf4j.MDC.remove(r1)
            goto L_0x007d
        L_0x008d:
            java.lang.ThreadLocal<java.lang.Integer> r0 = r5.callDepth
            r0.remove()
            goto L_0x009c
        L_0x0093:
            java.lang.ThreadLocal<java.lang.Integer> r1 = r5.callDepth
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r1.set(r0)
        L_0x009c:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.mina.filter.logging.MdcInjectionFilter.filter(org.apache.mina.core.filterchain.IoFilterEvent):void");
    }

    private Map<String, String> getAndFillContext(IoSession ioSession) {
        Map<String, String> context = getContext(ioSession);
        if (context.isEmpty()) {
            fillContext(ioSession, context);
        }
        return context;
    }

    private static Map<String, String> getContext(IoSession ioSession) {
        Map<String, String> map = (Map) ioSession.getAttribute(CONTEXT_KEY);
        if (map != null) {
            return map;
        }
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        ioSession.setAttribute(CONTEXT_KEY, concurrentHashMap);
        return concurrentHashMap;
    }

    /* access modifiers changed from: protected */
    public void fillContext(IoSession ioSession, Map<String, String> map) {
        if (this.mdcKeys.contains(MdcKey.handlerClass)) {
            map.put(MdcKey.handlerClass.name(), ioSession.getHandler().getClass().getName());
        }
        if (this.mdcKeys.contains(MdcKey.remoteAddress)) {
            map.put(MdcKey.remoteAddress.name(), ioSession.getRemoteAddress().toString());
        }
        if (this.mdcKeys.contains(MdcKey.localAddress)) {
            map.put(MdcKey.localAddress.name(), ioSession.getLocalAddress().toString());
        }
        if (ioSession.getTransportMetadata().getAddressType() == InetSocketAddress.class) {
            InetSocketAddress inetSocketAddress = (InetSocketAddress) ioSession.getRemoteAddress();
            InetSocketAddress inetSocketAddress2 = (InetSocketAddress) ioSession.getLocalAddress();
            if (this.mdcKeys.contains(MdcKey.remoteIp)) {
                map.put(MdcKey.remoteIp.name(), inetSocketAddress.getAddress().getHostAddress());
            }
            if (this.mdcKeys.contains(MdcKey.remotePort)) {
                map.put(MdcKey.remotePort.name(), String.valueOf(inetSocketAddress.getPort()));
            }
            if (this.mdcKeys.contains(MdcKey.localIp)) {
                map.put(MdcKey.localIp.name(), inetSocketAddress2.getAddress().getHostAddress());
            }
            if (this.mdcKeys.contains(MdcKey.localPort)) {
                map.put(MdcKey.localPort.name(), String.valueOf(inetSocketAddress2.getPort()));
            }
        }
    }

    public static String getProperty(IoSession ioSession, String str) {
        if (str != null) {
            String str2 = getContext(ioSession).get(str);
            if (str2 != null) {
                return str2;
            }
            return MDC.get(str);
        }
        throw new IllegalArgumentException("key should not be null");
    }

    public static void setProperty(IoSession ioSession, String str, String str2) {
        if (str != null) {
            if (str2 == null) {
                removeProperty(ioSession, str);
            }
            getContext(ioSession).put(str, str2);
            MDC.put(str, str2);
            return;
        }
        throw new IllegalArgumentException("key should not be null");
    }

    public static void removeProperty(IoSession ioSession, String str) {
        if (str != null) {
            getContext(ioSession).remove(str);
            MDC.remove(str);
            return;
        }
        throw new IllegalArgumentException("key should not be null");
    }
}
