package org.slf4j.helpers;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import org.slf4j.Marker;

public class BasicMarker implements Marker {
    private static String CLOSE = " ]";
    private static String OPEN = "[ ";
    private static String SEP = ", ";
    private static final long serialVersionUID = 1803952589649545191L;
    private final String name;
    private List refereceList;

    BasicMarker(String str) {
        if (str != null) {
            this.name = str;
            return;
        }
        throw new IllegalArgumentException("A merker name cannot be null");
    }

    public String getName() {
        return this.name;
    }

    public synchronized void add(Marker marker) {
        if (marker == null) {
            throw new IllegalArgumentException("A null value cannot be added to a Marker as reference.");
        } else if (!contains(marker)) {
            if (!marker.contains((Marker) this)) {
                if (this.refereceList == null) {
                    this.refereceList = new Vector();
                }
                this.refereceList.add(marker);
            }
        }
    }

    public synchronized boolean hasReferences() {
        return this.refereceList != null && this.refereceList.size() > 0;
    }

    public boolean hasChildren() {
        return hasReferences();
    }

    public synchronized Iterator iterator() {
        if (this.refereceList != null) {
            return this.refereceList.iterator();
        }
        return Collections.EMPTY_LIST.iterator();
    }

    public synchronized boolean remove(Marker marker) {
        if (this.refereceList == null) {
            return false;
        }
        int size = this.refereceList.size();
        for (int i = 0; i < size; i++) {
            if (marker.equals((Marker) this.refereceList.get(i))) {
                this.refereceList.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean contains(Marker marker) {
        if (marker == null) {
            throw new IllegalArgumentException("Other cannot be null");
        } else if (equals(marker)) {
            return true;
        } else {
            if (hasReferences()) {
                for (int i = 0; i < this.refereceList.size(); i++) {
                    if (((Marker) this.refereceList.get(i)).contains(marker)) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    public boolean contains(String str) {
        if (str == null) {
            throw new IllegalArgumentException("Other cannot be null");
        } else if (this.name.equals(str)) {
            return true;
        } else {
            if (hasReferences()) {
                for (int i = 0; i < this.refereceList.size(); i++) {
                    if (((Marker) this.refereceList.get(i)).contains(str)) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && (obj instanceof Marker)) {
            return this.name.equals(((Marker) obj).getName());
        }
        return false;
    }

    public int hashCode() {
        return this.name.hashCode();
    }

    public String toString() {
        if (!hasReferences()) {
            return getName();
        }
        Iterator it = iterator();
        StringBuffer stringBuffer = new StringBuffer(getName());
        stringBuffer.append(' ');
        stringBuffer.append(OPEN);
        while (it.hasNext()) {
            stringBuffer.append(((Marker) it.next()).getName());
            if (it.hasNext()) {
                stringBuffer.append(SEP);
            }
        }
        stringBuffer.append(CLOSE);
        return stringBuffer.toString();
    }
}
