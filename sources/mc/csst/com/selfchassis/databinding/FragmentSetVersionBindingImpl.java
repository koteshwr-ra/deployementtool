package mc.csst.com.selfchassis.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.Observable;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.ciot.sentrymove.R;
import mc.csst.com.selfchassis.ui.fragment.set.version.VersionInfo;

public class FragmentSetVersionBindingImpl extends FragmentSetVersionBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final LinearLayoutCompat mboundView0;
    private final AppCompatTextView mboundView1;
    private final AppCompatTextView mboundView2;
    private final AppCompatTextView mboundView3;
    private final AppCompatTextView mboundView4;
    private final AppCompatTextView mboundView5;
    private final AppCompatTextView mboundView6;
    private final AppCompatTextView mboundView7;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.btn_update_algorithm_usb, 8);
        sViewsWithIds.put(R.id.btn_update_algorithm, 9);
        sViewsWithIds.put(R.id.btn_update_drive_firmware, 10);
        sViewsWithIds.put(R.id.cl_update_time, 11);
        sViewsWithIds.put(R.id.btn_update_time, 12);
    }

    public FragmentSetVersionBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 13, sIncludes, sViewsWithIds));
    }

    private FragmentSetVersionBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 1, objArr[9], objArr[8], objArr[10], objArr[12], objArr[11]);
        this.mDirtyFlags = -1;
        LinearLayoutCompat linearLayoutCompat = objArr[0];
        this.mboundView0 = linearLayoutCompat;
        linearLayoutCompat.setTag((Object) null);
        AppCompatTextView appCompatTextView = objArr[1];
        this.mboundView1 = appCompatTextView;
        appCompatTextView.setTag((Object) null);
        AppCompatTextView appCompatTextView2 = objArr[2];
        this.mboundView2 = appCompatTextView2;
        appCompatTextView2.setTag((Object) null);
        AppCompatTextView appCompatTextView3 = objArr[3];
        this.mboundView3 = appCompatTextView3;
        appCompatTextView3.setTag((Object) null);
        AppCompatTextView appCompatTextView4 = objArr[4];
        this.mboundView4 = appCompatTextView4;
        appCompatTextView4.setTag((Object) null);
        AppCompatTextView appCompatTextView5 = objArr[5];
        this.mboundView5 = appCompatTextView5;
        appCompatTextView5.setTag((Object) null);
        AppCompatTextView appCompatTextView6 = objArr[6];
        this.mboundView6 = appCompatTextView6;
        appCompatTextView6.setTag((Object) null);
        AppCompatTextView appCompatTextView7 = objArr[7];
        this.mboundView7 = appCompatTextView7;
        appCompatTextView7.setTag((Object) null);
        setRootTag(view);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 64;
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

    public boolean setVariable(int i, Object obj) {
        if (25 != i) {
            return false;
        }
        setVersion((VersionInfo) obj);
        return true;
    }

    public void setVersion(VersionInfo versionInfo) {
        updateRegistration(0, (Observable) versionInfo);
        this.mVersion = versionInfo;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(25);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i != 0) {
            return false;
        }
        return onChangeVersion((VersionInfo) obj, i2);
    }

    private boolean onChangeVersion(VersionInfo versionInfo, int i) {
        if (i == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        } else if (i == 1) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        } else if (i == 9) {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        } else if (i == 10) {
            synchronized (this) {
                this.mDirtyFlags |= 8;
            }
            return true;
        } else if (i == 11) {
            synchronized (this) {
                this.mDirtyFlags |= 16;
            }
            return true;
        } else if (i != 19) {
            return false;
        } else {
            synchronized (this) {
                this.mDirtyFlags |= 32;
            }
            return true;
        }
    }

    /* access modifiers changed from: protected */
    public void executeBindings() {
        long j;
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        String str7;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        VersionInfo versionInfo = this.mVersion;
        String str8 = null;
        if ((127 & j) != 0) {
            if ((j & 65) == 0 || versionInfo == null) {
                str6 = null;
                str5 = null;
            } else {
                str6 = versionInfo.getCurVersion();
                str5 = versionInfo.getChassisType();
            }
            String robotChassisNumber = ((j & 97) == 0 || versionInfo == null) ? null : versionInfo.getRobotChassisNumber();
            String curTime = ((j & 81) == 0 || versionInfo == null) ? null : versionInfo.getCurTime();
            String chassisDriveFirmwareVersion = ((j & 69) == 0 || versionInfo == null) ? null : versionInfo.getChassisDriveFirmwareVersion();
            String chassisHardwareVersion = ((j & 73) == 0 || versionInfo == null) ? null : versionInfo.getChassisHardwareVersion();
            if (!((j & 67) == 0 || versionInfo == null)) {
                str8 = versionInfo.getAlgorithmVersion();
            }
            str7 = str8;
            str = robotChassisNumber;
            str2 = curTime;
            str4 = chassisDriveFirmwareVersion;
            str3 = chassisHardwareVersion;
        } else {
            str7 = null;
            str6 = null;
            str5 = null;
            str4 = null;
            str3 = null;
            str2 = null;
            str = null;
        }
        if ((j & 65) != 0) {
            TextViewBindingAdapter.setText(this.mboundView1, str6);
            TextViewBindingAdapter.setText(this.mboundView7, str5);
        }
        if ((j & 67) != 0) {
            TextViewBindingAdapter.setText(this.mboundView2, str7);
        }
        if ((j & 69) != 0) {
            TextViewBindingAdapter.setText(this.mboundView3, str4);
        }
        if ((j & 73) != 0) {
            TextViewBindingAdapter.setText(this.mboundView4, str3);
        }
        if ((81 & j) != 0) {
            TextViewBindingAdapter.setText(this.mboundView5, str2);
        }
        if ((j & 97) != 0) {
            TextViewBindingAdapter.setText(this.mboundView6, str);
        }
    }
}
