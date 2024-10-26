package org.apache.mina.core.write;

import java.util.Collection;

public class NothingWrittenException extends WriteException {
    private static final long serialVersionUID = -6331979307737691005L;

    public NothingWrittenException(Collection<WriteRequest> collection, String str, Throwable th) {
        super(collection, str, th);
    }

    public NothingWrittenException(Collection<WriteRequest> collection, String str) {
        super(collection, str);
    }

    public NothingWrittenException(Collection<WriteRequest> collection, Throwable th) {
        super(collection, th);
    }

    public NothingWrittenException(Collection<WriteRequest> collection) {
        super(collection);
    }

    public NothingWrittenException(WriteRequest writeRequest, String str, Throwable th) {
        super(writeRequest, str, th);
    }

    public NothingWrittenException(WriteRequest writeRequest, String str) {
        super(writeRequest, str);
    }

    public NothingWrittenException(WriteRequest writeRequest, Throwable th) {
        super(writeRequest, th);
    }

    public NothingWrittenException(WriteRequest writeRequest) {
        super(writeRequest);
    }
}
