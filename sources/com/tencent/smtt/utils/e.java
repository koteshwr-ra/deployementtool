package com.tencent.smtt.utils;

import android.util.Log;
import com.google.common.base.Ascii;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.UnknownFormatConversionException;

public class e implements Closeable {
    static final char[] a = {Ascii.MAX, 'E', 'L', 'F', 0};
    final char[] b = new char[16];
    boolean c;
    j[] d;
    l[] e;
    byte[] f;
    private final c g;
    private final a h;
    private final k[] i;
    private byte[] j;

    public static abstract class a {
        short a;
        short b;
        int c;
        int d;
        short e;
        short f;
        short g;
        short h;
        short i;
        short j;

        /* access modifiers changed from: package-private */
        public abstract long a();

        /* access modifiers changed from: package-private */
        public abstract long b();
    }

    static class b extends a {
        int k;
        int l;
        int m;

        b() {
        }

        /* access modifiers changed from: package-private */
        public long a() {
            return (long) this.m;
        }

        /* access modifiers changed from: package-private */
        public long b() {
            return (long) this.l;
        }
    }

    static class c extends j {
        int a;
        int b;
        int c;
        int d;
        int e;
        int f;

        c() {
        }
    }

    static class d extends k {
        int a;
        int b;
        int c;
        int d;
        int e;
        int f;

        d() {
        }

        public int a() {
            return this.d;
        }

        public long b() {
            return (long) this.c;
        }
    }

    /* renamed from: com.tencent.smtt.utils.e$e  reason: collision with other inner class name */
    static class C0001e extends l {
        int a;
        int b;

        C0001e() {
        }
    }

    static class f extends a {
        long k;
        long l;
        long m;

        f() {
        }

        /* access modifiers changed from: package-private */
        public long a() {
            return this.m;
        }

        /* access modifiers changed from: package-private */
        public long b() {
            return this.l;
        }
    }

    static class g extends j {
        long a;
        long b;
        long c;
        long d;
        long e;
        long f;

        g() {
        }
    }

    static class h extends k {
        long a;
        long b;
        long c;
        long d;
        long e;
        long f;

        h() {
        }

        public int a() {
            return (int) this.d;
        }

        public long b() {
            return this.c;
        }
    }

    static class i extends l {
        long a;
        long b;

        i() {
        }
    }

    static abstract class j {
        int g;
        int h;

        j() {
        }
    }

    public static abstract class k {
        int g;
        int h;
        int i;
        int j;

        public abstract int a();

        public abstract long b();
    }

    static abstract class l {
        int c;
        char d;
        char e;
        short f;

