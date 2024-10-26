package org.apache.commons.collections4.functors;

import java.io.Serializable;
import java.util.Comparator;
import org.apache.commons.collections4.Predicate;

public class ComparatorPredicate<T> implements Predicate<T>, Serializable {
    private static final long serialVersionUID = -1863209236504077399L;
    private final Comparator<T> comparator;
    private final Criterion criterion;
    private final T object;

    public enum Criterion {
        EQUAL,
        GREATER,
        LESS,
        GREATER_OR_EQUAL,
        LESS_OR_EQUAL
    }

    public static <T> Predicate<T> comparatorPredicate(T t, Comparator<T> comparator2) {
        return comparatorPredicate(t, comparator2, Criterion.EQUAL);
    }

    public static <T> Predicate<T> comparatorPredicate(T t, Comparator<T> comparator2, Criterion criterion2) {
        if (comparator2 == null) {
            throw new NullPointerException("Comparator must not be null.");
        } else if (criterion2 != null) {
            return new ComparatorPredicate(t, comparator2, criterion2);
        } else {
            throw new NullPointerException("Criterion must not be null.");
        }
    }

    public ComparatorPredicate(T t, Comparator<T> comparator2, Criterion criterion2) {
        this.object = t;
        this.comparator = comparator2;
        this.criterion = criterion2;
    }

    /* renamed from: org.apache.commons.collections4.functors.ComparatorPredicate$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$commons$collections4$functors$ComparatorPredicate$Criterion;

        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|12) */
        /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                org.apache.commons.collections4.functors.ComparatorPredicate$Criterion[] r0 = org.apache.commons.collections4.functors.ComparatorPredicate.Criterion.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$apache$commons$collections4$functors$ComparatorPredicate$Criterion = r0
                org.apache.commons.collections4.functors.ComparatorPredicate$Criterion r1 = org.apache.commons.collections4.functors.ComparatorPredicate.Criterion.EQUAL     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$apache$commons$collections4$functors$ComparatorPredicate$Criterion     // Catch:{ NoSuchFieldError -> 0x001d }
                org.apache.commons.collections4.functors.ComparatorPredicate$Criterion r1 = org.apache.commons.collections4.functors.ComparatorPredicate.Criterion.GREATER     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$org$apache$commons$collections4$functors$ComparatorPredicate$Criterion     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.apache.commons.collections4.functors.ComparatorPredicate$Criterion r1 = org.apache.commons.collections4.functors.ComparatorPredicate.Criterion.LESS     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$org$apache$commons$collections4$functors$ComparatorPredicate$Criterion     // Catch:{ NoSuchFieldError -> 0x0033 }
                org.apache.commons.collections4.functors.ComparatorPredicate$Criterion r1 = org.apache.commons.collections4.functors.ComparatorPredicate.Criterion.GREATER_OR_EQUAL     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$org$apache$commons$collections4$functors$ComparatorPredicate$Criterion     // Catch:{ NoSuchFieldError -> 0x003e }
                org.apache.commons.collections4.functors.ComparatorPredicate$Criterion r1 = org.apache.commons.collections4.functors.ComparatorPredicate.Criterion.LESS_OR_EQUAL     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.collections4.functors.ComparatorPredicate.AnonymousClass1.<clinit>():void");
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0024 A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean evaluate(T r5) {
        /*
            r4 = this;
            java.util.Comparator<T> r0 = r4.comparator
            T r1 = r4.object
            int r5 = r0.compare(r1, r5)
            int[] r0 = org.apache.commons.collections4.functors.ComparatorPredicate.AnonymousClass1.$SwitchMap$org$apache$commons$collections4$functors$ComparatorPredicate$Criterion
            org.apache.commons.collections4.functors.ComparatorPredicate$Criterion r1 = r4.criterion
            int r1 = r1.ordinal()
            r0 = r0[r1]
            r1 = 0
            r2 = 1
            if (r0 == r2) goto L_0x004d
            r3 = 2
            if (r0 == r3) goto L_0x004a
            r3 = 3
            if (r0 == r3) goto L_0x0047
            r3 = 4
            if (r0 == r3) goto L_0x0044
            r3 = 5
            if (r0 != r3) goto L_0x0026
            if (r5 > 0) goto L_0x0050
        L_0x0024:
            r1 = 1
            goto L_0x0050
        L_0x0026:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "The current criterion '"
            r0.append(r1)
            org.apache.commons.collections4.functors.ComparatorPredicate$Criterion r1 = r4.criterion
            r0.append(r1)
            java.lang.String r1 = "' is invalid."
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r5.<init>(r0)
            throw r5
        L_0x0044:
            if (r5 < 0) goto L_0x0050
            goto L_0x0024
        L_0x0047:
            if (r5 >= 0) goto L_0x0050
            goto L_0x0024
        L_0x004a:
            if (r5 <= 0) goto L_0x0050
            goto L_0x0024
        L_0x004d:
            if (r5 != 0) goto L_0x0050
            goto L_0x0024
        L_0x0050:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.collections4.functors.ComparatorPredicate.evaluate(java.lang.Object):boolean");
    }
}
