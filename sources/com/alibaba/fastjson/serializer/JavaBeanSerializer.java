package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.util.FieldInfo;
import com.alibaba.fastjson.util.TypeUtils;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class JavaBeanSerializer extends SerializeFilterable implements ObjectSerializer {
    protected SerializeBeanInfo beanInfo;
    protected final FieldSerializer[] getters;
    private volatile transient long[] hashArray;
    private volatile transient short[] hashArrayMapping;
    protected final FieldSerializer[] sortedGetters;

    public JavaBeanSerializer(Class<?> cls) {
        this(cls, (Map<String, String>) null);
    }

    public JavaBeanSerializer(Class<?> cls, String... strArr) {
        this(cls, createAliasMap(strArr));
    }

    static Map<String, String> createAliasMap(String... strArr) {
        HashMap hashMap = new HashMap();
        for (String str : strArr) {
            hashMap.put(str, str);
        }
        return hashMap;
    }

    public Class<?> getType() {
        return this.beanInfo.beanType;
    }

    public JavaBeanSerializer(Class<?> cls, Map<String, String> map) {
        this(TypeUtils.buildBeanInfo(cls, map, (PropertyNamingStrategy) null));
    }

    public JavaBeanSerializer(SerializeBeanInfo serializeBeanInfo) {
        boolean z;
        this.beanInfo = serializeBeanInfo;
        this.sortedGetters = new FieldSerializer[serializeBeanInfo.sortedFields.length];
        int i = 0;
        while (true) {
            FieldSerializer[] fieldSerializerArr = this.sortedGetters;
            if (i >= fieldSerializerArr.length) {
                break;
            }
            fieldSerializerArr[i] = new FieldSerializer(serializeBeanInfo.beanType, serializeBeanInfo.sortedFields[i]);
            i++;
        }
        if (serializeBeanInfo.fields == serializeBeanInfo.sortedFields) {
            this.getters = this.sortedGetters;
        } else {
            this.getters = new FieldSerializer[serializeBeanInfo.fields.length];
            int i2 = 0;
            while (true) {
                if (i2 >= this.getters.length) {
                    z = false;
                    break;
                }
                FieldSerializer fieldSerializer = getFieldSerializer(serializeBeanInfo.fields[i2].name);
                if (fieldSerializer == null) {
                    z = true;
                    break;
                } else {
                    this.getters[i2] = fieldSerializer;
                    i2++;
                }
            }
            if (z) {
                FieldSerializer[] fieldSerializerArr2 = this.sortedGetters;
                System.arraycopy(fieldSerializerArr2, 0, this.getters, 0, fieldSerializerArr2.length);
            }
        }
        if (serializeBeanInfo.jsonType != null) {
            for (Class constructor : serializeBeanInfo.jsonType.serialzeFilters()) {
                try {
                    addFilter((SerializeFilter) constructor.getConstructor(new Class[0]).newInstance(new Object[0]));
                } catch (Exception unused) {
                }
            }
        }
    }

    public void writeDirectNonContext(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type, int i) throws IOException {
        write(jSONSerializer, obj, obj2, type, i);
    }

    public void writeAsArray(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type, int i) throws IOException {
        write(jSONSerializer, obj, obj2, type, i);
    }

    public void writeAsArrayNonContext(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type, int i) throws IOException {
        write(jSONSerializer, obj, obj2, type, i);
    }

    public void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type, int i) throws IOException {
        write(jSONSerializer, obj, obj2, type, i, false);
    }

    public void writeNoneASM(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type, int i) throws IOException {
        write(jSONSerializer, obj, obj2, type, i, false);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v0, resolved type: com.alibaba.fastjson.serializer.JavaBeanSerializer} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v0, resolved type: java.lang.Exception} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v8, resolved type: com.alibaba.fastjson.serializer.JavaBeanSerializer} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v3, resolved type: java.lang.Exception} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v19, resolved type: com.alibaba.fastjson.serializer.JavaBeanSerializer} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v21, resolved type: com.alibaba.fastjson.serializer.JavaBeanSerializer} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v4, resolved type: java.lang.Exception} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v1, resolved type: com.alibaba.fastjson.serializer.JavaBeanSerializer} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v6, resolved type: java.lang.Exception} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v32, resolved type: com.alibaba.fastjson.serializer.JavaBeanSerializer} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v33, resolved type: com.alibaba.fastjson.serializer.JavaBeanSerializer} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v7, resolved type: java.lang.Exception} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v34, resolved type: com.alibaba.fastjson.serializer.JavaBeanSerializer} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v35, resolved type: com.alibaba.fastjson.serializer.JavaBeanSerializer} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v9, resolved type: java.lang.Exception} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v36, resolved type: com.alibaba.fastjson.serializer.JavaBeanSerializer} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v37, resolved type: com.alibaba.fastjson.serializer.JavaBeanSerializer} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v10, resolved type: java.lang.Exception} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v38, resolved type: com.alibaba.fastjson.serializer.JavaBeanSerializer} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v40, resolved type: com.alibaba.fastjson.serializer.JavaBeanSerializer} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v11, resolved type: java.lang.Exception} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v3, resolved type: com.alibaba.fastjson.serializer.JavaBeanSerializer} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v49, resolved type: com.alibaba.fastjson.serializer.JavaBeanSerializer} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v50, resolved type: com.alibaba.fastjson.serializer.JavaBeanSerializer} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v69, resolved type: com.alibaba.fastjson.serializer.JavaBeanSerializer} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v51, resolved type: java.lang.Exception} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v70, resolved type: com.alibaba.fastjson.serializer.JavaBeanSerializer} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v52, resolved type: java.lang.Exception} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v53, resolved type: java.lang.Exception} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v77, resolved type: com.alibaba.fastjson.serializer.JavaBeanSerializer} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v54, resolved type: java.lang.Exception} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v94, resolved type: com.alibaba.fastjson.serializer.JavaBeanSerializer} */
    /* JADX WARNING: type inference failed for: r3v1, types: [java.lang.Throwable] */
    /* JADX WARNING: type inference failed for: r3v2 */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:106:0x0146, code lost:
        if (r9.isWriteClassName(r12, r10) != false) goto L_0x0114;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x005f, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0060, code lost:
        r1 = r0;
        r2 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:366:0x0494, code lost:
        if (r5 == false) goto L_0x0473;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:396:0x04fe, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:397:0x04ff, code lost:
        r1 = r0;
        r2 = r33;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:398:0x0504, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:413:0x0528, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:414:0x0529, code lost:
        r2 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:416:0x052d, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:417:0x052e, code lost:
        r2 = r7;
        r1 = r10;
        r3 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:418:0x0531, code lost:
        r12 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0091, code lost:
        if (r9.isWriteClassName(r12, r10) == false) goto L_0x00ae;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x0107, code lost:
        if (r13.fieldTransient != false) goto L_0x0114;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:102:0x0138 A[SYNTHETIC, Splitter:B:102:0x0138] */
    /* JADX WARNING: Removed duplicated region for block: B:109:0x014e A[SYNTHETIC, Splitter:B:109:0x014e] */
    /* JADX WARNING: Removed duplicated region for block: B:120:0x0166 A[Catch:{ Exception -> 0x04ca, all -> 0x005f }] */
    /* JADX WARNING: Removed duplicated region for block: B:121:0x0167 A[Catch:{ Exception -> 0x04ca, all -> 0x005f }] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x005f A[ExcHandler: all (r0v20 'th' java.lang.Throwable A[CUSTOM_DECLARE]), PHI: r7 
      PHI: (r7v7 com.alibaba.fastjson.serializer.SerialContext) = (r7v2 com.alibaba.fastjson.serializer.SerialContext), (r7v2 com.alibaba.fastjson.serializer.SerialContext), (r7v2 com.alibaba.fastjson.serializer.SerialContext), (r7v2 com.alibaba.fastjson.serializer.SerialContext), (r7v2 com.alibaba.fastjson.serializer.SerialContext), (r7v2 com.alibaba.fastjson.serializer.SerialContext), (r7v2 com.alibaba.fastjson.serializer.SerialContext), (r7v2 com.alibaba.fastjson.serializer.SerialContext), (r7v2 com.alibaba.fastjson.serializer.SerialContext), (r7v2 com.alibaba.fastjson.serializer.SerialContext), (r7v2 com.alibaba.fastjson.serializer.SerialContext), (r7v2 com.alibaba.fastjson.serializer.SerialContext), (r7v2 com.alibaba.fastjson.serializer.SerialContext), (r7v2 com.alibaba.fastjson.serializer.SerialContext), (r7v2 com.alibaba.fastjson.serializer.SerialContext), (r7v2 com.alibaba.fastjson.serializer.SerialContext), (r7v0 com.alibaba.fastjson.serializer.SerialContext), (r7v0 com.alibaba.fastjson.serializer.SerialContext), (r7v0 com.alibaba.fastjson.serializer.SerialContext), (r7v0 com.alibaba.fastjson.serializer.SerialContext), (r7v0 com.alibaba.fastjson.serializer.SerialContext) binds: [B:71:0x00da, B:72:?, B:74:0x00ec, B:75:?, B:90:0x011e, B:99:0x0132, B:100:?, B:117:0x0160, B:130:0x017e, B:131:?, B:124:0x016d, B:113:0x0155, B:102:0x0138, B:93:0x0124, B:82:0x0105, B:83:?, B:48:0x0099, B:40:0x0086, B:32:0x006d, B:23:0x005b, B:24:?] A[DONT_GENERATE, DONT_INLINE], Splitter:B:117:0x0160] */
    /* JADX WARNING: Removed duplicated region for block: B:319:0x03ec A[Catch:{ Exception -> 0x049f, all -> 0x04fe }] */
    /* JADX WARNING: Removed duplicated region for block: B:349:0x0460 A[Catch:{ Exception -> 0x049f, all -> 0x04fe }] */
    /* JADX WARNING: Removed duplicated region for block: B:357:0x0475 A[Catch:{ Exception -> 0x049f, all -> 0x04fe }] */
    /* JADX WARNING: Removed duplicated region for block: B:369:0x049a  */
    /* JADX WARNING: Removed duplicated region for block: B:387:0x04e3  */
    /* JADX WARNING: Removed duplicated region for block: B:388:0x04e6  */
    /* JADX WARNING: Removed duplicated region for block: B:392:0x04ef A[SYNTHETIC, Splitter:B:392:0x04ef] */
    /* JADX WARNING: Removed duplicated region for block: B:396:0x04fe A[Catch:{ Exception -> 0x0504, all -> 0x04fe }, ExcHandler: all (r0v10 'th' java.lang.Throwable A[CUSTOM_DECLARE, Catch:{ Exception -> 0x0504, all -> 0x04fe }]), PHI: r33 
      PHI: (r33v4 com.alibaba.fastjson.serializer.SerialContext) = (r33v0 com.alibaba.fastjson.serializer.SerialContext), (r33v8 com.alibaba.fastjson.serializer.SerialContext), (r33v8 com.alibaba.fastjson.serializer.SerialContext), (r33v9 com.alibaba.fastjson.serializer.SerialContext), (r33v9 com.alibaba.fastjson.serializer.SerialContext) binds: [B:392:0x04ef, B:133:0x01a0, B:137:0x01a8, B:373:0x04a7, B:374:?] A[DONT_GENERATE, DONT_INLINE], Splitter:B:133:0x01a0] */
    /* JADX WARNING: Removed duplicated region for block: B:402:0x050d A[Catch:{ Exception -> 0x0504, all -> 0x04fe }] */
    /* JADX WARNING: Removed duplicated region for block: B:413:0x0528 A[ExcHandler: all (th java.lang.Throwable), PHI: r7 
      PHI: (r7v1 com.alibaba.fastjson.serializer.SerialContext) = (r7v0 com.alibaba.fastjson.serializer.SerialContext), (r7v0 com.alibaba.fastjson.serializer.SerialContext), (r7v0 com.alibaba.fastjson.serializer.SerialContext), (r7v0 com.alibaba.fastjson.serializer.SerialContext), (r7v0 com.alibaba.fastjson.serializer.SerialContext), (r7v2 com.alibaba.fastjson.serializer.SerialContext), (r7v2 com.alibaba.fastjson.serializer.SerialContext), (r7v0 com.alibaba.fastjson.serializer.SerialContext) binds: [B:29:0x006a, B:30:?, B:36:0x007b, B:37:?, B:61:0x00b7, B:68:0x00d7, B:69:?, B:45:0x0093] A[DONT_GENERATE, DONT_INLINE], Splitter:B:29:0x006a] */
    /* JADX WARNING: Removed duplicated region for block: B:421:0x0537 A[SYNTHETIC, Splitter:B:421:0x0537] */
    /* JADX WARNING: Removed duplicated region for block: B:425:0x0559 A[SYNTHETIC, Splitter:B:425:0x0559] */
    /* JADX WARNING: Removed duplicated region for block: B:427:0x056c A[ADDED_TO_REGION, Catch:{ all -> 0x05da }] */
    /* JADX WARNING: Removed duplicated region for block: B:436:0x05af A[Catch:{ all -> 0x05da }] */
    /* JADX WARNING: Removed duplicated region for block: B:439:0x05cb A[Catch:{ all -> 0x05da }] */
    /* JADX WARNING: Removed duplicated region for block: B:441:0x05d1 A[Catch:{ all -> 0x05da }] */
    /* JADX WARNING: Removed duplicated region for block: B:442:0x05d2 A[Catch:{ all -> 0x05da }] */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x00a5 A[Catch:{ Exception -> 0x0064, all -> 0x005f }] */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x00b3  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x00b6  */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x00c3 A[Catch:{ Exception -> 0x052d, all -> 0x0528 }] */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x00c5 A[Catch:{ Exception -> 0x052d, all -> 0x0528 }] */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x00da A[SYNTHETIC, Splitter:B:71:0x00da] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void write(com.alibaba.fastjson.serializer.JSONSerializer r35, java.lang.Object r36, java.lang.Object r37, java.lang.reflect.Type r38, int r39, boolean r40) throws java.io.IOException {
        /*
            r34 = this;
            r8 = r34
            r9 = r35
            r10 = r36
            r11 = r37
            r12 = r38
            r13 = r39
            com.alibaba.fastjson.serializer.SerializeWriter r14 = r9.out
            if (r10 != 0) goto L_0x0014
            r14.writeNull()
            return
        L_0x0014:
            boolean r1 = r8.writeReference(r9, r10, r13)
            if (r1 == 0) goto L_0x001b
            return
        L_0x001b:
            boolean r1 = r14.sortField
            if (r1 == 0) goto L_0x0022
            com.alibaba.fastjson.serializer.FieldSerializer[] r1 = r8.sortedGetters
            goto L_0x0024
        L_0x0022:
            com.alibaba.fastjson.serializer.FieldSerializer[] r1 = r8.getters
        L_0x0024:
            r15 = r1
            com.alibaba.fastjson.serializer.SerialContext r7 = r9.context
            com.alibaba.fastjson.serializer.SerializeBeanInfo r1 = r8.beanInfo
            java.lang.Class<?> r1 = r1.beanType
            boolean r1 = r1.isEnum()
            if (r1 != 0) goto L_0x0041
            com.alibaba.fastjson.serializer.SerializeBeanInfo r1 = r8.beanInfo
            int r5 = r1.features
            r1 = r35
            r2 = r7
            r3 = r36
            r4 = r37
            r6 = r39
            r1.setContext(r2, r3, r4, r5, r6)
        L_0x0041:
            boolean r16 = r8.isWriteAsArray(r9, r13)
            if (r16 == 0) goto L_0x004a
            r1 = 91
            goto L_0x004c
        L_0x004a:
            r1 = 123(0x7b, float:1.72E-43)
        L_0x004c:
            if (r16 == 0) goto L_0x0053
            r2 = 93
            r6 = 93
            goto L_0x0057
        L_0x0053:
            r2 = 125(0x7d, float:1.75E-43)
            r6 = 125(0x7d, float:1.75E-43)
        L_0x0057:
            r17 = 0
            if (r40 != 0) goto L_0x006a
            r14.append((char) r1)     // Catch:{ Exception -> 0x0064, all -> 0x005f }
            goto L_0x006a
        L_0x005f:
            r0 = move-exception
            r1 = r0
            r2 = r7
            goto L_0x05dd
        L_0x0064:
            r0 = move-exception
            r3 = r0
            r2 = r7
            r1 = r10
            goto L_0x0531
        L_0x006a:
            int r1 = r15.length     // Catch:{ Exception -> 0x052d, all -> 0x0528 }
            if (r1 <= 0) goto L_0x007b
            com.alibaba.fastjson.serializer.SerializerFeature r1 = com.alibaba.fastjson.serializer.SerializerFeature.PrettyFormat     // Catch:{ Exception -> 0x0064, all -> 0x005f }
            boolean r1 = r14.isEnabled((com.alibaba.fastjson.serializer.SerializerFeature) r1)     // Catch:{ Exception -> 0x0064, all -> 0x005f }
            if (r1 == 0) goto L_0x007b
            r35.incrementIndent()     // Catch:{ Exception -> 0x0064, all -> 0x005f }
            r35.println()     // Catch:{ Exception -> 0x0064, all -> 0x005f }
        L_0x007b:
            com.alibaba.fastjson.serializer.SerializeBeanInfo r1 = r8.beanInfo     // Catch:{ Exception -> 0x052d, all -> 0x0528 }
            int r1 = r1.features     // Catch:{ Exception -> 0x052d, all -> 0x0528 }
            com.alibaba.fastjson.serializer.SerializerFeature r2 = com.alibaba.fastjson.serializer.SerializerFeature.WriteClassName     // Catch:{ Exception -> 0x052d, all -> 0x0528 }
            int r2 = r2.mask     // Catch:{ Exception -> 0x052d, all -> 0x0528 }
            r1 = r1 & r2
            if (r1 != 0) goto L_0x0093
            com.alibaba.fastjson.serializer.SerializerFeature r1 = com.alibaba.fastjson.serializer.SerializerFeature.WriteClassName     // Catch:{ Exception -> 0x0064, all -> 0x005f }
            int r1 = r1.mask     // Catch:{ Exception -> 0x0064, all -> 0x005f }
            r1 = r1 & r13
            if (r1 != 0) goto L_0x0093
            boolean r1 = r9.isWriteClassName(r12, r10)     // Catch:{ Exception -> 0x0064, all -> 0x005f }
            if (r1 == 0) goto L_0x00ae
        L_0x0093:
            java.lang.Class r1 = r36.getClass()     // Catch:{ Exception -> 0x052d, all -> 0x0528 }
            if (r1 == r12) goto L_0x00a2
            boolean r2 = r12 instanceof java.lang.reflect.WildcardType     // Catch:{ Exception -> 0x0064, all -> 0x005f }
            if (r2 == 0) goto L_0x00a2
            java.lang.Class r2 = com.alibaba.fastjson.util.TypeUtils.getClass(r38)     // Catch:{ Exception -> 0x0064, all -> 0x005f }
            goto L_0x00a3
        L_0x00a2:
            r2 = r12
        L_0x00a3:
            if (r1 == r2) goto L_0x00ae
            com.alibaba.fastjson.serializer.SerializeBeanInfo r1 = r8.beanInfo     // Catch:{ Exception -> 0x0064, all -> 0x005f }
            java.lang.String r1 = r1.typeKey     // Catch:{ Exception -> 0x0064, all -> 0x005f }
            r8.writeClassName(r9, r1, r10)     // Catch:{ Exception -> 0x0064, all -> 0x005f }
            r1 = 1
            goto L_0x00af
        L_0x00ae:
            r1 = 0
        L_0x00af:
            r3 = 44
            if (r1 == 0) goto L_0x00b6
            r1 = 44
            goto L_0x00b7
        L_0x00b6:
            r1 = 0
        L_0x00b7:
            com.alibaba.fastjson.serializer.SerializerFeature r2 = com.alibaba.fastjson.serializer.SerializerFeature.WriteClassName     // Catch:{ Exception -> 0x052d, all -> 0x0528 }
            boolean r18 = r14.isEnabled((com.alibaba.fastjson.serializer.SerializerFeature) r2)     // Catch:{ Exception -> 0x052d, all -> 0x0528 }
            char r1 = r8.writeBefore(r9, r10, r1)     // Catch:{ Exception -> 0x052d, all -> 0x0528 }
            if (r1 != r3) goto L_0x00c5
            r1 = 1
            goto L_0x00c6
        L_0x00c5:
            r1 = 0
        L_0x00c6:
            com.alibaba.fastjson.serializer.SerializerFeature r2 = com.alibaba.fastjson.serializer.SerializerFeature.SkipTransientField     // Catch:{ Exception -> 0x052d, all -> 0x0528 }
            boolean r19 = r14.isEnabled((com.alibaba.fastjson.serializer.SerializerFeature) r2)     // Catch:{ Exception -> 0x052d, all -> 0x0528 }
            com.alibaba.fastjson.serializer.SerializerFeature r2 = com.alibaba.fastjson.serializer.SerializerFeature.IgnoreNonFieldGetter     // Catch:{ Exception -> 0x052d, all -> 0x0528 }
            boolean r20 = r14.isEnabled((com.alibaba.fastjson.serializer.SerializerFeature) r2)     // Catch:{ Exception -> 0x052d, all -> 0x0528 }
            r21 = r1
            r1 = r17
            r2 = 0
        L_0x00d7:
            int r3 = r15.length     // Catch:{ Exception -> 0x051f, all -> 0x0528 }
            if (r2 >= r3) goto L_0x04d4
            r3 = r15[r2]     // Catch:{ Exception -> 0x04cc, all -> 0x005f }
            com.alibaba.fastjson.util.FieldInfo r4 = r3.fieldInfo     // Catch:{ Exception -> 0x04cc, all -> 0x005f }
            java.lang.reflect.Field r4 = r4.field     // Catch:{ Exception -> 0x04cc, all -> 0x005f }
            com.alibaba.fastjson.util.FieldInfo r13 = r3.fieldInfo     // Catch:{ Exception -> 0x04cc, all -> 0x005f }
            java.lang.String r11 = r13.name     // Catch:{ Exception -> 0x04cc, all -> 0x005f }
            r24 = r15
            java.lang.Class<?> r15 = r13.fieldClass     // Catch:{ Exception -> 0x04cc, all -> 0x005f }
            int r5 = r14.features     // Catch:{ Exception -> 0x04cc, all -> 0x005f }
            r26 = r1
            int r1 = r13.serialzeFeatures     // Catch:{ Exception -> 0x04ca, all -> 0x005f }
            r27 = r2
            com.alibaba.fastjson.serializer.SerializerFeature r2 = com.alibaba.fastjson.serializer.SerializerFeature.UseSingleQuotes     // Catch:{ Exception -> 0x04ca, all -> 0x005f }
            boolean r28 = com.alibaba.fastjson.serializer.SerializerFeature.isEnabled(r5, r1, r2)     // Catch:{ Exception -> 0x04ca, all -> 0x005f }
            boolean r1 = r14.quoteFieldNames     // Catch:{ Exception -> 0x04ca, all -> 0x005f }
            if (r1 == 0) goto L_0x00ff
            if (r28 != 0) goto L_0x00ff
            r29 = 1
            goto L_0x0101
        L_0x00ff:
            r29 = 0
        L_0x0101:
            if (r19 == 0) goto L_0x0110
            if (r13 == 0) goto L_0x0110
            boolean r1 = r13.fieldTransient     // Catch:{ Exception -> 0x010a, all -> 0x005f }
            if (r1 == 0) goto L_0x0110
            goto L_0x0114
        L_0x010a:
            r0 = move-exception
            r3 = r0
            r2 = r7
            r1 = r10
            goto L_0x0525
        L_0x0110:
            if (r20 == 0) goto L_0x011e
            if (r4 != 0) goto L_0x011e
        L_0x0114:
            r32 = r6
            r33 = r7
            r4 = 1
            r6 = 0
            r10 = 44
            goto L_0x04b6
        L_0x011e:
            boolean r1 = r8.applyName(r9, r10, r11)     // Catch:{ Exception -> 0x04ca, all -> 0x005f }
            if (r1 == 0) goto L_0x012f
            java.lang.String r1 = r13.label     // Catch:{ Exception -> 0x010a, all -> 0x005f }
            boolean r1 = r8.applyLabel(r9, r1)     // Catch:{ Exception -> 0x010a, all -> 0x005f }
            if (r1 != 0) goto L_0x012d
            goto L_0x012f
        L_0x012d:
            r1 = 0
            goto L_0x0132
        L_0x012f:
            if (r16 == 0) goto L_0x0114
            r1 = 1
        L_0x0132:
            com.alibaba.fastjson.serializer.SerializeBeanInfo r2 = r8.beanInfo     // Catch:{ Exception -> 0x04ca, all -> 0x005f }
            java.lang.String r2 = r2.typeKey     // Catch:{ Exception -> 0x04ca, all -> 0x005f }
            if (r2 == 0) goto L_0x0149
            com.alibaba.fastjson.serializer.SerializeBeanInfo r2 = r8.beanInfo     // Catch:{ Exception -> 0x010a, all -> 0x005f }
            java.lang.String r2 = r2.typeKey     // Catch:{ Exception -> 0x010a, all -> 0x005f }
            boolean r2 = r11.equals(r2)     // Catch:{ Exception -> 0x010a, all -> 0x005f }
            if (r2 == 0) goto L_0x0149
            boolean r2 = r9.isWriteClassName(r12, r10)     // Catch:{ Exception -> 0x010a, all -> 0x005f }
            if (r2 == 0) goto L_0x0149
            goto L_0x0114
        L_0x0149:
            if (r1 == 0) goto L_0x014e
        L_0x014b:
            r1 = r17
            goto L_0x0160
        L_0x014e:
            java.lang.Object r1 = r3.getPropertyValueDirect(r10)     // Catch:{ InvocationTargetException -> 0x0153 }
            goto L_0x0160
        L_0x0153:
            r0 = move-exception
            r1 = r0
            com.alibaba.fastjson.serializer.SerializerFeature r2 = com.alibaba.fastjson.serializer.SerializerFeature.IgnoreErrorGetter     // Catch:{ Exception -> 0x04ae, all -> 0x005f }
            boolean r2 = r14.isEnabled((com.alibaba.fastjson.serializer.SerializerFeature) r2)     // Catch:{ Exception -> 0x04ae, all -> 0x005f }
            if (r2 == 0) goto L_0x04a4
            r26 = r3
            goto L_0x014b
        L_0x0160:
            boolean r2 = r8.apply(r9, r10, r11, r1)     // Catch:{ Exception -> 0x04ca, all -> 0x005f }
            if (r2 != 0) goto L_0x0167
            goto L_0x0114
        L_0x0167:
            java.lang.Class<java.lang.String> r2 = java.lang.String.class
            if (r15 != r2) goto L_0x017d
            java.lang.String r2 = "trim"
            java.lang.String r4 = r13.format     // Catch:{ Exception -> 0x010a, all -> 0x005f }
            boolean r2 = r2.equals(r4)     // Catch:{ Exception -> 0x010a, all -> 0x005f }
            if (r2 == 0) goto L_0x017d
            if (r1 == 0) goto L_0x017d
            java.lang.String r1 = (java.lang.String) r1     // Catch:{ Exception -> 0x010a, all -> 0x005f }
            java.lang.String r1 = r1.trim()     // Catch:{ Exception -> 0x010a, all -> 0x005f }
        L_0x017d:
            r5 = r1
            java.lang.String r4 = r8.processKey(r9, r10, r11, r5)     // Catch:{ Exception -> 0x04ca, all -> 0x005f }
            com.alibaba.fastjson.serializer.BeanContext r2 = r3.fieldContext     // Catch:{ Exception -> 0x04ca, all -> 0x005f }
            r1 = r34
            r30 = r2
            r2 = r35
            r12 = r3
            r10 = 44
            r3 = r30
            r31 = r4
            r22 = 0
            r4 = r36
            r23 = r5
            r5 = r11
            r32 = r6
            r6 = r23
            r33 = r7
            r7 = r39
            java.lang.Object r1 = r1.processValue(r2, r3, r4, r5, r6, r7)     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            java.lang.String r2 = ""
            if (r1 != 0) goto L_0x02c0
            int r3 = r13.serialzeFeatures     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            com.alibaba.fastjson.annotation.JSONField r4 = r13.getAnnotation()     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            com.alibaba.fastjson.serializer.SerializeBeanInfo r5 = r8.beanInfo     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            com.alibaba.fastjson.annotation.JSONType r5 = r5.jsonType     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            if (r5 == 0) goto L_0x01c1
            com.alibaba.fastjson.serializer.SerializeBeanInfo r5 = r8.beanInfo     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            com.alibaba.fastjson.annotation.JSONType r5 = r5.jsonType     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            com.alibaba.fastjson.serializer.SerializerFeature[] r5 = r5.serialzeFeatures()     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            int r5 = com.alibaba.fastjson.serializer.SerializerFeature.of(r5)     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            r3 = r3 | r5
        L_0x01c1:
            if (r4 == 0) goto L_0x01d3
            java.lang.String r5 = r4.defaultValue()     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            boolean r5 = r2.equals(r5)     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            if (r5 != 0) goto L_0x01d3
            java.lang.String r1 = r4.defaultValue()     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            goto L_0x02c0
        L_0x01d3:
            java.lang.Class<java.lang.Boolean> r4 = java.lang.Boolean.class
            if (r15 != r4) goto L_0x0209
            com.alibaba.fastjson.serializer.SerializerFeature r4 = com.alibaba.fastjson.serializer.SerializerFeature.WriteNullBooleanAsFalse     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            int r4 = r4.mask     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            com.alibaba.fastjson.serializer.SerializerFeature r5 = com.alibaba.fastjson.serializer.SerializerFeature.WriteMapNullValue     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            int r5 = r5.mask     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            r5 = r5 | r4
            if (r16 != 0) goto L_0x01ed
            r6 = r3 & r5
            if (r6 != 0) goto L_0x01ed
            int r6 = r14.features     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            r5 = r5 & r6
            if (r5 != 0) goto L_0x01ed
            goto L_0x037b
        L_0x01ed:
            r5 = r3 & r4
            if (r5 == 0) goto L_0x01f7
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r22)     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            goto L_0x02c0
        L_0x01f7:
            int r5 = r14.features     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            r4 = r4 & r5
            if (r4 == 0) goto L_0x02c0
            com.alibaba.fastjson.serializer.SerializerFeature r4 = com.alibaba.fastjson.serializer.SerializerFeature.WriteMapNullValue     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            int r4 = r4.mask     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            r3 = r3 & r4
            if (r3 != 0) goto L_0x02c0
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r22)     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            goto L_0x02c0
        L_0x0209:
            java.lang.Class<java.lang.String> r4 = java.lang.String.class
            if (r15 != r4) goto L_0x0237
            com.alibaba.fastjson.serializer.SerializerFeature r4 = com.alibaba.fastjson.serializer.SerializerFeature.WriteNullStringAsEmpty     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            int r4 = r4.mask     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            com.alibaba.fastjson.serializer.SerializerFeature r5 = com.alibaba.fastjson.serializer.SerializerFeature.WriteMapNullValue     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            int r5 = r5.mask     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            r5 = r5 | r4
            if (r16 != 0) goto L_0x0223
            r6 = r3 & r5
            if (r6 != 0) goto L_0x0223
            int r6 = r14.features     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            r5 = r5 & r6
            if (r5 != 0) goto L_0x0223
            goto L_0x037b
        L_0x0223:
            r5 = r3 & r4
            if (r5 == 0) goto L_0x022a
        L_0x0227:
            r1 = r2
            goto L_0x02c0
        L_0x022a:
            int r5 = r14.features     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            r4 = r4 & r5
            if (r4 == 0) goto L_0x02c0
            com.alibaba.fastjson.serializer.SerializerFeature r4 = com.alibaba.fastjson.serializer.SerializerFeature.WriteMapNullValue     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            int r4 = r4.mask     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            r3 = r3 & r4
            if (r3 != 0) goto L_0x02c0
            goto L_0x0227
        L_0x0237:
            java.lang.Class<java.lang.Number> r4 = java.lang.Number.class
            boolean r4 = r4.isAssignableFrom(r15)     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            if (r4 == 0) goto L_0x026f
            com.alibaba.fastjson.serializer.SerializerFeature r4 = com.alibaba.fastjson.serializer.SerializerFeature.WriteNullNumberAsZero     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            int r4 = r4.mask     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            com.alibaba.fastjson.serializer.SerializerFeature r5 = com.alibaba.fastjson.serializer.SerializerFeature.WriteMapNullValue     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            int r5 = r5.mask     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            r5 = r5 | r4
            if (r16 != 0) goto L_0x0255
            r6 = r3 & r5
            if (r6 != 0) goto L_0x0255
            int r6 = r14.features     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            r5 = r5 & r6
            if (r5 != 0) goto L_0x0255
            goto L_0x037b
        L_0x0255:
            r5 = r3 & r4
            if (r5 == 0) goto L_0x025e
            java.lang.Integer r1 = java.lang.Integer.valueOf(r22)     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            goto L_0x02c0
        L_0x025e:
            int r5 = r14.features     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            r4 = r4 & r5
            if (r4 == 0) goto L_0x02c0
            com.alibaba.fastjson.serializer.SerializerFeature r4 = com.alibaba.fastjson.serializer.SerializerFeature.WriteMapNullValue     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            int r4 = r4.mask     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            r3 = r3 & r4
            if (r3 != 0) goto L_0x02c0
            java.lang.Integer r1 = java.lang.Integer.valueOf(r22)     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            goto L_0x02c0
        L_0x026f:
            java.lang.Class<java.util.Collection> r4 = java.util.Collection.class
            boolean r4 = r4.isAssignableFrom(r15)     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            if (r4 == 0) goto L_0x02a7
            com.alibaba.fastjson.serializer.SerializerFeature r4 = com.alibaba.fastjson.serializer.SerializerFeature.WriteNullListAsEmpty     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            int r4 = r4.mask     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            com.alibaba.fastjson.serializer.SerializerFeature r5 = com.alibaba.fastjson.serializer.SerializerFeature.WriteMapNullValue     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            int r5 = r5.mask     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            r5 = r5 | r4
            if (r16 != 0) goto L_0x028d
            r6 = r3 & r5
            if (r6 != 0) goto L_0x028d
            int r6 = r14.features     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            r5 = r5 & r6
            if (r5 != 0) goto L_0x028d
            goto L_0x037b
        L_0x028d:
            r5 = r3 & r4
            if (r5 == 0) goto L_0x0296
            java.util.List r1 = java.util.Collections.emptyList()     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            goto L_0x02c0
        L_0x0296:
            int r5 = r14.features     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            r4 = r4 & r5
            if (r4 == 0) goto L_0x02c0
            com.alibaba.fastjson.serializer.SerializerFeature r4 = com.alibaba.fastjson.serializer.SerializerFeature.WriteMapNullValue     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            int r4 = r4.mask     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            r3 = r3 & r4
            if (r3 != 0) goto L_0x02c0
            java.util.List r1 = java.util.Collections.emptyList()     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            goto L_0x02c0
        L_0x02a7:
            if (r16 != 0) goto L_0x02c0
            boolean r4 = r12.writeNull     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            if (r4 != 0) goto L_0x02c0
            com.alibaba.fastjson.serializer.SerializerFeature r4 = com.alibaba.fastjson.serializer.SerializerFeature.WriteMapNullValue     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            int r4 = r4.mask     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            boolean r4 = r14.isEnabled((int) r4)     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            if (r4 != 0) goto L_0x02c0
            com.alibaba.fastjson.serializer.SerializerFeature r4 = com.alibaba.fastjson.serializer.SerializerFeature.WriteMapNullValue     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            int r4 = r4.mask     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            r3 = r3 & r4
            if (r3 != 0) goto L_0x02c0
            goto L_0x037b
        L_0x02c0:
            if (r1 == 0) goto L_0x0368
            boolean r3 = r14.notWriteDefaultValue     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            if (r3 != 0) goto L_0x02da
            int r3 = r13.serialzeFeatures     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            com.alibaba.fastjson.serializer.SerializerFeature r4 = com.alibaba.fastjson.serializer.SerializerFeature.NotWriteDefaultValue     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            int r4 = r4.mask     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            r3 = r3 & r4
            if (r3 != 0) goto L_0x02da
            com.alibaba.fastjson.serializer.SerializeBeanInfo r3 = r8.beanInfo     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            int r3 = r3.features     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            com.alibaba.fastjson.serializer.SerializerFeature r4 = com.alibaba.fastjson.serializer.SerializerFeature.NotWriteDefaultValue     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            int r4 = r4.mask     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            r3 = r3 & r4
            if (r3 == 0) goto L_0x0368
        L_0x02da:
            java.lang.Class<?> r3 = r13.fieldClass     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            java.lang.Class r4 = java.lang.Byte.TYPE     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            if (r3 != r4) goto L_0x02ef
            boolean r4 = r1 instanceof java.lang.Byte     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            if (r4 == 0) goto L_0x02ef
            r4 = r1
            java.lang.Byte r4 = (java.lang.Byte) r4     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            byte r4 = r4.byteValue()     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            if (r4 != 0) goto L_0x02ef
            goto L_0x037b
        L_0x02ef:
            java.lang.Class r4 = java.lang.Short.TYPE     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            if (r3 != r4) goto L_0x0302
            boolean r4 = r1 instanceof java.lang.Short     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            if (r4 == 0) goto L_0x0302
            r4 = r1
            java.lang.Short r4 = (java.lang.Short) r4     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            short r4 = r4.shortValue()     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            if (r4 != 0) goto L_0x0302
            goto L_0x037b
        L_0x0302:
            java.lang.Class r4 = java.lang.Integer.TYPE     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            if (r3 != r4) goto L_0x0315
            boolean r4 = r1 instanceof java.lang.Integer     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            if (r4 == 0) goto L_0x0315
            r4 = r1
            java.lang.Integer r4 = (java.lang.Integer) r4     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            int r4 = r4.intValue()     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            if (r4 != 0) goto L_0x0315
            goto L_0x037b
        L_0x0315:
            java.lang.Class r4 = java.lang.Long.TYPE     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            if (r3 != r4) goto L_0x032b
            boolean r4 = r1 instanceof java.lang.Long     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            if (r4 == 0) goto L_0x032b
            r4 = r1
            java.lang.Long r4 = (java.lang.Long) r4     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            long r4 = r4.longValue()     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            r6 = 0
            int r25 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r25 != 0) goto L_0x032b
            goto L_0x037b
        L_0x032b:
            java.lang.Class r4 = java.lang.Float.TYPE     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            if (r3 != r4) goto L_0x0340
            boolean r4 = r1 instanceof java.lang.Float     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            if (r4 == 0) goto L_0x0340
            r4 = r1
            java.lang.Float r4 = (java.lang.Float) r4     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            float r4 = r4.floatValue()     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            r5 = 0
            int r4 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1))
            if (r4 != 0) goto L_0x0340
            goto L_0x037b
        L_0x0340:
            java.lang.Class r4 = java.lang.Double.TYPE     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            if (r3 != r4) goto L_0x0356
            boolean r4 = r1 instanceof java.lang.Double     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            if (r4 == 0) goto L_0x0356
            r4 = r1
            java.lang.Double r4 = (java.lang.Double) r4     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            double r4 = r4.doubleValue()     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            r6 = 0
            int r25 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r25 != 0) goto L_0x0356
            goto L_0x037b
        L_0x0356:
            java.lang.Class r4 = java.lang.Boolean.TYPE     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            if (r3 != r4) goto L_0x0368
            boolean r3 = r1 instanceof java.lang.Boolean     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            if (r3 == 0) goto L_0x0368
            r3 = r1
            java.lang.Boolean r3 = (java.lang.Boolean) r3     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            boolean r3 = r3.booleanValue()     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            if (r3 != 0) goto L_0x0368
            goto L_0x037b
        L_0x0368:
            if (r21 == 0) goto L_0x038d
            boolean r3 = r13.unwrapped     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            if (r3 == 0) goto L_0x037f
            boolean r3 = r1 instanceof java.util.Map     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            if (r3 == 0) goto L_0x037f
            r3 = r1
            java.util.Map r3 = (java.util.Map) r3     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            int r3 = r3.size()     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            if (r3 != 0) goto L_0x037f
        L_0x037b:
            r4 = 1
            r6 = 0
            goto L_0x04b6
        L_0x037f:
            r14.write((int) r10)     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            com.alibaba.fastjson.serializer.SerializerFeature r3 = com.alibaba.fastjson.serializer.SerializerFeature.PrettyFormat     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            boolean r3 = r14.isEnabled((com.alibaba.fastjson.serializer.SerializerFeature) r3)     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            if (r3 == 0) goto L_0x038d
            r35.println()     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
        L_0x038d:
            r3 = r31
            if (r3 == r11) goto L_0x039f
            if (r16 != 0) goto L_0x0398
            r4 = 1
            r14.writeFieldName(r3, r4)     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            goto L_0x0399
        L_0x0398:
            r4 = 1
        L_0x0399:
            r9.write((java.lang.Object) r1)     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
        L_0x039c:
            r6 = 0
            goto L_0x0463
        L_0x039f:
            r3 = r23
            r4 = 1
            if (r3 == r1) goto L_0x03ad
            if (r16 != 0) goto L_0x03a9
            r12.writePrefix(r9)     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
        L_0x03a9:
            r9.write((java.lang.Object) r1)     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            goto L_0x039c
        L_0x03ad:
            if (r16 != 0) goto L_0x03e9
            java.lang.Class<java.util.Map> r3 = java.util.Map.class
            boolean r3 = r3.isAssignableFrom(r15)     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            boolean r5 = r15.isPrimitive()     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            if (r5 != 0) goto L_0x03c7
            java.lang.String r5 = r15.getName()     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            java.lang.String r6 = "java."
            boolean r5 = r5.startsWith(r6)     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            if (r5 == 0) goto L_0x03cb
        L_0x03c7:
            java.lang.Class<java.lang.Object> r5 = java.lang.Object.class
            if (r15 != r5) goto L_0x03cd
        L_0x03cb:
            r5 = 1
            goto L_0x03ce
        L_0x03cd:
            r5 = 0
        L_0x03ce:
            if (r18 != 0) goto L_0x03d8
            boolean r6 = r13.unwrapped     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            if (r6 == 0) goto L_0x03d8
            if (r3 != 0) goto L_0x03e9
            if (r5 != 0) goto L_0x03e9
        L_0x03d8:
            if (r29 == 0) goto L_0x03e4
            char[] r3 = r13.name_chars     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            char[] r5 = r13.name_chars     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            int r5 = r5.length     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            r6 = 0
            r14.write((char[]) r3, (int) r6, (int) r5)     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            goto L_0x03ea
        L_0x03e4:
            r6 = 0
            r12.writePrefix(r9)     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            goto L_0x03ea
        L_0x03e9:
            r6 = 0
        L_0x03ea:
            if (r16 != 0) goto L_0x0460
            com.alibaba.fastjson.annotation.JSONField r3 = r13.getAnnotation()     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            java.lang.Class<java.lang.String> r5 = java.lang.String.class
            if (r15 != r5) goto L_0x0445
            if (r3 == 0) goto L_0x03fe
            java.lang.Class r3 = r3.serializeUsing()     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            java.lang.Class<java.lang.Void> r5 = java.lang.Void.class
            if (r3 != r5) goto L_0x0445
        L_0x03fe:
            if (r1 != 0) goto L_0x0438
            int r3 = r12.features     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            com.alibaba.fastjson.serializer.SerializeBeanInfo r5 = r8.beanInfo     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            com.alibaba.fastjson.annotation.JSONType r5 = r5.jsonType     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            if (r5 == 0) goto L_0x0415
            com.alibaba.fastjson.serializer.SerializeBeanInfo r5 = r8.beanInfo     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            com.alibaba.fastjson.annotation.JSONType r5 = r5.jsonType     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            com.alibaba.fastjson.serializer.SerializerFeature[] r5 = r5.serialzeFeatures()     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            int r5 = com.alibaba.fastjson.serializer.SerializerFeature.of(r5)     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            r3 = r3 | r5
        L_0x0415:
            int r5 = r14.features     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            com.alibaba.fastjson.serializer.SerializerFeature r7 = com.alibaba.fastjson.serializer.SerializerFeature.WriteNullStringAsEmpty     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            int r7 = r7.mask     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            r5 = r5 & r7
            if (r5 == 0) goto L_0x0429
            com.alibaba.fastjson.serializer.SerializerFeature r5 = com.alibaba.fastjson.serializer.SerializerFeature.WriteMapNullValue     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            int r5 = r5.mask     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            r5 = r5 & r3
            if (r5 != 0) goto L_0x0429
            r14.writeString((java.lang.String) r2)     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            goto L_0x0463
        L_0x0429:
            com.alibaba.fastjson.serializer.SerializerFeature r5 = com.alibaba.fastjson.serializer.SerializerFeature.WriteNullStringAsEmpty     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            int r5 = r5.mask     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            r3 = r3 & r5
            if (r3 == 0) goto L_0x0434
            r14.writeString((java.lang.String) r2)     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            goto L_0x0463
        L_0x0434:
            r14.writeNull()     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            goto L_0x0463
        L_0x0438:
            r2 = r1
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            if (r28 == 0) goto L_0x0441
            r14.writeStringWithSingleQuote((java.lang.String) r2)     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            goto L_0x0463
        L_0x0441:
            r14.writeStringWithDoubleQuote((java.lang.String) r2, (char) r6)     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            goto L_0x0463
        L_0x0445:
            boolean r2 = r13.unwrapped     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            if (r2 == 0) goto L_0x045c
            boolean r2 = r1 instanceof java.util.Map     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            if (r2 == 0) goto L_0x045c
            r2 = r1
            java.util.Map r2 = (java.util.Map) r2     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            int r2 = r2.size()     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            if (r2 != 0) goto L_0x045c
            r1 = r26
            r21 = 0
            goto L_0x04b8
        L_0x045c:
            r12.writeValue(r9, r1)     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            goto L_0x0463
        L_0x0460:
            r12.writeValue(r9, r1)     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
        L_0x0463:
            boolean r2 = r13.unwrapped     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            if (r2 == 0) goto L_0x0497
            boolean r2 = r1 instanceof java.util.Map     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            if (r2 == 0) goto L_0x0497
            java.util.Map r1 = (java.util.Map) r1     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            int r2 = r1.size()     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            if (r2 != 0) goto L_0x0475
        L_0x0473:
            r5 = 1
            goto L_0x0498
        L_0x0475:
            com.alibaba.fastjson.serializer.SerializerFeature r2 = com.alibaba.fastjson.serializer.SerializerFeature.WriteMapNullValue     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            boolean r2 = r9.isEnabled(r2)     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            if (r2 != 0) goto L_0x0497
            java.util.Collection r1 = r1.values()     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            java.util.Iterator r1 = r1.iterator()     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
        L_0x0485:
            boolean r2 = r1.hasNext()     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            if (r2 == 0) goto L_0x0493
            java.lang.Object r2 = r1.next()     // Catch:{ Exception -> 0x049f, all -> 0x04fe }
            if (r2 == 0) goto L_0x0485
            r5 = 1
            goto L_0x0494
        L_0x0493:
            r5 = 0
        L_0x0494:
            if (r5 != 0) goto L_0x0497
            goto L_0x0473
        L_0x0497:
            r5 = 0
        L_0x0498:
            if (r5 != 0) goto L_0x04b6
            r1 = r26
            r21 = 1
            goto L_0x04b8
        L_0x049f:
            r0 = move-exception
            r1 = r36
            goto L_0x0505
        L_0x04a4:
            r12 = r3
            r33 = r7
            throw r1     // Catch:{ Exception -> 0x04a8, all -> 0x04fe }
        L_0x04a8:
            r0 = move-exception
            r1 = r36
            r3 = r0
            goto L_0x0508
        L_0x04ae:
            r0 = move-exception
            r12 = r3
            r1 = r36
            r3 = r0
            r2 = r7
            goto L_0x0533
        L_0x04b6:
            r1 = r26
        L_0x04b8:
            int r2 = r27 + 1
            r10 = r36
            r11 = r37
            r12 = r38
            r13 = r39
            r15 = r24
            r6 = r32
            r7 = r33
            goto L_0x00d7
        L_0x04ca:
            r0 = move-exception
            goto L_0x04cf
        L_0x04cc:
            r0 = move-exception
            r26 = r1
        L_0x04cf:
            r1 = r36
            r3 = r0
            r2 = r7
            goto L_0x0525
        L_0x04d4:
            r26 = r1
            r32 = r6
            r33 = r7
            r24 = r15
            r6 = 0
            r10 = 44
            r1 = r36
            if (r21 == 0) goto L_0x04e6
            r4 = 44
            goto L_0x04e7
        L_0x04e6:
            r4 = 0
        L_0x04e7:
            r8.writeAfter(r9, r1, r4)     // Catch:{ Exception -> 0x051b, all -> 0x0517 }
            r2 = r24
            int r2 = r2.length     // Catch:{ Exception -> 0x051b, all -> 0x0517 }
            if (r2 <= 0) goto L_0x050b
            com.alibaba.fastjson.serializer.SerializerFeature r2 = com.alibaba.fastjson.serializer.SerializerFeature.PrettyFormat     // Catch:{ Exception -> 0x0504, all -> 0x04fe }
            boolean r2 = r14.isEnabled((com.alibaba.fastjson.serializer.SerializerFeature) r2)     // Catch:{ Exception -> 0x0504, all -> 0x04fe }
            if (r2 == 0) goto L_0x050b
            r35.decrementIdent()     // Catch:{ Exception -> 0x0504, all -> 0x04fe }
            r35.println()     // Catch:{ Exception -> 0x0504, all -> 0x04fe }
            goto L_0x050b
        L_0x04fe:
            r0 = move-exception
            r1 = r0
            r2 = r33
            goto L_0x05dd
        L_0x0504:
            r0 = move-exception
        L_0x0505:
            r3 = r0
            r12 = r26
        L_0x0508:
            r2 = r33
            goto L_0x0533
        L_0x050b:
            if (r40 != 0) goto L_0x0512
            r2 = r32
            r14.append((char) r2)     // Catch:{ Exception -> 0x0504, all -> 0x04fe }
        L_0x0512:
            r2 = r33
            r9.context = r2
            return
        L_0x0517:
            r0 = move-exception
            r2 = r33
            goto L_0x052a
        L_0x051b:
            r0 = move-exception
            r2 = r33
            goto L_0x0524
        L_0x051f:
            r0 = move-exception
            r26 = r1
            r2 = r7
            r1 = r10
        L_0x0524:
            r3 = r0
        L_0x0525:
            r12 = r26
            goto L_0x0533
        L_0x0528:
            r0 = move-exception
            r2 = r7
        L_0x052a:
            r1 = r0
            goto L_0x05dd
        L_0x052d:
            r0 = move-exception
            r2 = r7
            r1 = r10
            r3 = r0
        L_0x0531:
            r12 = r17
        L_0x0533:
            java.lang.String r4 = "write javaBean error, fastjson version 1.2.68"
            if (r1 == 0) goto L_0x0553
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x05da }
            r5.<init>()     // Catch:{ all -> 0x05da }
            r5.append(r4)     // Catch:{ all -> 0x05da }
            java.lang.String r4 = ", class "
            r5.append(r4)     // Catch:{ all -> 0x05da }
            java.lang.Class r1 = r36.getClass()     // Catch:{ all -> 0x05da }
            java.lang.String r1 = r1.getName()     // Catch:{ all -> 0x05da }
            r5.append(r1)     // Catch:{ all -> 0x05da }
            java.lang.String r4 = r5.toString()     // Catch:{ all -> 0x05da }
        L_0x0553:
            java.lang.String r1 = ", fieldName : "
            r5 = r37
            if (r5 == 0) goto L_0x056c
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x05da }
            r6.<init>()     // Catch:{ all -> 0x05da }
            r6.append(r4)     // Catch:{ all -> 0x05da }
            r6.append(r1)     // Catch:{ all -> 0x05da }
            r6.append(r5)     // Catch:{ all -> 0x05da }
            java.lang.String r4 = r6.toString()     // Catch:{ all -> 0x05da }
            goto L_0x05a9
        L_0x056c:
            if (r12 == 0) goto L_0x05a9
            com.alibaba.fastjson.util.FieldInfo r5 = r12.fieldInfo     // Catch:{ all -> 0x05da }
            if (r5 == 0) goto L_0x05a9
            com.alibaba.fastjson.util.FieldInfo r5 = r12.fieldInfo     // Catch:{ all -> 0x05da }
            java.lang.reflect.Method r6 = r5.method     // Catch:{ all -> 0x05da }
            if (r6 == 0) goto L_0x0593
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x05da }
            r1.<init>()     // Catch:{ all -> 0x05da }
            r1.append(r4)     // Catch:{ all -> 0x05da }
            java.lang.String r4 = ", method : "
            r1.append(r4)     // Catch:{ all -> 0x05da }
            java.lang.reflect.Method r4 = r5.method     // Catch:{ all -> 0x05da }
            java.lang.String r4 = r4.getName()     // Catch:{ all -> 0x05da }
            r1.append(r4)     // Catch:{ all -> 0x05da }
            java.lang.String r4 = r1.toString()     // Catch:{ all -> 0x05da }
            goto L_0x05a9
        L_0x0593:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x05da }
            r5.<init>()     // Catch:{ all -> 0x05da }
            r5.append(r4)     // Catch:{ all -> 0x05da }
            r5.append(r1)     // Catch:{ all -> 0x05da }
            com.alibaba.fastjson.util.FieldInfo r1 = r12.fieldInfo     // Catch:{ all -> 0x05da }
            java.lang.String r1 = r1.name     // Catch:{ all -> 0x05da }
            r5.append(r1)     // Catch:{ all -> 0x05da }
            java.lang.String r4 = r5.toString()     // Catch:{ all -> 0x05da }
        L_0x05a9:
            java.lang.String r1 = r3.getMessage()     // Catch:{ all -> 0x05da }
            if (r1 == 0) goto L_0x05c7
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x05da }
            r1.<init>()     // Catch:{ all -> 0x05da }
            r1.append(r4)     // Catch:{ all -> 0x05da }
            java.lang.String r4 = ", "
            r1.append(r4)     // Catch:{ all -> 0x05da }
            java.lang.String r4 = r3.getMessage()     // Catch:{ all -> 0x05da }
            r1.append(r4)     // Catch:{ all -> 0x05da }
            java.lang.String r4 = r1.toString()     // Catch:{ all -> 0x05da }
        L_0x05c7:
            boolean r1 = r3 instanceof java.lang.reflect.InvocationTargetException     // Catch:{ all -> 0x05da }
            if (r1 == 0) goto L_0x05cf
            java.lang.Throwable r17 = r3.getCause()     // Catch:{ all -> 0x05da }
        L_0x05cf:
            if (r17 != 0) goto L_0x05d2
            goto L_0x05d4
        L_0x05d2:
            r3 = r17
        L_0x05d4:
            com.alibaba.fastjson.JSONException r1 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x05da }
            r1.<init>(r4, r3)     // Catch:{ all -> 0x05da }
            throw r1     // Catch:{ all -> 0x05da }
        L_0x05da:
            r0 = move-exception
            goto L_0x052a
        L_0x05dd:
            r9.context = r2
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.serializer.JavaBeanSerializer.write(com.alibaba.fastjson.serializer.JSONSerializer, java.lang.Object, java.lang.Object, java.lang.reflect.Type, int, boolean):void");
    }

    /* access modifiers changed from: protected */
    public void writeClassName(JSONSerializer jSONSerializer, String str, Object obj) {
        if (str == null) {
            str = jSONSerializer.config.typeKey;
        }
        jSONSerializer.out.writeFieldName(str, false);
        String str2 = this.beanInfo.typeName;
        if (str2 == null) {
            Class cls = obj.getClass();
            if (TypeUtils.isProxy(cls)) {
                cls = cls.getSuperclass();
            }
            str2 = cls.getName();
        }
        jSONSerializer.write(str2);
    }

    public boolean writeReference(JSONSerializer jSONSerializer, Object obj, int i) {
        SerialContext serialContext = jSONSerializer.context;
        int i2 = SerializerFeature.DisableCircularReferenceDetect.mask;
        if (serialContext == null || (serialContext.features & i2) != 0 || (i & i2) != 0 || jSONSerializer.references == null || !jSONSerializer.references.containsKey(obj)) {
            return false;
        }
        jSONSerializer.writeReference(obj);
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean isWriteAsArray(JSONSerializer jSONSerializer) {
        return isWriteAsArray(jSONSerializer, 0);
    }

    /* access modifiers changed from: protected */
    public boolean isWriteAsArray(JSONSerializer jSONSerializer, int i) {
        int i2 = SerializerFeature.BeanToArray.mask;
        return ((this.beanInfo.features & i2) == 0 && !jSONSerializer.out.beanToArray && (i & i2) == 0) ? false : true;
    }

    public Object getFieldValue(Object obj, String str) {
        FieldSerializer fieldSerializer = getFieldSerializer(str);
        if (fieldSerializer != null) {
            try {
                return fieldSerializer.getPropertyValue(obj);
            } catch (InvocationTargetException e) {
                throw new JSONException("getFieldValue error." + str, e);
            } catch (IllegalAccessException e2) {
                throw new JSONException("getFieldValue error." + str, e2);
            }
        } else {
            throw new JSONException("field not found. " + str);
        }
    }

    public Object getFieldValue(Object obj, String str, long j, boolean z) {
        FieldSerializer fieldSerializer = getFieldSerializer(j);
        if (fieldSerializer != null) {
            try {
                return fieldSerializer.getPropertyValue(obj);
            } catch (InvocationTargetException e) {
                throw new JSONException("getFieldValue error." + str, e);
            } catch (IllegalAccessException e2) {
                throw new JSONException("getFieldValue error." + str, e2);
            }
        } else if (!z) {
            return null;
        } else {
            throw new JSONException("field not found. " + str);
        }
    }

    public FieldSerializer getFieldSerializer(String str) {
        if (str == null) {
            return null;
        }
        int i = 0;
        int length = this.sortedGetters.length - 1;
        while (i <= length) {
            int i2 = (i + length) >>> 1;
            int compareTo = this.sortedGetters[i2].fieldInfo.name.compareTo(str);
            if (compareTo < 0) {
                i = i2 + 1;
            } else if (compareTo <= 0) {
                return this.sortedGetters[i2];
            } else {
                length = i2 - 1;
            }
        }
        return null;
    }

    public FieldSerializer getFieldSerializer(long j) {
        PropertyNamingStrategy[] propertyNamingStrategyArr;
        int binarySearch;
        if (this.hashArray == null) {
            propertyNamingStrategyArr = PropertyNamingStrategy.values();
            long[] jArr = new long[(this.sortedGetters.length * propertyNamingStrategyArr.length)];
            int i = 0;
            int i2 = 0;
            while (true) {
                FieldSerializer[] fieldSerializerArr = this.sortedGetters;
                if (i >= fieldSerializerArr.length) {
                    break;
                }
                String str = fieldSerializerArr[i].fieldInfo.name;
                jArr[i2] = TypeUtils.fnv1a_64(str);
                i2++;
                for (PropertyNamingStrategy translate : propertyNamingStrategyArr) {
                    String translate2 = translate.translate(str);
                    if (!str.equals(translate2)) {
                        jArr[i2] = TypeUtils.fnv1a_64(translate2);
                        i2++;
                    }
                }
                i++;
            }
            Arrays.sort(jArr, 0, i2);
            this.hashArray = new long[i2];
            System.arraycopy(jArr, 0, this.hashArray, 0, i2);
        } else {
            propertyNamingStrategyArr = null;
        }
        int binarySearch2 = Arrays.binarySearch(this.hashArray, j);
        if (binarySearch2 < 0) {
            return null;
        }
        if (this.hashArrayMapping == null) {
            if (propertyNamingStrategyArr == null) {
                propertyNamingStrategyArr = PropertyNamingStrategy.values();
            }
            short[] sArr = new short[this.hashArray.length];
            Arrays.fill(sArr, -1);
            int i3 = 0;
            while (true) {
                FieldSerializer[] fieldSerializerArr2 = this.sortedGetters;
                if (i3 >= fieldSerializerArr2.length) {
                    break;
                }
                String str2 = fieldSerializerArr2[i3].fieldInfo.name;
                int binarySearch3 = Arrays.binarySearch(this.hashArray, TypeUtils.fnv1a_64(str2));
                if (binarySearch3 >= 0) {
                    sArr[binarySearch3] = (short) i3;
                }
                for (PropertyNamingStrategy translate3 : propertyNamingStrategyArr) {
                    String translate4 = translate3.translate(str2);
                    if (!str2.equals(translate4) && (binarySearch = Arrays.binarySearch(this.hashArray, TypeUtils.fnv1a_64(translate4))) >= 0) {
                        sArr[binarySearch] = (short) i3;
                    }
                }
                i3++;
            }
            this.hashArrayMapping = sArr;
        }
        short s = this.hashArrayMapping[binarySearch2];
        if (s != -1) {
            return this.sortedGetters[s];
        }
        return null;
    }

    public List<Object> getFieldValues(Object obj) throws Exception {
        ArrayList arrayList = new ArrayList(this.sortedGetters.length);
        for (FieldSerializer propertyValue : this.sortedGetters) {
            arrayList.add(propertyValue.getPropertyValue(obj));
        }
        return arrayList;
    }

    public List<Object> getObjectFieldValues(Object obj) throws Exception {
        ArrayList arrayList = new ArrayList(this.sortedGetters.length);
        for (FieldSerializer fieldSerializer : this.sortedGetters) {
            Class<?> cls = fieldSerializer.fieldInfo.fieldClass;
            if (!cls.isPrimitive() && !cls.getName().startsWith("java.lang.")) {
                arrayList.add(fieldSerializer.getPropertyValue(obj));
            }
        }
        return arrayList;
    }

    public int getSize(Object obj) throws Exception {
        int i = 0;
        for (FieldSerializer propertyValueDirect : this.sortedGetters) {
            if (propertyValueDirect.getPropertyValueDirect(obj) != null) {
                i++;
            }
        }
        return i;
    }

    public Set<String> getFieldNames(Object obj) throws Exception {
        HashSet hashSet = new HashSet();
        for (FieldSerializer fieldSerializer : this.sortedGetters) {
            if (fieldSerializer.getPropertyValueDirect(obj) != null) {
                hashSet.add(fieldSerializer.fieldInfo.name);
            }
        }
        return hashSet;
    }

    public Map<String, Object> getFieldValuesMap(Object obj) throws Exception {
        LinkedHashMap linkedHashMap = new LinkedHashMap(this.sortedGetters.length);
        for (FieldSerializer fieldSerializer : this.sortedGetters) {
            boolean isEnabled = SerializerFeature.isEnabled(fieldSerializer.features, SerializerFeature.SkipTransientField);
            FieldInfo fieldInfo = fieldSerializer.fieldInfo;
            if (!isEnabled || fieldInfo == null || !fieldInfo.fieldTransient) {
                if (fieldSerializer.fieldInfo.unwrapped) {
                    Object json = JSON.toJSON(fieldSerializer.getPropertyValue(obj));
                    if (json instanceof Map) {
                        linkedHashMap.putAll((Map) json);
                    } else {
                        linkedHashMap.put(fieldSerializer.fieldInfo.name, fieldSerializer.getPropertyValue(obj));
                    }
                } else {
                    linkedHashMap.put(fieldSerializer.fieldInfo.name, fieldSerializer.getPropertyValue(obj));
                }
            }
        }
        return linkedHashMap;
    }

    /* access modifiers changed from: protected */
    public BeanContext getBeanContext(int i) {
        return this.sortedGetters[i].fieldContext;
    }

    /* access modifiers changed from: protected */
    public Type getFieldType(int i) {
        return this.sortedGetters[i].fieldInfo.fieldType;
    }

    /* access modifiers changed from: protected */
    public char writeBefore(JSONSerializer jSONSerializer, Object obj, char c) {
        if (jSONSerializer.beforeFilters != null) {
            for (BeforeFilter writeBefore : jSONSerializer.beforeFilters) {
                c = writeBefore.writeBefore(jSONSerializer, obj, c);
            }
        }
        if (this.beforeFilters != null) {
            for (BeforeFilter writeBefore2 : this.beforeFilters) {
                c = writeBefore2.writeBefore(jSONSerializer, obj, c);
            }
        }
        return c;
    }

    /* access modifiers changed from: protected */
    public char writeAfter(JSONSerializer jSONSerializer, Object obj, char c) {
        if (jSONSerializer.afterFilters != null) {
            for (AfterFilter writeAfter : jSONSerializer.afterFilters) {
                c = writeAfter.writeAfter(jSONSerializer, obj, c);
            }
        }
        if (this.afterFilters != null) {
            for (AfterFilter writeAfter2 : this.afterFilters) {
                c = writeAfter2.writeAfter(jSONSerializer, obj, c);
            }
        }
        return c;
    }

    /* access modifiers changed from: protected */
    public boolean applyLabel(JSONSerializer jSONSerializer, String str) {
        if (jSONSerializer.labelFilters != null) {
            for (LabelFilter apply : jSONSerializer.labelFilters) {
                if (!apply.apply(str)) {
                    return false;
                }
            }
        }
        if (this.labelFilters == null) {
            return true;
        }
        for (LabelFilter apply2 : this.labelFilters) {
            if (!apply2.apply(str)) {
                return false;
            }
        }
        return true;
    }
}
