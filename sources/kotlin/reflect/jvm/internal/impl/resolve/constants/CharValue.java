package kotlin.reflect.jvm.internal.impl.resolve.constants;

import java.util.Arrays;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import org.apache.log4j.spi.LocationInfo;

/* compiled from: constantValues.kt */
public final class CharValue extends IntegerValueConstant<Character> {
    public CharValue(char c) {
        super(Character.valueOf(c));
    }

    public SimpleType getType(ModuleDescriptor moduleDescriptor) {
        Intrinsics.checkParameterIsNotNull(moduleDescriptor, "module");
        SimpleType charType = moduleDescriptor.getBuiltIns().getCharType();
        Intrinsics.checkExpressionValueIsNotNull(charType, "module.builtIns.charType");
        return charType;
    }

    public String toString() {
        String format = String.format("\\u%04X ('%s')", Arrays.copyOf(new Object[]{Integer.valueOf(((Character) getValue()).charValue()), getPrintablePart(((Character) getValue()).charValue())}, 2));
        Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(this, *args)");
        return format;
    }

    private final String getPrintablePart(char c) {
        switch (c) {
            case 8:
                return "\\b";
            case 9:
                return "\\t";
            case 10:
                return "\\n";
            case 12:
                return "\\f";
            case 13:
                return "\\r";
            default:
                return isPrintableUnicode(c) ? String.valueOf(c) : LocationInfo.NA;
        }
    }

    private final boolean isPrintableUnicode(char c) {
        byte type = (byte) Character.getType(c);
        return (type == 0 || type == 13 || type == 14 || type == 15 || type == 16 || type == 18 || type == 19) ? false : true;
    }
}
