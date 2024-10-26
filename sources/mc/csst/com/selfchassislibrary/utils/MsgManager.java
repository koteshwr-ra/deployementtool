package mc.csst.com.selfchassislibrary.utils;

import android.util.Log;
import com.ciot.base.util.GsonUtils;
import com.ciot.base.util.MyLogUtils;
import com.google.gson.Gson;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import mc.csst.com.selfchassis.ui.fragment.set.schedule.ScheduleFragment;
import mc.csst.com.selfchassislibrary.bean.QuaternionBean;
import mc.csst.com.selfchassislibrary.bean.msg.AppendAreaBean;
import mc.csst.com.selfchassislibrary.bean.msg.AreaItemBean;
import mc.csst.com.selfchassislibrary.bean.msg.AreaMsgBean;
import mc.csst.com.selfchassislibrary.bean.msg.CancelGoalPublishBean;
import mc.csst.com.selfchassislibrary.bean.msg.CrossFloorNaviReqBean;
import mc.csst.com.selfchassislibrary.bean.msg.DoorLengthBean;
import mc.csst.com.selfchassislibrary.bean.msg.GateLengthBean;
import mc.csst.com.selfchassislibrary.bean.msg.GetCurrentMapBean;
import mc.csst.com.selfchassislibrary.bean.msg.GlobalLocateReqBean;
import mc.csst.com.selfchassislibrary.bean.msg.InsertCurrentPoseMarkerPublishBean;
import mc.csst.com.selfchassislibrary.bean.msg.LaserSafetyControllerConfidenceThresholdBean;
import mc.csst.com.selfchassislibrary.bean.msg.LiftControlConfigureReqBean;
import mc.csst.com.selfchassislibrary.bean.msg.LiftControlForceCancelReqBean;
import mc.csst.com.selfchassislibrary.bean.msg.MarkersDetailsRequestBean;
import mc.csst.com.selfchassislibrary.bean.msg.NaviSettingBean;
import mc.csst.com.selfchassislibrary.bean.msg.NaviSettingReqBean;
import mc.csst.com.selfchassislibrary.bean.msg.NodeManagerLaserSafetyControllerBean;
import mc.csst.com.selfchassislibrary.bean.msg.NodeManagerLaserSafetyRangeBean;
import mc.csst.com.selfchassislibrary.bean.msg.RequestBean;
import mc.csst.com.selfchassislibrary.bean.msg.SendGoalPublishBean;
import mc.csst.com.selfchassislibrary.bean.msg.SendGoalPublishIdBean;
import mc.csst.com.selfchassislibrary.bean.msg.SensorFeaturesBean;
import mc.csst.com.selfchassislibrary.bean.msg.SetAreaBean;
import mc.csst.com.selfchassislibrary.bean.msg.SetEstopBean;
import mc.csst.com.selfchassislibrary.bean.msg.SetHomePoseBean;
import mc.csst.com.selfchassislibrary.bean.msg.SetLayeredMapManagerPencilOpPublishBean;
import mc.csst.com.selfchassislibrary.bean.msg.SetPosePublishBean;
import mc.csst.com.selfchassislibrary.bean.msg.SetVirtualWallsBean;
import mc.csst.com.selfchassislibrary.bean.msg.SetWayPointBean;
import mc.csst.com.selfchassislibrary.bean.msg.UltrasonicDistanceBean;
import mc.csst.com.selfchassislibrary.bean.msg.UltrasonicDistanceReqBean;
import mc.csst.com.selfchassislibrary.bean.msg.VelocityPublishBean;
import mc.csst.com.selfchassislibrary.content.IDContent;
import mc.csst.com.selfchassislibrary.content.NaviSettingContent;
import mc.csst.com.selfchassislibrary.content.OpContent;
import mc.csst.com.selfchassislibrary.content.SensorSettingContent;
import mc.csst.com.selfchassislibrary.content.ServiceContent;
import mc.csst.com.selfchassislibrary.content.TopicContent;
import mc.csst.com.selfchassislibrary.content.TypeContent;

public class MsgManager {
    public static String initVelocityMsg() {
        RequestBean requestBean = new RequestBean();
        requestBean.setOp(OpContent.ADVERTISE);
        requestBean.setTopic(TopicContent.CMD_VEL_MUX_NPUT_TELEOP);
        requestBean.setType(TypeContent.GEOMETRY_MSGS_TWIST);
        return requestToJson(requestBean);
    }

    public static String velocityMsg(float f, float f2) {
        VelocityPublishBean velocityPublishBean = new VelocityPublishBean();
        velocityPublishBean.setOp(OpContent.PUBLISH);
        velocityPublishBean.setTopic(TopicContent.CMD_VEL_MUX_NPUT_TELEOP);
        VelocityPublishBean.MsgBean msgBean = new VelocityPublishBean.MsgBean();
        VelocityPublishBean.MsgBean.AngularBean angularBean = new VelocityPublishBean.MsgBean.AngularBean();
        angularBean.setZ(f);
        VelocityPublishBean.MsgBean.LinearBean linearBean = new VelocityPublishBean.MsgBean.LinearBean();
        linearBean.setX(f2);
        msgBean.setAngular(angularBean);
        msgBean.setLinear(linearBean);
        velocityPublishBean.setMsg(msgBean);
        return toJson(velocityPublishBean, VelocityPublishBean.class);
    }

    public static String stopVelocityMsg() {
        RequestBean requestBean = new RequestBean();
        requestBean.setOp(OpContent.UNADVERTISE);
        requestBean.setTopic(TopicContent.CMD_VEL_MUX_NPUT_TELEOP);
        return requestToJson(requestBean);
    }

    public static String initGetPoseMsg() {
        RequestBean requestBean = new RequestBean();
        requestBean.setOp(OpContent.SUBSCRIBE);
        requestBean.setTopic(TopicContent.ROBOT_POSE);
        requestBean.setType(TypeContent.GEOMETRY_MSGS_POSE2D);
        return requestToJson(requestBean);
    }

    public static String stopGetPoseMsg() {
        RequestBean requestBean = new RequestBean();
        requestBean.setOp(OpContent.UNSUBSCRIBE);
        requestBean.setTopic(TopicContent.ROBOT_POSE);
        return requestToJson(requestBean);
    }

    public static String initLaserDataMsg() {
        RequestBean requestBean = new RequestBean();
        requestBean.setOp(OpContent.SUBSCRIBE);
        requestBean.setTopic(TopicContent.LASER_DATA);
        requestBean.setType(TypeContent.YUTONG_ASSISTANCE_POINT_ARRAY);
        return requestToJson(requestBean);
    }

    public static String stopLaserDataMsg() {
        RequestBean requestBean = new RequestBean();
        requestBean.setType(TypeContent.YUTONG_ASSISTANCE_POINT_ARRAY);
        requestBean.setOp(OpContent.UNSUBSCRIBE);
        requestBean.setTopic(TopicContent.LASER_DATA);
        return requestToJson(requestBean);
    }

    public static String initSendGoalMsg() {
        RequestBean requestBean = new RequestBean();
        requestBean.setOp(OpContent.ADVERTISE);
        requestBean.setTopic(TopicContent.NAVI_GOAL);
        requestBean.setType(TypeContent.GEOMETRY_MSGS_POSESTAMPED);
        return requestToJson(requestBean);
    }

    public static String initSendGoalIdMsg() {
        RequestBean requestBean = new RequestBean();
        requestBean.setOp(OpContent.ADVERTISE);
        requestBean.setTopic(TopicContent.NAVI_GOAL_ID);
        requestBean.setType(TypeContent.MOVE_BASE_MSGS_MOVEBASEACTIONGOAL);
        return requestToJson(requestBean);
    }

    public static String sendGoalMsg(float f, float f2, float f3) {
        QuaternionBean eular2quaternion = ConvertorUtils.eular2quaternion(0.0f, 0.0f, f3);
        SendGoalPublishBean sendGoalPublishBean = new SendGoalPublishBean();
        sendGoalPublishBean.setOp(OpContent.PUBLISH);
        sendGoalPublishBean.setTopic(TopicContent.NAVI_GOAL);
        SendGoalPublishBean.MsgBean msgBean = new SendGoalPublishBean.MsgBean();
        SendGoalPublishBean.MsgBean.HeaderBean headerBean = new SendGoalPublishBean.MsgBean.HeaderBean();
        headerBean.setFrame_id(ScheduleFragment.MAP);
        SendGoalPublishBean.MsgBean.PoseBean poseBean = new SendGoalPublishBean.MsgBean.PoseBean();
        SendGoalPublishBean.MsgBean.PoseBean.PositionBean positionBean = new SendGoalPublishBean.MsgBean.PoseBean.PositionBean();
        positionBean.setX(f);
        positionBean.setY(f2);
        SendGoalPublishBean.MsgBean.PoseBean.OrientationBean orientationBean = new SendGoalPublishBean.MsgBean.PoseBean.OrientationBean();
        orientationBean.setW(eular2quaternion.getW());
        orientationBean.setX(eular2quaternion.getX());
        orientationBean.setY(eular2quaternion.getY());
        orientationBean.setZ(eular2quaternion.getZ());
        poseBean.setPosition(positionBean);
        poseBean.setOrientation(orientationBean);
        msgBean.setHeader(headerBean);
        msgBean.setPose(poseBean);
        sendGoalPublishBean.setMsg(msgBean);
        return toJson(sendGoalPublishBean, SendGoalPublishBean.class);
    }

    public static String sendGoalIdMsg(String str, float f, float f2, float f3) {
        QuaternionBean eular2quaternion = ConvertorUtils.eular2quaternion(0.0f, 0.0f, f3);
        SendGoalPublishIdBean sendGoalPublishIdBean = new SendGoalPublishIdBean();
        sendGoalPublishIdBean.setOp(OpContent.PUBLISH);
        sendGoalPublishIdBean.setTopic(TopicContent.NAVI_GOAL_ID);
        SendGoalPublishIdBean.MsgBean msgBean = new SendGoalPublishIdBean.MsgBean();
        SendGoalPublishIdBean.MsgBean.HeaderBean headerBean = new SendGoalPublishIdBean.MsgBean.HeaderBean();
        headerBean.setFrame_id(ScheduleFragment.MAP);
        SendGoalPublishIdBean.MsgBean.GoalIdBean goalIdBean = new SendGoalPublishIdBean.MsgBean.GoalIdBean();
        goalIdBean.setId(str);
        SendGoalPublishIdBean.MsgBean.GoalBean goalBean = new SendGoalPublishIdBean.MsgBean.GoalBean();
        SendGoalPublishIdBean.MsgBean.GoalBean.TargetPoseBean targetPoseBean = new SendGoalPublishIdBean.MsgBean.GoalBean.TargetPoseBean();
        SendGoalPublishIdBean.MsgBean.GoalBean.TargetPoseBean.HeaderBeanX headerBeanX = new SendGoalPublishIdBean.MsgBean.GoalBean.TargetPoseBean.HeaderBeanX();
        headerBeanX.setFrame_id(ScheduleFragment.MAP);
        SendGoalPublishIdBean.MsgBean.GoalBean.TargetPoseBean.PoseBean poseBean = new SendGoalPublishIdBean.MsgBean.GoalBean.TargetPoseBean.PoseBean();
        SendGoalPublishIdBean.MsgBean.GoalBean.TargetPoseBean.PoseBean.OrientationBean orientationBean = new SendGoalPublishIdBean.MsgBean.GoalBean.TargetPoseBean.PoseBean.OrientationBean();
        orientationBean.setW(eular2quaternion.getW());
        orientationBean.setX(eular2quaternion.getX());
        orientationBean.setY(eular2quaternion.getY());
        orientationBean.setZ(eular2quaternion.getZ());
        SendGoalPublishIdBean.MsgBean.GoalBean.TargetPoseBean.PoseBean.PositionBean positionBean = new SendGoalPublishIdBean.MsgBean.GoalBean.TargetPoseBean.PoseBean.PositionBean();
        positionBean.setX(f);
        positionBean.setY(f2);
        poseBean.setOrientation(orientationBean);
        poseBean.setPosition(positionBean);
        targetPoseBean.setHeader(headerBeanX);
        targetPoseBean.setPose(poseBean);
        goalBean.setTarget_pose(targetPoseBean);
        msgBean.setHeader(headerBean);
        msgBean.setGoal_id(goalIdBean);
        msgBean.setGoal(goalBean);
        sendGoalPublishIdBean.setMsg(msgBean);
        return toJson(sendGoalPublishIdBean, SendGoalPublishIdBean.class);
    }

