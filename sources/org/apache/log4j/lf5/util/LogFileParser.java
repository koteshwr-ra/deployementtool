package org.apache.log4j.lf5.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.SwingUtilities;
import org.apache.log4j.lf5.Log4JLogRecord;
import org.apache.log4j.lf5.LogLevel;
import org.apache.log4j.lf5.LogLevelFormatException;
import org.apache.log4j.lf5.LogRecord;
import org.apache.log4j.lf5.viewer.LogBrokerMonitor;
import org.apache.log4j.lf5.viewer.LogFactor5ErrorDialog;
import org.apache.log4j.lf5.viewer.LogFactor5LoadingDialog;

public class LogFileParser implements Runnable {
    public static final String ATTRIBUTE_DELIMITER = "[slf5s.";
    public static final String CATEGORY_DELIMITER = "[slf5s.CATEGORY]";
    public static final String DATE_DELIMITER = "[slf5s.DATE]";
    public static final String LOCATION_DELIMITER = "[slf5s.LOCATION]";
    public static final String MESSAGE_DELIMITER = "[slf5s.MESSAGE]";
    public static final String NDC_DELIMITER = "[slf5s.NDC]";
    public static final String PRIORITY_DELIMITER = "[slf5s.PRIORITY]";
    public static final String RECORD_DELIMITER = "[slf5s.start]";
    public static final String THREAD_DELIMITER = "[slf5s.THREAD]";
    private static SimpleDateFormat _sdf = new SimpleDateFormat("dd MMM yyyy HH:mm:ss,S");
    private InputStream _in;
    LogFactor5LoadingDialog _loadDialog;
    private LogBrokerMonitor _monitor;

    public LogFileParser(File file) throws IOException, FileNotFoundException {
        this((InputStream) new FileInputStream(file));
    }

    public LogFileParser(InputStream inputStream) throws IOException {
        this._in = null;
        this._in = inputStream;
    }

    public void parse(LogBrokerMonitor logBrokerMonitor) throws RuntimeException {
        this._monitor = logBrokerMonitor;
        new Thread(this).start();
    }

    public void run() {
        LogRecord createLogRecord;
        this._loadDialog = new LogFactor5LoadingDialog(this._monitor.getBaseFrame(), "Loading file...");
        try {
            String loadLogFile = loadLogFile(this._in);
            int i = 0;
            boolean z = false;
            while (true) {
                int indexOf = loadLogFile.indexOf(RECORD_DELIMITER, i);
                if (indexOf == -1) {
                    break;
                }
                LogRecord createLogRecord2 = createLogRecord(loadLogFile.substring(i, indexOf));
                z = true;
                if (createLogRecord2 != null) {
                    this._monitor.addMessage(createLogRecord2);
                }
                i = indexOf + 13;
            }
            if (i < loadLogFile.length() && z && (createLogRecord = createLogRecord(loadLogFile.substring(i))) != null) {
                this._monitor.addMessage(createLogRecord);
            }
            if (z) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        LogFileParser.this.destroyDialog();
                    }
                });
                this._in = null;
                return;
            }
            throw new RuntimeException("Invalid log file format");
        } catch (RuntimeException unused) {
            destroyDialog();
            displayError("Error - Invalid log file format.\nPlease see documentation on how to load log files.");
        } catch (IOException unused2) {
            destroyDialog();
            displayError("Error - Unable to load log file!");
        }
    }

    /* access modifiers changed from: protected */
    public void displayError(String str) {
        new LogFactor5ErrorDialog(this._monitor.getBaseFrame(), str);
    }

    /* access modifiers changed from: private */
    public void destroyDialog() {
        this._loadDialog.hide();
        this._loadDialog.dispose();
    }

    private String loadLogFile(InputStream inputStream) throws IOException {
        StringBuffer stringBuffer;
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        int available = bufferedInputStream.available();
        if (available > 0) {
            stringBuffer = new StringBuffer(available);
        } else {
            stringBuffer = new StringBuffer(1024);
        }
        while (true) {
            int read = bufferedInputStream.read();
            if (read == -1) {
                bufferedInputStream.close();
                return stringBuffer.toString();
            }
            stringBuffer.append((char) read);
        }
    }

    private String parseAttribute(String str, String str2) {
        int indexOf = str2.indexOf(str);
        if (indexOf == -1) {
            return null;
        }
        return getAttribute(indexOf, str2);
    }

    private long parseDate(String str) {
        try {
            String parseAttribute = parseAttribute(DATE_DELIMITER, str);
            if (parseAttribute == null) {
                return 0;
            }
            return _sdf.parse(parseAttribute).getTime();
        } catch (ParseException unused) {
            return 0;
        }
    }

    private LogLevel parsePriority(String str) {
        String parseAttribute = parseAttribute(PRIORITY_DELIMITER, str);
        if (parseAttribute == null) {
            return LogLevel.DEBUG;
        }
        try {
            return LogLevel.valueOf(parseAttribute);
        } catch (LogLevelFormatException unused) {
            return LogLevel.DEBUG;
        }
    }

    private String parseThread(String str) {
        return parseAttribute(THREAD_DELIMITER, str);
    }

    private String parseCategory(String str) {
        return parseAttribute(CATEGORY_DELIMITER, str);
    }

    private String parseLocation(String str) {
        return parseAttribute(LOCATION_DELIMITER, str);
    }

    private String parseMessage(String str) {
        return parseAttribute(MESSAGE_DELIMITER, str);
    }

    private String parseNDC(String str) {
        return parseAttribute(NDC_DELIMITER, str);
    }

    private String parseThrowable(String str) {
        return getAttribute(str.length(), str);
    }

    private LogRecord createLogRecord(String str) {
        if (str == null || str.trim().length() == 0) {
            return null;
        }
        Log4JLogRecord log4JLogRecord = new Log4JLogRecord();
        log4JLogRecord.setMillis(parseDate(str));
        log4JLogRecord.setLevel(parsePriority(str));
        log4JLogRecord.setCategory(parseCategory(str));
        log4JLogRecord.setLocation(parseLocation(str));
        log4JLogRecord.setThreadDescription(parseThread(str));
        log4JLogRecord.setNDC(parseNDC(str));
        log4JLogRecord.setMessage(parseMessage(str));
        log4JLogRecord.setThrownStackTrace(parseThrowable(str));
        return log4JLogRecord;
    }

    private String getAttribute(int i, String str) {
        int lastIndexOf = str.lastIndexOf(ATTRIBUTE_DELIMITER, i - 1);
        if (lastIndexOf == -1) {
            return str.substring(0, i);
        }
        return str.substring(str.indexOf("]", lastIndexOf) + 1, i).trim();
    }
}
