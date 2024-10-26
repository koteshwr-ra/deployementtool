package org.apache.log4j;

import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.helpers.OnlyOnceErrorHandler;
import org.apache.log4j.spi.ErrorHandler;
import org.apache.log4j.spi.Filter;
import org.apache.log4j.spi.LoggingEvent;
import org.apache.log4j.spi.OptionHandler;

public abstract class AppenderSkeleton implements Appender, OptionHandler {
    protected boolean closed = false;
    protected ErrorHandler errorHandler = new OnlyOnceErrorHandler();
    protected Filter headFilter;
    protected Layout layout;
    protected String name;
    protected Filter tailFilter;
    protected Priority threshold;

    public void activateOptions() {
    }

    /* access modifiers changed from: protected */
    public abstract void append(LoggingEvent loggingEvent);

    public abstract void close();

    public abstract boolean requiresLayout();

    public void addFilter(Filter filter) {
        if (this.headFilter == null) {
            this.tailFilter = filter;
            this.headFilter = filter;
            return;
        }
        this.tailFilter.next = filter;
        this.tailFilter = filter;
    }

    public void clearFilters() {
        this.tailFilter = null;
        this.headFilter = null;
    }

    public void finalize() {
        if (!this.closed) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Finalizing appender named [");
            stringBuffer.append(this.name);
            stringBuffer.append("].");
            LogLog.debug(stringBuffer.toString());
            close();
        }
    }

    public ErrorHandler getErrorHandler() {
        return this.errorHandler;
    }

    public Filter getFilter() {
        return this.headFilter;
    }

    public final Filter getFirstFilter() {
        return this.headFilter;
    }

    public Layout getLayout() {
        return this.layout;
    }

    public final String getName() {
        return this.name;
    }

    public Priority getThreshold() {
        return this.threshold;
    }

    public boolean isAsSevereAsThreshold(Priority priority) {
        Priority priority2 = this.threshold;
        return priority2 == null || priority.isGreaterOrEqual(priority2);
    }

    public synchronized void doAppend(LoggingEvent loggingEvent) {
        if (this.closed) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Attempted to append to closed appender named [");
            stringBuffer.append(this.name);
            stringBuffer.append("].");
            LogLog.error(stringBuffer.toString());
        } else if (isAsSevereAsThreshold(loggingEvent.getLevel())) {
            Filter filter = this.headFilter;
            while (true) {
                if (filter == null) {
                    break;
                }
                int decide = filter.decide(loggingEvent);
                if (decide == -1) {
                    return;
                }
                if (decide != 0) {
                    if (decide == 1) {
                        break;
                    }
                } else {
                    filter = filter.next;
                }
            }
            append(loggingEvent);
        }
    }

    public synchronized void setErrorHandler(ErrorHandler errorHandler2) {
        if (errorHandler2 == null) {
            LogLog.warn("You have tried to set a null error-handler.");
        } else {
            this.errorHandler = errorHandler2;
        }
    }

    public void setLayout(Layout layout2) {
        this.layout = layout2;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setThreshold(Priority priority) {
        this.threshold = priority;
    }
}
