package org.apache.mina.filter.reqres;

public class RequestTimeoutException extends RuntimeException {
    private static final long serialVersionUID = 5546784978950631652L;
    private final Request request;

    public RequestTimeoutException(Request request2) {
        if (request2 != null) {
            this.request = request2;
            return;
        }
        throw new IllegalArgumentException("request");
    }

    public RequestTimeoutException(Request request2, String str) {
        super(str);
        if (request2 != null) {
            this.request = request2;
            return;
        }
        throw new IllegalArgumentException("request");
    }

    public RequestTimeoutException(Request request2, String str, Throwable th) {
        super(str);
        initCause(th);
        if (request2 != null) {
            this.request = request2;
            return;
        }
        throw new IllegalArgumentException("request");
    }

    public RequestTimeoutException(Request request2, Throwable th) {
        initCause(th);
        if (request2 != null) {
            this.request = request2;
            return;
        }
        throw new IllegalArgumentException("request");
    }

    public Request getRequest() {
        return this.request;
    }
}
