package mc.csst.com.selfchassis.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.ciot.sentrymove.R;

public class FragmentScheduleBindingImpl extends FragmentScheduleBinding {
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
        sparseIntArray.put(R.id.cl_scheduling_setting, 1);
        sViewsWithIds.put(R.id.tv_operation, 2);
        sViewsWithIds.put(R.id.tv_multi_robot_mode, 3);
        sViewsWithIds.put(R.id.switch_multi_robot_mode, 4);
        sViewsWithIds.put(R.id.divider, 5);
        sViewsWithIds.put(R.id.cl_network_change, 6);
        sViewsWithIds.put(R.id.tv_network_change_label, 7);
        sViewsWithIds.put(R.id.rg_change_network, 8);
        sViewsWithIds.put(R.id.rb_local_network, 9);
        sViewsWithIds.put(R.id.rb_public_work, 10);
        sViewsWithIds.put(R.id.divider2, 11);
        sViewsWithIds.put(R.id.cl_network_config, 12);
        sViewsWithIds.put(R.id.tv_network_setting_label, 13);
        sViewsWithIds.put(R.id.tv_ipaddress, 14);
        sViewsWithIds.put(R.id.btn_modify, 15);
        sViewsWithIds.put(R.id.cl_project_name, 16);
        sViewsWithIds.put(R.id.tv_project_name_label, 17);
        sViewsWithIds.put(R.id.tv_project_name, 18);
        sViewsWithIds.put(R.id.btn_modify_name, 19);
        sViewsWithIds.put(R.id.cl_map_sync_config, 20);
        sViewsWithIds.put(R.id.tv_map_sync, 21);
        sViewsWithIds.put(R.id.label_upload_map, 22);
        sViewsWithIds.put(R.id.btn_sure_upload, 23);
        sViewsWithIds.put(R.id.mrmpw_upload, 24);
        sViewsWithIds.put(R.id.label_download_map, 25);
        sViewsWithIds.put(R.id.btn_sure_download, 26);
        sViewsWithIds.put(R.id.mrmpw_download, 27);
    }

    public FragmentScheduleBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 28, sIncludes, sViewsWithIds));
    }

    private FragmentScheduleBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, objArr[15], objArr[19], objArr[26], objArr[23], objArr[20], objArr[6], objArr[12], objArr[16], objArr[1], objArr[5], objArr[11], objArr[25], objArr[22], objArr[27], objArr[24], objArr[9], objArr[10], objArr[8], objArr[4], objArr[14], objArr[21], objArr[3], objArr[7], objArr[13], objArr[2], objArr[18], objArr[17]);
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
