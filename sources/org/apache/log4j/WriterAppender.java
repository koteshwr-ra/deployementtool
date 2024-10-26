package org.apache.log4j;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.helpers.QuietWriter;
import org.apache.log4j.spi.ErrorHandler;
import org.apache.log4j.spi.LoggingEvent;

public class WriterAppender extends AppenderSkeleton {
    protected String encoding;
    protected boolean immediateFlush;
    protected QuietWriter qw;

    public void activateOptions() {
    }

    public boolean requiresLayout() {
        return true;
    }

    public WriterAppender() {
        this.immediateFlush = true;
    }

    public WriterAppender(Layout layout, OutputStream outputStream) {
        this(layout, (Writer) new OutputStreamWriter(outputStream));
    }

    public WriterAppender(Layout layout, Writer writer) {
        this.immediateFlush = true;
        this.layout = layout;
        setWriter(writer);
    }

    public void setImmediateFlush(boolean z) {
        this.immediateFlush = z;
    }

    public boolean getImmediateFlush() {
        return this.immediateFlush;
    }

    public void append(LoggingEvent loggingEvent) {
        if (checkEntryConditions()) {
            subAppend(loggingEvent);
        }
    }

    /* access modifiers changed from: protected */
    public boolean checkEntryConditions() {
        if (this.closed) {
            LogLog.warn("Not allowed to write to a closed appender.");
            return false;
        } else if (this.qw == null) {
            ErrorHandler errorHandler = this.errorHandler;
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("No output stream or file set for the appender named [");
            stringBuffer.append(this.name);
            stringBuffer.append("].");
            errorHandler.error(stringBuffer.toString());
            return false;
        } else if (this.layout != null) {
            return true;
        } else {
            ErrorHandler errorHandler2 = this.errorHandler;
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("No layout set for the appender named [");
            stringBuffer2.append(this.name);
            stringBuffer2.append("].");
            errorHandler2.error(stringBuffer2.toString());
            return false;
        }
    }

    public synchronized void close() {
        if (!this.closed) {
            this.closed = true;
            writeFooter();
            reset();
        }
    }

    /* access modifiers changed from: protected */
    public void closeWriter() {
        QuietWriter quietWriter = this.qw;
        if (quietWriter != null) {
            try {
                quietWriter.close();
            } catch (IOException e) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Could not close ");
                stringBuffer.append(this.qw);
                LogLog.error(stringBuffer.toString(), e);
            }
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:10:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0019  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.io.OutputStreamWriter createWriter(java.io.OutputStream r3) {
        /*
            r2 = this;
            java.lang.String r0 = r2.getEncoding()
            if (r0 == 0) goto L_0x0016
            java.io.OutputStreamWriter r1 = new java.io.OutputStreamWriter     // Catch:{ IOException -> 0x000c }
            r1.<init>(r3, r0)     // Catch:{ IOException -> 0x000c }
            goto L_0x0017
        L_0x000c:
            java.lang.String r0 = "Error initializing output writer."
            org.apache.log4j.helpers.LogLog.warn(r0)
            java.lang.String r0 = "Unsupported encoding?"
            org.apache.log4j.helpers.LogLog.warn(r0)
        L_0x0016:
            r1 = 0
        L_0x0017:
            if (r1 != 0) goto L_0x001e
            java.io.OutputStreamWriter r1 = new java.io.OutputStreamWriter
            r1.<init>(r3)
        L_0x001e:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.log4j.WriterAppender.createWriter(java.io.OutputStream):java.io.OutputStreamWriter");
    }

    public String getEncoding() {
        return this.encoding;
    }

    public void setEncoding(String str) {
        this.encoding = str;
    }

    public synchronized void setErrorHandler(ErrorHandler errorHandler) {
        if (errorHandler == null) {
            LogLog.warn("You have tried to set a null error-handler.");
        } else {
            this.errorHandler = errorHandler;
            if (this.qw != null) {
                this.qw.setErrorHandler(errorHandler);
            }
        }
    }

    public synchronized void setWriter(Writer writer) {
        reset();
        this.qw = new QuietWriter(writer, this.errorHandler);
        writeHeader();
    }

    /* access modifiers changed from: protected */
    public void subAppend(LoggingEvent loggingEvent) {
        String[] throwableStrRep;
        this.qw.write(this.layout.format(loggingEvent));
        if (this.layout.ignoresThrowable() && (throwableStrRep = loggingEvent.getThrowableStrRep()) != null) {
            for (String write : throwableStrRep) {
                this.qw.write(write);
                this.qw.write(Layout.LINE_SEP);
            }
        }
        if (this.immediateFlush) {
            this.qw.flush();
        }
    }

    /* access modifiers changed from: protected */
    public void reset() {
        closeWriter();
        this.qw = null;
    }

    /* access modifiers changed from: protected */
    public void writeFooter() {
        String footer;
        QuietWriter quietWriter;
        if (this.layout != null && (footer = this.layout.getFooter()) != null && (quietWriter = this.qw) != null) {
            quietWriter.write(footer);
            this.qw.flush();
        }
    }

    /* access modifiers changed from: protected */
    public void writeHeader() {
        String header;
        QuietWriter quietWriter;
        if (this.layout != null && (header = this.layout.getHeader()) != null && (quietWriter = this.qw) != null) {
            quietWriter.write(header);
        }
    }
}
