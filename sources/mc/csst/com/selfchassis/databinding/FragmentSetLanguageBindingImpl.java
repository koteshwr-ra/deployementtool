package mc.csst.com.selfchassis.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.ciot.sentrymove.R;

public class FragmentSetLanguageBindingImpl extends FragmentSetLanguageBinding {
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
        sparseIntArray.put(R.id.tv_switch_language, 1);
        sViewsWithIds.put(R.id.fl_switch_language, 2);
        sViewsWithIds.put(R.id.rg_language, 3);
        sViewsWithIds.put(R.id.rb_simplified_chinese, 4);
        sViewsWithIds.put(R.id.rb_traditional_chinese, 5);
        sViewsWithIds.put(R.id.rb_english, 6);
        sViewsWithIds.put(R.id.rb_german, 7);
        sViewsWithIds.put(R.id.rb_japanese, 8);
        sViewsWithIds.put(R.id.rb_korean, 9);
        sViewsWithIds.put(R.id.rb_thai, 10);
        sViewsWithIds.put(R.id.rb_arabic, 11);
        sViewsWithIds.put(R.id.rb_indonesian, 12);
        sViewsWithIds.put(R.id.rb_hebrew, 13);
        sViewsWithIds.put(R.id.rb_turkish, 14);
        sViewsWithIds.put(R.id.line1, 15);
        sViewsWithIds.put(R.id.label_gateway, 16);
        sViewsWithIds.put(R.id.btn_sure_switch, 17);
        sViewsWithIds.put(R.id.gpw_download, 18);
    }

    public FragmentSetLanguageBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 19, sIncludes, sViewsWithIds));
    }

    private FragmentSetLanguageBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, objArr[17], objArr[2], objArr[18], objArr[16], objArr[15], objArr[11], objArr[6], objArr[7], objArr[13], objArr[12], objArr[8], objArr[9], objArr[4], objArr[10], objArr[5], objArr[14], objArr[3], objArr[1]);
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
