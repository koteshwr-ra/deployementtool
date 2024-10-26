package com.squareup.javapoet;

import com.alibaba.android.arouter.utils.Consts;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Modifier;
import org.apache.commons.lang3.StringUtils;

final class CodeWriter {
    private static final String NO_PACKAGE = new String();
    private boolean comment;
    private final Map<String, ClassName> importableTypes;
    private final Map<String, ClassName> importedTypes;
    private final String indent;
    private int indentLevel;
    private boolean javadoc;
    private final Appendable out;
    private String packageName;
    private final Set<String> referencedNames;
    int statementLine;
    private final Set<String> staticImportClassNames;
    private final Set<String> staticImports;
    private boolean trailingNewline;
    private final List<TypeSpec> typeSpecStack;

    CodeWriter(Appendable appendable) {
        this(appendable, "  ", Collections.emptySet());
    }

    CodeWriter(Appendable appendable, String str, Set<String> set) {
        this(appendable, str, Collections.emptyMap(), set);
    }

    CodeWriter(Appendable appendable, String str, Map<String, ClassName> map, Set<String> set) {
        this.javadoc = false;
        this.comment = false;
        this.packageName = NO_PACKAGE;
        this.typeSpecStack = new ArrayList();
        this.importableTypes = new LinkedHashMap();
        this.referencedNames = new LinkedHashSet();
        this.statementLine = -1;
        this.out = (Appendable) Util.checkNotNull(appendable, "out == null", new Object[0]);
        this.indent = (String) Util.checkNotNull(str, "indent == null", new Object[0]);
        this.importedTypes = (Map) Util.checkNotNull(map, "importedTypes == null", new Object[0]);
        this.staticImports = (Set) Util.checkNotNull(set, "staticImports == null", new Object[0]);
        this.staticImportClassNames = new LinkedHashSet();
        for (String next : set) {
            this.staticImportClassNames.add(next.substring(0, next.lastIndexOf(46)));
        }
    }

    public Map<String, ClassName> importedTypes() {
        return this.importedTypes;
    }

    public CodeWriter indent() {
        return indent(1);
    }

    public CodeWriter indent(int i) {
        this.indentLevel += i;
        return this;
    }

    public CodeWriter unindent() {
        return unindent(1);
    }

    public CodeWriter unindent(int i) {
        Util.checkArgument(this.indentLevel - i >= 0, "cannot unindent %s from %s", Integer.valueOf(i), Integer.valueOf(this.indentLevel));
        this.indentLevel -= i;
        return this;
    }

    public CodeWriter pushPackage(String str) {
        Util.checkState(this.packageName == NO_PACKAGE, "package already set: %s", this.packageName);
        this.packageName = (String) Util.checkNotNull(str, "packageName == null", new Object[0]);
        return this;
    }

    public CodeWriter popPackage() {
        Util.checkState(this.packageName != NO_PACKAGE, "package already set: %s", this.packageName);
        this.packageName = NO_PACKAGE;
        return this;
    }

    public CodeWriter pushType(TypeSpec typeSpec) {
        this.typeSpecStack.add(typeSpec);
        return this;
    }

    public CodeWriter popType() {
        List<TypeSpec> list = this.typeSpecStack;
        list.remove(list.size() - 1);
        return this;
    }

    public void emitComment(CodeBlock codeBlock) throws IOException {
        this.trailingNewline = true;
        this.comment = true;
        try {
            emit(codeBlock);
            emit(StringUtils.LF);
        } finally {
            this.comment = false;
        }
    }

    /* JADX INFO: finally extract failed */
    public void emitJavadoc(CodeBlock codeBlock) throws IOException {
        if (!codeBlock.isEmpty()) {
            emit("/**\n");
            this.javadoc = true;
            try {
                emit(codeBlock);
                this.javadoc = false;
                emit(" */\n");
            } catch (Throwable th) {
                this.javadoc = false;
                throw th;
            }
        }
    }

    public void emitAnnotations(List<AnnotationSpec> list, boolean z) throws IOException {
        for (AnnotationSpec emit : list) {
            emit.emit(this, z);
            emit(z ? StringUtils.SPACE : StringUtils.LF);
        }
    }

