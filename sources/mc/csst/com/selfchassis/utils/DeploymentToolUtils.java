package mc.csst.com.selfchassis.utils;

import com.blankj.utilcode.util.StringUtils;
import com.ciot.sentrymove.R;
import mc.csst.com.selfchassis.App;
import mc.csst.com.selfchassislibrary.bean.msg.MarkerOperationGetMarkersResponseBean;
import mc.csst.com.selfchassislibrary.chassis.SelfChassis;
import mc.csst.com.selfchassislibrary.content.VersionUpgradeContent;

public class DeploymentToolUtils {

    private static class Holder {
        /* access modifiers changed from: private */
        public static final DeploymentToolUtils INSTANCE = new DeploymentToolUtils();

        private Holder() {
        }
    }

    public static DeploymentToolUtils getInstance() {
        return Holder.INSTANCE;
    }

    public String getUpgradePrompt(int i) {
        if (i == 10) {
            return StringUtils.getString(R.string.order_processing);
        }
        if (i == 99) {
            return StringUtils.getString(R.string.txt_not_support_upgrades_prior_to_Litze_4_6_0);
        }
        if (i == 717) {
            return StringUtils.getString(R.string.firmware_invalid);
        }
        if (i == 788) {
            return StringUtils.getString(R.string.pack_unpack_failed);
        }
        if (i == 751) {
            return StringUtils.getString(R.string.usb_success);
        }
        if (i == 752) {
            return StringUtils.getString(R.string.network_success);
        }
        if (i == 762) {
            return StringUtils.getString(R.string.update_time_failed_network);
        }
        if (i == 763) {
            return StringUtils.getString(R.string.update_time_failed);
        }
        switch (i) {
            case 701:
                return StringUtils.getString(R.string.usb_not_found);
            case 702:
                return StringUtils.getString(R.string.usb_not_update);
            case 703:
                return StringUtils.getString(R.string.usb_update_invalid);
            case 704:
                return StringUtils.getString(R.string.usb_update_bad);
            default:
                switch (i) {
                    case VersionUpgradeContent.VersionResultContent.NO_NETWORK /*711*/:
                        return StringUtils.getString(R.string.no_network);
                    case VersionUpgradeContent.VersionResultContent.NETWORK_FILE_FAILED /*712*/:
                        return StringUtils.getString(R.string.network_file_failed);
                    case VersionUpgradeContent.VersionResultContent.NETWORK_DOWNLOAD_FAILED /*713*/:
                        return StringUtils.getString(R.string.network_download_failed);
                    case VersionUpgradeContent.VersionResultContent.NETWORK_FILE_READ_FAILED /*714*/:
                        return StringUtils.getString(R.string.network_file_read_failed);
                    case VersionUpgradeContent.VersionResultContent.FILE_CHECK_FAILED /*715*/:
                        return StringUtils.getString(R.string.file_check_failed);
                    default:
                        return StringUtils.getString(R.string.unknown_error) + i;
                }
        }
    }

    public String getManagerControlPrompt(int i) {
        String str;
        if (i != 99) {
            switch (i) {
                case 106:
                    str = StringUtils.getString(R.string.txt_system_error_please_restart);
                    break;
                case 107:
                    str = StringUtils.getString(R.string.txt_missing_map_info);
                    break;
                case 108:
                    str = StringUtils.getString(R.string.txt_map_is_damaged);
                    break;
                case 109:
                    str = StringUtils.getString(R.string.txt_scanning_state_not_switch_maps);
                    break;
                case 110:
                    str = StringUtils.getString(R.string.txt_failed_to_save_map);
                    break;
                case 111:
                    str = StringUtils.getString(R.string.txt_map_version_is_too_old);
                    break;
                default:
                    str = StringUtils.getString(R.string.unknown_error);
                    break;
            }
        } else {
            str = StringUtils.getString(R.string.txt_tip_continue_scan_no_support);
        }
        return str + "(" + i + ")";
    }

    public void navigationToPoint(MarkerOperationGetMarkersResponseBean.ValuesBean.MarkersBean.WaypointsBean waypointsBean) {
        int behavior_code = waypointsBean.getBehavior_code();
        String name = waypointsBean.getName();
        App.setNavType(0);
        SoftTypeInfoManager.getInstance().setSoftType(6);
        if (behavior_code == 20) {
            SelfChassis.getInstance().serviceTagPoi(name);
        } else {
            SelfChassis.getInstance().sendMoveByMarkerName(name);
        }
    }
}
