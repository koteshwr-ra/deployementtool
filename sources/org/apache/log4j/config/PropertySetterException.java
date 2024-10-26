package org.apache.log4j.config;

public class PropertySetterException extends Exception {
    protected Throwable rootCause;

    public PropertySetterException(String str) {
        super(str);
    }

    public PropertySetterException(Throwable th) {
        this.rootCause = th;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0006, code lost:
        r1 = r2.rootCause;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String getMessage() {
        /*
            r2 = this;
            java.lang.String r0 = super.getMessage()
            if (r0 != 0) goto L_0x000e
            java.lang.Throwable r1 = r2.rootCause
            if (r1 == 0) goto L_0x000e
            java.lang.String r0 = r1.getMessage()
        L_0x000e:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.log4j.config.PropertySetterException.getMessage():java.lang.String");
    }
}
