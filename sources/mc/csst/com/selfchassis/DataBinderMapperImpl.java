package mc.csst.com.selfchassis;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.ciot.sentrymove.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import mc.csst.com.selfchassis.databinding.ActivityMainBindingImpl;
import mc.csst.com.selfchassis.databinding.DialogAddMarkBindingImpl;
import mc.csst.com.selfchassis.databinding.DialogAddMarkRightBindingImpl;
import mc.csst.com.selfchassis.databinding.DialogBottomBindingImpl;
import mc.csst.com.selfchassis.databinding.DialogBuildMapBindingImpl;
import mc.csst.com.selfchassis.databinding.DialogChooseMarkerBindingImpl;
import mc.csst.com.selfchassis.databinding.DialogConnectedBindingImpl;
import mc.csst.com.selfchassis.databinding.DialogContinueScanConfirmBindingImpl;
import mc.csst.com.selfchassis.databinding.DialogDisconnectedBindingImpl;
import mc.csst.com.selfchassis.databinding.DialogEditBindingImpl;
import mc.csst.com.selfchassis.databinding.DialogElevatorBindingImpl;
import mc.csst.com.selfchassis.databinding.DialogLoading2BindingImpl;
import mc.csst.com.selfchassis.databinding.DialogLoadingBindingImpl;
import mc.csst.com.selfchassis.databinding.DialogSelfCheckBindingImpl;
import mc.csst.com.selfchassis.databinding.DialogSwitchingPointBindingImpl;
import mc.csst.com.selfchassis.databinding.FragmentMapBindingImpl;
import mc.csst.com.selfchassis.databinding.FragmentScheduleBindingImpl;
import mc.csst.com.selfchassis.databinding.FragmentSetConfigBindingImpl;
import mc.csst.com.selfchassis.databinding.FragmentSetDiagnoseBindingImpl;
import mc.csst.com.selfchassis.databinding.FragmentSetLanguageBindingImpl;
import mc.csst.com.selfchassis.databinding.FragmentSetMapUploadBindingImpl;
import mc.csst.com.selfchassis.databinding.FragmentSetOnlineUpdateConfigBindingImpl;
import mc.csst.com.selfchassis.databinding.FragmentSetVersionBindingImpl;
import mc.csst.com.selfchassis.databinding.FragmentSetVersionControlBindingImpl;
import mc.csst.com.selfchassis.databinding.LayoutCollectModeBindingImpl;
import mc.csst.com.selfchassis.databinding.LayoutMainDirectionControlBindingImpl;
import mc.csst.com.selfchassis.databinding.LayoutMainHeadBindingImpl;
import mc.csst.com.selfchassis.databinding.LayoutNavBindingImpl;
import mc.csst.com.selfchassis.databinding.LayoutTrackingBindingImpl;
import mc.csst.com.selfchassis.databinding.LayoutVisualLabelCalibrationModeBindingImpl;
import mc.csst.com.selfchassis.databinding.ViewLayerSelectBindingImpl;
import mc.csst.com.selfchassis.databinding.ViewNavParamConfigBindingImpl;
import mc.csst.com.selfchassis.databinding.ViewOtherConfigBindingImpl;
import mc.csst.com.selfchassis.ui.fragment.set.schedule.ScheduleFragment;

