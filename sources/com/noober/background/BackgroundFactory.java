package com.noober.background;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import androidx.collection.ArrayMap;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class BackgroundFactory implements LayoutInflater.Factory2 {
    private static final Object[] mConstructorArgs = new Object[2];
    private static final HashMap<String, HashMap<String, Method>> methodMap = new HashMap<>();
    private static final Map<String, Constructor<? extends View>> sConstructorMap = new ArrayMap();
    private static final Class<?>[] sConstructorSignature = {Context.class, AttributeSet.class};
    private LayoutInflater.Factory mViewCreateFactory;
    private LayoutInflater.Factory2 mViewCreateFactory2;

    private static boolean hasStatus(int i, int i2) {
        return (i & i2) == i2;
    }

    public View onCreateView(String str, Context context, AttributeSet attributeSet) {
        View view = null;
        if (str.startsWith("com.noober.background.view")) {
            return null;
        }
        LayoutInflater.Factory2 factory2 = this.mViewCreateFactory2;
        if (factory2 != null) {
            View onCreateView = factory2.onCreateView(str, context, attributeSet);
            view = onCreateView == null ? this.mViewCreateFactory2.onCreateView((View) null, str, context, attributeSet) : onCreateView;
        } else {
            LayoutInflater.Factory factory = this.mViewCreateFactory;
            if (factory != null) {
                view = factory.onCreateView(str, context, attributeSet);
            }
        }
        return setViewBackground(str, context, attributeSet, view);
    }

    public static View setViewBackground(Context context, AttributeSet attributeSet, View view) {
        return setViewBackground((String) null, context, attributeSet, view);
    }

    /* JADX WARNING: Removed duplicated region for block: B:101:0x020d A[Catch:{ Exception -> 0x0250 }] */
    /* JADX WARNING: Removed duplicated region for block: B:108:0x024c  */
    /* JADX WARNING: Removed duplicated region for block: B:118:0x0277 A[DONT_GENERATE] */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x018b A[Catch:{ Exception -> 0x0250 }] */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x0196 A[Catch:{ Exception -> 0x0250 }] */
    /* JADX WARNING: Removed duplicated region for block: B:94:0x01c6 A[Catch:{ Exception -> 0x0250 }] */
    /* JADX WARNING: Removed duplicated region for block: B:97:0x01da A[Catch:{ Exception -> 0x0250 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static android.view.View setViewBackground(java.lang.String r19, android.content.Context r20, android.util.AttributeSet r21, android.view.View r22) {
        /*
            r0 = r20
            r1 = r21
            int[] r2 = com.noober.background.R.styleable.background
            android.content.res.TypedArray r2 = r0.obtainStyledAttributes(r1, r2)
            int[] r3 = com.noober.background.R.styleable.background_press
            android.content.res.TypedArray r3 = r0.obtainStyledAttributes(r1, r3)
            int[] r4 = com.noober.background.R.styleable.background_selector
            android.content.res.TypedArray r4 = r0.obtainStyledAttributes(r1, r4)
            int[] r5 = com.noober.background.R.styleable.text_selector
            android.content.res.TypedArray r5 = r0.obtainStyledAttributes(r1, r5)
            int[] r6 = com.noober.background.R.styleable.background_button_drawable
            android.content.res.TypedArray r6 = r0.obtainStyledAttributes(r1, r6)
            int[] r7 = com.noober.background.R.styleable.bl_other
            android.content.res.TypedArray r7 = r0.obtainStyledAttributes(r1, r7)
            int[] r8 = com.noober.background.R.styleable.bl_anim
            android.content.res.TypedArray r8 = r0.obtainStyledAttributes(r1, r8)
            int[] r9 = com.noober.background.R.styleable.background_multi_selector
            android.content.res.TypedArray r9 = r0.obtainStyledAttributes(r1, r9)
            int[] r10 = com.noober.background.R.styleable.background_multi_selector_text
            android.content.res.TypedArray r10 = r0.obtainStyledAttributes(r1, r10)
            int r11 = android.os.Build.VERSION.SDK_INT
            r12 = 21
            r13 = 0
            if (r11 >= r12) goto L_0x0048
            int[] r11 = com.noober.background.R.styleable.background_selector_pre_21
            android.content.res.TypedArray r11 = r0.obtainStyledAttributes(r1, r11)
            goto L_0x0049
        L_0x0048:
            r11 = r13
        L_0x0049:
            int r14 = r2.getIndexCount()     // Catch:{ Exception -> 0x0254 }
            if (r14 != 0) goto L_0x009a
            int r14 = r4.getIndexCount()     // Catch:{ Exception -> 0x0254 }
            if (r14 != 0) goto L_0x009a
            int r14 = r3.getIndexCount()     // Catch:{ Exception -> 0x0254 }
            if (r14 != 0) goto L_0x009a
            int r14 = r5.getIndexCount()     // Catch:{ Exception -> 0x0254 }
            if (r14 != 0) goto L_0x009a
            int r14 = r6.getIndexCount()     // Catch:{ Exception -> 0x0254 }
            if (r14 != 0) goto L_0x009a
            int r14 = r8.getIndexCount()     // Catch:{ Exception -> 0x0254 }
            if (r14 != 0) goto L_0x009a
            int r14 = r9.getIndexCount()     // Catch:{ Exception -> 0x0254 }
            if (r14 != 0) goto L_0x009a
            int r14 = r10.getIndexCount()     // Catch:{ Exception -> 0x0254 }
            if (r14 != 0) goto L_0x009a
            r2.recycle()
            r3.recycle()
            r4.recycle()
            r5.recycle()
            r6.recycle()
            r7.recycle()
            r8.recycle()
            r9.recycle()
            r10.recycle()
            if (r11 == 0) goto L_0x0099
            r11.recycle()
        L_0x0099:
            return r22
        L_0x009a:
            if (r22 != 0) goto L_0x00a3
            r14 = r19
            android.view.View r1 = createViewFromTag(r0, r14, r1)     // Catch:{ Exception -> 0x0254 }
            goto L_0x00a5
        L_0x00a3:
            r1 = r22
        L_0x00a5:
            if (r1 != 0) goto L_0x00c8
            r2.recycle()
            r3.recycle()
            r4.recycle()
            r5.recycle()
            r6.recycle()
            r7.recycle()
            r8.recycle()
            r9.recycle()
            r10.recycle()
            if (r11 == 0) goto L_0x00c7
            r11.recycle()
        L_0x00c7:
            return r13
        L_0x00c8:
            int r14 = r4.getIndexCount()     // Catch:{ Exception -> 0x0250 }
            if (r14 <= 0) goto L_0x00dd
            int r14 = r9.getIndexCount()     // Catch:{ Exception -> 0x0250 }
            if (r14 > 0) goto L_0x00d5
            goto L_0x00dd
        L_0x00d5:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException     // Catch:{ Exception -> 0x0250 }
            java.lang.String r12 = "Background_selector and background_multi_selector cannot be used simultaneously"
            r0.<init>(r12)     // Catch:{ Exception -> 0x0250 }
            throw r0     // Catch:{ Exception -> 0x0250 }
        L_0x00dd:
            int r14 = r5.getIndexCount()     // Catch:{ Exception -> 0x0250 }
            if (r14 <= 0) goto L_0x00f2
            int r14 = r10.getIndexCount()     // Catch:{ Exception -> 0x0250 }
            if (r14 > 0) goto L_0x00ea
            goto L_0x00f2
        L_0x00ea:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException     // Catch:{ Exception -> 0x0250 }
            java.lang.String r12 = "text_selector and background_multi_selector_text cannot be used simultaneously"
            r0.<init>(r12)     // Catch:{ Exception -> 0x0250 }
            throw r0     // Catch:{ Exception -> 0x0250 }
        L_0x00f2:
            int r14 = r6.getIndexCount()     // Catch:{ Exception -> 0x0250 }
            r15 = 0
            r13 = 1
            if (r14 <= 0) goto L_0x010d
            boolean r14 = r1 instanceof android.widget.CompoundButton     // Catch:{ Exception -> 0x0250 }
            if (r14 == 0) goto L_0x010d
            r1.setClickable(r13)     // Catch:{ Exception -> 0x0250 }
            r14 = r1
            android.widget.CompoundButton r14 = (android.widget.CompoundButton) r14     // Catch:{ Exception -> 0x0250 }
            android.graphics.drawable.StateListDrawable r12 = com.noober.background.drawable.DrawableFactory.getButtonDrawable(r2, r6)     // Catch:{ Exception -> 0x0250 }
            r14.setButtonDrawable(r12)     // Catch:{ Exception -> 0x0250 }
            goto L_0x017f
        L_0x010d:
            int r12 = r4.getIndexCount()     // Catch:{ Exception -> 0x0250 }
            if (r12 <= 0) goto L_0x011f
            android.graphics.drawable.StateListDrawable r12 = com.noober.background.drawable.DrawableFactory.getSelectorDrawable(r2, r4)     // Catch:{ Exception -> 0x0250 }
            r1.setClickable(r13)     // Catch:{ Exception -> 0x0250 }
            setDrawable(r12, r1, r7, r2)     // Catch:{ Exception -> 0x0250 }
        L_0x011d:
            r14 = 0
            goto L_0x0181
        L_0x011f:
            int r12 = r3.getIndexCount()     // Catch:{ Exception -> 0x0250 }
            if (r12 <= 0) goto L_0x0139
            android.graphics.drawable.GradientDrawable r12 = com.noober.background.drawable.DrawableFactory.getDrawable(r2)     // Catch:{ Exception -> 0x0250 }
            android.graphics.drawable.StateListDrawable r14 = com.noober.background.drawable.DrawableFactory.getPressDrawable(r12, r2, r3)     // Catch:{ Exception -> 0x0250 }
            r1.setClickable(r13)     // Catch:{ Exception -> 0x0250 }
            setDrawable(r14, r1, r7, r2)     // Catch:{ Exception -> 0x0250 }
            r18 = r14
            r14 = r12
            r12 = r18
            goto L_0x0181
        L_0x0139:
            int r12 = r9.getIndexCount()     // Catch:{ Exception -> 0x0250 }
            if (r12 <= 0) goto L_0x0147
            android.graphics.drawable.StateListDrawable r12 = com.noober.background.drawable.DrawableFactory.getMultiSelectorDrawable(r0, r9, r2)     // Catch:{ Exception -> 0x0250 }
            setBackground(r12, r1, r2)     // Catch:{ Exception -> 0x0250 }
            goto L_0x011d
        L_0x0147:
            int r12 = r2.getIndexCount()     // Catch:{ Exception -> 0x0250 }
            if (r12 <= 0) goto L_0x0167
            if (r11 == 0) goto L_0x015d
            int r12 = r11.getIndexCount()     // Catch:{ Exception -> 0x0250 }
            if (r12 <= 0) goto L_0x015d
            android.graphics.drawable.StateListDrawable r12 = com.noober.background.drawable.DrawableFactory.getSelectorPre21Drawable(r2)     // Catch:{ Exception -> 0x0250 }
            setDrawable(r12, r1, r7, r2)     // Catch:{ Exception -> 0x0250 }
            goto L_0x011d
        L_0x015d:
            android.graphics.drawable.GradientDrawable r12 = com.noober.background.drawable.DrawableFactory.getDrawable(r2)     // Catch:{ Exception -> 0x0250 }
            setDrawable(r12, r1, r7, r2)     // Catch:{ Exception -> 0x0250 }
            r14 = r12
            r12 = 0
            goto L_0x0181
        L_0x0167:
            int r12 = r8.getIndexCount()     // Catch:{ Exception -> 0x0250 }
            if (r12 <= 0) goto L_0x017f
            android.graphics.drawable.AnimationDrawable r12 = com.noober.background.drawable.DrawableFactory.getAnimationDrawable(r8)     // Catch:{ Exception -> 0x0250 }
            setBackground(r12, r1, r2)     // Catch:{ Exception -> 0x0250 }
            int r14 = com.noober.background.R.styleable.bl_anim_bl_anim_auto_start     // Catch:{ Exception -> 0x0250 }
            boolean r14 = r8.getBoolean(r14, r15)     // Catch:{ Exception -> 0x0250 }
            if (r14 == 0) goto L_0x017f
            r12.start()     // Catch:{ Exception -> 0x0250 }
        L_0x017f:
            r12 = 0
            goto L_0x011d
        L_0x0181:
            boolean r13 = r1 instanceof android.widget.TextView     // Catch:{ Exception -> 0x0250 }
            if (r13 == 0) goto L_0x0196
            int r13 = r5.getIndexCount()     // Catch:{ Exception -> 0x0250 }
            if (r13 <= 0) goto L_0x0196
            r0 = r1
            android.widget.TextView r0 = (android.widget.TextView) r0     // Catch:{ Exception -> 0x0250 }
            android.content.res.ColorStateList r13 = com.noober.background.drawable.DrawableFactory.getTextSelectorColor(r5)     // Catch:{ Exception -> 0x0250 }
            r0.setTextColor(r13)     // Catch:{ Exception -> 0x0250 }
            goto L_0x01aa
        L_0x0196:
            boolean r13 = r1 instanceof android.widget.TextView     // Catch:{ Exception -> 0x0250 }
            if (r13 == 0) goto L_0x01aa
            int r13 = r10.getIndexCount()     // Catch:{ Exception -> 0x0250 }
            if (r13 <= 0) goto L_0x01aa
            r13 = r1
            android.widget.TextView r13 = (android.widget.TextView) r13     // Catch:{ Exception -> 0x0250 }
            android.content.res.ColorStateList r0 = com.noober.background.drawable.DrawableFactory.getMultiTextColorSelectorColorCreator(r0, r10)     // Catch:{ Exception -> 0x0250 }
            r13.setTextColor(r0)     // Catch:{ Exception -> 0x0250 }
        L_0x01aa:
            int r0 = com.noober.background.R.styleable.background_bl_ripple_enable     // Catch:{ Exception -> 0x0250 }
            boolean r0 = r2.getBoolean(r0, r15)     // Catch:{ Exception -> 0x0250 }
            if (r0 == 0) goto L_0x0205
            int r0 = com.noober.background.R.styleable.background_bl_ripple_color     // Catch:{ Exception -> 0x0250 }
            boolean r0 = r2.hasValue(r0)     // Catch:{ Exception -> 0x0250 }
            if (r0 == 0) goto L_0x0205
            int r0 = com.noober.background.R.styleable.background_bl_ripple_color     // Catch:{ Exception -> 0x0250 }
            int r0 = r2.getColor(r0, r15)     // Catch:{ Exception -> 0x0250 }
            int r13 = android.os.Build.VERSION.SDK_INT     // Catch:{ Exception -> 0x0250 }
            r15 = 21
            if (r13 < r15) goto L_0x01da
            if (r12 != 0) goto L_0x01c9
            r12 = r14
        L_0x01c9:
            android.graphics.drawable.RippleDrawable r13 = new android.graphics.drawable.RippleDrawable     // Catch:{ Exception -> 0x0250 }
            android.content.res.ColorStateList r0 = android.content.res.ColorStateList.valueOf(r0)     // Catch:{ Exception -> 0x0250 }
            r13.<init>(r0, r12, r12)     // Catch:{ Exception -> 0x0250 }
            r0 = 1
            r1.setClickable(r0)     // Catch:{ Exception -> 0x0250 }
            setBackground(r13, r1, r2)     // Catch:{ Exception -> 0x0250 }
            goto L_0x0205
        L_0x01da:
            if (r12 != 0) goto L_0x0205
            android.graphics.drawable.StateListDrawable r12 = new android.graphics.drawable.StateListDrawable     // Catch:{ Exception -> 0x0250 }
            r12.<init>()     // Catch:{ Exception -> 0x0250 }
            android.graphics.drawable.GradientDrawable r13 = com.noober.background.drawable.DrawableFactory.getDrawable(r2)     // Catch:{ Exception -> 0x0250 }
            r13.setColor(r0)     // Catch:{ Exception -> 0x0250 }
            r0 = 1
            int[] r15 = new int[r0]     // Catch:{ Exception -> 0x0250 }
            r16 = -16842919(0xfffffffffefeff59, float:-1.6947488E38)
            r17 = 0
            r15[r17] = r16     // Catch:{ Exception -> 0x0250 }
            r12.addState(r15, r14)     // Catch:{ Exception -> 0x0250 }
            int[] r14 = new int[r0]     // Catch:{ Exception -> 0x0250 }
            r15 = 16842919(0x10100a7, float:2.3694026E-38)
            r14[r17] = r15     // Catch:{ Exception -> 0x0250 }
            r12.addState(r14, r13)     // Catch:{ Exception -> 0x0250 }
            r1.setClickable(r0)     // Catch:{ Exception -> 0x0250 }
            setDrawable(r12, r1, r7, r2)     // Catch:{ Exception -> 0x0250 }
        L_0x0205:
            int r0 = com.noober.background.R.styleable.bl_other_bl_function     // Catch:{ Exception -> 0x0250 }
            boolean r0 = r7.hasValue(r0)     // Catch:{ Exception -> 0x0250 }
            if (r0 == 0) goto L_0x022f
            int r0 = com.noober.background.R.styleable.bl_other_bl_function     // Catch:{ Exception -> 0x0250 }
            java.lang.String r0 = r7.getString(r0)     // Catch:{ Exception -> 0x0250 }
            boolean r12 = android.text.TextUtils.isEmpty(r0)     // Catch:{ Exception -> 0x0250 }
            if (r12 != 0) goto L_0x022f
            android.content.Context r12 = r1.getContext()     // Catch:{ Exception -> 0x0250 }
            java.lang.Class r13 = r12.getClass()     // Catch:{ Exception -> 0x0250 }
            java.lang.reflect.Method r0 = getMethod(r13, r0)     // Catch:{ Exception -> 0x0250 }
            if (r0 == 0) goto L_0x022f
            com.noober.background.BackgroundFactory$1 r13 = new com.noober.background.BackgroundFactory$1     // Catch:{ Exception -> 0x0250 }
            r13.<init>(r0, r12)     // Catch:{ Exception -> 0x0250 }
            r1.setOnClickListener(r13)     // Catch:{ Exception -> 0x0250 }
        L_0x022f:
            r2.recycle()
            r3.recycle()
            r4.recycle()
            r5.recycle()
            r6.recycle()
            r7.recycle()
            r8.recycle()
            r9.recycle()
            r10.recycle()
            if (r11 == 0) goto L_0x024f
            r11.recycle()
        L_0x024f:
            return r1
        L_0x0250:
            r0 = move-exception
            goto L_0x0257
        L_0x0252:
            r0 = move-exception
            goto L_0x027b
        L_0x0254:
            r0 = move-exception
            r1 = r22
        L_0x0257:
            r0.printStackTrace()     // Catch:{ all -> 0x0252 }
            r2.recycle()
            r3.recycle()
            r4.recycle()
            r5.recycle()
            r6.recycle()
            r7.recycle()
            r8.recycle()
            r9.recycle()
            r10.recycle()
            if (r11 == 0) goto L_0x027a
            r11.recycle()
        L_0x027a:
            return r1
        L_0x027b:
            r2.recycle()
            r3.recycle()
            r4.recycle()
            r5.recycle()
            r6.recycle()
            r7.recycle()
            r8.recycle()
            r9.recycle()
            r10.recycle()
            if (r11 == 0) goto L_0x029b
            r11.recycle()
        L_0x029b:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.noober.background.BackgroundFactory.setViewBackground(java.lang.String, android.content.Context, android.util.AttributeSet, android.view.View):android.view.View");
    }

    private static Method getMethod(Class cls, String str) {
        Method method;
        HashMap hashMap = methodMap.get(cls.getCanonicalName());
        if (hashMap != null) {
            method = (Method) methodMap.get(cls.getCanonicalName()).get(str);
        } else {
            hashMap = new HashMap();
            methodMap.put(cls.getCanonicalName(), hashMap);
            method = null;
        }
        if (method == null && (method = findMethod(cls, str)) != null) {
            hashMap.put(str, method);
        }
        return method;
    }

    private static Method findMethod(Class cls, String str) {
        try {
            return cls.getMethod(str, new Class[0]);
        } catch (NoSuchMethodException unused) {
            return findDeclaredMethod(cls, str);
        }
    }

    private static Method findDeclaredMethod(Class cls, String str) {
        Method method = null;
        try {
            method = cls.getDeclaredMethod(str, new Class[0]);
            method.setAccessible(true);
            return method;
        } catch (NoSuchMethodException unused) {
            return cls.getSuperclass() != null ? findDeclaredMethod(cls.getSuperclass(), str) : method;
        }
    }

    private static void setDrawable(Drawable drawable, View view, TypedArray typedArray, TypedArray typedArray2) {
        if (!(view instanceof TextView)) {
            setBackground(drawable, view, typedArray2);
        } else if (!typedArray.hasValue(R.styleable.bl_other_bl_position)) {
            setBackground(drawable, view, typedArray2);
        } else if (typedArray.getInt(R.styleable.bl_other_bl_position, 0) == 1) {
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            ((TextView) view).setCompoundDrawables(drawable, (Drawable) null, (Drawable) null, (Drawable) null);
        } else if (typedArray.getInt(R.styleable.bl_other_bl_position, 0) == 2) {
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            ((TextView) view).setCompoundDrawables((Drawable) null, drawable, (Drawable) null, (Drawable) null);
        } else if (typedArray.getInt(R.styleable.bl_other_bl_position, 0) == 4) {
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            ((TextView) view).setCompoundDrawables((Drawable) null, (Drawable) null, drawable, (Drawable) null);
        } else if (typedArray.getInt(R.styleable.bl_other_bl_position, 0) == 8) {
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            ((TextView) view).setCompoundDrawables((Drawable) null, (Drawable) null, (Drawable) null, drawable);
        }
    }

    private static void setBackground(Drawable drawable, View view, TypedArray typedArray) {
        if (typedArray.hasValue(R.styleable.background_bl_stroke_width) && typedArray.hasValue(R.styleable.background_bl_stroke_position)) {
            float dimension = typedArray.getDimension(R.styleable.background_bl_stroke_width, 0.0f);
            int i = typedArray.getInt(R.styleable.background_bl_stroke_position, 0);
            float f = hasStatus(i, 2) ? dimension : -dimension;
            float f2 = hasStatus(i, 4) ? dimension : -dimension;
            float f3 = hasStatus(i, 8) ? dimension : -dimension;
            if (!hasStatus(i, 16)) {
                dimension = -dimension;
            }
            LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{drawable});
            layerDrawable.setLayerInset(0, (int) f, (int) f2, (int) f3, (int) dimension);
            drawable = layerDrawable;
        }
        if (Build.VERSION.SDK_INT >= 16) {
            view.setBackground(drawable);
        } else {
            view.setBackgroundDrawable(drawable);
        }
    }

    public void setInterceptFactory(LayoutInflater.Factory factory) {
        this.mViewCreateFactory = factory;
    }

    public void setInterceptFactory2(LayoutInflater.Factory2 factory2) {
        this.mViewCreateFactory2 = factory2;
    }

    private static View createViewFromTag(Context context, String str, AttributeSet attributeSet) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (str.equals("view")) {
            str = attributeSet.getAttributeValue((String) null, "class");
        }
        try {
            mConstructorArgs[0] = context;
            mConstructorArgs[1] = attributeSet;
            if (-1 == str.indexOf(46)) {
                View createView = "View".equals(str) ? createView(context, str, "android.view.") : null;
                if (createView == null) {
                    createView = createView(context, str, "android.widget.");
                }
                if (createView == null) {
                    createView = createView(context, str, "android.webkit.");
                }
                return createView;
            }
            View createView2 = createView(context, str, (String) null);
            Object[] objArr = mConstructorArgs;
            objArr[0] = null;
            objArr[1] = null;
            return createView2;
        } catch (Exception unused) {
            Log.w("BackgroundLibrary", "cannot create 【" + str + "】 : ");
            return null;
        } finally {
            Object[] objArr2 = mConstructorArgs;
            objArr2[0] = null;
            objArr2[1] = null;
        }
    }

    private static View createView(Context context, String str, String str2) throws InflateException {
        String str3;
        Constructor<? extends U> constructor = sConstructorMap.get(str);
        if (constructor == null) {
            try {
                ClassLoader classLoader = context.getClassLoader();
                if (str2 != null) {
                    str3 = str2 + str;
                } else {
                    str3 = str;
                }
                constructor = classLoader.loadClass(str3).asSubclass(View.class).getConstructor(sConstructorSignature);
                sConstructorMap.put(str, constructor);
            } catch (Exception unused) {
                Log.w("BackgroundLibrary", "cannot create 【" + str + "】 : ");
                return null;
            }
        }
        constructor.setAccessible(true);
        return (View) constructor.newInstance(mConstructorArgs);
    }

    public View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        return onCreateView(str, context, attributeSet);
    }
}
