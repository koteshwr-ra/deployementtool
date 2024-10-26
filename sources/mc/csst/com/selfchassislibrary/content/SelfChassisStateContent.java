package mc.csst.com.selfchassislibrary.content;

public interface SelfChassisStateContent {
    public static final int CALL_ELEVATOR_MARKER_TYPE = 3;
    public static final int CHARGE_MARKER_TYPE = 11;
    public static final int CHARGE_TYPE_CHARGING = 1;
    public static final int CHARGE_TYPE_DEFEATED = -1;
    public static final int CHARGE_TYPE_DISCONNECT = 0;
    public static final int CHARGE_TYPE_GOING_BACK = 2;
    public static final int NAV_STATUS_ARRIVED = 3;
    public static final int NAV_STATUS_CANCEL = 2;
    public static final int NAV_STATUS_CHASSIS_STARTING = 0;
    public static final int NAV_STATUS_FAIL = 4;
    public static final int NAV_STATUS_INVALID = 6;
    public static final int NAV_STATUS_REFUSE = 5;
    public static final int NAV_STATUS_RUNNING = 1;
    public static final int NORMAL_MARKER_TYPE = 0;
    public static final int TAKE_ELEVATOR_MARKER_TYPE = 4;

    public interface AreaTypes {
        public static final int DANGER_ZONE = 4;
        public static final int RAMP_ZONE = 6;
        public static final int SENSOR_DATA_IGNORE_ZONE = 5;
        public static final int SLOW_ZONE = 8;
        public static final int TAG_ZONE = 3;
        public static final int WORKING_ZONE = 7;
    }

    public interface CameraCalibrateStatus {
        public static final int COMMAND_IS_STILL_RUNNING = 13003;
        public static final int OUTPUT_RESULT_EXCEPTION = 13002;
        public static final int STARTUP_EXCEPTION = 13001;
        public static final int SUCCESS = 13000;
    }

    public interface ControlState {
        public static final int CONTINUE_SCANNING = 25;
        public static final int CREATE_MAP = 20;
        public static final int ERROR = 99;
        public static final int NAVIGATION = 30;
    }

    public interface CrossFloorNavStatus {
        public static final int CALL_THE_ELEVATOR = 643;
        public static final int CANCEL_REJECTION = 638;
        public static final int ENTER_ELEVATOR = 644;
        public static final int LEAVING_ELEVATOR = 648;
        public static final int NAV_CANCEL = 652;
        public static final int NAV_DETECTION_FEASIBILITY = 640;
        public static final int NAV_ERROR = 650;
        public static final int NAV_INPUT_ERROR = 630;
        public static final int NAV_INVALID_TARGET_POINT = 632;
        public static final int NAV_OUTSIDE_ELEVATOR = 641;
        public static final int NAV_SUCCESS = 651;
        public static final int NAV_TARGET_FLOOR_INVALID = 631;
        public static final int NAV_TO_TARGET_POINT_ON_SPECIFIED_FLOOR = 649;
        public static final int NAV_WAITING = 639;
        public static final int OBTAIN_ELEVATOR_POINTS_INVALID = 634;
        public static final int OBTAIN_MARK_POINT_ERROR = 633;
        public static final int OPTIMIZE_ROBOT_POS = 642;
        public static final int RESERVATION_ELEVATOR_FAIL = 637;
        public static final int ROTATE_TO_FACE_ELEVATOR_DOOR = 646;
        public static final int SINGLE_POINT_CANCEL = 636;
        public static final int SINGLE_POINT_FAIL = 635;
        public static final int SWITCHING_MAP = 645;
        public static final int WAIT_ELEVATOR_TO_REACH_DESIGNATED_FLOOR = 647;
    }

