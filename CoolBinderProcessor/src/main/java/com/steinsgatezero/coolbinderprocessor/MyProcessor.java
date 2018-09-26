package com.steinsgatezero.coolbinderprocessor;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import com.steinsgatezero.coolbinder.IntentKey;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;

@AutoService(Processor.class)
public class MyProcessor extends AbstractProcessor {

    private Messager mMessager;
    //private final TypeName activityClassName = ClassName.get("android.app", "Activity").withoutAnnotations();
    private final TypeName intentClassName = ClassName.get("android.content", "Intent").withoutAnnotations();
    private final TypeName objClassName = ClassName.get("java.lang", "Object").withoutAnnotations();

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        mMessager = processingEnv.getMessager();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        List<InjectInfo> list = new ArrayList<>();
        for (Element element : roundEnv.getElementsAnnotatedWith(IntentKey.class)) {
            if (element.getKind() != ElementKind.FIELD) {
                error(element, "Only fields can be annotated with @%s",
                        IntentKey.class.getSimpleName());
                continue;
            }
            analysisAnnotated(element, list);
        }

        String paramName = "obj";
        MethodSpec.Builder method = MethodSpec.methodBuilder("bind").addModifiers(Modifier.PUBLIC, Modifier.STATIC).addParameter(objClassName, paramName);
        for (int i = 0; i < list.size(); i++) {
            InjectInfo injectInfo = list.get(i);
            TypeName injectedType = createInjectClassFile(injectInfo, paramName);
            TypeName activityName = typeName(injectInfo.getTypeName());
            method.addCode((i == 0 ? "" : " else ") + "if ($N instanceof $T) {\n", paramName, activityName);
            method.addCode("\t$T binder = new $T();\n", injectedType, injectedType);
            method.addCode("\tbinder.bind(($T) $N);\n", activityName, paramName);
            method.addCode("\t}\n");
        }

        createJavaFile("com.steinsgatezero", "MyIntentBinderUtils", method.build());
        return true;
    }

    private TypeName createInjectClassFile(InjectInfo injectInfo, String paramName) {

        ClassName activityName = className(injectInfo.getTypeName());
        ClassName injectedClass = ClassName.get(activityName.packageName(), activityName.simpleName() + "$CoolBinder");

        MethodSpec.Builder method = MethodSpec.methodBuilder("bind")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .addParameter(activityName, paramName);
        if (injectInfo.getType() == IntentKey.TYPE_ACTIVITY) {
            method.addStatement("$T intent = $N.getIntent()", intentClassName, paramName);
        } else {
            method.addStatement("$T intent = $N.getActivity().getIntent()", intentClassName, paramName);
        }
        for (int i = 0; i < injectInfo.getList().size(); i++) {
            FieldInfo fieldInfo = injectInfo.getList().get(i);
            TypeName fieldTypeName = typeName(fieldInfo.getFieldTypeName());
            method.addCode("if (intent.hasExtra($S)) {\n", fieldInfo.getIntentName());
            method.addCode("\t$N.$N = ($T) intent.getSerializableExtra($S);\n", paramName, fieldInfo.getFieldName(), fieldTypeName, fieldInfo.getIntentName());
            if (!fieldTypeName.toString().contains("java.lang")) {
                method.addCode("\tif ($N.$N ==null){\n", paramName, fieldInfo.getFieldName());
                method.addCode("\t$N.$N = ($T) intent.getParcelableExtra($S);\n", paramName, fieldInfo.getFieldName(), fieldTypeName, fieldInfo.getIntentName());
                method.addCode("\t}\n");
            }
            method.addCode("}\n");
        }

        createJavaFile(injectedClass.packageName(), injectedClass.simpleName(), method.build());

        return injectedClass;
    }

    private void createJavaFile(String pkg, String classShortName, MethodSpec... method) {
        TypeSpec.Builder builder = TypeSpec.classBuilder(classShortName)
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL);
        for (MethodSpec spec : method) {
            builder.addMethod(spec);
        }
        TypeSpec clazzType = builder.build();

        try {
            JavaFile javaFile = JavaFile.builder(pkg, clazzType)
                    .addFileComment(" This codes are generated automatically. Do not modify!")
                    .indent("    ")
                    .build();
            // write to file
            javaFile.writeTo(processingEnv.getFiler());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private TypeName typeName(String className) {
        return className(className).withoutAnnotations();
    }

    private ClassName className(String className) {

        if (className.indexOf(".") <= 0) {
            switch (className) {
                case "byte":
                    return ClassName.get("java.lang", "Byte");
                case "short":
                    return ClassName.get("java.lang", "Short");
                case "int":
                    return ClassName.get("java.lang", "Integer");
                case "long":
                    return ClassName.get("java.lang", "Long");
                case "float":
                    return ClassName.get("java.lang", "Float");
                case "double":
                    return ClassName.get("java.lang", "Double");
                case "boolean":
                    return ClassName.get("java.lang", "Boolean");
                case "char":
                    return ClassName.get("java.lang", "Character");
                default:
            }
        }

        String packageD = className.substring(0, className.lastIndexOf('.'));
        String name = className.substring(className.lastIndexOf('.') + 1);
        return ClassName.get(packageD, name);
    }

    private void analysisAnnotated(Element classElement, List<InjectInfo> list) {
        TypeElement classType = (TypeElement) classElement.getEnclosingElement();
        String typeName = classType.getQualifiedName().toString();
        InjectInfo injectInfo = new InjectInfo();
        injectInfo.setTypeName(typeName);
        List<FieldInfo> infoList = new ArrayList<>();
        FieldInfo info = new FieldInfo();
        info.setFieldName(classElement.getSimpleName().toString());
        info.setFieldTypeName(classElement.asType().toString());
        info.setIntentName(classElement.getAnnotation(IntentKey.class).value());
        infoList.add(info);
        injectInfo.setList(infoList);
        injectInfo.setType(classElement.getAnnotation(IntentKey.class).intentType());
        if (!list.contains(injectInfo)) {
            //processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "1" + injectInfo.getTypeName());
            list.add(injectInfo);
        } else {
            //processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "2" + injectInfo.getTypeName());
            list.get(list.indexOf(injectInfo)).getList().add(info);
        }

    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> annotations = new LinkedHashSet<>();
        annotations.add(IntentKey.class.getCanonicalName());
        return annotations;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    private void error(Element e, String msg, Object... args) {
        mMessager.printMessage(
                Diagnostic.Kind.ERROR,
                String.format(msg, args),
                e);
    }
}
