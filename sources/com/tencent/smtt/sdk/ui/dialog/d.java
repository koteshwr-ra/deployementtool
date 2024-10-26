package com.tencent.smtt.sdk.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ResolveInfo;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.ciot.base.constant.NetConstant;
import com.limpoxe.support.servicemanager.ServiceProvider;
import com.tencent.smtt.sdk.TbsConfig;
import com.tencent.smtt.sdk.ValueCallback;
import com.tencent.smtt.sdk.ui.dialog.widget.a;
import com.tencent.smtt.sdk.ui.dialog.widget.b;
import com.tencent.smtt.utils.FileUtil;
import com.tencent.smtt.utils.TbsLog;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.List;
import org.apache.mina.proxy.handlers.http.ntlm.NTLMConstants;

public class d extends Dialog {
    static WeakReference<ValueCallback<String>> a;
    protected List<b> b;
    protected final String c = "extraMenu";
    protected final String d = ServiceProvider.NAME;
    protected final String e = "resource_id";
    protected final String f = "value";
    private ListView g;
    private Button h;
    private Button i;
    private final String j = "TBSActivityPicker";
    private String k;
    /* access modifiers changed from: private */
    public a l;
    private String m = "*/*";
    /* access modifiers changed from: private */
    public String n;
    /* access modifiers changed from: private */
    public Intent o;
    private SharedPreferences p;
    private int q;
    private int r;
    private FrameLayout s;
    private LinearLayout t;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public d(android.content.Context r17, java.lang.String r18, android.content.Intent r19, android.os.Bundle r20, com.tencent.smtt.sdk.ValueCallback<java.lang.String> r21, java.lang.String r22, java.lang.String r23) {
        /*
            r16 = this;
            r0 = r16
            r1 = r17
            r2 = r19
            r3 = r20
            r4 = 16973835(0x103000b, float:2.406093E-38)
            r0.<init>(r1, r4)
            java.lang.String r4 = "TBSActivityPicker"
            r0.j = r4
            java.lang.String r5 = "extraMenu"
            r0.c = r5
            java.lang.String r6 = "name"
            r0.d = r6
            java.lang.String r7 = "resource_id"
            r0.e = r7
            java.lang.String r8 = "value"
            r0.f = r8
            java.lang.String r9 = "*/*"
            r0.m = r9
            r9 = 0
            r0.p = r9
            r10 = 0
            r0.q = r10
            r0.r = r10
            r11 = r23
            r0.n = r11
            android.content.pm.PackageManager r11 = r17.getPackageManager()
            r12 = 65536(0x10000, float:9.18355E-41)
            java.util.List r11 = r11.queryIntentActivities(r2, r12)
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            java.lang.String r13 = "acts.size(): "
            r12.append(r13)
            int r13 = r11.size()
            r12.append(r13)
            java.lang.String r12 = r12.toString()
            com.tencent.smtt.utils.TbsLog.i(r4, r12)
            if (r3 == 0) goto L_0x005b
            android.os.Bundle r3 = r3.getBundle(r5)
            goto L_0x005c
        L_0x005b:
            r3 = r9
        L_0x005c:
            if (r3 == 0) goto L_0x00a3
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>()
            r0.b = r5
            java.util.Set r5 = r3.keySet()
            java.util.Iterator r5 = r5.iterator()
        L_0x006d:
            boolean r12 = r5.hasNext()
            if (r12 == 0) goto L_0x00a8
            java.lang.Object r12 = r5.next()
            java.lang.String r12 = (java.lang.String) r12
            android.os.Bundle r12 = r3.getBundle(r12)
            if (r12 == 0) goto L_0x00a0
            java.lang.String r13 = r12.getString(r6, r9)
            r14 = -1
            int r15 = r12.getInt(r7, r14)
            java.lang.String r12 = r12.getString(r8, r9)
            if (r13 == 0) goto L_0x00a0
            if (r15 == r14) goto L_0x00a0
            if (r12 == 0) goto L_0x00a0
            android.content.Context r14 = r16.getContext()
            java.util.List<com.tencent.smtt.sdk.ui.dialog.b> r9 = r0.b
            com.tencent.smtt.sdk.ui.dialog.b r10 = new com.tencent.smtt.sdk.ui.dialog.b
            r10.<init>(r14, r15, r13, r12)
            r9.add(r10)
        L_0x00a0:
            r9 = 0
            r10 = 0
            goto L_0x006d
        L_0x00a3:
            java.lang.String r3 = "no extra menu info in bundle"
            com.tencent.smtt.utils.TbsLog.i(r4, r3)
        L_0x00a8:
            r3 = 1
            if (r11 == 0) goto L_0x00b1
            int r5 = r11.size()
            if (r5 != 0) goto L_0x00db
        L_0x00b1:
            java.util.List<com.tencent.smtt.sdk.ui.dialog.b> r5 = r0.b
            if (r5 == 0) goto L_0x00bb
            boolean r5 = r5.isEmpty()
            if (r5 == 0) goto L_0x00db
        L_0x00bb:
            boolean r5 = com.tencent.smtt.sdk.stat.MttLoader.isBrowserInstalled(r17)
            if (r5 == 0) goto L_0x00db
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "no action has been found with Intent:"
            r5.append(r6)
            java.lang.String r6 = r19.toString()
            r5.append(r6)
            java.lang.String r5 = r5.toString()
            com.tencent.smtt.utils.TbsLog.i(r4, r5)
            com.tencent.smtt.sdk.QbSdk.isDefaultDialog = r3
        L_0x00db:
            android.content.Context r5 = r17.getApplicationContext()
            java.lang.String r5 = r5.getPackageName()
            java.lang.String r6 = "com.tencent.rtxlite"
            boolean r5 = r6.equalsIgnoreCase(r5)
            if (r5 == 0) goto L_0x0117
            if (r11 == 0) goto L_0x00f3
            int r5 = r11.size()
            if (r5 != 0) goto L_0x0117
        L_0x00f3:
            java.util.List<com.tencent.smtt.sdk.ui.dialog.b> r5 = r0.b
            if (r5 == 0) goto L_0x00fd
            boolean r5 = r5.isEmpty()
            if (r5 == 0) goto L_0x0117
        L_0x00fd:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "package name equal to `com.tencent.rtxlite` but no action has been found with Intent:"
            r5.append(r6)
            java.lang.String r6 = r19.toString()
            r5.append(r6)
            java.lang.String r5 = r5.toString()
            com.tencent.smtt.utils.TbsLog.i(r4, r5)
            com.tencent.smtt.sdk.QbSdk.isDefaultDialog = r3
        L_0x0117:
            r3 = r18
            r0.k = r3
            r0.o = r2
            java.lang.ref.WeakReference r2 = new java.lang.ref.WeakReference
            r3 = r21
            r2.<init>(r3)
            a = r2
            java.lang.String r2 = "tbs_file_open_dialog_config"
            r3 = 0
            android.content.SharedPreferences r1 = r1.getSharedPreferences(r2, r3)
            r0.p = r1
            boolean r1 = android.text.TextUtils.isEmpty(r22)
            if (r1 != 0) goto L_0x0139
            r1 = r22
            r0.m = r1
        L_0x0139:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Intent:"
            r1.append(r2)
            java.lang.String r2 = r0.m
            r1.append(r2)
            java.lang.String r2 = " MineType:"
            r1.append(r2)
            java.lang.String r2 = r0.m
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            com.tencent.smtt.utils.TbsLog.i(r4, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.ui.dialog.d.<init>(android.content.Context, java.lang.String, android.content.Intent, android.os.Bundle, com.tencent.smtt.sdk.ValueCallback, java.lang.String, java.lang.String):void");
    }

    private View a(Context context) {
        this.s = new FrameLayout(context);
        this.t = new LinearLayout(context);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, Double.valueOf((double) (((float) c.a(context)) * 0.5f)).intValue());
        layoutParams.gravity = 17;
        this.t.setLayoutParams(layoutParams);
        this.t.setOrientation(1);
        this.r = c.a(context, 72.0f);
        a aVar = new a(context, (float) c.a(context, 12.0f), (float) c.b(context, 35.0f), (float) c.b(context, 15.0f));
        aVar.setLayoutParams(new LinearLayout.LayoutParams(-1, this.r));
        aVar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                d.this.dismiss();
            }
        });
        this.t.addView(aVar);
        ListView listView = new ListView(context);
        this.g = listView;
        listView.setOverScrollMode(2);
        this.g.setVerticalScrollBarEnabled(false);
        this.g.setBackgroundColor(-1);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, -1);
        layoutParams2.weight = 1.0f;
        layoutParams2.gravity = 16;
        this.g.setLayoutParams(layoutParams2);
        this.g.setDividerHeight(0);
        this.t.addView(this.g);
        LinearLayout linearLayout = new LinearLayout(context);
        this.q = c.a(context, 150.0f);
        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-1, this.q);
        layoutParams3.weight = 0.0f;
        linearLayout.setLayoutParams(layoutParams3);
        linearLayout.setOrientation(0);
        linearLayout.setBackgroundColor(-1);
        linearLayout.setContentDescription("x5_tbs_activity_picker_btn_container");
        this.h = new b(context, c.a(context, 12.0f), Color.parseColor("#EBEDF5"));
        LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(-1, c.a(context, 90.0f));
        layoutParams4.weight = 1.0f;
        layoutParams4.topMargin = c.a(context, 30.0f);
        layoutParams4.bottomMargin = c.a(context, 30.0f);
        layoutParams4.leftMargin = c.a(context, 32.0f);
        layoutParams4.rightMargin = c.a(context, 8.0f);
        this.h.setLayoutParams(layoutParams4);
        this.h.setText(e.b("x5_tbs_wechat_activity_picker_label_always"));
        this.h.setTextColor(Color.rgb(29, 29, 29));
        this.h.setTextSize(0, (float) c.a(context, 34.0f));
        linearLayout.addView(this.h);
        this.i = new b(context, c.a(context, 12.0f), Color.parseColor("#00CAFC"));
        LinearLayout.LayoutParams layoutParams5 = new LinearLayout.LayoutParams(-1, c.a(context, 90.0f));
        layoutParams5.weight = 1.0f;
        layoutParams5.topMargin = c.a(context, 30.0f);
        layoutParams5.bottomMargin = c.a(context, 30.0f);
        layoutParams5.leftMargin = c.a(context, 8.0f);
        layoutParams5.rightMargin = c.a(context, 32.0f);
        this.i.setLayoutParams(layoutParams5);
        this.i.setText(e.b("x5_tbs_wechat_activity_picker_label_once"));
        this.i.setTextColor(Color.rgb(255, 255, 255));
        this.i.setTextSize(0, (float) c.a(context, 34.0f));
        linearLayout.addView(this.i);
        this.t.addView(linearLayout);
        this.s.addView(this.t);
        return this.s;
    }

    /* access modifiers changed from: private */
    public void a(b bVar) {
        if (!bVar.f()) {
            return;
        }
        if (!c() || a.get() == null) {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("https://mdc.html5.qq.com/d/directdown.jsp?channel_id=11041"));
            intent.addFlags(NTLMConstants.FLAG_UNIDENTIFIED_11);
            getContext().startActivity(intent);
            return;
        }
        ((ValueCallback) a.get()).onReceiveValue("https://mdc.html5.qq.com/d/directdown.jsp?channel_id=11047");
    }

    /* access modifiers changed from: private */
    public void b(String str) {
        String str2;
        ValueCallback valueCallback;
        ValueCallback valueCallback2;
        StringBuilder sb;
        String d2;
        if (this.l != null && c()) {
            b a2 = this.l.a();
            ResolveInfo a3 = this.l.a(a2);
            if (a.get() != null) {
                if (a2 != null && a3 != null && a3.activityInfo != null && a3.activityInfo.packageName != null) {
                    valueCallback = (ValueCallback) a.get();
                    str2 = str + a3.activityInfo.packageName;
                } else if (a2 != null) {
                    if (a2.e()) {
                        valueCallback2 = (ValueCallback) a.get();
                        sb = new StringBuilder();
                        sb.append(str);
                        d2 = a2.g();
                    } else if (a2.f()) {
                        valueCallback2 = (ValueCallback) a.get();
                        sb = new StringBuilder();
                        sb.append(str);
                        d2 = a2.d();
                    } else {
                        return;
                    }
                    sb.append(d2);
                    valueCallback2.onReceiveValue(sb.toString());
                    return;
                } else {
                    valueCallback = (ValueCallback) a.get();
                    str2 = str + "other";
                }
                valueCallback.onReceiveValue(str2);
            }
        }
    }

    private Drawable c(String str) {
        Context context = getContext();
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        File file = new File(context.getFilesDir(), str);
        if (!FileUtil.c(file)) {
            return null;
        }
        try {
            TbsLog.i("TBSActivityPicker", "load icon from: " + file.getAbsolutePath());
            return new BitmapDrawable(BitmapFactory.decodeFile(file.getAbsolutePath()));
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    /* access modifiers changed from: private */
    public boolean c() {
        return TbsConfig.APP_QQ.equals(getContext().getApplicationContext().getPackageName());
    }

    private void d() {
        String str;
        String str2;
        a aVar = this.l;
        Drawable drawable = null;
        b a2 = aVar != null ? aVar.a() : null;
        SharedPreferences sharedPreferences = this.p;
        if (sharedPreferences != null) {
            Drawable c2 = c(sharedPreferences.getString("key_tbs_recommend_icon_url", (String) null));
            str2 = this.p.getString("key_tbs_recommend_label", (String) null);
            str = this.p.getString("key_tbs_recommend_description", (String) null);
            if (TextUtils.isEmpty(str2)) {
                str2 = null;
            }
            if (TextUtils.isEmpty(str)) {
                str = null;
            }
            drawable = c2;
        } else {
            str2 = null;
            str = null;
        }
        if (drawable == null) {
            drawable = e.a("application_icon");
        }
        Drawable drawable2 = drawable;
        if (str2 == null) {
            str2 = "QQ浏览器";
        }
        String str3 = str2;
        if (str == null) {
            str = e.b("x5_tbs_wechat_activity_picker_label_recommend");
        }
        a aVar2 = new a(getContext(), this.o, new b(getContext(), drawable2, str3, TbsConfig.APP_QB, str), this.b, a2, this, this.g);
        this.l = aVar2;
        this.g.setAdapter(aVar2);
        e();
    }

    private void e() {
        ListAdapter adapter = this.g.getAdapter();
        if (adapter != null) {
            int i2 = 0;
            for (int i3 = 0; i3 < adapter.getCount(); i3++) {
                View view = adapter.getView(i3, (View) null, this.g);
                view.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
                i2 += view.getMeasuredHeight();
            }
            ViewGroup.LayoutParams layoutParams = this.t.getLayoutParams();
            float a2 = (float) c.a(getContext());
            layoutParams.height = Double.valueOf(Math.max(Math.min((double) (this.r + i2 + this.q), (double) (0.9f * a2)), (double) (a2 * 0.5f))).intValue();
            this.t.setLayoutParams(layoutParams);
        }
    }

    public String a() {
        if (this.p == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("getTBSPickedDefaultBrowser: ");
        SharedPreferences sharedPreferences = this.p;
        sb.append(sharedPreferences.getString("key_tbs_picked_default_browser_" + this.m, (String) null));
        TbsLog.i("TBSActivityPicker", sb.toString());
        SharedPreferences sharedPreferences2 = this.p;
        return sharedPreferences2.getString("key_tbs_picked_default_browser_" + this.m, (String) null);
    }

    public void a(String str) {
        SharedPreferences.Editor editor;
        TbsLog.i("TBSActivityPicker", "setTBSPickedDefaultBrowser:" + str);
        if (this.p != null) {
            if (TextUtils.isEmpty(str)) {
                TbsLog.i("TBSActivityPicker", "paramString empty, remove: key_tbs_picked_default_browser_" + this.m);
                SharedPreferences.Editor edit = this.p.edit();
                editor = edit.remove("key_tbs_picked_default_browser_" + this.m);
            } else {
                TbsLog.i("TBSActivityPicker", "paramString not empty, set: key_tbs_picked_default_browser_" + this.m + "=" + str);
                SharedPreferences.Editor edit2 = this.p.edit();
                StringBuilder sb = new StringBuilder();
                sb.append("key_tbs_picked_default_browser_");
                sb.append(this.m);
                editor = edit2.putString(sb.toString(), str);
            }
            editor.commit();
        }
    }

    /* access modifiers changed from: package-private */
    public void a(boolean z) {
        Button button = this.i;
        if (button != null) {
            button.setEnabled(z);
        }
        Button button2 = this.h;
        if (button2 != null) {
            button2.setEnabled(z);
        }
        b("userMenuClickEvent:");
    }

    public void b() {
        Window window = getWindow();
        if (window != null) {
            window.setBackgroundDrawable(new ColorDrawable(0));
            window.setGravity(80);
            window.setLayout(-1, -2);
            window.getDecorView().setPadding(0, 0, 0, 0);
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.width = -1;
            attributes.horizontalMargin = 0.0f;
            attributes.dimAmount = 0.5f;
            window.setAttributes(attributes);
        }
        setContentView(a(getContext()));
        d();
        this.h.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                b a2 = d.this.l.a();
                ResolveInfo a3 = d.this.l.a(a2);
                d.this.b("userClickAlwaysEvent:");
                if (a2 != null) {
                    if (a2.e()) {
                        String g = a2.g();
                        if (d.a.get() != null) {
                            ((ValueCallback) d.a.get()).onReceiveValue("extraMenuEvent:" + g);
                        }
                        d dVar = d.this;
                        dVar.a("extraMenuEvent:" + g);
                    } else if (a3 == null) {
                        d.this.a(a2);
                    } else {
                        Intent b = d.this.o;
                        Context context = d.this.getContext();
                        String str = a3.activityInfo.packageName;
                        b.setPackage(str);
                        if (TbsConfig.APP_QB.equals(str)) {
                            b.putExtra("ChannelID", context.getApplicationContext().getPackageName());
                            b.putExtra("PosID", NetConstant.PAGE_ID_CONSULTATION);
                        }
                        if (context != null && context.getApplicationInfo().targetSdkVersion >= 24 && Build.VERSION.SDK_INT >= 24) {
                            b.addFlags(1);
                        }
                        if (!TextUtils.isEmpty(d.this.n)) {
                            b.putExtra("big_brother_source_key", d.this.n);
                        }
                        try {
                            context.startActivity(b);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        if (d.a.get() != null) {
                            ((ValueCallback) d.a.get()).onReceiveValue("always");
                        }
                        d.this.a(str);
                    }
                    d.this.dismiss();
                }
            }
        });
        this.i.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                b a2 = d.this.l.a();
                ResolveInfo a3 = d.this.l.a(a2);
                d.this.b("userClickOnceEvent:");
                d.this.a("");
                if (a2 != null) {
                    if (!a2.e()) {
                        if (a3 == null) {
                            d.this.a(a2);
                        } else {
                            Intent b = d.this.o;
                            Context context = d.this.getContext();
                            String str = a3.activityInfo.packageName;
                            b.setPackage(str);
                            if (TbsConfig.APP_QB.equals(str)) {
                                b.putExtra("ChannelID", context.getApplicationContext().getPackageName());
                                b.putExtra("PosID", NetConstant.PAGE_ID_CONSULTATION);
                            }
                            if (context.getApplicationInfo().targetSdkVersion >= 24 && Build.VERSION.SDK_INT >= 24) {
                                b.addFlags(1);
                            }
                            if (!TextUtils.isEmpty(d.this.n)) {
                                b.putExtra("big_brother_source_key", d.this.n);
                            }
                            try {
                                context.startActivity(b);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            if (d.a.get() != null) {
                                ((ValueCallback) d.a.get()).onReceiveValue("once");
                            }
                        }
                    } else if (d.this.c() && d.a.get() != null) {
                        ((ValueCallback) d.a.get()).onReceiveValue("extraMenuEvent:" + a2.g());
                    }
                    d.this.dismiss();
                }
            }
        });
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        b();
    }
}
