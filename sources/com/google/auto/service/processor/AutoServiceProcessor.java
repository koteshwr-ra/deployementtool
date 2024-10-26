package com.google.auto.service.processor;

import com.alibaba.android.arouter.utils.Consts;
import com.google.auto.common.MoreElements;
import com.google.auto.service.AutoService;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedOptions;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.Element;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.tools.Diagnostic;
import javax.tools.FileObject;
import javax.tools.StandardLocation;

@SupportedOptions({"debug", "verify"})
public class AutoServiceProcessor extends AbstractProcessor {
    private Multimap<String, String> providers = HashMultimap.create();

    public ImmutableSet<String> getSupportedAnnotationTypes() {
        return ImmutableSet.of(AutoService.class.getName());
    }

    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        try {
            return processImpl(set, roundEnvironment);
        } catch (Exception e) {
            StringWriter stringWriter = new StringWriter();
            e.printStackTrace(new PrintWriter(stringWriter));
            fatalError(stringWriter.toString());
            return true;
        }
    }

    private boolean processImpl(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        if (roundEnvironment.processingOver()) {
            generateConfigFiles();
            return true;
        }
        processAnnotations(set, roundEnvironment);
        return true;
    }

    private void processAnnotations(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        Set<TypeElement> elementsAnnotatedWith = roundEnvironment.getElementsAnnotatedWith(AutoService.class);
        log(set.toString());
        log(elementsAnnotatedWith.toString());
        for (TypeElement typeElement : elementsAnnotatedWith) {
            TypeElement typeElement2 = typeElement;
            AnnotationMirror annotationMirror = MoreElements.getAnnotationMirror(typeElement, AutoService.class).get();
            TypeElement asElement = getProviderInterface(annotationMirror).asElement();
            log("provider interface: " + asElement.getQualifiedName());
            log("provider implementer: " + typeElement2.getQualifiedName());
            if (!checkImplementer(typeElement2, asElement)) {
                error("ServiceProviders must implement their service provider interface. " + typeElement2.getQualifiedName() + " does not implement " + asElement.getQualifiedName(), typeElement, annotationMirror);
            }
            String binaryName = getBinaryName(asElement);
            String binaryName2 = getBinaryName(typeElement2);
            log("provider interface binary name: " + binaryName);
            log("provider implementer binary name: " + binaryName2);
            this.providers.put(binaryName, binaryName2);
        }
    }

    private void generateConfigFiles() {
        Filer filer = this.processingEnv.getFiler();
        for (String next : this.providers.keySet()) {
            String str = "META-INF/services/" + next;
            log("Working on resource file: " + str);
            try {
                TreeSet newTreeSet = Sets.newTreeSet();
                try {
                    FileObject resource = filer.getResource(StandardLocation.CLASS_OUTPUT, "", str);
                    log("Looking for existing resource file at " + resource.toUri());
                    Set<String> readServiceFile = ServicesFiles.readServiceFile(resource.openInputStream());
                    log("Existing service entries: " + readServiceFile);
                    newTreeSet.addAll(readServiceFile);
                } catch (IOException unused) {
                    log("Resource file did not already exist.");
                }
                HashSet hashSet = new HashSet(this.providers.get(next));
                if (newTreeSet.containsAll(hashSet)) {
                    log("No new service entries being added.");
                    return;
                }
                newTreeSet.addAll(hashSet);
                log("New service file contents: " + newTreeSet);
                FileObject createResource = filer.createResource(StandardLocation.CLASS_OUTPUT, "", str, new Element[0]);
                OutputStream openOutputStream = createResource.openOutputStream();
                ServicesFiles.writeServiceFile(newTreeSet, openOutputStream);
                openOutputStream.close();
                log("Wrote to: " + createResource.toUri());
            } catch (IOException e) {
                fatalError("Unable to create " + str + ", " + e);
                return;
            }
        }
    }

    private boolean checkImplementer(TypeElement typeElement, TypeElement typeElement2) {
        String str = (String) this.processingEnv.getOptions().get("verify");
        if (str == null || !Boolean.valueOf(str).booleanValue()) {
            return true;
        }
        return this.processingEnv.getTypeUtils().isSubtype(typeElement.asType(), typeElement2.asType());
    }

    private String getBinaryName(TypeElement typeElement) {
        return getBinaryNameImpl(typeElement, typeElement.getSimpleName().toString());
    }

    private String getBinaryNameImpl(TypeElement typeElement, String str) {
        PackageElement enclosingElement = typeElement.getEnclosingElement();
        if (enclosingElement instanceof PackageElement) {
            PackageElement packageElement = enclosingElement;
            if (packageElement.isUnnamed()) {
                return str;
            }
            return packageElement.getQualifiedName() + Consts.DOT + str;
        }
        TypeElement typeElement2 = (TypeElement) enclosingElement;
        return getBinaryNameImpl(typeElement2, typeElement2.getSimpleName() + "$" + str);
    }

    private DeclaredType getProviderInterface(AnnotationMirror annotationMirror) {
        Map elementValues = annotationMirror.getElementValues();
        log("annotation values: " + elementValues);
        return (DeclaredType) ((AnnotationValue) elementValues.values().iterator().next()).getValue();
    }

    private void log(String str) {
        if (this.processingEnv.getOptions().containsKey("debug")) {
            this.processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, str);
        }
    }

    private void error(String str, Element element, AnnotationMirror annotationMirror) {
        this.processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, str, element, annotationMirror);
    }

    private void fatalError(String str) {
        Messager messager = this.processingEnv.getMessager();
        Diagnostic.Kind kind = Diagnostic.Kind.ERROR;
        messager.printMessage(kind, "FATAL ERROR: " + str);
    }
}
