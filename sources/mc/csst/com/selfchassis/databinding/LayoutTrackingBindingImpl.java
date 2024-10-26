package mc.csst.com.selfchassis.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.ciot.sentrymove.R;

public class LayoutTrackingBindingImpl extends LayoutTrackingBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;

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
        sparseIntArray.put(R.id.drawing_switch_view, 1);
        sViewsWithIds.put(R.id.start_drawing_view, 2);
        sViewsWithIds.put(R.id.line_tracking_setting_view, 3);
        sViewsWithIds.put(R.id.line_tracking_list_view, 4);
        sViewsWithIds.put(R.id.intellect_multi_list_ll, 5);
        sViewsWithIds.put(R.id.intellect_multi_title_cl, 6);
        sViewsWithIds.put(R.id.intellect_multi_more_iv, 7);
        sViewsWithIds.put(R.id.intellect_point_list_rv, 8);
        sViewsWithIds.put(R.id.intellect_multi_line_cl, 9);
        sViewsWithIds.put(R.id.intellect_multi_line_title_tv, 10);
        sViewsWithIds.put(R.id.intellect_multi_line_rv, 11);
        sViewsWithIds.put(R.id.intellect_multi_line_empty_tv, 12);
        sViewsWithIds.put(R.id.btn_sure, 13);
    }

    public LayoutTrackingBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 14, sIncludes, sViewsWithIds));
    }

    private LayoutTrackingBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, objArr[13], objArr[1], objArr[9], objArr[12], objArr[11], objArr[10], objArr[5], objArr[7], objArr[6], objArr[8], objArr[4], objArr[3], objArr[2]);
        this.mDirtyFlags = -1;
        ConstraintLayout constraintLayout = objArr[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag((Object) null);
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
