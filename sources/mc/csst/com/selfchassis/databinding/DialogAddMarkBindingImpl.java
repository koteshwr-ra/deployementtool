package mc.csst.com.selfchassis.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwner;
import com.ciot.sentrymove.R;

public class DialogAddMarkBindingImpl extends DialogAddMarkBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView1;

    public boolean setVariable(int i, Object obj) {
        return true;
    }

    static {
        ViewDataBinding.IncludedLayouts includedLayouts = new ViewDataBinding.IncludedLayouts(24);
        sIncludes = includedLayouts;
        includedLayouts.setIncludes(1, new String[]{"dialog_bottom"}, new int[]{2}, new int[]{R.layout.dialog_bottom});
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.title_cl, 3);
        sViewsWithIds.put(R.id.dialog_title, 4);
        sViewsWithIds.put(R.id.location_tv, 5);
        sViewsWithIds.put(R.id.x_tv, 6);
        sViewsWithIds.put(R.id.x_et, 7);
        sViewsWithIds.put(R.id.y_tv, 8);
        sViewsWithIds.put(R.id.y_et, 9);
        sViewsWithIds.put(R.id.t_tv, 10);
        sViewsWithIds.put(R.id.t_et, 11);
        sViewsWithIds.put(R.id.location_type_tv, 12);
        sViewsWithIds.put(R.id.point_type_normal_rb, 13);
        sViewsWithIds.put(R.id.point_type_charge_rb, 14);
        sViewsWithIds.put(R.id.point_type_gate_rb, 15);
        sViewsWithIds.put(R.id.point_type_access_control_rb, 16);
        sViewsWithIds.put(R.id.point_type_in_the_lift_rb, 17);
        sViewsWithIds.put(R.id.point_type_outside_the_lift_rb, 18);
        sViewsWithIds.put(R.id.point_type_stop_rb, 19);
        sViewsWithIds.put(R.id.point_type_wait_rb, 20);
        sViewsWithIds.put(R.id.point_outward_of_charging_pile, 21);
        sViewsWithIds.put(R.id.location_name_tv, 22);
        sViewsWithIds.put(R.id.location_name_et, 23);
    }

    public DialogAddMarkBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 24, sIncludes, sViewsWithIds));
    }

    private DialogAddMarkBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 1, objArr[2], objArr[4], objArr[23], objArr[22], objArr[5], objArr[12], objArr[21], objArr[16], objArr[14], objArr[15], objArr[17], objArr[13], objArr[18], objArr[19], objArr[20], objArr[0], objArr[11], objArr[10], objArr[3], objArr[7], objArr[6], objArr[9], objArr[8]);
        this.mDirtyFlags = -1;
        ConstraintLayout constraintLayout = objArr[1];
        this.mboundView1 = constraintLayout;
        constraintLayout.setTag((Object) null);
        this.rootRl.setTag((Object) null);
        setRootTag(view);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 2;
        }
        this.buttons.invalidateAll();
        requestRebind();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0016, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0013, code lost:
        if (r6.buttons.hasPendingBindings() == false) goto L_0x0016;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0015, code lost:
        return true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean hasPendingBindings() {
        /*
            r6 = this;
            monitor-enter(r6)
            long r0 = r6.mDirtyFlags     // Catch:{ all -> 0x0018 }
            r2 = 0
            r4 = 1
            int r5 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r5 == 0) goto L_0x000c
            monitor-exit(r6)     // Catch:{ all -> 0x0018 }
            return r4
        L_0x000c:
            monitor-exit(r6)     // Catch:{ all -> 0x0018 }
            mc.csst.com.selfchassis.databinding.DialogBottomBinding r0 = r6.buttons
            boolean r0 = r0.hasPendingBindings()
            if (r0 == 0) goto L_0x0016
            return r4
        L_0x0016:
            r0 = 0
            return r0
        L_0x0018:
            r0 = move-exception
            monitor-exit(r6)     // Catch:{ all -> 0x0018 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: mc.csst.com.selfchassis.databinding.DialogAddMarkBindingImpl.hasPendingBindings():boolean");
    }

    public void setLifecycleOwner(LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        this.buttons.setLifecycleOwner(lifecycleOwner);
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i != 0) {
            return false;
        }
        return onChangeButtons((DialogBottomBinding) obj, i2);
    }

    private boolean onChangeButtons(DialogBottomBinding dialogBottomBinding, int i) {
        if (i != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void executeBindings() {
        synchronized (this) {
            this.mDirtyFlags = 0;
        }
        executeBindingsOn(this.buttons);
    }
}
