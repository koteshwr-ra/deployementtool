package com.ciot.base.constant;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0014\u0010\u0011\u001a\u00020\u00042\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00040\u0013J\"\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u00042\b\u0010\u0017\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0012\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tXT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\tXT¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/ciot/base/constant/HttpConstant;", "", "()V", "ARTICLE_WEBSITE", "", "COIN_WEBSITE", "COLLECTIONS_WEBSITE", "COOKIE_NAME", "DEFAULT_TIMEOUT", "", "HTTP_URL", "MAX_CACHE_SIZE", "SAVE_USER_LOGIN_KEY", "SAVE_USER_REGISTER_KEY", "SET_COOKIE_KEY", "TODO_WEBSITE", "UNCOLLECTIONS_WEBSITE", "encodeCookie", "cookies", "", "saveCookie", "", "url", "domain", "library-base_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: HttpConstant.kt */
public final class HttpConstant {
    public static final String ARTICLE_WEBSITE = "article";
    public static final String COIN_WEBSITE = "lg/coin";
    public static final String COLLECTIONS_WEBSITE = "lg/collect";
    public static final String COOKIE_NAME = "Cookie";
    public static final long DEFAULT_TIMEOUT = 20;
    public static final String HTTP_URL = "http://";
    public static final HttpConstant INSTANCE = new HttpConstant();
    public static final long MAX_CACHE_SIZE = 52428800;
    public static final String SAVE_USER_LOGIN_KEY = "user/login";
    public static final String SAVE_USER_REGISTER_KEY = "user/register";
    public static final String SET_COOKIE_KEY = "set-cookie";
    public static final String TODO_WEBSITE = "lg/todo";
    public static final String UNCOLLECTIONS_WEBSITE = "lg/uncollect";

    public final void saveCookie(String str, String str2, String str3) {
        Intrinsics.checkNotNullParameter(str3, "cookies");
        if (str != null && str2 == null) {
        }
    }

    private HttpConstant() {
    }

    public final String encodeCookie(List<String> list) {
        List list2;
        boolean z;
        Intrinsics.checkNotNullParameter(list, "cookies");
        StringBuilder sb = new StringBuilder();
        HashSet hashSet = new HashSet();
        Iterable<String> iterable = list;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (String split : iterable) {
            List<String> split2 = new Regex(";").split(split, 0);
            if (!split2.isEmpty()) {
                ListIterator<String> listIterator = split2.listIterator(split2.size());
                while (true) {
                    if (!listIterator.hasPrevious()) {
                        break;
                    }
                    if (listIterator.previous().length() == 0) {
                        z = true;
                        continue;
                    } else {
                        z = false;
                        continue;
                    }
                    if (!z) {
                        list2 = CollectionsKt.take(split2, listIterator.nextIndex() + 1);
                        break;
                    }
                }
            }
            list2 = CollectionsKt.emptyList();
            Object[] array = list2.toArray(new String[0]);
            if (array != null) {
                arrayList.add((String[]) array);
            } else {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
            }
        }
        for (String[] strArr : (List) arrayList) {
            Collection arrayList2 = new ArrayList();
            for (String str : (String[]) r12.next()) {
                if (!hashSet.contains(str)) {
                    arrayList2.add(str);
                }
            }
            for (String add : (List) arrayList2) {
                hashSet.add(add);
            }
        }
        Iterator it = hashSet.iterator();
        Intrinsics.checkNotNullExpressionValue(it, "set.iterator()");
        while (it.hasNext()) {
            Object next = it.next();
            Intrinsics.checkNotNullExpressionValue(next, "ite.next()");
            sb.append((String) next);
            sb.append(";");
        }
        int lastIndexOf = sb.lastIndexOf(";");
        if (sb.length() - 1 == lastIndexOf) {
            sb.deleteCharAt(lastIndexOf);
        }
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "sb.toString()");
        return sb2;
    }
}
