package mc.csst.com.selfchassis.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.ciot.sentrymove.R;

public class FragmentSetOnlineUpdateConfigBindingImpl extends FragmentSetOnlineUpdateConfigBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final LinearLayoutCompat mboundView0;

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    public boolean setVariable(int i, Object obj) {
        return true;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.ll_back, 1);
        sViewsWithIds.put(R.id.img_back, 2);
        sViewsWithIds.put(R.id.tv_title, 3);
        sViewsWithIds.put(R.id.icon_btn_add, 4);
        sViewsWithIds.put(R.id.tv_select_upgrade_link_title, 5);
        sViewsWithIds.put(R.id.cl_upgrade_link, 6);
        sViewsWithIds.put(R.id.tv_upgrade_link, 7);
        sViewsWithIds.put(R.id.tv_link_desc, 8);
        sViewsWithIds.put(R.id.img_link_arrow, 9);
        sViewsWithIds.put(R.id.view, 10);
        sViewsWithIds.put(R.id.btn_update_now, 11);
        sViewsWithIds.put(R.id.card_upgrade_link, 12);
        sViewsWithIds.put(R.id.rv_upgrade_link, 13);
    }

    public FragmentSetOnlineUpdateConfigBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 14, sIncludes, sViewsWithIds));
    }

    private FragmentSetOnlineUpdateConfigBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, objArr[11], objArr[12], objArr[6], objArr[4], objArr[2], objArr[9], objArr[1], objArr[13], objArr[8], objArr[5], objArr[3], objArr[7], objArr[10]);
        this.mDirtyFlags = -1;
        LinearLayoutCompat linearLayoutCompat = objArr[0];
        this.mboundView0 = linearLayoutCompat;
        linearLayoutCompat.setTag((Object) null);
        setRootTag(view);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 1;
        }
        requestRebind();
    }

    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.mDirtyFlags != 0) {
                return true;
            }
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public void executeBindings() {
        synchronized (this) {
            this.mDirtyFlags = 0;
        }
    }
}
