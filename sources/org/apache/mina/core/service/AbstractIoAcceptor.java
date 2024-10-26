package org.apache.mina.core.service;

import java.io.IOException;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;
import org.apache.mina.core.RuntimeIoException;
import org.apache.mina.core.service.AbstractIoService;
import org.apache.mina.core.session.IoSessionConfig;

public abstract class AbstractIoAcceptor extends AbstractIoService implements IoAcceptor {
    protected final Object bindLock = new Object();
    private final Set<SocketAddress> boundAddresses = new HashSet();
    private final List<SocketAddress> defaultLocalAddresses;
    private boolean disconnectOnUnbind = true;
    private final List<SocketAddress> unmodifiableDefaultLocalAddresses;

    /* access modifiers changed from: protected */
    public abstract Set<SocketAddress> bindInternal(List<? extends SocketAddress> list) throws Exception;

    /* access modifiers changed from: protected */
    public abstract void unbind0(List<? extends SocketAddress> list) throws Exception;

    protected AbstractIoAcceptor(IoSessionConfig ioSessionConfig, Executor executor) {
        super(ioSessionConfig, executor);
        ArrayList arrayList = new ArrayList();
        this.defaultLocalAddresses = arrayList;
        this.unmodifiableDefaultLocalAddresses = Collections.unmodifiableList(arrayList);
        this.defaultLocalAddresses.add((Object) null);
    }

    public SocketAddress getLocalAddress() {
        Set<SocketAddress> localAddresses = getLocalAddresses();
        if (localAddresses.isEmpty()) {
            return null;
        }
        return localAddresses.iterator().next();
    }

    public final Set<SocketAddress> getLocalAddresses() {
        HashSet hashSet = new HashSet();
        synchronized (this.boundAddresses) {
            hashSet.addAll(this.boundAddresses);
        }
        return hashSet;
    }

    public SocketAddress getDefaultLocalAddress() {
        if (this.defaultLocalAddresses.isEmpty()) {
            return null;
        }
        return this.defaultLocalAddresses.iterator().next();
    }

    public final void setDefaultLocalAddress(SocketAddress socketAddress) {
        setDefaultLocalAddresses(socketAddress, new SocketAddress[0]);
    }

    public final List<SocketAddress> getDefaultLocalAddresses() {
        return this.unmodifiableDefaultLocalAddresses;
    }

    public final void setDefaultLocalAddresses(List<? extends SocketAddress> list) {
        if (list != null) {
            setDefaultLocalAddresses((Iterable<? extends SocketAddress>) list);
            return;
        }
        throw new IllegalArgumentException("localAddresses");
    }

    public final void setDefaultLocalAddresses(Iterable<? extends SocketAddress> iterable) {
        if (iterable != null) {
            synchronized (this.bindLock) {
                synchronized (this.boundAddresses) {
                    if (this.boundAddresses.isEmpty()) {
                        ArrayList arrayList = new ArrayList();
                        for (SocketAddress socketAddress : iterable) {
                            checkAddressType(socketAddress);
                            arrayList.add(socketAddress);
                        }
                        if (!arrayList.isEmpty()) {
                            this.defaultLocalAddresses.clear();
                            this.defaultLocalAddresses.addAll(arrayList);
                        } else {
                            throw new IllegalArgumentException("empty localAddresses");
                        }
                    } else {
                        throw new IllegalStateException("localAddress can't be set while the acceptor is bound.");
                    }
                }
            }
            return;
        }
        throw new IllegalArgumentException("localAddresses");
    }

    public final void setDefaultLocalAddresses(SocketAddress socketAddress, SocketAddress... socketAddressArr) {
        if (socketAddressArr == null) {
            socketAddressArr = new SocketAddress[0];
        }
        ArrayList arrayList = new ArrayList(socketAddressArr.length + 1);
        arrayList.add(socketAddress);
        for (SocketAddress add : socketAddressArr) {
            arrayList.add(add);
        }
        setDefaultLocalAddresses((Iterable<? extends SocketAddress>) arrayList);
    }

    public final boolean isCloseOnDeactivation() {
        return this.disconnectOnUnbind;
    }

    public final void setCloseOnDeactivation(boolean z) {
        this.disconnectOnUnbind = z;
    }

    public final void bind() throws IOException {
        bind((Iterable<? extends SocketAddress>) getDefaultLocalAddresses());
    }

    public final void bind(SocketAddress socketAddress) throws IOException {
        if (socketAddress != null) {
            ArrayList arrayList = new ArrayList(1);
            arrayList.add(socketAddress);
            bind((Iterable<? extends SocketAddress>) arrayList);
            return;
        }
        throw new IllegalArgumentException("localAddress");
    }

