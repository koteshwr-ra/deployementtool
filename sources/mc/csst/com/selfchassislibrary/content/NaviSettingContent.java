package mc.csst.com.selfchassislibrary.content;

public interface NaviSettingContent {
    public static final String LASER_DETECTION_NOTIFICATION = "laser_detection_notification";
    public static final String LASER_DETECTION_RANGE = "laser_detection_range";
    public static final String LOCAL_PLANNER_MAX_VEL_X = "local_planner/max_vel_x";
    public static final String NAVIGATION_MODE_AUTO_NAVI = "navigation_mode/auto_navi";
    public static final String NAVIGATION_MODE_FOLLOW_PATH = "navigation_mode/follow_path";
    public static final String NAV_FAILURE_TIME = "general/planner_timeout";
    public static final String SAFE_STOP_DISTANCE = "general/tolerance_distance";
    public static final String XY_GOAL_TOLERANCE = "local_planner/xy_goal_tolerance";
    public static final String YAW_GOAL_TOLERANCE = "local_planner/yaw_goal_tolerance";
}
