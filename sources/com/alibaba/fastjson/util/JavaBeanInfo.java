package com.alibaba.fastjson.util;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.annotation.JSONCreator;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class JavaBeanInfo {
    public final Method buildMethod;
    public final Class<?> builderClass;
    public final Class<?> clazz;
    public final Constructor<?> creatorConstructor;
    public Type[] creatorConstructorParameterTypes;
    public String[] creatorConstructorParameters;
    public final Constructor<?> defaultConstructor;
    public final int defaultConstructorParameterSize;
    public final Method factoryMethod;
    public final FieldInfo[] fields;
    public final JSONType jsonType;

    /* renamed from: kotlin  reason: collision with root package name */
    public boolean f4kotlin;
    public Constructor<?> kotlinDefaultConstructor;
    public String[] orders;
    public final int parserFeatures;
    public final FieldInfo[] sortedFields;
    public final String typeKey;
    public final String typeName;

    public JavaBeanInfo(Class<?> cls, Class<?> cls2, Constructor<?> constructor, Constructor<?> constructor2, Method method, Method method2, JSONType jSONType, List<FieldInfo> list) {
        JSONField jSONField;
        this.clazz = cls;
        this.builderClass = cls2;
        this.defaultConstructor = constructor;
        this.creatorConstructor = constructor2;
        this.factoryMethod = method;
        this.parserFeatures = TypeUtils.getParserFeatures(cls);
        this.buildMethod = method2;
        this.jsonType = jSONType;
        if (jSONType != null) {
            String typeName2 = jSONType.typeName();
            String typeKey2 = jSONType.typeKey();
            this.typeKey = typeKey2.length() <= 0 ? null : typeKey2;
            if (typeName2.length() != 0) {
                this.typeName = typeName2;
            } else {
                this.typeName = cls.getName();
            }
            String[] orders2 = jSONType.orders();
            this.orders = orders2.length == 0 ? null : orders2;
        } else {
            this.typeName = cls.getName();
            this.typeKey = null;
            this.orders = null;
        }
        FieldInfo[] fieldInfoArr = new FieldInfo[list.size()];
        this.fields = fieldInfoArr;
        list.toArray(fieldInfoArr);
        FieldInfo[] fieldInfoArr2 = this.fields;
        FieldInfo[] fieldInfoArr3 = new FieldInfo[fieldInfoArr2.length];
        boolean z = false;
        if (this.orders != null) {
            LinkedHashMap linkedHashMap = new LinkedHashMap(list.size());
            for (FieldInfo fieldInfo : this.fields) {
                linkedHashMap.put(fieldInfo.name, fieldInfo);
            }
            int i = 0;
            for (String str : this.orders) {
                FieldInfo fieldInfo2 = (FieldInfo) linkedHashMap.get(str);
                if (fieldInfo2 != null) {
                    fieldInfoArr3[i] = fieldInfo2;
                    linkedHashMap.remove(str);
                    i++;
                }
            }
            for (FieldInfo fieldInfo3 : linkedHashMap.values()) {
                fieldInfoArr3[i] = fieldInfo3;
                i++;
            }
        } else {
            System.arraycopy(fieldInfoArr2, 0, fieldInfoArr3, 0, fieldInfoArr2.length);
            Arrays.sort(fieldInfoArr3);
        }
        this.sortedFields = Arrays.equals(this.fields, fieldInfoArr3) ? this.fields : fieldInfoArr3;
        if (constructor != null) {
            this.defaultConstructorParameterSize = constructor.getParameterTypes().length;
        } else if (method != null) {
            this.defaultConstructorParameterSize = method.getParameterTypes().length;
        } else {
            this.defaultConstructorParameterSize = 0;
        }
        if (constructor2 != null) {
            this.creatorConstructorParameterTypes = constructor2.getParameterTypes();
            boolean isKotlin = TypeUtils.isKotlin(cls);
            this.f4kotlin = isKotlin;
            if (isKotlin) {
                this.creatorConstructorParameters = TypeUtils.getKoltinConstructorParameters(cls);
                try {
                    this.kotlinDefaultConstructor = cls.getConstructor(new Class[0]);
                } catch (Throwable unused) {
                }
                Annotation[][] parameterAnnotations = TypeUtils.getParameterAnnotations((Constructor) constructor2);
                int i2 = 0;
                while (i2 < this.creatorConstructorParameters.length && i2 < parameterAnnotations.length) {
                    Annotation[] annotationArr = parameterAnnotations[i2];
                    int length = annotationArr.length;
                    int i3 = 0;
                    while (true) {
                        if (i3 >= length) {
                            jSONField = null;
                            break;
                        }
                        Annotation annotation = annotationArr[i3];
                        if (annotation instanceof JSONField) {
                            jSONField = (JSONField) annotation;
                            break;
                        }
                        i3++;
                    }
                    if (jSONField != null) {
                        String name = jSONField.name();
                        if (name.length() > 0) {
                            this.creatorConstructorParameters[i2] = name;
                        }
                    }
                    i2++;
                }
                return;
            }
            if (this.creatorConstructorParameterTypes.length == this.fields.length) {
                int i4 = 0;
                while (true) {
                    Type[] typeArr = this.creatorConstructorParameterTypes;
                    if (i4 >= typeArr.length) {
                        z = true;
                        break;
                    } else if (typeArr[i4] != this.fields[i4].fieldClass) {
                        break;
                    } else {
                        i4++;
                    }
                }
            }
            if (!z) {
                this.creatorConstructorParameters = ASMUtils.lookupParameterNames(constructor2);
            }
        }
    }

    private static FieldInfo getField(List<FieldInfo> list, String str) {
        for (FieldInfo next : list) {
            if (next.name.equals(str)) {
                return next;
            }
            Field field = next.field;
            if (field != null && next.getAnnotation() != null && field.getName().equals(str)) {
                return next;
            }
        }
        return null;
    }

    static boolean add(List<FieldInfo> list, FieldInfo fieldInfo) {
        int size = list.size() - 1;
        while (size >= 0) {
            FieldInfo fieldInfo2 = list.get(size);
            if (!fieldInfo2.name.equals(fieldInfo.name) || (fieldInfo2.getOnly && !fieldInfo.getOnly)) {
                size--;
            } else if (fieldInfo2.fieldClass.isAssignableFrom(fieldInfo.fieldClass)) {
                list.set(size, fieldInfo);
                return true;
            } else if (fieldInfo2.compareTo(fieldInfo) >= 0) {
                return false;
            } else {
                list.set(size, fieldInfo);
                return true;
            }
        }
        list.add(fieldInfo);
        return true;
    }

    public static JavaBeanInfo build(Class<?> cls, Type type, PropertyNamingStrategy propertyNamingStrategy) {
        return build(cls, type, propertyNamingStrategy, false, TypeUtils.compatibleWithJavaBean, false);
    }

    private static Map<TypeVariable, Type> buildGenericInfo(Class<?> cls) {
        Class<? super Object> superclass = cls.getSuperclass();
        HashMap hashMap = null;
        if (superclass == null) {
            return null;
        }
        while (true) {
            Class<? super Object> cls2 = superclass;
            Class<?> cls3 = cls;
            cls = cls2;
            if (cls == null || cls == Object.class) {
                return hashMap;
            }
            if (cls3.getGenericSuperclass() instanceof ParameterizedType) {
                Type[] actualTypeArguments = ((ParameterizedType) cls3.getGenericSuperclass()).getActualTypeArguments();
                TypeVariable[] typeParameters = cls.getTypeParameters();
                for (int i = 0; i < actualTypeArguments.length; i++) {
                    if (hashMap == null) {
                        hashMap = new HashMap();
                    }
                    if (hashMap.containsKey(actualTypeArguments[i])) {
                        hashMap.put(typeParameters[i], hashMap.get(actualTypeArguments[i]));
                    } else {
                        hashMap.put(typeParameters[i], actualTypeArguments[i]);
                    }
                }
            }
            superclass = cls.getSuperclass();
        }
        return hashMap;
    }

    public static JavaBeanInfo build(Class<?> cls, Type type, PropertyNamingStrategy propertyNamingStrategy, boolean z, boolean z2) {
        return build(cls, type, propertyNamingStrategy, z, z2, false);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v7, resolved type: java.lang.Class<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v8, resolved type: java.lang.Class<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v9, resolved type: java.lang.Class<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v130, resolved type: java.lang.Class<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v131, resolved type: java.lang.Class<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v133, resolved type: java.lang.Class<?>} */
    /* JADX WARNING: type inference failed for: r0v24, types: [java.lang.annotation.Annotation] */
    /* JADX WARNING: type inference failed for: r0v102, types: [java.lang.annotation.Annotation] */
    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0011, code lost:
        r0 = r15.naming();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:349:0x078a, code lost:
        if (r4 == null) goto L_0x078c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:417:0x097c, code lost:
        if (r1.deserialize() == false) goto L_0x098c;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:162:0x030a  */
    /* JADX WARNING: Removed duplicated region for block: B:163:0x0310  */
    /* JADX WARNING: Removed duplicated region for block: B:167:0x0318  */
    /* JADX WARNING: Removed duplicated region for block: B:198:0x03e4  */
    /* JADX WARNING: Removed duplicated region for block: B:203:0x0407  */
    /* JADX WARNING: Removed duplicated region for block: B:206:0x040e  */
    /* JADX WARNING: Removed duplicated region for block: B:276:0x05ef  */
    /* JADX WARNING: Removed duplicated region for block: B:281:0x05ff  */
    /* JADX WARNING: Removed duplicated region for block: B:357:0x07bf  */
    /* JADX WARNING: Removed duplicated region for block: B:364:0x07f3  */
    /* JADX WARNING: Removed duplicated region for block: B:372:0x0859  */
    /* JADX WARNING: Removed duplicated region for block: B:375:0x0869  */
    /* JADX WARNING: Removed duplicated region for block: B:376:0x086f  */
    /* JADX WARNING: Removed duplicated region for block: B:381:0x08b3  */
    /* JADX WARNING: Removed duplicated region for block: B:427:0x09e6  */
    /* JADX WARNING: Removed duplicated region for block: B:475:0x0895 A[EDGE_INSN: B:475:0x0895->B:379:0x0895 ?: BREAK  , SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.alibaba.fastjson.util.JavaBeanInfo build(java.lang.Class<?> r47, java.lang.reflect.Type r48, com.alibaba.fastjson.PropertyNamingStrategy r49, boolean r50, boolean r51, boolean r52) {
        /*
            r13 = r47
            r14 = r48
            r9 = r52
            java.lang.Class<com.alibaba.fastjson.annotation.JSONType> r0 = com.alibaba.fastjson.annotation.JSONType.class
            java.lang.annotation.Annotation r0 = com.alibaba.fastjson.util.TypeUtils.getAnnotation((java.lang.Class<?>) r13, r0)
            r15 = r0
            com.alibaba.fastjson.annotation.JSONType r15 = (com.alibaba.fastjson.annotation.JSONType) r15
            if (r15 == 0) goto L_0x001d
            com.alibaba.fastjson.PropertyNamingStrategy r0 = r15.naming()
            if (r0 == 0) goto L_0x001d
            com.alibaba.fastjson.PropertyNamingStrategy r1 = com.alibaba.fastjson.PropertyNamingStrategy.CamelCase
            if (r0 == r1) goto L_0x001d
            r12 = r0
            goto L_0x001f
        L_0x001d:
            r12 = r49
        L_0x001f:
            java.lang.Class r11 = getBuilderClass(r13, r15)
            java.lang.reflect.Field[] r10 = r47.getDeclaredFields()
            java.lang.reflect.Method[] r8 = r47.getMethods()
            java.util.Map r16 = buildGenericInfo(r47)
            boolean r17 = com.alibaba.fastjson.util.TypeUtils.isKotlin(r47)
            java.lang.reflect.Constructor[] r0 = r47.getDeclaredConstructors()
            r18 = 0
            r7 = 1
            if (r17 == 0) goto L_0x0043
            int r1 = r0.length
            if (r1 != r7) goto L_0x0040
            goto L_0x0043
        L_0x0040:
            r19 = r18
            goto L_0x0054
        L_0x0043:
            if (r11 != 0) goto L_0x004a
            java.lang.reflect.Constructor r1 = getDefaultConstructor(r13, r0)
            goto L_0x0052
        L_0x004a:
            java.lang.reflect.Constructor[] r1 = r11.getDeclaredConstructors()
            java.lang.reflect.Constructor r1 = getDefaultConstructor(r11, r1)
        L_0x0052:
            r19 = r1
        L_0x0054:
            r20 = 0
            r21 = 0
            java.util.ArrayList r6 = new java.util.ArrayList
            r6.<init>()
            if (r50 == 0) goto L_0x0086
            r0 = r13
        L_0x0060:
            if (r0 == 0) goto L_0x006e
            java.lang.reflect.Field[] r1 = r0.getDeclaredFields()
            computeFields(r13, r14, r12, r6, r1)
            java.lang.Class r0 = r0.getSuperclass()
            goto L_0x0060
        L_0x006e:
            if (r19 == 0) goto L_0x0073
            com.alibaba.fastjson.util.TypeUtils.setAccessible(r19)
        L_0x0073:
            com.alibaba.fastjson.util.JavaBeanInfo r9 = new com.alibaba.fastjson.util.JavaBeanInfo
            r4 = 0
            r0 = r9
            r1 = r47
            r2 = r11
            r3 = r19
            r5 = r21
            r8 = r6
            r6 = r20
            r7 = r15
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8)
            return r9
        L_0x0086:
            boolean r1 = r47.isInterface()
            if (r1 != 0) goto L_0x0099
            int r1 = r47.getModifiers()
            boolean r1 = java.lang.reflect.Modifier.isAbstract(r1)
            if (r1 == 0) goto L_0x0097
            goto L_0x0099
        L_0x0097:
            r1 = 0
            goto L_0x009a
        L_0x0099:
            r1 = 1
        L_0x009a:
            if (r19 != 0) goto L_0x009e
            if (r11 == 0) goto L_0x00a0
        L_0x009e:
            if (r1 == 0) goto L_0x03fb
        L_0x00a0:
            java.lang.reflect.Constructor r22 = getCreatorConstructor(r0)
            if (r22 == 0) goto L_0x0181
            if (r1 != 0) goto L_0x0181
            com.alibaba.fastjson.util.TypeUtils.setAccessible(r22)
            java.lang.Class[] r9 = r22.getParameterTypes()
            int r0 = r9.length
            if (r0 <= 0) goto L_0x0179
            java.lang.annotation.Annotation[][] r2 = com.alibaba.fastjson.util.TypeUtils.getParameterAnnotations((java.lang.reflect.Constructor) r22)
            r0 = r18
            r1 = 0
        L_0x00b9:
            int r3 = r9.length
            if (r1 >= r3) goto L_0x0179
            int r3 = r2.length
            if (r1 >= r3) goto L_0x0179
            r3 = r2[r1]
            int r4 = r3.length
            r5 = 0
        L_0x00c3:
            if (r5 >= r4) goto L_0x00d6
            r7 = r3[r5]
            r52 = r2
            boolean r2 = r7 instanceof com.alibaba.fastjson.annotation.JSONField
            if (r2 == 0) goto L_0x00d0
            com.alibaba.fastjson.annotation.JSONField r7 = (com.alibaba.fastjson.annotation.JSONField) r7
            goto L_0x00da
        L_0x00d0:
            int r5 = r5 + 1
            r2 = r52
            r7 = 1
            goto L_0x00c3
        L_0x00d6:
            r52 = r2
            r7 = r18
        L_0x00da:
            r3 = r9[r1]
            java.lang.reflect.Type[] r2 = r22.getGenericParameterTypes()
            r4 = r2[r1]
            if (r7 == 0) goto L_0x010a
            java.lang.String r2 = r7.name()
            java.lang.reflect.Field r2 = com.alibaba.fastjson.util.TypeUtils.getField(r13, r2, r10)
            int r5 = r7.ordinal()
            com.alibaba.fastjson.serializer.SerializerFeature[] r25 = r7.serialzeFeatures()
            int r25 = com.alibaba.fastjson.serializer.SerializerFeature.of(r25)
            com.alibaba.fastjson.parser.Feature[] r26 = r7.parseFeatures()
            int r26 = com.alibaba.fastjson.parser.Feature.of(r26)
            java.lang.String r7 = r7.name()
            r46 = r7
            r7 = r5
            r5 = r46
            goto L_0x0112
        L_0x010a:
            r2 = r18
            r5 = r2
            r7 = 0
            r25 = 0
            r26 = 0
        L_0x0112:
            if (r5 == 0) goto L_0x011a
            int r27 = r5.length()
            if (r27 != 0) goto L_0x0122
        L_0x011a:
            if (r0 != 0) goto L_0x0120
            java.lang.String[] r0 = com.alibaba.fastjson.util.ASMUtils.lookupParameterNames(r22)
        L_0x0120:
            r5 = r0[r1]
        L_0x0122:
            if (r2 != 0) goto L_0x0141
            if (r0 != 0) goto L_0x0131
            if (r17 == 0) goto L_0x012d
            java.lang.String[] r0 = com.alibaba.fastjson.util.TypeUtils.getKoltinConstructorParameters(r47)
            goto L_0x0131
        L_0x012d:
            java.lang.String[] r0 = com.alibaba.fastjson.util.ASMUtils.lookupParameterNames(r22)
        L_0x0131:
            r27 = r2
            int r2 = r0.length
            if (r2 <= r1) goto L_0x0143
            r2 = r0[r1]
            java.lang.reflect.Field r2 = com.alibaba.fastjson.util.TypeUtils.getField(r13, r2, r10)
            r27 = r0
            r28 = r2
            goto L_0x0147
        L_0x0141:
            r27 = r2
        L_0x0143:
            r28 = r27
            r27 = r0
        L_0x0147:
            com.alibaba.fastjson.util.FieldInfo r2 = new com.alibaba.fastjson.util.FieldInfo
            r0 = r2
            r29 = r1
            r1 = r5
            r30 = r52
            r5 = r2
            r2 = r47
            r14 = 3
            r14 = 2
            r14 = r5
            r5 = r28
            r23 = r12
            r12 = r6
            r6 = r7
            r7 = r25
            r24 = r15
            r15 = r8
            r8 = r26
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8)
            add(r12, r14)
            int r1 = r29 + 1
            r14 = r48
            r6 = r12
            r8 = r15
            r12 = r23
            r15 = r24
            r0 = r27
            r2 = r30
            r7 = 1
            goto L_0x00b9
        L_0x0179:
            r23 = r12
            r24 = r15
            r12 = r6
            r15 = r8
            goto L_0x0403
        L_0x0181:
            r23 = r12
            r24 = r15
            r12 = r6
            r15 = r8
            java.lang.reflect.Method r14 = getFactoryMethod(r13, r15, r9)
            if (r14 == 0) goto L_0x023f
            com.alibaba.fastjson.util.TypeUtils.setAccessible(r14)
            java.lang.Class[] r8 = r14.getParameterTypes()
            int r0 = r8.length
            if (r0 <= 0) goto L_0x0405
            java.lang.annotation.Annotation[][] r15 = com.alibaba.fastjson.util.TypeUtils.getParameterAnnotations((java.lang.reflect.Method) r14)
            r0 = r18
            r7 = 0
        L_0x019e:
            int r1 = r8.length
            if (r7 >= r1) goto L_0x022e
            r1 = r15[r7]
            int r2 = r1.length
            r5 = 0
        L_0x01a5:
            if (r5 >= r2) goto L_0x01b3
            r3 = r1[r5]
            boolean r4 = r3 instanceof com.alibaba.fastjson.annotation.JSONField
            if (r4 == 0) goto L_0x01b0
            com.alibaba.fastjson.annotation.JSONField r3 = (com.alibaba.fastjson.annotation.JSONField) r3
            goto L_0x01b5
        L_0x01b0:
            int r5 = r5 + 1
            goto L_0x01a5
        L_0x01b3:
            r3 = r18
        L_0x01b5:
            if (r3 != 0) goto L_0x01c8
            if (r9 == 0) goto L_0x01c0
            boolean r1 = com.alibaba.fastjson.util.TypeUtils.isJacksonCreator(r14)
            if (r1 == 0) goto L_0x01c0
            goto L_0x01c8
        L_0x01c0:
            com.alibaba.fastjson.JSONException r0 = new com.alibaba.fastjson.JSONException
            java.lang.String r1 = "illegal json creator"
            r0.<init>(r1)
            throw r0
        L_0x01c8:
            if (r3 == 0) goto L_0x01e8
            java.lang.String r1 = r3.name()
            int r2 = r3.ordinal()
            com.alibaba.fastjson.serializer.SerializerFeature[] r4 = r3.serialzeFeatures()
            int r4 = com.alibaba.fastjson.serializer.SerializerFeature.of(r4)
            com.alibaba.fastjson.parser.Feature[] r3 = r3.parseFeatures()
            int r3 = com.alibaba.fastjson.parser.Feature.of(r3)
            r6 = r2
            r17 = r3
            r16 = r4
            goto L_0x01ef
        L_0x01e8:
            r1 = r18
            r6 = 0
            r16 = 0
            r17 = 0
        L_0x01ef:
            if (r1 == 0) goto L_0x01fb
            int r2 = r1.length()
            if (r2 != 0) goto L_0x01f8
            goto L_0x01fb
        L_0x01f8:
            r19 = r0
            goto L_0x0204
        L_0x01fb:
            if (r0 != 0) goto L_0x0201
            java.lang.String[] r0 = com.alibaba.fastjson.util.ASMUtils.lookupParameterNames(r14)
        L_0x0201:
            r1 = r0[r7]
            goto L_0x01f8
        L_0x0204:
            r3 = r8[r7]
            java.lang.reflect.Type[] r0 = r14.getGenericParameterTypes()
            r4 = r0[r7]
            java.lang.reflect.Field r5 = com.alibaba.fastjson.util.TypeUtils.getField(r13, r1, r10)
            com.alibaba.fastjson.util.FieldInfo r2 = new com.alibaba.fastjson.util.FieldInfo
            r0 = r2
            r9 = r2
            r2 = r47
            r20 = r7
            r7 = r16
            r16 = r8
            r8 = r17
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8)
            add(r12, r9)
            int r7 = r20 + 1
            r9 = r52
            r8 = r16
            r0 = r19
            goto L_0x019e
        L_0x022e:
            com.alibaba.fastjson.util.JavaBeanInfo r9 = new com.alibaba.fastjson.util.JavaBeanInfo
            r3 = 0
            r4 = 0
            r6 = 0
            r0 = r9
            r1 = r47
            r2 = r11
            r5 = r14
            r7 = r24
            r8 = r12
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8)
            return r9
        L_0x023f:
            if (r1 != 0) goto L_0x0405
            java.lang.String r9 = r47.getName()
            if (r17 == 0) goto L_0x025b
            int r1 = r0.length
            if (r1 <= 0) goto L_0x025b
            java.lang.String[] r1 = com.alibaba.fastjson.util.TypeUtils.getKoltinConstructorParameters(r47)
            java.lang.reflect.Constructor r0 = com.alibaba.fastjson.util.TypeUtils.getKoltinConstructor(r0, r1)
            com.alibaba.fastjson.util.TypeUtils.setAccessible(r0)
            r22 = r0
            r7 = r1
        L_0x0258:
            r8 = 0
            goto L_0x0308
        L_0x025b:
            int r1 = r0.length
            r2 = r18
            r5 = 0
        L_0x025f:
            if (r5 >= r1) goto L_0x0306
            r3 = r0[r5]
            java.lang.Class[] r4 = r3.getParameterTypes()
            java.lang.String r6 = "org.springframework.security.web.authentication.WebAuthenticationDetails"
            boolean r6 = r9.equals(r6)
            if (r6 == 0) goto L_0x0290
            int r6 = r4.length
            r7 = 2
            if (r6 != r7) goto L_0x028d
            r8 = 0
            r6 = r4[r8]
            java.lang.Class<java.lang.String> r7 = java.lang.String.class
            if (r6 != r7) goto L_0x0302
            r7 = 1
            r4 = r4[r7]
            java.lang.Class<java.lang.String> r6 = java.lang.String.class
            if (r4 != r6) goto L_0x0302
            r3.setAccessible(r7)
            java.lang.String[] r1 = com.alibaba.fastjson.util.ASMUtils.lookupParameterNames(r3)
        L_0x0288:
            r7 = r1
            r22 = r3
            goto L_0x0308
        L_0x028d:
            r8 = 0
            goto L_0x0302
        L_0x0290:
            r7 = 1
            r8 = 0
            java.lang.String r6 = "org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken"
            boolean r6 = r9.equals(r6)
            if (r6 == 0) goto L_0x02c3
            int r6 = r4.length
            r7 = 3
            if (r6 != r7) goto L_0x0302
            r6 = r4[r8]
            java.lang.Class<java.lang.Object> r7 = java.lang.Object.class
            if (r6 != r7) goto L_0x0302
            r6 = 1
            r7 = r4[r6]
            java.lang.Class<java.lang.Object> r8 = java.lang.Object.class
            if (r7 != r8) goto L_0x028d
            r7 = 2
            r4 = r4[r7]
            java.lang.Class<java.util.Collection> r7 = java.util.Collection.class
            if (r4 != r7) goto L_0x028d
            r3.setAccessible(r6)
            java.lang.String r0 = "principal"
            java.lang.String r1 = "credentials"
            java.lang.String r2 = "authorities"
            java.lang.String[] r1 = new java.lang.String[]{r0, r1, r2}
            r7 = r1
            r22 = r3
            goto L_0x0258
        L_0x02c3:
            java.lang.String r6 = "org.springframework.security.core.authority.SimpleGrantedAuthority"
            boolean r6 = r9.equals(r6)
            if (r6 == 0) goto L_0x02dd
            int r6 = r4.length
            r7 = 1
            if (r6 != r7) goto L_0x028d
            r8 = 0
            r4 = r4[r8]
            java.lang.Class<java.lang.String> r6 = java.lang.String.class
            if (r4 != r6) goto L_0x0302
            java.lang.String r0 = "authority"
            java.lang.String[] r1 = new java.lang.String[]{r0}
            goto L_0x0288
        L_0x02dd:
            r7 = 1
            r8 = 0
            int r4 = r3.getModifiers()
            r4 = r4 & r7
            if (r4 == 0) goto L_0x02e8
            r4 = 1
            goto L_0x02e9
        L_0x02e8:
            r4 = 0
        L_0x02e9:
            if (r4 != 0) goto L_0x02ec
            goto L_0x0302
        L_0x02ec:
            java.lang.String[] r4 = com.alibaba.fastjson.util.ASMUtils.lookupParameterNames(r3)
            if (r4 == 0) goto L_0x0302
            int r6 = r4.length
            if (r6 != 0) goto L_0x02f6
            goto L_0x0302
        L_0x02f6:
            if (r22 == 0) goto L_0x02ff
            if (r2 == 0) goto L_0x02ff
            int r6 = r4.length
            int r7 = r2.length
            if (r6 > r7) goto L_0x02ff
            goto L_0x0302
        L_0x02ff:
            r22 = r3
            r2 = r4
        L_0x0302:
            int r5 = r5 + 1
            goto L_0x025f
        L_0x0306:
            r8 = 0
            r7 = r2
        L_0x0308:
            if (r7 == 0) goto L_0x0310
            java.lang.Class[] r0 = r22.getParameterTypes()
            r6 = r0
            goto L_0x0312
        L_0x0310:
            r6 = r18
        L_0x0312:
            if (r7 == 0) goto L_0x03e4
            int r0 = r6.length
            int r1 = r7.length
            if (r0 != r1) goto L_0x03e4
            java.lang.annotation.Annotation[][] r21 = com.alibaba.fastjson.util.TypeUtils.getParameterAnnotations((java.lang.reflect.Constructor) r22)
            r5 = 0
        L_0x031d:
            int r0 = r6.length
            if (r5 >= r0) goto L_0x03c4
            r0 = r21[r5]
            r1 = r7[r5]
            int r2 = r0.length
            r3 = 0
        L_0x0326:
            if (r3 >= r2) goto L_0x0335
            r4 = r0[r3]
            boolean r8 = r4 instanceof com.alibaba.fastjson.annotation.JSONField
            if (r8 == 0) goto L_0x0331
            com.alibaba.fastjson.annotation.JSONField r4 = (com.alibaba.fastjson.annotation.JSONField) r4
            goto L_0x0337
        L_0x0331:
            int r3 = r3 + 1
            r8 = 0
            goto L_0x0326
        L_0x0335:
            r4 = r18
        L_0x0337:
            r3 = r6[r5]
            java.lang.reflect.Type[] r0 = r22.getGenericParameterTypes()
            r8 = r0[r5]
            java.lang.reflect.Field r2 = com.alibaba.fastjson.util.TypeUtils.getField(r13, r1, r10)
            if (r2 == 0) goto L_0x0350
            if (r4 != 0) goto L_0x0350
            java.lang.Class<com.alibaba.fastjson.annotation.JSONField> r0 = com.alibaba.fastjson.annotation.JSONField.class
            java.lang.annotation.Annotation r0 = com.alibaba.fastjson.util.TypeUtils.getAnnotation((java.lang.reflect.Field) r2, r0)
            r4 = r0
            com.alibaba.fastjson.annotation.JSONField r4 = (com.alibaba.fastjson.annotation.JSONField) r4
        L_0x0350:
            if (r4 != 0) goto L_0x0374
            java.lang.String r0 = "org.springframework.security.core.userdetails.User"
            boolean r0 = r0.equals(r9)
            if (r0 == 0) goto L_0x036d
            java.lang.String r0 = "password"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x036d
            com.alibaba.fastjson.parser.Feature r0 = com.alibaba.fastjson.parser.Feature.InitStringFieldAsEmpty
            int r0 = r0.mask
            r27 = r0
            r25 = 0
            r26 = 0
            goto L_0x0399
        L_0x036d:
            r25 = 0
            r26 = 0
            r27 = 0
            goto L_0x0399
        L_0x0374:
            java.lang.String r0 = r4.name()
            int r25 = r0.length()
            if (r25 == 0) goto L_0x037f
            r1 = r0
        L_0x037f:
            int r0 = r4.ordinal()
            com.alibaba.fastjson.serializer.SerializerFeature[] r25 = r4.serialzeFeatures()
            int r25 = com.alibaba.fastjson.serializer.SerializerFeature.of(r25)
            com.alibaba.fastjson.parser.Feature[] r4 = r4.parseFeatures()
            int r4 = com.alibaba.fastjson.parser.Feature.of(r4)
            r27 = r4
            r26 = r25
            r25 = r0
        L_0x0399:
            com.alibaba.fastjson.util.FieldInfo r4 = new com.alibaba.fastjson.util.FieldInfo
            r0 = r4
            r28 = r2
            r2 = r47
            r52 = r9
            r9 = r4
            r4 = r8
            r29 = r5
            r5 = r28
            r28 = r6
            r6 = r25
            r25 = r7
            r8 = 1
            r7 = r26
            r8 = r27
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8)
            add(r12, r9)
            int r5 = r29 + 1
            r9 = r52
            r7 = r25
            r6 = r28
            r8 = 0
            goto L_0x031d
        L_0x03c4:
            if (r17 != 0) goto L_0x0405
            java.lang.String r0 = r47.getName()
            java.lang.String r1 = "javax.servlet.http.Cookie"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0405
            com.alibaba.fastjson.util.JavaBeanInfo r9 = new com.alibaba.fastjson.util.JavaBeanInfo
            r3 = 0
            r5 = 0
            r6 = 0
            r0 = r9
            r1 = r47
            r2 = r11
            r4 = r22
            r7 = r24
            r8 = r12
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8)
            return r9
        L_0x03e4:
            com.alibaba.fastjson.JSONException r0 = new com.alibaba.fastjson.JSONException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "default constructor not found. "
            r1.append(r2)
            r1.append(r13)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x03fb:
            r23 = r12
            r24 = r15
            r12 = r6
            r15 = r8
            r22 = r18
        L_0x0403:
            r14 = r21
        L_0x0405:
            if (r19 == 0) goto L_0x040a
            com.alibaba.fastjson.util.TypeUtils.setAccessible(r19)
        L_0x040a:
            java.lang.String r9 = "set"
            if (r11 == 0) goto L_0x05ef
            java.lang.Class<com.alibaba.fastjson.annotation.JSONPOJOBuilder> r0 = com.alibaba.fastjson.annotation.JSONPOJOBuilder.class
            java.lang.annotation.Annotation r0 = com.alibaba.fastjson.util.TypeUtils.getAnnotation((java.lang.Class<?>) r11, r0)
            com.alibaba.fastjson.annotation.JSONPOJOBuilder r0 = (com.alibaba.fastjson.annotation.JSONPOJOBuilder) r0
            if (r0 == 0) goto L_0x041d
            java.lang.String r0 = r0.withPrefix()
            goto L_0x041f
        L_0x041d:
            r0 = r18
        L_0x041f:
            if (r0 != 0) goto L_0x0423
            java.lang.String r0 = "with"
        L_0x0423:
            r8 = r0
            java.lang.reflect.Method[] r7 = r11.getMethods()
            int r6 = r7.length
            r5 = 0
        L_0x042a:
            if (r5 >= r6) goto L_0x05a5
            r2 = r7[r5]
            int r0 = r2.getModifiers()
            boolean r0 = java.lang.reflect.Modifier.isStatic(r0)
            if (r0 == 0) goto L_0x044d
        L_0x0438:
            r29 = r5
            r30 = r6
            r31 = r7
            r17 = r8
            r40 = r9
            r36 = r10
            r37 = r11
            r27 = r14
            r38 = r23
            r14 = r12
            goto L_0x0590
        L_0x044d:
            java.lang.Class r0 = r2.getReturnType()
            boolean r0 = r0.equals(r11)
            if (r0 != 0) goto L_0x0458
            goto L_0x0438
        L_0x0458:
            java.lang.Class<com.alibaba.fastjson.annotation.JSONField> r0 = com.alibaba.fastjson.annotation.JSONField.class
            java.lang.annotation.Annotation r0 = com.alibaba.fastjson.util.TypeUtils.getAnnotation((java.lang.reflect.Method) r2, r0)
            com.alibaba.fastjson.annotation.JSONField r0 = (com.alibaba.fastjson.annotation.JSONField) r0
            if (r0 != 0) goto L_0x0466
            com.alibaba.fastjson.annotation.JSONField r0 = com.alibaba.fastjson.util.TypeUtils.getSuperMethodAnnotation(r13, r2)
        L_0x0466:
            r17 = r0
            if (r17 == 0) goto L_0x04ea
            boolean r0 = r17.deserialize()
            if (r0 != 0) goto L_0x0471
            goto L_0x0438
        L_0x0471:
            int r21 = r17.ordinal()
            com.alibaba.fastjson.serializer.SerializerFeature[] r0 = r17.serialzeFeatures()
            int r25 = com.alibaba.fastjson.serializer.SerializerFeature.of(r0)
            com.alibaba.fastjson.parser.Feature[] r0 = r17.parseFeatures()
            int r26 = com.alibaba.fastjson.parser.Feature.of(r0)
            java.lang.String r0 = r17.name()
            int r0 = r0.length()
            if (r0 == 0) goto L_0x04d0
            java.lang.String r1 = r17.name()
            com.alibaba.fastjson.util.FieldInfo r4 = new com.alibaba.fastjson.util.FieldInfo
            r3 = 0
            r27 = 0
            r28 = 0
            r0 = r4
            r34 = r4
            r4 = r47
            r29 = r5
            r5 = r48
            r30 = r6
            r6 = r21
            r31 = r7
            r7 = r25
            r52 = r8
            r8 = r26
            r35 = r9
            r9 = r17
            r36 = r10
            r10 = r27
            r37 = r11
            r11 = r28
            r27 = r14
            r38 = r23
            r14 = r12
            r12 = r16
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12)
            r0 = r34
            add(r14, r0)
            r17 = r52
            r40 = r35
            goto L_0x0590
        L_0x04d0:
            r29 = r5
            r30 = r6
            r31 = r7
            r52 = r8
            r35 = r9
            r36 = r10
            r37 = r11
            r27 = r14
            r38 = r23
            r14 = r12
            r6 = r21
            r7 = r25
            r8 = r26
            goto L_0x0500
        L_0x04ea:
            r29 = r5
            r30 = r6
            r31 = r7
            r52 = r8
            r35 = r9
            r36 = r10
            r37 = r11
            r27 = r14
            r38 = r23
            r14 = r12
            r6 = 0
            r7 = 0
            r8 = 0
        L_0x0500:
            java.lang.String r0 = r2.getName()
            r12 = r35
            boolean r1 = r0.startsWith(r12)
            if (r1 == 0) goto L_0x051d
            int r1 = r0.length()
            r3 = 3
            if (r1 <= r3) goto L_0x051d
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r0 = r0.substring(r3)
            r1.<init>(r0)
            goto L_0x0528
        L_0x051d:
            int r1 = r52.length()
            if (r1 != 0) goto L_0x052c
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>(r0)
        L_0x0528:
            r11 = r52
        L_0x052a:
            r10 = 0
            goto L_0x0552
        L_0x052c:
            r11 = r52
            boolean r1 = r0.startsWith(r11)
            if (r1 != 0) goto L_0x0539
        L_0x0534:
            r17 = r11
            r40 = r12
            goto L_0x0590
        L_0x0539:
            int r1 = r0.length()
            int r3 = r11.length()
            if (r1 > r3) goto L_0x0544
            goto L_0x0534
        L_0x0544:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            int r3 = r11.length()
            java.lang.String r0 = r0.substring(r3)
            r1.<init>(r0)
            goto L_0x052a
        L_0x0552:
            char r0 = r1.charAt(r10)
            int r3 = r11.length()
            if (r3 == 0) goto L_0x0563
            boolean r3 = java.lang.Character.isUpperCase(r0)
            if (r3 != 0) goto L_0x0563
            goto L_0x0534
        L_0x0563:
            char r0 = java.lang.Character.toLowerCase(r0)
            r1.setCharAt(r10, r0)
            java.lang.String r1 = r1.toString()
            com.alibaba.fastjson.util.FieldInfo r9 = new com.alibaba.fastjson.util.FieldInfo
            r3 = 0
            r21 = 0
            r23 = 0
            r0 = r9
            r4 = r47
            r5 = r48
            r39 = r9
            r9 = r17
            r10 = r21
            r17 = r11
            r11 = r23
            r40 = r12
            r12 = r16
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12)
            r0 = r39
            add(r14, r0)
        L_0x0590:
            int r5 = r29 + 1
            r12 = r14
            r8 = r17
            r14 = r27
            r6 = r30
            r7 = r31
            r10 = r36
            r11 = r37
            r23 = r38
            r9 = r40
            goto L_0x042a
        L_0x05a5:
            r40 = r9
            r36 = r10
            r27 = r14
            r38 = r23
            r14 = r12
            r12 = r11
            if (r12 == 0) goto L_0x05f9
            java.lang.Class<com.alibaba.fastjson.annotation.JSONPOJOBuilder> r0 = com.alibaba.fastjson.annotation.JSONPOJOBuilder.class
            java.lang.annotation.Annotation r0 = com.alibaba.fastjson.util.TypeUtils.getAnnotation((java.lang.Class<?>) r12, r0)
            com.alibaba.fastjson.annotation.JSONPOJOBuilder r0 = (com.alibaba.fastjson.annotation.JSONPOJOBuilder) r0
            if (r0 == 0) goto L_0x05c0
            java.lang.String r0 = r0.buildMethod()
            goto L_0x05c2
        L_0x05c0:
            r0 = r18
        L_0x05c2:
            if (r0 == 0) goto L_0x05ca
            int r1 = r0.length()
            if (r1 != 0) goto L_0x05cc
        L_0x05ca:
            java.lang.String r0 = "build"
        L_0x05cc:
            r11 = 0
            java.lang.Class[] r1 = new java.lang.Class[r11]     // Catch:{ NoSuchMethodException | SecurityException -> 0x05d4 }
            java.lang.reflect.Method r20 = r12.getMethod(r0, r1)     // Catch:{ NoSuchMethodException | SecurityException -> 0x05d4 }
            goto L_0x05d5
        L_0x05d4:
        L_0x05d5:
            if (r20 != 0) goto L_0x05e1
            java.lang.String r0 = "create"
            java.lang.Class[] r1 = new java.lang.Class[r11]     // Catch:{ NoSuchMethodException | SecurityException -> 0x05e0 }
            java.lang.reflect.Method r20 = r12.getMethod(r0, r1)     // Catch:{ NoSuchMethodException | SecurityException -> 0x05e0 }
            goto L_0x05e1
        L_0x05e0:
        L_0x05e1:
            if (r20 == 0) goto L_0x05e7
            com.alibaba.fastjson.util.TypeUtils.setAccessible(r20)
            goto L_0x05fa
        L_0x05e7:
            com.alibaba.fastjson.JSONException r0 = new com.alibaba.fastjson.JSONException
            java.lang.String r1 = "buildMethod not found."
            r0.<init>(r1)
            throw r0
        L_0x05ef:
            r40 = r9
            r36 = r10
            r27 = r14
            r38 = r23
            r14 = r12
            r12 = r11
        L_0x05f9:
            r11 = 0
        L_0x05fa:
            int r10 = r15.length
            r9 = 0
        L_0x05fc:
            r8 = 4
            if (r9 >= r10) goto L_0x0895
            r2 = r15[r9]
            r6 = 0
            r7 = 0
            r17 = 0
            java.lang.String r0 = r2.getName()
            int r1 = r2.getModifiers()
            boolean r1 = java.lang.reflect.Modifier.isStatic(r1)
            if (r1 == 0) goto L_0x0629
        L_0x0613:
            r28 = r9
            r29 = r10
            r23 = r12
            r21 = r15
            r43 = r36
            r15 = r38
            r30 = r40
            r26 = 2
        L_0x0623:
            r31 = 0
            r33 = 3
            goto L_0x0882
        L_0x0629:
            java.lang.Class r1 = r2.getReturnType()
            java.lang.Class r3 = java.lang.Void.TYPE
            boolean r3 = r1.equals(r3)
            if (r3 != 0) goto L_0x0640
            java.lang.Class r3 = r2.getDeclaringClass()
            boolean r1 = r1.equals(r3)
            if (r1 != 0) goto L_0x0640
            goto L_0x0613
        L_0x0640:
            java.lang.Class r1 = r2.getDeclaringClass()
            java.lang.Class<java.lang.Object> r3 = java.lang.Object.class
            if (r1 != r3) goto L_0x0649
            goto L_0x0613
        L_0x0649:
            java.lang.Class[] r1 = r2.getParameterTypes()
            int r3 = r1.length
            if (r3 == 0) goto L_0x0613
            int r3 = r1.length
            r5 = 2
            if (r3 <= r5) goto L_0x0655
            goto L_0x0613
        L_0x0655:
            java.lang.Class<com.alibaba.fastjson.annotation.JSONField> r3 = com.alibaba.fastjson.annotation.JSONField.class
            java.lang.annotation.Annotation r3 = com.alibaba.fastjson.util.TypeUtils.getAnnotation((java.lang.reflect.Method) r2, r3)
            r21 = r3
            com.alibaba.fastjson.annotation.JSONField r21 = (com.alibaba.fastjson.annotation.JSONField) r21
            if (r21 == 0) goto L_0x069c
            int r3 = r1.length
            if (r3 != r5) goto L_0x069c
            r3 = r1[r11]
            java.lang.Class<java.lang.String> r4 = java.lang.String.class
            if (r3 != r4) goto L_0x069c
            r4 = 1
            r3 = r1[r4]
            java.lang.Class<java.lang.Object> r4 = java.lang.Object.class
            if (r3 != r4) goto L_0x069c
            com.alibaba.fastjson.util.FieldInfo r8 = new com.alibaba.fastjson.util.FieldInfo
            r3 = 0
            r23 = 0
            r25 = 0
            java.lang.String r1 = ""
            r0 = r8
            r4 = r47
            r26 = 2
            r5 = r48
            r41 = r8
            r8 = r17
            r28 = r9
            r9 = r21
            r29 = r10
            r10 = r23
            r11 = r25
            r23 = r12
            r12 = r16
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12)
            r0 = r41
            add(r14, r0)
            goto L_0x06a8
        L_0x069c:
            r28 = r9
            r29 = r10
            r23 = r12
            r26 = 2
            int r3 = r1.length
            r12 = 1
            if (r3 == r12) goto L_0x06b2
        L_0x06a8:
            r21 = r15
        L_0x06aa:
            r43 = r36
            r15 = r38
            r30 = r40
            goto L_0x0623
        L_0x06b2:
            if (r21 != 0) goto L_0x06ba
            com.alibaba.fastjson.annotation.JSONField r3 = com.alibaba.fastjson.util.TypeUtils.getSuperMethodAnnotation(r13, r2)
            r9 = r3
            goto L_0x06bc
        L_0x06ba:
            r9 = r21
        L_0x06bc:
            if (r9 != 0) goto L_0x06c5
            int r3 = r0.length()
            if (r3 >= r8) goto L_0x06c5
            goto L_0x06a8
        L_0x06c5:
            if (r9 == 0) goto L_0x0717
            boolean r3 = r9.deserialize()
            if (r3 != 0) goto L_0x06ce
            goto L_0x06a8
        L_0x06ce:
            int r6 = r9.ordinal()
            com.alibaba.fastjson.serializer.SerializerFeature[] r3 = r9.serialzeFeatures()
            int r7 = com.alibaba.fastjson.serializer.SerializerFeature.of(r3)
            com.alibaba.fastjson.parser.Feature[] r3 = r9.parseFeatures()
            int r10 = com.alibaba.fastjson.parser.Feature.of(r3)
            java.lang.String r3 = r9.name()
            int r3 = r3.length()
            if (r3 == 0) goto L_0x0711
            java.lang.String r1 = r9.name()
            com.alibaba.fastjson.util.FieldInfo r11 = new com.alibaba.fastjson.util.FieldInfo
            r3 = 0
            r17 = 0
            r21 = 0
            r0 = r11
            r4 = r47
            r5 = r48
            r8 = r10
            r10 = r17
            r42 = r11
            r11 = r21
            r21 = r15
            r15 = 1
            r12 = r16
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12)
            r0 = r42
            add(r14, r0)
            goto L_0x06aa
        L_0x0711:
            r21 = r15
            r15 = 1
            r17 = r10
            goto L_0x071a
        L_0x0717:
            r21 = r15
            r15 = 1
        L_0x071a:
            r12 = r40
            if (r9 != 0) goto L_0x072d
            boolean r3 = r0.startsWith(r12)
            if (r3 == 0) goto L_0x0725
            goto L_0x072d
        L_0x0725:
            r30 = r12
            r43 = r36
        L_0x0729:
            r15 = r38
            goto L_0x0623
        L_0x072d:
            if (r23 == 0) goto L_0x0730
            goto L_0x0725
        L_0x0730:
            r11 = 3
            char r3 = r0.charAt(r11)
            boolean r4 = java.lang.Character.isUpperCase(r3)
            if (r4 != 0) goto L_0x0791
            r4 = 512(0x200, float:7.175E-43)
            if (r3 <= r4) goto L_0x0740
            goto L_0x0791
        L_0x0740:
            r4 = 95
            if (r3 != r4) goto L_0x075d
            java.lang.String r3 = r0.substring(r8)
            r10 = r36
            java.lang.reflect.Field r4 = com.alibaba.fastjson.util.TypeUtils.getField(r13, r3, r10)
            if (r4 != 0) goto L_0x07bd
            java.lang.String r0 = r0.substring(r11)
            java.lang.reflect.Field r4 = com.alibaba.fastjson.util.TypeUtils.getField(r13, r0, r10)
            if (r4 != 0) goto L_0x075b
            goto L_0x07bd
        L_0x075b:
            r3 = r0
            goto L_0x07bd
        L_0x075d:
            r10 = r36
            r4 = 102(0x66, float:1.43E-43)
            if (r3 != r4) goto L_0x0768
            java.lang.String r3 = r0.substring(r11)
            goto L_0x07bb
        L_0x0768:
            int r3 = r0.length()
            r4 = 5
            if (r3 < r4) goto L_0x0782
            char r3 = r0.charAt(r8)
            boolean r3 = java.lang.Character.isUpperCase(r3)
            if (r3 == 0) goto L_0x0782
            java.lang.String r0 = r0.substring(r11)
            java.lang.String r3 = com.alibaba.fastjson.util.TypeUtils.decapitalize(r0)
            goto L_0x07bb
        L_0x0782:
            java.lang.String r3 = r0.substring(r11)
            java.lang.reflect.Field r4 = com.alibaba.fastjson.util.TypeUtils.getField(r13, r3, r10)
            if (r4 != 0) goto L_0x07bd
        L_0x078c:
            r43 = r10
            r30 = r12
            goto L_0x0729
        L_0x0791:
            r10 = r36
            boolean r3 = com.alibaba.fastjson.util.TypeUtils.compatibleWithJavaBean
            if (r3 == 0) goto L_0x07a0
            java.lang.String r0 = r0.substring(r11)
            java.lang.String r3 = com.alibaba.fastjson.util.TypeUtils.decapitalize(r0)
            goto L_0x07bb
        L_0x07a0:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            char r4 = r0.charAt(r11)
            char r4 = java.lang.Character.toLowerCase(r4)
            r3.append(r4)
            java.lang.String r0 = r0.substring(r8)
            r3.append(r0)
            java.lang.String r3 = r3.toString()
        L_0x07bb:
            r4 = r18
        L_0x07bd:
            if (r4 != 0) goto L_0x07c3
            java.lang.reflect.Field r4 = com.alibaba.fastjson.util.TypeUtils.getField(r13, r3, r10)
        L_0x07c3:
            r8 = 0
            if (r4 != 0) goto L_0x07f1
            r0 = r1[r8]
            java.lang.Class r1 = java.lang.Boolean.TYPE
            if (r0 != r1) goto L_0x07f1
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "is"
            r0.append(r1)
            char r1 = r3.charAt(r8)
            char r1 = java.lang.Character.toUpperCase(r1)
            r0.append(r1)
            java.lang.String r1 = r3.substring(r15)
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            java.lang.reflect.Field r0 = com.alibaba.fastjson.util.TypeUtils.getField(r13, r0, r10)
            r4 = r0
        L_0x07f1:
            if (r4 == 0) goto L_0x0859
            java.lang.Class<com.alibaba.fastjson.annotation.JSONField> r0 = com.alibaba.fastjson.annotation.JSONField.class
            java.lang.annotation.Annotation r0 = com.alibaba.fastjson.util.TypeUtils.getAnnotation((java.lang.reflect.Field) r4, r0)
            r25 = r0
            com.alibaba.fastjson.annotation.JSONField r25 = (com.alibaba.fastjson.annotation.JSONField) r25
            if (r25 == 0) goto L_0x084c
            boolean r0 = r25.deserialize()
            if (r0 != 0) goto L_0x0806
            goto L_0x078c
        L_0x0806:
            int r6 = r25.ordinal()
            com.alibaba.fastjson.serializer.SerializerFeature[] r0 = r25.serialzeFeatures()
            int r7 = com.alibaba.fastjson.serializer.SerializerFeature.of(r0)
            com.alibaba.fastjson.parser.Feature[] r0 = r25.parseFeatures()
            int r17 = com.alibaba.fastjson.parser.Feature.of(r0)
            java.lang.String r0 = r25.name()
            int r0 = r0.length()
            if (r0 == 0) goto L_0x084c
            java.lang.String r1 = r25.name()
            com.alibaba.fastjson.util.FieldInfo r5 = new com.alibaba.fastjson.util.FieldInfo
            r30 = 0
            r0 = r5
            r3 = r4
            r4 = r47
            r15 = r5
            r5 = r48
            r31 = 0
            r8 = r17
            r43 = r10
            r10 = r25
            r33 = 3
            r11 = r30
            r30 = r12
            r12 = r16
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12)
            add(r14, r15)
            r15 = r38
            goto L_0x0882
        L_0x084c:
            r43 = r10
            r30 = r12
            r31 = 0
            r33 = 3
            r8 = r17
            r10 = r25
            goto L_0x0865
        L_0x0859:
            r43 = r10
            r30 = r12
            r31 = 0
            r33 = 3
            r8 = r17
            r10 = r18
        L_0x0865:
            r15 = r38
            if (r15 == 0) goto L_0x086f
            java.lang.String r0 = r15.translate(r3)
            r1 = r0
            goto L_0x0870
        L_0x086f:
            r1 = r3
        L_0x0870:
            com.alibaba.fastjson.util.FieldInfo r12 = new com.alibaba.fastjson.util.FieldInfo
            r11 = 0
            r0 = r12
            r3 = r4
            r4 = r47
            r5 = r48
            r13 = r12
            r12 = r16
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12)
            add(r14, r13)
        L_0x0882:
            int r9 = r28 + 1
            r13 = r47
            r38 = r15
            r15 = r21
            r12 = r23
            r10 = r29
            r40 = r30
            r36 = r43
            r11 = 0
            goto L_0x05fc
        L_0x0895:
            r23 = r12
            r43 = r36
            r15 = r38
            r31 = 0
            r33 = 3
            java.lang.reflect.Field[] r0 = r47.getFields()
            r11 = 3
            r13 = r47
            r12 = r48
            computeFields(r13, r12, r15, r14, r0)
            java.lang.reflect.Method[] r10 = r47.getMethods()
            int r9 = r10.length
            r7 = 0
        L_0x08b1:
            if (r7 >= r9) goto L_0x09dd
            r2 = r10[r7]
            java.lang.String r0 = r2.getName()
            int r1 = r0.length()
            if (r1 >= r8) goto L_0x08ce
        L_0x08bf:
            r31 = r7
            r21 = r9
            r17 = r10
            r13 = r12
            r45 = r43
        L_0x08c8:
            r18 = 4
            r25 = 3
            goto L_0x09ce
        L_0x08ce:
            int r1 = r2.getModifiers()
            boolean r1 = java.lang.reflect.Modifier.isStatic(r1)
            if (r1 == 0) goto L_0x08d9
            goto L_0x08bf
        L_0x08d9:
            if (r23 != 0) goto L_0x08bf
            java.lang.String r1 = "get"
            boolean r1 = r0.startsWith(r1)
            if (r1 == 0) goto L_0x08bf
            char r1 = r0.charAt(r11)
            boolean r1 = java.lang.Character.isUpperCase(r1)
            if (r1 == 0) goto L_0x08bf
            java.lang.Class[] r1 = r2.getParameterTypes()
            int r1 = r1.length
            if (r1 == 0) goto L_0x08f5
            goto L_0x08bf
        L_0x08f5:
            java.lang.Class<java.util.Collection> r1 = java.util.Collection.class
            java.lang.Class r3 = r2.getReturnType()
            boolean r1 = r1.isAssignableFrom(r3)
            if (r1 != 0) goto L_0x0925
            java.lang.Class<java.util.Map> r1 = java.util.Map.class
            java.lang.Class r3 = r2.getReturnType()
            boolean r1 = r1.isAssignableFrom(r3)
            if (r1 != 0) goto L_0x0925
            java.lang.Class<java.util.concurrent.atomic.AtomicBoolean> r1 = java.util.concurrent.atomic.AtomicBoolean.class
            java.lang.Class r3 = r2.getReturnType()
            if (r1 == r3) goto L_0x0925
            java.lang.Class<java.util.concurrent.atomic.AtomicInteger> r1 = java.util.concurrent.atomic.AtomicInteger.class
            java.lang.Class r3 = r2.getReturnType()
            if (r1 == r3) goto L_0x0925
            java.lang.Class<java.util.concurrent.atomic.AtomicLong> r1 = java.util.concurrent.atomic.AtomicLong.class
            java.lang.Class r3 = r2.getReturnType()
            if (r1 != r3) goto L_0x08bf
        L_0x0925:
            java.lang.Class<com.alibaba.fastjson.annotation.JSONField> r1 = com.alibaba.fastjson.annotation.JSONField.class
            java.lang.annotation.Annotation r1 = com.alibaba.fastjson.util.TypeUtils.getAnnotation((java.lang.reflect.Method) r2, r1)
            r17 = r1
            com.alibaba.fastjson.annotation.JSONField r17 = (com.alibaba.fastjson.annotation.JSONField) r17
            if (r17 == 0) goto L_0x0938
            boolean r1 = r17.deserialize()
            if (r1 == 0) goto L_0x0938
            goto L_0x08bf
        L_0x0938:
            if (r17 == 0) goto L_0x094b
            java.lang.String r1 = r17.name()
            int r1 = r1.length()
            if (r1 <= 0) goto L_0x094b
            java.lang.String r0 = r17.name()
            r6 = r43
            goto L_0x097f
        L_0x094b:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            char r3 = r0.charAt(r11)
            char r3 = java.lang.Character.toLowerCase(r3)
            r1.append(r3)
            java.lang.String r0 = r0.substring(r8)
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            r6 = r43
            java.lang.reflect.Field r1 = com.alibaba.fastjson.util.TypeUtils.getField(r13, r0, r6)
            if (r1 == 0) goto L_0x097f
            java.lang.Class<com.alibaba.fastjson.annotation.JSONField> r3 = com.alibaba.fastjson.annotation.JSONField.class
            java.lang.annotation.Annotation r1 = com.alibaba.fastjson.util.TypeUtils.getAnnotation((java.lang.reflect.Field) r1, r3)
            com.alibaba.fastjson.annotation.JSONField r1 = (com.alibaba.fastjson.annotation.JSONField) r1
            if (r1 == 0) goto L_0x097f
            boolean r1 = r1.deserialize()
            if (r1 != 0) goto L_0x097f
            goto L_0x098c
        L_0x097f:
            if (r15 == 0) goto L_0x0985
            java.lang.String r0 = r15.translate(r0)
        L_0x0985:
            r1 = r0
            com.alibaba.fastjson.util.FieldInfo r0 = getField(r14, r1)
            if (r0 == 0) goto L_0x0997
        L_0x098c:
            r45 = r6
            r31 = r7
            r21 = r9
            r17 = r10
            r13 = r12
            goto L_0x08c8
        L_0x0997:
            com.alibaba.fastjson.util.FieldInfo r5 = new com.alibaba.fastjson.util.FieldInfo
            r3 = 0
            r18 = 0
            r21 = 0
            r25 = 0
            r26 = 0
            r28 = 0
            r0 = r5
            r4 = r47
            r44 = r5
            r5 = r48
            r45 = r6
            r6 = r18
            r31 = r7
            r7 = r21
            r18 = 4
            r8 = r25
            r21 = r9
            r9 = r17
            r17 = r10
            r10 = r26
            r25 = 3
            r11 = r28
            r13 = r12
            r12 = r16
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12)
            r0 = r44
            add(r14, r0)
        L_0x09ce:
            int r7 = r31 + 1
            r12 = r13
            r10 = r17
            r9 = r21
            r43 = r45
            r8 = 4
            r11 = 3
            r13 = r47
            goto L_0x08b1
        L_0x09dd:
            r13 = r12
            r45 = r43
            int r0 = r14.size()
            if (r0 != 0) goto L_0x0a04
            boolean r0 = com.alibaba.fastjson.util.TypeUtils.isXmlField(r47)
            if (r0 == 0) goto L_0x09ef
            r32 = 1
            goto L_0x09f1
        L_0x09ef:
            r32 = r50
        L_0x09f1:
            if (r32 == 0) goto L_0x0a04
            r0 = r47
        L_0x09f5:
            if (r0 == 0) goto L_0x0a04
            r1 = r47
            r2 = r13
            r3 = r45
            computeFields(r1, r2, r15, r14, r3)
            java.lang.Class r0 = r0.getSuperclass()
            goto L_0x09f5
        L_0x0a04:
            r1 = r47
            com.alibaba.fastjson.util.JavaBeanInfo r9 = new com.alibaba.fastjson.util.JavaBeanInfo
            r0 = r9
            r1 = r47
            r2 = r23
            r3 = r19
            r4 = r22
            r5 = r27
            r6 = r20
            r7 = r24
            r8 = r14
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.util.JavaBeanInfo.build(java.lang.Class, java.lang.reflect.Type, com.alibaba.fastjson.PropertyNamingStrategy, boolean, boolean, boolean):com.alibaba.fastjson.util.JavaBeanInfo");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0055, code lost:
        if ((java.util.Map.class.isAssignableFrom(r2) || java.util.Collection.class.isAssignableFrom(r2) || java.util.concurrent.atomic.AtomicLong.class.equals(r2) || java.util.concurrent.atomic.AtomicInteger.class.equals(r2) || java.util.concurrent.atomic.AtomicBoolean.class.equals(r2)) == false) goto L_0x0018;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void computeFields(java.lang.Class<?> r20, java.lang.reflect.Type r21, com.alibaba.fastjson.PropertyNamingStrategy r22, java.util.List<com.alibaba.fastjson.util.FieldInfo> r23, java.lang.reflect.Field[] r24) {
        /*
            r0 = r22
            r1 = r24
            java.util.Map r15 = buildGenericInfo(r20)
            int r14 = r1.length
            r16 = 0
            r13 = 0
        L_0x000c:
            if (r13 >= r14) goto L_0x00e6
            r5 = r1[r13]
            int r2 = r5.getModifiers()
            r3 = r2 & 8
            if (r3 == 0) goto L_0x0020
        L_0x0018:
            r2 = r23
            r17 = r13
            r18 = r14
            goto L_0x00e0
        L_0x0020:
            r2 = r2 & 16
            r3 = 1
            if (r2 == 0) goto L_0x0058
            java.lang.Class r2 = r5.getType()
            java.lang.Class<java.util.Map> r4 = java.util.Map.class
            boolean r4 = r4.isAssignableFrom(r2)
            if (r4 != 0) goto L_0x0054
            java.lang.Class<java.util.Collection> r4 = java.util.Collection.class
            boolean r4 = r4.isAssignableFrom(r2)
            if (r4 != 0) goto L_0x0054
            java.lang.Class<java.util.concurrent.atomic.AtomicLong> r4 = java.util.concurrent.atomic.AtomicLong.class
            boolean r4 = r4.equals(r2)
            if (r4 != 0) goto L_0x0054
            java.lang.Class<java.util.concurrent.atomic.AtomicInteger> r4 = java.util.concurrent.atomic.AtomicInteger.class
            boolean r4 = r4.equals(r2)
            if (r4 != 0) goto L_0x0054
            java.lang.Class<java.util.concurrent.atomic.AtomicBoolean> r4 = java.util.concurrent.atomic.AtomicBoolean.class
            boolean r2 = r4.equals(r2)
            if (r2 == 0) goto L_0x0052
            goto L_0x0054
        L_0x0052:
            r2 = 0
            goto L_0x0055
        L_0x0054:
            r2 = 1
        L_0x0055:
            if (r2 != 0) goto L_0x0058
            goto L_0x0018
        L_0x0058:
            java.util.Iterator r2 = r23.iterator()
        L_0x005c:
            boolean r4 = r2.hasNext()
            if (r4 == 0) goto L_0x0075
            java.lang.Object r4 = r2.next()
            com.alibaba.fastjson.util.FieldInfo r4 = (com.alibaba.fastjson.util.FieldInfo) r4
            java.lang.String r4 = r4.name
            java.lang.String r6 = r5.getName()
            boolean r4 = r4.equals(r6)
            if (r4 == 0) goto L_0x005c
            goto L_0x0076
        L_0x0075:
            r3 = 0
        L_0x0076:
            if (r3 == 0) goto L_0x0079
            goto L_0x0018
        L_0x0079:
            java.lang.String r2 = r5.getName()
            java.lang.Class<com.alibaba.fastjson.annotation.JSONField> r3 = com.alibaba.fastjson.annotation.JSONField.class
            java.lang.annotation.Annotation r3 = com.alibaba.fastjson.util.TypeUtils.getAnnotation((java.lang.reflect.Field) r5, r3)
            r12 = r3
            com.alibaba.fastjson.annotation.JSONField r12 = (com.alibaba.fastjson.annotation.JSONField) r12
            if (r12 == 0) goto L_0x00b5
            boolean r3 = r12.deserialize()
            if (r3 != 0) goto L_0x008f
            goto L_0x0018
        L_0x008f:
            int r3 = r12.ordinal()
            com.alibaba.fastjson.serializer.SerializerFeature[] r4 = r12.serialzeFeatures()
            int r4 = com.alibaba.fastjson.serializer.SerializerFeature.of(r4)
            com.alibaba.fastjson.parser.Feature[] r6 = r12.parseFeatures()
            int r6 = com.alibaba.fastjson.parser.Feature.of(r6)
            java.lang.String r7 = r12.name()
            int r7 = r7.length()
            if (r7 == 0) goto L_0x00b1
            java.lang.String r2 = r12.name()
        L_0x00b1:
            r8 = r3
            r9 = r4
            r10 = r6
            goto L_0x00b8
        L_0x00b5:
            r8 = 0
            r9 = 0
            r10 = 0
        L_0x00b8:
            if (r0 == 0) goto L_0x00be
            java.lang.String r2 = r0.translate(r2)
        L_0x00be:
            r3 = r2
            com.alibaba.fastjson.util.FieldInfo r11 = new com.alibaba.fastjson.util.FieldInfo
            r4 = 0
            r17 = 0
            r18 = 0
            r2 = r11
            r6 = r20
            r7 = r21
            r19 = r11
            r11 = r17
            r17 = r13
            r13 = r18
            r18 = r14
            r14 = r15
            r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14)
            r2 = r23
            r3 = r19
            add(r2, r3)
        L_0x00e0:
            int r13 = r17 + 1
            r14 = r18
            goto L_0x000c
        L_0x00e6:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.util.JavaBeanInfo.computeFields(java.lang.Class, java.lang.reflect.Type, com.alibaba.fastjson.PropertyNamingStrategy, java.util.List, java.lang.reflect.Field[]):void");
    }

    static Constructor<?> getDefaultConstructor(Class<?> cls, Constructor<?>[] constructorArr) {
        Constructor<?> constructor = null;
        if (Modifier.isAbstract(cls.getModifiers())) {
            return null;
        }
        int length = constructorArr.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            Constructor<?> constructor2 = constructorArr[i];
            if (constructor2.getParameterTypes().length == 0) {
                constructor = constructor2;
                break;
            }
            i++;
        }
        if (constructor != null || !cls.isMemberClass() || Modifier.isStatic(cls.getModifiers())) {
            return constructor;
        }
        for (Constructor<?> constructor3 : constructorArr) {
            Class[] parameterTypes = constructor3.getParameterTypes();
            if (parameterTypes.length == 1 && parameterTypes[0].equals(cls.getDeclaringClass())) {
                return constructor3;
            }
        }
        return constructor;
    }

    public static Constructor<?> getCreatorConstructor(Constructor[] constructorArr) {
        boolean z;
        Constructor<?> constructor = null;
        for (Constructor<?> constructor2 : constructorArr) {
            if (((JSONCreator) constructor2.getAnnotation(JSONCreator.class)) != null) {
                if (constructor == null) {
                    constructor = constructor2;
                } else {
                    throw new JSONException("multi-JSONCreator");
                }
            }
        }
        if (constructor != null) {
            return constructor;
        }
        for (Constructor<?> constructor3 : constructorArr) {
            Annotation[][] parameterAnnotations = TypeUtils.getParameterAnnotations((Constructor) constructor3);
            if (parameterAnnotations.length != 0) {
                int length = parameterAnnotations.length;
                int i = 0;
                while (true) {
                    z = true;
                    if (i >= length) {
                        break;
                    }
                    Annotation[] annotationArr = parameterAnnotations[i];
                    int length2 = annotationArr.length;
                    int i2 = 0;
                    while (true) {
                        if (i2 >= length2) {
                            z = false;
                            break;
                        } else if (annotationArr[i2] instanceof JSONField) {
                            break;
                        } else {
                            i2++;
                        }
                    }
                    if (!z) {
                        z = false;
                        break;
                    }
                    i++;
                }
                if (!z) {
                    continue;
                } else if (constructor == null) {
                    constructor = constructor3;
                } else {
                    throw new JSONException("multi-JSONCreator");
                }
            }
        }
        if (constructor != null) {
        }
        return constructor;
    }

    private static Method getFactoryMethod(Class<?> cls, Method[] methodArr, boolean z) {
        Method method = null;
        for (Method method2 : methodArr) {
            if (Modifier.isStatic(method2.getModifiers()) && cls.isAssignableFrom(method2.getReturnType()) && ((JSONCreator) TypeUtils.getAnnotation(method2, JSONCreator.class)) != null) {
                if (method == null) {
                    method = method2;
                } else {
                    throw new JSONException("multi-JSONCreator");
                }
            }
        }
        if (method != null || !z) {
            return method;
        }
        for (Method method3 : methodArr) {
            if (TypeUtils.isJacksonCreator(method3)) {
                return method3;
            }
        }
        return method;
    }

    public static Class<?> getBuilderClass(JSONType jSONType) {
        return getBuilderClass((Class<?>) null, jSONType);
    }

    public static Class<?> getBuilderClass(Class<?> cls, JSONType jSONType) {
        Class<?> builder;
        if (cls != null && cls.getName().equals("org.springframework.security.web.savedrequest.DefaultSavedRequest")) {
            return TypeUtils.loadClass("org.springframework.security.web.savedrequest.DefaultSavedRequest$Builder");
        }
        if (jSONType == null || (builder = jSONType.builder()) == Void.class) {
            return null;
        }
        return builder;
    }
}
