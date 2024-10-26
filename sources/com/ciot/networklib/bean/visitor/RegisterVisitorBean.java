package com.ciot.networklib.bean.visitor;

public class RegisterVisitorBean {
    public String company;
    public int locale = 3;
    public String robot;
    public SettingsDTO settings;
    public int type = 3;
    public VisitorDTO visitor;

    public RegisterVisitorBean(String str, String str2) {
        this.company = str;
        this.robot = str2;
    }

    public static class VisitorDTO {
        public String company;
        public String idcard;
        public String name;
        public String phone;

        public VisitorDTO(String str, String str2, String str3, String str4) {
            this.name = str;
            this.company = str2;
            this.idcard = str3;
            this.phone = str4;
        }
    }

    public static class SettingsDTO {
        public String company;
        private String name = "现场登记";
        private int security = 1;
        private String type = "temp";

        public SettingsDTO(String str) {
            this.company = str;
        }
    }
}
