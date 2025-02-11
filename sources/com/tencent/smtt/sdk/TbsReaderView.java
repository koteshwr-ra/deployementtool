package com.tencent.smtt.sdk;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
import com.tencent.smtt.sdk.stat.MttLoader;
import com.tencent.smtt.utils.Apn;

public class TbsReaderView extends FrameLayout {
    public static final String IS_BAR_ANIMATING = "is_bar_animating";
    public static final String IS_BAR_SHOWING = "is_bar_show";
    public static final String IS_INTO_DOWNLOADING = "into_downloading";
    public static final String KEY_FILE_PATH = "filePath";
    public static final String KEY_TEMP_PATH = "tempPath";
    public static final int READER_CHANNEL_DOC_ID = 10965;
    public static final int READER_CHANNEL_PDF_ID = 10834;
    public static final int READER_CHANNEL_PPT_ID = 10833;
    public static final int READER_CHANNEL_TXT_ID = 10835;
    public static final String READER_STATISTICS_COUNT_CANCEL_LOADED_BTN = "AHNG802";
    public static final String READER_STATISTICS_COUNT_CLICK_LOADED_BTN = "AHNG801";
    public static final String READER_STATISTICS_COUNT_DOC_INTO_BROWSER = "AHNG829";
    public static final String READER_STATISTICS_COUNT_DOC_INTO_DIALOG = "AHNG827";
    public static final String READER_STATISTICS_COUNT_DOC_INTO_DOWNLOAD = "AHNG828";
    public static final String READER_STATISTICS_COUNT_DOC_SEARCH_BTN = "AHNG826";
    public static final String READER_STATISTICS_COUNT_PDF_FOLDER_BTN = "AHNG810";
    public static final String READER_STATISTICS_COUNT_PDF_INTO_BROWSER = "AHNG813";
    public static final String READER_STATISTICS_COUNT_PDF_INTO_DIALOG = "AHNG811";
    public static final String READER_STATISTICS_COUNT_PDF_INTO_DOWNLOAD = "AHNG812";
    public static final String READER_STATISTICS_COUNT_PPT_INTO_BROWSER = "AHNG809";
    public static final String READER_STATISTICS_COUNT_PPT_INTO_DIALOG = "AHNG807";
    public static final String READER_STATISTICS_COUNT_PPT_INTO_DOWNLOAD = "AHNG808";
    public static final String READER_STATISTICS_COUNT_PPT_PLAY_BTN = "AHNG806";
    public static final String READER_STATISTICS_COUNT_RETRY_BTN = "AHNG803";
    public static final String READER_STATISTICS_COUNT_TXT_INTO_BROWSER = "AHNG817";
    public static final String READER_STATISTICS_COUNT_TXT_INTO_DIALOG = "AHNG815";
    public static final String READER_STATISTICS_COUNT_TXT_INTO_DOWNLOAD = "AHNG816";
    public static final String READER_STATISTICS_COUNT_TXT_NOVEL_BTN = "AHNG814";
    public static final String TAG = "TbsReaderView";
    static boolean f = false;
    public static String gReaderPackName = "";
    public static String gReaderPackVersion = "";
    Context a = null;
    ReaderWizard b = null;
    Object c = null;
    ReaderCallback d = null;
    ReaderCallback e = null;

