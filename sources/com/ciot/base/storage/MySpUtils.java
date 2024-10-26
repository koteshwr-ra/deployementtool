package com.ciot.base.storage;

import android.content.SharedPreferences;
import com.ciot.base.util.ContextUtil;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public final class MySpUtils {
    private static final String DEFAULT_SP_NAME = "CIOT_SP_DATA";
    private static final Map<String, MySpUtils> SP_UTILS_MAP = new HashMap();
    private SharedPreferences sp;

    public static MySpUtils getInstance() {
        return getInstance("CIOT_SP_DATA", 0);
    }

    public static MySpUtils getInstance(int i) {
        return getInstance("CIOT_SP_DATA", i);
    }

    public static MySpUtils getInstance(String str) {
        return getInstance(str, 0);
    }

    public static MySpUtils getInstance(String str, int i) {
        if (isSpace(str)) {
            str = "CIOT_SP_DATA";
        }
        MySpUtils mySpUtils = SP_UTILS_MAP.get(str);
        if (mySpUtils == null) {
            synchronized (MySpUtils.class) {
                mySpUtils = SP_UTILS_MAP.get(str);
                if (mySpUtils == null) {
                    mySpUtils = new MySpUtils(str, i);
                    SP_UTILS_MAP.put(str, mySpUtils);
                }
            }
        }
        return mySpUtils;
    }

    private MySpUtils(String str) {
        this.sp = ContextUtil.getContext().getSharedPreferences(str, 0);
    }

    private MySpUtils(String str, int i) {
        this.sp = ContextUtil.getContext().getSharedPreferences(str, i);
    }

    public void put(String str, String str2) {
        put(str, str2, false);
    }

    public void putString(String str, String str2) {
        putString(str, str2, false);
    }

    public void put(String str, int i) {
        put(str, i, false);
    }

    public void put(String str, long j) {
        put(str, j, false);
    }

    public void put(String str, long j, boolean z) {
        if (z) {
            this.sp.edit().putLong(str, j).commit();
        } else {
            this.sp.edit().putLong(str, j).apply();
        }
    }

    public void put(String str, float f) {
        put(str, f, false);
    }

    public void put(String str, float f, boolean z) {
        if (z) {
            this.sp.edit().putFloat(str, f).commit();
        } else {
            this.sp.edit().putFloat(str, f).apply();
        }
    }

    public void put(String str, boolean z, boolean z2) {
        if (z2) {
            this.sp.edit().putBoolean(str, z).commit();
        } else {
            this.sp.edit().putBoolean(str, z).apply();
        }
    }

    public void put(String str, int i, boolean z) {
        if (z) {
            this.sp.edit().putInt(str, i).commit();
        } else {
            this.sp.edit().putInt(str, i).apply();
        }
    }

    public void putString(String str, String str2, boolean z) {
        if (z) {
            this.sp.edit().putString(str, str2).commit();
        } else {
            this.sp.edit().putString(str, str2).apply();
        }
    }

    public void put(String str, String str2, boolean z) {
        if (z) {
            this.sp.edit().putString(str, str2).commit();
        } else {
            this.sp.edit().putString(str, str2).apply();
        }
    }

    public String getString(String str) {
        return getString(str, "");
    }

    public String getString(String str, String str2) {
        return this.sp.getString(str, str2);
    }

    public void putInt(String str, int i) {
        putInt(str, i, false);
    }

    public void putInt(String str, int i, boolean z) {
        if (z) {
            this.sp.edit().putInt(str, i).commit();
        } else {
            this.sp.edit().putInt(str, i).apply();
        }
    }

    public int getInt(String str) {
        return getInt(str, -1);
    }

    public int getInt(String str, int i) {
        return this.sp.getInt(str, i);
    }

    public void putLong(String str, long j) {
        putLong(str, j, false);
    }

    public void putLong(String str, long j, boolean z) {
        if (z) {
            this.sp.edit().putLong(str, j).commit();
        } else {
            this.sp.edit().putLong(str, j).apply();
        }
    }

    public long getLong(String str) {
        return getLong(str, -1);
    }

    public long getLong(String str, long j) {
        return this.sp.getLong(str, j);
    }

    public void putFloat(String str, float f) {
        putFloat(str, f, false);
    }

    public void putFloat(String str, float f, boolean z) {
        if (z) {
            this.sp.edit().putFloat(str, f).commit();
        } else {
            this.sp.edit().putFloat(str, f).apply();
        }
    }

    public float getFloat(String str) {
        return getFloat(str, -1.0f);
    }

    public float getFloat(String str, float f) {
        return this.sp.getFloat(str, f);
    }

    public void putBoolean(String str, boolean z) {
        putBoolean(str, z, false);
    }

    public void put(String str, boolean z) {
        putBoolean(str, z, false);
    }

    public void putBoolean(String str, boolean z, boolean z2) {
        if (z2) {
            this.sp.edit().putBoolean(str, z).commit();
        } else {
            this.sp.edit().putBoolean(str, z).apply();
        }
    }

    public boolean getBoolean(String str) {
        return getBoolean(str, false);
    }

    public boolean getBoolean(String str, boolean z) {
        return this.sp.getBoolean(str, z);
    }

    public void putSet(String str, Set<String> set) {
        putSet(str, set, false);
    }

    public void putSet(String str, Set<String> set, boolean z) {
        if (z) {
            this.sp.edit().putStringSet(str, set).commit();
        } else {
            this.sp.edit().putStringSet(str, set).apply();
        }
    }

    public Set<String> getStringSet(String str) {
        return getStringSet(str, Collections.emptySet());
    }

    public Set<String> getStringSet(String str, Set<String> set) {
        return this.sp.getStringSet(str, set);
    }

    public Map<String, ?> getAll() {
        return this.sp.getAll();
    }

    public boolean contains(String str) {
        return this.sp.contains(str);
    }

    public void remove(String str) {
        remove(str, false);
    }

    public void remove(String str, boolean z) {
        if (z) {
            this.sp.edit().remove(str).commit();
        } else {
            this.sp.edit().remove(str).apply();
        }
    }

    public void clear() {
        clear(false);
    }

    public void clear(boolean z) {
        if (z) {
            this.sp.edit().clear().commit();
        } else {
            this.sp.edit().clear().apply();
        }
    }

    private static boolean isSpace(String str) {
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
}