    public static String stopSendGoalMsg() {
        RequestBean requestBean = new RequestBean();
        requestBean.setOp(OpContent.UNSUBSCRIBE);
        requestBean.setTopic(TopicContent.ROBOT_POSE);
        return requestToJson(requestBean);
    }

    public static String initCancelGoalMsg() {
        RequestBean requestBean = new RequestBean();
        requestBean.setOp(OpContent.ADVERTISE);
        requestBean.setTopic(TopicContent.MOVE_BASE_CANCEL);
        requestBean.setType("actionlib_msgs/GoalID");
        return requestToJson(requestBean);
    }

    public static String cancelGoalMsg() {
        CancelGoalPublishBean cancelGoalPublishBean = new CancelGoalPublishBean();
        cancelGoalPublishBean.setOp(OpContent.PUBLISH);
        cancelGoalPublishBean.setTopic(TopicContent.MOVE_BASE_CANCEL);
        CancelGoalPublishBean.MsgBean msgBean = new CancelGoalPublishBean.MsgBean();
        msgBean.setId("");
        msgBean.setStamp("");
        cancelGoalPublishBean.setMsg(msgBean);
        return toJson(cancelGoalPublishBean, CancelGoalPublishBean.class);
    }

    public static String stopCancelGoalMsg() {
        RequestBean requestBean = new RequestBean();
        requestBean.setOp(OpContent.UNSUBSCRIBE);
        requestBean.setTopic(TopicContent.MOVE_BASE_CANCEL);
        return requestToJson(requestBean);
    }

    public static String initSetPoseMsg() {
        RequestBean requestBean = new RequestBean();
        requestBean.setOp(OpContent.ADVERTISE);
        requestBean.setTopic(TopicContent.INITIALPOSE);
        requestBean.setType(TypeContent.GEOMETRY_MSGS_POSEWITHCOVARIANCESTAMPED);
        return requestToJson(requestBean);
    }

    public static String setPoseMsg(float f, float f2, float f3) {
        QuaternionBean eular2quaternion = ConvertorUtils.eular2quaternion(0.0f, 0.0f, f3);
        SetPosePublishBean setPosePublishBean = new SetPosePublishBean();
        setPosePublishBean.setOp(OpContent.PUBLISH);
        setPosePublishBean.setTopic(TopicContent.INITIALPOSE);
        SetPosePublishBean.MsgBean msgBean = new SetPosePublishBean.MsgBean();
        SetPosePublishBean.MsgBean.HeaderBean headerBean = new SetPosePublishBean.MsgBean.HeaderBean();
        headerBean.setFrame_id(ScheduleFragment.MAP);
        SetPosePublishBean.MsgBean.PoseBeanX poseBeanX = new SetPosePublishBean.MsgBean.PoseBeanX();
        SetPosePublishBean.MsgBean.PoseBeanX.PoseBean poseBean = new SetPosePublishBean.MsgBean.PoseBeanX.PoseBean();
        SetPosePublishBean.MsgBean.PoseBeanX.PoseBean.OrientationBean orientationBean = new SetPosePublishBean.MsgBean.PoseBeanX.PoseBean.OrientationBean();
        orientationBean.setW(eular2quaternion.getW());
        orientationBean.setX(eular2quaternion.getX());
        orientationBean.setY(eular2quaternion.getY());
        orientationBean.setZ(eular2quaternion.getZ());
        SetPosePublishBean.MsgBean.PoseBeanX.PoseBean.PositionBean positionBean = new SetPosePublishBean.MsgBean.PoseBeanX.PoseBean.PositionBean();
        positionBean.setX(f);
        positionBean.setY(f2);
        poseBean.setOrientation(orientationBean);
        poseBean.setPosition(positionBean);
        poseBeanX.setPose(poseBean);
        msgBean.setHeader(headerBean);
        msgBean.setPose(poseBeanX);
        setPosePublishBean.setMsg(msgBean);
        return toJson(setPosePublishBean, SetPosePublishBean.class);
    }

    public static String stopSetPoseMsg() {
        RequestBean requestBean = new RequestBean();
        requestBean.setOp(OpContent.UNADVERTISE);
        requestBean.setTopic(TopicContent.INITIALPOSE);
        return requestToJson(requestBean);
    }

    public static String getMapMsg() {
        RequestBean requestBean = new RequestBean();
        requestBean.setOp(OpContent.SUBSCRIBE);
        requestBean.setTopic(TopicContent.MAP);
        requestBean.setType(TypeContent.NAV_MSGS_OCCUPANCYGRID);
        requestBean.setCompression("png");
        requestBean.setFragment_size(6000);
        requestBean.setThrottle_rate(88);
        return requestToJson(requestBean);
    }

    public static String getMapChartMsg() {
        RequestBean requestBean = new RequestBean();
        requestBean.setOp(OpContent.SUBSCRIBE);
        requestBean.setTopic(TopicContent.MAP_CHART);
        requestBean.setId(IDContent.Other.GET_MAP_CHART);
        requestBean.setType(TypeContent.NUMPY_MSGS_OCCUPANCYGRID);
        requestBean.setCompression("png");
        return requestToJson(requestBean);
    }

    public static String initGetPathMsg() {
        RequestBean requestBean = new RequestBean();
        requestBean.setOp(OpContent.SUBSCRIBE);
        requestBean.setTopic(TopicContent.GLOBAL_PATH);
        requestBean.setType(TypeContent.YUTONG_ASSISTANCE_POINT_ARRAY);
        return requestToJson(requestBean);
    }

    public static String stopGetPathMsg() {
        RequestBean requestBean = new RequestBean();
        requestBean.setOp(OpContent.UNSUBSCRIBE);
        requestBean.setTopic(TopicContent.GLOBAL_PATH);
        requestBean.setType(TypeContent.YUTONG_ASSISTANCE_POINT_ARRAY);
        return requestToJson(requestBean);
    }

    public static String initRobotStatus() {
        RequestBean requestBean = new RequestBean();
        requestBean.setOp(OpContent.SUBSCRIBE);
        requestBean.setTopic(TopicContent.ROBOT_STATUS);
        requestBean.setType(TypeContent.YUTONG_ASSISTANCE_ROBOTSTATUS);
        return requestToJson(requestBean);
    }

    public static String stopRobotStatus() {
        RequestBean requestBean = new RequestBean();
        requestBean.setOp(OpContent.UNSUBSCRIBE);
        requestBean.setTopic(TopicContent.ROBOT_STATUS);
        requestBean.setType(TypeContent.YUTONG_ASSISTANCE_ROBOTSTATUS);
        return requestToJson(requestBean);
    }

    public static String initNaviStatus() {
        RequestBean requestBean = new RequestBean();
        requestBean.setOp(OpContent.SUBSCRIBE);
        requestBean.setTopic(TopicContent.NAVI_STATUS);
        requestBean.setType(TypeContent.ACTIONLIB_MSGS_GOALSTATUS);
        return requestToJson(requestBean);
    }

    public static String stopNaviStatus() {
        RequestBean requestBean = new RequestBean();
        requestBean.setOp(OpContent.UNSUBSCRIBE);
        requestBean.setTopic(TopicContent.NAVI_STATUS);
        requestBean.setType(TypeContent.ACTIONLIB_MSGS_GOALSTATUS);
        return requestToJson(requestBean);
    }

    public static String initChargeServerResult() {
        RequestBean requestBean = new RequestBean();
        requestBean.setOp(OpContent.SUBSCRIBE);
        requestBean.setTopic(TopicContent.CHARGE_SERVER_RESULT);
        requestBean.setType(TypeContent.STD_MSGS_INT32);
        return requestToJson(requestBean);
    }

    public static String stopChargeServerResult() {
        RequestBean requestBean = new RequestBean();
        requestBean.setOp(OpContent.UNSUBSCRIBE);
        requestBean.setTopic(TopicContent.CHARGE_SERVER_RESULT);
        requestBean.setType(TypeContent.STD_MSGS_INT32);
        return requestToJson(requestBean);
    }

    public static String initSetHomePose() {
        RequestBean requestBean = new RequestBean();
        requestBean.setOp(OpContent.ADVERTISE);
        requestBean.setTopic(TopicContent.CHARGE_SERVER_HOME_POSE);
        requestBean.setType(TypeContent.GEOMETRY_MSGS_POSESTAMPED);
        return requestToJson(requestBean);
    }

    public static String setHomePose(float f, float f2, float f3) {
        QuaternionBean eular2quaternion = ConvertorUtils.eular2quaternion(0.0f, 0.0f, f3);
        SetHomePoseBean setHomePoseBean = new SetHomePoseBean();
        setHomePoseBean.setOp(OpContent.PUBLISH);
        setHomePoseBean.setTopic(TopicContent.CHARGE_SERVER_HOME_POSE);
        SetHomePoseBean.MsgBean msgBean = new SetHomePoseBean.MsgBean();
        SetHomePoseBean.MsgBean.HeaderBean headerBean = new SetHomePoseBean.MsgBean.HeaderBean();
        headerBean.setFrame_id(TopicContent.MAP);
        SetHomePoseBean.MsgBean.PoseBean poseBean = new SetHomePoseBean.MsgBean.PoseBean();
        SetHomePoseBean.MsgBean.PoseBean.OrientationBean orientationBean = new SetHomePoseBean.MsgBean.PoseBean.OrientationBean();
        orientationBean.setW(eular2quaternion.getW());
        orientationBean.setX(eular2quaternion.getX());
        orientationBean.setY(eular2quaternion.getY());
        orientationBean.setZ(eular2quaternion.getZ());
        SetHomePoseBean.MsgBean.PoseBean.PositionBean positionBean = new SetHomePoseBean.MsgBean.PoseBean.PositionBean();
        positionBean.setX(f);
        positionBean.setY(f2);
        poseBean.setOrientation(orientationBean);
        poseBean.setPosition(positionBean);
        msgBean.setHeader(headerBean);
        msgBean.setPose(poseBean);
        setHomePoseBean.setMsg(msgBean);
        return toJson(setHomePoseBean, SetHomePoseBean.class);
    }

    public static String stopSetHomePose() {
        RequestBean requestBean = new RequestBean();
        requestBean.setOp(OpContent.UNADVERTISE);
        requestBean.setTopic(TopicContent.CHARGE_SERVER_HOME_POSE);
        return requestToJson(requestBean);
    }

    public static String initSoftStop() {
        RequestBean requestBean = new RequestBean();
        requestBean.setOp(OpContent.ADVERTISE);
        requestBean.setTopic(TopicContent.SOFT_STOP);
        requestBean.setType(TypeContent.STD_MSGS_BOOL);
        return requestToJson(requestBean);
    }

    public static String softStop(boolean z) {
        SetEstopBean setEstopBean = new SetEstopBean();
        setEstopBean.setOp(OpContent.PUBLISH);
        setEstopBean.setTopic(TopicContent.SOFT_STOP);
        SetEstopBean.MsgBean msgBean = new SetEstopBean.MsgBean();
        msgBean.setData(z);
        setEstopBean.setMsg(msgBean);
        return toJson(setEstopBean, SetEstopBean.class);
    }

    public static String stopSoftStop() {
        RequestBean requestBean = new RequestBean();
        requestBean.setOp(OpContent.UNADVERTISE);
        requestBean.setTopic(TopicContent.SOFT_STOP);
        return requestToJson(requestBean);
    }

    public static String initSetVirtualWalls() {
        RequestBean requestBean = new RequestBean();
        requestBean.setOp(OpContent.ADVERTISE);
        requestBean.setTopic(TopicContent.SET_VIRTUAL_WALLS);
        requestBean.setType(TypeContent.YUTONG_ASSISTANCE_VIRTUALWALLS);
        return requestToJson(requestBean);
    }

