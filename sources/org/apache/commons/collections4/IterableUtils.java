package org.apache.commons.collections4;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.apache.commons.collections4.functors.EqualPredicate;
import org.apache.commons.collections4.iterators.LazyIteratorChain;
import org.apache.commons.collections4.iterators.ReverseListIterator;
import org.apache.commons.collections4.iterators.UniqueFilterIterator;

public class IterableUtils {
    static final FluentIterable EMPTY_ITERABLE = new FluentIterable<Object>() {
        public Iterator<Object> iterator() {
            return IteratorUtils.emptyIterator();
        }
    };

    public static <E> Iterable<E> emptyIterable() {
        return EMPTY_ITERABLE;
    }

    public static <E> Iterable<E> chainedIterable(Iterable<? extends E> iterable, Iterable<? extends E> iterable2) {
        return chainedIterable(iterable, iterable2);
    }

    public static <E> Iterable<E> chainedIterable(Iterable<? extends E> iterable, Iterable<? extends E> iterable2, Iterable<? extends E> iterable3) {
        return chainedIterable(iterable, iterable2, iterable3);
    }

    public static <E> Iterable<E> chainedIterable(Iterable<? extends E> iterable, Iterable<? extends E> iterable2, Iterable<? extends E> iterable3, Iterable<? extends E> iterable4) {
        return chainedIterable(iterable, iterable2, iterable3, iterable4);
    }

    public static <E> Iterable<E> chainedIterable(final Iterable<? extends E>... iterableArr) {
        checkNotNull((Iterable<?>[]) iterableArr);
        return new FluentIterable<E>() {
            public Iterator<E> iterator() {
                return new LazyIteratorChain<E>() {
                    /* access modifiers changed from: protected */
                    public Iterator<? extends E> nextIterator(int i) {
                        if (i > iterableArr.length) {
                            return null;
                        }
                        return iterableArr[i - 1].iterator();
                    }
                };
            }
        };
    }

    public static <E> Iterable<E> collatedIterable(final Iterable<? extends E> iterable, final Iterable<? extends E> iterable2) {
        checkNotNull((Iterable<?>[]) new Iterable[]{iterable, iterable2});
        return new FluentIterable<E>() {
            public Iterator<E> iterator() {
                return IteratorUtils.collatedIterator((Comparator) null, iterable.iterator(), iterable2.iterator());
            }
        };
    }

    public static <E> Iterable<E> collatedIterable(final Comparator<? super E> comparator, final Iterable<? extends E> iterable, final Iterable<? extends E> iterable2) {
        checkNotNull((Iterable<?>[]) new Iterable[]{iterable, iterable2});
        return new FluentIterable<E>() {
            public Iterator<E> iterator() {
                return IteratorUtils.collatedIterator(comparator, iterable.iterator(), iterable2.iterator());
            }
        };
    }

    public static <E> Iterable<E> filteredIterable(final Iterable<E> iterable, final Predicate<? super E> predicate) {
        checkNotNull((Iterable<?>) iterable);
        if (predicate != null) {
            return new FluentIterable<E>() {
                public Iterator<E> iterator() {
                    return IteratorUtils.filteredIterator(IterableUtils.emptyIteratorIfNull(iterable), predicate);
                }
            };
        }
        throw new NullPointerException("Predicate must not be null.");
    }

    public static <E> Iterable<E> boundedIterable(final Iterable<E> iterable, final long j) {
        checkNotNull((Iterable<?>) iterable);
        if (j >= 0) {
            return new FluentIterable<E>() {
                public Iterator<E> iterator() {
                    return IteratorUtils.boundedIterator(iterable.iterator(), j);
                }
            };
        }
        throw new IllegalArgumentException("MaxSize parameter must not be negative.");
    }

    public static <E> Iterable<E> loopingIterable(final Iterable<E> iterable) {
        checkNotNull((Iterable<?>) iterable);
        return new FluentIterable<E>() {
            public Iterator<E> iterator() {
                return new LazyIteratorChain<E>() {
                    /* access modifiers changed from: protected */
                    public Iterator<? extends E> nextIterator(int i) {
                        if (IterableUtils.isEmpty(iterable)) {
                            return null;
                        }
                        return iterable.iterator();
                    }
                };
            }
        };
    }

    public static <E> Iterable<E> reversedIterable(final Iterable<E> iterable) {
        checkNotNull((Iterable<?>) iterable);
        return new FluentIterable<E>() {
            public Iterator<E> iterator() {
                Iterable iterable = iterable;
                return new ReverseListIterator(iterable instanceof List ? (List) iterable : IteratorUtils.toList(iterable.iterator()));
            }
        };
    }

    public static <E> Iterable<E> skippingIterable(final Iterable<E> iterable, final long j) {
        checkNotNull((Iterable<?>) iterable);
        if (j >= 0) {
            return new FluentIterable<E>() {
                public Iterator<E> iterator() {
                    return IteratorUtils.skippingIterator(iterable.iterator(), j);
                }
            };
        }
        throw new IllegalArgumentException("ElementsToSkip parameter must not be negative.");
    }

