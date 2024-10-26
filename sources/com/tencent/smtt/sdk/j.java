package com.tencent.smtt.sdk;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.tencent.smtt.utils.FileUtil;
import com.tencent.smtt.utils.TbsLog;
import java.io.File;
import org.apache.commons.lang3.StringUtils;

public class j {
    public static final String a = CookieManager.LOGTAG;
    static File b;

    public static File a(Context context) {
        if (b == null && context != null) {
            b = new File(context.getDir("webview", 0), "Cookies");
        }
        if (b == null) {
            b = new File("/data/data/" + context.getPackageName() + File.separator + "app_webview" + File.separator + "Cookies");
        }
        return b;
    }

    private static String a(SQLiteDatabase sQLiteDatabase, String str) {
        Cursor rawQuery = sQLiteDatabase.rawQuery("select * from " + str, (String[]) null);
        int count = rawQuery.getCount();
        int columnCount = rawQuery.getColumnCount();
        StringBuilder sb = new StringBuilder();
        sb.append("raws:" + count + ",columns:" + columnCount + StringUtils.LF);
        if (count <= 0 || !rawQuery.moveToFirst()) {
            return sb.toString();
        }
        do {
            sb.append(StringUtils.LF);
            for (int i = 0; i < columnCount; i++) {
                try {
                    sb.append(rawQuery.getString(i));
                    sb.append(",");
                } catch (Exception unused) {
                }
            }
            sb.append(StringUtils.LF);
        } while (rawQuery.moveToNext());
        return sb.toString();
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [java.util.ArrayList<java.lang.String>, java.lang.String[], android.database.Cursor] */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0035, code lost:
        if (r4.isOpen() != false) goto L_0x0046;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0044, code lost:
        if (r4.isOpen() != false) goto L_0x0046;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0046, code lost:
        r4.close();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.ArrayList<java.lang.String> a(android.database.sqlite.SQLiteDatabase r4) {
        /*
            r0 = 0
            if (r4 != 0) goto L_0x0004
            return r0
        L_0x0004:
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            java.lang.String r2 = "select * from sqlite_master where type='table'"
            android.database.Cursor r0 = r4.rawQuery(r2, r0)     // Catch:{ all -> 0x0038 }
            boolean r2 = r0.moveToFirst()     // Catch:{ all -> 0x0038 }
            if (r2 == 0) goto L_0x002a
        L_0x0015:
            r2 = 1
            java.lang.String r2 = r0.getString(r2)     // Catch:{ all -> 0x0038 }
            r3 = 4
            r0.getString(r3)     // Catch:{ all -> 0x0038 }
            r1.add(r2)     // Catch:{ all -> 0x0038 }
            a(r4, r2)     // Catch:{ all -> 0x0038 }
            boolean r2 = r0.moveToNext()     // Catch:{ all -> 0x0038 }
            if (r2 != 0) goto L_0x0015
        L_0x002a:
            if (r0 == 0) goto L_0x002f
            r0.close()
        L_0x002f:
            if (r4 == 0) goto L_0x0049
            boolean r0 = r4.isOpen()
            if (r0 == 0) goto L_0x0049
            goto L_0x0046
        L_0x0038:
            if (r0 == 0) goto L_0x003e
            r0.close()
        L_0x003e:
            if (r4 == 0) goto L_0x0049
            boolean r0 = r4.isOpen()
            if (r0 == 0) goto L_0x0049
        L_0x0046:
            r4.close()
        L_0x0049:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.j.a(android.database.sqlite.SQLiteDatabase):java.util.ArrayList");
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [java.lang.String[], android.database.Cursor] */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00ba, code lost:
        if (r0.isOpen() != false) goto L_0x00bc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00bc, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00e6, code lost:
        if (r0.isOpen() != false) goto L_0x00bc;
     */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00b1  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00b6  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00ef A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x00f0  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void a(android.content.Context r9, com.tencent.smtt.sdk.CookieManager.a r10, java.lang.String r11, boolean r12, boolean r13) {
        /*
            java.lang.String r13 = ";"
            if (r9 != 0) goto L_0x0005
            return
        L_0x0005:
            com.tencent.smtt.sdk.CookieManager$a r0 = com.tencent.smtt.sdk.CookieManager.a.MODE_KEYS
            if (r10 != r0) goto L_0x0010
            boolean r0 = android.text.TextUtils.isEmpty(r11)
            if (r0 == 0) goto L_0x0010
            return
        L_0x0010:
            java.lang.String r0 = ","
            java.lang.String[] r11 = r11.split(r0)
            if (r11 == 0) goto L_0x0159
            int r0 = r11.length
            r1 = 1
            if (r0 >= r1) goto L_0x001e
            goto L_0x0159
        L_0x001e:
            android.database.sqlite.SQLiteDatabase r0 = c(r9)
            if (r0 != 0) goto L_0x0025
            return
        L_0x0025:
            java.util.HashMap r2 = new java.util.HashMap
            r2.<init>()
            r3 = 0
            java.lang.String r4 = "select * from cookies"
            android.database.Cursor r3 = r0.rawQuery(r4, r3)     // Catch:{ all -> 0x00c0 }
            int r4 = r3.getCount()     // Catch:{ all -> 0x00c0 }
            if (r4 <= 0) goto L_0x00af
            boolean r4 = r3.moveToFirst()     // Catch:{ all -> 0x00c0 }
            if (r4 == 0) goto L_0x00af
        L_0x003d:
            java.lang.String r4 = "host_key"
            int r4 = r3.getColumnIndex(r4)     // Catch:{ all -> 0x00c0 }
            java.lang.String r4 = r3.getString(r4)     // Catch:{ all -> 0x00c0 }
            com.tencent.smtt.sdk.CookieManager$a r5 = com.tencent.smtt.sdk.CookieManager.a.MODE_KEYS     // Catch:{ all -> 0x00c0 }
            if (r10 != r5) goto L_0x0060
            int r5 = r11.length     // Catch:{ all -> 0x00c0 }
            r6 = 0
            r7 = 0
        L_0x004e:
            if (r7 >= r5) goto L_0x005d
            r8 = r11[r7]     // Catch:{ all -> 0x00c0 }
            boolean r8 = r4.equals(r8)     // Catch:{ all -> 0x00c0 }
            if (r8 == 0) goto L_0x005a
            r6 = 1
            goto L_0x005d
        L_0x005a:
            int r7 = r7 + 1
            goto L_0x004e
        L_0x005d:
            if (r6 != 0) goto L_0x0060
            goto L_0x00a9
        L_0x0060:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x00c0 }
            r5.<init>()     // Catch:{ all -> 0x00c0 }
            java.lang.String r6 = "value"
            int r6 = r3.getColumnIndex(r6)     // Catch:{ all -> 0x00c0 }
            java.lang.String r6 = r3.getString(r6)     // Catch:{ all -> 0x00c0 }
            r5.append(r6)     // Catch:{ all -> 0x00c0 }
            r5.append(r13)     // Catch:{ all -> 0x00c0 }
            java.lang.String r6 = "name"
            int r6 = r3.getColumnIndex(r6)     // Catch:{ all -> 0x00c0 }
            java.lang.String r6 = r3.getString(r6)     // Catch:{ all -> 0x00c0 }
            r5.append(r6)     // Catch:{ all -> 0x00c0 }
            r5.append(r13)     // Catch:{ all -> 0x00c0 }
            java.lang.String r6 = "expires_utc"
            int r6 = r3.getColumnIndex(r6)     // Catch:{ all -> 0x00c0 }
            int r6 = r3.getInt(r6)     // Catch:{ all -> 0x00c0 }
            r5.append(r6)     // Catch:{ all -> 0x00c0 }
            r5.append(r13)     // Catch:{ all -> 0x00c0 }
            java.lang.String r6 = "priority"
            int r6 = r3.getColumnIndex(r6)     // Catch:{ all -> 0x00c0 }
            int r6 = r3.getInt(r6)     // Catch:{ all -> 0x00c0 }
            r5.append(r6)     // Catch:{ all -> 0x00c0 }
            java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x00c0 }
            r2.put(r4, r5)     // Catch:{ all -> 0x00c0 }
        L_0x00a9:
            boolean r4 = r3.moveToNext()     // Catch:{ all -> 0x00c0 }
            if (r4 != 0) goto L_0x003d
        L_0x00af:
            if (r3 == 0) goto L_0x00b4
            r3.close()
        L_0x00b4:
            if (r0 == 0) goto L_0x00e9
            boolean r10 = r0.isOpen()
            if (r10 == 0) goto L_0x00e9
        L_0x00bc:
            r0.close()
            goto L_0x00e9
        L_0x00c0:
            r10 = move-exception
            java.lang.String r11 = a     // Catch:{ all -> 0x0147 }
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch:{ all -> 0x0147 }
            r13.<init>()     // Catch:{ all -> 0x0147 }
            java.lang.String r4 = "getCookieDBVersion exception:"
            r13.append(r4)     // Catch:{ all -> 0x0147 }
            java.lang.String r10 = r10.toString()     // Catch:{ all -> 0x0147 }
            r13.append(r10)     // Catch:{ all -> 0x0147 }
            java.lang.String r10 = r13.toString()     // Catch:{ all -> 0x0147 }
            android.util.Log.e(r11, r10)     // Catch:{ all -> 0x0147 }
            if (r3 == 0) goto L_0x00e0
            r3.close()
        L_0x00e0:
            if (r0 == 0) goto L_0x00e9
            boolean r10 = r0.isOpen()
            if (r10 == 0) goto L_0x00e9
            goto L_0x00bc
        L_0x00e9:
            boolean r10 = r2.isEmpty()
            if (r10 == 0) goto L_0x00f0
            return
        L_0x00f0:
            b(r9)
            java.util.Set r10 = r2.entrySet()
            java.util.Iterator r10 = r10.iterator()
        L_0x00fb:
            boolean r11 = r10.hasNext()
            if (r11 == 0) goto L_0x011b
            java.lang.Object r11 = r10.next()
            java.util.Map$Entry r11 = (java.util.Map.Entry) r11
            java.lang.Object r13 = r11.getKey()
            java.lang.String r13 = (java.lang.String) r13
            java.lang.Object r11 = r11.getValue()
            java.lang.String r11 = (java.lang.String) r11
            com.tencent.smtt.sdk.CookieManager r0 = com.tencent.smtt.sdk.CookieManager.getInstance()
            r0.setCookie((java.lang.String) r13, (java.lang.String) r11, (boolean) r1)
            goto L_0x00fb
        L_0x011b:
            int r10 = android.os.Build.VERSION.SDK_INT
            r11 = 21
            if (r10 < r11) goto L_0x0129
            com.tencent.smtt.sdk.CookieManager r10 = com.tencent.smtt.sdk.CookieManager.getInstance()
            r10.flush()
            goto L_0x0130
        L_0x0129:
            com.tencent.smtt.sdk.CookieSyncManager r10 = com.tencent.smtt.sdk.CookieSyncManager.getInstance()
            r10.sync()
        L_0x0130:
            if (r12 == 0) goto L_0x0146
            android.database.sqlite.SQLiteDatabase r10 = c(r9)
            a((android.database.sqlite.SQLiteDatabase) r10)
            int r10 = d(r9)
            r11 = -1
            if (r10 == r11) goto L_0x0146
            com.tencent.smtt.sdk.CookieManager.getInstance()
            com.tencent.smtt.sdk.CookieManager.setROMCookieDBVersion(r9, r10)
        L_0x0146:
            return
        L_0x0147:
            r9 = move-exception
            if (r3 == 0) goto L_0x014d
            r3.close()
        L_0x014d:
            if (r0 == 0) goto L_0x0158
            boolean r10 = r0.isOpen()
            if (r10 == 0) goto L_0x0158
            r0.close()
        L_0x0158:
            throw r9
        L_0x0159:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.j.a(android.content.Context, com.tencent.smtt.sdk.CookieManager$a, java.lang.String, boolean, boolean):void");
    }

    public static boolean b(Context context) {
        if (context == null) {
            return false;
        }
        FileUtil.a(a(context), false);
        return true;
    }

    public static SQLiteDatabase c(Context context) {
        File a2;
        SQLiteDatabase sQLiteDatabase = null;
        if (context == null || (a2 = a(context)) == null) {
            return null;
        }
        try {
            sQLiteDatabase = SQLiteDatabase.openDatabase(a2.getAbsolutePath(), (SQLiteDatabase.CursorFactory) null, 0);
        } catch (Exception unused) {
        }
        if (sQLiteDatabase == null) {
            TbsLog.i(a, "dbPath is not exist!");
        }
        return sQLiteDatabase;
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [java.lang.String[], android.database.Cursor] */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0054, code lost:
        if (r4.isOpen() != false) goto L_0x0056;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0056, code lost:
        r4.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0068, code lost:
        if (r4.isOpen() != false) goto L_0x0056;
     */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x005f  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0064  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int d(android.content.Context r4) {
        /*
            java.lang.System.currentTimeMillis()
            r0 = 0
            r1 = 0
            android.database.sqlite.SQLiteDatabase r4 = c(r4)     // Catch:{ all -> 0x005c }
            if (r4 != 0) goto L_0x0018
            r0 = -1
            if (r4 == 0) goto L_0x0017
            boolean r1 = r4.isOpen()
            if (r1 == 0) goto L_0x0017
            r4.close()
        L_0x0017:
            return r0
        L_0x0018:
            java.lang.String r2 = "select * from meta"
            android.database.Cursor r1 = r4.rawQuery(r2, r1)     // Catch:{ all -> 0x005a }
            int r2 = r1.getCount()     // Catch:{ all -> 0x005a }
            r1.getColumnCount()     // Catch:{ all -> 0x005a }
            if (r2 <= 0) goto L_0x0049
            boolean r2 = r1.moveToFirst()     // Catch:{ all -> 0x005a }
            if (r2 == 0) goto L_0x0049
        L_0x002d:
            java.lang.String r2 = r1.getString(r0)     // Catch:{ all -> 0x005a }
            java.lang.String r3 = "version"
            boolean r2 = r2.equals(r3)     // Catch:{ all -> 0x005a }
            if (r2 == 0) goto L_0x0043
            r2 = 1
            java.lang.String r2 = r1.getString(r2)     // Catch:{ all -> 0x005a }
            int r0 = java.lang.Integer.parseInt(r2)     // Catch:{ all -> 0x005a }
            goto L_0x0049
        L_0x0043:
            boolean r2 = r1.moveToNext()     // Catch:{ all -> 0x005a }
            if (r2 != 0) goto L_0x002d
        L_0x0049:
            if (r1 == 0) goto L_0x004e
            r1.close()
        L_0x004e:
            if (r4 == 0) goto L_0x006b
            boolean r1 = r4.isOpen()
            if (r1 == 0) goto L_0x006b
        L_0x0056:
            r4.close()
            goto L_0x006b
        L_0x005a:
            goto L_0x005d
        L_0x005c:
            r4 = r1
        L_0x005d:
            if (r1 == 0) goto L_0x0062
            r1.close()
        L_0x0062:
            if (r4 == 0) goto L_0x006b
            boolean r1 = r4.isOpen()
            if (r1 == 0) goto L_0x006b
            goto L_0x0056
        L_0x006b:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.j.d(android.content.Context):int");
    }
}
