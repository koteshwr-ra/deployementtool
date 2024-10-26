package com.google.auto.common;

import java.util.List;
import java.util.Map;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.AnnotationValueVisitor;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementVisitor;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.TypeParameterElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.ArrayType;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.ErrorType;
import javax.lang.model.type.ExecutableType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.type.TypeVisitor;
import javax.lang.model.type.WildcardType;
import javax.lang.model.util.AbstractElementVisitor6;
import javax.lang.model.util.SimpleAnnotationValueVisitor6;
import javax.lang.model.util.SimpleTypeVisitor6;

public final class SuperficialValidation {
    private static final ElementVisitor<Boolean, Void> ELEMENT_VALIDATING_VISITOR = new AbstractElementVisitor6<Boolean, Void>() {
        public Boolean visitPackage(PackageElement packageElement, Void voidR) {
            return Boolean.valueOf(SuperficialValidation.validateElements(packageElement.getEnclosedElements()));
        }

        public Boolean visitType(TypeElement typeElement, Void voidR) {
            return Boolean.valueOf(SuperficialValidation.isValidBaseElement(typeElement) && SuperficialValidation.validateElements(typeElement.getTypeParameters()) && SuperficialValidation.validateTypes(typeElement.getInterfaces()) && SuperficialValidation.validateType(typeElement.getSuperclass()));
        }

        public Boolean visitVariable(VariableElement variableElement, Void voidR) {
            return Boolean.valueOf(SuperficialValidation.isValidBaseElement(variableElement));
        }

        public Boolean visitExecutable(ExecutableElement executableElement, Void voidR) {
            AnnotationValue defaultValue = executableElement.getDefaultValue();
            return Boolean.valueOf(SuperficialValidation.isValidBaseElement(executableElement) && (defaultValue == null || SuperficialValidation.validateAnnotationValue(defaultValue, executableElement.getReturnType())) && SuperficialValidation.validateType(executableElement.getReturnType()) && SuperficialValidation.validateTypes(executableElement.getThrownTypes()) && SuperficialValidation.validateElements(executableElement.getTypeParameters()) && SuperficialValidation.validateElements(executableElement.getParameters()));
        }

        public Boolean visitTypeParameter(TypeParameterElement typeParameterElement, Void voidR) {
            return Boolean.valueOf(SuperficialValidation.isValidBaseElement(typeParameterElement) && SuperficialValidation.validateTypes(typeParameterElement.getBounds()));
        }

        public Boolean visitUnknown(Element element, Void voidR) {
            return true;
        }
    };
    private static final TypeVisitor<Boolean, Void> TYPE_VALIDATING_VISITOR = new SimpleTypeVisitor6<Boolean, Void>() {
        /* access modifiers changed from: protected */
        public Boolean defaultAction(TypeMirror typeMirror, Void voidR) {
            return true;
        }

        public Boolean visitArray(ArrayType arrayType, Void voidR) {
            return Boolean.valueOf(SuperficialValidation.validateType(arrayType.getComponentType()));
        }

        public Boolean visitDeclared(DeclaredType declaredType, Void voidR) {
            return Boolean.valueOf(SuperficialValidation.validateTypes(declaredType.getTypeArguments()));
        }

        public Boolean visitError(ErrorType errorType, Void voidR) {
            return false;
        }

        public Boolean visitUnknown(TypeMirror typeMirror, Void voidR) {
            return defaultAction(typeMirror, voidR);
        }

        public Boolean visitWildcard(WildcardType wildcardType, Void voidR) {
            TypeMirror extendsBound = wildcardType.getExtendsBound();
            TypeMirror superBound = wildcardType.getSuperBound();
            return Boolean.valueOf((extendsBound == null || SuperficialValidation.validateType(extendsBound)) && (superBound == null || SuperficialValidation.validateType(superBound)));
        }

        public Boolean visitExecutable(ExecutableType executableType, Void voidR) {
            return Boolean.valueOf(SuperficialValidation.validateTypes(executableType.getParameterTypes()) && SuperficialValidation.validateType(executableType.getReturnType()) && SuperficialValidation.validateTypes(executableType.getThrownTypes()) && SuperficialValidation.validateTypes(executableType.getTypeVariables()));
        }
    };
    private static final AnnotationValueVisitor<Boolean, TypeMirror> VALUE_VALIDATING_VISITOR = new SimpleAnnotationValueVisitor6<Boolean, TypeMirror>() {
        /* access modifiers changed from: protected */
        public Boolean defaultAction(Object obj, TypeMirror typeMirror) {
            return Boolean.valueOf(MoreTypes.isTypeOf(obj.getClass(), typeMirror));
        }

        public Boolean visitUnknown(AnnotationValue annotationValue, TypeMirror typeMirror) {
            return defaultAction((Object) annotationValue, typeMirror);
        }

        public Boolean visitAnnotation(AnnotationMirror annotationMirror, TypeMirror typeMirror) {
            return Boolean.valueOf(MoreTypes.equivalence().equivalent(annotationMirror.getAnnotationType(), typeMirror) && SuperficialValidation.validateAnnotation(annotationMirror));
        }

        public Boolean visitArray(List<? extends AnnotationValue> list, TypeMirror typeMirror) {
            if (!typeMirror.getKind().equals(TypeKind.ARRAY)) {
                return false;
            }
            try {
                TypeMirror componentType = MoreTypes.asArray(typeMirror).getComponentType();
                for (AnnotationValue accept : list) {
                    if (!((Boolean) accept.accept(this, componentType)).booleanValue()) {
                        return false;
                    }
                }
                return true;
            } catch (IllegalArgumentException unused) {
                return false;
            }
        }

        public Boolean visitEnumConstant(VariableElement variableElement, TypeMirror typeMirror) {
            return Boolean.valueOf(MoreTypes.equivalence().equivalent(variableElement.asType(), typeMirror) && SuperficialValidation.validateElement(variableElement));
        }

        public Boolean visitType(TypeMirror typeMirror, TypeMirror typeMirror2) {
            return Boolean.valueOf(SuperficialValidation.validateType(typeMirror));
        }

        public Boolean visitBoolean(boolean z, TypeMirror typeMirror) {
            return Boolean.valueOf(MoreTypes.isTypeOf(Boolean.TYPE, typeMirror));
        }

        public Boolean visitByte(byte b, TypeMirror typeMirror) {
            return Boolean.valueOf(MoreTypes.isTypeOf(Byte.TYPE, typeMirror));
        }

        public Boolean visitChar(char c, TypeMirror typeMirror) {
            return Boolean.valueOf(MoreTypes.isTypeOf(Character.TYPE, typeMirror));
        }

        public Boolean visitDouble(double d, TypeMirror typeMirror) {
            return Boolean.valueOf(MoreTypes.isTypeOf(Double.TYPE, typeMirror));
        }

        public Boolean visitFloat(float f, TypeMirror typeMirror) {
            return Boolean.valueOf(MoreTypes.isTypeOf(Float.TYPE, typeMirror));
        }

        public Boolean visitInt(int i, TypeMirror typeMirror) {
            return Boolean.valueOf(MoreTypes.isTypeOf(Integer.TYPE, typeMirror));
        }

        public Boolean visitLong(long j, TypeMirror typeMirror) {
            return Boolean.valueOf(MoreTypes.isTypeOf(Long.TYPE, typeMirror));
        }

        public Boolean visitShort(short s, TypeMirror typeMirror) {
            return Boolean.valueOf(MoreTypes.isTypeOf(Short.TYPE, typeMirror));
        }
    };

