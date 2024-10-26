package com.tencent.smtt.utils;

import android.content.ComponentName;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.ProviderInfo;
import android.content.res.XmlResourceParser;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.text.TextUtils;
import android.util.Log;
import com.limpoxe.support.servicemanager.ServiceProvider;
import com.tencent.smtt.sdk.QbSdk;
import com.tencent.smtt.sdk.TbsConfig;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import org.apache.mina.proxy.handlers.http.ntlm.NTLMConstants;
import org.xmlpull.v1.XmlPullParserException;

public class FileProvider extends ContentProvider {
    private static final String[] a = {"_display_name", "_size"};
    private static final File b = new File("/");
    private static HashMap<String, a> c = new HashMap<>();
    private a d;

    interface a {
        Uri a(File file);

        File a(Uri uri);
    }

    static class b implements a {
        private final String a;
        private final HashMap<String, File> b = new HashMap<>();

        public b(String str) {
            this.a = str;
        }

        public Uri a(File file) {
            try {
                String canonicalPath = file.getCanonicalPath();
                Map.Entry entry = null;
                for (Map.Entry next : this.b.entrySet()) {
                    String path = ((File) next.getValue()).getPath();
                    if (canonicalPath.startsWith(path) && (entry == null || path.length() > ((File) entry.getValue()).getPath().length())) {
                        entry = next;
                    }
                }
                if (entry != null) {
                    String path2 = ((File) entry.getValue()).getPath();
                    boolean endsWith = path2.endsWith("/");
                    int length = path2.length();
                    if (!endsWith) {
                        length++;
                    }
                    String substring = canonicalPath.substring(length);
                    return new Uri.Builder().scheme("content").authority(this.a).encodedPath(Uri.encode((String) entry.getKey()) + '/' + Uri.encode(substring, "/")).build();
                }
                throw new IllegalArgumentException("Failed to find configured root that contains " + canonicalPath);
            } catch (IOException unused) {
                throw new IllegalArgumentException("Failed to resolve canonical path for " + file);
            }
        }

        public File a(Uri uri) {
            String encodedPath = uri.getEncodedPath();
            int indexOf = encodedPath.indexOf(47, 1);
            String decode = Uri.decode(encodedPath.substring(1, indexOf));
            String decode2 = Uri.decode(encodedPath.substring(indexOf + 1));
            File file = this.b.get(decode);
            if (file != null) {
                File file2 = new File(file, decode2);
                try {
                    File canonicalFile = file2.getCanonicalFile();
                    if (canonicalFile.getPath().startsWith(file.getPath())) {
                        return canonicalFile;
                    }
                    throw new SecurityException("Resolved path jumped beyond configured root");
                } catch (IOException unused) {
                    throw new IllegalArgumentException("Failed to resolve canonical path for " + file2);
                }
            } else {
                throw new IllegalArgumentException("Unable to find configured root for " + uri);
            }
        }

        public void a(String str, File file) {
            if (!TextUtils.isEmpty(str)) {
                try {
                    this.b.put(str, file.getCanonicalFile());
                } catch (IOException e) {
                    throw new IllegalArgumentException("Failed to resolve canonical path for " + file, e);
                }
            } else {
                throw new IllegalArgumentException("Name must not be empty");
            }
        }
    }

    private static int a(String str) {
        if ("r".equals(str)) {
            return NTLMConstants.FLAG_UNIDENTIFIED_11;
        }
        if ("w".equals(str) || "wt".equals(str)) {
            return 738197504;
        }
        if ("wa".equals(str)) {
            return 704643072;
        }
        if ("rw".equals(str)) {
            return 939524096;
        }
        if ("rwt".equals(str)) {
            return 1006632960;
        }
        throw new IllegalArgumentException("Invalid mode: " + str);
    }

