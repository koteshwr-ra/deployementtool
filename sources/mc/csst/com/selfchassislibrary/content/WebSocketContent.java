package mc.csst.com.selfchassislibrary.content;

public interface WebSocketContent {
    public static final int CLOSE_GOING_AWAY = 1001;
    public static final int CLOSE_NORMAL = 1000;
    public static final int CLOSE_PROTOCOL_ERROR = 1002;
    public static final int CLOSE_UNSUPPORTED = 1003;
}
