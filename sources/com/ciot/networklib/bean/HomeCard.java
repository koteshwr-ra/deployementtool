package com.ciot.networklib.bean;

import android.text.TextUtils;
import java.util.List;
import java.util.Objects;

public class HomeCard {
    public String backgroundurl;
    public String card_name;
    public DataBean data;
    public boolean function_available;
    public String function_desc;
    public String function_key;
    private String function_name;
    public String function_type;

    public static class DataBean {
        public List<Classes> classes;
        public String isTop;
        public String label;

        public static class Classes {
            public List<String> ancestors;
            public String className;
            public String color;
            public long createTime;
            public String id;
            public String parentId;
            public String project;
            public long updateTime;
        }
    }

    public String getFunction_name() {
        if (!TextUtils.isEmpty(this.card_name)) {
            return this.card_name;
        }
        DataBean dataBean = this.data;
        if (dataBean == null) {
            this.card_name = this.function_name;
        } else if (!TextUtils.isEmpty(dataBean.label)) {
            this.card_name = this.data.label;
        } else if (this.data.classes == null || this.data.classes.size() <= 0) {
            this.card_name = this.function_name;
        } else {
            String str = this.data.classes.get(0).className;
            if (!TextUtils.isEmpty(str)) {
                this.card_name = str;
            }
        }
        return this.card_name;
    }

    public void setFunction_name(String str) {
        this.function_name = str;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        HomeCard homeCard = (HomeCard) obj;
        homeCard.getFunction_name();
        if (this.function_available != homeCard.function_available || !Objects.equals(this.backgroundurl, homeCard.backgroundurl) || !Objects.equals(this.function_desc, homeCard.function_desc) || !Objects.equals(this.function_key, homeCard.function_key) || !Objects.equals(this.function_name, homeCard.function_name) || !Objects.equals(this.function_type, homeCard.function_type) || !Objects.equals(this.card_name, homeCard.card_name)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        getFunction_name();
        return Objects.hash(new Object[]{this.backgroundurl, Boolean.valueOf(this.function_available), this.function_desc, this.function_key, this.function_name, this.function_type, this.card_name});
    }

    public String toString() {
        getFunction_name();
        return "HomeCard{backgroundurl='" + this.backgroundurl + '\'' + ", function_available=" + this.function_available + ", function_desc='" + this.function_desc + '\'' + ", function_key='" + this.function_key + '\'' + ", function_name='" + this.function_name + '\'' + ", function_type='" + this.function_type + '\'' + ", card_name='" + this.card_name + '\'' + '}';
    }
}
