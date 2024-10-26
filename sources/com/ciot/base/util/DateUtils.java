package com.ciot.base.util;

import android.text.format.Time;
import androidx.exifinterface.media.ExifInterface;
import com.blankj.utilcode.constant.TimeConstants;
import com.ciot.base.constant.NetConstant;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import mc.csst.com.selfchassis.ui.fragment.set.map.MapFragment;

public class DateUtils {
    public static SimpleDateFormat mDateFormat = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault());
    public static SimpleDateFormat mNowFormat = new SimpleDateFormat(ExifInterface.LONGITUDE_EAST, Locale.getDefault());
    public static SimpleDateFormat mTimeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
    public static SimpleDateFormat mWeekFormat = new SimpleDateFormat(ExifInterface.LONGITUDE_EAST, Locale.getDefault());

    public static String[] timeFormat(long j) {
        StringBuilder sb;
        String str;
        String[] strArr = new String[2];
        long j2 = (long) TimeConstants.MIN;
        long j3 = j / j2;
        long j4 = (j - (j2 * j3)) / ((long) 1000);
        if (j3 < 10) {
            sb = new StringBuilder();
            sb.append(NetConstant.PAGE_ID_HOME);
        } else {
            sb = new StringBuilder();
            sb.append("");
        }
        sb.append(j3);
        String sb2 = sb.toString();
        if (j4 < 10) {
            str = NetConstant.PAGE_ID_HOME + j4;
        } else {
            str = "" + j4;
        }
        strArr[0] = sb2;
        strArr[1] = str;
        return strArr;
    }

    public static String[] timeFormatMore(long j) {
        StringBuilder sb;
        StringBuilder sb2;
        StringBuilder sb3;
        String str;
        String[] strArr = new String[4];
        long j2 = (long) TimeConstants.DAY;
        long j3 = j / j2;
        long j4 = j - (j2 * j3);
        long j5 = (long) TimeConstants.HOUR;
        long j6 = j4 / j5;
        long j7 = j4 - (j5 * j6);
        long j8 = (long) TimeConstants.MIN;
        long j9 = j7 / j8;
        long j10 = (j7 - (j8 * j9)) / ((long) 1000);
        if (j3 < 10) {
            sb = new StringBuilder();
            sb.append(NetConstant.PAGE_ID_HOME);
        } else {
            sb = new StringBuilder();
            sb.append("");
        }
        sb.append(j3);
        String sb4 = sb.toString();
        if (j6 < 10) {
            sb2 = new StringBuilder();
            sb2.append(NetConstant.PAGE_ID_HOME);
        } else {
            sb2 = new StringBuilder();
            sb2.append("");
        }
        sb2.append(j6);
        String sb5 = sb2.toString();
        if (j9 < 10) {
            sb3 = new StringBuilder();
            sb3.append(NetConstant.PAGE_ID_HOME);
        } else {
            sb3 = new StringBuilder();
            sb3.append("");
        }
        sb3.append(j9);
        String sb6 = sb3.toString();
        if (j10 < 10) {
            str = NetConstant.PAGE_ID_HOME + j10;
        } else {
            str = "" + j10;
        }
        strArr[0] = sb4;
        strArr[1] = sb5;
        strArr[2] = sb6;
        strArr[3] = str;
        return strArr;
    }

    public static String getCurTime(String str) {
        if (MyStringUtils.isEmpty(str)) {
            str = "yyyy-MM-dd HH:mm:ss";
        }
        return new SimpleDateFormat(str).format(new GregorianCalendar().getTime());
    }

    public static String formatTime(Long l, String str) {
        try {
            return new SimpleDateFormat(str).format(new Date(l.longValue()));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String formatTime(Long l) {
        if (l.longValue() <= 0 || l.longValue() >= org.apache.commons.lang3.time.DateUtils.MILLIS_PER_DAY) {
            return "00:00";
        }
        Long valueOf = Long.valueOf(l.longValue() / 1000);
        Long valueOf2 = Long.valueOf(valueOf.longValue() % 60);
        Long valueOf3 = Long.valueOf((valueOf.longValue() / 60) % 60);
        Long valueOf4 = Long.valueOf(valueOf.longValue() / 3600);
        Formatter formatter = new Formatter(new StringBuilder(), Locale.getDefault());
        if (valueOf4.longValue() > 0) {
            return formatter.format("%d:%02d:%02d", new Object[]{valueOf4, valueOf3, valueOf2}).toString();
        }
        return formatter.format("%02d:%02d", new Object[]{valueOf3, valueOf2}).toString();
    }

    public static String getCurTimeAddND(int i, String str) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.add(5, i);
        return new SimpleDateFormat(str).format(gregorianCalendar.getTime());
    }

    public static String getCurTimeAddH(int i, String str) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.add(11, i);
        return new SimpleDateFormat(str).format(gregorianCalendar.getTime());
    }

    public static String getCurTimeAdd30M() {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.add(12, 30);
        return new SimpleDateFormat("HHmm").format(gregorianCalendar.getTime());
    }

    public static String getCurTimeAdd20M() {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.add(12, 20);
        return new SimpleDateFormat("HHmm").format(gregorianCalendar.getTime());
    }

    public static String getCurTimeAdd40M() {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.add(12, 40);
        return new SimpleDateFormat("HHmm").format(gregorianCalendar.getTime());
    }

    public static String getweekdayBystr(String str) {
        String str2 = "";
        if (str2.equals(str)) {
            return str2;
        }
        int intValue = Integer.valueOf(str.substring(8, 10)).intValue();
        Calendar instance = Calendar.getInstance();
        instance.set(Integer.valueOf(str.substring(0, 4)).intValue(), Integer.valueOf(str.substring(5, 7)).intValue() - 1, intValue);
        switch (instance.get(7) - 1) {
            case 0:
                str2 = "日";
                break;
            case 1:
                str2 = "一";
                break;
            case 2:
                str2 = "二";
                break;
            case 3:
                str2 = "三";
                break;
            case 4:
                str2 = "四";
                break;
            case 5:
                str2 = "五";
                break;
            case 6:
                str2 = "六";
                break;
        }
        return "星期" + str2;
    }

    public static String getweekdayBystrNew(String str) {
        String str2 = "";
        if (str2.equals(str)) {
            return str2;
        }
        int intValue = Integer.valueOf(str.substring(8, 10)).intValue();
        Calendar instance = Calendar.getInstance();
        instance.set(Integer.valueOf(str.substring(0, 4)).intValue(), Integer.valueOf(str.substring(5, 7)).intValue() - 1, intValue);
        switch (instance.get(7) - 1) {
            case 0:
                str2 = "日";
                break;
            case 1:
                str2 = "一";
                break;
            case 2:
                str2 = "二";
                break;
            case 3:
                str2 = "三";
                break;
            case 4:
                str2 = "四";
                break;
            case 5:
                str2 = "五";
                break;
            case 6:
                str2 = "六";
                break;
        }
        return "周" + str2;
    }

    public static int getDayofWeekIndex() {
        Calendar instance = Calendar.getInstance();
        instance.setTime(new Date());
        return instance.get(7) - 1;
    }

    public static String getTimeStrByLong(String str) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        Long valueOf = Long.valueOf(gregorianCalendar.getTime().getTime());
        Long valueOf2 = Long.valueOf(str);
        gregorianCalendar.setTimeInMillis(valueOf2.longValue() * 1000);
        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(gregorianCalendar.getTime());
        Long valueOf3 = Long.valueOf(valueOf.longValue() - (valueOf2.longValue() * 1000));
        Long valueOf4 = Long.valueOf("86400000");
        Long valueOf5 = Long.valueOf("3600000");
        Long valueOf6 = Long.valueOf("60000");
        if (valueOf3.longValue() >= valueOf4.longValue() * 30) {
            String str2 = "" + Long.valueOf(valueOf3.longValue() / (valueOf4.longValue() * 30)) + "月";
            Long.valueOf(valueOf3.longValue() % (valueOf4.longValue() * 30));
            return str2 + "前";
        } else if (valueOf3.longValue() >= valueOf4.longValue() * 7) {
            String str3 = "" + Long.valueOf(valueOf3.longValue() / (valueOf4.longValue() * 7)) + "周";
            Long.valueOf(valueOf3.longValue() % (valueOf4.longValue() * 7));
            return str3 + "前";
        } else if (valueOf3.longValue() >= valueOf4.longValue()) {
            String str4 = "" + Long.valueOf(valueOf3.longValue() / valueOf4.longValue()) + "天";
            Long.valueOf(valueOf3.longValue() % valueOf4.longValue());
            return str4 + "前";
        } else if (valueOf3.longValue() >= valueOf5.longValue()) {
            String str5 = "" + Long.valueOf(valueOf3.longValue() / valueOf5.longValue()) + "小时";
            Long.valueOf(valueOf3.longValue() % valueOf5.longValue());
            return str5 + "前";
        } else if (valueOf3.longValue() < valueOf6.longValue()) {
            return "";
        } else {
            return ("" + Long.valueOf(valueOf3.longValue() / valueOf6.longValue()) + "分钟") + "前";
        }
    }

    public static String getTodayZh(String str) {
        if (new SimpleDateFormat("yyyy-MM-dd").format(new GregorianCalendar().getTime()).equals(str)) {
            return "今天";
        }
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.add(5, 1);
        if (new SimpleDateFormat("yyyy-MM-dd").format(gregorianCalendar.getTime()).equals(str)) {
            return "明天";
        }
        GregorianCalendar gregorianCalendar2 = new GregorianCalendar();
        gregorianCalendar2.add(5, 2);
        return new SimpleDateFormat("yyyy-MM-dd").format(gregorianCalendar2.getTime()).equals(str) ? "后天" : "";
    }

    public static boolean isDeadLine(String str) {
        try {
            if (System.currentTimeMillis() - new SimpleDateFormat("yyyy-MM-dd").parse(str).getTime() > 0) {
                return true;
            }
            return false;
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean isToday(String str, String str2) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str2);
            if (simpleDateFormat.format(simpleDateFormat.parse(str)).equals(simpleDateFormat.format(new Date()))) {
                return true;
            }
            return false;
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String changeFormater(String str, String str2, String str3) {
        if ("".equals(str)) {
            return "";
        }
        try {
            return new SimpleDateFormat(str3).format(new SimpleDateFormat(str2).parse(str));
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static List<WeekDateObj> getWeekDateList() {
        String str;
        ArrayList arrayList = new ArrayList();
        Calendar instance = Calendar.getInstance();
        instance.setTime(new Date());
        int i = instance.get(7) - 1;
        System.out.println(i);
        for (int i2 = i; i2 < i + 7; i2++) {
            int i3 = i2 % 7;
            int i4 = 0;
            if (i2 != i) {
                i4 = 1;
            }
            instance.add(5, i4);
            switch (i3) {
                case 0:
                    str = "日";
                    break;
                case 1:
                    str = "一";
                    break;
                case 2:
                    str = "二";
                    break;
                case 3:
                    str = "三";
                    break;
                case 4:
                    str = "四";
                    break;
                case 5:
                    str = "五";
                    break;
                case 6:
                    str = "六";
                    break;
                default:
                    str = "";
                    break;
            }
            String format = new SimpleDateFormat("yyyy-MM-dd").format(instance.getTime());
            arrayList.add(new WeekDateObj("周" + str, format));
        }
        return arrayList;
    }

    public static String getNextWeekDayStrNew() {
        StringBuffer stringBuffer = new StringBuffer();
        Calendar instance = Calendar.getInstance();
        instance.setTime(new Date());
        instance.add(5, 7);
        int i = instance.get(5);
        stringBuffer.append(i + "日");
        stringBuffer.append(MapFragment.SLASH);
        instance.add(5, 6);
        int i2 = instance.get(5);
        stringBuffer.append(i2 + "日");
        return stringBuffer.toString();
    }

    public static String getCurWeekDayStrNew() {
        StringBuffer stringBuffer = new StringBuffer();
        Calendar instance = Calendar.getInstance();
        instance.setTime(new Date());
        int i = instance.get(5);
        stringBuffer.append(i + "日");
        stringBuffer.append(MapFragment.SLASH);
        instance.add(5, 6);
        int i2 = instance.get(5);
        stringBuffer.append(i2 + "日");
        return stringBuffer.toString();
    }

    public static String diffCurTime(String str, String str2) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            long time = simpleDateFormat.parse(str2).getTime() - simpleDateFormat.parse(str).getTime();
            long j = time / org.apache.commons.lang3.time.DateUtils.MILLIS_PER_DAY;
            long j2 = 24 * j;
            long j3 = (time / org.apache.commons.lang3.time.DateUtils.MILLIS_PER_HOUR) - j2;
            long j4 = j2 * 60;
            long j5 = j3 * 60;
            long j6 = ((time / 60000) - j4) - j5;
            long j7 = time / 1000;
            Long.signum(j4);
            long j8 = ((j7 - (j4 * 60)) - (j5 * 60)) - (60 * j6);
            StringBuffer stringBuffer = new StringBuffer();
            if (j > 0) {
                stringBuffer.append(j + "天");
            }
            if (j3 > 0) {
                stringBuffer.append(j3 + "小时");
            }
            if (j6 > 0) {
                stringBuffer.append(j6 + "分");
            }
            if (j8 > 0) {
                stringBuffer.append(j8 + "秒");
            }
            return stringBuffer.toString();
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static class WeekDateObj {
        private String date;
        private String week;

        public String getWeek() {
            return this.week;
        }

        public void setWeek(String str) {
            this.week = str;
        }

        public String getDate() {
            return this.date;
        }

        public void setDate(String str) {
            this.date = str;
        }

        public WeekDateObj(String str, String str2) {
            this.week = str;
            this.date = str2;
        }

        public String toString() {
            return "SelectSeatGalleryObj [week=" + this.week + ", date=" + this.date + "]";
        }
    }

    public static Date strToDateLong(String str) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(str, new ParsePosition(0));
    }

    public static boolean isCurrentInTimeScope(int i, int i2, int i3, int i4) {
        long currentTimeMillis = System.currentTimeMillis();
        Time time = new Time();
        time.set(currentTimeMillis);
        Time time2 = new Time();
        time2.set(currentTimeMillis);
        time2.hour = i;
        time2.minute = i2;
        Time time3 = new Time();
        time3.set(currentTimeMillis);
        time3.hour = i3;
        time3.minute = i4;
        boolean z = false;
        if (!time2.before(time3)) {
            time2.set(time2.toMillis(true) - org.apache.commons.lang3.time.DateUtils.MILLIS_PER_DAY);
            if (!time.before(time2) && !time.after(time3)) {
                z = true;
            }
            Time time4 = new Time();
            time4.set(time2.toMillis(true) + org.apache.commons.lang3.time.DateUtils.MILLIS_PER_DAY);
            if (!time.before(time4)) {
                return true;
            }
        } else if (!time.before(time2) && !time.after(time3)) {
            z = true;
        }
        return z;
    }

    public static int[] getHourAndMinute(String str) {
        int[] iArr = new int[2];
        if (MyStringUtils.isEmpty(str)) {
            return null;
        }
        String[] split = str.split(":");
        iArr[0] = Integer.parseInt(split[0]);
        iArr[1] = Integer.parseInt(split[1]);
        return iArr;
    }

    public static String plusDay(int i, String str) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        simpleDateFormat.parse(str);
        Calendar instance = Calendar.getInstance();
        instance.add(5, i);
        return simpleDateFormat.format(instance.getTime());
    }

    public static String formatTime(long j) {
        if (j <= 0 || j >= org.apache.commons.lang3.time.DateUtils.MILLIS_PER_DAY) {
            return "00:00";
        }
        long j2 = j / 1000;
        long j3 = j2 % 60;
        long j4 = (j2 / 60) % 60;
        long j5 = j2 / 3600;
        Formatter formatter = new Formatter(new StringBuilder(), Locale.getDefault());
        if (j5 > 0) {
            return formatter.format("%d:%02d:%02d", new Object[]{Long.valueOf(j5), Long.valueOf(j4), Long.valueOf(j3)}).toString();
        }
        return formatter.format("%02d:%02d", new Object[]{Long.valueOf(j4), Long.valueOf(j3)}).toString();
    }

    public static String getToday() {
        return new SimpleDateFormat("MM-dd", Locale.getDefault()).format(new Date());
    }

    public static String getToday(String str) {
        return new SimpleDateFormat(str, Locale.getDefault()).format(new Date());
    }

    public static String getCurrentHour() {
        return new SimpleDateFormat("HH", Locale.getDefault()).format(new Date());
    }

    public static String getCurrentTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()).format(new Date());
    }

    public static String getNowTimeStr(Date date) {
        return new SimpleDateFormat("HH:mm", Locale.getDefault()).format(date);
    }

    public static String formatWeek(Date date) {
        switch (getWeekOfDate(date)) {
            case 1:
                return "星期一";
            case 2:
                return "星期二";
            case 3:
                return "星期三";
            case 4:
                return "星期四";
            case 5:
                return "星期五";
            case 6:
                return "星期六";
            case 7:
                return "星期天";
            default:
                return "";
        }
    }

    public static int getWeekOfDate(Date date) {
        int[] iArr = {7, 1, 2, 3, 4, 5, 6};
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        int i = instance.get(7) - 1;
        if (i < 0) {
            i = 0;
        }
        return iArr[i];
    }

    public static boolean isEffectiveDate(Date date, Date date2, Date date3) {
        if (date.getTime() == date2.getTime() || date.getTime() == date3.getTime()) {
            return true;
        }
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        Calendar instance2 = Calendar.getInstance();
        instance2.setTime(date2);
        Calendar instance3 = Calendar.getInstance();
        instance3.setTime(date3);
        if (!instance.after(instance2) || !instance.before(instance3)) {
            return false;
        }
        return true;
    }
}
