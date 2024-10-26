package org.apache.commons.collections4.collection;

import java.util.ArrayList;
import java.util.Collection;
import org.apache.commons.collections4.Transformer;

public class TransformedCollection<E> extends AbstractCollectionDecorator<E> {
    private static final long serialVersionUID = 8692300188161871514L;
    protected final Transformer<? super E, ? extends E> transformer;

    public static <E> TransformedCollection<E> transformingCollection(Collection<E> collection, Transformer<? super E, ? extends E> transformer2) {
        return new TransformedCollection<>(collection, transformer2);
    }

    public static <E> TransformedCollection<E> transformedCollection(Collection<E> collection, Transformer<? super E, ? extends E> transformer2) {
        TransformedCollection<E> transformedCollection = new TransformedCollection<>(collection, transformer2);
        if (collection.size() > 0) {
            Object[] array = collection.toArray();
            collection.clear();
            for (Object transform : array) {
                transformedCollection.decorated().add(transformer2.transform(transform));
            }
        }
        return transformedCollection;
    }

    protected TransformedCollection(Collection<E> collection, Transformer<? super E, ? extends E> transformer2) {
        super(collection);
        if (transformer2 != null) {
            this.transformer = transformer2;
            return;
        }
        throw new NullPointerException("Transformer must not be null");
    }

    /* access modifiers changed from: protected */
    public E transform(E e) {
        return this.transformer.transform(e);
    }

    /* access modifiers changed from: protected */
    public Collection<E> transform(Collection<? extends E> collection) {
        ArrayList arrayList = new ArrayList(collection.size());
        for (Object transform : collection) {
            arrayList.add(transform(transform));
        }
        return arrayList;
    }

    public boolean add(E e) {
        return decorated().add(transform(e));
    }

    public boolean addAll(Collection<? extends E> collection) {
        return decorated().addAll(transform(collection));
    }
}
