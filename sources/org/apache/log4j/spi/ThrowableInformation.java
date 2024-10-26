package org.apache.log4j.spi;

import java.io.Serializable;

public class ThrowableInformation implements Serializable {
    static final long serialVersionUID = -4748765566864322735L;
    private String[] rep;
    private transient Throwable throwable;

    public ThrowableInformation(Throwable th) {
        this.throwable = th;
    }

    public Throwable getThrowable() {
        return this.throwable;
    }

    public String[] getThrowableStrRep() {
        String[] strArr = this.rep;
        if (strArr != null) {
            return (String[]) strArr.clone();
        }
        VectorWriter vectorWriter = new VectorWriter();
        this.throwable.printStackTrace(vectorWriter);
        String[] stringArray = vectorWriter.toStringArray();
        this.rep = stringArray;
        return stringArray;
    }
}
