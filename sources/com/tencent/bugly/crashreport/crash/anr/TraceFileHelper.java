package com.tencent.bugly.crashreport.crash.anr;

import com.tencent.bugly.proguard.x;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

/* compiled from: BUGLY */
public class TraceFileHelper {

    /* compiled from: BUGLY */
    public static class a {
        public long a;
        public String b;
        public long c;
        public Map<String, String[]> d;
    }

    /* compiled from: BUGLY */
    public interface b {
        boolean a(long j);

        boolean a(long j, long j2, String str);

        boolean a(String str, int i, String str2, String str3);
    }

    public static a readTargetDumpInfo(final String str, String str2, final boolean z) {
        if (!(str == null || str2 == null)) {
            final a aVar = new a();
            readTraceFile(str2, new b() {
                public final boolean a(String str, int i, String str2, String str3) {
                    x.c("new thread %s", str);
                    if (aVar.a > 0 && aVar.c > 0 && aVar.b != null) {
                        if (aVar.d == null) {
                            aVar.d = new HashMap();
                        }
                        Map<String, String[]> map = aVar.d;
                        StringBuilder sb = new StringBuilder();
                        sb.append(i);
                        map.put(str, new String[]{str2, str3, sb.toString()});
                    }
                    return true;
                }

                public final boolean a(long j, long j2, String str) {
                    x.c("new process %s", str);
                    if (!str.equals(str)) {
                        return true;
                    }
                    aVar.a = j;
                    aVar.b = str;
                    aVar.c = j2;
                    return z;
                }

                public final boolean a(long j) {
                    x.c("process end %d", Long.valueOf(j));
                    return aVar.a <= 0 || aVar.c <= 0 || aVar.b == null;
                }
            });
            if (aVar.a <= 0 || aVar.c <= 0 || aVar.b == null) {
                return null;
            }
            return aVar;
        }
        return null;
    }

