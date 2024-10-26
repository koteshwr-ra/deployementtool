package mc.csst.com.selfchassis.utils.constant;

public class DeploymentToolConstant {
    public static final int BUILD_MAP_TYPE_ASSIST = 13;
    public static final int BUILD_MAP_TYPE_BIG = 12;
    public static final int BUILD_MAP_TYPE_LIKE = 11;
    public static final int BUILD_MAP_TYPE_NORMAL = 0;
    public static final String CHASSIS_DIRECT_IP = "192.168.20.22";
    public static final String CHASSIS_IP = "10.42.0.1";
    public static final int CHASSIS_PORT = 9090;
    public static final String CHECK_LIFT_SERVER_OPEN = "Service /lift_control/configure does not exist";
    public static final String CHECK_NAVI_SETTING_EXIST = "Service /navi_setting does not exist";
    public static final String CHECK_ULTRASONIC_EXIST = "Service /sensor_setting/ultrasonic does not exist";
    public static final int CONTINUE_SCAN_MAP = 1;
    public static final boolean ENV_WAN_DE_CHANG = false;
    public static final int KEY_MODE_EIGHT = 1;
    public static final int KEY_MODE_FOUR = 0;
    public static final int MAIN_BRUSH_TYPE_B = 3;
    public static final int MAIN_BRUSH_TYPE_M = 2;
    public static final int MAIN_BRUSH_TYPE_NORMAL = 0;
    public static final int MAIN_BRUSH_TYPE_S = 1;
    public static final String MAIN_LEFT_TOOL_TYPE_DANGER_ZONE = "layer_danger_area";
    public static final String MAIN_LEFT_TOOL_TYPE_EMPTY_ZONE = "layer_empty_area";
    public static final String MAIN_LEFT_TOOL_TYPE_NARROW_CHANNEL = "layer_narrow_channel";
    public static final String MAIN_LEFT_TOOL_TYPE_NORMAL = "layer_normal";
    public static final String MAIN_LEFT_TOOL_TYPE_OBSTACLE_ZONE = "layer_obstacle_area";
    public static final String MAIN_LEFT_TOOL_TYPE_SLOPE_ZONE = "layer_slope_area";
    public static final String MAIN_LEFT_TOOL_TYPE_SLOW_ZONE = "layer_slow_area";
    public static final String MAIN_LEFT_TOOL_TYPE_STRONG_LIGHT_ZONE = "layer_strong_light_area";
    public static final String MAIN_LEFT_TOOL_TYPE_TAG_ZONE = "layer_label_area";
    public static final String MAIN_LEFT_TOOL_TYPE_UNKNOWN_ZONE = "layer_unknown_area";
    public static final String MAIN_LEFT_TOOL_TYPE_VIRTUAL_WALL = "layer_virtual_wall";
    public static final String MAIN_LEFT_TOOL_TYPE_WORKING_ZONE = "layer_work_area";
    public static final int MAIN_RIGHT_TOOL_TYPE_CALIBRATION = 3;
    public static final int MAIN_RIGHT_TOOL_TYPE_COLLECT_MODE = 8;
    public static final int MAIN_RIGHT_TOOL_TYPE_COVERAGE = 1;
    public static final int MAIN_RIGHT_TOOL_TYPE_EDIT_MAP = 4;
    public static final int MAIN_RIGHT_TOOL_TYPE_LINE_TRACKING = 7;
    public static final int MAIN_RIGHT_TOOL_TYPE_NORMAL = 0;
    public static final int MAIN_RIGHT_TOOL_TYPE_POSITIONING = 2;
    public static final int MAIN_RIGHT_TOOL_TYPE_SETTING = 5;
    public static final int MAIN_RIGHT_TOOL_TYPE_VISUAL_LABEL_CALIBRATION_MODE = 6;
    public static final int MAIN_SHAPE_TYPE_LINE = 1;
    public static final int MAIN_SHAPE_TYPE_NORMAL = 0;
    public static final int MAIN_SHAPE_TYPE_RECTANGLE = 2;
    public static final int NAV_MODE_AUTO = 0;
    public static final int NAV_MODE_VIRTUAL = 1;
    public static final int NAV_TYPE_MULTI = 1;
    public static final int NAV_TYPE_SINGLE = 0;
    public static final int POINT_CHARGE = 11;
    public static final int POINT_COMMON = 0;
    public static final int POINT_DOOR_GUARD = 8;
    public static final int POINT_ELEVATOR_IN = 4;
    public static final int POINT_ELEVATOR_OUT = 3;
    public static final int POINT_GATE_MACHINE = 7;
    public static final int POINT_OUTWARD_OF_CHARGING_PILE = 10;
    public static final int POINT_STOP = 50;
    public static final int POINT_TRAJECTORY = -65535;
    public static final int POINT_UNKOWN = 22;
    public static final int POINT_VISUAL_LABEL = 20;
    public static final int POINT_WAIT = 5;
    public static final int SOFT_TYPE_ADD_RANDOM_POINT = 12;
    public static final int SOFT_TYPE_CALIBRATION_MAP = 3;
    public static final int SOFT_TYPE_COLLECT_MODE = 11;
    public static final int SOFT_TYPE_CONTINUE_SCAN_MAP = 9;
    public static final int SOFT_TYPE_CROSS_FLOOR_NAV = 5;
    public static final int SOFT_TYPE_EDIT_MAP = 4;
    public static final int SOFT_TYPE_LOCAL_SELF_POSITION = 7;
    public static final int SOFT_TYPE_MARKER_NAV = 6;
    public static final int SOFT_TYPE_NAV = 1;
    public static final int SOFT_TYPE_NORMAL = 0;
    public static final int SOFT_TYPE_SCAN_MAP = 2;
    public static final int SOFT_TYPE_TRAJECTORY_DRAW = 10;
    public static final int SOFT_TYPE_VISUAL_LABEL_CALIBRATION_MODE = 8;
    public static final int SPEED_TYPE_CENTRE = 1;
    public static final int SPEED_TYPE_HIGH = 2;
    public static final int SPEED_TYPE_LOW = 0;
    public static final int TIP_TV_MULTI_CLICK_TIMES = 6;
    public static final int TRAVEL_MODE_BALANCE = 3;
    public static final int TRAVEL_MODE_EFFICIENCY = 6;
    public static final int TRAVEL_MODE_SECURITY = 0;
    public static final String UPDATE_URL_LAN_HAI = "http://slam.csstrobot.com/slam/version_info_LH.yaml";
    public static final String UPDATE_URL_LI_CE = "http://slam.csstrobot.com/slam/version_info.yaml";
    public static final String VOCA_ALGORITHM_PREFIX = "PR1150D";

