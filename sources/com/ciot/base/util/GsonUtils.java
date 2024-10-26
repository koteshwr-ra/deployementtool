package com.ciot.base.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import java.lang.reflect.Type;

public class GsonUtils {
    public static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ";
    private static final Gson sLocalGson = createLocalGson();
    private static final Gson sRemoteGson = createRemoteGson();

    private static GsonBuilder createLocalGsonBuilder() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setLenient();
        gsonBuilder.setDateFormat(DATE_FORMAT);
        return gsonBuilder;
    }

    private static Gson createLocalGson() {
        return createLocalGsonBuilder().create();
    }

    private static Gson createRemoteGson() {
        return createLocalGsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    }

    public static Gson getLocalGson() {
        return sLocalGson;
    }

    public static <T> T fromLocalJson(String str, Class<T> cls) throws JsonSyntaxException {
        try {
            return sLocalGson.fromJson(str, cls);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> T fromLocalJson(String str, Type type) {
        return sLocalGson.fromJson(str, type);
    }

    public static String toJson(Object obj) {
        return sLocalGson.toJson(obj);
    }

    public static String toRemoteJson(Object obj) {
        return sRemoteGson.toJson(obj);
    }
}
