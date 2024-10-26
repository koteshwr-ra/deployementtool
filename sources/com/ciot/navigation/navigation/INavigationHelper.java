package com.ciot.navigation.navigation;

import kotlin.Metadata;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\u0006\n\u0002\b\f\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\b\u0010\b\u001a\u00020\u0005H&J@\u0010\t\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u0007H&J\b\u0010\u0013\u001a\u00020\u0005H&J@\u0010\u0014\u001a\u00020\u00052\u0006\u0010\u0015\u001a\u00020\u00072\u0006\u0010\u0016\u001a\u00020\u000e2\b\b\u0002\u0010\u0017\u001a\u00020\u000e2\b\b\u0002\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u00192\b\b\u0002\u0010\u001b\u001a\u00020\u0019H&J\b\u0010\u001c\u001a\u00020\u0005H&J\b\u0010\u001d\u001a\u00020\u0005H&J\b\u0010\u001e\u001a\u00020\u0005H&J\b\u0010\u001f\u001a\u00020\u0005H&J \u0010 \u001a\u00020\u00032\u0006\u0010\u0018\u001a\u00020\u000b2\u0006\u0010\u001a\u001a\u00020\u000b2\u0006\u0010!\u001a\u00020\u000bH&J\u0018\u0010 \u001a\u00020\u00032\u0006\u0010\"\u001a\u00020\u00072\u0006\u0010#\u001a\u00020\u0007H&J\b\u0010$\u001a\u00020\u0005H&¨\u0006%"}, d2 = {"Lcom/ciot/navigation/navigation/INavigationHelper;", "", "cancelNavigation", "", "deleteMarkerByName", "", "markerName", "", "detectNewVersion", "getBetweenPath", "start_x", "", "start_y", "start_floor", "", "goal_x", "goal_y", "goal_floor", "uuid", "init", "insertMarker", "name", "type", "floor", "x", "", "y", "theta", "pause", "queryAllMarkers", "release", "sendGetMapList", "setTargetPosition", "radians", "id", "tagName", "updateChassisVersion", "library-navigation_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: INavigationHelper.kt */
public interface INavigationHelper {
    boolean cancelNavigation();

    void deleteMarkerByName(String str);

    void detectNewVersion();

    void getBetweenPath(float f, float f2, int i, float f3, float f4, int i2, String str);

    void init();

    void insertMarker(String str, int i, int i2, double d, double d2, double d3);

    void pause();

    void queryAllMarkers();

    void release();

    void sendGetMapList();

    boolean setTargetPosition(float f, float f2, float f3);

    boolean setTargetPosition(String str, String str2);

    void updateChassisVersion();

    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    /* compiled from: INavigationHelper.kt */
    public static final class DefaultImpls {
        public static /* synthetic */ void insertMarker$default(INavigationHelper iNavigationHelper, String str, int i, int i2, double d, double d2, double d3, int i3, Object obj) {
            if (obj == null) {
                iNavigationHelper.insertMarker(str, i, (i3 & 4) != 0 ? 0 : i2, (i3 & 8) != 0 ? 0.0d : d, (i3 & 16) != 0 ? 0.0d : d2, (i3 & 32) != 0 ? 0.0d : d3);
                return;
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: insertMarker");
        }
    }
}
