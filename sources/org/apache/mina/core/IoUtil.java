package org.apache.mina.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.future.IoFuture;
import org.apache.mina.core.future.WriteFuture;
import org.apache.mina.core.session.IoSession;

public class IoUtil {
    private static final IoSession[] EMPTY_SESSIONS = new IoSession[0];

    public static List<WriteFuture> broadcast(Object obj, Collection<IoSession> collection) {
        ArrayList arrayList = new ArrayList(collection.size());
        broadcast(obj, collection.iterator(), arrayList);
        return arrayList;
    }

    public static List<WriteFuture> broadcast(Object obj, Iterable<IoSession> iterable) {
        ArrayList arrayList = new ArrayList();
        broadcast(obj, iterable.iterator(), arrayList);
        return arrayList;
    }

    public static List<WriteFuture> broadcast(Object obj, Iterator<IoSession> it) {
        ArrayList arrayList = new ArrayList();
        broadcast(obj, it, arrayList);
        return arrayList;
    }

    public static List<WriteFuture> broadcast(Object obj, IoSession... ioSessionArr) {
        if (ioSessionArr == null) {
            ioSessionArr = EMPTY_SESSIONS;
        }
        ArrayList arrayList = new ArrayList(ioSessionArr.length);
        int i = 0;
        if (obj instanceof IoBuffer) {
            int length = ioSessionArr.length;
            while (i < length) {
                arrayList.add(ioSessionArr[i].write(((IoBuffer) obj).duplicate()));
                i++;
            }
        } else {
            int length2 = ioSessionArr.length;
            while (i < length2) {
                arrayList.add(ioSessionArr[i].write(obj));
                i++;
            }
        }
        return arrayList;
    }

    private static void broadcast(Object obj, Iterator<IoSession> it, Collection<WriteFuture> collection) {
        if (obj instanceof IoBuffer) {
            while (it.hasNext()) {
                collection.add(it.next().write(((IoBuffer) obj).duplicate()));
            }
            return;
        }
        while (it.hasNext()) {
            collection.add(it.next().write(obj));
        }
    }

    public static void await(Iterable<? extends IoFuture> iterable) throws InterruptedException {
        for (IoFuture await : iterable) {
            await.await();
        }
    }

    public static void awaitUninterruptably(Iterable<? extends IoFuture> iterable) {
        for (IoFuture awaitUninterruptibly : iterable) {
            awaitUninterruptibly.awaitUninterruptibly();
        }
    }

    public static boolean await(Iterable<? extends IoFuture> iterable, long j, TimeUnit timeUnit) throws InterruptedException {
        return await(iterable, timeUnit.toMillis(j));
    }

    public static boolean await(Iterable<? extends IoFuture> iterable, long j) throws InterruptedException {
        return await0(iterable, j, true);
    }

    public static boolean awaitUninterruptibly(Iterable<? extends IoFuture> iterable, long j, TimeUnit timeUnit) {
        return awaitUninterruptibly(iterable, timeUnit.toMillis(j));
    }

    public static boolean awaitUninterruptibly(Iterable<? extends IoFuture> iterable, long j) {
        try {
            return await0(iterable, j, false);
        } catch (InterruptedException unused) {
            throw new InternalError();
        }
    }

    private static boolean await0(Iterable<? extends IoFuture> iterable, long j, boolean z) throws InterruptedException {
        boolean z2;
        long currentTimeMillis;
        long currentTimeMillis2 = j <= 0 ? 0 : System.currentTimeMillis();
        Iterator<? extends IoFuture> it = iterable.iterator();
        long j2 = j;
        boolean z3 = true;
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            IoFuture ioFuture = (IoFuture) it.next();
            while (true) {
                if (z) {
                    z2 = ioFuture.await(j2);
                } else {
                    z2 = ioFuture.awaitUninterruptibly(j2);
                }
                currentTimeMillis = j - (System.currentTimeMillis() - currentTimeMillis2);
                if (!z2 && currentTimeMillis > 0 && !z2) {
                    j2 = currentTimeMillis;
                }
            }
            if (currentTimeMillis <= 0) {
                z3 = z2;
                break;
            }
            z3 = z2;
            j2 = currentTimeMillis;
        }
        if (!z3 || it.hasNext()) {
            return false;
        }
        return true;
    }

    private IoUtil() {
    }
}
