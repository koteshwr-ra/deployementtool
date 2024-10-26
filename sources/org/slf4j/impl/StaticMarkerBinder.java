package org.slf4j.impl;

import org.slf4j.IMarkerFactory;
import org.slf4j.helpers.BasicMarkerFactory;
import org.slf4j.spi.MarkerFactoryBinder;

public class StaticMarkerBinder implements MarkerFactoryBinder {
    public static final StaticMarkerBinder SINGLETON = new StaticMarkerBinder();
    static /* synthetic */ Class class$org$slf4j$helpers$BasicMarkerFactory;
    final IMarkerFactory markerFactory = new BasicMarkerFactory();

    private StaticMarkerBinder() {
    }

    public IMarkerFactory getMarkerFactory() {
        return this.markerFactory;
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    public String getMarkerFactoryClassStr() {
        Class cls = class$org$slf4j$helpers$BasicMarkerFactory;
        if (cls == null) {
            cls = class$("org.slf4j.helpers.BasicMarkerFactory");
            class$org$slf4j$helpers$BasicMarkerFactory = cls;
        }
        return cls.getName();
    }
}
