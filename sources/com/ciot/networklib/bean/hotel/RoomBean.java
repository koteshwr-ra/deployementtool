package com.ciot.networklib.bean.hotel;

import java.util.ArrayList;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u0012\u0012\u0004\u0012\u00020\u00020\u0001j\b\u0012\u0004\u0012\u00020\u0002`\u0003B\u0005¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"Lcom/ciot/networklib/bean/hotel/RoomBean;", "Ljava/util/ArrayList;", "Lcom/ciot/networklib/bean/hotel/RoomBeanItem;", "Lkotlin/collections/ArrayList;", "()V", "library-networkLib_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: RoomBean.kt */
public final class RoomBean extends ArrayList<RoomBeanItem> {
    public /* bridge */ boolean contains(RoomBeanItem roomBeanItem) {
        return super.contains(roomBeanItem);
    }

    public final /* bridge */ boolean contains(Object obj) {
        if (!(obj instanceof RoomBeanItem)) {
            return false;
        }
        return contains((RoomBeanItem) obj);
    }

    public /* bridge */ int getSize() {
        return super.size();
    }

    public /* bridge */ int indexOf(RoomBeanItem roomBeanItem) {
        return super.indexOf(roomBeanItem);
    }

    public final /* bridge */ int indexOf(Object obj) {
        if (!(obj instanceof RoomBeanItem)) {
            return -1;
        }
        return indexOf((RoomBeanItem) obj);
    }

    public /* bridge */ int lastIndexOf(RoomBeanItem roomBeanItem) {
        return super.lastIndexOf(roomBeanItem);
    }

    public final /* bridge */ int lastIndexOf(Object obj) {
        if (!(obj instanceof RoomBeanItem)) {
            return -1;
        }
        return lastIndexOf((RoomBeanItem) obj);
    }

    public final /* bridge */ RoomBeanItem remove(int i) {
        return removeAt(i);
    }

    public /* bridge */ boolean remove(RoomBeanItem roomBeanItem) {
        return super.remove(roomBeanItem);
    }

    public final /* bridge */ boolean remove(Object obj) {
        if (!(obj instanceof RoomBeanItem)) {
            return false;
        }
        return remove((RoomBeanItem) obj);
    }

    public /* bridge */ RoomBeanItem removeAt(int i) {
        return (RoomBeanItem) super.remove(i);
    }

    public final /* bridge */ int size() {
        return getSize();
    }
}
