package com.example.sroslibrary.monitor.monitor.result;

import com.google.gson.annotations.Expose;
import java.util.List;

public class PlaybackQueryResultBean {
    @Expose
    private String reason;
    @Expose
    private List<Record> records;
    @Expose
    private boolean result;

    public PlaybackQueryResultBean(List<Record> list) {
        this.records = list;
    }

    public void setReason(String str) {
        this.reason = str;
    }

    public void setResult(boolean z) {
        this.result = z;
    }

    public static class Record {
        @Expose
        private long begin;
        @Expose
        private long end;
        @Expose
        private String name;
        @Expose
        private int type;

        public Record(String str, long j, long j2, int i) {
            this.name = str;
            this.begin = j;
            this.end = j2;
            this.type = i;
        }
    }
}
