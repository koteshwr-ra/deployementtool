package com.ciot.networklib;

import java.util.List;

public class NearbyAttractList {
    public List<NearbyAttract> common;
    public List<NearbyAttract> identity;
    public List<NearbyAttract> specific;

    public static class NearbyAttract {
        public List<String> contents;
        public String groupName;
        public String personId;
        public String personName;
        public List<String> personType;
        public List<String> sex;
        public List<String> welcomeTime;

        public String toString() {
            return "NearbyAttract{personName='" + this.personName + '\'' + ", groupName='" + this.groupName + '\'' + ", personType=" + this.personType + ", personId='" + this.personId + '\'' + ", welcomeTime=" + this.welcomeTime + ", sex=" + this.sex + ", contents=" + this.contents + '}';
        }
    }
}
