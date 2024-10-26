package org.apache.log4j;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;

public class Level extends Priority implements Serializable {
    public static final Level ALL = new Level(Integer.MIN_VALUE, "ALL", 7);
    public static final Level DEBUG = new Level(10000, "DEBUG", 7);
    public static final Level ERROR = new Level(Priority.ERROR_INT, "ERROR", 3);
    public static final Level FATAL = new Level(Priority.FATAL_INT, "FATAL", 0);
    public static final Level INFO = new Level(20000, "INFO", 6);
    public static final Level OFF = new Level(Integer.MAX_VALUE, "OFF", 0);
    public static final Level TRACE = new Level(5000, "TRACE", 7);
    public static final int TRACE_INT = 5000;
    public static final Level WARN = new Level(30000, "WARN", 4);
    static /* synthetic */ Class class$org$apache$log4j$Level = null;
    static final long serialVersionUID = 3491141966387921974L;

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    protected Level(int i, String str, int i2) {
        super(i, str, i2);
    }

    public static Level toLevel(String str) {
        return toLevel(str, DEBUG);
    }

    public static Level toLevel(int i) {
        return toLevel(i, DEBUG);
    }

    public static Level toLevel(int i, Level level) {
        if (i == Integer.MIN_VALUE) {
            return ALL;
        }
        if (i == 5000) {
            return TRACE;
        }
        if (i == 10000) {
            return DEBUG;
        }
        if (i == 20000) {
            return INFO;
        }
        if (i == 30000) {
            return WARN;
        }
        if (i == 40000) {
            return ERROR;
        }
        if (i != 50000) {
            return i != Integer.MAX_VALUE ? level : OFF;
        }
        return FATAL;
    }

    public static Level toLevel(String str, Level level) {
        if (str == null) {
            return level;
        }
        String upperCase = str.toUpperCase();
        if (upperCase.equals("ALL")) {
            return ALL;
        }
        if (upperCase.equals("DEBUG")) {
            return DEBUG;
        }
        if (upperCase.equals("INFO")) {
            return INFO;
        }
        if (upperCase.equals("WARN")) {
            return WARN;
        }
        if (upperCase.equals("ERROR")) {
            return ERROR;
        }
        if (upperCase.equals("FATAL")) {
            return FATAL;
        }
        if (upperCase.equals("OFF")) {
            return OFF;
        }
        return upperCase.equals("TRACE") ? TRACE : level;
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        this.level = objectInputStream.readInt();
        this.syslogEquivalent = objectInputStream.readInt();
        this.levelStr = objectInputStream.readUTF();
        if (this.levelStr == null) {
            this.levelStr = "";
        }
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeInt(this.level);
        objectOutputStream.writeInt(this.syslogEquivalent);
        objectOutputStream.writeUTF(this.levelStr);
    }

    private Object readResolve() throws ObjectStreamException {
        Class<?> cls = getClass();
        Class<?> cls2 = class$org$apache$log4j$Level;
        if (cls2 == null) {
            cls2 = class$("org.apache.log4j.Level");
            class$org$apache$log4j$Level = cls2;
        }
        return cls == cls2 ? toLevel(this.level) : this;
    }
}
