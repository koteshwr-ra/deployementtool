package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.android.arouter.utils.Consts;
import com.alibaba.fastjson.asm.ClassWriter;
import com.alibaba.fastjson.asm.FieldWriter;
import com.alibaba.fastjson.asm.Label;
import com.alibaba.fastjson.asm.MethodVisitor;
import com.alibaba.fastjson.asm.MethodWriter;
import com.alibaba.fastjson.asm.Opcodes;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.JSONLexerBase;
import com.alibaba.fastjson.parser.ParseContext;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.parser.SymbolTable;
import com.alibaba.fastjson.util.ASMClassLoader;
import com.alibaba.fastjson.util.ASMUtils;
import com.alibaba.fastjson.util.FieldInfo;
import com.alibaba.fastjson.util.JavaBeanInfo;
import com.alibaba.fastjson.util.TypeUtils;
import com.tencent.smtt.sdk.TbsListener;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;
import org.apache.commons.lang3.ClassUtils;

public class ASMDeserializerFactory implements Opcodes {
    static final String DefaultJSONParser = ASMUtils.type(DefaultJSONParser.class);
    static final String JSONLexerBase = ASMUtils.type(JSONLexerBase.class);
    public final ASMClassLoader classLoader;
    protected final AtomicLong seed = new AtomicLong();

    public ASMDeserializerFactory(ClassLoader classLoader2) {
        this.classLoader = classLoader2 instanceof ASMClassLoader ? (ASMClassLoader) classLoader2 : new ASMClassLoader(classLoader2);
    }

    public ObjectDeserializer createJavaBeanDeserializer(ParserConfig parserConfig, JavaBeanInfo javaBeanInfo) throws Exception {
        String str;
        Class<?> cls = javaBeanInfo.clazz;
        if (!cls.isPrimitive()) {
            String str2 = "FastjsonASMDeserializer_" + this.seed.incrementAndGet() + "_" + cls.getSimpleName();
            Package packageR = ASMDeserializerFactory.class.getPackage();
            if (packageR != null) {
                String name = packageR.getName();
                String str3 = name.replace(ClassUtils.PACKAGE_SEPARATOR_CHAR, '/') + "/" + str2;
                str = name + Consts.DOT + str2;
                str2 = str3;
            } else {
                str = str2;
            }
            ClassWriter classWriter = new ClassWriter();
            classWriter.visit(49, 33, str2, ASMUtils.type(JavaBeanDeserializer.class), (String[]) null);
            _init(classWriter, new Context(str2, parserConfig, javaBeanInfo, 3));
            _createInstance(classWriter, new Context(str2, parserConfig, javaBeanInfo, 3));
            _deserialze(classWriter, new Context(str2, parserConfig, javaBeanInfo, 5));
            _deserialzeArrayMapping(classWriter, new Context(str2, parserConfig, javaBeanInfo, 4));
            byte[] byteArray = classWriter.toByteArray();
            return (ObjectDeserializer) this.classLoader.defineClassPublic(str, byteArray, 0, byteArray.length).getConstructor(new Class[]{ParserConfig.class, JavaBeanInfo.class}).newInstance(new Object[]{parserConfig, javaBeanInfo});
        }
        throw new IllegalArgumentException("not support type :" + cls.getName());
    }

    private void _setFlag(MethodVisitor methodVisitor, Context context, int i) {
        String str = "_asm_flag_" + (i / 32);
        methodVisitor.visitVarInsn(21, context.var(str));
        methodVisitor.visitLdcInsn(Integer.valueOf(1 << i));
        methodVisitor.visitInsn(128);
        methodVisitor.visitVarInsn(54, context.var(str));
    }

    private void _isFlag(MethodVisitor methodVisitor, Context context, int i, Label label) {
        methodVisitor.visitVarInsn(21, context.var("_asm_flag_" + (i / 32)));
        methodVisitor.visitLdcInsn(Integer.valueOf(1 << i));
        methodVisitor.visitInsn(126);
        methodVisitor.visitJumpInsn(Opcodes.IFEQ, label);
    }

