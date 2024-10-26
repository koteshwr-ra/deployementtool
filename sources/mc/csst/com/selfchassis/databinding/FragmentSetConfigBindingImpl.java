package mc.csst.com.selfchassis.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwner;
import com.ciot.sentrymove.R;

public class FragmentSetConfigBindingImpl extends FragmentSetConfigBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final LinearLayoutCompat mboundView1;

    public boolean setVariable(int i, Object obj) {
        return true;
    }

    static {
        ViewDataBinding.IncludedLayouts includedLayouts = new ViewDataBinding.IncludedLayouts(26);
        sIncludes = includedLayouts;
        includedLayouts.setIncludes(1, new String[]{"view_nav_param_config", "view_other_config"}, new int[]{2, 3}, new int[]{R.layout.view_nav_param_config, R.layout.view_other_config});
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.cl_operation, 4);
        sViewsWithIds.put(R.id.tv_operation, 5);
        sViewsWithIds.put(R.id.cl_nav_mode, 6);
        sViewsWithIds.put(R.id.rg_nav_mode, 7);
        sViewsWithIds.put(R.id.rb_nav_auto, 8);
        sViewsWithIds.put(R.id.rb_nav_virtual, 9);
        sViewsWithIds.put(R.id.cl_speed, 10);
        sViewsWithIds.put(R.id.tv_speed_config, 11);
        sViewsWithIds.put(R.id.sb_speed_config, 12);
        sViewsWithIds.put(R.id.cl_travel, 13);
        sViewsWithIds.put(R.id.rg_travel, 14);
        sViewsWithIds.put(R.id.rb_security, 15);
        sViewsWithIds.put(R.id.rb_balance, 16);
        sViewsWithIds.put(R.id.rb_efficiency, 17);
        sViewsWithIds.put(R.id.switch_push_mode, 18);
        sViewsWithIds.put(R.id.switch_manual, 19);
        sViewsWithIds.put(R.id.rg_control, 20);
        sViewsWithIds.put(R.id.rb_key_four, 21);
        sViewsWithIds.put(R.id.rb_key_eight, 22);
        sViewsWithIds.put(R.id.cl_sensor, 23);
        sViewsWithIds.put(R.id.btn_save_set, 24);
        sViewsWithIds.put(R.id.rv_sensor, 25);
    }

    public FragmentSetConfigBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 26, sIncludes, sViewsWithIds));
    }

    private FragmentSetConfigBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 2, objArr[24], objArr[6], objArr[4], objArr[23], objArr[10], objArr[13], objArr[2], objArr[3], objArr[16], objArr[17], objArr[22], objArr[21], objArr[8], objArr[9], objArr[15], objArr[20], objArr[7], objArr[14], objArr[25], objArr[12], objArr[0], objArr[19], objArr[18], objArr[5], objArr[11]);
        this.mDirtyFlags = -1;
        LinearLayoutCompat linearLayoutCompat = objArr[1];
        this.mboundView1 = linearLayoutCompat;
        linearLayoutCompat.setTag((Object) null);
        this.svConfig.setTag((Object) null);
        setRootTag(view);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 4;
        }
        this.includeNavParamConfig.invalidateAll();
        this.includeOtherConfig.invalidateAll();
        requestRebind();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001c, code lost:
        if (r6.includeOtherConfig.hasPendingBindings() == false) goto L_0x001f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001e, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001f, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0013, code lost:
        if (r6.includeNavParamConfig.hasPendingBindings() == false) goto L_0x0016;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0015, code lost:
        return true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean hasPendingBindings() {
        /*
            r6 = this;
            monitor-enter(r6)
            long r0 = r6.mDirtyFlags     // Catch:{ all -> 0x0021 }
            r2 = 0
            r4 = 1
            int r5 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r5 == 0) goto L_0x000c
            monitor-exit(r6)     // Catch:{ all -> 0x0021 }
            return r4
        L_0x000c:
            monitor-exit(r6)     // Catch:{ all -> 0x0021 }
            mc.csst.com.selfchassis.databinding.ViewNavParamConfigBinding r0 = r6.includeNavParamConfig
            boolean r0 = r0.hasPendingBindings()
            if (r0 == 0) goto L_0x0016
            return r4
        L_0x0016:
            mc.csst.com.selfchassis.databinding.ViewOtherConfigBinding r0 = r6.includeOtherConfig
            boolean r0 = r0.hasPendingBindings()
            if (r0 == 0) goto L_0x001f
            return r4
        L_0x001f:
            r0 = 0
            return r0
        L_0x0021:
            r0 = move-exception
            monitor-exit(r6)     // Catch:{ all -> 0x0021 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: mc.csst.com.selfchassis.databinding.FragmentSetConfigBindingImpl.hasPendingBindings():boolean");
    }

    public void setLifecycleOwner(LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        this.includeNavParamConfig.setLifecycleOwner(lifecycleOwner);
        this.includeOtherConfig.setLifecycleOwner(lifecycleOwner);
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i == 0) {
            return onChangeIncludeNavParamConfig((ViewNavParamConfigBinding) obj, i2);
        }
        if (i != 1) {
            return false;
        }
        return onChangeIncludeOtherConfig((ViewOtherConfigBinding) obj, i2);
    }

    private boolean onChangeIncludeNavParamConfig(ViewNavParamConfigBinding viewNavParamConfigBinding, int i) {
        if (i != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeIncludeOtherConfig(ViewOtherConfigBinding viewOtherConfigBinding, int i) {
        if (i != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void executeBindings() {
        synchronized (this) {
            this.mDirtyFlags = 0;
        }
        executeBindingsOn(this.includeNavParamConfig);
        executeBindingsOn(this.includeOtherConfig);
    }
}
