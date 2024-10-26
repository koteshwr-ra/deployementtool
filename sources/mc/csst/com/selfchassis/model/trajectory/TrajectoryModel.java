package mc.csst.com.selfchassis.model.trajectory;

import android.text.TextUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.google.gson.Gson;
import java.util.Date;
import java.util.List;
import mc.csst.com.selfchassis.utils.bean.ShowSelfChassisBean;
import mc.csst.com.selfchassislibrary.bean.msg.MarkerOperationGetMarkersResponseBean;
import mc.csst.com.selfchassislibrary.bean.msg.SendPathRecordRequestBean;

public class TrajectoryModel {
    private static float OFFSET = 0.2f;
    private static Gson gson = new Gson();
    private static final TrajectoryModel instance = new TrajectoryModel();
    private final String PREFIX_PATH = "PA_";
    private final String PREFIX_TASK = "TASK_";
    private String currentEndPointName = "";
    private String currentPathName = "";
    private String currentStartPointName = "";
    private String currentTaskId = "";
    private Boolean isInDrawing = false;

    public static TrajectoryModel getInstance() {
        return instance;
    }

    private TrajectoryModel() {
    }

    public String getCurrentTaskId() {
        return this.currentTaskId;
    }

    public void setCurrentTaskId() {
        this.currentPathName = "TASK_" + TimeUtils.date2String(new Date(), "yyyyMMddHHmmss");
    }

    public void setCurrentPathName() {
        this.currentPathName = "PA_" + TimeUtils.date2String(new Date(), "yyyyMMddHHmmss");
    }

    public String getCurrentPathName() {
        return this.currentPathName;
    }

    public String getCurrentStartPointName() {
        return this.currentStartPointName;
    }

    public void setCurrentStartPointName(String str) {
        this.currentStartPointName = str;
    }

    public String getCurrentEndPointName() {
        return this.currentEndPointName;
    }

    public void setCurrentEndPointName(String str) {
        this.currentEndPointName = str;
    }

    public Boolean getIsInDrawing() {
        return this.isInDrawing;
    }

    public void setIsInDrawing(Boolean bool) {
        this.isInDrawing = bool;
    }

    public String getCurrentHasPoint(List<MarkerOperationGetMarkersResponseBean.ValuesBean.MarkersBean.WaypointsBean> list) {
        if (!TextUtils.isEmpty(ShowSelfChassisBean.getInstance().getX()) && !TextUtils.isEmpty(ShowSelfChassisBean.getInstance().getY()) && !TextUtils.isEmpty(ShowSelfChassisBean.getInstance().getT())) {
            Float valueOf = Float.valueOf(Float.parseFloat(ShowSelfChassisBean.getInstance().getX()));
            Float valueOf2 = Float.valueOf(Float.parseFloat(ShowSelfChassisBean.getInstance().getY()));
            Float.valueOf(Float.parseFloat(ShowSelfChassisBean.getInstance().getT()));
            for (MarkerOperationGetMarkersResponseBean.ValuesBean.MarkersBean.WaypointsBean next : list) {
                if (Math.abs(next.getPose().getPosition().getX() - valueOf.floatValue()) < OFFSET && Math.abs(next.getPose().getPosition().getY() - valueOf2.floatValue()) < OFFSET) {
                    return next.getName();
                }
            }
        }
        return null;
    }

    public SendPathRecordRequestBean.StartNodePoseBean getStartPointNodePose(List<MarkerOperationGetMarkersResponseBean.ValuesBean.MarkersBean.WaypointsBean> list) {
        if (this.currentStartPointName == null || list == null) {
            return null;
        }
        for (MarkerOperationGetMarkersResponseBean.ValuesBean.MarkersBean.WaypointsBean next : list) {
            if (next.getName().equals(this.currentStartPointName)) {
                Gson gson2 = gson;
                return (SendPathRecordRequestBean.StartNodePoseBean) gson2.fromJson(gson2.toJson((Object) next.getPose()), SendPathRecordRequestBean.StartNodePoseBean.class);
            }
        }
        return null;
    }

    public SendPathRecordRequestBean.GoalNodePoseBean getEndPointNodePose(List<MarkerOperationGetMarkersResponseBean.ValuesBean.MarkersBean.WaypointsBean> list) {
        if (this.currentEndPointName == null || list == null) {
            return null;
        }
        for (MarkerOperationGetMarkersResponseBean.ValuesBean.MarkersBean.WaypointsBean next : list) {
            if (next.getName().equals(this.currentEndPointName)) {
                Gson gson2 = gson;
                return (SendPathRecordRequestBean.GoalNodePoseBean) gson2.fromJson(gson2.toJson((Object) next.getPose()), SendPathRecordRequestBean.GoalNodePoseBean.class);
            }
        }
        return null;
    }
}