    public interface ReaderCallback {
        public static final int COPY_SELECT_TEXT = 5003;
        public static final int GET_BAR_ANIMATING = 5000;
        public static final int GET_BAR_ISSHOWING = 5024;
        public static final int HIDDEN_BAR = 5001;
        public static final int INSTALL_QB = 5011;
        public static final int NOTIFY_CANDISPLAY = 12;
        public static final int NOTIFY_ERRORCODE = 19;
        public static final int READER_OPEN_QQ_FILE_LIST = 5031;
        public static final int READER_PDF_LIST = 5008;
        public static final int READER_PLUGIN_ACTIVITY_PAUSE = 5032;
        public static final int READER_PLUGIN_CANLOAD = 5013;
        public static final int READER_PLUGIN_COMMAND_FIXSCREEN = 5015;
        public static final int READER_PLUGIN_COMMAND_PDF_LIST = 5036;
        public static final int READER_PLUGIN_COMMAND_PPT_PLAYER = 5035;
        public static final int READER_PLUGIN_COMMAND_ROTATESCREEN = 5018;
        public static final int READER_PLUGIN_COMMAND_TEXT_FIND = 5038;
        public static final int READER_PLUGIN_COMMAND_TEXT_FIND_CLEAR = 5041;
        public static final int READER_PLUGIN_COMMAND_TEXT_FIND_NEXT = 5039;
        public static final int READER_PLUGIN_COMMAND_TEXT_FIND_PREV = 5040;
        public static final int READER_PLUGIN_DOWNLOADING = 5014;
        public static final int READER_PLUGIN_RES_DOC_GUIDE = 5029;
        public static final int READER_PLUGIN_RES_FIXSCREEN_NORMAL = 5016;
        public static final int READER_PLUGIN_RES_FIXSCREEN_PRESS = 5017;
        public static final int READER_PLUGIN_RES_PDF_GUIDE = 5023;
        public static final int READER_PLUGIN_RES_PPT_GUIDE = 5021;
        public static final int READER_PLUGIN_RES_ROTATESCREEN_NORMAL = 5019;
        public static final int READER_PLUGIN_RES_ROTATESCREEN_PRESS = 5020;
        public static final int READER_PLUGIN_RES_TXT_GUIDE = 5022;
        public static final int READER_PLUGIN_SO_ERR = 5025;
        public static final int READER_PLUGIN_SO_INTO_START = 5027;
        public static final int READER_PLUGIN_SO_PROGRESS = 5028;
        public static final int READER_PLUGIN_SO_VERSION = 5030;
        public static final int READER_PLUGIN_STATUS = 5012;
        public static final int READER_PLUGIN_TEXT_FIND_RESULT = 5042;
        public static final int READER_PPT_PLAY_MODEL = 5009;
        public static final int READER_SEARCH_IN_DOCUMENT = 5026;
        public static final int READER_TOAST = 5005;
        public static final int READER_TXT_READING_MODEL = 5010;
        public static final int SEARCH_SELECT_TEXT = 5004;
        public static final int SHOW_BAR = 5002;
        public static final int SHOW_DIALOG = 5006;

        void onCallBackAction(Integer num, Object obj, Object obj2);
    }