    public static <I, O> Iterable<O> transformedIterable(final Iterable<I> iterable, final Transformer<? super I, ? extends O> transformer) {
        checkNotNull((Iterable<?>) iterable);
        if (transformer != null) {
            return new FluentIterable<O>() {
                public Iterator<O> iterator() {
                    return IteratorUtils.transformedIterator(iterable.iterator(), transformer);
                }
            };
        }
        throw new NullPointerException("Transformer must not be null.");
    }

    public static <E> Iterable<E> uniqueIterable(final Iterable<E> iterable) {
        checkNotNull((Iterable<?>) iterable);
        return new FluentIterable<E>() {
            public Iterator<E> iterator() {
                return new UniqueFilterIterator(iterable.iterator());
            }
        };
    }

    public static <E> Iterable<E> unmodifiableIterable(Iterable<E> iterable) {
        checkNotNull((Iterable<?>) iterable);
        if (iterable instanceof UnmodifiableIterable) {
            return iterable;
        }
        return new UnmodifiableIterable(iterable);
    }

    private static final class UnmodifiableIterable<E> extends FluentIterable<E> {
        private final Iterable<E> unmodifiable;

        public UnmodifiableIterable(Iterable<E> iterable) {
            this.unmodifiable = iterable;
        }

        public Iterator<E> iterator() {
            return IteratorUtils.unmodifiableIterator(this.unmodifiable.iterator());
        }
    }

    public static <E> Iterable<E> zippingIterable(final Iterable<? extends E> iterable, final Iterable<? extends E> iterable2) {
        checkNotNull((Iterable<?>) iterable);
        checkNotNull((Iterable<?>) iterable2);
        return new FluentIterable<E>() {
            public Iterator<E> iterator() {
                return IteratorUtils.zippingIterator(iterable.iterator(), iterable2.iterator());
            }
        };
    }

    public static <E> Iterable<E> zippingIterable(final Iterable<? extends E> iterable, final Iterable<? extends E>... iterableArr) {
        checkNotNull((Iterable<?>) iterable);
        checkNotNull((Iterable<?>[]) iterableArr);
        return new FluentIterable<E>() {
            public Iterator<E> iterator() {
                Iterator[] itArr = new Iterator[(iterableArr.length + 1)];
                int i = 0;
                itArr[0] = iterable.iterator();
                while (true) {
                    Iterable[] iterableArr = iterableArr;
                    if (i >= iterableArr.length) {
                        return IteratorUtils.zippingIterator(itArr);
                    }
                    int i2 = i + 1;
                    itArr[i2] = iterableArr[i].iterator();
                    i = i2;
                }
            }
        };
    }

    public static <E> Iterable<E> emptyIfNull(Iterable<E> iterable) {
        return iterable == null ? emptyIterable() : iterable;
    }

    public static <E> void forEach(Iterable<E> iterable, Closure<? super E> closure) {
        IteratorUtils.forEach(emptyIteratorIfNull(iterable), closure);
    }

    public static <E> E forEachButLast(Iterable<E> iterable, Closure<? super E> closure) {
        return IteratorUtils.forEachButLast(emptyIteratorIfNull(iterable), closure);
    }

    public static <E> E find(Iterable<E> iterable, Predicate<? super E> predicate) {
        return IteratorUtils.find(emptyIteratorIfNull(iterable), predicate);
    }

    public static <E> int indexOf(Iterable<E> iterable, Predicate<? super E> predicate) {
        return IteratorUtils.indexOf(emptyIteratorIfNull(iterable), predicate);
    }

    public static <E> boolean matchesAll(Iterable<E> iterable, Predicate<? super E> predicate) {
        return IteratorUtils.matchesAll(emptyIteratorIfNull(iterable), predicate);
    }

    public static <E> boolean matchesAny(Iterable<E> iterable, Predicate<? super E> predicate) {
        return IteratorUtils.matchesAny(emptyIteratorIfNull(iterable), predicate);
    }

    public static <E> long countMatches(Iterable<E> iterable, Predicate<? super E> predicate) {
        if (predicate != null) {
            return (long) size(filteredIterable(emptyIfNull(iterable), predicate));
        }
        throw new NullPointerException("Predicate must not be null.");
    }

    public static boolean isEmpty(Iterable<?> iterable) {
        if (iterable instanceof Collection) {
            return ((Collection) iterable).isEmpty();
        }
        return IteratorUtils.isEmpty(emptyIteratorIfNull(iterable));
    }

    public static <E> boolean contains(Iterable<E> iterable, Object obj) {
        if (iterable instanceof Collection) {
            return ((Collection) iterable).contains(obj);
        }
        return IteratorUtils.contains(emptyIteratorIfNull(iterable), obj);
    }

