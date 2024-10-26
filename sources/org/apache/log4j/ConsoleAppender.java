package org.apache.log4j;

import java.io.IOException;
import java.io.OutputStream;
import org.apache.log4j.helpers.LogLog;

public class ConsoleAppender extends WriterAppender {
    public static final String SYSTEM_ERR = "System.err";
    public static final String SYSTEM_OUT = "System.out";
    private boolean follow;
    protected String target;

    public ConsoleAppender() {
        this.target = SYSTEM_OUT;
        this.follow = false;
    }

    public ConsoleAppender(Layout layout) {
        this(layout, SYSTEM_OUT);
    }

    public ConsoleAppender(Layout layout, String str) {
        this.target = SYSTEM_OUT;
        this.follow = false;
        setLayout(layout);
        setTarget(str);
        activateOptions();
    }

    public void setTarget(String str) {
        String trim = str.trim();
        if (SYSTEM_OUT.equalsIgnoreCase(trim)) {
            this.target = SYSTEM_OUT;
        } else if (SYSTEM_ERR.equalsIgnoreCase(trim)) {
            this.target = SYSTEM_ERR;
        } else {
            targetWarn(str);
        }
    }

    public String getTarget() {
        return this.target;
    }

    public final void setFollow(boolean z) {
        this.follow = z;
    }

    public final boolean getFollow() {
        return this.follow;
    }

    /* access modifiers changed from: package-private */
    public void targetWarn(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[");
        stringBuffer.append(str);
        stringBuffer.append("] should be System.out or System.err.");
        LogLog.warn(stringBuffer.toString());
        LogLog.warn("Using previously set target, System.out by default.");
    }

    public void activateOptions() {
        if (this.follow) {
            if (this.target.equals(SYSTEM_ERR)) {
                setWriter(createWriter(new SystemErrStream()));
            } else {
                setWriter(createWriter(new SystemOutStream()));
            }
        } else if (this.target.equals(SYSTEM_ERR)) {
            setWriter(createWriter(System.err));
        } else {
            setWriter(createWriter(System.out));
        }
        super.activateOptions();
    }

    /* access modifiers changed from: protected */
    public final void closeWriter() {
        if (this.follow) {
            super.closeWriter();
        }
    }

    private static class SystemErrStream extends OutputStream {
        public void close() {
        }

        public void flush() {
            System.err.flush();
        }

        public void write(byte[] bArr) throws IOException {
            System.err.write(bArr);
        }

        public void write(byte[] bArr, int i, int i2) throws IOException {
            System.err.write(bArr, i, i2);
        }

        public void write(int i) throws IOException {
            System.err.write(i);
        }
    }

    private static class SystemOutStream extends OutputStream {
        public void close() {
        }

        public void flush() {
            System.out.flush();
        }

        public void write(byte[] bArr) throws IOException {
            System.out.write(bArr);
        }

        public void write(byte[] bArr, int i, int i2) throws IOException {
            System.out.write(bArr, i, i2);
        }

        public void write(int i) throws IOException {
            System.out.write(i);
        }
    }
}
