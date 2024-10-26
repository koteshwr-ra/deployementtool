package com.tencent.smtt.sdk.ui.dialog;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.tencent.smtt.sdk.TbsConfig;
import com.tencent.smtt.sdk.stat.MttLoader;
import com.tencent.smtt.sdk.ui.dialog.widget.RoundImageView;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import kotlin.text.Typography;

public class a extends ArrayAdapter<b> implements View.OnClickListener, ListAdapter {
    private ArrayList<b> a;
    private b b = null;
    private Intent c;
    private WeakReference<ListView> d;
    private WeakReference<d> e;
    /* access modifiers changed from: private */
    public b f;
    private b g;
    private List<b> h;
    private Handler i;
    private String[] j;

    public a(Context context, Intent intent, b bVar, List<b> list, b bVar2, d dVar, ListView listView) {
        super(context, 0);
        a(dVar);
        a(listView);
        this.g = bVar;
        this.c = intent;
        if ("com.tencent.rtxlite".equalsIgnoreCase(context.getApplicationContext().getPackageName()) || MttLoader.isBrowserInstalled(context)) {
            this.f = null;
        } else {
            this.f = this.g;
        }
        this.h = list;
        this.i = new Handler() {
            public void handleMessage(Message message) {
                if (message.what == 1) {
                    a.this.b();
                }
            }
        };
        String[] strArr = new String[2];
        this.j = strArr;
        strArr[0] = e.b("x5_tbs_activity_picker_recommend_to_trim");
        this.j[1] = e.b("x5_tbs_activity_picker_recommend_with_chinese_brace_to_trim");
        a(context, bVar2);
    }

