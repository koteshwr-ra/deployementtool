package org.apache.mina.filter.reqres;

public interface ResponseInspector {
    Object getRequestId(Object obj);

    ResponseType getResponseType(Object obj);
}
