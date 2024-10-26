package com.alibaba.fastjson.serializer;

import com.alibaba.android.arouter.utils.Consts;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.annotation.JSONType;
import com.alibaba.fastjson.asm.ClassWriter;
import com.alibaba.fastjson.asm.FieldWriter;
import com.alibaba.fastjson.asm.Label;
import com.alibaba.fastjson.asm.MethodVisitor;
import com.alibaba.fastjson.asm.MethodWriter;
import com.alibaba.fastjson.asm.Opcodes;
import com.alibaba.fastjson.asm.Type;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.util.ASMClassLoader;
import com.alibaba.fastjson.util.ASMUtils;
import com.alibaba.fastjson.util.FieldInfo;
import com.alibaba.fastjson.util.TypeUtils;
import com.limpoxe.support.servicemanager.ServiceProvider;
import com.tencent.smtt.sdk.TbsListener;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import okhttp3.HttpUrl;
import org.apache.commons.lang3.ClassUtils;

public class ASMSerializerFactory implements Opcodes {
    static final String JSONSerializer = ASMUtils.type(JSONSerializer.class);
    static final String JavaBeanSerializer = ASMUtils.type(JavaBeanSerializer.class);
    static final String JavaBeanSerializer_desc = ("L" + ASMUtils.type(JavaBeanSerializer.class) + ";");
    static final String ObjectSerializer = ASMUtils.type(ObjectSerializer.class);
    static final String ObjectSerializer_desc = ("L" + ObjectSerializer + ";");
    static final String SerialContext_desc = ASMUtils.desc((Class<?>) SerialContext.class);
    static final String SerializeFilterable_desc = ASMUtils.desc((Class<?>) SerializeFilterable.class);
    static final String SerializeWriter = ASMUtils.type(SerializeWriter.class);
    static final String SerializeWriter_desc = ("L" + SerializeWriter + ";");
    protected final ASMClassLoader classLoader = new ASMClassLoader();
    private final AtomicLong seed = new AtomicLong();

    static class Context {
        static final int features = 5;
        static int fieldName = 6;
        static final int obj = 2;
        static int original = 7;
        static final int paramFieldName = 3;
        static final int paramFieldType = 4;
        static int processValue = 8;
        static final int serializer = 1;
        /* access modifiers changed from: private */
        public final SerializeBeanInfo beanInfo;
        /* access modifiers changed from: private */
        public final String className;
        private final FieldInfo[] getters;
        /* access modifiers changed from: private */
        public final boolean nonContext;
        /* access modifiers changed from: private */
        public int variantIndex = 9;
        private Map<String, Integer> variants = new HashMap();
        /* access modifiers changed from: private */
        public final boolean writeDirect;

        public Context(FieldInfo[] fieldInfoArr, SerializeBeanInfo serializeBeanInfo, String str, boolean z, boolean z2) {
            this.getters = fieldInfoArr;
            this.className = str;
            this.beanInfo = serializeBeanInfo;
            this.writeDirect = z;
            this.nonContext = z2 || serializeBeanInfo.beanType.isEnum();
        }

        public int var(String str) {
            if (this.variants.get(str) == null) {
                Map<String, Integer> map = this.variants;
                int i = this.variantIndex;
                this.variantIndex = i + 1;
                map.put(str, Integer.valueOf(i));
            }
            return this.variants.get(str).intValue();
        }

        public int var(String str, int i) {
            if (this.variants.get(str) == null) {
                this.variants.put(str, Integer.valueOf(this.variantIndex));
                this.variantIndex += i;
            }
            return this.variants.get(str).intValue();
        }

        public int getFieldOrinal(String str) {
            int length = this.getters.length;
            for (int i = 0; i < length; i++) {
                if (this.getters[i].name.equals(str)) {
                    return i;
                }
            }
            return -1;
        }
    }

