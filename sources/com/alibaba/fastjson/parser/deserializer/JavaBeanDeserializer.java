package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.JSONLexerBase;
import com.alibaba.fastjson.parser.ParseContext;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.util.FieldInfo;
import com.alibaba.fastjson.util.JavaBeanInfo;
import com.alibaba.fastjson.util.TypeUtils;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

public class JavaBeanDeserializer implements ObjectDeserializer {
    private final Map<String, FieldDeserializer> alterNameFieldDeserializers;
    public final JavaBeanInfo beanInfo;
    protected final Class<?> clazz;
    private ConcurrentMap<String, Object> extraFieldDeserializers;
    private Map<String, FieldDeserializer> fieldDeserializerMap;
    private final FieldDeserializer[] fieldDeserializers;
    private transient long[] hashArray;
    private transient short[] hashArrayMapping;
    private transient long[] smartMatchHashArray;
    private transient short[] smartMatchHashArrayMapping;
    protected final FieldDeserializer[] sortedFieldDeserializers;

    public int getFastMatchToken() {
        return 12;
    }

    public JavaBeanDeserializer(ParserConfig parserConfig, Class<?> cls) {
        this(parserConfig, cls, cls);
    }

    public JavaBeanDeserializer(ParserConfig parserConfig, Class<?> cls, Type type) {
        this(parserConfig, JavaBeanInfo.build(cls, type, parserConfig.propertyNamingStrategy, parserConfig.fieldBased, parserConfig.compatibleWithJavaBean, parserConfig.isJacksonCompatible()));
    }

    public JavaBeanDeserializer(ParserConfig parserConfig, JavaBeanInfo javaBeanInfo) {
        this.clazz = javaBeanInfo.clazz;
        this.beanInfo = javaBeanInfo;
        this.sortedFieldDeserializers = new FieldDeserializer[javaBeanInfo.sortedFields.length];
        int length = javaBeanInfo.sortedFields.length;
        HashMap hashMap = null;
        for (int i = 0; i < length; i++) {
            FieldInfo fieldInfo = javaBeanInfo.sortedFields[i];
            FieldDeserializer createFieldDeserializer = parserConfig.createFieldDeserializer(parserConfig, javaBeanInfo, fieldInfo);
            this.sortedFieldDeserializers[i] = createFieldDeserializer;
            if (length > 128) {
                if (this.fieldDeserializerMap == null) {
                    this.fieldDeserializerMap = new HashMap();
                }
                this.fieldDeserializerMap.put(fieldInfo.name, createFieldDeserializer);
            }
            for (String str : fieldInfo.alternateNames) {
                if (hashMap == null) {
                    hashMap = new HashMap();
                }
                hashMap.put(str, createFieldDeserializer);
            }
        }
        this.alterNameFieldDeserializers = hashMap;
        this.fieldDeserializers = new FieldDeserializer[javaBeanInfo.fields.length];
        int length2 = javaBeanInfo.fields.length;
        for (int i2 = 0; i2 < length2; i2++) {
            this.fieldDeserializers[i2] = getFieldDeserializer(javaBeanInfo.fields[i2].name);
        }
    }

    public FieldDeserializer getFieldDeserializer(String str) {
        return getFieldDeserializer(str, (int[]) null);
    }

    public FieldDeserializer getFieldDeserializer(String str, int[] iArr) {
        FieldDeserializer fieldDeserializer;
        if (str == null) {
            return null;
        }
        Map<String, FieldDeserializer> map = this.fieldDeserializerMap;
        if (map != null && (fieldDeserializer = map.get(str)) != null) {
            return fieldDeserializer;
        }
        int i = 0;
        int length = this.sortedFieldDeserializers.length - 1;
        while (i <= length) {
            int i2 = (i + length) >>> 1;
            int compareTo = this.sortedFieldDeserializers[i2].fieldInfo.name.compareTo(str);
            if (compareTo < 0) {
                i = i2 + 1;
            } else if (compareTo > 0) {
                length = i2 - 1;
            } else if (isSetFlag(i2, iArr)) {
                return null;
            } else {
                return this.sortedFieldDeserializers[i2];
            }
        }
        Map<String, FieldDeserializer> map2 = this.alterNameFieldDeserializers;
        if (map2 != null) {
            return map2.get(str);
        }
        return null;
    }

    public FieldDeserializer getFieldDeserializer(long j) {
        int i = 0;
        if (this.hashArray == null) {
            long[] jArr = new long[this.sortedFieldDeserializers.length];
            int i2 = 0;
            while (true) {
                FieldDeserializer[] fieldDeserializerArr = this.sortedFieldDeserializers;
                if (i2 >= fieldDeserializerArr.length) {
                    break;
                }
                jArr[i2] = TypeUtils.fnv1a_64(fieldDeserializerArr[i2].fieldInfo.name);
                i2++;
            }
            Arrays.sort(jArr);
            this.hashArray = jArr;
        }
        int binarySearch = Arrays.binarySearch(this.hashArray, j);
        if (binarySearch < 0) {
            return null;
        }
        if (this.hashArrayMapping == null) {
            short[] sArr = new short[this.hashArray.length];
            Arrays.fill(sArr, -1);
            while (true) {
                FieldDeserializer[] fieldDeserializerArr2 = this.sortedFieldDeserializers;
                if (i >= fieldDeserializerArr2.length) {
                    break;
                }
                int binarySearch2 = Arrays.binarySearch(this.hashArray, TypeUtils.fnv1a_64(fieldDeserializerArr2[i].fieldInfo.name));
                if (binarySearch2 >= 0) {
                    sArr[binarySearch2] = (short) i;
                }
                i++;
            }
            this.hashArrayMapping = sArr;
        }
        short s = this.hashArrayMapping[binarySearch];
        if (s != -1) {
            return this.sortedFieldDeserializers[s];
        }
        return null;
    }

    static boolean isSetFlag(int i, int[] iArr) {
        if (iArr == null) {
            return false;
        }
        int i2 = i / 32;
        int i3 = i % 32;
        if (i2 < iArr.length) {
            if (((1 << i3) & iArr[i2]) != 0) {
                return true;
            }
        }
        return false;
    }

