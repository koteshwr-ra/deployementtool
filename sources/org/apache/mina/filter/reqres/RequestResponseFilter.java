package org.apache.mina.filter.reqres;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import org.apache.mina.core.filterchain.IoFilter;
import org.apache.mina.core.filterchain.IoFilterChain;
import org.apache.mina.core.session.AttributeKey;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.core.write.WriteRequest;
import org.apache.mina.filter.util.WriteRequestFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestResponseFilter extends WriteRequestFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(RequestResponseFilter.class);
    private final AttributeKey REQUEST_STORE = new AttributeKey(getClass(), "requestStore");
    private final AttributeKey RESPONSE_INSPECTOR = new AttributeKey(getClass(), "responseInspector");
    private final AttributeKey UNRESPONDED_REQUEST_STORE = new AttributeKey(getClass(), "unrespondedRequestStore");
    private final ResponseInspectorFactory responseInspectorFactory;
    private final ScheduledExecutorService timeoutScheduler;

    /* access modifiers changed from: protected */
    public void destroyRequestStore(Map<Object, Request> map) {
    }

    /* access modifiers changed from: protected */
    public void destroyUnrespondedRequestStore(Set<Request> set) {
    }

    public RequestResponseFilter(final ResponseInspector responseInspector, ScheduledExecutorService scheduledExecutorService) {
        if (responseInspector == null) {
            throw new IllegalArgumentException("responseInspector");
        } else if (scheduledExecutorService != null) {
            this.responseInspectorFactory = new ResponseInspectorFactory() {
                public ResponseInspector getResponseInspector() {
                    return responseInspector;
                }
            };
            this.timeoutScheduler = scheduledExecutorService;
        } else {
            throw new IllegalArgumentException("timeoutScheduler");
        }
    }

    public RequestResponseFilter(ResponseInspectorFactory responseInspectorFactory2, ScheduledExecutorService scheduledExecutorService) {
        if (responseInspectorFactory2 == null) {
            throw new IllegalArgumentException("responseInspectorFactory");
        } else if (scheduledExecutorService != null) {
            this.responseInspectorFactory = responseInspectorFactory2;
            this.timeoutScheduler = scheduledExecutorService;
        } else {
            throw new IllegalArgumentException("timeoutScheduler");
        }
    }

    public void onPreAdd(IoFilterChain ioFilterChain, String str, IoFilter.NextFilter nextFilter) throws Exception {
        if (!ioFilterChain.contains((IoFilter) this)) {
            IoSession session = ioFilterChain.getSession();
            session.setAttribute(this.RESPONSE_INSPECTOR, this.responseInspectorFactory.getResponseInspector());
            session.setAttribute(this.REQUEST_STORE, createRequestStore(session));
            session.setAttribute(this.UNRESPONDED_REQUEST_STORE, createUnrespondedRequestStore(session));
            return;
        }
        throw new IllegalArgumentException("You can't add the same filter instance more than once.  Create another instance and add it.");
    }

    public void onPostRemove(IoFilterChain ioFilterChain, String str, IoFilter.NextFilter nextFilter) throws Exception {
        IoSession session = ioFilterChain.getSession();
        destroyUnrespondedRequestStore(getUnrespondedRequestStore(session));
        destroyRequestStore(getRequestStore(session));
        session.removeAttribute(this.UNRESPONDED_REQUEST_STORE);
        session.removeAttribute(this.REQUEST_STORE);
        session.removeAttribute(this.RESPONSE_INSPECTOR);
    }

    public void messageReceived(IoFilter.NextFilter nextFilter, IoSession ioSession, Object obj) throws Exception {
        Request request;
        ScheduledFuture<?> timeoutFuture;
        ResponseInspector responseInspector = (ResponseInspector) ioSession.getAttribute(this.RESPONSE_INSPECTOR);
        Object requestId = responseInspector.getRequestId(obj);
        if (requestId == null) {
            nextFilter.messageReceived(ioSession, obj);
            return;
        }
        ResponseType responseType = responseInspector.getResponseType(obj);
        if (responseType == null) {
            nextFilter.exceptionCaught(ioSession, new IllegalStateException(responseInspector.getClass().getName() + "#getResponseType() may not return null."));
        }
        Map<Object, Request> requestStore = getRequestStore(ioSession);
        int i = AnonymousClass2.$SwitchMap$org$apache$mina$filter$reqres$ResponseType[responseType.ordinal()];
        if (i == 1 || i == 2) {
            synchronized (requestStore) {
                request = requestStore.remove(requestId);
            }
        } else if (i == 3) {
            synchronized (requestStore) {
                request = requestStore.get(requestId);
            }
        } else {
            throw new InternalError();
        }
        if (request != null) {
            if (!(responseType == ResponseType.PARTIAL || (timeoutFuture = request.getTimeoutFuture()) == null)) {
                timeoutFuture.cancel(false);
                Set<Request> unrespondedRequestStore = getUnrespondedRequestStore(ioSession);
                synchronized (unrespondedRequestStore) {
                    unrespondedRequestStore.remove(request);
                }
            }
            Response response = new Response(request, obj, responseType);
            request.signal(response);
            nextFilter.messageReceived(ioSession, response);
        } else if (LOGGER.isWarnEnabled()) {
            Logger logger = LOGGER;
            logger.warn("Unknown request ID '" + requestId + "' for the response message. Timed out already?: " + obj);
        }
    }

    /* renamed from: org.apache.mina.filter.reqres.RequestResponseFilter$2  reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$mina$filter$reqres$ResponseType;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                org.apache.mina.filter.reqres.ResponseType[] r0 = org.apache.mina.filter.reqres.ResponseType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$apache$mina$filter$reqres$ResponseType = r0
                org.apache.mina.filter.reqres.ResponseType r1 = org.apache.mina.filter.reqres.ResponseType.WHOLE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$apache$mina$filter$reqres$ResponseType     // Catch:{ NoSuchFieldError -> 0x001d }
                org.apache.mina.filter.reqres.ResponseType r1 = org.apache.mina.filter.reqres.ResponseType.PARTIAL_LAST     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$org$apache$mina$filter$reqres$ResponseType     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.apache.mina.filter.reqres.ResponseType r1 = org.apache.mina.filter.reqres.ResponseType.PARTIAL     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.mina.filter.reqres.RequestResponseFilter.AnonymousClass2.<clinit>():void");
        }
    }

    /* access modifiers changed from: protected */
    public Object doFilterWrite(IoFilter.NextFilter nextFilter, IoSession ioSession, WriteRequest writeRequest) throws Exception {
        Request request;
        Object message = writeRequest.getMessage();
        if (!(message instanceof Request)) {
            return null;
        }
        Request request2 = (Request) message;
        if (request2.getTimeoutFuture() == null) {
            Map<Object, Request> requestStore = getRequestStore(ioSession);
            Object id = request2.getId();
            synchronized (requestStore) {
                request = requestStore.get(id);
                if (request == null) {
                    requestStore.put(id, request2);
                }
            }
            if (request == null) {
                TimeoutTask timeoutTask = new TimeoutTask(nextFilter, request2, ioSession);
                ScheduledFuture<?> schedule = this.timeoutScheduler.schedule(timeoutTask, request2.getTimeoutMillis(), TimeUnit.MILLISECONDS);
                request2.setTimeoutTask(timeoutTask);
                request2.setTimeoutFuture(schedule);
                Set<Request> unrespondedRequestStore = getUnrespondedRequestStore(ioSession);
                synchronized (unrespondedRequestStore) {
                    unrespondedRequestStore.add(request2);
                }
                return request2.getMessage();
            }
            throw new IllegalStateException("Duplicate request ID: " + request2.getId());
        }
        throw new IllegalArgumentException("Request can not be reused.");
    }

    public void sessionClosed(IoFilter.NextFilter nextFilter, IoSession ioSession) throws Exception {
        ArrayList<Request> arrayList;
        Set<Request> unrespondedRequestStore = getUnrespondedRequestStore(ioSession);
        synchronized (unrespondedRequestStore) {
            arrayList = new ArrayList<>(unrespondedRequestStore);
            unrespondedRequestStore.clear();
        }
        for (Request request : arrayList) {
            if (request.getTimeoutFuture().cancel(false)) {
                request.getTimeoutTask().run();
            }
        }
        Map<Object, Request> requestStore = getRequestStore(ioSession);
        synchronized (requestStore) {
            requestStore.clear();
        }
        nextFilter.sessionClosed(ioSession);
    }

    /* access modifiers changed from: private */
    public Map<Object, Request> getRequestStore(IoSession ioSession) {
        return (Map) ioSession.getAttribute(this.REQUEST_STORE);
    }

    /* access modifiers changed from: private */
    public Set<Request> getUnrespondedRequestStore(IoSession ioSession) {
        return (Set) ioSession.getAttribute(this.UNRESPONDED_REQUEST_STORE);
    }

    /* access modifiers changed from: protected */
    public Map<Object, Request> createRequestStore(IoSession ioSession) {
        return new ConcurrentHashMap();
    }

    /* access modifiers changed from: protected */
    public Set<Request> createUnrespondedRequestStore(IoSession ioSession) {
        return new LinkedHashSet();
    }

    private class TimeoutTask implements Runnable {
        private final IoFilter.NextFilter filter;
        private final Request request;
        private final IoSession session;

        private TimeoutTask(IoFilter.NextFilter nextFilter, Request request2, IoSession ioSession) {
            this.filter = nextFilter;
            this.request = request2;
            this.session = ioSession;
        }

        public void run() {
            boolean z;
            Set access$100 = RequestResponseFilter.this.getUnrespondedRequestStore(this.session);
            if (access$100 != null) {
                synchronized (access$100) {
                    access$100.remove(this.request);
                }
            }
            Map access$200 = RequestResponseFilter.this.getRequestStore(this.session);
            Object id = this.request.getId();
            synchronized (access$200) {
                if (access$200.get(id) == this.request) {
                    access$200.remove(id);
                    z = true;
                } else {
                    z = false;
                }
            }
            if (z) {
                RequestTimeoutException requestTimeoutException = new RequestTimeoutException(this.request);
                this.request.signal(requestTimeoutException);
                this.filter.exceptionCaught(this.session, requestTimeoutException);
            }
        }
    }
}
