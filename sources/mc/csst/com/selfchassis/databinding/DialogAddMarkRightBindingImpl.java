package mc.csst.com.selfchassis.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.ciot.sentrymove.R;

public class DialogAddMarkRightBindingImpl extends DialogAddMarkRightBinding {
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
        sparseIntArray.put(R.id.dialog_title, 1);
        sViewsWithIds.put(R.id.location_name_tv, 2);
        sViewsWithIds.put(R.id.location_name_et, 3);
        sViewsWithIds.put(R.id.location_type_tv, 4);
        sViewsWithIds.put(R.id.flowRG1, 5);
        sViewsWithIds.put(R.id.point_type_normal_rb, 6);
        sViewsWithIds.put(R.id.point_type_charge_rb, 7);
        sViewsWithIds.put(R.id.point_type_gate_rb, 8);
        sViewsWithIds.put(R.id.point_type_access_control_rb, 9);
        sViewsWithIds.put(R.id.point_type_in_the_lift_rb, 10);
        sViewsWithIds.put(R.id.flowRG2, 11);
        sViewsWithIds.put(R.id.point_type_outside_the_lift_rb, 12);
        sViewsWithIds.put(R.id.point_type_stop_rb, 13);
        sViewsWithIds.put(R.id.point_type_wait_rb, 14);
        sViewsWithIds.put(R.id.point_outward_of_charging_pile, 15);
        sViewsWithIds.put(R.id.location_tv, 16);
        sViewsWithIds.put(R.id.x_tv, 17);
        sViewsWithIds.put(R.id.x_et, 18);
        sViewsWithIds.put(R.id.y_tv, 19);
        sViewsWithIds.put(R.id.y_et, 20);
        sViewsWithIds.put(R.id.t_tv, 21);
        sViewsWithIds.put(R.id.t_et, 22);
        sViewsWithIds.put(R.id.point_rotate_view, 23);
        sViewsWithIds.put(R.id.box_button, 24);
        sViewsWithIds.put(R.id.btn_selectNegative, 25);
        sViewsWithIds.put(R.id.btn_selectPositive, 26);
    }

    public DialogAddMarkRightBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 27, sIncludes, sViewsWithIds));
    }

    private DialogAddMarkRightBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, objArr[24], objArr[25], objArr[26], objArr[1], objArr[5], objArr[11], objArr[3], objArr[2], objArr[16], objArr[4], objArr[15], objArr[23], objArr[9], objArr[7], objArr[8], objArr[10], objArr[6], objArr[12], objArr[13], objArr[14], objArr[0], objArr[22], objArr[21], objArr[18], objArr[17], objArr[20], objArr[19]);
        this.mDirtyFlags = -1;
        this.rootRl.setTag((Object) null);
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
