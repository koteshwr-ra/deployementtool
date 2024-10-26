package com.ciot.base.util;

import com.android.jni.ssrd.SNTool;

public class EepromUtils {
    private static final int MAX_DATA_LENTH = 10086;
    public static final int SERIAL_NUMBER_OFFSET = 0;
    private static SNTool tool;

    public static String readMotherBoardSn() {
        if (tool == null) {
            tool = new SNTool();
        }
        return tool.ReadMotherBoardSN();
    }

    public static boolean writeData(int i, String str) {
        if (tool == null) {
            tool = new SNTool();
        }
        return tool.Write(str, i);
    }

    public static boolean clear() {
        if (tool == null) {
            tool = new SNTool();
        }
        return tool.Clear();
    }

    public static String getLibVersion() {
        if (tool == null) {
            tool = new SNTool();
        }
        return tool.GetLibVersion();
    }
}