public class DataBinderMapperImpl extends DataBinderMapper {
    private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP;
    private static final int LAYOUT_ACTIVITYMAIN = 1;
    private static final int LAYOUT_DIALOGADDMARK = 2;
    private static final int LAYOUT_DIALOGADDMARKRIGHT = 3;
    private static final int LAYOUT_DIALOGBOTTOM = 4;
    private static final int LAYOUT_DIALOGBUILDMAP = 5;
    private static final int LAYOUT_DIALOGCHOOSEMARKER = 6;
    private static final int LAYOUT_DIALOGCONNECTED = 7;
    private static final int LAYOUT_DIALOGCONTINUESCANCONFIRM = 8;
    private static final int LAYOUT_DIALOGDISCONNECTED = 9;
    private static final int LAYOUT_DIALOGEDIT = 10;
    private static final int LAYOUT_DIALOGELEVATOR = 11;
    private static final int LAYOUT_DIALOGLOADING = 12;
    private static final int LAYOUT_DIALOGLOADING2 = 13;
    private static final int LAYOUT_DIALOGSELFCHECK = 14;
    private static final int LAYOUT_DIALOGSWITCHINGPOINT = 15;
    private static final int LAYOUT_FRAGMENTMAP = 16;
    private static final int LAYOUT_FRAGMENTSCHEDULE = 17;
    private static final int LAYOUT_FRAGMENTSETCONFIG = 18;
    private static final int LAYOUT_FRAGMENTSETDIAGNOSE = 19;
    private static final int LAYOUT_FRAGMENTSETLANGUAGE = 20;
    private static final int LAYOUT_FRAGMENTSETMAPUPLOAD = 21;
    private static final int LAYOUT_FRAGMENTSETONLINEUPDATECONFIG = 22;
    private static final int LAYOUT_FRAGMENTSETVERSION = 23;
    private static final int LAYOUT_FRAGMENTSETVERSIONCONTROL = 24;
    private static final int LAYOUT_LAYOUTCOLLECTMODE = 25;
    private static final int LAYOUT_LAYOUTMAINDIRECTIONCONTROL = 26;
    private static final int LAYOUT_LAYOUTMAINHEAD = 27;
    private static final int LAYOUT_LAYOUTNAV = 28;
    private static final int LAYOUT_LAYOUTTRACKING = 29;
    private static final int LAYOUT_LAYOUTVISUALLABELCALIBRATIONMODE = 30;
    private static final int LAYOUT_VIEWLAYERSELECT = 31;
    private static final int LAYOUT_VIEWNAVPARAMCONFIG = 32;
    private static final int LAYOUT_VIEWOTHERCONFIG = 33;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray(33);
        INTERNAL_LAYOUT_ID_LOOKUP = sparseIntArray;
        sparseIntArray.put(R.layout.activity_main, 1);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.dialog_add_mark, 2);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.dialog_add_mark_right, 3);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.dialog_bottom, 4);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.dialog_build_map, 5);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.dialog_choose_marker, 6);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.dialog_connected, 7);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.dialog_continue_scan_confirm, 8);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.dialog_disconnected, 9);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.dialog_edit, 10);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.dialog_elevator, 11);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.dialog_loading, 12);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.dialog_loading2, 13);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.dialog_self_check, 14);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.dialog_switching_point, 15);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.fragment_map, 16);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.fragment_schedule, 17);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.fragment_set_config, 18);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.fragment_set_diagnose, 19);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.fragment_set_language, 20);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.fragment_set_map_upload, 21);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.fragment_set_online_update_config, 22);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.fragment_set_version, 23);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.fragment_set_version_control, 24);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.layout_collect_mode, 25);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.layout_main_direction_control, 26);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.layout_main_head, 27);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.layout_nav, 28);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.layout_tracking, 29);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.layout_visual_label_calibration_mode, 30);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.view_layer_select, 31);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.view_nav_param_config, 32);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.view_other_config, 33);
    }

    public ViewDataBinding getDataBinder(DataBindingComponent dataBindingComponent, View view, int i) {
        int i2 = INTERNAL_LAYOUT_ID_LOOKUP.get(i);
        if (i2 <= 0) {
            return null;
        }
        Object tag = view.getTag();
        if (tag != null) {
            switch (i2) {
                case 1:
                    if ("layout/activity_main_0".equals(tag)) {
                        return new ActivityMainBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for activity_main is invalid. Received: " + tag);
                case 2:
                    if ("layout/dialog_add_mark_0".equals(tag)) {
                        return new DialogAddMarkBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for dialog_add_mark is invalid. Received: " + tag);
                case 3:
                    if ("layout/dialog_add_mark_right_0".equals(tag)) {
                        return new DialogAddMarkRightBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for dialog_add_mark_right is invalid. Received: " + tag);
                case 4:
                    if ("layout/dialog_bottom_0".equals(tag)) {
                        return new DialogBottomBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for dialog_bottom is invalid. Received: " + tag);
                case 5:
                    if ("layout/dialog_build_map_0".equals(tag)) {
                        return new DialogBuildMapBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for dialog_build_map is invalid. Received: " + tag);
                case 6:
                    if ("layout/dialog_choose_marker_0".equals(tag)) {
                        return new DialogChooseMarkerBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for dialog_choose_marker is invalid. Received: " + tag);
                case 7:
                    if ("layout/dialog_connected_0".equals(tag)) {
                        return new DialogConnectedBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for dialog_connected is invalid. Received: " + tag);
                case 8:
                    if ("layout/dialog_continue_scan_confirm_0".equals(tag)) {
                        return new DialogContinueScanConfirmBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for dialog_continue_scan_confirm is invalid. Received: " + tag);
                case 9:
                    if ("layout/dialog_disconnected_0".equals(tag)) {
                        return new DialogDisconnectedBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for dialog_disconnected is invalid. Received: " + tag);
                case 10:
                    if ("layout/dialog_edit_0".equals(tag)) {
                        return new DialogEditBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for dialog_edit is invalid. Received: " + tag);
                case 11:
                    if ("layout/dialog_elevator_0".equals(tag)) {
                        return new DialogElevatorBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for dialog_elevator is invalid. Received: " + tag);
                case 12:
                    if ("layout/dialog_loading_0".equals(tag)) {
                        return new DialogLoadingBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for dialog_loading is invalid. Received: " + tag);
                case 13:
                    if ("layout/dialog_loading2_0".equals(tag)) {
                        return new DialogLoading2BindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for dialog_loading2 is invalid. Received: " + tag);
                case 14:
                    if ("layout/dialog_self_check_0".equals(tag)) {
                        return new DialogSelfCheckBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for dialog_self_check is invalid. Received: " + tag);
                case 15:
                    if ("layout/dialog_switching_point_0".equals(tag)) {
                        return new DialogSwitchingPointBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for dialog_switching_point is invalid. Received: " + tag);
                case 16:
                    if ("layout/fragment_map_0".equals(tag)) {
                        return new FragmentMapBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for fragment_map is invalid. Received: " + tag);
                case 17:
                    if ("layout/fragment_schedule_0".equals(tag)) {
                        return new FragmentScheduleBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for fragment_schedule is invalid. Received: " + tag);
                case 18:
                    if ("layout/fragment_set_config_0".equals(tag)) {
                        return new FragmentSetConfigBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for fragment_set_config is invalid. Received: " + tag);
                case 19:
                    if ("layout/fragment_set_diagnose_0".equals(tag)) {
                        return new FragmentSetDiagnoseBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for fragment_set_diagnose is invalid. Received: " + tag);
                case 20:
                    if ("layout/fragment_set_language_0".equals(tag)) {
                        return new FragmentSetLanguageBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for fragment_set_language is invalid. Received: " + tag);
                case 21:
                    if ("layout/fragment_set_map_upload_0".equals(tag)) {
                        return new FragmentSetMapUploadBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for fragment_set_map_upload is invalid. Received: " + tag);
                case 22:
                    if ("layout/fragment_set_online_update_config_0".equals(tag)) {
                        return new FragmentSetOnlineUpdateConfigBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for fragment_set_online_update_config is invalid. Received: " + tag);
                case 23:
                    if ("layout/fragment_set_version_0".equals(tag)) {
                        return new FragmentSetVersionBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for fragment_set_version is invalid. Received: " + tag);
                case 24:
                    if ("layout/fragment_set_version_control_0".equals(tag)) {
                        return new FragmentSetVersionControlBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for fragment_set_version_control is invalid. Received: " + tag);
                case 25:
                    if ("layout/layout_collect_mode_0".equals(tag)) {
                        return new LayoutCollectModeBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for layout_collect_mode is invalid. Received: " + tag);
                case 26:
                    if ("layout/layout_main_direction_control_0".equals(tag)) {
                        return new LayoutMainDirectionControlBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for layout_main_direction_control is invalid. Received: " + tag);
                case 27:
                    if ("layout/layout_main_head_0".equals(tag)) {
                        return new LayoutMainHeadBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for layout_main_head is invalid. Received: " + tag);
                case 28:
                    if ("layout/layout_nav_0".equals(tag)) {
                        return new LayoutNavBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for layout_nav is invalid. Received: " + tag);
                case 29:
                    if ("layout/layout_tracking_0".equals(tag)) {
                        return new LayoutTrackingBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for layout_tracking is invalid. Received: " + tag);
                case 30:
                    if ("layout/layout_visual_label_calibration_mode_0".equals(tag)) {
                        return new LayoutVisualLabelCalibrationModeBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for layout_visual_label_calibration_mode is invalid. Received: " + tag);
                case 31:
                    if ("layout/view_layer_select_0".equals(tag)) {
                        return new ViewLayerSelectBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for view_layer_select is invalid. Received: " + tag);
                case 32:
                    if ("layout/view_nav_param_config_0".equals(tag)) {
                        return new ViewNavParamConfigBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for view_nav_param_config is invalid. Received: " + tag);
                case 33:
                    if ("layout/view_other_config_0".equals(tag)) {
                        return new ViewOtherConfigBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for view_other_config is invalid. Received: " + tag);
                default:
                    return null;
            }
        } else {
            throw new RuntimeException("view must have a tag");
        }
    }

    public ViewDataBinding getDataBinder(DataBindingComponent dataBindingComponent, View[] viewArr, int i) {
        if (viewArr == null || viewArr.length == 0 || INTERNAL_LAYOUT_ID_LOOKUP.get(i) <= 0 || viewArr[0].getTag() != null) {
            return null;
        }
        throw new RuntimeException("view must have a tag");
    }

    public int getLayoutId(String str) {
        Integer num;
        if (str == null || (num = InnerLayoutIdLookup.sKeys.get(str)) == null) {
            return 0;
        }
        return num.intValue();
    }

    public String convertBrIdToString(int i) {
        return InnerBrLookup.sKeys.get(i);
    }

    public List<DataBinderMapper> collectDependencies() {
        ArrayList arrayList = new ArrayList(4);
        arrayList.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
        arrayList.add(new com.ciot.navigation.DataBinderMapperImpl());
        arrayList.add(new com.ciot.networklib.DataBinderMapperImpl());
        arrayList.add(new com.drz.base.DataBinderMapperImpl());
        return arrayList;
    }

    private static class InnerBrLookup {
        static final SparseArray<String> sKeys;

        private InnerBrLookup() {
        }

        static {
            SparseArray<String> sparseArray = new SparseArray<>(28);
            sKeys = sparseArray;
            sparseArray.put(0, "_all");
            sKeys.put(1, "algorithmVersion");
            sKeys.put(2, "battery");
            sKeys.put(3, "batteryIconRes");
            sKeys.put(4, "batteryPercent");
            sKeys.put(5, "bottomLock");
            sKeys.put(6, "bottomTips");
            sKeys.put(7, "bottomTipsColor");
            sKeys.put(8, "build");
            sKeys.put(9, "chassisDriveFirmwareVersion");
            sKeys.put(10, "chassisHardwareVersion");
            sKeys.put(11, "curTime");
            sKeys.put(12, "deviceState");
            sKeys.put(13, "exitBtnCanClick");
            sKeys.put(14, ScheduleFragment.FLOOR);
            sKeys.put(15, "matching");
            sKeys.put(16, "matchingPercent");
            sKeys.put(17, "onDock");
            sKeys.put(18, "onDockIconRes");
            sKeys.put(19, "robotChassisNumber");
            sKeys.put(20, "showBottomTips");
            sKeys.put(21, "showChassisState");
            sKeys.put(22, "softStop");
            sKeys.put(23, "sportState");
            sKeys.put(24, "t");
            sKeys.put(25, "version");
            sKeys.put(26, "x");
            sKeys.put(27, "y");
        }
    }

    private static class InnerLayoutIdLookup {
        static final HashMap<String, Integer> sKeys;

        private InnerLayoutIdLookup() {
        }

        static {
            HashMap<String, Integer> hashMap = new HashMap<>(33);
            sKeys = hashMap;
            hashMap.put("layout/activity_main_0", Integer.valueOf(R.layout.activity_main));
            sKeys.put("layout/dialog_add_mark_0", Integer.valueOf(R.layout.dialog_add_mark));
            sKeys.put("layout/dialog_add_mark_right_0", Integer.valueOf(R.layout.dialog_add_mark_right));
            sKeys.put("layout/dialog_bottom_0", Integer.valueOf(R.layout.dialog_bottom));
            sKeys.put("layout/dialog_build_map_0", Integer.valueOf(R.layout.dialog_build_map));
            sKeys.put("layout/dialog_choose_marker_0", Integer.valueOf(R.layout.dialog_choose_marker));
            sKeys.put("layout/dialog_connected_0", Integer.valueOf(R.layout.dialog_connected));
            sKeys.put("layout/dialog_continue_scan_confirm_0", Integer.valueOf(R.layout.dialog_continue_scan_confirm));
            sKeys.put("layout/dialog_disconnected_0", Integer.valueOf(R.layout.dialog_disconnected));
            sKeys.put("layout/dialog_edit_0", Integer.valueOf(R.layout.dialog_edit));
            sKeys.put("layout/dialog_elevator_0", Integer.valueOf(R.layout.dialog_elevator));
            sKeys.put("layout/dialog_loading_0", Integer.valueOf(R.layout.dialog_loading));
            sKeys.put("layout/dialog_loading2_0", Integer.valueOf(R.layout.dialog_loading2));
            sKeys.put("layout/dialog_self_check_0", Integer.valueOf(R.layout.dialog_self_check));
            sKeys.put("layout/dialog_switching_point_0", Integer.valueOf(R.layout.dialog_switching_point));
            sKeys.put("layout/fragment_map_0", Integer.valueOf(R.layout.fragment_map));
            sKeys.put("layout/fragment_schedule_0", Integer.valueOf(R.layout.fragment_schedule));
            sKeys.put("layout/fragment_set_config_0", Integer.valueOf(R.layout.fragment_set_config));
            sKeys.put("layout/fragment_set_diagnose_0", Integer.valueOf(R.layout.fragment_set_diagnose));
            sKeys.put("layout/fragment_set_language_0", Integer.valueOf(R.layout.fragment_set_language));
            sKeys.put("layout/fragment_set_map_upload_0", Integer.valueOf(R.layout.fragment_set_map_upload));
            sKeys.put("layout/fragment_set_online_update_config_0", Integer.valueOf(R.layout.fragment_set_online_update_config));
            sKeys.put("layout/fragment_set_version_0", Integer.valueOf(R.layout.fragment_set_version));
            sKeys.put("layout/fragment_set_version_control_0", Integer.valueOf(R.layout.fragment_set_version_control));
            sKeys.put("layout/layout_collect_mode_0", Integer.valueOf(R.layout.layout_collect_mode));
            sKeys.put("layout/layout_main_direction_control_0", Integer.valueOf(R.layout.layout_main_direction_control));
            sKeys.put("layout/layout_main_head_0", Integer.valueOf(R.layout.layout_main_head));
            sKeys.put("layout/layout_nav_0", Integer.valueOf(R.layout.layout_nav));
            sKeys.put("layout/layout_tracking_0", Integer.valueOf(R.layout.layout_tracking));
            sKeys.put("layout/layout_visual_label_calibration_mode_0", Integer.valueOf(R.layout.layout_visual_label_calibration_mode));
            sKeys.put("layout/view_layer_select_0", Integer.valueOf(R.layout.view_layer_select));
            sKeys.put("layout/view_nav_param_config_0", Integer.valueOf(R.layout.view_nav_param_config));
            sKeys.put("layout/view_other_config_0", Integer.valueOf(R.layout.view_other_config));
        }
    }
}