    public interface LaserRadarObstacleDistance {
        public static final float MAXIMUM_LIMIT = 10.0f;
        public static final float MINIMUM_LIMIT = 0.05f;
    }

    public interface NavFailureTime {
        public static final int MAXIMUM_LIMIT = 300;
        public static final int MINIMUM_LIMIT = 0;
    }

    public interface OnlineUpdateUrlType {
        public static final int TYPE_CUSTOMIZE = 2;
        public static final int TYPE_EMPTY = -1;
        public static final int TYPE_LAN_HAI = 1;
        public static final int TYPE_LI_CE = 0;
        public static final int TYPE_LI_CE_OLD_VERSION = 3;
    }

    public interface PathWidth {
        public static final float MAXIMUM_LIMIT = 10.0f;
        public static final float MINIMUM_LIMIT = 0.0f;
    }

    public interface PatrolMode {
        public static final int TYPE_CYCLE = 2;
        public static final int TYPE_RANDOM = 1;
        public static final int TYPE_ROUND_TRIP = 3;
    }

    public interface PointToleranceAngle {
        public static final int MAXIMUM_LIMIT = 180;
        public static final int MINIMUM_LIMIT = 5;
    }

    public interface PointToleranceDistance {
        public static final float MAXIMUM_LIMIT = 0.5f;
        public static final float MINIMUM_LIMIT = 0.05f;
    }

    public interface SafeStopDistance {
        public static final float MAXIMUM_LIMIT = 3.0f;
        public static final float MINIMUM_LIMIT = 0.35f;
    }

    public interface UltrasoundDistance {
        public static final float DEFAULT_VALUE = 0.2f;
        public static final float MAXIMUM_LIMIT = 1.0f;
        public static final float MINIMUM_LIMIT = 0.01f;
    }
}