    public static a readFirstDumpInfo(String str, final boolean z) {
        if (str == null) {
            x.e("path:%s", str);
            return null;
        }
        final a aVar = new a();
        readTraceFile(str, new b() {
            public final boolean a(String str, int i, String str2, String str3) {
                x.c("new thread %s", str);
                if (aVar.d == null) {
                    aVar.d = new HashMap();
                }
                Map<String, String[]> map = aVar.d;
                StringBuilder sb = new StringBuilder();
                sb.append(i);
                map.put(str, new String[]{str2, str3, sb.toString()});
                return true;
            }

            public final boolean a(long j, long j2, String str) {
                x.c("new process %s", str);
                aVar.a = j;
                aVar.b = str;
                aVar.c = j2;
                return z;
            }

            public final boolean a(long j) {
                x.c("process end %d", Long.valueOf(j));
                return false;
            }
        });
        if (aVar.a > 0 && aVar.c > 0 && aVar.b != null) {
            return aVar;
        }
        x.e("first dump error %s", aVar.a + StringUtils.SPACE + aVar.c + StringUtils.SPACE + aVar.b);
        return null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:66:0x017f A[Catch:{ all -> 0x0175 }] */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x01a7 A[SYNTHETIC, Splitter:B:69:0x01a7] */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x01b9 A[SYNTHETIC, Splitter:B:77:0x01b9] */
    /* JADX WARNING: Removed duplicated region for block: B:96:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void readTraceFile(java.lang.String r18, com.tencent.bugly.crashreport.crash.anr.TraceFileHelper.b r19) {
        /*
            r0 = r18
            r6 = r19
            java.lang.String r7 = "\\s"
            if (r0 == 0) goto L_0x01c9
            if (r6 != 0) goto L_0x000c
            goto L_0x01c9
        L_0x000c:
            java.io.File r1 = new java.io.File
            r1.<init>(r0)
            boolean r0 = r1.exists()
            if (r0 != 0) goto L_0x0018
            return
        L_0x0018:
            r1.lastModified()
            r1.length()
            r2 = 0
            r8 = 2
            r9 = 0
            r10 = 1
            java.io.BufferedReader r11 = new java.io.BufferedReader     // Catch:{ Exception -> 0x0178 }
            java.io.FileReader r0 = new java.io.FileReader     // Catch:{ Exception -> 0x0178 }
            r0.<init>(r1)     // Catch:{ Exception -> 0x0178 }
            r11.<init>(r0)     // Catch:{ Exception -> 0x0178 }
            java.lang.String r0 = "-{5}\\spid\\s\\d+\\sat\\s\\d+-\\d+-\\d+\\s\\d{2}:\\d{2}:\\d{2}\\s-{5}"
            java.util.regex.Pattern r12 = java.util.regex.Pattern.compile(r0)     // Catch:{ Exception -> 0x0172, all -> 0x016e }
            java.lang.String r0 = "-{5}\\send\\s\\d+\\s-{5}"
            java.util.regex.Pattern r13 = java.util.regex.Pattern.compile(r0)     // Catch:{ Exception -> 0x0172, all -> 0x016e }
            java.lang.String r0 = "Cmd\\sline:\\s(\\S+)"
            java.util.regex.Pattern r14 = java.util.regex.Pattern.compile(r0)     // Catch:{ Exception -> 0x0172, all -> 0x016e }
            java.lang.String r0 = "\".+\"\\s(daemon\\s){0,1}prio=\\d+\\stid=\\d+\\s.*"
            java.util.regex.Pattern r15 = java.util.regex.Pattern.compile(r0)     // Catch:{ Exception -> 0x0172, all -> 0x016e }
            java.text.SimpleDateFormat r5 = new java.text.SimpleDateFormat     // Catch:{ Exception -> 0x0172, all -> 0x016e }
            java.lang.String r0 = "yyyy-MM-dd HH:mm:ss"
            java.util.Locale r1 = java.util.Locale.US     // Catch:{ Exception -> 0x0172, all -> 0x016e }
            r5.<init>(r0, r1)     // Catch:{ Exception -> 0x0172, all -> 0x016e }
        L_0x004d:
            java.util.regex.Pattern[] r0 = new java.util.regex.Pattern[r10]     // Catch:{ Exception -> 0x0172, all -> 0x016e }
            r0[r9] = r12     // Catch:{ Exception -> 0x0172, all -> 0x016e }
            java.lang.Object[] r0 = a(r11, r0)     // Catch:{ Exception -> 0x0172, all -> 0x016e }
            if (r0 == 0) goto L_0x015e
            r0 = r0[r10]     // Catch:{ Exception -> 0x0172, all -> 0x016e }
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x0172, all -> 0x016e }
            java.lang.String[] r0 = r0.split(r7)     // Catch:{ Exception -> 0x0172, all -> 0x016e }
            r1 = r0[r8]     // Catch:{ Exception -> 0x0172, all -> 0x016e }
            long r1 = java.lang.Long.parseLong(r1)     // Catch:{ Exception -> 0x0172, all -> 0x016e }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0172, all -> 0x016e }
            r3.<init>()     // Catch:{ Exception -> 0x0172, all -> 0x016e }
            r4 = 4
            r4 = r0[r4]     // Catch:{ Exception -> 0x0172, all -> 0x016e }
            r3.append(r4)     // Catch:{ Exception -> 0x0172, all -> 0x016e }
            java.lang.String r4 = " "
            r3.append(r4)     // Catch:{ Exception -> 0x0172, all -> 0x016e }
            r4 = 5
            r0 = r0[r4]     // Catch:{ Exception -> 0x0172, all -> 0x016e }
            r3.append(r0)     // Catch:{ Exception -> 0x0172, all -> 0x016e }
            java.lang.String r0 = r3.toString()     // Catch:{ Exception -> 0x0172, all -> 0x016e }
            java.util.Date r0 = r5.parse(r0)     // Catch:{ Exception -> 0x0172, all -> 0x016e }
            long r3 = r0.getTime()     // Catch:{ Exception -> 0x0172, all -> 0x016e }
            java.util.regex.Pattern[] r0 = new java.util.regex.Pattern[r10]     // Catch:{ Exception -> 0x0172, all -> 0x016e }
            r0[r9] = r14     // Catch:{ Exception -> 0x0172, all -> 0x016e }
            java.lang.Object[] r0 = a(r11, r0)     // Catch:{ Exception -> 0x0172, all -> 0x016e }
            if (r0 != 0) goto L_0x00a3
            r11.close()     // Catch:{ IOException -> 0x0097 }
            return
        L_0x0097:
            r0 = move-exception
            r1 = r0
            boolean r0 = com.tencent.bugly.proguard.x.a(r1)
            if (r0 != 0) goto L_0x00a2
            r1.printStackTrace()
        L_0x00a2:
            return
        L_0x00a3:
            r0 = r0[r10]     // Catch:{ Exception -> 0x0172, all -> 0x016e }
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x0172, all -> 0x016e }
            java.util.regex.Matcher r0 = r14.matcher(r0)     // Catch:{ Exception -> 0x0172, all -> 0x016e }
            r0.find()     // Catch:{ Exception -> 0x0172, all -> 0x016e }
            r0.group(r10)     // Catch:{ Exception -> 0x0172, all -> 0x016e }
            java.lang.String r16 = r0.group(r10)     // Catch:{ Exception -> 0x0172, all -> 0x016e }
            r0 = r19
            r17 = r5
            r5 = r16
            boolean r0 = r0.a(r1, r3, r5)     // Catch:{ Exception -> 0x0172, all -> 0x016e }
            if (r0 != 0) goto L_0x00d3
            r11.close()     // Catch:{ IOException -> 0x00c7 }
            return
        L_0x00c7:
            r0 = move-exception
            r1 = r0
            boolean r0 = com.tencent.bugly.proguard.x.a(r1)
            if (r0 != 0) goto L_0x00d2
            r1.printStackTrace()
        L_0x00d2:
            return
        L_0x00d3:
            java.util.regex.Pattern[] r0 = new java.util.regex.Pattern[r8]     // Catch:{ Exception -> 0x0172, all -> 0x016e }
            r0[r9] = r15     // Catch:{ Exception -> 0x0172, all -> 0x016e }
            r0[r10] = r13     // Catch:{ Exception -> 0x0172, all -> 0x016e }
            java.lang.Object[] r0 = a(r11, r0)     // Catch:{ Exception -> 0x0172, all -> 0x016e }
            if (r0 == 0) goto L_0x015a
            r1 = r0[r9]     // Catch:{ Exception -> 0x0172, all -> 0x016e }
            if (r1 != r15) goto L_0x0134
            r0 = r0[r10]     // Catch:{ Exception -> 0x0172, all -> 0x016e }
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x0172, all -> 0x016e }
            java.lang.String r1 = "\".+\""
            java.util.regex.Pattern r1 = java.util.regex.Pattern.compile(r1)     // Catch:{ Exception -> 0x0172, all -> 0x016e }
            java.util.regex.Matcher r1 = r1.matcher(r0)     // Catch:{ Exception -> 0x0172, all -> 0x016e }
            r1.find()     // Catch:{ Exception -> 0x0172, all -> 0x016e }
            java.lang.String r1 = r1.group()     // Catch:{ Exception -> 0x0172, all -> 0x016e }
            int r2 = r1.length()     // Catch:{ Exception -> 0x0172, all -> 0x016e }
            int r2 = r2 - r10
            java.lang.String r1 = r1.substring(r10, r2)     // Catch:{ Exception -> 0x0172, all -> 0x016e }
            java.lang.String r2 = "NATIVE"
            r0.contains(r2)     // Catch:{ Exception -> 0x0172, all -> 0x016e }
            java.lang.String r2 = "tid=\\d+"
            java.util.regex.Pattern r2 = java.util.regex.Pattern.compile(r2)     // Catch:{ Exception -> 0x0172, all -> 0x016e }
            java.util.regex.Matcher r0 = r2.matcher(r0)     // Catch:{ Exception -> 0x0172, all -> 0x016e }
            r0.find()     // Catch:{ Exception -> 0x0172, all -> 0x016e }
            java.lang.String r0 = r0.group()     // Catch:{ Exception -> 0x0172, all -> 0x016e }
            java.lang.String r2 = "="
            int r2 = r0.indexOf(r2)     // Catch:{ Exception -> 0x0172, all -> 0x016e }
            int r2 = r2 + r10
            java.lang.String r0 = r0.substring(r2)     // Catch:{ Exception -> 0x0172, all -> 0x016e }
            int r0 = java.lang.Integer.parseInt(r0)     // Catch:{ Exception -> 0x0172, all -> 0x016e }
            java.lang.String r2 = a(r11)     // Catch:{ Exception -> 0x0172, all -> 0x016e }
            java.lang.String r3 = b(r11)     // Catch:{ Exception -> 0x0172, all -> 0x016e }
            r6.a(r1, r0, r2, r3)     // Catch:{ Exception -> 0x0172, all -> 0x016e }
            goto L_0x00d3
        L_0x0134:
            r0 = r0[r10]     // Catch:{ Exception -> 0x0172, all -> 0x016e }
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x0172, all -> 0x016e }
            java.lang.String[] r0 = r0.split(r7)     // Catch:{ Exception -> 0x0172, all -> 0x016e }
            r0 = r0[r8]     // Catch:{ Exception -> 0x0172, all -> 0x016e }
            long r0 = java.lang.Long.parseLong(r0)     // Catch:{ Exception -> 0x0172, all -> 0x016e }
            boolean r0 = r6.a(r0)     // Catch:{ Exception -> 0x0172, all -> 0x016e }
            if (r0 != 0) goto L_0x015a
            r11.close()     // Catch:{ IOException -> 0x014e }
            return
        L_0x014e:
            r0 = move-exception
            r1 = r0
            boolean r0 = com.tencent.bugly.proguard.x.a(r1)
            if (r0 != 0) goto L_0x0159
            r1.printStackTrace()
        L_0x0159:
            return
        L_0x015a:
            r5 = r17
            goto L_0x004d
        L_0x015e:
            r11.close()     // Catch:{ IOException -> 0x0162 }
            return
        L_0x0162:
            r0 = move-exception
            r1 = r0
            boolean r0 = com.tencent.bugly.proguard.x.a(r1)
            if (r0 != 0) goto L_0x016d
            r1.printStackTrace()
        L_0x016d:
            return
        L_0x016e:
            r0 = move-exception
            r1 = r0
            r2 = r11
            goto L_0x01b7
        L_0x0172:
            r0 = move-exception
            r2 = r11
            goto L_0x0179
        L_0x0175:
            r0 = move-exception
            r1 = r0
            goto L_0x01b7
        L_0x0178:
            r0 = move-exception
        L_0x0179:
            boolean r1 = com.tencent.bugly.proguard.x.a(r0)     // Catch:{ all -> 0x0175 }
            if (r1 != 0) goto L_0x0182
            r0.printStackTrace()     // Catch:{ all -> 0x0175 }
        L_0x0182:
            java.lang.String r1 = "trace open fail:%s : %s"
            java.lang.Object[] r3 = new java.lang.Object[r8]     // Catch:{ all -> 0x0175 }
            java.lang.Class r4 = r0.getClass()     // Catch:{ all -> 0x0175 }
            java.lang.String r4 = r4.getName()     // Catch:{ all -> 0x0175 }
            r3[r9] = r4     // Catch:{ all -> 0x0175 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0175 }
            r4.<init>()     // Catch:{ all -> 0x0175 }
            java.lang.String r0 = r0.getMessage()     // Catch:{ all -> 0x0175 }
            r4.append(r0)     // Catch:{ all -> 0x0175 }
            java.lang.String r0 = r4.toString()     // Catch:{ all -> 0x0175 }
            r3[r10] = r0     // Catch:{ all -> 0x0175 }
            com.tencent.bugly.proguard.x.d(r1, r3)     // Catch:{ all -> 0x0175 }
            if (r2 == 0) goto L_0x01b6
            r2.close()     // Catch:{ IOException -> 0x01ab }
            return
        L_0x01ab:
            r0 = move-exception
            r1 = r0
            boolean r0 = com.tencent.bugly.proguard.x.a(r1)
            if (r0 != 0) goto L_0x01b6
            r1.printStackTrace()
        L_0x01b6:
            return
        L_0x01b7:
            if (r2 == 0) goto L_0x01c8
            r2.close()     // Catch:{ IOException -> 0x01bd }
            goto L_0x01c8
        L_0x01bd:
            r0 = move-exception
            r2 = r0
            boolean r0 = com.tencent.bugly.proguard.x.a(r2)
            if (r0 != 0) goto L_0x01c8
            r2.printStackTrace()
        L_0x01c8:
            throw r1
        L_0x01c9:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.crash.anr.TraceFileHelper.readTraceFile(java.lang.String, com.tencent.bugly.crashreport.crash.anr.TraceFileHelper$b):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0025, code lost:
        return new java.lang.Object[]{r5, r1};
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0026, code lost:
        r4 = r4 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0003, code lost:
        if (r8 != null) goto L_0x0006;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0006, code lost:
        r1 = r7.readLine();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:4:0x000a, code lost:
        if (r1 == null) goto L_0x0029;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x000c, code lost:
        r2 = r8.length;
        r4 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x000f, code lost:
        if (r4 >= r2) goto L_0x0006;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0011, code lost:
        r5 = r8[r4];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x001b, code lost:
        if (r5.matcher(r1).matches() == false) goto L_0x0026;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.Object[] a(java.io.BufferedReader r7, java.util.regex.Pattern... r8) throws java.io.IOException {
        /*
            r0 = 0
            if (r7 == 0) goto L_0x0029
            if (r8 != 0) goto L_0x0006
            goto L_0x0029
        L_0x0006:
            java.lang.String r1 = r7.readLine()
            if (r1 == 0) goto L_0x0029
            int r2 = r8.length
            r3 = 0
            r4 = 0
        L_0x000f:
            if (r4 >= r2) goto L_0x0006
            r5 = r8[r4]
            java.util.regex.Matcher r6 = r5.matcher(r1)
            boolean r6 = r6.matches()
            if (r6 == 0) goto L_0x0026
            r7 = 2
            java.lang.Object[] r7 = new java.lang.Object[r7]
            r7[r3] = r5
            r8 = 1
            r7[r8] = r1
            return r7
        L_0x0026:
            int r4 = r4 + 1
            goto L_0x000f
        L_0x0029:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.crash.anr.TraceFileHelper.a(java.io.BufferedReader, java.util.regex.Pattern[]):java.lang.Object[]");
    }

    private static String a(BufferedReader bufferedReader) throws IOException {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < 3; i++) {
            String readLine = bufferedReader.readLine();
            if (readLine == null) {
                return null;
            }
            stringBuffer.append(readLine + StringUtils.LF);
        }
        return stringBuffer.toString();
    }

    private static String b(BufferedReader bufferedReader) throws IOException {
        StringBuffer stringBuffer = new StringBuffer();
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine != null && readLine.trim().length() > 0) {
                stringBuffer.append(readLine + StringUtils.LF);
            }
        }
        return stringBuffer.toString();
    }
}
