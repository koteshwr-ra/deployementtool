package org.apache.mina.core.service;

import org.apache.mina.core.session.IoSession;

public interface IoProcessor<S extends IoSession> {
    void add(S s);

    void dispose();

    void flush(S s);

    boolean isDisposed();

    boolean isDisposing();

    void remove(S s);

    void updateTrafficControl(S s);
}
