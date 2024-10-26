package org.apache.mina.core.filterchain;

import com.limpoxe.support.servicemanager.ServiceProvider;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.log4j.spi.Configurator;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.filterchain.IoFilter;
import org.apache.mina.core.filterchain.IoFilterChain;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.AbstractIoSession;
import org.apache.mina.core.session.AttributeKey;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.core.write.WriteRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultIoFilterChain implements IoFilterChain {
    private static final Logger LOGGER;
    public static final AttributeKey SESSION_CREATED_FUTURE;
    private final EntryImpl head;
    private final Map<String, IoFilterChain.Entry> name2entry = new ConcurrentHashMap();
    private final AbstractIoSession session;
    private final EntryImpl tail;

    static {
        Class<DefaultIoFilterChain> cls = DefaultIoFilterChain.class;
        SESSION_CREATED_FUTURE = new AttributeKey(cls, "connectFuture");
        LOGGER = LoggerFactory.getLogger((Class) cls);
    }

    public DefaultIoFilterChain(AbstractIoSession abstractIoSession) {
        if (abstractIoSession != null) {
            this.session = abstractIoSession;
            EntryImpl entryImpl = new EntryImpl((EntryImpl) null, (EntryImpl) null, "head", new HeadFilter());
            this.head = entryImpl;
            EntryImpl entryImpl2 = new EntryImpl(entryImpl, (EntryImpl) null, "tail", new TailFilter());
            this.tail = entryImpl2;
            EntryImpl unused = this.head.nextEntry = entryImpl2;
            return;
        }
        throw new IllegalArgumentException("session");
    }

    public IoSession getSession() {
        return this.session;
    }

    public IoFilterChain.Entry getEntry(String str) {
        IoFilterChain.Entry entry = this.name2entry.get(str);
        if (entry == null) {
            return null;
        }
        return entry;
    }

    public IoFilterChain.Entry getEntry(IoFilter ioFilter) {
        for (EntryImpl access$300 = this.head.nextEntry; access$300 != this.tail; access$300 = access$300.nextEntry) {
            if (access$300.getFilter() == ioFilter) {
                return access$300;
            }
        }
        return null;
    }

    public IoFilterChain.Entry getEntry(Class<? extends IoFilter> cls) {
        for (EntryImpl access$300 = this.head.nextEntry; access$300 != this.tail; access$300 = access$300.nextEntry) {
            if (cls.isAssignableFrom(access$300.getFilter().getClass())) {
                return access$300;
            }
        }
        return null;
    }

    public IoFilter get(String str) {
        IoFilterChain.Entry entry = getEntry(str);
        if (entry == null) {
            return null;
        }
        return entry.getFilter();
    }

    public IoFilter get(Class<? extends IoFilter> cls) {
        IoFilterChain.Entry entry = getEntry(cls);
        if (entry == null) {
            return null;
        }
        return entry.getFilter();
    }

    public IoFilter.NextFilter getNextFilter(String str) {
        IoFilterChain.Entry entry = getEntry(str);
        if (entry == null) {
            return null;
        }
        return entry.getNextFilter();
    }

    public IoFilter.NextFilter getNextFilter(IoFilter ioFilter) {
        IoFilterChain.Entry entry = getEntry(ioFilter);
        if (entry == null) {
            return null;
        }
        return entry.getNextFilter();
    }

    public IoFilter.NextFilter getNextFilter(Class<? extends IoFilter> cls) {
        IoFilterChain.Entry entry = getEntry(cls);
        if (entry == null) {
            return null;
        }
        return entry.getNextFilter();
    }

    public synchronized void addFirst(String str, IoFilter ioFilter) {
        checkAddable(str);
        register(this.head, str, ioFilter);
    }

    public synchronized void addLast(String str, IoFilter ioFilter) {
        checkAddable(str);
        register(this.tail.prevEntry, str, ioFilter);
    }

    public synchronized void addBefore(String str, String str2, IoFilter ioFilter) {
        EntryImpl checkOldName = checkOldName(str);
        checkAddable(str2);
        register(checkOldName.prevEntry, str2, ioFilter);
    }

    public synchronized void addAfter(String str, String str2, IoFilter ioFilter) {
        EntryImpl checkOldName = checkOldName(str);
        checkAddable(str2);
        register(checkOldName, str2, ioFilter);
    }

    public synchronized IoFilter remove(String str) {
        EntryImpl checkOldName;
        checkOldName = checkOldName(str);
        deregister(checkOldName);
        return checkOldName.getFilter();
    }

    public synchronized void remove(IoFilter ioFilter) {
        EntryImpl access$300 = this.head.nextEntry;
        while (access$300 != this.tail) {
            if (access$300.getFilter() == ioFilter) {
                deregister(access$300);
            } else {
                access$300 = access$300.nextEntry;
            }
        }
        throw new IllegalArgumentException("Filter not found: " + ioFilter.getClass().getName());
    }

    public synchronized IoFilter remove(Class<? extends IoFilter> cls) {
        IoFilter filter;
        EntryImpl access$300 = this.head.nextEntry;
        while (access$300 != this.tail) {
            if (cls.isAssignableFrom(access$300.getFilter().getClass())) {
                filter = access$300.getFilter();
                deregister(access$300);
            } else {
                access$300 = access$300.nextEntry;
            }
        }
        throw new IllegalArgumentException("Filter not found: " + cls.getName());
        return filter;
    }

    public synchronized IoFilter replace(String str, IoFilter ioFilter) {
        IoFilter filter;
        EntryImpl checkOldName = checkOldName(str);
        filter = checkOldName.getFilter();
        checkOldName.setFilter(ioFilter);
        return filter;
    }

    public synchronized void replace(IoFilter ioFilter, IoFilter ioFilter2) {
        EntryImpl access$300 = this.head.nextEntry;
        while (access$300 != this.tail) {
            if (access$300.getFilter() == ioFilter) {
                access$300.setFilter(ioFilter2);
            } else {
                access$300 = access$300.nextEntry;
            }
        }
        throw new IllegalArgumentException("Filter not found: " + ioFilter.getClass().getName());
    }

    public synchronized IoFilter replace(Class<? extends IoFilter> cls, IoFilter ioFilter) {
        IoFilter filter;
        EntryImpl access$300 = this.head.nextEntry;
        while (access$300 != this.tail) {
            if (cls.isAssignableFrom(access$300.getFilter().getClass())) {
                filter = access$300.getFilter();
                access$300.setFilter(ioFilter);
            } else {
                access$300 = access$300.nextEntry;
            }
        }
        throw new IllegalArgumentException("Filter not found: " + cls.getName());
        return filter;
    }

    public synchronized void clear() throws Exception {
        for (IoFilterChain.Entry entry : new ArrayList(this.name2entry.values())) {
            try {
                deregister((EntryImpl) entry);
            } catch (Exception e) {
                throw new IoFilterLifeCycleException("clear(): " + entry.getName() + " in " + getSession(), e);
            }
        }
    }

    private void register(EntryImpl entryImpl, String str, IoFilter ioFilter) {
        EntryImpl entryImpl2 = new EntryImpl(entryImpl, entryImpl.nextEntry, str, ioFilter);
        try {
            ioFilter.onPreAdd(this, str, entryImpl2.getNextFilter());
            EntryImpl unused = entryImpl.nextEntry.prevEntry = entryImpl2;
            EntryImpl unused2 = entryImpl.nextEntry = entryImpl2;
            this.name2entry.put(str, entryImpl2);
            try {
                ioFilter.onPostAdd(this, str, entryImpl2.getNextFilter());
            } catch (Exception e) {
                deregister0(entryImpl2);
                throw new IoFilterLifeCycleException("onPostAdd(): " + str + ':' + ioFilter + " in " + getSession(), e);
            }
        } catch (Exception e2) {
            throw new IoFilterLifeCycleException("onPreAdd(): " + str + ':' + ioFilter + " in " + getSession(), e2);
        }
    }

    private void deregister(EntryImpl entryImpl) {
        IoFilter filter = entryImpl.getFilter();
        try {
            filter.onPreRemove(this, entryImpl.getName(), entryImpl.getNextFilter());
            deregister0(entryImpl);
            try {
                filter.onPostRemove(this, entryImpl.getName(), entryImpl.getNextFilter());
            } catch (Exception e) {
                throw new IoFilterLifeCycleException("onPostRemove(): " + entryImpl.getName() + ':' + filter + " in " + getSession(), e);
            }
        } catch (Exception e2) {
            throw new IoFilterLifeCycleException("onPreRemove(): " + entryImpl.getName() + ':' + filter + " in " + getSession(), e2);
        }
    }

    private void deregister0(EntryImpl entryImpl) {
        EntryImpl access$400 = entryImpl.prevEntry;
        EntryImpl access$300 = entryImpl.nextEntry;
        EntryImpl unused = access$400.nextEntry = access$300;
        EntryImpl unused2 = access$300.prevEntry = access$400;
        this.name2entry.remove(entryImpl.name);
    }

    private EntryImpl checkOldName(String str) {
        EntryImpl entryImpl = (EntryImpl) this.name2entry.get(str);
        if (entryImpl != null) {
            return entryImpl;
        }
        throw new IllegalArgumentException("Filter not found:" + str);
    }

    private void checkAddable(String str) {
        if (this.name2entry.containsKey(str)) {
            throw new IllegalArgumentException("Other filter is using the same name '" + str + "'");
        }
    }

    public void fireSessionCreated() {
        callNextSessionCreated(this.head, this.session);
    }

    /* access modifiers changed from: private */
    public void callNextSessionCreated(IoFilterChain.Entry entry, IoSession ioSession) {
        try {
            entry.getFilter().sessionCreated(entry.getNextFilter(), ioSession);
        } catch (Throwable th) {
            fireExceptionCaught(th);
        }
    }

    public void fireSessionOpened() {
        callNextSessionOpened(this.head, this.session);
    }

    /* access modifiers changed from: private */
    public void callNextSessionOpened(IoFilterChain.Entry entry, IoSession ioSession) {
        try {
            entry.getFilter().sessionOpened(entry.getNextFilter(), ioSession);
        } catch (Throwable th) {
            fireExceptionCaught(th);
        }
    }

    public void fireSessionClosed() {
        try {
            this.session.getCloseFuture().setClosed();
        } catch (Throwable th) {
            fireExceptionCaught(th);
        }
        callNextSessionClosed(this.head, this.session);
    }

    /* access modifiers changed from: private */
    public void callNextSessionClosed(IoFilterChain.Entry entry, IoSession ioSession) {
        try {
            entry.getFilter().sessionClosed(entry.getNextFilter(), ioSession);
        } catch (Throwable th) {
            fireExceptionCaught(th);
        }
    }

    public void fireSessionIdle(IdleStatus idleStatus) {
        this.session.increaseIdleCount(idleStatus, System.currentTimeMillis());
        callNextSessionIdle(this.head, this.session, idleStatus);
    }

    /* access modifiers changed from: private */
    public void callNextSessionIdle(IoFilterChain.Entry entry, IoSession ioSession, IdleStatus idleStatus) {
        try {
            entry.getFilter().sessionIdle(entry.getNextFilter(), ioSession, idleStatus);
        } catch (Throwable th) {
            fireExceptionCaught(th);
        }
    }

    public void fireMessageReceived(Object obj) {
        if (obj instanceof IoBuffer) {
            this.session.increaseReadBytes((long) ((IoBuffer) obj).remaining(), System.currentTimeMillis());
        }
        callNextMessageReceived(this.head, this.session, obj);
    }

    /* access modifiers changed from: private */
    public void callNextMessageReceived(IoFilterChain.Entry entry, IoSession ioSession, Object obj) {
        try {
            entry.getFilter().messageReceived(entry.getNextFilter(), ioSession, obj);
        } catch (Throwable th) {
            fireExceptionCaught(th);
        }
    }

    public void fireMessageSent(WriteRequest writeRequest) {
        this.session.increaseWrittenMessages(writeRequest, System.currentTimeMillis());
        try {
            writeRequest.getFuture().setWritten();
        } catch (Throwable th) {
            fireExceptionCaught(th);
        }
        EntryImpl entryImpl = this.head;
        if (!writeRequest.isEncoded()) {
            callNextMessageSent(entryImpl, this.session, writeRequest);
        }
    }

    /* access modifiers changed from: private */
    public void callNextMessageSent(IoFilterChain.Entry entry, IoSession ioSession, WriteRequest writeRequest) {
        try {
            entry.getFilter().messageSent(entry.getNextFilter(), ioSession, writeRequest);
        } catch (Throwable th) {
            fireExceptionCaught(th);
        }
    }

    public void fireExceptionCaught(Throwable th) {
        callNextExceptionCaught(this.head, this.session, th);
    }

    /* access modifiers changed from: private */
    public void callNextExceptionCaught(IoFilterChain.Entry entry, IoSession ioSession, Throwable th) {
        ConnectFuture connectFuture = (ConnectFuture) ioSession.removeAttribute(SESSION_CREATED_FUTURE);
        if (connectFuture == null) {
            try {
                entry.getFilter().exceptionCaught(entry.getNextFilter(), ioSession, th);
            } catch (Throwable th2) {
                LOGGER.warn("Unexpected exception from exceptionCaught handler.", th2);
            }
        } else {
            ioSession.close(true);
            connectFuture.setException(th);
        }
    }

    public void fireFilterWrite(WriteRequest writeRequest) {
        callPreviousFilterWrite(this.tail, this.session, writeRequest);
    }

    /* access modifiers changed from: private */
    public void callPreviousFilterWrite(IoFilterChain.Entry entry, IoSession ioSession, WriteRequest writeRequest) {
        try {
            entry.getFilter().filterWrite(entry.getNextFilter(), ioSession, writeRequest);
        } catch (Throwable th) {
            writeRequest.getFuture().setException(th);
            fireExceptionCaught(th);
        }
    }

    public void fireFilterClose() {
        callPreviousFilterClose(this.tail, this.session);
    }

    /* access modifiers changed from: private */
    public void callPreviousFilterClose(IoFilterChain.Entry entry, IoSession ioSession) {
        try {
            entry.getFilter().filterClose(entry.getNextFilter(), ioSession);
        } catch (Throwable th) {
            fireExceptionCaught(th);
        }
    }

    public List<IoFilterChain.Entry> getAll() {
        ArrayList arrayList = new ArrayList();
        for (EntryImpl access$300 = this.head.nextEntry; access$300 != this.tail; access$300 = access$300.nextEntry) {
            arrayList.add(access$300);
        }
        return arrayList;
    }

    public List<IoFilterChain.Entry> getAllReversed() {
        ArrayList arrayList = new ArrayList();
        for (EntryImpl access$400 = this.tail.prevEntry; access$400 != this.head; access$400 = access$400.prevEntry) {
            arrayList.add(access$400);
        }
        return arrayList;
    }

    public boolean contains(String str) {
        return getEntry(str) != null;
    }

    public boolean contains(IoFilter ioFilter) {
        return getEntry(ioFilter) != null;
    }

    public boolean contains(Class<? extends IoFilter> cls) {
        return getEntry(cls) != null;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{ ");
        boolean z = true;
        for (EntryImpl access$300 = this.head.nextEntry; access$300 != this.tail; access$300 = access$300.nextEntry) {
            if (!z) {
                sb.append(", ");
            } else {
                z = false;
            }
            sb.append('(');
            sb.append(access$300.getName());
            sb.append(':');
            sb.append(access$300.getFilter());
            sb.append(')');
        }
        if (z) {
            sb.append("empty");
        }
        sb.append(" }");
        return sb.toString();
    }

    private class HeadFilter extends IoFilterAdapter {
        private HeadFilter() {
        }

        public void filterWrite(IoFilter.NextFilter nextFilter, IoSession ioSession, WriteRequest writeRequest) throws Exception {
            AbstractIoSession abstractIoSession = (AbstractIoSession) ioSession;
            if (writeRequest.getMessage() instanceof IoBuffer) {
                IoBuffer ioBuffer = (IoBuffer) writeRequest.getMessage();
                ioBuffer.mark();
                int remaining = ioBuffer.remaining();
                if (remaining == 0) {
                    abstractIoSession.increaseScheduledWriteMessages();
                } else {
                    abstractIoSession.increaseScheduledWriteBytes(remaining);
                }
            } else {
                abstractIoSession.increaseScheduledWriteMessages();
            }
            abstractIoSession.getWriteRequestQueue().offer(abstractIoSession, writeRequest);
            if (!abstractIoSession.isWriteSuspended()) {
                abstractIoSession.getProcessor().flush(abstractIoSession);
            }
        }

        public void filterClose(IoFilter.NextFilter nextFilter, IoSession ioSession) throws Exception {
            AbstractIoSession abstractIoSession = (AbstractIoSession) ioSession;
            abstractIoSession.getProcessor().remove(abstractIoSession);
        }
    }

    private static class TailFilter extends IoFilterAdapter {
        private TailFilter() {
        }

        public void sessionCreated(IoFilter.NextFilter nextFilter, IoSession ioSession) throws Exception {
            try {
                ioSession.getHandler().sessionCreated(ioSession);
            } finally {
                ConnectFuture connectFuture = (ConnectFuture) ioSession.removeAttribute(DefaultIoFilterChain.SESSION_CREATED_FUTURE);
                if (connectFuture != null) {
                    connectFuture.setSession(ioSession);
                }
            }
        }

        public void sessionOpened(IoFilter.NextFilter nextFilter, IoSession ioSession) throws Exception {
            ioSession.getHandler().sessionOpened(ioSession);
        }

        public void sessionClosed(IoFilter.NextFilter nextFilter, IoSession ioSession) throws Exception {
            AbstractIoSession abstractIoSession = (AbstractIoSession) ioSession;
            try {
                abstractIoSession.getHandler().sessionClosed(ioSession);
                try {
                    abstractIoSession.getWriteRequestQueue().dispose(ioSession);
                    try {
                        abstractIoSession.getAttributeMap().dispose(ioSession);
                        try {
                            ioSession.getFilterChain().clear();
                        } finally {
                            if (abstractIoSession.getConfig().isUseReadOperation()) {
                                abstractIoSession.offerClosedReadFuture();
                            }
                        }
                    } catch (Throwable th) {
                        if (abstractIoSession.getConfig().isUseReadOperation()) {
                            abstractIoSession.offerClosedReadFuture();
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    if (abstractIoSession.getConfig().isUseReadOperation()) {
                        abstractIoSession.offerClosedReadFuture();
                    }
                    throw th2;
                }
            } catch (Throwable th3) {
                if (abstractIoSession.getConfig().isUseReadOperation()) {
                    abstractIoSession.offerClosedReadFuture();
                }
                throw th3;
            }
        }

        public void sessionIdle(IoFilter.NextFilter nextFilter, IoSession ioSession, IdleStatus idleStatus) throws Exception {
            ioSession.getHandler().sessionIdle(ioSession, idleStatus);
        }

        public void exceptionCaught(IoFilter.NextFilter nextFilter, IoSession ioSession, Throwable th) throws Exception {
            AbstractIoSession abstractIoSession = (AbstractIoSession) ioSession;
            try {
                abstractIoSession.getHandler().exceptionCaught(abstractIoSession, th);
            } finally {
                if (abstractIoSession.getConfig().isUseReadOperation()) {
                    abstractIoSession.offerFailedReadFuture(th);
                }
            }
        }

        public void messageReceived(IoFilter.NextFilter nextFilter, IoSession ioSession, Object obj) throws Exception {
            AbstractIoSession abstractIoSession = (AbstractIoSession) ioSession;
            if (!(obj instanceof IoBuffer)) {
                abstractIoSession.increaseReadMessages(System.currentTimeMillis());
            } else if (!((IoBuffer) obj).hasRemaining()) {
                abstractIoSession.increaseReadMessages(System.currentTimeMillis());
            }
            try {
                ioSession.getHandler().messageReceived(abstractIoSession, obj);
            } finally {
                if (abstractIoSession.getConfig().isUseReadOperation()) {
                    abstractIoSession.offerReadFuture(obj);
                }
            }
        }

        public void messageSent(IoFilter.NextFilter nextFilter, IoSession ioSession, WriteRequest writeRequest) throws Exception {
            ioSession.getHandler().messageSent(ioSession, writeRequest.getMessage());
        }

        public void filterWrite(IoFilter.NextFilter nextFilter, IoSession ioSession, WriteRequest writeRequest) throws Exception {
            nextFilter.filterWrite(ioSession, writeRequest);
        }

        public void filterClose(IoFilter.NextFilter nextFilter, IoSession ioSession) throws Exception {
            nextFilter.filterClose(ioSession);
        }
    }

    private class EntryImpl implements IoFilterChain.Entry {
        private IoFilter filter;
        /* access modifiers changed from: private */
        public final String name;
        /* access modifiers changed from: private */
        public EntryImpl nextEntry;
        private final IoFilter.NextFilter nextFilter;
        /* access modifiers changed from: private */
        public EntryImpl prevEntry;

        private EntryImpl(EntryImpl entryImpl, EntryImpl entryImpl2, String str, IoFilter ioFilter) {
            if (ioFilter == null) {
                throw new IllegalArgumentException("filter");
            } else if (str != null) {
                this.prevEntry = entryImpl;
                this.nextEntry = entryImpl2;
                this.name = str;
                this.filter = ioFilter;
                this.nextFilter = new IoFilter.NextFilter(DefaultIoFilterChain.this) {
                    public void sessionCreated(IoSession ioSession) {
                        DefaultIoFilterChain.this.callNextSessionCreated(EntryImpl.this.nextEntry, ioSession);
                    }

                    public void sessionOpened(IoSession ioSession) {
                        DefaultIoFilterChain.this.callNextSessionOpened(EntryImpl.this.nextEntry, ioSession);
                    }

                    public void sessionClosed(IoSession ioSession) {
                        DefaultIoFilterChain.this.callNextSessionClosed(EntryImpl.this.nextEntry, ioSession);
                    }

                    public void sessionIdle(IoSession ioSession, IdleStatus idleStatus) {
                        DefaultIoFilterChain.this.callNextSessionIdle(EntryImpl.this.nextEntry, ioSession, idleStatus);
                    }

                    public void exceptionCaught(IoSession ioSession, Throwable th) {
                        DefaultIoFilterChain.this.callNextExceptionCaught(EntryImpl.this.nextEntry, ioSession, th);
                    }

                    public void messageReceived(IoSession ioSession, Object obj) {
                        DefaultIoFilterChain.this.callNextMessageReceived(EntryImpl.this.nextEntry, ioSession, obj);
                    }

                    public void messageSent(IoSession ioSession, WriteRequest writeRequest) {
                        DefaultIoFilterChain.this.callNextMessageSent(EntryImpl.this.nextEntry, ioSession, writeRequest);
                    }

                    public void filterWrite(IoSession ioSession, WriteRequest writeRequest) {
                        DefaultIoFilterChain.this.callPreviousFilterWrite(EntryImpl.this.prevEntry, ioSession, writeRequest);
                    }

                    public void filterClose(IoSession ioSession) {
                        DefaultIoFilterChain.this.callPreviousFilterClose(EntryImpl.this.prevEntry, ioSession);
                    }

                    public String toString() {
                        return EntryImpl.this.nextEntry.name;
                    }
                };
            } else {
                throw new IllegalArgumentException(ServiceProvider.NAME);
            }
        }

        public String getName() {
            return this.name;
        }

        public IoFilter getFilter() {
            return this.filter;
        }

        /* access modifiers changed from: private */
        public void setFilter(IoFilter ioFilter) {
            if (ioFilter != null) {
                this.filter = ioFilter;
                return;
            }
            throw new IllegalArgumentException("filter");
        }

        public IoFilter.NextFilter getNextFilter() {
            return this.nextFilter;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("('");
            sb.append(getName());
            sb.append('\'');
            sb.append(", prev: '");
            EntryImpl entryImpl = this.prevEntry;
            if (entryImpl != null) {
                sb.append(entryImpl.name);
                sb.append(':');
                sb.append(this.prevEntry.getFilter().getClass().getSimpleName());
            } else {
                sb.append(Configurator.NULL);
            }
            sb.append("', next: '");
            EntryImpl entryImpl2 = this.nextEntry;
            if (entryImpl2 != null) {
                sb.append(entryImpl2.name);
                sb.append(':');
                sb.append(this.nextEntry.getFilter().getClass().getSimpleName());
            } else {
                sb.append(Configurator.NULL);
            }
            sb.append("')");
            return sb.toString();
        }

        public void addAfter(String str, IoFilter ioFilter) {
            DefaultIoFilterChain.this.addAfter(getName(), str, ioFilter);
        }

        public void addBefore(String str, IoFilter ioFilter) {
            DefaultIoFilterChain.this.addBefore(getName(), str, ioFilter);
        }

        public void remove() {
            DefaultIoFilterChain.this.remove(getName());
        }

        public void replace(IoFilter ioFilter) {
            DefaultIoFilterChain.this.replace(getName(), ioFilter);
        }
    }
}
