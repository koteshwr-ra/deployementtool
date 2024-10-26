package mc.csst.com.selfchassislibrary.content;

public interface VersionUpgradeContent {
    public static final int ALGORITHM = 0;
    public static final int CANCEL_UPGRADE_DOWNLOAD = 18;
    public static final int CLEAR_CHASSIS_LOG = 51;
    public static final int COMPRESS_CHASSIS_LOG = 50;
    public static final int COMPRESS_CHASSIS_MAP = 77;
    public static final int DECOMPRESS_CHASSIS_MAP = 78;
    public static final int DRIVE_FIRMWARE = 55;
    public static final int UPDATE_NET = 12;
    public static final int UPDATE_NET_BY_SPECIFY_LINK = 88;
    public static final int UPDATE_TIME = 68;
    public static final int UPDATE_USB = 11;
    public static final int VERSION_LAST_NET = 1;
    public static final int VERSION_LAST_NET_BY_SPECIFY_LINK = 87;
    public static final int VERSION_LAST_USB = 2;

    public interface VersionResultContent {
        public static final int FILE_CHECK_FAILED = 715;
        public static final int FIRMWARE_CANCEL_UPGRADE = 718;
        public static final int FIRMWARE_INVALID = 717;
        public static final int NETWORK_DOWNLOAD_FAILED = 713;
        public static final int NETWORK_FILE_FAILED = 712;
        public static final int NETWORK_FILE_READ_FAILED = 714;
        public static final int NETWORK_SUCCESS = 752;
        public static final int NOT_ALLOW_RUNNING_TASK_BECAUSE_OF_UPDATING = 749;
        public static final int NO_NETWORK = 711;
        public static final int PACK_UNPACK_FAILED = 788;
        public static final int PROCESSING = 10;
        public static final int RESULT_OK = 0;
        public static final int UNKNOWN = 99;
        public static final int UPDATE_TIME_FAILED = 763;
        public static final int UPDATE_TIME_FAILED_NETWORK = 762;
        public static final int USB_NOT_FOUND = 701;
        public static final int USB_NOT_UPDATE = 702;
        public static final int USB_SUCCESS = 751;
        public static final int USB_UPDATE_BAD = 704;
        public static final int USB_UPDATE_INVALID = 703;
    }
}
