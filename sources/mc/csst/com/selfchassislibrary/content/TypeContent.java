package mc.csst.com.selfchassislibrary.content;

public interface TypeContent {
    public static final String ACTIONLIB_MSGS_GOALID = "actionlib_msgs/GoalID";
    public static final String ACTIONLIB_MSGS_GOALSTATUS = "actionlib_msgs/GoalStatus";
    public static final String ACTIONLIB_MSGS_GOAL_ID = "actionlib_msgs/GoalID";
    public static final String AREA_MSGS_AREAZONE = "area_msgs/areazone";
    public static final String AREA_MSGS_ONEAREA = "area_msgs/oneArea";
    public static final String FOLLOW_PATH_MSGS_PATHNODE = "follow_path_msgs/PathNode";
    public static final String GEOMETRY_MSGS_POSE2D = "geometry_msgs/Pose2D";
    public static final String GEOMETRY_MSGS_POSESTAMPED = "geometry_msgs/PoseStamped";
    public static final String GEOMETRY_MSGS_POSEWITHCOVARIANCESTAMPED = "geometry_msgs/PoseWithCovarianceStamped";
    public static final String GEOMETRY_MSGS_TWIST = "geometry_msgs/Twist";
    public static final String KOBUKI_MSGS_SENSORSTATE = "kobuki_msgs/SensorState";
    public static final String MOVE_BASE_MSGS_MOVEBASEACTIONGOAL = "move_base_msgs/MoveBaseActionGoal";
    public static final String MQTTCLIENT_ROBOT_LIST = "mqtt_msg/RobotList";
    public static final String NAV_MSGS_OCCUPANCYGRID = "nav_msgs/OccupancyGrid";
    public static final String NUMPY_MSGS_OCCUPANCYGRID = "numpy_msg/OccupancyGrid”";
    public static final String ROSGRAPH_MSGS_LOG = "rosgraph_msgs/Log";
    public static final String STD_MSGS_BOOL = "std_msgs/Bool";
    public static final String STD_MSGS_EMPTY = "std_msgs/Empty";
    public static final String STD_MSGS_FLOAT32 = "std_msgs/Float32";
    public static final String STD_MSGS_FLOAT64 = "std_msgs/Float64";
    public static final String STD_MSGS_INT32 = "std_msgs/Int32";
    public static final String STD_MSGS_INT8 = "std_msgs/Int8";
    public static final String STD_MSGS_STRING = "std_msgs/String";
    public static final String YUTONG_ASSISTANCE_CROSSFLOORNAVI = "yutong_assistance/CrossFloorNavi";
    public static final String YUTONG_ASSISTANCE_INSERTPOSE = "yutong_assistance/InsertPose";
    public static final String YUTONG_ASSISTANCE_PENCILOPLIST = "yutong_assistance/PencilOpList";
    public static final String YUTONG_ASSISTANCE_POINT_ARRAY = "yutong_assistance/point_array";
    public static final String YUTONG_ASSISTANCE_ROBOTSTATUS = "yutong_assistance/RobotStatus";
    public static final String YUTONG_ASSISTANCE_VIRTUALWALLS = "yutong_assistance/VirtualWalls";
    public static final String YUTONG_ASSISTANCE_WAYPOINTLIST = "yutong_assistance/WaypointList";
    public static final String YU_TONG_ASSISTANCE_PATROL_FEED_BACK = "yutong_assistance/patrolFeedback";

    public interface BagRecordType {
        public static final String localization = "localization";
        public static final String mapping = "mapping";
        public static final String navi = "navi";
        public static final String sensors = "sensors";
    }
}
