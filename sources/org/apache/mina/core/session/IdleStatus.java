package org.apache.mina.core.session;

public class IdleStatus {
    public static final IdleStatus BOTH_IDLE = new IdleStatus("both idle");
    public static final IdleStatus READER_IDLE = new IdleStatus("reader idle");
    public static final IdleStatus WRITER_IDLE = new IdleStatus("writer idle");
    private final String strValue;

    private IdleStatus(String str) {
        this.strValue = str;
    }

    public String toString() {
        return this.strValue;
    }
}
