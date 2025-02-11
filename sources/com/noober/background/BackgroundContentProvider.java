package com.noober.background;

import android.app.Application;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

public class BackgroundContentProvider extends ContentProvider {
    public int delete(Uri uri, String str, String[] strArr) {
        return 0;
    }

    public String getType(Uri uri) {
        return null;
    }

    public Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        return null;
    }

    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        return 0;
    }

    public boolean onCreate() {
        if (getContext() == null || !(getContext() instanceof Application) || !BLAutoInjectController.isEnableAutoInject()) {
            return true;
        }
        BackgroundLibrary.inject(getContext());
        ((Application) getContext()).registerActivityLifecycleCallbacks(new BLActivityLifecycleRegister());
        return true;
    }
}