    public static String setVirtualWalls(List<SetVirtualWallsBean.MsgBean.WallsBean> list) {
        SetVirtualWallsBean setVirtualWallsBean = new SetVirtualWallsBean();
        setVirtualWallsBean.setOp(OpContent.PUBLISH);
        setVirtualWallsBean.setTopic(TopicContent.SET_VIRTUAL_WALLS);
        SetVirtualWallsBean.MsgBean msgBean = new SetVirtualWallsBean.MsgBean();
        SetVirtualWallsBean.MsgBean.HeaderBean headerBean = new SetVirtualWallsBean.MsgBean.HeaderBean();
        headerBean.setFrame_id(ScheduleFragment.MAP);
        msgBean.setHeader(headerBean);
        msgBean.setWalls(list);
        setVirtualWallsBean.setMsg(msgBean);
        return toJson(setVirtualWallsBean, SetVirtualWallsBean.class);
    }

    public static String stopSetVirtualWalls() {
        RequestBean requestBean = new RequestBean();
        requestBean.setOp(OpContent.UNADVERTISE);
        requestBean.setTopic(TopicContent.SET_VIRTUAL_WALLS);
        return requestToJson(requestBean);
    }

    public static String initAppendVirtualWalls() {
        RequestBean requestBean = new RequestBean();
        requestBean.setOp(OpContent.ADVERTISE);
        requestBean.setTopic(TopicContent.APPEND_VIRTUAL_WALLS);
        requestBean.setType(TypeContent.YUTONG_ASSISTANCE_VIRTUALWALLS);
        return requestToJson(requestBean);
    }

    public static String appendVirtualWalls(SetVirtualWallsBean.MsgBean.WallsBean wallsBean) {
        SetVirtualWallsBean setVirtualWallsBean = new SetVirtualWallsBean();
        setVirtualWallsBean.setOp(OpContent.PUBLISH);
        setVirtualWallsBean.setTopic(TopicContent.APPEND_VIRTUAL_WALLS);
        SetVirtualWallsBean.MsgBean msgBean = new SetVirtualWallsBean.MsgBean();
        SetVirtualWallsBean.MsgBean.HeaderBean headerBean = new SetVirtualWallsBean.MsgBean.HeaderBean();
        headerBean.setFrame_id(ScheduleFragment.MAP);
        msgBean.setHeader(headerBean);
        ArrayList arrayList = new ArrayList();
        arrayList.add(wallsBean);
        msgBean.setWalls(arrayList);
        setVirtualWallsBean.setMsg(msgBean);
        return toJson(setVirtualWallsBean, SetVirtualWallsBean.class);
    }

    public static String stopAppendVirtualWalls() {
        RequestBean requestBean = new RequestBean();
        requestBean.setOp(OpContent.UNADVERTISE);
        requestBean.setTopic(TopicContent.APPEND_VIRTUAL_WALLS);
        return requestToJson(requestBean);
    }

    public static String initSetWayPoint() {
        RequestBean requestBean = new RequestBean();
        requestBean.setOp(OpContent.ADVERTISE);
        requestBean.setTopic(TopicContent.SET_WAYPOINTS);
        requestBean.setType(TypeContent.YUTONG_ASSISTANCE_WAYPOINTLIST);
        return requestToJson(requestBean);
    }

    public static String setWayPoint(List<SetWayPointBean.MsgBean.WaypointsBean> list) {
        SetWayPointBean setWayPointBean = new SetWayPointBean();
        setWayPointBean.setOp(OpContent.PUBLISH);
        setWayPointBean.setTopic(TopicContent.SET_WAYPOINTS);
        SetWayPointBean.MsgBean msgBean = new SetWayPointBean.MsgBean();
        msgBean.setWaypoints(list);
        setWayPointBean.setMsg(msgBean);
        return toJson(setWayPointBean, SetWayPointBean.class);
    }

    public static String stopSetWayPoint() {
        RequestBean requestBean = new RequestBean();
        requestBean.setOp(OpContent.UNADVERTISE);
        requestBean.setTopic(TopicContent.SET_WAYPOINTS);
        return requestToJson(requestBean);
    }

    public static String initAppendWayPoint() {
        RequestBean requestBean = new RequestBean();
        requestBean.setOp(OpContent.ADVERTISE);
        requestBean.setTopic(TopicContent.APPEND_WAYPOINTS);
        requestBean.setType(TypeContent.YUTONG_ASSISTANCE_WAYPOINTLIST);
        return requestToJson(requestBean);
    }

    public static String appendWayPoints(SetWayPointBean.MsgBean.WaypointsBean waypointsBean) {
        SetWayPointBean setWayPointBean = new SetWayPointBean();
        setWayPointBean.setOp(OpContent.PUBLISH);
        setWayPointBean.setTopic(TopicContent.APPEND_WAYPOINTS);
        SetWayPointBean.MsgBean msgBean = new SetWayPointBean.MsgBean();
        ArrayList arrayList = new ArrayList();
        arrayList.add(waypointsBean);
        msgBean.setWaypoints(arrayList);
        setWayPointBean.setMsg(msgBean);
        return toJson(setWayPointBean, SetWayPointBean.class);
    }

    public static String stopAppendWayPoint() {
        RequestBean requestBean = new RequestBean();
        requestBean.setOp(OpContent.UNADVERTISE);
        requestBean.setTopic(TopicContent.APPEND_WAYPOINTS);
        return requestToJson(requestBean);
    }

    public static String initInsertCurrentPoseMarker() {
        RequestBean requestBean = new RequestBean();
        requestBean.setOp(OpContent.ADVERTISE);
        requestBean.setTopic(TopicContent.INSERT_CURRENT_POSE_MARKER);
        requestBean.setType(TypeContent.YUTONG_ASSISTANCE_INSERTPOSE);
        return requestToJson(requestBean);
    }

    public static String insertCurrentPoseMarker(String str, int i) {
        InsertCurrentPoseMarkerPublishBean insertCurrentPoseMarkerPublishBean = new InsertCurrentPoseMarkerPublishBean();
        insertCurrentPoseMarkerPublishBean.setOp(OpContent.PUBLISH);
        insertCurrentPoseMarkerPublishBean.setTopic(TopicContent.INSERT_CURRENT_POSE_MARKER);
        InsertCurrentPoseMarkerPublishBean.MsgBean msgBean = new InsertCurrentPoseMarkerPublishBean.MsgBean();
        msgBean.setBehavior_code(i);
        msgBean.setName(str);
        insertCurrentPoseMarkerPublishBean.setMsg(msgBean);
        return toJson(insertCurrentPoseMarkerPublishBean, InsertCurrentPoseMarkerPublishBean.class);
    }

    public static String stopInsertCurrentPoseMarker() {
        RequestBean requestBean = new RequestBean();
        requestBean.setOp(OpContent.UNADVERTISE);
        requestBean.setTopic(TopicContent.INSERT_CURRENT_POSE_MARKER);
        return requestToJson(requestBean);
    }

    public static String initSetLayeredMapManagerPencilOp() {
        RequestBean requestBean = new RequestBean();
        requestBean.setOp(OpContent.ADVERTISE);
        requestBean.setTopic(TopicContent.LAYERED_MAP_MANAGER_PENCIL_OP);
        requestBean.setType(TypeContent.YUTONG_ASSISTANCE_PENCILOPLIST);
        return requestToJson(requestBean);
    }

    public static String setLayeredMapManagerPencilOp(List<SetLayeredMapManagerPencilOpPublishBean.MsgBean.PointInfoBean> list) {
        SetLayeredMapManagerPencilOpPublishBean setLayeredMapManagerPencilOpPublishBean = new SetLayeredMapManagerPencilOpPublishBean();
        setLayeredMapManagerPencilOpPublishBean.setOp(OpContent.PUBLISH);
        setLayeredMapManagerPencilOpPublishBean.setTopic(TopicContent.LAYERED_MAP_MANAGER_PENCIL_OP);
        SetLayeredMapManagerPencilOpPublishBean.MsgBean msgBean = new SetLayeredMapManagerPencilOpPublishBean.MsgBean();
        msgBean.setPoint_info(list);
        setLayeredMapManagerPencilOpPublishBean.setMsg(msgBean);
        return toJson(setLayeredMapManagerPencilOpPublishBean, SetLayeredMapManagerPencilOpPublishBean.class);
    }

    public static String stopSetLayeredMapManagerPencilOp() {
        RequestBean requestBean = new RequestBean();
        requestBean.setOp(OpContent.UNADVERTISE);
        requestBean.setTopic(TopicContent.LAYERED_MAP_MANAGER_PENCIL_OP);
        return requestToJson(requestBean);
    }

    public static String initGetCurrentMap() {
        RequestBean requestBean = new RequestBean();
        requestBean.setOp(OpContent.ADVERTISE);
        requestBean.setTopic(TopicContent.GET_CURRENT_MAP);
        requestBean.setType(TypeContent.STD_MSGS_BOOL);
        return requestToJson(requestBean);
    }

    public static String getCurrentMap() {
        GetCurrentMapBean getCurrentMapBean = new GetCurrentMapBean();
        GetCurrentMapBean.MsgBean msgBean = new GetCurrentMapBean.MsgBean();
        msgBean.setData(true);
        getCurrentMapBean.setTopic(TopicContent.GET_CURRENT_MAP);
        getCurrentMapBean.setOp(OpContent.PUBLISH);
        getCurrentMapBean.setMsg(msgBean);
        return toJson(getCurrentMapBean, GetCurrentMapBean.class);
    }

    public static String stopGetCurrentMap() {
        RequestBean requestBean = new RequestBean();
        requestBean.setOp(OpContent.UNADVERTISE);
        requestBean.setTopic(TopicContent.GET_CURRENT_MAP);
        return requestToJson(requestBean);
    }

    public static String initApriltagsBuffer() {
        RequestBean requestBean = new RequestBean();
        requestBean.setOp(OpContent.SUBSCRIBE);
        requestBean.setTopic(TopicContent.APRIL_TAGS_BUFFER);
        requestBean.setType(TypeContent.YUTONG_ASSISTANCE_WAYPOINTLIST);
        return requestToJson(requestBean);
    }

    public static String stopApriltagsBuffer() {
        RequestBean requestBean = new RequestBean();
        requestBean.setOp(OpContent.UNSUBSCRIBE);
        requestBean.setTopic(TopicContent.APRIL_TAGS_BUFFER);
        requestBean.setType(TypeContent.YUTONG_ASSISTANCE_WAYPOINTLIST);
        return requestToJson(requestBean);
    }

    public static String initCurrentTag() {
        RequestBean requestBean = new RequestBean();
        requestBean.setOp(OpContent.SUBSCRIBE);
        requestBean.setTopic(TopicContent.CURRENT_TAG);
        requestBean.setType(TypeContent.STD_MSGS_STRING);
        requestBean.setId(IDContent.TagManagerControl.GET_CURRENT_TAG);
        return requestToJson(requestBean);
    }

    public static String stopCurrentTag() {
        RequestBean requestBean = new RequestBean();
        requestBean.setOp(OpContent.UNSUBSCRIBE);
        requestBean.setTopic(TopicContent.CURRENT_TAG);
        requestBean.setType(TypeContent.STD_MSGS_STRING);
        requestBean.setId(IDContent.TagManagerControl.GET_CURRENT_TAG);
        return requestToJson(requestBean);
    }

    public static String initUpgradeDownloadStatus() {
        RequestBean requestBean = new RequestBean();
        requestBean.setOp(OpContent.SUBSCRIBE);
        requestBean.setTopic(TopicContent.UPGRADE_DOWNLOAD_STATUS);
        requestBean.setType(TypeContent.STD_MSGS_FLOAT64);
        requestBean.setId(IDContent.GET_UPGRADEDOWNLOADSTATUS);
        return requestToJson(requestBean);
    }

    public static String initGetRobotList() {
        RequestBean requestBean = new RequestBean();
        requestBean.setOp(OpContent.SUBSCRIBE);
        requestBean.setTopic(TopicContent.ROBOT_LIST);
        requestBean.setType(TypeContent.MQTTCLIENT_ROBOT_LIST);
        requestBean.setId(IDContent.GET_ROBOT_LIST);
        return requestToJson(requestBean);
    }

