package com.ciot.navigation.navigation.task.turnstile;

public class OpenTurnstileBean {
    private String host;
    private String id;
    private int port;
    private int time;

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public String getHost() {
        return this.host;
    }

    public void setHost(String str) {
        this.host = str;
    }

    public int getPort() {
        return this.port;
    }

    public void setPort(int i) {
        this.port = i;
    }

    public int getTime() {
        return this.time;
    }

    public void setTime(int i) {
        this.time = i;
    }
}
