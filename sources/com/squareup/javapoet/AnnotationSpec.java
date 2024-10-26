package com.squareup.javapoet;

import java.io.IOException;
import java.io.StringWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.SimpleAnnotationValueVisitor7;
import org.apache.commons.lang3.StringUtils;

public final class AnnotationSpec {
    public final Map<String, List<CodeBlock>> members;
    public final TypeName type;

    private AnnotationSpec(Builder builder) {
        this.type = builder.type;
        this.members = Util.immutableMultimap(builder.members);
    }

    /* access modifiers changed from: package-private */
    public void emit(CodeWriter codeWriter, boolean z) throws IOException {
        String str = z ? "" : StringUtils.LF;
        String str2 = z ? ", " : ",\n";
        if (this.members.isEmpty()) {
            codeWriter.emit("@$T", this.type);
        } else if (this.members.size() != 1 || !this.members.containsKey("value")) {
            codeWriter.emit("@$T(" + str, this.type);
            codeWriter.indent(2);
            Iterator<Map.Entry<String, List<CodeBlock>>> it = this.members.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry next = it.next();
                codeWriter.emit("$L = ", next.getKey());
                emitAnnotationValues(codeWriter, str, str2, (List) next.getValue());
                if (it.hasNext()) {
                    codeWriter.emit(str2);
                }
            }
            codeWriter.unindent(2);
            codeWriter.emit(str + ")");
        } else {
            codeWriter.emit("@$T(", this.type);
            emitAnnotationValues(codeWriter, str, str2, this.members.get("value"));
            codeWriter.emit(")");
        }
    }

    private void emitAnnotationValues(CodeWriter codeWriter, String str, String str2, List<CodeBlock> list) throws IOException {
        boolean z = true;
        if (list.size() == 1) {
            codeWriter.indent(2);
            codeWriter.emit(list.get(0));
            codeWriter.unindent(2);
            return;
        }
        codeWriter.emit("{" + str);
        codeWriter.indent(2);
        for (CodeBlock next : list) {
            if (!z) {
                codeWriter.emit(str2);
            }
            codeWriter.emit(next);
            z = false;
        }
        codeWriter.unindent(2);
        codeWriter.emit(str + "}");
    }

    public static AnnotationSpec get(Annotation annotation) {
        return get(annotation, false);
    }

    public static AnnotationSpec get(Annotation annotation, boolean z) {
        Builder builder = builder((Class<?>) annotation.annotationType());
        try {
            Method[] declaredMethods = annotation.annotationType().getDeclaredMethods();
            Arrays.sort(declaredMethods, new Comparator<Method>() {
                public int compare(Method method, Method method2) {
                    return method.getName().compareTo(method2.getName());
                }
            });
            for (Method method : declaredMethods) {
                Object invoke = method.invoke(annotation, new Object[0]);
                if (z || !Objects.deepEquals(invoke, method.getDefaultValue())) {
                    if (invoke.getClass().isArray()) {
                        for (int i = 0; i < Array.getLength(invoke); i++) {
                            builder.addMemberForValue(method.getName(), Array.get(invoke, i));
                        }
                    } else if (invoke instanceof Annotation) {
                        builder.addMember(method.getName(), "$L", get((Annotation) invoke));
                    } else {
                        builder.addMemberForValue(method.getName(), invoke);
                    }
                }
            }
            return builder.build();
        } catch (Exception e) {
            throw new RuntimeException("Reflecting " + annotation + " failed!", e);
        }
    }

    public static AnnotationSpec get(AnnotationMirror annotationMirror) {
        Builder builder = builder(ClassName.get(annotationMirror.getAnnotationType().asElement()));
        Visitor visitor = new Visitor(builder);
        for (ExecutableElement executableElement : annotationMirror.getElementValues().keySet()) {
            ((AnnotationValue) annotationMirror.getElementValues().get(executableElement)).accept(visitor, executableElement.getSimpleName().toString());
        }
        return builder.build();
    }

    public static Builder builder(ClassName className) {
        Util.checkNotNull(className, "type == null", new Object[0]);
        return new Builder(className);
    }

    public static Builder builder(Class<?> cls) {
        return builder(ClassName.get(cls));
    }

    public Builder toBuilder() {
        Builder builder = new Builder(this.type);
        for (Map.Entry next : this.members.entrySet()) {
            builder.members.put(next.getKey(), new ArrayList((Collection) next.getValue()));
        }
        return builder;
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
            new CodeWriter(stringWriter).emit("$L", this);
            return stringWriter.toString();
        } catch (IOException unused) {
            throw new AssertionError();
        }
    }

    public static final class Builder {
        /* access modifiers changed from: private */
        public final Map<String, List<CodeBlock>> members;
        /* access modifiers changed from: private */
        public final TypeName type;

        private Builder(TypeName typeName) {
            this.members = new LinkedHashMap();
            this.type = typeName;
        }

        public Builder addMember(String str, String str2, Object... objArr) {
            return addMember(str, CodeBlock.of(str2, objArr));
        }

        public Builder addMember(String str, CodeBlock codeBlock) {
            List list = this.members.get(str);
            if (list == null) {
                list = new ArrayList();
                this.members.put(str, list);
            }
            list.add(codeBlock);
            return this;
        }

        /* access modifiers changed from: package-private */
        public Builder addMemberForValue(String str, Object obj) {
            Util.checkNotNull(str, "memberName == null", new Object[0]);
            Util.checkNotNull(obj, "value == null, constant non-null value expected for %s", str);
            if (obj instanceof Class) {
                return addMember(str, "$T.class", obj);
            } else if (obj instanceof Enum) {
                return addMember(str, "$T.$L", obj.getClass(), ((Enum) obj).name());
            } else if (obj instanceof String) {
                return addMember(str, "$S", obj);
            } else if (obj instanceof Float) {
                return addMember(str, "$Lf", obj);
            } else if (obj instanceof Character) {
                return addMember(str, "'$L'", Util.characterLiteralWithoutSingleQuotes(((Character) obj).charValue()));
            } else {
                return addMember(str, "$L", obj);
            }
        }

        public AnnotationSpec build() {
            return new AnnotationSpec(this);
        }
    }

    private static class Visitor extends SimpleAnnotationValueVisitor7<Builder, String> {
        final Builder builder;

        Visitor(Builder builder2) {
            super(builder2);
            this.builder = builder2;
        }

        /* access modifiers changed from: protected */
        public Builder defaultAction(Object obj, String str) {
            return this.builder.addMemberForValue(str, obj);
        }

        public Builder visitAnnotation(AnnotationMirror annotationMirror, String str) {
            return this.builder.addMember(str, "$L", AnnotationSpec.get(annotationMirror));
        }

        public Builder visitEnumConstant(VariableElement variableElement, String str) {
            return this.builder.addMember(str, "$T.$L", variableElement.asType(), variableElement.getSimpleName());
        }

        public Builder visitType(TypeMirror typeMirror, String str) {
            return this.builder.addMember(str, "$T.class", typeMirror);
        }

        public Builder visitArray(List<? extends AnnotationValue> list, String str) {
            for (AnnotationValue accept : list) {
                accept.accept(this, str);
            }
            return this.builder;
        }
    }
}
