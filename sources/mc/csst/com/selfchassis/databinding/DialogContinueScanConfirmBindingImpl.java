package mc.csst.com.selfchassis.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.ciot.sentrymove.R;

public class DialogContinueScanConfirmBindingImpl extends DialogContinueScanConfirmBinding {
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
        sparseIntArray.put(R.id.btn_top_exit, 1);
        sViewsWithIds.put(R.id.title, 2);
        sViewsWithIds.put(R.id.message, 3);
        sViewsWithIds.put(R.id.box_button, 4);
        sViewsWithIds.put(R.id.btn_selectNegative, 5);
        sViewsWithIds.put(R.id.btn_selectOther, 6);
        sViewsWithIds.put(R.id.btn_selectPositive, 7);
        sViewsWithIds.put(R.id.ll_desc, 8);
        sViewsWithIds.put(R.id.cb_desc, 9);
    }

    public DialogContinueScanConfirmBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 10, sIncludes, sViewsWithIds));
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private DialogContinueScanConfirmBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, objArr[4], objArr[5], objArr[6], objArr[7], objArr[1], objArr[9], objArr[8], objArr[3], objArr[2]);
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
