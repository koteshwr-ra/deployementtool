package org.apache.commons.collections4.set;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.iterators.EmptyIterator;
import org.apache.commons.collections4.iterators.IteratorChain;
import org.apache.commons.collections4.list.UnmodifiableList;

public class CompositeSet<E> implements Set<E>, Serializable {
    private static final long serialVersionUID = 5185069727540378940L;
    private final List<Set<E>> all = new ArrayList();
    private SetMutator<E> mutator;

    public interface SetMutator<E> extends Serializable {
        boolean add(CompositeSet<E> compositeSet, List<Set<E>> list, E e);

        boolean addAll(CompositeSet<E> compositeSet, List<Set<E>> list, Collection<? extends E> collection);

        void resolveCollision(CompositeSet<E> compositeSet, Set<E> set, Set<E> set2, Collection<E> collection);
    }

    public CompositeSet() {
    }

    public CompositeSet(Set<E> set) {
        addComposited(set);
    }

    public CompositeSet(Set<E>... setArr) {
        addComposited(setArr);
    }

    public int size() {
        int i = 0;
        for (Set<E> size : this.all) {
            i += size.size();
        }
        return i;
    }

    public boolean isEmpty() {
        for (Set<E> isEmpty : this.all) {
            if (!isEmpty.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public boolean contains(Object obj) {
        for (Set<E> contains : this.all) {
            if (contains.contains(obj)) {
                return true;
            }
        }
        return false;
    }

    public Iterator<E> iterator() {
        if (this.all.isEmpty()) {
            return EmptyIterator.emptyIterator();
        }
        IteratorChain iteratorChain = new IteratorChain();
        for (Set<E> it : this.all) {
            iteratorChain.addIterator(it.iterator());
        }
        return iteratorChain;
    }

    public Object[] toArray() {
        Object[] objArr = new Object[size()];
        Iterator it = iterator();
        int i = 0;
        while (it.hasNext()) {
            objArr[i] = it.next();
            i++;
        }
        return objArr;
    }

    public <T> T[] toArray(T[] tArr) {
        int size = size();
        if (tArr.length < size) {
            tArr = (Object[]) Array.newInstance(tArr.getClass().getComponentType(), size);
        }
        int i = 0;
        for (Set<E> it : this.all) {
            for (E e : it) {
                tArr[i] = e;
                i++;
            }
        }
        if (tArr.length > size) {
            tArr[size] = null;
        }
        return (Object[]) tArr;
    }

    public boolean add(E e) {
        SetMutator<E> setMutator = this.mutator;
        if (setMutator != null) {
            return setMutator.add(this, this.all, e);
        }
        throw new UnsupportedOperationException("add() is not supported on CompositeSet without a SetMutator strategy");
    }

    public boolean remove(Object obj) {
        for (Set set : getSets()) {
            if (set.contains(obj)) {
                return set.remove(obj);
            }
        }
        return false;
    }

    public boolean containsAll(Collection<?> collection) {
        for (Object contains : collection) {
            if (!contains(contains)) {
                return false;
            }
        }
        return true;
    }

    public boolean addAll(Collection<? extends E> collection) {
        SetMutator<E> setMutator = this.mutator;
        if (setMutator != null) {
            return setMutator.addAll(this, this.all, collection);
        }
        throw new UnsupportedOperationException("addAll() is not supported on CompositeSet without a SetMutator strategy");
    }

    public boolean removeAll(Collection<?> collection) {
        boolean z = false;
        if (collection.size() == 0) {
            return false;
        }
        for (Set<E> removeAll : this.all) {
            z |= removeAll.removeAll(collection);
        }
        return z;
    }

    public boolean retainAll(Collection<?> collection) {
        boolean z = false;
        for (Set<E> retainAll : this.all) {
            z |= retainAll.retainAll(collection);
        }
        return z;
    }

    public void clear() {
        for (Set<E> clear : this.all) {
            clear.clear();
        }
    }

    public void setMutator(SetMutator<E> setMutator) {
        this.mutator = setMutator;
    }

    public synchronized void addComposited(Set<E> set) {
        for (Set set2 : getSets()) {
            Collection<O> intersection = CollectionUtils.intersection(set2, set);
            if (intersection.size() > 0) {
                if (this.mutator != null) {
                    getMutator().resolveCollision(this, set2, set, intersection);
                    if (CollectionUtils.intersection(set2, set).size() > 0) {
                        throw new IllegalArgumentException("Attempt to add illegal entry unresolved by SetMutator.resolveCollision()");
                    }
                } else {
                    throw new UnsupportedOperationException("Collision adding composited set with no SetMutator set");
                }
            }
        }
        this.all.add(set);
    }

    public void addComposited(Set<E> set, Set<E> set2) {
        addComposited(set);
        addComposited(set2);
    }

    public void addComposited(Set<E>... setArr) {
        for (Set<E> addComposited : setArr) {
            addComposited(addComposited);
        }
    }

    public void removeComposited(Set<E> set) {
        this.all.remove(set);
    }

    public Set<E> toSet() {
        return new HashSet(this);
    }

    public List<Set<E>> getSets() {
        return UnmodifiableList.unmodifiableList(this.all);
    }

    /* access modifiers changed from: protected */
    public SetMutator<E> getMutator() {
        return this.mutator;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Set)) {
            return false;
        }
        Set set = (Set) obj;
        if (set.size() != size() || !set.containsAll(this)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int i;
        Iterator it = iterator();
        int i2 = 0;
        while (it.hasNext()) {
            Object next = it.next();
            if (next == null) {
                i = 0;
            } else {
                i = next.hashCode();
            }
            i2 += i;
        }
        return i2;
    }
}
