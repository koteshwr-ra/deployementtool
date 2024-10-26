package org.apache.log4j.helpers;

import org.apache.log4j.spi.LoggingEvent;

public class CyclicBuffer {
    LoggingEvent[] ea;
    int first;
    int last;
    int maxSize;
    int numElems;

    public CyclicBuffer(int i) throws IllegalArgumentException {
        if (i >= 1) {
            this.maxSize = i;
            this.ea = new LoggingEvent[i];
            this.first = 0;
            this.last = 0;
            this.numElems = 0;
            return;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("The maxSize argument (");
        stringBuffer.append(i);
        stringBuffer.append(") is not a positive integer.");
        throw new IllegalArgumentException(stringBuffer.toString());
    }

    public void add(LoggingEvent loggingEvent) {
        LoggingEvent[] loggingEventArr = this.ea;
        int i = this.last;
        loggingEventArr[i] = loggingEvent;
        int i2 = i + 1;
        this.last = i2;
        if (i2 == this.maxSize) {
            this.last = 0;
        }
        int i3 = this.numElems;
        int i4 = this.maxSize;
        if (i3 < i4) {
            this.numElems = i3 + 1;
            return;
        }
        int i5 = this.first + 1;
        this.first = i5;
        if (i5 == i4) {
            this.first = 0;
        }
    }

    public LoggingEvent get(int i) {
        if (i < 0 || i >= this.numElems) {
            return null;
        }
        return this.ea[(this.first + i) % this.maxSize];
    }

    public int getMaxSize() {
        return this.maxSize;
    }

    public LoggingEvent get() {
        int i = this.numElems;
        if (i <= 0) {
            return null;
        }
        this.numElems = i - 1;
        LoggingEvent[] loggingEventArr = this.ea;
        int i2 = this.first;
        LoggingEvent loggingEvent = loggingEventArr[i2];
        loggingEventArr[i2] = null;
        int i3 = i2 + 1;
        this.first = i3;
        if (i3 == this.maxSize) {
            this.first = 0;
        }
        return loggingEvent;
    }

    public int length() {
        return this.numElems;
    }

    public void resize(int i) {
        if (i >= 0) {
            int i2 = this.numElems;
            if (i != i2) {
                LoggingEvent[] loggingEventArr = new LoggingEvent[i];
                if (i < i2) {
                    i2 = i;
                }
                for (int i3 = 0; i3 < i2; i3++) {
                    LoggingEvent[] loggingEventArr2 = this.ea;
                    int i4 = this.first;
                    loggingEventArr[i3] = loggingEventArr2[i4];
                    loggingEventArr2[i4] = null;
                    int i5 = i4 + 1;
                    this.first = i5;
                    if (i5 == this.numElems) {
                        this.first = 0;
                    }
                }
                this.ea = loggingEventArr;
                this.first = 0;
                this.numElems = i2;
                this.maxSize = i;
                if (i2 == i) {
                    this.last = 0;
                } else {
                    this.last = i2;
                }
            }
        } else {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Negative array size [");
            stringBuffer.append(i);
            stringBuffer.append("] not allowed.");
            throw new IllegalArgumentException(stringBuffer.toString());
        }
    }
}