    public TbsReaderView(Context context, ReaderCallback readerCallback) throws RuntimeException {
        super(context.getApplicationContext());
        if (context instanceof Activity) {
            this.d = readerCallback;
            this.a = context;
            this.e = new ReaderCallback() {
                /* JADX WARNING: Removed duplicated region for block: B:36:0x016b A[ADDED_TO_REGION] */
                /* JADX WARNING: Removed duplicated region for block: B:39:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public void onCallBackAction(java.lang.Integer r12, java.lang.Object r13, java.lang.Object r14) {
                    /*
                        r11 = this;
                        int r0 = r12.intValue()
                        r1 = 5026(0x13a2, float:7.043E-42)
                        java.lang.String r2 = "docpath"
                        java.lang.String r3 = "channel_id"
                        java.lang.String r4 = "statistics"
                        java.lang.String r5 = "tip"
                        r6 = 5011(0x1393, float:7.022E-42)
                        java.lang.String r7 = ""
                        r8 = 0
                        r9 = 1
                        r10 = 0
                        if (r0 == r1) goto L_0x011b
                        r1 = 5030(0x13a6, float:7.049E-42)
                        if (r0 == r1) goto L_0x0105
                        switch(r0) {
                            case 5008: goto L_0x00b3;
                            case 5009: goto L_0x0069;
                            case 5010: goto L_0x0021;
                            default: goto L_0x001e;
                        }
                    L_0x001e:
                        r9 = 0
                        goto L_0x0165
                    L_0x0021:
                        com.tencent.smtt.sdk.TbsReaderView r0 = com.tencent.smtt.sdk.TbsReaderView.this
                        android.content.Context r0 = r0.a
                        boolean r0 = com.tencent.smtt.sdk.stat.MttLoader.isBrowserInstalledEx(r0)
                        if (r0 != 0) goto L_0x0051
                        java.lang.Integer r12 = java.lang.Integer.valueOf(r6)
                        com.tencent.smtt.sdk.TbsReaderView r13 = com.tencent.smtt.sdk.TbsReaderView.this
                        android.content.Context r13 = r13.a
                        r0 = 5022(0x139e, float:7.037E-42)
                        java.lang.String r13 = com.tencent.smtt.sdk.TbsReaderView.getResString(r13, r0)
                        android.os.Bundle r0 = new android.os.Bundle
                        r0.<init>()
                        r0.putString(r5, r13)
                        java.lang.String r13 = "AHNG816"
                        r0.putString(r4, r13)
                        r13 = 10835(0x2a53, float:1.5183E-41)
                        r0.putInt(r3, r13)
                        com.tencent.smtt.sdk.TbsReaderView r13 = com.tencent.smtt.sdk.TbsReaderView.this
                        java.lang.String r1 = "AHNG815"
                        goto L_0x00e1
                    L_0x0051:
                        if (r13 == 0) goto L_0x005a
                        r8 = r13
                        android.os.Bundle r8 = (android.os.Bundle) r8
                        java.lang.String r7 = r8.getString(r2)
                    L_0x005a:
                        r1 = r7
                        r5 = r8
                        com.tencent.smtt.sdk.TbsReaderView r0 = com.tencent.smtt.sdk.TbsReaderView.this
                        android.content.Context r0 = r0.a
                        r2 = 4
                        r3 = 0
                        java.lang.String r4 = "txt"
                        com.tencent.smtt.sdk.QbSdk.startQBForDoc(r0, r1, r2, r3, r4, r5)
                        goto L_0x0165
                    L_0x0069:
                        com.tencent.smtt.sdk.TbsReaderView r0 = com.tencent.smtt.sdk.TbsReaderView.this
                        android.content.Context r0 = r0.a
                        boolean r0 = com.tencent.smtt.sdk.stat.MttLoader.isBrowserInstalledEx(r0)
                        if (r0 != 0) goto L_0x0098
                        java.lang.Integer r12 = java.lang.Integer.valueOf(r6)
                        com.tencent.smtt.sdk.TbsReaderView r13 = com.tencent.smtt.sdk.TbsReaderView.this
                        android.content.Context r13 = r13.a
                        r0 = 5021(0x139d, float:7.036E-42)
                        java.lang.String r13 = com.tencent.smtt.sdk.TbsReaderView.getResString(r13, r0)
                        android.os.Bundle r0 = new android.os.Bundle
                        r0.<init>()
                        r0.putString(r5, r13)
                        java.lang.String r13 = "AHNG808"
                        r0.putString(r4, r13)
                        r13 = 10833(0x2a51, float:1.518E-41)
                        r0.putInt(r3, r13)
                        com.tencent.smtt.sdk.TbsReaderView r13 = com.tencent.smtt.sdk.TbsReaderView.this
                        java.lang.String r1 = "AHNG807"
                        goto L_0x00e1
                    L_0x0098:
                        if (r13 == 0) goto L_0x00a1
                        r8 = r13
                        android.os.Bundle r8 = (android.os.Bundle) r8
                        java.lang.String r7 = r8.getString(r2)
                    L_0x00a1:
                        r1 = r7
                        r5 = r8
                        com.tencent.smtt.sdk.TbsReaderView r0 = com.tencent.smtt.sdk.TbsReaderView.this
                        android.content.Context r0 = r0.a
                        r2 = 4
                        r3 = 0
                        java.lang.String r4 = ""
                        com.tencent.smtt.sdk.QbSdk.startQBForDoc(r0, r1, r2, r3, r4, r5)
                        com.tencent.smtt.sdk.TbsReaderView r0 = com.tencent.smtt.sdk.TbsReaderView.this
                        java.lang.String r1 = "AHNG809"
                        goto L_0x0101
                    L_0x00b3:
                        com.tencent.smtt.sdk.TbsReaderView r0 = com.tencent.smtt.sdk.TbsReaderView.this
                        android.content.Context r0 = r0.a
                        boolean r0 = com.tencent.smtt.sdk.stat.MttLoader.isBrowserInstalledEx(r0)
                        if (r0 != 0) goto L_0x00e7
                        java.lang.Integer r12 = java.lang.Integer.valueOf(r6)
                        com.tencent.smtt.sdk.TbsReaderView r13 = com.tencent.smtt.sdk.TbsReaderView.this
                        android.content.Context r13 = r13.a
                        r0 = 5023(0x139f, float:7.039E-42)
                        java.lang.String r13 = com.tencent.smtt.sdk.TbsReaderView.getResString(r13, r0)
                        android.os.Bundle r0 = new android.os.Bundle
                        r0.<init>()
                        r0.putString(r5, r13)
                        java.lang.String r13 = "AHNG812"
                        r0.putString(r4, r13)
                        r13 = 10834(0x2a52, float:1.5182E-41)
                        r0.putInt(r3, r13)
                        com.tencent.smtt.sdk.TbsReaderView r13 = com.tencent.smtt.sdk.TbsReaderView.this
                        java.lang.String r1 = "AHNG811"
                    L_0x00e1:
                        r13.userStatistics(r1)
                        r13 = r0
                        goto L_0x001e
                    L_0x00e7:
                        if (r13 == 0) goto L_0x00f0
                        r8 = r13
                        android.os.Bundle r8 = (android.os.Bundle) r8
                        java.lang.String r7 = r8.getString(r2)
                    L_0x00f0:
                        r1 = r7
                        r5 = r8
                        com.tencent.smtt.sdk.TbsReaderView r0 = com.tencent.smtt.sdk.TbsReaderView.this
                        android.content.Context r0 = r0.a
                        r2 = 4
                        r3 = 0
                        java.lang.String r4 = "pdf"
                        com.tencent.smtt.sdk.QbSdk.startQBForDoc(r0, r1, r2, r3, r4, r5)
                        com.tencent.smtt.sdk.TbsReaderView r0 = com.tencent.smtt.sdk.TbsReaderView.this
                        java.lang.String r1 = "AHNG813"
                    L_0x0101:
                        r0.userStatistics(r1)
                        goto L_0x0165
                    L_0x0105:
                        if (r13 == 0) goto L_0x0165
                        r0 = r13
                        android.os.Bundle r0 = (android.os.Bundle) r0
                        java.lang.String r1 = "name"
                        java.lang.String r1 = r0.getString(r1)
                        com.tencent.smtt.sdk.TbsReaderView.gReaderPackName = r1
                        java.lang.String r1 = "version"
                        java.lang.String r0 = r0.getString(r1)
                        com.tencent.smtt.sdk.TbsReaderView.gReaderPackVersion = r0
                        goto L_0x0165
                    L_0x011b:
                        com.tencent.smtt.sdk.TbsReaderView r0 = com.tencent.smtt.sdk.TbsReaderView.this
                        android.content.Context r0 = r0.a
                        boolean r0 = com.tencent.smtt.sdk.stat.MttLoader.isBrowserInstalledEx(r0)
                        if (r0 != 0) goto L_0x014a
                        java.lang.Integer r12 = java.lang.Integer.valueOf(r6)
                        com.tencent.smtt.sdk.TbsReaderView r13 = com.tencent.smtt.sdk.TbsReaderView.this
                        android.content.Context r13 = r13.a
                        r0 = 5029(0x13a5, float:7.047E-42)
                        java.lang.String r13 = com.tencent.smtt.sdk.TbsReaderView.getResString(r13, r0)
                        android.os.Bundle r0 = new android.os.Bundle
                        r0.<init>()
                        r0.putString(r5, r13)
                        java.lang.String r13 = "AHNG828"
                        r0.putString(r4, r13)
                        r13 = 10965(0x2ad5, float:1.5365E-41)
                        r0.putInt(r3, r13)
                        com.tencent.smtt.sdk.TbsReaderView r13 = com.tencent.smtt.sdk.TbsReaderView.this
                        java.lang.String r1 = "AHNG827"
                        goto L_0x00e1
                    L_0x014a:
                        if (r13 == 0) goto L_0x0153
                        r8 = r13
                        android.os.Bundle r8 = (android.os.Bundle) r8
                        java.lang.String r7 = r8.getString(r2)
                    L_0x0153:
                        r1 = r7
                        r5 = r8
                        com.tencent.smtt.sdk.TbsReaderView r0 = com.tencent.smtt.sdk.TbsReaderView.this
                        android.content.Context r0 = r0.a
                        r2 = 4
                        r3 = 0
                        java.lang.String r4 = "doc"
                        com.tencent.smtt.sdk.QbSdk.startQBForDoc(r0, r1, r2, r3, r4, r5)
                        com.tencent.smtt.sdk.TbsReaderView r0 = com.tencent.smtt.sdk.TbsReaderView.this
                        java.lang.String r1 = "AHNG829"
                        goto L_0x0101
                    L_0x0165:
                        com.tencent.smtt.sdk.TbsReaderView r0 = com.tencent.smtt.sdk.TbsReaderView.this
                        com.tencent.smtt.sdk.TbsReaderView$ReaderCallback r0 = r0.d
                        if (r0 == 0) goto L_0x0174
                        if (r9 != 0) goto L_0x0174
                        com.tencent.smtt.sdk.TbsReaderView r0 = com.tencent.smtt.sdk.TbsReaderView.this
                        com.tencent.smtt.sdk.TbsReaderView$ReaderCallback r0 = r0.d
                        r0.onCallBackAction(r12, r13, r14)
                    L_0x0174:
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.TbsReaderView.AnonymousClass1.onCallBackAction(java.lang.Integer, java.lang.Object, java.lang.Object):void");
                }
            };
            return;
        }
        throw new RuntimeException("error: unexpect context(none Activity)");
    }

    static boolean a(Context context) {
        if (!f) {
            f.a(true).a(context.getApplicationContext(), true, false);
            f = f.a(false).b();
        }
        return f;
    }

    public static Drawable getResDrawable(Context context, int i) {
        if (a(context)) {
            return ReaderWizard.getResDrawable(i);
        }
        return null;
    }

    public static String getResString(Context context, int i) {
        return a(context) ? ReaderWizard.getResString(i) : "";
    }

    public static boolean isSupportExt(Context context, String str) {
        return a(context) && ReaderWizard.isSupportCurrentPlatform(context) && ReaderWizard.isSupportExt(str);
    }

    /* access modifiers changed from: package-private */
    public boolean a() {
        try {
            if (this.b == null) {
                this.b = new ReaderWizard(this.e);
            }
            if (this.c == null) {
                this.c = this.b.getTbsReader();
            }
            if (this.c != null) {
                return this.b.initTbsReader(this.c, this.a);
            }
            return false;
        } catch (NullPointerException unused) {
            Log.e(TAG, "Unexpect null object!");
            return false;
        }
    }

    public void doCommand(Integer num, Object obj, Object obj2) {
        Object obj3;
        ReaderWizard readerWizard = this.b;
        if (readerWizard != null && (obj3 = this.c) != null) {
            readerWizard.doCommand(obj3, num, obj, obj2);
        }
    }

    public boolean downloadPlugin(String str) {
        Object obj = this.c;
        if (obj != null) {
            return this.b.checkPlugin(obj, this.a, str, true);
        }
        Log.e(TAG, "downloadPlugin failed!");
        return false;
    }

    public void onSizeChanged(int i, int i2) {
        Object obj;
        ReaderWizard readerWizard = this.b;
        if (readerWizard != null && (obj = this.c) != null) {
            readerWizard.onSizeChanged(obj, i, i2);
        }
    }

    public void onStop() {
        ReaderWizard readerWizard = this.b;
        if (readerWizard != null) {
            readerWizard.destroy(this.c);
            this.c = null;
        }
        this.a = null;
        f = false;
    }

    public void openFile(Bundle bundle) {
        if (this.c == null || bundle == null) {
            Log.e(TAG, "init failed!");
            return;
        }
        bundle.putBoolean("browser6.0", MttLoader.isBrowserInstalledEx(this.a) | (!MttLoader.isBrowserInstalled(this.a)));
        bundle.putBoolean("browser6.1", MttLoader.isGreatBrowserVer(this.a, 6101625, 610000) | (!MttLoader.isBrowserInstalled(this.a)));
        if (!this.b.openFile(this.c, this.a, bundle, this)) {
            Log.e(TAG, "OpenFile failed!");
        }
    }

    public boolean preOpen(String str, boolean z) {
        boolean z2 = false;
        if (!isSupportExt(this.a, str)) {
            Log.e(TAG, "not supported by:" + str);
            return false;
        }
        boolean a2 = a(this.a);
        if (!a2) {
            return a2;
        }
        boolean a3 = a();
        if (!z || !a3) {
            return a3;
        }
        if (Apn.getApnType(this.a) == 3) {
            z2 = true;
        }
        return this.b.checkPlugin(this.c, this.a, str, z2);
    }

    public void userStatistics(String str) {
        ReaderWizard readerWizard = this.b;
        if (readerWizard != null) {
            readerWizard.userStatistics(this.c, str);
        }
    }
}
