package mc.csst.com.selfchassis.ui.dialog;

import android.content.Context;
import android.text.TextUtils;
import androidx.appcompat.app.AppCompatActivity;
import com.ciot.base.util.ContextUtil;
import com.ciot.base.util.MyLogUtils;
import com.ciot.sentrymove.R;
import java.util.List;
import mc.csst.com.selfchassis.ui.dialog.AddMarkDialog;
import mc.csst.com.selfchassis.ui.dialog.BuildMapDialog;
import mc.csst.com.selfchassis.utils.MyToastUtils;
import mc.csst.com.selfchassis.utils.SoftTypeInfoManager;
import mc.csst.com.selfchassis.utils.bean.ShowSelfChassisBean;
import mc.csst.com.selfchassislibrary.bean.EularBean;
import mc.csst.com.selfchassislibrary.bean.msg.LayeredMapCmdResponseBean;
import mc.csst.com.selfchassislibrary.bean.msg.MarkerOperationGetMarkersResponseBean;
import mc.csst.com.selfchassislibrary.chassis.SelfChassis;
import mc.csst.com.selfchassislibrary.content.IDContent;
import mc.csst.com.selfchassislibrary.utils.ConvertorUtils;
import mc.csst.com.selfchassislibrary.utils.SelfChassisListenerUtils;
import mc.csst.com.selfchassislibrary.utils.eventbus.SelfChassisEventMsg;

public class DialogHelper {
    private static final String TAG = DialogHelper.class.getSimpleName();

    public static void showBuildMapDialog(AppCompatActivity appCompatActivity, final List<LayeredMapCmdResponseBean.ValuesBean.ListInfoBean> list) {
        BuildMapDialog newInstance = BuildMapDialog.newInstance();
        newInstance.setTitle(appCompatActivity.getString(R.string.build_map_title));
        newInstance.setOnDialogButtonOnClickListener(new BuildMapDialog.OnDialogButtonOnClickListener() {
            public void cancelBtn() {
            }

            public boolean okBtn(int i, String str, String str2) {
                try {
                    Integer.valueOf(str);
                    if (DialogHelper.checkMapExsit(str, str2, list)) {
                        return false;
                    }
                    SoftTypeInfoManager.getInstance().setSoftType(2);
                    SelfChassis.getInstance().serviceNodeManagerControl(i, str, str2);
                    return true;
                } catch (Exception unused) {
                    MyToastUtils.showShort(ContextUtil.getContext(), ContextUtil.getContext().getString(R.string.build_map_dialog_data_forat_error));
                    return false;
                }
            }
        });
        newInstance.showAllowingStateLoss(appCompatActivity.getSupportFragmentManager());
    }

    /* access modifiers changed from: private */
    public static boolean checkMapExsit(String str, String str2, List<LayeredMapCmdResponseBean.ValuesBean.ListInfoBean> list) {
        if (list != null) {
            for (LayeredMapCmdResponseBean.ValuesBean.ListInfoBean next : list) {
                String building_name = next.getBuilding_name();
                List<LayeredMapCmdResponseBean.ValuesBean.ListInfoBean.FloorInfoBean> floor_info = next.getFloor_info();
                if (floor_info != null) {
                    for (LayeredMapCmdResponseBean.ValuesBean.ListInfoBean.FloorInfoBean floor_name : floor_info) {
                        String floor_name2 = floor_name.getFloor_name();
                        if (TextUtils.equals(str2, building_name) && TextUtils.equals(str, floor_name2)) {
                            MyToastUtils.showShort(ContextUtil.getContext(), String.format(ContextUtil.getContext().getString(R.string.tip_build_map_error), new Object[]{str2, str}));
                            return true;
                        }
                    }
                    continue;
                }
            }
        }
        return false;
    }

