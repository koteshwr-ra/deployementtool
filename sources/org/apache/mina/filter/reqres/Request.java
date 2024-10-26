package org.apache.mina.filter.reqres;

import java.util.NoSuchElementException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import kotlin.jvm.internal.LongCompanionObject;

public class Request {
    private volatile boolean endOfResponses;
    private final Object id;
    private final Object message;
    private final BlockingQueue<Object> responses;
    private volatile ScheduledFuture<?> timeoutFuture;
    private final long timeoutMillis;
    private volatile Runnable timeoutTask;

    public Request(Object obj, Object obj2, long j) {
        this(obj, obj2, true, j);
    }

    public Request(Object obj, Object obj2, boolean z, long j) {
        this(obj, obj2, z, j, TimeUnit.MILLISECONDS);
    }

    public Request(Object obj, Object obj2, long j, TimeUnit timeUnit) {
        this(obj, obj2, true, j, timeUnit);
    }

    public Request(Object obj, Object obj2, boolean z, long j, TimeUnit timeUnit) {
        if (obj == null) {
            throw new IllegalArgumentException("id");
        } else if (obj2 != null) {
            int i = (j > 0 ? 1 : (j == 0 ? 0 : -1));
            if (i >= 0) {
                j = i == 0 ? LongCompanionObject.MAX_VALUE : j;
                if (timeUnit != null) {
                    this.id = obj;
                    this.message = obj2;
                    this.responses = z ? new LinkedBlockingQueue() : null;
                    this.timeoutMillis = timeUnit.toMillis(j);
                    return;
                }
                throw new IllegalArgumentException("unit");
            }
            throw new IllegalArgumentException("timeout: " + j + " (expected: 0+)");
        } else {
            throw new IllegalArgumentException("message");
        }
    }

    public Object getId() {
        return this.id;
    }

    public Object getMessage() {
        return this.message;
    }

    public long getTimeoutMillis() {
        return this.timeoutMillis;
    }

    public boolean isUseResponseQueue() {
        return this.responses != null;
    }

    public boolean hasResponse() {
        checkUseResponseQueue();
        return !this.responses.isEmpty();
    }

    public Response awaitResponse() throws RequestTimeoutException, InterruptedException {
        checkUseResponseQueue();
        chechEndOfResponses();
        return convertToResponse(this.responses.take());
    }

    public Response awaitResponse(long j, TimeUnit timeUnit) throws RequestTimeoutException, InterruptedException {
        checkUseResponseQueue();
        chechEndOfResponses();
        return convertToResponse(this.responses.poll(j, timeUnit));
    }

    private Response convertToResponse(Object obj) {
        if (obj instanceof Response) {
            return (Response) obj;
        }
        if (obj == null) {
            return null;
        }
        throw ((RequestTimeoutException) obj);
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(3:0|1|2) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:0:0x0000 */
    /* JADX WARNING: Removed duplicated region for block: B:0:0x0000 A[MTH_ENTER_BLOCK, SYNTHETIC, Splitter:B:0:0x0000] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.apache.mina.filter.reqres.Response awaitResponseUninterruptibly() throws org.apache.mina.filter.reqres.RequestTimeoutException {
        /*
            r1 = this;
        L_0x0000:
            org.apache.mina.filter.reqres.Response r0 = r1.awaitResponse()     // Catch:{ InterruptedException -> 0x0000 }
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.mina.filter.reqres.Request.awaitResponseUninterruptibly():org.apache.mina.filter.reqres.Response");
    }

    private void chechEndOfResponses() {
        if (this.responses != null && this.endOfResponses && this.responses.isEmpty()) {
            throw new NoSuchElementException("All responses has been retrieved already.");
        }
    }

    private void checkUseResponseQueue() {
        if (this.responses == null) {
            throw new UnsupportedOperationException("Response queue is not available; useResponseQueue is false.");
        }
    }

    /* access modifiers changed from: package-private */
    public void signal(Response response) {
        signal0(response);
        if (response.getType() != ResponseType.PARTIAL) {
            this.endOfResponses = true;
        }
    }

    /* access modifiers changed from: package-private */
    public void signal(RequestTimeoutException requestTimeoutException) {
        signal0(requestTimeoutException);
        this.endOfResponses = true;
    }

    private void signal0(Object obj) {
        BlockingQueue<Object> blockingQueue = this.responses;
        if (blockingQueue != null) {
            blockingQueue.add(obj);
        }
    }

    public int hashCode() {
        return getId().hashCode();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj != null && (obj instanceof Request)) {
            return getId().equals(((Request) obj).getId());
        }
        return false;
    }

    public String toString() {
        String valueOf = getTimeoutMillis() == LongCompanionObject.MAX_VALUE ? "max" : String.valueOf(getTimeoutMillis());
        return "request: { id=" + getId() + ", timeout=" + valueOf + ", message=" + getMessage() + " }";
    }

    /* access modifiers changed from: package-private */
    public Runnable getTimeoutTask() {
        return this.timeoutTask;
    }

    /* access modifiers changed from: package-private */
    public void setTimeoutTask(Runnable runnable) {
        this.timeoutTask = runnable;
    }

    /* access modifiers changed from: package-private */
    public ScheduledFuture<?> getTimeoutFuture() {
        return this.timeoutFuture;
    }

    /* access modifiers changed from: package-private */
    public void setTimeoutFuture(ScheduledFuture<?> scheduledFuture) {
        this.timeoutFuture = scheduledFuture;
    }
}