    public static String stopUpgradeDownloadStatus() {
        RequestBean requestBean = new RequestBean();
        requestBean.setOp(OpContent.UNSUBSCRIBE);
        requestBean.setTopic(TopicContent.UPGRADE_DOWNLOAD_STATUS);
        requestBean.setType(TypeContent.STD_MSGS_FLOAT64);
        requestBean.setId(IDContent.GET_UPGRADEDOWNLOADSTATUS);
        return requestToJson(requestBean);
    }

    public static String initLocalizationConfidence() {
        RequestBean requestBean = new RequestBean();
        requestBean.setOp(OpContent.SUBSCRIBE);
        requestBean.setTopic(TopicContent.LOCALIZATION_CONFIDENCE);
        requestBean.setType(TypeContent.STD_MSGS_FLOAT64);
        return requestToJson(requestBean);
    }

    public static String stopLocalizationConfidence() {
        RequestBean requestBean = new RequestBean();
        requestBean.setOp(OpContent.UNSUBSCRIBE);
        requestBean.setTopic(TopicContent.LOCALIZATION_CONFIDENCE);
        requestBean.setType(TypeContent.STD_MSGS_FLOAT64);
        return requestToJson(requestBean);
    }

    public static String initLaserSafetyControllerConfidenceThreshold() {
        RequestBean requestBean = new RequestBean();
        requestBean.setOp(OpContent.ADVERTISE);
        requestBean.setTopic(TopicContent.LASER_SAFETY_CONTROLLER_SETTING_CONFIDENCE_THRESHOLD);
        requestBean.setType(TypeContent.STD_MSGS_FLOAT64);
        return requestToJson(requestBean);
    }

    public static String laserSafetyControllerConfidenceThreshold(float f) {
        LaserSafetyControllerConfidenceThresholdBean laserSafetyControllerConfidenceThresholdBean = new LaserSafetyControllerConfidenceThresholdBean();
        LaserSafetyControllerConfidenceThresholdBean.MsgBean msgBean = new LaserSafetyControllerConfidenceThresholdBean.MsgBean();
        msgBean.setData(f);
        laserSafetyControllerConfidenceThresholdBean.setTopic(TopicContent.LASER_SAFETY_CONTROLLER_SETTING_CONFIDENCE_THRESHOLD);
        laserSafetyControllerConfidenceThresholdBean.setOp(OpContent.PUBLISH);
        laserSafetyControllerConfidenceThresholdBean.setMsg(msgBean);
        return toJson(laserSafetyControllerConfidenceThresholdBean, LaserSafetyControllerConfidenceThresholdBean.class);
    }

    public static String stopLaserSafetyControllerConfidenceThreshold() {
        RequestBean requestBean = new RequestBean();
        requestBean.setOp(OpContent.UNADVERTISE);
        requestBean.setTopic(TopicContent.LASER_SAFETY_CONTROLLER_SETTING_CONFIDENCE_THRESHOLD);
        requestBean.setType(TypeContent.STD_MSGS_FLOAT64);
        return requestToJson(requestBean);
    }

    public static String initObstacleRegion() {
        RequestBean requestBean = new RequestBean();
        requestBean.setOp(OpContent.SUBSCRIBE);
        requestBean.setTopic(TopicContent.OBSTACLE_REGION);
        requestBean.setType(TypeContent.STD_MSGS_INT8);
        return requestToJson(requestBean);
    }

    public static String stopObstacleRegion() {
        RequestBean requestBean = new RequestBean();
        requestBean.setTopic(TopicContent.OBSTACLE_REGION);
        requestBean.setType(TypeContent.STD_MSGS_INT8);
        requestBean.setOp(OpContent.UNSUBSCRIBE);
        return requestToJson(requestBean);
    }

    public static String initNodeManagerLaserSafetyController() {
        RequestBean requestBean = new RequestBean();
        requestBean.setTopic(TopicContent.NODE_MANAGER_LASER_SAFETY_CONTROLLER);
        requestBean.setType(TypeContent.STD_MSGS_BOOL);
        requestBean.setOp(OpContent.ADVERTISE);
        return requestToJson(requestBean);
    }

    public static String nodeManagerLaserSafetyController(boolean z) {
        NodeManagerLaserSafetyControllerBean nodeManagerLaserSafetyControllerBean = new NodeManagerLaserSafetyControllerBean();
        NodeManagerLaserSafetyControllerBean.MsgBean msgBean = new NodeManagerLaserSafetyControllerBean.MsgBean();
        msgBean.setData(z);
        nodeManagerLaserSafetyControllerBean.setMsg(msgBean);
        nodeManagerLaserSafetyControllerBean.setTopic(TopicContent.NODE_MANAGER_LASER_SAFETY_CONTROLLER);
        nodeManagerLaserSafetyControllerBean.setOp(OpContent.PUBLISH);
        return toJson(nodeManagerLaserSafetyControllerBean, NodeManagerLaserSafetyControllerBean.class);
    }

    public static String stopNodeManagerLaserSafetyController() {
        RequestBean requestBean = new RequestBean();
        requestBean.setTopic(TopicContent.NODE_MANAGER_LASER_SAFETY_CONTROLLER);
        requestBean.setOp(OpContent.UNADVERTISE);
        return requestToJson(requestBean);
    }

    public static String initNodeManagerLaserSafetyRange() {
        RequestBean requestBean = new RequestBean();
        requestBean.setTopic(TopicContent.NODE_MANAGER_LASER_SAFETY_RANGE);
        requestBean.setType(TypeContent.STD_MSGS_FLOAT64);
        requestBean.setOp(OpContent.ADVERTISE);
        return requestToJson(requestBean);
    }

    public static String nodeManagerLaserSafetyRange(float f) {
        NodeManagerLaserSafetyRangeBean nodeManagerLaserSafetyRangeBean = new NodeManagerLaserSafetyRangeBean();
        NodeManagerLaserSafetyRangeBean.MsgBean msgBean = new NodeManagerLaserSafetyRangeBean.MsgBean();
        msgBean.setData(f);
        nodeManagerLaserSafetyRangeBean.setMsg(msgBean);
        nodeManagerLaserSafetyRangeBean.setTopic(TopicContent.NODE_MANAGER_LASER_SAFETY_RANGE);
        nodeManagerLaserSafetyRangeBean.setOp(OpContent.PUBLISH);
        return toJson(nodeManagerLaserSafetyRangeBean, NodeManagerLaserSafetyRangeBean.class);
    }

    public static String stopNodeManagerLaserSafetyRange() {
        RequestBean requestBean = new RequestBean();
        requestBean.setTopic(TopicContent.NODE_MANAGER_LASER_SAFETY_RANGE);
        requestBean.setOp(OpContent.UNADVERTISE);
        return requestToJson(requestBean);
    }

    public static String initMobileBaseSensorsCore() {
        RequestBean requestBean = new RequestBean();
        requestBean.setTopic(TopicContent.MOBILE_BASE_SENSORS_CORE);
        requestBean.setType(TypeContent.KOBUKI_MSGS_SENSORSTATE);
        requestBean.setOp(OpContent.SUBSCRIBE);
        return requestToJson(requestBean);
    }

    public static String initNotification() {
        RequestBean requestBean = new RequestBean();
        requestBean.setTopic(TopicContent.NOTIFICATION);
        requestBean.setType(TypeContent.ROSGRAPH_MSGS_LOG);
        requestBean.setOp(OpContent.SUBSCRIBE);
        return requestToJson(requestBean);
    }

    public static String initCurrentMiles() {
        RequestBean requestBean = new RequestBean();
        requestBean.setTopic(TopicContent.CURRENT_MILES);
        requestBean.setType(TypeContent.STD_MSGS_FLOAT64);
        requestBean.setOp(OpContent.SUBSCRIBE);
        return requestToJson(requestBean);
    }

    public static String stopMobileBaseSensorsCore() {
        RequestBean requestBean = new RequestBean();
        requestBean.setTopic(TopicContent.MOBILE_BASE_SENSORS_CORE);
        requestBean.setType(TypeContent.KOBUKI_MSGS_SENSORSTATE);
        requestBean.setOp(OpContent.UNSUBSCRIBE);
        return requestToJson(requestBean);
    }

    public static String stopNotification() {
        RequestBean requestBean = new RequestBean();
        requestBean.setTopic(TopicContent.NOTIFICATION);
        requestBean.setType(TypeContent.ROSGRAPH_MSGS_LOG);
        requestBean.setOp(OpContent.UNSUBSCRIBE);
        return requestToJson(requestBean);
    }

    public static String stopCurrentMiles() {
        RequestBean requestBean = new RequestBean();
        requestBean.setTopic(TopicContent.CURRENT_MILES);
        requestBean.setType(TypeContent.STD_MSGS_FLOAT64);
        requestBean.setOp(OpContent.UNSUBSCRIBE);
        return requestToJson(requestBean);
    }

    public static String serviceNodeManagerControl(int i, String str, String str2) {
        RequestBean requestBean = new RequestBean();
        requestBean.setOp("call_service");
        requestBean.setService(ServiceContent.NODE_MANAGER_CONTROL);
        requestBean.setId(String.valueOf(i));
        RequestBean.ArgsBean argsBean = new RequestBean.ArgsBean();
        argsBean.setArgs(0);
        argsBean.setCmd(Integer.valueOf(i));
        argsBean.setFloor_num(str);
        argsBean.setBuilding_name(str2);
        requestBean.setArgs(argsBean);
        return requestToJson(requestBean);
    }

    public static String serviceLayeredMapCmdBase(int i, String str) {
        RequestBean requestBean = new RequestBean();
        requestBean.setId(str);
        requestBean.setOp("call_service");
        requestBean.setService(ServiceContent.LAYERED_MAP_CMD);
        RequestBean.ArgsBean argsBean = new RequestBean.ArgsBean();
        argsBean.setOp(Integer.valueOf(i));
        requestBean.setArgs(argsBean);
        return requestToJson(requestBean);
    }

    public static String serviceLayeredMapCmd() {
        return serviceLayeredMapCmdBase(0, IDContent.SERVICE_LAYERED_MAP_CMD);
    }

    public static String serviceMarkerOperationGetMarkers() {
        RequestBean requestBean = new RequestBean();
        requestBean.setOp("call_service");
        requestBean.setService(ServiceContent.MARKER_OPERATION_GET_MARKERS);
        RequestBean.ArgsBean argsBean = new RequestBean.ArgsBean();
        argsBean.setOp(0);
        requestBean.setArgs(argsBean);
        return requestToJson(requestBean);
    }

    public static String serviceMarkerOperationDeleteMarkersLocal() {
        RequestBean requestBean = new RequestBean();
        requestBean.setOp("call_service");
        requestBean.setService(ServiceContent.MARKER_OPERATION_DELETE_MARKERS);
        RequestBean.ArgsBean argsBean = new RequestBean.ArgsBean();
        argsBean.setOp(1);
        requestBean.setArgs(argsBean);
        return requestToJson(requestBean);
    }

    public static String serviceMarkerOperationDeleteMarkersTarget(String str, String str2) {
        RequestBean requestBean = new RequestBean();
        requestBean.setOp("call_service");
        requestBean.setService(ServiceContent.MARKER_OPERATION_DELETE_MARKERS);
        RequestBean.ArgsBean argsBean = new RequestBean.ArgsBean();
        argsBean.setOp(0);
        argsBean.setFloor_name(str2);
        argsBean.setBuilding_name(str);
        requestBean.setArgs(argsBean);
        return requestToJson(requestBean);
    }

    public static String serviceMarkerManagerDeletePoi(String str) {
        RequestBean requestBean = new RequestBean();
        requestBean.setOp("call_service");
        requestBean.setService(ServiceContent.MARKER_MANAGER_DELETE_POI);
        RequestBean.ArgsBean argsBean = new RequestBean.ArgsBean();
        argsBean.setPoi(str);
        requestBean.setArgs(argsBean);
        return requestToJson(requestBean);
    }

    public static String serviceGetVirtualWalls() {
        RequestBean requestBean = new RequestBean();
        requestBean.setService(ServiceContent.VIRTUAL_WALL_OPERATION_GET_WALLS);
        requestBean.setOp("call_service");
        RequestBean.ArgsBean argsBean = new RequestBean.ArgsBean();
        argsBean.setOp(0);
        requestBean.setArgs(argsBean);
        return requestToJson(requestBean);
    }

