package org.apache.commons.collections4.collection;

import java.util.Collection;
import java.util.HashMap;
import org.apache.commons.collections4.MultiMap;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.map.MultiValueMap;

public class IndexedCollection<K, C> extends AbstractCollectionDecorator<C> {
    private static final long serialVersionUID = -5512610452568370038L;
    private final MultiMap<K, C> index;
    private final Transformer<C, K> keyTransformer;
    private final boolean uniqueIndex;

    public static <K, C> IndexedCollection<K, C> uniqueIndexedCollection(Collection<C> collection, Transformer<C, K> transformer) {
        return new IndexedCollection<>(collection, transformer, MultiValueMap.multiValueMap(new HashMap()), true);
    }

    public static <K, C> IndexedCollection<K, C> nonUniqueIndexedCollection(Collection<C> collection, Transformer<C, K> transformer) {
        return new IndexedCollection<>(collection, transformer, MultiValueMap.multiValueMap(new HashMap()), false);
    }

    public IndexedCollection(Collection<C> collection, Transformer<C, K> transformer, MultiMap<K, C> multiMap, boolean z) {
        super(collection);
        this.keyTransformer = transformer;
        this.index = multiMap;
        this.uniqueIndex = z;
        reindex();
    }

    public boolean add(C c) {
        boolean add = super.add(c);
        if (add) {
            addToIndex(c);
        }
        return add;
    }

    public boolean addAll(Collection<? extends C> collection) {
        boolean z = false;
        for (Object add : collection) {
            z |= add(add);
        }
        return z;
    }

    public void clear() {
        super.clear();
        this.index.clear();
    }

    public boolean contains(Object obj) {
        return this.index.containsKey(this.keyTransformer.transform(obj));
    }

    public boolean containsAll(Collection<?> collection) {
        for (Object contains : collection) {
            if (!contains(contains)) {
                return false;
            }
        }
        return true;
    }

    public C get(K k) {
        Collection collection = (Collection) this.index.get(k);
        if (collection == null) {
            return null;
        }
        return collection.iterator().next();
    }

    public Collection<C> values(K k) {
        return (Collection) this.index.get(k);
    }

    public void reindex() {
        this.index.clear();
        for (Object addToIndex : decorated()) {
            addToIndex(addToIndex);
        }
    }

    public boolean remove(Object obj) {
        boolean remove = super.remove(obj);
        if (remove) {
            removeFromIndex(obj);
        }
        return remove;
    }

    public boolean removeAll(Collection<?> collection) {
        boolean z = false;
        for (Object remove : collection) {
            z |= remove(remove);
        }
        return z;
    }

    public boolean retainAll(Collection<?> collection) {
        boolean retainAll = super.retainAll(collection);
        if (retainAll) {
            reindex();
        }
        return retainAll;
    }

    private void addToIndex(C c) {
        K transform = this.keyTransformer.transform(c);
        if (!this.uniqueIndex || !this.index.containsKey(transform)) {
            this.index.put(transform, c);
            return;
        }
        throw new IllegalArgumentException("Duplicate key in uniquely indexed collection.");
    }

    private void removeFromIndex(C c) {
        this.index.remove(this.keyTransformer.transform(c));
    }
}
