package com.ciot.networklib.bean;

public class ArcSoftActiveInfo {
    public CodeDTO code;
    public String createtime;
    public String id;
    public String mac;
    public String robot;
    public String updatetime;

    public static class CodeDTO {
        public String active;
        public String app;
        public String key;
    }
}
