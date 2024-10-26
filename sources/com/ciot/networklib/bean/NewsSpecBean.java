package com.ciot.networklib.bean;

public class NewsSpecBean {
    private String alias;
    private String contents;
    private String editor;
    private String externalLink;
    private boolean hotspot;
    private String title;
    private long updateTime;
    private String url;

    public String getEditor() {
        return this.editor;
    }

    public void setEditor(String str) {
        this.editor = str;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getAlias() {
        String str = this.alias;
        return str == null ? "" : str;
    }

    public void setAlias(String str) {
        this.alias = str;
    }

    public boolean isHotspot() {
        return this.hotspot;
    }

    public void setHotspot(boolean z) {
        this.hotspot = z;
    }

    public String getContents() {
        return this.contents;
    }

    public void setContents(String str) {
        this.contents = str;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public String getExternalLink() {
        return this.externalLink;
    }

    public void setExternalLink(String str) {
        this.externalLink = str;
    }

    public long getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(long j) {
        this.updateTime = j;
    }
}
