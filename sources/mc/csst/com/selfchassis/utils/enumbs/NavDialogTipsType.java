package mc.csst.com.selfchassis.utils.enumbs;

import com.ciot.base.util.ContextUtil;
import com.ciot.sentrymove.R;
import mc.csst.com.selfchassislibrary.content.SelfChassisStateContent;

public enum NavDialogTipsType {
    NAV_REFUSED_RECHARGING(SelfChassisStateContent.NavStatus.NAV_REFUSED_RECHARGING, ContextUtil.getContext().getString(R.string.nav_refused_recharging)),
    NAV_REFUSED_EMERGENCY_STOP(SelfChassisStateContent.NavStatus.NAV_REFUSED_EMERGENCY_STOP, ContextUtil.getContext().getString(R.string.nav_refused_emergency_stop)),
    NAV_REFUSED_MULTIPOINT_NAV_RUNNING(SelfChassisStateContent.NavStatus.NAV_REFUSED_MULTIPOINT_NAV_RUNNING, ContextUtil.getContext().getString(R.string.nav_refused_multipoint_nav_running)),
    NAV_REFUSED_ACCESS_CONTROL_NAV_RUNNING(SelfChassisStateContent.NavStatus.NAV_REFUSED_ACCESS_CONTROL_NAV_RUNNING, ContextUtil.getContext().getString(R.string.nav_refused_access_control_nav_running)),
    NAV_REFUSED_CROSS_FLOOR_NAV_RUNNING(SelfChassisStateContent.NavStatus.NAV_REFUSED_CROSS_FLOOR_NAV_RUNNING, ContextUtil.getContext().getString(R.string.nav_refused_cross_floor_nav_running)),
    NAV_REFUSED_TURNSTILE_NAV_RUNNING(SelfChassisStateContent.NavStatus.NAV_REFUSED_TURNSTILE_NAV_RUNNING, ContextUtil.getContext().getString(R.string.nav_refused_turnstile_nav_running)),
    NAV_REFUSED(SelfChassisStateContent.NavStatus.NAV_REFUSED, ContextUtil.getContext().getString(R.string.sport_state_nav_refused)),
    CROSS_FLOOR_NAV_INPUT_ERROR(SelfChassisStateContent.CrossFloorNavStatus.NAV_INPUT_ERROR, ContextUtil.getContext().getString(R.string.tips_nav_input_error)),
    CROSS_FLOOR_NAV_TARGET_FLOOR_INVALID(SelfChassisStateContent.CrossFloorNavStatus.NAV_TARGET_FLOOR_INVALID, ContextUtil.getContext().getString(R.string.tips_nav_target_floor_invalid)),
    CROSS_FLOOR_NAV_INVALID_TARGET_POINT(SelfChassisStateContent.CrossFloorNavStatus.NAV_INVALID_TARGET_POINT, ContextUtil.getContext().getString(R.string.tips_nav_target_point_invalid)),
    CROSS_FLOOR_NAV_OBTAIN_MARK_POINT_ERROR(SelfChassisStateContent.CrossFloorNavStatus.OBTAIN_MARK_POINT_ERROR, ContextUtil.getContext().getString(R.string.tips_nav_obtain_mark_point_error)),
    CROSS_FLOOR_NAV_ELEVATOR_POINTS_INVALID(SelfChassisStateContent.CrossFloorNavStatus.OBTAIN_ELEVATOR_POINTS_INVALID, ContextUtil.getContext().getString(R.string.tips_nav_obtain_elevator_points_invalid)),
    CROSS_FLOOR_NAV_SINGLE_POINT_FAIL(SelfChassisStateContent.CrossFloorNavStatus.SINGLE_POINT_FAIL, ContextUtil.getContext().getString(R.string.tips_nav_single_point_fail)),
    CROSS_FLOOR_NAV_SINGLE_POINT_CANCEL(SelfChassisStateContent.CrossFloorNavStatus.SINGLE_POINT_CANCEL, ContextUtil.getContext().getString(R.string.tips_nav_single_point_fail)),
    CROSS_FLOOR_NAV_RESERVATION_ELEVATOR_FAIL(SelfChassisStateContent.CrossFloorNavStatus.RESERVATION_ELEVATOR_FAIL, ContextUtil.getContext().getString(R.string.tips_nav_reservation_elevator_fail)),
    CROSS_FLOOR_NAV_CANCEL_REJECTION(SelfChassisStateContent.CrossFloorNavStatus.CANCEL_REJECTION, ContextUtil.getContext().getString(R.string.tips_nav_cancel_rejection)),
    CANNOT_DETECT_INFRARED_SIGNAL(902, ContextUtil.getContext().getString(R.string.recharge_fail)),
    CANNOT_FIND_CHARGING_STATION(SelfChassisStateContent.InfraredStatus.CANNOT_FIND_CHARGING_STATION, ContextUtil.getContext().getString(R.string.recharge_fail)),
    RECHARGE_TIMEOUT(SelfChassisStateContent.InfraredStatus.RECHARGE_TIMEOUT, ContextUtil.getContext().getString(R.string.recharge_fail)),
    RECHARGE_CANCEL(SelfChassisStateContent.InfraredStatus.RECHARGE_CANCEL, ContextUtil.getContext().getString(R.string.recharge_cancel)),
    WAIT_FOR_RECHARGE(SelfChassisStateContent.InfraredStatus.WAIT_FOR_RECHARGE, ContextUtil.getContext().getString(R.string.recharging)),
    FAILED_BEFORE_CHARGING_PILE(SelfChassisStateContent.InfraredStatus.FAILED_BEFORE_CHARGING_PILE, ContextUtil.getContext().getString(R.string.recharge_fail)),
    OBSTACLES_BEHIND(SelfChassisStateContent.InfraredStatus.OBSTACLES_BEHIND, ContextUtil.getContext().getString(R.string.recharge_fail)),
    NO_INFRARED_SIGNAL(SelfChassisStateContent.InfraredStatus.NO_INFRARED_SIGNAL, ContextUtil.getContext().getString(R.string.recharge_fail)),
    NO_HEARTBEAT(SelfChassisStateContent.InfraredStatus.NO_HEARTBEAT, ContextUtil.getContext().getString(R.string.recharge_fail)),
    TIMEOUT_ON_CHARGING_PILE(SelfChassisStateContent.InfraredStatus.TIMEOUT_ON_CHARGING_PILE, ContextUtil.getContext().getString(R.string.recharge_fail)),
    CANCEL_WHEN_RECHARGING_NAVIGATING_TO_CHARGING_PILE(SelfChassisStateContent.InfraredStatus.CANCEL_WHEN_RECHARGING_NAVIGATING_TO_CHARGING_PILE, ContextUtil.getContext().getString(R.string.recharge_cancel));
    
    private int code;
    private String desc;

    private NavDialogTipsType(int i, String str) {
        this.code = i;
        this.desc = str;
    }

    public static String getDesc(int i) {
        for (NavDialogTipsType navDialogTipsType : values()) {
            if (navDialogTipsType.getCode() == i) {
                return navDialogTipsType.desc;
            }
        }
        return "";
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String str) {
        this.desc = str;
    }
}
