package org.apache.log4j;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Writer;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.helpers.QuietWriter;
import org.apache.log4j.spi.ErrorHandler;

public class FileAppender extends WriterAppender {
    protected int bufferSize;
    protected boolean bufferedIO;
    protected boolean fileAppend;
    protected String fileName;

    public FileAppender() {
        this.fileAppend = true;
        this.fileName = null;
        this.bufferedIO = false;
        this.bufferSize = 8192;
    }

    public FileAppender(Layout layout, String str, boolean z, boolean z2, int i) throws IOException {
        this.fileAppend = true;
        this.fileName = null;
        this.bufferedIO = false;
        this.bufferSize = 8192;
        this.layout = layout;
        setFile(str, z, z2, i);
    }

    public FileAppender(Layout layout, String str, boolean z) throws IOException {
        this.fileAppend = true;
        this.fileName = null;
        this.bufferedIO = false;
        this.bufferSize = 8192;
        this.layout = layout;
        setFile(str, z, false, this.bufferSize);
    }

    public FileAppender(Layout layout, String str) throws IOException {
        this(layout, str, true);
    }

    public void setFile(String str) {
        this.fileName = str.trim();
    }

    public boolean getAppend() {
        return this.fileAppend;
    }

    public String getFile() {
        return this.fileName;
    }

    public void activateOptions() {
        String str = this.fileName;
        if (str != null) {
            try {
                setFile(str, this.fileAppend, this.bufferedIO, this.bufferSize);
            } catch (IOException e) {
                ErrorHandler errorHandler = this.errorHandler;
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("setFile(");
                stringBuffer.append(this.fileName);
                stringBuffer.append(",");
                stringBuffer.append(this.fileAppend);
                stringBuffer.append(") call failed.");
                errorHandler.error(stringBuffer.toString(), e, 4);
            }
        } else {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("File option not set for appender [");
            stringBuffer2.append(this.name);
            stringBuffer2.append("].");
            LogLog.warn(stringBuffer2.toString());
            LogLog.warn("Are you using FileAppender instead of ConsoleAppender?");
        }
    }

    /* access modifiers changed from: protected */
    public void closeFile() {
        if (this.qw != null) {
            try {
                this.qw.close();
            } catch (IOException e) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Could not close ");
                stringBuffer.append(this.qw);
                LogLog.error(stringBuffer.toString(), e);
            }
        }
    }

    public boolean getBufferedIO() {
        return this.bufferedIO;
    }

    public int getBufferSize() {
        return this.bufferSize;
    }

    public void setAppend(boolean z) {
        this.fileAppend = z;
    }

    public void setBufferedIO(boolean z) {
        this.bufferedIO = z;
        if (z) {
            this.immediateFlush = false;
        }
    }

    public void setBufferSize(int i) {
        this.bufferSize = i;
    }

    public synchronized void setFile(String str, boolean z, boolean z2, int i) throws IOException {
        FileOutputStream fileOutputStream;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("setFile called: ");
        stringBuffer.append(str);
        stringBuffer.append(", ");
        stringBuffer.append(z);
        LogLog.debug(stringBuffer.toString());
        if (z2) {
            setImmediateFlush(false);
        }
        reset();
        try {
            fileOutputStream = new FileOutputStream(str, z);
        } catch (FileNotFoundException e) {
            String parent = new File(str).getParent();
            if (parent != null) {
                File file = new File(parent);
                if (file.exists() || !file.mkdirs()) {
                    throw e;
                }
                fileOutputStream = new FileOutputStream(str, z);
            } else {
                throw e;
            }
        }
        Writer createWriter = createWriter(fileOutputStream);
        if (z2) {
            createWriter = new BufferedWriter(createWriter, i);
        }
        setQWForFiles(createWriter);
        this.fileName = str;
        this.fileAppend = z;
        this.bufferedIO = z2;
        this.bufferSize = i;
        writeHeader();
        LogLog.debug("setFile ended");
    }

    /* access modifiers changed from: protected */
    public void setQWForFiles(Writer writer) {
        this.qw = new QuietWriter(writer, this.errorHandler);
    }

    /* access modifiers changed from: protected */
    public void reset() {
        closeFile();
        this.fileName = null;
        super.reset();
    }
}
