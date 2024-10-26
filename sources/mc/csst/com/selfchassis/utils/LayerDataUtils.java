package mc.csst.com.selfchassis.utils;

import android.text.TextUtils;
import com.blankj.utilcode.util.StringUtils;
import com.ciot.base.storage.MySpUtils;
import com.ciot.base.util.MyLogUtils;
import com.ciot.sentrymove.R;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import mc.csst.com.selfchassis.utils.bean.LayerBean;
import mc.csst.com.selfchassis.utils.constant.LayerConst;
import mc.csst.com.selfchassislibrary.chassis.SelfChassis;
import mc.csst.com.selfchassislibrary.chassis.SelfChassisState;

public class LayerDataUtils {
    private int curSoftType = -1;
    private long curTime;
    private List<LayerBean> mAllChildLayerBeans = new CopyOnWriteArrayList();
    private List<LayerBean> mLayerBeans = new CopyOnWriteArrayList();
    private final ConcurrentHashMap<String, Integer> mLayerStateMap = new ConcurrentHashMap<>();

    private static class Holder {
        /* access modifiers changed from: private */
        public static final LayerDataUtils INSTANCE = new LayerDataUtils();

        private Holder() {
        }
    }

    public static LayerDataUtils getInstance() {
        return Holder.INSTANCE;
    }

    public void init() {
        List<LayerBean> layerData = getLayerData();
        layerData.addAll(getAllLayerChildData());
        for (LayerBean next : layerData) {
            int selectStatus = next.getSelectStatus();
            this.mLayerStateMap.put(next.getId(), Integer.valueOf(selectStatus));
        }
    }

    public synchronized List<LayerBean> getLayerData() {
        if (this.mLayerBeans == null || this.mLayerBeans.size() <= 0) {
            this.mLayerBeans = new CopyOnWriteArrayList();
        } else {
            this.mLayerBeans.clear();
        }
        LayerBean layerBean = new LayerBean();
        layerBean.setId(LayerConst.LayerId.LOCATION);
        layerBean.setName(StringUtils.getString(R.string.coverage_location));
        layerBean.setSelectStatus(getLayerStatusById(LayerConst.LayerId.LOCATION));
        this.mLayerBeans.add(layerBean);
        LayerBean layerBean2 = new LayerBean();
        layerBean2.setId(LayerConst.LayerId.TRAVEL_PATH);
        layerBean2.setName(StringUtils.getString(R.string.coverage_path));
        layerBean2.setSelectStatus(getLayerStatusById(LayerConst.LayerId.TRAVEL_PATH));
        this.mLayerBeans.add(layerBean2);
        LayerBean layerBean3 = new LayerBean();
        layerBean3.setId(LayerConst.LayerId.PointCloud.POINT_CLOUD);
        layerBean3.setName(StringUtils.getString(R.string.coverage_laser));
        layerBean3.setSelectStatus(getLayerStatusById(LayerConst.LayerId.PointCloud.POINT_CLOUD));
        this.mLayerBeans.add(layerBean3);
        LayerBean layerBean4 = new LayerBean();
        layerBean4.setId(LayerConst.LayerId.MARK_POINT);
        layerBean4.setName(StringUtils.getString(R.string.coverage_bjd));
        layerBean4.setSelectStatus(getLayerStatusById(LayerConst.LayerId.MARK_POINT));
        this.mLayerBeans.add(layerBean4);
        LayerBean layerBean5 = new LayerBean();
        layerBean5.setId(LayerConst.LayerId.Area.AREA);
        layerBean5.setName(StringUtils.getString(R.string.coverage_area));
        layerBean5.setSelectStatus(getLayerStatusById(LayerConst.LayerId.Area.AREA));
        this.mLayerBeans.add(layerBean5);
        LayerBean layerBean6 = new LayerBean();
        layerBean6.setId("layer_virtual_wall");
        layerBean6.setName(StringUtils.getString(R.string.virtual_wall));
        layerBean6.setSelectStatus(getLayerStatusById("layer_virtual_wall"));
        this.mLayerBeans.add(layerBean6);
        LayerBean layerBean7 = new LayerBean();
        layerBean7.setId(LayerConst.LayerId.LABEL_POINT);
        layerBean7.setName(StringUtils.getString(R.string.coverage_label));
        layerBean7.setSelectStatus(getLayerStatusById(LayerConst.LayerId.LABEL_POINT));
        this.mLayerBeans.add(layerBean7);
        LayerBean layerBean8 = new LayerBean();
        layerBean8.setId(LayerConst.LayerId.TRAJECTORY);
        layerBean8.setName(StringUtils.getString(R.string.text_trajectory));
        layerBean8.setSelectStatus(getLayerStatusById(LayerConst.LayerId.TRAJECTORY));
        this.mLayerBeans.add(layerBean8);
        return this.mLayerBeans;
    }