    private void _deserialzeArrayMapping(ClassWriter classWriter, Context context) {
        FieldInfo[] fieldInfoArr;
        int i;
        int i2;
        Context context2 = context;
        MethodWriter methodWriter = new MethodWriter(classWriter, 1, "deserialzeArrayMapping", "(L" + DefaultJSONParser + ";Ljava/lang/reflect/Type;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", (String) null, (String[]) null);
        defineVarLexer(context2, methodWriter);
        methodWriter.visitVarInsn(25, context2.var("lexer"));
        methodWriter.visitVarInsn(25, 1);
        String str = DefaultJSONParser;
        methodWriter.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str, "getSymbolTable", "()" + ASMUtils.desc((Class<?>) SymbolTable.class));
        String str2 = JSONLexerBase;
        methodWriter.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str2, "scanTypeName", "(" + ASMUtils.desc((Class<?>) SymbolTable.class) + ")Ljava/lang/String;");
        methodWriter.visitVarInsn(58, context2.var("typeName"));
        Label label = new Label();
        methodWriter.visitVarInsn(25, context2.var("typeName"));
        methodWriter.visitJumpInsn(Opcodes.IFNULL, label);
        methodWriter.visitVarInsn(25, 1);
        String str3 = DefaultJSONParser;
        methodWriter.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str3, "getConfig", "()" + ASMUtils.desc((Class<?>) ParserConfig.class));
        methodWriter.visitVarInsn(25, 0);
        methodWriter.visitFieldInsn(180, ASMUtils.type(JavaBeanDeserializer.class), "beanInfo", ASMUtils.desc((Class<?>) JavaBeanInfo.class));
        methodWriter.visitVarInsn(25, context2.var("typeName"));
        String type = ASMUtils.type(JavaBeanDeserializer.class);
        methodWriter.visitMethodInsn(184, type, "getSeeAlso", "(" + ASMUtils.desc((Class<?>) ParserConfig.class) + ASMUtils.desc((Class<?>) JavaBeanInfo.class) + "Ljava/lang/String;)" + ASMUtils.desc((Class<?>) JavaBeanDeserializer.class));
        methodWriter.visitVarInsn(58, context2.var("userTypeDeser"));
        methodWriter.visitVarInsn(25, context2.var("userTypeDeser"));
        methodWriter.visitTypeInsn(Opcodes.INSTANCEOF, ASMUtils.type(JavaBeanDeserializer.class));
        methodWriter.visitJumpInsn(Opcodes.IFEQ, label);
        methodWriter.visitVarInsn(25, context2.var("userTypeDeser"));
        methodWriter.visitVarInsn(25, 1);
        methodWriter.visitVarInsn(25, 2);
        methodWriter.visitVarInsn(25, 3);
        methodWriter.visitVarInsn(25, 4);
        String type2 = ASMUtils.type(JavaBeanDeserializer.class);
        methodWriter.visitMethodInsn(Opcodes.INVOKEVIRTUAL, type2, "deserialzeArrayMapping", "(L" + DefaultJSONParser + ";Ljava/lang/reflect/Type;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;");
        methodWriter.visitInsn(176);
        methodWriter.visitLabel(label);
        _createInstance(context2, (MethodVisitor) methodWriter);
        FieldInfo[] fieldInfoArr2 = context.beanInfo.sortedFields;
        int length = fieldInfoArr2.length;
        int i3 = 0;
        while (i3 < length) {
            boolean z = i3 == length + -1;
            int i4 = z ? 93 : 44;
            FieldInfo fieldInfo = fieldInfoArr2[i3];
            Class<?> cls = fieldInfo.fieldClass;
            Type type3 = fieldInfo.fieldType;
            int i5 = length;
            if (cls == Byte.TYPE || cls == Short.TYPE || cls == Integer.TYPE) {
                i2 = i3;
                fieldInfoArr = fieldInfoArr2;
                i = i5;
                methodWriter.visitVarInsn(25, context2.var("lexer"));
                methodWriter.visitVarInsn(16, i4);
                methodWriter.visitMethodInsn(Opcodes.INVOKEVIRTUAL, JSONLexerBase, "scanInt", "(C)I");
                methodWriter.visitVarInsn(54, context2.var(fieldInfo.name + "_asm"));
            } else {
                FieldInfo[] fieldInfoArr3 = fieldInfoArr2;
                boolean z2 = z;
                String str4 = "(I)V";
                if (cls == Byte.class) {
                    methodWriter.visitVarInsn(25, context2.var("lexer"));
                    methodWriter.visitVarInsn(16, i4);
                    methodWriter.visitMethodInsn(Opcodes.INVOKEVIRTUAL, JSONLexerBase, "scanInt", "(C)I");
                    methodWriter.visitMethodInsn(184, "java/lang/Byte", "valueOf", "(B)Ljava/lang/Byte;");
                    methodWriter.visitVarInsn(58, context2.var(fieldInfo.name + "_asm"));
                    Label label2 = new Label();
                    methodWriter.visitVarInsn(25, context2.var("lexer"));
                    methodWriter.visitFieldInsn(180, JSONLexerBase, "matchStat", "I");
                    methodWriter.visitLdcInsn(5);
                    methodWriter.visitJumpInsn(160, label2);
                    methodWriter.visitInsn(1);
                    methodWriter.visitVarInsn(58, context2.var(fieldInfo.name + "_asm"));
                    methodWriter.visitLabel(label2);
                } else if (cls == Short.class) {
                    methodWriter.visitVarInsn(25, context2.var("lexer"));
                    methodWriter.visitVarInsn(16, i4);
                    methodWriter.visitMethodInsn(Opcodes.INVOKEVIRTUAL, JSONLexerBase, "scanInt", "(C)I");
                    methodWriter.visitMethodInsn(184, "java/lang/Short", "valueOf", "(S)Ljava/lang/Short;");
                    methodWriter.visitVarInsn(58, context2.var(fieldInfo.name + "_asm"));
                    Label label3 = new Label();
                    methodWriter.visitVarInsn(25, context2.var("lexer"));
                    methodWriter.visitFieldInsn(180, JSONLexerBase, "matchStat", "I");
                    methodWriter.visitLdcInsn(5);
                    methodWriter.visitJumpInsn(160, label3);
                    methodWriter.visitInsn(1);
                    methodWriter.visitVarInsn(58, context2.var(fieldInfo.name + "_asm"));
                    methodWriter.visitLabel(label3);
                } else if (cls == Integer.class) {
                    methodWriter.visitVarInsn(25, context2.var("lexer"));
                    methodWriter.visitVarInsn(16, i4);
                    methodWriter.visitMethodInsn(Opcodes.INVOKEVIRTUAL, JSONLexerBase, "scanInt", "(C)I");
                    methodWriter.visitMethodInsn(184, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;");
                    methodWriter.visitVarInsn(58, context2.var(fieldInfo.name + "_asm"));
                    Label label4 = new Label();
                    methodWriter.visitVarInsn(25, context2.var("lexer"));
                    methodWriter.visitFieldInsn(180, JSONLexerBase, "matchStat", "I");
                    methodWriter.visitLdcInsn(5);
                    methodWriter.visitJumpInsn(160, label4);
                    methodWriter.visitInsn(1);
                    methodWriter.visitVarInsn(58, context2.var(fieldInfo.name + "_asm"));
                    methodWriter.visitLabel(label4);
                } else if (cls == Long.TYPE) {
                    methodWriter.visitVarInsn(25, context2.var("lexer"));
                    methodWriter.visitVarInsn(16, i4);
                    methodWriter.visitMethodInsn(Opcodes.INVOKEVIRTUAL, JSONLexerBase, "scanLong", "(C)J");
                    methodWriter.visitVarInsn(55, context2.var(fieldInfo.name + "_asm", 2));
                } else if (cls == Long.class) {
                    methodWriter.visitVarInsn(25, context2.var("lexer"));
                    methodWriter.visitVarInsn(16, i4);
                    methodWriter.visitMethodInsn(Opcodes.INVOKEVIRTUAL, JSONLexerBase, "scanLong", "(C)J");
                    methodWriter.visitMethodInsn(184, "java/lang/Long", "valueOf", "(J)Ljava/lang/Long;");
                    methodWriter.visitVarInsn(58, context2.var(fieldInfo.name + "_asm"));
                    Label label5 = new Label();
                    methodWriter.visitVarInsn(25, context2.var("lexer"));
                    methodWriter.visitFieldInsn(180, JSONLexerBase, "matchStat", "I");
                    methodWriter.visitLdcInsn(5);
                    methodWriter.visitJumpInsn(160, label5);
                    methodWriter.visitInsn(1);
                    methodWriter.visitVarInsn(58, context2.var(fieldInfo.name + "_asm"));
                    methodWriter.visitLabel(label5);
                } else if (cls == Boolean.TYPE) {
                    methodWriter.visitVarInsn(25, context2.var("lexer"));
                    methodWriter.visitVarInsn(16, i4);
                    methodWriter.visitMethodInsn(Opcodes.INVOKEVIRTUAL, JSONLexerBase, "scanBoolean", "(C)Z");
                    methodWriter.visitVarInsn(54, context2.var(fieldInfo.name + "_asm"));
                } else if (cls == Float.TYPE) {
                    methodWriter.visitVarInsn(25, context2.var("lexer"));
                    methodWriter.visitVarInsn(16, i4);
                    methodWriter.visitMethodInsn(Opcodes.INVOKEVIRTUAL, JSONLexerBase, "scanFloat", "(C)F");
                    methodWriter.visitVarInsn(56, context2.var(fieldInfo.name + "_asm"));
                } else if (cls == Float.class) {
                    methodWriter.visitVarInsn(25, context2.var("lexer"));
                    methodWriter.visitVarInsn(16, i4);
                    methodWriter.visitMethodInsn(Opcodes.INVOKEVIRTUAL, JSONLexerBase, "scanFloat", "(C)F");
                    methodWriter.visitMethodInsn(184, "java/lang/Float", "valueOf", "(F)Ljava/lang/Float;");
                    methodWriter.visitVarInsn(58, context2.var(fieldInfo.name + "_asm"));
                    Label label6 = new Label();
                    methodWriter.visitVarInsn(25, context2.var("lexer"));
                    methodWriter.visitFieldInsn(180, JSONLexerBase, "matchStat", "I");
                    methodWriter.visitLdcInsn(5);
                    methodWriter.visitJumpInsn(160, label6);
                    methodWriter.visitInsn(1);
                    methodWriter.visitVarInsn(58, context2.var(fieldInfo.name + "_asm"));
                    methodWriter.visitLabel(label6);
                } else if (cls == Double.TYPE) {
                    methodWriter.visitVarInsn(25, context2.var("lexer"));
                    methodWriter.visitVarInsn(16, i4);
                    methodWriter.visitMethodInsn(Opcodes.INVOKEVIRTUAL, JSONLexerBase, "scanDouble", "(C)D");
                    methodWriter.visitVarInsn(57, context2.var(fieldInfo.name + "_asm", 2));
                } else if (cls == Double.class) {
                    methodWriter.visitVarInsn(25, context2.var("lexer"));
                    methodWriter.visitVarInsn(16, i4);
                    methodWriter.visitMethodInsn(Opcodes.INVOKEVIRTUAL, JSONLexerBase, "scanDouble", "(C)D");
                    methodWriter.visitMethodInsn(184, "java/lang/Double", "valueOf", "(D)Ljava/lang/Double;");
                    methodWriter.visitVarInsn(58, context2.var(fieldInfo.name + "_asm"));
                    Label label7 = new Label();
                    methodWriter.visitVarInsn(25, context2.var("lexer"));
                    methodWriter.visitFieldInsn(180, JSONLexerBase, "matchStat", "I");
                    methodWriter.visitLdcInsn(5);
                    methodWriter.visitJumpInsn(160, label7);
                    methodWriter.visitInsn(1);
                    methodWriter.visitVarInsn(58, context2.var(fieldInfo.name + "_asm"));
                    methodWriter.visitLabel(label7);
                } else if (cls == Character.TYPE) {
                    methodWriter.visitVarInsn(25, context2.var("lexer"));
                    methodWriter.visitVarInsn(16, i4);
                    methodWriter.visitMethodInsn(Opcodes.INVOKEVIRTUAL, JSONLexerBase, "scanString", "(C)Ljava/lang/String;");
                    methodWriter.visitInsn(3);
                    methodWriter.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/String", "charAt", "(I)C");
                    methodWriter.visitVarInsn(54, context2.var(fieldInfo.name + "_asm"));
                } else if (cls == String.class) {
                    methodWriter.visitVarInsn(25, context2.var("lexer"));
                    methodWriter.visitVarInsn(16, i4);
                    methodWriter.visitMethodInsn(Opcodes.INVOKEVIRTUAL, JSONLexerBase, "scanString", "(C)Ljava/lang/String;");
                    methodWriter.visitVarInsn(58, context2.var(fieldInfo.name + "_asm"));
                } else if (cls == BigDecimal.class) {
                    methodWriter.visitVarInsn(25, context2.var("lexer"));
                    methodWriter.visitVarInsn(16, i4);
                    methodWriter.visitMethodInsn(Opcodes.INVOKEVIRTUAL, JSONLexerBase, "scanDecimal", "(C)Ljava/math/BigDecimal;");
                    methodWriter.visitVarInsn(58, context2.var(fieldInfo.name + "_asm"));
                } else if (cls == Date.class) {
                    methodWriter.visitVarInsn(25, context2.var("lexer"));
                    methodWriter.visitVarInsn(16, i4);
                    methodWriter.visitMethodInsn(Opcodes.INVOKEVIRTUAL, JSONLexerBase, "scanDate", "(C)Ljava/util/Date;");
                    methodWriter.visitVarInsn(58, context2.var(fieldInfo.name + "_asm"));
                } else if (cls == UUID.class) {
                    methodWriter.visitVarInsn(25, context2.var("lexer"));
                    methodWriter.visitVarInsn(16, i4);
                    methodWriter.visitMethodInsn(Opcodes.INVOKEVIRTUAL, JSONLexerBase, "scanUUID", "(C)Ljava/util/UUID;");
                    methodWriter.visitVarInsn(58, context2.var(fieldInfo.name + "_asm"));
                } else if (cls.isEnum()) {
                    Label label8 = new Label();
                    Label label9 = new Label();
                    Label label10 = new Label();
                    Label label11 = new Label();
                    int i6 = i3;
                    methodWriter.visitVarInsn(25, context2.var("lexer"));
                    methodWriter.visitMethodInsn(Opcodes.INVOKEVIRTUAL, JSONLexerBase, "getCurrent", "()C");
                    methodWriter.visitInsn(89);
                    methodWriter.visitVarInsn(54, context2.var("ch"));
                    methodWriter.visitLdcInsn(110);
                    methodWriter.visitJumpInsn(Opcodes.IF_ICMPEQ, label11);
                    methodWriter.visitVarInsn(21, context2.var("ch"));
                    methodWriter.visitLdcInsn(34);
                    methodWriter.visitJumpInsn(160, label8);
                    methodWriter.visitLabel(label11);
                    methodWriter.visitVarInsn(25, context2.var("lexer"));
                    methodWriter.visitLdcInsn(com.alibaba.fastjson.asm.Type.getType(ASMUtils.desc(cls)));
                    methodWriter.visitVarInsn(25, 1);
                    String str5 = DefaultJSONParser;
                    methodWriter.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str5, "getSymbolTable", "()" + ASMUtils.desc((Class<?>) SymbolTable.class));
                    methodWriter.visitVarInsn(16, i4);
                    String str6 = JSONLexerBase;
                    methodWriter.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str6, "scanEnum", "(Ljava/lang/Class;" + ASMUtils.desc((Class<?>) SymbolTable.class) + "C)Ljava/lang/Enum;");
                    methodWriter.visitJumpInsn(167, label10);
                    methodWriter.visitLabel(label8);
                    methodWriter.visitVarInsn(21, context2.var("ch"));
                    methodWriter.visitLdcInsn(48);
                    methodWriter.visitJumpInsn(161, label9);
                    methodWriter.visitVarInsn(21, context2.var("ch"));
                    methodWriter.visitLdcInsn(57);
                    methodWriter.visitJumpInsn(163, label9);
                    _getFieldDeser(context2, methodWriter, fieldInfo);
                    methodWriter.visitTypeInsn(Opcodes.CHECKCAST, ASMUtils.type(EnumDeserializer.class));
                    methodWriter.visitVarInsn(25, context2.var("lexer"));
                    methodWriter.visitVarInsn(16, i4);
                    methodWriter.visitMethodInsn(Opcodes.INVOKEVIRTUAL, JSONLexerBase, "scanInt", "(C)I");
                    methodWriter.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.type(EnumDeserializer.class), "valueOf", "(I)Ljava/lang/Enum;");
                    methodWriter.visitJumpInsn(167, label10);
                    methodWriter.visitLabel(label9);
                    methodWriter.visitVarInsn(25, 0);
                    methodWriter.visitVarInsn(25, context2.var("lexer"));
                    methodWriter.visitVarInsn(16, i4);
                    String type4 = ASMUtils.type(JavaBeanDeserializer.class);
                    methodWriter.visitMethodInsn(Opcodes.INVOKEVIRTUAL, type4, "scanEnum", "(L" + JSONLexerBase + ";C)Ljava/lang/Enum;");
                    methodWriter.visitLabel(label10);
                    methodWriter.visitTypeInsn(Opcodes.CHECKCAST, ASMUtils.type(cls));
                    methodWriter.visitVarInsn(58, context2.var(fieldInfo.name + "_asm"));
                    i = i5;
                    fieldInfoArr = fieldInfoArr3;
                    i2 = i6;
                } else {
                    int i7 = i3;
                    String str7 = "_asm";
                    if (Collection.class.isAssignableFrom(cls)) {
                        Class<?> collectionItemClass = TypeUtils.getCollectionItemClass(type3);
                        if (collectionItemClass == String.class) {
                            if (cls == List.class || cls == Collections.class || cls == ArrayList.class) {
                                methodWriter.visitTypeInsn(Opcodes.NEW, ASMUtils.type(ArrayList.class));
                                methodWriter.visitInsn(89);
                                methodWriter.visitMethodInsn(Opcodes.INVOKESPECIAL, ASMUtils.type(ArrayList.class), "<init>", "()V");
                            } else {
                                methodWriter.visitLdcInsn(com.alibaba.fastjson.asm.Type.getType(ASMUtils.desc(cls)));
                                methodWriter.visitMethodInsn(184, ASMUtils.type(TypeUtils.class), "createCollection", "(Ljava/lang/Class;)Ljava/util/Collection;");
                            }
                            methodWriter.visitVarInsn(58, context2.var(fieldInfo.name + str7));
                            methodWriter.visitVarInsn(25, context2.var("lexer"));
                            methodWriter.visitVarInsn(25, context2.var(fieldInfo.name + str7));
                            methodWriter.visitVarInsn(16, i4);
                            methodWriter.visitMethodInsn(Opcodes.INVOKEVIRTUAL, JSONLexerBase, "scanStringArray", "(Ljava/util/Collection;C)V");
                            Label label12 = new Label();
                            methodWriter.visitVarInsn(25, context2.var("lexer"));
                            methodWriter.visitFieldInsn(180, JSONLexerBase, "matchStat", "I");
                            methodWriter.visitLdcInsn(5);
                            methodWriter.visitJumpInsn(160, label12);
                            methodWriter.visitInsn(1);
                            methodWriter.visitVarInsn(58, context2.var(fieldInfo.name + str7));
                            methodWriter.visitLabel(label12);
                            i3 = i7;
                        } else {
                            Label label13 = new Label();
                            methodWriter.visitVarInsn(25, context2.var("lexer"));
                            methodWriter.visitMethodInsn(Opcodes.INVOKEVIRTUAL, JSONLexerBase, "token", "()I");
                            methodWriter.visitVarInsn(54, context2.var("token"));
                            methodWriter.visitVarInsn(21, context2.var("token"));
                            int i8 = i7 == 0 ? 14 : 16;
                            methodWriter.visitLdcInsn(Integer.valueOf(i8));
                            methodWriter.visitJumpInsn(Opcodes.IF_ICMPEQ, label13);
                            methodWriter.visitVarInsn(25, 1);
                            methodWriter.visitLdcInsn(Integer.valueOf(i8));
                            String str8 = str4;
                            methodWriter.visitMethodInsn(Opcodes.INVOKEVIRTUAL, DefaultJSONParser, "throwException", str8);
                            methodWriter.visitLabel(label13);
                            Label label14 = new Label();
                            Label label15 = new Label();
                            methodWriter.visitVarInsn(25, context2.var("lexer"));
                            methodWriter.visitMethodInsn(Opcodes.INVOKEVIRTUAL, JSONLexerBase, "getCurrent", "()C");
                            methodWriter.visitVarInsn(16, 91);
                            methodWriter.visitJumpInsn(160, label14);
                            methodWriter.visitVarInsn(25, context2.var("lexer"));
                            methodWriter.visitMethodInsn(Opcodes.INVOKEVIRTUAL, JSONLexerBase, "next", "()C");
                            methodWriter.visitInsn(87);
                            methodWriter.visitVarInsn(25, context2.var("lexer"));
                            methodWriter.visitLdcInsn(14);
                            methodWriter.visitMethodInsn(Opcodes.INVOKEVIRTUAL, JSONLexerBase, "setToken", str8);
                            methodWriter.visitJumpInsn(167, label15);
                            methodWriter.visitLabel(label14);
                            methodWriter.visitVarInsn(25, context2.var("lexer"));
                            methodWriter.visitLdcInsn(14);
                            methodWriter.visitMethodInsn(Opcodes.INVOKEVIRTUAL, JSONLexerBase, "nextToken", str8);
                            methodWriter.visitLabel(label15);
                            i3 = i7;
                            _newCollection(methodWriter, cls, i3, false);
                            methodWriter.visitInsn(89);
                            methodWriter.visitVarInsn(58, context2.var(fieldInfo.name + str7));
                            _getCollectionFieldItemDeser(context2, methodWriter, fieldInfo, collectionItemClass);
                            methodWriter.visitVarInsn(25, 1);
                            methodWriter.visitLdcInsn(com.alibaba.fastjson.asm.Type.getType(ASMUtils.desc(collectionItemClass)));
                            methodWriter.visitVarInsn(25, 3);
                            String type5 = ASMUtils.type(JavaBeanDeserializer.class);
                            methodWriter.visitMethodInsn(184, type5, "parseArray", "(Ljava/util/Collection;" + ASMUtils.desc((Class<?>) ObjectDeserializer.class) + "L" + DefaultJSONParser + ";Ljava/lang/reflect/Type;Ljava/lang/Object;)V");
                        }
                    } else {
                        String str9 = str4;
                        int i9 = i7;
                        if (cls.isArray()) {
                            methodWriter.visitVarInsn(25, context2.var("lexer"));
                            methodWriter.visitLdcInsn(14);
                            methodWriter.visitMethodInsn(Opcodes.INVOKEVIRTUAL, JSONLexerBase, "nextToken", str9);
                            methodWriter.visitVarInsn(25, 1);
                            methodWriter.visitVarInsn(25, 0);
                            methodWriter.visitLdcInsn(Integer.valueOf(i9));
                            methodWriter.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.type(JavaBeanDeserializer.class), "getFieldType", "(I)Ljava/lang/reflect/Type;");
                            methodWriter.visitMethodInsn(Opcodes.INVOKEVIRTUAL, DefaultJSONParser, "parseObject", "(Ljava/lang/reflect/Type;)Ljava/lang/Object;");
                            methodWriter.visitTypeInsn(Opcodes.CHECKCAST, ASMUtils.type(cls));
                            methodWriter.visitVarInsn(58, context2.var(fieldInfo.name + str7));
                            i2 = i9;
                            i = i5;
                            fieldInfoArr = fieldInfoArr3;
                        } else {
                            Label label16 = new Label();
                            Label label17 = new Label();
                            if (cls == Date.class) {
                                methodWriter.visitVarInsn(25, context2.var("lexer"));
                                methodWriter.visitMethodInsn(Opcodes.INVOKEVIRTUAL, JSONLexerBase, "getCurrent", "()C");
                                methodWriter.visitLdcInsn(49);
                                methodWriter.visitJumpInsn(160, label16);
                                methodWriter.visitTypeInsn(Opcodes.NEW, ASMUtils.type(Date.class));
                                methodWriter.visitInsn(89);
                                methodWriter.visitVarInsn(25, context2.var("lexer"));
                                methodWriter.visitVarInsn(16, i4);
                                methodWriter.visitMethodInsn(Opcodes.INVOKEVIRTUAL, JSONLexerBase, "scanLong", "(C)J");
                                methodWriter.visitMethodInsn(Opcodes.INVOKESPECIAL, ASMUtils.type(Date.class), "<init>", "(J)V");
                                methodWriter.visitVarInsn(58, context2.var(fieldInfo.name + str7));
                                methodWriter.visitJumpInsn(167, label17);
                            }
                            methodWriter.visitLabel(label16);
                            _quickNextToken(context2, methodWriter, 14);
                            i2 = i9;
                            i = i5;
                            fieldInfoArr = fieldInfoArr3;
                            _deserObject(context, methodWriter, fieldInfo, cls, i2);
                            methodWriter.visitVarInsn(25, context2.var("lexer"));
                            methodWriter.visitMethodInsn(Opcodes.INVOKEVIRTUAL, JSONLexerBase, "token", "()I");
                            methodWriter.visitLdcInsn(15);
                            methodWriter.visitJumpInsn(Opcodes.IF_ICMPEQ, label17);
                            methodWriter.visitVarInsn(25, 0);
                            methodWriter.visitVarInsn(25, context2.var("lexer"));
                            if (!z2) {
                                methodWriter.visitLdcInsn(16);
                            } else {
                                methodWriter.visitLdcInsn(15);
                            }
                            String type6 = ASMUtils.type(JavaBeanDeserializer.class);
                            methodWriter.visitMethodInsn(Opcodes.INVOKESPECIAL, type6, "check", "(" + ASMUtils.desc((Class<?>) JSONLexer.class) + "I)V");
                            methodWriter.visitLabel(label17);
                        }
                    }
                }
                i2 = i3;
                i = i5;
                fieldInfoArr = fieldInfoArr3;
            }
            i3 = i2 + 1;
            length = i;
            fieldInfoArr2 = fieldInfoArr;
        }
        _batchSet(context2, methodWriter, false);
        Label label18 = new Label();
        Label label19 = new Label();
        Label label20 = new Label();
        Label label21 = new Label();
        methodWriter.visitVarInsn(25, context2.var("lexer"));
        methodWriter.visitMethodInsn(Opcodes.INVOKEVIRTUAL, JSONLexerBase, "getCurrent", "()C");
        methodWriter.visitInsn(89);
        methodWriter.visitVarInsn(54, context2.var("ch"));
        methodWriter.visitVarInsn(16, 44);
        methodWriter.visitJumpInsn(160, label19);
        methodWriter.visitVarInsn(25, context2.var("lexer"));
        methodWriter.visitMethodInsn(Opcodes.INVOKEVIRTUAL, JSONLexerBase, "next", "()C");
        methodWriter.visitInsn(87);
        methodWriter.visitVarInsn(25, context2.var("lexer"));
        methodWriter.visitLdcInsn(16);
        methodWriter.visitMethodInsn(Opcodes.INVOKEVIRTUAL, JSONLexerBase, "setToken", "(I)V");
        methodWriter.visitJumpInsn(167, label21);
        methodWriter.visitLabel(label19);
        methodWriter.visitVarInsn(21, context2.var("ch"));
        methodWriter.visitVarInsn(16, 93);
        methodWriter.visitJumpInsn(160, label20);
        methodWriter.visitVarInsn(25, context2.var("lexer"));
        methodWriter.visitMethodInsn(Opcodes.INVOKEVIRTUAL, JSONLexerBase, "next", "()C");
        methodWriter.visitInsn(87);
        methodWriter.visitVarInsn(25, context2.var("lexer"));
        methodWriter.visitLdcInsn(15);
        methodWriter.visitMethodInsn(Opcodes.INVOKEVIRTUAL, JSONLexerBase, "setToken", "(I)V");
        methodWriter.visitJumpInsn(167, label21);
        methodWriter.visitLabel(label20);
        methodWriter.visitVarInsn(21, context2.var("ch"));
        methodWriter.visitVarInsn(16, 26);
        methodWriter.visitJumpInsn(160, label18);
        methodWriter.visitVarInsn(25, context2.var("lexer"));
        methodWriter.visitMethodInsn(Opcodes.INVOKEVIRTUAL, JSONLexerBase, "next", "()C");
        methodWriter.visitInsn(87);
        methodWriter.visitVarInsn(25, context2.var("lexer"));
        methodWriter.visitLdcInsn(20);
        methodWriter.visitMethodInsn(Opcodes.INVOKEVIRTUAL, JSONLexerBase, "setToken", "(I)V");
        methodWriter.visitJumpInsn(167, label21);
        methodWriter.visitLabel(label18);
        methodWriter.visitVarInsn(25, context2.var("lexer"));
        methodWriter.visitLdcInsn(16);
        methodWriter.visitMethodInsn(Opcodes.INVOKEVIRTUAL, JSONLexerBase, "nextToken", "(I)V");
        methodWriter.visitLabel(label21);
        methodWriter.visitVarInsn(25, context2.var("instance"));
        methodWriter.visitInsn(176);
        methodWriter.visitMaxs(5, context.variantIndex);
        methodWriter.visitEnd();
    }

    /* JADX WARNING: Removed duplicated region for block: B:129:0x0e4b  */
    /* JADX WARNING: Removed duplicated region for block: B:130:0x0e6a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void _deserialze(com.alibaba.fastjson.asm.ClassWriter r32, com.alibaba.fastjson.parser.deserializer.ASMDeserializerFactory.Context r33) {
        /*
            r31 = this;
            r8 = r31
            r9 = r33
            com.alibaba.fastjson.util.FieldInfo[] r0 = r33.fieldInfoList
            int r0 = r0.length
            if (r0 != 0) goto L_0x000c
            return
        L_0x000c:
            com.alibaba.fastjson.util.FieldInfo[] r0 = r33.fieldInfoList
            int r1 = r0.length
            r10 = 0
            r2 = 0
        L_0x0013:
            if (r2 >= r1) goto L_0x003d
            r3 = r0[r2]
            java.lang.Class<?> r4 = r3.fieldClass
            java.lang.reflect.Type r3 = r3.fieldType
            java.lang.Class r5 = java.lang.Character.TYPE
            if (r4 != r5) goto L_0x0020
            return
        L_0x0020:
            java.lang.Class<java.util.Collection> r5 = java.util.Collection.class
            boolean r4 = r5.isAssignableFrom(r4)
            if (r4 == 0) goto L_0x003a
            boolean r4 = r3 instanceof java.lang.reflect.ParameterizedType
            if (r4 == 0) goto L_0x0039
            java.lang.reflect.ParameterizedType r3 = (java.lang.reflect.ParameterizedType) r3
            java.lang.reflect.Type[] r3 = r3.getActualTypeArguments()
            r3 = r3[r10]
            boolean r3 = r3 instanceof java.lang.Class
            if (r3 == 0) goto L_0x0039
            goto L_0x003a
        L_0x0039:
            return
        L_0x003a:
            int r2 = r2 + 1
            goto L_0x0013
        L_0x003d:
            com.alibaba.fastjson.util.JavaBeanInfo r0 = r33.beanInfo
            com.alibaba.fastjson.util.FieldInfo[] r1 = r0.sortedFields
            com.alibaba.fastjson.util.FieldInfo[] unused = r9.fieldInfoList = r1
            com.alibaba.fastjson.asm.MethodWriter r7 = new com.alibaba.fastjson.asm.MethodWriter
            r13 = 1
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "(L"
            r1.append(r2)
            java.lang.String r2 = DefaultJSONParser
            r1.append(r2)
            java.lang.String r2 = ";Ljava/lang/reflect/Type;Ljava/lang/Object;I)Ljava/lang/Object;"
            r1.append(r2)
            java.lang.String r15 = r1.toString()
            r16 = 0
            r17 = 0
            java.lang.String r14 = "deserialze"
            r11 = r7
            r12 = r32
            r11.<init>(r12, r13, r14, r15, r16, r17)
            com.alibaba.fastjson.asm.Label r11 = new com.alibaba.fastjson.asm.Label
            r11.<init>()
            com.alibaba.fastjson.asm.Label r12 = new com.alibaba.fastjson.asm.Label
            r12.<init>()
            com.alibaba.fastjson.asm.Label r13 = new com.alibaba.fastjson.asm.Label
            r13.<init>()
            com.alibaba.fastjson.asm.Label r14 = new com.alibaba.fastjson.asm.Label
            r14.<init>()
            r8.defineVarLexer(r9, r7)
            com.alibaba.fastjson.asm.Label r1 = new com.alibaba.fastjson.asm.Label
            r1.<init>()
            java.lang.String r15 = "lexer"
            int r2 = r9.var(r15)
            r6 = 25
            r7.visitVarInsn(r6, r2)
            java.lang.String r2 = JSONLexerBase
            r5 = 182(0xb6, float:2.55E-43)
            java.lang.String r3 = "token"
            java.lang.String r4 = "()I"
            r7.visitMethodInsn(r5, r2, r3, r4)
            r2 = 14
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r7.visitLdcInsn(r2)
            r4 = 160(0xa0, float:2.24E-43)
            r7.visitJumpInsn(r4, r1)
            int r0 = r0.parserFeatures
            com.alibaba.fastjson.parser.Feature r2 = com.alibaba.fastjson.parser.Feature.SupportArrayToBean
            int r2 = r2.mask
            r0 = r0 & r2
            r3 = 21
            r2 = 4
            if (r0 != 0) goto L_0x00dc
            int r0 = r9.var(r15)
            r7.visitVarInsn(r6, r0)
            r7.visitVarInsn(r3, r2)
            com.alibaba.fastjson.parser.Feature r0 = com.alibaba.fastjson.parser.Feature.SupportArrayToBean
            int r0 = r0.mask
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r7.visitLdcInsn(r0)
            java.lang.String r0 = JSONLexerBase
            java.lang.String r3 = "isEnabled"
            java.lang.String r2 = "(II)Z"
            r7.visitMethodInsn(r5, r0, r3, r2)
            r0 = 153(0x99, float:2.14E-43)
            r7.visitJumpInsn(r0, r1)
        L_0x00dc:
            r7.visitVarInsn(r6, r10)
            r3 = 1
            r7.visitVarInsn(r6, r3)
            r2 = 2
            r7.visitVarInsn(r6, r2)
            r0 = 3
            r7.visitVarInsn(r6, r0)
            r7.visitInsn(r3)
            r10 = 183(0xb7, float:2.56E-43)
            java.lang.String r2 = r33.className
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r3 = "(L"
            r0.append(r3)
            java.lang.String r3 = DefaultJSONParser
            r0.append(r3)
            java.lang.String r3 = ";Ljava/lang/reflect/Type;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"
            r0.append(r3)
            java.lang.String r0 = r0.toString()
            java.lang.String r3 = "deserialzeArrayMapping"
            r7.visitMethodInsn(r10, r2, r3, r0)
            r0 = 176(0xb0, float:2.47E-43)
            r7.visitInsn(r0)
            r7.visitLabel(r1)
            int r0 = r9.var(r15)
            r7.visitVarInsn(r6, r0)
            com.alibaba.fastjson.parser.Feature r0 = com.alibaba.fastjson.parser.Feature.SortFeidFastMatch
            int r0 = r0.mask
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r7.visitLdcInsn(r0)
            java.lang.String r0 = JSONLexerBase
            java.lang.String r1 = "isEnabled"
            java.lang.String r2 = "(I)Z"
            r7.visitMethodInsn(r5, r0, r1, r2)
            com.alibaba.fastjson.asm.Label r0 = new com.alibaba.fastjson.asm.Label
            r0.<init>()
            r1 = 154(0x9a, float:2.16E-43)
            r7.visitJumpInsn(r1, r0)
            r1 = 200(0xc8, float:2.8E-43)
            r7.visitJumpInsn(r1, r12)
            r7.visitLabel(r0)
            int r0 = r9.var(r15)
            r7.visitVarInsn(r6, r0)
            java.lang.Class r0 = r33.clazz
            java.lang.String r0 = r0.getName()
            r7.visitLdcInsn(r0)
            java.lang.String r0 = JSONLexerBase
            java.lang.String r1 = "scanType"
            java.lang.String r2 = "(Ljava/lang/String;)I"
            r7.visitMethodInsn(r5, r0, r1, r2)
            r0 = -1
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r7.visitLdcInsn(r0)
            com.alibaba.fastjson.asm.Label r0 = new com.alibaba.fastjson.asm.Label
            r0.<init>()
            r7.visitJumpInsn(r4, r0)
            r1 = 200(0xc8, float:2.8E-43)
            r7.visitJumpInsn(r1, r12)
            r7.visitLabel(r0)
            r0 = 1
            r7.visitVarInsn(r6, r0)
            java.lang.String r0 = DefaultJSONParser
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "()"
            r1.append(r2)
            java.lang.Class<com.alibaba.fastjson.parser.ParseContext> r2 = com.alibaba.fastjson.parser.ParseContext.class
            java.lang.String r2 = com.alibaba.fastjson.util.ASMUtils.desc((java.lang.Class<?>) r2)
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            java.lang.String r2 = "getContext"
            r7.visitMethodInsn(r5, r0, r2, r1)
            java.lang.String r0 = "mark_context"
            int r0 = r9.var(r0)
            r10 = 58
            r7.visitVarInsn(r10, r0)
            r0 = 3
            r7.visitInsn(r0)
            java.lang.String r0 = "matchedCount"
            int r0 = r9.var(r0)
            r3 = 54
            r7.visitVarInsn(r3, r0)
            r8._createInstance((com.alibaba.fastjson.parser.deserializer.ASMDeserializerFactory.Context) r9, (com.alibaba.fastjson.asm.MethodVisitor) r7)
            r0 = 1
            r7.visitVarInsn(r6, r0)
            java.lang.String r0 = DefaultJSONParser
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "()"
            r1.append(r2)
            java.lang.Class<com.alibaba.fastjson.parser.ParseContext> r2 = com.alibaba.fastjson.parser.ParseContext.class
            java.lang.String r2 = com.alibaba.fastjson.util.ASMUtils.desc((java.lang.Class<?>) r2)
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            java.lang.String r2 = "getContext"
            r7.visitMethodInsn(r5, r0, r2, r1)
            java.lang.String r0 = "context"
            int r0 = r9.var(r0)
            r7.visitVarInsn(r10, r0)
            r0 = 1
            r7.visitVarInsn(r6, r0)
            java.lang.String r0 = "context"
            int r0 = r9.var(r0)
            r7.visitVarInsn(r6, r0)
            java.lang.String r0 = "instance"
            int r0 = r9.var(r0)
            r7.visitVarInsn(r6, r0)
            r0 = 3
            r7.visitVarInsn(r6, r0)
            java.lang.String r0 = DefaultJSONParser
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "("
            r1.append(r2)
            java.lang.Class<com.alibaba.fastjson.parser.ParseContext> r2 = com.alibaba.fastjson.parser.ParseContext.class
            java.lang.String r2 = com.alibaba.fastjson.util.ASMUtils.desc((java.lang.Class<?>) r2)
            r1.append(r2)
            java.lang.String r2 = "Ljava/lang/Object;Ljava/lang/Object;)"
            r1.append(r2)
            java.lang.Class<com.alibaba.fastjson.parser.ParseContext> r2 = com.alibaba.fastjson.parser.ParseContext.class
            java.lang.String r2 = com.alibaba.fastjson.util.ASMUtils.desc((java.lang.Class<?>) r2)
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            java.lang.String r2 = "setContext"
            r7.visitMethodInsn(r5, r0, r2, r1)
            java.lang.String r0 = "childContext"
            int r0 = r9.var(r0)
            r7.visitVarInsn(r10, r0)
            int r0 = r9.var(r15)
            r7.visitVarInsn(r6, r0)
            java.lang.String r0 = JSONLexerBase
            r2 = 180(0xb4, float:2.52E-43)
            java.lang.String r1 = "matchStat"
            java.lang.String r4 = "I"
            r7.visitFieldInsn(r2, r0, r1, r4)
            r0 = 4
            java.lang.Integer r2 = java.lang.Integer.valueOf(r0)
            r7.visitLdcInsn(r2)
            r0 = 159(0x9f, float:2.23E-43)
            r7.visitJumpInsn(r0, r13)
            r0 = 3
            r7.visitInsn(r0)
            int r2 = r9.var(r1)
            r7.visitIntInsn(r3, r2)
            com.alibaba.fastjson.util.FieldInfo[] r2 = r33.fieldInfoList
            int r2 = r2.length
            r10 = 0
        L_0x0262:
            if (r10 >= r2) goto L_0x0287
            r7.visitInsn(r0)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r5 = "_asm_flag_"
            r0.append(r5)
            int r5 = r10 / 32
            r0.append(r5)
            java.lang.String r0 = r0.toString()
            int r0 = r9.var(r0)
            r7.visitVarInsn(r3, r0)
            int r10 = r10 + 32
            r0 = 3
            r5 = 182(0xb6, float:2.55E-43)
            goto L_0x0262
        L_0x0287:
            int r0 = r9.var(r15)
            r7.visitVarInsn(r6, r0)
            com.alibaba.fastjson.parser.Feature r0 = com.alibaba.fastjson.parser.Feature.InitStringFieldAsEmpty
            int r0 = r0.mask
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r7.visitLdcInsn(r0)
            java.lang.String r0 = JSONLexerBase
            java.lang.String r5 = "isEnabled"
            java.lang.String r10 = "(I)Z"
            r6 = 182(0xb6, float:2.55E-43)
            r7.visitMethodInsn(r6, r0, r5, r10)
            java.lang.String r0 = "initStringFieldAsEmpty"
            int r0 = r9.var(r0)
            r7.visitIntInsn(r3, r0)
            r0 = 0
        L_0x02ae:
            java.lang.String r10 = "_asm"
            if (r0 >= r2) goto L_0x03f8
            com.alibaba.fastjson.util.FieldInfo[] r5 = r33.fieldInfoList
            r5 = r5[r0]
            java.lang.Class<?> r6 = r5.fieldClass
            java.lang.Class r3 = java.lang.Boolean.TYPE
            if (r6 == r3) goto L_0x03c2
            java.lang.Class r3 = java.lang.Byte.TYPE
            if (r6 == r3) goto L_0x03c2
            java.lang.Class r3 = java.lang.Short.TYPE
            if (r6 == r3) goto L_0x03c2
            java.lang.Class r3 = java.lang.Integer.TYPE
            if (r6 != r3) goto L_0x02cc
            goto L_0x03c2
        L_0x02cc:
            java.lang.Class r3 = java.lang.Long.TYPE
            if (r6 != r3) goto L_0x02fb
            r3 = 9
            r7.visitInsn(r3)
            r3 = 55
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r5 = r5.name
            r6.append(r5)
            r6.append(r10)
            java.lang.String r5 = r6.toString()
            r6 = 2
            int r5 = r9.var(r5, r6)
            r7.visitVarInsn(r3, r5)
        L_0x02f0:
            r27 = r11
            r24 = r12
            r25 = r13
            r26 = r14
        L_0x02f8:
            r3 = 3
            goto L_0x03e8
        L_0x02fb:
            java.lang.Class r3 = java.lang.Float.TYPE
            if (r6 != r3) goto L_0x031f
            r3 = 11
            r7.visitInsn(r3)
            r3 = 56
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r5 = r5.name
            r6.append(r5)
            r6.append(r10)
            java.lang.String r5 = r6.toString()
            int r5 = r9.var(r5)
            r7.visitVarInsn(r3, r5)
            goto L_0x02f0
        L_0x031f:
            java.lang.Class r3 = java.lang.Double.TYPE
            if (r6 != r3) goto L_0x0344
            r3 = 14
            r7.visitInsn(r3)
            r3 = 57
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r5 = r5.name
            r6.append(r5)
            r6.append(r10)
            java.lang.String r5 = r6.toString()
            r6 = 2
            int r5 = r9.var(r5, r6)
            r7.visitVarInsn(r3, r5)
            goto L_0x02f0
        L_0x0344:
            java.lang.Class<java.lang.String> r3 = java.lang.String.class
            if (r6 != r3) goto L_0x0391
            com.alibaba.fastjson.asm.Label r3 = new com.alibaba.fastjson.asm.Label
            r3.<init>()
            r24 = r12
            com.alibaba.fastjson.asm.Label r12 = new com.alibaba.fastjson.asm.Label
            r12.<init>()
            r25 = r13
            java.lang.String r13 = "initStringFieldAsEmpty"
            int r13 = r9.var(r13)
            r26 = r14
            r14 = 21
            r7.visitVarInsn(r14, r13)
            r13 = 153(0x99, float:2.14E-43)
            r7.visitJumpInsn(r13, r12)
            r8._setFlag(r7, r9, r0)
            int r13 = r9.var(r15)
            r14 = 25
            r7.visitVarInsn(r14, r13)
            java.lang.String r13 = JSONLexerBase
            java.lang.String r14 = "stringDefaultValue"
            r27 = r11
            java.lang.String r11 = "()Ljava/lang/String;"
            r8 = 182(0xb6, float:2.55E-43)
            r7.visitMethodInsn(r8, r13, r14, r11)
            r8 = 167(0xa7, float:2.34E-43)
            r7.visitJumpInsn(r8, r3)
            r7.visitLabel(r12)
            r8 = 1
            r7.visitInsn(r8)
            r7.visitLabel(r3)
            goto L_0x039d
        L_0x0391:
            r27 = r11
            r24 = r12
            r25 = r13
            r26 = r14
            r8 = 1
            r7.visitInsn(r8)
        L_0x039d:
            r3 = 192(0xc0, float:2.69E-43)
            java.lang.String r6 = com.alibaba.fastjson.util.ASMUtils.type(r6)
            r7.visitTypeInsn(r3, r6)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r5 = r5.name
            r3.append(r5)
            r3.append(r10)
            java.lang.String r3 = r3.toString()
            int r3 = r9.var(r3)
            r5 = 58
            r7.visitVarInsn(r5, r3)
            goto L_0x02f8
        L_0x03c2:
            r27 = r11
            r24 = r12
            r25 = r13
            r26 = r14
            r3 = 3
            r7.visitInsn(r3)
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r5 = r5.name
            r6.append(r5)
            r6.append(r10)
            java.lang.String r5 = r6.toString()
            int r5 = r9.var(r5)
            r6 = 54
            r7.visitVarInsn(r6, r5)
        L_0x03e8:
            int r0 = r0 + 1
            r8 = r31
            r12 = r24
            r13 = r25
            r14 = r26
            r11 = r27
            r3 = 54
            goto L_0x02ae
        L_0x03f8:
            r27 = r11
            r24 = r12
            r25 = r13
            r26 = r14
            r3 = 3
            r8 = 0
        L_0x0402:
            if (r8 >= r2) goto L_0x0ef9
            com.alibaba.fastjson.util.FieldInfo[] r0 = r33.fieldInfoList
            r5 = r0[r8]
            java.lang.Class<?> r6 = r5.fieldClass
            java.lang.reflect.Type r0 = r5.fieldType
            com.alibaba.fastjson.asm.Label r11 = new com.alibaba.fastjson.asm.Label
            r11.<init>()
            java.lang.Class r12 = java.lang.Boolean.TYPE
            java.lang.String r13 = "[C"
            java.lang.String r14 = "_asm_prefix__"
            if (r6 != r12) goto L_0x0476
            int r0 = r9.var(r15)
            r6 = 25
            r7.visitVarInsn(r6, r0)
            r0 = 0
            r7.visitVarInsn(r6, r0)
            java.lang.String r0 = r33.className
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r12 = r5.name
            r6.append(r12)
            r6.append(r14)
            java.lang.String r6 = r6.toString()
            r12 = 180(0xb4, float:2.52E-43)
            r7.visitFieldInsn(r12, r0, r6, r13)
            java.lang.String r0 = JSONLexerBase
            java.lang.String r6 = "scanFieldBoolean"
            java.lang.String r12 = "([C)Z"
            r13 = 182(0xb6, float:2.55E-43)
            r7.visitMethodInsn(r13, r0, r6, r12)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r5 = r5.name
            r0.append(r5)
            r0.append(r10)
            java.lang.String r0 = r0.toString()
            int r0 = r9.var(r0)
            r5 = 54
            r7.visitVarInsn(r5, r0)
            r28 = r2
        L_0x0469:
            r30 = r8
            r29 = r11
            r8 = 1
            r13 = 182(0xb6, float:2.55E-43)
            r14 = 58
            r11 = r31
            goto L_0x0da5
        L_0x0476:
            java.lang.Class r12 = java.lang.Byte.TYPE
            java.lang.String r3 = "([C)I"
            r28 = r2
            java.lang.String r2 = "scanFieldInt"
            if (r6 != r12) goto L_0x04c9
            int r0 = r9.var(r15)
            r6 = 25
            r7.visitVarInsn(r6, r0)
            r0 = 0
            r7.visitVarInsn(r6, r0)
            java.lang.String r0 = r33.className
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r12 = r5.name
            r6.append(r12)
            r6.append(r14)
            java.lang.String r6 = r6.toString()
            r12 = 180(0xb4, float:2.52E-43)
            r7.visitFieldInsn(r12, r0, r6, r13)
            java.lang.String r0 = JSONLexerBase
            r6 = 182(0xb6, float:2.55E-43)
            r7.visitMethodInsn(r6, r0, r2, r3)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r2 = r5.name
            r0.append(r2)
            r0.append(r10)
            java.lang.String r0 = r0.toString()
            int r0 = r9.var(r0)
            r2 = 54
            r7.visitVarInsn(r2, r0)
            goto L_0x0469
        L_0x04c9:
            java.lang.Class<java.lang.Byte> r12 = java.lang.Byte.class
            r29 = r11
            java.lang.String r11 = "valueOf"
            r30 = r8
            r8 = 184(0xb8, float:2.58E-43)
            if (r6 != r12) goto L_0x0570
            int r0 = r9.var(r15)
            r6 = 25
            r7.visitVarInsn(r6, r0)
            r0 = 0
            r7.visitVarInsn(r6, r0)
            java.lang.String r0 = r33.className
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r12 = r5.name
            r6.append(r12)
            r6.append(r14)
            java.lang.String r6 = r6.toString()
            r12 = 180(0xb4, float:2.52E-43)
            r7.visitFieldInsn(r12, r0, r6, r13)
            java.lang.String r0 = JSONLexerBase
            r6 = 182(0xb6, float:2.55E-43)
            r7.visitMethodInsn(r6, r0, r2, r3)
            java.lang.String r0 = "java/lang/Byte"
            java.lang.String r2 = "(B)Ljava/lang/Byte;"
            r7.visitMethodInsn(r8, r0, r11, r2)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r2 = r5.name
            r0.append(r2)
            r0.append(r10)
            java.lang.String r0 = r0.toString()
            int r0 = r9.var(r0)
            r2 = 58
            r7.visitVarInsn(r2, r0)
            com.alibaba.fastjson.asm.Label r0 = new com.alibaba.fastjson.asm.Label
            r0.<init>()
            int r2 = r9.var(r15)
            r3 = 25
            r7.visitVarInsn(r3, r2)
            java.lang.String r2 = JSONLexerBase
            r3 = 180(0xb4, float:2.52E-43)
            r7.visitFieldInsn(r3, r2, r1, r4)
            r2 = 5
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r7.visitLdcInsn(r2)
            r2 = 160(0xa0, float:2.24E-43)
            r7.visitJumpInsn(r2, r0)
            r2 = 1
            r7.visitInsn(r2)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = r5.name
            r2.append(r3)
            r2.append(r10)
            java.lang.String r2 = r2.toString()
            int r2 = r9.var(r2)
            r3 = 58
            r7.visitVarInsn(r3, r2)
            r7.visitLabel(r0)
        L_0x0567:
            r11 = r31
            r8 = 1
        L_0x056a:
            r13 = 182(0xb6, float:2.55E-43)
            r14 = 58
            goto L_0x0da5
        L_0x0570:
            java.lang.Class r12 = java.lang.Short.TYPE
            if (r6 != r12) goto L_0x05bd
            int r0 = r9.var(r15)
            r6 = 25
            r7.visitVarInsn(r6, r0)
            r0 = 0
            r7.visitVarInsn(r6, r0)
            java.lang.String r0 = r33.className
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r8 = r5.name
            r6.append(r8)
            r6.append(r14)
            java.lang.String r6 = r6.toString()
            r8 = 180(0xb4, float:2.52E-43)
            r7.visitFieldInsn(r8, r0, r6, r13)
            java.lang.String r0 = JSONLexerBase
            r6 = 182(0xb6, float:2.55E-43)
            r7.visitMethodInsn(r6, r0, r2, r3)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r2 = r5.name
            r0.append(r2)
            r0.append(r10)
            java.lang.String r0 = r0.toString()
            int r0 = r9.var(r0)
            r2 = 54
            r7.visitVarInsn(r2, r0)
            goto L_0x0567
        L_0x05bd:
            java.lang.Class<java.lang.Short> r12 = java.lang.Short.class
            if (r6 != r12) goto L_0x0655
            int r0 = r9.var(r15)
            r6 = 25
            r7.visitVarInsn(r6, r0)
            r0 = 0
            r7.visitVarInsn(r6, r0)
            java.lang.String r0 = r33.className
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r12 = r5.name
            r6.append(r12)
            r6.append(r14)
            java.lang.String r6 = r6.toString()
            r12 = 180(0xb4, float:2.52E-43)
            r7.visitFieldInsn(r12, r0, r6, r13)
            java.lang.String r0 = JSONLexerBase
            r6 = 182(0xb6, float:2.55E-43)
            r7.visitMethodInsn(r6, r0, r2, r3)
            java.lang.String r0 = "java/lang/Short"
            java.lang.String r2 = "(S)Ljava/lang/Short;"
            r7.visitMethodInsn(r8, r0, r11, r2)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r2 = r5.name
            r0.append(r2)
            r0.append(r10)
            java.lang.String r0 = r0.toString()
            int r0 = r9.var(r0)
            r2 = 58
            r7.visitVarInsn(r2, r0)
            com.alibaba.fastjson.asm.Label r0 = new com.alibaba.fastjson.asm.Label
            r0.<init>()
            int r2 = r9.var(r15)
            r3 = 25
            r7.visitVarInsn(r3, r2)
            java.lang.String r2 = JSONLexerBase
            r3 = 180(0xb4, float:2.52E-43)
            r7.visitFieldInsn(r3, r2, r1, r4)
            r2 = 5
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r7.visitLdcInsn(r2)
            r2 = 160(0xa0, float:2.24E-43)
            r7.visitJumpInsn(r2, r0)
            r2 = 1
            r7.visitInsn(r2)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = r5.name
            r2.append(r3)
            r2.append(r10)
            java.lang.String r2 = r2.toString()
            int r2 = r9.var(r2)
            r3 = 58
            r7.visitVarInsn(r3, r2)
            r7.visitLabel(r0)
            goto L_0x0567
        L_0x0655:
            java.lang.Class r12 = java.lang.Integer.TYPE
            if (r6 != r12) goto L_0x06a3
            int r0 = r9.var(r15)
            r6 = 25
            r7.visitVarInsn(r6, r0)
            r0 = 0
            r7.visitVarInsn(r6, r0)
            java.lang.String r0 = r33.className
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r8 = r5.name
            r6.append(r8)
            r6.append(r14)
            java.lang.String r6 = r6.toString()
            r8 = 180(0xb4, float:2.52E-43)
            r7.visitFieldInsn(r8, r0, r6, r13)
            java.lang.String r0 = JSONLexerBase
            r6 = 182(0xb6, float:2.55E-43)
            r7.visitMethodInsn(r6, r0, r2, r3)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r2 = r5.name
            r0.append(r2)
            r0.append(r10)
            java.lang.String r0 = r0.toString()
            int r0 = r9.var(r0)
            r2 = 54
            r7.visitVarInsn(r2, r0)
            goto L_0x0567
        L_0x06a3:
            java.lang.Class<java.lang.Integer> r12 = java.lang.Integer.class
            if (r6 != r12) goto L_0x073b
            int r0 = r9.var(r15)
            r6 = 25
            r7.visitVarInsn(r6, r0)
            r0 = 0
            r7.visitVarInsn(r6, r0)
            java.lang.String r0 = r33.className
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r12 = r5.name
            r6.append(r12)
            r6.append(r14)
            java.lang.String r6 = r6.toString()
            r12 = 180(0xb4, float:2.52E-43)
            r7.visitFieldInsn(r12, r0, r6, r13)
            java.lang.String r0 = JSONLexerBase
            r6 = 182(0xb6, float:2.55E-43)
            r7.visitMethodInsn(r6, r0, r2, r3)
            java.lang.String r0 = "java/lang/Integer"
            java.lang.String r2 = "(I)Ljava/lang/Integer;"
            r7.visitMethodInsn(r8, r0, r11, r2)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r2 = r5.name
            r0.append(r2)
            r0.append(r10)
            java.lang.String r0 = r0.toString()
            int r0 = r9.var(r0)
            r2 = 58
            r7.visitVarInsn(r2, r0)
            com.alibaba.fastjson.asm.Label r0 = new com.alibaba.fastjson.asm.Label
            r0.<init>()
            int r2 = r9.var(r15)
            r3 = 25
            r7.visitVarInsn(r3, r2)
            java.lang.String r2 = JSONLexerBase
            r3 = 180(0xb4, float:2.52E-43)
            r7.visitFieldInsn(r3, r2, r1, r4)
            r2 = 5
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r7.visitLdcInsn(r2)
            r2 = 160(0xa0, float:2.24E-43)
            r7.visitJumpInsn(r2, r0)
            r2 = 1
            r7.visitInsn(r2)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = r5.name
            r2.append(r3)
            r2.append(r10)
            java.lang.String r2 = r2.toString()
            int r2 = r9.var(r2)
            r3 = 58
            r7.visitVarInsn(r3, r2)
            r7.visitLabel(r0)
            goto L_0x0567
        L_0x073b:
            java.lang.Class r2 = java.lang.Long.TYPE
            if (r6 != r2) goto L_0x078e
            int r0 = r9.var(r15)
            r2 = 25
            r7.visitVarInsn(r2, r0)
            r0 = 0
            r7.visitVarInsn(r2, r0)
            java.lang.String r0 = r33.className
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = r5.name
            r2.append(r3)
            r2.append(r14)
            java.lang.String r2 = r2.toString()
            r3 = 180(0xb4, float:2.52E-43)
            r7.visitFieldInsn(r3, r0, r2, r13)
            java.lang.String r0 = JSONLexerBase
            java.lang.String r2 = "scanFieldLong"
            java.lang.String r3 = "([C)J"
            r6 = 182(0xb6, float:2.55E-43)
            r7.visitMethodInsn(r6, r0, r2, r3)
            r0 = 55
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = r5.name
            r2.append(r3)
            r2.append(r10)
            java.lang.String r2 = r2.toString()
            r3 = 2
            int r2 = r9.var(r2, r3)
            r7.visitVarInsn(r0, r2)
            goto L_0x0567
        L_0x078e:
            java.lang.Class<java.lang.Long> r2 = java.lang.Long.class
            if (r6 != r2) goto L_0x082a
            int r0 = r9.var(r15)
            r2 = 25
            r7.visitVarInsn(r2, r0)
            r0 = 0
            r7.visitVarInsn(r2, r0)
            java.lang.String r0 = r33.className
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = r5.name
            r2.append(r3)
            r2.append(r14)
            java.lang.String r2 = r2.toString()
            r3 = 180(0xb4, float:2.52E-43)
            r7.visitFieldInsn(r3, r0, r2, r13)
            java.lang.String r0 = JSONLexerBase
            java.lang.String r2 = "scanFieldLong"
            java.lang.String r3 = "([C)J"
            r6 = 182(0xb6, float:2.55E-43)
            r7.visitMethodInsn(r6, r0, r2, r3)
            java.lang.String r0 = "java/lang/Long"
            java.lang.String r2 = "(J)Ljava/lang/Long;"
            r7.visitMethodInsn(r8, r0, r11, r2)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r2 = r5.name
            r0.append(r2)
            r0.append(r10)
            java.lang.String r0 = r0.toString()
            int r0 = r9.var(r0)
            r2 = 58
            r7.visitVarInsn(r2, r0)
            com.alibaba.fastjson.asm.Label r0 = new com.alibaba.fastjson.asm.Label
            r0.<init>()
            int r2 = r9.var(r15)
            r3 = 25
            r7.visitVarInsn(r3, r2)
            java.lang.String r2 = JSONLexerBase
            r3 = 180(0xb4, float:2.52E-43)
            r7.visitFieldInsn(r3, r2, r1, r4)
            r2 = 5
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r7.visitLdcInsn(r2)
            r2 = 160(0xa0, float:2.24E-43)
            r7.visitJumpInsn(r2, r0)
            r2 = 1
            r7.visitInsn(r2)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = r5.name
            r2.append(r3)
            r2.append(r10)
            java.lang.String r2 = r2.toString()
            int r2 = r9.var(r2)
            r3 = 58
            r7.visitVarInsn(r3, r2)
            r7.visitLabel(r0)
            goto L_0x0567
        L_0x082a:
            java.lang.Class r2 = java.lang.Float.TYPE
            if (r6 != r2) goto L_0x087c
            int r0 = r9.var(r15)
            r2 = 25
            r7.visitVarInsn(r2, r0)
            r0 = 0
            r7.visitVarInsn(r2, r0)
            java.lang.String r0 = r33.className
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = r5.name
            r2.append(r3)
            r2.append(r14)
            java.lang.String r2 = r2.toString()
            r3 = 180(0xb4, float:2.52E-43)
            r7.visitFieldInsn(r3, r0, r2, r13)
            java.lang.String r0 = JSONLexerBase
            java.lang.String r2 = "scanFieldFloat"
            java.lang.String r3 = "([C)F"
            r6 = 182(0xb6, float:2.55E-43)
            r7.visitMethodInsn(r6, r0, r2, r3)
            r0 = 56
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = r5.name
            r2.append(r3)
            r2.append(r10)
            java.lang.String r2 = r2.toString()
            int r2 = r9.var(r2)
            r7.visitVarInsn(r0, r2)
            goto L_0x0567
        L_0x087c:
            java.lang.Class<java.lang.Float> r2 = java.lang.Float.class
            if (r6 != r2) goto L_0x0918
            int r0 = r9.var(r15)
            r2 = 25
            r7.visitVarInsn(r2, r0)
            r0 = 0
            r7.visitVarInsn(r2, r0)
            java.lang.String r0 = r33.className
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = r5.name
            r2.append(r3)
            r2.append(r14)
            java.lang.String r2 = r2.toString()
            r3 = 180(0xb4, float:2.52E-43)
            r7.visitFieldInsn(r3, r0, r2, r13)
            java.lang.String r0 = JSONLexerBase
            java.lang.String r2 = "scanFieldFloat"
            java.lang.String r3 = "([C)F"
            r6 = 182(0xb6, float:2.55E-43)
            r7.visitMethodInsn(r6, r0, r2, r3)
            java.lang.String r0 = "java/lang/Float"
            java.lang.String r2 = "(F)Ljava/lang/Float;"
            r7.visitMethodInsn(r8, r0, r11, r2)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r2 = r5.name
            r0.append(r2)
            r0.append(r10)
            java.lang.String r0 = r0.toString()
            int r0 = r9.var(r0)
            r2 = 58
            r7.visitVarInsn(r2, r0)
            com.alibaba.fastjson.asm.Label r0 = new com.alibaba.fastjson.asm.Label
            r0.<init>()
            int r2 = r9.var(r15)
            r3 = 25
            r7.visitVarInsn(r3, r2)
            java.lang.String r2 = JSONLexerBase
            r3 = 180(0xb4, float:2.52E-43)
            r7.visitFieldInsn(r3, r2, r1, r4)
            r2 = 5
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r7.visitLdcInsn(r2)
            r2 = 160(0xa0, float:2.24E-43)
            r7.visitJumpInsn(r2, r0)
            r2 = 1
            r7.visitInsn(r2)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = r5.name
            r2.append(r3)
            r2.append(r10)
            java.lang.String r2 = r2.toString()
            int r2 = r9.var(r2)
            r3 = 58
            r7.visitVarInsn(r3, r2)
            r7.visitLabel(r0)
            goto L_0x0567
        L_0x0918:
            java.lang.Class r2 = java.lang.Double.TYPE
            if (r6 != r2) goto L_0x096b
            int r0 = r9.var(r15)
            r2 = 25
            r7.visitVarInsn(r2, r0)
            r0 = 0
            r7.visitVarInsn(r2, r0)
            java.lang.String r0 = r33.className
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = r5.name
            r2.append(r3)
            r2.append(r14)
            java.lang.String r2 = r2.toString()
            r3 = 180(0xb4, float:2.52E-43)
            r7.visitFieldInsn(r3, r0, r2, r13)
            java.lang.String r0 = JSONLexerBase
            java.lang.String r2 = "scanFieldDouble"
            java.lang.String r3 = "([C)D"
            r6 = 182(0xb6, float:2.55E-43)
            r7.visitMethodInsn(r6, r0, r2, r3)
            r0 = 57
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = r5.name
            r2.append(r3)
            r2.append(r10)
            java.lang.String r2 = r2.toString()
            r3 = 2
            int r2 = r9.var(r2, r3)
            r7.visitVarInsn(r0, r2)
            goto L_0x0567
        L_0x096b:
            r3 = 2
            java.lang.Class<java.lang.Double> r2 = java.lang.Double.class
            if (r6 != r2) goto L_0x0a0a
            int r0 = r9.var(r15)
            r2 = 25
            r7.visitVarInsn(r2, r0)
            r0 = 0
            r7.visitVarInsn(r2, r0)
            java.lang.String r0 = r33.className
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r6 = r5.name
            r2.append(r6)
            r2.append(r14)
            java.lang.String r2 = r2.toString()
            r6 = 180(0xb4, float:2.52E-43)
            r7.visitFieldInsn(r6, r0, r2, r13)
            java.lang.String r0 = JSONLexerBase
            java.lang.String r2 = "scanFieldDouble"
            java.lang.String r6 = "([C)D"
            r12 = 182(0xb6, float:2.55E-43)
            r7.visitMethodInsn(r12, r0, r2, r6)
            java.lang.String r0 = "java/lang/Double"
            java.lang.String r2 = "(D)Ljava/lang/Double;"
            r7.visitMethodInsn(r8, r0, r11, r2)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r2 = r5.name
            r0.append(r2)
            r0.append(r10)
            java.lang.String r0 = r0.toString()
            int r0 = r9.var(r0)
            r2 = 58
            r7.visitVarInsn(r2, r0)
            com.alibaba.fastjson.asm.Label r0 = new com.alibaba.fastjson.asm.Label
            r0.<init>()
            int r2 = r9.var(r15)
            r6 = 25
            r7.visitVarInsn(r6, r2)
            java.lang.String r2 = JSONLexerBase
            r6 = 180(0xb4, float:2.52E-43)
            r7.visitFieldInsn(r6, r2, r1, r4)
            r2 = 5
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r7.visitLdcInsn(r2)
            r2 = 160(0xa0, float:2.24E-43)
            r7.visitJumpInsn(r2, r0)
            r8 = 1
            r7.visitInsn(r8)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r5 = r5.name
            r2.append(r5)
            r2.append(r10)
            java.lang.String r2 = r2.toString()
            int r2 = r9.var(r2)
            r5 = 58
            r7.visitVarInsn(r5, r2)
            r7.visitLabel(r0)
        L_0x0a06:
            r11 = r31
            goto L_0x056a
        L_0x0a0a:
            r8 = 1
            java.lang.Class<java.lang.String> r2 = java.lang.String.class
            if (r6 != r2) goto L_0x0a5c
            int r0 = r9.var(r15)
            r2 = 25
            r7.visitVarInsn(r2, r0)
            r0 = 0
            r7.visitVarInsn(r2, r0)
            java.lang.String r0 = r33.className
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r6 = r5.name
            r2.append(r6)
            r2.append(r14)
            java.lang.String r2 = r2.toString()
            r6 = 180(0xb4, float:2.52E-43)
            r7.visitFieldInsn(r6, r0, r2, r13)
            java.lang.String r0 = JSONLexerBase
            java.lang.String r2 = "scanFieldString"
            java.lang.String r6 = "([C)Ljava/lang/String;"
            r11 = 182(0xb6, float:2.55E-43)
            r7.visitMethodInsn(r11, r0, r2, r6)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r2 = r5.name
            r0.append(r2)
            r0.append(r10)
            java.lang.String r0 = r0.toString()
            int r0 = r9.var(r0)
            r2 = 58
            r7.visitVarInsn(r2, r0)
            goto L_0x0a06
        L_0x0a5c:
            java.lang.Class<java.util.Date> r2 = java.util.Date.class
            if (r6 != r2) goto L_0x0aae
            int r0 = r9.var(r15)
            r2 = 25
            r7.visitVarInsn(r2, r0)
            r0 = 0
            r7.visitVarInsn(r2, r0)
            java.lang.String r0 = r33.className
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r6 = r5.name
            r2.append(r6)
            r2.append(r14)
            java.lang.String r2 = r2.toString()
            r6 = 180(0xb4, float:2.52E-43)
            r7.visitFieldInsn(r6, r0, r2, r13)
            java.lang.String r0 = JSONLexerBase
            java.lang.String r2 = "scanFieldDate"
            java.lang.String r6 = "([C)Ljava/util/Date;"
            r11 = 182(0xb6, float:2.55E-43)
            r7.visitMethodInsn(r11, r0, r2, r6)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r2 = r5.name
            r0.append(r2)
            r0.append(r10)
            java.lang.String r0 = r0.toString()
            int r0 = r9.var(r0)
            r2 = 58
            r7.visitVarInsn(r2, r0)
            goto L_0x0a06
        L_0x0aae:
            java.lang.Class<java.util.UUID> r2 = java.util.UUID.class
            if (r6 != r2) goto L_0x0b00
            int r0 = r9.var(r15)
            r2 = 25
            r7.visitVarInsn(r2, r0)
            r0 = 0
            r7.visitVarInsn(r2, r0)
            java.lang.String r0 = r33.className
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r6 = r5.name
            r2.append(r6)
            r2.append(r14)
            java.lang.String r2 = r2.toString()
            r6 = 180(0xb4, float:2.52E-43)
            r7.visitFieldInsn(r6, r0, r2, r13)
            java.lang.String r0 = JSONLexerBase
            java.lang.String r2 = "scanFieldUUID"
            java.lang.String r6 = "([C)Ljava/util/UUID;"
            r11 = 182(0xb6, float:2.55E-43)
            r7.visitMethodInsn(r11, r0, r2, r6)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r2 = r5.name
            r0.append(r2)
            r0.append(r10)
            java.lang.String r0 = r0.toString()
            int r0 = r9.var(r0)
            r2 = 58
            r7.visitVarInsn(r2, r0)
            goto L_0x0a06
        L_0x0b00:
            java.lang.Class<java.math.BigDecimal> r2 = java.math.BigDecimal.class
            if (r6 != r2) goto L_0x0b52
            int r0 = r9.var(r15)
            r2 = 25
            r7.visitVarInsn(r2, r0)
            r0 = 0
            r7.visitVarInsn(r2, r0)
            java.lang.String r0 = r33.className
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r6 = r5.name
            r2.append(r6)
            r2.append(r14)
            java.lang.String r2 = r2.toString()
            r6 = 180(0xb4, float:2.52E-43)
            r7.visitFieldInsn(r6, r0, r2, r13)
            java.lang.String r0 = JSONLexerBase
            java.lang.String r2 = "scanFieldDecimal"
            java.lang.String r6 = "([C)Ljava/math/BigDecimal;"
            r11 = 182(0xb6, float:2.55E-43)
            r7.visitMethodInsn(r11, r0, r2, r6)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r2 = r5.name
            r0.append(r2)
            r0.append(r10)
            java.lang.String r0 = r0.toString()
            int r0 = r9.var(r0)
            r2 = 58
            r7.visitVarInsn(r2, r0)
            goto L_0x0a06
        L_0x0b52:
            java.lang.Class<java.math.BigInteger> r2 = java.math.BigInteger.class
            if (r6 != r2) goto L_0x0ba4
            int r0 = r9.var(r15)
            r2 = 25
            r7.visitVarInsn(r2, r0)
            r0 = 0
            r7.visitVarInsn(r2, r0)
            java.lang.String r0 = r33.className
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r6 = r5.name
            r2.append(r6)
            r2.append(r14)
            java.lang.String r2 = r2.toString()
            r6 = 180(0xb4, float:2.52E-43)
            r7.visitFieldInsn(r6, r0, r2, r13)
            java.lang.String r0 = JSONLexerBase
            java.lang.String r2 = "scanFieldBigInteger"
            java.lang.String r6 = "([C)Ljava/math/BigInteger;"
            r11 = 182(0xb6, float:2.55E-43)
            r7.visitMethodInsn(r11, r0, r2, r6)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r2 = r5.name
            r0.append(r2)
            r0.append(r10)
            java.lang.String r0 = r0.toString()
            int r0 = r9.var(r0)
            r2 = 58
            r7.visitVarInsn(r2, r0)
            goto L_0x0a06
        L_0x0ba4:
            java.lang.Class<int[]> r2 = int[].class
            if (r6 != r2) goto L_0x0bf6
            int r0 = r9.var(r15)
            r2 = 25
            r7.visitVarInsn(r2, r0)
            r0 = 0
            r7.visitVarInsn(r2, r0)
            java.lang.String r0 = r33.className
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r6 = r5.name
            r2.append(r6)
            r2.append(r14)
            java.lang.String r2 = r2.toString()
            r6 = 180(0xb4, float:2.52E-43)
            r7.visitFieldInsn(r6, r0, r2, r13)
            java.lang.String r0 = JSONLexerBase
            java.lang.String r2 = "scanFieldIntArray"
            java.lang.String r6 = "([C)[I"
            r11 = 182(0xb6, float:2.55E-43)
            r7.visitMethodInsn(r11, r0, r2, r6)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r2 = r5.name
            r0.append(r2)
            r0.append(r10)
            java.lang.String r0 = r0.toString()
            int r0 = r9.var(r0)
            r2 = 58
            r7.visitVarInsn(r2, r0)
            goto L_0x0a06
        L_0x0bf6:
            java.lang.Class<float[]> r2 = float[].class
            if (r6 != r2) goto L_0x0c48
            int r0 = r9.var(r15)
            r2 = 25
            r7.visitVarInsn(r2, r0)
            r0 = 0
            r7.visitVarInsn(r2, r0)
            java.lang.String r0 = r33.className
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r6 = r5.name
            r2.append(r6)
            r2.append(r14)
            java.lang.String r2 = r2.toString()
            r6 = 180(0xb4, float:2.52E-43)
            r7.visitFieldInsn(r6, r0, r2, r13)
            java.lang.String r0 = JSONLexerBase
            java.lang.String r2 = "scanFieldFloatArray"
            java.lang.String r6 = "([C)[F"
            r11 = 182(0xb6, float:2.55E-43)
            r7.visitMethodInsn(r11, r0, r2, r6)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r2 = r5.name
            r0.append(r2)
            r0.append(r10)
            java.lang.String r0 = r0.toString()
            int r0 = r9.var(r0)
            r2 = 58
            r7.visitVarInsn(r2, r0)
            goto L_0x0a06
        L_0x0c48:
            java.lang.Class<float[][]> r2 = float[][].class
            if (r6 != r2) goto L_0x0c9a
            int r0 = r9.var(r15)
            r2 = 25
            r7.visitVarInsn(r2, r0)
            r0 = 0
            r7.visitVarInsn(r2, r0)
            java.lang.String r0 = r33.className
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r6 = r5.name
            r2.append(r6)
            r2.append(r14)
            java.lang.String r2 = r2.toString()
            r6 = 180(0xb4, float:2.52E-43)
            r7.visitFieldInsn(r6, r0, r2, r13)
            java.lang.String r0 = JSONLexerBase
            java.lang.String r2 = "scanFieldFloatArray2"
            java.lang.String r6 = "([C)[[F"
            r11 = 182(0xb6, float:2.55E-43)
            r7.visitMethodInsn(r11, r0, r2, r6)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r2 = r5.name
            r0.append(r2)
            r0.append(r10)
            java.lang.String r0 = r0.toString()
            int r0 = r9.var(r0)
            r2 = 58
            r7.visitVarInsn(r2, r0)
            goto L_0x0a06
        L_0x0c9a:
            boolean r2 = r6.isEnum()
            if (r2 == 0) goto L_0x0d27
            r2 = 0
            r11 = 25
            r7.visitVarInsn(r11, r2)
            int r0 = r9.var(r15)
            r7.visitVarInsn(r11, r0)
            r7.visitVarInsn(r11, r2)
            java.lang.String r0 = r33.className
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r11 = r5.name
            r2.append(r11)
            r2.append(r14)
            java.lang.String r2 = r2.toString()
            r11 = 180(0xb4, float:2.52E-43)
            r7.visitFieldInsn(r11, r0, r2, r13)
            r11 = r31
            r11._getFieldDeser(r9, r7, r5)
            java.lang.Class<com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer> r0 = com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer.class
            java.lang.String r0 = com.alibaba.fastjson.util.ASMUtils.type(r0)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r12 = "(L"
            r2.append(r12)
            java.lang.String r12 = JSONLexerBase
            r2.append(r12)
            java.lang.String r12 = ";[C"
            r2.append(r12)
            java.lang.Class<com.alibaba.fastjson.parser.deserializer.ObjectDeserializer> r12 = com.alibaba.fastjson.parser.deserializer.ObjectDeserializer.class
            java.lang.String r12 = com.alibaba.fastjson.util.ASMUtils.desc((java.lang.Class<?>) r12)
            r2.append(r12)
            java.lang.String r12 = ")Ljava/lang/Enum;"
            r2.append(r12)
            java.lang.String r2 = r2.toString()
            java.lang.String r12 = "scanEnum"
            r13 = 182(0xb6, float:2.55E-43)
            r7.visitMethodInsn(r13, r0, r12, r2)
            r0 = 192(0xc0, float:2.69E-43)
            java.lang.String r2 = com.alibaba.fastjson.util.ASMUtils.type(r6)
            r7.visitTypeInsn(r0, r2)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r2 = r5.name
            r0.append(r2)
            r0.append(r10)
            java.lang.String r0 = r0.toString()
            int r0 = r9.var(r0)
            r2 = 58
            r7.visitVarInsn(r2, r0)
            goto L_0x056a
        L_0x0d27:
            r11 = r31
            java.lang.Class<java.util.Collection> r2 = java.util.Collection.class
            boolean r2 = r2.isAssignableFrom(r6)
            if (r2 == 0) goto L_0x0ebd
            int r2 = r9.var(r15)
            r12 = 25
            r7.visitVarInsn(r12, r2)
            r2 = 0
            r7.visitVarInsn(r12, r2)
            java.lang.String r2 = r33.className
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            java.lang.String r3 = r5.name
            r12.append(r3)
            r12.append(r14)
            java.lang.String r3 = r12.toString()
            r12 = 180(0xb4, float:2.52E-43)
            r7.visitFieldInsn(r12, r2, r3, r13)
            java.lang.Class r12 = com.alibaba.fastjson.util.TypeUtils.getCollectionItemClass(r0)
            java.lang.Class<java.lang.String> r0 = java.lang.String.class
            if (r12 != r0) goto L_0x0e80
            java.lang.String r0 = com.alibaba.fastjson.util.ASMUtils.desc((java.lang.Class<?>) r6)
            com.alibaba.fastjson.asm.Type r0 = com.alibaba.fastjson.asm.Type.getType(r0)
            r7.visitLdcInsn(r0)
            java.lang.String r0 = JSONLexerBase
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "([CLjava/lang/Class;)"
            r2.append(r3)
            java.lang.Class<java.util.Collection> r3 = java.util.Collection.class
            java.lang.String r3 = com.alibaba.fastjson.util.ASMUtils.desc((java.lang.Class<?>) r3)
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            java.lang.String r3 = "scanFieldStringArray"
            r13 = 182(0xb6, float:2.55E-43)
            r7.visitMethodInsn(r13, r0, r3, r2)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r2 = r5.name
            r0.append(r2)
            r0.append(r10)
            java.lang.String r0 = r0.toString()
            int r0 = r9.var(r0)
            r14 = 58
            r7.visitVarInsn(r14, r0)
        L_0x0da5:
            int r0 = r9.var(r15)
            r2 = 25
            r7.visitVarInsn(r2, r0)
            java.lang.String r0 = JSONLexerBase
            r3 = 180(0xb4, float:2.52E-43)
            r7.visitFieldInsn(r3, r0, r1, r4)
            com.alibaba.fastjson.asm.Label r0 = new com.alibaba.fastjson.asm.Label
            r0.<init>()
            r5 = 158(0x9e, float:2.21E-43)
            r7.visitJumpInsn(r5, r0)
            r5 = r30
            r11._setFlag(r7, r9, r5)
            r7.visitLabel(r0)
            int r0 = r9.var(r15)
            r7.visitVarInsn(r2, r0)
            java.lang.String r0 = JSONLexerBase
            r7.visitFieldInsn(r3, r0, r1, r4)
            r0 = 89
            r7.visitInsn(r0)
            int r0 = r9.var(r1)
            r2 = 54
            r7.visitVarInsn(r2, r0)
            r0 = -1
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r7.visitLdcInsn(r0)
            r0 = 159(0x9f, float:2.23E-43)
            r3 = r27
            r7.visitJumpInsn(r0, r3)
            int r0 = r9.var(r15)
            r2 = 25
            r7.visitVarInsn(r2, r0)
            java.lang.String r0 = JSONLexerBase
            r2 = 180(0xb4, float:2.52E-43)
            r7.visitFieldInsn(r2, r0, r1, r4)
            r0 = 158(0x9e, float:2.21E-43)
            r2 = r29
            r7.visitJumpInsn(r0, r2)
            java.lang.String r0 = "matchedCount"
            int r0 = r9.var(r0)
            r6 = 21
            r7.visitVarInsn(r6, r0)
            r0 = 4
            r7.visitInsn(r0)
            r12 = 96
            r7.visitInsn(r12)
            java.lang.String r12 = "matchedCount"
            int r12 = r9.var(r12)
            r8 = 54
            r7.visitVarInsn(r8, r12)
            int r12 = r9.var(r15)
            r6 = 25
            r7.visitVarInsn(r6, r12)
            java.lang.String r6 = JSONLexerBase
            r12 = 180(0xb4, float:2.52E-43)
            r7.visitFieldInsn(r12, r6, r1, r4)
            java.lang.Integer r6 = java.lang.Integer.valueOf(r0)
            r7.visitLdcInsn(r6)
            r0 = 159(0x9f, float:2.23E-43)
            r6 = r26
            r7.visitJumpInsn(r0, r6)
            r7.visitLabel(r2)
            int r2 = r28 + -1
            if (r5 != r2) goto L_0x0e6a
            int r0 = r9.var(r15)
            r2 = 25
            r7.visitVarInsn(r2, r0)
            java.lang.String r0 = JSONLexerBase
            r12 = 180(0xb4, float:2.52E-43)
            r7.visitFieldInsn(r12, r0, r1, r4)
            r16 = 4
            java.lang.Integer r0 = java.lang.Integer.valueOf(r16)
            r7.visitLdcInsn(r0)
            r0 = 160(0xa0, float:2.24E-43)
            r7.visitJumpInsn(r0, r3)
            goto L_0x0e6c
        L_0x0e6a:
            r16 = 4
        L_0x0e6c:
            r21 = r1
            r14 = r3
            r23 = r4
            r8 = r6
            r12 = r7
            r16 = r28
            r13 = 25
            r18 = 54
            r19 = 160(0xa0, float:2.24E-43)
            r20 = 180(0xb4, float:2.52E-43)
            r7 = r5
            goto L_0x0ee8
        L_0x0e80:
            r8 = r26
            r3 = r27
            r2 = 25
            r13 = 3
            r14 = 58
            r16 = 4
            r18 = 21
            r19 = 160(0xa0, float:2.24E-43)
            r20 = 180(0xb4, float:2.52E-43)
            r0 = r31
            r21 = r1
            r1 = r33
            r16 = r28
            r14 = 2
            r22 = 25
            r2 = r7
            r13 = 1
            r18 = 54
            r23 = r4
            r4 = r5
            r14 = 182(0xb6, float:2.55E-43)
            r5 = r6
            r13 = 25
            r6 = r12
            r12 = r7
            r7 = r30
            r0._deserialze_list_obj(r1, r2, r3, r4, r5, r6, r7)
            int r2 = r16 + -1
            if (r7 != r2) goto L_0x0eba
            r4 = r27
            r11._deserialize_endCheck(r9, r12, r4)
            r14 = r4
            goto L_0x0ee8
        L_0x0eba:
            r14 = r27
            goto L_0x0ee8
        L_0x0ebd:
            r21 = r1
            r23 = r4
            r12 = r7
            r8 = r26
            r4 = r27
            r16 = r28
            r7 = r30
            r13 = 25
            r14 = 182(0xb6, float:2.55E-43)
            r18 = 54
            r19 = 160(0xa0, float:2.24E-43)
            r20 = 180(0xb4, float:2.52E-43)
            r0 = r31
            r1 = r33
            r2 = r12
            r3 = r4
            r14 = r4
            r4 = r5
            r5 = r6
            r6 = r7
            r0._deserialze_obj(r1, r2, r3, r4, r5, r6)
            int r2 = r16 + -1
            if (r7 != r2) goto L_0x0ee8
            r11._deserialize_endCheck(r9, r12, r14)
        L_0x0ee8:
            int r0 = r7 + 1
            r26 = r8
            r7 = r12
            r27 = r14
            r2 = r16
            r1 = r21
            r4 = r23
            r3 = 3
            r8 = r0
            goto L_0x0402
        L_0x0ef9:
            r11 = r31
            r16 = r2
            r12 = r7
            r8 = r26
            r14 = r27
            r13 = 25
            r12.visitLabel(r8)
            java.lang.Class r0 = r33.clazz
            boolean r0 = r0.isInterface()
            if (r0 != 0) goto L_0x0f22
            java.lang.Class r0 = r33.clazz
            int r0 = r0.getModifiers()
            boolean r0 = java.lang.reflect.Modifier.isAbstract(r0)
            if (r0 != 0) goto L_0x0f22
            r11._batchSet(r9, r12)
        L_0x0f22:
            r0 = r25
            r12.visitLabel(r0)
            r11._setContext(r9, r12)
            java.lang.String r0 = "instance"
            int r0 = r9.var(r0)
            r12.visitVarInsn(r13, r0)
            com.alibaba.fastjson.util.JavaBeanInfo r0 = r33.beanInfo
            java.lang.reflect.Method r0 = r0.buildMethod
            if (r0 == 0) goto L_0x0f65
            java.lang.Class r1 = r33.getInstClass()
            java.lang.String r1 = com.alibaba.fastjson.util.ASMUtils.type(r1)
            java.lang.String r2 = r0.getName()
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "()"
            r3.append(r4)
            java.lang.Class r0 = r0.getReturnType()
            java.lang.String r0 = com.alibaba.fastjson.util.ASMUtils.desc((java.lang.Class<?>) r0)
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            r3 = 182(0xb6, float:2.55E-43)
            r12.visitMethodInsn(r3, r1, r2, r0)
        L_0x0f65:
            r0 = 176(0xb0, float:2.47E-43)
            r12.visitInsn(r0)
            r12.visitLabel(r14)
            r11._batchSet(r9, r12)
            r0 = 0
            r12.visitVarInsn(r13, r0)
            r0 = 1
            r12.visitVarInsn(r13, r0)
            r0 = 2
            r12.visitVarInsn(r13, r0)
            r0 = 3
            r12.visitVarInsn(r13, r0)
            java.lang.String r0 = "instance"
            int r0 = r9.var(r0)
            r12.visitVarInsn(r13, r0)
            r0 = 21
            r1 = 4
            r12.visitVarInsn(r0, r1)
            int r2 = r16 / 32
            if (r16 == 0) goto L_0x0f99
            int r3 = r16 % 32
            if (r3 == 0) goto L_0x0f99
            int r2 = r2 + 1
        L_0x0f99:
            r3 = 1
            if (r2 != r3) goto L_0x0fa0
            r12.visitInsn(r1)
            goto L_0x0fa5
        L_0x0fa0:
            r3 = 16
            r12.visitIntInsn(r3, r2)
        L_0x0fa5:
            r3 = 188(0xbc, float:2.63E-43)
            r4 = 10
            r12.visitIntInsn(r3, r4)
            r3 = 0
        L_0x0fad:
            if (r3 >= r2) goto L_0x0fe7
            r4 = 89
            r12.visitInsn(r4)
            if (r3 != 0) goto L_0x0fbb
            r4 = 3
            r12.visitInsn(r4)
            goto L_0x0fc7
        L_0x0fbb:
            r4 = 1
            if (r3 != r4) goto L_0x0fc2
            r12.visitInsn(r1)
            goto L_0x0fc7
        L_0x0fc2:
            r4 = 16
            r12.visitIntInsn(r4, r3)
        L_0x0fc7:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "_asm_flag_"
            r4.append(r5)
            r4.append(r3)
            java.lang.String r4 = r4.toString()
            int r4 = r9.var(r4)
            r12.visitVarInsn(r0, r4)
            r4 = 79
            r12.visitInsn(r4)
            int r3 = r3 + 1
            goto L_0x0fad
        L_0x0fe7:
            java.lang.Class<com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer> r2 = com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer.class
            java.lang.String r2 = com.alibaba.fastjson.util.ASMUtils.type(r2)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "(L"
            r3.append(r4)
            java.lang.String r4 = DefaultJSONParser
            r3.append(r4)
            java.lang.String r4 = ";Ljava/lang/reflect/Type;Ljava/lang/Object;Ljava/lang/Object;I[I)Ljava/lang/Object;"
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            java.lang.String r4 = "parseRest"
            r5 = 182(0xb6, float:2.55E-43)
            r12.visitMethodInsn(r5, r2, r4, r3)
            r2 = 192(0xc0, float:2.69E-43)
            java.lang.Class r3 = r33.clazz
            java.lang.String r3 = com.alibaba.fastjson.util.ASMUtils.type(r3)
            r12.visitTypeInsn(r2, r3)
            r2 = 176(0xb0, float:2.47E-43)
            r12.visitInsn(r2)
            r2 = r24
            r12.visitLabel(r2)
            r2 = 0
            r12.visitVarInsn(r13, r2)
            r2 = 1
            r12.visitVarInsn(r13, r2)
            r2 = 2
            r12.visitVarInsn(r13, r2)
            r2 = 3
            r12.visitVarInsn(r13, r2)
            r12.visitVarInsn(r0, r1)
            r0 = 183(0xb7, float:2.56E-43)
            java.lang.Class<com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer> r1 = com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer.class
            java.lang.String r1 = com.alibaba.fastjson.util.ASMUtils.type(r1)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "(L"
            r2.append(r3)
            java.lang.String r3 = DefaultJSONParser
            r2.append(r3)
            java.lang.String r3 = ";Ljava/lang/reflect/Type;Ljava/lang/Object;I)Ljava/lang/Object;"
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            java.lang.String r3 = "deserialze"
            r12.visitMethodInsn(r0, r1, r3, r2)
            r0 = 176(0xb0, float:2.47E-43)
            r12.visitInsn(r0)
            r0 = 10
            int r1 = r33.variantIndex
            r12.visitMaxs(r0, r1)
            r12.visitEnd()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.deserializer.ASMDeserializerFactory._deserialze(com.alibaba.fastjson.asm.ClassWriter, com.alibaba.fastjson.parser.deserializer.ASMDeserializerFactory$Context):void");
    }

    private void defineVarLexer(Context context, MethodVisitor methodVisitor) {
        methodVisitor.visitVarInsn(25, 1);
        methodVisitor.visitFieldInsn(180, DefaultJSONParser, "lexer", ASMUtils.desc((Class<?>) JSONLexer.class));
        methodVisitor.visitTypeInsn(Opcodes.CHECKCAST, JSONLexerBase);
        methodVisitor.visitVarInsn(58, context.var("lexer"));
    }

    private void _createInstance(Context context, MethodVisitor methodVisitor) {
        Constructor<?> constructor = context.beanInfo.defaultConstructor;
        if (Modifier.isPublic(constructor.getModifiers())) {
            methodVisitor.visitTypeInsn(Opcodes.NEW, ASMUtils.type(context.getInstClass()));
            methodVisitor.visitInsn(89);
            methodVisitor.visitMethodInsn(Opcodes.INVOKESPECIAL, ASMUtils.type(constructor.getDeclaringClass()), "<init>", "()V");
            methodVisitor.visitVarInsn(58, context.var("instance"));
            return;
        }
        methodVisitor.visitVarInsn(25, 0);
        methodVisitor.visitVarInsn(25, 1);
        methodVisitor.visitVarInsn(25, 0);
        methodVisitor.visitFieldInsn(180, ASMUtils.type(JavaBeanDeserializer.class), "clazz", "Ljava/lang/Class;");
        String type = ASMUtils.type(JavaBeanDeserializer.class);
        methodVisitor.visitMethodInsn(Opcodes.INVOKESPECIAL, type, "createInstance", "(L" + DefaultJSONParser + ";Ljava/lang/reflect/Type;)Ljava/lang/Object;");
        methodVisitor.visitTypeInsn(Opcodes.CHECKCAST, ASMUtils.type(context.getInstClass()));
        methodVisitor.visitVarInsn(58, context.var("instance"));
    }

    private void _batchSet(Context context, MethodVisitor methodVisitor) {
        _batchSet(context, methodVisitor, true);
    }

    private void _batchSet(Context context, MethodVisitor methodVisitor, boolean z) {
        int length = context.fieldInfoList.length;
        for (int i = 0; i < length; i++) {
            Label label = new Label();
            if (z) {
                _isFlag(methodVisitor, context, i, label);
            }
            _loadAndSet(context, methodVisitor, context.fieldInfoList[i]);
            if (z) {
                methodVisitor.visitLabel(label);
            }
        }
    }

    private void _loadAndSet(Context context, MethodVisitor methodVisitor, FieldInfo fieldInfo) {
        Class<?> cls = fieldInfo.fieldClass;
        Type type = fieldInfo.fieldType;
        if (cls == Boolean.TYPE) {
            methodVisitor.visitVarInsn(25, context.var("instance"));
            methodVisitor.visitVarInsn(21, context.var(fieldInfo.name + "_asm"));
            _set(context, methodVisitor, fieldInfo);
        } else if (cls == Byte.TYPE || cls == Short.TYPE || cls == Integer.TYPE || cls == Character.TYPE) {
            methodVisitor.visitVarInsn(25, context.var("instance"));
            methodVisitor.visitVarInsn(21, context.var(fieldInfo.name + "_asm"));
            _set(context, methodVisitor, fieldInfo);
        } else if (cls == Long.TYPE) {
            methodVisitor.visitVarInsn(25, context.var("instance"));
            methodVisitor.visitVarInsn(22, context.var(fieldInfo.name + "_asm", 2));
            if (fieldInfo.method != null) {
                methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.type(context.getInstClass()), fieldInfo.method.getName(), ASMUtils.desc(fieldInfo.method));
                if (!fieldInfo.method.getReturnType().equals(Void.TYPE)) {
                    methodVisitor.visitInsn(87);
                    return;
                }
                return;
            }
            methodVisitor.visitFieldInsn(Opcodes.PUTFIELD, ASMUtils.type(fieldInfo.declaringClass), fieldInfo.field.getName(), ASMUtils.desc(fieldInfo.fieldClass));
        } else if (cls == Float.TYPE) {
            methodVisitor.visitVarInsn(25, context.var("instance"));
            methodVisitor.visitVarInsn(23, context.var(fieldInfo.name + "_asm"));
            _set(context, methodVisitor, fieldInfo);
        } else if (cls == Double.TYPE) {
            methodVisitor.visitVarInsn(25, context.var("instance"));
            methodVisitor.visitVarInsn(24, context.var(fieldInfo.name + "_asm", 2));
            _set(context, methodVisitor, fieldInfo);
        } else if (cls == String.class) {
            methodVisitor.visitVarInsn(25, context.var("instance"));
            methodVisitor.visitVarInsn(25, context.var(fieldInfo.name + "_asm"));
            _set(context, methodVisitor, fieldInfo);
        } else if (cls.isEnum()) {
            methodVisitor.visitVarInsn(25, context.var("instance"));
            methodVisitor.visitVarInsn(25, context.var(fieldInfo.name + "_asm"));
            _set(context, methodVisitor, fieldInfo);
        } else if (Collection.class.isAssignableFrom(cls)) {
            methodVisitor.visitVarInsn(25, context.var("instance"));
            if (TypeUtils.getCollectionItemClass(type) == String.class) {
                methodVisitor.visitVarInsn(25, context.var(fieldInfo.name + "_asm"));
                methodVisitor.visitTypeInsn(Opcodes.CHECKCAST, ASMUtils.type(cls));
            } else {
                methodVisitor.visitVarInsn(25, context.var(fieldInfo.name + "_asm"));
            }
            _set(context, methodVisitor, fieldInfo);
        } else {
            methodVisitor.visitVarInsn(25, context.var("instance"));
            methodVisitor.visitVarInsn(25, context.var(fieldInfo.name + "_asm"));
            _set(context, methodVisitor, fieldInfo);
        }
    }

    private void _set(Context context, MethodVisitor methodVisitor, FieldInfo fieldInfo) {
        Method method = fieldInfo.method;
        if (method != null) {
            methodVisitor.visitMethodInsn(method.getDeclaringClass().isInterface() ? Opcodes.INVOKEINTERFACE : Opcodes.INVOKEVIRTUAL, ASMUtils.type(fieldInfo.declaringClass), method.getName(), ASMUtils.desc(method));
            if (!fieldInfo.method.getReturnType().equals(Void.TYPE)) {
                methodVisitor.visitInsn(87);
                return;
            }
            return;
        }
        methodVisitor.visitFieldInsn(Opcodes.PUTFIELD, ASMUtils.type(fieldInfo.declaringClass), fieldInfo.field.getName(), ASMUtils.desc(fieldInfo.fieldClass));
    }

    private void _setContext(Context context, MethodVisitor methodVisitor) {
        methodVisitor.visitVarInsn(25, 1);
        methodVisitor.visitVarInsn(25, context.var("context"));
        String str = DefaultJSONParser;
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str, "setContext", "(" + ASMUtils.desc((Class<?>) ParseContext.class) + ")V");
        Label label = new Label();
        methodVisitor.visitVarInsn(25, context.var("childContext"));
        methodVisitor.visitJumpInsn(Opcodes.IFNULL, label);
        methodVisitor.visitVarInsn(25, context.var("childContext"));
        methodVisitor.visitVarInsn(25, context.var("instance"));
        methodVisitor.visitFieldInsn(Opcodes.PUTFIELD, ASMUtils.type(ParseContext.class), "object", "Ljava/lang/Object;");
        methodVisitor.visitLabel(label);
    }

    private void _deserialize_endCheck(Context context, MethodVisitor methodVisitor, Label label) {
        methodVisitor.visitIntInsn(21, context.var("matchedCount"));
        methodVisitor.visitJumpInsn(Opcodes.IFLE, label);
        methodVisitor.visitVarInsn(25, context.var("lexer"));
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, JSONLexerBase, "token", "()I");
        methodVisitor.visitLdcInsn(13);
        methodVisitor.visitJumpInsn(160, label);
        _quickNextTokenComma(context, methodVisitor);
    }

    private void _deserialze_list_obj(Context context, MethodVisitor methodVisitor, Label label, FieldInfo fieldInfo, Class<?> cls, Class<?> cls2, int i) {
        String str;
        String str2;
        String str3;
        int i2;
        Context context2 = context;
        MethodVisitor methodVisitor2 = methodVisitor;
        FieldInfo fieldInfo2 = fieldInfo;
        Class<?> cls3 = cls;
        Class<?> cls4 = cls2;
        int i3 = i;
        Label label2 = new Label();
        methodVisitor2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, JSONLexerBase, "matchField", "([C)Z");
        methodVisitor2.visitJumpInsn(Opcodes.IFEQ, label2);
        _setFlag(methodVisitor2, context2, i3);
        Label label3 = new Label();
        methodVisitor2.visitVarInsn(25, context2.var("lexer"));
        methodVisitor2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, JSONLexerBase, "token", "()I");
        methodVisitor2.visitLdcInsn(8);
        methodVisitor2.visitJumpInsn(160, label3);
        methodVisitor2.visitVarInsn(25, context2.var("lexer"));
        methodVisitor2.visitLdcInsn(16);
        methodVisitor2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, JSONLexerBase, "nextToken", "(I)V");
        methodVisitor2.visitJumpInsn(167, label2);
        methodVisitor2.visitLabel(label3);
        Label label4 = new Label();
        Label label5 = new Label();
        Label label6 = new Label();
        Label label7 = label2;
        methodVisitor2.visitVarInsn(25, context2.var("lexer"));
        methodVisitor2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, JSONLexerBase, "token", "()I");
        methodVisitor2.visitLdcInsn(21);
        methodVisitor2.visitJumpInsn(160, label5);
        methodVisitor2.visitVarInsn(25, context2.var("lexer"));
        methodVisitor2.visitLdcInsn(14);
        methodVisitor2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, JSONLexerBase, "nextToken", "(I)V");
        _newCollection(methodVisitor2, cls3, i3, true);
        methodVisitor2.visitJumpInsn(167, label4);
        methodVisitor2.visitLabel(label5);
        methodVisitor2.visitVarInsn(25, context2.var("lexer"));
        methodVisitor2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, JSONLexerBase, "token", "()I");
        methodVisitor2.visitLdcInsn(14);
        methodVisitor2.visitJumpInsn(Opcodes.IF_ICMPEQ, label6);
        methodVisitor2.visitVarInsn(25, context2.var("lexer"));
        methodVisitor2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, JSONLexerBase, "token", "()I");
        methodVisitor2.visitLdcInsn(12);
        methodVisitor2.visitJumpInsn(160, label);
        _newCollection(methodVisitor2, cls3, i3, false);
        StringBuilder sb = new StringBuilder();
        FieldInfo fieldInfo3 = fieldInfo;
        sb.append(fieldInfo3.name);
        sb.append("_asm");
        methodVisitor2.visitVarInsn(58, context2.var(sb.toString()));
        Class<?> cls5 = cls2;
        String str4 = "(I)V";
        _getCollectionFieldItemDeser(context2, methodVisitor2, fieldInfo3, cls5);
        String str5 = "token";
        methodVisitor2.visitVarInsn(25, 1);
        methodVisitor2.visitLdcInsn(com.alibaba.fastjson.asm.Type.getType(ASMUtils.desc(cls2)));
        methodVisitor2.visitInsn(3);
        String str6 = "nextToken";
        methodVisitor2.visitMethodInsn(184, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;");
        String type = ASMUtils.type(ObjectDeserializer.class);
        methodVisitor2.visitMethodInsn(Opcodes.INVOKEINTERFACE, type, "deserialze", "(L" + DefaultJSONParser + ";Ljava/lang/reflect/Type;Ljava/lang/Object;)Ljava/lang/Object;");
        methodVisitor2.visitVarInsn(58, context2.var("list_item_value"));
        methodVisitor2.visitVarInsn(25, context2.var(fieldInfo3.name + "_asm"));
        methodVisitor2.visitVarInsn(25, context2.var("list_item_value"));
        if (cls.isInterface()) {
            str = "list_item_value";
            methodVisitor2.visitMethodInsn(Opcodes.INVOKEINTERFACE, ASMUtils.type(cls), "add", "(Ljava/lang/Object;)Z");
        } else {
            str = "list_item_value";
            methodVisitor2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.type(cls), "add", "(Ljava/lang/Object;)Z");
        }
        methodVisitor2.visitInsn(87);
        Label label8 = label7;
        methodVisitor2.visitJumpInsn(167, label8);
        methodVisitor2.visitLabel(label6);
        _newCollection(methodVisitor2, cls3, i3, false);
        methodVisitor2.visitLabel(label4);
        methodVisitor2.visitVarInsn(58, context2.var(fieldInfo3.name + "_asm"));
        boolean isPrimitive2 = ParserConfig.isPrimitive2(fieldInfo3.fieldClass);
        _getCollectionFieldItemDeser(context2, methodVisitor2, fieldInfo3, cls5);
        if (isPrimitive2) {
            methodVisitor2.visitMethodInsn(Opcodes.INVOKEINTERFACE, ASMUtils.type(ObjectDeserializer.class), "getFastMatchToken", "()I");
            methodVisitor2.visitVarInsn(54, context2.var("fastMatchToken"));
            methodVisitor2.visitVarInsn(25, context2.var("lexer"));
            methodVisitor2.visitVarInsn(21, context2.var("fastMatchToken"));
            str2 = str4;
            str3 = str6;
            methodVisitor2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, JSONLexerBase, str3, str2);
        } else {
            str2 = str4;
            str3 = str6;
            methodVisitor2.visitInsn(87);
            methodVisitor2.visitLdcInsn(12);
            methodVisitor2.visitVarInsn(54, context2.var("fastMatchToken"));
            _quickNextToken(context2, methodVisitor2, 12);
        }
        methodVisitor2.visitVarInsn(25, 1);
        String str7 = DefaultJSONParser;
        StringBuilder sb2 = new StringBuilder();
        Label label9 = label8;
        sb2.append("()");
        sb2.append(ASMUtils.desc((Class<?>) ParseContext.class));
        methodVisitor2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str7, "getContext", sb2.toString());
        methodVisitor2.visitVarInsn(58, context2.var("listContext"));
        methodVisitor2.visitVarInsn(25, 1);
        methodVisitor2.visitVarInsn(25, context2.var(fieldInfo3.name + "_asm"));
        methodVisitor2.visitLdcInsn(fieldInfo3.name);
        String str8 = DefaultJSONParser;
        methodVisitor2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str8, "setContext", "(Ljava/lang/Object;Ljava/lang/Object;)" + ASMUtils.desc((Class<?>) ParseContext.class));
        methodVisitor2.visitInsn(87);
        Label label10 = new Label();
        Label label11 = new Label();
        methodVisitor2.visitInsn(3);
        methodVisitor2.visitVarInsn(54, context2.var("i"));
        methodVisitor2.visitLabel(label10);
        methodVisitor2.visitVarInsn(25, context2.var("lexer"));
        String str9 = str3;
        String str10 = str5;
        methodVisitor2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, JSONLexerBase, str10, "()I");
        methodVisitor2.visitLdcInsn(15);
        methodVisitor2.visitJumpInsn(Opcodes.IF_ICMPEQ, label11);
        methodVisitor2.visitVarInsn(25, 0);
        String access$300 = context.className;
        StringBuilder sb3 = new StringBuilder();
        Label label12 = label11;
        sb3.append(fieldInfo3.name);
        sb3.append("_asm_list_item_deser__");
        String str11 = str2;
        methodVisitor2.visitFieldInsn(180, access$300, sb3.toString(), ASMUtils.desc((Class<?>) ObjectDeserializer.class));
        methodVisitor2.visitVarInsn(25, 1);
        methodVisitor2.visitLdcInsn(com.alibaba.fastjson.asm.Type.getType(ASMUtils.desc(cls2)));
        methodVisitor2.visitVarInsn(21, context2.var("i"));
        methodVisitor2.visitMethodInsn(184, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;");
        String type2 = ASMUtils.type(ObjectDeserializer.class);
        methodVisitor2.visitMethodInsn(Opcodes.INVOKEINTERFACE, type2, "deserialze", "(L" + DefaultJSONParser + ";Ljava/lang/reflect/Type;Ljava/lang/Object;)Ljava/lang/Object;");
        String str12 = str;
        methodVisitor2.visitVarInsn(58, context2.var(str12));
        methodVisitor2.visitIincInsn(context2.var("i"), 1);
        methodVisitor2.visitVarInsn(25, context2.var(fieldInfo3.name + "_asm"));
        methodVisitor2.visitVarInsn(25, context2.var(str12));
        if (cls.isInterface()) {
            methodVisitor2.visitMethodInsn(Opcodes.INVOKEINTERFACE, ASMUtils.type(cls), "add", "(Ljava/lang/Object;)Z");
        } else {
            methodVisitor2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.type(cls), "add", "(Ljava/lang/Object;)Z");
        }
        methodVisitor2.visitInsn(87);
        methodVisitor2.visitVarInsn(25, 1);
        methodVisitor2.visitVarInsn(25, context2.var(fieldInfo3.name + "_asm"));
        methodVisitor2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, DefaultJSONParser, "checkListResolve", "(Ljava/util/Collection;)V");
        methodVisitor2.visitVarInsn(25, context2.var("lexer"));
        methodVisitor2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, JSONLexerBase, str10, "()I");
        methodVisitor2.visitLdcInsn(16);
        methodVisitor2.visitJumpInsn(160, label10);
        if (isPrimitive2) {
            methodVisitor2.visitVarInsn(25, context2.var("lexer"));
            methodVisitor2.visitVarInsn(21, context2.var("fastMatchToken"));
            methodVisitor2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, JSONLexerBase, str9, str11);
            i2 = 167;
        } else {
            _quickNextToken(context2, methodVisitor2, 12);
            i2 = 167;
        }
        methodVisitor2.visitJumpInsn(i2, label10);
        methodVisitor2.visitLabel(label12);
        methodVisitor2.visitVarInsn(25, 1);
        methodVisitor2.visitVarInsn(25, context2.var("listContext"));
        String str13 = DefaultJSONParser;
        methodVisitor2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str13, "setContext", "(" + ASMUtils.desc((Class<?>) ParseContext.class) + ")V");
        methodVisitor2.visitVarInsn(25, context2.var("lexer"));
        methodVisitor2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, JSONLexerBase, str10, "()I");
        methodVisitor2.visitLdcInsn(15);
        methodVisitor2.visitJumpInsn(160, label);
        _quickNextTokenComma(context, methodVisitor);
        methodVisitor2.visitLabel(label9);
    }

    private void _quickNextToken(Context context, MethodVisitor methodVisitor, int i) {
        Label label = new Label();
        Label label2 = new Label();
        methodVisitor.visitVarInsn(25, context.var("lexer"));
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, JSONLexerBase, "getCurrent", "()C");
        if (i == 12) {
            methodVisitor.visitVarInsn(16, TbsListener.ErrorCode.DOWNLOAD_RETRYTIMES302_EXCEED);
        } else if (i == 14) {
            methodVisitor.visitVarInsn(16, 91);
        } else {
            throw new IllegalStateException();
        }
        methodVisitor.visitJumpInsn(160, label);
        methodVisitor.visitVarInsn(25, context.var("lexer"));
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, JSONLexerBase, "next", "()C");
        methodVisitor.visitInsn(87);
        methodVisitor.visitVarInsn(25, context.var("lexer"));
        methodVisitor.visitLdcInsn(Integer.valueOf(i));
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, JSONLexerBase, "setToken", "(I)V");
        methodVisitor.visitJumpInsn(167, label2);
        methodVisitor.visitLabel(label);
        methodVisitor.visitVarInsn(25, context.var("lexer"));
        methodVisitor.visitLdcInsn(Integer.valueOf(i));
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, JSONLexerBase, "nextToken", "(I)V");
        methodVisitor.visitLabel(label2);
    }

    private void _quickNextTokenComma(Context context, MethodVisitor methodVisitor) {
        Context context2 = context;
        MethodVisitor methodVisitor2 = methodVisitor;
        Label label = new Label();
        Label label2 = new Label();
        Label label3 = new Label();
        Label label4 = new Label();
        Label label5 = new Label();
        methodVisitor2.visitVarInsn(25, context2.var("lexer"));
        methodVisitor2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, JSONLexerBase, "getCurrent", "()C");
        methodVisitor2.visitInsn(89);
        methodVisitor2.visitVarInsn(54, context2.var("ch"));
        methodVisitor2.visitVarInsn(16, 44);
        methodVisitor2.visitJumpInsn(160, label2);
        methodVisitor2.visitVarInsn(25, context2.var("lexer"));
        methodVisitor2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, JSONLexerBase, "next", "()C");
        methodVisitor2.visitInsn(87);
        methodVisitor2.visitVarInsn(25, context2.var("lexer"));
        methodVisitor2.visitLdcInsn(16);
        methodVisitor2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, JSONLexerBase, "setToken", "(I)V");
        methodVisitor2.visitJumpInsn(167, label5);
        methodVisitor2.visitLabel(label2);
        methodVisitor2.visitVarInsn(21, context2.var("ch"));
        methodVisitor2.visitVarInsn(16, TbsListener.ErrorCode.DOWNLOAD_THROWABLE);
        methodVisitor2.visitJumpInsn(160, label3);
        methodVisitor2.visitVarInsn(25, context2.var("lexer"));
        methodVisitor2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, JSONLexerBase, "next", "()C");
        methodVisitor2.visitInsn(87);
        methodVisitor2.visitVarInsn(25, context2.var("lexer"));
        methodVisitor2.visitLdcInsn(13);
        methodVisitor2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, JSONLexerBase, "setToken", "(I)V");
        methodVisitor2.visitJumpInsn(167, label5);
        methodVisitor2.visitLabel(label3);
        methodVisitor2.visitVarInsn(21, context2.var("ch"));
        methodVisitor2.visitVarInsn(16, 93);
        methodVisitor2.visitJumpInsn(160, label4);
        methodVisitor2.visitVarInsn(25, context2.var("lexer"));
        methodVisitor2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, JSONLexerBase, "next", "()C");
        methodVisitor2.visitInsn(87);
        methodVisitor2.visitVarInsn(25, context2.var("lexer"));
        methodVisitor2.visitLdcInsn(15);
        methodVisitor2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, JSONLexerBase, "setToken", "(I)V");
        methodVisitor2.visitJumpInsn(167, label5);
        methodVisitor2.visitLabel(label4);
        methodVisitor2.visitVarInsn(21, context2.var("ch"));
        methodVisitor2.visitVarInsn(16, 26);
        methodVisitor2.visitJumpInsn(160, label);
        methodVisitor2.visitVarInsn(25, context2.var("lexer"));
        methodVisitor2.visitLdcInsn(20);
        methodVisitor2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, JSONLexerBase, "setToken", "(I)V");
        methodVisitor2.visitJumpInsn(167, label5);
        methodVisitor2.visitLabel(label);
        methodVisitor2.visitVarInsn(25, context2.var("lexer"));
        methodVisitor2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, JSONLexerBase, "nextToken", "()V");
        methodVisitor2.visitLabel(label5);
    }

    private void _getCollectionFieldItemDeser(Context context, MethodVisitor methodVisitor, FieldInfo fieldInfo, Class<?> cls) {
        Label label = new Label();
        methodVisitor.visitVarInsn(25, 0);
        String access$300 = context.className;
        methodVisitor.visitFieldInsn(180, access$300, fieldInfo.name + "_asm_list_item_deser__", ASMUtils.desc((Class<?>) ObjectDeserializer.class));
        methodVisitor.visitJumpInsn(Opcodes.IFNONNULL, label);
        methodVisitor.visitVarInsn(25, 0);
        methodVisitor.visitVarInsn(25, 1);
        String str = DefaultJSONParser;
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str, "getConfig", "()" + ASMUtils.desc((Class<?>) ParserConfig.class));
        methodVisitor.visitLdcInsn(com.alibaba.fastjson.asm.Type.getType(ASMUtils.desc(cls)));
        String type = ASMUtils.type(ParserConfig.class);
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, type, "getDeserializer", "(Ljava/lang/reflect/Type;)" + ASMUtils.desc((Class<?>) ObjectDeserializer.class));
        String access$3002 = context.className;
        methodVisitor.visitFieldInsn(Opcodes.PUTFIELD, access$3002, fieldInfo.name + "_asm_list_item_deser__", ASMUtils.desc((Class<?>) ObjectDeserializer.class));
        methodVisitor.visitLabel(label);
        methodVisitor.visitVarInsn(25, 0);
        String access$3003 = context.className;
        methodVisitor.visitFieldInsn(180, access$3003, fieldInfo.name + "_asm_list_item_deser__", ASMUtils.desc((Class<?>) ObjectDeserializer.class));
    }

    private void _newCollection(MethodVisitor methodVisitor, Class<?> cls, int i, boolean z) {
        if (cls.isAssignableFrom(ArrayList.class) && !z) {
            methodVisitor.visitTypeInsn(Opcodes.NEW, "java/util/ArrayList");
            methodVisitor.visitInsn(89);
            methodVisitor.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/util/ArrayList", "<init>", "()V");
        } else if (cls.isAssignableFrom(LinkedList.class) && !z) {
            methodVisitor.visitTypeInsn(Opcodes.NEW, ASMUtils.type(LinkedList.class));
            methodVisitor.visitInsn(89);
            methodVisitor.visitMethodInsn(Opcodes.INVOKESPECIAL, ASMUtils.type(LinkedList.class), "<init>", "()V");
        } else if (cls.isAssignableFrom(HashSet.class)) {
            methodVisitor.visitTypeInsn(Opcodes.NEW, ASMUtils.type(HashSet.class));
            methodVisitor.visitInsn(89);
            methodVisitor.visitMethodInsn(Opcodes.INVOKESPECIAL, ASMUtils.type(HashSet.class), "<init>", "()V");
        } else if (cls.isAssignableFrom(TreeSet.class)) {
            methodVisitor.visitTypeInsn(Opcodes.NEW, ASMUtils.type(TreeSet.class));
            methodVisitor.visitInsn(89);
            methodVisitor.visitMethodInsn(Opcodes.INVOKESPECIAL, ASMUtils.type(TreeSet.class), "<init>", "()V");
        } else if (cls.isAssignableFrom(LinkedHashSet.class)) {
            methodVisitor.visitTypeInsn(Opcodes.NEW, ASMUtils.type(LinkedHashSet.class));
            methodVisitor.visitInsn(89);
            methodVisitor.visitMethodInsn(Opcodes.INVOKESPECIAL, ASMUtils.type(LinkedHashSet.class), "<init>", "()V");
        } else if (z) {
            methodVisitor.visitTypeInsn(Opcodes.NEW, ASMUtils.type(HashSet.class));
            methodVisitor.visitInsn(89);
            methodVisitor.visitMethodInsn(Opcodes.INVOKESPECIAL, ASMUtils.type(HashSet.class), "<init>", "()V");
        } else {
            methodVisitor.visitVarInsn(25, 0);
            methodVisitor.visitLdcInsn(Integer.valueOf(i));
            methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.type(JavaBeanDeserializer.class), "getFieldType", "(I)Ljava/lang/reflect/Type;");
            methodVisitor.visitMethodInsn(184, ASMUtils.type(TypeUtils.class), "createCollection", "(Ljava/lang/reflect/Type;)Ljava/util/Collection;");
        }
        methodVisitor.visitTypeInsn(Opcodes.CHECKCAST, ASMUtils.type(cls));
    }

    private void _deserialze_obj(Context context, MethodVisitor methodVisitor, Label label, FieldInfo fieldInfo, Class<?> cls, int i) {
        Context context2 = context;
        MethodVisitor methodVisitor2 = methodVisitor;
        FieldInfo fieldInfo2 = fieldInfo;
        Label label2 = new Label();
        Label label3 = new Label();
        methodVisitor2.visitVarInsn(25, context2.var("lexer"));
        methodVisitor2.visitVarInsn(25, 0);
        String access$300 = context.className;
        methodVisitor2.visitFieldInsn(180, access$300, fieldInfo2.name + "_asm_prefix__", "[C");
        methodVisitor2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, JSONLexerBase, "matchField", "([C)Z");
        methodVisitor2.visitJumpInsn(Opcodes.IFNE, label2);
        methodVisitor2.visitInsn(1);
        methodVisitor2.visitVarInsn(58, context2.var(fieldInfo2.name + "_asm"));
        methodVisitor2.visitJumpInsn(167, label3);
        methodVisitor2.visitLabel(label2);
        int i2 = i;
        _setFlag(methodVisitor2, context2, i2);
        methodVisitor2.visitVarInsn(21, context2.var("matchedCount"));
        methodVisitor2.visitInsn(4);
        methodVisitor2.visitInsn(96);
        methodVisitor2.visitVarInsn(54, context2.var("matchedCount"));
        _deserObject(context, methodVisitor, fieldInfo, cls, i2);
        methodVisitor2.visitVarInsn(25, 1);
        methodVisitor2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, DefaultJSONParser, "getResolveStatus", "()I");
        methodVisitor2.visitLdcInsn(1);
        methodVisitor2.visitJumpInsn(160, label3);
        methodVisitor2.visitVarInsn(25, 1);
        String str = DefaultJSONParser;
        methodVisitor2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str, "getLastResolveTask", "()" + ASMUtils.desc((Class<?>) DefaultJSONParser.ResolveTask.class));
        methodVisitor2.visitVarInsn(58, context2.var("resolveTask"));
        methodVisitor2.visitVarInsn(25, context2.var("resolveTask"));
        methodVisitor2.visitVarInsn(25, 1);
        String str2 = DefaultJSONParser;
        methodVisitor2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str2, "getContext", "()" + ASMUtils.desc((Class<?>) ParseContext.class));
        methodVisitor2.visitFieldInsn(Opcodes.PUTFIELD, ASMUtils.type(DefaultJSONParser.ResolveTask.class), "ownerContext", ASMUtils.desc((Class<?>) ParseContext.class));
        methodVisitor2.visitVarInsn(25, context2.var("resolveTask"));
        methodVisitor2.visitVarInsn(25, 0);
        methodVisitor2.visitLdcInsn(fieldInfo2.name);
        String type = ASMUtils.type(JavaBeanDeserializer.class);
        methodVisitor2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, type, "getFieldDeserializer", "(Ljava/lang/String;)" + ASMUtils.desc((Class<?>) FieldDeserializer.class));
        methodVisitor2.visitFieldInsn(Opcodes.PUTFIELD, ASMUtils.type(DefaultJSONParser.ResolveTask.class), "fieldDeserializer", ASMUtils.desc((Class<?>) FieldDeserializer.class));
        methodVisitor2.visitVarInsn(25, 1);
        methodVisitor2.visitLdcInsn(0);
        methodVisitor2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, DefaultJSONParser, "setResolveStatus", "(I)V");
        methodVisitor2.visitLabel(label3);
    }

    private void _deserObject(Context context, MethodVisitor methodVisitor, FieldInfo fieldInfo, Class<?> cls, int i) {
        Context context2 = context;
        MethodVisitor methodVisitor2 = methodVisitor;
        FieldInfo fieldInfo2 = fieldInfo;
        _getFieldDeser(context, methodVisitor, fieldInfo);
        Label label = new Label();
        Label label2 = new Label();
        if ((fieldInfo2.parserFeatures & Feature.SupportArrayToBean.mask) != 0) {
            methodVisitor2.visitInsn(89);
            methodVisitor2.visitTypeInsn(Opcodes.INSTANCEOF, ASMUtils.type(JavaBeanDeserializer.class));
            methodVisitor2.visitJumpInsn(Opcodes.IFEQ, label);
            methodVisitor2.visitTypeInsn(Opcodes.CHECKCAST, ASMUtils.type(JavaBeanDeserializer.class));
            methodVisitor2.visitVarInsn(25, 1);
            if (fieldInfo2.fieldType instanceof Class) {
                methodVisitor2.visitLdcInsn(com.alibaba.fastjson.asm.Type.getType(ASMUtils.desc(fieldInfo2.fieldClass)));
            } else {
                methodVisitor2.visitVarInsn(25, 0);
                methodVisitor2.visitLdcInsn(Integer.valueOf(i));
                methodVisitor2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.type(JavaBeanDeserializer.class), "getFieldType", "(I)Ljava/lang/reflect/Type;");
            }
            methodVisitor2.visitLdcInsn(fieldInfo2.name);
            methodVisitor2.visitLdcInsn(Integer.valueOf(fieldInfo2.parserFeatures));
            String type = ASMUtils.type(JavaBeanDeserializer.class);
            methodVisitor2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, type, "deserialze", "(L" + DefaultJSONParser + ";Ljava/lang/reflect/Type;Ljava/lang/Object;I)Ljava/lang/Object;");
            methodVisitor2.visitTypeInsn(Opcodes.CHECKCAST, ASMUtils.type(cls));
            methodVisitor2.visitVarInsn(58, context2.var(fieldInfo2.name + "_asm"));
            methodVisitor2.visitJumpInsn(167, label2);
            methodVisitor2.visitLabel(label);
        }
        methodVisitor2.visitVarInsn(25, 1);
        if (fieldInfo2.fieldType instanceof Class) {
            methodVisitor2.visitLdcInsn(com.alibaba.fastjson.asm.Type.getType(ASMUtils.desc(fieldInfo2.fieldClass)));
        } else {
            methodVisitor2.visitVarInsn(25, 0);
            methodVisitor2.visitLdcInsn(Integer.valueOf(i));
            methodVisitor2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.type(JavaBeanDeserializer.class), "getFieldType", "(I)Ljava/lang/reflect/Type;");
        }
        methodVisitor2.visitLdcInsn(fieldInfo2.name);
        String type2 = ASMUtils.type(ObjectDeserializer.class);
        methodVisitor2.visitMethodInsn(Opcodes.INVOKEINTERFACE, type2, "deserialze", "(L" + DefaultJSONParser + ";Ljava/lang/reflect/Type;Ljava/lang/Object;)Ljava/lang/Object;");
        methodVisitor2.visitTypeInsn(Opcodes.CHECKCAST, ASMUtils.type(cls));
        methodVisitor2.visitVarInsn(58, context2.var(fieldInfo2.name + "_asm"));
        methodVisitor2.visitLabel(label2);
    }

    private void _getFieldDeser(Context context, MethodVisitor methodVisitor, FieldInfo fieldInfo) {
        Label label = new Label();
        methodVisitor.visitVarInsn(25, 0);
        String access$300 = context.className;
        methodVisitor.visitFieldInsn(180, access$300, fieldInfo.name + "_asm_deser__", ASMUtils.desc((Class<?>) ObjectDeserializer.class));
        methodVisitor.visitJumpInsn(Opcodes.IFNONNULL, label);
        methodVisitor.visitVarInsn(25, 0);
        methodVisitor.visitVarInsn(25, 1);
        String str = DefaultJSONParser;
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str, "getConfig", "()" + ASMUtils.desc((Class<?>) ParserConfig.class));
        methodVisitor.visitLdcInsn(com.alibaba.fastjson.asm.Type.getType(ASMUtils.desc(fieldInfo.fieldClass)));
        String type = ASMUtils.type(ParserConfig.class);
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, type, "getDeserializer", "(Ljava/lang/reflect/Type;)" + ASMUtils.desc((Class<?>) ObjectDeserializer.class));
        String access$3002 = context.className;
        methodVisitor.visitFieldInsn(Opcodes.PUTFIELD, access$3002, fieldInfo.name + "_asm_deser__", ASMUtils.desc((Class<?>) ObjectDeserializer.class));
        methodVisitor.visitLabel(label);
        methodVisitor.visitVarInsn(25, 0);
        String access$3003 = context.className;
        methodVisitor.visitFieldInsn(180, access$3003, fieldInfo.name + "_asm_deser__", ASMUtils.desc((Class<?>) ObjectDeserializer.class));
    }

    static class Context {
        static final int fieldName = 3;
        static final int parser = 1;
        static final int type = 2;
        /* access modifiers changed from: private */
        public final JavaBeanInfo beanInfo;
        /* access modifiers changed from: private */
        public final String className;
        /* access modifiers changed from: private */
        public final Class<?> clazz;
        /* access modifiers changed from: private */
        public FieldInfo[] fieldInfoList;
        /* access modifiers changed from: private */
        public int variantIndex = -1;
        private final Map<String, Integer> variants = new HashMap();

        public Context(String str, ParserConfig parserConfig, JavaBeanInfo javaBeanInfo, int i) {
            this.className = str;
            this.clazz = javaBeanInfo.clazz;
            this.variantIndex = i;
            this.beanInfo = javaBeanInfo;
            this.fieldInfoList = javaBeanInfo.fields;
        }

        public Class<?> getInstClass() {
            Class<?> cls = this.beanInfo.builderClass;
            return cls == null ? this.clazz : cls;
        }

        public int var(String str, int i) {
            if (this.variants.get(str) == null) {
                this.variants.put(str, Integer.valueOf(this.variantIndex));
                this.variantIndex += i;
            }
            return this.variants.get(str).intValue();
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
    }

    private void _init(ClassWriter classWriter, Context context) {
        ClassWriter classWriter2 = classWriter;
        for (FieldInfo fieldInfo : context.fieldInfoList) {
            new FieldWriter(classWriter, 1, fieldInfo.name + "_asm_prefix__", "[C").visitEnd();
        }
        for (FieldInfo fieldInfo2 : context.fieldInfoList) {
            Class<?> cls = fieldInfo2.fieldClass;
            if (!cls.isPrimitive()) {
                if (Collection.class.isAssignableFrom(cls)) {
                    new FieldWriter(classWriter, 1, fieldInfo2.name + "_asm_list_item_deser__", ASMUtils.desc((Class<?>) ObjectDeserializer.class)).visitEnd();
                } else {
                    new FieldWriter(classWriter, 1, fieldInfo2.name + "_asm_deser__", ASMUtils.desc((Class<?>) ObjectDeserializer.class)).visitEnd();
                }
            }
        }
        MethodWriter methodWriter = new MethodWriter(classWriter, 1, "<init>", "(" + ASMUtils.desc((Class<?>) ParserConfig.class) + ASMUtils.desc((Class<?>) JavaBeanInfo.class) + ")V", (String) null, (String[]) null);
        methodWriter.visitVarInsn(25, 0);
        methodWriter.visitVarInsn(25, 1);
        methodWriter.visitVarInsn(25, 2);
        methodWriter.visitMethodInsn(Opcodes.INVOKESPECIAL, ASMUtils.type(JavaBeanDeserializer.class), "<init>", "(" + ASMUtils.desc((Class<?>) ParserConfig.class) + ASMUtils.desc((Class<?>) JavaBeanInfo.class) + ")V");
        for (FieldInfo fieldInfo3 : context.fieldInfoList) {
            methodWriter.visitVarInsn(25, 0);
            methodWriter.visitLdcInsn("\"" + fieldInfo3.name + "\":");
            methodWriter.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/String", "toCharArray", "()[C");
            methodWriter.visitFieldInsn(Opcodes.PUTFIELD, context.className, fieldInfo3.name + "_asm_prefix__", "[C");
        }
        methodWriter.visitInsn(177);
        methodWriter.visitMaxs(4, 4);
        methodWriter.visitEnd();
    }

    private void _createInstance(ClassWriter classWriter, Context context) {
        if (Modifier.isPublic(context.beanInfo.defaultConstructor.getModifiers())) {
            MethodWriter methodWriter = new MethodWriter(classWriter, 1, "createInstance", "(L" + DefaultJSONParser + ";Ljava/lang/reflect/Type;)Ljava/lang/Object;", (String) null, (String[]) null);
            methodWriter.visitTypeInsn(Opcodes.NEW, ASMUtils.type(context.getInstClass()));
            methodWriter.visitInsn(89);
            methodWriter.visitMethodInsn(Opcodes.INVOKESPECIAL, ASMUtils.type(context.getInstClass()), "<init>", "()V");
            methodWriter.visitInsn(176);
            methodWriter.visitMaxs(3, 3);
            methodWriter.visitEnd();
        }
    }
}
