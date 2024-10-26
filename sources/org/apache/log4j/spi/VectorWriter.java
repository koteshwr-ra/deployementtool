package org.apache.log4j.spi;

import java.io.PrintWriter;
import java.util.Vector;

/* compiled from: ThrowableInformation */
class VectorWriter extends PrintWriter {
    private Vector v = new Vector();

    VectorWriter() {
        super(new NullWriter());
    }

    public void print(Object obj) {
        this.v.addElement(obj.toString());
    }

    public void print(char[] cArr) {
        this.v.addElement(new String(cArr));
    }

    public void print(String str) {
        this.v.addElement(str);
    }

    public void println(Object obj) {
        this.v.addElement(obj.toString());
    }

    public void println(char[] cArr) {
        this.v.addElement(new String(cArr));
    }

    public void println(String str) {
        this.v.addElement(str);
    }

    public void write(char[] cArr) {
        this.v.addElement(new String(cArr));
    }

    public void write(char[] cArr, int i, int i2) {
        this.v.addElement(new String(cArr, i, i2));
    }

    public void write(String str, int i, int i2) {
        this.v.addElement(str.substring(i, i2 + i));
    }

    public void write(String str) {
        this.v.addElement(str);
    }

    public String[] toStringArray() {
        int size = this.v.size();
        String[] strArr = new String[size];
        for (int i = 0; i < size; i++) {
            strArr[i] = (String) this.v.elementAt(i);
        }
        return strArr;
    }
}
