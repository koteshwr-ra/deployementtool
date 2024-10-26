package org.slf4j;

import java.util.Map;
import org.slf4j.helpers.Util;
import org.slf4j.impl.StaticMDCBinder;
import org.slf4j.spi.MDCAdapter;

public class MDC {
    static final String NO_STATIC_MDC_BINDER_URL = "http://www.slf4j.org/codes.html#no_static_mdc_binder";
    static final String NULL_MDCA_URL = "http://www.slf4j.org/codes.html#null_MDCA";
    static MDCAdapter mdcAdapter;

    private MDC() {
    }

    static {
        try {
            mdcAdapter = StaticMDCBinder.SINGLETON.getMDCA();
        } catch (NoClassDefFoundError e) {
            String message = e.getMessage();
            if (!(message == null || message.indexOf("org/slf4j/impl/StaticMDCBinder") == -1)) {
                Util.reportFailure("Failed to load class \"org.slf4j.impl.StaticMDCBinder\".");
                Util.reportFailure("See http://www.slf4j.org/codes.html#no_static_mdc_binder for further details.");
            }
            throw e;
        } catch (Exception e2) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Could not bind with an instance of class [");
            stringBuffer.append(StaticMDCBinder.SINGLETON.getMDCAdapterClassStr());
            stringBuffer.append("]");
            Util.reportFailure(stringBuffer.toString(), e2);
        }
    }

    public static void put(String str, String str2) throws IllegalArgumentException {
        if (str != null) {
            MDCAdapter mDCAdapter = mdcAdapter;
            if (mDCAdapter != null) {
                mDCAdapter.put(str, str2);
                return;
            }
            throw new IllegalStateException("MDCAdapter cannot be null. See also http://www.slf4j.org/codes.html#null_MDCA");
        }
        throw new IllegalArgumentException("key parameter cannot be null");
    }

    public static String get(String str) throws IllegalArgumentException {
        if (str != null) {
            MDCAdapter mDCAdapter = mdcAdapter;
            if (mDCAdapter != null) {
                return mDCAdapter.get(str);
            }
            throw new IllegalStateException("MDCAdapter cannot be null. See also http://www.slf4j.org/codes.html#null_MDCA");
        }
        throw new IllegalArgumentException("key parameter cannot be null");
    }

    public static void remove(String str) throws IllegalArgumentException {
        if (str != null) {
            MDCAdapter mDCAdapter = mdcAdapter;
            if (mDCAdapter != null) {
                mDCAdapter.remove(str);
                return;
            }
            throw new IllegalStateException("MDCAdapter cannot be null. See also http://www.slf4j.org/codes.html#null_MDCA");
        }
        throw new IllegalArgumentException("key parameter cannot be null");
    }

    public static void clear() {
        MDCAdapter mDCAdapter = mdcAdapter;
        if (mDCAdapter != null) {
            mDCAdapter.clear();
            return;
        }
        throw new IllegalStateException("MDCAdapter cannot be null. See also http://www.slf4j.org/codes.html#null_MDCA");
    }

    public static Map getCopyOfContextMap() {
        MDCAdapter mDCAdapter = mdcAdapter;
        if (mDCAdapter != null) {
            return mDCAdapter.getCopyOfContextMap();
        }
        throw new IllegalStateException("MDCAdapter cannot be null. See also http://www.slf4j.org/codes.html#null_MDCA");
    }

    public static void setContextMap(Map map) {
        MDCAdapter mDCAdapter = mdcAdapter;
        if (mDCAdapter != null) {
            mDCAdapter.setContextMap(map);
            return;
        }
        throw new IllegalStateException("MDCAdapter cannot be null. See also http://www.slf4j.org/codes.html#null_MDCA");
    }

    public static MDCAdapter getMDCAdapter() {
        return mdcAdapter;
    }
}
