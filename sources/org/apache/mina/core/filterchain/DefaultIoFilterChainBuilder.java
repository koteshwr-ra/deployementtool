package org.apache.mina.core.filterchain;

import com.alibaba.android.arouter.utils.Consts;
import com.limpoxe.support.servicemanager.ServiceProvider;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import org.apache.mina.core.filterchain.IoFilter;
import org.apache.mina.core.filterchain.IoFilterChain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultIoFilterChainBuilder implements IoFilterChainBuilder {
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultIoFilterChainBuilder.class);
    private final List<IoFilterChain.Entry> entries;

    public DefaultIoFilterChainBuilder() {
        this.entries = new CopyOnWriteArrayList();
    }

    public DefaultIoFilterChainBuilder(DefaultIoFilterChainBuilder defaultIoFilterChainBuilder) {
        if (defaultIoFilterChainBuilder != null) {
            this.entries = new CopyOnWriteArrayList(defaultIoFilterChainBuilder.entries);
            return;
        }
        throw new IllegalArgumentException("filterChain");
    }

    public IoFilterChain.Entry getEntry(String str) {
        for (IoFilterChain.Entry next : this.entries) {
            if (next.getName().equals(str)) {
                return next;
            }
        }
        return null;
    }

    public IoFilterChain.Entry getEntry(IoFilter ioFilter) {
        for (IoFilterChain.Entry next : this.entries) {
            if (next.getFilter() == ioFilter) {
                return next;
            }
        }
        return null;
    }

    public IoFilterChain.Entry getEntry(Class<? extends IoFilter> cls) {
        for (IoFilterChain.Entry next : this.entries) {
            if (cls.isAssignableFrom(next.getFilter().getClass())) {
                return next;
            }
        }
        return null;
    }

    public IoFilter get(String str) {
        IoFilterChain.Entry entry = getEntry(str);
        if (entry == null) {
            return null;
        }
        return entry.getFilter();
    }

    public IoFilter get(Class<? extends IoFilter> cls) {
        IoFilterChain.Entry entry = getEntry(cls);
        if (entry == null) {
            return null;
        }
        return entry.getFilter();
    }

    public List<IoFilterChain.Entry> getAll() {
        return new ArrayList(this.entries);
    }

    public List<IoFilterChain.Entry> getAllReversed() {
        List<IoFilterChain.Entry> all = getAll();
        Collections.reverse(all);
        return all;
    }

    public boolean contains(String str) {
        return getEntry(str) != null;
    }

    public boolean contains(IoFilter ioFilter) {
        return getEntry(ioFilter) != null;
    }

    public boolean contains(Class<? extends IoFilter> cls) {
        return getEntry(cls) != null;
    }

    public synchronized void addFirst(String str, IoFilter ioFilter) {
        register(0, new EntryImpl(str, ioFilter));
    }

    public synchronized void addLast(String str, IoFilter ioFilter) {
        register(this.entries.size(), new EntryImpl(str, ioFilter));
    }

    public synchronized void addBefore(String str, String str2, IoFilter ioFilter) {
        checkBaseName(str);
        ListIterator<IoFilterChain.Entry> listIterator = this.entries.listIterator();
        while (true) {
            if (listIterator.hasNext()) {
                if (listIterator.next().getName().equals(str)) {
                    register(listIterator.previousIndex(), new EntryImpl(str2, ioFilter));
                    break;
                }
            } else {
                break;
            }
        }
    }

    public synchronized void addAfter(String str, String str2, IoFilter ioFilter) {
        checkBaseName(str);
        ListIterator<IoFilterChain.Entry> listIterator = this.entries.listIterator();
        while (true) {
            if (listIterator.hasNext()) {
                if (listIterator.next().getName().equals(str)) {
                    register(listIterator.nextIndex(), new EntryImpl(str2, ioFilter));
                    break;
                }
            } else {
                break;
            }
        }
    }

    public synchronized IoFilter remove(String str) {
        IoFilterChain.Entry next;
        if (str != null) {
            try {
                ListIterator<IoFilterChain.Entry> listIterator = this.entries.listIterator();
                while (listIterator.hasNext()) {
                    next = listIterator.next();
                    if (next.getName().equals(str)) {
                        this.entries.remove(listIterator.previousIndex());
                    }
                }
                throw new IllegalArgumentException("Unknown filter name: " + str);
            } catch (Throwable th) {
                throw th;
            }
        } else {
            throw new IllegalArgumentException(ServiceProvider.NAME);
        }
        return next.getFilter();
    }

    public synchronized IoFilter remove(IoFilter ioFilter) {
        IoFilterChain.Entry next;
        if (ioFilter != null) {
            try {
                ListIterator<IoFilterChain.Entry> listIterator = this.entries.listIterator();
                while (listIterator.hasNext()) {
                    next = listIterator.next();
                    if (next.getFilter() == ioFilter) {
                        this.entries.remove(listIterator.previousIndex());
                    }
                }
                throw new IllegalArgumentException("Filter not found: " + ioFilter.getClass().getName());
            } catch (Throwable th) {
                throw th;
            }
        } else {
            throw new IllegalArgumentException("filter");
        }
        return next.getFilter();
    }

    public synchronized IoFilter remove(Class<? extends IoFilter> cls) {
        IoFilterChain.Entry next;
        if (cls != null) {
            try {
                ListIterator<IoFilterChain.Entry> listIterator = this.entries.listIterator();
                while (listIterator.hasNext()) {
                    next = listIterator.next();
                    if (cls.isAssignableFrom(next.getFilter().getClass())) {
                        this.entries.remove(listIterator.previousIndex());
                    }
                }
                throw new IllegalArgumentException("Filter not found: " + cls.getName());
            } catch (Throwable th) {
                throw th;
            }
        } else {
            throw new IllegalArgumentException("filterType");
        }
        return next.getFilter();
    }

    public synchronized IoFilter replace(String str, IoFilter ioFilter) {
        IoFilter filter;
        checkBaseName(str);
        EntryImpl entryImpl = (EntryImpl) getEntry(str);
        filter = entryImpl.getFilter();
        entryImpl.setFilter(ioFilter);
        return filter;
    }

    public synchronized void replace(IoFilter ioFilter, IoFilter ioFilter2) {
        for (IoFilterChain.Entry next : this.entries) {
            if (next.getFilter() == ioFilter) {
                ((EntryImpl) next).setFilter(ioFilter2);
            }
        }
        throw new IllegalArgumentException("Filter not found: " + ioFilter.getClass().getName());
    }

    public synchronized void replace(Class<? extends IoFilter> cls, IoFilter ioFilter) {
        for (IoFilterChain.Entry next : this.entries) {
            if (cls.isAssignableFrom(next.getFilter().getClass())) {
                ((EntryImpl) next).setFilter(ioFilter);
            }
        }
        throw new IllegalArgumentException("Filter not found: " + cls.getName());
    }

    public synchronized void clear() {
        this.entries.clear();
    }

    public void setFilters(Map<String, ? extends IoFilter> map) {
        if (map == null) {
            throw new IllegalArgumentException("filters");
        } else if (isOrderedMap(map)) {
            LinkedHashMap linkedHashMap = new LinkedHashMap(map);
            for (Map.Entry entry : linkedHashMap.entrySet()) {
                if (entry.getKey() == null) {
                    throw new IllegalArgumentException("filters contains a null key.");
                } else if (entry.getValue() == null) {
                    throw new IllegalArgumentException("filters contains a null value.");
                }
            }
            synchronized (this) {
                clear();
                for (Map.Entry entry2 : linkedHashMap.entrySet()) {
                    addLast((String) entry2.getKey(), (IoFilter) entry2.getValue());
                }
            }
        } else {
            throw new IllegalArgumentException("filters is not an ordered map. Please try " + LinkedHashMap.class.getName() + Consts.DOT);
        }
    }

    private boolean isOrderedMap(Map map) {
        String valueOf;
        Class<?> cls = map.getClass();
        if (LinkedHashMap.class.isAssignableFrom(cls)) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug(cls.getSimpleName() + " is an ordered map.");
            }
            return true;
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(cls.getName() + " is not a " + LinkedHashMap.class.getSimpleName());
        }
        Class<?> cls2 = cls;
        while (true) {
            if (cls2 != null) {
                for (Class name : cls2.getInterfaces()) {
                    if (name.getName().endsWith("OrderedMap")) {
                        if (LOGGER.isDebugEnabled()) {
                            LOGGER.debug(cls.getSimpleName() + " is an ordered map (guessed from that it " + " implements OrderedMap interface.)");
                        }
                        return true;
                    }
                }
                cls2 = cls2.getSuperclass();
            } else {
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug(cls.getName() + " doesn't implement OrderedMap interface.");
                }
                LOGGER.debug("Last resort; trying to create a new map instance with a default constructor and test if insertion order is maintained.");
                try {
                    Map map2 = (Map) cls.newInstance();
                    Random random = new Random();
                    ArrayList arrayList = new ArrayList();
                    IoFilterAdapter ioFilterAdapter = new IoFilterAdapter();
                    for (int i = 0; i < 65536; i++) {
                        do {
                            valueOf = String.valueOf(random.nextInt());
                        } while (map2.containsKey(valueOf));
                        map2.put(valueOf, ioFilterAdapter);
                        arrayList.add(valueOf);
                        Iterator it = arrayList.iterator();
                        for (Object equals : map2.keySet()) {
                            if (!((String) it.next()).equals(equals)) {
                                if (LOGGER.isDebugEnabled()) {
                                    LOGGER.debug("The specified map didn't pass the insertion order test after " + (i + 1) + " tries.");
                                }
                                return false;
                            }
                        }
                    }
                    if (LOGGER.isDebugEnabled()) {
                        LOGGER.debug("The specified map passed the insertion order test.");
                    }
                    return true;
                } catch (Exception e) {
                    if (LOGGER.isDebugEnabled()) {
                        LOGGER.debug("Failed to create a new map instance of '" + cls.getName() + "'.", (Throwable) e);
                    }
                    return false;
                }
            }
        }
    }

    public void buildFilterChain(IoFilterChain ioFilterChain) throws Exception {
        for (IoFilterChain.Entry next : this.entries) {
            ioFilterChain.addLast(next.getName(), next.getFilter());
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{ ");
        boolean z = true;
        for (IoFilterChain.Entry next : this.entries) {
            if (!z) {
                sb.append(", ");
            } else {
                z = false;
            }
            sb.append('(');
            sb.append(next.getName());
            sb.append(':');
            sb.append(next.getFilter());
            sb.append(')');
        }
        if (z) {
            sb.append("empty");
        }
        sb.append(" }");
        return sb.toString();
    }

    private void checkBaseName(String str) {
        if (str == null) {
            throw new IllegalArgumentException("baseName");
        } else if (!contains(str)) {
            throw new IllegalArgumentException("Unknown filter name: " + str);
        }
    }

    private void register(int i, IoFilterChain.Entry entry) {
        if (!contains(entry.getName())) {
            this.entries.add(i, entry);
            return;
        }
        throw new IllegalArgumentException("Other filter is using the same name: " + entry.getName());
    }

    private class EntryImpl implements IoFilterChain.Entry {
        private volatile IoFilter filter;
        private final String name;

        private EntryImpl(String str, IoFilter ioFilter) {
            if (str == null) {
                throw new IllegalArgumentException(ServiceProvider.NAME);
            } else if (ioFilter != null) {
                this.name = str;
                this.filter = ioFilter;
            } else {
                throw new IllegalArgumentException("filter");
            }
        }

        public String getName() {
            return this.name;
        }

        public IoFilter getFilter() {
            return this.filter;
        }

        /* access modifiers changed from: private */
        public void setFilter(IoFilter ioFilter) {
            this.filter = ioFilter;
        }

        public IoFilter.NextFilter getNextFilter() {
            throw new IllegalStateException();
        }

        public String toString() {
            return "(" + getName() + ':' + this.filter + ')';
        }

        public void addAfter(String str, IoFilter ioFilter) {
            DefaultIoFilterChainBuilder.this.addAfter(getName(), str, ioFilter);
        }

        public void addBefore(String str, IoFilter ioFilter) {
            DefaultIoFilterChainBuilder.this.addBefore(getName(), str, ioFilter);
        }

        public void remove() {
            DefaultIoFilterChainBuilder.this.remove(getName());
        }

        public void replace(IoFilter ioFilter) {
            DefaultIoFilterChainBuilder.this.replace(getName(), ioFilter);
        }
    }
}
