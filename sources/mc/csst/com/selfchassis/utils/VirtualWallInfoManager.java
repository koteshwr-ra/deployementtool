package mc.csst.com.selfchassis.utils;

import com.ciot.base.util.GsonUtils;
import com.ciot.base.util.MyLogUtils;
import java.util.ArrayList;
import java.util.List;
import mc.csst.com.selfchassislibrary.bean.msg.SetVirtualWallsBean;
import mc.csst.com.selfchassislibrary.bean.msg.VirtualWallOperationGetWallsResponseBean;

public class VirtualWallInfoManager {
    private static volatile VirtualWallInfoManager mInstance;
    List<VirtualWallOperationGetWallsResponseBean.ValuesBean.VwallsBean.WallsBean> mPoints;

    private VirtualWallInfoManager() {
    }

    public static VirtualWallInfoManager getInstance() {
        if (mInstance == null) {
            synchronized (VirtualWallInfoManager.class) {
                if (mInstance == null) {
                    mInstance = new VirtualWallInfoManager();
                }
            }
        }
        return mInstance;
    }

    public List<VirtualWallOperationGetWallsResponseBean.ValuesBean.VwallsBean.WallsBean> getPoints() {
        return this.mPoints;
    }

    public void setPoints(List<VirtualWallOperationGetWallsResponseBean.ValuesBean.VwallsBean.WallsBean> list) {
        MyLogUtils.Logd("tg----setPoints:" + GsonUtils.toJson(list));
        this.mPoints = list;
    }

    public List<SetVirtualWallsBean.MsgBean.WallsBean> getSetVirtualWallsBeanPoints() {
        List<VirtualWallOperationGetWallsResponseBean.ValuesBean.VwallsBean.WallsBean> list = this.mPoints;
        if (list == null || list.size() == 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (VirtualWallOperationGetWallsResponseBean.ValuesBean.VwallsBean.WallsBean json : this.mPoints) {
            String json2 = GsonUtils.toJson(json);
            MyLogUtils.Logd("zzy", "s:" + json2);
            MyLogUtils.Logd("tg----s:" + json2);
            arrayList.add((SetVirtualWallsBean.MsgBean.WallsBean) GsonUtils.fromLocalJson(json2, SetVirtualWallsBean.MsgBean.WallsBean.class));
        }
        MyLogUtils.Logd("tg----getSetVirtualWallsBeanPoints:" + GsonUtils.toJson(arrayList));
        return arrayList;
    }
}
