package com.squareup.javapoet;

import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.ParameterSpec;
import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeParameterElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.ExecutableType;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Types;
import org.apache.commons.lang3.StringUtils;

public final class MethodSpec {
    static final String CONSTRUCTOR = "<init>";
    static final ClassName OVERRIDE = ClassName.get((Class<?>) Override.class);
    public final List<AnnotationSpec> annotations;
    public final CodeBlock code;
    public final CodeBlock defaultValue;
    public final List<TypeName> exceptions;
    public final CodeBlock javadoc;
    public final Set<Modifier> modifiers;
    public final String name;
    public final List<ParameterSpec> parameters;
    public final TypeName returnType;
    public final List<TypeVariableName> typeVariables;
    public final boolean varargs;

    private MethodSpec(Builder builder) {
        CodeBlock build = builder.code.build();
        Util.checkArgument(build.isEmpty() || !builder.modifiers.contains(Modifier.ABSTRACT), "abstract method %s cannot have code", builder.name);
        Util.checkArgument(!builder.varargs || lastParameterIsArray(builder.parameters), "last parameter of varargs method %s must be an array", builder.name);
        this.name = (String) Util.checkNotNull(builder.name, "name == null", new Object[0]);
        this.javadoc = builder.javadoc.build();
        this.annotations = Util.immutableList(builder.annotations);
        this.modifiers = Util.immutableSet(builder.modifiers);
        this.typeVariables = Util.immutableList(builder.typeVariables);
        this.returnType = builder.returnType;
        this.parameters = Util.immutableList(builder.parameters);
        this.varargs = builder.varargs;
        this.exceptions = Util.immutableList(builder.exceptions);
        this.defaultValue = builder.defaultValue;
        this.code = build;
    }

    private boolean lastParameterIsArray(List<ParameterSpec> list) {
        if (list.isEmpty() || TypeName.arrayComponent(list.get(list.size() - 1).type) == null) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public void emit(CodeWriter codeWriter, String str, Set<Modifier> set) throws IOException {
        codeWriter.emitJavadoc(this.javadoc);
        codeWriter.emitAnnotations(this.annotations, false);
        codeWriter.emitModifiers(this.modifiers, set);
        if (!this.typeVariables.isEmpty()) {
            codeWriter.emitTypeVariables(this.typeVariables);
            codeWriter.emit(StringUtils.SPACE);
        }
        if (isConstructor()) {
            codeWriter.emit("$L(", str);
        } else {
            codeWriter.emit("$T $L(", this.returnType, this.name);
        }
        Iterator<ParameterSpec> it = this.parameters.iterator();
        boolean z = true;
        while (it.hasNext()) {
            ParameterSpec next = it.next();
            if (!z) {
                codeWriter.emit(", ");
            }
            next.emit(codeWriter, !it.hasNext() && this.varargs);
            z = false;
        }
        codeWriter.emit(")");
        CodeBlock codeBlock = this.defaultValue;
        if (codeBlock != null && !codeBlock.isEmpty()) {
            codeWriter.emit(" default ");
            codeWriter.emit(this.defaultValue);
        }
        if (!this.exceptions.isEmpty()) {
            codeWriter.emit(" throws");
            boolean z2 = true;
            for (TypeName next2 : this.exceptions) {
                if (!z2) {
                    codeWriter.emit(",");
                }
                codeWriter.emit(" $T", next2);
                z2 = false;
            }
        }
        if (hasModifier(Modifier.ABSTRACT)) {
            codeWriter.emit(";\n");
        } else if (hasModifier(Modifier.NATIVE)) {
            codeWriter.emit(this.code);
            codeWriter.emit(";\n");
        } else {
            codeWriter.emit(" {\n");
            codeWriter.indent();
            codeWriter.emit(this.code);
            codeWriter.unindent();
            codeWriter.emit("}\n");
        }
    }

    public boolean hasModifier(Modifier modifier) {
        return this.modifiers.contains(modifier);
    }

    public boolean isConstructor() {
        return this.name.equals(CONSTRUCTOR);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            return toString().equals(obj.toString());
        }
        return false;
    }

    public int hashCode() {
        return toString().hashCode();
    }

    public String toString() {
        StringWriter stringWriter = new StringWriter();
        try {
            emit(new CodeWriter(stringWriter), "Constructor", Collections.emptySet());
            return stringWriter.toString();
        } catch (IOException unused) {
            throw new AssertionError();
        }
    }

    public static Builder methodBuilder(String str) {
        return new Builder(str);
    }

    public static Builder constructorBuilder() {
        return new Builder(CONSTRUCTOR);
    }