    public static <E> boolean contains(Iterable<? extends E> iterable, E e, Equator<? super E> equator) {
        if (equator != null) {
            return matchesAny(iterable, EqualPredicate.equalPredicate(e, equator));
        }
        throw new NullPointerException("Equator must not be null.");
    }

    public static <E, T extends E> int frequency(Iterable<E> iterable, T t) {
        if (iterable instanceof Set) {
            return ((Set) iterable).contains(t) ? 1 : 0;
        }
        if (iterable instanceof Bag) {
            return ((Bag) iterable).getCount(t);
        }
        return size(filteredIterable(emptyIfNull(iterable), EqualPredicate.equalPredicate(t)));
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [java.lang.Iterable<T>, java.lang.Iterable] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static <T> T get(java.lang.Iterable<T> r1, int r2) {
        /*
            org.apache.commons.collections4.CollectionUtils.checkIndexBounds(r2)
            boolean r0 = r1 instanceof java.util.List
            if (r0 == 0) goto L_0x000e
            java.util.List r1 = (java.util.List) r1
            java.lang.Object r1 = r1.get(r2)
            return r1
        L_0x000e:
            java.util.Iterator r1 = emptyIteratorIfNull(r1)
            java.lang.Object r1 = org.apache.commons.collections4.IteratorUtils.get(r1, r2)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.collections4.IterableUtils.get(java.lang.Iterable, int):java.lang.Object");
    }

    public static int size(Iterable<?> iterable) {
        if (iterable instanceof Collection) {
            return ((Collection) iterable).size();
        }
        return IteratorUtils.size(emptyIteratorIfNull(iterable));
    }

    public static <O> List<List<O>> partition(Iterable<? extends O> iterable, Predicate<? super O> predicate) {
        if (predicate != null) {
            return partition(iterable, FactoryUtils.instantiateFactory(ArrayList.class), predicate);
        }
        throw new NullPointerException("Predicate must not be null.");
    }

    public static <O> List<List<O>> partition(Iterable<? extends O> iterable, Predicate<? super O>... predicateArr) {
        return partition(iterable, FactoryUtils.instantiateFactory(ArrayList.class), predicateArr);
    }

    public static <O, R extends Collection<O>> List<R> partition(Iterable<? extends O> iterable, Factory<R> factory, Predicate<? super O>... predicateArr) {
        boolean z;
        if (iterable == null) {
            return partition(emptyIterable(), factory, predicateArr);
        }
        if (predicateArr != null) {
            int length = predicateArr.length;
            int i = 0;
            while (i < length) {
                if (predicateArr[i] != null) {
                    i++;
                } else {
                    throw new NullPointerException("Predicate must not be null.");
                }
            }
            if (predicateArr.length < 1) {
                Collection collection = (Collection) factory.create();
                CollectionUtils.addAll(collection, iterable);
                return Collections.singletonList(collection);
            }
            int length2 = predicateArr.length;
            int i2 = length2 + 1;
            ArrayList arrayList = new ArrayList(i2);
            for (int i3 = 0; i3 < i2; i3++) {
                arrayList.add(factory.create());
            }
            for (Object next : iterable) {
                int i4 = 0;
                while (true) {
                    if (i4 >= length2) {
                        z = false;
                        break;
                    } else if (predicateArr[i4].evaluate(next)) {
                        ((Collection) arrayList.get(i4)).add(next);
                        z = true;
                        break;
                    } else {
                        i4++;
                    }
                }
                if (!z) {
                    ((Collection) arrayList.get(length2)).add(next);
                }
            }
            return arrayList;
        }
        throw new NullPointerException("Predicates must not be null.");
    }

    public static <E> List<E> toList(Iterable<E> iterable) {
        return IteratorUtils.toList(emptyIteratorIfNull(iterable));
    }

    public static <E> String toString(Iterable<E> iterable) {
        return IteratorUtils.toString(emptyIteratorIfNull(iterable));
    }

    public static <E> String toString(Iterable<E> iterable, Transformer<? super E, String> transformer) {
        if (transformer != null) {
            return IteratorUtils.toString(emptyIteratorIfNull(iterable), transformer);
        }
        throw new NullPointerException("Transformer must not be null.");
    }

    public static <E> String toString(Iterable<E> iterable, Transformer<? super E, String> transformer, String str, String str2, String str3) {
        return IteratorUtils.toString(emptyIteratorIfNull(iterable), transformer, str, str2, str3);
    }

    static void checkNotNull(Iterable<?> iterable) {
        if (iterable == null) {
            throw new NullPointerException("Iterable must not be null.");
        }
    }

    static void checkNotNull(Iterable<?>... iterableArr) {
        if (iterableArr != null) {
            for (Iterable<?> checkNotNull : iterableArr) {
                checkNotNull(checkNotNull);
            }
            return;
        }
        throw new NullPointerException("Iterables must not be null.");
    }

    /* access modifiers changed from: private */
    public static <E> Iterator<E> emptyIteratorIfNull(Iterable<E> iterable) {
        return iterable != null ? iterable.iterator() : IteratorUtils.emptyIterator();
    }
}
