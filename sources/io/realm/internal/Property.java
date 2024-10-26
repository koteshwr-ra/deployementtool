package io.realm.internal;

import com.tencent.smtt.sdk.TbsListener;
import io.realm.RealmFieldType;
import java.util.Locale;
import org.apache.log4j.net.SyslogAppender;

public class Property implements NativeObject {
    public static final boolean INDEXED = true;
    public static final boolean PRIMARY_KEY = true;
    public static final boolean REQUIRED = true;
    public static final int TYPE_ARRAY = 128;
    public static final int TYPE_BOOL = 1;
    public static final int TYPE_DATA = 3;
    public static final int TYPE_DATE = 4;
    public static final int TYPE_DOUBLE = 6;
    public static final int TYPE_FLOAT = 5;
    public static final int TYPE_INT = 0;
    public static final int TYPE_LINKING_OBJECTS = 8;
    public static final int TYPE_NULLABLE = 64;
    public static final int TYPE_OBJECT = 7;
    public static final int TYPE_REQUIRED = 0;
    public static final int TYPE_STRING = 2;
    private static final long nativeFinalizerPtr = nativeGetFinalizerPtr();
    private long nativePtr;

    static native long nativeCreateComputedLinkProperty(String str, String str2, String str3);

    static native long nativeCreatePersistedLinkProperty(String str, int i, String str2);

    static native long nativeCreatePersistedProperty(String str, int i, boolean z, boolean z2);

    private static native long nativeGetColumnKey(long j);

    private static native long nativeGetFinalizerPtr();

    private static native String nativeGetLinkedObjectName(long j);

    private static native int nativeGetType(long j);

    Property(long j) {
        this.nativePtr = j;
        NativeContext.dummyContext.addReference(this);
    }

