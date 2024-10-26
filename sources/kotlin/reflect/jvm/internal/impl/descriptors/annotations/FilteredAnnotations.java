package kotlin.reflect.jvm.internal.impl.descriptors.annotations;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.name.FqName;

/* compiled from: Annotations.kt */
public final class FilteredAnnotations implements Annotations {
    private final Annotations delegate;
    private final Function1<FqName, Boolean> fqNameFilter;

    public FilteredAnnotations(Annotations annotations, Function1<? super FqName, Boolean> function1) {
        Intrinsics.checkParameterIsNotNull(annotations, "delegate");
        Intrinsics.checkParameterIsNotNull(function1, "fqNameFilter");
        this.delegate = annotations;
        this.fqNameFilter = function1;
    }

    public boolean hasAnnotation(FqName fqName) {
        Intrinsics.checkParameterIsNotNull(fqName, "fqName");
        if (this.fqNameFilter.invoke(fqName).booleanValue()) {
            return this.delegate.hasAnnotation(fqName);
        }
        return false;
    }

    public AnnotationDescriptor findAnnotation(FqName fqName) {
        Intrinsics.checkParameterIsNotNull(fqName, "fqName");
        if (this.fqNameFilter.invoke(fqName).booleanValue()) {
            return this.delegate.findAnnotation(fqName);
        }
        return null;
    }

    public Iterator<AnnotationDescriptor> iterator() {
        Collection arrayList = new ArrayList();
        for (Object next : this.delegate) {
            if (shouldBeReturned((AnnotationDescriptor) next)) {
                arrayList.add(next);
            }
        }
        return ((List) arrayList).iterator();
    }

    public boolean isEmpty() {
        Iterable<AnnotationDescriptor> iterable = this.delegate;
        if ((iterable instanceof Collection) && ((Collection) iterable).isEmpty()) {
            return false;
        }
        for (AnnotationDescriptor shouldBeReturned : iterable) {
            if (shouldBeReturned(shouldBeReturned)) {
                return true;
            }
        }
        return false;
    }

    private final boolean shouldBeReturned(AnnotationDescriptor annotationDescriptor) {
        FqName fqName = annotationDescriptor.getFqName();
        return fqName != null && this.fqNameFilter.invoke(fqName).booleanValue();
    }
}