    static Uri a(Context context, File file) {
        String str;
        Method declaredMethod;
        if (Build.VERSION.SDK_INT < 24) {
            return null;
        }
        try {
            str = context.getPackageManager().getProviderInfo(new ComponentName(context.getPackageName(), "androidx.core.content.FileProvider"), 0).authority;
        } catch (Exception e) {
            e.printStackTrace();
            str = "";
        }
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            Class<?> cls = Class.forName("androidx.core.content.FileProvider");
            if (cls == null || (declaredMethod = cls.getDeclaredMethod("getUriForFile", new Class[]{Context.class, String.class, File.class})) == null) {
                return null;
            }
            Object invoke = declaredMethod.invoke((Object) null, new Object[]{context, str, file});
            if (invoke instanceof Uri) {
                return (Uri) invoke;
            }
            return null;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static Uri a(Context context, String str) {
        Uri uri = null;
        if (context == null || context.getApplicationContext() == null || !TbsConfig.APP_QQ.equals(context.getApplicationContext().getApplicationInfo().packageName)) {
            if (context != null && context.getApplicationInfo().targetSdkVersion >= 24 && Build.VERSION.SDK_INT >= 24 && (uri = a(context, new File(str))) == null && QbSdk.checkContentProviderPrivilage(context)) {
                uri = a(context, context.getApplicationInfo().packageName + ".provider", new File(str));
            }
            if (uri != null) {
                return uri;
            }
            try {
                return Uri.fromFile(new File(str));
            } catch (Exception e) {
                e.printStackTrace();
                Log.e("FileProvider", "create uri failed,please check again");
                return uri;
            }
        } else {
            try {
                return (Uri) i.a(Class.forName("com.tencent.mobileqq.utils.kapalaiadapter.FileProvider7Helper"), "getUriForFile", (Class<?>[]) new Class[]{Context.class, File.class}, context, new File(str));
            } catch (Exception e2) {
                e2.printStackTrace();
                return null;
            }
        }
    }

    public static Uri a(Context context, String str, File file) {
        return b(context, str).a(file);
    }

    private static File a(File file, String... strArr) {
        for (String str : strArr) {
            if (str != null) {
                file = new File(file, str);
            }
        }
        return file;
    }

    private static Object[] a(Object[] objArr, int i) {
        Object[] objArr2 = new Object[i];
        System.arraycopy(objArr, 0, objArr2, 0, i);
        return objArr2;
    }

    private static String[] a(String[] strArr, int i) {
        String[] strArr2 = new String[i];
        System.arraycopy(strArr, 0, strArr2, 0, i);
        return strArr2;
    }

    private static a b(Context context, String str) {
        a aVar;
        synchronized (c) {
            aVar = c.get(str);
            if (aVar == null) {
                try {
                    aVar = c(context, str);
                    c.put(str, aVar);
                } catch (IOException e) {
                    throw new IllegalArgumentException("Failed to parse android.support.FILE_PROVIDER_PATHS meta-data", e);
                } catch (XmlPullParserException e2) {
                    throw new IllegalArgumentException("Failed to parse android.support.FILE_PROVIDER_PATHS meta-data", e2);
                }
            }
        }
        return aVar;
    }

    private static a c(Context context, String str) throws IOException, XmlPullParserException {
        b bVar = new b(str);
        ProviderInfo resolveContentProvider = context.getPackageManager().resolveContentProvider(str, 128);
        if (resolveContentProvider != null) {
            XmlResourceParser loadXmlMetaData = resolveContentProvider.loadXmlMetaData(context.getPackageManager(), "android.support.FILE_PROVIDER_PATHS");
            if (loadXmlMetaData != null) {
                while (true) {
                    int next = loadXmlMetaData.next();
                    if (next == 1) {
                        return bVar;
                    }
                    if (next == 2) {
                        String name = loadXmlMetaData.getName();
                        File file = null;
                        String attributeValue = loadXmlMetaData.getAttributeValue((String) null, ServiceProvider.NAME);
                        String attributeValue2 = loadXmlMetaData.getAttributeValue((String) null, "path");
                        if ("root-path".equals(name)) {
                            file = a(b, attributeValue2);
                        } else if ("files-path".equals(name)) {
                            file = a(context.getFilesDir(), attributeValue2);
                        } else if ("cache-path".equals(name)) {
                            file = a(context.getCacheDir(), attributeValue2);
                        } else if ("external-path".equals(name)) {
                            file = a(Environment.getExternalStorageDirectory(), attributeValue2);
                        }
                        if (file != null) {
                            bVar.a(attributeValue, file);
                        }
                    }
                }
            } else {
                throw new IllegalArgumentException("Missing android.support.FILE_PROVIDER_PATHS meta-data");
            }
        } else {
            throw new RuntimeException("Must declare com.tencent.smtt.utils.FileProvider in AndroidManifest above Android 7.0,please view document in x5.tencent.com");
        }
    }

    public void attachInfo(Context context, ProviderInfo providerInfo) {
        super.attachInfo(context, providerInfo);
        if (providerInfo.exported) {
            throw new SecurityException("Provider must not be exported");
        } else if (providerInfo.grantUriPermissions) {
            this.d = b(context, providerInfo.authority);
        } else {
            throw new SecurityException("Provider must grant uri permissions");
        }
    }

    public int delete(Uri uri, String str, String[] strArr) {
        return this.d.a(uri).delete() ? 1 : 0;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0012, code lost:
        r3 = android.webkit.MimeTypeMap.getSingleton().getMimeTypeFromExtension(r3.getName().substring(r0 + 1));
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String getType(android.net.Uri r3) {
        /*
            r2 = this;
            com.tencent.smtt.utils.FileProvider$a r0 = r2.d
            java.io.File r3 = r0.a((android.net.Uri) r3)
            java.lang.String r0 = r3.getName()
            r1 = 46
            int r0 = r0.lastIndexOf(r1)
            if (r0 < 0) goto L_0x0027
            java.lang.String r3 = r3.getName()
            int r0 = r0 + 1
            java.lang.String r3 = r3.substring(r0)
            android.webkit.MimeTypeMap r0 = android.webkit.MimeTypeMap.getSingleton()
            java.lang.String r3 = r0.getMimeTypeFromExtension(r3)
            if (r3 == 0) goto L_0x0027
            return r3
        L_0x0027:
            java.lang.String r3 = "application/octet-stream"
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.utils.FileProvider.getType(android.net.Uri):java.lang.String");
    }

    public Uri insert(Uri uri, ContentValues contentValues) {
        throw new UnsupportedOperationException("No external inserts");
    }

    public boolean onCreate() {
        return true;
    }

    public ParcelFileDescriptor openFile(Uri uri, String str) throws FileNotFoundException {
        return ParcelFileDescriptor.open(this.d.a(uri), a(str));
    }

    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        int i;
        File a2 = this.d.a(uri);
        if (strArr == null) {
            strArr = a;
        }
        String[] strArr3 = new String[strArr.length];
        Object[] objArr = new Object[strArr.length];
        int i2 = 0;
        for (String str3 : strArr) {
            if ("_display_name".equals(str3)) {
                strArr3[i2] = "_display_name";
                i = i2 + 1;
                objArr[i2] = a2.getName();
            } else if ("_size".equals(str3)) {
                strArr3[i2] = "_size";
                i = i2 + 1;
                objArr[i2] = Long.valueOf(a2.length());
            }
            i2 = i;
        }
        String[] a3 = a(strArr3, i2);
        Object[] a4 = a(objArr, i2);
        MatrixCursor matrixCursor = new MatrixCursor(a3, 1);
        matrixCursor.addRow(a4);
        return matrixCursor;
    }

    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        throw new UnsupportedOperationException("No external updates");
    }
}
