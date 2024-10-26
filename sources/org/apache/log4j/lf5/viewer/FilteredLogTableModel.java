package org.apache.log4j.lf5.viewer;

import io.realm.com_ciot_realm_db_patrol_MessageRealmProxy;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import org.apache.log4j.lf5.LogRecord;
import org.apache.log4j.lf5.LogRecordFilter;
import org.apache.log4j.lf5.PassingLogRecordFilter;

public class FilteredLogTableModel extends AbstractTableModel {
    protected List _allRecords = new ArrayList();
    protected String[] _colNames = {"Date", "Thread", "Message #", "Level", "NDC", "Category", com_ciot_realm_db_patrol_MessageRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME, "Location", "Thrown"};
    protected LogRecordFilter _filter = new PassingLogRecordFilter();
    protected List _filteredRecords;
    protected int _maxNumberOfLogRecords = 5000;

    public void setLogRecordFilter(LogRecordFilter logRecordFilter) {
        this._filter = logRecordFilter;
    }

    public LogRecordFilter getLogRecordFilter() {
        return this._filter;
    }

    public String getColumnName(int i) {
        return this._colNames[i];
    }

    public int getColumnCount() {
        return this._colNames.length;
    }

    public int getRowCount() {
        return getFilteredRecords().size();
    }

    public int getTotalRowCount() {
        return this._allRecords.size();
    }

    public Object getValueAt(int i, int i2) {
        return getColumn(i2, getFilteredRecord(i));
    }

    public void setMaxNumberOfLogRecords(int i) {
        if (i > 0) {
            this._maxNumberOfLogRecords = i;
        }
    }

    public synchronized boolean addLogRecord(LogRecord logRecord) {
        this._allRecords.add(logRecord);
        if (!this._filter.passes(logRecord)) {
            return false;
        }
        getFilteredRecords().add(logRecord);
        fireTableRowsInserted(getRowCount(), getRowCount());
        trimRecords();
        return true;
    }

    public synchronized void refresh() {
        this._filteredRecords = createFilteredRecordsList();
        fireTableDataChanged();
    }

    public synchronized void fastRefresh() {
        this._filteredRecords.remove(0);
        fireTableRowsDeleted(0, 0);
    }

    public synchronized void clear() {
        this._allRecords.clear();
        this._filteredRecords.clear();
        fireTableDataChanged();
    }

    /* access modifiers changed from: protected */
    public List getFilteredRecords() {
        if (this._filteredRecords == null) {
            refresh();
        }
        return this._filteredRecords;
    }

    /* access modifiers changed from: protected */
    public List createFilteredRecordsList() {
        ArrayList arrayList = new ArrayList();
        for (LogRecord logRecord : this._allRecords) {
            if (this._filter.passes(logRecord)) {
                arrayList.add(logRecord);
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: protected */
    public LogRecord getFilteredRecord(int i) {
        List filteredRecords = getFilteredRecords();
        int size = filteredRecords.size();
        if (i < size) {
            return (LogRecord) filteredRecords.get(i);
        }
        return (LogRecord) filteredRecords.get(size - 1);
    }

    /* access modifiers changed from: protected */
    public Object getColumn(int i, LogRecord logRecord) {
        if (logRecord == null) {
            return "NULL Column";
        }
        String date = new Date(logRecord.getMillis()).toString();
        switch (i) {
            case 0:
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(date);
                stringBuffer.append(" (");
                stringBuffer.append(logRecord.getMillis());
                stringBuffer.append(")");
                return stringBuffer.toString();
            case 1:
                return logRecord.getThreadDescription();
            case 2:
                return new Long(logRecord.getSequenceNumber());
            case 3:
                return logRecord.getLevel();
            case 4:
                return logRecord.getNDC();
            case 5:
                return logRecord.getCategory();
            case 6:
                return logRecord.getMessage();
            case 7:
                return logRecord.getLocation();
            case 8:
                return logRecord.getThrownStackTrace();
            default:
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append("The column number ");
                stringBuffer2.append(i);
                stringBuffer2.append("must be between 0 and 8");
                throw new IllegalArgumentException(stringBuffer2.toString());
        }
    }

    /* access modifiers changed from: protected */
    public void trimRecords() {
        if (needsTrimming()) {
            trimOldestRecords();
        }
    }

    /* access modifiers changed from: protected */
    public boolean needsTrimming() {
        return this._allRecords.size() > this._maxNumberOfLogRecords;
    }

    /* access modifiers changed from: protected */
    public void trimOldestRecords() {
        synchronized (this._allRecords) {
            int numberOfRecordsToTrim = numberOfRecordsToTrim();
            if (numberOfRecordsToTrim > 1) {
                this._allRecords.subList(0, numberOfRecordsToTrim).clear();
                refresh();
            } else {
                this._allRecords.remove(0);
                fastRefresh();
            }
        }
    }

    private int numberOfRecordsToTrim() {
        return this._allRecords.size() - this._maxNumberOfLogRecords;
    }
}
