package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.BinaryVersion;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.TypeTable;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.VersionRequirementTable;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.VersionSpecificBehaviorKt;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedContainerSource;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;

/* compiled from: context.kt */
public final class DeserializationContext {
    private final DeserializationComponents components;
    private final DeserializedContainerSource containerSource;
    private final DeclarationDescriptor containingDeclaration;
    private final MemberDeserializer memberDeserializer;
    private final BinaryVersion metadataVersion;
    private final NameResolver nameResolver;
    private final TypeDeserializer typeDeserializer;
    private final TypeTable typeTable;
    private final VersionRequirementTable versionRequirementTable;

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0064, code lost:
        r0 = r0.getPresentableString();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public DeserializationContext(kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationComponents r13, kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver r14, kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor r15, kotlin.reflect.jvm.internal.impl.metadata.deserialization.TypeTable r16, kotlin.reflect.jvm.internal.impl.metadata.deserialization.VersionRequirementTable r17, kotlin.reflect.jvm.internal.impl.metadata.deserialization.BinaryVersion r18, kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedContainerSource r19, kotlin.reflect.jvm.internal.impl.serialization.deserialization.TypeDeserializer r20, java.util.List<kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeParameter> r21) {
        /*
            r12 = this;
            r9 = r12
            r0 = r13
            r1 = r14
            r2 = r15
            r3 = r16
            r4 = r17
            r5 = r18
            java.lang.String r6 = "components"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r13, r6)
            java.lang.String r6 = "nameResolver"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r14, r6)
            java.lang.String r6 = "containingDeclaration"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r15, r6)
            java.lang.String r6 = "typeTable"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r3, r6)
            java.lang.String r6 = "versionRequirementTable"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r4, r6)
            java.lang.String r6 = "metadataVersion"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r5, r6)
            java.lang.String r6 = "typeParameters"
            r7 = r21
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r7, r6)
            r12.<init>()
            r9.components = r0
            r9.nameResolver = r1
            r9.containingDeclaration = r2
            r9.typeTable = r3
            r9.versionRequirementTable = r4
            r9.metadataVersion = r5
            r0 = r19
            r9.containerSource = r0
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.TypeDeserializer r10 = new kotlin.reflect.jvm.internal.impl.serialization.deserialization.TypeDeserializer
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Deserializer for \""
            r0.append(r1)
            kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor r1 = r9.containingDeclaration
            kotlin.reflect.jvm.internal.impl.name.Name r1 = r1.getName()
            r0.append(r1)
            r1 = 34
            r0.append(r1)
            java.lang.String r4 = r0.toString()
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedContainerSource r0 = r9.containerSource
            if (r0 == 0) goto L_0x006b
            java.lang.String r0 = r0.getPresentableString()
            if (r0 == 0) goto L_0x006b
            goto L_0x006d
        L_0x006b:
            java.lang.String r0 = "[container not found]"
        L_0x006d:
            r5 = r0
            r6 = 0
            r8 = 32
            r11 = 0
            r0 = r10
            r1 = r12
            r2 = r20
            r3 = r21
            r7 = r8
            r8 = r11
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8)
            r9.typeDeserializer = r10
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.MemberDeserializer r0 = new kotlin.reflect.jvm.internal.impl.serialization.deserialization.MemberDeserializer
            r0.<init>(r12)
            r9.memberDeserializer = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationContext.<init>(kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationComponents, kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver, kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor, kotlin.reflect.jvm.internal.impl.metadata.deserialization.TypeTable, kotlin.reflect.jvm.internal.impl.metadata.deserialization.VersionRequirementTable, kotlin.reflect.jvm.internal.impl.metadata.deserialization.BinaryVersion, kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedContainerSource, kotlin.reflect.jvm.internal.impl.serialization.deserialization.TypeDeserializer, java.util.List):void");
    }

    public final DeserializationComponents getComponents() {
        return this.components;
    }

    public final NameResolver getNameResolver() {
        return this.nameResolver;
    }

    public final DeclarationDescriptor getContainingDeclaration() {
        return this.containingDeclaration;
    }

    public final TypeTable getTypeTable() {
        return this.typeTable;
    }

    public final VersionRequirementTable getVersionRequirementTable() {
        return this.versionRequirementTable;
    }

    public final DeserializedContainerSource getContainerSource() {
        return this.containerSource;
    }

    public final TypeDeserializer getTypeDeserializer() {
        return this.typeDeserializer;
    }

    public final MemberDeserializer getMemberDeserializer() {
        return this.memberDeserializer;
    }

    public final StorageManager getStorageManager() {
        return this.components.getStorageManager();
    }

    public static /* synthetic */ DeserializationContext childContext$default(DeserializationContext deserializationContext, DeclarationDescriptor declarationDescriptor, List list, NameResolver nameResolver2, TypeTable typeTable2, VersionRequirementTable versionRequirementTable2, BinaryVersion binaryVersion, int i, Object obj) {
        if ((i & 4) != 0) {
            nameResolver2 = deserializationContext.nameResolver;
        }
        NameResolver nameResolver3 = nameResolver2;
        if ((i & 8) != 0) {
            typeTable2 = deserializationContext.typeTable;
        }
        TypeTable typeTable3 = typeTable2;
        if ((i & 16) != 0) {
            versionRequirementTable2 = deserializationContext.versionRequirementTable;
        }
        VersionRequirementTable versionRequirementTable3 = versionRequirementTable2;
        if ((i & 32) != 0) {
            binaryVersion = deserializationContext.metadataVersion;
        }
        return deserializationContext.childContext(declarationDescriptor, list, nameResolver3, typeTable3, versionRequirementTable3, binaryVersion);
    }

    public final DeserializationContext childContext(DeclarationDescriptor declarationDescriptor, List<ProtoBuf.TypeParameter> list, NameResolver nameResolver2, TypeTable typeTable2, VersionRequirementTable versionRequirementTable2, BinaryVersion binaryVersion) {
        DeclarationDescriptor declarationDescriptor2 = declarationDescriptor;
        Intrinsics.checkParameterIsNotNull(declarationDescriptor, "descriptor");
        List<ProtoBuf.TypeParameter> list2 = list;
        Intrinsics.checkParameterIsNotNull(list, "typeParameterProtos");
        NameResolver nameResolver3 = nameResolver2;
        Intrinsics.checkParameterIsNotNull(nameResolver2, "nameResolver");
        Intrinsics.checkParameterIsNotNull(typeTable2, "typeTable");
        VersionRequirementTable versionRequirementTable3 = versionRequirementTable2;
        Intrinsics.checkParameterIsNotNull(versionRequirementTable3, "versionRequirementTable");
        Intrinsics.checkParameterIsNotNull(binaryVersion, "metadataVersion");
        DeserializationComponents deserializationComponents = this.components;
        if (!VersionSpecificBehaviorKt.isVersionRequirementTableWrittenCorrectly(binaryVersion)) {
            versionRequirementTable3 = this.versionRequirementTable;
        }
        return new DeserializationContext(deserializationComponents, nameResolver2, declarationDescriptor, typeTable2, versionRequirementTable3, binaryVersion, this.containerSource, this.typeDeserializer, list);
    }
}