    public final void bind(SocketAddress socketAddress, SocketAddress... socketAddressArr) throws IOException {
        if (socketAddress == null) {
            bind((Iterable<? extends SocketAddress>) getDefaultLocalAddresses());
            return;
        }
        ArrayList arrayList = new ArrayList(2);
        arrayList.add(socketAddress);
        if (socketAddressArr != null) {
            for (SocketAddress add : socketAddressArr) {
                arrayList.add(add);
            }
        }
        bind((Iterable<? extends SocketAddress>) arrayList);
    }

    public final void bind(Iterable<? extends SocketAddress> iterable) throws IOException {
        boolean isEmpty;
        if (isDisposing()) {
            throw new IllegalStateException("Already disposed.");
        } else if (iterable != null) {
            ArrayList arrayList = new ArrayList();
            for (SocketAddress socketAddress : iterable) {
                checkAddressType(socketAddress);
                arrayList.add(socketAddress);
            }
            if (!arrayList.isEmpty()) {
                synchronized (this.bindLock) {
                    synchronized (this.boundAddresses) {
                        isEmpty = this.boundAddresses.isEmpty();
                    }
                    if (getHandler() != null) {
                        try {
                            Set<SocketAddress> bindInternal = bindInternal(arrayList);
                            synchronized (this.boundAddresses) {
                                this.boundAddresses.addAll(bindInternal);
                            }
                        } catch (IOException e) {
                            throw e;
                        } catch (RuntimeException e2) {
                            throw e2;
                        } catch (Throwable th) {
                            throw new RuntimeIoException("Failed to bind to: " + getLocalAddresses(), th);
                        }
                    } else {
                        throw new IllegalStateException("handler is not set.");
                    }
                }
                if (isEmpty) {
                    getListeners().fireServiceActivated();
                    return;
                }
                return;
            }
            throw new IllegalArgumentException("localAddresses is empty.");
        } else {
            throw new IllegalArgumentException("localAddresses");
        }
    }

    public final void unbind() {
        unbind((Iterable<? extends SocketAddress>) getLocalAddresses());
    }

    public final void unbind(SocketAddress socketAddress) {
        if (socketAddress != null) {
            ArrayList arrayList = new ArrayList(1);
            arrayList.add(socketAddress);
            unbind((Iterable<? extends SocketAddress>) arrayList);
            return;
        }
        throw new IllegalArgumentException("localAddress");
    }

