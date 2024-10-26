package com.ciot.base.util;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\u0018\u0000 \u00032\u00020\u0001:\u0002\u0003\u0004B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0005"}, d2 = {"Lcom/ciot/base/util/ExeShellUtils;", "", "()V", "Companion", "ExecRootCmdListener", "library-base_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: ExeShellUtils.kt */
public final class ExeShellUtils {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/ciot/base/util/ExeShellUtils$ExecRootCmdListener;", "", "onProcess", "", "str", "", "library-base_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* compiled from: ExeShellUtils.kt */
    public interface ExecRootCmdListener {
        void onProcess(String str);
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0016J\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0016J\u001a\u0010\u0007\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tH\u0016¨\u0006\n"}, d2 = {"Lcom/ciot/base/util/ExeShellUtils$Companion;", "", "()V", "execRootCmd", "", "cmd", "execRootCmdOnMount", "execRootCmdOnMountForResult", "listener", "Lcom/ciot/base/util/ExeShellUtils$ExecRootCmdListener;", "library-base_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* compiled from: ExeShellUtils.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX WARNING: Removed duplicated region for block: B:47:0x00bd A[SYNTHETIC, Splitter:B:47:0x00bd] */
        /* JADX WARNING: Removed duplicated region for block: B:52:0x00c7 A[SYNTHETIC, Splitter:B:52:0x00c7] */
        /* JADX WARNING: Removed duplicated region for block: B:57:0x00d1 A[SYNTHETIC, Splitter:B:57:0x00d1] */
        /* JADX WARNING: Removed duplicated region for block: B:62:0x00d8 A[SYNTHETIC, Splitter:B:62:0x00d8] */
        /* JADX WARNING: Removed duplicated region for block: B:67:0x00e2 A[SYNTHETIC, Splitter:B:67:0x00e2] */
        /* JADX WARNING: Removed duplicated region for block: B:72:0x00ec A[SYNTHETIC, Splitter:B:72:0x00ec] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.String execRootCmd(java.lang.String r8) {
            /*
                r7 = this;
                java.lang.String r0 = "cmd"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r0)
                java.lang.String r0 = ""
                r1 = 0
                java.lang.Runtime r2 = java.lang.Runtime.getRuntime()     // Catch:{ Exception -> 0x00b5, all -> 0x00b1 }
                java.lang.String r3 = "su"
                java.lang.Process r2 = r2.exec(r3)     // Catch:{ Exception -> 0x00b5, all -> 0x00b1 }
                java.io.DataOutputStream r3 = new java.io.DataOutputStream     // Catch:{ Exception -> 0x00b5, all -> 0x00b1 }
                java.io.OutputStream r4 = r2.getOutputStream()     // Catch:{ Exception -> 0x00b5, all -> 0x00b1 }
                r3.<init>(r4)     // Catch:{ Exception -> 0x00b5, all -> 0x00b1 }
                java.io.DataInputStream r4 = new java.io.DataInputStream     // Catch:{ Exception -> 0x00ac, all -> 0x00a7 }
                java.io.InputStream r5 = r2.getInputStream()     // Catch:{ Exception -> 0x00ac, all -> 0x00a7 }
                r4.<init>(r5)     // Catch:{ Exception -> 0x00ac, all -> 0x00a7 }
                java.io.DataInputStream r5 = new java.io.DataInputStream     // Catch:{ Exception -> 0x00a4, all -> 0x00a1 }
                java.io.InputStream r6 = r2.getErrorStream()     // Catch:{ Exception -> 0x00a4, all -> 0x00a1 }
                r5.<init>(r6)     // Catch:{ Exception -> 0x00a4, all -> 0x00a1 }
                java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x009f, all -> 0x009d }
                r1.<init>()     // Catch:{ Exception -> 0x009f, all -> 0x009d }
                java.lang.String r6 = "\n                    "
                r1.append(r6)     // Catch:{ Exception -> 0x009f, all -> 0x009d }
                r1.append(r8)     // Catch:{ Exception -> 0x009f, all -> 0x009d }
                java.lang.String r8 = "\n                    \n                    "
                r1.append(r8)     // Catch:{ Exception -> 0x009f, all -> 0x009d }
                java.lang.String r8 = r1.toString()     // Catch:{ Exception -> 0x009f, all -> 0x009d }
                java.lang.String r8 = kotlin.text.StringsKt.trimIndent(r8)     // Catch:{ Exception -> 0x009f, all -> 0x009d }
                r3.writeBytes(r8)     // Catch:{ Exception -> 0x009f, all -> 0x009d }
                r3.flush()     // Catch:{ Exception -> 0x009f, all -> 0x009d }
                java.lang.String r8 = "exit\n"
                r3.writeBytes(r8)     // Catch:{ Exception -> 0x009f, all -> 0x009d }
                r3.flush()     // Catch:{ Exception -> 0x009f, all -> 0x009d }
            L_0x0055:
                java.lang.String r8 = r4.readLine()     // Catch:{ Exception -> 0x009f, all -> 0x009d }
                if (r8 == 0) goto L_0x006b
                java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x009f, all -> 0x009d }
                r1.<init>()     // Catch:{ Exception -> 0x009f, all -> 0x009d }
                r1.append(r0)     // Catch:{ Exception -> 0x009f, all -> 0x009d }
                r1.append(r8)     // Catch:{ Exception -> 0x009f, all -> 0x009d }
                java.lang.String r0 = r1.toString()     // Catch:{ Exception -> 0x009f, all -> 0x009d }
                goto L_0x0055
            L_0x006b:
                java.lang.String r8 = r5.readLine()     // Catch:{ Exception -> 0x009f, all -> 0x009d }
                if (r8 == 0) goto L_0x0081
                java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x009f, all -> 0x009d }
                r1.<init>()     // Catch:{ Exception -> 0x009f, all -> 0x009d }
                r1.append(r0)     // Catch:{ Exception -> 0x009f, all -> 0x009d }
                r1.append(r8)     // Catch:{ Exception -> 0x009f, all -> 0x009d }
                java.lang.String r0 = r1.toString()     // Catch:{ Exception -> 0x009f, all -> 0x009d }
                goto L_0x006b
            L_0x0081:
                r2.waitFor()     // Catch:{ Exception -> 0x009f, all -> 0x009d }
                r3.close()     // Catch:{ IOException -> 0x0088 }
                goto L_0x008c
            L_0x0088:
                r8 = move-exception
                r8.printStackTrace()
            L_0x008c:
                r4.close()     // Catch:{ IOException -> 0x0090 }
                goto L_0x0094
            L_0x0090:
                r8 = move-exception
                r8.printStackTrace()
            L_0x0094:
                r5.close()     // Catch:{ IOException -> 0x0098 }
                goto L_0x00d4
            L_0x0098:
                r8 = move-exception
                r8.printStackTrace()
                goto L_0x00d4
            L_0x009d:
                r8 = move-exception
                goto L_0x00aa
            L_0x009f:
                r8 = move-exception
                goto L_0x00af
            L_0x00a1:
                r8 = move-exception
                r5 = r1
                goto L_0x00aa
            L_0x00a4:
                r8 = move-exception
                r5 = r1
                goto L_0x00af
            L_0x00a7:
                r8 = move-exception
                r4 = r1
                r5 = r4
            L_0x00aa:
                r1 = r3
                goto L_0x00d6
            L_0x00ac:
                r8 = move-exception
                r4 = r1
                r5 = r4
            L_0x00af:
                r1 = r3
                goto L_0x00b8
            L_0x00b1:
                r8 = move-exception
                r4 = r1
                r5 = r4
                goto L_0x00d6
            L_0x00b5:
                r8 = move-exception
                r4 = r1
                r5 = r4
            L_0x00b8:
                r8.printStackTrace()     // Catch:{ all -> 0x00d5 }
                if (r1 == 0) goto L_0x00c5
                r1.close()     // Catch:{ IOException -> 0x00c1 }
                goto L_0x00c5
            L_0x00c1:
                r8 = move-exception
                r8.printStackTrace()
            L_0x00c5:
                if (r4 == 0) goto L_0x00cf
                r4.close()     // Catch:{ IOException -> 0x00cb }
                goto L_0x00cf
            L_0x00cb:
                r8 = move-exception
                r8.printStackTrace()
            L_0x00cf:
                if (r5 == 0) goto L_0x00d4
                r5.close()     // Catch:{ IOException -> 0x0098 }
            L_0x00d4:
                return r0
            L_0x00d5:
                r8 = move-exception
            L_0x00d6:
                if (r1 == 0) goto L_0x00e0
                r1.close()     // Catch:{ IOException -> 0x00dc }
                goto L_0x00e0
            L_0x00dc:
                r0 = move-exception
                r0.printStackTrace()
            L_0x00e0:
                if (r4 == 0) goto L_0x00ea
                r4.close()     // Catch:{ IOException -> 0x00e6 }
                goto L_0x00ea
            L_0x00e6:
                r0 = move-exception
                r0.printStackTrace()
            L_0x00ea:
                if (r5 == 0) goto L_0x00f4
                r5.close()     // Catch:{ IOException -> 0x00f0 }
                goto L_0x00f4
            L_0x00f0:
                r0 = move-exception
                r0.printStackTrace()
            L_0x00f4:
                throw r8
            */
            throw new UnsupportedOperationException("Method not decompiled: com.ciot.base.util.ExeShellUtils.Companion.execRootCmd(java.lang.String):java.lang.String");
        }

