package com.squareup.javapoet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.lang.model.element.Modifier;
import kotlin.text.Typography;

final class Util {
    static final Modifier DEFAULT;

    private Util() {
    }

    static {
        Modifier modifier;
        try {
            modifier = Modifier.valueOf("DEFAULT");
        } catch (IllegalArgumentException unused) {
            modifier = null;
        }
        DEFAULT = modifier;
    }

    static <K, V> Map<K, List<V>> immutableMultimap(Map<K, List<V>> map) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry next : map.entrySet()) {
            if (!((List) next.getValue()).isEmpty()) {
                linkedHashMap.put(next.getKey(), immutableList((Collection) next.getValue()));
            }
        }
        return Collections.unmodifiableMap(linkedHashMap);
    }

    static <K, V> Map<K, V> immutableMap(Map<K, V> map) {
        return Collections.unmodifiableMap(new LinkedHashMap(map));
    }

    static void checkArgument(boolean z, String str, Object... objArr) {
        if (!z) {
            throw new IllegalArgumentException(String.format(str, objArr));
        }
    }

    static <T> T checkNotNull(T t, String str, Object... objArr) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(String.format(str, objArr));
    }

    static void checkState(boolean z, String str, Object... objArr) {
        if (!z) {
            throw new IllegalStateException(String.format(str, objArr));
        }
    }

    static <T> List<T> immutableList(Collection<T> collection) {
        return Collections.unmodifiableList(new ArrayList(collection));
    }

    static <T> Set<T> immutableSet(Collection<T> collection) {
        return Collections.unmodifiableSet(new LinkedHashSet(collection));
    }

    static String join(String str, List<String> list) {
        if (list.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(list.get(0));
        for (int i = 1; i < list.size(); i++) {
            sb.append(str);
            sb.append(list.get(i));
        }
        return sb.toString();
    }

    static <T> Set<T> union(Set<T> set, Set<T> set2) {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        linkedHashSet.addAll(set);
        linkedHashSet.addAll(set2);
        return linkedHashSet;
    }

    static void requireExactlyOneOf(Set<Modifier> set, Modifier... modifierArr) {
        int i = 0;
        for (Modifier modifier : modifierArr) {
            if (!(modifier == null && DEFAULT == null) && set.contains(modifier)) {
                i++;
            }
        }
        checkArgument(i == 1, "modifiers %s must contain one of %s", set, Arrays.toString(modifierArr));
    }

    static boolean hasDefaultModifier(Collection<Modifier> collection) {
        Modifier modifier = DEFAULT;
        return modifier != null && collection.contains(modifier);
    }

    static String characterLiteralWithoutSingleQuotes(char c) {
        if (c == 12) {
            return "\\f";
        }
        if (c == 13) {
            return "\\r";
        }
        if (c == '\"') {
            return "\"";
        }
        if (c == '\'') {
            return "\\'";
        }
        if (c == '\\') {
            return "\\\\";
        }
        switch (c) {
            case 8:
                return "\\b";
            case 9:
                return "\\t";
            case 10:
                return "\\n";
            default:
                if (!Character.isISOControl(c)) {
                    return Character.toString(c);
                }
                return String.format("\\u%04x", new Object[]{Integer.valueOf(c)});
        }
    }

    static String stringLiteralWithDoubleQuotes(String str, String str2) {
        StringBuilder sb = new StringBuilder(str.length() + 2);
        sb.append(Typography.quote);
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (charAt == '\'') {
                sb.append("'");
            } else if (charAt == '\"') {
                sb.append("\\\"");
            } else {
                sb.append(characterLiteralWithoutSingleQuotes(charAt));
                if (charAt == 10 && i + 1 < str.length()) {
                    sb.append("\"\n");
                    sb.append(str2);
                    sb.append(str2);
                    sb.append("+ \"");
                }
            }
        }
        sb.append(Typography.quote);
        return sb.toString();
    }
}
