package com.tencent.mars.logger;

import com.ciot.networklib.bean.phone.VisitByPhone;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class LoggerPrinter implements Printer {
    private static final int JSON_INDENT = 2;
    private final List<LogAdapter> logAdapters = new ArrayList();

    LoggerPrinter() {
    }

    public void v(String str, String str2, Object... objArr) {
        log(0, str, (Throwable) null, str2, objArr);
    }

    public void i(String str, String str2, Object... objArr) {
        log(2, str, (Throwable) null, str2, objArr);
    }

    public void d(String str, String str2, Object... objArr) {
        log(1, str, (Throwable) null, str2, objArr);
    }

    public void e(String str, String str2, Object... objArr) {
        log(4, str, (Throwable) null, str2, objArr);
    }

    public void e(String str, Throwable th, String str2, Object... objArr) {
        log(4, str, th, str2, objArr);
    }

    public void w(String str, String str2, Object... objArr) {
        log(3, str, (Throwable) null, str2, objArr);
    }

    public void wtf(String str, String str2, Object... objArr) {
        log(5, str, (Throwable) null, str2, objArr);
    }

    public void json(String str, String str2) {
        if (Utils.isEmpty(str2)) {
            d(str, "Empty/Null json content", new Object[0]);
            return;
        }
        try {
            String trim = str2.trim();
            if (trim.startsWith("{")) {
                d(str, new JSONObject(trim).toString(2), new Object[0]);
            } else if (trim.startsWith("[")) {
                d(str, new JSONArray(trim).toString(2), new Object[0]);
            } else {
                e(str, "Invalid Json", new Object[0]);
            }
        } catch (JSONException unused) {
            e(str, "Invalid Json", new Object[0]);
        }
    }

    public void xml(String str, String str2) {
        if (Utils.isEmpty(str2)) {
            d(str, "Empty/Null xml content", new Object[0]);
            return;
        }
        try {
            StreamSource streamSource = new StreamSource(new StringReader(str2));
            StreamResult streamResult = new StreamResult(new StringWriter());
            Transformer newTransformer = TransformerFactory.newInstance().newTransformer();
            newTransformer.setOutputProperty("indent", VisitByPhone.VERIFIED);
            newTransformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            newTransformer.transform(streamSource, streamResult);
            d(str, streamResult.getWriter().toString().replaceFirst(">", ">\n"), new Object[0]);
        } catch (TransformerException unused) {
            e(str, "Invalid xml", new Object[0]);
        }
    }

    public synchronized void log(int i, String str, String str2, Throwable th) {
        if (!(th == null || str2 == null)) {
            str2 = str2 + " : " + Utils.getStackTraceString(th);
        }
        if (th != null && str2 == null) {
            str2 = Utils.getStackTraceString(th);
        }
        if (Utils.isEmpty(str2)) {
            str2 = "Empty/NULL log message";
        }
        for (LogAdapter next : this.logAdapters) {
            if (next.isLoggable(i, str)) {
                next.log(i, str, str2);
            }
        }
    }

    public void clearLogAdapters() {
        this.logAdapters.clear();
    }

    public void addAdapter(LogAdapter logAdapter) {
        this.logAdapters.add((LogAdapter) Utils.checkNotNull(logAdapter));
    }

    private synchronized void log(int i, String str, Throwable th, String str2, Object... objArr) {
        Utils.checkNotNull(str2);
        log(i, str, createMessage(str2, objArr), th);
    }

    private String createMessage(String str, Object... objArr) {
        return (objArr == null || objArr.length == 0) ? str : String.format(str, objArr);
    }
}
