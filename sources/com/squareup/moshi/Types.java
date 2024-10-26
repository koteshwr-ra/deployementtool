package com.squareup.moshi;

import com.squareup.moshi.internal.Util;
import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nullable;
import org.apache.log4j.spi.Configurator;

@CheckReturnValue
public final class Types {
    private Types() {
    }

    @Nullable
    public static Set<? extends Annotation> nextAnnotations(Set<? extends Annotation> set, Class<? extends Annotation> cls) {
        if (!cls.isAnnotationPresent(JsonQualifier.class)) {
            throw new IllegalArgumentException(cls + " is not a JsonQualifier.");
        } else if (set.isEmpty()) {
            return null;
        } else {
            for (Annotation annotation : set) {
                if (cls.equals(annotation.annotationType())) {
                    LinkedHashSet linkedHashSet = new LinkedHashSet(set);
                    linkedHashSet.remove(annotation);
                    return Collections.unmodifiableSet(linkedHashSet);
                }
            }
            return null;
        }
    }

    public static ParameterizedType newParameterizedType(Type type, Type... typeArr) {
        return new Util.ParameterizedTypeImpl((Type) null, type, typeArr);
    }

    public static ParameterizedType newParameterizedTypeWithOwner(Type type, Type type2, Type... typeArr) {
        return new Util.ParameterizedTypeImpl(type, type2, typeArr);
    }

    public static GenericArrayType arrayOf(Type type) {
        return new Util.GenericArrayTypeImpl(type);
    }

    public static WildcardType subtypeOf(Type type) {
        return new Util.WildcardTypeImpl(new Type[]{type}, Util.EMPTY_TYPE_ARRAY);
    }

    public static WildcardType supertypeOf(Type type) {
        return new Util.WildcardTypeImpl(new Type[]{Object.class}, new Type[]{type});
    }

    public static Class<?> getRawType(Type type) {
        String str;
        if (type instanceof Class) {
            return (Class) type;
        }
        if (type instanceof ParameterizedType) {
            return (Class) ((ParameterizedType) type).getRawType();
        }
        if (type instanceof GenericArrayType) {
            return Array.newInstance(getRawType(((GenericArrayType) type).getGenericComponentType()), 0).getClass();
        }
        if (type instanceof TypeVariable) {
            return Object.class;
        }
        if (type instanceof WildcardType) {
            return getRawType(((WildcardType) type).getUpperBounds()[0]);
        }
        if (type == null) {
            str = Configurator.NULL;
        } else {
            str = type.getClass().getName();
        }
        throw new IllegalArgumentException("Expected a Class, ParameterizedType, or GenericArrayType, but <" + type + "> is of type " + str);
    }

    public static Type collectionElementType(Type type, Class<?> cls) {
        Type supertype = getSupertype(type, cls, Collection.class);
        if (supertype instanceof WildcardType) {
            supertype = ((WildcardType) supertype).getUpperBounds()[0];
        }
        if (supertype instanceof ParameterizedType) {
            return ((ParameterizedType) supertype).getActualTypeArguments()[0];
        }
        return Object.class;
    }

