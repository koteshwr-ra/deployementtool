package org.apache.log4j.lf5.viewer;

import io.realm.com_ciot_realm_db_patrol_MessageRealmProxy;
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LogTableColumn implements Serializable {
    public static final LogTableColumn CATEGORY = new LogTableColumn("Category");
    public static final LogTableColumn DATE = new LogTableColumn("Date");
    public static final LogTableColumn LEVEL = new LogTableColumn("Level");
    public static final LogTableColumn LOCATION = new LogTableColumn("Location");
    public static final LogTableColumn MESSAGE = new LogTableColumn(com_ciot_realm_db_patrol_MessageRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME);
    public static final LogTableColumn MESSAGE_NUM = new LogTableColumn("Message #");
    public static final LogTableColumn NDC = new LogTableColumn("NDC");
    public static final LogTableColumn THREAD = new LogTableColumn("Thread");
    public static final LogTableColumn THROWN;
    private static LogTableColumn[] _log4JColumns;
    private static Map _logTableColumnMap = new HashMap();
    protected String _label;

    static {
        LogTableColumn logTableColumn = new LogTableColumn("Thrown");
        THROWN = logTableColumn;
        int i = 0;
        _log4JColumns = new LogTableColumn[]{DATE, THREAD, MESSAGE_NUM, LEVEL, NDC, CATEGORY, MESSAGE, LOCATION, logTableColumn};
        while (true) {
            LogTableColumn[] logTableColumnArr = _log4JColumns;
            if (i < logTableColumnArr.length) {
                _logTableColumnMap.put(logTableColumnArr[i].getLabel(), _log4JColumns[i]);
                i++;
            } else {
                return;
            }
        }
    }

    public LogTableColumn(String str) {
        this._label = str;
    }

    public String getLabel() {
        return this._label;
    }

    public static LogTableColumn valueOf(String str) throws LogTableColumnFormatException {
        LogTableColumn logTableColumn;
        if (str != null) {
            str = str.trim();
            logTableColumn = (LogTableColumn) _logTableColumnMap.get(str);
        } else {
            logTableColumn = null;
        }
        if (logTableColumn != null) {
            return logTableColumn;
        }
        StringBuffer stringBuffer = new StringBuffer();
        StringBuffer stringBuffer2 = new StringBuffer();
        stringBuffer2.append("Error while trying to parse (");
        stringBuffer2.append(str);
        stringBuffer2.append(") into");
        stringBuffer.append(stringBuffer2.toString());
        stringBuffer.append(" a LogTableColumn.");
        throw new LogTableColumnFormatException(stringBuffer.toString());
    }

    public boolean equals(Object obj) {
        return (obj instanceof LogTableColumn) && getLabel() == ((LogTableColumn) obj).getLabel();
    }

    public int hashCode() {
        return this._label.hashCode();
    }

    public String toString() {
        return this._label;
    }

    public static List getLogTableColumns() {
        return Arrays.asList(_log4JColumns);
    }

    public static LogTableColumn[] getLogTableColumnArray() {
        return _log4JColumns;
    }
}
