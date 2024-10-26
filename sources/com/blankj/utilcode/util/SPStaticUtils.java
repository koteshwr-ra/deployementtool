package com.blankj.utilcode.util;

import java.util.Map;
import java.util.Set;

public final class SPStaticUtils {
    private static SPUtils sDefaultSPUtils;

    public static void setDefaultSPUtils(SPUtils sPUtils) {
        sDefaultSPUtils = sPUtils;
    }

    public static void put(String str, String str2) {
        if (str != null) {
            put(str, str2, getDefaultSPUtils());
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static void put(String str, String str2, boolean z) {
        if (str != null) {
            put(str, str2, z, getDefaultSPUtils());
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static String getString(String str) {
        if (str != null) {
            return getString(str, getDefaultSPUtils());
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static String getString(String str, String str2) {
        if (str != null) {
            return getString(str, str2, getDefaultSPUtils());
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static void put(String str, int i) {
        if (str != null) {
            put(str, i, getDefaultSPUtils());
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static void put(String str, int i, boolean z) {
        if (str != null) {
            put(str, i, z, getDefaultSPUtils());
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static int getInt(String str) {
        if (str != null) {
            return getInt(str, getDefaultSPUtils());
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static int getInt(String str, int i) {
        if (str != null) {
            return getInt(str, i, getDefaultSPUtils());
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static void put(String str, long j) {
        if (str != null) {
            put(str, j, getDefaultSPUtils());
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static void put(String str, long j, boolean z) {
        if (str != null) {
            put(str, j, z, getDefaultSPUtils());
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static long getLong(String str) {
        if (str != null) {
            return getLong(str, getDefaultSPUtils());
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static long getLong(String str, long j) {
        if (str != null) {
            return getLong(str, j, getDefaultSPUtils());
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static void put(String str, float f) {
        if (str != null) {
            put(str, f, getDefaultSPUtils());
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static void put(String str, float f, boolean z) {
        if (str != null) {
            put(str, f, z, getDefaultSPUtils());
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static float getFloat(String str) {
        if (str != null) {
            return getFloat(str, getDefaultSPUtils());
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static float getFloat(String str, float f) {
        if (str != null) {
            return getFloat(str, f, getDefaultSPUtils());
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static void put(String str, boolean z) {
        if (str != null) {
            put(str, z, getDefaultSPUtils());
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static void put(String str, boolean z, boolean z2) {
        if (str != null) {
            put(str, z, z2, getDefaultSPUtils());
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static boolean getBoolean(String str) {
        if (str != null) {
            return getBoolean(str, getDefaultSPUtils());
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static boolean getBoolean(String str, boolean z) {
        if (str != null) {
            return getBoolean(str, z, getDefaultSPUtils());
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static void put(String str, Set<String> set) {
        if (str != null) {
            put(str, set, getDefaultSPUtils());
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static void put(String str, Set<String> set, boolean z) {
        if (str != null) {
            put(str, set, z, getDefaultSPUtils());
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static Set<String> getStringSet(String str) {
        if (str != null) {
            return getStringSet(str, getDefaultSPUtils());
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static Set<String> getStringSet(String str, Set<String> set) {
        if (str != null) {
            return getStringSet(str, set, getDefaultSPUtils());
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static Map<String, ?> getAll() {
        return getAll(getDefaultSPUtils());
    }

    public static boolean contains(String str) {
        if (str != null) {
            return contains(str, getDefaultSPUtils());
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static void remove(String str) {
        if (str != null) {
            remove(str, getDefaultSPUtils());
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static void remove(String str, boolean z) {
        if (str != null) {
            remove(str, z, getDefaultSPUtils());
            return;
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static void clear() {
        clear(getDefaultSPUtils());
    }

    public static void clear(boolean z) {
        clear(z, getDefaultSPUtils());
    }

    public static void put(String str, String str2, SPUtils sPUtils) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (sPUtils != null) {
            sPUtils.put(str, str2);
        } else {
            throw new NullPointerException("Argument 'spUtils' of type SPUtils (#2 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }
    }

    public static void put(String str, String str2, boolean z, SPUtils sPUtils) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 4, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (sPUtils != null) {
            sPUtils.put(str, str2, z);
        } else {
            throw new NullPointerException("Argument 'spUtils' of type SPUtils (#3 out of 4, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }
    }

    public static String getString(String str, SPUtils sPUtils) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (sPUtils != null) {
            return sPUtils.getString(str);
        } else {
            throw new NullPointerException("Argument 'spUtils' of type SPUtils (#1 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }
    }

    public static String getString(String str, String str2, SPUtils sPUtils) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (sPUtils != null) {
            return sPUtils.getString(str, str2);
        } else {
            throw new NullPointerException("Argument 'spUtils' of type SPUtils (#2 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }
    }

    public static void put(String str, int i, SPUtils sPUtils) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (sPUtils != null) {
            sPUtils.put(str, i);
        } else {
            throw new NullPointerException("Argument 'spUtils' of type SPUtils (#2 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }
    }

    public static void put(String str, int i, boolean z, SPUtils sPUtils) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 4, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (sPUtils != null) {
            sPUtils.put(str, i, z);
        } else {
            throw new NullPointerException("Argument 'spUtils' of type SPUtils (#3 out of 4, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }
    }

    public static int getInt(String str, SPUtils sPUtils) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (sPUtils != null) {
            return sPUtils.getInt(str);
        } else {
            throw new NullPointerException("Argument 'spUtils' of type SPUtils (#1 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }
    }

    public static int getInt(String str, int i, SPUtils sPUtils) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (sPUtils != null) {
            return sPUtils.getInt(str, i);
        } else {
            throw new NullPointerException("Argument 'spUtils' of type SPUtils (#2 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }
    }

    public static void put(String str, long j, SPUtils sPUtils) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (sPUtils != null) {
            sPUtils.put(str, j);
        } else {
            throw new NullPointerException("Argument 'spUtils' of type SPUtils (#2 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }
    }

    public static void put(String str, long j, boolean z, SPUtils sPUtils) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 4, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (sPUtils != null) {
            sPUtils.put(str, j, z);
        } else {
            throw new NullPointerException("Argument 'spUtils' of type SPUtils (#3 out of 4, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }
    }

    public static long getLong(String str, SPUtils sPUtils) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (sPUtils != null) {
            return sPUtils.getLong(str);
        } else {
            throw new NullPointerException("Argument 'spUtils' of type SPUtils (#1 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }
    }

    public static long getLong(String str, long j, SPUtils sPUtils) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (sPUtils != null) {
            return sPUtils.getLong(str, j);
        } else {
            throw new NullPointerException("Argument 'spUtils' of type SPUtils (#2 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }
    }

    public static void put(String str, float f, SPUtils sPUtils) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (sPUtils != null) {
            sPUtils.put(str, f);
        } else {
            throw new NullPointerException("Argument 'spUtils' of type SPUtils (#2 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }
    }

    public static void put(String str, float f, boolean z, SPUtils sPUtils) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 4, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (sPUtils != null) {
            sPUtils.put(str, f, z);
        } else {
            throw new NullPointerException("Argument 'spUtils' of type SPUtils (#3 out of 4, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }
    }

    public static float getFloat(String str, SPUtils sPUtils) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (sPUtils != null) {
            return sPUtils.getFloat(str);
        } else {
            throw new NullPointerException("Argument 'spUtils' of type SPUtils (#1 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }
    }

    public static float getFloat(String str, float f, SPUtils sPUtils) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (sPUtils != null) {
            return sPUtils.getFloat(str, f);
        } else {
            throw new NullPointerException("Argument 'spUtils' of type SPUtils (#2 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }
    }

    public static void put(String str, boolean z, SPUtils sPUtils) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (sPUtils != null) {
            sPUtils.put(str, z);
        } else {
            throw new NullPointerException("Argument 'spUtils' of type SPUtils (#2 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }
    }

    public static void put(String str, boolean z, boolean z2, SPUtils sPUtils) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 4, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (sPUtils != null) {
            sPUtils.put(str, z, z2);
        } else {
            throw new NullPointerException("Argument 'spUtils' of type SPUtils (#3 out of 4, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }
    }

    public static boolean getBoolean(String str, SPUtils sPUtils) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (sPUtils != null) {
            return sPUtils.getBoolean(str);
        } else {
            throw new NullPointerException("Argument 'spUtils' of type SPUtils (#1 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }
    }

    public static boolean getBoolean(String str, boolean z, SPUtils sPUtils) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (sPUtils != null) {
            return sPUtils.getBoolean(str, z);
        } else {
            throw new NullPointerException("Argument 'spUtils' of type SPUtils (#2 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }
    }

    public static void put(String str, Set<String> set, SPUtils sPUtils) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (sPUtils != null) {
            sPUtils.put(str, set);
        } else {
            throw new NullPointerException("Argument 'spUtils' of type SPUtils (#2 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }
    }

    public static void put(String str, Set<String> set, boolean z, SPUtils sPUtils) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 4, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (sPUtils != null) {
            sPUtils.put(str, set, z);
        } else {
            throw new NullPointerException("Argument 'spUtils' of type SPUtils (#3 out of 4, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }
    }

    public static Set<String> getStringSet(String str, SPUtils sPUtils) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (sPUtils != null) {
            return sPUtils.getStringSet(str);
        } else {
            throw new NullPointerException("Argument 'spUtils' of type SPUtils (#1 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }
    }

    public static Set<String> getStringSet(String str, Set<String> set, SPUtils sPUtils) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (sPUtils != null) {
            return sPUtils.getStringSet(str, set);
        } else {
            throw new NullPointerException("Argument 'spUtils' of type SPUtils (#2 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }
    }

    public static Map<String, ?> getAll(SPUtils sPUtils) {
        if (sPUtils != null) {
            return sPUtils.getAll();
        }
        throw new NullPointerException("Argument 'spUtils' of type SPUtils (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static boolean contains(String str, SPUtils sPUtils) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (sPUtils != null) {
            return sPUtils.contains(str);
        } else {
            throw new NullPointerException("Argument 'spUtils' of type SPUtils (#1 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }
    }

    public static void remove(String str, SPUtils sPUtils) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (sPUtils != null) {
            sPUtils.remove(str);
        } else {
            throw new NullPointerException("Argument 'spUtils' of type SPUtils (#1 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }
    }

    public static void remove(String str, boolean z, SPUtils sPUtils) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (sPUtils != null) {
            sPUtils.remove(str, z);
        } else {
            throw new NullPointerException("Argument 'spUtils' of type SPUtils (#2 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }
    }

    public static void clear(SPUtils sPUtils) {
        if (sPUtils != null) {
            sPUtils.clear();
            return;
        }
        throw new NullPointerException("Argument 'spUtils' of type SPUtils (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static void clear(boolean z, SPUtils sPUtils) {
        if (sPUtils != null) {
            sPUtils.clear(z);
            return;
        }
        throw new NullPointerException("Argument 'spUtils' of type SPUtils (#1 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    private static SPUtils getDefaultSPUtils() {
        SPUtils sPUtils = sDefaultSPUtils;
        return sPUtils != null ? sPUtils : SPUtils.getInstance();
    }
}
