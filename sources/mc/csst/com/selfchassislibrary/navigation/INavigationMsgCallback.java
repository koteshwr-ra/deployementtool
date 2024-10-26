package mc.csst.com.selfchassislibrary.navigation;

import android.graphics.Bitmap;
import java.util.List;
import mc.csst.com.selfchassislibrary.bean.MapInfoBean;
import mc.csst.com.selfchassislibrary.bean.msg.SelfDiagnosisResponseBean;
import mc.csst.com.selfchassislibrary.chassis.SelfChassisState;

public interface INavigationMsgCallback {
    void chassisState(SelfChassisState selfChassisState);

    void currentMiles(float f);

    void getAllPositionList(List<PositionTag> list);

    void isConnect(boolean z);

    void isInitSuccess(boolean z);

    void localizationConfidence(float f);

    void mapBit(MapInfoBean mapInfoBean, Bitmap bitmap);

    void mapInfo(String str, String str2);

    void obstacleRegion(int i);

    void onError(int i, String str);

    void onNavigationState(String str, int i);

    void selfDiagnosis(SelfDiagnosisResponseBean selfDiagnosisResponseBean);
}
