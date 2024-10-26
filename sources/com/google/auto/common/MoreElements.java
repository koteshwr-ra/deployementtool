package com.google.auto.common;

import com.google.common.base.Optional;
import java.lang.annotation.Annotation;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ElementVisitor;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.SimpleElementVisitor6;

public final class MoreElements {
    private static final ElementVisitor<ExecutableElement, Void> EXECUTABLE_ELEMENT_VISITOR = new SimpleElementVisitor6<ExecutableElement, Void>() {
        public ExecutableElement visitExecutable(ExecutableElement executableElement, Void voidR) {
            return executableElement;
        }

        /* access modifiers changed from: protected */
        public ExecutableElement defaultAction(Element element, Void voidR) {
            throw new IllegalArgumentException();
        }
    };
    private static final ElementVisitor<PackageElement, Void> PACKAGE_ELEMENT_VISITOR = new SimpleElementVisitor6<PackageElement, Void>() {
        public PackageElement visitPackage(PackageElement packageElement, Void voidR) {
            return packageElement;
        }

        /* access modifiers changed from: protected */
        public PackageElement defaultAction(Element element, Void voidR) {
            throw new IllegalArgumentException();
        }
    };
    private static final ElementVisitor<TypeElement, Void> TYPE_ELEMENT_VISITOR = new SimpleElementVisitor6<TypeElement, Void>() {
        public TypeElement visitType(TypeElement typeElement, Void voidR) {
            return typeElement;
        }

        /* access modifiers changed from: protected */
        public TypeElement defaultAction(Element element, Void voidR) {
            throw new IllegalArgumentException();
        }
    };
    private static final ElementVisitor<VariableElement, Void> VARIABLE_ELEMENT_VISITOR = new SimpleElementVisitor6<VariableElement, Void>() {
        public VariableElement visitVariable(VariableElement variableElement, Void voidR) {
            return variableElement;
        }

        /* access modifiers changed from: protected */
        public VariableElement defaultAction(Element element, Void voidR) {
            throw new IllegalArgumentException();
        }
    };

    public static PackageElement getPackage(Element element) {
        while (element.getKind() != ElementKind.PACKAGE) {
            element = element.getEnclosingElement();
        }
        return (PackageElement) element;
    }

    public static PackageElement asPackage(Element element) {
        return (PackageElement) element.accept(PACKAGE_ELEMENT_VISITOR, (Object) null);
    }

    public static TypeElement asType(Element element) {
        return (TypeElement) element.accept(TYPE_ELEMENT_VISITOR, (Object) null);
    }

    public static VariableElement asVariable(Element element) {
        return (VariableElement) element.accept(VARIABLE_ELEMENT_VISITOR, (Object) null);
    }

    public static ExecutableElement asExecutable(Element element) {
        return (ExecutableElement) element.accept(EXECUTABLE_ELEMENT_VISITOR, (Object) null);
    }

    public static boolean isAnnotationPresent(Element element, Class<? extends Annotation> cls) {
        return getAnnotationMirror(element, cls).isPresent();
    }

    public static Optional<AnnotationMirror> getAnnotationMirror(Element element, Class<? extends Annotation> cls) {
        String canonicalName = cls.getCanonicalName();
        for (AnnotationMirror annotationMirror : element.getAnnotationMirrors()) {
            if (asType(annotationMirror.getAnnotationType().asElement()).getQualifiedName().contentEquals(canonicalName)) {
                return Optional.of(annotationMirror);
            }
        }
        return Optional.absent();
    }

    private MoreElements() {
    }
}
