package com.blankj.utilcode.util;

import android.content.SharedPreferences;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public final class SPUtils {
    private static final Map<String, SPUtils> SP_UTILS_MAP = new HashMap();
    private SharedPreferences sp;

    public static SPUtils getInstance() {
        return getInstance("", 0);
    }

    public static SPUtils getInstance(int i) {
        return getInstance("", i);
    }

    public static SPUtils getInstance(String str) {
        return getInstance(str, 0);
    }

    public static SPUtils getInstance(String str, int i) {
        if (isSpace(str)) {
            str = "spUtils";
        }
        SPUtils sPUtils = SP_UTILS_MAP.get(str);
        if (sPUtils == null) {
            synchronized (SPUtils.class) {
                sPUtils = SP_UTILS_MAP.get(str);
                if (sPUtils == null) {
                    sPUtils = new SPUtils(str, i);
                    SP_UTILS_MAP.put(str, sPUtils);
                }
            }
        }
        return sPUtils;
    }

    private SPUtils(String str) {
        this.sp = Utils.getApp().getSharedPreferences(str, 0);
    }

    private SPUtils(String str, int i) {
        this.sp = Utils.getApp().getSharedPreferences(str, i);
    }

    public void put(String str, String str2) {
        if (str != null) {
            put(str, str2, false);
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public void put(String str, String str2, boolean z) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (z) {
            this.sp.edit().putString(str, str2).commit();
        } else {
            this.sp.edit().putString(str, str2).apply();
        }
    }

    public String getString(String str) {
        if (str != null) {
            return getString(str, "");
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public String getString(String str, String str2) {
        if (str != null) {
            return this.sp.getString(str, str2);
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public void put(String str, int i) {
        if (str != null) {
            put(str, i, false);
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public void put(String str, int i, boolean z) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (z) {
            this.sp.edit().putInt(str, i).commit();
        } else {
            this.sp.edit().putInt(str, i).apply();
        }
    }

    public int getInt(String str) {
        if (str != null) {
            return getInt(str, -1);
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public int getInt(String str, int i) {
        if (str != null) {
            return this.sp.getInt(str, i);
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public void put(String str, long j) {
        if (str != null) {
            put(str, j, false);
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public void put(String str, long j, boolean z) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (z) {
            this.sp.edit().putLong(str, j).commit();
        } else {
            this.sp.edit().putLong(str, j).apply();
        }
    }

    public long getLong(String str) {
        if (str != null) {
            return getLong(str, -1);
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public long getLong(String str, long j) {
        if (str != null) {
            return this.sp.getLong(str, j);
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public void put(String str, float f) {
        if (str != null) {
            put(str, f, false);
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public void put(String str, float f, boolean z) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (z) {
            this.sp.edit().putFloat(str, f).commit();
        } else {
            this.sp.edit().putFloat(str, f).apply();
        }
    }

    public float getFloat(String str) {
        if (str != null) {
            return getFloat(str, -1.0f);
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public float getFloat(String str, float f) {
        if (str != null) {
            return this.sp.getFloat(str, f);
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public void put(String str, boolean z) {
        if (str != null) {
            put(str, z, false);
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public void put(String str, boolean z, boolean z2) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (z2) {
            this.sp.edit().putBoolean(str, z).commit();
        } else {
            this.sp.edit().putBoolean(str, z).apply();
        }
    }

    public boolean getBoolean(String str) {
        if (str != null) {
            return getBoolean(str, false);
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public boolean getBoolean(String str, boolean z) {
        if (str != null) {
            return this.sp.getBoolean(str, z);
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public void put(String str, Set<String> set) {
        if (str != null) {
            put(str, set, false);
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public void put(String str, Set<String> set, boolean z) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (z) {
            this.sp.edit().putStringSet(str, set).commit();
        } else {
            this.sp.edit().putStringSet(str, set).apply();
        }
    }

    public Set<String> getStringSet(String str) {
        if (str != null) {
            return getStringSet(str, Collections.emptySet());
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public Set<String> getStringSet(String str, Set<String> set) {
        if (str != null) {
            return this.sp.getStringSet(str, set);
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public Map<String, ?> getAll() {
        return this.sp.getAll();
    }

    public boolean contains(String str) {
        if (str != null) {
            return this.sp.contains(str);
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public void remove(String str) {
        if (str != null) {
            remove(str, false);
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public void remove(String str, boolean z) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (z) {
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
