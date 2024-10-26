package org.apache.log4j;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

/* compiled from: DailyRollingFileAppender */
class RollingCalendar extends GregorianCalendar {
    int type = -1;

    RollingCalendar() {
    }

    RollingCalendar(TimeZone timeZone, Locale locale) {
        super(timeZone, locale);
    }

    /* access modifiers changed from: package-private */
    public void setType(int i) {
        this.type = i;
    }

    public long getNextCheckMillis(Date date) {
        return getNextCheckDate(date).getTime();
    }

    public Date getNextCheckDate(Date date) {
        setTime(date);
        int i = this.type;
        if (i == 0) {
            set(13, 0);
            set(14, 0);
            add(12, 1);
        } else if (i == 1) {
            set(12, 0);
            set(13, 0);
            set(14, 0);
            add(11, 1);
        } else if (i == 2) {
            set(12, 0);
            set(13, 0);
            set(14, 0);
            if (get(11) < 12) {
                set(11, 12);
            } else {
                set(11, 0);
                add(5, 1);
            }
        } else if (i == 3) {
            set(11, 0);
            set(12, 0);
            set(13, 0);
            set(14, 0);
            add(5, 1);
        } else if (i == 4) {
            set(7, getFirstDayOfWeek());
            set(11, 0);
            set(12, 0);
            set(13, 0);
            set(14, 0);
            add(3, 1);
        } else if (i == 5) {
            set(5, 1);
            set(11, 0);
            set(12, 0);
            set(13, 0);
            set(14, 0);
            add(2, 1);
        } else {
            throw new IllegalStateException("Unknown periodicity type.");
        }
        return getTime();
    }
}
