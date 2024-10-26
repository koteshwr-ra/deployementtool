package me.jessyan.retrofiturlmanager.cache;

import java.util.Set;

public interface Cache<K, V> {
    void clear();

    boolean containsKey(K k);

    V get(K k);

    int getMaxSize();

    Set<K> keySet();

    V put(K k, V v);

    V remove(K k);

    int size();
}
