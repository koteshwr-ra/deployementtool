package com.google.common.eventbus;

import com.google.common.base.Preconditions;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.annotation.Nullable;

class EventSubscriber {
    private final Method method;
    private final Object target;

    EventSubscriber(Object obj, Method method2) {
        Preconditions.checkNotNull(obj, "EventSubscriber target cannot be null.");
        Preconditions.checkNotNull(method2, "EventSubscriber method cannot be null.");
        this.target = obj;
        this.method = method2;
        method2.setAccessible(true);
    }

    public void handleEvent(Object obj) throws InvocationTargetException {
        Preconditions.checkNotNull(obj);
        try {
            this.method.invoke(this.target, new Object[]{obj});
        } catch (IllegalArgumentException e) {
            String valueOf = String.valueOf(String.valueOf(obj));
            StringBuilder sb = new StringBuilder(valueOf.length() + 33);
            sb.append("Method rejected target/argument: ");
            sb.append(valueOf);
            throw new Error(sb.toString(), e);
        } catch (IllegalAccessException e2) {
            String valueOf2 = String.valueOf(String.valueOf(obj));
            StringBuilder sb2 = new StringBuilder(valueOf2.length() + 28);
            sb2.append("Method became inaccessible: ");
            sb2.append(valueOf2);
            throw new Error(sb2.toString(), e2);
        } catch (InvocationTargetException e3) {
            if (e3.getCause() instanceof Error) {
                throw ((Error) e3.getCause());
            }
            throw e3;
        }
    }

    public String toString() {
        String valueOf = String.valueOf(String.valueOf(this.method));
        StringBuilder sb = new StringBuilder(valueOf.length() + 10);
        sb.append("[wrapper ");
        sb.append(valueOf);
        sb.append("]");
        return sb.toString();
    }

    public int hashCode() {
        return ((this.method.hashCode() + 31) * 31) + System.identityHashCode(this.target);
    }

    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof EventSubscriber)) {
            return false;
        }
        EventSubscriber eventSubscriber = (EventSubscriber) obj;
        if (this.target != eventSubscriber.target || !this.method.equals(eventSubscriber.method)) {
            return false;
        }
        return true;
    }

    public Object getSubscriber() {
        return this.target;
    }

    public Method getMethod() {
        return this.method;
    }
}
