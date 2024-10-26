package com.ciot.base.config;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u000f\n\u0002\u0010\u000e\n\u0002\b\u000f\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u00020\tX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u000e\u0010\u000e\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u001a\u0010\u0012\u001a\u00020\tX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u000b\"\u0004\b\u0014\u0010\rR\u0011\u0010\u0015\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u000e\u0010\u0018\u001a\u00020\u0019XT¢\u0006\u0002\n\u0000R\u0011\u0010\u001a\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u0017R\u0012\u0010\u001c\u001a\u00020\t8\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u001d\u001a\u00020\tX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u000b\"\u0004\b\u001f\u0010\rR\u000e\u0010 \u001a\u00020\u0019XT¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u0019XT¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u0019XT¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u0019XT¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\u0019XT¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\u0019XT¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020\u0019XT¢\u0006\u0002\n\u0000¨\u0006("}, d2 = {"Lcom/ciot/base/config/NavigationConfig;", "", "()V", "CHARGE_STATE_CHARGING", "", "CHARGE_STATE_CHARGING_FAILED", "CHARGE_STATE_DISCONNECT", "CHARGE_STATE_RECHARGING", "DATA_SOURCE_IS_SDCARD", "", "getDATA_SOURCE_IS_SDCARD", "()Z", "setDATA_SOURCE_IS_SDCARD", "(Z)V", "EVENT_STATE_CANCEL", "EVENT_STATE_CANTINUE", "EVENT_STATE_IDLE", "EVENT_STATE_PAUSE", "FUN_NAVIGATION_SURPPORT_MULTY_TASK", "getFUN_NAVIGATION_SURPPORT_MULTY_TASK", "setFUN_NAVIGATION_SURPPORT_MULTY_TASK", "GLOBAL_PLAN_FAIL", "getGLOBAL_PLAN_FAIL", "()I", "IMPPORT_DATA_DIR", "", "IS_NOT_MATCH_NAVI_COMMAND", "getIS_NOT_MATCH_NAVI_COMMAND", "IS_POWER_ON_CHARGED", "IS_SURPPORT_IMPPORT_DATA_FROM_USB", "getIS_SURPPORT_IMPPORT_DATA_FROM_USB", "setIS_SURPPORT_IMPPORT_DATA_FROM_USB", "NAVIGATION_NAME", "NAVIGATION_RECEPTION_DISTANCE", "NAVIGATION_RECEPTION_NAME", "NAVIGATION_WELCOME_IS_ALLOW_MOVE", "NAVIGATION_X86_URL", "Self_NAV_URL", "TAG_NAME_TYPE_CHAGE", "WATER_TCP_IP", "library-base_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: NavigationConfig.kt */
public final class NavigationConfig {
    public static final int CHARGE_STATE_CHARGING = 1;
    public static final int CHARGE_STATE_CHARGING_FAILED = -1;
    public static final int CHARGE_STATE_DISCONNECT = 0;
    public static final int CHARGE_STATE_RECHARGING = 2;
    private static boolean DATA_SOURCE_IS_SDCARD = true;
    public static final int EVENT_STATE_CANCEL = 3;
    public static final int EVENT_STATE_CANTINUE = 2;
    public static final int EVENT_STATE_IDLE = 0;
    public static final int EVENT_STATE_PAUSE = 1;
    private static boolean FUN_NAVIGATION_SURPPORT_MULTY_TASK = false;
    public static final String IMPPORT_DATA_DIR = "ServiceRobot/";
    public static final NavigationConfig INSTANCE = new NavigationConfig();
    public static boolean IS_POWER_ON_CHARGED = false;
    private static boolean IS_SURPPORT_IMPPORT_DATA_FROM_USB = false;
    public static final String NAVIGATION_NAME = "NAVIGATION_NAME";
    public static final String NAVIGATION_RECEPTION_DISTANCE = "NAVIGATION_RECEPTION_DISTANCE";
    public static final String NAVIGATION_RECEPTION_NAME = "NAVIGATION_RECEPTION_NAME";
    public static final String NAVIGATION_WELCOME_IS_ALLOW_MOVE = "NAVIGATION_WELCOME_IS_ALLOW_MOVE";
    public static final String NAVIGATION_X86_URL = "NAVIGATION_X86_URL";
    public static final String Self_NAV_URL = "ws://192.168.20.22:9090";
    public static final int TAG_NAME_TYPE_CHAGE = 11;
    public static final String WATER_TCP_IP = "192.168.1.20";

    public final int getGLOBAL_PLAN_FAIL() {
        return 99;
    }

    public final int getIS_NOT_MATCH_NAVI_COMMAND() {
        return -10000;
    }

    private NavigationConfig() {
    }

    public final boolean getFUN_NAVIGATION_SURPPORT_MULTY_TASK() {
        return FUN_NAVIGATION_SURPPORT_MULTY_TASK;
    }

    public final void setFUN_NAVIGATION_SURPPORT_MULTY_TASK(boolean z) {
        FUN_NAVIGATION_SURPPORT_MULTY_TASK = z;
    }

    public final boolean getIS_SURPPORT_IMPPORT_DATA_FROM_USB() {
        return IS_SURPPORT_IMPPORT_DATA_FROM_USB;
    }

    public final void setIS_SURPPORT_IMPPORT_DATA_FROM_USB(boolean z) {
        IS_SURPPORT_IMPPORT_DATA_FROM_USB = z;
    }

    public final boolean getDATA_SOURCE_IS_SDCARD() {
        return DATA_SOURCE_IS_SDCARD;
    }

    public final void setDATA_SOURCE_IS_SDCARD(boolean z) {
        DATA_SOURCE_IS_SDCARD = z;
    }
}