    /* renamed from: io.realm.internal.Property$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$io$realm$RealmFieldType;

        /* JADX WARNING: Can't wrap try/catch for region: R(36:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|36) */
        /* JADX WARNING: Code restructure failed: missing block: B:37:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0054 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0060 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x006c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0078 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x0084 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x0090 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x009c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x00a8 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:31:0x00b4 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:33:0x00c0 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                io.realm.RealmFieldType[] r0 = io.realm.RealmFieldType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$io$realm$RealmFieldType = r0
                io.realm.RealmFieldType r1 = io.realm.RealmFieldType.OBJECT     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$io$realm$RealmFieldType     // Catch:{ NoSuchFieldError -> 0x001d }
                io.realm.RealmFieldType r1 = io.realm.RealmFieldType.LIST     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$io$realm$RealmFieldType     // Catch:{ NoSuchFieldError -> 0x0028 }
                io.realm.RealmFieldType r1 = io.realm.RealmFieldType.LINKING_OBJECTS     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$io$realm$RealmFieldType     // Catch:{ NoSuchFieldError -> 0x0033 }
                io.realm.RealmFieldType r1 = io.realm.RealmFieldType.INTEGER     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$io$realm$RealmFieldType     // Catch:{ NoSuchFieldError -> 0x003e }
                io.realm.RealmFieldType r1 = io.realm.RealmFieldType.BOOLEAN     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$io$realm$RealmFieldType     // Catch:{ NoSuchFieldError -> 0x0049 }
                io.realm.RealmFieldType r1 = io.realm.RealmFieldType.STRING     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = $SwitchMap$io$realm$RealmFieldType     // Catch:{ NoSuchFieldError -> 0x0054 }
                io.realm.RealmFieldType r1 = io.realm.RealmFieldType.BINARY     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                int[] r0 = $SwitchMap$io$realm$RealmFieldType     // Catch:{ NoSuchFieldError -> 0x0060 }
                io.realm.RealmFieldType r1 = io.realm.RealmFieldType.DATE     // Catch:{ NoSuchFieldError -> 0x0060 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0060 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0060 }
            L_0x0060:
                int[] r0 = $SwitchMap$io$realm$RealmFieldType     // Catch:{ NoSuchFieldError -> 0x006c }
                io.realm.RealmFieldType r1 = io.realm.RealmFieldType.FLOAT     // Catch:{ NoSuchFieldError -> 0x006c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x006c }
                r2 = 9
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x006c }
            L_0x006c:
                int[] r0 = $SwitchMap$io$realm$RealmFieldType     // Catch:{ NoSuchFieldError -> 0x0078 }
                io.realm.RealmFieldType r1 = io.realm.RealmFieldType.DOUBLE     // Catch:{ NoSuchFieldError -> 0x0078 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0078 }
                r2 = 10
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0078 }
            L_0x0078:
                int[] r0 = $SwitchMap$io$realm$RealmFieldType     // Catch:{ NoSuchFieldError -> 0x0084 }
                io.realm.RealmFieldType r1 = io.realm.RealmFieldType.INTEGER_LIST     // Catch:{ NoSuchFieldError -> 0x0084 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0084 }
                r2 = 11
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0084 }
            L_0x0084:
                int[] r0 = $SwitchMap$io$realm$RealmFieldType     // Catch:{ NoSuchFieldError -> 0x0090 }
                io.realm.RealmFieldType r1 = io.realm.RealmFieldType.BOOLEAN_LIST     // Catch:{ NoSuchFieldError -> 0x0090 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0090 }
                r2 = 12
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0090 }
            L_0x0090:
                int[] r0 = $SwitchMap$io$realm$RealmFieldType     // Catch:{ NoSuchFieldError -> 0x009c }
                io.realm.RealmFieldType r1 = io.realm.RealmFieldType.STRING_LIST     // Catch:{ NoSuchFieldError -> 0x009c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x009c }
                r2 = 13
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x009c }
            L_0x009c:
                int[] r0 = $SwitchMap$io$realm$RealmFieldType     // Catch:{ NoSuchFieldError -> 0x00a8 }
                io.realm.RealmFieldType r1 = io.realm.RealmFieldType.BINARY_LIST     // Catch:{ NoSuchFieldError -> 0x00a8 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00a8 }
                r2 = 14
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00a8 }
            L_0x00a8:
                int[] r0 = $SwitchMap$io$realm$RealmFieldType     // Catch:{ NoSuchFieldError -> 0x00b4 }
                io.realm.RealmFieldType r1 = io.realm.RealmFieldType.DATE_LIST     // Catch:{ NoSuchFieldError -> 0x00b4 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00b4 }
                r2 = 15
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00b4 }
            L_0x00b4:
                int[] r0 = $SwitchMap$io$realm$RealmFieldType     // Catch:{ NoSuchFieldError -> 0x00c0 }
                io.realm.RealmFieldType r1 = io.realm.RealmFieldType.FLOAT_LIST     // Catch:{ NoSuchFieldError -> 0x00c0 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00c0 }
                r2 = 16
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00c0 }
            L_0x00c0:
                int[] r0 = $SwitchMap$io$realm$RealmFieldType     // Catch:{ NoSuchFieldError -> 0x00cc }
                io.realm.RealmFieldType r1 = io.realm.RealmFieldType.DOUBLE_LIST     // Catch:{ NoSuchFieldError -> 0x00cc }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00cc }
                r2 = 17
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00cc }
            L_0x00cc:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: io.realm.internal.Property.AnonymousClass1.<clinit>():void");
        }
    }

    static int convertFromRealmFieldType(RealmFieldType realmFieldType, boolean z) {
        int i = 1;
        int i2 = 0;
        switch (AnonymousClass1.$SwitchMap$io$realm$RealmFieldType[realmFieldType.ordinal()]) {
            case 1:
                return 71;
            case 2:
                return 135;
            case 3:
                return SyslogAppender.LOG_LOCAL1;
            case 4:
                i = 0;
                break;
            case 5:
                break;
            case 6:
                i = 2;
                break;
            case 7:
                i = 3;
                break;
            case 8:
                i = 4;
                break;
            case 9:
                i = 5;
                break;
            case 10:
                i = 6;
                break;
            case 11:
                i = 128;
                break;
            case 12:
                i = TbsListener.ErrorCode.RENAME_NO_NEED_SENDREQUEST;
                break;
            case 13:
                i = 130;
                break;
            case 14:
                i = 131;
                break;
            case 15:
                i = 132;
                break;
            case 16:
                i = 133;
                break;
            case 17:
                i = 134;
                break;
            default:
                throw new IllegalArgumentException(String.format(Locale.US, "Unsupported filed type: '%s'.", new Object[]{realmFieldType.name()}));
        }
        if (!z) {
            i2 = 64;
        }
        return i | i2;
    }

    private static RealmFieldType convertToRealmFieldType(int i) {
        int i2 = i & -65;
        switch (i2) {
            case 0:
                return RealmFieldType.INTEGER;
            case 1:
                return RealmFieldType.BOOLEAN;
            case 2:
                return RealmFieldType.STRING;
            case 3:
                return RealmFieldType.BINARY;
            case 4:
                return RealmFieldType.DATE;
            case 5:
                return RealmFieldType.FLOAT;
            case 6:
                return RealmFieldType.DOUBLE;
            case 7:
                return RealmFieldType.OBJECT;
            default:
                switch (i2) {
                    case 128:
                        return RealmFieldType.INTEGER_LIST;
                    case TbsListener.ErrorCode.RENAME_NO_NEED_SENDREQUEST:
                        return RealmFieldType.BOOLEAN_LIST;
                    case 130:
                        return RealmFieldType.STRING_LIST;
                    case 131:
                        return RealmFieldType.BINARY_LIST;
                    case 132:
                        return RealmFieldType.DATE_LIST;
                    case 133:
                        return RealmFieldType.FLOAT_LIST;
                    case 134:
                        return RealmFieldType.DOUBLE_LIST;
                    case 135:
                        return RealmFieldType.LIST;
                    case SyslogAppender.LOG_LOCAL1 /*136*/:
                        return RealmFieldType.LINKING_OBJECTS;
                    default:
                        throw new IllegalArgumentException(String.format(Locale.US, "Unsupported property type: '%d'", new Object[]{Integer.valueOf(i)}));
                }
        }
    }

    public RealmFieldType getType() {
        return convertToRealmFieldType(nativeGetType(this.nativePtr));
    }

    public String getLinkedObjectName() {
        return nativeGetLinkedObjectName(this.nativePtr);
    }

    public long getColumnKey() {
        return nativeGetColumnKey(this.nativePtr);
    }

    public long getNativePtr() {
        return this.nativePtr;
    }

    public long getNativeFinalizerPtr() {
        return nativeFinalizerPtr;
    }
}