    public static void showEditMapDialog(AppCompatActivity appCompatActivity, int i, final String str, final String str2, final List<LayeredMapCmdResponseBean.ValuesBean.ListInfoBean> list) {
        BuildMapDialog newInstance = BuildMapDialog.newInstance(i, str, str2);
        newInstance.setTitle(appCompatActivity.getString(R.string.edit_map_title));
        newInstance.setOnDialogButtonOnClickListener(new BuildMapDialog.OnDialogButtonOnClickListener() {
            public void cancelBtn() {
            }

            public boolean okBtn(int i, String str, String str2) {
                if (TextUtils.equals(str, str) && TextUtils.equals(str2, str2)) {
                    return true;
                }
                if (DialogHelper.checkMapExsit(str, str2, list)) {
                    MyToastUtils.showShort(ContextUtil.getContext(), String.format(ContextUtil.getContext().getString(R.string.tip_build_map_error), new Object[]{str2, str}));
                    return false;
                }
                SelfChassis.getInstance().sendMapRename(str, str2, str, str2);
                SelfChassisListenerUtils.getInstance().setMapRenameListener($$Lambda$DialogHelper$2$7FHCP9xk852ZJljsMXgoFmxUFLc.INSTANCE);
                return true;
            }

            static /* synthetic */ void lambda$okBtn$0(SelfChassisEventMsg selfChassisEventMsg) {
                if (selfChassisEventMsg != null) {
                    LayeredMapCmdResponseBean layeredMapCmdResponseBean = (LayeredMapCmdResponseBean) selfChassisEventMsg.getData();
                    if (TextUtils.equals(layeredMapCmdResponseBean.getId(), IDContent.SERVICE_MAP_RENAME) && layeredMapCmdResponseBean.isResult()) {
                        MyToastUtils.showShort(ContextUtil.getContext(), ContextUtil.getContext().getString(R.string.tip_success));
                        SelfChassis.getInstance().serviceLayeredMapCmd();
                    }
                }
            }
        });
        newInstance.showAllowingStateLoss(appCompatActivity.getSupportFragmentManager());
    }

    public static AddMarkDialog showAddMarkDialog(AppCompatActivity appCompatActivity, final List<MarkerOperationGetMarkersResponseBean.ValuesBean.MarkersBean.WaypointsBean> list, AddMarkDialog.OnInsertMarkerListener onInsertMarkerListener) {
        AddMarkDialog newInstance = AddMarkDialog.newInstance(ShowSelfChassisBean.getInstance().getX(), ShowSelfChassisBean.getInstance().getY(), ShowSelfChassisBean.getInstance().getT());
        String str = TAG;
        MyLogUtils.Logd(str, "showAddMarkDialog" + list);
        newInstance.setOnInsertMarkerListener(onInsertMarkerListener);
        newInstance.setOnDialogButtonOnClickListener(new AddMarkDialog.OnDialogButtonOnClickListener() {
            public void cancelBtn() {
            }

            public boolean okBtn(int i, String str, float f, float f2, float f3) {
                return !DialogHelper.checkMarkerExsit(str, list);
            }
        });
        newInstance.showAllowingStateLoss(appCompatActivity.getSupportFragmentManager());
        return newInstance;
    }

    public static boolean checkMarkerExsit(String str, List<MarkerOperationGetMarkersResponseBean.ValuesBean.MarkersBean.WaypointsBean> list) {
        if (list != null) {
            for (MarkerOperationGetMarkersResponseBean.ValuesBean.MarkersBean.WaypointsBean name : list) {
                if (TextUtils.equals(name.getName(), str)) {
                    MyToastUtils.showShort(ContextUtil.getContext(), String.format(ContextUtil.getContext().getString(R.string.tip_exsit_marker), new Object[]{str}));
                    return true;
                }
            }
        }
        return false;
    }

