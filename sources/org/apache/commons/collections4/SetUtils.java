package org.apache.commons.collections4;

import java.util.AbstractSet;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import org.apache.commons.collections4.set.ListOrderedSet;
import org.apache.commons.collections4.set.PredicatedNavigableSet;
import org.apache.commons.collections4.set.PredicatedSet;
import org.apache.commons.collections4.set.PredicatedSortedSet;
import org.apache.commons.collections4.set.TransformedNavigableSet;
import org.apache.commons.collections4.set.TransformedSet;
import org.apache.commons.collections4.set.TransformedSortedSet;
import org.apache.commons.collections4.set.UnmodifiableNavigableSet;
import org.apache.commons.collections4.set.UnmodifiableSet;
import org.apache.commons.collections4.set.UnmodifiableSortedSet;

public class SetUtils {
    public static final SortedSet EMPTY_SORTED_SET = UnmodifiableSortedSet.unmodifiableSortedSet(new TreeSet());

    public static <E> Set<E> emptySet() {
        return Collections.emptySet();
    }

    public static <E> SortedSet<E> emptySortedSet() {
        return EMPTY_SORTED_SET;
    }

    private SetUtils() {
    }

    public static <T> Set<T> emptyIfNull(Set<T> set) {
        return set == null ? Collections.emptySet() : set;
    }

    public static boolean isEqualSet(Collection<?> collection, Collection<?> collection2) {
        if (collection == collection2) {
            return true;
        }
        if (collection == null || collection2 == null || collection.size() != collection2.size()) {
            return false;
        }
        return collection.containsAll(collection2);
    }

    public static <T> int hashCodeForSet(Collection<T> collection) {
        int i = 0;
        if (collection == null) {
            return 0;
        }
        for (T next : collection) {
            if (next != null) {
                i += next.hashCode();
            }
        }
        return i;
    }

    public static <E> Set<E> newIdentityHashSet() {
        return Collections.newSetFromMap(new IdentityHashMap());
    }

    public static <E> Set<E> synchronizedSet(Set<E> set) {
        return Collections.synchronizedSet(set);
    }

    public static <E> Set<E> unmodifiableSet(Set<? extends E> set) {
        return UnmodifiableSet.unmodifiableSet(set);
    }

    public static <E> Set<E> predicatedSet(Set<E> set, Predicate<? super E> predicate) {
        return PredicatedSet.predicatedSet(set, predicate);
    }

    public static <E> Set<E> transformedSet(Set<E> set, Transformer<? super E, ? extends E> transformer) {
        return TransformedSet.transformingSet(set, transformer);
    }

    public static <E> Set<E> orderedSet(Set<E> set) {
        return ListOrderedSet.listOrderedSet(set);
    }

    public static <E> SortedSet<E> synchronizedSortedSet(SortedSet<E> sortedSet) {
        return Collections.synchronizedSortedSet(sortedSet);
    }

    public static <E> SortedSet<E> unmodifiableSortedSet(SortedSet<E> sortedSet) {
        return UnmodifiableSortedSet.unmodifiableSortedSet(sortedSet);
    }

    public static <E> SortedSet<E> predicatedSortedSet(SortedSet<E> sortedSet, Predicate<? super E> predicate) {
        return PredicatedSortedSet.predicatedSortedSet(sortedSet, predicate);
    }

    public static <E> SortedSet<E> transformedSortedSet(SortedSet<E> sortedSet, Transformer<? super E, ? extends E> transformer) {
        return TransformedSortedSet.transformingSortedSet(sortedSet, transformer);
    }

    public static <E> SortedSet<E> unmodifiableNavigableSet(NavigableSet<E> navigableSet) {
        return UnmodifiableNavigableSet.unmodifiableNavigableSet(navigableSet);
    }

    public static <E> SortedSet<E> predicatedNavigableSet(NavigableSet<E> navigableSet, Predicate<? super E> predicate) {
        return PredicatedNavigableSet.predicatedNavigableSet(navigableSet, predicate);
    }

    public static <E> SortedSet<E> transformedNavigableSet(NavigableSet<E> navigableSet, Transformer<? super E, ? extends E> transformer) {
        return TransformedNavigableSet.transformingNavigableSet(navigableSet, transformer);
    }

    public static <E> SetView<E> union(final Set<? extends E> set, final Set<? extends E> set2) {
        if (set == null || set2 == null) {
            throw new NullPointerException("Sets must not be null.");
        }
        final SetView<? extends E> difference = difference(set2, set);
        return new SetView<E>() {
            public boolean contains(Object obj) {
                return set.contains(obj) || set2.contains(obj);
            }

            public Iterator<E> createIterator() {
                return IteratorUtils.chainedIterator(set.iterator(), difference.iterator());
            }

            public boolean isEmpty() {
                return set.isEmpty() && set2.isEmpty();
            }

            public int size() {
                return set.size() + difference.size();
            }
        };
    }

    public static <E> SetView<E> difference(final Set<? extends E> set, final Set<? extends E> set2) {
        if (set == null || set2 == null) {
            throw new NullPointerException("Sets must not be null.");
        }
        final AnonymousClass2 r0 = new Predicate<E>() {
            public boolean evaluate(E e) {
                return !set2.contains(e);
            }
        };
        return new SetView<E>() {
            public boolean contains(Object obj) {
                return set.contains(obj) && !set2.contains(obj);
            }

            public Iterator<E> createIterator() {
                return IteratorUtils.filteredIterator(set.iterator(), r0);
            }
        };
    }

    public static <E> SetView<E> intersection(final Set<? extends E> set, final Set<? extends E> set2) {
        if (set == null || set2 == null) {
            throw new NullPointerException("Sets must not be null.");
        }
        final AnonymousClass4 r0 = new Predicate<E>() {
            public boolean evaluate(E e) {
                return set2.contains(e);
            }
        };
        return new SetView<E>() {
            public boolean contains(Object obj) {
                return set.contains(obj) && set2.contains(obj);
            }

            public Iterator<E> createIterator() {
                return IteratorUtils.filteredIterator(set.iterator(), r0);
            }
        };
    }

    public static <E> SetView<E> disjunction(final Set<? extends E> set, final Set<? extends E> set2) {
        if (set == null || set2 == null) {
            throw new NullPointerException("Sets must not be null.");
        }
        final SetView<? extends E> difference = difference(set, set2);
        final SetView<? extends E> difference2 = difference(set2, set);
        return new SetView<E>() {
            public boolean contains(Object obj) {
                return set2.contains(obj) ^ set.contains(obj);
            }

            public Iterator<E> createIterator() {
                return IteratorUtils.chainedIterator(difference.iterator(), difference2.iterator());
            }

            public boolean isEmpty() {
                return difference.isEmpty() && difference2.isEmpty();
            }

            public int size() {
                return difference.size() + difference2.size();
            }
        };
    }

    public static abstract class SetView<E> extends AbstractSet<E> {
        /* access modifiers changed from: protected */
        public abstract Iterator<E> createIterator();

        public Iterator<E> iterator() {
            return IteratorUtils.unmodifiableIterator(createIterator());
        }

        public int size() {
            return IteratorUtils.size(iterator());
        }

        public <S extends Set<E>> void copyInto(S s) {
            CollectionUtils.addAll(s, this);
        }

        public Set<E> toSet() {
            HashSet hashSet = new HashSet(size());
            copyInto(hashSet);
            return hashSet;
        }
    }
}
