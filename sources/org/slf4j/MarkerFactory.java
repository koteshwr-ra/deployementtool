package org.slf4j;

import org.slf4j.helpers.Util;
import org.slf4j.impl.StaticMarkerBinder;

public class MarkerFactory {
    static IMarkerFactory markerFactory;

    private MarkerFactory() {
    }

    static {
        try {
            markerFactory = StaticMarkerBinder.SINGLETON.getMarkerFactory();
        } catch (Exception e) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Could not instantiate instance of class [");
            stringBuffer.append(StaticMarkerBinder.SINGLETON.getMarkerFactoryClassStr());
            stringBuffer.append("]");
            Util.reportFailure(stringBuffer.toString(), e);
        }
    }

    public static Marker getMarker(String str) {
        return markerFactory.getMarker(str);
    }

    public static Marker getDetachedMarker(String str) {
        return markerFactory.getDetachedMarker(str);
    }

    public static IMarkerFactory getIMarkerFactory() {
        return markerFactory;
    }
}
