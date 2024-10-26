package com.android.jni.ssrd;

public class SNTool {
    public native boolean Clear();

    public native String GetLibVersion();

    public native String Read(int i, int i2);

    public native String ReadMotherBoardSN();

    public native boolean Write(String str, int i);

    static {
        System.loadLibrary("SerialNumber");
    }
}