        /* JADX WARNING: Removed duplicated region for block: B:48:0x00bc A[SYNTHETIC, Splitter:B:48:0x00bc] */
        /* JADX WARNING: Removed duplicated region for block: B:53:0x00c6 A[SYNTHETIC, Splitter:B:53:0x00c6] */
        /* JADX WARNING: Removed duplicated region for block: B:58:0x00d0 A[SYNTHETIC, Splitter:B:58:0x00d0] */
        /* JADX WARNING: Removed duplicated region for block: B:63:0x00d7 A[SYNTHETIC, Splitter:B:63:0x00d7] */
        /* JADX WARNING: Removed duplicated region for block: B:68:0x00e1 A[SYNTHETIC, Splitter:B:68:0x00e1] */
        /* JADX WARNING: Removed duplicated region for block: B:73:0x00eb A[SYNTHETIC, Splitter:B:73:0x00eb] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.String execRootCmdOnMount(java.lang.String r8) {
            /*
                r7 = this;
                java.lang.String r0 = "cmd"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r0)
                java.lang.String r0 = ""
                r1 = 0
                java.lang.Runtime r2 = java.lang.Runtime.getRuntime()     // Catch:{ Exception -> 0x00b4, all -> 0x00b0 }
                java.lang.String r3 = "su"
                java.lang.Process r2 = r2.exec(r3)     // Catch:{ Exception -> 0x00b4, all -> 0x00b0 }
                java.io.DataOutputStream r3 = new java.io.DataOutputStream     // Catch:{ Exception -> 0x00b4, all -> 0x00b0 }
                java.io.OutputStream r4 = r2.getOutputStream()     // Catch:{ Exception -> 0x00b4, all -> 0x00b0 }
                r3.<init>(r4)     // Catch:{ Exception -> 0x00b4, all -> 0x00b0 }
                java.io.DataInputStream r4 = new java.io.DataInputStream     // Catch:{ Exception -> 0x00ab, all -> 0x00a6 }
                java.io.InputStream r5 = r2.getInputStream()     // Catch:{ Exception -> 0x00ab, all -> 0x00a6 }
                r4.<init>(r5)     // Catch:{ Exception -> 0x00ab, all -> 0x00a6 }
                java.io.DataInputStream r5 = new java.io.DataInputStream     // Catch:{ Exception -> 0x00a3, all -> 0x00a0 }
                java.io.InputStream r6 = r2.getErrorStream()     // Catch:{ Exception -> 0x00a3, all -> 0x00a0 }
                r5.<init>(r6)     // Catch:{ Exception -> 0x00a3, all -> 0x00a0 }
                java.lang.String r1 = "mount -o rw,remount /system \n"
                r3.writeBytes(r1)     // Catch:{ Exception -> 0x009e, all -> 0x009c }
                r3.flush()     // Catch:{ Exception -> 0x009e, all -> 0x009c }
                java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x009e, all -> 0x009c }
                r1.<init>()     // Catch:{ Exception -> 0x009e, all -> 0x009c }
                r1.append(r8)     // Catch:{ Exception -> 0x009e, all -> 0x009c }
                java.lang.String r8 = " \n"
                r1.append(r8)     // Catch:{ Exception -> 0x009e, all -> 0x009c }
                java.lang.String r8 = r1.toString()     // Catch:{ Exception -> 0x009e, all -> 0x009c }
                r3.writeBytes(r8)     // Catch:{ Exception -> 0x009e, all -> 0x009c }
                r3.flush()     // Catch:{ Exception -> 0x009e, all -> 0x009c }
                java.lang.String r8 = "exit\n"
                r3.writeBytes(r8)     // Catch:{ Exception -> 0x009e, all -> 0x009c }
                r3.flush()     // Catch:{ Exception -> 0x009e, all -> 0x009c }
            L_0x0054:
                java.lang.String r8 = r4.readLine()     // Catch:{ Exception -> 0x009e, all -> 0x009c }
                if (r8 == 0) goto L_0x006a
                java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x009e, all -> 0x009c }
                r1.<init>()     // Catch:{ Exception -> 0x009e, all -> 0x009c }
                r1.append(r0)     // Catch:{ Exception -> 0x009e, all -> 0x009c }
                r1.append(r8)     // Catch:{ Exception -> 0x009e, all -> 0x009c }
                java.lang.String r0 = r1.toString()     // Catch:{ Exception -> 0x009e, all -> 0x009c }
                goto L_0x0054
            L_0x006a:
                java.lang.String r8 = r5.readLine()     // Catch:{ Exception -> 0x009e, all -> 0x009c }
                if (r8 == 0) goto L_0x0080
                java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x009e, all -> 0x009c }
                r1.<init>()     // Catch:{ Exception -> 0x009e, all -> 0x009c }
                r1.append(r0)     // Catch:{ Exception -> 0x009e, all -> 0x009c }
                r1.append(r8)     // Catch:{ Exception -> 0x009e, all -> 0x009c }
                java.lang.String r0 = r1.toString()     // Catch:{ Exception -> 0x009e, all -> 0x009c }
                goto L_0x006a
            L_0x0080:
                r2.waitFor()     // Catch:{ Exception -> 0x009e, all -> 0x009c }
                r3.close()     // Catch:{ IOException -> 0x0087 }
                goto L_0x008b
            L_0x0087:
                r8 = move-exception
                r8.printStackTrace()
            L_0x008b:
                r4.close()     // Catch:{ IOException -> 0x008f }
                goto L_0x0093
            L_0x008f:
                r8 = move-exception
                r8.printStackTrace()
            L_0x0093:
                r5.close()     // Catch:{ IOException -> 0x0097 }
                goto L_0x00d3
            L_0x0097:
                r8 = move-exception
                r8.printStackTrace()
                goto L_0x00d3
            L_0x009c:
                r8 = move-exception
                goto L_0x00a9
            L_0x009e:
                r8 = move-exception
                goto L_0x00ae
            L_0x00a0:
                r8 = move-exception
                r5 = r1
                goto L_0x00a9
            L_0x00a3:
                r8 = move-exception
                r5 = r1
                goto L_0x00ae
            L_0x00a6:
                r8 = move-exception
                r4 = r1
                r5 = r4
            L_0x00a9:
                r1 = r3
                goto L_0x00d5
            L_0x00ab:
                r8 = move-exception
                r4 = r1
                r5 = r4
            L_0x00ae:
                r1 = r3
                goto L_0x00b7
            L_0x00b0:
                r8 = move-exception
                r4 = r1
                r5 = r4
                goto L_0x00d5
            L_0x00b4:
                r8 = move-exception
                r4 = r1
                r5 = r4
            L_0x00b7:
                r8.printStackTrace()     // Catch:{ all -> 0x00d4 }
                if (r1 == 0) goto L_0x00c4
                r1.close()     // Catch:{ IOException -> 0x00c0 }
                goto L_0x00c4
            L_0x00c0:
                r8 = move-exception
                r8.printStackTrace()
            L_0x00c4:
                if (r4 == 0) goto L_0x00ce
                r4.close()     // Catch:{ IOException -> 0x00ca }
                goto L_0x00ce
            L_0x00ca:
                r8 = move-exception
                r8.printStackTrace()
            L_0x00ce:
                if (r5 == 0) goto L_0x00d3
                r5.close()     // Catch:{ IOException -> 0x0097 }
            L_0x00d3:
                return r0
            L_0x00d4:
                r8 = move-exception
            L_0x00d5:
                if (r1 == 0) goto L_0x00df
                r1.close()     // Catch:{ IOException -> 0x00db }
                goto L_0x00df
            L_0x00db:
                r0 = move-exception
                r0.printStackTrace()
            L_0x00df:
                if (r4 == 0) goto L_0x00e9
                r4.close()     // Catch:{ IOException -> 0x00e5 }
                goto L_0x00e9
            L_0x00e5:
                r0 = move-exception
                r0.printStackTrace()
            L_0x00e9:
                if (r5 == 0) goto L_0x00f3
                r5.close()     // Catch:{ IOException -> 0x00ef }
                goto L_0x00f3
            L_0x00ef:
                r0 = move-exception
                r0.printStackTrace()
            L_0x00f3:
                throw r8
            */
            throw new UnsupportedOperationException("Method not decompiled: com.ciot.base.util.ExeShellUtils.Companion.execRootCmdOnMount(java.lang.String):java.lang.String");
        }

        /* JADX WARNING: Removed duplicated region for block: B:52:0x00cb A[SYNTHETIC, Splitter:B:52:0x00cb] */
        /* JADX WARNING: Removed duplicated region for block: B:57:0x00d5 A[SYNTHETIC, Splitter:B:57:0x00d5] */
        /* JADX WARNING: Removed duplicated region for block: B:62:0x00df A[SYNTHETIC, Splitter:B:62:0x00df] */
        /* JADX WARNING: Removed duplicated region for block: B:67:0x00e6 A[SYNTHETIC, Splitter:B:67:0x00e6] */
        /* JADX WARNING: Removed duplicated region for block: B:72:0x00f0 A[SYNTHETIC, Splitter:B:72:0x00f0] */
        /* JADX WARNING: Removed duplicated region for block: B:77:0x00fa A[SYNTHETIC, Splitter:B:77:0x00fa] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.String execRootCmdOnMountForResult(java.lang.String r8, com.ciot.base.util.ExeShellUtils.ExecRootCmdListener r9) {
            /*
                r7 = this;
                java.lang.String r0 = "cmd"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r0)
                java.lang.String r0 = "listener"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r9, r0)
                java.lang.String r0 = ""
                r1 = 0
                java.lang.Runtime r2 = java.lang.Runtime.getRuntime()     // Catch:{ Exception -> 0x00c3, all -> 0x00bf }
                java.lang.String r3 = "su"
                java.lang.Process r2 = r2.exec(r3)     // Catch:{ Exception -> 0x00c3, all -> 0x00bf }
                java.io.DataOutputStream r3 = new java.io.DataOutputStream     // Catch:{ Exception -> 0x00c3, all -> 0x00bf }
                java.io.OutputStream r4 = r2.getOutputStream()     // Catch:{ Exception -> 0x00c3, all -> 0x00bf }
                r3.<init>(r4)     // Catch:{ Exception -> 0x00c3, all -> 0x00bf }
                java.io.DataInputStream r4 = new java.io.DataInputStream     // Catch:{ Exception -> 0x00ba, all -> 0x00b5 }
                java.io.InputStream r5 = r2.getInputStream()     // Catch:{ Exception -> 0x00ba, all -> 0x00b5 }
                r4.<init>(r5)     // Catch:{ Exception -> 0x00ba, all -> 0x00b5 }
                java.io.DataInputStream r5 = new java.io.DataInputStream     // Catch:{ Exception -> 0x00b2, all -> 0x00af }
                java.io.InputStream r6 = r2.getErrorStream()     // Catch:{ Exception -> 0x00b2, all -> 0x00af }
                r5.<init>(r6)     // Catch:{ Exception -> 0x00b2, all -> 0x00af }
                java.lang.String r1 = "mount -o rw,remount /system \n"
                r3.writeBytes(r1)     // Catch:{ Exception -> 0x00ad, all -> 0x00ab }
                r3.flush()     // Catch:{ Exception -> 0x00ad, all -> 0x00ab }
                java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00ad, all -> 0x00ab }
                r1.<init>()     // Catch:{ Exception -> 0x00ad, all -> 0x00ab }
                r1.append(r8)     // Catch:{ Exception -> 0x00ad, all -> 0x00ab }
                java.lang.String r8 = " \n"
                r1.append(r8)     // Catch:{ Exception -> 0x00ad, all -> 0x00ab }
                java.lang.String r8 = r1.toString()     // Catch:{ Exception -> 0x00ad, all -> 0x00ab }
                r3.writeBytes(r8)     // Catch:{ Exception -> 0x00ad, all -> 0x00ab }
                r3.flush()     // Catch:{ Exception -> 0x00ad, all -> 0x00ab }
                java.lang.String r8 = "exit\n"
                r3.writeBytes(r8)     // Catch:{ Exception -> 0x00ad, all -> 0x00ab }
                r3.flush()     // Catch:{ Exception -> 0x00ad, all -> 0x00ab }
            L_0x0059:
                java.lang.String r8 = r4.readLine()     // Catch:{ Exception -> 0x00ad, all -> 0x00ab }
                if (r8 == 0) goto L_0x0074
                java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00ad, all -> 0x00ab }
                r1.<init>()     // Catch:{ Exception -> 0x00ad, all -> 0x00ab }
                r1.append(r0)     // Catch:{ Exception -> 0x00ad, all -> 0x00ab }
                r1.append(r8)     // Catch:{ Exception -> 0x00ad, all -> 0x00ab }
                java.lang.String r0 = r1.toString()     // Catch:{ Exception -> 0x00ad, all -> 0x00ab }
                if (r8 == 0) goto L_0x0059
                r9.onProcess(r8)     // Catch:{ Exception -> 0x00ad, all -> 0x00ab }
                goto L_0x0059
            L_0x0074:
                java.lang.String r8 = r5.readLine()     // Catch:{ Exception -> 0x00ad, all -> 0x00ab }
                if (r8 == 0) goto L_0x008f
                java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00ad, all -> 0x00ab }
                r1.<init>()     // Catch:{ Exception -> 0x00ad, all -> 0x00ab }
                r1.append(r0)     // Catch:{ Exception -> 0x00ad, all -> 0x00ab }
                r1.append(r8)     // Catch:{ Exception -> 0x00ad, all -> 0x00ab }
                java.lang.String r0 = r1.toString()     // Catch:{ Exception -> 0x00ad, all -> 0x00ab }
                if (r8 == 0) goto L_0x0074
                r9.onProcess(r8)     // Catch:{ Exception -> 0x00ad, all -> 0x00ab }
                goto L_0x0074
            L_0x008f:
                r2.waitFor()     // Catch:{ Exception -> 0x00ad, all -> 0x00ab }
                r3.close()     // Catch:{ IOException -> 0x0096 }
                goto L_0x009a
            L_0x0096:
                r8 = move-exception
                r8.printStackTrace()
            L_0x009a:
                r4.close()     // Catch:{ IOException -> 0x009e }
                goto L_0x00a2
            L_0x009e:
                r8 = move-exception
                r8.printStackTrace()
            L_0x00a2:
                r5.close()     // Catch:{ IOException -> 0x00a6 }
                goto L_0x00e2
            L_0x00a6:
                r8 = move-exception
                r8.printStackTrace()
                goto L_0x00e2
            L_0x00ab:
                r8 = move-exception
                goto L_0x00b8
            L_0x00ad:
                r8 = move-exception
                goto L_0x00bd
            L_0x00af:
                r8 = move-exception
                r5 = r1
                goto L_0x00b8
            L_0x00b2:
                r8 = move-exception
                r5 = r1
                goto L_0x00bd
            L_0x00b5:
                r8 = move-exception
                r4 = r1
                r5 = r4
            L_0x00b8:
                r1 = r3
                goto L_0x00e4
            L_0x00ba:
                r8 = move-exception
                r4 = r1
                r5 = r4
            L_0x00bd:
                r1 = r3
                goto L_0x00c6
            L_0x00bf:
                r8 = move-exception
                r4 = r1
                r5 = r4
                goto L_0x00e4
            L_0x00c3:
                r8 = move-exception
                r4 = r1
                r5 = r4
            L_0x00c6:
                r8.printStackTrace()     // Catch:{ all -> 0x00e3 }
                if (r1 == 0) goto L_0x00d3
                r1.close()     // Catch:{ IOException -> 0x00cf }
                goto L_0x00d3
            L_0x00cf:
                r8 = move-exception
                r8.printStackTrace()
            L_0x00d3:
                if (r4 == 0) goto L_0x00dd
                r4.close()     // Catch:{ IOException -> 0x00d9 }
                goto L_0x00dd
            L_0x00d9:
                r8 = move-exception
                r8.printStackTrace()
            L_0x00dd:
                if (r5 == 0) goto L_0x00e2
                r5.close()     // Catch:{ IOException -> 0x00a6 }
            L_0x00e2:
                return r0
            L_0x00e3:
                r8 = move-exception
            L_0x00e4:
                if (r1 == 0) goto L_0x00ee
                r1.close()     // Catch:{ IOException -> 0x00ea }
                goto L_0x00ee
            L_0x00ea:
                r9 = move-exception
                r9.printStackTrace()
            L_0x00ee:
                if (r4 == 0) goto L_0x00f8
                r4.close()     // Catch:{ IOException -> 0x00f4 }
                goto L_0x00f8
            L_0x00f4:
                r9 = move-exception
                r9.printStackTrace()
            L_0x00f8:
                if (r5 == 0) goto L_0x0102
                r5.close()     // Catch:{ IOException -> 0x00fe }
                goto L_0x0102
            L_0x00fe:
                r9 = move-exception
                r9.printStackTrace()
            L_0x0102:
                throw r8
            */
            throw new UnsupportedOperationException("Method not decompiled: com.ciot.base.util.ExeShellUtils.Companion.execRootCmdOnMountForResult(java.lang.String, com.ciot.base.util.ExeShellUtils$ExecRootCmdListener):java.lang.String");
        }
    }
}
