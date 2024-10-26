package org.apache.commons.collections4.comparators;

import java.io.Serializable;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FixedOrderComparator<T> implements Comparator<T>, Serializable {
    private static final long serialVersionUID = 82794675842863201L;
    private int counter;
    private boolean isLocked;
    private final Map<T, Integer> map;
    private UnknownObjectBehavior unknownObjectBehavior;

    public enum UnknownObjectBehavior {
        BEFORE,
        AFTER,
        EXCEPTION
    }

    public FixedOrderComparator() {
        this.map = new HashMap();
        this.counter = 0;
        this.isLocked = false;
        this.unknownObjectBehavior = UnknownObjectBehavior.EXCEPTION;
    }

    public FixedOrderComparator(T... tArr) {
        this.map = new HashMap();
        this.counter = 0;
        this.isLocked = false;
        this.unknownObjectBehavior = UnknownObjectBehavior.EXCEPTION;
        if (tArr != null) {
            for (T add : tArr) {
                add(add);
            }
            return;
        }
        throw new NullPointerException("The list of items must not be null");
    }

    public FixedOrderComparator(List<T> list) {
        this.map = new HashMap();
        this.counter = 0;
        this.isLocked = false;
        this.unknownObjectBehavior = UnknownObjectBehavior.EXCEPTION;
        if (list != null) {
            for (T add : list) {
                add(add);
            }
            return;
        }
        throw new NullPointerException("The list of items must not be null");
    }

    public boolean isLocked() {
        return this.isLocked;
    }

    /* access modifiers changed from: protected */
    public void checkLocked() {
        if (isLocked()) {
            throw new UnsupportedOperationException("Cannot modify a FixedOrderComparator after a comparison");
        }
    }

    public UnknownObjectBehavior getUnknownObjectBehavior() {
        return this.unknownObjectBehavior;
    }

    public void setUnknownObjectBehavior(UnknownObjectBehavior unknownObjectBehavior2) {
        checkLocked();
        if (unknownObjectBehavior2 != null) {
            this.unknownObjectBehavior = unknownObjectBehavior2;
            return;
        }
        throw new NullPointerException("Unknown object behavior must not be null");
    }

    public boolean add(T t) {
        checkLocked();
        Map<T, Integer> map2 = this.map;
        int i = this.counter;
        this.counter = i + 1;
        return map2.put(t, Integer.valueOf(i)) == null;
    }

    public boolean addAsEqual(T t, T t2) {
        checkLocked();
        Integer num = this.map.get(t);
        if (num != null) {
            return this.map.put(t2, num) == null;
        }
        throw new IllegalArgumentException(t + " not known to " + this);
    }

    public int compare(T t, T t2) {
        this.isLocked = true;
        Integer num = this.map.get(t);
        Integer num2 = this.map.get(t2);
        if (num != null && num2 != null) {
            return num.compareTo(num2);
        }
        int i = AnonymousClass1.$SwitchMap$org$apache$commons$collections4$comparators$FixedOrderComparator$UnknownObjectBehavior[this.unknownObjectBehavior.ordinal()];
        if (i != 1) {
            if (i != 2) {
                if (i == 3) {
                    if (num != null) {
                        t = t2;
                    }
                    throw new IllegalArgumentException("Attempting to compare unknown object " + t);
                }
                throw new UnsupportedOperationException("Unknown unknownObjectBehavior: " + this.unknownObjectBehavior);
            } else if (num != null) {
                return -1;
            } else {
                if (num2 == null) {
                    return 0;
                }
                return 1;
            }
        } else if (num == null) {
            return num2 == null ? 0 : -1;
        } else {
            return 1;
        }
    }

    /* renamed from: org.apache.commons.collections4.comparators.FixedOrderComparator$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$commons$collections4$comparators$FixedOrderComparator$UnknownObjectBehavior;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                org.apache.commons.collections4.comparators.FixedOrderComparator$UnknownObjectBehavior[] r0 = org.apache.commons.collections4.comparators.FixedOrderComparator.UnknownObjectBehavior.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$apache$commons$collections4$comparators$FixedOrderComparator$UnknownObjectBehavior = r0
                org.apache.commons.collections4.comparators.FixedOrderComparator$UnknownObjectBehavior r1 = org.apache.commons.collections4.comparators.FixedOrderComparator.UnknownObjectBehavior.BEFORE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$apache$commons$collections4$comparators$FixedOrderComparator$UnknownObjectBehavior     // Catch:{ NoSuchFieldError -> 0x001d }
                org.apache.commons.collections4.comparators.FixedOrderComparator$UnknownObjectBehavior r1 = org.apache.commons.collections4.comparators.FixedOrderComparator.UnknownObjectBehavior.AFTER     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$org$apache$commons$collections4$comparators$FixedOrderComparator$UnknownObjectBehavior     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.apache.commons.collections4.comparators.FixedOrderComparator$UnknownObjectBehavior r1 = org.apache.commons.collections4.comparators.FixedOrderComparator.UnknownObjectBehavior.EXCEPTION     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.collections4.comparators.FixedOrderComparator.AnonymousClass1.<clinit>():void");
        }
    }

    public int hashCode() {
        Map<T, Integer> map2 = this.map;
        int i = 0;
        int hashCode = (629 + (map2 == null ? 0 : map2.hashCode())) * 37;
        UnknownObjectBehavior unknownObjectBehavior2 = this.unknownObjectBehavior;
        if (unknownObjectBehavior2 != null) {
            i = unknownObjectBehavior2.hashCode();
        }
        return ((((hashCode + i) * 37) + this.counter) * 37) + (this.isLocked ^ true ? 1 : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !obj.getClass().equals(getClass())) {
            return false;
        }
        FixedOrderComparator fixedOrderComparator = (FixedOrderComparator) obj;
        Map<T, Integer> map2 = this.map;
        if (map2 != null ? map2.equals(fixedOrderComparator.map) : fixedOrderComparator.map == null) {
            UnknownObjectBehavior unknownObjectBehavior2 = this.unknownObjectBehavior;
            if (unknownObjectBehavior2 != null) {
                UnknownObjectBehavior unknownObjectBehavior3 = fixedOrderComparator.unknownObjectBehavior;
                if (unknownObjectBehavior2 == unknownObjectBehavior3 && this.counter == fixedOrderComparator.counter && this.isLocked == fixedOrderComparator.isLocked && unknownObjectBehavior2 == unknownObjectBehavior3) {
                    return true;
                }
                return false;
            } else if (fixedOrderComparator.unknownObjectBehavior == null) {
                return true;
            }
        }
        return false;
    }
}
