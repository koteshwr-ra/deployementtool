package mc.csst.com.selfchassis.utils.enumbs;

import com.ciot.base.util.ContextUtil;
import com.ciot.sentrymove.R;
import mc.csst.com.selfchassislibrary.content.SelfChassisStateContent;

public enum NavStateType {
    INIT(SelfChassisStateContent.NavStatus.INIT, ContextUtil.getContext().getString(R.string.sport_state_waiting_nav)),
    NAVIGATING(SelfChassisStateContent.NavStatus.NAVIGATING, ContextUtil.getContext().getString(R.string.sport_state_naving)),
    CANCEL_NAV(SelfChassisStateContent.NavStatus.CANCEL_NAV, ContextUtil.getContext().getString(R.string.sport_state_cancel_nav)),
    NAV_SUCCESS(SelfChassisStateContent.NavStatus.NAV_SUCCESS, ContextUtil.getContext().getString(R.string.sport_state_nav_success)),
    NAV_FAIL(SelfChassisStateContent.NavStatus.NAV_FAIL, ContextUtil.getContext().getString(R.string.sport_state_nav_fail)),
    NAV_REFUSED(SelfChassisStateContent.NavStatus.NAV_REFUSED, ContextUtil.getContext().getString(R.string.sport_state_nav_refused)),
    NAV_CANCELING(SelfChassisStateContent.NavStatus.NAV_CANCELING, ContextUtil.getContext().getString(R.string.sport_state_nav_canceling)),
    NAV_RESETTING(SelfChassisStateContent.NavStatus.NAV_RESETTING, ContextUtil.getContext().getString(R.string.sport_state_nav_resetting)),
    NAV_RESET_COMPLETE(SelfChassisStateContent.NavStatus.NAV_RESET_COMPLETE, ContextUtil.getContext().getString(R.string.sport_state_nav_reset_complete)),
    NAV_STATUS_LOST(SelfChassisStateContent.NavStatus.NAV_STATUS_LOST, ContextUtil.getContext().getString(R.string.sport_state_nav_status_lost)),
    NAV_REFUSED_RECHARGING(SelfChassisStateContent.NavStatus.NAV_REFUSED_RECHARGING, ContextUtil.getContext().getString(R.string.sport_state_nav_refused)),
    NAV_REFUSED_EMERGENCY_STOP(SelfChassisStateContent.NavStatus.NAV_REFUSED_EMERGENCY_STOP, ContextUtil.getContext().getString(R.string.sport_state_nav_refused)),
    NAV_REFUSED_MULTIPOINT_NAV_RUNNING(SelfChassisStateContent.NavStatus.NAV_REFUSED_MULTIPOINT_NAV_RUNNING, ContextUtil.getContext().getString(R.string.sport_state_nav_refused)),
    NAV_REFUSED_ACCESS_CONTROL_NAV_RUNNING(SelfChassisStateContent.NavStatus.NAV_REFUSED_ACCESS_CONTROL_NAV_RUNNING, ContextUtil.getContext().getString(R.string.sport_state_nav_refused)),
    NAV_REFUSED_CROSS_FLOOR_NAV_RUNNING(SelfChassisStateContent.NavStatus.NAV_REFUSED_CROSS_FLOOR_NAV_RUNNING, ContextUtil.getContext().getString(R.string.sport_state_nav_refused)),
    NAV_REFUSED_TURNSTILE_NAV_RUNNING(SelfChassisStateContent.NavStatus.NAV_REFUSED_TURNSTILE_NAV_RUNNING, ContextUtil.getContext().getString(R.string.sport_state_nav_refused)),
    NAV_CONFIG_FAIL(SelfChassisStateContent.NavStatus.NAV_CONFIG_FAIL, ContextUtil.getContext().getString(R.string.sport_state_nav_config_fail)),
    NAV_EMERGENCY_STOP(SelfChassisStateContent.NavStatus.NAV_EMERGENCY_STOP, ContextUtil.getContext().getString(R.string.sport_state_nav_emergency_stop)),
    CROSS_FLOOR_NAV_DETECTION_FEASIBILITY(SelfChassisStateContent.CrossFloorNavStatus.NAV_DETECTION_FEASIBILITY, ContextUtil.getContext().getString(R.string.sport_state_naving)),
    NAV_OUTSIDE_ELEVATOR(SelfChassisStateContent.CrossFloorNavStatus.NAV_OUTSIDE_ELEVATOR, ContextUtil.getContext().getString(R.string.sport_state_naving)),
    OPTIMIZE_ROBOT_POS(SelfChassisStateContent.CrossFloorNavStatus.OPTIMIZE_ROBOT_POS, ContextUtil.getContext().getString(R.string.sport_state_naving)),
    ELEVATOR_TO_CUR_FLOOR(SelfChassisStateContent.CrossFloorNavStatus.CALL_THE_ELEVATOR, ContextUtil.getContext().getString(R.string.sport_state_naving)),
    ENTER_ELEVATOR(SelfChassisStateContent.CrossFloorNavStatus.ENTER_ELEVATOR, ContextUtil.getContext().getString(R.string.sport_state_naving)),
    SWITCHING_MAP(SelfChassisStateContent.CrossFloorNavStatus.SWITCHING_MAP, ContextUtil.getContext().getString(R.string.sport_state_naving)),
    ROTATE_TO_FACE_ELEVATOR_DOOR(SelfChassisStateContent.CrossFloorNavStatus.ROTATE_TO_FACE_ELEVATOR_DOOR, ContextUtil.getContext().getString(R.string.sport_state_naving)),
    WAIT_ELEVATOR_TO_DESIGNATED_FLOOR(SelfChassisStateContent.CrossFloorNavStatus.WAIT_ELEVATOR_TO_REACH_DESIGNATED_FLOOR, ContextUtil.getContext().getString(R.string.sport_state_naving)),
    LEAVING_ELEVATOR(SelfChassisStateContent.CrossFloorNavStatus.LEAVING_ELEVATOR, ContextUtil.getContext().getString(R.string.sport_state_naving)),
    CROSS_FLOOR_NAV_TO_TARGET_POINT(SelfChassisStateContent.CrossFloorNavStatus.NAV_TO_TARGET_POINT_ON_SPECIFIED_FLOOR, ContextUtil.getContext().getString(R.string.sport_state_naving)),
    CROSS_FLOOR_NAV_FAIL(SelfChassisStateContent.CrossFloorNavStatus.NAV_ERROR, ContextUtil.getContext().getString(R.string.sport_state_nav_fail)),
    CROSS_FLOOR_NAV_SUCCESS(SelfChassisStateContent.CrossFloorNavStatus.NAV_SUCCESS, ContextUtil.getContext().getString(R.string.sport_state_nav_success)),
    CROSS_FLOOR_NAV_CANCEL(SelfChassisStateContent.CrossFloorNavStatus.NAV_CANCEL, ContextUtil.getContext().getString(R.string.sport_state_cancel_nav)),
    CROSS_FLOOR_NAV_INPUT_ERROR(SelfChassisStateContent.CrossFloorNavStatus.NAV_INPUT_ERROR, ContextUtil.getContext().getString(R.string.sport_state_nav_fail)),
    CROSS_FLOOR_NAV_TARGET_FLOOR_INVALID(SelfChassisStateContent.CrossFloorNavStatus.NAV_TARGET_FLOOR_INVALID, ContextUtil.getContext().getString(R.string.sport_state_nav_fail)),
    CROSS_FLOOR_NAV_INVALID_TARGET_POINT(SelfChassisStateContent.CrossFloorNavStatus.NAV_INVALID_TARGET_POINT, ContextUtil.getContext().getString(R.string.sport_state_nav_fail)),
    CROSS_FLOOR_NAV_OBTAIN_MARK_POINT_ERROR(SelfChassisStateContent.CrossFloorNavStatus.OBTAIN_MARK_POINT_ERROR, ContextUtil.getContext().getString(R.string.sport_state_nav_fail)),
    CROSS_FLOOR_NAV_ELEVATOR_POINTS_INVALID(SelfChassisStateContent.CrossFloorNavStatus.OBTAIN_ELEVATOR_POINTS_INVALID, ContextUtil.getContext().getString(R.string.sport_state_nav_fail)),
    CROSS_FLOOR_NAV_SINGLE_POINT_FAIL(SelfChassisStateContent.CrossFloorNavStatus.SINGLE_POINT_FAIL, ContextUtil.getContext().getString(R.string.sport_state_nav_fail)),
    CROSS_FLOOR_NAV_SINGLE_POINT_CANCEL(SelfChassisStateContent.CrossFloorNavStatus.SINGLE_POINT_CANCEL, ContextUtil.getContext().getString(R.string.sport_state_nav_fail)),
    CROSS_FLOOR_NAV_RESERVATION_ELEVATOR_FAIL(SelfChassisStateContent.CrossFloorNavStatus.RESERVATION_ELEVATOR_FAIL, ContextUtil.getContext().getString(R.string.sport_state_nav_fail)),
    CROSS_FLOOR_NAV_CANCEL_REJECTION(SelfChassisStateContent.CrossFloorNavStatus.CANCEL_REJECTION, ContextUtil.getContext().getString(R.string.sport_state_naving)),
    CROSS_FLOOR_NAV_WAITING(SelfChassisStateContent.CrossFloorNavStatus.NAV_WAITING, ContextUtil.getContext().getString(R.string.sport_state_naving)),
    SUCCESSFULLY_RECHARGED(901, ContextUtil.getContext().getString(R.string.recharge_ok)),
    CANNOT_DETECT_INFRARED_SIGNAL(902, ContextUtil.getContext().getString(R.string.recharge_fail)),
    CANNOT_FIND_CHARGING_STATION(SelfChassisStateContent.InfraredStatus.CANNOT_FIND_CHARGING_STATION, ContextUtil.getContext().getString(R.string.recharge_fail)),
    RECHARGE_TIMEOUT(SelfChassisStateContent.InfraredStatus.RECHARGE_TIMEOUT, ContextUtil.getContext().getString(R.string.recharge_fail)),
    RECHARGING_IN_OPERATION(SelfChassisStateContent.InfraredStatus.RECHARGING_IN_OPERATION, ContextUtil.getContext().getString(R.string.recharging)),
    RECHARGE_CANCEL(SelfChassisStateContent.InfraredStatus.RECHARGE_CANCEL, ContextUtil.getContext().getString(R.string.recharge_cancel)),
    OBSTACLES_DETECTED_DURING_RETREAT(SelfChassisStateContent.InfraredStatus.OBSTACLES_DETECTED_DURING_RETREAT, ContextUtil.getContext().getString(R.string.recharging)),
    WAIT_FOR_RECHARGE(SelfChassisStateContent.InfraredStatus.WAIT_FOR_RECHARGE, ContextUtil.getContext().getString(R.string.recharging)),
    FAILED_BEFORE_CHARGING_PILE(SelfChassisStateContent.InfraredStatus.FAILED_BEFORE_CHARGING_PILE, ContextUtil.getContext().getString(R.string.recharge_fail)),
    OBSTACLES_BEHIND(SelfChassisStateContent.InfraredStatus.OBSTACLES_BEHIND, ContextUtil.getContext().getString(R.string.recharge_fail)),
    NO_INFRARED_SIGNAL(SelfChassisStateContent.InfraredStatus.NO_INFRARED_SIGNAL, ContextUtil.getContext().getString(R.string.recharge_fail)),
    NO_HEARTBEAT(SelfChassisStateContent.InfraredStatus.NO_HEARTBEAT, ContextUtil.getContext().getString(R.string.recharge_fail)),
    TIMEOUT_ON_CHARGING_PILE(SelfChassisStateContent.InfraredStatus.TIMEOUT_ON_CHARGING_PILE, ContextUtil.getContext().getString(R.string.recharge_fail)),
    BEFORE_RETURNING_TO_THE_CHARGING_PILE(SelfChassisStateContent.InfraredStatus.BEFORE_RETURNING_TO_THE_CHARGING_PILE, ContextUtil.getContext().getString(R.string.recharging)),
    FIND_CHARGING_PILE_SIGNAL(SelfChassisStateContent.InfraredStatus.FIND_CHARGING_PILE_SIGNAL, ContextUtil.getContext().getString(R.string.recharging)),
    CANCEL_WHEN_RECHARGING_NAVIGATING_TO_CHARGING_PILE(SelfChassisStateContent.InfraredStatus.CANCEL_WHEN_RECHARGING_NAVIGATING_TO_CHARGING_PILE, ContextUtil.getContext().getString(R.string.recharge_cancel)),
    WAITING_FOR_RECHARGE(SelfChassisStateContent.InfraredStatus.WAITING_FOR_RECHARGE, ContextUtil.getContext().getString(R.string.recharging)),
    RECHARGE_AND_TRY_AGAIN(SelfChassisStateContent.InfraredStatus.RECHARGE_AND_TRY_AGAIN, ContextUtil.getContext().getString(R.string.recharging)),
    MULTI_POINT_NAV_SUCCESS(SelfChassisStateContent.MultiPointNavStatus.NAV_SUCCESS, ContextUtil.getContext().getString(R.string.sport_state_multi_point_finish)),
    MULTI_POINT_NAV_RUNNING(SelfChassisStateContent.MultiPointNavStatus.NAV_RUNNING, ContextUtil.getContext().getString(R.string.sport_state_naving)),
    MULTI_POINT_NAV_PAUSE(SelfChassisStateContent.MultiPointNavStatus.NAV_PAUSE, ContextUtil.getContext().getString(R.string.txt_nav_pause)),
    MULTI_POINT_NAV_CONTINUE(SelfChassisStateContent.MultiPointNavStatus.NAV_CONTINUE, ContextUtil.getContext().getString(R.string.sport_state_naving)),
    OLD_MULTI_POINT_NAV_SUCCESS(4, ContextUtil.getContext().getString(R.string.sport_state_multi_point_finish)),
    OLD_MULTI_POINT_NAV_RUNNING(1, ContextUtil.getContext().getString(R.string.sport_state_naving)),
    OLD_MULTI_POINT_NAV_PAUSE(2, ContextUtil.getContext().getString(R.string.txt_nav_pause)),
    OLD_MULTI_POINT_NAV_CONTINUE(3, ContextUtil.getContext().getString(R.string.sport_state_naving)),
    MULTI_POINT_NAV_CAN_WORK_BUT_SOME_POI_MISSING(SelfChassisStateContent.MultiPointNavStatus.NAV_FAIL_BUT_SOME_POI_MISSING, ContextUtil.getContext().getString(R.string.sport_state_nav_fail)),
    MULTI_POINT_NAV_FAIL_ONLY_VALID_POINTS(SelfChassisStateContent.MultiPointNavStatus.NAV_FAIL_ONLY_VALID_POINTS, ContextUtil.getContext().getString(R.string.sport_state_nav_fail)),
    MULTI_POINT_NAV_CHECK_RUNNING(SelfChassisStateContent.MultiPointNavStatus.NAV_CHECK_RUNNING, ContextUtil.getContext().getString(R.string.sport_state_naving)),
    MULTI_POINT_NAV_FAIL_NOT_ENOUGH_POINTS(SelfChassisStateContent.MultiPointNavStatus.NAV_FAIL_NOT_ENOUGH_POINTS, ContextUtil.getContext().getString(R.string.sport_state_nav_fail)),
    ELEVATOR_INFO_NOT_CONFIGURED(10000, ContextUtil.getContext().getString(R.string.sport_state_nav_fail)),
    ELEVATOR_INFO_TOKEN_ERROR(SelfChassisStateContent.LiftControlStatus.ELEVATOR_INFO_TOKEN_ERROR, ContextUtil.getContext().getString(R.string.sport_state_nav_fail)),
    WANGLONG_BASIC_ERROR_CODE(SelfChassisStateContent.LiftControlStatus.WANGLONG_BASIC_ERROR_CODE, ContextUtil.getContext().getString(R.string.sport_state_nav_fail)),
    CAN_NOT_REACH_SERVER(SelfChassisStateContent.LiftControlStatus.CAN_NOT_REACH_SERVER, ContextUtil.getContext().getString(R.string.sport_state_nav_fail));
    
    private int code;
    private String desc;

    public static boolean isCommonNavigating(int i) {
        return i == 601;
    }

    public static boolean isCrossFloorNaving(int i) {
        return i >= 640 && i <= 649;
    }

    public static boolean isEnableExitBtn(int i) {
        return i >= 644 && i <= 648;
    }

    public static boolean isNavExit(int i) {
        return i == 603 || i == 604 || i == 605 || (i >= 610 && i <= 615) || i == 650 || i == 651 || i == 602 || i == 652 || ((i >= 902 && i <= 904) || i == 906 || ((i >= 910 && i <= 915) || i == 918 || i == 680));
    }

    public static boolean isNavRemoveLadder(int i) {
        return (i >= 640 && i <= 644) || (i >= 648 && i <= 649);
    }

    private NavStateType(int i, String str) {
        this.code = i;
        this.desc = str;
    }

    public static String getDesc(int i) {
        for (NavStateType navStateType : values()) {
            if (navStateType.getCode() == i) {
                return navStateType.desc;
            }
        }
        return "" + i;
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

    public static boolean isNavigating(int i) {
        return isCommonNavigating(i) || isCrossFloorNaving(i);
    }
}
