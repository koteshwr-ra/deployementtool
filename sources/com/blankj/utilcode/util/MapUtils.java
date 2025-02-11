package com.blankj.utilcode.util;

import android.util.Pair;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
import org.apache.log4j.spi.Configurator;

public class MapUtils {

    public interface Closure<K, V> {
        void execute(K k, V v);
    }

    public interface Transformer<K1, V1, K2, V2> {
        Pair<K2, V2> transform(K1 k1, V1 v1);
    }

    private MapUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    @SafeVarargs
    public static <K, V> Map<K, V> newUnmodifiableMap(Pair<K, V>... pairArr) {
        return Collections.unmodifiableMap(newHashMap(pairArr));
    }

    @SafeVarargs
    public static <K, V> HashMap<K, V> newHashMap(Pair<K, V>... pairArr) {
        HashMap<K, V> hashMap = new HashMap<>();
        if (!(pairArr == null || pairArr.length == 0)) {
            for (Pair<K, V> pair : pairArr) {
                if (pair != null) {
                    hashMap.put(pair.first, pair.second);
                }
            }
        }
        return hashMap;
    }

    @SafeVarargs
    public static <K, V> LinkedHashMap<K, V> newLinkedHashMap(Pair<K, V>... pairArr) {
        LinkedHashMap<K, V> linkedHashMap = new LinkedHashMap<>();
        if (!(pairArr == null || pairArr.length == 0)) {
            for (Pair<K, V> pair : pairArr) {
                if (pair != null) {
                    linkedHashMap.put(pair.first, pair.second);
                }
            }
        }
        return linkedHashMap;
    }

    @SafeVarargs
    public static <K, V> TreeMap<K, V> newTreeMap(Comparator<K> comparator, Pair<K, V>... pairArr) {
        if (comparator != null) {
            TreeMap<K, V> treeMap = new TreeMap<>(comparator);
            if (!(pairArr == null || pairArr.length == 0)) {
                for (Pair<K, V> pair : pairArr) {
                    if (pair != null) {
                        treeMap.put(pair.first, pair.second);
                    }
                }
            }
            return treeMap;
        }
        throw new IllegalArgumentException("comparator must not be null");
    }

    @SafeVarargs
    public static <K, V> Hashtable<K, V> newHashTable(Pair<K, V>... pairArr) {
        Hashtable<K, V> hashtable = new Hashtable<>();
        if (!(pairArr == null || pairArr.length == 0)) {
            for (Pair<K, V> pair : pairArr) {
                if (pair != null) {
                    hashtable.put(pair.first, pair.second);
                }
            }
        }
        return hashtable;
    }

    public static boolean isEmpty(Map map) {
        return map == null || map.size() == 0;
    }

    public static boolean isNotEmpty(Map map) {
        return !isEmpty(map);
    }

    public static int size(Map map) {
        if (map == null) {
            return 0;
        }
        return map.size();
    }

    public static <K, V> void forAllDo(Map<K, V> map, Closure<K, V> closure) {
        if (map != null && closure != null) {
            for (Map.Entry next : map.entrySet()) {
                closure.execute(next.getKey(), next.getValue());
            }
        }
    }

    public static <K1, V1, K2, V2> Map<K2, V2> transform(Map<K1, V1> map, final Transformer<K1, V1, K2, V2> transformer) {
        if (!(map == null || transformer == null)) {
            try {
                final Map<K2, V2> map2 = (Map) map.getClass().newInstance();
                forAllDo(map, new Closure<K1, V1>() {
                    public void execute(K1 k1, V1 v1) {
                        Pair transform = transformer.transform(k1, v1);
                        map2.put(transform.first, transform.second);
                    }
                });
                return map2;
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }

    public static String toString(Map map) {
        return map == null ? Configurator.NULL : map.toString();
    }
}
