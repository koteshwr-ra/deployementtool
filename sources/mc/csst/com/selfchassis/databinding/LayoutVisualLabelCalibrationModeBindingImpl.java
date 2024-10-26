package mc.csst.com.selfchassis.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.ciot.sentrymove.R;

public class LayoutVisualLabelCalibrationModeBindingImpl extends LayoutVisualLabelCalibrationModeBinding {
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
        sparseIntArray.put(R.id.label_info_cl, 1);
        sViewsWithIds.put(R.id.cl_label_camera, 2);
        sViewsWithIds.put(R.id.tv_label_camera_window, 3);
        sViewsWithIds.put(R.id.vlcm_switch_label_camera_window, 4);
        sViewsWithIds.put(R.id.label_info_icon_iv, 5);
        sViewsWithIds.put(R.id.label_info_tv, 6);
        sViewsWithIds.put(R.id.label_info_iv, 7);
        sViewsWithIds.put(R.id.label_list_rv, 8);
    }

    public LayoutVisualLabelCalibrationModeBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 9, sIncludes, sViewsWithIds));
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private LayoutVisualLabelCalibrationModeBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, objArr[2], objArr[1], objArr[5], objArr[7], objArr[6], objArr[8], objArr[0], objArr[3], objArr[4]);
        this.mDirtyFlags = -1;
        this.labelRootCl.setTag((Object) null);
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
