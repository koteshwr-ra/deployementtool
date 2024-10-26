package mc.csst.com.selfchassis.databinding;

import android.support.v4.media.session.PlaybackStateCompat;
import android.util.SparseIntArray;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.Observable;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import androidx.lifecycle.LifecycleOwner;
import com.ciot.sentrymove.R;
import mc.csst.com.selfchassis.utils.bean.ShowSelfChassisBean;

public class ActivityMainBindingImpl extends ActivityMainBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;

    static {
        ViewDataBinding.IncludedLayouts includedLayouts = new ViewDataBinding.IncludedLayouts(71);
        sIncludes = includedLayouts;
        includedLayouts.setIncludes(0, new String[]{"layout_main_direction_control", "layout_main_head", "layout_nav", "layout_visual_label_calibration_mode", "layout_tracking", "layout_collect_mode"}, new int[]{5, 6, 7, 8, 9, 10}, new int[]{R.layout.layout_main_direction_control, R.layout.layout_main_head, R.layout.layout_nav, R.layout.layout_visual_label_calibration_mode, R.layout.layout_tracking, R.layout.layout_collect_mode});
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.main_map_control_mrv, 11);
        sViewsWithIds.put(R.id.main_map_area, 12);
        sViewsWithIds.put(R.id.main_area_title_tv, 13);
        sViewsWithIds.put(R.id.main_area_content_tv, 14);
        sViewsWithIds.put(R.id.main_scale, 15);
        sViewsWithIds.put(R.id.main_cl_cam_point_cloud, 16);
        sViewsWithIds.put(R.id.main_img_cam_point_cloud_bg, 17);
        sViewsWithIds.put(R.id.main_layer_view, 18);
        sViewsWithIds.put(R.id.main_bottom_layer_iv, 19);
        sViewsWithIds.put(R.id.main_bottom_restore_iv, 20);
        sViewsWithIds.put(R.id.main_bottom_btn_cl, 21);
        sViewsWithIds.put(R.id.main_bottom_nav_iv, 22);
        sViewsWithIds.put(R.id.main_zoom_cl, 23);
        sViewsWithIds.put(R.id.main_zoom_in_tv, 24);
        sViewsWithIds.put(R.id.main_zoom_out_tv, 25);
        sViewsWithIds.put(R.id.robot_list_container, 26);
        sViewsWithIds.put(R.id.cl_robot_list, 27);
        sViewsWithIds.put(R.id.iv_robot_list, 28);
        sViewsWithIds.put(R.id.tv_robot_list, 29);
        sViewsWithIds.put(R.id.ll_robot_list, 30);
        sViewsWithIds.put(R.id.rv_robot_list, 31);
        sViewsWithIds.put(R.id.main_point_info_cl, 32);
        sViewsWithIds.put(R.id.main_point_info_icon_iv, 33);
        sViewsWithIds.put(R.id.main_point_info_tv, 34);
        sViewsWithIds.put(R.id.main_point_info_fl, 35);
        sViewsWithIds.put(R.id.main_point_info_iv, 36);
        sViewsWithIds.put(R.id.main_point_list_ll, 37);
        sViewsWithIds.put(R.id.main_point_swipe_refresh, 38);
        sViewsWithIds.put(R.id.main_point_list_rv, 39);
        sViewsWithIds.put(R.id.main_build_info_cl, 40);
        sViewsWithIds.put(R.id.main_build_info_icon_iv, 41);
        sViewsWithIds.put(R.id.main_build_info_tv, 42);
        sViewsWithIds.put(R.id.main_build_info_list_rv, 43);
        sViewsWithIds.put(R.id.main_top_menu_rv, 44);
        sViewsWithIds.put(R.id.main_left_tool_rv, 45);
        sViewsWithIds.put(R.id.main_left_tool_shape_cl, 46);
        sViewsWithIds.put(R.id.main_line_iv, 47);
        sViewsWithIds.put(R.id.main_rectangle_iv, 48);
        sViewsWithIds.put(R.id.main_left_tool_brush_cl, 49);
        sViewsWithIds.put(R.id.main_brush_small_rl, 50);
        sViewsWithIds.put(R.id.main_brush_small_iv, 51);
        sViewsWithIds.put(R.id.main_brush_medium_rl, 52);
        sViewsWithIds.put(R.id.main_brush_medium_iv, 53);
        sViewsWithIds.put(R.id.main_brush_big_rl, 54);
        sViewsWithIds.put(R.id.main_brush_big_iv, 55);
        sViewsWithIds.put(R.id.main_left_clean_draw_area_iv, 56);
        sViewsWithIds.put(R.id.main_right_tool_rl, 57);
        sViewsWithIds.put(R.id.right_area_root_cl, 58);
        sViewsWithIds.put(R.id.right_area_list_title_cl, 59);
        sViewsWithIds.put(R.id.right_area_title_name_tv, 60);
        sViewsWithIds.put(R.id.right_area_arrow_iv, 61);
        sViewsWithIds.put(R.id.right_area_list_cl, 62);
        sViewsWithIds.put(R.id.right_area_list_rv, 63);
        sViewsWithIds.put(R.id.main_right_btn_lock_ib, 64);
        sViewsWithIds.put(R.id.main_right_btn_cancel_nav_ib, 65);
        sViewsWithIds.put(R.id.main_right_btn_ok_ib, 66);
        sViewsWithIds.put(R.id.main_build_floor_ll, 67);
        sViewsWithIds.put(R.id.main_build_name_rv, 68);
        sViewsWithIds.put(R.id.main_floor_rv, 69);
        sViewsWithIds.put(R.id.add_random_mark_view, 70);
    }

    public ActivityMainBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 71, sIncludes, sViewsWithIds));
    }

    private ActivityMainBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 7, objArr[70], objArr[27], objArr[10], objArr[5], objArr[6], objArr[7], objArr[9], objArr[8], objArr[28], objArr[30], objArr[14], objArr[13], objArr[21], objArr[19], objArr[2], objArr[22], objArr[20], objArr[3], objArr[55], objArr[54], objArr[53], objArr[52], objArr[51], objArr[50], objArr[67], objArr[40], objArr[41], objArr[43], objArr[42], objArr[68], objArr[16], objArr[69], objArr[17], objArr[18], objArr[56], objArr[49], objArr[45], objArr[46], objArr[47], objArr[12], objArr[11], objArr[32], objArr[35], objArr[33], objArr[36], objArr[34], objArr[37], objArr[39], objArr[38], objArr[1], objArr[48], objArr[65], objArr[64], objArr[66], objArr[57], objArr[15], objArr[4], objArr[44], objArr[23], objArr[24], objArr[25], objArr[61], objArr[62], objArr[63], objArr[59], objArr[58], objArr[60], objArr[26], objArr[31], objArr[29]);
        this.mDirtyFlags = -1;
        this.mainBottomLockIv.setTag((Object) null);
        this.mainBottomStopIv.setTag((Object) null);
        this.mainPointTypeCl.setTag((Object) null);
        this.mainTipsTv.setTag((Object) null);
        ConstraintLayout constraintLayout = objArr[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag((Object) null);
        setRootTag(view);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
        }
        this.includeDirectionControl.invalidateAll();
        this.includeHead.invalidateAll();
        this.includeNav.invalidateAll();
        this.includeVisualLabel.invalidateAll();
        this.includeTracking.invalidateAll();
        this.includeCollecting.invalidateAll();
        requestRebind();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001c, code lost:
        if (r6.includeHead.hasPendingBindings() == false) goto L_0x001f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001e, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0025, code lost:
        if (r6.includeNav.hasPendingBindings() == false) goto L_0x0028;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0027, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x002e, code lost:
        if (r6.includeVisualLabel.hasPendingBindings() == false) goto L_0x0031;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0030, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0037, code lost:
        if (r6.includeTracking.hasPendingBindings() == false) goto L_0x003a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0039, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0040, code lost:
        if (r6.includeCollecting.hasPendingBindings() == false) goto L_0x0043;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0042, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0043, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0013, code lost:
        if (r6.includeDirectionControl.hasPendingBindings() == false) goto L_0x0016;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0015, code lost:
        return true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean hasPendingBindings() {
        /*
            r6 = this;
            monitor-enter(r6)
            long r0 = r6.mDirtyFlags     // Catch:{ all -> 0x0045 }
            r2 = 0
            r4 = 1
            int r5 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r5 == 0) goto L_0x000c
            monitor-exit(r6)     // Catch:{ all -> 0x0045 }
            return r4
        L_0x000c:
            monitor-exit(r6)     // Catch:{ all -> 0x0045 }
            mc.csst.com.selfchassis.databinding.LayoutMainDirectionControlBinding r0 = r6.includeDirectionControl
            boolean r0 = r0.hasPendingBindings()
            if (r0 == 0) goto L_0x0016
            return r4
        L_0x0016:
            mc.csst.com.selfchassis.databinding.LayoutMainHeadBinding r0 = r6.includeHead
            boolean r0 = r0.hasPendingBindings()
            if (r0 == 0) goto L_0x001f
            return r4
        L_0x001f:
            mc.csst.com.selfchassis.databinding.LayoutNavBinding r0 = r6.includeNav
            boolean r0 = r0.hasPendingBindings()
            if (r0 == 0) goto L_0x0028
            return r4
        L_0x0028:
            mc.csst.com.selfchassis.databinding.LayoutVisualLabelCalibrationModeBinding r0 = r6.includeVisualLabel
            boolean r0 = r0.hasPendingBindings()
            if (r0 == 0) goto L_0x0031
            return r4
        L_0x0031:
            mc.csst.com.selfchassis.databinding.LayoutTrackingBinding r0 = r6.includeTracking
            boolean r0 = r0.hasPendingBindings()
            if (r0 == 0) goto L_0x003a
            return r4
        L_0x003a:
            mc.csst.com.selfchassis.databinding.LayoutCollectModeBinding r0 = r6.includeCollecting
            boolean r0 = r0.hasPendingBindings()
            if (r0 == 0) goto L_0x0043
            return r4
        L_0x0043:
            r0 = 0
            return r0
        L_0x0045:
            r0 = move-exception
            monitor-exit(r6)     // Catch:{ all -> 0x0045 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: mc.csst.com.selfchassis.databinding.ActivityMainBindingImpl.hasPendingBindings():boolean");
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

    public void setLifecycleOwner(LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        this.includeDirectionControl.setLifecycleOwner(lifecycleOwner);
        this.includeHead.setLifecycleOwner(lifecycleOwner);
        this.includeNav.setLifecycleOwner(lifecycleOwner);
        this.includeVisualLabel.setLifecycleOwner(lifecycleOwner);
        this.includeTracking.setLifecycleOwner(lifecycleOwner);
        this.includeCollecting.setLifecycleOwner(lifecycleOwner);
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        switch (i) {
            case 0:
                return onChangeShowChassisState((ShowSelfChassisBean) obj, i2);
            case 1:
                return onChangeIncludeNav((LayoutNavBinding) obj, i2);
            case 2:
                return onChangeIncludeDirectionControl((LayoutMainDirectionControlBinding) obj, i2);
            case 3:
                return onChangeIncludeHead((LayoutMainHeadBinding) obj, i2);
            case 4:
                return onChangeIncludeVisualLabel((LayoutVisualLabelCalibrationModeBinding) obj, i2);
            case 5:
                return onChangeIncludeCollecting((LayoutCollectModeBinding) obj, i2);
            case 6:
                return onChangeIncludeTracking((LayoutTrackingBinding) obj, i2);
            default:
                return false;
        }
    }

    private boolean onChangeShowChassisState(ShowSelfChassisBean showSelfChassisBean, int i) {
        if (i == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        } else if (i == 5) {
            synchronized (this) {
                this.mDirtyFlags |= 128;
            }
            return true;
        } else if (i == 22) {
            synchronized (this) {
                this.mDirtyFlags |= 256;
            }
            return true;
        } else if (i == 6) {
            synchronized (this) {
                this.mDirtyFlags |= 512;
            }
            return true;
        } else if (i == 7) {
            synchronized (this) {
                this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
            }
            return true;
        } else if (i != 20) {
            return false;
        } else {
            synchronized (this) {
                this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
            }
            return true;
        }
    }

    private boolean onChangeIncludeNav(LayoutNavBinding layoutNavBinding, int i) {
        if (i != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeIncludeDirectionControl(LayoutMainDirectionControlBinding layoutMainDirectionControlBinding, int i) {
        if (i != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    private boolean onChangeIncludeHead(LayoutMainHeadBinding layoutMainHeadBinding, int i) {
        if (i != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        return true;
    }

    private boolean onChangeIncludeVisualLabel(LayoutVisualLabelCalibrationModeBinding layoutVisualLabelCalibrationModeBinding, int i) {
        if (i != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        return true;
    }

    private boolean onChangeIncludeCollecting(LayoutCollectModeBinding layoutCollectModeBinding, int i) {
        if (i != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        return true;
    }

    private boolean onChangeIncludeTracking(LayoutTrackingBinding layoutTrackingBinding, int i) {
        if (i != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 64;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void executeBindings() {
        long j;
        int i;
        int i2;
        int i3;
        int i4;
        boolean z;
        boolean z2;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        String str = null;
        ShowSelfChassisBean showSelfChassisBean = this.mShowChassisState;
        if ((8065 & j) != 0) {
            int i5 = ((j & 4225) > 0 ? 1 : ((j & 4225) == 0 ? 0 : -1));
            if (i5 != 0) {
                if (showSelfChassisBean != null) {
                    z2 = showSelfChassisBean.isBottomLock();
                } else {
                    z2 = false;
                }
                if (i5 != 0) {
                    j |= z2 ? 65536 : PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID;
                }
                i3 = z2 ? R.mipmap.icon_lock : R.mipmap.icon_unlock;
            } else {
                i3 = 0;
            }
            if (!((j & 4609) == 0 || showSelfChassisBean == null)) {
                str = showSelfChassisBean.getBottomTips();
            }
            int i6 = ((j & 4353) > 0 ? 1 : ((j & 4353) == 0 ? 0 : -1));
            if (i6 != 0) {
                if (showSelfChassisBean != null) {
                    z = showSelfChassisBean.isSoftStop();
                } else {
                    z = false;
                }
                if (i6 != 0) {
                    j |= z ? 16384 : PlaybackStateCompat.ACTION_PLAY_FROM_URI;
                }
                i2 = z ? R.mipmap.icon_soft_stop_open : R.mipmap.icon_soft_stop;
            } else {
                i2 = 0;
            }
            int showBottomTips = ((j & 6145) == 0 || showSelfChassisBean == null) ? 0 : showSelfChassisBean.getShowBottomTips();
            if ((j & 5121) == 0 || showSelfChassisBean == null) {
                i = showBottomTips;
                i4 = 0;
            } else {
                i4 = showSelfChassisBean.getBottomTipsColor();
                i = showBottomTips;
            }
        } else {
            i4 = 0;
            i3 = 0;
            i2 = 0;
            i = 0;
        }
        if ((j & 4225) != 0) {
            this.mainBottomLockIv.setBackgroundResource(i3);
        }
        if ((j & 4353) != 0) {
            this.mainBottomStopIv.setBackgroundResource(i2);
        }
        if ((j & 4609) != 0) {
            TextViewBindingAdapter.setText(this.mainTipsTv, str);
        }
        if ((j & 5121) != 0) {
            this.mainTipsTv.setTextColor(i4);
        }
        if ((j & 6145) != 0) {
            this.mainTipsTv.setVisibility(i);
        }
        executeBindingsOn(this.includeDirectionControl);
        executeBindingsOn(this.includeHead);
        executeBindingsOn(this.includeNav);
        executeBindingsOn(this.includeVisualLabel);
        executeBindingsOn(this.includeTracking);
        executeBindingsOn(this.includeCollecting);
    }
}