    private static boolean validateAnnotationValues(Map<? extends ExecutableElement, ? extends AnnotationValue> map) {
        return true;
    }

    public static boolean validateElements(Iterable<? extends Element> iterable) {
        for (Element validateElement : iterable) {
            if (!validateElement(validateElement)) {
                return false;
            }
        }
        return true;
    }

    public static boolean validateElement(Element element) {
        return ((Boolean) element.accept(ELEMENT_VALIDATING_VISITOR, (Object) null)).booleanValue();
    }

    /* access modifiers changed from: private */
    public static boolean isValidBaseElement(Element element) {
        return validateType(element.asType()) && validateAnnotations(element.getAnnotationMirrors()) && validateElements(element.getEnclosedElements());
    }

    /* access modifiers changed from: private */
    public static boolean validateTypes(Iterable<? extends TypeMirror> iterable) {
        for (TypeMirror validateType : iterable) {
            if (!validateType(validateType)) {
                return false;
            }
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static boolean validateType(TypeMirror typeMirror) {
        return ((Boolean) typeMirror.accept(TYPE_VALIDATING_VISITOR, (Object) null)).booleanValue();
    }

    private static boolean validateAnnotations(Iterable<? extends AnnotationMirror> iterable) {
        for (AnnotationMirror validateAnnotation : iterable) {
            if (!validateAnnotation(validateAnnotation)) {
                return false;
            }
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static boolean validateAnnotation(AnnotationMirror annotationMirror) {
        return validateType(annotationMirror.getAnnotationType()) && validateAnnotationValues(annotationMirror.getElementValues());
    }

    /* access modifiers changed from: private */
    public static boolean validateAnnotationValue(AnnotationValue annotationValue, TypeMirror typeMirror) {
        return ((Boolean) annotationValue.accept(VALUE_VALIDATING_VISITOR, typeMirror)).booleanValue();
    }
}
