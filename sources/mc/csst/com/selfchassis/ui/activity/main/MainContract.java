package mc.csst.com.selfchassis.ui.activity.main;

import android.app.Activity;
import android.graphics.Bitmap;
import androidx.fragment.app.FragmentManager;
import java.util.List;
import mc.csst.com.selfchassis.ui.activity.base.BaseView;
import mc.csst.com.selfchassis.ui.dialog.AddMarkDialog;
import mc.csst.com.selfchassis.ui.dialog.ConfirmDialog;
import mc.csst.com.selfchassis.ui.dialog.ContinueScanConfirmDialog;
import mc.csst.com.selfchassis.utils.bean.LeftToolbarBean;
import mc.csst.com.selfchassis.utils.bean.ShowSelfChassisBean;
import mc.csst.com.selfchassis.utils.bean.ToolbarBean;
import mc.csst.com.selfchassis.utils.bean.TopMenuBean;
import mc.csst.com.selfchassislibrary.bean.MapInfoBean;
import mc.csst.com.selfchassislibrary.bean.msg.ApriltagsBufferBean;
import mc.csst.com.selfchassislibrary.bean.msg.AreaItemBean;
import mc.csst.com.selfchassislibrary.bean.msg.CrossFloorNaviReqBean;
import mc.csst.com.selfchassislibrary.bean.msg.GlobalLocateReqBean;
import mc.csst.com.selfchassislibrary.bean.msg.LayeredMapCmdResponseBean;
import mc.csst.com.selfchassislibrary.bean.msg.MarkerOperationGetMarkersResponseBean;
import mc.csst.com.selfchassislibrary.chassis.SelfChassisState;
import mc.csst.com.selfchassislibrary.utils.eventbus.SelfChassisEventMsg;

public interface MainContract {

    public interface Model {
    }

    public interface Presenter {
        void addLineTrackingPoint(AddMarkDialog.OnInsertMarkerListener onInsertMarkerListener);

        void addPoint();

        void appendArea(FragmentManager fragmentManager, List<AreaItemBean> list, AreaItemBean areaItemBean, String str);

        boolean appendArea(AreaItemBean areaItemBean);

        void bottomLock();

        void buildManage();

        void cancelTimer();

        void changeBuilding();

        void changeSoftType(int i);

        void changeSoftTypeNav();

        ShowSelfChassisBean chassisState(SelfChassisState selfChassisState);

        void contend(boolean z);

        void continueToScanMap();

        void controlDirectionStart(float f, float f2);

        void controlDirectionStop();

        void createMap();

        void crossFloorNavToPoint(CrossFloorNaviReqBean.MsgBean msgBean, FragmentManager fragmentManager);

        void dealNotification(SelfChassisEventMsg selfChassisEventMsg);

        void dealOnResume();

        void deleteArea(List<AreaItemBean> list, String str);

        void deleteMap(String str, String str2);

        void deleteMapInfoOnScanMap();

        void deletePoint(String str);

        void deleteVisualLabelByName(String str);

        void editMapSave();

        void exitEditMap();

        void exitMode();

        void forcedCancelFloorNav();

        boolean havPointInDangerousArea(List<AreaItemBean> list, MarkerOperationGetMarkersResponseBean.ValuesBean.MarkersBean.WaypointsBean waypointsBean);

        List<LeftToolbarBean> initEditLeftToolbar();

        List<ToolbarBean> initRightToolbar(boolean z);

        List<TopMenuBean> initTopMenu();

        void localPositioning();

        ShowSelfChassisBean localizationConfidence(float f);

        void mapLogic(MapInfoBean mapInfoBean, Bitmap bitmap);

        void navigationToPoint(MarkerOperationGetMarkersResponseBean.ValuesBean.MarkersBean.WaypointsBean waypointsBean);

        void placeCalibration(String str);

        void pointManage();

        void resetNotificationState();

        void rightBtnSave();

        void rightTopBtnCancelNav();

        void rightTopBtnLock();

        void saveVisualLabelCalibrationMode();

        void selectBigBrush();

        void selectBuild(LayeredMapCmdResponseBean.ValuesBean.ListInfoBean listInfoBean);

        void selectFloor(String str, String str2);

        void selectLine();

        void selectMediumBrush();

        void selectRectangle();

