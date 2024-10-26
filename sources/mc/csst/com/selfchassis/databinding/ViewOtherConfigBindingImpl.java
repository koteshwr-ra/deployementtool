package mc.csst.com.selfchassis.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.ciot.sentrymove.R;

public class ViewOtherConfigBindingImpl extends ViewOtherConfigBinding {
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
        sparseIntArray.put(R.id.tv_set_other, 1);
        sViewsWithIds.put(R.id.cl_switch_smooth, 2);
        sViewsWithIds.put(R.id.switch_smooth, 3);
        sViewsWithIds.put(R.id.cl_security_threshold, 4);
        sViewsWithIds.put(R.id.tv_security_threshold, 5);
        sViewsWithIds.put(R.id.sb_threshold, 6);
        sViewsWithIds.put(R.id.cl_security_threshold_calculate, 7);
        sViewsWithIds.put(R.id.tv_calculate_threshold, 8);
        sViewsWithIds.put(R.id.img_recalculate, 9);
        sViewsWithIds.put(R.id.tv_recalculate, 10);
        sViewsWithIds.put(R.id.btn_set_as_threshold, 11);
        sViewsWithIds.put(R.id.cl_elevator, 12);
        sViewsWithIds.put(R.id.btn_elevator_set, 13);
        sViewsWithIds.put(R.id.cl_door, 14);
        sViewsWithIds.put(R.id.tv_door_control_size, 15);
        sViewsWithIds.put(R.id.cl_gate, 16);
        sViewsWithIds.put(R.id.tv_gate_control_size, 17);
        sViewsWithIds.put(R.id.cl_depth_camera_up_probe_calibration, 18);
        sViewsWithIds.put(R.id.btn_depth_camera_up_probe_calibration, 19);
        sViewsWithIds.put(R.id.cl_depth_camera_down_probe_calibration, 20);
        sViewsWithIds.put(R.id.btn_depth_camera_down_probe_calibration, 21);
        sViewsWithIds.put(R.id.cl_label_camera_install_type, 22);
        sViewsWithIds.put(R.id.rg_label_camera_install_coordinate_type, 23);
        sViewsWithIds.put(R.id.rb_d1, 24);
        sViewsWithIds.put(R.id.rb_d3, 25);
        sViewsWithIds.put(R.id.cl_located_type, 26);
        sViewsWithIds.put(R.id.rg_located_type, 27);
        sViewsWithIds.put(R.id.rb_laser_radar, 28);
        sViewsWithIds.put(R.id.rb_speedometer, 29);
        sViewsWithIds.put(R.id.cl_ultrasonic_beep, 30);
        sViewsWithIds.put(R.id.ll_ultrasonic_beep, 31);
        sViewsWithIds.put(R.id.tv_ultrasonic_beep, 32);
        sViewsWithIds.put(R.id.tv_ultrasonic_beep_tip, 33);
        sViewsWithIds.put(R.id.switch_ultrasonic_beep, 34);
    }

    public ViewOtherConfigBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 35, sIncludes, sViewsWithIds));
    }

    private ViewOtherConfigBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, objArr[21], objArr[19], objArr[13], objArr[11], objArr[20], objArr[18], objArr[14], objArr[12], objArr[16], objArr[22], objArr[26], objArr[0], objArr[4], objArr[7], objArr[2], objArr[30], objArr[9], objArr[31], objArr[24], objArr[25], objArr[28], objArr[29], objArr[23], objArr[27], objArr[6], objArr[3], objArr[34], objArr[8], objArr[15], objArr[17], objArr[10], objArr[5], objArr[1], objArr[32], objArr[33]);
        this.mDirtyFlags = -1;
        this.clOtherSet.setTag((Object) null);
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
