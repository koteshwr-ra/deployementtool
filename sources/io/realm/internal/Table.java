package io.realm.internal;

import com.alibaba.android.arouter.utils.Consts;
import io.realm.RealmFieldType;
import io.realm.exceptions.RealmPrimaryKeyConstraintException;
import java.util.Date;
import javax.annotation.Nullable;
import org.apache.commons.lang3.StringUtils;

public class Table implements NativeObject {
    public static final int CLASS_NAME_MAX_LENGTH;
    public static final long INFINITE = -1;
    public static final int MAX_BINARY_SIZE = 16777200;
    public static final int MAX_STRING_SIZE = 16777199;
    public static final boolean NOT_NULLABLE = false;
    public static final int NO_MATCH = -1;
    public static final boolean NULLABLE = true;
    private static final int TABLE_NAME_MAX_LENGTH = 63;
    private static final String TABLE_PREFIX;
    private static final long nativeFinalizerPtr = nativeGetFinalizerPtr();
    private final NativeContext context;
    private final long nativeTableRefPtr;
    private final OsSharedRealm sharedRealm;

    private native long nativeAddColumn(long j, int i, String str, boolean z);

    private native long nativeAddColumnLink(long j, int i, String str, long j2);

    private native long nativeAddPrimitiveListColumn(long j, int i, String str, boolean z);

    private native void nativeAddSearchIndex(long j, long j2);

    private native void nativeClear(long j, boolean z);

    private native void nativeConvertColumnToNotNullable(long j, long j2, boolean z);

    private native void nativeConvertColumnToNullable(long j, long j2, boolean z);

    private native long nativeCountDouble(long j, long j2, double d);

    private native long nativeCountFloat(long j, long j2, float f);

    private native long nativeCountLong(long j, long j2, long j3);

    private native long nativeCountString(long j, long j2, String str);

    private native long nativeFindFirstBool(long j, long j2, boolean z);

    private native long nativeFindFirstDouble(long j, long j2, double d);

    private native long nativeFindFirstFloat(long j, long j2, float f);

    public static native long nativeFindFirstInt(long j, long j2, long j3);

    public static native long nativeFindFirstNull(long j, long j2);

    public static native long nativeFindFirstString(long j, long j2, String str);

    private native long nativeFindFirstTimestamp(long j, long j2, long j3);

    private static native long nativeFreeze(long j, long j2);

    private native boolean nativeGetBoolean(long j, long j2, long j3);

    private native byte[] nativeGetByteArray(long j, long j2, long j3);

    private native long nativeGetColumnCount(long j);

    private native long nativeGetColumnKey(long j, String str);

    private native String nativeGetColumnName(long j, long j2);

    private native String[] nativeGetColumnNames(long j);

    private native int nativeGetColumnType(long j, long j2);

    private native double nativeGetDouble(long j, long j2, long j3);

    private static native long nativeGetFinalizerPtr();

    private native float nativeGetFloat(long j, long j2, long j3);

    private native long nativeGetLink(long j, long j2, long j3);

    private native long nativeGetLinkTarget(long j, long j2);

    private native long nativeGetLong(long j, long j2, long j3);

    private native String nativeGetName(long j);

    private native String nativeGetString(long j, long j2, long j3);

    private native long nativeGetTimestamp(long j, long j2, long j3);

    private native boolean nativeHasSameSchema(long j, long j2);

    private native boolean nativeHasSearchIndex(long j, long j2);

    public static native void nativeIncrementLong(long j, long j2, long j3, long j4);

    private native boolean nativeIsColumnNullable(long j, long j2);

    private native boolean nativeIsNull(long j, long j2, long j3);

    private native boolean nativeIsNullLink(long j, long j2, long j3);

    private native boolean nativeIsValid(long j);

    private native void nativeMoveLastOver(long j, long j2);

    public static native void nativeNullifyLink(long j, long j2, long j3);

    private native void nativeRemoveColumn(long j, long j2);

    private native void nativeRemoveSearchIndex(long j, long j2);

    private native void nativeRenameColumn(long j, long j2, String str);

    public static native void nativeSetBoolean(long j, long j2, long j3, boolean z, boolean z2);

    public static native void nativeSetByteArray(long j, long j2, long j3, byte[] bArr, boolean z);

    public static native void nativeSetDouble(long j, long j2, long j3, double d, boolean z);

    public static native void nativeSetFloat(long j, long j2, long j3, float f, boolean z);

    public static native void nativeSetLink(long j, long j2, long j3, long j4, boolean z);

    public static native void nativeSetLong(long j, long j2, long j3, long j4, boolean z);

    public static native void nativeSetNull(long j, long j2, long j3, boolean z);

