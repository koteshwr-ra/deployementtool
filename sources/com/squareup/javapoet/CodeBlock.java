package com.squareup.javapoet;

import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import javax.lang.model.element.Element;
import javax.lang.model.type.TypeMirror;

public final class CodeBlock {
    final List<Object> args;
    final List<String> formatParts;

    private CodeBlock(Builder builder) {
        this.formatParts = Util.immutableList(builder.formatParts);
        this.args = Util.immutableList(builder.args);
    }

    public boolean isEmpty() {
        return this.formatParts.isEmpty();
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
            new CodeWriter(stringWriter).emit(this);
            return stringWriter.toString();
        } catch (IOException unused) {
            throw new AssertionError();
        }
    }

    public static CodeBlock of(String str, Object... objArr) {
        return new Builder().add(str, objArr).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    public Builder toBuilder() {
        Builder builder = new Builder();
        builder.formatParts.addAll(this.formatParts);
        builder.args.addAll(this.args);
        return builder;
    }

    public static final class Builder {
        final List<Object> args;
        final List<String> formatParts;

        private Object argToLiteral(Object obj) {
            return obj;
        }

        private Builder() {
            this.formatParts = new ArrayList();
            this.args = new ArrayList();
        }

        public Builder add(String str, Object... objArr) {
            boolean z;
            int i;
            int i2;
            char charAt;
            boolean z2;
            int i3;
            String str2 = str;
            Object[] objArr2 = objArr;
            int[] iArr = new int[objArr2.length];
            int i4 = 0;
            boolean z3 = false;
            int i5 = 0;
            boolean z4 = false;
            while (i4 < str.length()) {
                if (str2.charAt(i4) != '$') {
                    int indexOf = str2.indexOf(36, i4 + 1);
                    if (indexOf == -1) {
                        indexOf = str.length();
                    }
                    this.formatParts.add(str2.substring(i4, indexOf));
                    i4 = indexOf;
                } else {
                    int i6 = i4 + 1;
                    int i7 = i6;
                    while (true) {
                        Util.checkArgument(i7 < str.length(), "dangling format characters in '%s'", str2);
                        i2 = i7 + 1;
                        charAt = str2.charAt(i7);
                        if (charAt < '0' || charAt > '9') {
                            int i8 = i2 - 1;
                        } else {
                            i7 = i2;
                        }
                    }
                    int i82 = i2 - 1;
                    if (charAt == '$' || charAt == '>' || charAt == '<' || charAt == '[' || charAt == ']') {
                        Util.checkArgument(i6 == i82, "$$, $>, $<, $[ and $] may not have an index", new Object[0]);
                        this.formatParts.add("$" + charAt);
                        i4 = i2;
                    } else {
                        if (i6 < i82) {
                            int parseInt = Integer.parseInt(str2.substring(i6, i82)) - 1;
                            int length = parseInt % objArr2.length;
                            iArr[length] = iArr[length] + 1;
                            z2 = true;
                            int i9 = parseInt;
                            i3 = i5;
                            i5 = i9;
                        } else {
                            z2 = z4;
                            i3 = i5 + 1;
                            z3 = true;
                        }
                        Util.checkArgument(i5 >= 0 && i5 < objArr2.length, "index %d for '%s' not in range (received %s arguments)", Integer.valueOf(i5 + 1), str2.substring(i6 - 1, i82 + 1), Integer.valueOf(objArr2.length));
                        Util.checkArgument(!z2 || !z3, "cannot mix indexed and positional parameters", new Object[0]);
                        if (charAt == 'L') {
                            this.args.add(argToLiteral(objArr2[i5]));
                        } else if (charAt == 'N') {
                            this.args.add(argToName(objArr2[i5]));
                        } else if (charAt == 'S') {
                            this.args.add(argToString(objArr2[i5]));
                        } else if (charAt == 'T') {
                            this.args.add(argToType(objArr2[i5]));
                        } else {
                            throw new IllegalArgumentException(String.format("invalid format string: '%s'", new Object[]{str2}));
                        }
                        this.formatParts.add("$" + charAt);
                        i5 = i3;
                        i4 = i2;
                        z4 = z2;
                    }
                }
            }
            if (z3) {
                if (i5 >= objArr2.length) {
                    i = 2;
                    z = true;
                } else {
                    i = 2;
                    z = false;
                }
                Object[] objArr3 = new Object[i];
                objArr3[0] = Integer.valueOf(i5);
                objArr3[1] = Integer.valueOf(objArr2.length);
                Util.checkArgument(z, "unused arguments: expected %s, received %s", objArr3);
            }
            if (z4) {
                ArrayList arrayList = new ArrayList();
                for (int i10 = 0; i10 < objArr2.length; i10++) {
                    if (iArr[i10] == 0) {
                        arrayList.add("$" + (i10 + 1));
                    }
                }
                Util.checkArgument(arrayList.isEmpty(), "unused argument%s: %s", arrayList.size() == 1 ? "" : "s", Util.join(", ", arrayList));
            }
            return this;
        }

        private String argToName(Object obj) {
            if (obj instanceof CharSequence) {
                return obj.toString();
            }
            if (obj instanceof ParameterSpec) {
                return ((ParameterSpec) obj).name;
            }
            if (obj instanceof FieldSpec) {
                return ((FieldSpec) obj).name;
            }
            if (obj instanceof MethodSpec) {
                return ((MethodSpec) obj).name;
            }
            if (obj instanceof TypeSpec) {
                return ((TypeSpec) obj).name;
            }
            throw new IllegalArgumentException("expected name but was " + obj);
        }

        private String argToString(Object obj) {
            if (obj != null) {
                return String.valueOf(obj);
            }
            return null;
        }

        private TypeName argToType(Object obj) {
            if (obj instanceof TypeName) {
                return (TypeName) obj;
            }
            if (obj instanceof TypeMirror) {
                return TypeName.get((TypeMirror) obj);
            }
            if (obj instanceof Element) {
                return TypeName.get(((Element) obj).asType());
            }
            if (obj instanceof Type) {
                return TypeName.get((Type) obj);
            }
            throw new IllegalArgumentException("expected type but was " + obj);
        }

        public Builder beginControlFlow(String str, Object... objArr) {
            add(str + " {\n", objArr);
            indent();
            return this;
        }

        public Builder nextControlFlow(String str, Object... objArr) {
            unindent();
            add("} " + str + " {\n", objArr);
            indent();
            return this;
        }

        public Builder endControlFlow() {
            unindent();
            add("}\n", new Object[0]);
            return this;
        }

        public Builder endControlFlow(String str, Object... objArr) {
            unindent();
            add("} " + str + ";\n", objArr);
            return this;
        }

        public Builder addStatement(String str, Object... objArr) {
            add("$[", new Object[0]);
            add(str, objArr);
            add(";\n$]", new Object[0]);
            return this;
        }

        public Builder add(CodeBlock codeBlock) {
            this.formatParts.addAll(codeBlock.formatParts);
            this.args.addAll(codeBlock.args);
            return this;
        }

        public Builder indent() {
            this.formatParts.add("$>");
            return this;
        }

        public Builder unindent() {
            this.formatParts.add("$<");
            return this;
        }

        public CodeBlock build() {
            return new CodeBlock(this);
        }
    }
}
