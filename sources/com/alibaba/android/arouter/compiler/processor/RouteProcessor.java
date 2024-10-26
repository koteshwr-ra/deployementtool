package com.alibaba.android.arouter.compiler.processor;

import com.alibaba.android.arouter.compiler.entity.RouteDoc;
import com.alibaba.android.arouter.compiler.utils.Consts;
import com.alibaba.android.arouter.compiler.utils.Logger;
import com.alibaba.android.arouter.compiler.utils.TypeUtils;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.enums.RouteType;
import com.alibaba.android.arouter.facade.enums.TypeKind;
import com.alibaba.android.arouter.facade.model.RouteMeta;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import com.squareup.javapoet.WildcardTypeName;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
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
import javax.tools.StandardLocation;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

@SupportedAnnotationTypes({"com.alibaba.android.arouter.facade.annotation.Route", "com.alibaba.android.arouter.facade.annotation.Autowired"})
@SupportedOptions({"AROUTER_MODULE_NAME", "AROUTER_GENERATE_DOC"})
@SupportedSourceVersion(SourceVersion.RELEASE_7)
public class RouteProcessor extends AbstractProcessor {
    private Writer docWriter;
    private Elements elements;
    private boolean generateDoc;
    private Map<String, Set<RouteMeta>> groupMap = new HashMap();
    private TypeMirror iProvider = null;
    /* access modifiers changed from: private */
    public Logger logger;
    private Filer mFiler;
    private String moduleName = null;
    private Map<String, String> rootMap = new TreeMap();
    private TypeUtils typeUtils;
    private Types types;

    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        RouteProcessor.super.init(processingEnvironment);
        this.mFiler = processingEnvironment.getFiler();
        this.types = processingEnvironment.getTypeUtils();
        Elements elementUtils = processingEnvironment.getElementUtils();
        this.elements = elementUtils;
        this.typeUtils = new TypeUtils(this.types, elementUtils);
        this.logger = new Logger(processingEnvironment.getMessager());
        Map options = processingEnvironment.getOptions();
        if (MapUtils.isNotEmpty(options)) {
            this.moduleName = (String) options.get(Consts.KEY_MODULE_NAME);
            this.generateDoc = Consts.VALUE_ENABLE.equals(options.get(Consts.KEY_GENERATE_DOC_NAME));
        }
        if (StringUtils.isNotEmpty(this.moduleName)) {
            this.moduleName = this.moduleName.replaceAll("[^0-9a-zA-Z_]+", "");
            Logger logger2 = this.logger;
            logger2.info("The user has configuration the module name, it was [" + this.moduleName + "]");
            if (this.generateDoc) {
                try {
                    Filer filer = this.mFiler;
                    StandardLocation standardLocation = StandardLocation.SOURCE_OUTPUT;
                    this.docWriter = filer.createResource(standardLocation, Consts.PACKAGE_OF_GENERATE_DOCS, "arouter-map-of-" + this.moduleName + ".json", new Element[0]).openWriter();
                } catch (IOException e) {
                    Logger logger3 = this.logger;
                    logger3.error((CharSequence) "Create doc writer failed, because " + e.getMessage());
                }
            }
            this.iProvider = this.elements.getTypeElement(Consts.IPROVIDER).asType();
            this.logger.info(">>> RouteProcessor init. <<<");
        } else {
            this.logger.error((CharSequence) Consts.NO_MODULE_NAME_TIPS);
            throw new RuntimeException("ARouter::Compiler >>> No module name, for more information, look at gradle log.");
        }
    }

    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        if (!CollectionUtils.isNotEmpty(set)) {
            return false;
        }
        Set elementsAnnotatedWith = roundEnvironment.getElementsAnnotatedWith(Route.class);
        try {
            this.logger.info(">>> Found routes, start... <<<");
            parseRoutes(elementsAnnotatedWith);
            return true;
        } catch (Exception e) {
            this.logger.error((Throwable) e);
            return true;
        }
    }

    private void parseRoutes(Set<? extends Element> set) throws IOException {
        HashMap hashMap;
        MethodSpec.Builder builder;
        String str;
        ParameterSpec parameterSpec;
        ParameterSpec parameterSpec2;
        ClassName className;
        TypeMirror typeMirror;
        RouteMeta routeMeta;
        ParameterSpec parameterSpec3;
        ParameterSpec parameterSpec4;
        if (CollectionUtils.isNotEmpty(set)) {
            Logger logger2 = this.logger;
            StringBuilder sb = new StringBuilder();
            sb.append(">>> Found routes, size is ");
            sb.append(set.size());
            String str2 = " <<<";
            sb.append(str2);
            logger2.info(sb.toString());
            this.rootMap.clear();
            TypeMirror asType = this.elements.getTypeElement(Consts.ACTIVITY).asType();
            TypeMirror asType2 = this.elements.getTypeElement(Consts.SERVICE).asType();
            TypeMirror asType3 = this.elements.getTypeElement(Consts.FRAGMENT).asType();
            TypeMirror asType4 = this.elements.getTypeElement(Consts.FRAGMENT_V4).asType();
            TypeElement typeElement = this.elements.getTypeElement(Consts.IROUTE_GROUP);
            TypeElement typeElement2 = this.elements.getTypeElement(Consts.IPROVIDER_GROUP);
            ClassName className2 = ClassName.get((Class<?>) RouteMeta.class);
            ClassName className3 = ClassName.get((Class<?>) RouteType.class);
            TypeElement typeElement3 = typeElement2;
            ParameterizedTypeName parameterizedTypeName = ParameterizedTypeName.get(ClassName.get((Class<?>) Map.class), ClassName.get((Class<?>) String.class), ParameterizedTypeName.get(ClassName.get((Class<?>) Class.class), WildcardTypeName.subtypeOf((TypeName) ClassName.get(typeElement))));
            ParameterizedTypeName parameterizedTypeName2 = ParameterizedTypeName.get(ClassName.get((Class<?>) Map.class), ClassName.get((Class<?>) String.class), ClassName.get((Class<?>) RouteMeta.class));
            ParameterSpec build = ParameterSpec.builder((TypeName) parameterizedTypeName, "routes", new Modifier[0]).build();
            ParameterSpec build2 = ParameterSpec.builder((TypeName) parameterizedTypeName2, "atlas", new Modifier[0]).build();
            ParameterSpec build3 = ParameterSpec.builder((TypeName) parameterizedTypeName2, "providers", new Modifier[0]).build();
            String str3 = Consts.METHOD_LOAD_INTO;
            TypeElement typeElement4 = typeElement;
            ClassName className4 = className3;
            MethodSpec.Builder addParameter = MethodSpec.methodBuilder(str3).addAnnotation((Class<?>) Override.class).addModifiers(Modifier.PUBLIC).addParameter(build);
            Iterator<? extends Element> it = set.iterator();
            while (it.hasNext()) {
                Element next = it.next();
                TypeMirror asType5 = next.asType();
                Iterator<? extends Element> it2 = it;
                Route route = (Route) next.getAnnotation(Route.class);
                MethodSpec.Builder builder2 = addParameter;
                if (this.types.isSubtype(asType5, asType)) {
                    Logger logger3 = this.logger;
                    typeMirror = asType;
                    StringBuilder sb2 = new StringBuilder();
                    className = className2;
                    sb2.append(">>> Found activity route: ");
                    sb2.append(asType5.toString());
                    sb2.append(str2);
                    logger3.info(sb2.toString());
                    HashMap hashMap2 = new HashMap();
                    HashMap hashMap3 = new HashMap();
                    Iterator it3 = next.getEnclosedElements().iterator();
                    while (it3.hasNext()) {
                        Element element = (Element) it3.next();
                        Iterator it4 = it3;
                        if (!element.getKind().isField() || element.getAnnotation(Autowired.class) == null) {
                            parameterSpec3 = build3;
                            parameterSpec4 = build2;
                        } else {
                            parameterSpec4 = build2;
                            parameterSpec3 = build3;
                            if (!this.types.isSubtype(element.asType(), this.iProvider)) {
                                Autowired autowired = (Autowired) element.getAnnotation(Autowired.class);
                                String obj = StringUtils.isEmpty(autowired.name()) ? element.getSimpleName().toString() : autowired.name();
                                hashMap2.put(obj, Integer.valueOf(this.typeUtils.typeExchange(element)));
                                hashMap3.put(obj, autowired);
                            }
                        }
                        it3 = it4;
                        build2 = parameterSpec4;
                        build3 = parameterSpec3;
                    }
                    parameterSpec = build3;
                    parameterSpec2 = build2;
                    routeMeta = new RouteMeta(route, next, RouteType.ACTIVITY, hashMap2);
                    routeMeta.setInjectConfig(hashMap3);
                } else {
                    typeMirror = asType;
                    className = className2;
                    parameterSpec = build3;
                    parameterSpec2 = build2;
                    if (this.types.isSubtype(asType5, this.iProvider)) {
                        this.logger.info(">>> Found provider route: " + asType5.toString() + str2);
                        routeMeta = new RouteMeta(route, next, RouteType.PROVIDER, (Map<String, Integer>) null);
                    } else if (this.types.isSubtype(asType5, asType2)) {
                        this.logger.info(">>> Found service route: " + asType5.toString() + str2);
                        routeMeta = new RouteMeta(route, next, RouteType.parse(Consts.SERVICE), (Map<String, Integer>) null);
                    } else if (this.types.isSubtype(asType5, asType3) || this.types.isSubtype(asType5, asType4)) {
                        this.logger.info(">>> Found fragment route: " + asType5.toString() + str2);
                        routeMeta = new RouteMeta(route, next, RouteType.parse(Consts.FRAGMENT), (Map<String, Integer>) null);
                    } else {
                        throw new RuntimeException("ARouter::Compiler >>> Found unsupported class type, type = [" + this.types.toString() + "].");
                    }
                }
                categories(routeMeta);
                it = it2;
                addParameter = builder2;
                asType = typeMirror;
                className2 = className;
                build2 = parameterSpec2;
                build3 = parameterSpec;
            }
            MethodSpec.Builder builder3 = addParameter;
            ClassName className5 = className2;
            ParameterSpec parameterSpec5 = build2;
            MethodSpec.Builder addParameter2 = MethodSpec.methodBuilder(str3).addAnnotation((Class<?>) Override.class).addModifiers(Modifier.PUBLIC).addParameter(build3);
            HashMap hashMap4 = new HashMap();
            Iterator<Map.Entry<String, Set<RouteMeta>>> it5 = this.groupMap.entrySet().iterator();
            while (it5.hasNext()) {
                Map.Entry next2 = it5.next();
                String str4 = (String) next2.getKey();
                ParameterSpec parameterSpec6 = parameterSpec5;
                MethodSpec.Builder addParameter3 = MethodSpec.methodBuilder(str3).addAnnotation((Class<?>) Override.class).addModifiers(Modifier.PUBLIC).addParameter(parameterSpec6);
                ArrayList arrayList = new ArrayList();
                Iterator it6 = ((Set) next2.getValue()).iterator();
                while (it6.hasNext()) {
                    RouteMeta routeMeta2 = (RouteMeta) it6.next();
                    RouteDoc extractDocInfo = extractDocInfo(routeMeta2);
                    ClassName className6 = ClassName.get(routeMeta2.getRawType());
                    Iterator<Map.Entry<String, Set<RouteMeta>>> it7 = it5;
                    Iterator it8 = it6;
                    ParameterSpec parameterSpec7 = parameterSpec6;
                    if (AnonymousClass2.$SwitchMap$com$alibaba$android$arouter$facade$enums$RouteType[routeMeta2.getType().ordinal()] == 1) {
                        Iterator it9 = routeMeta2.getRawType().getInterfaces().iterator();
                        while (it9.hasNext()) {
                            TypeMirror typeMirror2 = (TypeMirror) it9.next();
                            Iterator it10 = it9;
                            extractDocInfo.addPrototype(typeMirror2.toString());
                            String str5 = str3;
                            String str6 = str2;
                            if (this.types.isSameType(typeMirror2, this.iProvider)) {
                                addParameter2.addStatement("providers.put($S, $T.build($T." + routeMeta2.getType() + ", $T.class, $S, $S, null, " + routeMeta2.getPriority() + ", " + routeMeta2.getExtra() + "))", routeMeta2.getRawType().toString(), className5, className4, className6, routeMeta2.getPath(), routeMeta2.getGroup());
                                hashMap = hashMap4;
                            } else {
                                hashMap = hashMap4;
                                if (this.types.isSubtype(typeMirror2, this.iProvider)) {
                                    addParameter2.addStatement("providers.put($S, $T.build($T." + routeMeta2.getType() + ", $T.class, $S, $S, null, " + routeMeta2.getPriority() + ", " + routeMeta2.getExtra() + "))", typeMirror2.toString(), className5, className4, className6, routeMeta2.getPath(), routeMeta2.getGroup());
                                }
                            }
                            it9 = it10;
                            str3 = str5;
                            str2 = str6;
                            hashMap4 = hashMap;
                        }
                    }
                    HashMap hashMap5 = hashMap4;
                    String str7 = str2;
                    String str8 = str3;
                    StringBuilder sb3 = new StringBuilder();
                    Map<String, Integer> paramsType = routeMeta2.getParamsType();
                    Map<String, Autowired> injectConfig = routeMeta2.getInjectConfig();
                    if (MapUtils.isNotEmpty(paramsType)) {
                        ArrayList arrayList2 = new ArrayList();
                        Iterator<Map.Entry<String, Integer>> it11 = paramsType.entrySet().iterator();
                        while (it11.hasNext()) {
                            Map.Entry next3 = it11.next();
                            Iterator<Map.Entry<String, Integer>> it12 = it11;
                            sb3.append("put(\"");
                            sb3.append((String) next3.getKey());
                            sb3.append("\", ");
                            sb3.append(next3.getValue());
                            sb3.append("); ");
                            RouteDoc.Param param = new RouteDoc.Param();
                            MethodSpec.Builder builder4 = addParameter2;
                            Autowired autowired2 = injectConfig.get(next3.getKey());
                            param.setKey((String) next3.getKey());
                            param.setType(TypeKind.values()[((Integer) next3.getValue()).intValue()].name().toLowerCase());
                            param.setDescription(autowired2.desc());
                            param.setRequired(autowired2.required());
                            arrayList2.add(param);
                            it11 = it12;
                            addParameter2 = builder4;
                            injectConfig = injectConfig;
                        }
                        builder = addParameter2;
                        extractDocInfo.setParams(arrayList2);
                    } else {
                        builder = addParameter2;
                    }
                    String sb4 = sb3.toString();
                    StringBuilder sb5 = new StringBuilder();
                    sb5.append("atlas.put($S, $T.build($T.");
                    sb5.append(routeMeta2.getType());
                    sb5.append(", $T.class, $S, $S, ");
                    if (StringUtils.isEmpty(sb4)) {
                        str = null;
                    } else {
                        str = "new java.util.HashMap<String, Integer>(){{" + sb3.toString() + "}}";
                    }
                    sb5.append(str);
                    sb5.append(", ");
                    sb5.append(routeMeta2.getPriority());
                    sb5.append(", ");
                    sb5.append(routeMeta2.getExtra());
                    sb5.append("))");
                    addParameter3.addStatement(sb5.toString(), routeMeta2.getPath(), className5, className4, className6, routeMeta2.getPath().toLowerCase(), routeMeta2.getGroup().toLowerCase());
                    extractDocInfo.setClassName(className6.toString());
                    arrayList.add(extractDocInfo);
                    it5 = it7;
                    it6 = it8;
                    parameterSpec6 = parameterSpec7;
                    str3 = str8;
                    str2 = str7;
                    hashMap4 = hashMap5;
                    addParameter2 = builder;
                }
                MethodSpec.Builder builder5 = addParameter2;
                HashMap hashMap6 = hashMap4;
                String str9 = str3;
                String str10 = Consts.NAME_OF_GROUP + str4;
                JavaFile.builder("com.alibaba.android.arouter.routes", TypeSpec.classBuilder(str10).addJavadoc(Consts.WARNING_TIPS, new Object[0]).addSuperinterface((TypeName) ClassName.get(typeElement4)).addModifiers(Modifier.PUBLIC).addMethod(addParameter3.build()).build()).build().writeTo(this.mFiler);
                this.logger.info(">>> Generated group: " + str4 + "<<<");
                this.rootMap.put(str4, str10);
                HashMap hashMap7 = hashMap6;
                hashMap7.put(str4, arrayList);
                hashMap4 = hashMap7;
                it5 = it5;
                parameterSpec5 = parameterSpec6;
                str2 = str2;
                addParameter2 = builder5;
            }
            MethodSpec.Builder builder6 = addParameter2;
            HashMap hashMap8 = hashMap4;
            String str11 = str2;
            if (MapUtils.isNotEmpty(this.rootMap)) {
                for (Map.Entry next4 : this.rootMap.entrySet()) {
                    builder3.addStatement("routes.put($S, $T.class)", next4.getKey(), ClassName.get("com.alibaba.android.arouter.routes", (String) next4.getValue(), new String[0]));
                }
            }
            MethodSpec.Builder builder7 = builder3;
            if (this.generateDoc) {
                this.docWriter.append(JSON.toJSONString((Object) hashMap8, SerializerFeature.PrettyFormat));
                this.docWriter.flush();
                this.docWriter.close();
            }
            String str12 = "ARouter$$Providers$$" + this.moduleName;
            JavaFile.builder("com.alibaba.android.arouter.routes", TypeSpec.classBuilder(str12).addJavadoc(Consts.WARNING_TIPS, new Object[0]).addSuperinterface((TypeName) ClassName.get(typeElement3)).addModifiers(Modifier.PUBLIC).addMethod(builder6.build()).build()).build().writeTo(this.mFiler);
            Logger logger4 = this.logger;
            StringBuilder sb6 = new StringBuilder();
            sb6.append(">>> Generated provider map, name is ");
            sb6.append(str12);
            String str13 = str11;
            sb6.append(str13);
            logger4.info(sb6.toString());
            String str14 = "ARouter$$Root$$" + this.moduleName;
            JavaFile.builder("com.alibaba.android.arouter.routes", TypeSpec.classBuilder(str14).addJavadoc(Consts.WARNING_TIPS, new Object[0]).addSuperinterface((TypeName) ClassName.get(this.elements.getTypeElement(Consts.ITROUTE_ROOT))).addModifiers(Modifier.PUBLIC).addMethod(builder7.build()).build()).build().writeTo(this.mFiler);
            this.logger.info(">>> Generated root, name is " + str14 + str13);
        }
    }

    /* renamed from: com.alibaba.android.arouter.compiler.processor.RouteProcessor$2  reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$alibaba$android$arouter$facade$enums$RouteType;

        static {
            int[] iArr = new int[RouteType.values().length];
            $SwitchMap$com$alibaba$android$arouter$facade$enums$RouteType = iArr;
            try {
                iArr[RouteType.PROVIDER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    private RouteDoc extractDocInfo(RouteMeta routeMeta) {
        RouteDoc routeDoc = new RouteDoc();
        routeDoc.setGroup(routeMeta.getGroup());
        routeDoc.setPath(routeMeta.getPath());
        routeDoc.setDescription(routeMeta.getName());
        routeDoc.setType(routeMeta.getType().name().toLowerCase());
        routeDoc.setMark(routeMeta.getExtra());
        return routeDoc;
    }

    private void categories(RouteMeta routeMeta) {
        if (routeVerify(routeMeta)) {
            Logger logger2 = this.logger;
            logger2.info(">>> Start categories, group = " + routeMeta.getGroup() + ", path = " + routeMeta.getPath() + " <<<");
            Set set = this.groupMap.get(routeMeta.getGroup());
            if (CollectionUtils.isEmpty(set)) {
                TreeSet treeSet = new TreeSet(new Comparator<RouteMeta>() {
                    public int compare(RouteMeta routeMeta, RouteMeta routeMeta2) {
                        try {
                            return routeMeta.getPath().compareTo(routeMeta2.getPath());
                        } catch (NullPointerException e) {
                            RouteProcessor.this.logger.error((CharSequence) e.getMessage());
                            return 0;
                        }
                    }
                });
                treeSet.add(routeMeta);
                this.groupMap.put(routeMeta.getGroup(), treeSet);
                return;
            }
            set.add(routeMeta);
            return;
        }
        Logger logger3 = this.logger;
        logger3.warning(">>> Route meta verify error, group is " + routeMeta.getGroup() + " <<<");
    }

    private boolean routeVerify(RouteMeta routeMeta) {
        String path = routeMeta.getPath();
        if (StringUtils.isEmpty(path) || !path.startsWith("/")) {
            return false;
        }
        if (!StringUtils.isEmpty(routeMeta.getGroup())) {
            return true;
        }
        try {
            String substring = path.substring(1, path.indexOf("/", 1));
            if (StringUtils.isEmpty(substring)) {
                return false;
            }
            routeMeta.setGroup(substring);
            return true;
        } catch (Exception e) {
            Logger logger2 = this.logger;
            logger2.error((CharSequence) "Failed to extract default group! " + e.getMessage());
            return false;
        }
    }
}
