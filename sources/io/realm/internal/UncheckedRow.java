package io.realm.internal;

import io.realm.RealmFieldType;
import java.util.Date;
import javax.annotation.Nullable;

public class UncheckedRow implements NativeObject, Row {
    private static final long nativeFinalizerPtr = nativeGetFinalizerPtr();
    protected final NativeContext context;
    private final long nativePtr;
    protected final Table parent;

    private static native long nativeGetFinalizerPtr();

    public boolean isLoaded() {
        return true;
    }

    /* access modifiers changed from: protected */
    public native long nativeFreeze(long j, long j2);

    /* access modifiers changed from: protected */
    public native boolean nativeGetBoolean(long j, long j2);

    /* access modifiers changed from: protected */
    public native byte[] nativeGetByteArray(long j, long j2);

    /* access modifiers changed from: protected */
    public native long nativeGetColumnCount(long j);

    /* access modifiers changed from: protected */
    public native long nativeGetColumnKey(long j, String str);

    /* access modifiers changed from: protected */
    public native String[] nativeGetColumnNames(long j);

    /* access modifiers changed from: protected */
    public native int nativeGetColumnType(long j, long j2);

    /* access modifiers changed from: protected */
    public native double nativeGetDouble(long j, long j2);

    /* access modifiers changed from: protected */
    public native float nativeGetFloat(long j, long j2);

    /* access modifiers changed from: protected */
    public native long nativeGetLink(long j, long j2);

    /* access modifiers changed from: protected */
    public native long nativeGetLong(long j, long j2);

    /* access modifiers changed from: protected */
    public native long nativeGetObjectKey(long j);

    /* access modifiers changed from: protected */
    public native String nativeGetString(long j, long j2);

    /* access modifiers changed from: protected */
    public native long nativeGetTimestamp(long j, long j2);

    /* access modifiers changed from: protected */
    public native boolean nativeHasColumn(long j, String str);

    /* access modifiers changed from: protected */
    public native boolean nativeIsNull(long j, long j2);

    /* access modifiers changed from: protected */
    public native boolean nativeIsNullLink(long j, long j2);

    /* access modifiers changed from: protected */
    public native boolean nativeIsValid(long j);

    /* access modifiers changed from: protected */
    public native void nativeNullifyLink(long j, long j2);

    /* access modifiers changed from: protected */
    public native void nativeSetBoolean(long j, long j2, boolean z);

    /* access modifiers changed from: protected */
    public native void nativeSetByteArray(long j, long j2, @Nullable byte[] bArr);

    /* access modifiers changed from: protected */
    public native void nativeSetDouble(long j, long j2, double d);

    /* access modifiers changed from: protected */
    public native void nativeSetFloat(long j, long j2, float f);

    /* access modifiers changed from: protected */
    public native void nativeSetLink(long j, long j2, long j3);

    /* access modifiers changed from: protected */
    public native void nativeSetLong(long j, long j2, long j3);

    /* access modifiers changed from: protected */
    public native void nativeSetNull(long j, long j2);

    /* access modifiers changed from: protected */
    public native void nativeSetString(long j, long j2, String str);

    /* access modifiers changed from: protected */
    public native void nativeSetTimestamp(long j, long j2, long j3);

    public UncheckedRow(NativeContext nativeContext, Table table, long j) {
        this.context = nativeContext;
        this.parent = table;
        this.nativePtr = j;
        nativeContext.addReference(this);
    }

    UncheckedRow(UncheckedRow uncheckedRow) {
        this.context = uncheckedRow.context;
        this.parent = uncheckedRow.parent;
        this.nativePtr = uncheckedRow.nativePtr;
    }

    public long getNativePtr() {
        return this.nativePtr;
    }

    public long getNativeFinalizerPtr() {
        return nativeFinalizerPtr;
    }

    static UncheckedRow getByRowKey(NativeContext nativeContext, Table table, long j) {
        return new UncheckedRow(nativeContext, table, table.nativeGetRowPtr(table.getNativePtr(), j));
    }

    static UncheckedRow getByRowPointer(NativeContext nativeContext, Table table, long j) {
        return new UncheckedRow(nativeContext, table, j);
    }

    public long getColumnCount() {
        return nativeGetColumnCount(this.nativePtr);
    }