    public static native void nativeSetString(long j, long j2, long j3, String str, boolean z);

    public static native void nativeSetTimestamp(long j, long j2, long j3, long j4, boolean z);

    private native long nativeSize(long j);

    private native long nativeWhere(long j);

    public Table getTable() {
        return this;
    }

    /* access modifiers changed from: package-private */
    public native long nativeGetRowPtr(long j, long j2);

    static {
        String tablePrefix = Util.getTablePrefix();
        TABLE_PREFIX = tablePrefix;
        CLASS_NAME_MAX_LENGTH = 63 - tablePrefix.length();
    }

    Table(OsSharedRealm osSharedRealm, long j) {
        NativeContext nativeContext = osSharedRealm.context;
        this.context = nativeContext;
        this.sharedRealm = osSharedRealm;
        this.nativeTableRefPtr = j;
        nativeContext.addReference(this);
    }

    public long getNativePtr() {
        return this.nativeTableRefPtr;
    }

    public long getNativeFinalizerPtr() {
        return nativeFinalizerPtr;
    }

    public boolean isValid() {
        long j = this.nativeTableRefPtr;
        return j != 0 && nativeIsValid(j);
    }

    private void verifyColumnName(String str) {
        if (str.length() > 63) {
            throw new IllegalArgumentException("Column names are currently limited to max 63 characters.");
        }
    }

