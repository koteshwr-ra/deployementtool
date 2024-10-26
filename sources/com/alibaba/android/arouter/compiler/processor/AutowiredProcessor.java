package com.alibaba.android.arouter.compiler.processor;

import com.alibaba.android.arouter.compiler.utils.Consts;
import com.alibaba.android.arouter.compiler.utils.Logger;
import com.alibaba.android.arouter.compiler.utils.TypeUtils;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.enums.TypeKind;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedOptions;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

@SupportedAnnotationTypes({"com.alibaba.android.arouter.facade.annotation.Autowired"})
@SupportedOptions({"AROUTER_MODULE_NAME"})
@SupportedSourceVersion(SourceVersion.RELEASE_7)
public class AutowiredProcessor extends AbstractProcessor {
    private static final ClassName ARouterClass = ClassName.get("com.alibaba.android.arouter.launcher", "ARouter", new String[0]);
    private static final ClassName AndroidLog = ClassName.get("android.util", "Log", new String[0]);
    private Elements elements;
    private Logger logger;
    private Filer mFiler;
    private Map<TypeElement, List<Element>> parentAndChild = new HashMap();
    private TypeUtils typeUtils;
    private Types types;

    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        AutowiredProcessor.super.init(processingEnvironment);
        this.mFiler = this.processingEnv.getFiler();
        this.types = this.processingEnv.getTypeUtils();
        Elements elementUtils = this.processingEnv.getElementUtils();
        this.elements = elementUtils;
        this.typeUtils = new TypeUtils(this.types, elementUtils);
        Logger logger2 = new Logger(this.processingEnv.getMessager());
        this.logger = logger2;
        logger2.info(">>> AutowiredProcessor init. <<<");
    }

    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        if (!CollectionUtils.isNotEmpty(set)) {
            return false;
        }
        try {
            this.logger.info(">>> Found autowired field, start... <<<");
            categories(roundEnvironment.getElementsAnnotatedWith(Autowired.class));
            generateHelper();
            return true;
        } catch (Exception e) {
            this.logger.error((Throwable) e);
            return true;
        }
    }

    private void generateHelper() throws IOException, IllegalAccessException {
        TypeSpec.Builder builder;
        String str;
        String str2;
        Iterator<Map.Entry<TypeElement, List<Element>>> it;
        boolean z;
        String str3;
        TypeElement typeElement = this.elements.getTypeElement(Consts.ISYRINGE);
        TypeElement typeElement2 = this.elements.getTypeElement(Consts.JSON_SERVICE);
        TypeMirror asType = this.elements.getTypeElement(Consts.IPROVIDER).asType();
        TypeMirror asType2 = this.elements.getTypeElement(Consts.ACTIVITY).asType();
        TypeMirror asType3 = this.elements.getTypeElement(Consts.FRAGMENT).asType();
        TypeMirror asType4 = this.elements.getTypeElement(Consts.FRAGMENT_V4).asType();
        int i = 0;
        ParameterSpec build = ParameterSpec.builder((TypeName) TypeName.OBJECT, "target", new Modifier[0]).build();
        if (MapUtils.isNotEmpty(this.parentAndChild)) {
            Iterator<Map.Entry<TypeElement, List<Element>>> it2 = this.parentAndChild.entrySet().iterator();
            while (it2.hasNext()) {
                Map.Entry next = it2.next();
                MethodSpec.Builder addAnnotation = MethodSpec.methodBuilder(Consts.METHOD_INJECT).addAnnotation((Class<?>) Override.class);
                Modifier[] modifierArr = new Modifier[1];
                modifierArr[i] = Modifier.PUBLIC;
                MethodSpec.Builder addParameter = addAnnotation.addModifiers(modifierArr).addParameter(build);
                TypeElement typeElement3 = (TypeElement) next.getKey();
                List list = (List) next.getValue();
                String obj = typeElement3.getQualifiedName().toString();
                String substring = obj.substring(i, obj.lastIndexOf(com.alibaba.android.arouter.utils.Consts.DOT));
                String str4 = typeElement3.getSimpleName() + "$$ARouter$$Autowired";
                Logger logger2 = this.logger;
                StringBuilder sb = new StringBuilder();
                ParameterSpec parameterSpec = build;
                sb.append(">>> Start process ");
                sb.append(list.size());
                sb.append(" field in ");
                sb.append(typeElement3.getSimpleName());
                sb.append(" ... <<<");
                logger2.info(sb.toString());
                TypeSpec.Builder addModifiers = TypeSpec.classBuilder(str4).addJavadoc(Consts.WARNING_TIPS, new Object[0]).addSuperinterface((TypeName) ClassName.get(typeElement)).addModifiers(Modifier.PUBLIC);
                TypeElement typeElement4 = typeElement;
                addModifiers.addField(FieldSpec.builder(TypeName.get(typeElement2.asType()), "serializationService", Modifier.PRIVATE).build());
                addParameter.addStatement("serializationService = $T.getInstance().navigation($T.class)", ARouterClass, ClassName.get(typeElement2));
                addParameter.addStatement("$T substitute = ($T)target", ClassName.get(typeElement3), ClassName.get(typeElement3));
                Iterator it3 = list.iterator();
                while (it3.hasNext()) {
                    Element element = (Element) it3.next();
                    Autowired autowired = (Autowired) element.getAnnotation(Autowired.class);
                    String obj2 = element.getSimpleName().toString();
                    TypeElement typeElement5 = typeElement2;
                    Iterator it4 = it3;
                    TypeMirror typeMirror = asType;
                    if (this.types.isSubtype(element.asType(), asType)) {
                        it = it2;
                        if ("".equals(autowired.name())) {
                            addParameter.addStatement("substitute." + obj2 + " = $T.getInstance().navigation($T.class)", ARouterClass, ClassName.get(element.asType()));
                        } else {
                            addParameter.addStatement("substitute." + obj2 + " = ($T)$T.getInstance().build($S).navigation()", ClassName.get(element.asType()), ARouterClass, autowired.name());
                        }
                        if (autowired.required()) {
                            addParameter.beginControlFlow("if (substitute." + obj2 + " == null)", new Object[0]);
                            addParameter.addStatement("throw new RuntimeException(\"The field '" + obj2 + "' is null, in class '\" + $T.class.getName() + \"!\")", ClassName.get(typeElement3));
                            addParameter.endControlFlow();
                        }
                        builder = addModifiers;
                        str2 = str4;
                        str = substring;
                    } else {
                        it = it2;
                        String str5 = "substitute." + obj2;
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("substitute.");
                        sb2.append(obj2);
                        str2 = str4;
                        sb2.append(" = ");
                        str = substring;
                        sb2.append(buildCastCode(element));
                        sb2.append("substitute.");
                        String sb3 = sb2.toString();
                        builder = addModifiers;
                        if (this.types.isSubtype(typeElement3.asType(), asType2)) {
                            str3 = sb3 + "getIntent().";
                            z = true;
                        } else if (this.types.isSubtype(typeElement3.asType(), asType3) || this.types.isSubtype(typeElement3.asType(), asType4)) {
                            str3 = sb3 + "getArguments().";
                            z = false;
                        } else {
                            throw new IllegalAccessException("The field [" + obj2 + "] need autowired from intent, its parent must be activity or fragment!");
                        }
                        String buildStatement = buildStatement(str5, str3, this.typeUtils.typeExchange(element), z);
                        if (buildStatement.startsWith("serializationService.")) {
                            addParameter.beginControlFlow("if (null != serializationService)", new Object[0]);
                            String str6 = "substitute." + obj2 + " = " + buildStatement;
                            Object[] objArr = new Object[2];
                            objArr[0] = StringUtils.isEmpty(autowired.name()) ? obj2 : autowired.name();
                            objArr[1] = ClassName.get(element.asType());
                            addParameter.addStatement(str6, objArr);
                            addParameter.nextControlFlow("else", new Object[0]);
                            addParameter.addStatement("$T.e(\"ARouter::\", \"You want automatic inject the field '" + obj2 + "' in class '$T' , then you should implement 'SerializationService' to support object auto inject!\")", AndroidLog, ClassName.get(typeElement3));
                            addParameter.endControlFlow();
                        } else {
                            Object[] objArr2 = new Object[1];
                            objArr2[0] = StringUtils.isEmpty(autowired.name()) ? obj2 : autowired.name();
                            addParameter.addStatement(buildStatement, objArr2);
                        }
                        if (autowired.required() && !element.asType().getKind().isPrimitive()) {
                            addParameter.beginControlFlow("if (null == substitute." + obj2 + ")", new Object[0]);
                            addParameter.addStatement("$T.e(\"ARouter::\", \"The field '" + obj2 + "' is null, in class '\" + $T.class.getName() + \"!\")", AndroidLog, ClassName.get(typeElement3));
                            addParameter.endControlFlow();
                            typeElement2 = typeElement5;
                            it3 = it4;
                            asType = typeMirror;
                            it2 = it;
                            str4 = str2;
                            substring = str;
                            addModifiers = builder;
                        }
                    }
                    typeElement2 = typeElement5;
                    it3 = it4;
                    asType = typeMirror;
                    it2 = it;
                    str4 = str2;
                    substring = str;
                    addModifiers = builder;
                }
                TypeElement typeElement6 = typeElement2;
                Iterator<Map.Entry<TypeElement, List<Element>>> it5 = it2;
                TypeSpec.Builder builder2 = addModifiers;
                builder2.addMethod(addParameter.build());
                JavaFile.builder(substring, builder2.build()).build().writeTo(this.mFiler);
                this.logger.info(">>> " + typeElement3.getSimpleName() + " has been processed, " + str4 + " has been generated. <<<");
                build = parameterSpec;
                typeElement = typeElement4;
                typeElement2 = typeElement6;
                asType = asType;
                i = 0;
            }
            this.logger.info(">>> Autowired processor stop. <<<");
        }
    }

    private String buildCastCode(Element element) {
        if (this.typeUtils.typeExchange(element) != TypeKind.SERIALIZABLE.ordinal()) {
            return "";
        }
        return CodeBlock.builder().add("($T) ", ClassName.get(element.asType())).build().toString();
    }

    private String buildStatement(String str, String str2, int i, boolean z) {
        String str3;
        String str4;
        String str5;
        String str6;
        String str7;
        String str8;
        String str9;
        String str10;
        if (i == TypeKind.BOOLEAN.ordinal()) {
            StringBuilder sb = new StringBuilder();
            sb.append(str2);
            if (z) {
                str10 = "getBooleanExtra($S, " + str + ")";
            } else {
                str10 = "getBoolean($S)";
            }
            sb.append(str10);
            return sb.toString();
        } else if (i == TypeKind.BYTE.ordinal()) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(str2);
            if (z) {
                str9 = "getByteExtra($S, " + str + ")";
            } else {
                str9 = "getByte($S)";
            }
            sb2.append(str9);
            return sb2.toString();
        } else if (i == TypeKind.SHORT.ordinal()) {
            StringBuilder sb3 = new StringBuilder();
            sb3.append(str2);
            if (z) {
                str8 = "getShortExtra($S, " + str + ")";
            } else {
                str8 = "getShort($S)";
            }
            sb3.append(str8);
            return sb3.toString();
        } else if (i == TypeKind.INT.ordinal()) {
            StringBuilder sb4 = new StringBuilder();
            sb4.append(str2);
            if (z) {
                str7 = "getIntExtra($S, " + str + ")";
            } else {
                str7 = "getInt($S)";
            }
            sb4.append(str7);
            return sb4.toString();
        } else if (i == TypeKind.LONG.ordinal()) {
            StringBuilder sb5 = new StringBuilder();
            sb5.append(str2);
            if (z) {
                str6 = "getLongExtra($S, " + str + ")";
            } else {
                str6 = "getLong($S)";
            }
            sb5.append(str6);
            return sb5.toString();
        } else if (i == TypeKind.CHAR.ordinal()) {
            StringBuilder sb6 = new StringBuilder();
            sb6.append(str2);
            if (z) {
                str5 = "getCharExtra($S, " + str + ")";
            } else {
                str5 = "getChar($S)";
            }
            sb6.append(str5);
            return sb6.toString();
        } else if (i == TypeKind.FLOAT.ordinal()) {
            StringBuilder sb7 = new StringBuilder();
            sb7.append(str2);
            if (z) {
                str4 = "getFloatExtra($S, " + str + ")";
            } else {
                str4 = "getFloat($S)";
            }
            sb7.append(str4);
            return sb7.toString();
        } else if (i == TypeKind.DOUBLE.ordinal()) {
            StringBuilder sb8 = new StringBuilder();
            sb8.append(str2);
            if (z) {
                str3 = "getDoubleExtra($S, " + str + ")";
            } else {
                str3 = "getDouble($S)";
            }
            sb8.append(str3);
            return sb8.toString();
        } else {
            String str11 = "getStringExtra($S)";
            if (i == TypeKind.STRING.ordinal()) {
                StringBuilder sb9 = new StringBuilder();
                sb9.append(str2);
                if (!z) {
                    str11 = "getString($S)";
                }
                sb9.append(str11);
                return sb9.toString();
            } else if (i == TypeKind.SERIALIZABLE.ordinal()) {
                StringBuilder sb10 = new StringBuilder();
                sb10.append(str2);
                sb10.append(z ? "getSerializableExtra($S)" : "getSerializable($S)");
                return sb10.toString();
            } else if (i == TypeKind.PARCELABLE.ordinal()) {
                StringBuilder sb11 = new StringBuilder();
                sb11.append(str2);
                sb11.append(z ? "getParcelableExtra($S)" : "getParcelable($S)");
                return sb11.toString();
            } else if (i != TypeKind.OBJECT.ordinal()) {
                return str2;
            } else {
                StringBuilder sb12 = new StringBuilder();
                sb12.append("serializationService.parseObject(substitute.");
                sb12.append(z ? "getIntent()." : "getArguments().");
                if (!z) {
                    str11 = "getString($S)";
                }
                sb12.append(str11);
                sb12.append(", new com.alibaba.android.arouter.facade.model.TypeWrapper<$T>(){}.getType())");
                return sb12.toString();
            }
        }
    }

    private void categories(Set<? extends Element> set) throws IllegalAccessException {
        if (CollectionUtils.isNotEmpty(set)) {
            for (Element next : set) {
                TypeElement enclosingElement = next.getEnclosingElement();
                if (next.getModifiers().contains(Modifier.PRIVATE)) {
                    throw new IllegalAccessException("The inject fields CAN NOT BE 'private'!!! please check field [" + next.getSimpleName() + "] in class [" + enclosingElement.getQualifiedName() + "]");
                } else if (this.parentAndChild.containsKey(enclosingElement)) {
                    this.parentAndChild.get(enclosingElement).add(next);
                } else {
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(next);
                    this.parentAndChild.put(enclosingElement, arrayList);
                }
            }
            this.logger.info("categories finished.");
        }
    }
}
