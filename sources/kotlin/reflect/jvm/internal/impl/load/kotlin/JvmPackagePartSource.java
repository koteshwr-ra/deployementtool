package kotlin.reflect.jvm.internal.impl.load.kotlin;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceFile;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMetadataVersion;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmClassName;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.IncompatibleVersionErrorData;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedContainerSource;
import kotlin.text.StringsKt;

/* compiled from: JvmPackagePartSource.kt */
public final class JvmPackagePartSource implements DeserializedContainerSource {
    private final JvmClassName className;
    private final JvmClassName facadeClassName;
    private final IncompatibleVersionErrorData<JvmMetadataVersion> incompatibility;
    private final boolean isPreReleaseInvisible;
    private final KotlinJvmBinaryClass knownJvmBinaryClass;
    private final String moduleName;

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x002d, code lost:
        r2 = r5.getString(r2.intValue());
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public JvmPackagePartSource(kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmClassName r2, kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmClassName r3, kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Package r4, kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver r5, kotlin.reflect.jvm.internal.impl.serialization.deserialization.IncompatibleVersionErrorData<kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMetadataVersion> r6, boolean r7, kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass r8) {
        /*
            r1 = this;
            java.lang.String r0 = "className"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r2, r0)
            java.lang.String r0 = "packageProto"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r4, r0)
            java.lang.String r0 = "nameResolver"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r5, r0)
            r1.<init>()
            r1.className = r2
            r1.facadeClassName = r3
            r1.incompatibility = r6
            r1.isPreReleaseInvisible = r7
            r1.knownJvmBinaryClass = r8
            kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$ExtendableMessage r4 = (kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.ExtendableMessage) r4
            kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$GeneratedExtension<kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Package, java.lang.Integer> r2 = kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.packageModuleName
            java.lang.String r3 = "JvmProtoBuf.packageModuleName"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r2, r3)
            java.lang.Object r2 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.ProtoBufUtilKt.getExtensionOrNull(r4, r2)
            java.lang.Integer r2 = (java.lang.Integer) r2
            if (r2 == 0) goto L_0x003a
            java.lang.Number r2 = (java.lang.Number) r2
            int r2 = r2.intValue()
            java.lang.String r2 = r5.getString(r2)
            if (r2 == 0) goto L_0x003a
            goto L_0x003c
        L_0x003a:
            java.lang.String r2 = "main"
        L_0x003c:
            r1.moduleName = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.load.kotlin.JvmPackagePartSource.<init>(kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmClassName, kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmClassName, kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Package, kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver, kotlin.reflect.jvm.internal.impl.serialization.deserialization.IncompatibleVersionErrorData, boolean, kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass):void");
    }

    public final JvmClassName getFacadeClassName() {
        return this.facadeClassName;
    }

    public final KotlinJvmBinaryClass getKnownJvmBinaryClass() {
        return this.knownJvmBinaryClass;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public JvmPackagePartSource(kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass r10, kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Package r11, kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver r12, kotlin.reflect.jvm.internal.impl.serialization.deserialization.IncompatibleVersionErrorData<kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMetadataVersion> r13, boolean r14) {
        /*
            r9 = this;
            java.lang.String r0 = "kotlinClass"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r10, r0)
            java.lang.String r0 = "packageProto"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r11, r0)
            java.lang.String r0 = "nameResolver"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r12, r0)
            kotlin.reflect.jvm.internal.impl.name.ClassId r0 = r10.getClassId()
            kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmClassName r2 = kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmClassName.byClassId(r0)
            java.lang.String r0 = "JvmClassName.byClassId(kotlinClass.classId)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r2, r0)
            kotlin.reflect.jvm.internal.impl.load.kotlin.header.KotlinClassHeader r0 = r10.getClassHeader()
            java.lang.String r0 = r0.getMultifileClassName()
            r1 = 0
            if (r0 == 0) goto L_0x0039
            r3 = r0
            java.lang.CharSequence r3 = (java.lang.CharSequence) r3
            int r3 = r3.length()
            if (r3 <= 0) goto L_0x0032
            r3 = 1
            goto L_0x0033
        L_0x0032:
            r3 = 0
        L_0x0033:
            if (r3 == 0) goto L_0x0039
            kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmClassName r1 = kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmClassName.byInternalName(r0)
        L_0x0039:
            r3 = r1
            r1 = r9
            r4 = r11
            r5 = r12
            r6 = r13
            r7 = r14
            r8 = r10
            r1.<init>(r2, r3, r4, r5, r6, r7, r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.load.kotlin.JvmPackagePartSource.<init>(kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass, kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Package, kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver, kotlin.reflect.jvm.internal.impl.serialization.deserialization.IncompatibleVersionErrorData, boolean):void");
    }

    public String getPresentableString() {
        return "Class '" + getClassId().asSingleFqName().asString() + '\'';
    }

    public final Name getSimpleName() {
        String internalName = this.className.getInternalName();
        Intrinsics.checkExpressionValueIsNotNull(internalName, "className.internalName");
        Name identifier = Name.identifier(StringsKt.substringAfterLast$default(internalName, '/', (String) null, 2, (Object) null));
        Intrinsics.checkExpressionValueIsNotNull(identifier, "Name.identifier(classNam….substringAfterLast('/'))");
        return identifier;
    }

    public final ClassId getClassId() {
        return new ClassId(this.className.getPackageFqName(), getSimpleName());
    }

    public String toString() {
        return getClass().getSimpleName() + ": " + this.className;
    }

    public SourceFile getContainingFile() {
        SourceFile sourceFile = SourceFile.NO_SOURCE_FILE;
        Intrinsics.checkExpressionValueIsNotNull(sourceFile, "SourceFile.NO_SOURCE_FILE");
        return sourceFile;
    }
}
