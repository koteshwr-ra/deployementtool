package mc.csst.com.selfchassis.databinding;

import android.content.res.Resources;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.SparseIntArray;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.Observable;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.ciot.sentrymove.R;
import mc.csst.com.selfchassis.utils.bean.ShowSelfChassisBean;
import mc.csst.com.selfchassis.utils.view.IconButton;
import okhttp3.internal.ws.WebSocketProtocol;

public class LayoutMainHeadBindingImpl extends LayoutMainHeadBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.head_create_map_ib, 13);
        sViewsWithIds.put(R.id.head_building_cl, 14);
        sViewsWithIds.put(R.id.head_building_floor_iv, 15);
        sViewsWithIds.put(R.id.head_battery_title_tv, 16);
        sViewsWithIds.put(R.id.head_suitability_title_tv, 17);
        sViewsWithIds.put(R.id.head_motion_title_tv, 18);
        sViewsWithIds.put(R.id.head_device_title_tv, 19);
        sViewsWithIds.put(R.id.head_location_title_tv, 20);
        sViewsWithIds.put(R.id.head_title_cl, 21);
        sViewsWithIds.put(R.id.head_location_ll, 22);
    }

    public LayoutMainHeadBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 23, sIncludes, sViewsWithIds));
    }

    private LayoutMainHeadBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 1, objArr[5], objArr[16], objArr[4], objArr[14], objArr[15], objArr[2], objArr[1], objArr[13], objArr[19], objArr[8], objArr[22], objArr[11], objArr[20], objArr[9], objArr[10], objArr[18], objArr[7], objArr[12], objArr[3], objArr[17], objArr[6], objArr[21]);
        this.mDirtyFlags = -1;
        this.headBatteryIv.setTag((Object) null);
        this.headBatteryTv.setTag((Object) null);
        this.headBuildingFloorTv.setTag((Object) null);
        this.headCancelIb.setTag((Object) null);
        this.headDeviceTv.setTag((Object) null);
        this.headLocationTTv.setTag((Object) null);
        this.headLocationXTv.setTag((Object) null);
        this.headLocationYTv.setTag((Object) null);
        this.headMotionTv.setTag((Object) null);
        this.headOnDockStateIv.setTag((Object) null);
        this.headOnDockStateTv.setTag((Object) null);
        this.headSuitabilityTv.setTag((Object) null);
        ConstraintLayout constraintLayout = objArr[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag((Object) null);
        setRootTag(view);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID;
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
        if (21 != i) {
            return false;
        }
        setShowChassisState((ShowSelfChassisBean) obj);
        return true;
    }

    public void setShowChassisState(ShowSelfChassisBean showSelfChassisBean) {
        updateRegistration(0, (Observable) showSelfChassisBean);
        this.mShowChassisState = showSelfChassisBean;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(21);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i != 0) {
            return false;
        }
        return onChangeShowChassisState((ShowSelfChassisBean) obj, i2);
    }

    private boolean onChangeShowChassisState(ShowSelfChassisBean showSelfChassisBean, int i) {
        if (i == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        } else if (i == 13) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        } else if (i == 8) {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        } else if (i == 14) {
            synchronized (this) {
                this.mDirtyFlags |= 8;
            }
            return true;
        } else if (i == 17) {
            synchronized (this) {
                this.mDirtyFlags |= 16;
            }
            return true;
        } else if (i == 4) {
            synchronized (this) {
                this.mDirtyFlags |= 32;
            }
            return true;
        } else if (i == 2) {
            synchronized (this) {
                this.mDirtyFlags |= 64;
            }
            return true;
        } else if (i == 3) {
            synchronized (this) {
                this.mDirtyFlags |= 128;
            }
            return true;
        } else if (i == 16) {
            synchronized (this) {
                this.mDirtyFlags |= 256;
            }
            return true;
        } else if (i == 23) {
            synchronized (this) {
                this.mDirtyFlags |= 512;
            }
            return true;
        } else if (i == 12) {
            synchronized (this) {
                this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
            }
            return true;
        } else if (i == 26) {
            synchronized (this) {
                this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
            }
            return true;
        } else if (i == 27) {
            synchronized (this) {
                this.mDirtyFlags |= PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
            }
            return true;
        } else if (i == 24) {
            synchronized (this) {
                this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_URI;
            }
            return true;
        } else if (i != 18) {
            return false;
        } else {
            synchronized (this) {
                this.mDirtyFlags |= 16384;
            }
            return true;
        }
    }

    /* access modifiers changed from: protected */
    public void executeBindings() {
        long j;
        int i;
        boolean z;
        String str;
        String str2;
        String str3;
        boolean z2;
        String str4;
        int i2;
        String str5;
        String str6;
        String str7;
        String str8;
        String str9;
        String str10;
        int i3;
        String str11;
        String str12;
        String str13;
        boolean z3;
        String str14;
        int i4;
        int i5;
        int i6;
        Resources resources;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        ShowSelfChassisBean showSelfChassisBean = this.mShowChassisState;
        boolean z4 = false;
        String str15 = null;
        if ((WebSocketProtocol.PAYLOAD_SHORT_MAX & j) != 0) {
            int i7 = ((j & 32785) > 0 ? 1 : ((j & 32785) == 0 ? 0 : -1));
            if (i7 != 0) {
                if (showSelfChassisBean != null) {
                    i5 = showSelfChassisBean.getOnDock();
                } else {
                    i5 = 0;
                }
                boolean z5 = i5 == 1;
                if (i7 != 0) {
                    j |= z5 ? PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE : PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED;
                }
                if (z5) {
                    resources = this.headOnDockStateTv.getResources();
                    i6 = R.string.txt_on_dock;
                } else {
                    resources = this.headOnDockStateTv.getResources();
                    i6 = R.string.txt_off_dock;
                }
                str10 = resources.getString(i6);
            } else {
                str10 = null;
            }
            str9 = ((j & 34817) == 0 || showSelfChassisBean == null) ? null : showSelfChassisBean.getX();
            str3 = ((j & 40961) == 0 || showSelfChassisBean == null) ? null : showSelfChassisBean.getT();
            int onDockIconRes = ((j & 49153) == 0 || showSelfChassisBean == null) ? 0 : showSelfChassisBean.getOnDockIconRes();
            int batteryIconRes = ((j & 32897) == 0 || showSelfChassisBean == null) ? 0 : showSelfChassisBean.getBatteryIconRes();
            String batteryPercent = ((j & 32801) == 0 || showSelfChassisBean == null) ? null : showSelfChassisBean.getBatteryPercent();
            String deviceState = ((j & 33793) == 0 || showSelfChassisBean == null) ? null : showSelfChassisBean.getDeviceState();
            int i8 = ((j & 32781) > 0 ? 1 : ((j & 32781) == 0 ? 0 : -1));
            if (i8 != 0) {
                if (showSelfChassisBean != null) {
                    str2 = showSelfChassisBean.getFloor();
                    str14 = showSelfChassisBean.getBuild();
                } else {
                    str2 = null;
                    str14 = null;
                }
                z3 = str2 != null;
                z = str14 != null;
                if (i8 != 0) {
                    j |= z3 ? 8388608 : 4194304;
                }
                if ((j & 32781) != 0) {
                    j = z ? j | PlaybackStateCompat.ACTION_PREPARE_FROM_URI : j | 65536;
                }
            } else {
                str2 = null;
                str14 = null;
                z3 = false;
                z = false;
            }
            String matchingPercent = ((j & 33025) == 0 || showSelfChassisBean == null) ? null : showSelfChassisBean.getMatchingPercent();
            String sportState = ((j & 33281) == 0 || showSelfChassisBean == null) ? null : showSelfChassisBean.getSportState();
            int i9 = ((j & 32833) > 0 ? 1 : ((j & 32833) == 0 ? 0 : -1));
            if (i9 != 0) {
                if (showSelfChassisBean != null) {
                    i4 = showSelfChassisBean.getBattery();
                } else {
                    i4 = 0;
                }
                boolean z6 = i4 < 20;
                if (i9 != 0) {
                    j |= z6 ? PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE_ENABLED : PlaybackStateCompat.ACTION_SET_REPEAT_MODE;
                }
                i2 = getColorFromResource(this.headBatteryTv, z6 ? R.color.clr_FF1313 : R.color.clr_333333);
            } else {
                i2 = 0;
            }
            str4 = ((j & 36865) == 0 || showSelfChassisBean == null) ? null : showSelfChassisBean.getY();
            if ((j & 32771) == 0 || showSelfChassisBean == null) {
                i = onDockIconRes;
                i3 = batteryIconRes;
                str6 = batteryPercent;
                str5 = deviceState;
                str8 = str14;
                str = matchingPercent;
                z4 = z3;
                str7 = sportState;
                z2 = false;
            } else {
                z2 = showSelfChassisBean.isExitBtnCanClick();
                i = onDockIconRes;
                i3 = batteryIconRes;
                str6 = batteryPercent;
                str5 = deviceState;
                str8 = str14;
                str = matchingPercent;
                z4 = z3;
                str7 = sportState;
            }
        } else {
            str10 = null;
            str9 = null;
            str8 = null;
            str7 = null;
            str6 = null;
            str5 = null;
            str4 = null;
            str3 = null;
            str2 = null;
            str = null;
            i3 = 0;
            i2 = 0;
            z2 = false;
            z = false;
            i = 0;
        }
        if ((j & PlaybackStateCompat.ACTION_PREPARE_FROM_URI) != 0) {
            str11 = str8 + " / ";
        } else {
            str11 = null;
        }
        int i10 = ((j & 32781) > 0 ? 1 : ((j & 32781) == 0 ? 0 : -1));
        if (i10 != 0) {
            if (!z4) {
                str2 = "";
            }
            str12 = str2;
        } else {
            str12 = null;
        }
        if (i10 != 0) {
            if (!z) {
                str11 = "";
            }
            str13 = str10;
            str15 = str11 + str12;
        } else {
            str13 = str10;
        }
        String str16 = str15;
        if ((j & 32897) != 0) {
            this.headBatteryIv.setBackgroundResource(i3);
        }
        if ((j & 32801) != 0) {
            TextViewBindingAdapter.setText(this.headBatteryTv, str6);
        }
        if ((j & 32833) != 0) {
            this.headBatteryTv.setTextColor(i2);
        }
        if (i10 != 0) {
            TextViewBindingAdapter.setText(this.headBuildingFloorTv, str16);
        }
        if ((32771 & j) != 0) {
            IconButton.setEnableBg(this.headCancelIb, z2);
        }
        if ((j & 33793) != 0) {
            TextViewBindingAdapter.setText(this.headDeviceTv, str5);
        }
        if ((j & 40961) != 0) {
            TextViewBindingAdapter.setText(this.headLocationTTv, str3);
        }
        if ((j & 34817) != 0) {
            TextViewBindingAdapter.setText(this.headLocationXTv, str9);
        }
        if ((36865 & j) != 0) {
            TextViewBindingAdapter.setText(this.headLocationYTv, str4);
        }
        if ((33281 & j) != 0) {
            TextViewBindingAdapter.setText(this.headMotionTv, str7);
        }
        if ((j & 49153) != 0) {
            this.headOnDockStateIv.setBackgroundResource(i);
        }
        if ((j & 32785) != 0) {
            TextViewBindingAdapter.setText(this.headOnDockStateTv, str13);
        }
        if ((j & 33025) != 0) {
            TextViewBindingAdapter.setText(this.headSuitabilityTv, str);
        }
    }
}
