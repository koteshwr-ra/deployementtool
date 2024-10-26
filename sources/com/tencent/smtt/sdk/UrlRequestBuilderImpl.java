package com.tencent.smtt.sdk;

import android.util.Pair;
import com.google.common.net.HttpHeaders;
import com.tencent.smtt.export.external.interfaces.UrlRequest;
import java.util.ArrayList;
import java.util.concurrent.Executor;

public class UrlRequestBuilderImpl extends UrlRequest.Builder {
    private static final String a = UrlRequestBuilderImpl.class.getSimpleName();
    private final String b;
    private final UrlRequest.Callback c;
    private final Executor d;
    private String e;
    private final ArrayList<Pair<String, String>> f = new ArrayList<>();
    private boolean g;
    private int h = 3;
    private String i;
    private byte[] j;
    private String k;
    private String l;

    public UrlRequestBuilderImpl(String str, UrlRequest.Callback callback, Executor executor) {
        if (str == null) {
            throw new NullPointerException("URL is required.");
        } else if (callback == null) {
            throw new NullPointerException("Callback is required.");
        } else if (executor != null) {
            this.b = str;
            this.c = callback;
            this.d = executor;
        } else {
            throw new NullPointerException("Executor is required.");
        }
    }

    public UrlRequestBuilderImpl addHeader(String str, String str2) {
        if (str == null) {
            throw new NullPointerException("Invalid header name.");
        } else if (str2 == null) {
            throw new NullPointerException("Invalid header value.");
        } else if (HttpHeaders.ACCEPT_ENCODING.equalsIgnoreCase(str)) {
            return this;
        } else {
            this.f.add(Pair.create(str, str2));
            return this;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v8, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v7, resolved type: com.tencent.smtt.export.external.interfaces.UrlRequest} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v41, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v10, resolved type: com.tencent.smtt.export.external.interfaces.UrlRequest} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.tencent.smtt.export.external.interfaces.UrlRequest build() throws java.lang.NullPointerException {
        /*
            r17 = this;
            r0 = r17
            java.lang.Class<byte[]> r1 = byte[].class
            com.tencent.smtt.sdk.w r2 = com.tencent.smtt.sdk.w.a()
            if (r2 == 0) goto L_0x01bb
            boolean r3 = r2.b()
            if (r3 == 0) goto L_0x01bb
            com.tencent.smtt.sdk.x r2 = r2.c()
            com.tencent.smtt.export.external.DexLoader r2 = r2.b()
            r3 = 11
            java.lang.Class[] r4 = new java.lang.Class[r3]
            java.lang.Class<java.lang.String> r5 = java.lang.String.class
            r6 = 0
            r4[r6] = r5
            java.lang.Class r5 = java.lang.Integer.TYPE
            r7 = 1
            r4[r7] = r5
            java.lang.Class<com.tencent.smtt.export.external.interfaces.UrlRequest$Callback> r5 = com.tencent.smtt.export.external.interfaces.UrlRequest.Callback.class
            r8 = 2
            r4[r8] = r5
            java.lang.Class<java.util.concurrent.Executor> r5 = java.util.concurrent.Executor.class
            r9 = 3
            r4[r9] = r5
            java.lang.Class r5 = java.lang.Boolean.TYPE
            r10 = 4
            r4[r10] = r5
            java.lang.Class<java.lang.String> r5 = java.lang.String.class
            r11 = 5
            r4[r11] = r5
            java.lang.Class<java.util.ArrayList> r5 = java.util.ArrayList.class
            r12 = 6
            r4[r12] = r5
            java.lang.Class<java.lang.String> r5 = java.lang.String.class
            r13 = 7
            r4[r13] = r5
            r5 = 8
            r4[r5] = r1
            java.lang.Class<java.lang.String> r14 = java.lang.String.class
            r15 = 9
            r4[r15] = r14
            java.lang.Class<java.lang.String> r14 = java.lang.String.class
            r16 = 10
            r4[r16] = r14
            java.lang.Object[] r14 = new java.lang.Object[r3]
            java.lang.String r3 = r0.b
            r14[r6] = r3
            int r3 = r0.h
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            r14[r7] = r3
            com.tencent.smtt.export.external.interfaces.UrlRequest$Callback r3 = r0.c
            r14[r8] = r3
            java.util.concurrent.Executor r3 = r0.d
            r14[r9] = r3
            boolean r3 = r0.g
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r3)
            r14[r10] = r3
            java.lang.String r3 = r0.e
            r14[r11] = r3
            java.util.ArrayList<android.util.Pair<java.lang.String, java.lang.String>> r3 = r0.f
            r14[r12] = r3
            java.lang.String r3 = r0.i
            r14[r13] = r3
            byte[] r3 = r0.j
            r14[r5] = r3
            java.lang.String r3 = r0.k
            r14[r15] = r3
            java.lang.String r3 = r0.l
            r14[r16] = r3
            java.lang.String r3 = "com.tencent.smtt.net.X5UrlRequestProvider"
            java.lang.String r15 = "GetX5UrlRequestProvider"
            java.lang.Object r4 = r2.invokeStaticMethod(r3, r15, r4, r14)
            com.tencent.smtt.export.external.interfaces.UrlRequest r4 = (com.tencent.smtt.export.external.interfaces.UrlRequest) r4
            if (r4 != 0) goto L_0x00e8
            java.lang.Class[] r4 = new java.lang.Class[r5]
            java.lang.Class<java.lang.String> r14 = java.lang.String.class
            r4[r6] = r14
            java.lang.Class r14 = java.lang.Integer.TYPE
            r4[r7] = r14
            java.lang.Class<com.tencent.smtt.export.external.interfaces.UrlRequest$Callback> r14 = com.tencent.smtt.export.external.interfaces.UrlRequest.Callback.class
            r4[r8] = r14
            java.lang.Class<java.util.concurrent.Executor> r14 = java.util.concurrent.Executor.class
            r4[r9] = r14
            java.lang.Class r14 = java.lang.Boolean.TYPE
            r4[r10] = r14
            java.lang.Class<java.lang.String> r14 = java.lang.String.class
            r4[r11] = r14
            java.lang.Class<java.util.ArrayList> r14 = java.util.ArrayList.class
            r4[r12] = r14
            java.lang.Class<java.lang.String> r14 = java.lang.String.class
            r4[r13] = r14
            java.lang.Object[] r14 = new java.lang.Object[r5]
            java.lang.String r5 = r0.b
            r14[r6] = r5
            int r5 = r0.h
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            r14[r7] = r5
            com.tencent.smtt.export.external.interfaces.UrlRequest$Callback r5 = r0.c
            r14[r8] = r5
            java.util.concurrent.Executor r5 = r0.d
            r14[r9] = r5
            boolean r5 = r0.g
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)
            r14[r10] = r5
            java.lang.String r5 = r0.e
            r14[r11] = r5
            java.util.ArrayList<android.util.Pair<java.lang.String, java.lang.String>> r5 = r0.f
            r14[r12] = r5
            java.lang.String r5 = r0.i
            r14[r13] = r5
            java.lang.Object r4 = r2.invokeStaticMethod(r3, r15, r4, r14)
            com.tencent.smtt.export.external.interfaces.UrlRequest r4 = (com.tencent.smtt.export.external.interfaces.UrlRequest) r4
        L_0x00e8:
            if (r4 != 0) goto L_0x0135
            java.lang.Class[] r4 = new java.lang.Class[r13]
            java.lang.Class<java.lang.String> r5 = java.lang.String.class
            r4[r6] = r5
            java.lang.Class r5 = java.lang.Integer.TYPE
            r4[r7] = r5
            java.lang.Class<com.tencent.smtt.export.external.interfaces.UrlRequest$Callback> r5 = com.tencent.smtt.export.external.interfaces.UrlRequest.Callback.class
            r4[r8] = r5
            java.lang.Class<java.util.concurrent.Executor> r5 = java.util.concurrent.Executor.class
            r4[r9] = r5
            java.lang.Class r5 = java.lang.Boolean.TYPE
            r4[r10] = r5
            java.lang.Class<java.lang.String> r5 = java.lang.String.class
            r4[r11] = r5
            java.lang.Class<java.util.ArrayList> r5 = java.util.ArrayList.class
            r4[r12] = r5
            java.lang.Object[] r5 = new java.lang.Object[r13]
            java.lang.String r14 = r0.b
            r5[r6] = r14
            int r14 = r0.h
            java.lang.Integer r14 = java.lang.Integer.valueOf(r14)
            r5[r7] = r14
            com.tencent.smtt.export.external.interfaces.UrlRequest$Callback r14 = r0.c
            r5[r8] = r14
            java.util.concurrent.Executor r14 = r0.d
            r5[r9] = r14
            boolean r14 = r0.g
            java.lang.Boolean r14 = java.lang.Boolean.valueOf(r14)
            r5[r10] = r14
            java.lang.String r14 = r0.e
            r5[r11] = r14
            java.util.ArrayList<android.util.Pair<java.lang.String, java.lang.String>> r14 = r0.f
            r5[r12] = r14
            java.lang.Object r3 = r2.invokeStaticMethod(r3, r15, r4, r5)
            r4 = r3
            com.tencent.smtt.export.external.interfaces.UrlRequest r4 = (com.tencent.smtt.export.external.interfaces.UrlRequest) r4
        L_0x0135:
            if (r4 != 0) goto L_0x01b0
            r3 = 11
            java.lang.Class[] r4 = new java.lang.Class[r3]
            java.lang.Class<java.lang.String> r3 = java.lang.String.class
            r4[r6] = r3
            java.lang.Class r3 = java.lang.Integer.TYPE
            r4[r7] = r3
            java.lang.Class<com.tencent.smtt.export.external.interfaces.UrlRequest$Callback> r3 = com.tencent.smtt.export.external.interfaces.UrlRequest.Callback.class
            r4[r8] = r3
            java.lang.Class<java.util.concurrent.Executor> r3 = java.util.concurrent.Executor.class
            r4[r9] = r3
            java.lang.Class r3 = java.lang.Boolean.TYPE
            r4[r10] = r3
            java.lang.Class<java.lang.String> r3 = java.lang.String.class
            r4[r11] = r3
            java.lang.Class<java.util.ArrayList> r3 = java.util.ArrayList.class
            r4[r12] = r3
            java.lang.Class<java.lang.String> r3 = java.lang.String.class
            r4[r13] = r3
            r3 = 8
            r4[r3] = r1
            java.lang.Class<java.lang.String> r1 = java.lang.String.class
            r3 = 9
            r4[r3] = r1
            java.lang.Class<java.lang.String> r1 = java.lang.String.class
            r4[r16] = r1
            r1 = 11
            java.lang.Object[] r1 = new java.lang.Object[r1]
            java.lang.String r3 = r0.b
            r1[r6] = r3
            int r3 = r0.h
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            r1[r7] = r3
            com.tencent.smtt.export.external.interfaces.UrlRequest$Callback r3 = r0.c
            r1[r8] = r3
            java.util.concurrent.Executor r3 = r0.d
            r1[r9] = r3
            boolean r3 = r0.g
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r3)
            r1[r10] = r3
            java.lang.String r3 = r0.e
            r1[r11] = r3
            java.util.ArrayList<android.util.Pair<java.lang.String, java.lang.String>> r3 = r0.f
            r1[r12] = r3
            java.lang.String r3 = r0.i
            r1[r13] = r3
            byte[] r3 = r0.j
            r5 = 8
            r1[r5] = r3
            java.lang.String r3 = r0.k
            r5 = 9
            r1[r5] = r3
            java.lang.String r3 = r0.l
            r1[r16] = r3
            java.lang.String r3 = "com.tencent.tbs.tbsshell.WebCoreProxy"
            java.lang.String r5 = "UrlRequest_getX5UrlRequestProvider"
            java.lang.Object r1 = r2.invokeStaticMethod(r3, r5, r4, r1)
            r4 = r1
            com.tencent.smtt.export.external.interfaces.UrlRequest r4 = (com.tencent.smtt.export.external.interfaces.UrlRequest) r4
        L_0x01b0:
            if (r4 == 0) goto L_0x01b3
            return r4
        L_0x01b3:
            java.lang.NullPointerException r1 = new java.lang.NullPointerException
            java.lang.String r2 = "UrlRequest build fail"
            r1.<init>(r2)
            throw r1
        L_0x01bb:
            r1 = 0
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.UrlRequestBuilderImpl.build():com.tencent.smtt.export.external.interfaces.UrlRequest");
    }

    public UrlRequestBuilderImpl disableCache() {
        this.g = true;
        return this;
    }

    public UrlRequestBuilderImpl setDns(String str, String str2) {
        if (str == null || str2 == null) {
            throw new NullPointerException("host and address are required.");
        }
        this.k = str;
        this.l = str2;
        try {
            w a2 = w.a();
            if (a2 != null && a2.b()) {
                a2.c().b().invokeStaticMethod("com.tencent.smtt.net.X5UrlRequestProvider", "setDns", new Class[]{String.class, String.class}, this.k, this.l);
            }
        } catch (Exception unused) {
        }
        return this;
    }

    public UrlRequest.Builder setHttpMethod(String str) {
        if (str != null) {
            this.e = str;
            return this;
        }
        throw new NullPointerException("Method is required.");
    }

    public UrlRequestBuilderImpl setPriority(int i2) {
        this.h = i2;
        return this;
    }

    public UrlRequest.Builder setRequestBody(String str) {
        if (str != null) {
            this.i = str;
            return this;
        }
        throw new NullPointerException("Body is required.");
    }

    public UrlRequest.Builder setRequestBodyBytes(byte[] bArr) {
        if (bArr != null) {
            this.j = bArr;
            return this;
        }
        throw new NullPointerException("Body is required.");
    }
}
