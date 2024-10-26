package mc.csst.com.selfchassislibrary.content;

public interface IDContent {
    public static final String CONFIG_STATION_SERVER = "set_mqtt_server";
    public static final String GET_PATROL_FEEDBACK = "get_patrol_feedback";
    public static final String GET_ROBOT_LIST = "get_robot_list";
    public static final String GET_UPGRADEDOWNLOADSTATUS = "get_upgradeDownloadStatus";
    public static final String MARKER_OPERATION_GET_ALL_ID = "MARKER_OPERATION_GET_ALL_ID";
    public static final String SERVICE_AREA_MANAGER_REVERT = "service_area_manager_revert";
    public static final String SERVICE_CANCEL_UPGRADE_DOWNLOAD = "service_cancel_upgrade_download";
    public static final String SERVICE_CHANGE_LOCATION_MODE = "service_change_location_mode";
    public static final String SERVICE_CROSS_FLOOR_CANCEL = "service_cross_floor_cancel";
    public static final String SERVICE_DELETE_LIFT_CONFIGURE = "service_delete_lift_configure";
    public static final String SERVICE_DOWNCAMERA_AUTOCALIBRATE = "service_downcamera_autocalibrate";
    public static final String SERVICE_GET_AREAS = "service_get_areas";
    public static final String SERVICE_GET_FLOOR_DATA = "service_get_floor_data";
    public static final String SERVICE_GET_LIFT_CONFIGURE = "service_get_lift_configure";
    public static final String SERVICE_GLOBAL_LOCATE_GLOBAL = "service_global_locate_global";
    public static final String SERVICE_GLOBAL_LOCATE_LOCAL = "service_global_locate_local";
    public static final String SERVICE_LABELCAMERA_EXTRINSIC_CONFIG = "service_labelcamera_extrinsic_config";
    public static final String SERVICE_LASER_DETECTION_SETTING = "service_laser_detection_setting";
    public static final String SERVICE_LAYERED_MAP_CMD = "service_layered_map_cmd";
    public static final String SERVICE_MAP_RENAME = "service_map_rename";
    public static final String SERVICE_SET_LIFT_CONFIGURE = "service_set_lift_configure";
    public static final String SERVICE_TAG_POI = "service_tag_poi";
    public static final String SERVICE_UPCAMERA_AUTOCALIBRATE = "service_upcamera_autocalibrate";
    public static final String SERVICE_VIRTUAL_WALL_MANAGER_REVERT = "service_virtual_wall_manager_revert";
    public static final String SET_POSE_ADVANCE = "set_pose_advance";

    public interface AreaManager {
        public static final String DELETE_ALL = "service_area_manager_delete_all";
        public static final String REVERT = "service_area_manager_revert";
    }

    public interface MatchThreshold {
        public static final String SERVICE_MINNUM_COMMAND = "service_minnum_command";
        public static final String SERVICE_MINNUM_FINDRESULT = "service_minnum_findresult";
        public static final String SERVICE_MINNUM_STATUS = "service_minnum_status";
    }

    public interface NaviSetting {
        public static final String CONFIG_NAVI_SETTING = "CONFIG_NAVI_SETTING";
        public static final String GET_NAVI_SETTING = "GET_NAVI_SETTING";
    }

    public interface Other {
        public static final String GET_MAP_CHART = "get_map_chart/mapdata";
        public static final String SERVICE_BAG_CANCLE = "service_bag_cancel";
        public static final String SERVICE_BAG_RECORD = "service_bag_record";
    }

    public interface TagManagerControl {
        public static final String DELETE_ALL = "service_area_manager_delete_all";
        public static final String GET_CURRENT_TAG = "get_current_tag";
        public static final String SAVE = "service_area_manager_save";
    }

    public interface TrajectoryDrawing {
        public static final String CANCEL_PATH_FOLLOW = "cancel_path_follow";
        public static final String PATH_MARKER_SYNC = "path_marker_sync";
        public static final String SERVICE_DELETE_PATH_POI = "service_delete_path_poi";
        public static final String SERVICE_FOLLOW_PATH_OP = "service_follow_path_op";
        public static final String SERVICE_GET_PATH = "service_get_path";
        public static final String SERVICE_LOAD_PATH = "service_load_path";
        public static final String SERVICE_PATH_RECORD = "service_path_record";
        public static final String SERVICE_SAVE_PATH = "service_path_save";
    }

    public interface UltrasonicDistance {
        public static final String SERVICE_ULTRASONIC_DISTANCE_GET = "service_ultrasonic_distance_get";
        public static final String SERVICE_ULTRASONIC_DISTANCE_SET = "service_ultrasonic_distance_set";
    }

    public interface VelocityControl {
        public static final String GET_SPEED = "VELOCITY_CONTROL_GET";
        public static final String SET_SPEED = "VELOCITY_CONTROL_SET";
    }
}
