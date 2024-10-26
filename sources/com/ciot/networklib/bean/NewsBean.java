package com.ciot.networklib.bean;

import com.ciot.networklib.util.TimeUtils;

public class NewsBean {
    public String alias;
    public long createTime;
    public String editor;
    public boolean hotspot;
    public String id;
    public String title;
    public long updateTime;
    public String url;

    public String getUpdateTime() {
        return TimeUtils.getYearMonthDayHourMinuteSecond(this.updateTime);
    }

    public void setUpdateTime(long j) {
        this.updateTime = j;
    }
}