    public String[] getColumnNames() {
        return nativeGetColumnNames(this.nativePtr);
    }

    public long getColumnKey(String str) {
        if (str != null) {
            return nativeGetColumnKey(this.nativePtr, str);
        }
        throw new IllegalArgumentException("Column name can not be null.");
    }

    public RealmFieldType getColumnType(long j) {
        return RealmFieldType.fromNativeValue(nativeGetColumnType(this.nativePtr, j));
    }

    public Table getTable() {
        return this.parent;
    }

    public long getObjectKey() {
        return nativeGetObjectKey(this.nativePtr);
    }

    public long getLong(long j) {
        return nativeGetLong(this.nativePtr, j);
    }

    public boolean getBoolean(long j) {
        return nativeGetBoolean(this.nativePtr, j);
    }

    public float getFloat(long j) {
        return nativeGetFloat(this.nativePtr, j);
    }

    public double getDouble(long j) {
        return nativeGetDouble(this.nativePtr, j);
    }

    public Date getDate(long j) {
        return new Date(nativeGetTimestamp(this.nativePtr, j));
    }

    public String getString(long j) {
        return nativeGetString(this.nativePtr, j);
    }

    public byte[] getBinaryByteArray(long j) {
        return nativeGetByteArray(this.nativePtr, j);
    }

    public long getLink(long j) {
        return nativeGetLink(this.nativePtr, j);
    }

    public boolean isNullLink(long j) {
        return nativeIsNullLink(this.nativePtr, j);
    }

    public OsList getModelList(long j) {
        return new OsList(this, j);
    }

    public OsList getValueList(long j, RealmFieldType realmFieldType) {
        return new OsList(this, j);
    }

    public void setLong(long j, long j2) {
        this.parent.checkImmutable();
        nativeSetLong(this.nativePtr, j, j2);
    }

    public void setBoolean(long j, boolean z) {
        this.parent.checkImmutable();
        nativeSetBoolean(this.nativePtr, j, z);
    }

    public void setFloat(long j, float f) {
        this.parent.checkImmutable();
        nativeSetFloat(this.nativePtr, j, f);
    }

    public void setDouble(long j, double d) {
        this.parent.checkImmutable();
        nativeSetDouble(this.nativePtr, j, d);
    }

    public void setDate(long j, Date date) {
        this.parent.checkImmutable();
        if (date != null) {
            nativeSetTimestamp(this.nativePtr, j, date.getTime());
            return;
        }
        throw new IllegalArgumentException("Null Date is not allowed.");
    }

    public void setString(long j, @Nullable String str) {
        this.parent.checkImmutable();
        if (str == null) {
            nativeSetNull(this.nativePtr, j);
            return;
        }
        nativeSetString(this.nativePtr, j, str);
    }

    public void setBinaryByteArray(long j, @Nullable byte[] bArr) {
        this.parent.checkImmutable();
        nativeSetByteArray(this.nativePtr, j, bArr);
    }

    public void setLink(long j, long j2) {
        this.parent.checkImmutable();
        nativeSetLink(this.nativePtr, j, j2);
    }

    public void nullifyLink(long j) {
        this.parent.checkImmutable();
        nativeNullifyLink(this.nativePtr, j);
    }

    public boolean isNull(long j) {
        return nativeIsNull(this.nativePtr, j);
    }

    public void setNull(long j) {
        this.parent.checkImmutable();
        nativeSetNull(this.nativePtr, j);
    }

    public CheckedRow convertToChecked() {
        return CheckedRow.getFromRow(this);
    }

    public boolean isValid() {
        long j = this.nativePtr;
        return j != 0 && nativeIsValid(j);
    }

    public void checkIfAttached() {
        if (!isValid()) {
            throw new IllegalStateException("Object is no longer managed by Realm. Has it been deleted?");
        }
    }

    public boolean hasColumn(String str) {
        return nativeHasColumn(this.nativePtr, str);
    }

    public Row freeze(OsSharedRealm osSharedRealm) {
        if (!isValid()) {
            return InvalidRow.INSTANCE;
        }
        return new UncheckedRow(this.context, this.parent.freeze(osSharedRealm), nativeFreeze(this.nativePtr, osSharedRealm.getNativePtr()));
    }
}
