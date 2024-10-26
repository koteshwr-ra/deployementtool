package org.apache.commons.collections4.list;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.collections4.iterators.AbstractIteratorDecorator;
import org.apache.commons.collections4.iterators.AbstractListIteratorDecorator;
import org.apache.commons.collections4.set.UnmodifiableSet;

public class SetUniqueList<E> extends AbstractSerializableListDecorator<E> {
    private static final long serialVersionUID = 7196982186153478694L;
    private final Set<E> set;

    public static <E> SetUniqueList<E> setUniqueList(List<E> list) {
        if (list == null) {
            throw new NullPointerException("List must not be null");
        } else if (list.isEmpty()) {
            return new SetUniqueList<>(list, new HashSet());
        } else {
            ArrayList arrayList = new ArrayList(list);
            list.clear();
            SetUniqueList<E> setUniqueList = new SetUniqueList<>(list, new HashSet());
            setUniqueList.addAll(arrayList);
            return setUniqueList;
        }
    }

    protected SetUniqueList(List<E> list, Set<E> set2) {
        super(list);
        if (set2 != null) {
            this.set = set2;
            return;
        }
        throw new NullPointerException("Set must not be null");
    }

    public Set<E> asSet() {
        return UnmodifiableSet.unmodifiableSet(this.set);
    }

    public boolean add(E e) {
        int size = size();
        add(size(), e);
        return size != size();
    }

    public void add(int i, E e) {
        if (!this.set.contains(e)) {
            super.add(i, e);
            this.set.add(e);
        }
    }

    public boolean addAll(Collection<? extends E> collection) {
        return addAll(size(), collection);
    }

    public boolean addAll(int i, Collection<? extends E> collection) {
        ArrayList arrayList = new ArrayList();
        for (Object next : collection) {
            if (this.set.add(next)) {
                arrayList.add(next);
            }
        }
        return super.addAll(i, arrayList);
    }

    public E set(int i, E e) {
        int indexOf = indexOf(e);
        E e2 = super.set(i, e);
        if (!(indexOf == -1 || indexOf == i)) {
            super.remove(indexOf);
        }
        this.set.remove(e2);
        this.set.add(e);
        return e2;
    }

    public boolean remove(Object obj) {
        boolean remove = this.set.remove(obj);
        if (remove) {
            super.remove(obj);
        }
        return remove;
    }

    public E remove(int i) {
        E remove = super.remove(i);
        this.set.remove(remove);
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
        boolean retainAll = this.set.retainAll(collection);
        if (!retainAll) {
            return false;
        }
        if (this.set.size() == 0) {
            super.clear();
        } else {
            super.retainAll(this.set);
        }
        return retainAll;
    }

    public void clear() {
        super.clear();
        this.set.clear();
    }

    public boolean contains(Object obj) {
        return this.set.contains(obj);
    }

    public boolean containsAll(Collection<?> collection) {
        return this.set.containsAll(collection);
    }

    public Iterator<E> iterator() {
        return new SetListIterator(super.iterator(), this.set);
    }

    public ListIterator<E> listIterator() {
        return new SetListListIterator(super.listIterator(), this.set);
    }

    public ListIterator<E> listIterator(int i) {
        return new SetListListIterator(super.listIterator(i), this.set);
    }

    public List<E> subList(int i, int i2) {
        List subList = super.subList(i, i2);
        return ListUtils.unmodifiableList(new SetUniqueList(subList, createSetBasedOnList(this.set, subList)));
    }

    /* access modifiers changed from: protected */
    public Set<E> createSetBasedOnList(Set<E> set2, List<E> list) {
        Set<E> set3;
        if (set2.getClass().equals(HashSet.class)) {
            set3 = new HashSet<>(list.size());
        } else {
            try {
                set3 = (Set) set2.getClass().newInstance();
            } catch (InstantiationException unused) {
                set3 = new HashSet<>();
            } catch (IllegalAccessException unused2) {
                set3 = new HashSet<>();
            }
        }
        set3.addAll(list);
        return set3;
    }

    static class SetListIterator<E> extends AbstractIteratorDecorator<E> {
        private E last = null;
        private final Set<E> set;

        protected SetListIterator(Iterator<E> it, Set<E> set2) {
            super(it);
            this.set = set2;
        }

        public E next() {
            E next = super.next();
            this.last = next;
            return next;
        }

        public void remove() {
            super.remove();
            this.set.remove(this.last);
            this.last = null;
        }
    }

    static class SetListListIterator<E> extends AbstractListIteratorDecorator<E> {
        private E last = null;
        private final Set<E> set;

        protected SetListListIterator(ListIterator<E> listIterator, Set<E> set2) {
            super(listIterator);
            this.set = set2;
        }

        public E next() {
            E next = super.next();
            this.last = next;
            return next;
        }

        public E previous() {
            E previous = super.previous();
            this.last = previous;
            return previous;
        }

        public void remove() {
            super.remove();
            this.set.remove(this.last);
            this.last = null;
        }

        public void add(E e) {
            if (!this.set.contains(e)) {
                super.add(e);
                this.set.add(e);
            }
        }

        public void set(E e) {
            throw new UnsupportedOperationException("ListIterator does not support set");
        }
    }
}
