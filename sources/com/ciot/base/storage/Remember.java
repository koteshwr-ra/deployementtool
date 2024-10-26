package com.ciot.base.storage;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import com.google.gson.GsonBuilder;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class Remember {
    private static final Remember INSTANCE = new Remember();
    /* access modifiers changed from: private */
    public static final Object SHARED_PREFS_LOCK = new Object();
    private static final String TAG = Remember.class.getSimpleName();
    private static String mSharedPrefsName;
    private volatile Context mAppContext;
    private ConcurrentMap<String, Object> mData;
    private volatile boolean mWasInitialized = false;

    public interface Callback {
        void apply(Boolean bool);
    }

    private Remember() {
    }

    private void initWithContext(Context context, String str) {
        long uptimeMillis = SystemClock.uptimeMillis();
        this.mAppContext = context.getApplicationContext();
        mSharedPrefsName = str;
        SharedPreferences sharedPreferences = getSharedPreferences();
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        this.mData = concurrentHashMap;
        concurrentHashMap.putAll(sharedPreferences.getAll());
        this.mWasInitialized = true;
        long uptimeMillis2 = SystemClock.uptimeMillis() - uptimeMillis;
        String str2 = TAG;
        Log.i(str2, "Remember took " + uptimeMillis2 + " ms to init");
    }

    public static synchronized Remember init(Context context, String str) {
        Remember remember;
        synchronized (Remember.class) {
            if (context != null) {
                if (!TextUtils.isEmpty(str)) {
                    if (!INSTANCE.mWasInitialized) {
                        INSTANCE.initWithContext(context, str);
                    }
                    remember = INSTANCE;
                }
            }
            throw new RuntimeException("You must provide a valid context and shared prefs name when initializing Remember");
        }
        return remember;
    }

    /* access modifiers changed from: private */
    public static Remember getInstance() {
        if (INSTANCE.mWasInitialized) {
            return INSTANCE;
        }
        throw new RuntimeException("Remember was not initialized! You must call Remember.init() before using this.");
    }

    /* access modifiers changed from: private */
    public SharedPreferences getSharedPreferences() {
        return this.mAppContext.getSharedPreferences(mSharedPrefsName, 0);
    }

    /* access modifiers changed from: private */
    public boolean saveToDisk(String str, Object obj) {
        boolean z;
        synchronized (SHARED_PREFS_LOCK) {
            SharedPreferences.Editor edit = getSharedPreferences().edit();
            boolean z2 = true;
            z = false;
            if (obj instanceof Float) {
                edit.putFloat(str, ((Float) obj).floatValue());
            } else if (obj instanceof Integer) {
                edit.putInt(str, ((Integer) obj).intValue());
            } else if (obj instanceof Long) {
                edit.putLong(str, ((Long) obj).longValue());
            } else if (obj instanceof String) {
                edit.putString(str, (String) obj);
            } else if (obj instanceof Boolean) {
                edit.putBoolean(str, ((Boolean) obj).booleanValue());
            } else {
                z2 = false;
            }
            if (z2) {
                z = edit.commit();
            }
        }
        return z;
    }

    private <T> Remember saveAsync(final String str, final T t, final Callback callback) {
        this.mData.put(str, t);
        new AsyncTask<Void, Void, Boolean>() {
            /* access modifiers changed from: protected */
            public Boolean doInBackground(Void... voidArr) {
                return Boolean.valueOf(Remember.this.saveToDisk(str, t));
            }

            /* access modifiers changed from: protected */
            public void onPostExecute(Boolean bool) {
                Callback callback = callback;
                if (callback != null) {
                    callback.apply(bool);
                }
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        return this;
    }

    public static void clear() {
        getInstance();
        clear((Callback) null);
    }

    public static void clear(final Callback callback) {
        getInstance().mData.clear();
        new AsyncTask<Void, Void, Boolean>() {
            /* access modifiers changed from: protected */
            public Boolean doInBackground(Void... voidArr) {
                Boolean valueOf;
                synchronized (Remember.SHARED_PREFS_LOCK) {
                    SharedPreferences.Editor edit = Remember.getInstance().getSharedPreferences().edit();
                    edit.clear();
                    valueOf = Boolean.valueOf(edit.commit());
                }
                return valueOf;
            }

            /* access modifiers changed from: protected */
            public void onPostExecute(Boolean bool) {
                Callback callback = Callback.this;
                if (callback != null) {
                    callback.apply(bool);
                }
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    public static void remove(String str) {
        getInstance();
        remove(str, (Callback) null);
    }

    public static void remove(final String str, final Callback callback) {
        getInstance().mData.remove(str);
        new AsyncTask<Void, Void, Boolean>() {
            /* access modifiers changed from: protected */
            public Boolean doInBackground(Void... voidArr) {
                Boolean valueOf;
                synchronized (Remember.SHARED_PREFS_LOCK) {
                    SharedPreferences.Editor edit = Remember.getInstance().getSharedPreferences().edit();
                    edit.remove(str);
                    valueOf = Boolean.valueOf(edit.commit());
                }
                return valueOf;
            }

            /* access modifiers changed from: protected */
            public void onPostExecute(Boolean bool) {
                Callback callback = callback;
                if (callback != null) {
                    callback.apply(bool);
                }
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    public static Remember putFloat(String str, float f) {
        return getInstance().saveAsync(str, Float.valueOf(f), (Callback) null);
    }

    public static Remember putInt(String str, int i) {
        return getInstance().saveAsync(str, Integer.valueOf(i), (Callback) null);
    }

    public static Remember putLong(String str, long j) {
        return getInstance().saveAsync(str, Long.valueOf(j), (Callback) null);
    }

    public static Remember putString(String str, String str2) {
        return getInstance().saveAsync(str, str2, (Callback) null);
    }

    public static Remember putBoolean(String str, boolean z) {
        return getInstance().saveAsync(str, Boolean.valueOf(z), (Callback) null);
    }

    public static Remember putFloat(String str, float f, Callback callback) {
        return getInstance().saveAsync(str, Float.valueOf(f), callback);
    }

    public static Remember putInt(String str, int i, Callback callback) {
        return getInstance().saveAsync(str, Integer.valueOf(i), callback);
    }

    public static Remember putLong(String str, long j, Callback callback) {
        return getInstance().saveAsync(str, Long.valueOf(j), callback);
    }

    public static Remember putString(String str, String str2, Callback callback) {
        return getInstance().saveAsync(str, str2, callback);
    }

    public static Remember putBoolean(String str, boolean z, Callback callback) {
        return getInstance().saveAsync(str, Boolean.valueOf(z), callback);
    }

    public static float getFloat(String str, float f) {
        Float f2 = (Float) getInstance().get(str, Float.class);
        return f2 != null ? f2.floatValue() : f;
    }

    public static int getInt(String str, int i) {
        Integer num = (Integer) getInstance().get(str, Integer.class);
        return num != null ? num.intValue() : i;
    }

    public static long getLong(String str, long j) {
        Long l = (Long) getInstance().get(str, Long.class);
        return l != null ? l.longValue() : j;
    }

    public static String getString(String str, String str2) {
        String str3 = (String) getInstance().get(str, String.class);
        return str3 != null ? str3 : str2;
    }

    public static boolean getBoolean(String str, boolean z) {
        Boolean bool = (Boolean) getInstance().get(str, Boolean.class);
        return bool != null ? bool.booleanValue() : z;
    }

    public static boolean containsKey(String str) {
        return getInstance().mData.containsKey(str);
    }

    private <T> T get(String str, Class<T> cls) {
        Object obj = this.mData.get(str);
        if (cls.isInstance(obj)) {
            return cls.cast(obj);
        }
        return null;
    }

    public static <T> T getObject(String str, Class<T> cls) {
        String string = getString(str, (String) null);
        if (!TextUtils.isEmpty(string)) {
            return new GsonBuilder().create().fromJson(string, cls);
        }
        return null;
    }

    public static Remember putObject(String str, Object obj) {
        return putString(str, new GsonBuilder().create().toJson(obj));
    }
}
