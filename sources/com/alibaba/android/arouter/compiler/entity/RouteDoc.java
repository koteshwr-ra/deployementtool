package com.alibaba.android.arouter.compiler.entity;

import com.alibaba.fastjson.annotation.JSONField;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

public class RouteDoc {
    @JSONField(ordinal = 5)
    private String className;
    @JSONField(ordinal = 3)
    private String description;
    @JSONField(ordinal = 1)
    private String group;
    @JSONField(ordinal = 7)
    private int mark;
    @JSONField(ordinal = 8)
    private List<Param> params;
    @JSONField(ordinal = 2)
    private String path;
    @JSONField(ordinal = 4)
    private String prototype;
    @JSONField(ordinal = 6)
    private String type;

    public String getGroup() {
        return this.group;
    }

    public void setGroup(String str) {
        this.group = str;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String str) {
        this.path = str;
    }

    public String getPrototype() {
        return this.prototype;
    }

    public void setPrototype(String str) {
        this.prototype = str;
    }

    public void addPrototype(String str) {
        if (StringUtils.isNotEmpty(getPrototype())) {
            setPrototype(str);
            return;
        }
        setPrototype(getPrototype() + ", " + str);
    }

    public String getClassName() {
        return this.className;
    }

    public void setClassName(String str) {
        this.className = str;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        if (StringUtils.isNotEmpty(str)) {
            this.description = str;
        }
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public int getMark() {
        return this.mark;
    }

    public void setMark(int i) {
        this.mark = i;
    }

    public List<Param> getParams() {
        return this.params;
    }

    public void setParams(List<Param> list) {
        this.params = list;
    }

    public static class Param {
        @JSONField(ordinal = 3)
        private String description;
        @JSONField(ordinal = 1)
        private String key;
        @JSONField(ordinal = 4)
        private boolean required;
        @JSONField(ordinal = 2)
        private String type;

        public String getKey() {
            return this.key;
        }

        public void setKey(String str) {
            this.key = str;
        }

        public String getType() {
            return this.type;
        }

        public void setType(String str) {
            this.type = str;
        }

        public String getDescription() {
            return this.description;
        }

        public void setDescription(String str) {
            if (StringUtils.isNotEmpty(str)) {
                this.description = str;
            }
        }

        public boolean isRequired() {
            return this.required;
        }

        public void setRequired(boolean z) {
            this.required = z;
        }
    }
}
