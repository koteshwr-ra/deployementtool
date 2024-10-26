package org.apache.mina.core.write;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import org.apache.mina.util.MapBackedSet;

public class WriteException extends IOException {
    private static final long serialVersionUID = -4174407422754524197L;
    private final List<WriteRequest> requests;

    public WriteException(WriteRequest writeRequest) {
        this.requests = asRequestList(writeRequest);
    }

    public WriteException(WriteRequest writeRequest, String str) {
        super(str);
        this.requests = asRequestList(writeRequest);
    }

    public WriteException(WriteRequest writeRequest, String str, Throwable th) {
        super(str);
        initCause(th);
        this.requests = asRequestList(writeRequest);
    }

    public WriteException(WriteRequest writeRequest, Throwable th) {
        initCause(th);
        this.requests = asRequestList(writeRequest);
    }

    public WriteException(Collection<WriteRequest> collection) {
        this.requests = asRequestList(collection);
    }

    public WriteException(Collection<WriteRequest> collection, String str) {
        super(str);
        this.requests = asRequestList(collection);
    }

    public WriteException(Collection<WriteRequest> collection, String str, Throwable th) {
        super(str);
        initCause(th);
        this.requests = asRequestList(collection);
    }

    public WriteException(Collection<WriteRequest> collection, Throwable th) {
        initCause(th);
        this.requests = asRequestList(collection);
    }

    public List<WriteRequest> getRequests() {
        return this.requests;
    }

    public WriteRequest getRequest() {
        return this.requests.get(0);
    }

    private static List<WriteRequest> asRequestList(Collection<WriteRequest> collection) {
        if (collection == null) {
            throw new IllegalArgumentException("requests");
        } else if (!collection.isEmpty()) {
            MapBackedSet mapBackedSet = new MapBackedSet(new LinkedHashMap());
            for (WriteRequest originalRequest : collection) {
                mapBackedSet.add(originalRequest.getOriginalRequest());
            }
            return Collections.unmodifiableList(new ArrayList(mapBackedSet));
        } else {
            throw new IllegalArgumentException("requests is empty.");
        }
    }

    private static List<WriteRequest> asRequestList(WriteRequest writeRequest) {
        if (writeRequest != null) {
            ArrayList arrayList = new ArrayList(1);
            arrayList.add(writeRequest.getOriginalRequest());
            return Collections.unmodifiableList(arrayList);
        }
        throw new IllegalArgumentException("request");
    }
}
