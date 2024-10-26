package org.apache.commons.collections4;

import java.util.Set;

public interface SetValuedMap<K, V> extends MultiValuedMap<K, V> {
    Set<V> get(K k);

    Set<V> remove(Object obj);
}
