package com.alibaba.android.arouter.compiler.processor;

import com.alibaba.android.arouter.compiler.utils.Consts;
import com.alibaba.android.arouter.compiler.utils.Logger;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import com.squareup.javapoet.WildcardTypeName;
import java.io.IOException;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
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
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

@SupportedAnnotationTypes({"com.alibaba.android.arouter.facade.annotation.Interceptor"})
@SupportedOptions({"AROUTER_MODULE_NAME"})
@SupportedSourceVersion(SourceVersion.RELEASE_7)
public class InterceptorProcessor extends AbstractProcessor {
    private Elements elementUtil;
    private TypeMirror iInterceptor = null;
    private Map<Integer, Element> interceptors = new TreeMap();
    private Logger logger;
    private Filer mFiler;
    private String moduleName = null;

    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        InterceptorProcessor.super.init(processingEnvironment);
        this.mFiler = processingEnvironment.getFiler();
        this.elementUtil = processingEnvironment.getElementUtils();
        this.logger = new Logger(processingEnvironment.getMessager());
        Map options = processingEnvironment.getOptions();
        if (MapUtils.isNotEmpty(options)) {
            this.moduleName = (String) options.get(Consts.KEY_MODULE_NAME);
        }
        if (StringUtils.isNotEmpty(this.moduleName)) {
            this.moduleName = this.moduleName.replaceAll("[^0-9a-zA-Z_]+", "");
            Logger logger2 = this.logger;
            logger2.info("The user has configuration the module name, it was [" + this.moduleName + "]");
            this.iInterceptor = this.elementUtil.getTypeElement(Consts.IINTERCEPTOR).asType();
            this.logger.info(">>> InterceptorProcessor init. <<<");
        } else {
            this.logger.error((CharSequence) Consts.NO_MODULE_NAME_TIPS);
            throw new RuntimeException("ARouter::Compiler >>> No module name, for more information, look at gradle log.");
        }
    }

    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        if (!CollectionUtils.isNotEmpty(set)) {
            return false;
        }
        try {
            parseInterceptors(roundEnvironment.getElementsAnnotatedWith(Interceptor.class));
            return true;
        } catch (Exception e) {
            this.logger.error((Throwable) e);
            return true;
        }
    }

    private void parseInterceptors(Set<? extends Element> set) throws IOException {
        if (CollectionUtils.isNotEmpty(set)) {
            Logger logger2 = this.logger;
            logger2.info(">>> Found interceptors, size is " + set.size() + " <<<");
            for (Element next : set) {
                if (verify(next)) {
                    Logger logger3 = this.logger;
                    logger3.info("A interceptor verify over, its " + next.asType());
                    Interceptor interceptor = (Interceptor) next.getAnnotation(Interceptor.class);
                    Element element = this.interceptors.get(Integer.valueOf(interceptor.priority()));
                    if (element == null) {
                        this.interceptors.put(Integer.valueOf(interceptor.priority()), next);
                    } else {
                        throw new IllegalArgumentException(String.format(Locale.getDefault(), "More than one interceptors use same priority [%d], They are [%s] and [%s].", new Object[]{Integer.valueOf(interceptor.priority()), element.getSimpleName(), next.getSimpleName()}));
                    }
                } else {
                    Logger logger4 = this.logger;
                    logger4.error((CharSequence) "A interceptor verify failed, its " + next.asType());
                }
            }
            TypeElement typeElement = this.elementUtil.getTypeElement(Consts.IINTERCEPTOR);
            TypeElement typeElement2 = this.elementUtil.getTypeElement(Consts.IINTERCEPTOR_GROUP);
            MethodSpec.Builder addParameter = MethodSpec.methodBuilder(Consts.METHOD_LOAD_INTO).addAnnotation((Class<?>) Override.class).addModifiers(Modifier.PUBLIC).addParameter(ParameterSpec.builder((TypeName) ParameterizedTypeName.get(ClassName.get((Class<?>) Map.class), ClassName.get((Class<?>) Integer.class), ParameterizedTypeName.get(ClassName.get((Class<?>) Class.class), WildcardTypeName.subtypeOf((TypeName) ClassName.get(typeElement)))), "interceptors", new Modifier[0]).build());
            Map<Integer, Element> map = this.interceptors;
            if (map != null && map.size() > 0) {
                for (Map.Entry next2 : this.interceptors.entrySet()) {
                    addParameter.addStatement("interceptors.put(" + next2.getKey() + ", $T.class)", ClassName.get((TypeElement) next2.getValue()));
                }
            }
            JavaFile.builder("com.alibaba.android.arouter.routes", TypeSpec.classBuilder("ARouter$$Interceptors$$" + this.moduleName).addModifiers(Modifier.PUBLIC).addJavadoc(Consts.WARNING_TIPS, new Object[0]).addMethod(addParameter.build()).addSuperinterface((TypeName) ClassName.get(typeElement2)).build()).build().writeTo(this.mFiler);
            this.logger.info(">>> Interceptor group write over. <<<");
        }
    }

    private boolean verify(Element element) {
        return ((Interceptor) element.getAnnotation(Interceptor.class)) != null && ((TypeElement) element).getInterfaces().contains(this.iInterceptor);
    }
}
