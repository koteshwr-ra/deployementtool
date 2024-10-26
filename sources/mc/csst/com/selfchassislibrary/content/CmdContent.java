package mc.csst.com.selfchassislibrary.content;

public interface CmdContent {

    public interface LiftControlConfigure {
        public static final int DELETE_CONF_INFO = 4;
        public static final int GET_CONF_INFO = 1;
        public static final int SET_CONF_INFO = 0;
    }

    public interface MatchThreshold {
        public static final int TURN_OFF_AUTOMATIC_MATCHING_CALCULATION = 0;
        public static final int TURN_ON_AUTOMATIC_MATCHING_CALCULATION = 1;
    }

    public interface NaviSetting {
        public static final int CONFIG_NAVI_SETTING = 1;
        public static final int GET_NAVI_SETTING = 2;
    }

    public interface NodeManagerControl {
        public static final int CONTINUE_BUILD_MAPS = 1;
        public static final int OPEN_MAP = 0;
        public static final int RESTART_BUILD_MAPS = 2;
        public static final int SAVE_MAP_INFO = 3;
        public static final int SWITCH_MAP = 7;
        public static final int TURN_ON_LOCATION_NAV = 4;
    }

    public interface TagManagerMode {
        public static final int CALIBRATION_MODE = 1;
        public static final int POSITIONING_MODE = 2;
    }

    public interface UltrasonicDistance {
        public static final int GET_ULTRASONIC_DISTANCE = 2;
        public static final int SET_ULTRASONIC_DISTANCE = 1;
    }
}
