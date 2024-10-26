package mc.csst.com.selfchassislibrary.content;

public interface OpContent {
    public static final String ADVERTISE = "advertise";
    public static final String CALL_SERVICE = "call_service";
    public static final String FRAGMENT = "fragment";
    public static final String PUBLISH = "publish";
    public static final String SERVICE_RESPONSE = "service_response";
    public static final String SUBSCRIBE = "subscribe";
    public static final String UNADVERTISE = "unadvertise";
    public static final String UNSUBSCRIBE = "unsubscribe";

    public interface AreaManagerCmd {
        public static final int DELETE_ALL = 4;
        public static final int REVERT = 1;
    }

    public interface LayeredMapCmd {
        public static final int GET_AREAS = 45;
        public static final int GET_FLOOR_DATA = 4;
        public static final int LAYERED_MAP_CMD = 0;
        public static final int MAP_RENAME = 70;
    }

    public interface TagManagerControl {
        public static final int DELETE_ALL = 4;
        public static final int SAVE = 2;
    }
}
