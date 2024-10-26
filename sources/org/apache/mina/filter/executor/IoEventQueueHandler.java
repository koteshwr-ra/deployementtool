package org.apache.mina.filter.executor;

import java.util.EventListener;
import org.apache.mina.core.session.IoEvent;

public interface IoEventQueueHandler extends EventListener {
    public static final IoEventQueueHandler NOOP = new IoEventQueueHandler() {
        public boolean accept(Object obj, IoEvent ioEvent) {
            return true;
        }

        public void offered(Object obj, IoEvent ioEvent) {
        }

        public void polled(Object obj, IoEvent ioEvent) {
        }
    };

    boolean accept(Object obj, IoEvent ioEvent);

    void offered(Object obj, IoEvent ioEvent);

    void polled(Object obj, IoEvent ioEvent);
}