        void selectSmallBrush();

        void sendGetFloorData();

        void setRightTopBtnCancelNav(boolean z);

        void setVisualLabelListListener();

        void showAddRandomPoint();

        void showCalibration();

        void showCollectMode();

        void showCoverage();

        void showEditMap();

        void showOtherFloorPoint();

        void showPositioning(Activity activity);

        void showSetting();

        void showTopMenu();

        void showTrajectoryDrawing();

        void showVisualLabelCalibrationMode();

        void softStop();

        void startPoiPatrol(int i, int i2, List<MarkerOperationGetMarkersResponseBean.ValuesBean.MarkersBean.WaypointsBean> list);

        void stopPoiPatrol();

        void zoomIn();

        void zoomOut();
    }

    public interface View extends BaseView {
        void changeBottomLock(boolean z);

        void changeNavType(int i);

        void changePatrollingView(boolean z);

        void changeRightTopBtnLock(boolean z);

        void clearUnfinishedArea();

        void closeConfirmDialog();

        void closeWaitDialog();

        List<GlobalLocateReqBean.ArgsBean.SearchBoundaryBean.PolygonBean.PointsBean> getPositioningRectPoints();

        void hiddenTips();

        boolean isShowBrushBtn();

        boolean isShowBuildPointManageView();

        boolean isShowChangeBuildingView();

        boolean isShowCoverageView();

        boolean isShowPointManageView();

        boolean isShowShapeBtn();

        boolean isShowTopMenuView();

        void lockMap(boolean z);

        void refreshAreaView(List<AreaItemBean> list);

        void refreshCurrentVisualLabelData(List<String> list);

        void refreshMap(MapInfoBean mapInfoBean, Bitmap bitmap);

        void refreshPatrolPointAdapter(List<MarkerOperationGetMarkersResponseBean.ValuesBean.MarkersBean.WaypointsBean> list);

        void refreshPointAdapter();

        void refreshRightToolBarAdapter(boolean z);

        void refreshVisualLabelData(List<ApriltagsBufferBean.MsgBean.WaypointsBean> list);

        void removeItemOnVisualLabelAdapter(String str);

        void resetData();

        void selectBrushBtn(int i);

        void selectLeftToolBtn(String str);

        void selectPatrolRoute(int i, int i2, boolean z);

        void selectRightToolBtn(int i);

        void selectShapeBtn(int i);

        void showAddMarkDialog();

        void showAddRandomPointView(boolean z);

        void showBuildManageView(boolean z);

        void showBuildMapDialog();

        void showCameraPointCloudView();

        void showChangeBuildingView(boolean z);

        void showChooseMarkerDialog();

        void showCollectModeView(boolean z);

        void showConfirmDialog(String str, String str2, String str3, String str4, ConfirmDialog.OnDialogButtonClickListener onDialogButtonClickListener, ConfirmDialog.OnDialogButtonClickListener onDialogButtonClickListener2);

        void showConfirmDialog(String str, String str2, ConfirmDialog.OnDialogButtonClickListener onDialogButtonClickListener);

        void showConfirmDialog(String str, String str2, ConfirmDialog.OnDialogButtonClickListener onDialogButtonClickListener, ConfirmDialog.OnDialogButtonClickListener onDialogButtonClickListener2);

        void showConnectedDialog();

        void showContinueScanConfirmDialog(ContinueScanConfirmDialog.OnDialogButtonClickListener onDialogButtonClickListener);

        void showCoverageView(boolean z);

        void showEditMapDialog();

        void showFloorRv(LayeredMapCmdResponseBean.ValuesBean.ListInfoBean listInfoBean);

        void showLineTrackingAddMarkDialog(AddMarkDialog.OnInsertMarkerListener onInsertMarkerListener);

        void showPointManageView(boolean z);

        void showRightBtnSave(boolean z);

        void showRocker(boolean z);

        void showSetActivity();

        void showSoftTypeView(int i);

        void showSwitchPointDialog();

        void showTipsBlack(String str);

        void showTipsGreen(String str);

        void showTopMenuView(boolean z);

        void showTrajectoryDrawingView(boolean z);

        void showVisualLabelView();

        void showWaitDialog(String str);

        void startPatrol();

        void updateMultipointNavData(int i, int i2);
    }
}
