package io.realm;

import io.realm.internal.InvalidRow;
import io.realm.internal.OsList;
import io.realm.internal.OsSharedRealm;
import io.realm.internal.Row;
import io.realm.internal.Table;
import java.util.Date;

public enum FrozenPendingRow implements Row {
    INSTANCE;
    
    private static final String QUERY_NOT_RETURNED_MESSAGE = "This object was frozen while a query for it was still running.";

    public boolean isLoaded() {
        return false;
    }

    public boolean isValid() {
        return false;
    }

    public long getColumnCount() {
        throw new IllegalStateException(QUERY_NOT_RETURNED_MESSAGE);
    }

    public String[] getColumnNames() {
        throw new IllegalStateException(QUERY_NOT_RETURNED_MESSAGE);
    }

    public long getColumnKey(String str) {
        throw new IllegalStateException(QUERY_NOT_RETURNED_MESSAGE);
    }

    public RealmFieldType getColumnType(long j) {
        throw new IllegalStateException(QUERY_NOT_RETURNED_MESSAGE);
    }

    public Table getTable() {
        throw new IllegalStateException(QUERY_NOT_RETURNED_MESSAGE);
    }

    public long getObjectKey() {
        throw new IllegalStateException(QUERY_NOT_RETURNED_MESSAGE);
    }

    public long getLong(long j) {
        throw new IllegalStateException(QUERY_NOT_RETURNED_MESSAGE);
    }

    public boolean getBoolean(long j) {
        throw new IllegalStateException(QUERY_NOT_RETURNED_MESSAGE);
    }

    public float getFloat(long j) {
        throw new IllegalStateException(QUERY_NOT_RETURNED_MESSAGE);
    }

    public double getDouble(long j) {
        throw new IllegalStateException(QUERY_NOT_RETURNED_MESSAGE);
    }

    public Date getDate(long j) {
        throw new IllegalStateException(QUERY_NOT_RETURNED_MESSAGE);
    }

    public String getString(long j) {
        throw new IllegalStateException(QUERY_NOT_RETURNED_MESSAGE);
    }

    public byte[] getBinaryByteArray(long j) {
        throw new IllegalStateException(QUERY_NOT_RETURNED_MESSAGE);
    }

    public long getLink(long j) {
        throw new IllegalStateException(QUERY_NOT_RETURNED_MESSAGE);
    }

    public boolean isNullLink(long j) {
        throw new IllegalStateException(QUERY_NOT_RETURNED_MESSAGE);
    }

    public OsList getModelList(long j) {
        throw new IllegalStateException(QUERY_NOT_RETURNED_MESSAGE);
    }

    public OsList getValueList(long j, RealmFieldType realmFieldType) {
        throw new IllegalStateException(QUERY_NOT_RETURNED_MESSAGE);
    }

    public void setLong(long j, long j2) {
        throw new IllegalStateException(QUERY_NOT_RETURNED_MESSAGE);
    }

    public void setBoolean(long j, boolean z) {
        throw new IllegalStateException(QUERY_NOT_RETURNED_MESSAGE);
    }

    public void setFloat(long j, float f) {
        throw new IllegalStateException(QUERY_NOT_RETURNED_MESSAGE);
    }

    public void setDouble(long j, double d) {
        throw new IllegalStateException(QUERY_NOT_RETURNED_MESSAGE);
    }

    public void setDate(long j, Date date) {
        throw new IllegalStateException(QUERY_NOT_RETURNED_MESSAGE);
    }

    public void setString(long j, String str) {
        throw new IllegalStateException(QUERY_NOT_RETURNED_MESSAGE);
    }

    public void setBinaryByteArray(long j, byte[] bArr) {
        throw new IllegalStateException(QUERY_NOT_RETURNED_MESSAGE);
    }

    public void setLink(long j, long j2) {
        throw new IllegalStateException(QUERY_NOT_RETURNED_MESSAGE);
    }

    public void nullifyLink(long j) {
        throw new IllegalStateException(QUERY_NOT_RETURNED_MESSAGE);
    }

    public boolean isNull(long j) {
        throw new IllegalStateException(QUERY_NOT_RETURNED_MESSAGE);
    }

    public void setNull(long j) {
        throw new IllegalStateException(QUERY_NOT_RETURNED_MESSAGE);
    }

    public void checkIfAttached() {
        throw new IllegalStateException(QUERY_NOT_RETURNED_MESSAGE);
    }

    public boolean hasColumn(String str) {
        throw new IllegalStateException(QUERY_NOT_RETURNED_MESSAGE);
    }

    public Row freeze(OsSharedRealm osSharedRealm) {
        return InvalidRow.INSTANCE;
    }
}
