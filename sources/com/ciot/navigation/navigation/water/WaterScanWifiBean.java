package com.ciot.navigation.navigation.water;

import java.util.List;

public class WaterScanWifiBean {
    public String command;
    public String error_message;
    public Result results;
    public String status;
    public String uuid;

    public static class Result {
        public List<Output> output;
        public int status;

        public static class Output {
            public boolean active;
            public String encrypt;
            public boolean security;
            public int signal;
            public String ssid;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj == null || getClass() != obj.getClass()) {
                    return false;
                }
                String str = this.ssid;
                String str2 = ((Output) obj).ssid;
                if (str != null) {
                    return str.equals(str2);
                }
                if (str2 == null) {
                    return true;
                }
                return false;
            }

            public int hashCode() {
                String str = this.ssid;
                if (str != null) {
                    return str.hashCode();
                }
                return 0;
            }
        }
    }
}
