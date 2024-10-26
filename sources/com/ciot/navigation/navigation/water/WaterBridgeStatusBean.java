package com.ciot.navigation.navigation.water;

public class WaterBridgeStatusBean {
    public String command;
    public String error_message;
    public Results results;
    public String status;

    public static class Results {
        public Object output;
        public int status;
    }
}