    private View a(Context context) {
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setLayoutParams(new AbsListView.LayoutParams(-1, -2));
        linearLayout.setOrientation(1);
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{16842919}, new ColorDrawable(Color.argb(41, 0, 0, 0)));
        stateListDrawable.addState(new int[]{-16842919}, new ColorDrawable(0));
        linearLayout.setBackgroundDrawable(stateListDrawable);
        RelativeLayout relativeLayout = new RelativeLayout(context);
        relativeLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, c.a(getContext(), 144.0f)));
        RoundImageView roundImageView = new RoundImageView(context);
        roundImageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(c.a(getContext(), 96.0f), c.a(getContext(), 96.0f));
        layoutParams.addRule(9);
        layoutParams.addRule(15);
        layoutParams.setMargins(c.a(getContext(), 32.0f), c.a(getContext(), 24.0f), c.a(getContext(), 24.0f), c.a(getContext(), 24.0f));
        roundImageView.setLayoutParams(layoutParams);
        roundImageView.setId(101);
        relativeLayout.addView(roundImageView);
        LinearLayout linearLayout2 = new LinearLayout(context);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams2.addRule(15);
        layoutParams2.addRule(1, 101);
        linearLayout2.setLayoutParams(layoutParams2);
        linearLayout2.setOrientation(1);
        TextView textView = new TextView(context);
        textView.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        textView.setMaxLines(1);
        textView.setTextColor(Color.rgb(29, 29, 29));
        textView.setTextSize(1, 17.0f);
        textView.setId(102);
        linearLayout2.addView(textView);
        TextView textView2 = new TextView(context);
        textView2.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        textView2.setText(e.b("x5_tbs_wechat_activity_picker_label_recommend"));
        textView2.setTextColor(Color.parseColor("#00CAFC"));
        textView2.setTextSize(1, 14.0f);
        textView2.setId(103);
        linearLayout2.addView(textView2);
        relativeLayout.addView(linearLayout2);
        ImageView imageView = new ImageView(context);
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(c.a(getContext(), 48.0f), c.a(getContext(), 48.0f));
        layoutParams3.addRule(11);
        layoutParams3.addRule(15);
        layoutParams3.setMargins(0, 0, c.a(getContext(), 32.0f), 0);
        imageView.setLayoutParams(layoutParams3);
        imageView.setImageDrawable(e.a("x5_tbs_activity_picker_check"));
        imageView.setId(104);
        relativeLayout.addView(imageView);
        relativeLayout.setId(105);
        linearLayout.addView(relativeLayout);
        return linearLayout;
    }

    private void a(b bVar, View view) {
        if (view != null && bVar != null) {
            TextView textView = (TextView) view.findViewById(102);
            TextView textView2 = (TextView) view.findViewById(103);
            ImageView imageView = (ImageView) view.findViewById(104);
            View findViewById = view.findViewById(105);
            View findViewById2 = view.findViewById(106);
            ((ImageView) view.findViewById(101)).setImageDrawable(bVar.a());
            String replaceAll = bVar.b().trim().replaceAll(Typography.nbsp + "", "");
            for (String str : this.j) {
                if (str != null && str.length() > 0) {
                    replaceAll = replaceAll.replaceAll(str, "");
                }
            }
            textView.setText(replaceAll);
            if (bVar.c() == null) {
                bVar.a(a(bVar));
            }
            findViewById.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    ViewParent parent = view.getParent();
                    if (parent instanceof View) {
                        View view2 = (View) parent;
                        if (view2.getTag() == a.this.f) {
                            a.this.onClick(view2);
                        }
                    }
                }
            });
            if (TbsConfig.APP_QB.equals(bVar.d())) {
                textView2.setVisibility(0);
                textView2.setText(bVar.h());
            } else {
                textView2.setVisibility(8);
            }
            findViewById.setClickable(false);
            findViewById.setEnabled(false);
            if (bVar == this.b) {
                imageView.setVisibility(0);
                if (findViewById2 != null) {
                    findViewById2.setVisibility(0);
                }
            } else {
                imageView.setVisibility(8);
                if (findViewById2 != null) {
                    findViewById2.setVisibility(8);
                }
            }
            view.setTag(bVar);
            view.setOnClickListener(this);
        }
    }

    private void a(boolean z) {
        d dVar;
        WeakReference<d> weakReference = this.e;
        if (weakReference != null && (dVar = (d) weakReference.get()) != null) {
            dVar.a(z);
        }
    }

    public static boolean a(Context context, String str) {
        if (str != null && !"".equals(str)) {
            try {
                context.getPackageManager().getApplicationInfo(str, 8192);
                return true;
            } catch (PackageManager.NameNotFoundException unused) {
            }
        }
        return false;
    }

    private void b(Context context, b bVar) {
        this.b = bVar;
        if (bVar != null) {
            a((bVar.e() || this.b.f()) ? true : a(context, this.b.d()));
        }
    }

    public ResolveInfo a(b bVar) {
        if (bVar != null && !TextUtils.isEmpty(bVar.d())) {
            for (ResolveInfo next : getContext().getPackageManager().queryIntentActivities(this.c, 65536)) {
                if (bVar.d().equals(next.activityInfo.packageName)) {
                    return next;
                }
            }
        }
        return null;
    }

    public b a() {
        return this.b;
    }

    /* renamed from: a */
    public b getItem(int i2) {
        if (i2 < 0 || i2 >= this.a.size()) {
            return null;
        }
        return this.a.get(i2);
    }

    /* access modifiers changed from: package-private */
    public void a(Context context, b bVar) {
        b bVar2;
        this.a = new ArrayList<>();
        List<b> list = this.h;
        if (!(list == null || list.size() == 0)) {
            this.a.addAll(this.h);
        }
        boolean z = false;
        boolean z2 = false;
        for (ResolveInfo next : context.getPackageManager().queryIntentActivities(this.c, 65536)) {
            if (b.a(context, next.activityInfo.packageName) != null || next.loadIcon(context.getPackageManager()) != null) {
                b bVar3 = new b(context, next);
                b bVar4 = this.f;
                if (bVar4 != null && bVar4.d().equals(next.activityInfo.packageName)) {
                    bVar3.a(this.f.f());
                    bVar3.a(this.f.h());
                    bVar3.a(this.f.a());
                    this.a.add(0, bVar3);
                    z = true;
                } else if (TbsConfig.APP_QB.equals(next.activityInfo.packageName)) {
                    b bVar5 = this.g;
                    if (bVar5 != null) {
                        bVar3.a(bVar5.f());
                        bVar3.a(this.g.h());
                        bVar3.a(this.g.a());
                    }
                    this.a.add(0, bVar3);
                } else {
                    this.a.add(bVar3);
                }
                if (!z2 && bVar != null && bVar3.d().equals(bVar.d())) {
                    b(context, bVar3);
                    z2 = true;
                }
            }
        }
        if (!z && (bVar2 = this.f) != null) {
            this.a.add(0, bVar2);
        }
        if (!z2 && this.a.size() > 0) {
            b(context, this.a.get(0));
        }
    }

    /* access modifiers changed from: package-private */
    public void a(ListView listView) {
        this.d = new WeakReference<>(listView);
    }

    /* access modifiers changed from: package-private */
    public void a(d dVar) {
        this.e = new WeakReference<>(dVar);
    }

    public void b() {
        View findViewWithTag;
        if (Looper.myLooper() != Looper.getMainLooper()) {
            this.i.obtainMessage(1).sendToTarget();
            return;
        }
        ListView listView = (ListView) this.d.get();
        if (listView != null && (findViewWithTag = listView.findViewWithTag(this.f)) != null) {
            a(this.f, findViewWithTag);
        }
    }

    public int getCount() {
        return this.a.size();
    }

    public View getView(int i2, View view, ViewGroup viewGroup) {
        b a2 = getItem(i2);
        if (a2 == null) {
            return null;
        }
        if (view == null) {
            view = a(getContext());
        }
        a(a2, view);
        return view;
    }

    public void onClick(View view) {
        b bVar;
        Object tag = view.getTag();
        if (tag != null && (tag instanceof b) && (bVar = (b) tag) != this.b) {
            ViewParent parent = view.getParent();
            View view2 = null;
            if (parent instanceof View) {
                view2 = (View) parent;
            }
            b bVar2 = this.b;
            b(view.getContext(), bVar);
            a(bVar2, view2.findViewWithTag(bVar2));
            a(this.b, view);
        }
    }
}
