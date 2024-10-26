package com.noober.background;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.view.LayoutInflaterCompat;
import java.lang.reflect.Field;

public class BackgroundLibrary {
    public static LayoutInflater inject(Context context) {
        LayoutInflater layoutInflater;
        if (context instanceof Activity) {
            layoutInflater = ((Activity) context).getLayoutInflater();
        } else {
            layoutInflater = LayoutInflater.from(context);
        }
        if (layoutInflater == null) {
            return null;
        }
        if (layoutInflater.getFactory2() == null) {
            layoutInflater.setFactory2(setDelegateFactory(context));
        } else if (!(layoutInflater.getFactory2() instanceof BackgroundFactory)) {
            forceSetFactory2(layoutInflater);
        }
        return layoutInflater;
    }

    private static BackgroundFactory setDelegateFactory(Context context) {
        BackgroundFactory backgroundFactory = new BackgroundFactory();
        if (context instanceof AppCompatActivity) {
            final AppCompatDelegate delegate = ((AppCompatActivity) context).getDelegate();
            backgroundFactory.setInterceptFactory(new LayoutInflater.Factory() {
                public View onCreateView(String str, Context context, AttributeSet attributeSet) {
                    return delegate.createView((View) null, str, context, attributeSet);
                }
            });
        }
        return backgroundFactory;
    }

    public static LayoutInflater inject2(Context context) {
        LayoutInflater layoutInflater;
        if (context instanceof Activity) {
            layoutInflater = ((Activity) context).getLayoutInflater();
        } else {
            layoutInflater = LayoutInflater.from(context);
        }
        if (layoutInflater == null) {
            return null;
        }
        forceSetFactory2(layoutInflater);
        return layoutInflater;
    }

    private static void forceSetFactory2(LayoutInflater layoutInflater) {
        Class<LayoutInflater> cls = LayoutInflater.class;
        try {
            Field declaredField = LayoutInflaterCompat.class.getDeclaredField("sCheckedField");
            declaredField.setAccessible(true);
            declaredField.setBoolean(layoutInflater, false);
            Field declaredField2 = cls.getDeclaredField("mFactory");
            declaredField2.setAccessible(true);
            Field declaredField3 = cls.getDeclaredField("mFactory2");
            declaredField3.setAccessible(true);
            BackgroundFactory backgroundFactory = new BackgroundFactory();
            if (layoutInflater.getFactory2() != null) {
                backgroundFactory.setInterceptFactory2(layoutInflater.getFactory2());
            } else if (layoutInflater.getFactory() != null) {
                backgroundFactory.setInterceptFactory(layoutInflater.getFactory());
            }
            declaredField3.set(layoutInflater, backgroundFactory);
            declaredField2.set(layoutInflater, backgroundFactory);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e2) {
            e2.printStackTrace();
        }
    }
}
