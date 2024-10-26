package org.apache.commons.collections4.set;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.OrderedIterator;
import org.apache.commons.collections4.functors.UniquePredicate;
import org.apache.commons.collections4.iterators.AbstractIteratorDecorator;
import org.apache.commons.collections4.list.UnmodifiableList;

public class ListOrderedSet<E> extends AbstractSerializableSetDecorator<E> {
    private static final long serialVersionUID = -228664372470420141L;
    private final List<E> setOrder;

    public static <E> ListOrderedSet<E> listOrderedSet(Set<E> set, List<E> list) {
        if (set == null) {
            throw new NullPointerException("Set must not be null");
        } else if (list == null) {
            throw new NullPointerException("List must not be null");
        } else if (set.size() <= 0 && list.size() <= 0) {
            return new ListOrderedSet<>(set, list);
        } else {
            throw new IllegalArgumentException("Set and List must be empty");
        }
    }

    public static <E> ListOrderedSet<E> listOrderedSet(Set<E> set) {
        return new ListOrderedSet<>(set);
    }

    public static <E> ListOrderedSet<E> listOrderedSet(List<E> list) {
        if (list != null) {
            CollectionUtils.filter(list, UniquePredicate.uniquePredicate());
            return new ListOrderedSet<>(new HashSet(list), list);
        }
        throw new NullPointerException("List must not be null");
    }

    public ListOrderedSet() {
        super(new HashSet());
        this.setOrder = new ArrayList();
    }

    protected ListOrderedSet(Set<E> set) {
        super(set);
        this.setOrder = new ArrayList(set);
    }

    protected ListOrderedSet(Set<E> set, List<E> list) {
        super(set);
        if (list != null) {
            this.setOrder = list;
            return;
        }
        throw new NullPointerException("List must not be null");
    }

    public List<E> asList() {
        return UnmodifiableList.unmodifiableList(this.setOrder);
    }

    public void clear() {
        decorated().clear();
        this.setOrder.clear();
    }

    public OrderedIterator<E> iterator() {
        return new OrderedSetIterator(this.setOrder.listIterator(), decorated());
    }

    public boolean add(E e) {
        if (!decorated().add(e)) {
            return false;
        }
        this.setOrder.add(e);
        return true;
    }

    public boolean addAll(Collection<? extends E> collection) {
        boolean z = false;
        for (Object add : collection) {
            z |= add(add);
        }
        return z;
    }

    public boolean remove(Object obj) {
        boolean remove = decorated().remove(obj);
        if (remove) {
            this.setOrder.remove(obj);
        }
        return remove;
    }

    public boolean removeAll(Collection<?> collection) {
        boolean z = false;
        for (Object remove : collection) {
            z |= remove((Object) remove);
        }
        return z;
    }

    public boolean retainAll(Collection<?> collection) {
        boolean retainAll = decorated().retainAll(collection);
        if (!retainAll) {
            return false;
        }
        if (decorated().size() == 0) {
            this.setOrder.clear();
        } else {
            Iterator<E> it = this.setOrder.iterator();
            while (it.hasNext()) {
                if (!decorated().contains(it.next())) {
                    it.remove();
                }
            }
        }
        return retainAll;
    }

    public Object[] toArray() {
        return this.setOrder.toArray();
    }

    public <T> T[] toArray(T[] tArr) {
        return this.setOrder.toArray(tArr);
    }

    public E get(int i) {
        return this.setOrder.get(i);
    }

    public int indexOf(Object obj) {
        return this.setOrder.indexOf(obj);
    }

    public void add(int i, E e) {
        if (!contains(e)) {
            decorated().add(e);
            this.setOrder.add(i, e);
        }
    }

    public boolean addAll(int i, Collection<? extends E> collection) {
        ArrayList arrayList = new ArrayList();
        boolean z = false;
        for (Object next : collection) {
            if (!contains(next)) {
                decorated().add(next);
                arrayList.add(next);
                z = true;
            }
        }
        if (z) {
            this.setOrder.addAll(i, arrayList);
        }
        return z;
    }

    public E remove(int i) {
        E remove = this.setOrder.remove(i);
        remove((Object) remove);
        return remove;
    }

    public String toString() {
        return this.setOrder.toString();
    }

    static class OrderedSetIterator<E> extends AbstractIteratorDecorator<E> implements OrderedIterator<E> {
        private E last;
        private final Collection<E> set;

        private OrderedSetIterator(ListIterator<E> listIterator, Collection<E> collection) {
            super(listIterator);
            this.set = collection;
        }

        public E next() {
            E next = getIterator().next();
            this.last = next;
            return next;
        }

        public void remove() {
            this.set.remove(this.last);
            getIterator().remove();
            this.last = null;
        }

        public boolean hasPrevious() {
            return ((ListIterator) getIterator()).hasPrevious();
        }

        public E previous() {
            E previous = ((ListIterator) getIterator()).previous();
            this.last = previous;
            return previous;
        }
    }
}