    public void emitModifiers(Set<Modifier> set, Set<Modifier> set2) throws IOException {
        if (!set.isEmpty()) {
            Iterator it = EnumSet.copyOf(set).iterator();
            while (it.hasNext()) {
                Modifier modifier = (Modifier) it.next();
                if (!set2.contains(modifier)) {
                    emitAndIndent(modifier.name().toLowerCase(Locale.US));
                    emitAndIndent(StringUtils.SPACE);
                }
            }
        }
    }

    public void emitModifiers(Set<Modifier> set) throws IOException {
        emitModifiers(set, Collections.emptySet());
    }

    public void emitTypeVariables(List<TypeVariableName> list) throws IOException {
        if (!list.isEmpty()) {
            emit("<");
            boolean z = true;
            for (TypeVariableName next : list) {
                if (!z) {
                    emit(", ");
                }
                emit("$L", next.name);
                boolean z2 = true;
                for (TypeName typeName : next.bounds) {
                    emit(z2 ? " extends $T" : " & $T", typeName);
                    z2 = false;
                }
                z = false;
            }
            emit(">");
        }
    }

    public CodeWriter emit(String str) throws IOException {
        return emitAndIndent(str);
    }

    public CodeWriter emit(String str, Object... objArr) throws IOException {
        return emit(CodeBlock.of(str, objArr));
    }

