package com.alibaba.android.arouter.compiler.utils;

import com.alibaba.android.arouter.facade.enums.TypeKind;
import javax.lang.model.element.Element;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

public class TypeUtils {
    private Elements elements;
    private TypeMirror parcelableType;
    private TypeMirror serializableType = this.elements.getTypeElement(Consts.SERIALIZABLE).asType();
    private Types types;

    public TypeUtils(Types types2, Elements elements2) {
        this.types = types2;
        this.elements = elements2;
        this.parcelableType = elements2.getTypeElement(Consts.PARCELABLE).asType();
    }

    public int typeExchange(Element element) {
        TypeMirror asType = element.asType();
        if (asType.getKind().isPrimitive()) {
            return element.asType().getKind().ordinal();
        }
        String typeMirror = asType.toString();
        char c = 65535;
        switch (typeMirror.hashCode()) {
            case -2056817302:
                if (typeMirror.equals(Consts.INTEGER)) {
                    c = 2;
                    break;
                }
                break;
            case -527879800:
                if (typeMirror.equals(Consts.FLOAT)) {
                    c = 4;
                    break;
                }
                break;
            case -515992664:
                if (typeMirror.equals(Consts.SHORT)) {
                    c = 1;
                    break;
                }
                break;
            case 155276373:
                if (typeMirror.equals(Consts.CHAR)) {
                    c = 7;
                    break;
                }
                break;
            case 344809556:
                if (typeMirror.equals(Consts.BOOLEAN)) {
                    c = 6;
                    break;
                }
                break;
            case 398507100:
                if (typeMirror.equals(Consts.BYTE)) {
                    c = 0;
                    break;
                }
                break;
            case 398795216:
                if (typeMirror.equals(Consts.LONG)) {
                    c = 3;
                    break;
                }
                break;
            case 761287205:
                if (typeMirror.equals(Consts.DOUBEL)) {
                    c = 5;
                    break;
                }
                break;
            case 1195259493:
                if (typeMirror.equals(Consts.STRING)) {
                    c = 8;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return TypeKind.BYTE.ordinal();
            case 1:
                return TypeKind.SHORT.ordinal();
            case 2:
                return TypeKind.INT.ordinal();
            case 3:
                return TypeKind.LONG.ordinal();
            case 4:
                return TypeKind.FLOAT.ordinal();
            case 5:
                return TypeKind.DOUBLE.ordinal();
            case 6:
                return TypeKind.BOOLEAN.ordinal();
            case 7:
                return TypeKind.CHAR.ordinal();
            case 8:
                return TypeKind.STRING.ordinal();
            default:
                if (this.types.isSubtype(asType, this.parcelableType)) {
                    return TypeKind.PARCELABLE.ordinal();
                }
                if (this.types.isSubtype(asType, this.serializableType)) {
                    return TypeKind.SERIALIZABLE.ordinal();
                }
                return TypeKind.OBJECT.ordinal();
        }
    }
}
