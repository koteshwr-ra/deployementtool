package com.ciot.navigation.navigation.water;

public class WaterHotspotBean {
    public String command;
    public String error_message;
    public Result results;
    public String status;
    public String uuid;

    public static class Result {
        public String output;
        public int status;
    }
}
