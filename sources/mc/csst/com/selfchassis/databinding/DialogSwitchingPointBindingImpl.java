package mc.csst.com.selfchassis.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.ciot.sentrymove.R;

public class DialogSwitchingPointBindingImpl extends DialogSwitchingPointBinding {
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
        sparseIntArray.put(R.id.title_lin, 1);
        sViewsWithIds.put(R.id.point_title_tv, 2);
        sViewsWithIds.put(R.id.exit_btn, 3);
        sViewsWithIds.put(R.id.rv_lin, 4);
        sViewsWithIds.put(R.id.floor_swipe_refresh, 5);
        sViewsWithIds.put(R.id.build_name_rv, 6);
        sViewsWithIds.put(R.id.marker_recycle, 7);
    }

    public DialogSwitchingPointBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 8, sIncludes, sViewsWithIds));
    }

    private DialogSwitchingPointBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, objArr[6], objArr[3], objArr[5], objArr[7], objArr[2], objArr[4], objArr[1]);
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