    public interface InfraredStatus {
        public static final int BEFORE_RETURNING_TO_THE_CHARGING_PILE = 916;
        public static final int CANCEL_WHEN_RECHARGING_NAVIGATING_TO_CHARGING_PILE = 918;
        public static final int CANNOT_DETECT_INFRARED_SIGNAL = 902;
        public static final int CANNOT_FIND_CHARGING_STATION = 903;
        public static final int FAILED_BEFORE_CHARGING_PILE = 910;
        public static final int FIND_CHARGING_PILE_SIGNAL = 917;
        public static final int NO_HEARTBEAT = 914;
        public static final int NO_INFRARED_SIGNAL = 913;
        public static final int OBSTACLES_BEHIND = 912;
        public static final int OBSTACLES_DETECTED_DURING_RETREAT = 907;
        public static final int RECHARGE_AND_TRY_AGAIN = 920;
        public static final int RECHARGE_CANCEL = 906;
        public static final int RECHARGE_TIMEOUT = 904;
        public static final int RECHARGING_IN_OPERATION = 905;
        public static final int SUCCESSFULLY_RECHARGED = 901;
        public static final int TIMEOUT_ON_CHARGING_PILE = 915;
        public static final int WAITING_FOR_RECHARGE = 919;
        public static final int WAIT_FOR_RECHARGE = 908;
    }

    public interface LiftControlStatus {
        public static final int CAN_NOT_REACH_SERVER = 10003;
        public static final int ELEVATOR_INFO_NOT_CONFIGURED = 10000;
        public static final int ELEVATOR_INFO_TOKEN_ERROR = 10001;
        public static final int WANGLONG_BASIC_ERROR_CODE = 10002;
    }

    public interface ManagerControlStatus {
        public static final int ALREADY_IN_MODE = 102;
        public static final int BROKEN_MAP = 108;
        public static final int CURRENT_OPERATION_NOT_SUPPORTED = 109;
        public static final int FAILED_TO_SAVE_MAP = 110;
        public static final int MAP_NOT_EXIST = 111;
        public static final int MISSING_MAP_INFO = 107;
        public static final int PROGRAM_NOT_CLOSED = 101;
        public static final int SUCCESS = 0;
        public static final int SYSTEM_FATAL_ERROR = 106;
        public static final int UNKNOWN = 99;
    }

    public interface MultiPointNavStatus {
        public static final int NAV_CHECK_NOT_WORK = 689;
        public static final int NAV_CHECK_RUNNING = 688;
        public static final int NAV_CONTINUE = 683;
        public static final int NAV_FAIL_BUT_SOME_POI_MISSING = 686;
        public static final int NAV_FAIL_NOT_ENOUGH_POINTS = 690;
        public static final int NAV_FAIL_ONLY_VALID_POINTS = 687;
        public static final int NAV_PAUSE = 682;
        public static final int NAV_RUNNING = 681;
        public static final int NAV_SUCCESS = 680;
    }

    public interface NavStatus {
        public static final int CANCEL_NAV = 602;
        public static final int INIT = 600;
        public static final int NAVIGATING = 601;
        public static final int NAV_CANCELING = 606;
        public static final int NAV_CONFIG_FAIL = 620;
        public static final int NAV_EMERGENCY_STOP = 621;
        public static final int NAV_FAIL = 604;
        public static final int NAV_REFUSED = 605;
        public static final int NAV_REFUSED_ACCESS_CONTROL_NAV_RUNNING = 613;
        public static final int NAV_REFUSED_CROSS_FLOOR_NAV_RUNNING = 614;
        public static final int NAV_REFUSED_EMERGENCY_STOP = 611;
        public static final int NAV_REFUSED_MULTIPOINT_NAV_RUNNING = 612;
        public static final int NAV_REFUSED_RECHARGING = 610;
        public static final int NAV_REFUSED_TURNSTILE_NAV_RUNNING = 615;
        public static final int NAV_RESETTING = 607;
        public static final int NAV_RESET_COMPLETE = 608;
        public static final int NAV_STATUS_LOST = 609;
        public static final int NAV_SUCCESS = 603;
    }
}
