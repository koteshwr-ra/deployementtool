package org.apache.log4j.lf5;

import java.awt.Color;
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class LogLevel implements Serializable {
    public static final LogLevel CONFIG = new LogLevel("CONFIG", 4);
    public static final LogLevel DEBUG = new LogLevel("DEBUG", 4);
    public static final LogLevel ERROR = new LogLevel("ERROR", 1);
    public static final LogLevel FATAL = new LogLevel("FATAL", 0);
    public static final LogLevel FINE = new LogLevel("FINE", 5);
    public static final LogLevel FINER = new LogLevel("FINER", 6);
    public static final LogLevel FINEST = new LogLevel("FINEST", 7);
    public static final LogLevel INFO = new LogLevel("INFO", 3);
    public static final LogLevel SEVERE = new LogLevel("SEVERE", 1);
    public static final LogLevel WARN = new LogLevel("WARN", 2);
    public static final LogLevel WARNING = new LogLevel("WARNING", 2);
    private static LogLevel[] _allDefaultLevels;
    private static LogLevel[] _jdk14Levels;
    private static LogLevel[] _log4JLevels;
    private static Map _logLevelColorMap = new HashMap();
    private static Map _logLevelMap = new HashMap();
    private static Map _registeredLogLevelMap = new HashMap();
    protected String _label;
    protected int _precedence;

    static {
        int i = 0;
        LogLevel logLevel = FATAL;
        LogLevel logLevel2 = ERROR;
        LogLevel logLevel3 = WARN;
        LogLevel logLevel4 = INFO;
        LogLevel logLevel5 = DEBUG;
        _log4JLevels = new LogLevel[]{logLevel, logLevel2, logLevel3, logLevel4, logLevel5};
        LogLevel logLevel6 = SEVERE;
        LogLevel logLevel7 = WARNING;
        LogLevel logLevel8 = CONFIG;
        LogLevel logLevel9 = FINE;
        LogLevel logLevel10 = FINER;
        LogLevel logLevel11 = FINEST;
        _jdk14Levels = new LogLevel[]{logLevel6, logLevel7, logLevel4, logLevel8, logLevel9, logLevel10, logLevel11};
        _allDefaultLevels = new LogLevel[]{logLevel, logLevel2, logLevel3, logLevel4, logLevel5, logLevel6, logLevel7, logLevel8, logLevel9, logLevel10, logLevel11};
        int i2 = 0;
        while (true) {
            LogLevel[] logLevelArr = _allDefaultLevels;
            if (i2 >= logLevelArr.length) {
                break;
            }
            _logLevelMap.put(logLevelArr[i2].getLabel(), _allDefaultLevels[i2]);
            i2++;
        }
        while (true) {
            LogLevel[] logLevelArr2 = _allDefaultLevels;
            if (i < logLevelArr2.length) {
                _logLevelColorMap.put(logLevelArr2[i], Color.black);
                i++;
            } else {
                return;
            }
        }
    }

    public LogLevel(String str, int i) {
        this._label = str;
        this._precedence = i;
    }

    public String getLabel() {
        return this._label;
    }

    public boolean encompasses(LogLevel logLevel) {
        return logLevel.getPrecedence() <= getPrecedence();
    }

    public static LogLevel valueOf(String str) throws LogLevelFormatException {
        LogLevel logLevel;
        if (str != null) {
            str = str.trim().toUpperCase();
            logLevel = (LogLevel) _logLevelMap.get(str);
        } else {
            logLevel = null;
        }
        if (logLevel == null && _registeredLogLevelMap.size() > 0) {
            logLevel = (LogLevel) _registeredLogLevelMap.get(str);
        }
        if (logLevel != null) {
            return logLevel;
        }
        StringBuffer stringBuffer = new StringBuffer();
        StringBuffer stringBuffer2 = new StringBuffer();
        stringBuffer2.append("Error while trying to parse (");
        stringBuffer2.append(str);
        stringBuffer2.append(") into");
        stringBuffer.append(stringBuffer2.toString());
        stringBuffer.append(" a LogLevel.");
        throw new LogLevelFormatException(stringBuffer.toString());
    }

    public static LogLevel register(LogLevel logLevel) {
        if (logLevel != null && _logLevelMap.get(logLevel.getLabel()) == null) {
            return (LogLevel) _registeredLogLevelMap.put(logLevel.getLabel(), logLevel);
        }
        return null;
    }

    public static void register(LogLevel[] logLevelArr) {
        if (logLevelArr != null) {
            for (LogLevel register : logLevelArr) {
                register(register);
            }
        }
    }

    public static void register(List list) {
        if (list != null) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                register((LogLevel) it.next());
            }
        }
    }

    public boolean equals(Object obj) {
        return (obj instanceof LogLevel) && getPrecedence() == ((LogLevel) obj).getPrecedence();
    }

    public int hashCode() {
        return this._label.hashCode();
    }

    public String toString() {
        return this._label;
    }

    public void setLogLevelColorMap(LogLevel logLevel, Color color) {
        _logLevelColorMap.remove(logLevel);
        if (color == null) {
            color = Color.black;
        }
        _logLevelColorMap.put(logLevel, color);
    }

    public static void resetLogLevelColorMap() {
        _logLevelColorMap.clear();
        int i = 0;
        while (true) {
            LogLevel[] logLevelArr = _allDefaultLevels;
            if (i < logLevelArr.length) {
                _logLevelColorMap.put(logLevelArr[i], Color.black);
                i++;
            } else {
                return;
            }
        }
    }

    public static List getLog4JLevels() {
        return Arrays.asList(_log4JLevels);
    }

    public static List getJdk14Levels() {
        return Arrays.asList(_jdk14Levels);
    }

    public static List getAllDefaultLevels() {
        return Arrays.asList(_allDefaultLevels);
    }

    public static Map getLogLevelColorMap() {
        return _logLevelColorMap;
    }

    /* access modifiers changed from: protected */
    public int getPrecedence() {
        return this._precedence;
    }
}
