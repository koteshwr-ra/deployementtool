package com.squareup.javapoet;

import com.alibaba.android.arouter.utils.Consts;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.NestingKind;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;

public final class ClassName extends TypeName implements Comparable<ClassName> {
    public static final ClassName OBJECT = get((Class<?>) Object.class);
    final String canonicalName;
    final List<String> names;

    private ClassName(List<String> list) {
        this(list, new ArrayList());
    }

    private ClassName(List<String> list, List<AnnotationSpec> list2) {
        super(list2);
        String str;
        for (int i = 1; i < list.size(); i++) {
            Util.checkArgument(SourceVersion.isName(list.get(i)), "part '%s' is keyword", list.get(i));
        }
        this.names = Util.immutableList(list);
        if (list.get(0).isEmpty()) {
            str = Util.join(Consts.DOT, list.subList(1, list.size()));
        } else {
            str = Util.join(Consts.DOT, list);
        }
        this.canonicalName = str;
    }

    public ClassName annotated(List<AnnotationSpec> list) {
        return new ClassName(this.names, concatAnnotations(list));
    }

    public TypeName withoutAnnotations() {
        return new ClassName(this.names);
    }

    public String packageName() {
        return this.names.get(0);
    }

    public ClassName enclosingClassName() {
        if (this.names.size() == 2) {
            return null;
        }
        List<String> list = this.names;
        return new ClassName(list.subList(0, list.size() - 1));
    }

    public ClassName topLevelClassName() {
        return new ClassName(this.names.subList(0, 2));
    }

    public ClassName nestedClass(String str) {
        Util.checkNotNull(str, "name == null", new Object[0]);
        ArrayList arrayList = new ArrayList(this.names.size() + 1);
        arrayList.addAll(this.names);
        arrayList.add(str);
        return new ClassName(arrayList);
    }

    public List<String> simpleNames() {
        List<String> list = this.names;
        return list.subList(1, list.size());
    }

    public ClassName peerClass(String str) {
        ArrayList arrayList = new ArrayList(this.names);
        arrayList.set(arrayList.size() - 1, str);
        return new ClassName(arrayList);
    }

    public String simpleName() {
        List<String> list = this.names;
        return list.get(list.size() - 1);
    }

    public static ClassName get(Class<?> cls) {
        Util.checkNotNull(cls, "clazz == null", new Object[0]);
        Util.checkArgument(!cls.isPrimitive(), "primitive types cannot be represented as a ClassName", new Object[0]);
        Util.checkArgument(!Void.TYPE.equals(cls), "'void' type cannot be represented as a ClassName", new Object[0]);
        Util.checkArgument(!cls.isArray(), "array types cannot be represented as a ClassName", new Object[0]);
        ArrayList arrayList = new ArrayList();
        while (true) {
            arrayList.add(cls.getSimpleName());
            Class<?> enclosingClass = cls.getEnclosingClass();
            if (enclosingClass == null) {
                break;
            }
            cls = enclosingClass;
        }
        int lastIndexOf = cls.getName().lastIndexOf(46);
        if (lastIndexOf != -1) {
            arrayList.add(cls.getName().substring(0, lastIndexOf));
        }
        Collections.reverse(arrayList);
        return new ClassName(arrayList);
    }

    public static ClassName bestGuess(String str) {
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (i < str.length() && Character.isLowerCase(str.codePointAt(i))) {
            i = str.indexOf(46, i) + 1;
            Util.checkArgument(i != 0, "couldn't make a guess for %s", str);
        }
        arrayList.add(i != 0 ? str.substring(0, i - 1) : "");
        for (String str2 : str.substring(i).split("\\.", -1)) {
            Util.checkArgument(!str2.isEmpty() && Character.isUpperCase(str2.codePointAt(0)), "couldn't make a guess for %s", str);
            arrayList.add(str2);
        }
        Util.checkArgument(arrayList.size() >= 2, "couldn't make a guess for %s", str);
        return new ClassName(arrayList);
    }

    public static ClassName get(String str, String str2, String... strArr) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(str);
        arrayList.add(str2);
        Collections.addAll(arrayList, strArr);
        return new ClassName(arrayList);
    }

    public static ClassName get(TypeElement typeElement) {
        Util.checkNotNull(typeElement, "element == null", new Object[0]);
        ArrayList arrayList = new ArrayList();
        for (TypeElement typeElement2 = typeElement; isClassOrInterface(typeElement2); typeElement2 = typeElement2.getEnclosingElement()) {
            Util.checkArgument(typeElement.getNestingKind() == NestingKind.TOP_LEVEL || typeElement.getNestingKind() == NestingKind.MEMBER, "unexpected type testing", new Object[0]);
            arrayList.add(typeElement2.getSimpleName().toString());
        }
        arrayList.add(getPackage(typeElement).getQualifiedName().toString());
        Collections.reverse(arrayList);
        return new ClassName(arrayList);
    }

    private static boolean isClassOrInterface(Element element) {
        return element.getKind().isClass() || element.getKind().isInterface();
    }

    private static PackageElement getPackage(Element element) {
        while (element.getKind() != ElementKind.PACKAGE) {
            element = element.getEnclosingElement();
        }
        return (PackageElement) element;
    }

    public int compareTo(ClassName className) {
        return this.canonicalName.compareTo(className.canonicalName);
    }

    /* access modifiers changed from: package-private */
    public CodeWriter emit(CodeWriter codeWriter) throws IOException {
        return codeWriter.emitAndIndent(codeWriter.lookupName(this));
    }
}
