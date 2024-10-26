package mc.csst.com.selfchassis.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.ciot.sentrymove.R;

public class ViewNavParamConfigBindingImpl extends ViewNavParamConfigBinding {
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
        sparseIntArray.put(R.id.tv_nav_param_config, 1);
        sViewsWithIds.put(R.id.cl_point_tolerance_setting, 2);
        sViewsWithIds.put(R.id.tv_point_position_title, 3);
        sViewsWithIds.put(R.id.ll_point_position, 4);
        sViewsWithIds.put(R.id.tv_point_position, 5);
        sViewsWithIds.put(R.id.tv_point_rad_separator, 6);
        sViewsWithIds.put(R.id.tv_point_rad_title, 7);
        sViewsWithIds.put(R.id.ll_point_rad, 8);
        sViewsWithIds.put(R.id.tv_point_rad, 9);
        sViewsWithIds.put(R.id.cl_safe_stop_distance, 10);
        sViewsWithIds.put(R.id.ll_safe_stop_distance, 11);
        sViewsWithIds.put(R.id.tv_safe_stop_distance, 12);
        sViewsWithIds.put(R.id.cl_laser_radar_obstacle_detection, 13);
        sViewsWithIds.put(R.id.title_laser_radar_obstacle_detection, 14);
        sViewsWithIds.put(R.id.switch_laser_radar_obstacle_detection, 15);
        sViewsWithIds.put(R.id.tip_unit, 16);
        sViewsWithIds.put(R.id.ll_laser_radar_obstacle_detection, 17);
        sViewsWithIds.put(R.id.tv_laser_radar_obstacle_detection, 18);
        sViewsWithIds.put(R.id.cl_nav_failure_time, 19);
        sViewsWithIds.put(R.id.ll_nav_failure_time, 20);
        sViewsWithIds.put(R.id.tv_nav_failure_time, 21);
    }

    public ViewNavParamConfigBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 22, sIncludes, sViewsWithIds));
    }

    private ViewNavParamConfigBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, objArr[13], objArr[19], objArr[0], objArr[2], objArr[10], objArr[17], objArr[20], objArr[4], objArr[8], objArr[11], objArr[15], objArr[16], objArr[14], objArr[18], objArr[21], objArr[1], objArr[5], objArr[3], objArr[9], objArr[6], objArr[7], objArr[12]);
        this.mDirtyFlags = -1;
        this.clNavParamConfig.setTag((Object) null);
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
