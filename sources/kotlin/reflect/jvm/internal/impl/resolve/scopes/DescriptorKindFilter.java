package kotlin.reflect.jvm.internal.impl.resolve.scopes;

import com.limpoxe.support.servicemanager.ServiceProvider;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MemberScope.kt */
public final class DescriptorKindFilter {
    public static final DescriptorKindFilter ALL = new DescriptorKindFilter(ALL_KINDS_MASK, (List) null, 2, (DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final int ALL_KINDS_MASK = (Companion.nextMask() - 1);
    public static final DescriptorKindFilter CALLABLES = new DescriptorKindFilter(CALLABLES_MASK, (List) null, 2, (DefaultConstructorMarker) null);
    private static final int CALLABLES_MASK;
    public static final DescriptorKindFilter CLASSIFIERS = new DescriptorKindFilter(CLASSIFIERS_MASK, (List) null, 2, (DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final int CLASSIFIERS_MASK;
    public static final Companion Companion;
    private static final List<Companion.MaskToName> DEBUG_MASK_BIT_NAMES;
    private static final List<Companion.MaskToName> DEBUG_PREDEFINED_FILTERS_MASK_NAMES;
    public static final DescriptorKindFilter FUNCTIONS = new DescriptorKindFilter(FUNCTIONS_MASK, (List) null, 2, (DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final int FUNCTIONS_MASK = Companion.nextMask();
    public static final DescriptorKindFilter NON_SINGLETON_CLASSIFIERS = new DescriptorKindFilter(NON_SINGLETON_CLASSIFIERS_MASK, (List) null, 2, (DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final int NON_SINGLETON_CLASSIFIERS_MASK;
    public static final DescriptorKindFilter PACKAGES = new DescriptorKindFilter(PACKAGES_MASK, (List) null, 2, (DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final int PACKAGES_MASK = Companion.nextMask();
    public static final DescriptorKindFilter SINGLETON_CLASSIFIERS = new DescriptorKindFilter(SINGLETON_CLASSIFIERS_MASK, (List) null, 2, (DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final int SINGLETON_CLASSIFIERS_MASK = Companion.nextMask();
    public static final DescriptorKindFilter TYPE_ALIASES = new DescriptorKindFilter(TYPE_ALIASES_MASK, (List) null, 2, (DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final int TYPE_ALIASES_MASK = Companion.nextMask();
    public static final DescriptorKindFilter VALUES = new DescriptorKindFilter(VALUES_MASK, (List) null, 2, (DefaultConstructorMarker) null);
    private static final int VALUES_MASK;
    public static final DescriptorKindFilter VARIABLES = new DescriptorKindFilter(VARIABLES_MASK, (List) null, 2, (DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final int VARIABLES_MASK = Companion.nextMask();
    /* access modifiers changed from: private */
    public static int nextMaskValue = 1;
    private final List<DescriptorKindExclude> excludes;
    private final int kindMask;

    public DescriptorKindFilter(int i, List<? extends DescriptorKindExclude> list) {
        Intrinsics.checkParameterIsNotNull(list, "excludes");
        this.excludes = list;
        for (DescriptorKindExclude fullyExcludedDescriptorKinds : list) {
            i &= ~fullyExcludedDescriptorKinds.getFullyExcludedDescriptorKinds();
        }
        this.kindMask = i;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ DescriptorKindFilter(int i, List list, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, (i2 & 2) != 0 ? CollectionsKt.emptyList() : list);
    }

    public final List<DescriptorKindExclude> getExcludes() {
        return this.excludes;
    }

    public final int getKindMask() {
        return this.kindMask;
    }

    public final boolean acceptsKinds(int i) {
        return (i & this.kindMask) != 0;
    }

    public final DescriptorKindFilter restrictedToKindsOrNull(int i) {
        int i2 = i & this.kindMask;
        if (i2 == 0) {
            return null;
        }
        return new DescriptorKindFilter(i2, this.excludes);
    }

    public String toString() {
        Object obj;
        boolean z;
        Iterator it = DEBUG_PREDEFINED_FILTERS_MASK_NAMES.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (((Companion.MaskToName) obj).getMask() == this.kindMask) {
                z = true;
                continue;
            } else {
                z = false;
                continue;
            }
            if (z) {
                break;
            }
        }
        Companion.MaskToName maskToName = (Companion.MaskToName) obj;
        String name = maskToName != null ? maskToName.getName() : null;
        if (name == null) {
            Collection arrayList = new ArrayList();
            for (Companion.MaskToName maskToName2 : DEBUG_MASK_BIT_NAMES) {
                String name2 = acceptsKinds(maskToName2.getMask()) ? maskToName2.getName() : null;
                if (name2 != null) {
                    arrayList.add(name2);
                }
            }
            name = CollectionsKt.joinToString$default((List) arrayList, " | ", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null);
        }
        return "DescriptorKindFilter(" + name + ", " + this.excludes + ')';
    }

    /* compiled from: MemberScope.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* access modifiers changed from: private */
        public final int nextMask() {
            int access$getNextMaskValue$cp = DescriptorKindFilter.nextMaskValue;
            DescriptorKindFilter.nextMaskValue = DescriptorKindFilter.nextMaskValue << 1;
            return access$getNextMaskValue$cp;
        }

        public final int getNON_SINGLETON_CLASSIFIERS_MASK() {
            return DescriptorKindFilter.NON_SINGLETON_CLASSIFIERS_MASK;
        }

        public final int getSINGLETON_CLASSIFIERS_MASK() {
            return DescriptorKindFilter.SINGLETON_CLASSIFIERS_MASK;
        }

        public final int getTYPE_ALIASES_MASK() {
            return DescriptorKindFilter.TYPE_ALIASES_MASK;
        }

        public final int getPACKAGES_MASK() {
            return DescriptorKindFilter.PACKAGES_MASK;
        }

        public final int getFUNCTIONS_MASK() {
            return DescriptorKindFilter.FUNCTIONS_MASK;
        }

        public final int getVARIABLES_MASK() {
            return DescriptorKindFilter.VARIABLES_MASK;
        }

        public final int getALL_KINDS_MASK() {
            return DescriptorKindFilter.ALL_KINDS_MASK;
        }

        public final int getCLASSIFIERS_MASK() {
            return DescriptorKindFilter.CLASSIFIERS_MASK;
        }

        /* compiled from: MemberScope.kt */
        private static final class MaskToName {
            private final int mask;
            private final String name;

            public MaskToName(int i, String str) {
                Intrinsics.checkParameterIsNotNull(str, ServiceProvider.NAME);
                this.mask = i;
                this.name = str;
            }

            public final int getMask() {
                return this.mask;
            }

            public final String getName() {
                return this.name;
            }
        }
    }

    static {
        Companion.MaskToName maskToName;
        Companion.MaskToName maskToName2;
        Class<DescriptorKindFilter> cls = DescriptorKindFilter.class;
        Companion companion = new Companion((DefaultConstructorMarker) null);
        Companion = companion;
        NON_SINGLETON_CLASSIFIERS_MASK = companion.nextMask();
        int i = NON_SINGLETON_CLASSIFIERS_MASK;
        int i2 = SINGLETON_CLASSIFIERS_MASK;
        CLASSIFIERS_MASK = i | i2 | TYPE_ALIASES_MASK;
        int i3 = FUNCTIONS_MASK;
        int i4 = VARIABLES_MASK;
        VALUES_MASK = i2 | i3 | i4;
        CALLABLES_MASK = i3 | i4;
        Field[] fields = cls.getFields();
        Intrinsics.checkExpressionValueIsNotNull(fields, "T::class.java.fields");
        Collection arrayList = new ArrayList();
        for (Field field : fields) {
            Intrinsics.checkExpressionValueIsNotNull(field, "it");
            if (Modifier.isStatic(field.getModifiers())) {
                arrayList.add(field);
            }
        }
        Collection arrayList2 = new ArrayList();
        for (Field field2 : (List) arrayList) {
            Object obj = field2.get((Object) null);
            if (!(obj instanceof DescriptorKindFilter)) {
                obj = null;
            }
            DescriptorKindFilter descriptorKindFilter = (DescriptorKindFilter) obj;
            if (descriptorKindFilter != null) {
                int i5 = descriptorKindFilter.kindMask;
                Intrinsics.checkExpressionValueIsNotNull(field2, "field");
                String name = field2.getName();
                Intrinsics.checkExpressionValueIsNotNull(name, "field.name");
                maskToName2 = new Companion.MaskToName(i5, name);
            } else {
                maskToName2 = null;
            }
            if (maskToName2 != null) {
                arrayList2.add(maskToName2);
            }
        }
        DEBUG_PREDEFINED_FILTERS_MASK_NAMES = CollectionsKt.toList((List) arrayList2);
        Field[] fields2 = cls.getFields();
        Intrinsics.checkExpressionValueIsNotNull(fields2, "T::class.java.fields");
        Collection arrayList3 = new ArrayList();
        for (Field field3 : fields2) {
            Intrinsics.checkExpressionValueIsNotNull(field3, "it");
            if (Modifier.isStatic(field3.getModifiers())) {
                arrayList3.add(field3);
            }
        }
        Collection arrayList4 = new ArrayList();
        for (Object next : (List) arrayList3) {
            Field field4 = (Field) next;
            Intrinsics.checkExpressionValueIsNotNull(field4, "it");
            if (Intrinsics.areEqual((Object) field4.getType(), (Object) Integer.TYPE)) {
                arrayList4.add(next);
            }
        }
        Collection arrayList5 = new ArrayList();
        for (Field field5 : (List) arrayList4) {
            Object obj2 = field5.get((Object) null);
            if (obj2 != null) {
                int intValue = ((Integer) obj2).intValue();
                if (intValue == ((-intValue) & intValue)) {
                    Intrinsics.checkExpressionValueIsNotNull(field5, "field");
                    String name2 = field5.getName();
                    Intrinsics.checkExpressionValueIsNotNull(name2, "field.name");
                    maskToName = new Companion.MaskToName(intValue, name2);
                } else {
                    maskToName = null;
                }
                if (maskToName != null) {
                    arrayList5.add(maskToName);
                }
            } else {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.Int");
            }
        }
        DEBUG_MASK_BIT_NAMES = CollectionsKt.toList((List) arrayList5);
    }
}
