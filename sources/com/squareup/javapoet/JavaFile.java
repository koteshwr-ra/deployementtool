package com.squareup.javapoet;

import com.alibaba.android.arouter.utils.Consts;
import com.squareup.javapoet.CodeBlock;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;

public final class JavaFile {
    private static final Appendable NULL_APPENDABLE = new Appendable() {
        public Appendable append(char c) {
            return this;
        }

        public Appendable append(CharSequence charSequence) {
            return this;
        }

        public Appendable append(CharSequence charSequence, int i, int i2) {
            return this;
        }
    };
    public final CodeBlock fileComment;
    private final String indent;
    public final String packageName;
    public final boolean skipJavaLangImports;
    private final Set<String> staticImports;
    public final TypeSpec typeSpec;

    private JavaFile(Builder builder) {
        this.fileComment = builder.fileComment.build();
        this.packageName = builder.packageName;
        this.typeSpec = builder.typeSpec;
        this.skipJavaLangImports = builder.skipJavaLangImports;
        this.staticImports = Util.immutableSet(builder.staticImports);
        this.indent = builder.indent;
    }

    public void writeTo(Appendable appendable) throws IOException {
        CodeWriter codeWriter = new CodeWriter(NULL_APPENDABLE, this.indent, this.staticImports);
        emit(codeWriter);
        emit(new CodeWriter(appendable, this.indent, codeWriter.suggestedImports(), this.staticImports));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x006e, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0073, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0074, code lost:
        r6.addSuppressed(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0077, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void writeTo(java.nio.file.Path r6) throws java.io.IOException {
        /*
            r5 = this;
            r0 = 0
            java.nio.file.LinkOption[] r1 = new java.nio.file.LinkOption[r0]
            boolean r1 = java.nio.file.Files.notExists(r6, r1)
            r2 = 1
            if (r1 != 0) goto L_0x0015
            java.nio.file.LinkOption[] r1 = new java.nio.file.LinkOption[r0]
            boolean r1 = java.nio.file.Files.isDirectory(r6, r1)
            if (r1 == 0) goto L_0x0013
            goto L_0x0015
        L_0x0013:
            r1 = 0
            goto L_0x0016
        L_0x0015:
            r1 = 1
        L_0x0016:
            java.lang.Object[] r2 = new java.lang.Object[r2]
            r2[r0] = r6
            java.lang.String r3 = "path %s exists but is not a directory."
            com.squareup.javapoet.Util.checkArgument(r1, r3, r2)
            java.lang.String r1 = r5.packageName
            boolean r1 = r1.isEmpty()
            if (r1 != 0) goto L_0x0041
            java.lang.String r1 = r5.packageName
            java.lang.String r2 = "\\."
            java.lang.String[] r1 = r1.split(r2)
            int r2 = r1.length
            r3 = 0
        L_0x0031:
            if (r3 >= r2) goto L_0x003c
            r4 = r1[r3]
            java.nio.file.Path r6 = r6.resolve(r4)
            int r3 = r3 + 1
            goto L_0x0031
        L_0x003c:
            java.nio.file.attribute.FileAttribute[] r1 = new java.nio.file.attribute.FileAttribute[r0]
            java.nio.file.Files.createDirectories(r6, r1)
        L_0x0041:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            com.squareup.javapoet.TypeSpec r2 = r5.typeSpec
            java.lang.String r2 = r2.name
            r1.append(r2)
            java.lang.String r2 = ".java"
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            java.nio.file.Path r6 = r6.resolve(r1)
            java.io.OutputStreamWriter r1 = new java.io.OutputStreamWriter
            java.nio.file.OpenOption[] r0 = new java.nio.file.OpenOption[r0]
            java.io.OutputStream r6 = java.nio.file.Files.newOutputStream(r6, r0)
            r1.<init>(r6)
            r5.writeTo((java.lang.Appendable) r1)     // Catch:{ all -> 0x006c }
            r1.close()
            return
        L_0x006c:
            r6 = move-exception
            throw r6     // Catch:{ all -> 0x006e }
        L_0x006e:
            r0 = move-exception
            r1.close()     // Catch:{ all -> 0x0073 }
            goto L_0x0077
        L_0x0073:
            r1 = move-exception
            r6.addSuppressed(r1)
        L_0x0077:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.javapoet.JavaFile.writeTo(java.nio.file.Path):void");
    }

    public void writeTo(File file) throws IOException {
        writeTo(file.toPath());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x004a, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x004b, code lost:
        if (r0 != null) goto L_0x004d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0055, code lost:
        throw r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void writeTo(javax.annotation.processing.Filer r4) throws java.io.IOException {
        /*
            r3 = this;
            java.lang.String r0 = r3.packageName
            boolean r0 = r0.isEmpty()
            if (r0 == 0) goto L_0x000d
            com.squareup.javapoet.TypeSpec r0 = r3.typeSpec
            java.lang.String r0 = r0.name
            goto L_0x0027
        L_0x000d:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = r3.packageName
            r0.append(r1)
            java.lang.String r1 = "."
            r0.append(r1)
            com.squareup.javapoet.TypeSpec r1 = r3.typeSpec
            java.lang.String r1 = r1.name
            r0.append(r1)
            java.lang.String r0 = r0.toString()
        L_0x0027:
            com.squareup.javapoet.TypeSpec r1 = r3.typeSpec
            java.util.List<javax.lang.model.element.Element> r1 = r1.originatingElements
            int r2 = r1.size()
            javax.lang.model.element.Element[] r2 = new javax.lang.model.element.Element[r2]
            java.lang.Object[] r1 = r1.toArray(r2)
            javax.lang.model.element.Element[] r1 = (javax.lang.model.element.Element[]) r1
            javax.tools.JavaFileObject r4 = r4.createSourceFile(r0, r1)
            java.io.Writer r0 = r4.openWriter()     // Catch:{ Exception -> 0x0056 }
            r3.writeTo((java.lang.Appendable) r0)     // Catch:{ all -> 0x0048 }
            if (r0 == 0) goto L_0x0047
            r0.close()     // Catch:{ Exception -> 0x0056 }
        L_0x0047:
            return
        L_0x0048:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x004a }
        L_0x004a:
            r2 = move-exception
            if (r0 == 0) goto L_0x0055
            r0.close()     // Catch:{ all -> 0x0051 }
            goto L_0x0055
        L_0x0051:
            r0 = move-exception
            r1.addSuppressed(r0)     // Catch:{ Exception -> 0x0056 }
        L_0x0055:
            throw r2     // Catch:{ Exception -> 0x0056 }
        L_0x0056:
            r0 = move-exception
            r4.delete()     // Catch:{ Exception -> 0x005a }
        L_0x005a:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.javapoet.JavaFile.writeTo(javax.annotation.processing.Filer):void");
    }

    private void emit(CodeWriter codeWriter) throws IOException {
        codeWriter.pushPackage(this.packageName);
        if (!this.fileComment.isEmpty()) {
            codeWriter.emitComment(this.fileComment);
        }
        if (!this.packageName.isEmpty()) {
            codeWriter.emit("package $L;\n", this.packageName);
            codeWriter.emit(StringUtils.LF);
        }
        if (!this.staticImports.isEmpty()) {
            for (String str : this.staticImports) {
                codeWriter.emit("import static $L;\n", str);
            }
            codeWriter.emit(StringUtils.LF);
        }
        Iterator it = new TreeSet(codeWriter.importedTypes().values()).iterator();
        int i = 0;
        while (it.hasNext()) {
            ClassName className = (ClassName) it.next();
            if (!this.skipJavaLangImports || !className.packageName().equals("java.lang")) {
                codeWriter.emit("import $L;\n", className);
                i++;
            }
        }
        if (i > 0) {
            codeWriter.emit(StringUtils.LF);
        }
        this.typeSpec.emit(codeWriter, (String) null, Collections.emptySet());
        codeWriter.popPackage();
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
        try {
            StringBuilder sb = new StringBuilder();
            writeTo((Appendable) sb);
            return sb.toString();
        } catch (IOException unused) {
            throw new AssertionError();
        }
    }

    public JavaFileObject toJavaFileObject() {
        String str;
        StringBuilder sb = new StringBuilder();
        if (this.packageName.isEmpty()) {
            str = this.typeSpec.name;
        } else {
            str = this.packageName.replace(ClassUtils.PACKAGE_SEPARATOR_CHAR, '/') + '/' + this.typeSpec.name;
        }
        sb.append(str);
        sb.append(JavaFileObject.Kind.SOURCE.extension);
        return new SimpleJavaFileObject(URI.create(sb.toString()), JavaFileObject.Kind.SOURCE) {
            private final long lastModified = System.currentTimeMillis();

            public String getCharContent(boolean z) {
                return JavaFile.this.toString();
            }

            public InputStream openInputStream() throws IOException {
                return new ByteArrayInputStream(getCharContent(true).getBytes());
            }

            public long getLastModified() {
                return this.lastModified;
            }
        };
    }

    public static Builder builder(String str, TypeSpec typeSpec2) {
        Util.checkNotNull(str, "packageName == null", new Object[0]);
        Util.checkNotNull(typeSpec2, "typeSpec == null", new Object[0]);
        return new Builder(str, typeSpec2);
    }

    public Builder toBuilder() {
        Builder builder = new Builder(this.packageName, this.typeSpec);
        builder.fileComment.add(this.fileComment);
        boolean unused = builder.skipJavaLangImports = this.skipJavaLangImports;
        String unused2 = builder.indent = this.indent;
        return builder;
    }

    public static final class Builder {
        /* access modifiers changed from: private */
        public final CodeBlock.Builder fileComment;
        /* access modifiers changed from: private */
        public String indent;
        /* access modifiers changed from: private */
        public final String packageName;
        /* access modifiers changed from: private */
        public boolean skipJavaLangImports;
        /* access modifiers changed from: private */
        public final Set<String> staticImports;
        /* access modifiers changed from: private */
        public final TypeSpec typeSpec;

        private Builder(String str, TypeSpec typeSpec2) {
            this.fileComment = CodeBlock.builder();
            this.staticImports = new TreeSet();
            this.indent = "  ";
            this.packageName = str;
            this.typeSpec = typeSpec2;
        }

        public Builder addFileComment(String str, Object... objArr) {
            this.fileComment.add(str, objArr);
            return this;
        }

        public Builder addStaticImport(Enum<?> enumR) {
            return addStaticImport(ClassName.get(enumR.getDeclaringClass()), enumR.name());
        }

        public Builder addStaticImport(Class<?> cls, String... strArr) {
            return addStaticImport(ClassName.get(cls), strArr);
        }

        public Builder addStaticImport(ClassName className, String... strArr) {
            Util.checkArgument(className != null, "className == null", new Object[0]);
            Util.checkArgument(strArr != null, "names == null", new Object[0]);
            Util.checkArgument(strArr.length > 0, "names array is empty", new Object[0]);
            int length = strArr.length;
            for (int i = 0; i < length; i++) {
                String str = strArr[i];
                Util.checkArgument(str != null, "null entry in names array: %s", Arrays.toString(strArr));
                Set<String> set = this.staticImports;
                set.add(className.canonicalName + Consts.DOT + str);
            }
            return this;
        }

        public Builder skipJavaLangImports(boolean z) {
            this.skipJavaLangImports = z;
            return this;
        }

        public Builder indent(String str) {
            this.indent = str;
            return this;
        }

        public JavaFile build() {
            return new JavaFile(this);
        }
    }
}
