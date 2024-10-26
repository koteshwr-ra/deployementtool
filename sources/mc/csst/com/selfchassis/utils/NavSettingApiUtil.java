package mc.csst.com.selfchassis.utils;

import android.text.TextUtils;
import com.ciot.base.storage.MySpUtils;
import com.ciot.base.util.MyLogUtils;
import mc.csst.com.selfchassislibrary.bean.msg.NaviSettingBean;
import mc.csst.com.selfchassislibrary.bean.msg.NaviSettingResponseBean;
import mc.csst.com.selfchassislibrary.chassis.SelfChassis;
import mc.csst.com.selfchassislibrary.content.IDContent;
import mc.csst.com.selfchassislibrary.content.NaviSettingContent;
import mc.csst.com.selfchassislibrary.content.ServiceContent;
import mc.csst.com.selfchassislibrary.utils.SelfChassisListenerUtils;
import mc.csst.com.selfchassislibrary.utils.eventbus.SelfChassisEventMsg;

public class NavSettingApiUtil {
    private static final String TAG = "NavSettingApiUtil";
    private static NavSettingApiUtil instance = new NavSettingApiUtil();

    public static NavSettingApiUtil getInstance() {
        return instance;
    }

    public void initListener() {
        SelfChassis.getInstance().setNaviSettingListener(new SelfChassisListenerUtils.OnBaseSelfChassisListener() {
            public void onSelfChassisMsg(SelfChassisEventMsg selfChassisEventMsg) {
                MyLogUtils.Logd(NavSettingApiUtil.TAG, "onSelfChassisMsg-->" + selfChassisEventMsg.toString());
                try {
                    String code = selfChassisEventMsg.getCode();
                    char c = 65535;
                    if (code.hashCode() == -270181274) {
                        if (code.equals(ServiceContent.NAVI_SETTING)) {
                            c = 0;
                        }
                    }
                    if (c == 0) {
                        NavSettingApiUtil.this.dealNaviSetting(selfChassisEventMsg);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void releaseListener() {
        SelfChassis.getInstance().setRobotInfoListener((SelfChassisListenerUtils.OnBaseSelfChassisListener) null);
    }

    /* access modifiers changed from: private */
    public void dealNaviSetting(SelfChassisEventMsg selfChassisEventMsg) {
        MyLogUtils.Logd(TAG, "dealNaviSetting:" + selfChassisEventMsg.toString());
        if (selfChassisEventMsg.getData() != null) {
            NaviSettingResponseBean naviSettingResponseBean = (NaviSettingResponseBean) selfChassisEventMsg.getData();
            naviSettingResponseBean.getInfo();
            String id = naviSettingResponseBean.getId();
            if (naviSettingResponseBean.getValues().getResult().intValue() == 10) {
                MyLogUtils.Logd(TAG, "重新获取导航相关参数接口");
                SelfChassis.getInstance().getNaviSetting();
            } else if (!TextUtils.equals(IDContent.NaviSetting.CONFIG_NAVI_SETTING, id) && TextUtils.equals(IDContent.NaviSetting.GET_NAVI_SETTING, id)) {
                for (NaviSettingBean next : naviSettingResponseBean.getValues().getFeaturesResp()) {
                    float valueExt = next.getValueExt();
                    if (TextUtils.equals(next.getName(), NaviSettingContent.NAVIGATION_MODE_AUTO_NAVI)) {
                        MySpUtils.getInstance().put(SpConstant.NAV_MODE, valueExt == 1.0f ? 0 : 1);
                    }
                }
            }
        }
    }
}
