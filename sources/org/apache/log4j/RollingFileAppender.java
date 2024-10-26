package org.apache.log4j;

import com.alibaba.android.arouter.utils.Consts;
import java.io.File;
import java.io.IOException;
import java.io.Writer;
import org.apache.commons.lang3.ClassUtils;
import org.apache.log4j.helpers.CountingQuietWriter;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.helpers.OptionConverter;
import org.apache.log4j.spi.LoggingEvent;

public class RollingFileAppender extends FileAppender {
    protected int maxBackupIndex = 1;
    protected long maxFileSize = 10485760;

    public RollingFileAppender() {
    }

    public RollingFileAppender(Layout layout, String str, boolean z) throws IOException {
        super(layout, str, z);
    }

    public RollingFileAppender(Layout layout, String str) throws IOException {
        super(layout, str);
    }

    public int getMaxBackupIndex() {
        return this.maxBackupIndex;
    }

    public long getMaximumFileSize() {
        return this.maxFileSize;
    }

    public void rollOver() {
        if (this.qw != null) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("rolling over count=");
            stringBuffer.append(((CountingQuietWriter) this.qw).getCount());
            LogLog.debug(stringBuffer.toString());
        }
        StringBuffer stringBuffer2 = new StringBuffer();
        stringBuffer2.append("maxBackupIndex=");
        stringBuffer2.append(this.maxBackupIndex);
        LogLog.debug(stringBuffer2.toString());
        if (this.maxBackupIndex > 0) {
            StringBuffer stringBuffer3 = new StringBuffer();
            stringBuffer3.append(this.fileName);
            stringBuffer3.append(ClassUtils.PACKAGE_SEPARATOR_CHAR);
            stringBuffer3.append(this.maxBackupIndex);
            File file = new File(stringBuffer3.toString());
            if (file.exists()) {
                file.delete();
            }
            for (int i = this.maxBackupIndex - 1; i >= 1; i--) {
                StringBuffer stringBuffer4 = new StringBuffer();
                stringBuffer4.append(this.fileName);
                stringBuffer4.append(Consts.DOT);
                stringBuffer4.append(i);
                File file2 = new File(stringBuffer4.toString());
                if (file2.exists()) {
                    StringBuffer stringBuffer5 = new StringBuffer();
                    stringBuffer5.append(this.fileName);
                    stringBuffer5.append(ClassUtils.PACKAGE_SEPARATOR_CHAR);
                    stringBuffer5.append(i + 1);
                    File file3 = new File(stringBuffer5.toString());
                    StringBuffer stringBuffer6 = new StringBuffer();
                    stringBuffer6.append("Renaming file ");
                    stringBuffer6.append(file2);
                    stringBuffer6.append(" to ");
                    stringBuffer6.append(file3);
                    LogLog.debug(stringBuffer6.toString());
                    file2.renameTo(file3);
                }
            }
            StringBuffer stringBuffer7 = new StringBuffer();
            stringBuffer7.append(this.fileName);
            stringBuffer7.append(Consts.DOT);
            stringBuffer7.append(1);
            File file4 = new File(stringBuffer7.toString());
            closeFile();
            File file5 = new File(this.fileName);
            StringBuffer stringBuffer8 = new StringBuffer();
            stringBuffer8.append("Renaming file ");
            stringBuffer8.append(file5);
            stringBuffer8.append(" to ");
            stringBuffer8.append(file4);
            LogLog.debug(stringBuffer8.toString());
            file5.renameTo(file4);
        }
        try {
            setFile(this.fileName, false, this.bufferedIO, this.bufferSize);
        } catch (IOException e) {
            StringBuffer stringBuffer9 = new StringBuffer();
            stringBuffer9.append("setFile(");
            stringBuffer9.append(this.fileName);
            stringBuffer9.append(", false) call failed.");
            LogLog.error(stringBuffer9.toString(), e);
        }
    }

    public synchronized void setFile(String str, boolean z, boolean z2, int i) throws IOException {
        super.setFile(str, z, this.bufferedIO, this.bufferSize);
        if (z) {
            ((CountingQuietWriter) this.qw).setCount(new File(str).length());
        }
    }

    public void setMaxBackupIndex(int i) {
        this.maxBackupIndex = i;
    }

    public void setMaximumFileSize(long j) {
        this.maxFileSize = j;
    }

    public void setMaxFileSize(String str) {
        this.maxFileSize = OptionConverter.toFileSize(str, this.maxFileSize + 1);
    }

    /* access modifiers changed from: protected */
    public void setQWForFiles(Writer writer) {
        this.qw = new CountingQuietWriter(writer, this.errorHandler);
    }

    /* access modifiers changed from: protected */
    public void subAppend(LoggingEvent loggingEvent) {
        super.subAppend(loggingEvent);
        if (this.fileName != null && ((CountingQuietWriter) this.qw).getCount() >= this.maxFileSize) {
            rollOver();
        }
    }
}
