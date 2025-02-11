package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import java.io.IOException;
import java.lang.reflect.Type;
import okhttp3.HttpUrl;

public class ObjectArrayCodec implements ObjectSerializer, ObjectDeserializer {
    public static final ObjectArrayCodec instance = new ObjectArrayCodec();

    public int getFastMatchToken() {
        return 14;
    }

    public final void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type, int i) throws IOException {
        JSONSerializer jSONSerializer2 = jSONSerializer;
        Object obj3 = obj;
        SerializeWriter serializeWriter = jSONSerializer2.out;
        Object[] objArr = (Object[]) obj3;
        if (obj3 == null) {
            serializeWriter.writeNull(SerializerFeature.WriteNullListAsEmpty);
            return;
        }
        int length = objArr.length;
        int i2 = length - 1;
        if (i2 == -1) {
            serializeWriter.append((CharSequence) HttpUrl.PATH_SEGMENT_ENCODE_SET_URI);
            return;
        }
        SerialContext serialContext = jSONSerializer2.context;
        jSONSerializer2.setContext(serialContext, obj3, obj2, 0);
        try {
            serializeWriter.append('[');
            if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                jSONSerializer.incrementIndent();
                jSONSerializer.println();
                for (int i3 = 0; i3 < length; i3++) {
                    if (i3 != 0) {
                        serializeWriter.write(44);
                        jSONSerializer.println();
                    }
                    jSONSerializer2.write(objArr[i3]);
                }
                jSONSerializer.decrementIdent();
                jSONSerializer.println();
                serializeWriter.write(93);
                return;
            }
            Class<?> cls = null;
            ObjectSerializer objectSerializer = null;
            for (int i4 = 0; i4 < i2; i4++) {
                Object obj4 = objArr[i4];
                if (obj4 == null) {
                    serializeWriter.append((CharSequence) "null,");
                } else {
                    if (jSONSerializer2.containsReference(obj4)) {
                        jSONSerializer2.writeReference(obj4);
                    } else {
                        Class<?> cls2 = obj4.getClass();
                        if (cls2 == cls) {
                            objectSerializer.write(jSONSerializer, obj4, Integer.valueOf(i4), (Type) null, 0);
                        } else {
                            objectSerializer = jSONSerializer2.getObjectWriter(cls2);
                            objectSerializer.write(jSONSerializer, obj4, Integer.valueOf(i4), (Type) null, 0);
                            cls = cls2;
                        }
                    }
                    serializeWriter.append(',');
                }
            }
            Object obj5 = objArr[i2];
            if (obj5 == null) {
                serializeWriter.append((CharSequence) "null]");
            } else {
                if (jSONSerializer2.containsReference(obj5)) {
                    jSONSerializer2.writeReference(obj5);
                } else {
                    jSONSerializer2.writeWithFieldName(obj5, Integer.valueOf(i2));
                }
                serializeWriter.append(']');
            }
            jSONSerializer2.context = serialContext;
        } finally {
            jSONSerializer2.context = serialContext;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v1, resolved type: java.lang.reflect.Type} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v1, resolved type: java.lang.Class} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v3, resolved type: java.lang.reflect.Type} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v2, resolved type: java.lang.Class} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v5, resolved type: java.lang.reflect.Type} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v12, resolved type: java.lang.Class<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v13, resolved type: java.lang.Class<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v14, resolved type: java.lang.Class<java.lang.Object>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v15, resolved type: java.lang.Class} */
    /* JADX WARNING: type inference failed for: r8v1, types: [T, byte[]] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public <T> T deserialze(com.alibaba.fastjson.parser.DefaultJSONParser r8, java.lang.reflect.Type r9, java.lang.Object r10) {
        /*
            r7 = this;
            com.alibaba.fastjson.parser.JSONLexer r0 = r8.lexer
            int r1 = r0.token()
            r2 = 16
            r3 = 0
            r4 = 8
            if (r1 != r4) goto L_0x0011
            r0.nextToken(r2)
            return r3
        L_0x0011:
            r4 = 4
            if (r1 == r4) goto L_0x0090
            r4 = 26
            if (r1 != r4) goto L_0x001a
            goto L_0x0090
        L_0x001a:
            boolean r0 = r9 instanceof java.lang.reflect.GenericArrayType
            if (r0 == 0) goto L_0x007c
            java.lang.reflect.GenericArrayType r9 = (java.lang.reflect.GenericArrayType) r9
            java.lang.reflect.Type r9 = r9.getGenericComponentType()
            boolean r0 = r9 instanceof java.lang.reflect.TypeVariable
            if (r0 == 0) goto L_0x0077
            r0 = r9
            java.lang.reflect.TypeVariable r0 = (java.lang.reflect.TypeVariable) r0
            com.alibaba.fastjson.parser.ParseContext r1 = r8.getContext()
            java.lang.reflect.Type r1 = r1.type
            boolean r2 = r1 instanceof java.lang.reflect.ParameterizedType
            r4 = 0
            if (r2 == 0) goto L_0x006c
            java.lang.reflect.ParameterizedType r1 = (java.lang.reflect.ParameterizedType) r1
            java.lang.reflect.Type r2 = r1.getRawType()
            boolean r5 = r2 instanceof java.lang.Class
            if (r5 == 0) goto L_0x0062
            java.lang.Class r2 = (java.lang.Class) r2
            java.lang.reflect.TypeVariable[] r2 = r2.getTypeParameters()
        L_0x0046:
            int r5 = r2.length
            if (r4 >= r5) goto L_0x0062
            r5 = r2[r4]
            java.lang.String r5 = r5.getName()
            java.lang.String r6 = r0.getName()
            boolean r5 = r5.equals(r6)
            if (r5 == 0) goto L_0x005f
            java.lang.reflect.Type[] r3 = r1.getActualTypeArguments()
            r3 = r3[r4]
        L_0x005f:
            int r4 = r4 + 1
            goto L_0x0046
        L_0x0062:
            boolean r0 = r3 instanceof java.lang.Class
            if (r0 == 0) goto L_0x0069
            java.lang.Class r3 = (java.lang.Class) r3
            goto L_0x0083
        L_0x0069:
            java.lang.Class<java.lang.Object> r3 = java.lang.Object.class
            goto L_0x0083
        L_0x006c:
            java.lang.reflect.Type[] r0 = r0.getBounds()
            r0 = r0[r4]
            java.lang.Class r3 = com.alibaba.fastjson.util.TypeUtils.getClass(r0)
            goto L_0x0083
        L_0x0077:
            java.lang.Class r3 = com.alibaba.fastjson.util.TypeUtils.getClass(r9)
            goto L_0x0083
        L_0x007c:
            java.lang.Class r9 = (java.lang.Class) r9
            java.lang.Class r9 = r9.getComponentType()
            r3 = r9
        L_0x0083:
            com.alibaba.fastjson.JSONArray r0 = new com.alibaba.fastjson.JSONArray
            r0.<init>()
            r8.parseArray(r9, r0, r10)
            java.lang.Object r8 = r7.toObjectArray(r8, r3, r0)
            return r8
        L_0x0090:
            byte[] r8 = r0.bytesValue()
            r0.nextToken(r2)
            int r10 = r8.length
            if (r10 != 0) goto L_0x009f
            java.lang.Class<byte[]> r10 = byte[].class
            if (r9 == r10) goto L_0x009f
            return r3
        L_0x009f:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.serializer.ObjectArrayCodec.deserialze(com.alibaba.fastjson.parser.DefaultJSONParser, java.lang.reflect.Type, java.lang.Object):java.lang.Object");
    }

    /* JADX WARNING: type inference failed for: r13v0, types: [java.lang.reflect.Type, java.lang.Class<?>, java.lang.Class] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0057  */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private <T> T toObjectArray(com.alibaba.fastjson.parser.DefaultJSONParser r12, java.lang.Class<?> r13, com.alibaba.fastjson.JSONArray r14) {
        /*
            r11 = this;
            r0 = 0
            if (r14 != 0) goto L_0x0004
            return r0
        L_0x0004:
            int r1 = r14.size()
            java.lang.Object r2 = java.lang.reflect.Array.newInstance(r13, r1)
            r3 = 0
            r4 = 0
        L_0x000e:
            if (r4 >= r1) goto L_0x0065
            java.lang.Object r5 = r14.get(r4)
            if (r5 != r14) goto L_0x001a
            java.lang.reflect.Array.set(r2, r4, r2)
            goto L_0x0062
        L_0x001a:
            boolean r6 = r13.isArray()
            if (r6 == 0) goto L_0x0031
            boolean r6 = r13.isInstance(r5)
            if (r6 == 0) goto L_0x0027
            goto L_0x002d
        L_0x0027:
            com.alibaba.fastjson.JSONArray r5 = (com.alibaba.fastjson.JSONArray) r5
            java.lang.Object r5 = r11.toObjectArray(r12, r13, r5)
        L_0x002d:
            java.lang.reflect.Array.set(r2, r4, r5)
            goto L_0x0062
        L_0x0031:
            boolean r6 = r5 instanceof com.alibaba.fastjson.JSONArray
            if (r6 == 0) goto L_0x0054
            r6 = r5
            com.alibaba.fastjson.JSONArray r6 = (com.alibaba.fastjson.JSONArray) r6
            int r7 = r6.size()
            r8 = 0
            r9 = 0
        L_0x003e:
            if (r8 >= r7) goto L_0x004d
            java.lang.Object r10 = r6.get(r8)
            if (r10 != r14) goto L_0x004a
            r6.set(r4, r2)
            r9 = 1
        L_0x004a:
            int r8 = r8 + 1
            goto L_0x003e
        L_0x004d:
            if (r9 == 0) goto L_0x0054
            java.lang.Object[] r6 = r6.toArray()
            goto L_0x0055
        L_0x0054:
            r6 = r0
        L_0x0055:
            if (r6 != 0) goto L_0x005f
            com.alibaba.fastjson.parser.ParserConfig r6 = r12.getConfig()
            java.lang.Object r6 = com.alibaba.fastjson.util.TypeUtils.cast((java.lang.Object) r5, r13, (com.alibaba.fastjson.parser.ParserConfig) r6)
        L_0x005f:
            java.lang.reflect.Array.set(r2, r4, r6)
        L_0x0062:
            int r4 = r4 + 1
            goto L_0x000e
        L_0x0065:
            r14.setRelatedArray(r2)
            r14.setComponentType(r13)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.serializer.ObjectArrayCodec.toObjectArray(com.alibaba.fastjson.parser.DefaultJSONParser, java.lang.Class, com.alibaba.fastjson.JSONArray):java.lang.Object");
    }
}