    public synchronized List<LayerBean> getAllLayerChildData() {
        if (this.mAllChildLayerBeans == null || this.mAllChildLayerBeans.size() <= 0) {
            this.mAllChildLayerBeans = new CopyOnWriteArrayList();
        } else {
            this.mAllChildLayerBeans.clear();
        }
        LayerBean layerBean = new LayerBean();
        layerBean.setRootId(LayerConst.LayerId.PointCloud.POINT_CLOUD);
        layerBean.setId(LayerConst.LayerId.PointCloud.RADAR);
        layerBean.setName(StringUtils.getString(R.string.txt_radar_point_cloud));
        layerBean.setSelectStatus(getLayerStatusById(LayerConst.LayerId.PointCloud.RADAR));
        this.mAllChildLayerBeans.add(layerBean);
        LayerBean layerBean2 = new LayerBean();
        layerBean2.setRootId(LayerConst.LayerId.PointCloud.POINT_CLOUD);
        layerBean2.setId(LayerConst.LayerId.PointCloud.UP_CAM);
        layerBean2.setName(StringUtils.getString(R.string.txt_up_cam_point_cloud));
        layerBean2.setSelectStatus(getLayerStatusById(LayerConst.LayerId.PointCloud.UP_CAM));
        this.mAllChildLayerBeans.add(layerBean2);
        LayerBean layerBean3 = new LayerBean();
        layerBean3.setRootId(LayerConst.LayerId.PointCloud.POINT_CLOUD);
        layerBean3.setId(LayerConst.LayerId.PointCloud.DOWN_CAM);
        layerBean3.setName(StringUtils.getString(R.string.txt_down_cam_point_cloud));
        layerBean3.setSelectStatus(getLayerStatusById(LayerConst.LayerId.PointCloud.DOWN_CAM));
        this.mAllChildLayerBeans.add(layerBean3);
        LayerBean layerBean4 = new LayerBean();
        layerBean4.setRootId(LayerConst.LayerId.Area.AREA);
        layerBean4.setId("layer_strong_light_area");
        layerBean4.setName(StringUtils.getString(R.string.strong_light_zone));
        layerBean4.setSelectStatus(getLayerStatusById("layer_strong_light_area"));
        this.mAllChildLayerBeans.add(layerBean4);
        LayerBean layerBean5 = new LayerBean();
        layerBean5.setRootId(LayerConst.LayerId.Area.AREA);
        layerBean5.setId("layer_slow_area");
        layerBean5.setName(StringUtils.getString(R.string.slow_zone));
        layerBean5.setSelectStatus(getLayerStatusById("layer_slow_area"));
        this.mAllChildLayerBeans.add(layerBean5);
        LayerBean layerBean6 = new LayerBean();
        layerBean6.setRootId(LayerConst.LayerId.Area.AREA);
        layerBean6.setId("layer_danger_area");
        layerBean6.setName(StringUtils.getString(R.string.danger_zone));
        layerBean6.setSelectStatus(getLayerStatusById("layer_danger_area"));
        this.mAllChildLayerBeans.add(layerBean6);
        LayerBean layerBean7 = new LayerBean();
        layerBean7.setRootId(LayerConst.LayerId.Area.AREA);
        layerBean7.setId("layer_slope_area");
        layerBean7.setName(StringUtils.getString(R.string.slope_zone));
        layerBean7.setSelectStatus(getLayerStatusById("layer_slope_area"));
        this.mAllChildLayerBeans.add(layerBean7);
        LayerBean layerBean8 = new LayerBean();
        layerBean8.setRootId(LayerConst.LayerId.Area.AREA);
        layerBean8.setId("layer_label_area");
        layerBean8.setName(StringUtils.getString(R.string.txt_label_area));
        layerBean8.setSelectStatus(getLayerStatusById("layer_label_area"));
        this.mAllChildLayerBeans.add(layerBean8);
        return this.mAllChildLayerBeans;
    }

