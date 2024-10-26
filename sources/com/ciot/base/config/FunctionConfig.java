package com.ciot.base.config;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b&\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001a\u0010\f\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u0006\"\u0004\b\u000e\u0010\bR\u001a\u0010\u000f\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0006\"\u0004\b\u0011\u0010\bR\u001a\u0010\u0012\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0006\"\u0004\b\u0014\u0010\bR\u001a\u0010\u0015\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0006\"\u0004\b\u0017\u0010\bR\u001a\u0010\u0018\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0006\"\u0004\b\u001a\u0010\bR\u001a\u0010\u001b\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0006\"\u0004\b\u001d\u0010\bR\u001a\u0010\u001e\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u0006\"\u0004\b \u0010\bR\u001a\u0010!\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u0006\"\u0004\b#\u0010\bR\u001a\u0010$\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u0006\"\u0004\b&\u0010\bR\u001a\u0010'\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010\u0006\"\u0004\b)\u0010\b¨\u0006*"}, d2 = {"Lcom/ciot/base/config/FunctionConfig;", "", "()V", "FUN_CAMERA", "", "getFUN_CAMERA", "()Z", "setFUN_CAMERA", "(Z)V", "FUN_DANCE", "getFUN_DANCE", "setFUN_DANCE", "FUN_FACE_DETECT", "getFUN_FACE_DETECT", "setFUN_FACE_DETECT", "FUN_FACE_DETECT_LEAVE_DO_SLEEP", "getFUN_FACE_DETECT_LEAVE_DO_SLEEP", "setFUN_FACE_DETECT_LEAVE_DO_SLEEP", "FUN_MOVIE", "getFUN_MOVIE", "setFUN_MOVIE", "FUN_MUSIC", "getFUN_MUSIC", "setFUN_MUSIC", "FUN_NAVIGATION", "getFUN_NAVIGATION", "setFUN_NAVIGATION", "FUN_NAVIGATION_COMMAND_FILTER", "getFUN_NAVIGATION_COMMAND_FILTER", "setFUN_NAVIGATION_COMMAND_FILTER", "FUN_SAY_HELLO_BOTH_SALUTE", "getFUN_SAY_HELLO_BOTH_SALUTE", "setFUN_SAY_HELLO_BOTH_SALUTE", "IS_OPEN_FULL_DUPLEX", "getIS_OPEN_FULL_DUPLEX", "setIS_OPEN_FULL_DUPLEX", "IS_OPEN_TEMP_DETECT", "getIS_OPEN_TEMP_DETECT", "setIS_OPEN_TEMP_DETECT", "IS_OPEN_UNDERSTAND", "getIS_OPEN_UNDERSTAND", "setIS_OPEN_UNDERSTAND", "library-base_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: FunctionConfig.kt */
public final class FunctionConfig {
    private static volatile boolean FUN_CAMERA;
    private static volatile boolean FUN_DANCE;
    private static volatile boolean FUN_FACE_DETECT = true;
    private static volatile boolean FUN_FACE_DETECT_LEAVE_DO_SLEEP;
    private static volatile boolean FUN_MOVIE = true;
    private static volatile boolean FUN_MUSIC = true;
    private static boolean FUN_NAVIGATION = true;
    private static boolean FUN_NAVIGATION_COMMAND_FILTER;
    private static volatile boolean FUN_SAY_HELLO_BOTH_SALUTE;
    public static final FunctionConfig INSTANCE = new FunctionConfig();
    private static volatile boolean IS_OPEN_FULL_DUPLEX = true;
    private static volatile boolean IS_OPEN_TEMP_DETECT = true;
    private static volatile boolean IS_OPEN_UNDERSTAND = true;

    private FunctionConfig() {
    }

    public final boolean getFUN_MUSIC() {
        return FUN_MUSIC;
    }

    public final void setFUN_MUSIC(boolean z) {
        FUN_MUSIC = z;
    }

    public final boolean getFUN_MOVIE() {
        return FUN_MOVIE;
    }

    public final void setFUN_MOVIE(boolean z) {
        FUN_MOVIE = z;
    }

    public final boolean getFUN_DANCE() {
        return FUN_DANCE;
    }

    public final void setFUN_DANCE(boolean z) {
        FUN_DANCE = z;
    }

    public final boolean getFUN_NAVIGATION() {
        return FUN_NAVIGATION;
    }

    public final void setFUN_NAVIGATION(boolean z) {
        FUN_NAVIGATION = z;
    }

    public final boolean getFUN_NAVIGATION_COMMAND_FILTER() {
        return FUN_NAVIGATION_COMMAND_FILTER;
    }

    public final void setFUN_NAVIGATION_COMMAND_FILTER(boolean z) {
        FUN_NAVIGATION_COMMAND_FILTER = z;
    }

    public final boolean getFUN_CAMERA() {
        return FUN_CAMERA;
    }

    public final void setFUN_CAMERA(boolean z) {
        FUN_CAMERA = z;
    }

    public final boolean getFUN_FACE_DETECT() {
        return FUN_FACE_DETECT;
    }

    public final void setFUN_FACE_DETECT(boolean z) {
        FUN_FACE_DETECT = z;
    }

    public final boolean getFUN_FACE_DETECT_LEAVE_DO_SLEEP() {
        return FUN_FACE_DETECT_LEAVE_DO_SLEEP;
    }

    public final void setFUN_FACE_DETECT_LEAVE_DO_SLEEP(boolean z) {
        FUN_FACE_DETECT_LEAVE_DO_SLEEP = z;
    }

    public final boolean getFUN_SAY_HELLO_BOTH_SALUTE() {
        return FUN_SAY_HELLO_BOTH_SALUTE;
    }

    public final void setFUN_SAY_HELLO_BOTH_SALUTE(boolean z) {
        FUN_SAY_HELLO_BOTH_SALUTE = z;
    }

    public final boolean getIS_OPEN_UNDERSTAND() {
        return IS_OPEN_UNDERSTAND;
    }

    public final void setIS_OPEN_UNDERSTAND(boolean z) {
        IS_OPEN_UNDERSTAND = z;
    }

    public final boolean getIS_OPEN_FULL_DUPLEX() {
        return IS_OPEN_FULL_DUPLEX;
    }

    public final void setIS_OPEN_FULL_DUPLEX(boolean z) {
        IS_OPEN_FULL_DUPLEX = z;
    }

    public final boolean getIS_OPEN_TEMP_DETECT() {
        return IS_OPEN_TEMP_DETECT;
    }

    public final void setIS_OPEN_TEMP_DETECT(boolean z) {
        IS_OPEN_TEMP_DETECT = z;
    }
}
