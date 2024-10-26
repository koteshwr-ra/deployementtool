package org.apache.log4j.chainsaw;

import org.apache.log4j.Priority;
import org.apache.log4j.spi.LoggingEvent;

class EventDetails {
    private final String mCategoryName;
    private final String mLocationDetails;
    private final String mMessage;
    private final String mNDC;
    private final Priority mPriority;
    private final String mThreadName;
    private final String[] mThrowableStrRep;
    private final long mTimeStamp;

    EventDetails(long j, Priority priority, String str, String str2, String str3, String str4, String[] strArr, String str5) {
        this.mTimeStamp = j;
        this.mPriority = priority;
        this.mCategoryName = str;
        this.mNDC = str2;
        this.mThreadName = str3;
        this.mMessage = str4;
        this.mThrowableStrRep = strArr;
        this.mLocationDetails = str5;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    EventDetails(LoggingEvent loggingEvent) {
        this(loggingEvent.timeStamp, loggingEvent.getLevel(), loggingEvent.getLoggerName(), loggingEvent.getNDC(), loggingEvent.getThreadName(), loggingEvent.getRenderedMessage(), loggingEvent.getThrowableStrRep(), loggingEvent.getLocationInformation() == null ? null : loggingEvent.getLocationInformation().fullInfo);
    }

    /* access modifiers changed from: package-private */
    public long getTimeStamp() {
        return this.mTimeStamp;
    }

    /* access modifiers changed from: package-private */
    public Priority getPriority() {
        return this.mPriority;
    }

    /* access modifiers changed from: package-private */
    public String getCategoryName() {
        return this.mCategoryName;
    }

    /* access modifiers changed from: package-private */
    public String getNDC() {
        return this.mNDC;
    }

    /* access modifiers changed from: package-private */
    public String getThreadName() {
        return this.mThreadName;
    }

    /* access modifiers changed from: package-private */
    public String getMessage() {
        return this.mMessage;
    }

    /* access modifiers changed from: package-private */
    public String getLocationDetails() {
        return this.mLocationDetails;
    }

    /* access modifiers changed from: package-private */
    public String[] getThrowableStrRep() {
        return this.mThrowableStrRep;
    }
}