    public JavaBeanSerializer createJavaBeanSerializer(SerializeBeanInfo serializeBeanInfo) throws Exception {
        String str;
        String str2;
        boolean z;
        FieldInfo[] fieldInfoArr;
        String str3;
        boolean z2;
        boolean z3;
        boolean z4;
        String str4;
        boolean z5;
        String str5;
        boolean z6;
        JSONType jSONType;
        int i;
        ClassWriter classWriter;
        int i2;
        SerializeBeanInfo serializeBeanInfo2 = serializeBeanInfo;
        Class<ASMSerializerFactory> cls = ASMSerializerFactory.class;
        Class<?> cls2 = serializeBeanInfo2.beanType;
        if (!cls2.isPrimitive()) {
            JSONType jSONType2 = (JSONType) TypeUtils.getAnnotation(cls2, JSONType.class);
            FieldInfo[] fieldInfoArr2 = serializeBeanInfo2.fields;
            for (FieldInfo fieldInfo : fieldInfoArr2) {
                if (fieldInfo.field == null && fieldInfo.method != null && fieldInfo.method.getDeclaringClass().isInterface()) {
                    return new JavaBeanSerializer(serializeBeanInfo2);
                }
            }
            FieldInfo[] fieldInfoArr3 = serializeBeanInfo2.sortedFields;
            boolean z7 = serializeBeanInfo2.sortedFields == serializeBeanInfo2.fields;
            if (fieldInfoArr3.length > 256) {
                return new JavaBeanSerializer(serializeBeanInfo2);
            }
            for (FieldInfo member : fieldInfoArr3) {
                if (!ASMUtils.checkName(member.getMember().getName())) {
                    return new JavaBeanSerializer(serializeBeanInfo2);
                }
            }
            String str6 = "ASMSerializer_" + this.seed.incrementAndGet() + "_" + cls2.getSimpleName();
            Package packageR = cls.getPackage();
            if (packageR != null) {
                String name = packageR.getName();
                str2 = name + Consts.DOT + str6;
                str = name.replace(ClassUtils.PACKAGE_SEPARATOR_CHAR, '/') + "/" + str6;
            } else {
                str2 = str6;
                str = str2;
            }
            cls.getPackage().getName();
            ClassWriter classWriter2 = new ClassWriter();
            classWriter2.visit(49, 33, str, JavaBeanSerializer, new String[]{ObjectSerializer});
            int length = fieldInfoArr3.length;
            int i3 = 0;
            while (i3 < length) {
                FieldInfo fieldInfo2 = fieldInfoArr3[i3];
                if (fieldInfo2.fieldClass.isPrimitive() || fieldInfo2.fieldClass == String.class) {
                    i2 = length;
                } else {
                    i2 = length;
                    new FieldWriter(classWriter2, 1, fieldInfo2.name + "_asm_fieldType", "Ljava/lang/reflect/Type;").visitEnd();
                    if (List.class.isAssignableFrom(fieldInfo2.fieldClass)) {
                        new FieldWriter(classWriter2, 1, fieldInfo2.name + "_asm_list_item_ser_", ObjectSerializer_desc).visitEnd();
                    }
                    new FieldWriter(classWriter2, 1, fieldInfo2.name + "_asm_ser_", ObjectSerializer_desc).visitEnd();
                }
                i3++;
                length = i2;
            }
            MethodWriter methodWriter = new MethodWriter(classWriter2, 1, "<init>", "(" + ASMUtils.desc((Class<?>) SerializeBeanInfo.class) + ")V", (String) null, (String[]) null);
            methodWriter.visitVarInsn(25, 0);
            methodWriter.visitVarInsn(25, 1);
            methodWriter.visitMethodInsn(Opcodes.INVOKESPECIAL, JavaBeanSerializer, "<init>", "(" + ASMUtils.desc((Class<?>) SerializeBeanInfo.class) + ")V");
            int i4 = 0;
            while (i4 < fieldInfoArr3.length) {
                FieldInfo fieldInfo3 = fieldInfoArr3[i4];
                if (fieldInfo3.fieldClass.isPrimitive() || fieldInfo3.fieldClass == String.class) {
                    classWriter = classWriter2;
                } else {
                    methodWriter.visitVarInsn(25, 0);
                    if (fieldInfo3.method != null) {
                        methodWriter.visitLdcInsn(Type.getType(ASMUtils.desc(fieldInfo3.declaringClass)));
                        methodWriter.visitLdcInsn(fieldInfo3.method.getName());
                        classWriter = classWriter2;
                        methodWriter.visitMethodInsn(184, ASMUtils.type(ASMUtils.class), "getMethodType", "(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/reflect/Type;");
                    } else {
                        classWriter = classWriter2;
                        methodWriter.visitVarInsn(25, 0);
                        methodWriter.visitLdcInsn(Integer.valueOf(i4));
                        methodWriter.visitMethodInsn(Opcodes.INVOKESPECIAL, JavaBeanSerializer, "getFieldType", "(I)Ljava/lang/reflect/Type;");
                    }
                    methodWriter.visitFieldInsn(Opcodes.PUTFIELD, str, fieldInfo3.name + "_asm_fieldType", "Ljava/lang/reflect/Type;");
                }
                i4++;
                classWriter2 = classWriter;
            }
            ClassWriter classWriter3 = classWriter2;
            methodWriter.visitInsn(177);
            methodWriter.visitMaxs(4, 4);
            methodWriter.visitEnd();
            if (jSONType2 != null) {
                SerializerFeature[] serialzeFeatures = jSONType2.serialzeFeatures();
                int length2 = serialzeFeatures.length;
                int i5 = 0;
                while (true) {
                    if (i5 >= length2) {
                        break;
                    } else if (serialzeFeatures[i5] == SerializerFeature.DisableCircularReferenceDetect) {
                        z = true;
                        break;
                    } else {
                        i5++;
                    }
                }
            }
            z = false;
            int i6 = 0;
            while (true) {
                fieldInfoArr = fieldInfoArr2;
                if (i6 >= 3) {
                    break;
                }
                if (i6 == 0) {
                    str4 = "write";
                    z4 = z;
                    z5 = true;
                } else if (i6 == 1) {
                    str4 = "writeNormal";
                    z4 = z;
                    z5 = false;
                } else {
                    str4 = "writeDirectNonContext";
                    z5 = true;
                    z4 = true;
                }
                Context context = r1;
                String str7 = "entity";
                ClassWriter classWriter4 = classWriter3;
                String str8 = str2;
                String str9 = str;
                Context context2 = new Context(fieldInfoArr3, serializeBeanInfo, str, z5, z4);
                int i7 = i6;
                MethodWriter methodWriter2 = new MethodWriter(classWriter3, 1, str4, "(L" + JSONSerializer + ";Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V", (String) null, new String[]{"java/io/IOException"});
                Label label = new Label();
                methodWriter2.visitVarInsn(25, 2);
                methodWriter2.visitJumpInsn(Opcodes.IFNONNULL, label);
                methodWriter2.visitVarInsn(25, 1);
                methodWriter2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, JSONSerializer, "writeNull", "()V");
                methodWriter2.visitInsn(177);
                methodWriter2.visitLabel(label);
                methodWriter2.visitVarInsn(25, 1);
                methodWriter2.visitFieldInsn(180, JSONSerializer, "out", SerializeWriter_desc);
                Context context3 = context;
                methodWriter2.visitVarInsn(58, context3.var("out"));
                if (z7 || context3.writeDirect || (jSONType2 != null && !jSONType2.alphabetic())) {
                    str5 = str9;
                } else {
                    Label label2 = new Label();
                    methodWriter2.visitVarInsn(25, context3.var("out"));
                    methodWriter2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, SerializeWriter, "isSortField", "()Z");
                    methodWriter2.visitJumpInsn(Opcodes.IFNE, label2);
                    methodWriter2.visitVarInsn(25, 0);
                    methodWriter2.visitVarInsn(25, 1);
                    methodWriter2.visitVarInsn(25, 2);
                    methodWriter2.visitVarInsn(25, 3);
                    methodWriter2.visitVarInsn(25, 4);
                    methodWriter2.visitVarInsn(21, 5);
                    str5 = str9;
                    methodWriter2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str5, "writeUnsorted", "(L" + JSONSerializer + ";Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
                    methodWriter2.visitInsn(177);
                    methodWriter2.visitLabel(label2);
                }
                if (!context3.writeDirect || z4) {
                    jSONType = jSONType2;
                    z6 = z7;
                    i = 177;
                } else {
                    Label label3 = new Label();
                    Label label4 = new Label();
                    methodWriter2.visitVarInsn(25, 0);
                    methodWriter2.visitVarInsn(25, 1);
                    String str10 = JavaBeanSerializer;
                    StringBuilder sb = new StringBuilder();
                    sb.append("(L");
                    jSONType = jSONType2;
                    sb.append(JSONSerializer);
                    sb.append(";)Z");
                    z6 = z7;
                    methodWriter2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str10, "writeDirect", sb.toString());
                    methodWriter2.visitJumpInsn(Opcodes.IFNE, label4);
                    methodWriter2.visitVarInsn(25, 0);
                    methodWriter2.visitVarInsn(25, 1);
                    methodWriter2.visitVarInsn(25, 2);
                    methodWriter2.visitVarInsn(25, 3);
                    methodWriter2.visitVarInsn(25, 4);
                    methodWriter2.visitVarInsn(21, 5);
                    methodWriter2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str5, "writeNormal", "(L" + JSONSerializer + ";Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
                    methodWriter2.visitInsn(177);
                    methodWriter2.visitLabel(label4);
                    methodWriter2.visitVarInsn(25, context3.var("out"));
                    methodWriter2.visitLdcInsn(Integer.valueOf(SerializerFeature.DisableCircularReferenceDetect.mask));
                    methodWriter2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, SerializeWriter, "isEnabled", "(I)Z");
                    methodWriter2.visitJumpInsn(Opcodes.IFEQ, label3);
                    methodWriter2.visitVarInsn(25, 0);
                    methodWriter2.visitVarInsn(25, 1);
                    methodWriter2.visitVarInsn(25, 2);
                    methodWriter2.visitVarInsn(25, 3);
                    methodWriter2.visitVarInsn(25, 4);
                    methodWriter2.visitVarInsn(21, 5);
                    methodWriter2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str5, "writeDirectNonContext", "(L" + JSONSerializer + ";Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
                    i = 177;
                    methodWriter2.visitInsn(177);
                    methodWriter2.visitLabel(label3);
                }
                methodWriter2.visitVarInsn(25, 2);
                methodWriter2.visitTypeInsn(Opcodes.CHECKCAST, ASMUtils.type(cls2));
                methodWriter2.visitVarInsn(58, context3.var(str7));
                generateWriteMethod(cls2, methodWriter2, fieldInfoArr3, context3);
                methodWriter2.visitInsn(i);
                methodWriter2.visitMaxs(7, context3.variantIndex + 2);
                methodWriter2.visitEnd();
                i6 = i7 + 1;
                SerializeBeanInfo serializeBeanInfo3 = serializeBeanInfo;
                str = str5;
                jSONType2 = jSONType;
                z7 = z6;
                fieldInfoArr2 = fieldInfoArr;
                classWriter3 = classWriter4;
                str2 = str8;
            }
            String str11 = str2;
            String str12 = str;
            boolean z8 = z7;
            ClassWriter classWriter5 = classWriter3;
            String str13 = "entity";
            if (!z8) {
                Context context4 = r1;
                Context context5 = new Context(fieldInfoArr3, serializeBeanInfo, str12, false, z);
                MethodWriter methodWriter3 = new MethodWriter(classWriter5, 1, "writeUnsorted", "(L" + JSONSerializer + ";Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V", (String) null, new String[]{"java/io/IOException"});
                methodWriter3.visitVarInsn(25, 1);
                methodWriter3.visitFieldInsn(180, JSONSerializer, "out", SerializeWriter_desc);
                methodWriter3.visitVarInsn(58, context4.var("out"));
                methodWriter3.visitVarInsn(25, 2);
                methodWriter3.visitTypeInsn(Opcodes.CHECKCAST, ASMUtils.type(cls2));
                methodWriter3.visitVarInsn(58, context4.var(str13));
                generateWriteMethod(cls2, methodWriter3, fieldInfoArr, context4);
                methodWriter3.visitInsn(177);
                methodWriter3.visitMaxs(7, context4.variantIndex + 2);
                methodWriter3.visitEnd();
            }
            for (int i8 = 0; i8 < 3; i8++) {
                if (i8 == 0) {
                    str3 = "writeAsArray";
                    z2 = z;
                    z3 = true;
                } else if (i8 == 1) {
                    str3 = "writeAsArrayNormal";
                    z2 = z;
                    z3 = false;
                } else {
                    str3 = "writeAsArrayNonContext";
                    z3 = true;
                    z2 = true;
                }
                Context context6 = r1;
                Context context7 = new Context(fieldInfoArr3, serializeBeanInfo, str12, z3, z2);
                MethodWriter methodWriter4 = new MethodWriter(classWriter5, 1, str3, "(L" + JSONSerializer + ";Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V", (String) null, new String[]{"java/io/IOException"});
                methodWriter4.visitVarInsn(25, 1);
                methodWriter4.visitFieldInsn(180, JSONSerializer, "out", SerializeWriter_desc);
                Context context8 = context6;
                methodWriter4.visitVarInsn(58, context8.var("out"));
                methodWriter4.visitVarInsn(25, 2);
                methodWriter4.visitTypeInsn(Opcodes.CHECKCAST, ASMUtils.type(cls2));
                methodWriter4.visitVarInsn(58, context8.var(str13));
                generateWriteAsArray(cls2, methodWriter4, fieldInfoArr3, context8);
                methodWriter4.visitInsn(177);
                methodWriter4.visitMaxs(7, context8.variantIndex + 2);
                methodWriter4.visitEnd();
            }
            byte[] byteArray = classWriter5.toByteArray();
            return (JavaBeanSerializer) this.classLoader.defineClassPublic(str11, byteArray, 0, byteArray.length).getConstructor(new Class[]{SerializeBeanInfo.class}).newInstance(new Object[]{serializeBeanInfo});
        }
        throw new JSONException("unsupportd class " + cls2.getName());
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v81, resolved type: java.lang.Class<java.lang.Object>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v82, resolved type: java.lang.Class<java.lang.Object>} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void generateWriteAsArray(java.lang.Class<?> r30, com.alibaba.fastjson.asm.MethodVisitor r31, com.alibaba.fastjson.util.FieldInfo[] r32, com.alibaba.fastjson.serializer.ASMSerializerFactory.Context r33) throws java.lang.Exception {
        /*
            r29 = this;
            r0 = r29
            r1 = r31
            r2 = r32
            r3 = r33
            com.alibaba.fastjson.asm.Label r4 = new com.alibaba.fastjson.asm.Label
            r4.<init>()
            r5 = 25
            r6 = 1
            r1.visitVarInsn(r5, r6)
            r7 = 0
            r1.visitVarInsn(r5, r7)
            java.lang.String r8 = JSONSerializer
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r10 = "("
            r9.append(r10)
            java.lang.String r10 = SerializeFilterable_desc
            r9.append(r10)
            java.lang.String r10 = ")Z"
            r9.append(r10)
            java.lang.String r9 = r9.toString()
            r10 = 182(0xb6, float:2.55E-43)
            java.lang.String r11 = "hasPropertyFilters"
            r1.visitMethodInsn(r10, r8, r11, r9)
            r8 = 154(0x9a, float:2.16E-43)
            r1.visitJumpInsn(r8, r4)
            r1.visitVarInsn(r5, r7)
            r1.visitVarInsn(r5, r6)
            r8 = 2
            r1.visitVarInsn(r5, r8)
            r8 = 3
            r1.visitVarInsn(r5, r8)
            r8 = 4
            r1.visitVarInsn(r5, r8)
            r8 = 21
            r9 = 5
            r1.visitVarInsn(r8, r9)
            java.lang.String r9 = JavaBeanSerializer
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            java.lang.String r12 = "(L"
            r11.append(r12)
            java.lang.String r13 = JSONSerializer
            r11.append(r13)
            java.lang.String r13 = ";Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V"
            r11.append(r13)
            java.lang.String r11 = r11.toString()
            r14 = 183(0xb7, float:2.56E-43)
            java.lang.String r15 = "writeNoneASM"
            r1.visitMethodInsn(r14, r9, r15, r11)
            r9 = 177(0xb1, float:2.48E-43)
            r1.visitInsn(r9)
            r1.visitLabel(r4)
            java.lang.String r4 = "out"
            int r9 = r3.var(r4)
            r1.visitVarInsn(r5, r9)
            r9 = 16
            r11 = 91
            r1.visitVarInsn(r9, r11)
            java.lang.String r11 = SerializeWriter
            java.lang.String r14 = "write"
            java.lang.String r15 = "(I)V"
            r1.visitMethodInsn(r10, r11, r14, r15)
            int r11 = r2.length
            if (r11 != 0) goto L_0x00ac
            int r2 = r3.var(r4)
            r1.visitVarInsn(r5, r2)
            r2 = 93
            r1.visitVarInsn(r9, r2)
            java.lang.String r2 = SerializeWriter
            r1.visitMethodInsn(r10, r2, r14, r15)
            return
        L_0x00ac:
            r6 = 0
        L_0x00ad:
            if (r6 >= r11) goto L_0x08a1
            int r8 = r11 + -1
            if (r6 != r8) goto L_0x00b6
            r8 = 93
            goto L_0x00b8
        L_0x00b6:
            r8 = 44
        L_0x00b8:
            r7 = r2[r6]
            java.lang.Class<?> r9 = r7.fieldClass
            java.lang.String r10 = r7.name
            r1.visitLdcInsn(r10)
            int r10 = com.alibaba.fastjson.serializer.ASMSerializerFactory.Context.fieldName
            r5 = 58
            r1.visitVarInsn(r5, r10)
            java.lang.Class r10 = java.lang.Byte.TYPE
            r5 = 89
            if (r9 == r10) goto L_0x085b
            java.lang.Class r10 = java.lang.Short.TYPE
            if (r9 == r10) goto L_0x085b
            java.lang.Class r10 = java.lang.Integer.TYPE
            if (r9 != r10) goto L_0x00d8
            goto L_0x085b
        L_0x00d8:
            java.lang.Class r10 = java.lang.Long.TYPE
            if (r9 != r10) goto L_0x0112
            int r9 = r3.var(r4)
            r10 = 25
            r1.visitVarInsn(r10, r9)
            r1.visitInsn(r5)
            r0._get(r1, r3, r7)
            java.lang.String r5 = SerializeWriter
            java.lang.String r7 = "writeLong"
            java.lang.String r9 = "(J)V"
            r10 = 182(0xb6, float:2.55E-43)
            r1.visitMethodInsn(r10, r5, r7, r9)
            r5 = 16
            r1.visitVarInsn(r5, r8)
            java.lang.String r5 = SerializeWriter
            r1.visitMethodInsn(r10, r5, r14, r15)
        L_0x0100:
            r10 = r0
            r0 = r4
            r20 = r6
            r19 = r11
            r11 = r13
            r9 = r15
        L_0x0108:
            r2 = 0
            r5 = 1
            r6 = 25
            r7 = 16
            r8 = 182(0xb6, float:2.55E-43)
            goto L_0x088b
        L_0x0112:
            java.lang.Class r10 = java.lang.Float.TYPE
            if (r9 != r10) goto L_0x013f
            int r9 = r3.var(r4)
            r10 = 25
            r1.visitVarInsn(r10, r9)
            r1.visitInsn(r5)
            r0._get(r1, r3, r7)
            r5 = 4
            r1.visitInsn(r5)
            java.lang.String r5 = SerializeWriter
            java.lang.String r7 = "writeFloat"
            java.lang.String r9 = "(FZ)V"
            r10 = 182(0xb6, float:2.55E-43)
            r1.visitMethodInsn(r10, r5, r7, r9)
            r5 = 16
            r1.visitVarInsn(r5, r8)
            java.lang.String r5 = SerializeWriter
            r1.visitMethodInsn(r10, r5, r14, r15)
            goto L_0x0100
        L_0x013f:
            java.lang.Class r10 = java.lang.Double.TYPE
            if (r9 != r10) goto L_0x016c
            int r9 = r3.var(r4)
            r10 = 25
            r1.visitVarInsn(r10, r9)
            r1.visitInsn(r5)
            r0._get(r1, r3, r7)
            r5 = 4
            r1.visitInsn(r5)
            java.lang.String r5 = SerializeWriter
            java.lang.String r7 = "writeDouble"
            java.lang.String r9 = "(DZ)V"
            r10 = 182(0xb6, float:2.55E-43)
            r1.visitMethodInsn(r10, r5, r7, r9)
            r5 = 16
            r1.visitVarInsn(r5, r8)
            java.lang.String r5 = SerializeWriter
            r1.visitMethodInsn(r10, r5, r14, r15)
            goto L_0x0100
        L_0x016c:
            java.lang.Class r10 = java.lang.Boolean.TYPE
            if (r9 != r10) goto L_0x0194
            int r9 = r3.var(r4)
            r10 = 25
            r1.visitVarInsn(r10, r9)
            r1.visitInsn(r5)
            r0._get(r1, r3, r7)
            java.lang.String r5 = SerializeWriter
            java.lang.String r7 = "(Z)V"
            r9 = 182(0xb6, float:2.55E-43)
            r1.visitMethodInsn(r9, r5, r14, r7)
            r5 = 16
            r1.visitVarInsn(r5, r8)
            java.lang.String r5 = SerializeWriter
            r1.visitMethodInsn(r9, r5, r14, r15)
            goto L_0x0100
        L_0x0194:
            java.lang.Class r10 = java.lang.Character.TYPE
            r5 = 184(0xb8, float:2.58E-43)
            if (r9 != r10) goto L_0x01c1
            int r9 = r3.var(r4)
            r10 = 25
            r1.visitVarInsn(r10, r9)
            r0._get(r1, r3, r7)
            java.lang.String r7 = "java/lang/Character"
            java.lang.String r9 = "toString"
            java.lang.String r10 = "(C)Ljava/lang/String;"
            r1.visitMethodInsn(r5, r7, r9, r10)
            r10 = 16
            r1.visitVarInsn(r10, r8)
            java.lang.String r5 = SerializeWriter
            java.lang.String r7 = "writeString"
            java.lang.String r8 = "(Ljava/lang/String;C)V"
            r9 = 182(0xb6, float:2.55E-43)
            r1.visitMethodInsn(r9, r5, r7, r8)
            goto L_0x0100
        L_0x01c1:
            r10 = 16
            java.lang.Class<java.lang.String> r5 = java.lang.String.class
            if (r9 != r5) goto L_0x01e3
            int r5 = r3.var(r4)
            r9 = 25
            r1.visitVarInsn(r9, r5)
            r0._get(r1, r3, r7)
            r1.visitVarInsn(r10, r8)
            java.lang.String r5 = SerializeWriter
            java.lang.String r7 = "writeString"
            java.lang.String r8 = "(Ljava/lang/String;C)V"
            r10 = 182(0xb6, float:2.55E-43)
            r1.visitMethodInsn(r10, r5, r7, r8)
            goto L_0x0100
        L_0x01e3:
            r5 = 25
            r10 = 182(0xb6, float:2.55E-43)
            boolean r16 = r9.isEnum()
            if (r16 == 0) goto L_0x0211
            int r9 = r3.var(r4)
            r1.visitVarInsn(r5, r9)
            r5 = 89
            r1.visitInsn(r5)
            r0._get(r1, r3, r7)
            java.lang.String r5 = SerializeWriter
            java.lang.String r7 = "writeEnum"
            java.lang.String r9 = "(Ljava/lang/Enum;)V"
            r1.visitMethodInsn(r10, r5, r7, r9)
            r5 = 16
            r1.visitVarInsn(r5, r8)
            java.lang.String r5 = SerializeWriter
            r1.visitMethodInsn(r10, r5, r14, r15)
            goto L_0x0100
        L_0x0211:
            java.lang.Class<java.util.List> r5 = java.util.List.class
            boolean r5 = r5.isAssignableFrom(r9)
            java.lang.String r10 = "writeWithFieldName"
            if (r5 == 0) goto L_0x05d8
            java.lang.reflect.Type r5 = r7.fieldType
            boolean r9 = r5 instanceof java.lang.Class
            if (r9 == 0) goto L_0x0224
            java.lang.Class<java.lang.Object> r5 = java.lang.Object.class
            goto L_0x022d
        L_0x0224:
            java.lang.reflect.ParameterizedType r5 = (java.lang.reflect.ParameterizedType) r5
            java.lang.reflect.Type[] r5 = r5.getActualTypeArguments()
            r9 = 0
            r5 = r5[r9]
        L_0x022d:
            boolean r9 = r5 instanceof java.lang.Class
            if (r9 == 0) goto L_0x0238
            r9 = r5
            java.lang.Class r9 = (java.lang.Class) r9
            java.lang.Class<java.lang.Object> r2 = java.lang.Object.class
            if (r9 != r2) goto L_0x0239
        L_0x0238:
            r9 = 0
        L_0x0239:
            r0._get(r1, r3, r7)
            r2 = 192(0xc0, float:2.69E-43)
            r19 = r11
            java.lang.String r11 = "java/util/List"
            r1.visitTypeInsn(r2, r11)
            java.lang.String r2 = "list"
            int r11 = r3.var(r2)
            r20 = r6
            r6 = 58
            r1.visitVarInsn(r6, r11)
            java.lang.Class<java.lang.String> r6 = java.lang.String.class
            if (r9 != r6) goto L_0x0284
            boolean r6 = r33.writeDirect
            if (r6 == 0) goto L_0x0284
            int r5 = r3.var(r4)
            r6 = 25
            r1.visitVarInsn(r6, r5)
            int r2 = r3.var(r2)
            r1.visitVarInsn(r6, r2)
            java.lang.String r2 = SerializeWriter
            java.lang.String r5 = "(Ljava/util/List;)V"
            r6 = 182(0xb6, float:2.55E-43)
            r1.visitMethodInsn(r6, r2, r14, r5)
            r0 = r4
            r21 = r8
            r11 = r13
            r8 = r15
            r2 = 21
            r5 = 25
            r6 = 16
            r7 = 182(0xb6, float:2.55E-43)
            goto L_0x05c2
        L_0x0284:
            com.alibaba.fastjson.asm.Label r6 = new com.alibaba.fastjson.asm.Label
            r6.<init>()
            com.alibaba.fastjson.asm.Label r11 = new com.alibaba.fastjson.asm.Label
            r11.<init>()
            r21 = r8
            int r8 = r3.var(r2)
            r22 = r10
            r10 = 25
            r1.visitVarInsn(r10, r8)
            r8 = 199(0xc7, float:2.79E-43)
            r1.visitJumpInsn(r8, r11)
            int r8 = r3.var(r4)
            r1.visitVarInsn(r10, r8)
            java.lang.String r8 = SerializeWriter
            java.lang.String r10 = "writeNull"
            r18 = r5
            java.lang.String r5 = "()V"
            r23 = r13
            r13 = 182(0xb6, float:2.55E-43)
            r1.visitMethodInsn(r13, r8, r10, r5)
            r5 = 167(0xa7, float:2.34E-43)
            r1.visitJumpInsn(r5, r6)
            r1.visitLabel(r11)
            int r5 = r3.var(r2)
            r8 = 25
            r1.visitVarInsn(r8, r5)
            java.lang.String r5 = "java/util/List"
            java.lang.String r10 = "size"
            java.lang.String r11 = "()I"
            r13 = 185(0xb9, float:2.59E-43)
            r1.visitMethodInsn(r13, r5, r10, r11)
            r5 = 54
            java.lang.String r10 = "size"
            int r10 = r3.var(r10)
            r1.visitVarInsn(r5, r10)
            int r5 = r3.var(r4)
            r1.visitVarInsn(r8, r5)
            r5 = 91
            r8 = 16
            r1.visitVarInsn(r8, r5)
            java.lang.String r5 = SerializeWriter
            r8 = 182(0xb6, float:2.55E-43)
            r1.visitMethodInsn(r8, r5, r14, r15)
            com.alibaba.fastjson.asm.Label r5 = new com.alibaba.fastjson.asm.Label
            r5.<init>()
            com.alibaba.fastjson.asm.Label r8 = new com.alibaba.fastjson.asm.Label
            r8.<init>()
            com.alibaba.fastjson.asm.Label r10 = new com.alibaba.fastjson.asm.Label
            r10.<init>()
            r11 = 3
            r1.visitInsn(r11)
            r11 = 54
            java.lang.String r13 = "i"
            r24 = r6
            int r6 = r3.var(r13)
            r1.visitVarInsn(r11, r6)
            r1.visitLabel(r5)
            int r6 = r3.var(r13)
            r11 = 21
            r1.visitVarInsn(r11, r6)
            java.lang.String r6 = "size"
            int r6 = r3.var(r6)
            r1.visitVarInsn(r11, r6)
            r6 = 162(0xa2, float:2.27E-43)
            r1.visitJumpInsn(r6, r10)
            int r6 = r3.var(r13)
            r1.visitVarInsn(r11, r6)
            r6 = 153(0x99, float:2.14E-43)
            r1.visitJumpInsn(r6, r8)
            int r6 = r3.var(r4)
            r11 = 25
            r1.visitVarInsn(r11, r6)
            r6 = 44
            r11 = 16
            r1.visitVarInsn(r11, r6)
            java.lang.String r6 = SerializeWriter
            r11 = 182(0xb6, float:2.55E-43)
            r1.visitMethodInsn(r11, r6, r14, r15)
            r1.visitLabel(r8)
            int r2 = r3.var(r2)
            r6 = 25
            r1.visitVarInsn(r6, r2)
            int r2 = r3.var(r13)
            r6 = 21
            r1.visitVarInsn(r6, r2)
            java.lang.String r2 = "java/util/List"
            java.lang.String r6 = "get"
            java.lang.String r8 = "(I)Ljava/lang/Object;"
            r11 = 185(0xb9, float:2.59E-43)
            r1.visitMethodInsn(r11, r2, r6, r8)
            java.lang.String r2 = "list_item"
            int r6 = r3.var(r2)
            r8 = 58
            r1.visitVarInsn(r8, r6)
            com.alibaba.fastjson.asm.Label r6 = new com.alibaba.fastjson.asm.Label
            r6.<init>()
            com.alibaba.fastjson.asm.Label r8 = new com.alibaba.fastjson.asm.Label
            r8.<init>()
            int r11 = r3.var(r2)
            r25 = r15
            r15 = 25
            r1.visitVarInsn(r15, r11)
            r11 = 199(0xc7, float:2.79E-43)
            r1.visitJumpInsn(r11, r8)
            int r11 = r3.var(r4)
            r1.visitVarInsn(r15, r11)
            java.lang.String r11 = SerializeWriter
            java.lang.String r15 = "writeNull"
            r26 = r4
            java.lang.String r4 = "()V"
            r27 = r10
            r10 = 182(0xb6, float:2.55E-43)
            r1.visitMethodInsn(r10, r11, r15, r4)
            r4 = 167(0xa7, float:2.34E-43)
            r1.visitJumpInsn(r4, r6)
            r1.visitLabel(r8)
            com.alibaba.fastjson.asm.Label r4 = new com.alibaba.fastjson.asm.Label
            r4.<init>()
            com.alibaba.fastjson.asm.Label r8 = new com.alibaba.fastjson.asm.Label
            r8.<init>()
            if (r9 == 0) goto L_0x0514
            int r10 = r9.getModifiers()
            boolean r10 = java.lang.reflect.Modifier.isPublic(r10)
            if (r10 == 0) goto L_0x0514
            int r10 = r3.var(r2)
            r11 = 25
            r1.visitVarInsn(r11, r10)
            java.lang.String r10 = "java/lang/Object"
            java.lang.String r11 = "getClass"
            java.lang.String r15 = "()Ljava/lang/Class;"
            r28 = r5
            r5 = 182(0xb6, float:2.55E-43)
            r1.visitMethodInsn(r5, r10, r11, r15)
            java.lang.String r5 = com.alibaba.fastjson.util.ASMUtils.desc((java.lang.Class<?>) r9)
            com.alibaba.fastjson.asm.Type r5 = com.alibaba.fastjson.asm.Type.getType(r5)
            r1.visitLdcInsn(r5)
            r5 = 166(0xa6, float:2.33E-43)
            r1.visitJumpInsn(r5, r8)
            r0._getListFieldItemSer(r3, r1, r7, r9)
            java.lang.String r5 = "list_item_desc"
            int r5 = r3.var(r5)
            r10 = 58
            r1.visitVarInsn(r10, r5)
            com.alibaba.fastjson.asm.Label r5 = new com.alibaba.fastjson.asm.Label
            r5.<init>()
            com.alibaba.fastjson.asm.Label r10 = new com.alibaba.fastjson.asm.Label
            r10.<init>()
            boolean r11 = r33.writeDirect
            if (r11 == 0) goto L_0x04a0
            java.lang.String r11 = "list_item_desc"
            int r11 = r3.var(r11)
            r15 = 25
            r1.visitVarInsn(r15, r11)
            r11 = 193(0xc1, float:2.7E-43)
            java.lang.String r15 = JavaBeanSerializer
            r1.visitTypeInsn(r11, r15)
            r11 = 153(0x99, float:2.14E-43)
            r1.visitJumpInsn(r11, r5)
            java.lang.String r11 = "list_item_desc"
            int r11 = r3.var(r11)
            r15 = 25
            r1.visitVarInsn(r15, r11)
            r11 = 192(0xc0, float:2.69E-43)
            java.lang.String r0 = JavaBeanSerializer
            r1.visitTypeInsn(r11, r0)
            r0 = 1
            r1.visitVarInsn(r15, r0)
            int r11 = r3.var(r2)
            r1.visitVarInsn(r15, r11)
            boolean r11 = r33.nonContext
            if (r11 == 0) goto L_0x044c
            r1.visitInsn(r0)
            r17 = r6
            goto L_0x0462
        L_0x044c:
            int r0 = r3.var(r13)
            r11 = 21
            r1.visitVarInsn(r11, r0)
            java.lang.String r0 = "java/lang/Integer"
            java.lang.String r11 = "valueOf"
            java.lang.String r15 = "(I)Ljava/lang/Integer;"
            r17 = r6
            r6 = 184(0xb8, float:2.58E-43)
            r1.visitMethodInsn(r6, r0, r11, r15)
        L_0x0462:
            java.lang.String r0 = com.alibaba.fastjson.util.ASMUtils.desc((java.lang.Class<?>) r9)
            com.alibaba.fastjson.asm.Type r0 = com.alibaba.fastjson.asm.Type.getType(r0)
            r1.visitLdcInsn(r0)
            int r0 = r7.serialzeFeatures
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r1.visitLdcInsn(r0)
            java.lang.String r0 = JavaBeanSerializer
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            r6.append(r12)
            java.lang.String r11 = JSONSerializer
            r6.append(r11)
            r11 = r23
            r6.append(r11)
            java.lang.String r6 = r6.toString()
            java.lang.String r15 = "writeAsArrayNonContext"
            r23 = r8
            r8 = 182(0xb6, float:2.55E-43)
            r1.visitMethodInsn(r8, r0, r15, r6)
            r0 = 167(0xa7, float:2.34E-43)
            r1.visitJumpInsn(r0, r10)
            r1.visitLabel(r5)
            goto L_0x04a6
        L_0x04a0:
            r17 = r6
            r11 = r23
            r23 = r8
        L_0x04a6:
            java.lang.String r0 = "list_item_desc"
            int r0 = r3.var(r0)
            r5 = 25
            r1.visitVarInsn(r5, r0)
            r0 = 1
            r1.visitVarInsn(r5, r0)
            int r6 = r3.var(r2)
            r1.visitVarInsn(r5, r6)
            boolean r5 = r33.nonContext
            if (r5 == 0) goto L_0x04c6
            r1.visitInsn(r0)
            goto L_0x04da
        L_0x04c6:
            int r0 = r3.var(r13)
            r5 = 21
            r1.visitVarInsn(r5, r0)
            java.lang.String r0 = "java/lang/Integer"
            java.lang.String r5 = "valueOf"
            java.lang.String r6 = "(I)Ljava/lang/Integer;"
            r8 = 184(0xb8, float:2.58E-43)
            r1.visitMethodInsn(r8, r0, r5, r6)
        L_0x04da:
            java.lang.String r0 = com.alibaba.fastjson.util.ASMUtils.desc((java.lang.Class<?>) r9)
            com.alibaba.fastjson.asm.Type r0 = com.alibaba.fastjson.asm.Type.getType(r0)
            r1.visitLdcInsn(r0)
            int r0 = r7.serialzeFeatures
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r1.visitLdcInsn(r0)
            java.lang.String r0 = ObjectSerializer
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            r5.append(r12)
            java.lang.String r6 = JSONSerializer
            r5.append(r6)
            r5.append(r11)
            java.lang.String r5 = r5.toString()
            r6 = 185(0xb9, float:2.59E-43)
            r1.visitMethodInsn(r6, r0, r14, r5)
            r1.visitLabel(r10)
            r0 = 167(0xa7, float:2.34E-43)
            r1.visitJumpInsn(r0, r4)
            r0 = r23
            goto L_0x051b
        L_0x0514:
            r28 = r5
            r17 = r6
            r11 = r23
            r0 = r8
        L_0x051b:
            r1.visitLabel(r0)
            r0 = 1
            r5 = 25
            r1.visitVarInsn(r5, r0)
            int r2 = r3.var(r2)
            r1.visitVarInsn(r5, r2)
            boolean r2 = r33.nonContext
            if (r2 == 0) goto L_0x0537
            r1.visitInsn(r0)
            r2 = 21
            goto L_0x054b
        L_0x0537:
            int r0 = r3.var(r13)
            r2 = 21
            r1.visitVarInsn(r2, r0)
            java.lang.String r0 = "java/lang/Integer"
            java.lang.String r5 = "valueOf"
            java.lang.String r6 = "(I)Ljava/lang/Integer;"
            r8 = 184(0xb8, float:2.58E-43)
            r1.visitMethodInsn(r8, r0, r5, r6)
        L_0x054b:
            if (r9 == 0) goto L_0x057b
            int r0 = r9.getModifiers()
            boolean r0 = java.lang.reflect.Modifier.isPublic(r0)
            if (r0 == 0) goto L_0x057b
            r5 = r18
            java.lang.Class r5 = (java.lang.Class) r5
            java.lang.String r0 = com.alibaba.fastjson.util.ASMUtils.desc((java.lang.Class<?>) r5)
            com.alibaba.fastjson.asm.Type r0 = com.alibaba.fastjson.asm.Type.getType(r0)
            r1.visitLdcInsn(r0)
            int r0 = r7.serialzeFeatures
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r1.visitLdcInsn(r0)
            java.lang.String r0 = JSONSerializer
            java.lang.String r5 = "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V"
            r6 = r22
            r7 = 182(0xb6, float:2.55E-43)
            r1.visitMethodInsn(r7, r0, r6, r5)
            goto L_0x0586
        L_0x057b:
            r6 = r22
            r7 = 182(0xb6, float:2.55E-43)
            java.lang.String r0 = JSONSerializer
            java.lang.String r5 = "(Ljava/lang/Object;Ljava/lang/Object;)V"
            r1.visitMethodInsn(r7, r0, r6, r5)
        L_0x0586:
            r1.visitLabel(r4)
            r0 = r17
            r1.visitLabel(r0)
            int r0 = r3.var(r13)
            r4 = 1
            r1.visitIincInsn(r0, r4)
            r0 = r28
            r4 = 167(0xa7, float:2.34E-43)
            r1.visitJumpInsn(r4, r0)
            r0 = r27
            r1.visitLabel(r0)
            r0 = r26
            int r4 = r3.var(r0)
            r5 = 25
            r1.visitVarInsn(r5, r4)
            r4 = 93
            r6 = 16
            r1.visitVarInsn(r6, r4)
            java.lang.String r4 = SerializeWriter
            r8 = r25
            r7 = 182(0xb6, float:2.55E-43)
            r1.visitMethodInsn(r7, r4, r14, r8)
            r4 = r24
            r1.visitLabel(r4)
        L_0x05c2:
            int r4 = r3.var(r0)
            r1.visitVarInsn(r5, r4)
            r4 = r21
            r1.visitVarInsn(r6, r4)
            java.lang.String r4 = SerializeWriter
            r1.visitMethodInsn(r7, r4, r14, r8)
            r10 = r29
            r9 = r8
            goto L_0x0108
        L_0x05d8:
            r0 = r4
            r20 = r6
            r4 = r8
            r6 = r10
            r19 = r11
            r11 = r13
            r8 = r15
            r2 = 21
            com.alibaba.fastjson.asm.Label r5 = new com.alibaba.fastjson.asm.Label
            r5.<init>()
            com.alibaba.fastjson.asm.Label r10 = new com.alibaba.fastjson.asm.Label
            r10.<init>()
            r13 = r29
            r13._get(r1, r3, r7)
            r15 = 89
            r1.visitInsn(r15)
            java.lang.StringBuilder r15 = new java.lang.StringBuilder
            r15.<init>()
            java.lang.String r2 = "field_"
            r15.append(r2)
            r25 = r8
            java.lang.Class<?> r8 = r7.fieldClass
            java.lang.String r8 = r8.getName()
            r15.append(r8)
            java.lang.String r8 = r15.toString()
            int r8 = r3.var(r8)
            r15 = 58
            r1.visitVarInsn(r15, r8)
            r8 = 199(0xc7, float:2.79E-43)
            r1.visitJumpInsn(r8, r10)
            int r8 = r3.var(r0)
            r15 = 25
            r1.visitVarInsn(r15, r8)
            java.lang.String r8 = SerializeWriter
            java.lang.String r15 = "writeNull"
            r21 = r4
            java.lang.String r4 = "()V"
            r26 = r0
            r0 = 182(0xb6, float:2.55E-43)
            r1.visitMethodInsn(r0, r8, r15, r4)
            r0 = 167(0xa7, float:2.34E-43)
            r1.visitJumpInsn(r0, r5)
            r1.visitLabel(r10)
            com.alibaba.fastjson.asm.Label r0 = new com.alibaba.fastjson.asm.Label
            r0.<init>()
            com.alibaba.fastjson.asm.Label r4 = new com.alibaba.fastjson.asm.Label
            r4.<init>()
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            r8.append(r2)
            java.lang.Class<?> r10 = r7.fieldClass
            java.lang.String r10 = r10.getName()
            r8.append(r10)
            java.lang.String r8 = r8.toString()
            int r8 = r3.var(r8)
            r10 = 25
            r1.visitVarInsn(r10, r8)
            java.lang.String r8 = "java/lang/Object"
            java.lang.String r10 = "getClass"
            java.lang.String r15 = "()Ljava/lang/Class;"
            r18 = r5
            r5 = 182(0xb6, float:2.55E-43)
            r1.visitMethodInsn(r5, r8, r10, r15)
            java.lang.String r5 = com.alibaba.fastjson.util.ASMUtils.desc((java.lang.Class<?>) r9)
            com.alibaba.fastjson.asm.Type r5 = com.alibaba.fastjson.asm.Type.getType(r5)
            r1.visitLdcInsn(r5)
            r5 = 166(0xa6, float:2.33E-43)
            r1.visitJumpInsn(r5, r4)
            r13._getFieldSer(r3, r1, r7)
            java.lang.String r5 = "fied_ser"
            int r8 = r3.var(r5)
            r10 = 58
            r1.visitVarInsn(r10, r8)
            com.alibaba.fastjson.asm.Label r8 = new com.alibaba.fastjson.asm.Label
            r8.<init>()
            com.alibaba.fastjson.asm.Label r10 = new com.alibaba.fastjson.asm.Label
            r10.<init>()
            boolean r15 = r33.writeDirect
            if (r15 == 0) goto L_0x0733
            int r15 = r9.getModifiers()
            boolean r15 = java.lang.reflect.Modifier.isPublic(r15)
            if (r15 == 0) goto L_0x0733
            int r15 = r3.var(r5)
            r13 = 25
            r1.visitVarInsn(r13, r15)
            r15 = 193(0xc1, float:2.7E-43)
            java.lang.String r13 = JavaBeanSerializer
            r1.visitTypeInsn(r15, r13)
            r13 = 153(0x99, float:2.14E-43)
            r1.visitJumpInsn(r13, r8)
            int r13 = r3.var(r5)
            r15 = 25
            r1.visitVarInsn(r15, r13)
            r13 = 192(0xc0, float:2.69E-43)
            r22 = r6
            java.lang.String r6 = JavaBeanSerializer
            r1.visitTypeInsn(r13, r6)
            r6 = 1
            r1.visitVarInsn(r15, r6)
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            r6.append(r2)
            java.lang.Class<?> r13 = r7.fieldClass
            java.lang.String r13 = r13.getName()
            r6.append(r13)
            java.lang.String r6 = r6.toString()
            int r6 = r3.var(r6)
            r1.visitVarInsn(r15, r6)
            int r6 = com.alibaba.fastjson.serializer.ASMSerializerFactory.Context.fieldName
            r1.visitVarInsn(r15, r6)
            java.lang.String r6 = com.alibaba.fastjson.util.ASMUtils.desc((java.lang.Class<?>) r9)
            com.alibaba.fastjson.asm.Type r6 = com.alibaba.fastjson.asm.Type.getType(r6)
            r1.visitLdcInsn(r6)
            int r6 = r7.serialzeFeatures
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            r1.visitLdcInsn(r6)
            java.lang.String r6 = JavaBeanSerializer
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            r13.<init>()
            r13.append(r12)
            java.lang.String r15 = JSONSerializer
            r13.append(r15)
            r13.append(r11)
            java.lang.String r13 = r13.toString()
            java.lang.String r15 = "writeAsArrayNonContext"
            r17 = r4
            r4 = 182(0xb6, float:2.55E-43)
            r1.visitMethodInsn(r4, r6, r15, r13)
            r4 = 167(0xa7, float:2.34E-43)
            r1.visitJumpInsn(r4, r10)
            r1.visitLabel(r8)
            goto L_0x0737
        L_0x0733:
            r17 = r4
            r22 = r6
        L_0x0737:
            int r4 = r3.var(r5)
            r5 = 25
            r1.visitVarInsn(r5, r4)
            r4 = 1
            r1.visitVarInsn(r5, r4)
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r4.append(r2)
            java.lang.Class<?> r6 = r7.fieldClass
            java.lang.String r6 = r6.getName()
            r4.append(r6)
            java.lang.String r4 = r4.toString()
            int r4 = r3.var(r4)
            r1.visitVarInsn(r5, r4)
            int r4 = com.alibaba.fastjson.serializer.ASMSerializerFactory.Context.fieldName
            r1.visitVarInsn(r5, r4)
            java.lang.String r4 = com.alibaba.fastjson.util.ASMUtils.desc((java.lang.Class<?>) r9)
            com.alibaba.fastjson.asm.Type r4 = com.alibaba.fastjson.asm.Type.getType(r4)
            r1.visitLdcInsn(r4)
            int r4 = r7.serialzeFeatures
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            r1.visitLdcInsn(r4)
            java.lang.String r4 = ObjectSerializer
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            r5.append(r12)
            java.lang.String r6 = JSONSerializer
            r5.append(r6)
            r5.append(r11)
            java.lang.String r5 = r5.toString()
            r6 = 185(0xb9, float:2.59E-43)
            r1.visitMethodInsn(r6, r4, r14, r5)
            r1.visitLabel(r10)
            r4 = 167(0xa7, float:2.34E-43)
            r1.visitJumpInsn(r4, r0)
            r4 = r17
            r1.visitLabel(r4)
            java.lang.String r4 = r7.getFormat()
            r5 = 1
            r6 = 25
            r1.visitVarInsn(r6, r5)
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            r8.append(r2)
            java.lang.Class<?> r2 = r7.fieldClass
            java.lang.String r2 = r2.getName()
            r8.append(r2)
            java.lang.String r2 = r8.toString()
            int r2 = r3.var(r2)
            r1.visitVarInsn(r6, r2)
            if (r4 == 0) goto L_0x07d9
            r1.visitLdcInsn(r4)
            java.lang.String r2 = JSONSerializer
            java.lang.String r4 = "writeWithFormat"
            java.lang.String r7 = "(Ljava/lang/Object;Ljava/lang/String;)V"
            r8 = 182(0xb6, float:2.55E-43)
            r1.visitMethodInsn(r8, r2, r4, r7)
            r2 = 0
            goto L_0x0835
        L_0x07d9:
            int r2 = com.alibaba.fastjson.serializer.ASMSerializerFactory.Context.fieldName
            r1.visitVarInsn(r6, r2)
            java.lang.reflect.Type r2 = r7.fieldType
            boolean r2 = r2 instanceof java.lang.Class
            if (r2 == 0) goto L_0x07fd
            java.lang.reflect.Type r2 = r7.fieldType
            java.lang.Class r2 = (java.lang.Class) r2
            boolean r2 = r2.isPrimitive()
            if (r2 == 0) goto L_0x07fd
            java.lang.String r2 = JSONSerializer
            java.lang.String r4 = "(Ljava/lang/Object;Ljava/lang/Object;)V"
            r6 = r22
            r7 = 182(0xb6, float:2.55E-43)
            r1.visitMethodInsn(r7, r2, r6, r4)
            r2 = 0
            r8 = 182(0xb6, float:2.55E-43)
            goto L_0x0835
        L_0x07fd:
            r6 = r22
            r2 = 0
            r4 = 25
            r1.visitVarInsn(r4, r2)
            r4 = 180(0xb4, float:2.52E-43)
            java.lang.String r8 = r33.className
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r10 = r7.name
            r9.append(r10)
            java.lang.String r10 = "_asm_fieldType"
            r9.append(r10)
            java.lang.String r9 = r9.toString()
            java.lang.String r10 = "Ljava/lang/reflect/Type;"
            r1.visitFieldInsn(r4, r8, r9, r10)
            int r4 = r7.serialzeFeatures
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            r1.visitLdcInsn(r4)
            java.lang.String r4 = JSONSerializer
            java.lang.String r7 = "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V"
            r8 = 182(0xb6, float:2.55E-43)
            r1.visitMethodInsn(r8, r4, r6, r7)
        L_0x0835:
            r1.visitLabel(r0)
            r0 = r18
            r1.visitLabel(r0)
            r0 = r26
            int r4 = r3.var(r0)
            r6 = 25
            r1.visitVarInsn(r6, r4)
            r4 = r21
            r7 = 16
            r1.visitVarInsn(r7, r4)
            java.lang.String r4 = SerializeWriter
            r9 = r25
            r1.visitMethodInsn(r8, r4, r14, r9)
            r10 = r29
            r7 = 16
            goto L_0x088b
        L_0x085b:
            r0 = r4
            r20 = r6
            r4 = r8
            r19 = r11
            r11 = r13
            r9 = r15
            r2 = 0
            r5 = 1
            r6 = 25
            r8 = 182(0xb6, float:2.55E-43)
            int r10 = r3.var(r0)
            r1.visitVarInsn(r6, r10)
            r10 = 89
            r1.visitInsn(r10)
            r10 = r29
            r10._get(r1, r3, r7)
            java.lang.String r7 = SerializeWriter
            java.lang.String r13 = "writeInt"
            r1.visitMethodInsn(r8, r7, r13, r9)
            r7 = 16
            r1.visitVarInsn(r7, r4)
            java.lang.String r4 = SerializeWriter
            r1.visitMethodInsn(r8, r4, r14, r9)
        L_0x088b:
            int r4 = r20 + 1
            r2 = r32
            r6 = r4
            r15 = r9
            r13 = r11
            r11 = r19
            r5 = 25
            r7 = 0
            r8 = 21
            r9 = 16
            r4 = r0
            r0 = r10
            r10 = 182(0xb6, float:2.55E-43)
            goto L_0x00ad
        L_0x08a1:
            r10 = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.serializer.ASMSerializerFactory.generateWriteAsArray(java.lang.Class, com.alibaba.fastjson.asm.MethodVisitor, com.alibaba.fastjson.util.FieldInfo[], com.alibaba.fastjson.serializer.ASMSerializerFactory$Context):void");
    }

    private void generateWriteMethod(Class<?> cls, MethodVisitor methodVisitor, FieldInfo[] fieldInfoArr, Context context) throws Exception {
        Label label;
        String str;
        String str2;
        Class<?> cls2;
        int i;
        Class<?> cls3 = cls;
        MethodVisitor methodVisitor2 = methodVisitor;
        FieldInfo[] fieldInfoArr2 = fieldInfoArr;
        Context context2 = context;
        Label label2 = new Label();
        int length = fieldInfoArr2.length;
        String str3 = "out";
        if (!context.writeDirect) {
            Label label3 = new Label();
            Label label4 = new Label();
            label = label2;
            methodVisitor2.visitVarInsn(25, context2.var(str3));
            methodVisitor2.visitLdcInsn(Integer.valueOf(SerializerFeature.PrettyFormat.mask));
            methodVisitor2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, SerializeWriter, "isEnabled", "(I)Z");
            methodVisitor2.visitJumpInsn(Opcodes.IFNE, label4);
            int length2 = fieldInfoArr2.length;
            int i2 = 0;
            boolean z = false;
            while (i2 < length2) {
                int i3 = length2;
                if (fieldInfoArr2[i2].method != null) {
                    z = true;
                }
                i2++;
                length2 = i3;
            }
            if (z) {
                methodVisitor2.visitVarInsn(25, context2.var(str3));
                methodVisitor2.visitLdcInsn(Integer.valueOf(SerializerFeature.IgnoreErrorGetter.mask));
                methodVisitor2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, SerializeWriter, "isEnabled", "(I)Z");
                methodVisitor2.visitJumpInsn(Opcodes.IFEQ, label3);
            } else {
                methodVisitor2.visitJumpInsn(167, label3);
            }
            methodVisitor2.visitLabel(label4);
            methodVisitor2.visitVarInsn(25, 0);
            methodVisitor2.visitVarInsn(25, 1);
            methodVisitor2.visitVarInsn(25, 2);
            methodVisitor2.visitVarInsn(25, 3);
            methodVisitor2.visitVarInsn(25, 4);
            methodVisitor2.visitVarInsn(21, 5);
            String str4 = JavaBeanSerializer;
            methodVisitor2.visitMethodInsn(Opcodes.INVOKESPECIAL, str4, "write", "(L" + JSONSerializer + ";Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
            methodVisitor2.visitInsn(177);
            methodVisitor2.visitLabel(label3);
        } else {
            label = label2;
        }
        if (!context.nonContext) {
            Label label5 = new Label();
            methodVisitor2.visitVarInsn(25, 0);
            methodVisitor2.visitVarInsn(25, 1);
            methodVisitor2.visitVarInsn(25, 2);
            methodVisitor2.visitVarInsn(21, 5);
            String str5 = JavaBeanSerializer;
            methodVisitor2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str5, "writeReference", "(L" + JSONSerializer + ";Ljava/lang/Object;I)Z");
            methodVisitor2.visitJumpInsn(Opcodes.IFEQ, label5);
            methodVisitor2.visitInsn(177);
            methodVisitor2.visitLabel(label5);
        }
        String str6 = context.writeDirect ? context.nonContext ? "writeAsArrayNonContext" : "writeAsArray" : "writeAsArrayNormal";
        if ((context.beanInfo.features & SerializerFeature.BeanToArray.mask) == 0) {
            Label label6 = new Label();
            methodVisitor2.visitVarInsn(25, context2.var(str3));
            methodVisitor2.visitLdcInsn(Integer.valueOf(SerializerFeature.BeanToArray.mask));
            methodVisitor2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, SerializeWriter, "isEnabled", "(I)Z");
            methodVisitor2.visitJumpInsn(Opcodes.IFEQ, label6);
            methodVisitor2.visitVarInsn(25, 0);
            methodVisitor2.visitVarInsn(25, 1);
            methodVisitor2.visitVarInsn(25, 2);
            methodVisitor2.visitVarInsn(25, 3);
            methodVisitor2.visitVarInsn(25, 4);
            methodVisitor2.visitVarInsn(21, 5);
            String access$300 = context.className;
            methodVisitor2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, access$300, str6, "(L" + JSONSerializer + ";Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
            methodVisitor2.visitInsn(177);
            methodVisitor2.visitLabel(label6);
        } else {
            methodVisitor2.visitVarInsn(25, 0);
            methodVisitor2.visitVarInsn(25, 1);
            methodVisitor2.visitVarInsn(25, 2);
            methodVisitor2.visitVarInsn(25, 3);
            methodVisitor2.visitVarInsn(25, 4);
            methodVisitor2.visitVarInsn(21, 5);
            String access$3002 = context.className;
            methodVisitor2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, access$3002, str6, "(L" + JSONSerializer + ";Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
            methodVisitor2.visitInsn(177);
        }
        if (!context.nonContext) {
            methodVisitor2.visitVarInsn(25, 1);
            String str7 = JSONSerializer;
            methodVisitor2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str7, "getContext", "()" + SerialContext_desc);
            methodVisitor2.visitVarInsn(58, context2.var("parent"));
            methodVisitor2.visitVarInsn(25, 1);
            methodVisitor2.visitVarInsn(25, context2.var("parent"));
            methodVisitor2.visitVarInsn(25, 2);
            methodVisitor2.visitVarInsn(25, 3);
            methodVisitor2.visitLdcInsn(Integer.valueOf(context.beanInfo.features));
            String str8 = JSONSerializer;
            methodVisitor2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str8, "setContext", "(" + SerialContext_desc + "Ljava/lang/Object;Ljava/lang/Object;I)V");
        }
        boolean z2 = (context.beanInfo.features & SerializerFeature.WriteClassName.mask) != 0;
        if (z2 || !context.writeDirect) {
            Label label7 = new Label();
            Label label8 = new Label();
            Label label9 = new Label();
            if (!z2) {
                methodVisitor2.visitVarInsn(25, 1);
                methodVisitor2.visitVarInsn(25, 4);
                methodVisitor2.visitVarInsn(25, 2);
                String str9 = JSONSerializer;
                str = "parent";
                i = Opcodes.INVOKEVIRTUAL;
                methodVisitor2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str9, "isWriteClassName", "(Ljava/lang/reflect/Type;Ljava/lang/Object;)Z");
                methodVisitor2.visitJumpInsn(Opcodes.IFEQ, label8);
            } else {
                str = "parent";
                i = Opcodes.INVOKEVIRTUAL;
            }
            methodVisitor2.visitVarInsn(25, 4);
            methodVisitor2.visitVarInsn(25, 2);
            methodVisitor2.visitMethodInsn(i, "java/lang/Object", "getClass", "()Ljava/lang/Class;");
            methodVisitor2.visitJumpInsn(165, label8);
            methodVisitor2.visitLabel(label9);
            methodVisitor2.visitVarInsn(25, context2.var(str3));
            methodVisitor2.visitVarInsn(16, TbsListener.ErrorCode.DOWNLOAD_RETRYTIMES302_EXCEED);
            methodVisitor2.visitMethodInsn(i, SerializeWriter, "write", "(I)V");
            methodVisitor2.visitVarInsn(25, 0);
            methodVisitor2.visitVarInsn(25, 1);
            if (context.beanInfo.typeKey != null) {
                methodVisitor2.visitLdcInsn(context.beanInfo.typeKey);
            } else {
                methodVisitor2.visitInsn(1);
            }
            methodVisitor2.visitVarInsn(25, 2);
            String str10 = JavaBeanSerializer;
            methodVisitor2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str10, "writeClassName", "(L" + JSONSerializer + ";Ljava/lang/String;Ljava/lang/Object;)V");
            methodVisitor2.visitVarInsn(16, 44);
            methodVisitor2.visitJumpInsn(167, label7);
            methodVisitor2.visitLabel(label8);
            methodVisitor2.visitVarInsn(16, TbsListener.ErrorCode.DOWNLOAD_RETRYTIMES302_EXCEED);
            methodVisitor2.visitLabel(label7);
        } else {
            methodVisitor2.visitVarInsn(16, TbsListener.ErrorCode.DOWNLOAD_RETRYTIMES302_EXCEED);
            str = "parent";
        }
        methodVisitor2.visitVarInsn(54, context2.var("seperator"));
        if (!context.writeDirect) {
            _before(methodVisitor2, context2);
        }
        if (!context.writeDirect) {
            methodVisitor2.visitVarInsn(25, context2.var(str3));
            methodVisitor2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, SerializeWriter, "isNotWriteDefaultValue", "()Z");
            methodVisitor2.visitVarInsn(54, context2.var("notWriteDefaultValue"));
            methodVisitor2.visitVarInsn(25, 1);
            methodVisitor2.visitVarInsn(25, 0);
            String str11 = JSONSerializer;
            methodVisitor2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str11, "checkValue", "(" + SerializeFilterable_desc + ")Z");
            methodVisitor2.visitVarInsn(54, context2.var("checkValue"));
            methodVisitor2.visitVarInsn(25, 1);
            methodVisitor2.visitVarInsn(25, 0);
            String str12 = JSONSerializer;
            methodVisitor2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str12, "hasNameFilters", "(" + SerializeFilterable_desc + ")Z");
            methodVisitor2.visitVarInsn(54, context2.var("hasNameFilters"));
        }
        int i4 = 0;
        while (i4 < length) {
            FieldInfo fieldInfo = fieldInfoArr2[i4];
            Class<?> cls4 = fieldInfo.fieldClass;
            methodVisitor2.visitLdcInsn(fieldInfo.name);
            methodVisitor2.visitVarInsn(58, Context.fieldName);
            if (cls4 == Byte.TYPE || cls4 == Short.TYPE || cls4 == Integer.TYPE) {
                Class<?> cls5 = cls;
                str2 = str3;
                _int(cls, methodVisitor, fieldInfo, context, context2.var(cls4.getName()), 'I');
            } else {
                if (cls4 == Long.TYPE) {
                    cls2 = cls;
                    _long(cls2, methodVisitor2, fieldInfo, context2);
                } else {
                    cls2 = cls;
                    if (cls4 == Float.TYPE) {
                        _float(cls2, methodVisitor2, fieldInfo, context2);
                    } else if (cls4 == Double.TYPE) {
                        _double(cls2, methodVisitor2, fieldInfo, context2);
                    } else if (cls4 == Boolean.TYPE) {
                        Class<?> cls6 = cls2;
                        str2 = str3;
                        _int(cls, methodVisitor, fieldInfo, context, context2.var("boolean"), 'Z');
                    } else {
                        FieldInfo fieldInfo2 = fieldInfo;
                        Class<?> cls7 = cls2;
                        str2 = str3;
                        if (cls4 == Character.TYPE) {
                            _int(cls, methodVisitor, fieldInfo2, context, context2.var("char"), 'C');
                        } else if (cls4 == String.class) {
                            _string(cls7, methodVisitor2, fieldInfo2, context2);
                        } else {
                            FieldInfo fieldInfo3 = fieldInfo2;
                            if (cls4 == BigDecimal.class) {
                                _decimal(cls7, methodVisitor2, fieldInfo3, context2);
                            } else if (List.class.isAssignableFrom(cls4)) {
                                _list(cls7, methodVisitor2, fieldInfo3, context2);
                            } else if (cls4.isEnum()) {
                                _enum(cls7, methodVisitor2, fieldInfo3, context2);
                            } else {
                                _object(cls7, methodVisitor2, fieldInfo3, context2);
                            }
                        }
                    }
                }
                Class<?> cls8 = cls2;
                str2 = str3;
            }
            i4++;
            fieldInfoArr2 = fieldInfoArr;
            str3 = str2;
        }
        String str13 = str3;
        if (!context.writeDirect) {
            _after(methodVisitor2, context2);
        }
        Label label10 = new Label();
        Label label11 = new Label();
        methodVisitor2.visitVarInsn(21, context2.var("seperator"));
        methodVisitor2.visitIntInsn(16, TbsListener.ErrorCode.DOWNLOAD_RETRYTIMES302_EXCEED);
        methodVisitor2.visitJumpInsn(160, label10);
        String str14 = str13;
        methodVisitor2.visitVarInsn(25, context2.var(str14));
        methodVisitor2.visitVarInsn(16, TbsListener.ErrorCode.DOWNLOAD_RETRYTIMES302_EXCEED);
        methodVisitor2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, SerializeWriter, "write", "(I)V");
        methodVisitor2.visitLabel(label10);
        methodVisitor2.visitVarInsn(25, context2.var(str14));
        methodVisitor2.visitVarInsn(16, TbsListener.ErrorCode.DOWNLOAD_THROWABLE);
        methodVisitor2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, SerializeWriter, "write", "(I)V");
        methodVisitor2.visitLabel(label11);
        methodVisitor2.visitLabel(label);
        if (!context.nonContext) {
            methodVisitor2.visitVarInsn(25, 1);
            methodVisitor2.visitVarInsn(25, context2.var(str));
            String str15 = JSONSerializer;
            methodVisitor2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str15, "setContext", "(" + SerialContext_desc + ")V");
        }
    }

    private void _object(Class<?> cls, MethodVisitor methodVisitor, FieldInfo fieldInfo, Context context) {
        Label label = new Label();
        _nameApply(methodVisitor, fieldInfo, context, label);
        _get(methodVisitor, context, fieldInfo);
        methodVisitor.visitVarInsn(58, context.var("object"));
        _filters(methodVisitor, fieldInfo, context, label);
        _writeObject(methodVisitor, fieldInfo, context, label);
        methodVisitor.visitLabel(label);
    }

    private void _enum(Class<?> cls, MethodVisitor methodVisitor, FieldInfo fieldInfo, Context context) {
        Label label = new Label();
        Label label2 = new Label();
        Label label3 = new Label();
        _nameApply(methodVisitor, fieldInfo, context, label3);
        _get(methodVisitor, context, fieldInfo);
        methodVisitor.visitTypeInsn(Opcodes.CHECKCAST, "java/lang/Enum");
        methodVisitor.visitVarInsn(58, context.var("enum"));
        _filters(methodVisitor, fieldInfo, context, label3);
        methodVisitor.visitVarInsn(25, context.var("enum"));
        methodVisitor.visitJumpInsn(Opcodes.IFNONNULL, label);
        _if_write_null(methodVisitor, fieldInfo, context);
        methodVisitor.visitJumpInsn(167, label2);
        methodVisitor.visitLabel(label);
        if (context.writeDirect) {
            methodVisitor.visitVarInsn(25, context.var("out"));
            methodVisitor.visitVarInsn(21, context.var("seperator"));
            methodVisitor.visitVarInsn(25, Context.fieldName);
            methodVisitor.visitVarInsn(25, context.var("enum"));
            methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/Enum", ServiceProvider.NAME, "()Ljava/lang/String;");
            methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, SerializeWriter, "writeFieldValueStringWithDoubleQuote", "(CLjava/lang/String;Ljava/lang/String;)V");
        } else {
            methodVisitor.visitVarInsn(25, context.var("out"));
            methodVisitor.visitVarInsn(21, context.var("seperator"));
            methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, SerializeWriter, "write", "(I)V");
            methodVisitor.visitVarInsn(25, context.var("out"));
            methodVisitor.visitVarInsn(25, Context.fieldName);
            methodVisitor.visitInsn(3);
            methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, SerializeWriter, "writeFieldName", "(Ljava/lang/String;Z)V");
            methodVisitor.visitVarInsn(25, 1);
            methodVisitor.visitVarInsn(25, context.var("enum"));
            methodVisitor.visitVarInsn(25, Context.fieldName);
            methodVisitor.visitLdcInsn(Type.getType(ASMUtils.desc(fieldInfo.fieldClass)));
            methodVisitor.visitLdcInsn(Integer.valueOf(fieldInfo.serialzeFeatures));
            methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, JSONSerializer, "writeWithFieldName", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
        }
        _seperator(methodVisitor, context);
        methodVisitor.visitLabel(label2);
        methodVisitor.visitLabel(label3);
    }

    private void _int(Class<?> cls, MethodVisitor methodVisitor, FieldInfo fieldInfo, Context context, int i, char c) {
        Label label = new Label();
        _nameApply(methodVisitor, fieldInfo, context, label);
        _get(methodVisitor, context, fieldInfo);
        methodVisitor.visitVarInsn(54, i);
        _filters(methodVisitor, fieldInfo, context, label);
        methodVisitor.visitVarInsn(25, context.var("out"));
        methodVisitor.visitVarInsn(21, context.var("seperator"));
        methodVisitor.visitVarInsn(25, Context.fieldName);
        methodVisitor.visitVarInsn(21, i);
        String str = SerializeWriter;
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str, "writeFieldValue", "(CLjava/lang/String;" + c + ")V");
        _seperator(methodVisitor, context);
        methodVisitor.visitLabel(label);
    }

    private void _long(Class<?> cls, MethodVisitor methodVisitor, FieldInfo fieldInfo, Context context) {
        Label label = new Label();
        _nameApply(methodVisitor, fieldInfo, context, label);
        _get(methodVisitor, context, fieldInfo);
        methodVisitor.visitVarInsn(55, context.var("long", 2));
        _filters(methodVisitor, fieldInfo, context, label);
        methodVisitor.visitVarInsn(25, context.var("out"));
        methodVisitor.visitVarInsn(21, context.var("seperator"));
        methodVisitor.visitVarInsn(25, Context.fieldName);
        methodVisitor.visitVarInsn(22, context.var("long", 2));
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, SerializeWriter, "writeFieldValue", "(CLjava/lang/String;J)V");
        _seperator(methodVisitor, context);
        methodVisitor.visitLabel(label);
    }

    private void _float(Class<?> cls, MethodVisitor methodVisitor, FieldInfo fieldInfo, Context context) {
        Label label = new Label();
        _nameApply(methodVisitor, fieldInfo, context, label);
        _get(methodVisitor, context, fieldInfo);
        methodVisitor.visitVarInsn(56, context.var("float"));
        _filters(methodVisitor, fieldInfo, context, label);
        methodVisitor.visitVarInsn(25, context.var("out"));
        methodVisitor.visitVarInsn(21, context.var("seperator"));
        methodVisitor.visitVarInsn(25, Context.fieldName);
        methodVisitor.visitVarInsn(23, context.var("float"));
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, SerializeWriter, "writeFieldValue", "(CLjava/lang/String;F)V");
        _seperator(methodVisitor, context);
        methodVisitor.visitLabel(label);
    }

    private void _double(Class<?> cls, MethodVisitor methodVisitor, FieldInfo fieldInfo, Context context) {
        Label label = new Label();
        _nameApply(methodVisitor, fieldInfo, context, label);
        _get(methodVisitor, context, fieldInfo);
        methodVisitor.visitVarInsn(57, context.var("double", 2));
        _filters(methodVisitor, fieldInfo, context, label);
        methodVisitor.visitVarInsn(25, context.var("out"));
        methodVisitor.visitVarInsn(21, context.var("seperator"));
        methodVisitor.visitVarInsn(25, Context.fieldName);
        methodVisitor.visitVarInsn(24, context.var("double", 2));
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, SerializeWriter, "writeFieldValue", "(CLjava/lang/String;D)V");
        _seperator(methodVisitor, context);
        methodVisitor.visitLabel(label);
    }

    private void _get(MethodVisitor methodVisitor, Context context, FieldInfo fieldInfo) {
        Method method = fieldInfo.method;
        if (method != null) {
            methodVisitor.visitVarInsn(25, context.var("entity"));
            Class<?> declaringClass = method.getDeclaringClass();
            methodVisitor.visitMethodInsn(declaringClass.isInterface() ? Opcodes.INVOKEINTERFACE : Opcodes.INVOKEVIRTUAL, ASMUtils.type(declaringClass), method.getName(), ASMUtils.desc(method));
            if (!method.getReturnType().equals(fieldInfo.fieldClass)) {
                methodVisitor.visitTypeInsn(Opcodes.CHECKCAST, ASMUtils.type(fieldInfo.fieldClass));
                return;
            }
            return;
        }
        methodVisitor.visitVarInsn(25, context.var("entity"));
        Field field = fieldInfo.field;
        methodVisitor.visitFieldInsn(180, ASMUtils.type(fieldInfo.declaringClass), field.getName(), ASMUtils.desc(field.getType()));
        if (!field.getType().equals(fieldInfo.fieldClass)) {
            methodVisitor.visitTypeInsn(Opcodes.CHECKCAST, ASMUtils.type(fieldInfo.fieldClass));
        }
    }

    private void _decimal(Class<?> cls, MethodVisitor methodVisitor, FieldInfo fieldInfo, Context context) {
        Label label = new Label();
        _nameApply(methodVisitor, fieldInfo, context, label);
        _get(methodVisitor, context, fieldInfo);
        methodVisitor.visitVarInsn(58, context.var("decimal"));
        _filters(methodVisitor, fieldInfo, context, label);
        Label label2 = new Label();
        Label label3 = new Label();
        Label label4 = new Label();
        methodVisitor.visitLabel(label2);
        methodVisitor.visitVarInsn(25, context.var("decimal"));
        methodVisitor.visitJumpInsn(Opcodes.IFNONNULL, label3);
        _if_write_null(methodVisitor, fieldInfo, context);
        methodVisitor.visitJumpInsn(167, label4);
        methodVisitor.visitLabel(label3);
        methodVisitor.visitVarInsn(25, context.var("out"));
        methodVisitor.visitVarInsn(21, context.var("seperator"));
        methodVisitor.visitVarInsn(25, Context.fieldName);
        methodVisitor.visitVarInsn(25, context.var("decimal"));
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, SerializeWriter, "writeFieldValue", "(CLjava/lang/String;Ljava/math/BigDecimal;)V");
        _seperator(methodVisitor, context);
        methodVisitor.visitJumpInsn(167, label4);
        methodVisitor.visitLabel(label4);
        methodVisitor.visitLabel(label);
    }

    private void _string(Class<?> cls, MethodVisitor methodVisitor, FieldInfo fieldInfo, Context context) {
        Label label = new Label();
        if (fieldInfo.name.equals(context.beanInfo.typeKey)) {
            methodVisitor.visitVarInsn(25, 1);
            methodVisitor.visitVarInsn(25, 4);
            methodVisitor.visitVarInsn(25, 2);
            methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, JSONSerializer, "isWriteClassName", "(Ljava/lang/reflect/Type;Ljava/lang/Object;)Z");
            methodVisitor.visitJumpInsn(Opcodes.IFNE, label);
        }
        _nameApply(methodVisitor, fieldInfo, context, label);
        _get(methodVisitor, context, fieldInfo);
        methodVisitor.visitVarInsn(58, context.var("string"));
        _filters(methodVisitor, fieldInfo, context, label);
        Label label2 = new Label();
        Label label3 = new Label();
        methodVisitor.visitVarInsn(25, context.var("string"));
        methodVisitor.visitJumpInsn(Opcodes.IFNONNULL, label2);
        _if_write_null(methodVisitor, fieldInfo, context);
        methodVisitor.visitJumpInsn(167, label3);
        methodVisitor.visitLabel(label2);
        if ("trim".equals(fieldInfo.format)) {
            methodVisitor.visitVarInsn(25, context.var("string"));
            methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/String", "trim", "()Ljava/lang/String;");
            methodVisitor.visitVarInsn(58, context.var("string"));
        }
        if (context.writeDirect) {
            methodVisitor.visitVarInsn(25, context.var("out"));
            methodVisitor.visitVarInsn(21, context.var("seperator"));
            methodVisitor.visitVarInsn(25, Context.fieldName);
            methodVisitor.visitVarInsn(25, context.var("string"));
            methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, SerializeWriter, "writeFieldValueStringWithDoubleQuoteCheck", "(CLjava/lang/String;Ljava/lang/String;)V");
        } else {
            methodVisitor.visitVarInsn(25, context.var("out"));
            methodVisitor.visitVarInsn(21, context.var("seperator"));
            methodVisitor.visitVarInsn(25, Context.fieldName);
            methodVisitor.visitVarInsn(25, context.var("string"));
            methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, SerializeWriter, "writeFieldValue", "(CLjava/lang/String;Ljava/lang/String;)V");
        }
        _seperator(methodVisitor, context);
        methodVisitor.visitLabel(label3);
        methodVisitor.visitLabel(label);
    }

    private void _list(Class<?> cls, MethodVisitor methodVisitor, FieldInfo fieldInfo, Context context) {
        Label label;
        int i;
        int i2;
        int i3;
        Label label2;
        Label label3;
        Label label4;
        String str;
        Label label5;
        Label label6;
        Label label7;
        String str2;
        Label label8;
        String str3;
        MethodVisitor methodVisitor2 = methodVisitor;
        FieldInfo fieldInfo2 = fieldInfo;
        Context context2 = context;
        java.lang.reflect.Type collectionItemType = TypeUtils.getCollectionItemType(fieldInfo2.fieldType);
        Class<Serializable> cls2 = null;
        Class<Serializable> cls3 = collectionItemType instanceof Class ? (Class) collectionItemType : null;
        if (!(cls3 == Object.class || cls3 == Serializable.class)) {
            cls2 = cls3;
        }
        Label label9 = new Label();
        Label label10 = new Label();
        Label label11 = new Label();
        _nameApply(methodVisitor2, fieldInfo2, context2, label9);
        _get(methodVisitor2, context2, fieldInfo2);
        methodVisitor2.visitTypeInsn(Opcodes.CHECKCAST, "java/util/List");
        methodVisitor2.visitVarInsn(58, context2.var("list"));
        _filters(methodVisitor2, fieldInfo2, context2, label9);
        methodVisitor2.visitVarInsn(25, context2.var("list"));
        methodVisitor2.visitJumpInsn(Opcodes.IFNONNULL, label10);
        _if_write_null(methodVisitor2, fieldInfo2, context2);
        methodVisitor2.visitJumpInsn(167, label11);
        methodVisitor2.visitLabel(label10);
        methodVisitor2.visitVarInsn(25, context2.var("out"));
        methodVisitor2.visitVarInsn(21, context2.var("seperator"));
        methodVisitor2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, SerializeWriter, "write", "(I)V");
        _writeFieldName(methodVisitor2, context2);
        methodVisitor2.visitVarInsn(25, context2.var("list"));
        methodVisitor2.visitMethodInsn(Opcodes.INVOKEINTERFACE, "java/util/List", "size", "()I");
        methodVisitor2.visitVarInsn(54, context2.var("size"));
        Label label12 = new Label();
        Label label13 = new Label();
        Label label14 = label9;
        Label label15 = label11;
        methodVisitor2.visitVarInsn(21, context2.var("size"));
        methodVisitor2.visitInsn(3);
        methodVisitor2.visitJumpInsn(160, label12);
        methodVisitor2.visitVarInsn(25, context2.var("out"));
        methodVisitor2.visitLdcInsn(HttpUrl.PATH_SEGMENT_ENCODE_SET_URI);
        methodVisitor2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, SerializeWriter, "write", "(Ljava/lang/String;)V");
        methodVisitor2.visitJumpInsn(167, label13);
        methodVisitor2.visitLabel(label12);
        if (!context.nonContext) {
            methodVisitor2.visitVarInsn(25, 1);
            methodVisitor2.visitVarInsn(25, context2.var("list"));
            methodVisitor2.visitVarInsn(25, Context.fieldName);
            methodVisitor2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, JSONSerializer, "setContext", "(Ljava/lang/Object;Ljava/lang/Object;)V");
        }
        if (collectionItemType != String.class || !context.writeDirect) {
            methodVisitor2.visitVarInsn(25, context2.var("out"));
            methodVisitor2.visitVarInsn(16, 91);
            methodVisitor2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, SerializeWriter, "write", "(I)V");
            Label label16 = new Label();
            Label label17 = new Label();
            Label label18 = new Label();
            methodVisitor2.visitInsn(3);
            label = label13;
            java.lang.reflect.Type type = collectionItemType;
            methodVisitor2.visitVarInsn(54, context2.var("i"));
            methodVisitor2.visitLabel(label16);
            methodVisitor2.visitVarInsn(21, context2.var("i"));
            methodVisitor2.visitVarInsn(21, context2.var("size"));
            methodVisitor2.visitJumpInsn(162, label18);
            methodVisitor2.visitVarInsn(21, context2.var("i"));
            methodVisitor2.visitJumpInsn(Opcodes.IFEQ, label17);
            methodVisitor2.visitVarInsn(25, context2.var("out"));
            methodVisitor2.visitVarInsn(16, 44);
            methodVisitor2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, SerializeWriter, "write", "(I)V");
            methodVisitor2.visitLabel(label17);
            methodVisitor2.visitVarInsn(25, context2.var("list"));
            methodVisitor2.visitVarInsn(21, context2.var("i"));
            methodVisitor2.visitMethodInsn(Opcodes.INVOKEINTERFACE, "java/util/List", "get", "(I)Ljava/lang/Object;");
            methodVisitor2.visitVarInsn(58, context2.var("list_item"));
            Label label19 = new Label();
            Label label20 = new Label();
            methodVisitor2.visitVarInsn(25, context2.var("list_item"));
            methodVisitor2.visitJumpInsn(Opcodes.IFNONNULL, label20);
            methodVisitor2.visitVarInsn(25, context2.var("out"));
            String str4 = "(I)V";
            methodVisitor2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, SerializeWriter, "writeNull", "()V");
            methodVisitor2.visitJumpInsn(167, label19);
            methodVisitor2.visitLabel(label20);
            Label label21 = new Label();
            Label label22 = new Label();
            String str5 = "out";
            if (cls2 == null || !Modifier.isPublic(cls2.getModifiers())) {
                label3 = label16;
                label2 = label19;
                label6 = label21;
                str = "write";
                label4 = label18;
                label5 = label22;
            } else {
                label4 = label18;
                methodVisitor2.visitVarInsn(25, context2.var("list_item"));
                label3 = label16;
                label2 = label19;
                methodVisitor2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/Object", "getClass", "()Ljava/lang/Class;");
                methodVisitor2.visitLdcInsn(Type.getType(ASMUtils.desc((Class<?>) cls2)));
                methodVisitor2.visitJumpInsn(166, label22);
                _getListFieldItemSer(context2, methodVisitor2, fieldInfo2, cls2);
                methodVisitor2.visitVarInsn(58, context2.var("list_item_desc"));
                Label label23 = new Label();
                Label label24 = new Label();
                if (context.writeDirect) {
                    if (!context.nonContext || !context.writeDirect) {
                        label7 = label22;
                        str3 = "write";
                    } else {
                        label7 = label22;
                        str3 = "writeDirectNonContext";
                    }
                    label8 = label21;
                    methodVisitor2.visitVarInsn(25, context2.var("list_item_desc"));
                    methodVisitor2.visitTypeInsn(Opcodes.INSTANCEOF, JavaBeanSerializer);
                    methodVisitor2.visitJumpInsn(Opcodes.IFEQ, label23);
                    methodVisitor2.visitVarInsn(25, context2.var("list_item_desc"));
                    str2 = "write";
                    methodVisitor2.visitTypeInsn(Opcodes.CHECKCAST, JavaBeanSerializer);
                    methodVisitor2.visitVarInsn(25, 1);
                    methodVisitor2.visitVarInsn(25, context2.var("list_item"));
                    if (context.nonContext) {
                        methodVisitor2.visitInsn(1);
                    } else {
                        methodVisitor2.visitVarInsn(21, context2.var("i"));
                        methodVisitor2.visitMethodInsn(184, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;");
                    }
                    methodVisitor2.visitLdcInsn(Type.getType(ASMUtils.desc((Class<?>) cls2)));
                    methodVisitor2.visitLdcInsn(Integer.valueOf(fieldInfo2.serialzeFeatures));
                    String str6 = JavaBeanSerializer;
                    methodVisitor2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str6, str3, "(L" + JSONSerializer + ";Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
                    methodVisitor2.visitJumpInsn(167, label24);
                    methodVisitor2.visitLabel(label23);
                } else {
                    label8 = label21;
                    label7 = label22;
                    str2 = "write";
                }
                methodVisitor2.visitVarInsn(25, context2.var("list_item_desc"));
                methodVisitor2.visitVarInsn(25, 1);
                methodVisitor2.visitVarInsn(25, context2.var("list_item"));
                if (context.nonContext) {
                    methodVisitor2.visitInsn(1);
                } else {
                    methodVisitor2.visitVarInsn(21, context2.var("i"));
                    methodVisitor2.visitMethodInsn(184, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;");
                }
                methodVisitor2.visitLdcInsn(Type.getType(ASMUtils.desc((Class<?>) cls2)));
                methodVisitor2.visitLdcInsn(Integer.valueOf(fieldInfo2.serialzeFeatures));
                String str7 = ObjectSerializer;
                str = str2;
                methodVisitor2.visitMethodInsn(Opcodes.INVOKEINTERFACE, str7, str, "(L" + JSONSerializer + ";Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
                methodVisitor2.visitLabel(label24);
                label6 = label8;
                methodVisitor2.visitJumpInsn(167, label6);
                label5 = label7;
            }
            methodVisitor2.visitLabel(label5);
            methodVisitor2.visitVarInsn(25, 1);
            methodVisitor2.visitVarInsn(25, context2.var("list_item"));
            if (context.nonContext) {
                methodVisitor2.visitInsn(1);
            } else {
                methodVisitor2.visitVarInsn(21, context2.var("i"));
                methodVisitor2.visitMethodInsn(184, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;");
            }
            if (cls2 == null || !Modifier.isPublic(cls2.getModifiers())) {
                methodVisitor2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, JSONSerializer, "writeWithFieldName", "(Ljava/lang/Object;Ljava/lang/Object;)V");
            } else {
                methodVisitor2.visitLdcInsn(Type.getType(ASMUtils.desc((Class<?>) (Class) type)));
                methodVisitor2.visitLdcInsn(Integer.valueOf(fieldInfo2.serialzeFeatures));
                methodVisitor2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, JSONSerializer, "writeWithFieldName", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
            }
            methodVisitor2.visitLabel(label6);
            methodVisitor2.visitLabel(label2);
            i3 = 1;
            methodVisitor2.visitIincInsn(context2.var("i"), 1);
            methodVisitor2.visitJumpInsn(167, label3);
            methodVisitor2.visitLabel(label4);
            i2 = 25;
            methodVisitor2.visitVarInsn(25, context2.var(str5));
            methodVisitor2.visitVarInsn(16, 93);
            i = Opcodes.INVOKEVIRTUAL;
            methodVisitor2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, SerializeWriter, str, str4);
        } else {
            methodVisitor2.visitVarInsn(25, context2.var("out"));
            methodVisitor2.visitVarInsn(25, context2.var("list"));
            methodVisitor2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, SerializeWriter, "write", "(Ljava/util/List;)V");
            label = label13;
            i3 = 1;
            i2 = 25;
            i = Opcodes.INVOKEVIRTUAL;
        }
        methodVisitor2.visitVarInsn(i2, i3);
        methodVisitor2.visitMethodInsn(i, JSONSerializer, "popContext", "()V");
        methodVisitor2.visitLabel(label);
        _seperator(methodVisitor2, context2);
        methodVisitor2.visitLabel(label15);
        methodVisitor2.visitLabel(label14);
    }

    private void _filters(MethodVisitor methodVisitor, FieldInfo fieldInfo, Context context, Label label) {
        if (fieldInfo.fieldTransient) {
            methodVisitor.visitVarInsn(25, context.var("out"));
            methodVisitor.visitLdcInsn(Integer.valueOf(SerializerFeature.SkipTransientField.mask));
            methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, SerializeWriter, "isEnabled", "(I)Z");
            methodVisitor.visitJumpInsn(Opcodes.IFNE, label);
        }
        _notWriteDefault(methodVisitor, fieldInfo, context, label);
        if (!context.writeDirect) {
            _apply(methodVisitor, fieldInfo, context);
            methodVisitor.visitJumpInsn(Opcodes.IFEQ, label);
            _processKey(methodVisitor, fieldInfo, context);
            _processValue(methodVisitor, fieldInfo, context, label);
        }
    }

    private void _nameApply(MethodVisitor methodVisitor, FieldInfo fieldInfo, Context context, Label label) {
        if (!context.writeDirect) {
            methodVisitor.visitVarInsn(25, 0);
            methodVisitor.visitVarInsn(25, 1);
            methodVisitor.visitVarInsn(25, 2);
            methodVisitor.visitVarInsn(25, Context.fieldName);
            String str = JavaBeanSerializer;
            methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str, "applyName", "(L" + JSONSerializer + ";Ljava/lang/Object;Ljava/lang/String;)Z");
            methodVisitor.visitJumpInsn(Opcodes.IFEQ, label);
            _labelApply(methodVisitor, fieldInfo, context, label);
        }
        if (fieldInfo.field == null) {
            methodVisitor.visitVarInsn(25, context.var("out"));
            methodVisitor.visitLdcInsn(Integer.valueOf(SerializerFeature.IgnoreNonFieldGetter.mask));
            methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, SerializeWriter, "isEnabled", "(I)Z");
            methodVisitor.visitJumpInsn(Opcodes.IFNE, label);
        }
    }

    private void _labelApply(MethodVisitor methodVisitor, FieldInfo fieldInfo, Context context, Label label) {
        methodVisitor.visitVarInsn(25, 0);
        methodVisitor.visitVarInsn(25, 1);
        methodVisitor.visitLdcInsn(fieldInfo.label);
        String str = JavaBeanSerializer;
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str, "applyLabel", "(L" + JSONSerializer + ";Ljava/lang/String;)Z");
        methodVisitor.visitJumpInsn(Opcodes.IFEQ, label);
    }

    private void _writeObject(MethodVisitor methodVisitor, FieldInfo fieldInfo, Context context, Label label) {
        String str;
        Label label2;
        Label label3;
        MethodVisitor methodVisitor2 = methodVisitor;
        FieldInfo fieldInfo2 = fieldInfo;
        Context context2 = context;
        String format = fieldInfo.getFormat();
        Class<?> cls = fieldInfo2.fieldClass;
        Label label4 = new Label();
        if (context.writeDirect) {
            methodVisitor2.visitVarInsn(25, context2.var("object"));
        } else {
            methodVisitor2.visitVarInsn(25, Context.processValue);
        }
        methodVisitor2.visitInsn(89);
        methodVisitor2.visitVarInsn(58, context2.var("object"));
        methodVisitor2.visitJumpInsn(Opcodes.IFNONNULL, label4);
        _if_write_null(methodVisitor, fieldInfo, context);
        methodVisitor2.visitJumpInsn(167, label);
        methodVisitor2.visitLabel(label4);
        methodVisitor2.visitVarInsn(25, context2.var("out"));
        methodVisitor2.visitVarInsn(21, context2.var("seperator"));
        methodVisitor2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, SerializeWriter, "write", "(I)V");
        _writeFieldName(methodVisitor2, context2);
        Label label5 = new Label();
        Label label6 = new Label();
        if (!Modifier.isPublic(cls.getModifiers()) || ParserConfig.isPrimitive2(cls)) {
            str = format;
            label3 = label5;
            label2 = label6;
        } else {
            methodVisitor2.visitVarInsn(25, context2.var("object"));
            methodVisitor2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/Object", "getClass", "()Ljava/lang/Class;");
            methodVisitor2.visitLdcInsn(Type.getType(ASMUtils.desc(cls)));
            methodVisitor2.visitJumpInsn(166, label6);
            _getFieldSer(context2, methodVisitor2, fieldInfo2);
            methodVisitor2.visitVarInsn(58, context2.var("fied_ser"));
            Label label7 = new Label();
            Label label8 = new Label();
            methodVisitor2.visitVarInsn(25, context2.var("fied_ser"));
            methodVisitor2.visitTypeInsn(Opcodes.INSTANCEOF, JavaBeanSerializer);
            methodVisitor2.visitJumpInsn(Opcodes.IFEQ, label7);
            boolean z = (fieldInfo2.serialzeFeatures & SerializerFeature.DisableCircularReferenceDetect.mask) != 0;
            boolean z2 = (SerializerFeature.BeanToArray.mask & fieldInfo2.serialzeFeatures) != 0;
            String str2 = (z || (context.nonContext && context.writeDirect)) ? z2 ? "writeAsArrayNonContext" : "writeDirectNonContext" : z2 ? "writeAsArray" : "write";
            methodVisitor2.visitVarInsn(25, context2.var("fied_ser"));
            str = format;
            methodVisitor2.visitTypeInsn(Opcodes.CHECKCAST, JavaBeanSerializer);
            methodVisitor2.visitVarInsn(25, 1);
            methodVisitor2.visitVarInsn(25, context2.var("object"));
            methodVisitor2.visitVarInsn(25, Context.fieldName);
            methodVisitor2.visitVarInsn(25, 0);
            String access$300 = context.className;
            methodVisitor2.visitFieldInsn(180, access$300, fieldInfo2.name + "_asm_fieldType", "Ljava/lang/reflect/Type;");
            methodVisitor2.visitLdcInsn(Integer.valueOf(fieldInfo2.serialzeFeatures));
            String str3 = JavaBeanSerializer;
            methodVisitor2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str3, str2, "(L" + JSONSerializer + ";Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
            methodVisitor2.visitJumpInsn(167, label8);
            methodVisitor2.visitLabel(label7);
            methodVisitor2.visitVarInsn(25, context2.var("fied_ser"));
            methodVisitor2.visitVarInsn(25, 1);
            methodVisitor2.visitVarInsn(25, context2.var("object"));
            methodVisitor2.visitVarInsn(25, Context.fieldName);
            methodVisitor2.visitVarInsn(25, 0);
            String access$3002 = context.className;
            methodVisitor2.visitFieldInsn(180, access$3002, fieldInfo2.name + "_asm_fieldType", "Ljava/lang/reflect/Type;");
            methodVisitor2.visitLdcInsn(Integer.valueOf(fieldInfo2.serialzeFeatures));
            String str4 = ObjectSerializer;
            methodVisitor2.visitMethodInsn(Opcodes.INVOKEINTERFACE, str4, "write", "(L" + JSONSerializer + ";Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
            methodVisitor2.visitLabel(label8);
            label3 = label5;
            methodVisitor2.visitJumpInsn(167, label3);
            label2 = label6;
        }
        methodVisitor2.visitLabel(label2);
        methodVisitor2.visitVarInsn(25, 1);
        if (context.writeDirect) {
            methodVisitor2.visitVarInsn(25, context2.var("object"));
        } else {
            methodVisitor2.visitVarInsn(25, Context.processValue);
        }
        if (str != null) {
            methodVisitor2.visitLdcInsn(str);
            methodVisitor2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, JSONSerializer, "writeWithFormat", "(Ljava/lang/Object;Ljava/lang/String;)V");
        } else {
            methodVisitor2.visitVarInsn(25, Context.fieldName);
            if (!(fieldInfo2.fieldType instanceof Class) || !((Class) fieldInfo2.fieldType).isPrimitive()) {
                if (fieldInfo2.fieldClass == String.class) {
                    methodVisitor2.visitLdcInsn(Type.getType(ASMUtils.desc((Class<?>) String.class)));
                } else {
                    methodVisitor2.visitVarInsn(25, 0);
                    String access$3003 = context.className;
                    methodVisitor2.visitFieldInsn(180, access$3003, fieldInfo2.name + "_asm_fieldType", "Ljava/lang/reflect/Type;");
                }
                methodVisitor2.visitLdcInsn(Integer.valueOf(fieldInfo2.serialzeFeatures));
                methodVisitor2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, JSONSerializer, "writeWithFieldName", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
            } else {
                methodVisitor2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, JSONSerializer, "writeWithFieldName", "(Ljava/lang/Object;Ljava/lang/Object;)V");
            }
        }
        methodVisitor2.visitLabel(label3);
        _seperator(methodVisitor2, context2);
    }

    private void _before(MethodVisitor methodVisitor, Context context) {
        methodVisitor.visitVarInsn(25, 0);
        methodVisitor.visitVarInsn(25, 1);
        methodVisitor.visitVarInsn(25, 2);
        methodVisitor.visitVarInsn(21, context.var("seperator"));
        String str = JavaBeanSerializer;
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str, "writeBefore", "(L" + JSONSerializer + ";Ljava/lang/Object;C)C");
        methodVisitor.visitVarInsn(54, context.var("seperator"));
    }

    private void _after(MethodVisitor methodVisitor, Context context) {
        methodVisitor.visitVarInsn(25, 0);
        methodVisitor.visitVarInsn(25, 1);
        methodVisitor.visitVarInsn(25, 2);
        methodVisitor.visitVarInsn(21, context.var("seperator"));
        String str = JavaBeanSerializer;
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str, "writeAfter", "(L" + JSONSerializer + ";Ljava/lang/Object;C)C");
        methodVisitor.visitVarInsn(54, context.var("seperator"));
    }

    private void _notWriteDefault(MethodVisitor methodVisitor, FieldInfo fieldInfo, Context context, Label label) {
        if (!context.writeDirect) {
            Label label2 = new Label();
            methodVisitor.visitVarInsn(21, context.var("notWriteDefaultValue"));
            methodVisitor.visitJumpInsn(Opcodes.IFEQ, label2);
            Class<?> cls = fieldInfo.fieldClass;
            if (cls == Boolean.TYPE) {
                methodVisitor.visitVarInsn(21, context.var("boolean"));
                methodVisitor.visitJumpInsn(Opcodes.IFEQ, label);
            } else if (cls == Byte.TYPE) {
                methodVisitor.visitVarInsn(21, context.var("byte"));
                methodVisitor.visitJumpInsn(Opcodes.IFEQ, label);
            } else if (cls == Short.TYPE) {
                methodVisitor.visitVarInsn(21, context.var("short"));
                methodVisitor.visitJumpInsn(Opcodes.IFEQ, label);
            } else if (cls == Integer.TYPE) {
                methodVisitor.visitVarInsn(21, context.var("int"));
                methodVisitor.visitJumpInsn(Opcodes.IFEQ, label);
            } else if (cls == Long.TYPE) {
                methodVisitor.visitVarInsn(22, context.var("long"));
                methodVisitor.visitInsn(9);
                methodVisitor.visitInsn(148);
                methodVisitor.visitJumpInsn(Opcodes.IFEQ, label);
            } else if (cls == Float.TYPE) {
                methodVisitor.visitVarInsn(23, context.var("float"));
                methodVisitor.visitInsn(11);
                methodVisitor.visitInsn(149);
                methodVisitor.visitJumpInsn(Opcodes.IFEQ, label);
            } else if (cls == Double.TYPE) {
                methodVisitor.visitVarInsn(24, context.var("double"));
                methodVisitor.visitInsn(14);
                methodVisitor.visitInsn(Opcodes.DCMPL);
                methodVisitor.visitJumpInsn(Opcodes.IFEQ, label);
            }
            methodVisitor.visitLabel(label2);
        }
    }

    private void _apply(MethodVisitor methodVisitor, FieldInfo fieldInfo, Context context) {
        Class<?> cls = fieldInfo.fieldClass;
        methodVisitor.visitVarInsn(25, 0);
        methodVisitor.visitVarInsn(25, 1);
        methodVisitor.visitVarInsn(25, 2);
        methodVisitor.visitVarInsn(25, Context.fieldName);
        if (cls == Byte.TYPE) {
            methodVisitor.visitVarInsn(21, context.var("byte"));
            methodVisitor.visitMethodInsn(184, "java/lang/Byte", "valueOf", "(B)Ljava/lang/Byte;");
        } else if (cls == Short.TYPE) {
            methodVisitor.visitVarInsn(21, context.var("short"));
            methodVisitor.visitMethodInsn(184, "java/lang/Short", "valueOf", "(S)Ljava/lang/Short;");
        } else if (cls == Integer.TYPE) {
            methodVisitor.visitVarInsn(21, context.var("int"));
            methodVisitor.visitMethodInsn(184, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;");
        } else if (cls == Character.TYPE) {
            methodVisitor.visitVarInsn(21, context.var("char"));
            methodVisitor.visitMethodInsn(184, "java/lang/Character", "valueOf", "(C)Ljava/lang/Character;");
        } else if (cls == Long.TYPE) {
            methodVisitor.visitVarInsn(22, context.var("long", 2));
            methodVisitor.visitMethodInsn(184, "java/lang/Long", "valueOf", "(J)Ljava/lang/Long;");
        } else if (cls == Float.TYPE) {
            methodVisitor.visitVarInsn(23, context.var("float"));
            methodVisitor.visitMethodInsn(184, "java/lang/Float", "valueOf", "(F)Ljava/lang/Float;");
        } else if (cls == Double.TYPE) {
            methodVisitor.visitVarInsn(24, context.var("double", 2));
            methodVisitor.visitMethodInsn(184, "java/lang/Double", "valueOf", "(D)Ljava/lang/Double;");
        } else if (cls == Boolean.TYPE) {
            methodVisitor.visitVarInsn(21, context.var("boolean"));
            methodVisitor.visitMethodInsn(184, "java/lang/Boolean", "valueOf", "(Z)Ljava/lang/Boolean;");
        } else if (cls == BigDecimal.class) {
            methodVisitor.visitVarInsn(25, context.var("decimal"));
        } else if (cls == String.class) {
            methodVisitor.visitVarInsn(25, context.var("string"));
        } else if (cls.isEnum()) {
            methodVisitor.visitVarInsn(25, context.var("enum"));
        } else if (List.class.isAssignableFrom(cls)) {
            methodVisitor.visitVarInsn(25, context.var("list"));
        } else {
            methodVisitor.visitVarInsn(25, context.var("object"));
        }
        String str = JavaBeanSerializer;
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str, "apply", "(L" + JSONSerializer + ";Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;)Z");
    }

    private void _processValue(MethodVisitor methodVisitor, FieldInfo fieldInfo, Context context, Label label) {
        MethodVisitor methodVisitor2 = methodVisitor;
        FieldInfo fieldInfo2 = fieldInfo;
        Context context2 = context;
        Label label2 = new Label();
        Class<?> cls = fieldInfo2.fieldClass;
        if (cls.isPrimitive()) {
            Label label3 = new Label();
            methodVisitor2.visitVarInsn(21, context2.var("checkValue"));
            methodVisitor2.visitJumpInsn(Opcodes.IFNE, label3);
            methodVisitor2.visitInsn(1);
            methodVisitor2.visitInsn(89);
            methodVisitor2.visitVarInsn(58, Context.original);
            methodVisitor2.visitVarInsn(58, Context.processValue);
            methodVisitor2.visitJumpInsn(167, label2);
            methodVisitor2.visitLabel(label3);
        }
        methodVisitor2.visitVarInsn(25, 0);
        methodVisitor2.visitVarInsn(25, 1);
        methodVisitor2.visitVarInsn(25, 0);
        methodVisitor2.visitLdcInsn(Integer.valueOf(context2.getFieldOrinal(fieldInfo2.name)));
        String str = JavaBeanSerializer;
        methodVisitor2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str, "getBeanContext", "(I)" + ASMUtils.desc((Class<?>) BeanContext.class));
        methodVisitor2.visitVarInsn(25, 2);
        methodVisitor2.visitVarInsn(25, Context.fieldName);
        if (cls == Byte.TYPE) {
            methodVisitor2.visitVarInsn(21, context2.var("byte"));
            methodVisitor2.visitMethodInsn(184, "java/lang/Byte", "valueOf", "(B)Ljava/lang/Byte;");
            methodVisitor2.visitInsn(89);
            methodVisitor2.visitVarInsn(58, Context.original);
        } else if (cls == Short.TYPE) {
            methodVisitor2.visitVarInsn(21, context2.var("short"));
            methodVisitor2.visitMethodInsn(184, "java/lang/Short", "valueOf", "(S)Ljava/lang/Short;");
            methodVisitor2.visitInsn(89);
            methodVisitor2.visitVarInsn(58, Context.original);
        } else if (cls == Integer.TYPE) {
            methodVisitor2.visitVarInsn(21, context2.var("int"));
            methodVisitor2.visitMethodInsn(184, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;");
            methodVisitor2.visitInsn(89);
            methodVisitor2.visitVarInsn(58, Context.original);
        } else if (cls == Character.TYPE) {
            methodVisitor2.visitVarInsn(21, context2.var("char"));
            methodVisitor2.visitMethodInsn(184, "java/lang/Character", "valueOf", "(C)Ljava/lang/Character;");
            methodVisitor2.visitInsn(89);
            methodVisitor2.visitVarInsn(58, Context.original);
        } else if (cls == Long.TYPE) {
            methodVisitor2.visitVarInsn(22, context2.var("long", 2));
            methodVisitor2.visitMethodInsn(184, "java/lang/Long", "valueOf", "(J)Ljava/lang/Long;");
            methodVisitor2.visitInsn(89);
            methodVisitor2.visitVarInsn(58, Context.original);
        } else if (cls == Float.TYPE) {
            methodVisitor2.visitVarInsn(23, context2.var("float"));
            methodVisitor2.visitMethodInsn(184, "java/lang/Float", "valueOf", "(F)Ljava/lang/Float;");
            methodVisitor2.visitInsn(89);
            methodVisitor2.visitVarInsn(58, Context.original);
        } else if (cls == Double.TYPE) {
            methodVisitor2.visitVarInsn(24, context2.var("double", 2));
            methodVisitor2.visitMethodInsn(184, "java/lang/Double", "valueOf", "(D)Ljava/lang/Double;");
            methodVisitor2.visitInsn(89);
            methodVisitor2.visitVarInsn(58, Context.original);
        } else if (cls == Boolean.TYPE) {
            methodVisitor2.visitVarInsn(21, context2.var("boolean"));
            methodVisitor2.visitMethodInsn(184, "java/lang/Boolean", "valueOf", "(Z)Ljava/lang/Boolean;");
            methodVisitor2.visitInsn(89);
            methodVisitor2.visitVarInsn(58, Context.original);
        } else if (cls == BigDecimal.class) {
            methodVisitor2.visitVarInsn(25, context2.var("decimal"));
            methodVisitor2.visitVarInsn(58, Context.original);
            methodVisitor2.visitVarInsn(25, Context.original);
        } else if (cls == String.class) {
            methodVisitor2.visitVarInsn(25, context2.var("string"));
            methodVisitor2.visitVarInsn(58, Context.original);
            methodVisitor2.visitVarInsn(25, Context.original);
        } else if (cls.isEnum()) {
            methodVisitor2.visitVarInsn(25, context2.var("enum"));
            methodVisitor2.visitVarInsn(58, Context.original);
            methodVisitor2.visitVarInsn(25, Context.original);
        } else if (List.class.isAssignableFrom(cls)) {
            methodVisitor2.visitVarInsn(25, context2.var("list"));
            methodVisitor2.visitVarInsn(58, Context.original);
            methodVisitor2.visitVarInsn(25, Context.original);
        } else {
            methodVisitor2.visitVarInsn(25, context2.var("object"));
            methodVisitor2.visitVarInsn(58, Context.original);
            methodVisitor2.visitVarInsn(25, Context.original);
        }
        String str2 = JavaBeanSerializer;
        methodVisitor2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str2, "processValue", "(L" + JSONSerializer + ";" + ASMUtils.desc((Class<?>) BeanContext.class) + "Ljava/lang/Object;Ljava/lang/String;" + "Ljava/lang/Object;" + ")Ljava/lang/Object;");
        methodVisitor2.visitVarInsn(58, Context.processValue);
        methodVisitor2.visitVarInsn(25, Context.original);
        methodVisitor2.visitVarInsn(25, Context.processValue);
        methodVisitor2.visitJumpInsn(165, label2);
        _writeObject(methodVisitor, fieldInfo, context, label);
        methodVisitor2.visitJumpInsn(167, label);
        methodVisitor2.visitLabel(label2);
    }

    private void _processKey(MethodVisitor methodVisitor, FieldInfo fieldInfo, Context context) {
        Label label = new Label();
        methodVisitor.visitVarInsn(21, context.var("hasNameFilters"));
        methodVisitor.visitJumpInsn(Opcodes.IFEQ, label);
        Class<?> cls = fieldInfo.fieldClass;
        methodVisitor.visitVarInsn(25, 0);
        methodVisitor.visitVarInsn(25, 1);
        methodVisitor.visitVarInsn(25, 2);
        methodVisitor.visitVarInsn(25, Context.fieldName);
        if (cls == Byte.TYPE) {
            methodVisitor.visitVarInsn(21, context.var("byte"));
            methodVisitor.visitMethodInsn(184, "java/lang/Byte", "valueOf", "(B)Ljava/lang/Byte;");
        } else if (cls == Short.TYPE) {
            methodVisitor.visitVarInsn(21, context.var("short"));
            methodVisitor.visitMethodInsn(184, "java/lang/Short", "valueOf", "(S)Ljava/lang/Short;");
        } else if (cls == Integer.TYPE) {
            methodVisitor.visitVarInsn(21, context.var("int"));
            methodVisitor.visitMethodInsn(184, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;");
        } else if (cls == Character.TYPE) {
            methodVisitor.visitVarInsn(21, context.var("char"));
            methodVisitor.visitMethodInsn(184, "java/lang/Character", "valueOf", "(C)Ljava/lang/Character;");
        } else if (cls == Long.TYPE) {
            methodVisitor.visitVarInsn(22, context.var("long", 2));
            methodVisitor.visitMethodInsn(184, "java/lang/Long", "valueOf", "(J)Ljava/lang/Long;");
        } else if (cls == Float.TYPE) {
            methodVisitor.visitVarInsn(23, context.var("float"));
            methodVisitor.visitMethodInsn(184, "java/lang/Float", "valueOf", "(F)Ljava/lang/Float;");
        } else if (cls == Double.TYPE) {
            methodVisitor.visitVarInsn(24, context.var("double", 2));
            methodVisitor.visitMethodInsn(184, "java/lang/Double", "valueOf", "(D)Ljava/lang/Double;");
        } else if (cls == Boolean.TYPE) {
            methodVisitor.visitVarInsn(21, context.var("boolean"));
            methodVisitor.visitMethodInsn(184, "java/lang/Boolean", "valueOf", "(Z)Ljava/lang/Boolean;");
        } else if (cls == BigDecimal.class) {
            methodVisitor.visitVarInsn(25, context.var("decimal"));
        } else if (cls == String.class) {
            methodVisitor.visitVarInsn(25, context.var("string"));
        } else if (cls.isEnum()) {
            methodVisitor.visitVarInsn(25, context.var("enum"));
        } else if (List.class.isAssignableFrom(cls)) {
            methodVisitor.visitVarInsn(25, context.var("list"));
        } else {
            methodVisitor.visitVarInsn(25, context.var("object"));
        }
        String str = JavaBeanSerializer;
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str, "processKey", "(L" + JSONSerializer + ";Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;)Ljava/lang/String;");
        methodVisitor.visitVarInsn(58, Context.fieldName);
        methodVisitor.visitLabel(label);
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x0094  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x00e8  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x012f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void _if_write_null(com.alibaba.fastjson.asm.MethodVisitor r12, com.alibaba.fastjson.util.FieldInfo r13, com.alibaba.fastjson.serializer.ASMSerializerFactory.Context r14) {
        /*
            r11 = this;
            java.lang.Class<?> r0 = r13.fieldClass
            com.alibaba.fastjson.asm.Label r1 = new com.alibaba.fastjson.asm.Label
            r1.<init>()
            com.alibaba.fastjson.asm.Label r2 = new com.alibaba.fastjson.asm.Label
            r2.<init>()
            com.alibaba.fastjson.asm.Label r3 = new com.alibaba.fastjson.asm.Label
            r3.<init>()
            com.alibaba.fastjson.asm.Label r4 = new com.alibaba.fastjson.asm.Label
            r4.<init>()
            r12.visitLabel(r1)
            com.alibaba.fastjson.annotation.JSONField r13 = r13.getAnnotation()
            r1 = 0
            if (r13 == 0) goto L_0x0029
            com.alibaba.fastjson.serializer.SerializerFeature[] r13 = r13.serialzeFeatures()
            int r13 = com.alibaba.fastjson.serializer.SerializerFeature.of(r13)
            goto L_0x002a
        L_0x0029:
            r13 = 0
        L_0x002a:
            com.alibaba.fastjson.serializer.SerializeBeanInfo r5 = r14.beanInfo
            com.alibaba.fastjson.annotation.JSONType r5 = r5.jsonType
            if (r5 == 0) goto L_0x003b
            com.alibaba.fastjson.serializer.SerializerFeature[] r5 = r5.serialzeFeatures()
            int r5 = com.alibaba.fastjson.serializer.SerializerFeature.of(r5)
            r13 = r13 | r5
        L_0x003b:
            java.lang.Class<java.lang.String> r5 = java.lang.String.class
            if (r0 != r5) goto L_0x004d
            com.alibaba.fastjson.serializer.SerializerFeature r5 = com.alibaba.fastjson.serializer.SerializerFeature.WriteMapNullValue
            int r5 = r5.getMask()
            com.alibaba.fastjson.serializer.SerializerFeature r6 = com.alibaba.fastjson.serializer.SerializerFeature.WriteNullStringAsEmpty
            int r6 = r6.getMask()
        L_0x004b:
            r5 = r5 | r6
            goto L_0x008a
        L_0x004d:
            java.lang.Class<java.lang.Number> r5 = java.lang.Number.class
            boolean r5 = r5.isAssignableFrom(r0)
            if (r5 == 0) goto L_0x0062
            com.alibaba.fastjson.serializer.SerializerFeature r5 = com.alibaba.fastjson.serializer.SerializerFeature.WriteMapNullValue
            int r5 = r5.getMask()
            com.alibaba.fastjson.serializer.SerializerFeature r6 = com.alibaba.fastjson.serializer.SerializerFeature.WriteNullNumberAsZero
            int r6 = r6.getMask()
            goto L_0x004b
        L_0x0062:
            java.lang.Class<java.util.Collection> r5 = java.util.Collection.class
            boolean r5 = r5.isAssignableFrom(r0)
            if (r5 == 0) goto L_0x0077
            com.alibaba.fastjson.serializer.SerializerFeature r5 = com.alibaba.fastjson.serializer.SerializerFeature.WriteMapNullValue
            int r5 = r5.getMask()
            com.alibaba.fastjson.serializer.SerializerFeature r6 = com.alibaba.fastjson.serializer.SerializerFeature.WriteNullListAsEmpty
            int r6 = r6.getMask()
            goto L_0x004b
        L_0x0077:
            java.lang.Class<java.lang.Boolean> r5 = java.lang.Boolean.class
            if (r5 != r0) goto L_0x0088
            com.alibaba.fastjson.serializer.SerializerFeature r5 = com.alibaba.fastjson.serializer.SerializerFeature.WriteMapNullValue
            int r5 = r5.getMask()
            com.alibaba.fastjson.serializer.SerializerFeature r6 = com.alibaba.fastjson.serializer.SerializerFeature.WriteNullBooleanAsFalse
            int r6 = r6.getMask()
            goto L_0x004b
        L_0x0088:
            int r5 = com.alibaba.fastjson.serializer.SerializerFeature.WRITE_MAP_NULL_FEATURES
        L_0x008a:
            r6 = r13 & r5
            r7 = 182(0xb6, float:2.55E-43)
            java.lang.String r8 = "out"
            r9 = 25
            if (r6 != 0) goto L_0x00b0
            int r6 = r14.var(r8)
            r12.visitVarInsn(r9, r6)
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            r12.visitLdcInsn(r5)
            java.lang.String r5 = SerializeWriter
            java.lang.String r6 = "isEnabled"
            java.lang.String r10 = "(I)Z"
            r12.visitMethodInsn(r7, r5, r6, r10)
            r5 = 153(0x99, float:2.14E-43)
            r12.visitJumpInsn(r5, r2)
        L_0x00b0:
            r12.visitLabel(r3)
            int r3 = r14.var(r8)
            r12.visitVarInsn(r9, r3)
            r3 = 21
            java.lang.String r5 = "seperator"
            int r5 = r14.var(r5)
            r12.visitVarInsn(r3, r5)
            java.lang.String r3 = SerializeWriter
            java.lang.String r5 = "write"
            java.lang.String r6 = "(I)V"
            r12.visitMethodInsn(r7, r3, r5, r6)
            r11._writeFieldName(r12, r14)
            int r3 = r14.var(r8)
            r12.visitVarInsn(r9, r3)
            java.lang.Integer r13 = java.lang.Integer.valueOf(r13)
            r12.visitLdcInsn(r13)
            java.lang.Class<java.lang.String> r13 = java.lang.String.class
            if (r0 == r13) goto L_0x012f
            java.lang.Class<java.lang.Character> r13 = java.lang.Character.class
            if (r0 != r13) goto L_0x00e8
            goto L_0x012f
        L_0x00e8:
            java.lang.Class<java.lang.Number> r13 = java.lang.Number.class
            boolean r13 = r13.isAssignableFrom(r0)
            if (r13 == 0) goto L_0x00fc
            com.alibaba.fastjson.serializer.SerializerFeature r13 = com.alibaba.fastjson.serializer.SerializerFeature.WriteNullNumberAsZero
            int r13 = r13.mask
            java.lang.Integer r13 = java.lang.Integer.valueOf(r13)
            r12.visitLdcInsn(r13)
            goto L_0x013a
        L_0x00fc:
            java.lang.Class<java.lang.Boolean> r13 = java.lang.Boolean.class
            if (r0 != r13) goto L_0x010c
            com.alibaba.fastjson.serializer.SerializerFeature r13 = com.alibaba.fastjson.serializer.SerializerFeature.WriteNullBooleanAsFalse
            int r13 = r13.mask
            java.lang.Integer r13 = java.lang.Integer.valueOf(r13)
            r12.visitLdcInsn(r13)
            goto L_0x013a
        L_0x010c:
            java.lang.Class<java.util.Collection> r13 = java.util.Collection.class
            boolean r13 = r13.isAssignableFrom(r0)
            if (r13 != 0) goto L_0x0123
            boolean r13 = r0.isArray()
            if (r13 == 0) goto L_0x011b
            goto L_0x0123
        L_0x011b:
            java.lang.Integer r13 = java.lang.Integer.valueOf(r1)
            r12.visitLdcInsn(r13)
            goto L_0x013a
        L_0x0123:
            com.alibaba.fastjson.serializer.SerializerFeature r13 = com.alibaba.fastjson.serializer.SerializerFeature.WriteNullListAsEmpty
            int r13 = r13.mask
            java.lang.Integer r13 = java.lang.Integer.valueOf(r13)
            r12.visitLdcInsn(r13)
            goto L_0x013a
        L_0x012f:
            com.alibaba.fastjson.serializer.SerializerFeature r13 = com.alibaba.fastjson.serializer.SerializerFeature.WriteNullStringAsEmpty
            int r13 = r13.mask
            java.lang.Integer r13 = java.lang.Integer.valueOf(r13)
            r12.visitLdcInsn(r13)
        L_0x013a:
            java.lang.String r13 = SerializeWriter
            java.lang.String r0 = "writeNull"
            java.lang.String r1 = "(II)V"
            r12.visitMethodInsn(r7, r13, r0, r1)
            r11._seperator(r12, r14)
            r13 = 167(0xa7, float:2.34E-43)
            r12.visitJumpInsn(r13, r4)
            r12.visitLabel(r2)
            r12.visitLabel(r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.serializer.ASMSerializerFactory._if_write_null(com.alibaba.fastjson.asm.MethodVisitor, com.alibaba.fastjson.util.FieldInfo, com.alibaba.fastjson.serializer.ASMSerializerFactory$Context):void");
    }

    private void _writeFieldName(MethodVisitor methodVisitor, Context context) {
        if (context.writeDirect) {
            methodVisitor.visitVarInsn(25, context.var("out"));
            methodVisitor.visitVarInsn(25, Context.fieldName);
            methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, SerializeWriter, "writeFieldNameDirect", "(Ljava/lang/String;)V");
            return;
        }
        methodVisitor.visitVarInsn(25, context.var("out"));
        methodVisitor.visitVarInsn(25, Context.fieldName);
        methodVisitor.visitInsn(3);
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, SerializeWriter, "writeFieldName", "(Ljava/lang/String;Z)V");
    }

    private void _seperator(MethodVisitor methodVisitor, Context context) {
        methodVisitor.visitVarInsn(16, 44);
        methodVisitor.visitVarInsn(54, context.var("seperator"));
    }

    private void _getListFieldItemSer(Context context, MethodVisitor methodVisitor, FieldInfo fieldInfo, Class<?> cls) {
        Label label = new Label();
        methodVisitor.visitVarInsn(25, 0);
        String access$300 = context.className;
        methodVisitor.visitFieldInsn(180, access$300, fieldInfo.name + "_asm_list_item_ser_", ObjectSerializer_desc);
        methodVisitor.visitJumpInsn(Opcodes.IFNONNULL, label);
        methodVisitor.visitVarInsn(25, 0);
        methodVisitor.visitVarInsn(25, 1);
        methodVisitor.visitLdcInsn(Type.getType(ASMUtils.desc(cls)));
        String str = JSONSerializer;
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str, "getObjectWriter", "(Ljava/lang/Class;)" + ObjectSerializer_desc);
        String access$3002 = context.className;
        methodVisitor.visitFieldInsn(Opcodes.PUTFIELD, access$3002, fieldInfo.name + "_asm_list_item_ser_", ObjectSerializer_desc);
        methodVisitor.visitLabel(label);
        methodVisitor.visitVarInsn(25, 0);
        String access$3003 = context.className;
        methodVisitor.visitFieldInsn(180, access$3003, fieldInfo.name + "_asm_list_item_ser_", ObjectSerializer_desc);
    }

    private void _getFieldSer(Context context, MethodVisitor methodVisitor, FieldInfo fieldInfo) {
        Label label = new Label();
        methodVisitor.visitVarInsn(25, 0);
        String access$300 = context.className;
        methodVisitor.visitFieldInsn(180, access$300, fieldInfo.name + "_asm_ser_", ObjectSerializer_desc);
        methodVisitor.visitJumpInsn(Opcodes.IFNONNULL, label);
        methodVisitor.visitVarInsn(25, 0);
        methodVisitor.visitVarInsn(25, 1);
        methodVisitor.visitLdcInsn(Type.getType(ASMUtils.desc(fieldInfo.fieldClass)));
        String str = JSONSerializer;
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str, "getObjectWriter", "(Ljava/lang/Class;)" + ObjectSerializer_desc);
        String access$3002 = context.className;
        methodVisitor.visitFieldInsn(Opcodes.PUTFIELD, access$3002, fieldInfo.name + "_asm_ser_", ObjectSerializer_desc);
        methodVisitor.visitLabel(label);
        methodVisitor.visitVarInsn(25, 0);
        String access$3003 = context.className;
        methodVisitor.visitFieldInsn(180, access$3003, fieldInfo.name + "_asm_ser_", ObjectSerializer_desc);
    }
}