    public static String serviceVirtualWallOperationDeleteWallsLocal() {
        RequestBean requestBean = new RequestBean();
        requestBean.setService(ServiceContent.VIRTUAL_WALL_OPERATION_DELETE_WALLS);
        requestBean.setOp("call_service");
        RequestBean.ArgsBean argsBean = new RequestBean.ArgsBean();
        argsBean.setOp(1);
        requestBean.setArgs(argsBean);
        return requestToJson(requestBean);
    }

    public static String serviceVirtualWallOperationDeleteWallsTarget(String str, String str2) {
        RequestBean requestBean = new RequestBean();
        requestBean.setService(ServiceContent.VIRTUAL_WALL_OPERATION_DELETE_WALLS);
        requestBean.setOp("call_service");
        RequestBean.ArgsBean argsBean = new RequestBean.ArgsBean();
        argsBean.setOp(0);
        argsBean.setBuilding_name(str);
        argsBean.setFloor_name(str2);
        requestBean.setArgs(argsBean);
        return requestToJson(requestBean);
    }

    public static String serviceVirtualWallManagerDeletePoie(String str) {
        RequestBean requestBean = new RequestBean();
        requestBean.setService(ServiceContent.VIRTUAL_WALL_MANAGER_DELETE_POIE);
        requestBean.setOp("call_service");
        RequestBean.ArgsBean argsBean = new RequestBean.ArgsBean();
        argsBean.setPoi(str);
        requestBean.setArgs(argsBean);
        return requestToJson(requestBean);
    }

    public static String servicePoi(String str) {
        RequestBean requestBean = new RequestBean();
        requestBean.setOp("call_service");
        requestBean.setService(ServiceContent.POI);
        RequestBean.ArgsBean argsBean = new RequestBean.ArgsBean();
        argsBean.setPoi(str);
        requestBean.setArgs(argsBean);
        return requestToJson(requestBean);
    }

    public static String servicePoiId(String str, String str2) {
        RequestBean requestBean = new RequestBean();
        requestBean.setOp("call_service");
        requestBean.setService(ServiceContent.POI_ID);
        RequestBean.ArgsBean argsBean = new RequestBean.ArgsBean();
        argsBean.setPoi(str2);
        argsBean.setUuid(str);
        requestBean.setArgs(argsBean);
        return requestToJson(requestBean);
    }

    public static String servicePoiInit(String str) {
        RequestBean requestBean = new RequestBean();
        requestBean.setService(ServiceContent.POI_INIT);
        requestBean.setOp("call_service");
        RequestBean.ArgsBean argsBean = new RequestBean.ArgsBean();
        argsBean.setPoi(str);
        requestBean.setArgs(argsBean);
        return requestToJson(requestBean);
    }

    public static String servicePoiPatrol(int i, int i2, int i3, List<String> list) {
        RequestBean requestBean = new RequestBean();
        requestBean.setOp("call_service");
        requestBean.setService(ServiceContent.POI_PATROL);
        RequestBean.ArgsBean argsBean = new RequestBean.ArgsBean();
        argsBean.setCmd(Integer.valueOf(i));
        argsBean.setPatrol_time(Integer.valueOf(i2));
        argsBean.setPatrol_mode(Integer.valueOf(i3));
        argsBean.setPoi_patrol_list(list);
        requestBean.setArgs(argsBean);
        return requestToJson(requestBean);
    }

    public static String serviceStartRecharge(int i, String str) {
        RequestBean requestBean = new RequestBean();
        requestBean.setOp("call_service");
        requestBean.setService(ServiceContent.START_RECHARGE);
        RequestBean.ArgsBean argsBean = new RequestBean.ArgsBean();
        argsBean.setCmd(Integer.valueOf(i));
        argsBean.setStr(str);
        requestBean.setArgs(argsBean);
        return requestToJson(requestBean);
    }

    public static String serviceGetMapInfo() {
        RequestBean requestBean = new RequestBean();
        requestBean.setOp("call_service");
        requestBean.setService(ServiceContent.GET_MAP_INFO);
        RequestBean.ArgsBean argsBean = new RequestBean.ArgsBean();
        argsBean.setCmd(0);
        requestBean.setArgs(argsBean);
        return requestToJson(requestBean);
    }

    public static String serviceBuildingOperationDelete(String str, String str2) {
        RequestBean requestBean = new RequestBean();
        requestBean.setOp("call_service");
        requestBean.setService(ServiceContent.BUILDING_OPERATION_DELETE);
        RequestBean.ArgsBean argsBean = new RequestBean.ArgsBean();
        argsBean.setOp(0);
        argsBean.setFloor_name(str);
        argsBean.setBuilding_name(str2);
        requestBean.setArgs(argsBean);
        return requestToJson(requestBean);
    }

    public static String serviceVelocityControl(int i) {
        RequestBean requestBean = new RequestBean();
        if (i == 99) {
            requestBean.setId(IDContent.VelocityControl.GET_SPEED);
        } else {
            requestBean.setId(IDContent.VelocityControl.SET_SPEED);
        }
        requestBean.setOp("call_service");
        requestBean.setService(ServiceContent.VELOCITY_CONTROL);
        RequestBean.ArgsBean argsBean = new RequestBean.ArgsBean();
        argsBean.setCmd(Integer.valueOf(i));
        argsBean.setStr("");
        requestBean.setArgs(argsBean);
        return requestToJson(requestBean);
    }

    public static String serviceVersionUpgrade(int i) {
        return serviceVersionUpgrade(i, "");
    }

    public static String serviceVersionUpgrade(int i, String str) {
        RequestBean requestBean = new RequestBean();
        requestBean.setId(String.valueOf(i));
        requestBean.setOp("call_service");
        requestBean.setService(ServiceContent.VERSION_UPGRADE);
        RequestBean.ArgsBean argsBean = new RequestBean.ArgsBean();
        argsBean.setCmd(Integer.valueOf(i));
        argsBean.setSpec_version(str);
        requestBean.setArgs(argsBean);
        return requestToJson(requestBean);
    }

    public static String serviceCancelUpgradeDownload() {
        RequestBean requestBean = new RequestBean();
        requestBean.setId(IDContent.SERVICE_CANCEL_UPGRADE_DOWNLOAD);
        requestBean.setOp("call_service");
        requestBean.setService(ServiceContent.CANCEL_UPGRADE_DOWNLOAD);
        RequestBean.ArgsBean argsBean = new RequestBean.ArgsBean();
        argsBean.setCmd(18);
        argsBean.setStr("");
        requestBean.setArgs(argsBean);
        return requestToJson(requestBean);
    }

    public static String laserDetectionSetting(int i, double d, boolean z) {
        RequestBean requestBean = new RequestBean();
        requestBean.setId(IDContent.SERVICE_LASER_DETECTION_SETTING);
        requestBean.setOp("call_service");
        requestBean.setService(ServiceContent.LASER_DETECTION_SETTING);
        RequestBean.ArgsBean argsBean = new RequestBean.ArgsBean();
        argsBean.setCmd(Integer.valueOf(i));
        ArrayList arrayList = new ArrayList();
        SensorFeaturesBean sensorFeaturesBean = new SensorFeaturesBean(NaviSettingContent.LASER_DETECTION_RANGE, false);
        sensorFeaturesBean.setValue_ext(d);
        arrayList.add(sensorFeaturesBean);
        arrayList.add(new SensorFeaturesBean(NaviSettingContent.LASER_DETECTION_NOTIFICATION, z));
        argsBean.setFeatures(arrayList);
        requestBean.setArgs(argsBean);
        return requestToJson(requestBean);
    }

    public static String labelCameraExtrinsicConfig(int i) {
        RequestBean requestBean = new RequestBean();
        requestBean.setId(IDContent.SERVICE_LABELCAMERA_EXTRINSIC_CONFIG);
        requestBean.setOp("call_service");
        requestBean.setService(ServiceContent.LABEL_CAMERA_EXTRINSIC_CONFIG);
        RequestBean.ArgsBean argsBean = new RequestBean.ArgsBean();
        argsBean.setCmd(Integer.valueOf(i));
        requestBean.setArgs(argsBean);
        return requestToJson(requestBean);
    }

    public static String locatedModeConfig(int i) {
        RequestBean requestBean = new RequestBean();
        requestBean.setId(IDContent.SERVICE_CHANGE_LOCATION_MODE);
        requestBean.setOp("call_service");
        requestBean.setService(ServiceContent.CHANGE_LOCATION_MODE);
        RequestBean.ArgsBean argsBean = new RequestBean.ArgsBean();
        argsBean.setCmd(Integer.valueOf(i));
        requestBean.setArgs(argsBean);
        return requestToJson(requestBean);
    }

    public static String serviceSelfDiagnosis() {
        RequestBean requestBean = new RequestBean();
        requestBean.setOp("call_service");
        requestBean.setService(ServiceContent.SELF_DIAGNOSIS);
        requestBean.setArgs(new RequestBean.ArgsBean());
        return requestToJson(requestBean);
    }

    public static String serviceRobotInfo() {
        RequestBean requestBean = new RequestBean();
        requestBean.setOp("call_service");
        requestBean.setService(ServiceContent.ROBOT_INFO);
        requestBean.setArgs(new RequestBean.ArgsBean());
        return requestToJson(requestBean);
    }

    public static String serviceGetTime() {
        RequestBean requestBean = new RequestBean();
        requestBean.setOp("call_service");
        requestBean.setService(ServiceContent.GET_TIME);
        requestBean.setArgs(new RequestBean.ArgsBean());
        return requestToJson(requestBean);
    }

    public static String serviceGlobalLocate() {
        RequestBean requestBean = new RequestBean();
        requestBean.setOp("call_service");
        requestBean.setService(ServiceContent.GLOBAL_LOCATE);
        requestBean.setId(IDContent.SERVICE_GLOBAL_LOCATE_GLOBAL);
        RequestBean.ArgsBean argsBean = new RequestBean.ArgsBean();
        argsBean.setCmd(0);
        requestBean.setArgs(argsBean);
        return requestToJson(requestBean);
    }

    public static String serviceGlobalLocateLocal(float f, int i, List<GlobalLocateReqBean.ArgsBean.SearchBoundaryBean.PolygonBean.PointsBean> list) {
        GlobalLocateReqBean globalLocateReqBean = new GlobalLocateReqBean();
        globalLocateReqBean.setOp("call_service");
        globalLocateReqBean.setService(ServiceContent.GLOBAL_LOCATE);
        globalLocateReqBean.setId(IDContent.SERVICE_GLOBAL_LOCATE_LOCAL);
        GlobalLocateReqBean.ArgsBean argsBean = new GlobalLocateReqBean.ArgsBean();
        argsBean.setCmd(1);
        argsBean.setSearch_step_angular(i);
        argsBean.setSearch_step_linear((double) f);
        GlobalLocateReqBean.ArgsBean.SearchBoundaryBean searchBoundaryBean = new GlobalLocateReqBean.ArgsBean.SearchBoundaryBean();
        GlobalLocateReqBean.ArgsBean.SearchBoundaryBean.HeaderBean headerBean = new GlobalLocateReqBean.ArgsBean.SearchBoundaryBean.HeaderBean();
        headerBean.setSeq(0);
        headerBean.setFrame_id(TopicContent.MAP);
        headerBean.setStamp(0.0d);
        searchBoundaryBean.setHeader(headerBean);
        GlobalLocateReqBean.ArgsBean.SearchBoundaryBean.PolygonBean polygonBean = new GlobalLocateReqBean.ArgsBean.SearchBoundaryBean.PolygonBean();
        polygonBean.setPoints(list);
        searchBoundaryBean.setPolygon(polygonBean);
        argsBean.setSearch_boundary(searchBoundaryBean);
        globalLocateReqBean.setArgs(argsBean);
        return GsonUtils.toJson(globalLocateReqBean);
    }

