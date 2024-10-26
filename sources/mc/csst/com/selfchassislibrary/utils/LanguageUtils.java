package mc.csst.com.selfchassislibrary.utils;

import android.text.TextUtils;
import com.ciot.base.util.ContextUtil;
import com.ciot.base.util.MultiLanguageUtil;
import java.util.ArrayList;
import java.util.List;
import mc.csst.com.selfchassislibrary.R;
import mc.csst.com.selfchassislibrary.bean.msg.SelfDiagnosisResponseBean;

public class LanguageUtils {
    public static List<SelfDiagnosisResponseBean.ValuesBean.StatusBean> selfDiagnosisChangeByLocalLanguage(List<SelfDiagnosisResponseBean.ValuesBean.StatusBean> list) {
        ArrayList arrayList = new ArrayList();
        for (SelfDiagnosisResponseBean.ValuesBean.StatusBean next : list) {
            String changeByLocalLanguage = changeByLocalLanguage(next.getHardware_id());
            String changeByLocalLanguage2 = changeByLocalLanguage(next.getMessage());
            next.setHardware_id(changeByLocalLanguage);
            next.setMessage(changeByLocalLanguage2);
            arrayList.add(next);
        }
        return arrayList;
    }

    private static String changeByLocalLanguage(String str) {
        if (TextUtils.equals(MultiLanguageUtil.getCNStrByResId(R.string.sc_txt_lidar), str)) {
            return ContextUtil.getContext().getString(R.string.sc_txt_lidar);
        }
        if (TextUtils.equals(MultiLanguageUtil.getCNStrByResId(R.string.sc_txt_internet_connection), str)) {
            return ContextUtil.getContext().getString(R.string.sc_txt_internet_connection);
        }
        if (TextUtils.equals(MultiLanguageUtil.getCNStrByResId(R.string.sc_txt_left_motor), str)) {
            return ContextUtil.getContext().getString(R.string.sc_txt_left_motor);
        }
        if (TextUtils.equals(MultiLanguageUtil.getCNStrByResId(R.string.sc_txt_right_motor), str)) {
            return ContextUtil.getContext().getString(R.string.sc_txt_right_motor);
        }
        if (TextUtils.equals(MultiLanguageUtil.getCNStrByResId(R.string.sc_txt_ultrasound), str)) {
            return ContextUtil.getContext().getString(R.string.sc_txt_ultrasound);
        }
        if (TextUtils.equals(MultiLanguageUtil.getCNStrByResId(R.string.sc_txt_gyro), str)) {
            return ContextUtil.getContext().getString(R.string.sc_txt_gyro);
        }
        if (TextUtils.equals(MultiLanguageUtil.getCNStrByResId(R.string.sc_txt_battery), str)) {
            return ContextUtil.getContext().getString(R.string.sc_txt_battery);
        }
        if (TextUtils.equals(MultiLanguageUtil.getCNStrByResId(R.string.sc_txt_system_current), str)) {
            return ContextUtil.getContext().getString(R.string.sc_txt_system_current);
        }
        if (TextUtils.equals(MultiLanguageUtil.getCNStrByResId(R.string.sc_txt_peripherals), str)) {
            return ContextUtil.getContext().getString(R.string.sc_txt_peripherals);
        }
        if (TextUtils.equals(MultiLanguageUtil.getCNStrByResId(R.string.sc_txt_other_system_error), str)) {
            return ContextUtil.getContext().getString(R.string.sc_txt_other_system_error);
        }
        if (TextUtils.equals(MultiLanguageUtil.getCNStrByResId(R.string.sc_txt_deep_camera), str)) {
            return ContextUtil.getContext().getString(R.string.sc_txt_deep_camera);
        }
        if (TextUtils.equals(MultiLanguageUtil.getCNStrByResId(R.string.sc_txt_downward_depth_camera), str)) {
            return ContextUtil.getContext().getString(R.string.sc_txt_downward_depth_camera);
        }
        if (TextUtils.equals(MultiLanguageUtil.getCNStrByResId(R.string.sc_txt_upward_depth_camera), str)) {
            return ContextUtil.getContext().getString(R.string.sc_txt_upward_depth_camera);
        }
        if (TextUtils.equals(MultiLanguageUtil.getCNStrByResId(R.string.sc_txt_tagged_camera), str)) {
            return ContextUtil.getContext().getString(R.string.sc_txt_tagged_camera);
        }
        if (TextUtils.equals(MultiLanguageUtil.getCNStrByResId(R.string.sc_txt_lidar_data_more_than_90_invalid), str)) {
            return ContextUtil.getContext().getString(R.string.sc_txt_lidar_data_more_than_90_invalid);
        }
        if (TextUtils.equals(MultiLanguageUtil.getCNStrByResId(R.string.sc_txt_lidar_not_connected), str)) {
            return ContextUtil.getContext().getString(R.string.sc_txt_lidar_not_connected);
        }
        if (TextUtils.equals(MultiLanguageUtil.getCNStrByResId(R.string.sc_txt_lidar_data_timeout), str)) {
            return ContextUtil.getContext().getString(R.string.sc_txt_lidar_data_timeout);
        }
        if (TextUtils.equals(MultiLanguageUtil.getCNStrByResId(R.string.sc_txt_network_not_connected), str)) {
            return ContextUtil.getContext().getString(R.string.sc_txt_network_not_connected);
        }
        if (TextUtils.equals(MultiLanguageUtil.getCNStrByResId(R.string.sc_txt_depth_camera_not_connected), str)) {
            return ContextUtil.getContext().getString(R.string.sc_txt_depth_camera_not_connected);
        }
        if (TextUtils.equals(MultiLanguageUtil.getCNStrByResId(R.string.sc_txt_depth_camera_program_is_not_configured_to_star), str)) {
            return ContextUtil.getContext().getString(R.string.sc_txt_depth_camera_program_is_not_configured_to_star);
        }
        if (TextUtils.equals(MultiLanguageUtil.getCNStrByResId(R.string.sc_txt_downward_depth_camera_not_connected), str)) {
            return ContextUtil.getContext().getString(R.string.sc_txt_downward_depth_camera_not_connected);
        }
        if (TextUtils.equals(MultiLanguageUtil.getCNStrByResId(R.string.sc_txt_downward_depth_camera_program_no_config_start), str)) {
            return ContextUtil.getContext().getString(R.string.sc_txt_downward_depth_camera_program_no_config_start);
        }
        if (TextUtils.equals(MultiLanguageUtil.getCNStrByResId(R.string.sc_txt_upward_depth_camera_not_connected), str)) {
            return ContextUtil.getContext().getString(R.string.sc_txt_upward_depth_camera_not_connected);
        }
        if (TextUtils.equals(MultiLanguageUtil.getCNStrByResId(R.string.sc_txt_upward_depth_camera_not_config_start), str)) {
            return ContextUtil.getContext().getString(R.string.sc_txt_upward_depth_camera_not_config_start);
        }
        if (TextUtils.equals(MultiLanguageUtil.getCNStrByResId(R.string.sc_txt_tagged_camera_not_connected), str)) {
            return ContextUtil.getContext().getString(R.string.sc_txt_tagged_camera_not_connected);
        }
        return TextUtils.equals(MultiLanguageUtil.getCNStrByResId(R.string.sc_txt_tagged_camera_not_config_start), str) ? ContextUtil.getContext().getString(R.string.sc_txt_tagged_camera_not_config_start) : str;
    }
}
