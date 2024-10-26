package com.ciot.networklib.bean.hotel;

import java.util.ArrayList;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u0012\u0012\u0004\u0012\u00020\u00020\u0001j\b\u0012\u0004\u0012\u00020\u0002`\u0003B\u0005¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"Lcom/ciot/networklib/bean/hotel/ResigerTypeBean;", "Ljava/util/ArrayList;", "Lcom/ciot/networklib/bean/hotel/ResigerTypeBeanItem;", "Lkotlin/collections/ArrayList;", "()V", "library-networkLib_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: ResigerTypeBean.kt */
public final class ResigerTypeBean extends ArrayList<ResigerTypeBeanItem> {
    public /* bridge */ boolean contains(ResigerTypeBeanItem resigerTypeBeanItem) {
        return super.contains(resigerTypeBeanItem);
    }

    public final /* bridge */ boolean contains(Object obj) {
        if (!(obj instanceof ResigerTypeBeanItem)) {
            return false;
        }
        return contains((ResigerTypeBeanItem) obj);
    }

    public /* bridge */ int getSize() {
        return super.size();
    }

    public /* bridge */ int indexOf(ResigerTypeBeanItem resigerTypeBeanItem) {
        return super.indexOf(resigerTypeBeanItem);
    }

    public final /* bridge */ int indexOf(Object obj) {
        if (!(obj instanceof ResigerTypeBeanItem)) {
            return -1;
        }
        return indexOf((ResigerTypeBeanItem) obj);
    }

    public /* bridge */ int lastIndexOf(ResigerTypeBeanItem resigerTypeBeanItem) {
        return super.lastIndexOf(resigerTypeBeanItem);
    }

    public final /* bridge */ int lastIndexOf(Object obj) {
        if (!(obj instanceof ResigerTypeBeanItem)) {
            return -1;
        }
        return lastIndexOf((ResigerTypeBeanItem) obj);
    }

    public final /* bridge */ ResigerTypeBeanItem remove(int i) {
        return removeAt(i);
    }

    public /* bridge */ boolean remove(ResigerTypeBeanItem resigerTypeBeanItem) {
        return super.remove(resigerTypeBeanItem);
    }

    public final /* bridge */ boolean remove(Object obj) {
        if (!(obj instanceof ResigerTypeBeanItem)) {
            return false;
        }
        return remove((ResigerTypeBeanItem) obj);
    }

    public /* bridge */ ResigerTypeBeanItem removeAt(int i) {
        return (ResigerTypeBeanItem) super.remove(i);
    }

    public final /* bridge */ int size() {
        return getSize();
    }
}