    public static String serviceTagManagerMode(int i) {
        RequestBean requestBean = new RequestBean();
        requestBean.setOp("call_service");
        requestBean.setService(ServiceContent.TAG_MANAGER_MODE);
        requestBean.setId(i + "");
        RequestBean.ArgsBean argsBean = new RequestBean.ArgsBean();
        argsBean.setCmd(Integer.valueOf(i));
        argsBean.setStr("");
        requestBean.setArgs(argsBean);
        return requestToJson(requestBean);
    }

    public static String serviceTagManagerDeletePoi(String str) {
        RequestBean requestBean = new RequestBean();
        requestBean.setOp("call_service");
        requestBean.setService(ServiceContent.TAG_MANAGER_DELETE_POI);
        RequestBean.ArgsBean argsBean = new RequestBean.ArgsBean();
        argsBean.setPoi(str);
        requestBean.setArgs(argsBean);
        return requestToJson(requestBean);
    }

    public static String serviceTagManagerControl() {
        RequestBean requestBean = new RequestBean();
        requestBean.setOp("call_service");
        requestBean.setService(ServiceContent.TAG_MANAGER_CONTROL);
        requestBean.setId(IDContent.TagManagerControl.SAVE);
        RequestBean.ArgsBean argsBean = new RequestBean.ArgsBean();
        argsBean.setOp(2);
        argsBean.setArg("");
        argsBean.setMap_name("");
        requestBean.setArgs(argsBean);
        return requestToJson(requestBean);
    }

    public static String serviceTagManagerControlDeleteAll() {
        RequestBean requestBean = new RequestBean();
        requestBean.setOp("call_service");
        requestBean.setService(ServiceContent.TAG_MANAGER_CONTROL);
        requestBean.setId("service_area_manager_delete_all");
        RequestBean.ArgsBean argsBean = new RequestBean.ArgsBean();
        argsBean.setOp(4);
        requestBean.setArgs(argsBean);
        return requestToJson(requestBean);
    }

    public static String serviceUploadMaps(int i, String str, List<RequestBean.ArgsBean.FloorBean> list) {
        RequestBean requestBean = new RequestBean();
        requestBean.setOp("call_service");
        requestBean.setService(ServiceContent.UPLOAD_MAPS);
        RequestBean.ArgsBean argsBean = new RequestBean.ArgsBean();
        argsBean.setCmd(Integer.valueOf(i));
        argsBean.setBuilding_name(str);
        argsBean.setFloor(list);
        requestBean.setArgs(argsBean);
        return requestToJson(requestBean);
    }

    public static String serviceDownloadMaps(int i, String str, List<RequestBean.ArgsBean.FloorBean> list) {
        RequestBean requestBean = new RequestBean();
        requestBean.setOp("call_service");
        requestBean.setService(ServiceContent.DOWNLOAD_MAPS);
        RequestBean.ArgsBean argsBean = new RequestBean.ArgsBean();
        argsBean.setCmd(Integer.valueOf(i));
        argsBean.setBuilding_name(str);
        argsBean.setFloor(list);
        requestBean.setArgs(argsBean);
        return requestToJson(requestBean);
    }

    public static String serviceUploadMaps(String str, List<RequestBean.ArgsBean.FloorBean> list) {
        return serviceUploadMaps(0, str, list);
    }

    public static String serviceDownloadMaps(String str, List<RequestBean.ArgsBean.FloorBean> list) {
        return serviceDownloadMaps(0, str, list);
    }

    public static String serviceGetMatchThreshold() {
        RequestBean requestBean = new RequestBean();
        requestBean.setOp("call_service");
        requestBean.setService(ServiceContent.GET_MATCH_THRESHOLD);
        RequestBean.ArgsBean argsBean = new RequestBean.ArgsBean();
        argsBean.setCmd(0);
        argsBean.setStr("");
        requestBean.setArgs(argsBean);
        return requestToJson(requestBean);
    }

    public static String serviceRecordMiles(boolean z) {
        RequestBean requestBean = new RequestBean();
        requestBean.setOp("call_service");
        requestBean.setService(ServiceContent.RECORD_MILES);
        RequestBean.ArgsBean argsBean = new RequestBean.ArgsBean();
        argsBean.setCmd(1);
        argsBean.setStr("");
        requestBean.setArgs(argsBean);
        return requestToJson(requestBean);
    }

    public static String serviceGetSensors() {
        RequestBean requestBean = new RequestBean();
        requestBean.setId(String.valueOf(2));
        requestBean.setOp("call_service");
        requestBean.setService(ServiceContent.SENSORS_CONFIG);
        RequestBean.ArgsBean argsBean = new RequestBean.ArgsBean();
        argsBean.setCmd(2);
        requestBean.setArgs(argsBean);
        return requestToJson(requestBean);
    }

    public static String serviceSensorsConfig(List<SensorFeaturesBean> list) {
        RequestBean requestBean = new RequestBean();
        requestBean.setId(String.valueOf(1));
        requestBean.setOp("call_service");
        requestBean.setService(ServiceContent.SENSORS_CONFIG);
        RequestBean.ArgsBean argsBean = new RequestBean.ArgsBean();
        argsBean.setCmd(1);
        argsBean.setFeatures(list);
        requestBean.setArgs(argsBean);
        return requestToJson(requestBean);
    }

    public static String serviceGetMarkersDetails(String str, int i, String str2) {
        MarkersDetailsRequestBean markersDetailsRequestBean = new MarkersDetailsRequestBean();
        markersDetailsRequestBean.setOp("call_service");
        markersDetailsRequestBean.setService(ServiceContent.GET_MARKERS_DETAILS);
        MarkersDetailsRequestBean.ArgsBean argsBean = new MarkersDetailsRequestBean.ArgsBean();
        argsBean.setFloorName(str);
        argsBean.setType(Integer.valueOf(i));
        argsBean.setBuildingName(str2);
        markersDetailsRequestBean.setArgs(argsBean);
        return GsonUtils.toJson(markersDetailsRequestBean);
    }

    public static String initCrossFloorNaviTopic() {
        RequestBean requestBean = new RequestBean();
        requestBean.setOp(OpContent.ADVERTISE);
        requestBean.setTopic(TopicContent.CROSS_FLOOR_NAVI);
        requestBean.setType(TypeContent.YUTONG_ASSISTANCE_CROSSFLOORNAVI);
        return requestToJson(requestBean);
    }

    public static String stopCrossFloorNaviTopic() {
        RequestBean requestBean = new RequestBean();
        requestBean.setOp(OpContent.UNADVERTISE);
        requestBean.setTopic(TopicContent.CROSS_FLOOR_NAVI);
        return requestToJson(requestBean);
    }

    public static String startCrossFloorNavi(CrossFloorNaviReqBean.MsgBean msgBean) {
        CrossFloorNaviReqBean crossFloorNaviReqBean = new CrossFloorNaviReqBean();
        crossFloorNaviReqBean.setOp(OpContent.PUBLISH);
        crossFloorNaviReqBean.setTopic(TopicContent.CROSS_FLOOR_NAVI);
        crossFloorNaviReqBean.setMsg(msgBean);
        MyLogUtils.Logd("startCrossFloorNavi===>" + GsonUtils.toJson(crossFloorNaviReqBean));
        return GsonUtils.toJson(crossFloorNaviReqBean);
    }

    public static String initLiftControlStatus() {
        RequestBean requestBean = new RequestBean();
        requestBean.setTopic(TopicContent.LIFT_CONTROL_STATUS);
        requestBean.setType(TypeContent.STD_MSGS_INT32);
        requestBean.setOp(OpContent.SUBSCRIBE);
        return requestToJson(requestBean);
    }

    public static String stopLiftControlStatus() {
        RequestBean requestBean = new RequestBean();
        requestBean.setTopic(TopicContent.LIFT_CONTROL_STATUS);
        requestBean.setType(TypeContent.STD_MSGS_INT32);
        requestBean.setOp(OpContent.UNSUBSCRIBE);
        return requestToJson(requestBean);
    }

    public static String serviceLiftControlCancel() {
        RequestBean requestBean = new RequestBean();
        requestBean.setOp("call_service");
        requestBean.setId(IDContent.SERVICE_CROSS_FLOOR_CANCEL);
        requestBean.setService(ServiceContent.LIFT_CONTROL_COMMAND);
        RequestBean.ArgsBean argsBean = new RequestBean.ArgsBean();
        argsBean.setCmd(0);
        requestBean.setArgs(argsBean);
        return requestToJson(requestBean);
    }

    public static String initLiftControlForceCancelTopic() {
        RequestBean requestBean = new RequestBean();
        requestBean.setOp(OpContent.ADVERTISE);
        requestBean.setTopic(TopicContent.LIFT_CONTROL_FORCE_CANCEL);
        requestBean.setType("actionlib_msgs/GoalID");
        return requestToJson(requestBean);
    }

    public static String stopLiftControlForceCancelTopic() {
        RequestBean requestBean = new RequestBean();
        requestBean.setOp(OpContent.UNADVERTISE);
        requestBean.setTopic(TopicContent.LIFT_CONTROL_FORCE_CANCEL);
        return requestToJson(requestBean);
    }

    public static String liftControlForceCancel() {
        LiftControlForceCancelReqBean liftControlForceCancelReqBean = new LiftControlForceCancelReqBean();
        liftControlForceCancelReqBean.setOp(OpContent.PUBLISH);
        liftControlForceCancelReqBean.setTopic(TopicContent.LIFT_CONTROL_FORCE_CANCEL);
        LiftControlForceCancelReqBean.MsgBean msgBean = new LiftControlForceCancelReqBean.MsgBean();
        msgBean.setStamp("");
        msgBean.setId("");
        liftControlForceCancelReqBean.setMsg(msgBean);
        MyLogUtils.Logd("liftControlForceCancel===>" + GsonUtils.toJson(liftControlForceCancelReqBean));
        return GsonUtils.toJson(liftControlForceCancelReqBean);
    }

    public static String initAppendArea() {
        RequestBean requestBean = new RequestBean();
        requestBean.setOp(OpContent.ADVERTISE);
        requestBean.setTopic(TopicContent.APPEND_AREA);
        requestBean.setType(TypeContent.AREA_MSGS_ONEAREA);
        return requestToJson(requestBean);
    }

    public static String appendAreas(AreaItemBean areaItemBean) {
        AppendAreaBean appendAreaBean = new AppendAreaBean();
        appendAreaBean.setOp(OpContent.PUBLISH);
        appendAreaBean.setTopic(TopicContent.APPEND_AREA);
        appendAreaBean.setMsg(areaItemBean);
        return GsonUtils.toJson(appendAreaBean);
    }

    public static String stopAppendArea() {
        RequestBean requestBean = new RequestBean();
        requestBean.setOp(OpContent.UNADVERTISE);
        requestBean.setTopic(TopicContent.APPEND_AREA);
        return requestToJson(requestBean);
    }

    public static String initSetArea() {
        RequestBean requestBean = new RequestBean();
        requestBean.setOp(OpContent.ADVERTISE);
        requestBean.setTopic(TopicContent.SET_AREA);
        requestBean.setType(TypeContent.AREA_MSGS_AREAZONE);
        return requestToJson(requestBean);
    }

    public static String setAreas(List<AreaItemBean> list) {
        SetAreaBean setAreaBean = new SetAreaBean();
        setAreaBean.setOp(OpContent.PUBLISH);
        setAreaBean.setTopic(TopicContent.SET_AREA);
        AreaMsgBean areaMsgBean = new AreaMsgBean();
        areaMsgBean.setAreas(list);
        setAreaBean.setMsg(areaMsgBean);
        return GsonUtils.toJson(setAreaBean);
    }

    public static String stopSetArea() {
        RequestBean requestBean = new RequestBean();
        requestBean.setOp(OpContent.UNADVERTISE);
        requestBean.setTopic(TopicContent.SET_AREA);
        return requestToJson(requestBean);
    }

    public static String serviceAreaDeletePoi(String str) {
        RequestBean requestBean = new RequestBean();
        requestBean.setOp("call_service");
        requestBean.setService(ServiceContent.AREA_MANAGER_DELETE_POI);
        RequestBean.ArgsBean argsBean = new RequestBean.ArgsBean();
        argsBean.setPoi(str);
        requestBean.setArgs(argsBean);
        return requestToJson(requestBean);
    }

    public static String serviceGetAreas() {
        return serviceLayeredMapCmdBase(45, IDContent.SERVICE_GET_AREAS);
    }

