package mc.csst.com.selfchassis.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.ciot.sentrymove.R;

public class FragmentMapBindingImpl extends FragmentMapBinding {
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
        sparseIntArray.put(R.id.tv_map_sync, 1);
        sViewsWithIds.put(R.id.label_upload_map, 2);
        sViewsWithIds.put(R.id.btn_sure_upload, 3);
        sViewsWithIds.put(R.id.mrmpw_upload, 4);
        sViewsWithIds.put(R.id.label_download_map, 5);
        sViewsWithIds.put(R.id.btn_sure_download, 6);
        sViewsWithIds.put(R.id.mrmpw_download, 7);
    }

    public FragmentMapBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 8, sIncludes, sViewsWithIds));
    }

    private FragmentMapBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, objArr[6], objArr[3], objArr[5], objArr[2], objArr[7], objArr[4], objArr[1]);
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
