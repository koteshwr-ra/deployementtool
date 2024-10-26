package org.apache.mina.util;

import java.util.NoSuchElementException;
import java.util.Set;

public class AvailablePortFinder {
    public static final int MAX_PORT_NUMBER = 49151;
    public static final int MIN_PORT_NUMBER = 1;

    private AvailablePortFinder() {
    }

    public static Set<Integer> getAvailablePorts() {
        return getAvailablePorts(1, MAX_PORT_NUMBER);
    }

    public static int getNextAvailable() {
        return getNextAvailable(1);
    }

    public static int getNextAvailable(int i) {
        if (i < 1 || i > 49151) {
            throw new IllegalArgumentException("Invalid start port: " + i);
        }
        for (int i2 = i; i2 <= 49151; i2++) {
            if (available(i2)) {
                return i2;
            }
        }
        throw new NoSuchElementException("Could not find an available port above " + i);
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x002d  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0032 A[SYNTHETIC, Splitter:B:27:0x0032] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0039  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x003e A[SYNTHETIC, Splitter:B:36:0x003e] */
    /* JADX WARNING: Removed duplicated region for block: B:43:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean available(int r4) {
        /*
            r0 = 1
            if (r4 < r0) goto L_0x0043
            r1 = 49151(0xbfff, float:6.8875E-41)
            if (r4 > r1) goto L_0x0043
            r1 = 0
            java.net.ServerSocket r2 = new java.net.ServerSocket     // Catch:{ IOException -> 0x0036, all -> 0x0029 }
            r2.<init>(r4)     // Catch:{ IOException -> 0x0036, all -> 0x0029 }
            r2.setReuseAddress(r0)     // Catch:{ IOException -> 0x0027, all -> 0x0025 }
            java.net.DatagramSocket r3 = new java.net.DatagramSocket     // Catch:{ IOException -> 0x0027, all -> 0x0025 }
            r3.<init>(r4)     // Catch:{ IOException -> 0x0027, all -> 0x0025 }
            r3.setReuseAddress(r0)     // Catch:{ IOException -> 0x0023, all -> 0x0020 }
            r3.close()
            r2.close()     // Catch:{ IOException -> 0x001f }
        L_0x001f:
            return r0
        L_0x0020:
            r4 = move-exception
            r1 = r3
            goto L_0x002b
        L_0x0023:
            r1 = r3
            goto L_0x0037
        L_0x0025:
            r4 = move-exception
            goto L_0x002b
        L_0x0027:
            goto L_0x0037
        L_0x0029:
            r4 = move-exception
            r2 = r1
        L_0x002b:
            if (r1 == 0) goto L_0x0030
            r1.close()
        L_0x0030:
            if (r2 == 0) goto L_0x0035
            r2.close()     // Catch:{ IOException -> 0x0035 }
        L_0x0035:
            throw r4
        L_0x0036:
            r2 = r1
        L_0x0037:
            if (r1 == 0) goto L_0x003c
            r1.close()
        L_0x003c:
            if (r2 == 0) goto L_0x0041
            r2.close()     // Catch:{ IOException -> 0x0041 }
        L_0x0041:
            r4 = 0
            return r4
        L_0x0043:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Invalid start port: "
            r1.append(r2)
            r1.append(r4)
            java.lang.String r4 = r1.toString()
            r0.<init>(r4)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.mina.util.AvailablePortFinder.available(int):boolean");
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x002b A[SYNTHETIC, Splitter:B:20:0x002b] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0032 A[SYNTHETIC, Splitter:B:26:0x0032] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0035 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.Set<java.lang.Integer> getAvailablePorts(int r3, int r4) {
        /*
            r0 = 1
            if (r3 < r0) goto L_0x0039
            r0 = 49151(0xbfff, float:6.8875E-41)
            if (r4 > r0) goto L_0x0039
            if (r3 > r4) goto L_0x0039
            java.util.TreeSet r0 = new java.util.TreeSet
            r0.<init>()
        L_0x000f:
            if (r3 > r4) goto L_0x0038
            r1 = 0
            java.net.ServerSocket r2 = new java.net.ServerSocket     // Catch:{ IOException -> 0x002f, all -> 0x0028 }
            r2.<init>(r3)     // Catch:{ IOException -> 0x002f, all -> 0x0028 }
            java.lang.Integer r1 = new java.lang.Integer     // Catch:{ IOException -> 0x0026, all -> 0x0023 }
            r1.<init>(r3)     // Catch:{ IOException -> 0x0026, all -> 0x0023 }
            r0.add(r1)     // Catch:{ IOException -> 0x0026, all -> 0x0023 }
            r2.close()     // Catch:{ IOException -> 0x0035 }
            goto L_0x0035
        L_0x0023:
            r3 = move-exception
            r1 = r2
            goto L_0x0029
        L_0x0026:
            r1 = r2
            goto L_0x0030
        L_0x0028:
            r3 = move-exception
        L_0x0029:
            if (r1 == 0) goto L_0x002e
            r1.close()     // Catch:{ IOException -> 0x002e }
        L_0x002e:
            throw r3
        L_0x002f:
        L_0x0030:
            if (r1 == 0) goto L_0x0035
            r1.close()     // Catch:{ IOException -> 0x0035 }
        L_0x0035:
            int r3 = r3 + 1
            goto L_0x000f
        L_0x0038:
            return r0
        L_0x0039:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Invalid port range: "
            r1.append(r2)
            r1.append(r3)
            java.lang.String r3 = " ~ "
            r1.append(r3)
            r1.append(r4)
            java.lang.String r3 = r1.toString()
            r0.<init>(r3)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.mina.util.AvailablePortFinder.getAvailablePorts(int, int):java.util.Set");
    }
}
