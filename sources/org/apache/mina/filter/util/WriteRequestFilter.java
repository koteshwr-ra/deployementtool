package org.apache.mina.filter.util;

import org.apache.mina.core.filterchain.IoFilter;
import org.apache.mina.core.filterchain.IoFilterAdapter;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.core.write.WriteRequest;
import org.apache.mina.core.write.WriteRequestWrapper;

public abstract class WriteRequestFilter extends IoFilterAdapter {
    /* access modifiers changed from: protected */
    public abstract Object doFilterWrite(IoFilter.NextFilter nextFilter, IoSession ioSession, WriteRequest writeRequest) throws Exception;

    public void filterWrite(IoFilter.NextFilter nextFilter, IoSession ioSession, WriteRequest writeRequest) throws Exception {
        Object doFilterWrite = doFilterWrite(nextFilter, ioSession, writeRequest);
        if (doFilterWrite == null || doFilterWrite == writeRequest.getMessage()) {
            nextFilter.filterWrite(ioSession, writeRequest);
        } else {
            nextFilter.filterWrite(ioSession, new FilteredWriteRequest(doFilterWrite, writeRequest));
        }
    }

    public void messageSent(IoFilter.NextFilter nextFilter, IoSession ioSession, WriteRequest writeRequest) throws Exception {
        if (writeRequest instanceof FilteredWriteRequest) {
            FilteredWriteRequest filteredWriteRequest = (FilteredWriteRequest) writeRequest;
            if (filteredWriteRequest.getParent() == this) {
                nextFilter.messageSent(ioSession, filteredWriteRequest.getParentRequest());
                return;
            }
        }
        nextFilter.messageSent(ioSession, writeRequest);
    }

    private class FilteredWriteRequest extends WriteRequestWrapper {
        private final Object filteredMessage;

        public FilteredWriteRequest(Object obj, WriteRequest writeRequest) {
            super(writeRequest);
            if (obj != null) {
                this.filteredMessage = obj;
                return;
            }
            throw new IllegalArgumentException("filteredMessage");
        }

        public WriteRequestFilter getParent() {
            return WriteRequestFilter.this;
        }

        public Object getMessage() {
            return this.filteredMessage;
        }
    }
}