    /* renamed from: io.realm.internal.Table$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$io$realm$RealmFieldType;

        /* JADX WARNING: Can't wrap try/catch for region: R(28:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|26|(3:27|28|30)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(30:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|30) */
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
                io.realm.RealmFieldType r1 = io.realm.RealmFieldType.INTEGER     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$io$realm$RealmFieldType     // Catch:{ NoSuchFieldError -> 0x001d }
                io.realm.RealmFieldType r1 = io.realm.RealmFieldType.BOOLEAN     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$io$realm$RealmFieldType     // Catch:{ NoSuchFieldError -> 0x0028 }
                io.realm.RealmFieldType r1 = io.realm.RealmFieldType.STRING     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$io$realm$RealmFieldType     // Catch:{ NoSuchFieldError -> 0x0033 }
                io.realm.RealmFieldType r1 = io.realm.RealmFieldType.BINARY     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$io$realm$RealmFieldType     // Catch:{ NoSuchFieldError -> 0x003e }
                io.realm.RealmFieldType r1 = io.realm.RealmFieldType.DATE     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$io$realm$RealmFieldType     // Catch:{ NoSuchFieldError -> 0x0049 }
                io.realm.RealmFieldType r1 = io.realm.RealmFieldType.FLOAT     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = $SwitchMap$io$realm$RealmFieldType     // Catch:{ NoSuchFieldError -> 0x0054 }
                io.realm.RealmFieldType r1 = io.realm.RealmFieldType.DOUBLE     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                int[] r0 = $SwitchMap$io$realm$RealmFieldType     // Catch:{ NoSuchFieldError -> 0x0060 }
                io.realm.RealmFieldType r1 = io.realm.RealmFieldType.INTEGER_LIST     // Catch:{ NoSuchFieldError -> 0x0060 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0060 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0060 }
            L_0x0060:
                int[] r0 = $SwitchMap$io$realm$RealmFieldType     // Catch:{ NoSuchFieldError -> 0x006c }
                io.realm.RealmFieldType r1 = io.realm.RealmFieldType.BOOLEAN_LIST     // Catch:{ NoSuchFieldError -> 0x006c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x006c }
                r2 = 9
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x006c }
            L_0x006c:
                int[] r0 = $SwitchMap$io$realm$RealmFieldType     // Catch:{ NoSuchFieldError -> 0x0078 }
                io.realm.RealmFieldType r1 = io.realm.RealmFieldType.STRING_LIST     // Catch:{ NoSuchFieldError -> 0x0078 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0078 }
                r2 = 10
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0078 }
            L_0x0078:
                int[] r0 = $SwitchMap$io$realm$RealmFieldType     // Catch:{ NoSuchFieldError -> 0x0084 }
                io.realm.RealmFieldType r1 = io.realm.RealmFieldType.BINARY_LIST     // Catch:{ NoSuchFieldError -> 0x0084 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0084 }
                r2 = 11
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0084 }
            L_0x0084:
                int[] r0 = $SwitchMap$io$realm$RealmFieldType     // Catch:{ NoSuchFieldError -> 0x0090 }
                io.realm.RealmFieldType r1 = io.realm.RealmFieldType.DATE_LIST     // Catch:{ NoSuchFieldError -> 0x0090 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0090 }
                r2 = 12
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0090 }
            L_0x0090:
                int[] r0 = $SwitchMap$io$realm$RealmFieldType     // Catch:{ NoSuchFieldError -> 0x009c }
                io.realm.RealmFieldType r1 = io.realm.RealmFieldType.FLOAT_LIST     // Catch:{ NoSuchFieldError -> 0x009c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x009c }
                r2 = 13
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x009c }
            L_0x009c:
                int[] r0 = $SwitchMap$io$realm$RealmFieldType     // Catch:{ NoSuchFieldError -> 0x00a8 }
                io.realm.RealmFieldType r1 = io.realm.RealmFieldType.DOUBLE_LIST     // Catch:{ NoSuchFieldError -> 0x00a8 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00a8 }
                r2 = 14
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00a8 }
            L_0x00a8:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: io.realm.internal.Table.AnonymousClass1.<clinit>():void");
        }
    }

    public long addColumn(RealmFieldType realmFieldType, String str, boolean z) {
        verifyColumnName(str);
        switch (AnonymousClass1.$SwitchMap$io$realm$RealmFieldType[realmFieldType.ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
                return nativeAddColumn(this.nativeTableRefPtr, realmFieldType.getNativeValue(), str, z);
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
                return nativeAddPrimitiveListColumn(this.nativeTableRefPtr, realmFieldType.getNativeValue() - 128, str, z);
            default:
                throw new IllegalArgumentException("Unsupported type: " + realmFieldType);
        }
    }

    public long addColumn(RealmFieldType realmFieldType, String str) {
        return addColumn(realmFieldType, str, false);
    }

    public long addColumnLink(RealmFieldType realmFieldType, String str, Table table) {
        verifyColumnName(str);
        return nativeAddColumnLink(this.nativeTableRefPtr, realmFieldType.getNativeValue(), str, table.nativeTableRefPtr);
    }

    public void removeColumn(long j) {
        String className = getClassName();
        String columnName = getColumnName(j);
        String primaryKeyForObject = OsObjectStore.getPrimaryKeyForObject(this.sharedRealm, getClassName());
        nativeRemoveColumn(this.nativeTableRefPtr, j);
        if (columnName.equals(primaryKeyForObject)) {
            OsObjectStore.setPrimaryKeyForObject(this.sharedRealm, className, (String) null);
        }
    }

    public void renameColumn(long j, String str) {
        verifyColumnName(str);
        String nativeGetColumnName = nativeGetColumnName(this.nativeTableRefPtr, j);
        String primaryKeyForObject = OsObjectStore.getPrimaryKeyForObject(this.sharedRealm, getClassName());
        nativeRenameColumn(this.nativeTableRefPtr, j, str);
        if (nativeGetColumnName.equals(primaryKeyForObject)) {
            try {
                OsObjectStore.setPrimaryKeyForObject(this.sharedRealm, getClassName(), str);
            } catch (Exception e) {
                nativeRenameColumn(this.nativeTableRefPtr, j, nativeGetColumnName);
                throw new RuntimeException(e);
            }
        }
    }

    public boolean isColumnNullable(long j) {
        return nativeIsColumnNullable(this.nativeTableRefPtr, j);
    }

    public void convertColumnToNullable(long j) {
        if (!this.sharedRealm.isSyncRealm()) {
            nativeConvertColumnToNullable(this.nativeTableRefPtr, j, isPrimaryKey(j));
            return;
        }
        throw new IllegalStateException("This method is only available for non-synchronized Realms");
    }

    public void convertColumnToNotNullable(long j) {
        if (!this.sharedRealm.isSyncRealm()) {
            nativeConvertColumnToNotNullable(this.nativeTableRefPtr, j, isPrimaryKey(j));
            return;
        }
        throw new IllegalStateException("This method is only available for non-synchronized Realms");
    }

    public long size() {
        return nativeSize(this.nativeTableRefPtr);
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public void clear(boolean z) {
        checkImmutable();
        nativeClear(this.nativeTableRefPtr, z);
    }

    public long getColumnCount() {
        return nativeGetColumnCount(this.nativeTableRefPtr);
    }

    public String getColumnName(long j) {
        return nativeGetColumnName(this.nativeTableRefPtr, j);
    }

    public String[] getColumnNames() {
        return nativeGetColumnNames(this.nativeTableRefPtr);
    }

    public long getColumnKey(String str) {
        if (str != null) {
            return nativeGetColumnKey(this.nativeTableRefPtr, str);
        }
        throw new IllegalArgumentException("Column name can not be null.");
    }

    public RealmFieldType getColumnType(long j) {
        return RealmFieldType.fromNativeValue(nativeGetColumnType(this.nativeTableRefPtr, j));
    }

    public void moveLastOver(long j) {
        checkImmutable();
        nativeMoveLastOver(this.nativeTableRefPtr, j);
    }

    private boolean isPrimaryKey(long j) {
        return getColumnName(j).equals(OsObjectStore.getPrimaryKeyForObject(this.sharedRealm, getClassName()));
    }

    public static void throwDuplicatePrimaryKeyException(Object obj) {
        throw new RealmPrimaryKeyConstraintException("Value already exists: " + obj);
    }

    public OsSharedRealm getSharedRealm() {
        return this.sharedRealm;
    }

    public long getLong(long j, long j2) {
        return nativeGetLong(this.nativeTableRefPtr, j, j2);
    }

    public boolean getBoolean(long j, long j2) {
        return nativeGetBoolean(this.nativeTableRefPtr, j, j2);
    }

    public float getFloat(long j, long j2) {
        return nativeGetFloat(this.nativeTableRefPtr, j, j2);
    }

    public double getDouble(long j, long j2) {
        return nativeGetDouble(this.nativeTableRefPtr, j, j2);
    }

    public Date getDate(long j, long j2) {
        return new Date(nativeGetTimestamp(this.nativeTableRefPtr, j, j2));
    }

    public String getString(long j, long j2) {
        return nativeGetString(this.nativeTableRefPtr, j, j2);
    }

    public byte[] getBinaryByteArray(long j, long j2) {
        return nativeGetByteArray(this.nativeTableRefPtr, j, j2);
    }

    public long getLink(long j, long j2) {
        return nativeGetLink(this.nativeTableRefPtr, j, j2);
    }

    public Table getLinkTarget(long j) {
        return new Table(this.sharedRealm, nativeGetLinkTarget(this.nativeTableRefPtr, j));
    }

    public UncheckedRow getUncheckedRow(long j) {
        return UncheckedRow.getByRowKey(this.context, this, j);
    }

    public UncheckedRow getUncheckedRowByPointer(long j) {
        return UncheckedRow.getByRowPointer(this.context, this, j);
    }

    public CheckedRow getCheckedRow(long j) {
        return CheckedRow.get(this.context, this, j);
    }

    public void setLong(long j, long j2, long j3, boolean z) {
        checkImmutable();
        nativeSetLong(this.nativeTableRefPtr, j, j2, j3, z);
    }

    public void incrementLong(long j, long j2, long j3) {
        checkImmutable();
        nativeIncrementLong(this.nativeTableRefPtr, j, j2, j3);
    }

    public void setBoolean(long j, long j2, boolean z, boolean z2) {
        checkImmutable();
        nativeSetBoolean(this.nativeTableRefPtr, j, j2, z, z2);
    }

    public void setFloat(long j, long j2, float f, boolean z) {
        checkImmutable();
        nativeSetFloat(this.nativeTableRefPtr, j, j2, f, z);
    }

    public void setDouble(long j, long j2, double d, boolean z) {
        checkImmutable();
        nativeSetDouble(this.nativeTableRefPtr, j, j2, d, z);
    }

    public void setDate(long j, long j2, Date date, boolean z) {
        if (date != null) {
            checkImmutable();
            nativeSetTimestamp(this.nativeTableRefPtr, j, j2, date.getTime(), z);
            return;
        }
        throw new IllegalArgumentException("Null Date is not allowed.");
    }

    public void setString(long j, long j2, @Nullable String str, boolean z) {
        checkImmutable();
        if (str == null) {
            nativeSetNull(this.nativeTableRefPtr, j, j2, z);
        } else {
            nativeSetString(this.nativeTableRefPtr, j, j2, str, z);
        }
    }

    public void setBinaryByteArray(long j, long j2, byte[] bArr, boolean z) {
        checkImmutable();
        nativeSetByteArray(this.nativeTableRefPtr, j, j2, bArr, z);
    }

    public void setLink(long j, long j2, long j3, boolean z) {
        checkImmutable();
        nativeSetLink(this.nativeTableRefPtr, j, j2, j3, z);
    }

    public void setNull(long j, long j2, boolean z) {
        checkImmutable();
        nativeSetNull(this.nativeTableRefPtr, j, j2, z);
    }

    public void addSearchIndex(long j) {
        checkImmutable();
        nativeAddSearchIndex(this.nativeTableRefPtr, j);
    }

    public void removeSearchIndex(long j) {
        checkImmutable();
        nativeRemoveSearchIndex(this.nativeTableRefPtr, j);
    }

    public boolean hasSearchIndex(long j) {
        return nativeHasSearchIndex(this.nativeTableRefPtr, j);
    }

    public boolean isNullLink(long j, long j2) {
        return nativeIsNullLink(this.nativeTableRefPtr, j, j2);
    }

    public void nullifyLink(long j, long j2) {
        nativeNullifyLink(this.nativeTableRefPtr, j, j2);
    }

    /* access modifiers changed from: package-private */
    public boolean isImmutable() {
        OsSharedRealm osSharedRealm = this.sharedRealm;
        return osSharedRealm != null && !osSharedRealm.isInTransaction();
    }

    /* access modifiers changed from: package-private */
    public void checkImmutable() {
        if (isImmutable()) {
            throwImmutable();
        }
    }

    public long count(long j, long j2) {
        return nativeCountLong(this.nativeTableRefPtr, j, j2);
    }

    public long count(long j, float f) {
        return nativeCountFloat(this.nativeTableRefPtr, j, f);
    }

    public long count(long j, double d) {
        return nativeCountDouble(this.nativeTableRefPtr, j, d);
    }

    public long count(long j, String str) {
        return nativeCountString(this.nativeTableRefPtr, j, str);
    }

    public TableQuery where() {
        return new TableQuery(this.context, this, nativeWhere(this.nativeTableRefPtr));
    }

    public long findFirstLong(long j, long j2) {
        return nativeFindFirstInt(this.nativeTableRefPtr, j, j2);
    }

    public long findFirstBoolean(long j, boolean z) {
        return nativeFindFirstBool(this.nativeTableRefPtr, j, z);
    }

    public long findFirstFloat(long j, float f) {
        return nativeFindFirstFloat(this.nativeTableRefPtr, j, f);
    }

    public long findFirstDouble(long j, double d) {
        return nativeFindFirstDouble(this.nativeTableRefPtr, j, d);
    }

    public long findFirstDate(long j, Date date) {
        if (date != null) {
            return nativeFindFirstTimestamp(this.nativeTableRefPtr, j, date.getTime());
        }
        throw new IllegalArgumentException("null is not supported");
    }

    public long findFirstString(long j, String str) {
        if (str != null) {
            return nativeFindFirstString(this.nativeTableRefPtr, j, str);
        }
        throw new IllegalArgumentException("null is not supported");
    }

    public long findFirstNull(long j) {
        return nativeFindFirstNull(this.nativeTableRefPtr, j);
    }

    @Nullable
    public String getName() {
        return nativeGetName(this.nativeTableRefPtr);
    }

    public String getClassName() {
        String classNameForTable = getClassNameForTable(getName());
        if (!Util.isEmptyString(classNameForTable)) {
            return classNameForTable;
        }
        throw new IllegalStateException("This object class is no longer part of the schema for the Realm file. It is therefor not possible to access the schema name.");
    }

    public String toString() {
        long columnCount = getColumnCount();
        String name = getName();
        StringBuilder sb = new StringBuilder("The Table ");
        if (name != null && !name.isEmpty()) {
            sb.append(getName());
            sb.append(StringUtils.SPACE);
        }
        sb.append("contains ");
        sb.append(columnCount);
        sb.append(" columns: ");
        String[] columnNames = getColumnNames();
        int length = columnNames.length;
        boolean z = true;
        int i = 0;
        while (i < length) {
            String str = columnNames[i];
            if (!z) {
                sb.append(", ");
            }
            sb.append(str);
            i++;
            z = false;
        }
        sb.append(Consts.DOT);
        sb.append(" And ");
        sb.append(size());
        sb.append(" rows.");
        return sb.toString();
    }

    private static void throwImmutable() {
        throw new IllegalStateException("Cannot modify managed objects outside of a write transaction.");
    }

    public boolean hasSameSchema(Table table) {
        if (table != null) {
            return nativeHasSameSchema(this.nativeTableRefPtr, table.nativeTableRefPtr);
        }
        throw new IllegalArgumentException("The argument cannot be null");
    }

    public Table freeze(OsSharedRealm osSharedRealm) {
        if (osSharedRealm.isFrozen()) {
            return new Table(osSharedRealm, nativeFreeze(osSharedRealm.getNativePtr(), this.nativeTableRefPtr));
        }
        throw new IllegalArgumentException("Frozen Realm required");
    }

    @Nullable
    public static String getClassNameForTable(@Nullable String str) {
        if (str == null) {
            return null;
        }
        if (!str.startsWith(TABLE_PREFIX)) {
            return str;
        }
        return str.substring(TABLE_PREFIX.length());
    }

    public static String getTableNameForClass(String str) {
        if (str == null) {
            return null;
        }
        return TABLE_PREFIX + str;
    }
}
