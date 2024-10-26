package mc.csst.com.selfchassis.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.ciot.sentrymove.R;

public class FragmentSetMapUploadBindingImpl extends FragmentSetMapUploadBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;

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
        sparseIntArray.put(R.id.cl_select_map_root, 1);
        sViewsWithIds.put(R.id.tv_upload_map_title, 2);
        sViewsWithIds.put(R.id.cl_upload_map_info, 3);
        sViewsWithIds.put(R.id.tv_upload_map_info, 4);
        sViewsWithIds.put(R.id.img_upload_map_arrow, 5);
        sViewsWithIds.put(R.id.view, 6);
        sViewsWithIds.put(R.id.btn_upload_map, 7);
        sViewsWithIds.put(R.id.cl_other_set, 8);
        sViewsWithIds.put(R.id.tv_set_other, 9);
        sViewsWithIds.put(R.id.cl_security_threshold, 10);
        sViewsWithIds.put(R.id.tv_security_threshold, 11);
        sViewsWithIds.put(R.id.sb_threshold, 12);
        sViewsWithIds.put(R.id.cl_security_threshold_calculate, 13);
        sViewsWithIds.put(R.id.tv_calculate_threshold, 14);
        sViewsWithIds.put(R.id.tv_recalculate, 15);
        sViewsWithIds.put(R.id.btn_set_as_threshold, 16);
    }

    public FragmentSetMapUploadBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 17, sIncludes, sViewsWithIds));
    }

    private FragmentSetMapUploadBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, objArr[16], objArr[7], objArr[8], objArr[10], objArr[13], objArr[1], objArr[3], objArr[5], objArr[12], objArr[0], objArr[14], objArr[15], objArr[11], objArr[9], objArr[4], objArr[2], objArr[6]);
        this.mDirtyFlags = -1;
        this.svConfig.setTag((Object) null);
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
