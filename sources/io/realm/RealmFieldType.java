package io.realm;

import com.tencent.smtt.sdk.TbsListener;
import java.nio.ByteBuffer;
import java.util.Date;
import org.apache.log4j.net.SyslogAppender;

public enum RealmFieldType {
    INTEGER(0),
    BOOLEAN(1),
    STRING(2),
    BINARY(4),
    DATE(8),
    FLOAT(9),
    DOUBLE(10),
    OBJECT(12),
    LIST(13),
    LINKING_OBJECTS(14),
    INTEGER_LIST(128),
    BOOLEAN_LIST(TbsListener.ErrorCode.RENAME_NO_NEED_SENDREQUEST),
    STRING_LIST(130),
    BINARY_LIST(132),
    DATE_LIST(SyslogAppender.LOG_LOCAL1),
    FLOAT_LIST(137),
    DOUBLE_LIST(138);
    
    private static final RealmFieldType[] basicTypes = null;
    private static final RealmFieldType[] listTypes = null;
    private final int nativeValue;

    static {
        int i;
        basicTypes = new RealmFieldType[15];
        listTypes = new RealmFieldType[15];
        for (RealmFieldType realmFieldType : values()) {
            int i2 = realmFieldType.nativeValue;
            if (i2 < 128) {
                basicTypes[i2] = realmFieldType;
            } else {
                listTypes[i2 - 128] = realmFieldType;
            }
        }
    }

    private RealmFieldType(int i) {
        this.nativeValue = i;
    }

    public int getNativeValue() {
        return this.nativeValue;
    }

    public boolean isValid(Object obj) {
        int i = this.nativeValue;
        if (i != 0) {
            if (i == 1) {
                return obj instanceof Boolean;
            }
            if (i == 2) {
                return obj instanceof String;
            }
            if (i != 4) {
                if (i == 132) {
                    return false;
                }
                switch (i) {
                    case 8:
                        return obj instanceof Date;
                    case 9:
                        return obj instanceof Float;
                    case 10:
                        return obj instanceof Double;
                    default:
                        switch (i) {
                            case 12:
                            case 13:
                            case 14:
                                break;
                            default:
                                switch (i) {
                                    case 128:
                                    case TbsListener.ErrorCode.RENAME_NO_NEED_SENDREQUEST:
                                    case 130:
                                        break;
                                    default:
                                        switch (i) {
                                            case SyslogAppender.LOG_LOCAL1 /*136*/:
                                            case 137:
                                            case 138:
                                                break;
                                            default:
                                                throw new RuntimeException("Unsupported Realm type:  " + this);
                                        }
                                }
                        }
                        return false;
                }
            } else if ((obj instanceof byte[]) || (obj instanceof ByteBuffer)) {
                return true;
            } else {
                return false;
            }
        } else if ((obj instanceof Long) || (obj instanceof Integer) || (obj instanceof Short) || (obj instanceof Byte)) {
            return true;
        } else {
            return false;
        }
    }

    public static RealmFieldType fromNativeValue(int i) {
        RealmFieldType realmFieldType;
        RealmFieldType realmFieldType2;
        if (i >= 0) {
            RealmFieldType[] realmFieldTypeArr = basicTypes;
            if (i < realmFieldTypeArr.length && (realmFieldType2 = realmFieldTypeArr[i]) != null) {
                return realmFieldType2;
            }
        }
        if (128 <= i) {
            int i2 = i - 128;
            RealmFieldType[] realmFieldTypeArr2 = listTypes;
            if (i2 < realmFieldTypeArr2.length && (realmFieldType = realmFieldTypeArr2[i2]) != null) {
                return realmFieldType;
            }
        }
        throw new IllegalArgumentException("Invalid native Realm type: " + i);
    }
}