    public static Builder overriding(ExecutableElement executableElement) {
        Util.checkNotNull(executableElement, "method == null", new Object[0]);
        Set modifiers2 = executableElement.getModifiers();
        if (modifiers2.contains(Modifier.PRIVATE) || modifiers2.contains(Modifier.FINAL) || modifiers2.contains(Modifier.STATIC)) {
            throw new IllegalArgumentException("cannot override method with modifiers: " + modifiers2);
        }
        Builder methodBuilder = methodBuilder(executableElement.getSimpleName().toString());
        methodBuilder.addAnnotation(OVERRIDE);
        for (AnnotationMirror annotationMirror : executableElement.getAnnotationMirrors()) {
            AnnotationSpec annotationSpec = AnnotationSpec.get(annotationMirror);
            if (!annotationSpec.type.equals(OVERRIDE)) {
                methodBuilder.addAnnotation(annotationSpec);
            }
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet(modifiers2);
        linkedHashSet.remove(Modifier.ABSTRACT);
        methodBuilder.addModifiers((Iterable<Modifier>) linkedHashSet);
        for (TypeParameterElement asType : executableElement.getTypeParameters()) {
            methodBuilder.addTypeVariable(TypeVariableName.get(asType.asType()));
        }
        methodBuilder.returns(TypeName.get(executableElement.getReturnType()));
        for (VariableElement variableElement : executableElement.getParameters()) {
            TypeName typeName = TypeName.get(variableElement.asType());
            String obj = variableElement.getSimpleName().toString();
            Set modifiers3 = variableElement.getModifiers();
            ParameterSpec.Builder addModifiers = ParameterSpec.builder(typeName, obj, new Modifier[0]).addModifiers((Modifier[]) modifiers3.toArray(new Modifier[modifiers3.size()]));
            for (AnnotationMirror annotationMirror2 : variableElement.getAnnotationMirrors()) {
                addModifiers.addAnnotation(AnnotationSpec.get(annotationMirror2));
            }
            methodBuilder.addParameter(addModifiers.build());
        }
        methodBuilder.varargs(executableElement.isVarArgs());
        for (TypeMirror typeMirror : executableElement.getThrownTypes()) {
            methodBuilder.addException(TypeName.get(typeMirror));
        }
        return methodBuilder;
    }

    public static Builder overriding(ExecutableElement executableElement, DeclaredType declaredType, Types types) {
        ExecutableType asMemberOf = types.asMemberOf(declaredType, executableElement);
        List parameterTypes = asMemberOf.getParameterTypes();
        TypeMirror returnType2 = asMemberOf.getReturnType();
        Builder overriding = overriding(executableElement);
        overriding.returns(TypeName.get(returnType2));
        int size = overriding.parameters.size();
        for (int i = 0; i < size; i++) {
            ParameterSpec parameterSpec = (ParameterSpec) overriding.parameters.get(i);
            overriding.parameters.set(i, parameterSpec.toBuilder(TypeName.get((TypeMirror) parameterTypes.get(i)), parameterSpec.name).build());
        }
        return overriding;
    }

    public Builder toBuilder() {
        Builder builder = new Builder(this.name);
        builder.javadoc.add(this.javadoc);
        builder.annotations.addAll(this.annotations);
        builder.modifiers.addAll(this.modifiers);
        builder.typeVariables.addAll(this.typeVariables);
        TypeName unused = builder.returnType = this.returnType;
        builder.parameters.addAll(this.parameters);
        builder.exceptions.addAll(this.exceptions);
        builder.code.add(this.code);
        boolean unused2 = builder.varargs = this.varargs;
        CodeBlock unused3 = builder.defaultValue = this.defaultValue;
        return builder;
    }

    public static final class Builder {
        /* access modifiers changed from: private */
        public final List<AnnotationSpec> annotations;
        /* access modifiers changed from: private */
        public final CodeBlock.Builder code;
        /* access modifiers changed from: private */
        public CodeBlock defaultValue;
        /* access modifiers changed from: private */
        public final Set<TypeName> exceptions;
        /* access modifiers changed from: private */
        public final CodeBlock.Builder javadoc;
        /* access modifiers changed from: private */
        public final List<Modifier> modifiers;
        /* access modifiers changed from: private */
        public final String name;
        /* access modifiers changed from: private */
        public final List<ParameterSpec> parameters;
        /* access modifiers changed from: private */
        public TypeName returnType;
        /* access modifiers changed from: private */
        public List<TypeVariableName> typeVariables;
        /* access modifiers changed from: private */
        public boolean varargs;

        private Builder(String str) {
            this.javadoc = CodeBlock.builder();
            this.annotations = new ArrayList();
            this.modifiers = new ArrayList();
            this.typeVariables = new ArrayList();
            this.parameters = new ArrayList();
            this.exceptions = new LinkedHashSet();
            this.code = CodeBlock.builder();
            Util.checkArgument(str.equals(MethodSpec.CONSTRUCTOR) || SourceVersion.isName(str), "not a valid name: %s", str);
            this.name = str;
            this.returnType = str.equals(MethodSpec.CONSTRUCTOR) ? null : TypeName.VOID;
        }

        public Builder addJavadoc(String str, Object... objArr) {
            this.javadoc.add(str, objArr);
            return this;
        }

        public Builder addAnnotations(Iterable<AnnotationSpec> iterable) {
            Util.checkArgument(iterable != null, "annotationSpecs == null", new Object[0]);
            for (AnnotationSpec add : iterable) {
                this.annotations.add(add);
            }
            return this;
        }

        public Builder addAnnotation(AnnotationSpec annotationSpec) {
            this.annotations.add(annotationSpec);
            return this;
        }

        public Builder addAnnotation(ClassName className) {
            this.annotations.add(AnnotationSpec.builder(className).build());
            return this;
        }

        public Builder addAnnotation(Class<?> cls) {
            return addAnnotation(ClassName.get(cls));
        }

        public Builder addModifiers(Modifier... modifierArr) {
            Collections.addAll(this.modifiers, modifierArr);
            return this;
        }

        public Builder addModifiers(Iterable<Modifier> iterable) {
            Util.checkNotNull(iterable, "modifiers == null", new Object[0]);
            for (Modifier add : iterable) {
                this.modifiers.add(add);
            }
            return this;
        }

        public Builder addTypeVariables(Iterable<TypeVariableName> iterable) {
            Util.checkArgument(iterable != null, "typeVariables == null", new Object[0]);
            for (TypeVariableName add : iterable) {
                this.typeVariables.add(add);
            }
            return this;
        }

        public Builder addTypeVariable(TypeVariableName typeVariableName) {
            this.typeVariables.add(typeVariableName);
            return this;
        }

        public Builder returns(TypeName typeName) {
            Util.checkState(!this.name.equals(MethodSpec.CONSTRUCTOR), "constructor cannot have return type.", new Object[0]);
            this.returnType = typeName;
            return this;
        }

        public Builder returns(Type type) {
            return returns(TypeName.get(type));
        }

        public Builder addParameters(Iterable<ParameterSpec> iterable) {
            Util.checkArgument(iterable != null, "parameterSpecs == null", new Object[0]);
            for (ParameterSpec add : iterable) {
                this.parameters.add(add);
            }
            return this;
        }

        public Builder addParameter(ParameterSpec parameterSpec) {
            this.parameters.add(parameterSpec);
            return this;
        }

        public Builder addParameter(TypeName typeName, String str, Modifier... modifierArr) {
            return addParameter(ParameterSpec.builder(typeName, str, modifierArr).build());
        }

        public Builder addParameter(Type type, String str, Modifier... modifierArr) {
            return addParameter(TypeName.get(type), str, modifierArr);
        }

        public Builder varargs() {
            return varargs(true);
        }

        public Builder varargs(boolean z) {
            this.varargs = z;
            return this;
        }

        public Builder addExceptions(Iterable<? extends TypeName> iterable) {
            Util.checkArgument(iterable != null, "exceptions == null", new Object[0]);
            for (TypeName add : iterable) {
                this.exceptions.add(add);
            }
            return this;
        }

        public Builder addException(TypeName typeName) {
            this.exceptions.add(typeName);
            return this;
        }

        public Builder addException(Type type) {
            return addException(TypeName.get(type));
        }

        public Builder addCode(String str, Object... objArr) {
            this.code.add(str, objArr);
            return this;
        }

        public Builder addCode(CodeBlock codeBlock) {
            this.code.add(codeBlock);
            return this;
        }

        public Builder defaultValue(String str, Object... objArr) {
            return defaultValue(CodeBlock.of(str, objArr));
        }

        public Builder defaultValue(CodeBlock codeBlock) {
            Util.checkState(this.defaultValue == null, "defaultValue was already set", new Object[0]);
            this.defaultValue = (CodeBlock) Util.checkNotNull(codeBlock, "codeBlock == null", new Object[0]);
            return this;
        }

        public Builder beginControlFlow(String str, Object... objArr) {
            this.code.beginControlFlow(str, objArr);
            return this;
        }

        public Builder nextControlFlow(String str, Object... objArr) {
            this.code.nextControlFlow(str, objArr);
            return this;
        }

        public Builder endControlFlow() {
            this.code.endControlFlow();
            return this;
        }

        public Builder endControlFlow(String str, Object... objArr) {
            this.code.endControlFlow(str, objArr);
            return this;
        }

        public Builder addStatement(String str, Object... objArr) {
            this.code.addStatement(str, objArr);
            return this;
        }

        public MethodSpec build() {
            return new MethodSpec(this);
        }
    }
}
