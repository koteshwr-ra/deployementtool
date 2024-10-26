package mc.csst.com.selfchassis.utils.enumbs;

import android.text.TextUtils;

public enum ConvertChassisAreaType {
    TYPE_SLOW_ZONE("layer_slow_area", 8),
    TYPE_WORKING_ZONE("layer_work_area", 7),
    TYPE_STRONG_LIGHT_ZONE("layer_strong_light_area", 5),
    TYPE_DANGER_ZONE("layer_danger_area", 4),
    TYPE_NARROW_CHANNEL("layer_narrow_channel", 7),
    TYPE_SLOPE_ZONE("layer_slope_area", 6),
    TYPE_TAG_ZONE("layer_label_area", 3);
    
    private String areaType;
    private int chassisAreaType;

    public static int getChassisAreaTypeByToolType(String str) {
        for (ConvertChassisAreaType convertChassisAreaType : values()) {
            if (TextUtils.equals(convertChassisAreaType.getAreaType(), str)) {
                return convertChassisAreaType.getChassisAreaType();
            }
        }
        return -1;
    }

    public static String getToolTypeByChassisAreaType(int i) {
        for (ConvertChassisAreaType convertChassisAreaType : values()) {
            if (convertChassisAreaType.getChassisAreaType() == i) {
                return convertChassisAreaType.getAreaType();
            }
        }
        return "";
    }

    public static boolean isEqualLeftToolType(String str) {
        for (ConvertChassisAreaType areaType2 : values()) {
            if (TextUtils.equals(areaType2.getAreaType(), str)) {
                return true;
            }
        }
        return false;
    }

    private ConvertChassisAreaType(String str, int i) {
        this.areaType = str;
        this.chassisAreaType = i;
    }

    public String getAreaType() {
        return this.areaType;
    }

    public void setAreaType(String str) {
        this.areaType = str;
    }

    public int getChassisAreaType() {
        return this.chassisAreaType;
    }

    public void setChassisAreaType(int i) {
        this.chassisAreaType = i;
    }
}
