package org.apache.mina.filter.reqres;

public class Response {
    private final Object message;
    private final Request request;
    private final ResponseType type;

    public Response(Request request2, Object obj, ResponseType responseType) {
        if (request2 == null) {
            throw new IllegalArgumentException("request");
        } else if (obj == null) {
            throw new IllegalArgumentException("message");
        } else if (responseType != null) {
            this.request = request2;
            this.type = responseType;
            this.message = obj;
        } else {
            throw new IllegalArgumentException("type");
        }
    }

    public Request getRequest() {
        return this.request;
    }

    public ResponseType getType() {
        return this.type;
    }

    public Object getMessage() {
        return this.message;
    }

    public int hashCode() {
        return getRequest().getId().hashCode();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || !(obj instanceof Response)) {
            return false;
        }
        Response response = (Response) obj;
        if (!getRequest().equals(response.getRequest())) {
            return false;
        }
        return getType().equals(response.getType());
    }

    public String toString() {
        return "response: { requestId=" + getRequest().getId() + ", type=" + getType() + ", message=" + getMessage() + " }";
    }
}
