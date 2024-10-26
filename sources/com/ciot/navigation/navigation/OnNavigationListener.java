package com.ciot.navigation.navigation;

import java.util.List;
import kotlin.Metadata;
import mc.csst.com.selfchassislibrary.navigation.PositionTag;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\n\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J \u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u00052\u000e\u0010\b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\tH&J\b\u0010\u000b\u001a\u00020\u0003H&J\b\u0010\f\u001a\u00020\u0003H&J\b\u0010\r\u001a\u00020\u0003H&J\u0012\u0010\u000e\u001a\u00020\u00032\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H&J\u0018\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0010H&J \u0010\u0015\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u00052\u0006\u0010\u0017\u001a\u00020\u00132\u0006\u0010\u0018\u001a\u00020\u0010H&J \u0010\u0019\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u00052\u0006\u0010\u0017\u001a\u00020\u00132\u0006\u0010\u0018\u001a\u00020\u0010H&J\u0018\u0010\u001a\u001a\u00020\u00032\u0006\u0010\u001b\u001a\u00020\u00132\u0006\u0010\u001c\u001a\u00020\u0010H&¨\u0006\u001d"}, d2 = {"Lcom/ciot/navigation/navigation/OnNavigationListener;", "", "connectResult", "", "isConnectSuccess", "", "getNavigationTags", "isGetTags", "tagList", "", "Lmc/csst/com/selfchassislibrary/navigation/PositionTag;", "navigationCanceled", "navigationPaused", "navigationResumed", "navigationarrived", "tagName", "", "senMsg", "msgType", "", "msg", "setInitPositionResult", "isSetSuccess", "resultCode", "resultMessage", "setTargetPositionResult", "updateErrorMessage", "errorCode", "errorMessage", "library-navigation_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: OnNavigationListener.kt */
public interface OnNavigationListener {
    void connectResult(boolean z);

    void getNavigationTags(boolean z, List<? extends PositionTag> list);

    void navigationCanceled();

    void navigationPaused();

    void navigationResumed();

    void navigationarrived(String str);

    void senMsg(int i, String str);

    void setInitPositionResult(boolean z, int i, String str);

    void setTargetPositionResult(boolean z, int i, String str);

    void updateErrorMessage(int i, String str);
}
