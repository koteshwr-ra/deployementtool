package com.ciot.networklib.bean;

import java.util.List;

public class RegistrationType {
    public String account;
    public boolean advertisement;
    public String aucdk;
    public String backgroundurl;
    public String blackNotify;
    public List<FunclistDTO> funclist;
    public String guideWord;
    public List<?> healthcodeSwitch;
    public List<?> homeCards;
    public String id;
    public List<NotifyTimesDTO> notifyTimes;
    public boolean realtimeUpload;
    public double temperature;
    public List<?> timedTasks;
    public int validity;
    public String video;
    public String vipNotify;

    public static class NotifyTimesDTO {
        public String begin;
        public String description;
        public boolean enable;
        public String end;
    }

    public static class FunclistDTO {
        public String backgroundurl;
        public boolean function_available;
        public String function_desc;
        public int function_id;
        public String function_key;
        public String function_name;
        public String function_type;
        public boolean isCheck = false;

        public String toString() {
            return "FunclistDTO{function_id=" + this.function_id + ", function_key='" + this.function_key + '\'' + ", function_type='" + this.function_type + '\'' + ", function_name='" + this.function_name + '\'' + ", function_desc='" + this.function_desc + '\'' + ", function_available=" + this.function_available + ", backgroundurl='" + this.backgroundurl + '\'' + ", isCheck=" + this.isCheck + '}';
        }
    }
}
