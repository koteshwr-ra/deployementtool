package com.ciot.base.util;

import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import com.alibaba.android.arouter.utils.Consts;
import com.ciot.base.config.CommonConfig;
import com.ciot.base.constant.FileConstant;
import com.ciot.base.constant.NetConstant;
import com.ciot.networklib.bean.phone.VisitByPhone;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;
import java.util.List;
import java.util.Locale;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import mc.csst.com.selfchassis.ui.fragment.set.map.MapFragment;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class MyLogUtils {
    public static final int A = 32;
    private static final String ARGS = "args";
    private static final String BOTTOM_BORDER = "╚═══════════════════════════════════════════════════════════════════════════════════════════════════";
    public static final int D = 2;
    public static final int E = 16;
    private static final int FILE = 241;
    public static final int I = 4;
    private static final int JSON = 242;
    private static final String LEFT_BORDER = "║ ";
    private static final String LINE_SEPARATOR = System.getProperty("line.separator");
    private static final int MAX_LEN = 4000;
    private static final String NULL = "null";
    private static final String NULL_TIPS = "Log with null object.";
    public static final String TAG = "MY_LOG";
    public static final String TAG_PREFIX = "robot_log";
    private static final String TOP_BORDER = "╔═══════════════════════════════════════════════════════════════════════════════════════════════════";
    public static final int V = 1;
    public static final int W = 8;
    private static final int XML = 244;
    /* access modifiers changed from: private */
    public static String dir = null;
    public static String mLogMsg = null;
    public static String sDateLogContent = null;
    public static String sFinalFullPath = null;
    /* access modifiers changed from: private */
    public static String sGlobalTag = null;
    /* access modifiers changed from: private */
    public static boolean sLog2FileSwitch = true;
    /* access modifiers changed from: private */
    public static boolean sLogBorderSwitch = false;
    /* access modifiers changed from: private */
    public static boolean sLogDetailSwitch = false;
    /* access modifiers changed from: private */
    public static int sLogFilter = 2;
    /* access modifiers changed from: private */
    public static boolean sLogSwitch = true;
    /* access modifiers changed from: private */
    public static boolean sTagIsSpace = true;

    @Retention(RetentionPolicy.SOURCE)
    public @interface TYPE {
    }

    public MyLogUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static class Builder {
        public Builder() {
            if ("mounted".equals(Environment.getExternalStorageState())) {
                String unused = MyLogUtils.dir = FileConstant.Dir_LOG_TODAY;
            } else {
                String unused2 = MyLogUtils.dir = ContextUtil.getContext().getCacheDir() + File.separator + "log" + File.separator;
            }
            File file = new File(MyLogUtils.dir);
            if (!file.exists()) {
                file.mkdirs();
            }
        }

        public Builder setGlobalTag(String str) {
            if (!MyLogUtils.isSpace(str)) {
                String unused = MyLogUtils.sGlobalTag = str;
                boolean unused2 = MyLogUtils.sTagIsSpace = false;
            } else {
                String unused3 = MyLogUtils.sGlobalTag = "";
                boolean unused4 = MyLogUtils.sTagIsSpace = true;
            }
            return this;
        }

        public Builder setLogSwitch(boolean z) {
            boolean unused = MyLogUtils.sLogSwitch = z;
            return this;
        }

        public Builder setLog2FileSwitch(boolean z) {
            boolean unused = MyLogUtils.sLog2FileSwitch = z;
            return this;
        }

        public Builder setLogDetailSwitch(boolean z) {
            boolean unused = MyLogUtils.sLogDetailSwitch = z;
            return this;
        }

        public Builder setBorderSwitch(boolean z) {
            boolean unused = MyLogUtils.sLogBorderSwitch = z;
            return this;
        }

        public Builder setLogFilter(int i) {
            int unused = MyLogUtils.sLogFilter = i;
            return this;
        }
    }

    public static void Logd(String str, String str2) {
        if (sLogSwitch) {
            log1(2, str, str2);
        }
    }

    public static void Logi(String str, String str2) {
        if (sLogSwitch) {
            log1(4, str, str2);
        }
    }

    public static void Logw(String str, String str2) {
        if (sLogSwitch) {
            log1(8, str, str2);
        }
    }

    public static void Loge(String str, String str2) {
        if (sLogSwitch) {
            log1(16, str, str2);
        }
    }

    public static void Logv(String str, String str2) {
        if (sLogSwitch) {
            Log.v(str, str2);
        }
    }

    public static void Logd(String str) {
        Logd(generateTag(), str);
    }

    public static void Logi(String str) {
        Logi(generateTag(), str);
    }

    public static void Logw(String str) {
        Logw(generateTag(), str);
    }

    public static void Loge(String str) {
        Loge(generateTag(), str);
    }

    public static void Logv(String str) {
        Logv(generateTag(), str);
    }

    public static void file(Object obj) {
        log(241, sGlobalTag, obj);
    }

    public static void file(String str, Object obj) {
        log(241, str, obj);
    }

    public static void json(String str) {
        log(242, sGlobalTag, str);
    }

    public static void json(String str, String str2) {
        log(242, str, str2);
    }

    public static void xml(String str) {
        log(XML, sGlobalTag, str);
    }

    public static void xml(String str, String str2) {
        log(XML, str, str2);
    }

    public static boolean checkLog() {
        final String str = FileConstant.LOG_TODAY_FILE_NAME;
        if (TextUtils.isEmpty(str)) {
            Log.v(TAG, "checkLog failed,fileDir is empty:" + str);
            return true;
        }
        ThreadPoolProxyFactory.getNormalThreadPoolProxy().execute(new Runnable() {
            public void run() {
                try {
                    int parseInt = Integer.parseInt(str.replace(MapFragment.SLASH, ""));
                    List<File> listDirInDir = MyFileUtils.listDirInDir(FileConstant.APP_PATH);
                    if (listDirInDir != null) {
                        if (listDirInDir.size() != 0) {
                            Log.v(MyLogUtils.TAG, "fileDir size =" + listDirInDir.size());
                            for (int i = 0; i < listDirInDir.size(); i++) {
                                String name = listDirInDir.get(i).getName();
                                int parseInt2 = Integer.parseInt(name.replace(MapFragment.SLASH, ""));
                                if (parseInt - parseInt2 > 7) {
                                    boolean deleteDir = MyFileUtils.deleteDir(FileConstant.APP_PATH + File.separator + name);
                                    Log.v(MyLogUtils.TAG, "curDate=" + parseInt + ",fileDate=" + parseInt2 + ",isDelSuc=" + deleteDir);
                                }
                            }
                            return;
                        }
                    }
                    Log.v(MyLogUtils.TAG, "checkLog failed,files.size() is 0:");
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        });
        return false;
    }

    private static void log(int i, String str, Object... objArr) {
        if (sLogSwitch) {
            String[] processContents = processContents(i, str, objArr);
            String str2 = processContents[0];
            String str3 = processContents[1];
            if (i == 1 || i == 2 || i == 4 || i == 8 || i == 16 || i == 32) {
                int i2 = sLogFilter;
                if (1 == i2 || i >= i2) {
                    printLog(i, str2, str3);
                }
                if (sLog2FileSwitch && i >= sLogFilter) {
                    print2File(str2, str3);
                }
            } else if (i == XML) {
                printLog(2, str2, str3);
            } else if (i == 241) {
                print2File(str2, str3);
            } else if (i == 242) {
                printLog(2, str2, str3);
            }
        }
    }

    private static void log1(int i, String str, String str2) {
        if (sLogSwitch) {
            String processMsg = processMsg(i, str, str2);
            if (i == 1 || i == 2 || i == 4 || i == 8 || i == 16 || i == 32) {
                int i2 = sLogFilter;
                if (1 == i2 || i >= i2) {
                    printLog(i, str, processMsg);
                }
                if (sLog2FileSwitch && i >= sLogFilter) {
                    print2File(str, processMsg);
                }
            } else if (i == XML) {
                printLog(2, str, processMsg);
            } else if (i == 241) {
                print2File(str, processMsg);
            } else if (i == 242) {
                printLog(2, str, processMsg);
            }
        }
    }

    private static String[] processContents(int i, String str, Object... objArr) {
        String str2;
        String str3;
        String str4;
        StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[5];
        String className = stackTraceElement.getClassName();
        String[] split = className.split("\\.");
        if (split.length > 0) {
            className = split[split.length - 1];
        }
        if (className.contains("$")) {
            className = className.split("\\$")[0];
        }
        if (!sTagIsSpace) {
            str = sGlobalTag;
        } else if (isSpace(str)) {
            str = className;
        }
        if (sLogDetailSwitch) {
            str2 = new Formatter().format("Thread: %s, %s(%s.java:%d)" + LINE_SEPARATOR, new Object[]{Thread.currentThread().getName(), stackTraceElement.getMethodName(), className, Integer.valueOf(stackTraceElement.getLineNumber())}).toString();
        } else {
            str2 = "";
        }
        if (objArr != null) {
            str3 = "null";
            if (objArr.length == 1) {
                Object obj = objArr[0];
                if (obj != null) {
                    str3 = obj.toString();
                }
                if (i == 242) {
                    str3 = formatJson(str3);
                } else if (i == XML) {
                    str3 = formatXml(str3);
                }
            } else {
                StringBuilder sb = new StringBuilder();
                int length = objArr.length;
                for (int i2 = 0; i2 < length; i2++) {
                    Object obj2 = objArr[i2];
                    sb.append(ARGS);
                    sb.append("[");
                    sb.append(i2);
                    sb.append("]");
                    sb.append(" = ");
                    if (obj2 == null) {
                        str4 = str3;
                    } else {
                        str4 = obj2.toString();
                    }
                    sb.append(str4);
                    sb.append(LINE_SEPARATOR);
                }
                str3 = sb.toString();
            }
        } else {
            str3 = NULL_TIPS;
        }
        if (sLogBorderSwitch) {
            StringBuilder sb2 = new StringBuilder();
            for (String append : str3.split(LINE_SEPARATOR)) {
                sb2.append(LEFT_BORDER);
                sb2.append(append);
                sb2.append(LINE_SEPARATOR);
            }
            str3 = sb2.toString();
        }
        return new String[]{str, str2 + str3};
    }

    private static String formatJson(String str) {
        try {
            if (str.startsWith("{")) {
                return new JSONObject(str).toString(4);
            }
            if (str.startsWith("[")) {
                return new JSONArray(str).toString(4);
            }
            return str;
        } catch (JSONException e) {
            e.printStackTrace();
            return str;
        }
    }

    private static String formatXml(String str) {
        try {
            StreamSource streamSource = new StreamSource(new StringReader(str));
            StreamResult streamResult = new StreamResult(new StringWriter());
            Transformer newTransformer = TransformerFactory.newInstance().newTransformer();
            newTransformer.setOutputProperty("indent", VisitByPhone.VERIFIED);
            newTransformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", NetConstant.PAGE_ID_CONSULTATION);
            newTransformer.transform(streamSource, streamResult);
            String obj = streamResult.getWriter().toString();
            return obj.replaceFirst(">", ">" + LINE_SEPARATOR);
        } catch (Exception e) {
            e.printStackTrace();
            return str;
        }
    }

    private static void printLog(int i, String str, String str2) {
        if (sLogBorderSwitch) {
            printBorder(i, str, true);
        }
        if (!MyStringUtils.isEmpty(str2)) {
            int length = str2.length();
            int i2 = length / MAX_LEN;
            if (i2 > 0) {
                int i3 = 0;
                int i4 = 0;
                while (i3 < i2) {
                    int i5 = i4 + MAX_LEN;
                    printSubLog(i, str, str2.substring(i4, i5));
                    i3++;
                    i4 = i5;
                }
                printSubLog(i, str, str2.substring(i4, length));
            } else {
                printSubLog(i, str, str2);
            }
            if (sLogBorderSwitch) {
                printBorder(i, str, false);
            }
        }
    }

    private static void printSubLog(int i, String str, String str2) {
        if (sLogBorderSwitch) {
            str2 = LEFT_BORDER + str2;
        }
        if (i == 1) {
            Log.v(str, str2);
        } else if (i == 2) {
            Log.d(str, str2);
        } else if (i == 4) {
            Log.i(str, str2);
        } else if (i == 8) {
            Log.w(str, str2);
        } else if (i == 16) {
            Log.e(str, str2);
        } else if (i == 32) {
            Log.wtf(str, str2);
        }
    }

    private static void printBorder(int i, String str, boolean z) {
        String str2 = z ? TOP_BORDER : BOTTOM_BORDER;
        if (i == 1) {
            Log.v(str, str2);
        } else if (i == 2) {
            Log.d(str, str2);
        } else if (i == 4) {
            Log.i(str, str2);
        } else if (i == 8) {
            Log.w(str, str2);
        } else if (i == 16) {
            Log.e(str, str2);
        } else if (i == 32) {
            Log.wtf(str, str2);
        }
    }

    public static synchronized void print2File(String str, String str2) {
        String str3;
        synchronized (MyLogUtils.class) {
            char c = 65535;
            switch (str.hashCode()) {
                case -2008334641:
                    if (str.equals("NAVIGATION_TAG")) {
                        c = 3;
                        break;
                    }
                    break;
                case -1827881208:
                    if (str.equals(CommonConfig.TAG_AD)) {
                        c = 8;
                        break;
                    }
                    break;
                case -525464262:
                    if (str.equals(CommonConfig.SELF_CHASSIS)) {
                        c = 1;
                        break;
                    }
                    break;
                case -455733736:
                    if (str.equals(CommonConfig.FRAGMENT_OPERATION)) {
                        c = 9;
                        break;
                    }
                    break;
                case -413312368:
                    if (str.equals("FACEMANAGER")) {
                        c = 4;
                        break;
                    }
                    break;
                case -286208063:
                    if (str.equals(CommonConfig.CIOT_SEMANTIC_INTENT)) {
                        c = 6;
                        break;
                    }
                    break;
                case 213623241:
                    if (str.equals("NETWORK_TAG")) {
                        c = 2;
                        break;
                    }
                    break;
                case 281039786:
                    if (str.equals(CommonConfig.CSST_PERIPHERAL)) {
                        c = 5;
                        break;
                    }
                    break;
                case 356722918:
                    if (str.equals(CommonConfig.NETINFO_TAG)) {
                        c = 10;
                        break;
                    }
                    break;
                case 390118507:
                    if (str.equals("SPEECHMANAGER")) {
                        c = 0;
                        break;
                    }
                    break;
                case 545387316:
                    if (str.equals(CommonConfig.SPRAY_CONFIG)) {
                        c = 7;
                        break;
                    }
                    break;
                case 2124503313:
                    if (str.equals(CommonConfig.VIDEO_MONITOR_TAG)) {
                        c = 11;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    str3 = dir + File.separator + "SPEECHMANAGER" + ".txt";
                    break;
                case 1:
                    str3 = dir + File.separator + CommonConfig.SELF_CHASSIS + ".txt";
                    break;
                case 2:
                    str3 = dir + File.separator + "NETWORK_TAG" + ".txt";
                    break;
                case 3:
                    str3 = dir + File.separator + "NAVIGATION_TAG" + ".txt";
                    break;
                case 4:
                    str3 = dir + File.separator + "FACEMANAGER" + ".txt";
                    break;
                case 5:
                    str3 = dir + File.separator + CommonConfig.CSST_PERIPHERAL + ".txt";
                    break;
                case 6:
                    str3 = dir + File.separator + CommonConfig.CIOT_SEMANTIC_INTENT + ".txt";
                    break;
                case 7:
                    str3 = dir + File.separator + CommonConfig.SPRAY_CONFIG + ".txt";
                    break;
                case 8:
                    str3 = dir + File.separator + CommonConfig.TAG_AD + ".txt";
                    break;
                case 9:
                    str3 = dir + File.separator + CommonConfig.FRAGMENT_OPERATION + ".txt";
                    break;
                case 10:
                    str3 = dir + File.separator + CommonConfig.NETINFO_TAG + ".txt";
                    break;
                case 11:
                    String str4 = File.separator;
                    break;
            }
            str3 = dir + File.separator + TAG + ".txt";
            if (!MyStringUtils.isEmpty(str3)) {
                if (createOrExistsFile(str3)) {
                    String format = new SimpleDateFormat("MM-dd HH:mm:ss.SSS ", Locale.getDefault()).format(new Date());
                    StringBuilder sb = new StringBuilder();
                    if (sLogBorderSwitch) {
                        sb.append(TOP_BORDER);
                        sb.append(LINE_SEPARATOR);
                    }
                    sb.append(format);
                    sb.append(str);
                    sb.append(": ");
                    sb.append(str2);
                    sb.append(LINE_SEPARATOR);
                    if (sLogBorderSwitch) {
                        sb.append(BOTTOM_BORDER);
                        sb.append(LINE_SEPARATOR);
                    }
                    sDateLogContent = sb.toString();
                    sFinalFullPath = str3;
                    ThreadPoolProxyFactory.getNormalThreadPoolProxy().submit((Runnable) new writeLogTask());
                }
            }
        }
    }

    static class writeLogTask implements Runnable {
        writeLogTask() {
        }

        /* JADX WARNING: Removed duplicated region for block: B:15:0x0027 A[SYNTHETIC, Splitter:B:15:0x0027] */
        /* JADX WARNING: Removed duplicated region for block: B:21:0x0033 A[SYNTHETIC, Splitter:B:21:0x0033] */
        /* JADX WARNING: Removed duplicated region for block: B:27:? A[RETURN, SYNTHETIC] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            /*
                r6 = this;
                r0 = 0
                java.io.BufferedWriter r1 = new java.io.BufferedWriter     // Catch:{ IOException -> 0x001e, all -> 0x0019 }
                java.io.FileWriter r2 = new java.io.FileWriter     // Catch:{ IOException -> 0x001e, all -> 0x0019 }
                java.lang.String r3 = com.ciot.base.util.MyLogUtils.sFinalFullPath     // Catch:{ IOException -> 0x001e, all -> 0x0019 }
                r4 = 1
                r2.<init>(r3, r4)     // Catch:{ IOException -> 0x001e, all -> 0x0019 }
                r1.<init>(r2)     // Catch:{ IOException -> 0x001e, all -> 0x0019 }
                java.lang.String r0 = com.ciot.base.util.MyLogUtils.sDateLogContent     // Catch:{ IOException -> 0x0017 }
                r1.write(r0)     // Catch:{ IOException -> 0x0017 }
                r1.close()     // Catch:{ IOException -> 0x002b }
                goto L_0x002f
            L_0x0017:
                r0 = move-exception
                goto L_0x0022
            L_0x0019:
                r1 = move-exception
                r5 = r1
                r1 = r0
                r0 = r5
                goto L_0x0031
            L_0x001e:
                r1 = move-exception
                r5 = r1
                r1 = r0
                r0 = r5
            L_0x0022:
                r0.printStackTrace()     // Catch:{ all -> 0x0030 }
                if (r1 == 0) goto L_0x002f
                r1.close()     // Catch:{ IOException -> 0x002b }
                goto L_0x002f
            L_0x002b:
                r0 = move-exception
                r0.printStackTrace()
            L_0x002f:
                return
            L_0x0030:
                r0 = move-exception
            L_0x0031:
                if (r1 == 0) goto L_0x003b
                r1.close()     // Catch:{ IOException -> 0x0037 }
                goto L_0x003b
            L_0x0037:
                r1 = move-exception
                r1.printStackTrace()
            L_0x003b:
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.ciot.base.util.MyLogUtils.writeLogTask.run():void");
        }
    }

    private static boolean createOrExistsFile(String str) {
        return createOrExistsFile(isSpace(str) ? null : new File(str));
    }

    private static boolean createOrExistsFile(File file) {
        if (file == null) {
            return false;
        }
        if (file.exists()) {
            return file.isFile();
        }
        if (!createOrExistsDir(file.getParentFile())) {
            return false;
        }
        try {
            return file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static boolean createOrExistsDir(File file) {
        return file != null && (!file.exists() ? file.mkdirs() : file.isDirectory());
    }

    /* access modifiers changed from: private */
    public static boolean isSpace(String str) {
        if (str == null) {
            return true;
        }
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    private static String processMsg(int i, String str, String str2) {
        StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[5];
        String className = stackTraceElement.getClassName();
        String[] split = className.split("\\.");
        if (split.length > 0) {
            className = split[split.length - 1];
        }
        if (className.contains("$")) {
            className = className.split("\\$")[0];
        }
        if (sTagIsSpace) {
            boolean isSpace = isSpace(str);
        }
        if (sLogDetailSwitch) {
            str2 = str2 + "------" + new Formatter().format("Thread: %s, %s(%s.java:%d)" + LINE_SEPARATOR, new Object[]{Thread.currentThread().getName(), stackTraceElement.getMethodName(), className, Integer.valueOf(stackTraceElement.getLineNumber())}).toString();
        }
        if (!sLogBorderSwitch) {
            return str2;
        }
        StringBuilder sb = new StringBuilder();
        for (String append : str2.split(LINE_SEPARATOR)) {
            sb.append(LEFT_BORDER);
            sb.append(append);
            sb.append(LINE_SEPARATOR);
        }
        return sb.toString();
    }

    private static String generateTag() {
        StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[4];
        String className = stackTraceElement.getClassName();
        String format = String.format("%s.%s(L:%d)", new Object[]{className.substring(className.lastIndexOf(Consts.DOT) + 1), stackTraceElement.getMethodName(), Integer.valueOf(stackTraceElement.getLineNumber())});
        if (TextUtils.isEmpty(TAG_PREFIX)) {
            return format;
        }
        return "robot_log:" + format;
    }
}