    public static String serviceGetFloorData() {
        return serviceLayeredMapCmdBase(4, IDContent.SERVICE_GET_FLOOR_DATA);
    }

    public static String serviceMapRename(String str, String str2, String str3, String str4) {
        RequestBean requestBean = new RequestBean();
        requestBean.setId(IDContent.SERVICE_MAP_RENAME);
        requestBean.setOp("call_service");
        requestBean.setService(ServiceContent.LAYERED_MAP_CMD);
        RequestBean.ArgsBean argsBean = new RequestBean.ArgsBean();
        argsBean.setOp(70);
        argsBean.setTarget_floor(str);
        argsBean.setTarget_building(str2);
        argsBean.setFloor_name(str3);
        argsBean.setBuilding_name(str4);
        requestBean.setArgs(argsBean);
        return requestToJson(requestBean);
    }

    public static String setLiftControlConfigure(String str, String str2, String str3, String str4) {
        LiftControlConfigureReqBean liftControlConfigureReqBean = new LiftControlConfigureReqBean();
        liftControlConfigureReqBean.setId(IDContent.SERVICE_SET_LIFT_CONFIGURE);
        liftControlConfigureReqBean.setOp("call_service");
        liftControlConfigureReqBean.setService(ServiceContent.LIFT_CONTROL_CONFIGURE);
        LiftControlConfigureReqBean.ArgsBean argsBean = new LiftControlConfigureReqBean.ArgsBean();
        argsBean.setCmd(0);
        argsBean.setAPPID(str2);
        argsBean.setAPPSecret(str3);
        argsBean.setRobotID(str4);
        argsBean.setProjectID(str);
        liftControlConfigureReqBean.setArgs(argsBean);
        return GsonUtils.toJson(liftControlConfigureReqBean);
    }

    public static String getLiftControlConfigure() {
        LiftControlConfigureReqBean liftControlConfigureReqBean = new LiftControlConfigureReqBean();
        liftControlConfigureReqBean.setId(IDContent.SERVICE_GET_LIFT_CONFIGURE);
        liftControlConfigureReqBean.setOp("call_service");
        liftControlConfigureReqBean.setService(ServiceContent.LIFT_CONTROL_CONFIGURE);
        LiftControlConfigureReqBean.ArgsBean argsBean = new LiftControlConfigureReqBean.ArgsBean();
        argsBean.setCmd(1);
        liftControlConfigureReqBean.setArgs(argsBean);
        return GsonUtils.toJson(liftControlConfigureReqBean);
    }

    public static String deleteLiftControlConfigure() {
        LiftControlConfigureReqBean liftControlConfigureReqBean = new LiftControlConfigureReqBean();
        liftControlConfigureReqBean.setId(IDContent.SERVICE_DELETE_LIFT_CONFIGURE);
        liftControlConfigureReqBean.setOp("call_service");
        liftControlConfigureReqBean.setService(ServiceContent.LIFT_CONTROL_CONFIGURE);
        LiftControlConfigureReqBean.ArgsBean argsBean = new LiftControlConfigureReqBean.ArgsBean();
        argsBean.setCmd(4);
        liftControlConfigureReqBean.setArgs(argsBean);
        return GsonUtils.toJson(liftControlConfigureReqBean);
    }

    private static String toJson(Object obj, Type type) {
        String json = new Gson().toJson(obj, type);
        return json == null ? "" : json;
    }

    private static String requestToJson(Object obj) {
        try {
            return toJson(obj, RequestBean.class);
        } catch (Exception e) {
            MyLogUtils.Loge("MsgManager===>requestToJson throwable：", Log.getStackTraceString(e));
            return null;
        }
    }

    public static String initWayPointStateTopic() {
        RequestBean requestBean = new RequestBean();
        requestBean.setOp(OpContent.SUBSCRIBE);
        requestBean.setTopic(TopicContent.WAYPOINT_STATE);
        requestBean.setType(TypeContent.YU_TONG_ASSISTANCE_PATROL_FEED_BACK);
        requestBean.setId(IDContent.GET_PATROL_FEEDBACK);
        return requestToJson(requestBean);
    }

    public static String stopWayPointStateTopic() {
        RequestBean requestBean = new RequestBean();
        requestBean.setOp(OpContent.UNSUBSCRIBE);
        requestBean.setTopic(TopicContent.WAYPOINT_STATE);
        requestBean.setType(TypeContent.YU_TONG_ASSISTANCE_PATROL_FEED_BACK);
        requestBean.setId(IDContent.GET_PATROL_FEEDBACK);
        return requestToJson(requestBean);
    }

    public static String serviceTagPoi(String str) {
        RequestBean requestBean = new RequestBean();
        requestBean.setOp("call_service");
        requestBean.setService(ServiceContent.TAG_MANAGER_NAVI);
        requestBean.setId(IDContent.SERVICE_TAG_POI);
        RequestBean.ArgsBean argsBean = new RequestBean.ArgsBean();
        argsBean.setPoi(str);
        requestBean.setArgs(argsBean);
        return requestToJson(requestBean);
    }

    public static String serviceAreaManagerRevert() {
        return serviceAreaManager(1, "service_area_manager_revert");
    }

    public static String serviceAreaManagerDelete() {
        return serviceAreaManager(4, "service_area_manager_delete_all");
    }

    private static String serviceAreaManager(int i, String str) {
        RequestBean requestBean = new RequestBean();
        requestBean.setOp("call_service");
        requestBean.setService(ServiceContent.AREA_MANAGER_CONTROL);
        requestBean.setId(str);
        RequestBean.ArgsBean argsBean = new RequestBean.ArgsBean();
        argsBean.setOp(Integer.valueOf(i));
        requestBean.setArgs(argsBean);
        return requestToJson(requestBean);
    }

    public static String serviceVirtualWallManagerRevert() {
        RequestBean requestBean = new RequestBean();
        requestBean.setOp("call_service");
        requestBean.setService(ServiceContent.VIRTUAL_WALL_MANAGER_CONTROL);
        requestBean.setId(IDContent.SERVICE_VIRTUAL_WALL_MANAGER_REVERT);
        RequestBean.ArgsBean argsBean = new RequestBean.ArgsBean();
        argsBean.setOp(1);
        requestBean.setArgs(argsBean);
        return requestToJson(requestBean);
    }

    public static String initPoseTopic() {
        RequestBean requestBean = new RequestBean();
        requestBean.setOp(OpContent.ADVERTISE);
        requestBean.setId(IDContent.SET_POSE_ADVANCE);
        requestBean.setTopic(TopicContent.SET_INIT_POSE);
        requestBean.setType(TypeContent.GEOMETRY_MSGS_POSEWITHCOVARIANCESTAMPED);
        return requestToJson(requestBean);
    }

    public static String setInitPose(float f, float f2, float f3) {
        QuaternionBean eular2quaternion = ConvertorUtils.eular2quaternion(0.0f, 0.0f, f3);
        SetPosePublishBean setPosePublishBean = new SetPosePublishBean();
        setPosePublishBean.setOp(OpContent.PUBLISH);
        setPosePublishBean.setTopic(TopicContent.SET_INIT_POSE);
        setPosePublishBean.setId(IDContent.SET_POSE_ADVANCE);
        SetPosePublishBean.MsgBean msgBean = new SetPosePublishBean.MsgBean();
        SetPosePublishBean.MsgBean.HeaderBean headerBean = new SetPosePublishBean.MsgBean.HeaderBean();
        headerBean.setFrame_id(ScheduleFragment.MAP);
        SetPosePublishBean.MsgBean.PoseBeanX poseBeanX = new SetPosePublishBean.MsgBean.PoseBeanX();
        SetPosePublishBean.MsgBean.PoseBeanX.PoseBean poseBean = new SetPosePublishBean.MsgBean.PoseBeanX.PoseBean();
        SetPosePublishBean.MsgBean.PoseBeanX.PoseBean.OrientationBean orientationBean = new SetPosePublishBean.MsgBean.PoseBeanX.PoseBean.OrientationBean();
        orientationBean.setW(eular2quaternion.getW());
        orientationBean.setX(eular2quaternion.getX());
        orientationBean.setY(eular2quaternion.getY());
        orientationBean.setZ(eular2quaternion.getZ());
        SetPosePublishBean.MsgBean.PoseBeanX.PoseBean.PositionBean positionBean = new SetPosePublishBean.MsgBean.PoseBeanX.PoseBean.PositionBean();
        positionBean.setX(f);
        positionBean.setY(f2);
        poseBean.setOrientation(orientationBean);
        poseBean.setPosition(positionBean);
        poseBeanX.setPose(poseBean);
        msgBean.setHeader(headerBean);
        msgBean.setPose(poseBeanX);
        setPosePublishBean.setMsg(msgBean);
        return toJson(setPosePublishBean, SetPosePublishBean.class);
    }

    public static String stopInitPoseTopic() {
        RequestBean requestBean = new RequestBean();
        requestBean.setOp(OpContent.UNSUBSCRIBE);
        requestBean.setTopic(TopicContent.SET_INIT_POSE);
        requestBean.setId(IDContent.SET_POSE_ADVANCE);
        return requestToJson(requestBean);
    }

    public static String initDoorLengthTopic() {
        RequestBean requestBean = new RequestBean();
        requestBean.setOp(OpContent.ADVERTISE);
        requestBean.setTopic(TopicContent.DOOR_LENGTH);
        requestBean.setType(TypeContent.STD_MSGS_FLOAT32);
        return requestToJson(requestBean);
    }

    public static String stopDoorLengthTopic() {
        RequestBean requestBean = new RequestBean();
        requestBean.setOp(OpContent.UNADVERTISE);
        requestBean.setTopic(TopicContent.DOOR_LENGTH);
        return requestToJson(requestBean);
    }

    public static String serviceGetDoorLength() {
        RequestBean requestBean = new RequestBean();
        requestBean.setOp("call_service");
        requestBean.setService(ServiceContent.GET_DOOR_LENGTH);
        RequestBean.ArgsBean argsBean = new RequestBean.ArgsBean();
        argsBean.setCmd(0);
        argsBean.setStr("");
        requestBean.setArgs(argsBean);
        return requestToJson(requestBean);
    }

    public static String setDoorLength(float f) {
        DoorLengthBean doorLengthBean = new DoorLengthBean();
        DoorLengthBean.MsgBean msgBean = new DoorLengthBean.MsgBean();
        msgBean.setData(f);
        doorLengthBean.setTopic(TopicContent.DOOR_LENGTH);
        doorLengthBean.setOp(OpContent.PUBLISH);
        doorLengthBean.setId("set_door_length");
        doorLengthBean.setMsg(msgBean);
        return toJson(doorLengthBean, DoorLengthBean.class);
    }

    public static String initGateLengthTopic() {
        RequestBean requestBean = new RequestBean();
        requestBean.setOp(OpContent.ADVERTISE);
        requestBean.setTopic(TopicContent.GATE_LENGTH);
        requestBean.setType(TypeContent.STD_MSGS_FLOAT32);
        return requestToJson(requestBean);
    }

    public static String stopGateLengthTopic() {
        RequestBean requestBean = new RequestBean();
        requestBean.setOp(OpContent.UNADVERTISE);
        requestBean.setTopic(TopicContent.GATE_LENGTH);
        return requestToJson(requestBean);
    }

    public static String serviceGetGateLength() {
        RequestBean requestBean = new RequestBean();
        requestBean.setOp("call_service");
        requestBean.setService(ServiceContent.GET_GATE_LENGTH);
        RequestBean.ArgsBean argsBean = new RequestBean.ArgsBean();
        argsBean.setCmd(0);
        argsBean.setStr("");
        requestBean.setArgs(argsBean);
        return requestToJson(requestBean);
    }

    public static String setGateLength(float f) {
        GateLengthBean gateLengthBean = new GateLengthBean();
        GateLengthBean.MsgBean msgBean = new GateLengthBean.MsgBean();
        msgBean.setData(f);
        gateLengthBean.setTopic(TopicContent.GATE_LENGTH);
        gateLengthBean.setOp(OpContent.PUBLISH);
        gateLengthBean.setMsg(msgBean);
        return toJson(gateLengthBean, GateLengthBean.class);
    }