    public List<LayerBean> getLayerChildData(String str) {
        List<LayerBean> allLayerChildData = getAllLayerChildData();
        ArrayList arrayList = new ArrayList();
        for (LayerBean next : allLayerChildData) {
            if (TextUtils.equals(str, next.getRootId())) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    public boolean getCanShowLayer(String str) {
        for (Map.Entry next : this.mLayerStateMap.entrySet()) {
            if (TextUtils.equals(str, (CharSequence) next.getKey())) {
                if (((Integer) next.getValue()).intValue() != 0) {
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    public int getLayerStatusById(String str) {
        if (TextUtils.equals(str, LayerConst.LayerId.PointCloud.DOWN_CAM) || TextUtils.equals(str, LayerConst.LayerId.PointCloud.UP_CAM)) {
            return MySpUtils.getInstance().getInt(str, 0);
        }
        return MySpUtils.getInstance().getInt(str, 1);
    }

    public void setLayerStatus(String str, int i) {
        MySpUtils.getInstance().putInt(str, i);
        this.mLayerStateMap.put(str, Integer.valueOf(i));
        if (TextUtils.equals(LayerConst.LayerId.PointCloud.UP_CAM, str)) {
            if (i == 0) {
                SelfChassis.getInstance().stopUpCamData();
            } else if (!SelfChassisState.getInstance().isUpCamDataState()) {
                SelfChassis.getInstance().initUpCamData();
            }
        } else if (!TextUtils.equals(LayerConst.LayerId.PointCloud.DOWN_CAM, str)) {
        } else {
            if (i == 0) {
                SelfChassis.getInstance().stopDownCamData();
            } else if (!SelfChassisState.getInstance().isDownCamDataState()) {
                SelfChassis.getInstance().initDownCamData();
            }
        }
    }

    public boolean setCanEditBySoftType() {
        this.curTime = System.currentTimeMillis();
        if (this.curSoftType == SoftTypeInfoManager.getInstance().getSoftType()) {
            return false;
        }
        int softType = SoftTypeInfoManager.getInstance().getSoftType();
        this.curSoftType = softType;
        if (softType == 9 || softType == 2) {
            return setCanEditByLayerIds(LayerConst.LayerId.LOCATION, LayerConst.LayerId.PointCloud.RADAR);
        }
        if (softType == 3 || softType == 7 || softType == 8) {
            return setCanEditByLayerIds(LayerConst.LayerId.LOCATION);
        }
        if (softType == 1 || softType == 5 || softType == 6) {
            return setCanEditByLayerIds(LayerConst.LayerId.LOCATION, LayerConst.LayerId.PointCloud.RADAR, LayerConst.LayerId.TRAVEL_PATH);
        }
        return setCanEditByLayerIds("");
    }

    private boolean setCanEditByLayerIds(String... strArr) {
        List<LayerBean> allLayerChildData = getAllLayerChildData();
        List<LayerBean> layerData = getLayerData();
        if (strArr == null || strArr.length <= 0) {
            return false;
        }
        for (LayerBean next : layerData) {
            if (next.getSelectStatus() == -1) {
                next.setSelectStatus(1);
                setLayerStatus(next.getId(), 1);
            }
        }
        for (LayerBean next2 : allLayerChildData) {
            if (next2.getSelectStatus() == -1) {
                next2.setSelectStatus(1);
                setLayerStatus(next2.getId(), 1);
                if (next2.getRootId() != null) {
                    setLayerStatus(next2.getRootId(), 1);
                }
            }
        }
        for (String str : strArr) {
            if (!TextUtils.isEmpty(str)) {
                setLayerStatus(str, -1);
            }
        }
        MyLogUtils.Logd("setCanEditByLayerIds 花了时长：" + (System.currentTimeMillis() - this.curTime));
        return true;
    }

    public boolean isCanEditBySoftType() {
        return SoftTypeInfoManager.getInstance().getSoftType() != 0;
    }

    public boolean getShowArea() {
        return getCanShowLayer(LayerConst.LayerId.Area.AREA);
    }

    public void setShowArea(String str, int i) {
        setLayerStatus(LayerConst.LayerId.Area.AREA, i);
        setLayerStatus(str, i);
    }

    public boolean getShowSlowArea() {
        return getCanShowLayer("layer_slow_area");
    }

    public boolean getShowStrongArea() {
        return getCanShowLayer("layer_strong_light_area");
    }

    public boolean getShowDangerArea() {
        return getCanShowLayer("layer_danger_area");
    }

    public boolean getShowSlopeArea() {
        return getCanShowLayer("layer_slope_area");
    }

    public boolean getShowLabelArea() {
        return getCanShowLayer("layer_label_area");
    }

    public boolean getShowLocation() {
        return getCanShowLayer(LayerConst.LayerId.LOCATION);
    }

    public boolean getShowTravelPath() {
        return getCanShowLayer(LayerConst.LayerId.TRAVEL_PATH);
    }

    public boolean getShowPointCloud() {
        return getCanShowLayer(LayerConst.LayerId.PointCloud.POINT_CLOUD);
    }

    public boolean getShowPointCloudRadar() {
        return getCanShowLayer(LayerConst.LayerId.PointCloud.RADAR);
    }

    public boolean getShowPointCloudUpCam() {
        return getCanShowLayer(LayerConst.LayerId.PointCloud.UP_CAM);
    }

    public boolean getShowPointCloudDownCam() {
        return getCanShowLayer(LayerConst.LayerId.PointCloud.DOWN_CAM);
    }

    public boolean getShowMarkPoint() {
        return getCanShowLayer(LayerConst.LayerId.MARK_POINT);
    }

    public boolean getShowVirtualWall() {
        return getCanShowLayer("layer_virtual_wall");
    }

    public boolean getShowLabelPoint() {
        return getCanShowLayer(LayerConst.LayerId.LABEL_POINT);
    }

    public boolean getShowTrajectory() {
        return getCanShowLayer(LayerConst.LayerId.TRAJECTORY);
    }
}