    public static boolean equals(@Nullable Type type, @Nullable Type type2) {
        Type[] typeArr;
        Type[] typeArr2;
        if (type == type2) {
            return true;
        }
        if (type instanceof Class) {
            if (type2 instanceof GenericArrayType) {
                return equals(((Class) type).getComponentType(), ((GenericArrayType) type2).getGenericComponentType());
            }
            return type.equals(type2);
        } else if (type instanceof ParameterizedType) {
            if (!(type2 instanceof ParameterizedType)) {
                return false;
            }
            ParameterizedType parameterizedType = (ParameterizedType) type;
            ParameterizedType parameterizedType2 = (ParameterizedType) type2;
            if (parameterizedType instanceof Util.ParameterizedTypeImpl) {
                typeArr = ((Util.ParameterizedTypeImpl) parameterizedType).typeArguments;
            } else {
                typeArr = parameterizedType.getActualTypeArguments();
            }
            if (parameterizedType2 instanceof Util.ParameterizedTypeImpl) {
                typeArr2 = ((Util.ParameterizedTypeImpl) parameterizedType2).typeArguments;
            } else {
                typeArr2 = parameterizedType2.getActualTypeArguments();
            }
            if (!equals(parameterizedType.getOwnerType(), parameterizedType2.getOwnerType()) || !parameterizedType.getRawType().equals(parameterizedType2.getRawType()) || !Arrays.equals(typeArr, typeArr2)) {
                return false;
            }
            return true;
        } else if (type instanceof GenericArrayType) {
            if (type2 instanceof Class) {
                return equals(((Class) type2).getComponentType(), ((GenericArrayType) type).getGenericComponentType());
            }
            if (!(type2 instanceof GenericArrayType)) {
                return false;
            }
            return equals(((GenericArrayType) type).getGenericComponentType(), ((GenericArrayType) type2).getGenericComponentType());
        } else if (type instanceof WildcardType) {
            if (!(type2 instanceof WildcardType)) {
                return false;
            }
            WildcardType wildcardType = (WildcardType) type;
            WildcardType wildcardType2 = (WildcardType) type2;
            if (!Arrays.equals(wildcardType.getUpperBounds(), wildcardType2.getUpperBounds()) || !Arrays.equals(wildcardType.getLowerBounds(), wildcardType2.getLowerBounds())) {
                return false;
            }
            return true;
        } else if (!(type instanceof TypeVariable) || !(type2 instanceof TypeVariable)) {
            return false;
        } else {
            TypeVariable typeVariable = (TypeVariable) type;
            TypeVariable typeVariable2 = (TypeVariable) type2;
            if (typeVariable.getGenericDeclaration() != typeVariable2.getGenericDeclaration() || !typeVariable.getName().equals(typeVariable2.getName())) {
                return false;
            }
            return true;
        }
    }

    public static Set<? extends Annotation> getFieldJsonQualifierAnnotations(Class<?> cls, String str) {
        try {
            Field declaredField = cls.getDeclaredField(str);
            declaredField.setAccessible(true);
            Annotation[] declaredAnnotations = declaredField.getDeclaredAnnotations();
            LinkedHashSet linkedHashSet = new LinkedHashSet(declaredAnnotations.length);
            for (Annotation annotation : declaredAnnotations) {
                if (annotation.annotationType().isAnnotationPresent(JsonQualifier.class)) {
                    linkedHashSet.add(annotation);
                }
            }
            return Collections.unmodifiableSet(linkedHashSet);
        } catch (NoSuchFieldException e) {
            throw new IllegalArgumentException("Could not access field " + str + " on class " + cls.getCanonicalName(), e);
        }
    }

