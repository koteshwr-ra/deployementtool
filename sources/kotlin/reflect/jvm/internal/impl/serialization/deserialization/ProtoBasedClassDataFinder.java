package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.BinaryVersion;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver;
import kotlin.reflect.jvm.internal.impl.name.ClassId;

/* compiled from: ProtoBasedClassDataFinder.kt */
public final class ProtoBasedClassDataFinder implements ClassDataFinder {
    private final Map<ClassId, ProtoBuf.Class> classIdToProto;
    private final Function1<ClassId, SourceElement> classSource;
    private final BinaryVersion metadataVersion;
    private final NameResolver nameResolver;

    public ProtoBasedClassDataFinder(ProtoBuf.PackageFragment packageFragment, NameResolver nameResolver2, BinaryVersion binaryVersion, Function1<? super ClassId, ? extends SourceElement> function1) {
        Intrinsics.checkParameterIsNotNull(packageFragment, "proto");
        Intrinsics.checkParameterIsNotNull(nameResolver2, "nameResolver");
        Intrinsics.checkParameterIsNotNull(binaryVersion, "metadataVersion");
        Intrinsics.checkParameterIsNotNull(function1, "classSource");
        this.nameResolver = nameResolver2;
        this.metadataVersion = binaryVersion;
        this.classSource = function1;
        List<ProtoBuf.Class> class_List = packageFragment.getClass_List();
        Intrinsics.checkExpressionValueIsNotNull(class_List, "proto.class_List");
        Iterable iterable = class_List;
        Map<ClassId, ProtoBuf.Class> linkedHashMap = new LinkedHashMap<>(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(iterable, 10)), 16));
        for (Object next : iterable) {
            ProtoBuf.Class classR = (ProtoBuf.Class) next;
            NameResolver nameResolver3 = this.nameResolver;
            Intrinsics.checkExpressionValueIsNotNull(classR, "klass");
            linkedHashMap.put(NameResolverUtilKt.getClassId(nameResolver3, classR.getFqName()), next);
        }
        this.classIdToProto = linkedHashMap;
    }

    public final Collection<ClassId> getAllClassIds() {
        return this.classIdToProto.keySet();
    }

    public ClassData findClassData(ClassId classId) {
        Intrinsics.checkParameterIsNotNull(classId, "classId");
        ProtoBuf.Class classR = this.classIdToProto.get(classId);
        if (classR != null) {
            return new ClassData(this.nameResolver, classR, this.metadataVersion, this.classSource.invoke(classId));
        }
        return null;
    }
}
