package org.apache.log4j.spi;

import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;
import org.apache.log4j.helpers.LogLog;

public class LocationInfo implements Serializable {
    public static final String NA = "?";
    static boolean inVisualAge = false;
    private static PrintWriter pw = new PrintWriter(sw);
    static final long serialVersionUID = -1325822038990805636L;
    private static StringWriter sw = new StringWriter();
    transient String className;
    transient String fileName;
    public String fullInfo;
    transient String lineNumber;
    transient String methodName;

    static {
        inVisualAge = false;
        try {
            Class.forName("com.ibm.uvm.tools.DebugSupport");
            inVisualAge = true;
            LogLog.debug("Detected IBM VisualAge environment.");
        } catch (Throwable unused) {
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0030, code lost:
        r5 = r5 + org.apache.log4j.Layout.LINE_SEP_LEN;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public LocationInfo(java.lang.Throwable r4, java.lang.String r5) {
        /*
            r3 = this;
            r3.<init>()
            if (r4 != 0) goto L_0x0006
            return
        L_0x0006:
            java.io.StringWriter r0 = sw
            monitor-enter(r0)
            java.io.PrintWriter r1 = pw     // Catch:{ all -> 0x0052 }
            r4.printStackTrace(r1)     // Catch:{ all -> 0x0052 }
            java.io.StringWriter r4 = sw     // Catch:{ all -> 0x0052 }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x0052 }
            java.io.StringWriter r1 = sw     // Catch:{ all -> 0x0052 }
            java.lang.StringBuffer r1 = r1.getBuffer()     // Catch:{ all -> 0x0052 }
            r2 = 0
            r1.setLength(r2)     // Catch:{ all -> 0x0052 }
            monitor-exit(r0)     // Catch:{ all -> 0x0052 }
            int r5 = r4.lastIndexOf(r5)
            r0 = -1
            if (r5 != r0) goto L_0x0027
            return
        L_0x0027:
            java.lang.String r1 = org.apache.log4j.Layout.LINE_SEP
            int r5 = r4.indexOf(r1, r5)
            if (r5 != r0) goto L_0x0030
            return
        L_0x0030:
            int r1 = org.apache.log4j.Layout.LINE_SEP_LEN
            int r5 = r5 + r1
            java.lang.String r1 = org.apache.log4j.Layout.LINE_SEP
            int r1 = r4.indexOf(r1, r5)
            if (r1 != r0) goto L_0x003c
            return
        L_0x003c:
            boolean r2 = inVisualAge
            if (r2 != 0) goto L_0x004b
            java.lang.String r5 = "at "
            int r5 = r4.lastIndexOf(r5, r1)
            if (r5 != r0) goto L_0x0049
            return
        L_0x0049:
            int r5 = r5 + 3
        L_0x004b:
            java.lang.String r4 = r4.substring(r5, r1)
            r3.fullInfo = r4
            return
        L_0x0052:
            r4 = move-exception
            monitor-exit(r0)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.log4j.spi.LocationInfo.<init>(java.lang.Throwable, java.lang.String):void");
    }

    public String getClassName() {
        String str = this.fullInfo;
        if (str == null) {
            return NA;
        }
        if (this.className == null) {
            int lastIndexOf = str.lastIndexOf(40);
            if (lastIndexOf == -1) {
                this.className = NA;
            } else {
                int lastIndexOf2 = this.fullInfo.lastIndexOf(46, lastIndexOf);
                int i = 0;
                if (inVisualAge) {
                    i = this.fullInfo.lastIndexOf(32, lastIndexOf2) + 1;
                }
                if (lastIndexOf2 == -1) {
                    this.className = NA;
                } else {
                    this.className = this.fullInfo.substring(i, lastIndexOf2);
                }
            }
        }
        return this.className;
    }

    public String getFileName() {
        String str = this.fullInfo;
        if (str == null) {
            return NA;
        }
        if (this.fileName == null) {
            int lastIndexOf = str.lastIndexOf(58);
            if (lastIndexOf == -1) {
                this.fileName = NA;
            } else {
                this.fileName = this.fullInfo.substring(this.fullInfo.lastIndexOf(40, lastIndexOf - 1) + 1, lastIndexOf);
            }
        }
        return this.fileName;
    }

    public String getLineNumber() {
        String str = this.fullInfo;
        if (str == null) {
            return NA;
        }
        if (this.lineNumber == null) {
            int lastIndexOf = str.lastIndexOf(41);
            int lastIndexOf2 = this.fullInfo.lastIndexOf(58, lastIndexOf - 1);
            if (lastIndexOf2 == -1) {
                this.lineNumber = NA;
            } else {
                this.lineNumber = this.fullInfo.substring(lastIndexOf2 + 1, lastIndexOf);
            }
        }
        return this.lineNumber;
    }

    public String getMethodName() {
        String str = this.fullInfo;
        if (str == null) {
            return NA;
        }
        if (this.methodName == null) {
            int lastIndexOf = str.lastIndexOf(40);
            int lastIndexOf2 = this.fullInfo.lastIndexOf(46, lastIndexOf);
            if (lastIndexOf2 == -1) {
                this.methodName = NA;
            } else {
                this.methodName = this.fullInfo.substring(lastIndexOf2 + 1, lastIndexOf);
            }
        }
        return this.methodName;
    }
}