    static <T extends Annotation> T createJsonQualifierImplementation(final Class<T> cls) {
        if (!cls.isAnnotation()) {
            throw new IllegalArgumentException(cls + " must be an annotation.");
        } else if (!cls.isAnnotationPresent(JsonQualifier.class)) {
            throw new IllegalArgumentException(cls + " must have @JsonQualifier.");
        } else if (cls.getDeclaredMethods().length == 0) {
            return (Annotation) Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, new InvocationHandler() {
                /* JADX WARNING: Can't fix incorrect switch cases order */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public java.lang.Object invoke(java.lang.Object r7, java.lang.reflect.Method r8, java.lang.Object[] r9) throws java.lang.Throwable {
                    /*
                        r6 = this;
                        java.lang.String r0 = r8.getName()
                        int r1 = r0.hashCode()
                        r2 = 3
                        r3 = 2
                        r4 = 1
                        r5 = 0
                        switch(r1) {
                            case -1776922004: goto L_0x002e;
                            case -1295482945: goto L_0x0024;
                            case 147696667: goto L_0x001a;
                            case 1444986633: goto L_0x0010;
                            default: goto L_0x000f;
                        }
                    L_0x000f:
                        goto L_0x0038
                    L_0x0010:
                        java.lang.String r1 = "annotationType"
                        boolean r0 = r0.equals(r1)
                        if (r0 == 0) goto L_0x0038
                        r0 = 0
                        goto L_0x0039
                    L_0x001a:
                        java.lang.String r1 = "hashCode"
                        boolean r0 = r0.equals(r1)
                        if (r0 == 0) goto L_0x0038
                        r0 = 2
                        goto L_0x0039
                    L_0x0024:
                        java.lang.String r1 = "equals"
                        boolean r0 = r0.equals(r1)
                        if (r0 == 0) goto L_0x0038
                        r0 = 1
                        goto L_0x0039
                    L_0x002e:
                        java.lang.String r1 = "toString"
                        boolean r0 = r0.equals(r1)
                        if (r0 == 0) goto L_0x0038
                        r0 = 3
                        goto L_0x0039
                    L_0x0038:
                        r0 = -1
                    L_0x0039:
                        if (r0 == 0) goto L_0x0075
                        if (r0 == r4) goto L_0x0068
                        if (r0 == r3) goto L_0x0063
                        if (r0 == r2) goto L_0x0046
                        java.lang.Object r7 = r8.invoke(r7, r9)
                        return r7
                    L_0x0046:
                        java.lang.StringBuilder r7 = new java.lang.StringBuilder
                        r7.<init>()
                        java.lang.String r8 = "@"
                        r7.append(r8)
                        java.lang.Class r8 = r3
                        java.lang.String r8 = r8.getName()
                        r7.append(r8)
                        java.lang.String r8 = "()"
                        r7.append(r8)
                        java.lang.String r7 = r7.toString()
                        return r7
                    L_0x0063:
                        java.lang.Integer r7 = java.lang.Integer.valueOf(r5)
                        return r7
                    L_0x0068:
                        r7 = r9[r5]
                        java.lang.Class r8 = r3
                        boolean r7 = r8.isInstance(r7)
                        java.lang.Boolean r7 = java.lang.Boolean.valueOf(r7)
                        return r7
                    L_0x0075:
                        java.lang.Class r7 = r3
                        return r7
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.squareup.moshi.Types.AnonymousClass1.invoke(java.lang.Object, java.lang.reflect.Method, java.lang.Object[]):java.lang.Object");
                }
            });
        } else {
            throw new IllegalArgumentException(cls + " must not declare methods.");
        }
    }

    static Type[] mapKeyAndValueTypes(Type type, Class<?> cls) {
        if (type == Properties.class) {
            return new Type[]{String.class, String.class};
        }
        Type supertype = getSupertype(type, cls, Map.class);
        if (supertype instanceof ParameterizedType) {
            return ((ParameterizedType) supertype).getActualTypeArguments();
        }
        return new Type[]{Object.class, Object.class};
    }

    static Type getSupertype(Type type, Class<?> cls, Class<?> cls2) {
        if (cls2.isAssignableFrom(cls)) {
            return Util.resolve(type, cls, Util.getGenericSupertype(type, cls, cls2));
        }
        throw new IllegalArgumentException();
    }

    static Type getGenericSuperclass(Type type) {
        Class<?> rawType = getRawType(type);
        return Util.resolve(type, rawType, rawType.getGenericSuperclass());
    }

    static Type arrayComponentType(Type type) {
        if (type instanceof GenericArrayType) {
            return ((GenericArrayType) type).getGenericComponentType();
        }
        if (type instanceof Class) {
            return ((Class) type).getComponentType();
        }
        return null;
    }

    static boolean isAllowedPlatformType(Type type) {
        return type == Boolean.class || type == Byte.class || type == Character.class || type == Double.class || type == Float.class || type == Integer.class || type == Long.class || type == Short.class || type == String.class || type == Object.class;
    }
}
