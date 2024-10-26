package org.apache.commons.collections4.multimap;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import org.apache.commons.collections4.SetUtils;
import org.apache.commons.collections4.SetValuedMap;

public abstract class AbstractSetValuedMap<K, V> extends AbstractMultiValuedMap<K, V> implements SetValuedMap<K, V> {
    /* access modifiers changed from: protected */
    public abstract Set<V> createCollection();

    protected AbstractSetValuedMap() {
    }

    protected AbstractSetValuedMap(Map<K, ? extends Set<V>> map) {
        super(map);
    }

    /* access modifiers changed from: protected */
    public Map<K, Set<V>> getMap() {
        return super.getMap();
    }

    public Set<V> get(K k) {
        return wrappedCollection((Object) k);
    }

    /* access modifiers changed from: package-private */
    public Set<V> wrappedCollection(K k) {
        return new WrappedSet(k);
    }

    public Set<V> remove(Object obj) {
        return SetUtils.emptyIfNull((Set) getMap().remove(obj));
    }

    private class WrappedSet extends AbstractMultiValuedMap<K, V>.WrappedCollection implements Set<V> {
        public WrappedSet(K k) {
            super(k);
        }

        public boolean equals(Object obj) {
            Set set = (Set) getMapping();
            if (set == null) {
                return Collections.emptySet().equals(obj);
            }
            if (!(obj instanceof Set)) {
                return false;
            }
            return SetUtils.isEqualSet(set, (Set) obj);
        }

        public int hashCode() {
            return SetUtils.hashCodeForSet((Set) getMapping());
        }
    }
}