    public static String serviceUpCameraAutoCalibrate() {
        RequestBean requestBean = new RequestBean();
        requestBean.setOp("call_service");
        requestBean.setService(ServiceContent.UP_CAMERA_EXTRINSIC_AUTO_CALIBRATE);
        requestBean.setId(IDContent.SERVICE_UPCAMERA_AUTOCALIBRATE);
        RequestBean.ArgsBean argsBean = new RequestBean.ArgsBean();
        argsBean.setCmd(1);
        requestBean.setArgs(argsBean);
        return requestToJson(requestBean);
    }

    public static String serviceDownCameraAutoCalibrate() {
        RequestBean requestBean = new RequestBean();
        requestBean.setOp("call_service");
        requestBean.setService(ServiceContent.DOWN_CAMERA_EXTRINSIC_AUTO_CALIBRATE);
        requestBean.setId(IDContent.SERVICE_DOWNCAMERA_AUTOCALIBRATE);
        RequestBean.ArgsBean argsBean = new RequestBean.ArgsBean();
        argsBean.setCmd(1);
        requestBean.setArgs(argsBean);
        return requestToJson(requestBean);
    }

    public static String serviceMinNumCommand(int i) {
        RequestBean requestBean = new RequestBean();
        requestBean.setOp("call_service");
        requestBean.setService(ServiceContent.MatchThreshold.MINNUM_COMMAND);
        requestBean.setId(IDContent.MatchThreshold.SERVICE_MINNUM_COMMAND);
        RequestBean.ArgsBean argsBean = new RequestBean.ArgsBean();
        argsBean.setCmd(Integer.valueOf(i));
        requestBean.setArgs(argsBean);
        return requestToJson(requestBean);
    }

    public static String serviceMinNumStatus() {
        RequestBean requestBean = new RequestBean();
        requestBean.setOp("call_service");
        requestBean.setService(ServiceContent.MatchThreshold.MINNUM_STATUS);
        requestBean.setId(IDContent.MatchThreshold.SERVICE_MINNUM_STATUS);
        RequestBean.ArgsBean argsBean = new RequestBean.ArgsBean();
        argsBean.setCmd(0);
        requestBean.setArgs(argsBean);
        return requestToJson(requestBean);
    }

    public static String serviceMinNumFindResult() {
        RequestBean requestBean = new RequestBean();
        requestBean.setOp("call_service");
        requestBean.setService(ServiceContent.MatchThreshold.MINNUM_FINDRESULT);
        requestBean.setId(IDContent.MatchThreshold.SERVICE_MINNUM_FINDRESULT);
        requestBean.setArgs(new RequestBean.ArgsBean());
        return requestToJson(requestBean);
    }

    public static String serviceGetUltrasonicDistance() {
        UltrasonicDistanceReqBean ultrasonicDistanceReqBean = new UltrasonicDistanceReqBean();
        ultrasonicDistanceReqBean.setOp("call_service");
        ultrasonicDistanceReqBean.setService(ServiceContent.ULTRASONIC_DISTANCE);
        ultrasonicDistanceReqBean.setId(IDContent.UltrasonicDistance.SERVICE_ULTRASONIC_DISTANCE_GET);
        UltrasonicDistanceReqBean.ArgsDTO argsDTO = new UltrasonicDistanceReqBean.ArgsDTO();
        argsDTO.setCmd(2);
        UltrasonicDistanceBean ultrasonicDistanceBean = new UltrasonicDistanceBean();
        ultrasonicDistanceBean.setName(SensorSettingContent.ULTRASONIC_DISTANCE);
        ArrayList arrayList = new ArrayList();
        arrayList.add(ultrasonicDistanceBean);
        argsDTO.setFeatures(arrayList);
        ultrasonicDistanceReqBean.setArgs(argsDTO);
        return GsonUtils.toJson(ultrasonicDistanceReqBean);
    }

    public static String serviceSetUltrasonicDistance(int i) {
        UltrasonicDistanceReqBean ultrasonicDistanceReqBean = new UltrasonicDistanceReqBean();
        ultrasonicDistanceReqBean.setOp("call_service");
        ultrasonicDistanceReqBean.setService(ServiceContent.ULTRASONIC_DISTANCE);
        ultrasonicDistanceReqBean.setId(IDContent.UltrasonicDistance.SERVICE_ULTRASONIC_DISTANCE_SET);
        UltrasonicDistanceReqBean.ArgsDTO argsDTO = new UltrasonicDistanceReqBean.ArgsDTO();
        argsDTO.setCmd(1);
        UltrasonicDistanceBean ultrasonicDistanceBean = new UltrasonicDistanceBean();
        ultrasonicDistanceBean.setValue(i);
        ultrasonicDistanceBean.setName(SensorSettingContent.ULTRASONIC_DISTANCE);
        ArrayList arrayList = new ArrayList();
        arrayList.add(ultrasonicDistanceBean);
        argsDTO.setFeatures(arrayList);
        ultrasonicDistanceReqBean.setArgs(argsDTO);
        return GsonUtils.toJson(ultrasonicDistanceReqBean);
    }

    public static String initUpCamDataMsg() {
        RequestBean requestBean = new RequestBean();
        requestBean.setOp(OpContent.SUBSCRIBE);
        requestBean.setTopic(TopicContent.UPCAM_DATA);
        requestBean.setType(TypeContent.YUTONG_ASSISTANCE_POINT_ARRAY);
        requestBean.setId("initUpCamDataMsg");
        return requestToJson(requestBean);
    }

    public static String stopUpCamDataMsg() {
        RequestBean requestBean = new RequestBean();
        requestBean.setType(TypeContent.YUTONG_ASSISTANCE_POINT_ARRAY);
        requestBean.setOp(OpContent.UNSUBSCRIBE);
        requestBean.setTopic(TopicContent.UPCAM_DATA);
        return requestToJson(requestBean);
    }

    public static String getStartCurrentRecordPathParams() {
        RequestBean requestBean = new RequestBean();
        requestBean.setOp(OpContent.SUBSCRIBE);
        requestBean.setTopic(TopicContent.CURRENT_RECORD_PATH);
        requestBean.setType(TypeContent.FOLLOW_PATH_MSGS_PATHNODE);
        requestBean.setId("get_record_path");
        return requestToJson(requestBean);
    }

    public static String getStopCurrentRecordPathParams() {
        RequestBean requestBean = new RequestBean();
        requestBean.setOp(OpContent.UNSUBSCRIBE);
        requestBean.setTopic(TopicContent.CURRENT_RECORD_PATH);
        requestBean.setType(TypeContent.FOLLOW_PATH_MSGS_PATHNODE);
        requestBean.setId("get_record_path");
        return requestToJson(requestBean);
    }

    public static String getStartPathMarkerSyncParams() {
        RequestBean requestBean = new RequestBean();
        requestBean.setOp(OpContent.ADVERTISE);
        requestBean.setTopic(TopicContent.PATH_MANAGER_PATH_MARKER_SYNC);
        requestBean.setType(TypeContent.STD_MSGS_EMPTY);
        requestBean.setId(IDContent.TrajectoryDrawing.PATH_MARKER_SYNC);
        return requestToJson(requestBean);
    }

    public static String getSendPathMarkerSyncParams() {
        RequestBean requestBean = new RequestBean();
        requestBean.setOp(OpContent.PUBLISH);
        requestBean.setTopic(TopicContent.PATH_MANAGER_PATH_MARKER_SYNC);
        requestBean.setId(IDContent.TrajectoryDrawing.PATH_MARKER_SYNC);
        return requestToJson(requestBean);
    }

    public static String getStopPathMarkerSyncParams() {
        RequestBean requestBean = new RequestBean();
        requestBean.setOp(OpContent.UNADVERTISE);
        requestBean.setTopic(TopicContent.PATH_MANAGER_PATH_MARKER_SYNC);
        requestBean.setId(IDContent.TrajectoryDrawing.PATH_MARKER_SYNC);
        return requestToJson(requestBean);
    }

    public static String getStartPathFollowerCancelParams() {
        RequestBean requestBean = new RequestBean();
        requestBean.setOp(OpContent.ADVERTISE);
        requestBean.setTopic(TopicContent.PATH_FOLLOWER_CANCEL);
        requestBean.setType("actionlib_msgs/GoalID");
        requestBean.setId(IDContent.TrajectoryDrawing.CANCEL_PATH_FOLLOW);
        return requestToJson(requestBean);
    }

    public static String getSendPathFollowerCancelParams() {
        RequestBean requestBean = new RequestBean();
        requestBean.setOp(OpContent.PUBLISH);
        requestBean.setTopic(TopicContent.PATH_FOLLOWER_CANCEL);
        requestBean.setId(IDContent.TrajectoryDrawing.CANCEL_PATH_FOLLOW);
        return requestToJson(requestBean);
    }

    public static String getStopPathFollowerCancelParams() {
        RequestBean requestBean = new RequestBean();
        requestBean.setOp(OpContent.UNADVERTISE);
        requestBean.setTopic(TopicContent.PATH_FOLLOWER_CANCEL);
        requestBean.setId(IDContent.TrajectoryDrawing.CANCEL_PATH_FOLLOW);
        return requestToJson(requestBean);
    }

    public static String initDownCamDataMsg() {
        RequestBean requestBean = new RequestBean();
        requestBean.setOp(OpContent.SUBSCRIBE);
        requestBean.setTopic(TopicContent.DOWNCAM_DATA);
        requestBean.setType(TypeContent.YUTONG_ASSISTANCE_POINT_ARRAY);
        requestBean.setId("initDownCamDataMsg");
        return requestToJson(requestBean);
    }

    public static String stopDownCamDataMsg() {
        RequestBean requestBean = new RequestBean();
        requestBean.setType(TypeContent.YUTONG_ASSISTANCE_POINT_ARRAY);
        requestBean.setOp(OpContent.UNSUBSCRIBE);
        requestBean.setTopic(TopicContent.DOWNCAM_DATA);
        return requestToJson(requestBean);
    }

    public static String configNaviSetting(List<NaviSettingBean> list) {
        NaviSettingReqBean naviSettingReqBean = new NaviSettingReqBean();
        naviSettingReqBean.setOp("call_service");
        naviSettingReqBean.setService(ServiceContent.NAVI_SETTING);
        naviSettingReqBean.setId(IDContent.NaviSetting.CONFIG_NAVI_SETTING);
        NaviSettingReqBean.ArgsDTO argsDTO = new NaviSettingReqBean.ArgsDTO();
        argsDTO.setCmd(1);
        argsDTO.setFeatures(list);
        naviSettingReqBean.setArgs(argsDTO);
        return GsonUtils.toJson(naviSettingReqBean);
    }

    public static String getNaviSetting(List<NaviSettingBean> list) {
        NaviSettingReqBean naviSettingReqBean = new NaviSettingReqBean();
        naviSettingReqBean.setOp("call_service");
        naviSettingReqBean.setService(ServiceContent.NAVI_SETTING);
        naviSettingReqBean.setId(IDContent.NaviSetting.GET_NAVI_SETTING);
        NaviSettingReqBean.ArgsDTO argsDTO = new NaviSettingReqBean.ArgsDTO();
        argsDTO.setCmd(2);
        argsDTO.setFeatures(list);
        naviSettingReqBean.setArgs(argsDTO);
        return GsonUtils.toJson(naviSettingReqBean);
    }

    public static String configStationServer(int i, String str, Boolean bool) {
        RequestBean requestBean = new RequestBean();
        requestBean.setId(IDContent.CONFIG_STATION_SERVER);
        requestBean.setOp("call_service");
        requestBean.setService(ServiceContent.CONFIG_STATION_SERVER);
        RequestBean.ArgsBean argsBean = new RequestBean.ArgsBean();
        argsBean.setCmd(Integer.valueOf(i));
        if (i == 1) {
            argsBean.setHost(str);
        } else if (i == 2) {
            argsBean.setSwitch_on(bool);
        } else if (i == 3) {
            argsBean.setWan_switch(bool);
        } else if (i == 4) {
            argsBean.setProject_name(str);
        }
        requestBean.setArgs(argsBean);
        return requestToJson(requestBean);
    }
}
