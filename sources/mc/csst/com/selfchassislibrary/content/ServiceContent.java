package mc.csst.com.selfchassislibrary.content;

public interface ServiceContent {
    public static final String AREA_MANAGER_CONTROL = "/area_manager/control";
    public static final String AREA_MANAGER_DELETE_POI = "/area_manager/delete_poi";
    public static final String BAG_CANCLE = "/bag_cancel";
    public static final String BAG_RECORD = "/bag_record";
    public static final String BUILDING_OPERATION_DELETE = "/building_operation/delete";
    public static final String CANCEL_UPGRADE_DOWNLOAD = "/cancel_upgrade_download";
    public static final String CHANGE_LOCATION_MODE = "/change_location_mode";
    public static final String CONFIG_STATION_SERVER = "/config_mqtt_server";
    public static final String DELETE_POI = "/path_manager/delete_poi";
    public static final String DOWNLOAD_MAPS = "/download_maps";
    public static final String DOWN_CAMERA_EXTRINSIC_AUTO_CALIBRATE = "/downcamera_extrinsic_autocalibrate";
    public static final String FOLLOW_PATH_OP = "/followPath";
    public static final String GET_DOOR_LENGTH = "/get_door_length";
    public static final String GET_GATE_LENGTH = "/get_gate_length";
    public static final String GET_MAP_INFO = "/get_map_info";
    public static final String GET_MARKERS_DETAILS = "/marker_manager/get_markers_details";
    public static final String GET_MATCH_THRESHOLD = "/get_match_threshold";
    public static final String GET_TIME = "/rosapi/get_time";
    public static final String GLOBAL_LOCATE = "/global_locate";
    public static final String LABEL_CAMERA_EXTRINSIC_CONFIG = "/labelcamera_extrinsic_config";
    public static final String LASER_DETECTION_SETTING = "/laser_detection/setting";
    public static final String LAYERED_MAP_CMD = "/layered_map_cmd";
    public static final String LIFT_CONTROL_COMMAND = "/lift_control/command";
    public static final String LIFT_CONTROL_CONFIGURE = "/lift_control/configure";
    public static final String MARKER_MANAGER_DELETE_POI = "/marker_manager/delete_poi";
    public static final String MARKER_OPERATION_DELETE_MARKERS = "/marker_operation/delete_markers";
    public static final String MARKER_OPERATION_GET_MARKERS = "/marker_operation/get_markers";
    public static final String NAVI_SETTING = "/navi_setting";
    public static final String NODE_MANAGER_CONTROL = "/node_manager_control";
    public static final String PATH_GET = "/path_manager/path_get";
    public static final String PATH_MANAGER_CONTROL = "/path_manager/control";
    public static final String PATH_RECORD = "/path_manager/path_record";
    public static final String PATH_SAVE = "/path_manager/path_save";
    public static final String POI = "/poi";
    public static final String POI_ID = "/poi_id";
    public static final String POI_INIT = "/poi_init";
    public static final String POI_PATROL = "/poi_patrol";
    public static final String RECORD_MILES = "/record_miles";
    public static final String ROBOT_INFO = "/robot_info";
    public static final String SELF_DIAGNOSIS = "/self_diagnosis";
    public static final String SENSORS_CONFIG = "/sensors_config";
    public static final String START_RECHARGE = "/start_recharge";
    public static final String TAG_MANAGER_CONTROL = "/tag_manager/control";
    public static final String TAG_MANAGER_DELETE_POI = "/tag_manager/delete_poi";
    public static final String TAG_MANAGER_MODE = "/tag_manager/mode";
    public static final String TAG_MANAGER_NAVI = "/tag_manager/navi";
    public static final String ULTRASONIC_DISTANCE = "/sensor_setting/ultrasonic";
    public static final String UPLOAD_MAPS = "/upload_maps";
    public static final String UP_CAMERA_EXTRINSIC_AUTO_CALIBRATE = "/upcamera_extrinsic_autocalibrate";
    public static final String VELOCITY_CONTROL = "/velocity_control";
    public static final String VERSION_UPGRADE = "/version_upgrade";
    public static final String VIRTUAL_WALL_MANAGER_CONTROL = "/virtual_wall_manager/control";
    public static final String VIRTUAL_WALL_MANAGER_DELETE_POIE = "/virtual_wall_manager/delete_poi";
    public static final String VIRTUAL_WALL_OPERATION_DELETE_WALLS = "/virtual_wall_operation/delete_walls";
    public static final String VIRTUAL_WALL_OPERATION_GET_WALLS = "/virtual_wall_operation/get_walls";

    public interface MatchThreshold {
        public static final String MINNUM_COMMAND = "/minnum_command";
        public static final String MINNUM_FINDRESULT = "/minnum_findresult";
        public static final String MINNUM_STATUS = "/minnum_status";
    }
}
