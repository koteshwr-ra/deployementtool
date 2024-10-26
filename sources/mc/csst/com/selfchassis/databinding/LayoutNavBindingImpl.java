package mc.csst.com.selfchassis.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.ciot.sentrymove.R;

public class LayoutNavBindingImpl extends LayoutNavBinding {
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
        sparseIntArray.put(R.id.nav_type_rg, 1);
        sViewsWithIds.put(R.id.nav_type_single_rb, 2);
        sViewsWithIds.put(R.id.nav_type_multi_rb, 3);
        sViewsWithIds.put(R.id.nav_multi_line_cl, 4);
        sViewsWithIds.put(R.id.nav_multi_line_title_tv, 5);
        sViewsWithIds.put(R.id.nav_multi_line_rv, 6);
        sViewsWithIds.put(R.id.nav_multi_clear_btn, 7);
        sViewsWithIds.put(R.id.nav_multi_line_empty_tv, 8);
        sViewsWithIds.put(R.id.nav_multi_line_tip, 9);
        sViewsWithIds.put(R.id.nav_multi_list_ll, 10);
        sViewsWithIds.put(R.id.nav_multi_title_cl, 11);
        sViewsWithIds.put(R.id.nav_multi_list_icon_iv, 12);
        sViewsWithIds.put(R.id.nav_multi_more_iv, 13);
        sViewsWithIds.put(R.id.nav_multi_list_rv, 14);
        sViewsWithIds.put(R.id.nav_set_cl, 15);
        sViewsWithIds.put(R.id.radioGroup, 16);
        sViewsWithIds.put(R.id.nav_multi_single_rb, 17);
        sViewsWithIds.put(R.id.nav_multi_back_rb, 18);
        sViewsWithIds.put(R.id.nav_multi_cycle_rb, 19);
        sViewsWithIds.put(R.id.nav_multi_times_cl, 20);
        sViewsWithIds.put(R.id.nav_multi_times_title_tv, 21);
        sViewsWithIds.put(R.id.nav_multi_times_et, 22);
        sViewsWithIds.put(R.id.nav_multi_times_tail_tv, 23);
        sViewsWithIds.put(R.id.nav_start_nav_cl, 24);
        sViewsWithIds.put(R.id.nav_start_icon_iv, 25);
        sViewsWithIds.put(R.id.nav_start_tv, 26);
    }

    public LayoutNavBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 27, sIncludes, sViewsWithIds));
    }

    private LayoutNavBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, objArr[18], objArr[7], objArr[19], objArr[4], objArr[8], objArr[6], objArr[9], objArr[5], objArr[12], objArr[10], objArr[14], objArr[13], objArr[17], objArr[20], objArr[22], objArr[23], objArr[21], objArr[11], objArr[0], objArr[15], objArr[25], objArr[24], objArr[26], objArr[3], objArr[1], objArr[2], objArr[16]);
        this.mDirtyFlags = -1;
        this.navRootCl.setTag((Object) null);
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