    public Object createInstance(DefaultJSONParser defaultJSONParser, Type type) {
        Object obj;
        if (!(type instanceof Class) || !this.clazz.isInterface()) {
            Object obj2 = null;
            if (this.beanInfo.defaultConstructor == null && this.beanInfo.factoryMethod == null) {
                return null;
            }
            if (this.beanInfo.factoryMethod != null && this.beanInfo.defaultConstructorParameterSize > 0) {
                return null;
            }
            try {
                Constructor<?> constructor = this.beanInfo.defaultConstructor;
                if (this.beanInfo.defaultConstructorParameterSize != 0) {
                    ParseContext context = defaultJSONParser.getContext();
                    if (context != null) {
                        if (context.object != null) {
                            if (type instanceof Class) {
                                String name = ((Class) type).getName();
                                String substring = name.substring(0, name.lastIndexOf(36));
                                Object obj3 = context.object;
                                String name2 = obj3.getClass().getName();
                                if (!name2.equals(substring)) {
                                    ParseContext parseContext = context.parent;
                                    if (parseContext == null || parseContext.object == null || (!"java.util.ArrayList".equals(name2) && !"java.util.List".equals(name2) && !"java.util.Collection".equals(name2) && !"java.util.Map".equals(name2) && !"java.util.HashMap".equals(name2))) {
                                        obj2 = obj3;
                                    } else if (parseContext.object.getClass().getName().equals(substring)) {
                                        obj2 = parseContext.object;
                                    }
                                    obj3 = obj2;
                                }
                                if (obj3 == null || ((obj3 instanceof Collection) && ((Collection) obj3).isEmpty())) {
                                    throw new JSONException("can't create non-static inner class instance.");
                                }
                                obj = constructor.newInstance(new Object[]{obj3});
                            } else {
                                throw new JSONException("can't create non-static inner class instance.");
                            }
                        }
                    }
                    throw new JSONException("can't create non-static inner class instance.");
                } else if (constructor != null) {
                    obj = constructor.newInstance(new Object[0]);
                } else {
                    obj = this.beanInfo.factoryMethod.invoke((Object) null, new Object[0]);
                }
                if (defaultJSONParser != null && defaultJSONParser.lexer.isEnabled(Feature.InitStringFieldAsEmpty)) {
                    for (FieldInfo fieldInfo : this.beanInfo.fields) {
                        if (fieldInfo.fieldClass == String.class) {
                            try {
                                fieldInfo.set(obj, "");
                            } catch (Exception e) {
                                throw new JSONException("create instance error, class " + this.clazz.getName(), e);
                            }
                        }
                    }
                }
                return obj;
            } catch (JSONException e2) {
                throw e2;
            } catch (Exception e3) {
                throw new JSONException("create instance error, class " + this.clazz.getName(), e3);
            }
        } else {
            return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{(Class) type}, new JSONObject());
        }
    }

    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        return deserialze(defaultJSONParser, type, obj, 0);
    }

    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj, int i) {
        return deserialze(defaultJSONParser, type, obj, (Object) null, i, (int[]) null);
    }

    public <T> T deserialzeArrayMapping(DefaultJSONParser defaultJSONParser, Type type, Object obj, Object obj2) {
        Enum<?> enumR;
        JSONLexer jSONLexer = defaultJSONParser.lexer;
        if (jSONLexer.token() == 14) {
            String scanTypeName = jSONLexer.scanTypeName(defaultJSONParser.symbolTable);
            if (scanTypeName != null) {
                Object seeAlso = getSeeAlso(defaultJSONParser.getConfig(), this.beanInfo, scanTypeName);
                if (seeAlso == null) {
                    seeAlso = defaultJSONParser.getConfig().getDeserializer((Type) defaultJSONParser.getConfig().checkAutoType(scanTypeName, TypeUtils.getClass(type), jSONLexer.getFeatures()));
                }
                if (seeAlso instanceof JavaBeanDeserializer) {
                    return ((JavaBeanDeserializer) seeAlso).deserialzeArrayMapping(defaultJSONParser, type, obj, obj2);
                }
            }
            T createInstance = createInstance(defaultJSONParser, type);
            int i = 0;
            int length = this.sortedFieldDeserializers.length;
            while (true) {
                int i2 = 16;
                if (i >= length) {
                    break;
                }
                char c = i == length + -1 ? ']' : ',';
                FieldDeserializer fieldDeserializer = this.sortedFieldDeserializers[i];
                Class<?> cls = fieldDeserializer.fieldInfo.fieldClass;
                if (cls == Integer.TYPE) {
                    fieldDeserializer.setValue((Object) createInstance, jSONLexer.scanInt(c));
                } else if (cls == String.class) {
                    fieldDeserializer.setValue((Object) createInstance, jSONLexer.scanString(c));
                } else if (cls == Long.TYPE) {
                    fieldDeserializer.setValue((Object) createInstance, jSONLexer.scanLong(c));
                } else if (cls.isEnum()) {
                    char current = jSONLexer.getCurrent();
                    if (current == '\"' || current == 'n') {
                        enumR = jSONLexer.scanEnum(cls, defaultJSONParser.getSymbolTable(), c);
                    } else if (current < '0' || current > '9') {
                        enumR = scanEnum(jSONLexer, c);
                    } else {
                        enumR = ((EnumDeserializer) ((DefaultFieldDeserializer) fieldDeserializer).getFieldValueDeserilizer(defaultJSONParser.getConfig())).valueOf(jSONLexer.scanInt(c));
                    }
                    fieldDeserializer.setValue((Object) createInstance, (Object) enumR);
                } else if (cls == Boolean.TYPE) {
                    fieldDeserializer.setValue((Object) createInstance, jSONLexer.scanBoolean(c));
                } else if (cls == Float.TYPE) {
                    fieldDeserializer.setValue((Object) createInstance, (Object) Float.valueOf(jSONLexer.scanFloat(c)));
                } else if (cls == Double.TYPE) {
                    fieldDeserializer.setValue((Object) createInstance, (Object) Double.valueOf(jSONLexer.scanDouble(c)));
                } else if (cls == Date.class && jSONLexer.getCurrent() == '1') {
                    fieldDeserializer.setValue((Object) createInstance, (Object) new Date(jSONLexer.scanLong(c)));
                } else if (cls == BigDecimal.class) {
                    fieldDeserializer.setValue((Object) createInstance, (Object) jSONLexer.scanDecimal(c));
                } else {
                    jSONLexer.nextToken(14);
                    fieldDeserializer.setValue((Object) createInstance, defaultJSONParser.parseObject(fieldDeserializer.fieldInfo.fieldType, (Object) fieldDeserializer.fieldInfo.name));
                    if (jSONLexer.token() == 15) {
                        break;
                    }
                    if (c == ']') {
                        i2 = 15;
                    }
                    check(jSONLexer, i2);
                }
                i++;
            }
            jSONLexer.nextToken(16);
            return createInstance;
        }
        throw new JSONException("error");
    }

    /* access modifiers changed from: protected */
    public void check(JSONLexer jSONLexer, int i) {
        if (jSONLexer.token() != i) {
            throw new JSONException("syntax error");
        }
    }

    /* access modifiers changed from: protected */
    public Enum<?> scanEnum(JSONLexer jSONLexer, char c) {
        throw new JSONException("illegal enum. " + jSONLexer.info());
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v1, resolved type: com.alibaba.fastjson.parser.ParseContext} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v0, resolved type: com.alibaba.fastjson.parser.ParseContext} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v1, resolved type: com.alibaba.fastjson.parser.ParseContext} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r37v3, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v2, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v3, resolved type: com.alibaba.fastjson.parser.ParseContext} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v8, resolved type: com.alibaba.fastjson.parser.ParseContext} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v19, resolved type: ?} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v20, resolved type: ?} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v28, resolved type: ?} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v21, resolved type: T} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v22, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r29v0, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v8, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v9, resolved type: com.alibaba.fastjson.parser.ParseContext} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v24, resolved type: com.alibaba.fastjson.parser.ParseContext} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v27, resolved type: com.alibaba.fastjson.parser.ParseContext} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r29v1, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r29v2, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r37v12, resolved type: com.alibaba.fastjson.parser.ParseContext} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v31, resolved type: com.alibaba.fastjson.parser.ParseContext} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r29v3, resolved type: com.alibaba.fastjson.parser.ParseContext} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r29v4, resolved type: com.alibaba.fastjson.parser.ParseContext} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r37v15, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r37v16, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r37v21, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v37, resolved type: com.alibaba.fastjson.parser.ParseContext} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v43, resolved type: com.alibaba.fastjson.parser.ParseContext} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v22, resolved type: com.alibaba.fastjson.parser.ParseContext} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v23, resolved type: com.alibaba.fastjson.parser.ParseContext} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v26, resolved type: com.alibaba.fastjson.parser.ParseContext} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v126, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v128, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v129, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v131, resolved type: java.lang.Class<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v132, resolved type: java.lang.Class<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v44, resolved type: com.alibaba.fastjson.parser.ParseContext} */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:102:0x012c, code lost:
        throw new com.alibaba.fastjson.JSONException(r1.getMessage(), r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0049, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x004a, code lost:
        r14 = r35;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:313:0x0391, code lost:
        if (r12.matchStat == -2) goto L_0x0393;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:339:0x03ef, code lost:
        r12.nextTokenWithColon(4);
        r1 = r12.token();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:340:0x03f7, code lost:
        if (r1 != 4) goto L_0x04ac;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:341:0x03f9, code lost:
        r1 = r12.stringVal();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:342:0x0403, code lost:
        if ("@".equals(r1) == false) goto L_0x0409;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:343:0x0405, code lost:
        r1 = r7.object;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:345:0x040f, code lost:
        if ("..".equals(r1) == false) goto L_0x0427;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:346:0x0411, code lost:
        r2 = r7.parent;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:347:0x0415, code lost:
        if (r2.object == null) goto L_0x041b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:348:0x0417, code lost:
        r1 = r2.object;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:349:0x041b, code lost:
        r9.addResolveTask(new com.alibaba.fastjson.parser.DefaultJSONParser.ResolveTask(r2, r1));
        r9.resolveStatus = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:351:0x042d, code lost:
        if ("$".equals(r1) == false) goto L_0x044a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:352:0x042f, code lost:
        r2 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:354:0x0432, code lost:
        if (r2.parent == null) goto L_0x0437;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:355:0x0434, code lost:
        r2 = r2.parent;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:357:0x0439, code lost:
        if (r2.object == null) goto L_0x043e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:358:0x043b, code lost:
        r1 = r2.object;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:359:0x043e, code lost:
        r9.addResolveTask(new com.alibaba.fastjson.parser.DefaultJSONParser.ResolveTask(r2, r1));
        r9.resolveStatus = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:361:0x0450, code lost:
        if (r1.indexOf(92) <= 0) goto L_0x0474;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:362:0x0452, code lost:
        r3 = new java.lang.StringBuilder();
        r4 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:364:0x045c, code lost:
        if (r4 >= r1.length()) goto L_0x0470;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:365:0x045e, code lost:
        r6 = r1.charAt(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:366:0x0462, code lost:
        if (r6 != '\\') goto L_0x046a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:367:0x0464, code lost:
        r4 = r4 + 1;
        r6 = r1.charAt(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:368:0x046a, code lost:
        r3.append(r6);
        r4 = r4 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:369:0x0470, code lost:
        r1 = r3.toString();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:370:0x0474, code lost:
        r2 = r9.resolveReference(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:371:0x0478, code lost:
        if (r2 == null) goto L_0x047c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:372:0x047a, code lost:
        r1 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:373:0x047c, code lost:
        r9.addResolveTask(new com.alibaba.fastjson.parser.DefaultJSONParser.ResolveTask(r7, r1));
        r9.resolveStatus = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:374:0x0487, code lost:
        r1 = r37;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:377:?, code lost:
        r12.nextToken(13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:378:0x0492, code lost:
        if (r12.token() != 13) goto L_0x04a4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:379:0x0494, code lost:
        r12.nextToken(16);
        r9.setContext(r7, r1, r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:380:0x049c, code lost:
        if (r5 == null) goto L_0x04a0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:381:0x049e, code lost:
        r5.object = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:382:0x04a0, code lost:
        r9.setContext(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:383:0x04a3, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:386:0x04ab, code lost:
        throw new com.alibaba.fastjson.JSONException("illegal ref");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:389:0x04c6, code lost:
        throw new com.alibaba.fastjson.JSONException("illegal ref, " + com.alibaba.fastjson.parser.JSONToken.name(r1));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:510:0x06a1, code lost:
        r1 = r18;
        r5 = r29;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:713:0x096d, code lost:
        throw new com.alibaba.fastjson.JSONException("syntax error, unexpect token " + com.alibaba.fastjson.parser.JSONToken.name(r12.token()));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x0121, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:99:0x0122, code lost:
        r1 = r0;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:14:0x0039, B:82:0x00e6] */
    /* JADX WARNING: Incorrect type for immutable var: ssa=int[], code=com.alibaba.fastjson.parser.ParseContext, for r37v0, types: [int[]] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:266:0x030d A[Catch:{ all -> 0x03a2, all -> 0x0570 }] */
    /* JADX WARNING: Removed duplicated region for block: B:324:0x03b6 A[Catch:{ all -> 0x03a2, all -> 0x0570 }] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0071 A[Catch:{ Exception -> 0x0121, all -> 0x0049 }] */
    /* JADX WARNING: Removed duplicated region for block: B:429:0x0573  */
    /* JADX WARNING: Removed duplicated region for block: B:431:0x057e A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:454:0x05c6  */
    /* JADX WARNING: Removed duplicated region for block: B:481:0x0629 A[Catch:{ all -> 0x063a }] */
    /* JADX WARNING: Removed duplicated region for block: B:506:0x0694 A[Catch:{ all -> 0x097a }] */
    /* JADX WARNING: Removed duplicated region for block: B:507:0x0698 A[Catch:{ all -> 0x097a }] */
    /* JADX WARNING: Removed duplicated region for block: B:634:0x082b A[SYNTHETIC, Splitter:B:634:0x082b] */
    /* JADX WARNING: Removed duplicated region for block: B:655:0x086a A[SYNTHETIC, Splitter:B:655:0x086a] */
    /* JADX WARNING: Removed duplicated region for block: B:726:0x0990  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public <T> T deserialze(com.alibaba.fastjson.parser.DefaultJSONParser r32, java.lang.reflect.Type r33, java.lang.Object r34, java.lang.Object r35, int r36, com.alibaba.fastjson.parser.ParseContext r37) {
        /*
            r31 = this;
            r8 = r31
            r9 = r32
            r10 = r33
            r11 = r34
            java.lang.Class<com.alibaba.fastjson.JSON> r1 = com.alibaba.fastjson.JSON.class
            if (r10 == r1) goto L_0x0996
            java.lang.Class<com.alibaba.fastjson.JSONObject> r1 = com.alibaba.fastjson.JSONObject.class
            if (r10 != r1) goto L_0x0012
            goto L_0x0996
        L_0x0012:
            com.alibaba.fastjson.parser.JSONLexer r1 = r9.lexer
            r12 = r1
            com.alibaba.fastjson.parser.JSONLexerBase r12 = (com.alibaba.fastjson.parser.JSONLexerBase) r12
            com.alibaba.fastjson.parser.ParserConfig r13 = r32.getConfig()
            int r1 = r12.token()
            r2 = 8
            r14 = 16
            r15 = 0
            if (r1 != r2) goto L_0x002a
            r12.nextToken(r14)
            return r15
        L_0x002a:
            com.alibaba.fastjson.parser.ParseContext r2 = r32.getContext()
            if (r35 == 0) goto L_0x0034
            if (r2 == 0) goto L_0x0034
            com.alibaba.fastjson.parser.ParseContext r2 = r2.parent
        L_0x0034:
            r7 = r2
            r6 = 13
            if (r1 != r6) goto L_0x0050
            r12.nextToken(r14)     // Catch:{ all -> 0x0049 }
            if (r35 != 0) goto L_0x0043
            java.lang.Object r1 = r31.createInstance((com.alibaba.fastjson.parser.DefaultJSONParser) r32, (java.lang.reflect.Type) r33)     // Catch:{ all -> 0x0049 }
            goto L_0x0045
        L_0x0043:
            r1 = r35
        L_0x0045:
            r9.setContext(r7)
            return r1
        L_0x0049:
            r0 = move-exception
            r14 = r35
        L_0x004c:
            r1 = r0
        L_0x004d:
            r3 = r7
            goto L_0x098e
        L_0x0050:
            r2 = 14
            if (r1 != r2) goto L_0x0079
            com.alibaba.fastjson.parser.Feature r3 = com.alibaba.fastjson.parser.Feature.SupportArrayToBean     // Catch:{ all -> 0x0049 }
            int r3 = r3.mask     // Catch:{ all -> 0x0049 }
            com.alibaba.fastjson.util.JavaBeanInfo r6 = r8.beanInfo     // Catch:{ all -> 0x0049 }
            int r6 = r6.parserFeatures     // Catch:{ all -> 0x0049 }
            r6 = r6 & r3
            if (r6 != 0) goto L_0x006e
            com.alibaba.fastjson.parser.Feature r6 = com.alibaba.fastjson.parser.Feature.SupportArrayToBean     // Catch:{ all -> 0x0049 }
            boolean r6 = r12.isEnabled((com.alibaba.fastjson.parser.Feature) r6)     // Catch:{ all -> 0x0049 }
            if (r6 != 0) goto L_0x006e
            r3 = r36 & r3
            if (r3 == 0) goto L_0x006c
            goto L_0x006e
        L_0x006c:
            r3 = 0
            goto L_0x006f
        L_0x006e:
            r3 = 1
        L_0x006f:
            if (r3 == 0) goto L_0x0079
            java.lang.Object r1 = r31.deserialzeArrayMapping(r32, r33, r34, r35)     // Catch:{ all -> 0x0049 }
            r9.setContext(r7)
            return r1
        L_0x0079:
            r3 = 12
            r6 = 4
            if (r1 == r3) goto L_0x016a
            if (r1 == r14) goto L_0x016a
            boolean r3 = r12.isBlankInput()     // Catch:{ all -> 0x0049 }
            if (r3 == 0) goto L_0x008a
            r9.setContext(r7)
            return r15
        L_0x008a:
            if (r1 != r6) goto L_0x00c4
            java.lang.String r3 = r12.stringVal()     // Catch:{ all -> 0x0049 }
            int r10 = r3.length()     // Catch:{ all -> 0x0049 }
            if (r10 != 0) goto L_0x009d
            r12.nextToken()     // Catch:{ all -> 0x0049 }
            r9.setContext(r7)
            return r15
        L_0x009d:
            com.alibaba.fastjson.util.JavaBeanInfo r10 = r8.beanInfo     // Catch:{ all -> 0x0049 }
            com.alibaba.fastjson.annotation.JSONType r10 = r10.jsonType     // Catch:{ all -> 0x0049 }
            if (r10 == 0) goto L_0x00c4
            com.alibaba.fastjson.util.JavaBeanInfo r10 = r8.beanInfo     // Catch:{ all -> 0x0049 }
            com.alibaba.fastjson.annotation.JSONType r10 = r10.jsonType     // Catch:{ all -> 0x0049 }
            java.lang.Class[] r10 = r10.seeAlso()     // Catch:{ all -> 0x0049 }
            int r14 = r10.length     // Catch:{ all -> 0x0049 }
            r6 = 0
        L_0x00ad:
            if (r6 >= r14) goto L_0x00c4
            r5 = r10[r6]     // Catch:{ all -> 0x0049 }
            java.lang.Class<java.lang.Enum> r4 = java.lang.Enum.class
            boolean r4 = r4.isAssignableFrom(r5)     // Catch:{ all -> 0x0049 }
            if (r4 == 0) goto L_0x00c1
            java.lang.Enum r1 = java.lang.Enum.valueOf(r5, r3)     // Catch:{ IllegalArgumentException -> 0x00c1 }
            r9.setContext(r7)
            return r1
        L_0x00c1:
            int r6 = r6 + 1
            goto L_0x00ad
        L_0x00c4:
            if (r1 != r2) goto L_0x00d8
            char r2 = r12.getCurrent()     // Catch:{ all -> 0x0049 }
            r3 = 93
            if (r2 != r3) goto L_0x00d8
            r12.next()     // Catch:{ all -> 0x0049 }
            r12.nextToken()     // Catch:{ all -> 0x0049 }
            r9.setContext(r7)
            return r15
        L_0x00d8:
            com.alibaba.fastjson.util.JavaBeanInfo r2 = r8.beanInfo     // Catch:{ all -> 0x0049 }
            java.lang.reflect.Method r2 = r2.factoryMethod     // Catch:{ all -> 0x0049 }
            if (r2 == 0) goto L_0x012d
            com.alibaba.fastjson.util.JavaBeanInfo r2 = r8.beanInfo     // Catch:{ all -> 0x0049 }
            com.alibaba.fastjson.util.FieldInfo[] r2 = r2.fields     // Catch:{ all -> 0x0049 }
            int r2 = r2.length     // Catch:{ all -> 0x0049 }
            r3 = 1
            if (r2 != r3) goto L_0x012d
            com.alibaba.fastjson.util.JavaBeanInfo r2 = r8.beanInfo     // Catch:{ Exception -> 0x0121 }
            com.alibaba.fastjson.util.FieldInfo[] r2 = r2.fields     // Catch:{ Exception -> 0x0121 }
            r3 = 0
            r2 = r2[r3]     // Catch:{ Exception -> 0x0121 }
            java.lang.Class<?> r3 = r2.fieldClass     // Catch:{ Exception -> 0x0121 }
            java.lang.Class<java.lang.Integer> r4 = java.lang.Integer.class
            if (r3 != r4) goto L_0x0109
            r3 = 2
            if (r1 != r3) goto L_0x012d
            int r1 = r12.intValue()     // Catch:{ Exception -> 0x0121 }
            r12.nextToken()     // Catch:{ Exception -> 0x0121 }
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch:{ Exception -> 0x0121 }
            java.lang.Object r1 = r8.createFactoryInstance(r13, r1)     // Catch:{ Exception -> 0x0121 }
            r9.setContext(r7)
            return r1
        L_0x0109:
            java.lang.Class<?> r2 = r2.fieldClass     // Catch:{ Exception -> 0x0121 }
            java.lang.Class<java.lang.String> r3 = java.lang.String.class
            if (r2 != r3) goto L_0x012d
            r2 = 4
            if (r1 != r2) goto L_0x012d
            java.lang.String r1 = r12.stringVal()     // Catch:{ Exception -> 0x0121 }
            r12.nextToken()     // Catch:{ Exception -> 0x0121 }
            java.lang.Object r1 = r8.createFactoryInstance(r13, r1)     // Catch:{ Exception -> 0x0121 }
            r9.setContext(r7)
            return r1
        L_0x0121:
            r0 = move-exception
            r1 = r0
            com.alibaba.fastjson.JSONException r2 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x0049 }
            java.lang.String r3 = r1.getMessage()     // Catch:{ all -> 0x0049 }
            r2.<init>(r3, r1)     // Catch:{ all -> 0x0049 }
            throw r2     // Catch:{ all -> 0x0049 }
        L_0x012d:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x0049 }
            r1.<init>()     // Catch:{ all -> 0x0049 }
            java.lang.String r2 = "syntax error, expect {, actual "
            r1.append(r2)     // Catch:{ all -> 0x0049 }
            java.lang.String r2 = r12.tokenName()     // Catch:{ all -> 0x0049 }
            r1.append(r2)     // Catch:{ all -> 0x0049 }
            java.lang.String r2 = ", pos "
            r1.append(r2)     // Catch:{ all -> 0x0049 }
            int r2 = r12.pos()     // Catch:{ all -> 0x0049 }
            r1.append(r2)     // Catch:{ all -> 0x0049 }
            boolean r2 = r11 instanceof java.lang.String     // Catch:{ all -> 0x0049 }
            if (r2 == 0) goto L_0x0156
            java.lang.String r2 = ", fieldName "
            r1.append(r2)     // Catch:{ all -> 0x0049 }
            r1.append(r11)     // Catch:{ all -> 0x0049 }
        L_0x0156:
            java.lang.String r2 = ", fastjson-version "
            r1.append(r2)     // Catch:{ all -> 0x0049 }
            java.lang.String r2 = "1.2.68"
            r1.append(r2)     // Catch:{ all -> 0x0049 }
            com.alibaba.fastjson.JSONException r2 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x0049 }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x0049 }
            r2.<init>(r1)     // Catch:{ all -> 0x0049 }
            throw r2     // Catch:{ all -> 0x0049 }
        L_0x016a:
            int r1 = r9.resolveStatus     // Catch:{ all -> 0x0988 }
            r2 = 2
            if (r1 != r2) goto L_0x0173
            r5 = 0
            r9.resolveStatus = r5     // Catch:{ all -> 0x0049 }
            goto L_0x0174
        L_0x0173:
            r5 = 0
        L_0x0174:
            com.alibaba.fastjson.util.JavaBeanInfo r1 = r8.beanInfo     // Catch:{ all -> 0x0988 }
            java.lang.String r6 = r1.typeKey     // Catch:{ all -> 0x0988 }
            r1 = r35
            r2 = r37
            r5 = r15
            r20 = r5
            r3 = 0
            r4 = 0
        L_0x0181:
            com.alibaba.fastjson.parser.deserializer.FieldDeserializer[] r15 = r8.sortedFieldDeserializers     // Catch:{ all -> 0x0980 }
            int r15 = r15.length     // Catch:{ all -> 0x0980 }
            if (r4 >= r15) goto L_0x01b3
            if (r3 >= r14) goto L_0x01b3
            com.alibaba.fastjson.parser.deserializer.FieldDeserializer[] r15 = r8.sortedFieldDeserializers     // Catch:{ all -> 0x01ad }
            r15 = r15[r4]     // Catch:{ all -> 0x01ad }
            com.alibaba.fastjson.util.FieldInfo r14 = r15.fieldInfo     // Catch:{ all -> 0x01ad }
            r35 = r4
            java.lang.Class<?> r4 = r14.fieldClass     // Catch:{ all -> 0x01ad }
            com.alibaba.fastjson.annotation.JSONField r21 = r14.getAnnotation()     // Catch:{ all -> 0x01ad }
            if (r21 == 0) goto L_0x01a5
            r36 = r4
            boolean r4 = r15 instanceof com.alibaba.fastjson.parser.deserializer.DefaultFieldDeserializer     // Catch:{ all -> 0x01ad }
            if (r4 == 0) goto L_0x01a7
            r4 = r15
            com.alibaba.fastjson.parser.deserializer.DefaultFieldDeserializer r4 = (com.alibaba.fastjson.parser.deserializer.DefaultFieldDeserializer) r4     // Catch:{ all -> 0x01ad }
            boolean r4 = r4.customDeserilizer     // Catch:{ all -> 0x01ad }
            r10 = r15
            goto L_0x01a9
        L_0x01a5:
            r36 = r4
        L_0x01a7:
            r10 = r15
            r4 = 0
        L_0x01a9:
            r15 = r14
            r14 = r36
            goto L_0x01bb
        L_0x01ad:
            r0 = move-exception
            r14 = r1
            r15 = r5
            r3 = r7
            goto L_0x098d
        L_0x01b3:
            r35 = r4
            r4 = 0
            r10 = 0
            r14 = 0
            r15 = 0
            r21 = 0
        L_0x01bb:
            r22 = 0
            r24 = 0
            r25 = 0
            if (r10 == 0) goto L_0x03ab
            r36 = r2
            char[] r2 = r15.name_chars     // Catch:{ all -> 0x03a2 }
            if (r4 == 0) goto L_0x01d6
            boolean r4 = r12.matchField((char[]) r2)     // Catch:{ all -> 0x01ad }
            if (r4 == 0) goto L_0x01d6
            r37 = r1
        L_0x01d1:
            r28 = r10
            r1 = 1
            goto L_0x03b2
        L_0x01d6:
            java.lang.Class r4 = java.lang.Integer.TYPE     // Catch:{ all -> 0x03a2 }
            r37 = r1
            r1 = -2
            if (r14 == r4) goto L_0x0374
            java.lang.Class<java.lang.Integer> r4 = java.lang.Integer.class
            if (r14 != r4) goto L_0x01e3
            goto L_0x0374
        L_0x01e3:
            java.lang.Class r4 = java.lang.Long.TYPE     // Catch:{ all -> 0x0570 }
            if (r14 == r4) goto L_0x0353
            java.lang.Class<java.lang.Long> r4 = java.lang.Long.class
            if (r14 != r4) goto L_0x01ed
            goto L_0x0353
        L_0x01ed:
            java.lang.Class<java.lang.String> r4 = java.lang.String.class
            if (r14 != r4) goto L_0x020a
            java.lang.String r2 = r12.scanFieldString(r2)     // Catch:{ all -> 0x0570 }
            int r4 = r12.matchStat     // Catch:{ all -> 0x0570 }
            if (r4 <= 0) goto L_0x01ff
        L_0x01f9:
            r28 = r10
        L_0x01fb:
            r1 = 1
            r4 = 1
            goto L_0x03b4
        L_0x01ff:
            int r4 = r12.matchStat     // Catch:{ all -> 0x0570 }
            if (r4 != r1) goto L_0x0205
            goto L_0x0393
        L_0x0205:
            r28 = r10
        L_0x0207:
            r1 = 0
            goto L_0x03b3
        L_0x020a:
            java.lang.Class<java.util.Date> r4 = java.util.Date.class
            if (r14 != r4) goto L_0x0221
            java.lang.String r4 = r15.format     // Catch:{ all -> 0x0570 }
            if (r4 != 0) goto L_0x0221
            java.util.Date r2 = r12.scanFieldDate(r2)     // Catch:{ all -> 0x0570 }
            int r4 = r12.matchStat     // Catch:{ all -> 0x0570 }
            if (r4 <= 0) goto L_0x021b
            goto L_0x01f9
        L_0x021b:
            int r4 = r12.matchStat     // Catch:{ all -> 0x0570 }
            if (r4 != r1) goto L_0x0205
            goto L_0x0393
        L_0x0221:
            java.lang.Class<java.math.BigDecimal> r4 = java.math.BigDecimal.class
            if (r14 != r4) goto L_0x0234
            java.math.BigDecimal r2 = r12.scanFieldDecimal(r2)     // Catch:{ all -> 0x0570 }
            int r4 = r12.matchStat     // Catch:{ all -> 0x0570 }
            if (r4 <= 0) goto L_0x022e
            goto L_0x01f9
        L_0x022e:
            int r4 = r12.matchStat     // Catch:{ all -> 0x0570 }
            if (r4 != r1) goto L_0x0205
            goto L_0x0393
        L_0x0234:
            java.lang.Class<java.math.BigInteger> r4 = java.math.BigInteger.class
            if (r14 != r4) goto L_0x0247
            java.math.BigInteger r2 = r12.scanFieldBigInteger(r2)     // Catch:{ all -> 0x0570 }
            int r4 = r12.matchStat     // Catch:{ all -> 0x0570 }
            if (r4 <= 0) goto L_0x0241
            goto L_0x01f9
        L_0x0241:
            int r4 = r12.matchStat     // Catch:{ all -> 0x0570 }
            if (r4 != r1) goto L_0x0205
            goto L_0x0393
        L_0x0247:
            java.lang.Class r4 = java.lang.Boolean.TYPE     // Catch:{ all -> 0x0570 }
            if (r14 == r4) goto L_0x0336
            java.lang.Class<java.lang.Boolean> r4 = java.lang.Boolean.class
            if (r14 != r4) goto L_0x0251
            goto L_0x0336
        L_0x0251:
            java.lang.Class r4 = java.lang.Float.TYPE     // Catch:{ all -> 0x0570 }
            if (r14 == r4) goto L_0x0314
            java.lang.Class<java.lang.Float> r4 = java.lang.Float.class
            if (r14 != r4) goto L_0x025b
            goto L_0x0314
        L_0x025b:
            java.lang.Class r4 = java.lang.Double.TYPE     // Catch:{ all -> 0x0570 }
            if (r14 == r4) goto L_0x02ef
            java.lang.Class<java.lang.Double> r4 = java.lang.Double.class
            if (r14 != r4) goto L_0x0265
            goto L_0x02ef
        L_0x0265:
            boolean r4 = r14.isEnum()     // Catch:{ all -> 0x0570 }
            if (r4 == 0) goto L_0x02a2
            com.alibaba.fastjson.parser.ParserConfig r4 = r32.getConfig()     // Catch:{ all -> 0x0570 }
            com.alibaba.fastjson.parser.deserializer.ObjectDeserializer r4 = r4.getDeserializer((java.lang.reflect.Type) r14)     // Catch:{ all -> 0x0570 }
            boolean r4 = r4 instanceof com.alibaba.fastjson.parser.deserializer.EnumDeserializer     // Catch:{ all -> 0x0570 }
            if (r4 == 0) goto L_0x02a2
            if (r21 == 0) goto L_0x0281
            java.lang.Class r4 = r21.deserializeUsing()     // Catch:{ all -> 0x0570 }
            java.lang.Class<java.lang.Void> r1 = java.lang.Void.class
            if (r4 != r1) goto L_0x02a2
        L_0x0281:
            boolean r1 = r10 instanceof com.alibaba.fastjson.parser.deserializer.DefaultFieldDeserializer     // Catch:{ all -> 0x0570 }
            if (r1 == 0) goto L_0x03af
            r1 = r10
            com.alibaba.fastjson.parser.deserializer.DefaultFieldDeserializer r1 = (com.alibaba.fastjson.parser.deserializer.DefaultFieldDeserializer) r1     // Catch:{ all -> 0x0570 }
            com.alibaba.fastjson.parser.deserializer.ObjectDeserializer r1 = r1.fieldValueDeserilizer     // Catch:{ all -> 0x0570 }
            java.lang.Enum r2 = r8.scanEnum(r12, r2, r1)     // Catch:{ all -> 0x0570 }
            int r1 = r12.matchStat     // Catch:{ all -> 0x0570 }
            if (r1 <= 0) goto L_0x0295
            r1 = 1
            r4 = 1
            goto L_0x029e
        L_0x0295:
            int r1 = r12.matchStat     // Catch:{ all -> 0x0570 }
            r4 = -2
            if (r1 != r4) goto L_0x029c
            goto L_0x0393
        L_0x029c:
            r1 = 0
            r4 = 0
        L_0x029e:
            r28 = r10
            goto L_0x03b4
        L_0x02a2:
            java.lang.Class<int[]> r1 = int[].class
            if (r14 != r1) goto L_0x02b7
            int[] r2 = r12.scanFieldIntArray(r2)     // Catch:{ all -> 0x0570 }
            int r1 = r12.matchStat     // Catch:{ all -> 0x0570 }
            if (r1 <= 0) goto L_0x02b0
            goto L_0x01f9
        L_0x02b0:
            int r1 = r12.matchStat     // Catch:{ all -> 0x0570 }
            r4 = -2
            if (r1 != r4) goto L_0x0205
            goto L_0x0393
        L_0x02b7:
            java.lang.Class<float[]> r1 = float[].class
            if (r14 != r1) goto L_0x02cc
            float[] r2 = r12.scanFieldFloatArray(r2)     // Catch:{ all -> 0x0570 }
            int r1 = r12.matchStat     // Catch:{ all -> 0x0570 }
            if (r1 <= 0) goto L_0x02c5
            goto L_0x01f9
        L_0x02c5:
            int r1 = r12.matchStat     // Catch:{ all -> 0x0570 }
            r4 = -2
            if (r1 != r4) goto L_0x0205
            goto L_0x0393
        L_0x02cc:
            java.lang.Class<float[][]> r1 = float[][].class
            if (r14 != r1) goto L_0x02e1
            float[][] r2 = r12.scanFieldFloatArray2(r2)     // Catch:{ all -> 0x0570 }
            int r1 = r12.matchStat     // Catch:{ all -> 0x0570 }
            if (r1 <= 0) goto L_0x02da
            goto L_0x01f9
        L_0x02da:
            int r1 = r12.matchStat     // Catch:{ all -> 0x0570 }
            r4 = -2
            if (r1 != r4) goto L_0x0205
            goto L_0x0393
        L_0x02e1:
            boolean r1 = r12.matchField((char[]) r2)     // Catch:{ all -> 0x0570 }
            if (r1 == 0) goto L_0x02e9
            goto L_0x01d1
        L_0x02e9:
            r14 = r37
            r21 = r3
            goto L_0x03e1
        L_0x02ef:
            double r1 = r12.scanFieldDouble(r2)     // Catch:{ all -> 0x0570 }
            int r4 = (r1 > r22 ? 1 : (r1 == r22 ? 0 : -1))
            if (r4 != 0) goto L_0x0300
            int r4 = r12.matchStat     // Catch:{ all -> 0x0570 }
            r28 = r10
            r10 = 5
            if (r4 != r10) goto L_0x0302
            r2 = 0
            goto L_0x0307
        L_0x0300:
            r28 = r10
        L_0x0302:
            java.lang.Double r1 = java.lang.Double.valueOf(r1)     // Catch:{ all -> 0x0570 }
            r2 = r1
        L_0x0307:
            int r1 = r12.matchStat     // Catch:{ all -> 0x0570 }
            if (r1 <= 0) goto L_0x030d
        L_0x030b:
            goto L_0x01fb
        L_0x030d:
            int r1 = r12.matchStat     // Catch:{ all -> 0x0570 }
            r4 = -2
            if (r1 != r4) goto L_0x0207
            goto L_0x0393
        L_0x0314:
            r28 = r10
            float r1 = r12.scanFieldFloat(r2)     // Catch:{ all -> 0x0570 }
            int r2 = (r1 > r24 ? 1 : (r1 == r24 ? 0 : -1))
            if (r2 != 0) goto L_0x0325
            int r2 = r12.matchStat     // Catch:{ all -> 0x0570 }
            r4 = 5
            if (r2 != r4) goto L_0x0325
            r2 = 0
            goto L_0x032a
        L_0x0325:
            java.lang.Float r1 = java.lang.Float.valueOf(r1)     // Catch:{ all -> 0x0570 }
            r2 = r1
        L_0x032a:
            int r1 = r12.matchStat     // Catch:{ all -> 0x0570 }
            if (r1 <= 0) goto L_0x032f
            goto L_0x030b
        L_0x032f:
            int r1 = r12.matchStat     // Catch:{ all -> 0x0570 }
            r4 = -2
            if (r1 != r4) goto L_0x0207
            goto L_0x0393
        L_0x0336:
            r28 = r10
            boolean r1 = r12.scanFieldBoolean(r2)     // Catch:{ all -> 0x0570 }
            int r2 = r12.matchStat     // Catch:{ all -> 0x0570 }
            r4 = 5
            if (r2 != r4) goto L_0x0343
            r2 = 0
            goto L_0x0348
        L_0x0343:
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r1)     // Catch:{ all -> 0x0570 }
            r2 = r1
        L_0x0348:
            int r1 = r12.matchStat     // Catch:{ all -> 0x0570 }
            if (r1 <= 0) goto L_0x034d
            goto L_0x030b
        L_0x034d:
            int r1 = r12.matchStat     // Catch:{ all -> 0x0570 }
            r4 = -2
            if (r1 != r4) goto L_0x0207
            goto L_0x0393
        L_0x0353:
            r28 = r10
            long r1 = r12.scanFieldLong(r2)     // Catch:{ all -> 0x0570 }
            int r4 = (r1 > r25 ? 1 : (r1 == r25 ? 0 : -1))
            if (r4 != 0) goto L_0x0364
            int r4 = r12.matchStat     // Catch:{ all -> 0x0570 }
            r10 = 5
            if (r4 != r10) goto L_0x0364
            r2 = 0
            goto L_0x0369
        L_0x0364:
            java.lang.Long r1 = java.lang.Long.valueOf(r1)     // Catch:{ all -> 0x0570 }
            r2 = r1
        L_0x0369:
            int r1 = r12.matchStat     // Catch:{ all -> 0x0570 }
            if (r1 <= 0) goto L_0x036e
            goto L_0x030b
        L_0x036e:
            int r1 = r12.matchStat     // Catch:{ all -> 0x0570 }
            r4 = -2
            if (r1 != r4) goto L_0x0207
            goto L_0x0393
        L_0x0374:
            r28 = r10
            int r1 = r12.scanFieldInt(r2)     // Catch:{ all -> 0x0570 }
            if (r1 != 0) goto L_0x0383
            int r2 = r12.matchStat     // Catch:{ all -> 0x0570 }
            r4 = 5
            if (r2 != r4) goto L_0x0383
            r2 = 0
            goto L_0x0388
        L_0x0383:
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch:{ all -> 0x0570 }
            r2 = r1
        L_0x0388:
            int r1 = r12.matchStat     // Catch:{ all -> 0x0570 }
            if (r1 <= 0) goto L_0x038e
            goto L_0x030b
        L_0x038e:
            int r1 = r12.matchStat     // Catch:{ all -> 0x0570 }
            r4 = -2
            if (r1 != r4) goto L_0x0207
        L_0x0393:
            int r3 = r3 + 1
            r10 = r35
            r14 = r37
            r16 = r3
            r19 = r6
            r3 = r7
            r18 = r20
            goto L_0x0554
        L_0x03a2:
            r0 = move-exception
            r37 = r1
        L_0x03a5:
            r14 = r37
        L_0x03a7:
            r1 = r0
            r15 = r5
            goto L_0x004d
        L_0x03ab:
            r37 = r1
            r36 = r2
        L_0x03af:
            r28 = r10
            r1 = 0
        L_0x03b2:
            r2 = 0
        L_0x03b3:
            r4 = 0
        L_0x03b4:
            if (r1 != 0) goto L_0x0573
            com.alibaba.fastjson.parser.SymbolTable r10 = r9.symbolTable     // Catch:{ all -> 0x0570 }
            java.lang.String r10 = r12.scanSymbol(r10)     // Catch:{ all -> 0x0570 }
            if (r10 != 0) goto L_0x03e5
            r21 = r3
            int r3 = r12.token()     // Catch:{ all -> 0x0570 }
            r27 = r14
            r14 = 13
            if (r3 != r14) goto L_0x03d3
            r14 = 16
            r12.nextToken(r14)     // Catch:{ all -> 0x0570 }
            r14 = r37
            goto L_0x0543
        L_0x03d3:
            r14 = 16
            if (r3 != r14) goto L_0x03e9
            com.alibaba.fastjson.parser.Feature r3 = com.alibaba.fastjson.parser.Feature.AllowArbitraryCommas     // Catch:{ all -> 0x0570 }
            boolean r3 = r12.isEnabled((com.alibaba.fastjson.parser.Feature) r3)     // Catch:{ all -> 0x0570 }
            if (r3 == 0) goto L_0x03e9
            r14 = r37
        L_0x03e1:
            r3 = 13
            goto L_0x054b
        L_0x03e5:
            r21 = r3
            r27 = r14
        L_0x03e9:
            java.lang.String r3 = "$ref"
            if (r3 != r10) goto L_0x04c7
            if (r7 == 0) goto L_0x04c7
            r3 = 4
            r12.nextTokenWithColon(r3)     // Catch:{ all -> 0x0570 }
            int r1 = r12.token()     // Catch:{ all -> 0x0570 }
            if (r1 != r3) goto L_0x04ac
            java.lang.String r1 = r12.stringVal()     // Catch:{ all -> 0x0570 }
            java.lang.String r2 = "@"
            boolean r2 = r2.equals(r1)     // Catch:{ all -> 0x0570 }
            if (r2 == 0) goto L_0x0409
            java.lang.Object r1 = r7.object     // Catch:{ all -> 0x0570 }
            goto L_0x0489
        L_0x0409:
            java.lang.String r2 = ".."
            boolean r2 = r2.equals(r1)     // Catch:{ all -> 0x0570 }
            if (r2 == 0) goto L_0x0427
            com.alibaba.fastjson.parser.ParseContext r2 = r7.parent     // Catch:{ all -> 0x0570 }
            java.lang.Object r3 = r2.object     // Catch:{ all -> 0x0570 }
            if (r3 == 0) goto L_0x041b
            java.lang.Object r1 = r2.object     // Catch:{ all -> 0x0570 }
            goto L_0x0489
        L_0x041b:
            com.alibaba.fastjson.parser.DefaultJSONParser$ResolveTask r3 = new com.alibaba.fastjson.parser.DefaultJSONParser$ResolveTask     // Catch:{ all -> 0x0570 }
            r3.<init>(r2, r1)     // Catch:{ all -> 0x0570 }
            r9.addResolveTask(r3)     // Catch:{ all -> 0x0570 }
            r1 = 1
            r9.resolveStatus = r1     // Catch:{ all -> 0x0570 }
            goto L_0x0487
        L_0x0427:
            java.lang.String r2 = "$"
            boolean r2 = r2.equals(r1)     // Catch:{ all -> 0x0570 }
            if (r2 == 0) goto L_0x044a
            r2 = r7
        L_0x0430:
            com.alibaba.fastjson.parser.ParseContext r3 = r2.parent     // Catch:{ all -> 0x0570 }
            if (r3 == 0) goto L_0x0437
            com.alibaba.fastjson.parser.ParseContext r2 = r2.parent     // Catch:{ all -> 0x0570 }
            goto L_0x0430
        L_0x0437:
            java.lang.Object r3 = r2.object     // Catch:{ all -> 0x0570 }
            if (r3 == 0) goto L_0x043e
            java.lang.Object r1 = r2.object     // Catch:{ all -> 0x0570 }
            goto L_0x0489
        L_0x043e:
            com.alibaba.fastjson.parser.DefaultJSONParser$ResolveTask r3 = new com.alibaba.fastjson.parser.DefaultJSONParser$ResolveTask     // Catch:{ all -> 0x0570 }
            r3.<init>(r2, r1)     // Catch:{ all -> 0x0570 }
            r9.addResolveTask(r3)     // Catch:{ all -> 0x0570 }
            r1 = 1
            r9.resolveStatus = r1     // Catch:{ all -> 0x0570 }
            goto L_0x0487
        L_0x044a:
            r2 = 92
            int r3 = r1.indexOf(r2)     // Catch:{ all -> 0x0570 }
            if (r3 <= 0) goto L_0x0474
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0570 }
            r3.<init>()     // Catch:{ all -> 0x0570 }
            r4 = 0
        L_0x0458:
            int r6 = r1.length()     // Catch:{ all -> 0x0570 }
            if (r4 >= r6) goto L_0x0470
            char r6 = r1.charAt(r4)     // Catch:{ all -> 0x0570 }
            if (r6 != r2) goto L_0x046a
            int r4 = r4 + 1
            char r6 = r1.charAt(r4)     // Catch:{ all -> 0x0570 }
        L_0x046a:
            r3.append(r6)     // Catch:{ all -> 0x0570 }
            r6 = 1
            int r4 = r4 + r6
            goto L_0x0458
        L_0x0470:
            java.lang.String r1 = r3.toString()     // Catch:{ all -> 0x0570 }
        L_0x0474:
            java.lang.Object r2 = r9.resolveReference(r1)     // Catch:{ all -> 0x0570 }
            if (r2 == 0) goto L_0x047c
            r1 = r2
            goto L_0x0489
        L_0x047c:
            com.alibaba.fastjson.parser.DefaultJSONParser$ResolveTask r2 = new com.alibaba.fastjson.parser.DefaultJSONParser$ResolveTask     // Catch:{ all -> 0x0570 }
            r2.<init>(r7, r1)     // Catch:{ all -> 0x0570 }
            r9.addResolveTask(r2)     // Catch:{ all -> 0x0570 }
            r1 = 1
            r9.resolveStatus = r1     // Catch:{ all -> 0x0570 }
        L_0x0487:
            r1 = r37
        L_0x0489:
            r2 = 13
            r12.nextToken(r2)     // Catch:{ all -> 0x01ad }
            int r3 = r12.token()     // Catch:{ all -> 0x01ad }
            if (r3 != r2) goto L_0x04a4
            r2 = 16
            r12.nextToken(r2)     // Catch:{ all -> 0x01ad }
            r9.setContext(r7, r1, r11)     // Catch:{ all -> 0x01ad }
            if (r5 == 0) goto L_0x04a0
            r5.object = r1
        L_0x04a0:
            r9.setContext(r7)
            return r1
        L_0x04a4:
            com.alibaba.fastjson.JSONException r2 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x01ad }
            java.lang.String r3 = "illegal ref"
            r2.<init>(r3)     // Catch:{ all -> 0x01ad }
            throw r2     // Catch:{ all -> 0x01ad }
        L_0x04ac:
            com.alibaba.fastjson.JSONException r2 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x0570 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0570 }
            r3.<init>()     // Catch:{ all -> 0x0570 }
            java.lang.String r4 = "illegal ref, "
            r3.append(r4)     // Catch:{ all -> 0x0570 }
            java.lang.String r1 = com.alibaba.fastjson.parser.JSONToken.name(r1)     // Catch:{ all -> 0x0570 }
            r3.append(r1)     // Catch:{ all -> 0x0570 }
            java.lang.String r1 = r3.toString()     // Catch:{ all -> 0x0570 }
            r2.<init>(r1)     // Catch:{ all -> 0x0570 }
            throw r2     // Catch:{ all -> 0x0570 }
        L_0x04c7:
            if (r6 == 0) goto L_0x04cf
            boolean r3 = r6.equals(r10)     // Catch:{ all -> 0x0570 }
            if (r3 != 0) goto L_0x04d3
        L_0x04cf:
            java.lang.String r3 = com.alibaba.fastjson.JSON.DEFAULT_TYPE_KEY     // Catch:{ all -> 0x0570 }
            if (r3 != r10) goto L_0x056b
        L_0x04d3:
            r1 = 4
            r12.nextTokenWithColon(r1)     // Catch:{ all -> 0x0570 }
            int r2 = r12.token()     // Catch:{ all -> 0x0570 }
            if (r2 != r1) goto L_0x0561
            java.lang.String r1 = r12.stringVal()     // Catch:{ all -> 0x0570 }
            r2 = 16
            r12.nextToken(r2)     // Catch:{ all -> 0x0570 }
            com.alibaba.fastjson.util.JavaBeanInfo r2 = r8.beanInfo     // Catch:{ all -> 0x0570 }
            java.lang.String r2 = r2.typeName     // Catch:{ all -> 0x0570 }
            boolean r2 = r1.equals(r2)     // Catch:{ all -> 0x0570 }
            if (r2 != 0) goto L_0x0536
            com.alibaba.fastjson.parser.Feature r2 = com.alibaba.fastjson.parser.Feature.IgnoreAutoType     // Catch:{ all -> 0x0570 }
            boolean r2 = r9.isEnabled(r2)     // Catch:{ all -> 0x0570 }
            if (r2 == 0) goto L_0x04f9
            goto L_0x0536
        L_0x04f9:
            com.alibaba.fastjson.util.JavaBeanInfo r2 = r8.beanInfo     // Catch:{ all -> 0x0570 }
            com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer r2 = getSeeAlso(r13, r2, r1)     // Catch:{ all -> 0x0570 }
            if (r2 != 0) goto L_0x0516
            java.lang.Class r2 = com.alibaba.fastjson.util.TypeUtils.getClass(r33)     // Catch:{ all -> 0x0570 }
            int r3 = r12.getFeatures()     // Catch:{ all -> 0x0570 }
            java.lang.Class r15 = r13.checkAutoType(r1, r2, r3)     // Catch:{ all -> 0x0570 }
            com.alibaba.fastjson.parser.ParserConfig r2 = r32.getConfig()     // Catch:{ all -> 0x0570 }
            com.alibaba.fastjson.parser.deserializer.ObjectDeserializer r2 = r2.getDeserializer((java.lang.reflect.Type) r15)     // Catch:{ all -> 0x0570 }
            goto L_0x0517
        L_0x0516:
            r15 = 0
        L_0x0517:
            java.lang.Object r3 = r2.deserialze(r9, r15, r11)     // Catch:{ all -> 0x0570 }
            boolean r4 = r2 instanceof com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer     // Catch:{ all -> 0x0570 }
            if (r4 == 0) goto L_0x052c
            com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer r2 = (com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer) r2     // Catch:{ all -> 0x0570 }
            if (r6 == 0) goto L_0x052c
            com.alibaba.fastjson.parser.deserializer.FieldDeserializer r2 = r2.getFieldDeserializer((java.lang.String) r6)     // Catch:{ all -> 0x0570 }
            if (r2 == 0) goto L_0x052c
            r2.setValue((java.lang.Object) r3, (java.lang.String) r1)     // Catch:{ all -> 0x0570 }
        L_0x052c:
            if (r5 == 0) goto L_0x0532
            r14 = r37
            r5.object = r14
        L_0x0532:
            r9.setContext(r7)
            return r3
        L_0x0536:
            r14 = r37
            int r1 = r12.token()     // Catch:{ all -> 0x05ad }
            r3 = 13
            if (r1 != r3) goto L_0x054b
            r12.nextToken()     // Catch:{ all -> 0x05ad }
        L_0x0543:
            r30 = r7
            r1 = r20
            r35 = 0
            goto L_0x06a5
        L_0x054b:
            r10 = r35
            r19 = r6
            r3 = r7
            r18 = r20
            r16 = r21
        L_0x0554:
            r1 = 0
            r2 = 16
            r4 = 0
            r6 = 1
            r15 = 13
            r21 = 4
            r20 = r36
            goto L_0x093c
        L_0x0561:
            r14 = r37
            com.alibaba.fastjson.JSONException r1 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x05ad }
            java.lang.String r2 = "syntax error"
            r1.<init>(r2)     // Catch:{ all -> 0x05ad }
            throw r1     // Catch:{ all -> 0x05ad }
        L_0x056b:
            r14 = r37
            r3 = 13
            goto L_0x057c
        L_0x0570:
            r0 = move-exception
            goto L_0x03a5
        L_0x0573:
            r21 = r3
            r27 = r14
            r3 = 13
            r14 = r37
            r10 = 0
        L_0x057c:
            if (r14 != 0) goto L_0x05bc
            if (r20 != 0) goto L_0x05bc
            java.lang.Object r14 = r31.createInstance((com.alibaba.fastjson.parser.DefaultJSONParser) r32, (java.lang.reflect.Type) r33)     // Catch:{ all -> 0x05b5 }
            if (r14 != 0) goto L_0x0593
            java.util.HashMap r3 = new java.util.HashMap     // Catch:{ all -> 0x05b5 }
            r37 = r5
            com.alibaba.fastjson.parser.deserializer.FieldDeserializer[] r5 = r8.fieldDeserializers     // Catch:{ all -> 0x05b3 }
            int r5 = r5.length     // Catch:{ all -> 0x05b3 }
            r3.<init>(r5)     // Catch:{ all -> 0x05b3 }
            r20 = r3
            goto L_0x0595
        L_0x0593:
            r37 = r5
        L_0x0595:
            com.alibaba.fastjson.parser.ParseContext r5 = r9.setContext(r7, r14, r11)     // Catch:{ all -> 0x05b3 }
            if (r36 != 0) goto L_0x05b0
            com.alibaba.fastjson.parser.deserializer.FieldDeserializer[] r3 = r8.fieldDeserializers     // Catch:{ all -> 0x05ad }
            int r3 = r3.length     // Catch:{ all -> 0x05ad }
            int r3 = r3 / 32
            r19 = 1
            int r3 = r3 + 1
            int[] r3 = new int[r3]     // Catch:{ all -> 0x05ad }
            r29 = r5
            r5 = r20
            r20 = r3
            goto L_0x05c4
        L_0x05ad:
            r0 = move-exception
            goto L_0x03a7
        L_0x05b0:
            r29 = r5
            goto L_0x05c0
        L_0x05b3:
            r0 = move-exception
            goto L_0x05b8
        L_0x05b5:
            r0 = move-exception
            r37 = r5
        L_0x05b8:
            r15 = r37
            goto L_0x004c
        L_0x05bc:
            r37 = r5
            r29 = r37
        L_0x05c0:
            r5 = r20
            r20 = r36
        L_0x05c4:
            if (r1 == 0) goto L_0x0629
            if (r4 != 0) goto L_0x05e1
            r4 = r33
            r1 = r28
            r1.parseField(r9, r14, r4, r5)     // Catch:{ all -> 0x063a }
        L_0x05cf:
            r10 = r35
            r18 = r5
            r19 = r6
            r30 = r7
            r16 = r21
            r35 = 0
            r15 = 13
            r21 = 4
            goto L_0x068c
        L_0x05e1:
            r4 = r33
            r1 = r28
            if (r14 != 0) goto L_0x05ed
            java.lang.String r1 = r15.name     // Catch:{ all -> 0x063a }
            r5.put(r1, r2)     // Catch:{ all -> 0x063a }
            goto L_0x060c
        L_0x05ed:
            if (r2 != 0) goto L_0x0609
            java.lang.Class r3 = java.lang.Integer.TYPE     // Catch:{ all -> 0x063a }
            r10 = r27
            if (r10 == r3) goto L_0x060c
            java.lang.Class r3 = java.lang.Long.TYPE     // Catch:{ all -> 0x063a }
            if (r10 == r3) goto L_0x060c
            java.lang.Class r3 = java.lang.Float.TYPE     // Catch:{ all -> 0x063a }
            if (r10 == r3) goto L_0x060c
            java.lang.Class r3 = java.lang.Double.TYPE     // Catch:{ all -> 0x063a }
            if (r10 == r3) goto L_0x060c
            java.lang.Class r3 = java.lang.Boolean.TYPE     // Catch:{ all -> 0x063a }
            if (r10 == r3) goto L_0x060c
            r1.setValue((java.lang.Object) r14, (java.lang.Object) r2)     // Catch:{ all -> 0x063a }
            goto L_0x060c
        L_0x0609:
            r1.setValue((java.lang.Object) r14, (java.lang.Object) r2)     // Catch:{ all -> 0x063a }
        L_0x060c:
            if (r20 == 0) goto L_0x061b
            int r1 = r35 / 32
            int r2 = r35 % 32
            r3 = r20[r1]     // Catch:{ all -> 0x063a }
            r15 = 1
            int r2 = r15 << r2
            r2 = r2 | r3
            r20[r1] = r2     // Catch:{ all -> 0x063a }
            goto L_0x061c
        L_0x061b:
            r15 = 1
        L_0x061c:
            int r1 = r12.matchStat     // Catch:{ all -> 0x063a }
            r3 = 4
            if (r1 != r3) goto L_0x05cf
            r18 = r5
            r30 = r7
            r35 = 0
            goto L_0x06a1
        L_0x0629:
            r4 = r33
            r3 = 4
            r15 = 1
            if (r5 != 0) goto L_0x0641
            java.util.HashMap r1 = new java.util.HashMap     // Catch:{ all -> 0x063a }
            com.alibaba.fastjson.parser.deserializer.FieldDeserializer[] r2 = r8.fieldDeserializers     // Catch:{ all -> 0x063a }
            int r2 = r2.length     // Catch:{ all -> 0x063a }
            r1.<init>(r2)     // Catch:{ all -> 0x063a }
            r17 = r1
            goto L_0x0643
        L_0x063a:
            r0 = move-exception
            r1 = r0
            r3 = r7
        L_0x063d:
            r15 = r29
            goto L_0x098e
        L_0x0641:
            r17 = r5
        L_0x0643:
            r1 = r31
            r2 = r32
            r16 = r21
            r19 = 4
            r21 = 13
            r3 = r10
            r10 = r35
            r4 = r14
            r18 = r5
            r35 = 0
            r5 = r33
            r19 = r6
            r15 = 13
            r21 = 4
            r6 = r17
            r30 = r7
            r7 = r20
            boolean r1 = r1.parseField(r2, r3, r4, r5, r6, r7)     // Catch:{ all -> 0x097a }
            if (r1 != 0) goto L_0x0684
            int r1 = r12.token()     // Catch:{ all -> 0x067c }
            if (r1 != r15) goto L_0x0673
            r12.nextToken()     // Catch:{ all -> 0x067c }
            goto L_0x06a1
        L_0x0673:
            r3 = r30
            r1 = 0
            r2 = 16
        L_0x0678:
            r4 = 0
            r6 = 1
            goto L_0x093a
        L_0x067c:
            r0 = move-exception
            r1 = r0
            r15 = r29
            r3 = r30
            goto L_0x098e
        L_0x0684:
            int r1 = r12.token()     // Catch:{ all -> 0x097a }
            r2 = 17
            if (r1 == r2) goto L_0x096e
        L_0x068c:
            int r1 = r12.token()     // Catch:{ all -> 0x097a }
            r2 = 16
            if (r1 != r2) goto L_0x0698
            r3 = r30
            r1 = 0
            goto L_0x0678
        L_0x0698:
            int r1 = r12.token()     // Catch:{ all -> 0x097a }
            if (r1 != r15) goto L_0x0927
            r12.nextToken(r2)     // Catch:{ all -> 0x097a }
        L_0x06a1:
            r1 = r18
            r5 = r29
        L_0x06a5:
            if (r14 != 0) goto L_0x08f9
            if (r1 != 0) goto L_0x06ca
            java.lang.Object r1 = r31.createInstance((com.alibaba.fastjson.parser.DefaultJSONParser) r32, (java.lang.reflect.Type) r33)     // Catch:{ all -> 0x06c5 }
            if (r5 != 0) goto L_0x06bb
            r3 = r30
            com.alibaba.fastjson.parser.ParseContext r5 = r9.setContext(r3, r1, r11)     // Catch:{ all -> 0x06b6 }
            goto L_0x06bd
        L_0x06b6:
            r0 = move-exception
            r14 = r1
            r15 = r5
            goto L_0x098d
        L_0x06bb:
            r3 = r30
        L_0x06bd:
            if (r5 == 0) goto L_0x06c1
            r5.object = r1
        L_0x06c1:
            r9.setContext(r3)
            return r1
        L_0x06c5:
            r0 = move-exception
            r3 = r30
            goto L_0x0923
        L_0x06ca:
            r3 = r30
            com.alibaba.fastjson.util.JavaBeanInfo r2 = r8.beanInfo     // Catch:{ all -> 0x0922 }
            java.lang.String[] r2 = r2.creatorConstructorParameters     // Catch:{ all -> 0x0922 }
            java.lang.String r4 = ""
            if (r2 == 0) goto L_0x0780
            int r6 = r2.length     // Catch:{ all -> 0x0922 }
            java.lang.Object[] r6 = new java.lang.Object[r6]     // Catch:{ all -> 0x0922 }
            r7 = 0
        L_0x06d8:
            int r10 = r2.length     // Catch:{ all -> 0x0922 }
            if (r7 >= r10) goto L_0x07f4
            r10 = r2[r7]     // Catch:{ all -> 0x0922 }
            java.lang.Object r10 = r1.remove(r10)     // Catch:{ all -> 0x0922 }
            if (r10 != 0) goto L_0x073c
            com.alibaba.fastjson.util.JavaBeanInfo r11 = r8.beanInfo     // Catch:{ all -> 0x0922 }
            java.lang.reflect.Type[] r11 = r11.creatorConstructorParameterTypes     // Catch:{ all -> 0x0922 }
            r11 = r11[r7]     // Catch:{ all -> 0x0922 }
            com.alibaba.fastjson.util.JavaBeanInfo r12 = r8.beanInfo     // Catch:{ all -> 0x0922 }
            com.alibaba.fastjson.util.FieldInfo[] r12 = r12.fields     // Catch:{ all -> 0x0922 }
            r12 = r12[r7]     // Catch:{ all -> 0x0922 }
            java.lang.Class r13 = java.lang.Byte.TYPE     // Catch:{ all -> 0x0922 }
            if (r11 != r13) goto L_0x06f8
            java.lang.Byte r10 = java.lang.Byte.valueOf(r35)     // Catch:{ all -> 0x0922 }
            goto L_0x073a
        L_0x06f8:
            java.lang.Class r13 = java.lang.Short.TYPE     // Catch:{ all -> 0x0922 }
            if (r11 != r13) goto L_0x0701
            java.lang.Short r10 = java.lang.Short.valueOf(r35)     // Catch:{ all -> 0x0922 }
            goto L_0x073a
        L_0x0701:
            java.lang.Class r13 = java.lang.Integer.TYPE     // Catch:{ all -> 0x0922 }
            if (r11 != r13) goto L_0x070a
            java.lang.Integer r10 = java.lang.Integer.valueOf(r35)     // Catch:{ all -> 0x0922 }
            goto L_0x073a
        L_0x070a:
            java.lang.Class r13 = java.lang.Long.TYPE     // Catch:{ all -> 0x0922 }
            if (r11 != r13) goto L_0x0713
            java.lang.Long r10 = java.lang.Long.valueOf(r25)     // Catch:{ all -> 0x0922 }
            goto L_0x073a
        L_0x0713:
            java.lang.Class r13 = java.lang.Float.TYPE     // Catch:{ all -> 0x0922 }
            if (r11 != r13) goto L_0x071c
            java.lang.Float r10 = java.lang.Float.valueOf(r24)     // Catch:{ all -> 0x0922 }
            goto L_0x073a
        L_0x071c:
            java.lang.Class r13 = java.lang.Double.TYPE     // Catch:{ all -> 0x0922 }
            if (r11 != r13) goto L_0x0725
            java.lang.Double r10 = java.lang.Double.valueOf(r22)     // Catch:{ all -> 0x0922 }
            goto L_0x073a
        L_0x0725:
            java.lang.Class r13 = java.lang.Boolean.TYPE     // Catch:{ all -> 0x0922 }
            if (r11 != r13) goto L_0x072c
            java.lang.Boolean r10 = java.lang.Boolean.FALSE     // Catch:{ all -> 0x0922 }
            goto L_0x073a
        L_0x072c:
            java.lang.Class<java.lang.String> r13 = java.lang.String.class
            if (r11 != r13) goto L_0x073a
            int r11 = r12.parserFeatures     // Catch:{ all -> 0x0922 }
            com.alibaba.fastjson.parser.Feature r12 = com.alibaba.fastjson.parser.Feature.InitStringFieldAsEmpty     // Catch:{ all -> 0x0922 }
            int r12 = r12.mask     // Catch:{ all -> 0x0922 }
            r11 = r11 & r12
            if (r11 == 0) goto L_0x073a
            r10 = r4
        L_0x073a:
            r13 = 0
            goto L_0x0778
        L_0x073c:
            com.alibaba.fastjson.util.JavaBeanInfo r11 = r8.beanInfo     // Catch:{ all -> 0x0922 }
            java.lang.reflect.Type[] r11 = r11.creatorConstructorParameterTypes     // Catch:{ all -> 0x0922 }
            if (r11 == 0) goto L_0x073a
            com.alibaba.fastjson.util.JavaBeanInfo r11 = r8.beanInfo     // Catch:{ all -> 0x0922 }
            java.lang.reflect.Type[] r11 = r11.creatorConstructorParameterTypes     // Catch:{ all -> 0x0922 }
            int r11 = r11.length     // Catch:{ all -> 0x0922 }
            if (r7 >= r11) goto L_0x073a
            com.alibaba.fastjson.util.JavaBeanInfo r11 = r8.beanInfo     // Catch:{ all -> 0x0922 }
            java.lang.reflect.Type[] r11 = r11.creatorConstructorParameterTypes     // Catch:{ all -> 0x0922 }
            r11 = r11[r7]     // Catch:{ all -> 0x0922 }
            boolean r12 = r11 instanceof java.lang.Class     // Catch:{ all -> 0x0922 }
            if (r12 == 0) goto L_0x073a
            java.lang.Class r11 = (java.lang.Class) r11     // Catch:{ all -> 0x0922 }
            boolean r12 = r11.isInstance(r10)     // Catch:{ all -> 0x0922 }
            if (r12 != 0) goto L_0x073a
            boolean r12 = r10 instanceof java.util.List     // Catch:{ all -> 0x0922 }
            if (r12 == 0) goto L_0x073a
            r12 = r10
            java.util.List r12 = (java.util.List) r12     // Catch:{ all -> 0x0922 }
            int r13 = r12.size()     // Catch:{ all -> 0x0922 }
            r15 = 1
            if (r13 != r15) goto L_0x073a
            r13 = 0
            java.lang.Object r15 = r12.get(r13)     // Catch:{ all -> 0x0922 }
            boolean r11 = r11.isInstance(r15)     // Catch:{ all -> 0x0922 }
            if (r11 == 0) goto L_0x0778
            java.lang.Object r10 = r12.get(r13)     // Catch:{ all -> 0x0922 }
        L_0x0778:
            r6[r7] = r10     // Catch:{ all -> 0x0922 }
            int r7 = r7 + 1
            r35 = 0
            goto L_0x06d8
        L_0x0780:
            r13 = 0
            com.alibaba.fastjson.util.JavaBeanInfo r6 = r8.beanInfo     // Catch:{ all -> 0x0922 }
            com.alibaba.fastjson.util.FieldInfo[] r6 = r6.fields     // Catch:{ all -> 0x0922 }
            int r7 = r6.length     // Catch:{ all -> 0x0922 }
            java.lang.Object[] r10 = new java.lang.Object[r7]     // Catch:{ all -> 0x0922 }
            r11 = 0
        L_0x0789:
            if (r11 >= r7) goto L_0x07f3
            r12 = r6[r11]     // Catch:{ all -> 0x0922 }
            java.lang.String r15 = r12.name     // Catch:{ all -> 0x0922 }
            java.lang.Object r15 = r1.get(r15)     // Catch:{ all -> 0x0922 }
            if (r15 != 0) goto L_0x07e9
            java.lang.reflect.Type r13 = r12.fieldType     // Catch:{ all -> 0x0922 }
            r33 = r4
            java.lang.Class r4 = java.lang.Byte.TYPE     // Catch:{ all -> 0x0922 }
            if (r13 != r4) goto L_0x07a3
            r4 = 0
            java.lang.Byte r15 = java.lang.Byte.valueOf(r4)     // Catch:{ all -> 0x0922 }
            goto L_0x07eb
        L_0x07a3:
            java.lang.Class r4 = java.lang.Short.TYPE     // Catch:{ all -> 0x0922 }
            if (r13 != r4) goto L_0x07ad
            r4 = 0
            java.lang.Short r15 = java.lang.Short.valueOf(r4)     // Catch:{ all -> 0x0922 }
            goto L_0x07eb
        L_0x07ad:
            java.lang.Class r4 = java.lang.Integer.TYPE     // Catch:{ all -> 0x0922 }
            if (r13 != r4) goto L_0x07b7
            r4 = 0
            java.lang.Integer r15 = java.lang.Integer.valueOf(r4)     // Catch:{ all -> 0x0922 }
            goto L_0x07eb
        L_0x07b7:
            java.lang.Class r4 = java.lang.Long.TYPE     // Catch:{ all -> 0x0922 }
            if (r13 != r4) goto L_0x07c0
            java.lang.Long r15 = java.lang.Long.valueOf(r25)     // Catch:{ all -> 0x0922 }
            goto L_0x07eb
        L_0x07c0:
            java.lang.Class r4 = java.lang.Float.TYPE     // Catch:{ all -> 0x0922 }
            if (r13 != r4) goto L_0x07c9
            java.lang.Float r15 = java.lang.Float.valueOf(r24)     // Catch:{ all -> 0x0922 }
            goto L_0x07eb
        L_0x07c9:
            java.lang.Class r4 = java.lang.Double.TYPE     // Catch:{ all -> 0x0922 }
            if (r13 != r4) goto L_0x07d2
            java.lang.Double r15 = java.lang.Double.valueOf(r22)     // Catch:{ all -> 0x0922 }
            goto L_0x07eb
        L_0x07d2:
            java.lang.Class r4 = java.lang.Boolean.TYPE     // Catch:{ all -> 0x0922 }
            if (r13 != r4) goto L_0x07d9
            java.lang.Boolean r15 = java.lang.Boolean.FALSE     // Catch:{ all -> 0x0922 }
            goto L_0x07eb
        L_0x07d9:
            java.lang.Class<java.lang.String> r4 = java.lang.String.class
            if (r13 != r4) goto L_0x07eb
            int r4 = r12.parserFeatures     // Catch:{ all -> 0x0922 }
            com.alibaba.fastjson.parser.Feature r12 = com.alibaba.fastjson.parser.Feature.InitStringFieldAsEmpty     // Catch:{ all -> 0x0922 }
            int r12 = r12.mask     // Catch:{ all -> 0x0922 }
            r4 = r4 & r12
            if (r4 == 0) goto L_0x07eb
            r15 = r33
            goto L_0x07eb
        L_0x07e9:
            r33 = r4
        L_0x07eb:
            r10[r11] = r15     // Catch:{ all -> 0x0922 }
            int r11 = r11 + 1
            r4 = r33
            r13 = 0
            goto L_0x0789
        L_0x07f3:
            r6 = r10
        L_0x07f4:
            com.alibaba.fastjson.util.JavaBeanInfo r4 = r8.beanInfo     // Catch:{ all -> 0x0922 }
            java.lang.reflect.Constructor<?> r4 = r4.creatorConstructor     // Catch:{ all -> 0x0922 }
            if (r4 == 0) goto L_0x08c2
            com.alibaba.fastjson.util.JavaBeanInfo r4 = r8.beanInfo     // Catch:{ all -> 0x0922 }
            boolean r4 = r4.f4kotlin     // Catch:{ all -> 0x0922 }
            if (r4 == 0) goto L_0x0827
            r4 = 0
        L_0x0801:
            int r7 = r6.length     // Catch:{ all -> 0x0922 }
            if (r4 >= r7) goto L_0x0827
            r7 = r6[r4]     // Catch:{ all -> 0x0922 }
            if (r7 != 0) goto L_0x0824
            com.alibaba.fastjson.util.JavaBeanInfo r7 = r8.beanInfo     // Catch:{ all -> 0x0922 }
            com.alibaba.fastjson.util.FieldInfo[] r7 = r7.fields     // Catch:{ all -> 0x0922 }
            if (r7 == 0) goto L_0x0824
            com.alibaba.fastjson.util.JavaBeanInfo r7 = r8.beanInfo     // Catch:{ all -> 0x0922 }
            com.alibaba.fastjson.util.FieldInfo[] r7 = r7.fields     // Catch:{ all -> 0x0922 }
            int r7 = r7.length     // Catch:{ all -> 0x0922 }
            if (r4 >= r7) goto L_0x0824
            com.alibaba.fastjson.util.JavaBeanInfo r7 = r8.beanInfo     // Catch:{ all -> 0x0922 }
            com.alibaba.fastjson.util.FieldInfo[] r7 = r7.fields     // Catch:{ all -> 0x0922 }
            r4 = r7[r4]     // Catch:{ all -> 0x0922 }
            java.lang.Class<?> r4 = r4.fieldClass     // Catch:{ all -> 0x0922 }
            java.lang.Class<java.lang.String> r7 = java.lang.String.class
            if (r4 != r7) goto L_0x0827
            r27 = 1
            goto L_0x0829
        L_0x0824:
            int r4 = r4 + 1
            goto L_0x0801
        L_0x0827:
            r27 = 0
        L_0x0829:
            if (r27 == 0) goto L_0x0860
            com.alibaba.fastjson.util.JavaBeanInfo r4 = r8.beanInfo     // Catch:{ Exception -> 0x0894 }
            java.lang.reflect.Constructor<?> r4 = r4.kotlinDefaultConstructor     // Catch:{ Exception -> 0x0894 }
            if (r4 == 0) goto L_0x0860
            com.alibaba.fastjson.util.JavaBeanInfo r4 = r8.beanInfo     // Catch:{ Exception -> 0x0894 }
            java.lang.reflect.Constructor<?> r4 = r4.kotlinDefaultConstructor     // Catch:{ Exception -> 0x0894 }
            r7 = 0
            java.lang.Object[] r10 = new java.lang.Object[r7]     // Catch:{ Exception -> 0x0894 }
            java.lang.Object r4 = r4.newInstance(r10)     // Catch:{ Exception -> 0x0894 }
            r7 = 0
        L_0x083d:
            int r10 = r6.length     // Catch:{ Exception -> 0x085d }
            if (r7 >= r10) goto L_0x0868
            r10 = r6[r7]     // Catch:{ Exception -> 0x085d }
            if (r10 == 0) goto L_0x085a
            com.alibaba.fastjson.util.JavaBeanInfo r11 = r8.beanInfo     // Catch:{ Exception -> 0x085d }
            com.alibaba.fastjson.util.FieldInfo[] r11 = r11.fields     // Catch:{ Exception -> 0x085d }
            if (r11 == 0) goto L_0x085a
            com.alibaba.fastjson.util.JavaBeanInfo r11 = r8.beanInfo     // Catch:{ Exception -> 0x085d }
            com.alibaba.fastjson.util.FieldInfo[] r11 = r11.fields     // Catch:{ Exception -> 0x085d }
            int r11 = r11.length     // Catch:{ Exception -> 0x085d }
            if (r7 >= r11) goto L_0x085a
            com.alibaba.fastjson.util.JavaBeanInfo r11 = r8.beanInfo     // Catch:{ Exception -> 0x085d }
            com.alibaba.fastjson.util.FieldInfo[] r11 = r11.fields     // Catch:{ Exception -> 0x085d }
            r11 = r11[r7]     // Catch:{ Exception -> 0x085d }
            r11.set(r4, r10)     // Catch:{ Exception -> 0x085d }
        L_0x085a:
            int r7 = r7 + 1
            goto L_0x083d
        L_0x085d:
            r0 = move-exception
            r1 = r0
            goto L_0x0897
        L_0x0860:
            com.alibaba.fastjson.util.JavaBeanInfo r4 = r8.beanInfo     // Catch:{ Exception -> 0x0894 }
            java.lang.reflect.Constructor<?> r4 = r4.creatorConstructor     // Catch:{ Exception -> 0x0894 }
            java.lang.Object r4 = r4.newInstance(r6)     // Catch:{ Exception -> 0x0894 }
        L_0x0868:
            if (r2 == 0) goto L_0x0892
            java.util.Set r1 = r1.entrySet()     // Catch:{ all -> 0x08be }
            java.util.Iterator r1 = r1.iterator()     // Catch:{ all -> 0x08be }
        L_0x0872:
            boolean r2 = r1.hasNext()     // Catch:{ all -> 0x08be }
            if (r2 == 0) goto L_0x0892
            java.lang.Object r2 = r1.next()     // Catch:{ all -> 0x08be }
            java.util.Map$Entry r2 = (java.util.Map.Entry) r2     // Catch:{ all -> 0x08be }
            java.lang.Object r6 = r2.getKey()     // Catch:{ all -> 0x08be }
            java.lang.String r6 = (java.lang.String) r6     // Catch:{ all -> 0x08be }
            com.alibaba.fastjson.parser.deserializer.FieldDeserializer r6 = r8.getFieldDeserializer((java.lang.String) r6)     // Catch:{ all -> 0x08be }
            if (r6 == 0) goto L_0x0872
            java.lang.Object r2 = r2.getValue()     // Catch:{ all -> 0x08be }
            r6.setValue((java.lang.Object) r4, (java.lang.Object) r2)     // Catch:{ all -> 0x08be }
            goto L_0x0872
        L_0x0892:
            r14 = r4
            goto L_0x08f4
        L_0x0894:
            r0 = move-exception
            r1 = r0
            r4 = r14
        L_0x0897:
            com.alibaba.fastjson.JSONException r6 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x08be }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x08be }
            r7.<init>()     // Catch:{ all -> 0x08be }
            java.lang.String r10 = "create instance error, "
            r7.append(r10)     // Catch:{ all -> 0x08be }
            r7.append(r2)     // Catch:{ all -> 0x08be }
            java.lang.String r2 = ", "
            r7.append(r2)     // Catch:{ all -> 0x08be }
            com.alibaba.fastjson.util.JavaBeanInfo r2 = r8.beanInfo     // Catch:{ all -> 0x08be }
            java.lang.reflect.Constructor<?> r2 = r2.creatorConstructor     // Catch:{ all -> 0x08be }
            java.lang.String r2 = r2.toGenericString()     // Catch:{ all -> 0x08be }
            r7.append(r2)     // Catch:{ all -> 0x08be }
            java.lang.String r2 = r7.toString()     // Catch:{ all -> 0x08be }
            r6.<init>(r2, r1)     // Catch:{ all -> 0x08be }
            throw r6     // Catch:{ all -> 0x08be }
        L_0x08be:
            r0 = move-exception
            r1 = r0
            r14 = r4
            goto L_0x0924
        L_0x08c2:
            com.alibaba.fastjson.util.JavaBeanInfo r1 = r8.beanInfo     // Catch:{ all -> 0x0922 }
            java.lang.reflect.Method r1 = r1.factoryMethod     // Catch:{ all -> 0x0922 }
            if (r1 == 0) goto L_0x08f4
            com.alibaba.fastjson.util.JavaBeanInfo r1 = r8.beanInfo     // Catch:{ Exception -> 0x08d3 }
            java.lang.reflect.Method r1 = r1.factoryMethod     // Catch:{ Exception -> 0x08d3 }
            r4 = 0
            java.lang.Object r1 = r1.invoke(r4, r6)     // Catch:{ Exception -> 0x08d3 }
            r14 = r1
            goto L_0x08f4
        L_0x08d3:
            r0 = move-exception
            r1 = r0
            com.alibaba.fastjson.JSONException r2 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x0922 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0922 }
            r4.<init>()     // Catch:{ all -> 0x0922 }
            java.lang.String r6 = "create factory method error, "
            r4.append(r6)     // Catch:{ all -> 0x0922 }
            com.alibaba.fastjson.util.JavaBeanInfo r6 = r8.beanInfo     // Catch:{ all -> 0x0922 }
            java.lang.reflect.Method r6 = r6.factoryMethod     // Catch:{ all -> 0x0922 }
            java.lang.String r6 = r6.toString()     // Catch:{ all -> 0x0922 }
            r4.append(r6)     // Catch:{ all -> 0x0922 }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x0922 }
            r2.<init>(r4, r1)     // Catch:{ all -> 0x0922 }
            throw r2     // Catch:{ all -> 0x0922 }
        L_0x08f4:
            if (r5 == 0) goto L_0x08fb
            r5.object = r14     // Catch:{ all -> 0x0922 }
            goto L_0x08fb
        L_0x08f9:
            r3 = r30
        L_0x08fb:
            com.alibaba.fastjson.util.JavaBeanInfo r1 = r8.beanInfo     // Catch:{ all -> 0x0922 }
            java.lang.reflect.Method r1 = r1.buildMethod     // Catch:{ all -> 0x0922 }
            if (r1 != 0) goto L_0x0909
            if (r5 == 0) goto L_0x0905
            r5.object = r14
        L_0x0905:
            r9.setContext(r3)
            return r14
        L_0x0909:
            r2 = 0
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ Exception -> 0x0918 }
            java.lang.Object r1 = r1.invoke(r14, r2)     // Catch:{ Exception -> 0x0918 }
            if (r5 == 0) goto L_0x0914
            r5.object = r14
        L_0x0914:
            r9.setContext(r3)
            return r1
        L_0x0918:
            r0 = move-exception
            r1 = r0
            com.alibaba.fastjson.JSONException r2 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x0922 }
            java.lang.String r4 = "build object error"
            r2.<init>(r4, r1)     // Catch:{ all -> 0x0922 }
            throw r2     // Catch:{ all -> 0x0922 }
        L_0x0922:
            r0 = move-exception
        L_0x0923:
            r1 = r0
        L_0x0924:
            r15 = r5
            goto L_0x098e
        L_0x0927:
            r3 = r30
            r1 = 0
            r4 = 0
            int r5 = r12.token()     // Catch:{ all -> 0x0978 }
            r6 = 18
            if (r5 == r6) goto L_0x094f
            int r5 = r12.token()     // Catch:{ all -> 0x0978 }
            r6 = 1
            if (r5 == r6) goto L_0x094f
        L_0x093a:
            r5 = r29
        L_0x093c:
            int r7 = r10 + 1
            r10 = r33
            r4 = r7
            r1 = r14
            r6 = r19
            r2 = r20
            r14 = 16
            r7 = r3
            r3 = r16
            r20 = r18
            goto L_0x0181
        L_0x094f:
            com.alibaba.fastjson.JSONException r1 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x0978 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0978 }
            r2.<init>()     // Catch:{ all -> 0x0978 }
            java.lang.String r4 = "syntax error, unexpect token "
            r2.append(r4)     // Catch:{ all -> 0x0978 }
            int r4 = r12.token()     // Catch:{ all -> 0x0978 }
            java.lang.String r4 = com.alibaba.fastjson.parser.JSONToken.name(r4)     // Catch:{ all -> 0x0978 }
            r2.append(r4)     // Catch:{ all -> 0x0978 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x0978 }
            r1.<init>(r2)     // Catch:{ all -> 0x0978 }
            throw r1     // Catch:{ all -> 0x0978 }
        L_0x096e:
            r3 = r30
            com.alibaba.fastjson.JSONException r1 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x0978 }
            java.lang.String r2 = "syntax error, unexpect token ':'"
            r1.<init>(r2)     // Catch:{ all -> 0x0978 }
            throw r1     // Catch:{ all -> 0x0978 }
        L_0x0978:
            r0 = move-exception
            goto L_0x097d
        L_0x097a:
            r0 = move-exception
            r3 = r30
        L_0x097d:
            r1 = r0
            goto L_0x063d
        L_0x0980:
            r0 = move-exception
            r14 = r1
            r37 = r5
            r3 = r7
            r15 = r37
            goto L_0x098d
        L_0x0988:
            r0 = move-exception
            r3 = r7
            r4 = r15
            r14 = r35
        L_0x098d:
            r1 = r0
        L_0x098e:
            if (r15 == 0) goto L_0x0992
            r15.object = r14
        L_0x0992:
            r9.setContext(r3)
            throw r1
        L_0x0996:
            java.lang.Object r1 = r32.parse()
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer.deserialze(com.alibaba.fastjson.parser.DefaultJSONParser, java.lang.reflect.Type, java.lang.Object, java.lang.Object, int, int[]):java.lang.Object");
    }

    /* access modifiers changed from: protected */
    public Enum scanEnum(JSONLexerBase jSONLexerBase, char[] cArr, ObjectDeserializer objectDeserializer) {
        EnumDeserializer enumDeserializer = objectDeserializer instanceof EnumDeserializer ? (EnumDeserializer) objectDeserializer : null;
        if (enumDeserializer == null) {
            jSONLexerBase.matchStat = -1;
            return null;
        }
        long scanEnumSymbol = jSONLexerBase.scanEnumSymbol(cArr);
        if (jSONLexerBase.matchStat <= 0) {
            return null;
        }
        Enum enumByHashCode = enumDeserializer.getEnumByHashCode(scanEnumSymbol);
        if (enumByHashCode == null) {
            if (scanEnumSymbol == -3750763034362895579L) {
                return null;
            }
            if (jSONLexerBase.isEnabled(Feature.ErrorOnEnumNotMatch)) {
                throw new JSONException("not match enum value, " + enumDeserializer.enumClass);
            }
        }
        return enumByHashCode;
    }

    public boolean parseField(DefaultJSONParser defaultJSONParser, String str, Object obj, Type type, Map<String, Object> map) {
        return parseField(defaultJSONParser, str, obj, type, map, (int[]) null);
    }

    /* JADX WARNING: type inference failed for: r17v0, types: [boolean, int] */
    /* JADX WARNING: type inference failed for: r17v1 */
    /* JADX WARNING: type inference failed for: r17v3 */
    /* JADX WARNING: type inference failed for: r17v4 */
    /* JADX WARNING: Removed duplicated region for block: B:101:0x01fb  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x010e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean parseField(com.alibaba.fastjson.parser.DefaultJSONParser r22, java.lang.String r23, java.lang.Object r24, java.lang.reflect.Type r25, java.util.Map<java.lang.String, java.lang.Object> r26, int[] r27) {
        /*
            r21 = this;
            r1 = r21
            r0 = r22
            r11 = r23
            r12 = r24
            r13 = r25
            r14 = r26
            r15 = r27
            com.alibaba.fastjson.parser.JSONLexer r10 = r0.lexer
            com.alibaba.fastjson.parser.Feature r2 = com.alibaba.fastjson.parser.Feature.DisableFieldSmartMatch
            int r2 = r2.mask
            boolean r3 = r10.isEnabled((int) r2)
            if (r3 != 0) goto L_0x0027
            com.alibaba.fastjson.util.JavaBeanInfo r3 = r1.beanInfo
            int r3 = r3.parserFeatures
            r2 = r2 & r3
            if (r2 == 0) goto L_0x0022
            goto L_0x0027
        L_0x0022:
            com.alibaba.fastjson.parser.deserializer.FieldDeserializer r2 = r1.smartMatch(r11, r15)
            goto L_0x002b
        L_0x0027:
            com.alibaba.fastjson.parser.deserializer.FieldDeserializer r2 = r1.getFieldDeserializer((java.lang.String) r11)
        L_0x002b:
            com.alibaba.fastjson.parser.Feature r3 = com.alibaba.fastjson.parser.Feature.SupportNonPublicField
            int r3 = r3.mask
            r16 = 0
            r9 = 1
            if (r2 != 0) goto L_0x0104
            boolean r4 = r10.isEnabled((int) r3)
            if (r4 != 0) goto L_0x0041
            com.alibaba.fastjson.util.JavaBeanInfo r4 = r1.beanInfo
            int r4 = r4.parserFeatures
            r3 = r3 & r4
            if (r3 == 0) goto L_0x0104
        L_0x0041:
            java.util.concurrent.ConcurrentMap<java.lang.String, java.lang.Object> r3 = r1.extraFieldDeserializers
            if (r3 != 0) goto L_0x00b1
            java.util.concurrent.ConcurrentHashMap r3 = new java.util.concurrent.ConcurrentHashMap
            r4 = 1061158912(0x3f400000, float:0.75)
            r3.<init>(r9, r4, r9)
            java.lang.Class<?> r4 = r1.clazz
        L_0x004e:
            if (r4 == 0) goto L_0x00ac
            java.lang.Class<java.lang.Object> r5 = java.lang.Object.class
            if (r4 == r5) goto L_0x00ac
            java.lang.reflect.Field[] r5 = r4.getDeclaredFields()
            int r6 = r5.length
            r7 = 0
        L_0x005a:
            if (r7 >= r6) goto L_0x00a4
            r8 = r5[r7]
            java.lang.String r9 = r8.getName()
            com.alibaba.fastjson.parser.deserializer.FieldDeserializer r18 = r1.getFieldDeserializer((java.lang.String) r9)
            if (r18 == 0) goto L_0x0069
            goto L_0x0098
        L_0x0069:
            int r18 = r8.getModifiers()
            r19 = r18 & 16
            if (r19 != 0) goto L_0x0098
            r18 = r18 & 8
            if (r18 == 0) goto L_0x0076
            goto L_0x0098
        L_0x0076:
            r18 = r2
            java.lang.Class<com.alibaba.fastjson.annotation.JSONField> r2 = com.alibaba.fastjson.annotation.JSONField.class
            java.lang.annotation.Annotation r2 = com.alibaba.fastjson.util.TypeUtils.getAnnotation((java.lang.reflect.Field) r8, r2)
            com.alibaba.fastjson.annotation.JSONField r2 = (com.alibaba.fastjson.annotation.JSONField) r2
            if (r2 == 0) goto L_0x0092
            java.lang.String r2 = r2.name()
            r19 = r5
            java.lang.String r5 = ""
            boolean r5 = r5.equals(r2)
            if (r5 != 0) goto L_0x0094
            r9 = r2
            goto L_0x0094
        L_0x0092:
            r19 = r5
        L_0x0094:
            r3.put(r9, r8)
            goto L_0x009c
        L_0x0098:
            r18 = r2
            r19 = r5
        L_0x009c:
            int r7 = r7 + 1
            r2 = r18
            r5 = r19
            r9 = 1
            goto L_0x005a
        L_0x00a4:
            r18 = r2
            java.lang.Class r4 = r4.getSuperclass()
            r9 = 1
            goto L_0x004e
        L_0x00ac:
            r18 = r2
            r1.extraFieldDeserializers = r3
            goto L_0x00b3
        L_0x00b1:
            r18 = r2
        L_0x00b3:
            java.util.concurrent.ConcurrentMap<java.lang.String, java.lang.Object> r2 = r1.extraFieldDeserializers
            java.lang.Object r2 = r2.get(r11)
            if (r2 == 0) goto L_0x0106
            boolean r3 = r2 instanceof com.alibaba.fastjson.parser.deserializer.FieldDeserializer
            if (r3 == 0) goto L_0x00c5
            com.alibaba.fastjson.parser.deserializer.FieldDeserializer r2 = (com.alibaba.fastjson.parser.deserializer.FieldDeserializer) r2
            r15 = r10
            r17 = 1
            goto L_0x010b
        L_0x00c5:
            r7 = r2
            java.lang.reflect.Field r7 = (java.lang.reflect.Field) r7
            r9 = 1
            r7.setAccessible(r9)
            com.alibaba.fastjson.util.FieldInfo r8 = new com.alibaba.fastjson.util.FieldInfo
            java.lang.Class r4 = r7.getDeclaringClass()
            java.lang.Class r5 = r7.getType()
            java.lang.reflect.Type r6 = r7.getGenericType()
            r17 = 0
            r18 = 0
            r19 = 0
            r2 = r8
            r3 = r23
            r20 = r8
            r8 = r17
            r17 = 1
            r9 = r18
            r15 = r10
            r10 = r19
            r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10)
            com.alibaba.fastjson.parser.deserializer.DefaultFieldDeserializer r2 = new com.alibaba.fastjson.parser.deserializer.DefaultFieldDeserializer
            com.alibaba.fastjson.parser.ParserConfig r3 = r22.getConfig()
            java.lang.Class<?> r4 = r1.clazz
            r5 = r20
            r2.<init>(r3, r4, r5)
            java.util.concurrent.ConcurrentMap<java.lang.String, java.lang.Object> r3 = r1.extraFieldDeserializers
            r3.put(r11, r2)
            goto L_0x010b
        L_0x0104:
            r18 = r2
        L_0x0106:
            r15 = r10
            r17 = 1
            r2 = r18
        L_0x010b:
            r3 = -1
            if (r2 != 0) goto L_0x01fb
            com.alibaba.fastjson.parser.Feature r2 = com.alibaba.fastjson.parser.Feature.IgnoreNotMatch
            boolean r2 = r15.isEnabled((com.alibaba.fastjson.parser.Feature) r2)
            if (r2 == 0) goto L_0x01d6
            r2 = 0
            r4 = -1
        L_0x0118:
            com.alibaba.fastjson.parser.deserializer.FieldDeserializer[] r5 = r1.sortedFieldDeserializers
            int r6 = r5.length
            if (r2 >= r6) goto L_0x01c0
            r5 = r5[r2]
            com.alibaba.fastjson.util.FieldInfo r6 = r5.fieldInfo
            boolean r7 = r6.unwrapped
            if (r7 == 0) goto L_0x01bc
            boolean r7 = r5 instanceof com.alibaba.fastjson.parser.deserializer.DefaultFieldDeserializer
            if (r7 == 0) goto L_0x01bc
            java.lang.reflect.Field r7 = r6.field
            java.lang.String r8 = "parse unwrapped field error."
            if (r7 == 0) goto L_0x0197
            r7 = r5
            com.alibaba.fastjson.parser.deserializer.DefaultFieldDeserializer r7 = (com.alibaba.fastjson.parser.deserializer.DefaultFieldDeserializer) r7
            com.alibaba.fastjson.parser.ParserConfig r9 = r22.getConfig()
            com.alibaba.fastjson.parser.deserializer.ObjectDeserializer r9 = r7.getFieldValueDeserilizer(r9)
            boolean r10 = r9 instanceof com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer
            if (r10 == 0) goto L_0x016c
            r10 = r9
            com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer r10 = (com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer) r10
            com.alibaba.fastjson.parser.deserializer.FieldDeserializer r10 = r10.getFieldDeserializer((java.lang.String) r11)
            if (r10 == 0) goto L_0x01bc
            java.lang.reflect.Field r4 = r6.field     // Catch:{ Exception -> 0x0165 }
            java.lang.Object r4 = r4.get(r12)     // Catch:{ Exception -> 0x0165 }
            if (r4 != 0) goto L_0x015a
            com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer r9 = (com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer) r9     // Catch:{ Exception -> 0x0165 }
            java.lang.reflect.Type r4 = r6.fieldType     // Catch:{ Exception -> 0x0165 }
            java.lang.Object r4 = r9.createInstance((com.alibaba.fastjson.parser.DefaultJSONParser) r0, (java.lang.reflect.Type) r4)     // Catch:{ Exception -> 0x0165 }
            r5.setValue((java.lang.Object) r12, (java.lang.Object) r4)     // Catch:{ Exception -> 0x0165 }
        L_0x015a:
            int r5 = r7.getFastMatchToken()     // Catch:{ Exception -> 0x0165 }
            r15.nextTokenWithColon(r5)     // Catch:{ Exception -> 0x0165 }
            r10.parseField(r0, r4, r13, r14)     // Catch:{ Exception -> 0x0165 }
            goto L_0x01b3
        L_0x0165:
            r0 = move-exception
            com.alibaba.fastjson.JSONException r2 = new com.alibaba.fastjson.JSONException
            r2.<init>(r8, r0)
            throw r2
        L_0x016c:
            boolean r7 = r9 instanceof com.alibaba.fastjson.parser.deserializer.MapDeserializer
            if (r7 == 0) goto L_0x01bc
            com.alibaba.fastjson.parser.deserializer.MapDeserializer r9 = (com.alibaba.fastjson.parser.deserializer.MapDeserializer) r9
            java.lang.reflect.Field r4 = r6.field     // Catch:{ Exception -> 0x0190 }
            java.lang.Object r4 = r4.get(r12)     // Catch:{ Exception -> 0x0190 }
            java.util.Map r4 = (java.util.Map) r4     // Catch:{ Exception -> 0x0190 }
            if (r4 != 0) goto L_0x0185
            java.lang.reflect.Type r4 = r6.fieldType     // Catch:{ Exception -> 0x0190 }
            java.util.Map r4 = r9.createMap(r4)     // Catch:{ Exception -> 0x0190 }
            r5.setValue((java.lang.Object) r12, (java.lang.Object) r4)     // Catch:{ Exception -> 0x0190 }
        L_0x0185:
            r15.nextTokenWithColon()     // Catch:{ Exception -> 0x0190 }
            java.lang.Object r5 = r22.parse(r23)     // Catch:{ Exception -> 0x0190 }
            r4.put(r11, r5)     // Catch:{ Exception -> 0x0190 }
            goto L_0x01b3
        L_0x0190:
            r0 = move-exception
            com.alibaba.fastjson.JSONException r2 = new com.alibaba.fastjson.JSONException
            r2.<init>(r8, r0)
            throw r2
        L_0x0197:
            java.lang.reflect.Method r5 = r6.method
            java.lang.Class[] r5 = r5.getParameterTypes()
            int r5 = r5.length
            r7 = 2
            if (r5 != r7) goto L_0x01bc
            r15.nextTokenWithColon()
            java.lang.Object r4 = r22.parse(r23)
            java.lang.reflect.Method r5 = r6.method     // Catch:{ Exception -> 0x01b5 }
            java.lang.Object[] r6 = new java.lang.Object[r7]     // Catch:{ Exception -> 0x01b5 }
            r6[r16] = r11     // Catch:{ Exception -> 0x01b5 }
            r6[r17] = r4     // Catch:{ Exception -> 0x01b5 }
            r5.invoke(r12, r6)     // Catch:{ Exception -> 0x01b5 }
        L_0x01b3:
            r4 = r2
            goto L_0x01bc
        L_0x01b5:
            r0 = move-exception
            com.alibaba.fastjson.JSONException r2 = new com.alibaba.fastjson.JSONException
            r2.<init>(r8, r0)
            throw r2
        L_0x01bc:
            int r2 = r2 + 1
            goto L_0x0118
        L_0x01c0:
            if (r4 == r3) goto L_0x01d2
            r5 = r27
            if (r5 == 0) goto L_0x01d1
            int r0 = r4 / 32
            int r4 = r4 % 32
            r2 = r5[r0]
            int r3 = r17 << r4
            r2 = r2 | r3
            r5[r0] = r2
        L_0x01d1:
            return r17
        L_0x01d2:
            r0.parseExtra(r12, r11)
            return r16
        L_0x01d6:
            com.alibaba.fastjson.JSONException r0 = new com.alibaba.fastjson.JSONException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "setter not found, class "
            r2.append(r3)
            java.lang.Class<?> r3 = r1.clazz
            java.lang.String r3 = r3.getName()
            r2.append(r3)
            java.lang.String r3 = ", property "
            r2.append(r3)
            r2.append(r11)
            java.lang.String r2 = r2.toString()
            r0.<init>(r2)
            throw r0
        L_0x01fb:
            r5 = r27
            r4 = r15
            r6 = 0
        L_0x01ff:
            com.alibaba.fastjson.parser.deserializer.FieldDeserializer[] r7 = r1.sortedFieldDeserializers
            int r8 = r7.length
            if (r6 >= r8) goto L_0x020c
            r7 = r7[r6]
            if (r7 != r2) goto L_0x0209
            goto L_0x020d
        L_0x0209:
            int r6 = r6 + 1
            goto L_0x01ff
        L_0x020c:
            r6 = -1
        L_0x020d:
            if (r6 == r3) goto L_0x0223
            if (r5 == 0) goto L_0x0223
            java.lang.String r3 = "_"
            boolean r3 = r11.startsWith(r3)
            if (r3 == 0) goto L_0x0223
            boolean r3 = isSetFlag(r6, r5)
            if (r3 == 0) goto L_0x0223
            r0.parseExtra(r12, r11)
            return r16
        L_0x0223:
            int r3 = r2.getFastMatchToken()
            r4.nextTokenWithColon(r3)
            r2.parseField(r0, r12, r13, r14)
            if (r5 == 0) goto L_0x023a
            int r0 = r6 / 32
            int r6 = r6 % 32
            r2 = r5[r0]
            int r3 = r17 << r6
            r2 = r2 | r3
            r5[r0] = r2
        L_0x023a:
            return r17
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer.parseField(com.alibaba.fastjson.parser.DefaultJSONParser, java.lang.String, java.lang.Object, java.lang.reflect.Type, java.util.Map, int[]):boolean");
    }

    public FieldDeserializer smartMatch(String str) {
        return smartMatch(str, (int[]) null);
    }

    public FieldDeserializer smartMatch(String str, int[] iArr) {
        boolean z;
        if (str == null) {
            return null;
        }
        FieldDeserializer fieldDeserializer = getFieldDeserializer(str, iArr);
        if (fieldDeserializer == null) {
            long fnv1a_64_lower = TypeUtils.fnv1a_64_lower(str);
            int i = 0;
            if (this.smartMatchHashArray == null) {
                long[] jArr = new long[this.sortedFieldDeserializers.length];
                int i2 = 0;
                while (true) {
                    FieldDeserializer[] fieldDeserializerArr = this.sortedFieldDeserializers;
                    if (i2 >= fieldDeserializerArr.length) {
                        break;
                    }
                    jArr[i2] = TypeUtils.fnv1a_64_lower(fieldDeserializerArr[i2].fieldInfo.name);
                    i2++;
                }
                Arrays.sort(jArr);
                this.smartMatchHashArray = jArr;
            }
            int binarySearch = Arrays.binarySearch(this.smartMatchHashArray, fnv1a_64_lower);
            if (binarySearch < 0) {
                z = str.startsWith("is");
                if (z) {
                    binarySearch = Arrays.binarySearch(this.smartMatchHashArray, TypeUtils.fnv1a_64_lower(str.substring(2)));
                }
            } else {
                z = false;
            }
            if (binarySearch >= 0) {
                if (this.smartMatchHashArrayMapping == null) {
                    short[] sArr = new short[this.smartMatchHashArray.length];
                    Arrays.fill(sArr, -1);
                    while (true) {
                        FieldDeserializer[] fieldDeserializerArr2 = this.sortedFieldDeserializers;
                        if (i >= fieldDeserializerArr2.length) {
                            break;
                        }
                        int binarySearch2 = Arrays.binarySearch(this.smartMatchHashArray, TypeUtils.fnv1a_64_lower(fieldDeserializerArr2[i].fieldInfo.name));
                        if (binarySearch2 >= 0) {
                            sArr[binarySearch2] = (short) i;
                        }
                        i++;
                    }
                    this.smartMatchHashArrayMapping = sArr;
                }
                short s = this.smartMatchHashArrayMapping[binarySearch];
                if (s != -1 && !isSetFlag(s, iArr)) {
                    fieldDeserializer = this.sortedFieldDeserializers[s];
                }
            }
            if (fieldDeserializer != null) {
                FieldInfo fieldInfo = fieldDeserializer.fieldInfo;
                if ((fieldInfo.parserFeatures & Feature.DisableFieldSmartMatch.mask) != 0) {
                    return null;
                }
                Class<?> cls = fieldInfo.fieldClass;
                if (!(!z || cls == Boolean.TYPE || cls == Boolean.class)) {
                    return null;
                }
            }
        }
        return fieldDeserializer;
    }

    private Object createFactoryInstance(ParserConfig parserConfig, Object obj) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
        return this.beanInfo.factoryMethod.invoke((Object) null, new Object[]{obj});
    }

    /* JADX WARNING: Code restructure failed: missing block: B:144:0x0248, code lost:
        if (r12.beanInfo.fields[r13].fieldClass == java.lang.String.class) goto L_0x024f;
     */
    /* JADX WARNING: Removed duplicated region for block: B:151:0x0259 A[SYNTHETIC, Splitter:B:151:0x0259] */
    /* JADX WARNING: Removed duplicated region for block: B:166:0x02a2 A[SYNTHETIC, Splitter:B:166:0x02a2] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object createInstance(java.util.Map<java.lang.String, java.lang.Object> r13, com.alibaba.fastjson.parser.ParserConfig r14) throws java.lang.IllegalArgumentException, java.lang.IllegalAccessException, java.lang.reflect.InvocationTargetException {
        /*
            r12 = this;
            com.alibaba.fastjson.util.JavaBeanInfo r0 = r12.beanInfo
            java.lang.reflect.Constructor<?> r0 = r0.creatorConstructor
            r1 = 1
            r2 = 0
            r3 = 0
            if (r0 != 0) goto L_0x016b
            com.alibaba.fastjson.util.JavaBeanInfo r0 = r12.beanInfo
            java.lang.reflect.Method r0 = r0.factoryMethod
            if (r0 != 0) goto L_0x016b
            java.lang.Class<?> r0 = r12.clazz
            java.lang.Object r0 = r12.createInstance((com.alibaba.fastjson.parser.DefaultJSONParser) r2, (java.lang.reflect.Type) r0)
            java.util.Set r13 = r13.entrySet()
            java.util.Iterator r13 = r13.iterator()
        L_0x001d:
            boolean r4 = r13.hasNext()
            if (r4 == 0) goto L_0x0150
            java.lang.Object r4 = r13.next()
            java.util.Map$Entry r4 = (java.util.Map.Entry) r4
            java.lang.Object r5 = r4.getKey()
            java.lang.String r5 = (java.lang.String) r5
            java.lang.Object r4 = r4.getValue()
            com.alibaba.fastjson.parser.deserializer.FieldDeserializer r5 = r12.smartMatch(r5)
            if (r5 != 0) goto L_0x003a
            goto L_0x001d
        L_0x003a:
            com.alibaba.fastjson.util.FieldInfo r6 = r5.fieldInfo
            com.alibaba.fastjson.util.FieldInfo r7 = r5.fieldInfo
            java.lang.reflect.Field r7 = r7.field
            java.lang.reflect.Type r8 = r6.fieldType
            java.lang.Class<?> r9 = r6.declaringClass
            if (r9 == 0) goto L_0x006d
            com.alibaba.fastjson.annotation.JSONField r9 = r6.getAnnotation()
            if (r9 == 0) goto L_0x006d
            com.alibaba.fastjson.annotation.JSONField r9 = r6.getAnnotation()
            java.lang.Class r9 = r9.deserializeUsing()
            java.lang.Class<java.lang.Void> r10 = java.lang.Void.class
            if (r9 == r10) goto L_0x006d
            java.lang.Class<?> r9 = r6.fieldClass
            boolean r9 = r9.isInstance(r4)
            if (r9 == 0) goto L_0x006d
            com.alibaba.fastjson.parser.DefaultJSONParser r6 = new com.alibaba.fastjson.parser.DefaultJSONParser
            java.lang.String r4 = com.alibaba.fastjson.JSON.toJSONString(r4)
            r6.<init>((java.lang.String) r4)
            r5.parseField(r6, r0, r8, r2)
            goto L_0x001d
        L_0x006d:
            if (r7 == 0) goto L_0x0115
            java.lang.Class r9 = r7.getType()
            java.lang.Class r10 = java.lang.Boolean.TYPE
            if (r9 != r10) goto L_0x0087
            java.lang.Boolean r9 = java.lang.Boolean.FALSE
            if (r4 != r9) goto L_0x007f
            r7.setBoolean(r0, r3)
            goto L_0x001d
        L_0x007f:
            java.lang.Boolean r9 = java.lang.Boolean.TRUE
            if (r4 != r9) goto L_0x0115
            r7.setBoolean(r0, r1)
            goto L_0x001d
        L_0x0087:
            java.lang.Class r10 = java.lang.Integer.TYPE
            if (r9 != r10) goto L_0x0099
            boolean r9 = r4 instanceof java.lang.Number
            if (r9 == 0) goto L_0x0115
            java.lang.Number r4 = (java.lang.Number) r4
            int r4 = r4.intValue()
            r7.setInt(r0, r4)
            goto L_0x001d
        L_0x0099:
            java.lang.Class r10 = java.lang.Long.TYPE
            if (r9 != r10) goto L_0x00ac
            boolean r9 = r4 instanceof java.lang.Number
            if (r9 == 0) goto L_0x0115
            java.lang.Number r4 = (java.lang.Number) r4
            long r4 = r4.longValue()
            r7.setLong(r0, r4)
            goto L_0x001d
        L_0x00ac:
            java.lang.Class r10 = java.lang.Float.TYPE
            r11 = 10
            if (r9 != r10) goto L_0x00db
            boolean r9 = r4 instanceof java.lang.Number
            if (r9 == 0) goto L_0x00c1
            java.lang.Number r4 = (java.lang.Number) r4
            float r4 = r4.floatValue()
            r7.setFloat(r0, r4)
            goto L_0x001d
        L_0x00c1:
            boolean r9 = r4 instanceof java.lang.String
            if (r9 == 0) goto L_0x0115
            java.lang.String r4 = (java.lang.String) r4
            int r5 = r4.length()
            if (r5 > r11) goto L_0x00d2
            float r4 = com.alibaba.fastjson.util.TypeUtils.parseFloat(r4)
            goto L_0x00d6
        L_0x00d2:
            float r4 = java.lang.Float.parseFloat(r4)
        L_0x00d6:
            r7.setFloat(r0, r4)
            goto L_0x001d
        L_0x00db:
            java.lang.Class r10 = java.lang.Double.TYPE
            if (r9 != r10) goto L_0x0108
            boolean r9 = r4 instanceof java.lang.Number
            if (r9 == 0) goto L_0x00ee
            java.lang.Number r4 = (java.lang.Number) r4
            double r4 = r4.doubleValue()
            r7.setDouble(r0, r4)
            goto L_0x001d
        L_0x00ee:
            boolean r9 = r4 instanceof java.lang.String
            if (r9 == 0) goto L_0x0115
            java.lang.String r4 = (java.lang.String) r4
            int r5 = r4.length()
            if (r5 > r11) goto L_0x00ff
            double r4 = com.alibaba.fastjson.util.TypeUtils.parseDouble(r4)
            goto L_0x0103
        L_0x00ff:
            double r4 = java.lang.Double.parseDouble(r4)
        L_0x0103:
            r7.setDouble(r0, r4)
            goto L_0x001d
        L_0x0108:
            if (r4 == 0) goto L_0x0115
            java.lang.Class r9 = r4.getClass()
            if (r8 != r9) goto L_0x0115
            r7.set(r0, r4)
            goto L_0x001d
        L_0x0115:
            java.lang.String r6 = r6.format
            if (r6 == 0) goto L_0x0122
            java.lang.Class<java.util.Date> r7 = java.util.Date.class
            if (r8 != r7) goto L_0x0122
            java.util.Date r4 = com.alibaba.fastjson.util.TypeUtils.castToDate(r4, r6)
            goto L_0x014b
        L_0x0122:
            if (r6 == 0) goto L_0x013c
            boolean r7 = r8 instanceof java.lang.Class
            if (r7 == 0) goto L_0x013c
            r7 = r8
            java.lang.Class r7 = (java.lang.Class) r7
            java.lang.String r7 = r7.getName()
            java.lang.String r9 = "java.time.LocalDateTime"
            boolean r7 = r7.equals(r9)
            if (r7 == 0) goto L_0x013c
            java.time.LocalDateTime r4 = com.alibaba.fastjson.util.TypeUtils.castToLocalDateTime(r4, r6)
            goto L_0x014b
        L_0x013c:
            boolean r6 = r8 instanceof java.lang.reflect.ParameterizedType
            if (r6 == 0) goto L_0x0147
            java.lang.reflect.ParameterizedType r8 = (java.lang.reflect.ParameterizedType) r8
            java.lang.Object r4 = com.alibaba.fastjson.util.TypeUtils.cast((java.lang.Object) r4, (java.lang.reflect.ParameterizedType) r8, (com.alibaba.fastjson.parser.ParserConfig) r14)
            goto L_0x014b
        L_0x0147:
            java.lang.Object r4 = com.alibaba.fastjson.util.TypeUtils.cast((java.lang.Object) r4, (java.lang.reflect.Type) r8, (com.alibaba.fastjson.parser.ParserConfig) r14)
        L_0x014b:
            r5.setValue((java.lang.Object) r0, (java.lang.Object) r4)
            goto L_0x001d
        L_0x0150:
            com.alibaba.fastjson.util.JavaBeanInfo r13 = r12.beanInfo
            java.lang.reflect.Method r13 = r13.buildMethod
            if (r13 == 0) goto L_0x016a
            com.alibaba.fastjson.util.JavaBeanInfo r13 = r12.beanInfo     // Catch:{ Exception -> 0x0161 }
            java.lang.reflect.Method r13 = r13.buildMethod     // Catch:{ Exception -> 0x0161 }
            java.lang.Object[] r14 = new java.lang.Object[r3]     // Catch:{ Exception -> 0x0161 }
            java.lang.Object r13 = r13.invoke(r0, r14)     // Catch:{ Exception -> 0x0161 }
            return r13
        L_0x0161:
            r13 = move-exception
            com.alibaba.fastjson.JSONException r14 = new com.alibaba.fastjson.JSONException
            java.lang.String r0 = "build object error"
            r14.<init>(r0, r13)
            throw r14
        L_0x016a:
            return r0
        L_0x016b:
            com.alibaba.fastjson.util.JavaBeanInfo r14 = r12.beanInfo
            com.alibaba.fastjson.util.FieldInfo[] r14 = r14.fields
            int r0 = r14.length
            java.lang.Object[] r4 = new java.lang.Object[r0]
            r6 = r2
            r5 = 0
        L_0x0174:
            if (r5 >= r0) goto L_0x01e5
            r7 = r14[r5]
            java.lang.String r8 = r7.name
            java.lang.Object r8 = r13.get(r8)
            if (r8 != 0) goto L_0x01e0
            java.lang.Class<?> r9 = r7.fieldClass
            java.lang.Class r10 = java.lang.Integer.TYPE
            if (r9 != r10) goto L_0x018b
            java.lang.Integer r8 = java.lang.Integer.valueOf(r3)
            goto L_0x01d0
        L_0x018b:
            java.lang.Class r10 = java.lang.Long.TYPE
            if (r9 != r10) goto L_0x0196
            r8 = 0
            java.lang.Long r8 = java.lang.Long.valueOf(r8)
            goto L_0x01d0
        L_0x0196:
            java.lang.Class r10 = java.lang.Short.TYPE
            if (r9 != r10) goto L_0x019f
            java.lang.Short r8 = java.lang.Short.valueOf(r3)
            goto L_0x01d0
        L_0x019f:
            java.lang.Class r10 = java.lang.Byte.TYPE
            if (r9 != r10) goto L_0x01a8
            java.lang.Byte r8 = java.lang.Byte.valueOf(r3)
            goto L_0x01d0
        L_0x01a8:
            java.lang.Class r10 = java.lang.Float.TYPE
            if (r9 != r10) goto L_0x01b2
            r8 = 0
            java.lang.Float r8 = java.lang.Float.valueOf(r8)
            goto L_0x01d0
        L_0x01b2:
            java.lang.Class r10 = java.lang.Double.TYPE
            if (r9 != r10) goto L_0x01bd
            r8 = 0
            java.lang.Double r8 = java.lang.Double.valueOf(r8)
            goto L_0x01d0
        L_0x01bd:
            java.lang.Class r10 = java.lang.Character.TYPE
            if (r9 != r10) goto L_0x01c8
            r8 = 48
            java.lang.Character r8 = java.lang.Character.valueOf(r8)
            goto L_0x01d0
        L_0x01c8:
            java.lang.Class r10 = java.lang.Boolean.TYPE
            if (r9 != r10) goto L_0x01d0
            java.lang.Boolean r8 = java.lang.Boolean.valueOf(r3)
        L_0x01d0:
            if (r6 != 0) goto L_0x01d7
            java.util.HashMap r6 = new java.util.HashMap
            r6.<init>()
        L_0x01d7:
            java.lang.String r7 = r7.name
            java.lang.Integer r9 = java.lang.Integer.valueOf(r5)
            r6.put(r7, r9)
        L_0x01e0:
            r4[r5] = r8
            int r5 = r5 + 1
            goto L_0x0174
        L_0x01e5:
            if (r6 == 0) goto L_0x021e
            java.util.Set r13 = r13.entrySet()
            java.util.Iterator r13 = r13.iterator()
        L_0x01ef:
            boolean r14 = r13.hasNext()
            if (r14 == 0) goto L_0x021e
            java.lang.Object r14 = r13.next()
            java.util.Map$Entry r14 = (java.util.Map.Entry) r14
            java.lang.Object r5 = r14.getKey()
            java.lang.String r5 = (java.lang.String) r5
            java.lang.Object r14 = r14.getValue()
            com.alibaba.fastjson.parser.deserializer.FieldDeserializer r5 = r12.smartMatch(r5)
            if (r5 == 0) goto L_0x01ef
            com.alibaba.fastjson.util.FieldInfo r5 = r5.fieldInfo
            java.lang.String r5 = r5.name
            java.lang.Object r5 = r6.get(r5)
            java.lang.Integer r5 = (java.lang.Integer) r5
            if (r5 == 0) goto L_0x01ef
            int r5 = r5.intValue()
            r4[r5] = r14
            goto L_0x01ef
        L_0x021e:
            com.alibaba.fastjson.util.JavaBeanInfo r13 = r12.beanInfo
            java.lang.reflect.Constructor<?> r13 = r13.creatorConstructor
            if (r13 == 0) goto L_0x02ca
            com.alibaba.fastjson.util.JavaBeanInfo r13 = r12.beanInfo
            boolean r13 = r13.f4kotlin
            if (r13 == 0) goto L_0x024e
            r13 = 0
        L_0x022b:
            if (r13 >= r0) goto L_0x024e
            r14 = r4[r13]
            if (r14 != 0) goto L_0x024b
            com.alibaba.fastjson.util.JavaBeanInfo r14 = r12.beanInfo
            com.alibaba.fastjson.util.FieldInfo[] r14 = r14.fields
            if (r14 == 0) goto L_0x024b
            com.alibaba.fastjson.util.JavaBeanInfo r14 = r12.beanInfo
            com.alibaba.fastjson.util.FieldInfo[] r14 = r14.fields
            int r14 = r14.length
            if (r13 >= r14) goto L_0x024b
            com.alibaba.fastjson.util.JavaBeanInfo r14 = r12.beanInfo
            com.alibaba.fastjson.util.FieldInfo[] r14 = r14.fields
            r13 = r14[r13]
            java.lang.Class<?> r13 = r13.fieldClass
            java.lang.Class<java.lang.String> r14 = java.lang.String.class
            if (r13 != r14) goto L_0x024e
            goto L_0x024f
        L_0x024b:
            int r13 = r13 + 1
            goto L_0x022b
        L_0x024e:
            r1 = 0
        L_0x024f:
            java.lang.String r13 = "create instance error, "
            if (r1 == 0) goto L_0x02a2
            com.alibaba.fastjson.util.JavaBeanInfo r14 = r12.beanInfo
            java.lang.reflect.Constructor<?> r14 = r14.kotlinDefaultConstructor
            if (r14 == 0) goto L_0x02a2
            com.alibaba.fastjson.util.JavaBeanInfo r14 = r12.beanInfo     // Catch:{ Exception -> 0x0284 }
            java.lang.reflect.Constructor<?> r14 = r14.kotlinDefaultConstructor     // Catch:{ Exception -> 0x0284 }
            java.lang.Object[] r1 = new java.lang.Object[r3]     // Catch:{ Exception -> 0x0284 }
            java.lang.Object r14 = r14.newInstance(r1)     // Catch:{ Exception -> 0x0284 }
        L_0x0263:
            if (r3 >= r0) goto L_0x0282
            r1 = r4[r3]     // Catch:{ Exception -> 0x0284 }
            if (r1 == 0) goto L_0x027f
            com.alibaba.fastjson.util.JavaBeanInfo r2 = r12.beanInfo     // Catch:{ Exception -> 0x0284 }
            com.alibaba.fastjson.util.FieldInfo[] r2 = r2.fields     // Catch:{ Exception -> 0x0284 }
            if (r2 == 0) goto L_0x027f
            com.alibaba.fastjson.util.JavaBeanInfo r2 = r12.beanInfo     // Catch:{ Exception -> 0x0284 }
            com.alibaba.fastjson.util.FieldInfo[] r2 = r2.fields     // Catch:{ Exception -> 0x0284 }
            int r2 = r2.length     // Catch:{ Exception -> 0x0284 }
            if (r3 >= r2) goto L_0x027f
            com.alibaba.fastjson.util.JavaBeanInfo r2 = r12.beanInfo     // Catch:{ Exception -> 0x0284 }
            com.alibaba.fastjson.util.FieldInfo[] r2 = r2.fields     // Catch:{ Exception -> 0x0284 }
            r2 = r2[r3]     // Catch:{ Exception -> 0x0284 }
            r2.set(r14, r1)     // Catch:{ Exception -> 0x0284 }
        L_0x027f:
            int r3 = r3 + 1
            goto L_0x0263
        L_0x0282:
            r2 = r14
            goto L_0x02f9
        L_0x0284:
            r14 = move-exception
            com.alibaba.fastjson.JSONException r0 = new com.alibaba.fastjson.JSONException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r13)
            com.alibaba.fastjson.util.JavaBeanInfo r13 = r12.beanInfo
            java.lang.reflect.Constructor<?> r13 = r13.creatorConstructor
            java.lang.String r13 = r13.toGenericString()
            r1.append(r13)
            java.lang.String r13 = r1.toString()
            r0.<init>(r13, r14)
            throw r0
        L_0x02a2:
            com.alibaba.fastjson.util.JavaBeanInfo r14 = r12.beanInfo     // Catch:{ Exception -> 0x02ac }
            java.lang.reflect.Constructor<?> r14 = r14.creatorConstructor     // Catch:{ Exception -> 0x02ac }
            java.lang.Object r13 = r14.newInstance(r4)     // Catch:{ Exception -> 0x02ac }
            r2 = r13
            goto L_0x02f9
        L_0x02ac:
            r14 = move-exception
            com.alibaba.fastjson.JSONException r0 = new com.alibaba.fastjson.JSONException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r13)
            com.alibaba.fastjson.util.JavaBeanInfo r13 = r12.beanInfo
            java.lang.reflect.Constructor<?> r13 = r13.creatorConstructor
            java.lang.String r13 = r13.toGenericString()
            r1.append(r13)
            java.lang.String r13 = r1.toString()
            r0.<init>(r13, r14)
            throw r0
        L_0x02ca:
            com.alibaba.fastjson.util.JavaBeanInfo r13 = r12.beanInfo
            java.lang.reflect.Method r13 = r13.factoryMethod
            if (r13 == 0) goto L_0x02f9
            com.alibaba.fastjson.util.JavaBeanInfo r13 = r12.beanInfo     // Catch:{ Exception -> 0x02d9 }
            java.lang.reflect.Method r13 = r13.factoryMethod     // Catch:{ Exception -> 0x02d9 }
            java.lang.Object r2 = r13.invoke(r2, r4)     // Catch:{ Exception -> 0x02d9 }
            goto L_0x02f9
        L_0x02d9:
            r13 = move-exception
            com.alibaba.fastjson.JSONException r14 = new com.alibaba.fastjson.JSONException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "create factory method error, "
            r0.append(r1)
            com.alibaba.fastjson.util.JavaBeanInfo r1 = r12.beanInfo
            java.lang.reflect.Method r1 = r1.factoryMethod
            java.lang.String r1 = r1.toString()
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r14.<init>(r0, r13)
            throw r14
        L_0x02f9:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer.createInstance(java.util.Map, com.alibaba.fastjson.parser.ParserConfig):java.lang.Object");
    }

    public Type getFieldType(int i) {
        return this.sortedFieldDeserializers[i].fieldInfo.fieldType;
    }

    /* access modifiers changed from: protected */
    public Object parseRest(DefaultJSONParser defaultJSONParser, Type type, Object obj, Object obj2, int i) {
        return parseRest(defaultJSONParser, type, obj, obj2, i, new int[0]);
    }

    /* access modifiers changed from: protected */
    public Object parseRest(DefaultJSONParser defaultJSONParser, Type type, Object obj, Object obj2, int i, int[] iArr) {
        return deserialze(defaultJSONParser, type, obj, obj2, i, iArr);
    }

    protected static JavaBeanDeserializer getSeeAlso(ParserConfig parserConfig, JavaBeanInfo javaBeanInfo, String str) {
        if (javaBeanInfo.jsonType == null) {
            return null;
        }
        for (Class deserializer : javaBeanInfo.jsonType.seeAlso()) {
            ObjectDeserializer deserializer2 = parserConfig.getDeserializer((Type) deserializer);
            if (deserializer2 instanceof JavaBeanDeserializer) {
                JavaBeanDeserializer javaBeanDeserializer = (JavaBeanDeserializer) deserializer2;
                JavaBeanInfo javaBeanInfo2 = javaBeanDeserializer.beanInfo;
                if (javaBeanInfo2.typeName.equals(str)) {
                    return javaBeanDeserializer;
                }
                JavaBeanDeserializer seeAlso = getSeeAlso(parserConfig, javaBeanInfo2, str);
                if (seeAlso != null) {
                    return seeAlso;
                }
            }
        }
        return null;
    }

    protected static void parseArray(Collection collection, ObjectDeserializer objectDeserializer, DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        JSONLexerBase jSONLexerBase = (JSONLexerBase) defaultJSONParser.lexer;
        int i = jSONLexerBase.token();
        if (i == 8) {
            jSONLexerBase.nextToken(16);
            jSONLexerBase.token();
            return;
        }
        if (i != 14) {
            defaultJSONParser.throwException(i);
        }
        if (jSONLexerBase.getCurrent() == '[') {
            jSONLexerBase.next();
            jSONLexerBase.setToken(14);
        } else {
            jSONLexerBase.nextToken(14);
        }
        if (jSONLexerBase.token() == 15) {
            jSONLexerBase.nextToken();
            return;
        }
        int i2 = 0;
        while (true) {
            collection.add(objectDeserializer.deserialze(defaultJSONParser, type, Integer.valueOf(i2)));
            i2++;
            if (jSONLexerBase.token() != 16) {
                break;
            } else if (jSONLexerBase.getCurrent() == '[') {
                jSONLexerBase.next();
                jSONLexerBase.setToken(14);
            } else {
                jSONLexerBase.nextToken(14);
            }
        }
        int i3 = jSONLexerBase.token();
        if (i3 != 15) {
            defaultJSONParser.throwException(i3);
        }
        if (jSONLexerBase.getCurrent() == ',') {
            jSONLexerBase.next();
            jSONLexerBase.setToken(16);
            return;
        }
        jSONLexerBase.nextToken(16);
    }
}
