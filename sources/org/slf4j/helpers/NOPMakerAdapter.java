package org.slf4j.helpers;

import java.util.Map;
import org.slf4j.spi.MDCAdapter;

public class NOPMakerAdapter implements MDCAdapter {
    public void clear() {
    }

    public String get(String str) {
        return null;
    }

    public Map getCopyOfContextMap() {
        return null;
    }

    public void put(String str, String str2) {
    }

    public void remove(String str) {
    }

    public void setContextMap(Map map) {
    }
}
