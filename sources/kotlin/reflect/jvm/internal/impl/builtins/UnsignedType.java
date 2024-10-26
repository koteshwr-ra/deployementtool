package kotlin.reflect.jvm.internal.impl.builtins;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;

/* compiled from: UnsignedType.kt */
public enum UnsignedType {
    UBYTE(r2),
    USHORT(r2),
    UINT(r2),
    ULONG(r2);
    
    private final ClassId arrayClassId;
    private final ClassId classId;
    private final Name typeName;

    private UnsignedType(ClassId classId2) {
        this.classId = classId2;
        Name shortClassName = classId2.getShortClassName();
        Intrinsics.checkExpressionValueIsNotNull(shortClassName, "classId.shortClassName");
        this.typeName = shortClassName;
        FqName packageFqName = this.classId.getPackageFqName();
        this.arrayClassId = new ClassId(packageFqName, Name.identifier(this.typeName.asString() + "Array"));
    }

    public final ClassId getClassId() {
        return this.classId;
    }

    public final Name getTypeName() {
        return this.typeName;
    }

    public final ClassId getArrayClassId() {
        return this.arrayClassId;
    }
}
