package mc.csst.com.selfchassis.utils.constant;

public interface LayerConst {

    public interface LayerId {
        public static final String LABEL_POINT = "layer_label_point";
        public static final String LOCATION = "layer_location";
        public static final String MARK_POINT = "layer_mark_point";
        public static final String NORMAL = "layer_normal";
        public static final String TRAJECTORY = "layer_trajectory";
        public static final String TRAVEL_PATH = "layer_travel_path";
        public static final String VIRTUAL_WALL = "layer_virtual_wall";

        public interface Area {
            public static final String AREA = "layer_area";
            public static final String DANGER_AREA = "layer_danger_area";
            public static final String EMPTY_AREA = "layer_empty_area";
            public static final String LABEL_AREA = "layer_label_area";
            public static final String NARROW_CHANNEL = "layer_narrow_channel";
            public static final String OBSTACLE_AREA = "layer_obstacle_area";
            public static final String SLOPE_AREA = "layer_slope_area";
            public static final String SLOW_AREA = "layer_slow_area";
            public static final String STRONG_LIGHT_AREA = "layer_strong_light_area";
            public static final String UNKNOWN_AREA = "layer_unknown_area";
            public static final String WORK_AREA = "layer_work_area";
        }

        public interface PointCloud {
            public static final String DOWN_CAM = "layer_down_cam_point_cloud";
            public static final String POINT_CLOUD = "layer_point_cloud";
            public static final String RADAR = "layer_radar_point_cloud";
            public static final String UP_CAM = "layer_up_cam_point_cloud";
        }
    }

    public interface LayerState {
        public static final int NOT_EDITABLE = -1;
        public static final int SELECTED = 1;
        public static final int UNSELECTED = 0;
    }
}
