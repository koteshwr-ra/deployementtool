package com.ciot.navigation.navigation.task.utils;

import com.ciot.navigation.navigation.task.bean.EditMarkerBean;
import com.ciot.realm.db.ChildTask;
import com.ciot.realm.db.patrol.Line;
import com.ciot.waterchassis.WaterChassisHelper;
import com.ciot.waterchassis.bean.WaterMarkerBean;
import java.util.ArrayList;
import java.util.List;

public class ChildTaskUtil {
    private static ChildTaskUtil sInstance;

    public static ChildTaskUtil getInstance() {
        if (sInstance == null) {
            synchronized (ChildTaskUtil.class) {
                if (sInstance == null) {
                    sInstance = new ChildTaskUtil();
                }
            }
        }
        return sInstance;
    }

    public ChildTask addChildTaskWater(WaterMarkerBean waterMarkerBean) {
        ChildTask childTask = new ChildTask();
        childTask.setTaskNodeID(waterMarkerBean.getMarker_name());
        childTask.setPointName(waterMarkerBean.getMarker_name());
        double w = waterMarkerBean.getPose().getOrientation().getW();
        double z = waterMarkerBean.getPose().getOrientation().getZ();
        childTask.setX((float) waterMarkerBean.getPose().getPosition().getX());
        childTask.setY((float) waterMarkerBean.getPose().getPosition().getY());
        childTask.setAngle((float) WaterChassisHelper.getInstance().getTheta(w, z));
        childTask.setPType(waterMarkerBean.getKey());
        childTask.setZ(waterMarkerBean.getFloor());
        return childTask;
    }

    public ChildTask addChildTaskMarker(EditMarkerBean editMarkerBean) {
        ChildTask childTask = new ChildTask();
        childTask.setPointName(editMarkerBean.getName());
        childTask.setTaskNodeID(editMarkerBean.getName());
        childTask.setX((float) editMarkerBean.getX());
        childTask.setY((float) editMarkerBean.getY());
        childTask.setAngle((float) editMarkerBean.getTheta());
        childTask.setPType(editMarkerBean.getType());
        childTask.setZ(editMarkerBean.getFloor());
        return childTask;
    }

    public ChildTask addChildTask(Line line) {
        ChildTask childTask = new ChildTask();
        childTask.setTaskNodeID(line.getPlaceInfo().getPositionName());
        childTask.setPointName(line.getPlaceInfo().getPositionName());
        childTask.setX(line.getPlaceInfo().getX());
        childTask.setY(line.getPlaceInfo().getY());
        childTask.setAngle(line.getPlaceInfo().getAngle());
        childTask.setPType(line.getPlaceInfo().getType());
        childTask.setZ(line.getPlaceInfo().getZ());
        return childTask;
    }

    public List<ChildTask> addChildTaskList(List<WaterMarkerBean> list) {
        if (list == null || list.size() == 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (WaterMarkerBean addChildTaskWater : list) {
            arrayList.add(addChildTaskWater(addChildTaskWater));
        }
        return arrayList;
    }
}
