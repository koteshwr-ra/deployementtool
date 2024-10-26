package com.blankj.utilcode.util;

import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public final class GsonUtils {
    private static final Map<String, Gson> GSONS = new ConcurrentHashMap();
    private static final String KEY_DEFAULT = "defaultGson";
    private static final String KEY_DELEGATE = "delegateGson";
    private static final String KEY_LOG_UTILS = "logUtilsGson";

    private GsonUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static void setGsonDelegate(Gson gson) {
        if (gson != null) {
            GSONS.put(KEY_DELEGATE, gson);
        }
    }

    public static void setGson(String str, Gson gson) {
        if (!TextUtils.isEmpty(str) && gson != null) {
            GSONS.put(str, gson);
        }
    }

    public static Gson getGson(String str) {
        return GSONS.get(str);
    }

    public static Gson getGson() {
        Gson gson = GSONS.get(KEY_DELEGATE);
        if (gson != null) {
            return gson;
        }
        Gson gson2 = GSONS.get(KEY_DEFAULT);
        if (gson2 != null) {
            return gson2;
        }
        Gson createGson = createGson();
        GSONS.put(KEY_DEFAULT, createGson);
        return createGson;
    }

    public static String toJson(Object obj) {
        return toJson(getGson(), obj);
    }

    public static String toJson(Object obj, Type type) {
        if (type != null) {
            return toJson(getGson(), obj, type);
        }
        throw new NullPointerException("Argument 'typeOfSrc' of type Type (#1 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static String toJson(Gson gson, Object obj) {
        if (gson != null) {
            return gson.toJson(obj);
        }
        throw new NullPointerException("Argument 'gson' of type Gson (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static String toJson(Gson gson, Object obj, Type type) {
        if (gson == null) {
            throw new NullPointerException("Argument 'gson' of type Gson (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (type != null) {
            return gson.toJson(obj, type);
        } else {
            throw new NullPointerException("Argument 'typeOfSrc' of type Type (#2 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }
    }

    public static <T> T fromJson(String str, Class<T> cls) {
        if (cls != null) {
            return fromJson(getGson(), str, cls);
        }
        throw new NullPointerException("Argument 'type' of type Class<T> (#1 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static <T> T fromJson(String str, Type type) {
        if (type != null) {
            return fromJson(getGson(), str, type);
        }
        throw new NullPointerException("Argument 'type' of type Type (#1 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static <T> T fromJson(Reader reader, Class<T> cls) {
        if (reader == null) {
            throw new NullPointerException("Argument 'reader' of type Reader (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (cls != null) {
            return fromJson(getGson(), reader, cls);
        } else {
            throw new NullPointerException("Argument 'type' of type Class<T> (#1 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }
    }

    public static <T> T fromJson(Reader reader, Type type) {
        if (reader == null) {
            throw new NullPointerException("Argument 'reader' of type Reader (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (type != null) {
            return fromJson(getGson(), reader, type);
        } else {
            throw new NullPointerException("Argument 'type' of type Type (#1 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }
    }

    public static <T> T fromJson(Gson gson, String str, Class<T> cls) {
        if (gson == null) {
            throw new NullPointerException("Argument 'gson' of type Gson (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (cls != null) {
            return gson.fromJson(str, cls);
        } else {
            throw new NullPointerException("Argument 'type' of type Class<T> (#2 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }
    }

    public static <T> T fromJson(Gson gson, String str, Type type) {
        if (gson == null) {
            throw new NullPointerException("Argument 'gson' of type Gson (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (type != null) {
            return gson.fromJson(str, type);
        } else {
            throw new NullPointerException("Argument 'type' of type Type (#2 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }
    }

    public static <T> T fromJson(Gson gson, Reader reader, Class<T> cls) {
        if (gson == null) {
            throw new NullPointerException("Argument 'gson' of type Gson (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (cls != null) {
            return gson.fromJson(reader, cls);
        } else {
            throw new NullPointerException("Argument 'type' of type Class<T> (#2 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }
    }

    public static <T> T fromJson(Gson gson, Reader reader, Type type) {
        if (gson == null) {
            throw new NullPointerException("Argument 'gson' of type Gson (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (type != null) {
            return gson.fromJson(reader, type);
        } else {
            throw new NullPointerException("Argument 'type' of type Type (#2 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }
    }

    public static Type getListType(Type type) {
        if (type != null) {
            return TypeToken.getParameterized(List.class, type).getType();
        }
        throw new NullPointerException("Argument 'type' of type Type (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static Type getSetType(Type type) {
        if (type != null) {
            return TypeToken.getParameterized(Set.class, type).getType();
        }
        throw new NullPointerException("Argument 'type' of type Type (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static Type getMapType(Type type, Type type2) {
        if (type == null) {
            throw new NullPointerException("Argument 'keyType' of type Type (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (type2 != null) {
            return TypeToken.getParameterized(Map.class, type, type2).getType();
        } else {
            throw new NullPointerException("Argument 'valueType' of type Type (#1 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }
    }

    public static Type getArrayType(Type type) {
        if (type != null) {
            return TypeToken.getArray(type).getType();
        }
        throw new NullPointerException("Argument 'type' of type Type (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static Type getType(Type type, Type... typeArr) {
        if (type == null) {
            throw new NullPointerException("Argument 'rawType' of type Type (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        } else if (typeArr != null) {
            return TypeToken.getParameterized(type, typeArr).getType();
        } else {
            throw new NullPointerException("Argument 'typeArguments' of type Type[] (#1 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }
    }

    static Gson getGson4LogUtils() {
        Gson gson = GSONS.get(KEY_LOG_UTILS);
        if (gson != null) {
            return gson;
        }
        Gson create = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        GSONS.put(KEY_LOG_UTILS, create);
        return create;
    }

    private static Gson createGson() {
        return new GsonBuilder().serializeNulls().disableHtmlEscaping().create();
    }
}
