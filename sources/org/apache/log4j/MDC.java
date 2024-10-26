package org.apache.log4j;

import java.util.Hashtable;
import org.apache.log4j.helpers.Loader;
import org.apache.log4j.helpers.ThreadLocalMap;

public class MDC {
    static final int HT_SIZE = 7;
    static final MDC mdc = new MDC();
    boolean java1;
    Object tlm;

    private MDC() {
        boolean isJava1 = Loader.isJava1();
        this.java1 = isJava1;
        if (!isJava1) {
            this.tlm = new ThreadLocalMap();
        }
    }

    public static void put(String str, Object obj) {
        mdc.put0(str, obj);
    }

    public static Object get(String str) {
        return mdc.get0(str);
    }

    public static void remove(String str) {
        mdc.remove0(str);
    }

    public static Hashtable getContext() {
        return mdc.getContext0();
    }

    private void put0(String str, Object obj) {
        if (!this.java1) {
            Hashtable hashtable = (Hashtable) ((ThreadLocalMap) this.tlm).get();
            if (hashtable == null) {
                hashtable = new Hashtable(7);
                ((ThreadLocalMap) this.tlm).set(hashtable);
            }
            hashtable.put(str, obj);
        }
    }

    private Object get0(String str) {
        Hashtable hashtable;
        if (this.java1 || (hashtable = (Hashtable) ((ThreadLocalMap) this.tlm).get()) == null || str == null) {
            return null;
        }
        return hashtable.get(str);
    }

    private void remove0(String str) {
        Hashtable hashtable;
        if (!this.java1 && (hashtable = (Hashtable) ((ThreadLocalMap) this.tlm).get()) != null) {
            hashtable.remove(str);
        }
    }

    private Hashtable getContext0() {
        if (this.java1) {
            return null;
        }
        return (Hashtable) ((ThreadLocalMap) this.tlm).get();
    }
}
