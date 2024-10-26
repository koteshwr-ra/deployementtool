package org.apache.commons.collections4;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import org.apache.commons.collections4.functors.EqualPredicate;
import org.apache.commons.collections4.iterators.ArrayIterator;
import org.apache.commons.collections4.iterators.ArrayListIterator;
import org.apache.commons.collections4.iterators.BoundedIterator;
import org.apache.commons.collections4.iterators.CollatingIterator;
import org.apache.commons.collections4.iterators.EmptyIterator;
import org.apache.commons.collections4.iterators.EmptyListIterator;
import org.apache.commons.collections4.iterators.EmptyMapIterator;
import org.apache.commons.collections4.iterators.EmptyOrderedIterator;
import org.apache.commons.collections4.iterators.EmptyOrderedMapIterator;
import org.apache.commons.collections4.iterators.EnumerationIterator;
import org.apache.commons.collections4.iterators.FilterIterator;
import org.apache.commons.collections4.iterators.FilterListIterator;
import org.apache.commons.collections4.iterators.IteratorChain;
import org.apache.commons.collections4.iterators.IteratorEnumeration;
import org.apache.commons.collections4.iterators.IteratorIterable;
import org.apache.commons.collections4.iterators.ListIteratorWrapper;
import org.apache.commons.collections4.iterators.LoopingIterator;
import org.apache.commons.collections4.iterators.LoopingListIterator;
import org.apache.commons.collections4.iterators.NodeListIterator;
import org.apache.commons.collections4.iterators.ObjectArrayIterator;
import org.apache.commons.collections4.iterators.ObjectArrayListIterator;
import org.apache.commons.collections4.iterators.ObjectGraphIterator;
import org.apache.commons.collections4.iterators.PeekingIterator;
import org.apache.commons.collections4.iterators.PushbackIterator;
import org.apache.commons.collections4.iterators.SingletonIterator;
import org.apache.commons.collections4.iterators.SingletonListIterator;
import org.apache.commons.collections4.iterators.SkippingIterator;
import org.apache.commons.collections4.iterators.TransformIterator;
import org.apache.commons.collections4.iterators.UnmodifiableIterator;
import org.apache.commons.collections4.iterators.UnmodifiableListIterator;
import org.apache.commons.collections4.iterators.UnmodifiableMapIterator;
import org.apache.commons.collections4.iterators.ZippingIterator;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class IteratorUtils {
    private static final String DEFAULT_TOSTRING_DELIMITER = ", ";
    private static final String DEFAULT_TOSTRING_PREFIX = "[";
    private static final String DEFAULT_TOSTRING_SUFFIX = "]";
    public static final ResettableIterator EMPTY_ITERATOR = EmptyIterator.RESETTABLE_INSTANCE;
    public static final ResettableListIterator EMPTY_LIST_ITERATOR = EmptyListIterator.RESETTABLE_INSTANCE;
    public static final MapIterator EMPTY_MAP_ITERATOR = EmptyMapIterator.INSTANCE;
    public static final OrderedIterator EMPTY_ORDERED_ITERATOR = EmptyOrderedIterator.INSTANCE;
    public static final OrderedMapIterator EMPTY_ORDERED_MAP_ITERATOR = EmptyOrderedMapIterator.INSTANCE;

    private IteratorUtils() {
    }

    public static <E> ResettableIterator<E> emptyIterator() {
        return EmptyIterator.resettableEmptyIterator();
    }

    public static <E> ResettableListIterator<E> emptyListIterator() {
        return EmptyListIterator.resettableEmptyListIterator();
    }

    public static <E> OrderedIterator<E> emptyOrderedIterator() {
        return EmptyOrderedIterator.emptyOrderedIterator();
    }

    public static <K, V> MapIterator<K, V> emptyMapIterator() {
        return EmptyMapIterator.emptyMapIterator();
    }

    public static <K, V> OrderedMapIterator<K, V> emptyOrderedMapIterator() {
        return EmptyOrderedMapIterator.emptyOrderedMapIterator();
    }

    public static <E> ResettableIterator<E> singletonIterator(E e) {
        return new SingletonIterator(e);
    }

    public static <E> ListIterator<E> singletonListIterator(E e) {
        return new SingletonListIterator(e);
    }

    public static <E> ResettableIterator<E> arrayIterator(E... eArr) {
        return new ObjectArrayIterator(eArr);
    }

    public static <E> ResettableIterator<E> arrayIterator(Object obj) {
        return new ArrayIterator(obj);
    }

    public static <E> ResettableIterator<E> arrayIterator(E[] eArr, int i) {
        return new ObjectArrayIterator(eArr, i);
    }

    public static <E> ResettableIterator<E> arrayIterator(Object obj, int i) {
        return new ArrayIterator(obj, i);
    }

    public static <E> ResettableIterator<E> arrayIterator(E[] eArr, int i, int i2) {
        return new ObjectArrayIterator(eArr, i, i2);
    }

    public static <E> ResettableIterator<E> arrayIterator(Object obj, int i, int i2) {
        return new ArrayIterator(obj, i, i2);
    }

    public static <E> ResettableListIterator<E> arrayListIterator(E... eArr) {
        return new ObjectArrayListIterator(eArr);
    }

    public static <E> ResettableListIterator<E> arrayListIterator(Object obj) {
        return new ArrayListIterator(obj);
    }

    public static <E> ResettableListIterator<E> arrayListIterator(E[] eArr, int i) {
        return new ObjectArrayListIterator(eArr, i);
    }

    public static <E> ResettableListIterator<E> arrayListIterator(Object obj, int i) {
        return new ArrayListIterator(obj, i);
    }

    public static <E> ResettableListIterator<E> arrayListIterator(E[] eArr, int i, int i2) {
        return new ObjectArrayListIterator(eArr, i, i2);
    }

    public static <E> ResettableListIterator<E> arrayListIterator(Object obj, int i, int i2) {
        return new ArrayListIterator(obj, i, i2);
    }

    public static <E> BoundedIterator<E> boundedIterator(Iterator<? extends E> it, long j) {
        return boundedIterator(it, 0, j);
    }

    public static <E> BoundedIterator<E> boundedIterator(Iterator<? extends E> it, long j, long j2) {
        return new BoundedIterator(it, j, j2);
    }

    public static <E> Iterator<E> unmodifiableIterator(Iterator<E> it) {
        return UnmodifiableIterator.unmodifiableIterator(it);
    }

    public static <E> ListIterator<E> unmodifiableListIterator(ListIterator<E> listIterator) {
        return UnmodifiableListIterator.umodifiableListIterator(listIterator);
    }

    public static <K, V> MapIterator<K, V> unmodifiableMapIterator(MapIterator<K, V> mapIterator) {
        return UnmodifiableMapIterator.unmodifiableMapIterator(mapIterator);
    }

    public static <E> Iterator<E> chainedIterator(Iterator<? extends E> it, Iterator<? extends E> it2) {
        return new IteratorChain(it, it2);
    }

    public static <E> Iterator<E> chainedIterator(Iterator<? extends E>... itArr) {
        return new IteratorChain(itArr);
    }

    public static <E> Iterator<E> chainedIterator(Collection<Iterator<? extends E>> collection) {
        return new IteratorChain(collection);
    }

    public static <E> Iterator<E> collatedIterator(Comparator<? super E> comparator, Iterator<? extends E> it, Iterator<? extends E> it2) {
        if (comparator == null) {
            comparator = ComparatorUtils.NATURAL_COMPARATOR;
        }
        return new CollatingIterator(comparator, it, it2);
    }

    public static <E> Iterator<E> collatedIterator(Comparator<? super E> comparator, Iterator<? extends E>... itArr) {
        if (comparator == null) {
            comparator = ComparatorUtils.NATURAL_COMPARATOR;
        }
        return new CollatingIterator(comparator, itArr);
    }

    public static <E> Iterator<E> collatedIterator(Comparator<? super E> comparator, Collection<Iterator<? extends E>> collection) {
        if (comparator == null) {
            comparator = ComparatorUtils.NATURAL_COMPARATOR;
        }
        return new CollatingIterator(comparator, collection);
    }

    public static <E> Iterator<E> objectGraphIterator(E e, Transformer<? super E, ? extends E> transformer) {
        return new ObjectGraphIterator(e, transformer);
    }

    public static <I, O> Iterator<O> transformedIterator(Iterator<? extends I> it, Transformer<? super I, ? extends O> transformer) {
        if (it == null) {
            throw new NullPointerException("Iterator must not be null");
        } else if (transformer != null) {
            return new TransformIterator(it, transformer);
        } else {
            throw new NullPointerException("Transformer must not be null");
        }
    }

    public static <E> Iterator<E> filteredIterator(Iterator<? extends E> it, Predicate<? super E> predicate) {
        if (it == null) {
            throw new NullPointerException("Iterator must not be null");
        } else if (predicate != null) {
            return new FilterIterator(it, predicate);
        } else {
            throw new NullPointerException("Predicate must not be null");
        }
    }

    public static <E> ListIterator<E> filteredListIterator(ListIterator<? extends E> listIterator, Predicate<? super E> predicate) {
        if (listIterator == null) {
            throw new NullPointerException("ListIterator must not be null");
        } else if (predicate != null) {
            return new FilterListIterator(listIterator, predicate);
        } else {
            throw new NullPointerException("Predicate must not be null");
        }
    }

    public static <E> ResettableIterator<E> loopingIterator(Collection<? extends E> collection) {
        if (collection != null) {
            return new LoopingIterator(collection);
        }
        throw new NullPointerException("Collection must not be null");
    }

    public static <E> ResettableListIterator<E> loopingListIterator(List<E> list) {
        if (list != null) {
            return new LoopingListIterator(list);
        }
        throw new NullPointerException("List must not be null");
    }

    public static NodeListIterator nodeListIterator(NodeList nodeList) {
        if (nodeList != null) {
            return new NodeListIterator(nodeList);
        }
        throw new NullPointerException("NodeList must not be null");
    }

    public static NodeListIterator nodeListIterator(Node node) {
        if (node != null) {
            return new NodeListIterator(node);
        }
        throw new NullPointerException("Node must not be null");
    }

    public static <E> Iterator<E> peekingIterator(Iterator<? extends E> it) {
        return PeekingIterator.peekingIterator(it);
    }

    public static <E> Iterator<E> pushbackIterator(Iterator<? extends E> it) {
        return PushbackIterator.pushbackIterator(it);
    }

    public static <E> SkippingIterator<E> skippingIterator(Iterator<E> it, long j) {
        return new SkippingIterator<>(it, j);
    }

    public static <E> ZippingIterator<E> zippingIterator(Iterator<? extends E> it, Iterator<? extends E> it2) {
        return new ZippingIterator<>(it, it2);
    }

    public static <E> ZippingIterator<E> zippingIterator(Iterator<? extends E> it, Iterator<? extends E> it2, Iterator<? extends E> it3) {
        return new ZippingIterator<>(it, it2, it3);
    }

    public static <E> ZippingIterator<E> zippingIterator(Iterator<? extends E>... itArr) {
        return new ZippingIterator<>(itArr);
    }

    public static <E> Iterator<E> asIterator(Enumeration<? extends E> enumeration) {
        if (enumeration != null) {
            return new EnumerationIterator(enumeration);
        }
        throw new NullPointerException("Enumeration must not be null");
    }

    public static <E> Iterator<E> asIterator(Enumeration<? extends E> enumeration, Collection<? super E> collection) {
        if (enumeration == null) {
            throw new NullPointerException("Enumeration must not be null");
        } else if (collection != null) {
            return new EnumerationIterator(enumeration, collection);
        } else {
            throw new NullPointerException("Collection must not be null");
        }
    }

    public static <E> Enumeration<E> asEnumeration(Iterator<? extends E> it) {
        if (it != null) {
            return new IteratorEnumeration(it);
        }
        throw new NullPointerException("Iterator must not be null");
    }

    public static <E> Iterable<E> asIterable(Iterator<? extends E> it) {
        if (it != null) {
            return new IteratorIterable(it, false);
        }
        throw new NullPointerException("Iterator must not be null");
    }

    public static <E> Iterable<E> asMultipleUseIterable(Iterator<? extends E> it) {
        if (it != null) {
            return new IteratorIterable(it, true);
        }
        throw new NullPointerException("Iterator must not be null");
    }

    public static <E> ListIterator<E> toListIterator(Iterator<? extends E> it) {
        if (it != null) {
            return new ListIteratorWrapper(it);
        }
        throw new NullPointerException("Iterator must not be null");
    }

    public static Object[] toArray(Iterator<?> it) {
        if (it != null) {
            return toList(it, 100).toArray();
        }
        throw new NullPointerException("Iterator must not be null");
    }

    public static <E> E[] toArray(Iterator<? extends E> it, Class<E> cls) {
        if (it == null) {
            throw new NullPointerException("Iterator must not be null");
        } else if (cls != null) {
            List<? extends E> list = toList(it, 100);
            return list.toArray((Object[]) Array.newInstance(cls, list.size()));
        } else {
            throw new NullPointerException("Array class must not be null");
        }
    }

    public static <E> List<E> toList(Iterator<? extends E> it) {
        return toList(it, 10);
    }

    public static <E> List<E> toList(Iterator<? extends E> it, int i) {
        if (it == null) {
            throw new NullPointerException("Iterator must not be null");
        } else if (i >= 1) {
            ArrayList arrayList = new ArrayList(i);
            while (it.hasNext()) {
                arrayList.add(it.next());
            }
            return arrayList;
        } else {
            throw new IllegalArgumentException("Estimated size must be greater than 0");
        }
    }

    public static Iterator<?> getIterator(Object obj) {
        Iterator<?> it;
        if (obj == null) {
            return emptyIterator();
        }
        if (obj instanceof Iterator) {
            return (Iterator) obj;
        }
        if (obj instanceof Iterable) {
            return ((Iterable) obj).iterator();
        }
        if (obj instanceof Object[]) {
            return new ObjectArrayIterator((Object[]) obj);
        }
        if (obj instanceof Enumeration) {
            return new EnumerationIterator((Enumeration) obj);
        }
        if (obj instanceof Map) {
            return ((Map) obj).values().iterator();
        }
        if (obj instanceof NodeList) {
            return new NodeListIterator((NodeList) obj);
        }
        if (obj instanceof Node) {
            return new NodeListIterator((Node) obj);
        }
        if (obj instanceof Dictionary) {
            return new EnumerationIterator(((Dictionary) obj).elements());
        }
        if (obj.getClass().isArray()) {
            return new ArrayIterator(obj);
        }
        try {
            Method method = obj.getClass().getMethod("iterator", (Class[]) null);
            if (Iterator.class.isAssignableFrom(method.getReturnType()) && (it = (Iterator) method.invoke(obj, (Object[]) null)) != null) {
                return it;
            }
        } catch (IllegalAccessException | NoSuchMethodException | RuntimeException | InvocationTargetException unused) {
        }
        return singletonIterator(obj);
    }

    public static <E> void forEach(Iterator<E> it, Closure<? super E> closure) {
        if (closure == null) {
            throw new NullPointerException("Closure must not be null");
        } else if (it != null) {
            while (it.hasNext()) {
                closure.execute(it.next());
            }
        }
    }

    public static <E> E forEachButLast(Iterator<E> it, Closure<? super E> closure) {
        if (closure == null) {
            throw new NullPointerException("Closure must not be null.");
        } else if (it == null) {
            return null;
        } else {
            while (it.hasNext()) {
                E next = it.next();
                if (!it.hasNext()) {
                    return next;
                }
                closure.execute(next);
            }
            return null;
        }
    }

    public static <E> E find(Iterator<E> it, Predicate<? super E> predicate) {
        if (predicate == null) {
            throw new NullPointerException("Predicate must not be null");
        } else if (it == null) {
            return null;
        } else {
            while (it.hasNext()) {
                E next = it.next();
                if (predicate.evaluate(next)) {
                    return next;
                }
            }
            return null;
        }
    }

    public static <E> int indexOf(Iterator<E> it, Predicate<? super E> predicate) {
        if (predicate == null) {
            throw new NullPointerException("Predicate must not be null");
        } else if (it == null) {
            return -1;
        } else {
            int i = 0;
            while (it.hasNext()) {
                if (predicate.evaluate(it.next())) {
                    return i;
                }
                i++;
            }
            return -1;
        }
    }

    public static <E> boolean matchesAny(Iterator<E> it, Predicate<? super E> predicate) {
        return indexOf(it, predicate) != -1;
    }

    public static <E> boolean matchesAll(Iterator<E> it, Predicate<? super E> predicate) {
        if (predicate == null) {
            throw new NullPointerException("Predicate must not be null");
        } else if (it == null) {
            return true;
        } else {
            while (it.hasNext()) {
                if (!predicate.evaluate(it.next())) {
                    return false;
                }
            }
            return true;
        }
    }

    public static boolean isEmpty(Iterator<?> it) {
        return it == null || !it.hasNext();
    }

    public static <E> boolean contains(Iterator<E> it, Object obj) {
        return matchesAny(it, EqualPredicate.equalPredicate(obj));
    }

    public static <E> E get(Iterator<E> it, int i) {
        CollectionUtils.checkIndexBounds(i);
        while (it.hasNext()) {
            i--;
            if (i == -1) {
                return it.next();
            }
            it.next();
        }
        throw new IndexOutOfBoundsException("Entry does not exist: " + i);
    }

    public static int size(Iterator<?> it) {
        int i = 0;
        if (it != null) {
            while (it.hasNext()) {
                it.next();
                i++;
            }
        }
        return i;
    }

    public static <E> String toString(Iterator<E> it) {
        return toString(it, TransformerUtils.stringValueTransformer(), DEFAULT_TOSTRING_DELIMITER, DEFAULT_TOSTRING_PREFIX, DEFAULT_TOSTRING_SUFFIX);
    }

    public static <E> String toString(Iterator<E> it, Transformer<? super E, String> transformer) {
        return toString(it, transformer, DEFAULT_TOSTRING_DELIMITER, DEFAULT_TOSTRING_PREFIX, DEFAULT_TOSTRING_SUFFIX);
    }

    public static <E> String toString(Iterator<E> it, Transformer<? super E, String> transformer, String str, String str2, String str3) {
        if (transformer == null) {
            throw new NullPointerException("transformer may not be null");
        } else if (str == null) {
            throw new NullPointerException("delimiter may not be null");
        } else if (str2 == null) {
            throw new NullPointerException("prefix may not be null");
        } else if (str3 != null) {
            StringBuilder sb = new StringBuilder(str2);
            if (it != null) {
                while (it.hasNext()) {
                    sb.append(transformer.transform(it.next()));
                    sb.append(str);
                }
                if (sb.length() > str2.length()) {
                    sb.setLength(sb.length() - str.length());
                }
            }
            sb.append(str3);
            return sb.toString();
        } else {
            throw new NullPointerException("suffix may not be null");
        }
    }
}