    /* JADX WARNING: Removed duplicated region for block: B:51:0x00a3  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x00b8  */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x00d0  */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x00e1  */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x00e6  */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x00eb  */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x00f0  */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x0141  */
    /* JADX WARNING: Removed duplicated region for block: B:91:0x015a  */
    /* JADX WARNING: Removed duplicated region for block: B:92:0x0168  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.squareup.javapoet.CodeWriter emit(com.squareup.javapoet.CodeBlock r12) throws java.io.IOException {
        /*
            r11 = this;
            java.util.List<java.lang.String> r0 = r12.formatParts
            java.util.ListIterator r0 = r0.listIterator()
            r1 = 0
            r2 = 0
            r4 = r1
            r3 = 0
        L_0x000a:
            boolean r5 = r0.hasNext()
            if (r5 == 0) goto L_0x017f
            java.lang.Object r5 = r0.next()
            java.lang.String r5 = (java.lang.String) r5
            int r6 = r5.hashCode()
            r7 = 1152(0x480, float:1.614E-42)
            r8 = 2
            r9 = -1
            r10 = 1
            if (r6 == r7) goto L_0x0093
            r7 = 1176(0x498, float:1.648E-42)
            if (r6 == r7) goto L_0x0089
            r7 = 1178(0x49a, float:1.651E-42)
            if (r6 == r7) goto L_0x007f
            r7 = 1192(0x4a8, float:1.67E-42)
            if (r6 == r7) goto L_0x0075
            r7 = 1194(0x4aa, float:1.673E-42)
            if (r6 == r7) goto L_0x006b
            r7 = 1207(0x4b7, float:1.691E-42)
            if (r6 == r7) goto L_0x0061
            r7 = 1209(0x4b9, float:1.694E-42)
            if (r6 == r7) goto L_0x0056
            r7 = 1199(0x4af, float:1.68E-42)
            if (r6 == r7) goto L_0x004c
            r7 = 1200(0x4b0, float:1.682E-42)
            if (r6 == r7) goto L_0x0042
            goto L_0x009d
        L_0x0042:
            java.lang.String r6 = "$T"
            boolean r6 = r5.equals(r6)
            if (r6 == 0) goto L_0x009d
            r6 = 3
            goto L_0x009e
        L_0x004c:
            java.lang.String r6 = "$S"
            boolean r6 = r5.equals(r6)
            if (r6 == 0) goto L_0x009d
            r6 = 2
            goto L_0x009e
        L_0x0056:
            java.lang.String r6 = "$]"
            boolean r6 = r5.equals(r6)
            if (r6 == 0) goto L_0x009d
            r6 = 8
            goto L_0x009e
        L_0x0061:
            java.lang.String r6 = "$["
            boolean r6 = r5.equals(r6)
            if (r6 == 0) goto L_0x009d
            r6 = 7
            goto L_0x009e
        L_0x006b:
            java.lang.String r6 = "$N"
            boolean r6 = r5.equals(r6)
            if (r6 == 0) goto L_0x009d
            r6 = 1
            goto L_0x009e
        L_0x0075:
            java.lang.String r6 = "$L"
            boolean r6 = r5.equals(r6)
            if (r6 == 0) goto L_0x009d
            r6 = 0
            goto L_0x009e
        L_0x007f:
            java.lang.String r6 = "$>"
            boolean r6 = r5.equals(r6)
            if (r6 == 0) goto L_0x009d
            r6 = 5
            goto L_0x009e
        L_0x0089:
            java.lang.String r6 = "$<"
            boolean r6 = r5.equals(r6)
            if (r6 == 0) goto L_0x009d
            r6 = 6
            goto L_0x009e
        L_0x0093:
            java.lang.String r6 = "$$"
            boolean r6 = r5.equals(r6)
            if (r6 == 0) goto L_0x009d
            r6 = 4
            goto L_0x009e
        L_0x009d:
            r6 = -1
        L_0x009e:
            java.lang.String r7 = "$"
            switch(r6) {
                case 0: goto L_0x0168;
                case 1: goto L_0x015a;
                case 2: goto L_0x0141;
                case 3: goto L_0x00f0;
                case 4: goto L_0x00eb;
                case 5: goto L_0x00e6;
                case 6: goto L_0x00e1;
                case 7: goto L_0x00d0;
                case 8: goto L_0x00b8;
                default: goto L_0x00a3;
            }
        L_0x00a3:
            if (r4 == 0) goto L_0x017a
            java.lang.String r6 = "."
            boolean r6 = r5.startsWith(r6)
            if (r6 == 0) goto L_0x0176
            java.lang.String r6 = r4.canonicalName
            boolean r6 = r11.emitStaticImportMember(r6, r5)
            if (r6 == 0) goto L_0x0176
            r4 = r1
            goto L_0x000a
        L_0x00b8:
            int r5 = r11.statementLine
            if (r5 == r9) goto L_0x00bd
            goto L_0x00be
        L_0x00bd:
            r10 = 0
        L_0x00be:
            java.lang.Object[] r5 = new java.lang.Object[r2]
            java.lang.String r6 = "statement exit $] has no matching statement enter $["
            com.squareup.javapoet.Util.checkState(r10, r6, r5)
            int r5 = r11.statementLine
            if (r5 <= 0) goto L_0x00cc
            r11.unindent(r8)
        L_0x00cc:
            r11.statementLine = r9
            goto L_0x000a
        L_0x00d0:
            int r5 = r11.statementLine
            if (r5 != r9) goto L_0x00d5
            goto L_0x00d6
        L_0x00d5:
            r10 = 0
        L_0x00d6:
            java.lang.Object[] r5 = new java.lang.Object[r2]
            java.lang.String r6 = "statement enter $[ followed by statement enter $["
            com.squareup.javapoet.Util.checkState(r10, r6, r5)
            r11.statementLine = r2
            goto L_0x000a
        L_0x00e1:
            r11.unindent()
            goto L_0x000a
        L_0x00e6:
            r11.indent()
            goto L_0x000a
        L_0x00eb:
            r11.emitAndIndent(r7)
            goto L_0x000a
        L_0x00f0:
            java.util.List<java.lang.Object> r5 = r12.args
            int r6 = r3 + 1
            java.lang.Object r3 = r5.get(r3)
            com.squareup.javapoet.TypeName r3 = (com.squareup.javapoet.TypeName) r3
            boolean r5 = r3.isAnnotated()
            if (r5 == 0) goto L_0x0107
            r3.emitAnnotations(r11)
            com.squareup.javapoet.TypeName r3 = r3.withoutAnnotations()
        L_0x0107:
            boolean r5 = r3 instanceof com.squareup.javapoet.ClassName
            if (r5 == 0) goto L_0x013d
            boolean r5 = r0.hasNext()
            if (r5 == 0) goto L_0x013d
            java.util.List<java.lang.String> r5 = r12.formatParts
            int r8 = r0.nextIndex()
            java.lang.Object r5 = r5.get(r8)
            java.lang.String r5 = (java.lang.String) r5
            boolean r5 = r5.startsWith(r7)
            if (r5 != 0) goto L_0x013d
            r5 = r3
            com.squareup.javapoet.ClassName r5 = (com.squareup.javapoet.ClassName) r5
            java.util.Set<java.lang.String> r7 = r11.staticImportClassNames
            java.lang.String r8 = r5.canonicalName
            boolean r7 = r7.contains(r8)
            if (r7 == 0) goto L_0x013d
            if (r4 != 0) goto L_0x0133
            goto L_0x0134
        L_0x0133:
            r10 = 0
        L_0x0134:
            java.lang.Object[] r3 = new java.lang.Object[r2]
            java.lang.String r4 = "pending type for static import?!"
            com.squareup.javapoet.Util.checkState(r10, r4, r3)
            r4 = r5
            goto L_0x0173
        L_0x013d:
            r3.emit(r11)
            goto L_0x0173
        L_0x0141:
            java.util.List<java.lang.Object> r5 = r12.args
            int r6 = r3 + 1
            java.lang.Object r3 = r5.get(r3)
            java.lang.String r3 = (java.lang.String) r3
            if (r3 == 0) goto L_0x0154
            java.lang.String r5 = r11.indent
            java.lang.String r3 = com.squareup.javapoet.Util.stringLiteralWithDoubleQuotes(r3, r5)
            goto L_0x0156
        L_0x0154:
            java.lang.String r3 = "null"
        L_0x0156:
            r11.emitAndIndent(r3)
            goto L_0x0173
        L_0x015a:
            java.util.List<java.lang.Object> r5 = r12.args
            int r6 = r3 + 1
            java.lang.Object r3 = r5.get(r3)
            java.lang.String r3 = (java.lang.String) r3
            r11.emitAndIndent(r3)
            goto L_0x0173
        L_0x0168:
            java.util.List<java.lang.Object> r5 = r12.args
            int r6 = r3 + 1
            java.lang.Object r3 = r5.get(r3)
            r11.emitLiteral(r3)
        L_0x0173:
            r3 = r6
            goto L_0x000a
        L_0x0176:
            r4.emit(r11)
            r4 = r1
        L_0x017a:
            r11.emitAndIndent(r5)
            goto L_0x000a
        L_0x017f:
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.javapoet.CodeWriter.emit(com.squareup.javapoet.CodeBlock):com.squareup.javapoet.CodeWriter");
    }

    private static String extractMemberName(String str) {
        Util.checkArgument(Character.isJavaIdentifierStart(str.charAt(0)), "not an identifier: %s", str);
        for (int i = 1; i <= str.length(); i++) {
            if (!SourceVersion.isIdentifier(str.substring(0, i))) {
                return str.substring(0, i - 1);
            }
        }
        return str;
    }

    private boolean emitStaticImportMember(String str, String str2) throws IOException {
        String substring = str2.substring(1);
        if (substring.isEmpty() || !Character.isJavaIdentifierStart(substring.charAt(0))) {
            return false;
        }
        String str3 = str + Consts.DOT + extractMemberName(substring);
        String str4 = str + ".*";
        if (!this.staticImports.contains(str3) && !this.staticImports.contains(str4)) {
            return false;
        }
        emitAndIndent(substring);
        return true;
    }

    private void emitLiteral(Object obj) throws IOException {
        if (obj instanceof TypeSpec) {
            ((TypeSpec) obj).emit(this, (String) null, Collections.emptySet());
        } else if (obj instanceof AnnotationSpec) {
            ((AnnotationSpec) obj).emit(this, true);
        } else if (obj instanceof CodeBlock) {
            emit((CodeBlock) obj);
        } else {
            emitAndIndent(String.valueOf(obj));
        }
    }

    /* access modifiers changed from: package-private */
    public String lookupName(ClassName className) {
        ClassName className2 = className;
        boolean z = false;
        while (className2 != null) {
            ClassName resolve = resolve(className2.simpleName());
            boolean z2 = resolve != null;
            if (Objects.equals(resolve, className2)) {
                return Util.join(Consts.DOT, className.simpleNames().subList(className2.simpleNames().size() - 1, className.simpleNames().size()));
            }
            className2 = className2.enclosingClassName();
            z = z2;
        }
        if (z) {
            return className.canonicalName;
        }
        if (Objects.equals(this.packageName, className.packageName())) {
            this.referencedNames.add(className.topLevelClassName().simpleName());
            return Util.join(Consts.DOT, className.simpleNames());
        }
        if (!this.javadoc) {
            importableType(className);
        }
        return className.canonicalName;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x000b, code lost:
        r3 = r3.topLevelClassName();
        r0 = r3.simpleName();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void importableType(com.squareup.javapoet.ClassName r3) {
        /*
            r2 = this;
            java.lang.String r0 = r3.packageName()
            boolean r0 = r0.isEmpty()
            if (r0 == 0) goto L_0x000b
            return
        L_0x000b:
            com.squareup.javapoet.ClassName r3 = r3.topLevelClassName()
            java.lang.String r0 = r3.simpleName()
            java.util.Map<java.lang.String, com.squareup.javapoet.ClassName> r1 = r2.importableTypes
            java.lang.Object r3 = r1.put(r0, r3)
            com.squareup.javapoet.ClassName r3 = (com.squareup.javapoet.ClassName) r3
            if (r3 == 0) goto L_0x0022
            java.util.Map<java.lang.String, com.squareup.javapoet.ClassName> r1 = r2.importableTypes
            r1.put(r0, r3)
        L_0x0022:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.javapoet.CodeWriter.importableType(com.squareup.javapoet.ClassName):void");
    }

    private ClassName resolve(String str) {
        for (int size = this.typeSpecStack.size() - 1; size >= 0; size--) {
            for (TypeSpec typeSpec : this.typeSpecStack.get(size).typeSpecs) {
                if (Objects.equals(typeSpec.name, str)) {
                    return stackClassName(size, str);
                }
            }
        }
        if (this.typeSpecStack.size() > 0 && Objects.equals(this.typeSpecStack.get(0).name, str)) {
            return ClassName.get(this.packageName, str, new String[0]);
        }
        ClassName className = this.importedTypes.get(str);
        if (className != null) {
            return className;
        }
        return null;
    }

    private ClassName stackClassName(int i, String str) {
        ClassName className = ClassName.get(this.packageName, this.typeSpecStack.get(0).name, new String[0]);
        for (int i2 = 1; i2 <= i; i2++) {
            className = className.nestedClass(this.typeSpecStack.get(i2).name);
        }
        return className.nestedClass(str);
    }

    /* access modifiers changed from: package-private */
    public CodeWriter emitAndIndent(String str) throws IOException {
        String[] split = str.split(StringUtils.LF, -1);
        int length = split.length;
        int i = 0;
        boolean z = true;
        while (i < length) {
            String str2 = split[i];
            if (!z) {
                if ((this.javadoc || this.comment) && this.trailingNewline) {
                    emitIndentation();
                    this.out.append(this.javadoc ? " *" : "//");
                }
                this.out.append(10);
                this.trailingNewline = true;
                int i2 = this.statementLine;
                if (i2 != -1) {
                    if (i2 == 0) {
                        indent(2);
                    }
                    this.statementLine++;
                }
            }
            if (!str2.isEmpty()) {
                if (this.trailingNewline) {
                    emitIndentation();
                    if (this.javadoc) {
                        this.out.append(" * ");
                    } else if (this.comment) {
                        this.out.append("// ");
                    }
                }
                this.out.append(str2);
                this.trailingNewline = false;
            }
            i++;
            z = false;
        }
        return this;
    }

    private void emitIndentation() throws IOException {
        for (int i = 0; i < this.indentLevel; i++) {
            this.out.append(this.indent);
        }
    }

    /* access modifiers changed from: package-private */
    public Map<String, ClassName> suggestedImports() {
        LinkedHashMap linkedHashMap = new LinkedHashMap(this.importableTypes);
        linkedHashMap.keySet().removeAll(this.referencedNames);
        return linkedHashMap;
    }
}
