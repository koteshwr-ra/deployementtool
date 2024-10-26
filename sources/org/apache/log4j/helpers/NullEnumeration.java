package org.apache.log4j.helpers;

import java.util.Enumeration;
import java.util.NoSuchElementException;

public class NullEnumeration implements Enumeration {
    private static final NullEnumeration instance = new NullEnumeration();

    public boolean hasMoreElements() {
        return false;
    }

    private NullEnumeration() {
    }

    public static NullEnumeration getInstance() {
        return instance;
    }

    public Object nextElement() {
        throw new NoSuchElementException();
    }
}
