package mc.csst.com.selfchassislibrary.navigation;

import android.content.Context;

public interface INavigationManager {
    boolean cancelNavigation();

    void connect(String str);

    void getMap();

    void getRobotCurrentMap();

    void init(Context context);

    int loadMap();

    String loadSpeakTxt(String str);

    void release();

    void requestAllPositionList();

    void sendTarget(float f, float f2, float f3);

    void sendTarget(String str);

    void sendTarget(String str, String str2);

    void setNavigationListener(INavigationMsgCallback iNavigationMsgCallback);

    void updateSpeakTxt(String str, String str2);
}
