package com.ciot.networklib.util;

import com.ciot.base.constant.NetConstant;
import java.util.Calendar;
import java.util.TimeZone;
import mc.csst.com.selfchassis.ui.fragment.set.map.MapFragment;
import org.apache.commons.lang3.StringUtils;

public class TimeUtils {
    public static String getYearMonthDayHourMinuteSecond(long j) {
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        Calendar instance = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
        instance.setTimeInMillis(j);
        int i = instance.get(1);
        int i2 = instance.get(2) + 1;
        if (String.valueOf(i2).length() == 1) {
            str = NetConstant.PAGE_ID_HOME + i2;
        } else {
            str = String.valueOf(i2);
        }
        int i3 = instance.get(5);
        if (String.valueOf(i3).length() == 1) {
            str2 = NetConstant.PAGE_ID_HOME + i3;
        } else {
            str2 = String.valueOf(i3);
        }
        int i4 = instance.get(11);
        if (String.valueOf(i4).length() == 1) {
            str3 = NetConstant.PAGE_ID_HOME + i4;
        } else {
            str3 = String.valueOf(i4);
        }
        int i5 = instance.get(12);
        if (String.valueOf(i5).length() == 1) {
            str4 = NetConstant.PAGE_ID_HOME + i5;
        } else {
            str4 = String.valueOf(i5);
        }
        int i6 = instance.get(13);
        if (String.valueOf(i6).length() == 1) {
            str5 = NetConstant.PAGE_ID_HOME + i6;
        } else {
            str5 = String.valueOf(i6);
        }
        return i + MapFragment.SLASH + str + MapFragment.SLASH + str2 + StringUtils.SPACE + str3 + ":" + str4 + ":" + str5;
    }

    public static String getYearMonthDay(long j) {
        String str;
        String str2;
        Calendar instance = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
        instance.setTimeInMillis(j);
        int i = instance.get(1);
        int i2 = instance.get(2) + 1;
        if (String.valueOf(i2).length() == 1) {
            str = NetConstant.PAGE_ID_HOME + i2;
        } else {
            str = String.valueOf(i2);
        }
        int i3 = instance.get(5);
        if (String.valueOf(i3).length() == 1) {
            str2 = NetConstant.PAGE_ID_HOME + i3;
        } else {
            str2 = String.valueOf(i3);
        }
        int i4 = instance.get(11);
        if (String.valueOf(i4).length() != 1) {
            String.valueOf(i4);
        }
        int i5 = instance.get(12);
        if (String.valueOf(i5).length() != 1) {
            String.valueOf(i5);
        }
        int i6 = instance.get(13);
        if (String.valueOf(i6).length() != 1) {
            String.valueOf(i6);
        }
        return i + MapFragment.SLASH + str + MapFragment.SLASH + str2;
    }

    public static String getMonthDay(long j) {
        String str;
        String str2;
        Calendar instance = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
        instance.setTimeInMillis(j);
        instance.get(1);
        int i = instance.get(2) + 1;
        if (String.valueOf(i).length() == 1) {
            str = NetConstant.PAGE_ID_HOME + i;
        } else {
            str = String.valueOf(i);
        }
        int i2 = instance.get(5);
        if (String.valueOf(i2).length() == 1) {
            str2 = NetConstant.PAGE_ID_HOME + i2;
        } else {
            str2 = String.valueOf(i2);
        }
        int i3 = instance.get(11);
        if (String.valueOf(i3).length() != 1) {
            String.valueOf(i3);
        }
        int i4 = instance.get(12);
        if (String.valueOf(i4).length() != 1) {
            String.valueOf(i4);
        }
        int i5 = instance.get(13);
        if (String.valueOf(i5).length() != 1) {
            String.valueOf(i5);
        }
        return str + "月" + str2 + "日";
    }

    public static String getHourMinute(long j) {
        String str;
        String str2;
        Calendar instance = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
        instance.setTimeInMillis(j);
        instance.get(1);
        int i = instance.get(2) + 1;
        if (String.valueOf(i).length() != 1) {
            String.valueOf(i);
        }
        int i2 = instance.get(5);
        if (String.valueOf(i2).length() != 1) {
            String.valueOf(i2);
        }
        int i3 = instance.get(11);
        if (String.valueOf(i3).length() == 1) {
            str = NetConstant.PAGE_ID_HOME + i3;
        } else {
            str = String.valueOf(i3);
        }
        int i4 = instance.get(12);
        if (String.valueOf(i4).length() == 1) {
            str2 = NetConstant.PAGE_ID_HOME + i4;
        } else {
            str2 = String.valueOf(i4);
        }
        int i5 = instance.get(13);
        if (String.valueOf(i5).length() != 1) {
            String.valueOf(i5);
        }
        return str + ":" + str2;
    }
}
