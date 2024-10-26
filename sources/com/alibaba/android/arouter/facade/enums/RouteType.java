package com.alibaba.android.arouter.facade.enums;

import com.alibaba.android.arouter.compiler.utils.Consts;

public enum RouteType {
    ACTIVITY(0, Consts.ACTIVITY),
    SERVICE(1, Consts.SERVICE),
    PROVIDER(2, Consts.IPROVIDER),
    CONTENT_PROVIDER(-1, "android.app.ContentProvider"),
    BOARDCAST(-1, ""),
    METHOD(-1, ""),
    FRAGMENT(-1, Consts.FRAGMENT),
    UNKNOWN(-1, "Unknown route type");
    
    String className;
    int id;

    public int getId() {
        return this.id;
    }

    public RouteType setId(int i) {
        this.id = i;
        return this;
    }

    public String getClassName() {
        return this.className;
    }

    public RouteType setClassName(String str) {
        this.className = str;
        return this;
    }

    private RouteType(int i, String str) {
        this.id = i;
        this.className = str;
    }

    public static RouteType parse(String str) {
        for (RouteType routeType : values()) {
            if (routeType.getClassName().equals(str)) {
                return routeType;
            }
        }
        return UNKNOWN;
    }
}