    public static AddMarkDialog showEditMarkDialog(AppCompatActivity appCompatActivity, MarkerOperationGetMarkersResponseBean.ValuesBean.MarkersBean.WaypointsBean waypointsBean, final List<MarkerOperationGetMarkersResponseBean.ValuesBean.MarkersBean.WaypointsBean> list) {
        String str;
        String str2;
        String str3;
        MarkerOperationGetMarkersResponseBean.ValuesBean.MarkersBean.WaypointsBean.PoseBean pose = waypointsBean.getPose();
        StringBuilder sb = new StringBuilder();
        sb.append(waypointsBean.getBehavior_code());
        String str4 = "";
        sb.append(str4);
        String sb2 = sb.toString();
        final String name = waypointsBean.getName();
        if (pose != null) {
            try {
                str = String.valueOf(pose.getPosition().getX());
                try {
                    str2 = String.valueOf(pose.getPosition().getY());
                    try {
                        str3 = String.valueOf(getEularBeanByWaypoint(waypointsBean).getY());
                        str4 = str;
                    } catch (Exception e) {
                        e = e;
                        MyLogUtils.Loge(TAG, e.getMessage());
                        String str5 = TAG;
                        MyLogUtils.Logd(str5, "showEditMarkDialog" + list);
                        AddMarkDialog newInstance = AddMarkDialog.newInstance(str, str2, str4, sb2, name);
                        newInstance.setOnDialogButtonOnClickListener(new AddMarkDialog.OnDialogButtonOnClickListener() {
                            public void cancelBtn() {
                            }

                            public boolean okBtn(int i, String str, float f, float f2, float f3) {
                                return TextUtils.equals(name, str) || !DialogHelper.checkMarkerExsit(str, list);
                            }
                        });
                        newInstance.showAllowingStateLoss(appCompatActivity.getSupportFragmentManager());
                        return newInstance;
                    }
                } catch (Exception e2) {
                    e = e2;
                    str2 = str4;
                    MyLogUtils.Loge(TAG, e.getMessage());
                    String str52 = TAG;
                    MyLogUtils.Logd(str52, "showEditMarkDialog" + list);
                    AddMarkDialog newInstance2 = AddMarkDialog.newInstance(str, str2, str4, sb2, name);
                    newInstance2.setOnDialogButtonOnClickListener(new AddMarkDialog.OnDialogButtonOnClickListener() {
                        public void cancelBtn() {
                        }

                        public boolean okBtn(int i, String str, float f, float f2, float f3) {
                            return TextUtils.equals(name, str) || !DialogHelper.checkMarkerExsit(str, list);
                        }
                    });
                    newInstance2.showAllowingStateLoss(appCompatActivity.getSupportFragmentManager());
                    return newInstance2;
                }
            } catch (Exception e3) {
                e = e3;
                str2 = str4;
                str = str2;
                MyLogUtils.Loge(TAG, e.getMessage());
                String str522 = TAG;
                MyLogUtils.Logd(str522, "showEditMarkDialog" + list);
                AddMarkDialog newInstance22 = AddMarkDialog.newInstance(str, str2, str4, sb2, name);
                newInstance22.setOnDialogButtonOnClickListener(new AddMarkDialog.OnDialogButtonOnClickListener() {
                    public void cancelBtn() {
                    }

                    public boolean okBtn(int i, String str, float f, float f2, float f3) {
                        return TextUtils.equals(name, str) || !DialogHelper.checkMarkerExsit(str, list);
                    }
                });
                newInstance22.showAllowingStateLoss(appCompatActivity.getSupportFragmentManager());
                return newInstance22;
            }
        } else {
            str3 = str4;
            str2 = str3;
        }
        str = str4;
        str4 = str3;
        String str5222 = TAG;
        MyLogUtils.Logd(str5222, "showEditMarkDialog" + list);
        AddMarkDialog newInstance222 = AddMarkDialog.newInstance(str, str2, str4, sb2, name);
        newInstance222.setOnDialogButtonOnClickListener(new AddMarkDialog.OnDialogButtonOnClickListener() {
            public void cancelBtn() {
            }

            public boolean okBtn(int i, String str, float f, float f2, float f3) {
                return TextUtils.equals(name, str) || !DialogHelper.checkMarkerExsit(str, list);
            }
        });
        newInstance222.showAllowingStateLoss(appCompatActivity.getSupportFragmentManager());
        return newInstance222;
    }

    public static NormalDialog showCalibrationMode(AppCompatActivity appCompatActivity) {
        Context baseContext = appCompatActivity.getBaseContext();
        NormalDialog createDialog = NormalDialog.createDialog(appCompatActivity);
        createDialog.setTitle((CharSequence) null);
        createDialog.setMessage(baseContext.getString(R.string.selected_calibration_mode));
        createDialog.setBtCancel(baseContext.getString(R.string.location_calibration));
        createDialog.setBtOk(baseContext.getString(R.string.manual_calibration));
        createDialog.setShowTopExit(true);
        createDialog.setMessageTxtColor(R.color.black);
        createDialog.setBtnCancleStyle();
        createDialog.show();
        return createDialog;
    }

    public static EularBean getEularBeanByWaypoint(MarkerOperationGetMarkersResponseBean.ValuesBean.MarkersBean.WaypointsBean waypointsBean) {
        return ConvertorUtils.quaternion2eular(waypointsBean.getPose().getOrientation().getW(), waypointsBean.getPose().getOrientation().getX(), waypointsBean.getPose().getOrientation().getY(), waypointsBean.getPose().getOrientation().getZ());
    }
}
