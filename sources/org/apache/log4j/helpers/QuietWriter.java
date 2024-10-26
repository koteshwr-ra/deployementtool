package org.apache.log4j.helpers;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;
import org.apache.log4j.spi.ErrorHandler;

public class QuietWriter extends FilterWriter {
    protected ErrorHandler errorHandler;

    public QuietWriter(Writer writer, ErrorHandler errorHandler2) {
        super(writer);
        setErrorHandler(errorHandler2);
    }

    public void write(String str) {
        try {
            this.out.write(str);
        } catch (IOException e) {
            ErrorHandler errorHandler2 = this.errorHandler;
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Failed to write [");
            stringBuffer.append(str);
            stringBuffer.append("].");
            errorHandler2.error(stringBuffer.toString(), e, 1);
        }
    }

    public void flush() {
        try {
            this.out.flush();
        } catch (IOException e) {
            this.errorHandler.error("Failed to flush writer,", e, 2);
        }
    }

    public void setErrorHandler(ErrorHandler errorHandler2) {
        if (errorHandler2 != null) {
            this.errorHandler = errorHandler2;
            return;
        }
        throw new IllegalArgumentException("Attempted to set null ErrorHandler.");
    }
}