    public final void unbind(SocketAddress socketAddress, SocketAddress... socketAddressArr) {
        if (socketAddress == null) {
            throw new IllegalArgumentException("firstLocalAddress");
        } else if (socketAddressArr != null) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(socketAddress);
            Collections.addAll(arrayList, socketAddressArr);
            unbind((Iterable<? extends SocketAddress>) arrayList);
        } else {
            throw new IllegalArgumentException("otherLocalAddresses");
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0074, code lost:
        if (r3 == false) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0076, code lost:
        getListeners().fireServiceDeactivated();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void unbind(java.lang.Iterable<? extends java.net.SocketAddress> r8) {
        /*
            r7 = this;
            if (r8 == 0) goto L_0x008c
            java.lang.Object r0 = r7.bindLock
            monitor-enter(r0)
            java.util.Set<java.net.SocketAddress> r1 = r7.boundAddresses     // Catch:{ all -> 0x0089 }
            monitor-enter(r1)     // Catch:{ all -> 0x0089 }
            java.util.Set<java.net.SocketAddress> r2 = r7.boundAddresses     // Catch:{ all -> 0x0086 }
            boolean r2 = r2.isEmpty()     // Catch:{ all -> 0x0086 }
            if (r2 == 0) goto L_0x0013
            monitor-exit(r1)     // Catch:{ all -> 0x0086 }
            monitor-exit(r0)     // Catch:{ all -> 0x0089 }
            return
        L_0x0013:
            java.util.ArrayList r2 = new java.util.ArrayList     // Catch:{ all -> 0x0086 }
            r2.<init>()     // Catch:{ all -> 0x0086 }
            java.util.Iterator r8 = r8.iterator()     // Catch:{ all -> 0x0086 }
            r3 = 0
            r4 = 0
        L_0x001e:
            boolean r5 = r8.hasNext()     // Catch:{ all -> 0x0086 }
            if (r5 == 0) goto L_0x003a
            java.lang.Object r5 = r8.next()     // Catch:{ all -> 0x0086 }
            java.net.SocketAddress r5 = (java.net.SocketAddress) r5     // Catch:{ all -> 0x0086 }
            int r4 = r4 + 1
            if (r5 == 0) goto L_0x001e
            java.util.Set<java.net.SocketAddress> r6 = r7.boundAddresses     // Catch:{ all -> 0x0086 }
            boolean r6 = r6.contains(r5)     // Catch:{ all -> 0x0086 }
            if (r6 == 0) goto L_0x001e
            r2.add(r5)     // Catch:{ all -> 0x0086 }
            goto L_0x001e
        L_0x003a:
            if (r4 == 0) goto L_0x007e
            boolean r8 = r2.isEmpty()     // Catch:{ all -> 0x0086 }
            if (r8 != 0) goto L_0x0072
            r7.unbind0(r2)     // Catch:{ RuntimeException -> 0x0070, all -> 0x0054 }
            java.util.Set<java.net.SocketAddress> r8 = r7.boundAddresses     // Catch:{ all -> 0x0086 }
            r8.removeAll(r2)     // Catch:{ all -> 0x0086 }
            java.util.Set<java.net.SocketAddress> r8 = r7.boundAddresses     // Catch:{ all -> 0x0086 }
            boolean r8 = r8.isEmpty()     // Catch:{ all -> 0x0086 }
            if (r8 == 0) goto L_0x0072
            r3 = 1
            goto L_0x0072
        L_0x0054:
            r8 = move-exception
            org.apache.mina.core.RuntimeIoException r2 = new org.apache.mina.core.RuntimeIoException     // Catch:{ all -> 0x0086 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0086 }
            r3.<init>()     // Catch:{ all -> 0x0086 }
            java.lang.String r4 = "Failed to unbind from: "
            r3.append(r4)     // Catch:{ all -> 0x0086 }
            java.util.Set r4 = r7.getLocalAddresses()     // Catch:{ all -> 0x0086 }
            r3.append(r4)     // Catch:{ all -> 0x0086 }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x0086 }
            r2.<init>(r3, r8)     // Catch:{ all -> 0x0086 }
            throw r2     // Catch:{ all -> 0x0086 }
        L_0x0070:
            r8 = move-exception
            throw r8     // Catch:{ all -> 0x0086 }
        L_0x0072:
            monitor-exit(r1)     // Catch:{ all -> 0x0086 }
            monitor-exit(r0)     // Catch:{ all -> 0x0089 }
            if (r3 == 0) goto L_0x007d
            org.apache.mina.core.service.IoServiceListenerSupport r8 = r7.getListeners()
            r8.fireServiceDeactivated()
        L_0x007d:
            return
        L_0x007e:
            java.lang.IllegalArgumentException r8 = new java.lang.IllegalArgumentException     // Catch:{ all -> 0x0086 }
            java.lang.String r2 = "localAddresses is empty."
            r8.<init>(r2)     // Catch:{ all -> 0x0086 }
            throw r8     // Catch:{ all -> 0x0086 }
        L_0x0086:
            r8 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0086 }
            throw r8     // Catch:{ all -> 0x0089 }
        L_0x0089:
            r8 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0089 }
            throw r8
        L_0x008c:
            java.lang.IllegalArgumentException r8 = new java.lang.IllegalArgumentException
            java.lang.String r0 = "localAddresses"
            r8.<init>(r0)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.mina.core.service.AbstractIoAcceptor.unbind(java.lang.Iterable):void");
    }

    public String toString() {
        String str;
        TransportMetadata transportMetadata = getTransportMetadata();
        StringBuilder sb = new StringBuilder();
        sb.append('(');
        sb.append(transportMetadata.getProviderName());
        sb.append(' ');
        sb.append(transportMetadata.getName());
        sb.append(" acceptor: ");
        if (isActive()) {
            str = "localAddress(es): " + getLocalAddresses() + ", managedSessionCount: " + getManagedSessionCount();
        } else {
            str = "not bound";
        }
        sb.append(str);
        sb.append(')');
        return sb.toString();
    }

    private void checkAddressType(SocketAddress socketAddress) {
        if (socketAddress != null && !getTransportMetadata().getAddressType().isAssignableFrom(socketAddress.getClass())) {
            throw new IllegalArgumentException("localAddress type: " + socketAddress.getClass().getSimpleName() + " (expected: " + getTransportMetadata().getAddressType().getSimpleName() + ")");
        }
    }

    public static class AcceptorOperationFuture extends AbstractIoService.ServiceOperationFuture {
        private final List<SocketAddress> localAddresses;

        public AcceptorOperationFuture(List<? extends SocketAddress> list) {
            this.localAddresses = new ArrayList(list);
        }

        public final List<SocketAddress> getLocalAddresses() {
            return Collections.unmodifiableList(this.localAddresses);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Acceptor operation : ");
            List<SocketAddress> list = this.localAddresses;
            if (list != null) {
                boolean z = true;
                for (SocketAddress next : list) {
                    if (z) {
                        z = false;
                    } else {
                        sb.append(", ");
                    }
                    sb.append(next);
                }
            }
            return sb.toString();
        }
    }
}