        l() {
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v3, resolved type: com.tencent.smtt.utils.e$b} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v11, resolved type: com.tencent.smtt.utils.e$f} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v12, resolved type: com.tencent.smtt.utils.e$b} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v13, resolved type: com.tencent.smtt.utils.e$b} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public e(java.io.File r8) throws java.io.IOException, java.util.UnknownFormatConversionException {
        /*
            r7 = this;
            r7.<init>()
            r0 = 16
            char[] r0 = new char[r0]
            r7.b = r0
            com.tencent.smtt.utils.c r0 = new com.tencent.smtt.utils.c
            r0.<init>((java.io.File) r8)
            r7.g = r0
            char[] r1 = r7.b
            r0.a((char[]) r1)
            boolean r1 = r7.a()
            if (r1 == 0) goto L_0x01b5
            boolean r8 = r7.e()
            r0.a((boolean) r8)
            boolean r8 = r7.d()
            if (r8 == 0) goto L_0x0052
            com.tencent.smtt.utils.e$f r1 = new com.tencent.smtt.utils.e$f
            r1.<init>()
            short r2 = r0.a()
            r1.a = r2
            short r2 = r0.a()
            r1.b = r2
            int r2 = r0.b()
            r1.c = r2
            long r2 = r0.c()
            r1.k = r2
            long r2 = r0.c()
            r1.l = r2
            long r2 = r0.c()
            r1.m = r2
            goto L_0x007b
        L_0x0052:
            com.tencent.smtt.utils.e$b r1 = new com.tencent.smtt.utils.e$b
            r1.<init>()
            short r2 = r0.a()
            r1.a = r2
            short r2 = r0.a()
            r1.b = r2
            int r2 = r0.b()
            r1.c = r2
            int r2 = r0.b()
            r1.k = r2
            int r2 = r0.b()
            r1.l = r2
            int r2 = r0.b()
            r1.m = r2
        L_0x007b:
            r7.h = r1
            com.tencent.smtt.utils.e$a r1 = r7.h
            int r2 = r0.b()
            r1.d = r2
            short r2 = r0.a()
            r1.e = r2
            short r2 = r0.a()
            r1.f = r2
            short r2 = r0.a()
            r1.g = r2
            short r2 = r0.a()
            r1.h = r2
            short r2 = r0.a()
            r1.i = r2
            short r2 = r0.a()
            r1.j = r2
            short r2 = r1.i
            com.tencent.smtt.utils.e$k[] r2 = new com.tencent.smtt.utils.e.k[r2]
            r7.i = r2
            r2 = 0
        L_0x00b0:
            short r3 = r1.i
            if (r2 >= r3) goto L_0x0152
            long r3 = r1.a()
            short r5 = r1.h
            int r5 = r5 * r2
            long r5 = (long) r5
            long r3 = r3 + r5
            r0.a((long) r3)
            if (r8 == 0) goto L_0x0109
            com.tencent.smtt.utils.e$h r3 = new com.tencent.smtt.utils.e$h
            r3.<init>()
            int r4 = r0.b()
            r3.g = r4
            int r4 = r0.b()
            r3.h = r4
            long r4 = r0.c()
            r3.a = r4
            long r4 = r0.c()
            r3.b = r4
            long r4 = r0.c()
            r3.c = r4
            long r4 = r0.c()
            r3.d = r4
            int r4 = r0.b()
            r3.i = r4
            int r4 = r0.b()
            r3.j = r4
            long r4 = r0.c()
            r3.e = r4
            long r4 = r0.c()
            r3.f = r4
            com.tencent.smtt.utils.e$k[] r4 = r7.i
            r4[r2] = r3
            goto L_0x014e
        L_0x0109:
            com.tencent.smtt.utils.e$d r3 = new com.tencent.smtt.utils.e$d
            r3.<init>()
            int r4 = r0.b()
            r3.g = r4
            int r4 = r0.b()
            r3.h = r4
            int r4 = r0.b()
            r3.a = r4
            int r4 = r0.b()
            r3.b = r4
            int r4 = r0.b()
            r3.c = r4
            int r4 = r0.b()
            r3.d = r4
            int r4 = r0.b()
            r3.i = r4
            int r4 = r0.b()
            r3.j = r4
            int r4 = r0.b()
            r3.e = r4
            int r4 = r0.b()
            r3.f = r4
            com.tencent.smtt.utils.e$k[] r4 = r7.i
            r4[r2] = r3
        L_0x014e:
            int r2 = r2 + 1
            goto L_0x00b0
        L_0x0152:
            short r8 = r1.j
            r2 = -1
            if (r8 <= r2) goto L_0x019c
            short r8 = r1.j
            com.tencent.smtt.utils.e$k[] r2 = r7.i
            int r3 = r2.length
            if (r8 >= r3) goto L_0x019c
            short r8 = r1.j
            r8 = r2[r8]
            int r2 = r8.h
            r3 = 3
            if (r2 != r3) goto L_0x0183
            int r1 = r8.a()
            byte[] r1 = new byte[r1]
            r7.j = r1
            long r1 = r8.b()
            r0.a((long) r1)
            byte[] r8 = r7.j
            r0.a((byte[]) r8)
            boolean r8 = r7.c
            if (r8 == 0) goto L_0x0182
            r7.f()
        L_0x0182:
            return
        L_0x0183:
            java.util.UnknownFormatConversionException r8 = new java.util.UnknownFormatConversionException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r2 = "Wrong string section e_shstrndx="
            r0.append(r2)
            short r1 = r1.j
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r8.<init>(r0)
            throw r8
        L_0x019c:
            java.util.UnknownFormatConversionException r8 = new java.util.UnknownFormatConversionException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r2 = "Invalid e_shstrndx="
            r0.append(r2)
            short r1 = r1.j
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r8.<init>(r0)
            throw r8
        L_0x01b5:
            java.util.UnknownFormatConversionException r0 = new java.util.UnknownFormatConversionException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Invalid elf magic: "
            r1.append(r2)
            r1.append(r8)
            java.lang.String r8 = r1.toString()
            r0.<init>(r8)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.utils.e.<init>(java.io.File):void");
    }

    public static boolean a(File file) {
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
            long readInt = (long) randomAccessFile.readInt();
            randomAccessFile.close();
            return readInt == 2135247942;
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    public static boolean b(File file) {
        String str;
        StringBuilder sb;
        if (!g() || !a(file)) {
            return true;
        }
        try {
            new e(file);
            return true;
        } catch (IOException e2) {
            Log.e("ELF", "checkElfFile IOException: " + e2);
            return false;
        } catch (UnknownFormatConversionException e3) {
            th = e3;
            sb = new StringBuilder();
            str = "checkElfFile UnknownFormatConversionException: ";
            sb.append(str);
            sb.append(th);
            Log.e("ELF", sb.toString());
            return true;
        } catch (Throwable th) {
            th = th;
            sb = new StringBuilder();
            str = "checkElfFile Throwable: ";
            sb.append(str);
            sb.append(th);
            Log.e("ELF", sb.toString());
            return true;
        }
    }

    private void f() throws IOException {
        a aVar = this.h;
        c cVar = this.g;
        boolean d2 = d();
        k a2 = a(".dynsym");
        if (a2 != null) {
            cVar.a(a2.b());
            int a3 = a2.a() / (d2 ? 24 : 16);
            this.e = new l[a3];
            char[] cArr = new char[1];
            for (int i2 = 0; i2 < a3; i2++) {
                if (d2) {
                    i iVar = new i();
                    iVar.c = cVar.b();
                    cVar.a(cArr);
                    iVar.d = cArr[0];
                    cVar.a(cArr);
                    iVar.e = cArr[0];
                    iVar.a = cVar.c();
                    iVar.b = cVar.c();
                    iVar.f = cVar.a();
                    this.e[i2] = iVar;
                } else {
                    C0001e eVar = new C0001e();
                    eVar.c = cVar.b();
                    eVar.a = cVar.b();
                    eVar.b = cVar.b();
                    cVar.a(cArr);
                    eVar.d = cArr[0];
                    cVar.a(cArr);
                    eVar.e = cArr[0];
                    eVar.f = cVar.a();
                    this.e[i2] = eVar;
                }
            }
            k kVar = this.i[a2.i];
            cVar.a(kVar.b());
            byte[] bArr = new byte[kVar.a()];
            this.f = bArr;
            cVar.a(bArr);
        }
        this.d = new j[aVar.g];
        for (int i3 = 0; i3 < aVar.g; i3++) {
            cVar.a(aVar.b() + ((long) (aVar.f * i3)));
            if (d2) {
                g gVar = new g();
                gVar.g = cVar.b();
                gVar.h = cVar.b();
                gVar.a = cVar.c();
                gVar.b = cVar.c();
                gVar.c = cVar.c();
                gVar.d = cVar.c();
                gVar.e = cVar.c();
                gVar.f = cVar.c();
                this.d[i3] = gVar;
            } else {
                c cVar2 = new c();
                cVar2.g = cVar.b();
                cVar2.h = cVar.b();
                cVar2.a = cVar.b();
                cVar2.b = cVar.b();
                cVar2.c = cVar.b();
                cVar2.d = cVar.b();
                cVar2.e = cVar.b();
                cVar2.f = cVar.b();
                this.d[i3] = cVar2;
            }
        }
    }

    private static boolean g() {
        String property = System.getProperty("java.vm.version");
        return property != null && property.startsWith("2");
    }

    public final k a(String str) {
        for (k kVar : this.i) {
            if (str.equals(a(kVar.g))) {
                return kVar;
            }
        }
        return null;
    }

    public final String a(int i2) {
        if (i2 == 0) {
            return "SHN_UNDEF";
        }
        int i3 = i2;
        while (this.j[i3] != 0) {
            i3++;
        }
        return new String(this.j, i2, i3 - i2);
    }

    /* access modifiers changed from: package-private */
    public final boolean a() {
        return this.b[0] == a[0];
    }

    /* access modifiers changed from: package-private */
    public final char b() {
        return this.b[4];
    }

    /* access modifiers changed from: package-private */
    public final char c() {
        return this.b[5];
    }

    public void close() {
        this.g.close();
    }

    public final boolean d() {
        return b() == 2;
    }

    public final boolean e() {
        return c() == 1;
    }
}
