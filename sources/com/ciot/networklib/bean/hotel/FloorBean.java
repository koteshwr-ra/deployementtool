package com.ciot.networklib.bean.hotel;

import java.util.ArrayList;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u0012\u0012\u0004\u0012\u00020\u00020\u0001j\b\u0012\u0004\u0012\u00020\u0002`\u0003B\u0005¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"Lcom/ciot/networklib/bean/hotel/FloorBean;", "Ljava/util/ArrayList;", "Lcom/ciot/networklib/bean/hotel/FloorBeanItem;", "Lkotlin/collections/ArrayList;", "()V", "library-networkLib_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: FloorBean.kt */
public final class FloorBean extends ArrayList<FloorBeanItem> {
    public /* bridge */ boolean contains(FloorBeanItem floorBeanItem) {
        return super.contains(floorBeanItem);
    }

    public final /* bridge */ boolean contains(Object obj) {
        if (!(obj instanceof FloorBeanItem)) {
            return false;
        }
        return contains((FloorBeanItem) obj);
    }

    public /* bridge */ int getSize() {
        return super.size();
    }

    public /* bridge */ int indexOf(FloorBeanItem floorBeanItem) {
        return super.indexOf(floorBeanItem);
    }

    public final /* bridge */ int indexOf(Object obj) {
        if (!(obj instanceof FloorBeanItem)) {
            return -1;
        }
        return indexOf((FloorBeanItem) obj);
    }

    public /* bridge */ int lastIndexOf(FloorBeanItem floorBeanItem) {
        return super.lastIndexOf(floorBeanItem);
    }

    public final /* bridge */ int lastIndexOf(Object obj) {
        if (!(obj instanceof FloorBeanItem)) {
            return -1;
        }
        return lastIndexOf((FloorBeanItem) obj);
    }

    public final /* bridge */ FloorBeanItem remove(int i) {
        return removeAt(i);
    }

    public /* bridge */ boolean remove(FloorBeanItem floorBeanItem) {
        return super.remove(floorBeanItem);
    }

    public final /* bridge */ boolean remove(Object obj) {
        if (!(obj instanceof FloorBeanItem)) {
            return false;
        }
        return remove((FloorBeanItem) obj);
    }

    public /* bridge */ FloorBeanItem removeAt(int i) {
        return (FloorBeanItem) super.remove(i);
    }

    public final /* bridge */ int size() {
        return getSize();
    }
}
