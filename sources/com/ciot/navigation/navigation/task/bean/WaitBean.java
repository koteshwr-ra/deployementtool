package com.ciot.navigation.navigation.task.bean;

import java.io.Serializable;

public class WaitBean implements Serializable {
    public static final int TYPE_ASK_WAY = 2;
    public static final int TYPE_GUIDE = 1;
    public static final int TYPE_INTRODUCE = 0;
    private static final long serialVersionUID = 3222019352694438330L;
    private String title;
    private int type;

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }

    public String toString() {
        return "WaitBean{title='" + this.title + '\'' + ", type=" + this.type + '}';
    }
}
