package mc.csst.com.selfchassis.ui.dialog;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.blankj.utilcode.util.AdaptScreenUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ciot.base.util.GsonUtils;
import com.ciot.base.util.MyLogUtils;
import com.ciot.sentrymove.R;
import java.util.ArrayList;
import java.util.List;
import mc.csst.com.selfchassis.databinding.DialogSwitchingPointBinding;
import mc.csst.com.selfchassis.ui.adapter.AllBuildAdapter;
import mc.csst.com.selfchassis.ui.adapter.CurMarkerAdapter;
import mc.csst.com.selfchassislibrary.bean.msg.CrossFloorNaviReqBean;
import mc.csst.com.selfchassislibrary.bean.msg.MarkerBean;
import mc.csst.com.selfchassislibrary.bean.msg.MarkersDetailsBean;
import mc.csst.com.selfchassislibrary.chassis.SelfChassis;
import mc.csst.com.selfchassislibrary.content.ServiceContent;
import mc.csst.com.selfchassislibrary.utils.SelfChassisListenerUtils;
import mc.csst.com.selfchassislibrary.utils.eventbus.SelfChassisEventMsg;

public class SwitchPointDialog extends BaseDialog implements View.OnClickListener, BaseQuickAdapter.OnItemClickListener, SelfChassisListenerUtils.OnBaseSelfChassisListener, SwipeRefreshLayout.OnRefreshListener, CurMarkerAdapter.OnNavClickListener {
    private AllBuildAdapter mAllBuildAdapter;
    private DialogSwitchingPointBinding mBinding;
    private CurMarkerAdapter mCurMarkerAdapter = null;
    private List<MarkersDetailsBean.ValuesBean.FloorsBean> mFloorList = null;
    private String mFloorName;
    private List<MarkerBean> mMarkerList = null;
    private OnSwitchPointCancelListener mSwitchPointCancelListener;
    private OnSwitchPointSelectListener mSwitchPointSelectListener;

    public interface OnSwitchPointCancelListener {
        void onCancel();
    }

    public interface OnSwitchPointSelectListener {
        void onSelect(CrossFloorNaviReqBean.MsgBean msgBean);
    }

    public void convertView(View view, BaseDialog baseDialog) {
    }

    public int setUpLayoutId() {
        return 0;
    }

    public /* bridge */ /* synthetic */ boolean checkNullAndShowToast(String str, String str2) {
        return super.checkNullAndShowToast(str, str2);
    }

    public /* bridge */ /* synthetic */ void fullScreenImmersive() {
        super.fullScreenImmersive();
    }

    public /* bridge */ /* synthetic */ boolean isShow() {
        return super.isShow();
    }

    public /* bridge */ /* synthetic */ void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public /* bridge */ /* synthetic */ void onStart() {
        super.onStart();
    }

    public /* bridge */ /* synthetic */ void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
    }

    public /* bridge */ /* synthetic */ void removeDecorViewBg() {
        super.removeDecorViewBg();
    }

    public /* bridge */ /* synthetic */ BaseDialog setAnimStyle(int i) {
        return super.setAnimStyle(i);
    }

    public /* bridge */ /* synthetic */ BaseDialog setDimAmout(float f) {
        return super.setDimAmout(f);
    }

    public /* bridge */ /* synthetic */ BaseDialog setMargin(int i) {
        return super.setMargin(i);
    }

    public /* bridge */ /* synthetic */ BaseDialog setOutCancel(boolean z) {
        return super.setOutCancel(z);
    }

    public /* bridge */ /* synthetic */ BaseDialog setShowBottom(boolean z) {
        return super.setShowBottom(z);
    }

    public /* bridge */ /* synthetic */ BaseDialog setSize(int i, int i2) {
        return super.setSize(i, i2);
    }

    public /* bridge */ /* synthetic */ BaseDialog show(FragmentManager fragmentManager) {
        return super.show(fragmentManager);
    }

    public /* bridge */ /* synthetic */ void showAllowingStateLoss(FragmentManager fragmentManager) {
        super.showAllowingStateLoss(fragmentManager);
    }

    public /* bridge */ /* synthetic */ void showAllowingStateLoss(FragmentManager fragmentManager, String str) {
        super.showAllowingStateLoss(fragmentManager, str);
    }

    public static SwitchPointDialog newInstance() {
        SwitchPointDialog switchPointDialog = new SwitchPointDialog();
        switchPointDialog.setSize(AdaptScreenUtils.pt2Px(848.0f), AdaptScreenUtils.pt2Px(564.0f));
        return switchPointDialog;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mBinding = (DialogSwitchingPointBinding) DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.dialog_switching_point, viewGroup, false);
        initView();
        initEvent();
        return this.mBinding.getRoot();
    }

    private void initEvent() {
        this.mAllBuildAdapter.setOnItemClickListener(this);
        this.mCurMarkerAdapter.setOnNavClickListener(this);
        this.mBinding.exitBtn.setOnClickListener(this);
        this.mBinding.floorSwipeRefresh.setOnRefreshListener(this);
        SelfChassis.getInstance().setLiftControlStatus(this);
        SelfChassis.getInstance().setMarkersDetailsListener(this);
    }

    private void initView() {
        this.mFloorList = new ArrayList();
        this.mMarkerList = new ArrayList();
        this.mAllBuildAdapter = new AllBuildAdapter(this.mFloorList);
        this.mBinding.buildNameRv.setLayoutManager(new LinearLayoutManager(getContext()));
        this.mBinding.buildNameRv.setAdapter(this.mAllBuildAdapter);
        this.mCurMarkerAdapter = new CurMarkerAdapter(this.mMarkerList);
        this.mBinding.markerRecycle.setLayoutManager(new LinearLayoutManager(getContext()));
        this.mBinding.markerRecycle.setAdapter(this.mCurMarkerAdapter);
        getFloorsInfo();
    }

    private void getFloorsInfo() {
        this.mBinding.floorSwipeRefresh.setRefreshing(true);
        SelfChassis.getInstance().serviceGetMarkersDetails("", "", 99);
    }

    public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        if (baseQuickAdapter instanceof AllBuildAdapter) {
            selectFloor(i);
        }
    }

    private void selectFloor(int i) {
        MarkersDetailsBean.ValuesBean.FloorsBean floorsBean = this.mFloorList.get(i);
        this.mFloorName = floorsBean.getFloor_name();
        this.mAllBuildAdapter.setCheckedItem(i);
        this.mMarkerList.clear();
        List<MarkerBean> markers = floorsBean.getMarkers();
        if (markers != null) {
            for (MarkerBean next : markers) {
                int behavior_code = (int) next.getBehavior_code();
                if (!(behavior_code == 5 || behavior_code == 4 || behavior_code == 3)) {
                    this.mMarkerList.add(next);
                }
            }
        }
        this.mCurMarkerAdapter.setCheckedItem(-1);
        this.mCurMarkerAdapter.notifyDataSetChanged();
    }

    public void onClick(View view) {
        if (view.getId() == R.id.exit_btn) {
            OnSwitchPointCancelListener onSwitchPointCancelListener = this.mSwitchPointCancelListener;
            if (onSwitchPointCancelListener != null) {
                onSwitchPointCancelListener.onCancel();
            }
            if (isAdded()) {
                dismissAllowingStateLoss();
            }
        }
    }

    public void onSelfChassisMsg(SelfChassisEventMsg selfChassisEventMsg) {
        if (selfChassisEventMsg != null && TextUtils.equals(selfChassisEventMsg.getCode(), ServiceContent.GET_MARKERS_DETAILS) && getActivity() != null) {
            getActivity().runOnUiThread(new Runnable(selfChassisEventMsg) {
                public final /* synthetic */ SelfChassisEventMsg f$1;

                {
                    this.f$1 = r2;
                }

                public final void run() {
                    SwitchPointDialog.this.lambda$onSelfChassisMsg$0$SwitchPointDialog(this.f$1);
                }
            });
        }
    }

    public /* synthetic */ void lambda$onSelfChassisMsg$0$SwitchPointDialog(SelfChassisEventMsg selfChassisEventMsg) {
        this.mBinding.floorSwipeRefresh.setRefreshing(false);
        MyLogUtils.Logd("SwitchPointDialog msg===" + selfChassisEventMsg.getData());
        if (selfChassisEventMsg.getData() != null && (selfChassisEventMsg.getData() instanceof MarkersDetailsBean)) {
            MarkersDetailsBean markersDetailsBean = (MarkersDetailsBean) selfChassisEventMsg.getData();
            MyLogUtils.Logd("markersDetails===" + GsonUtils.toJson(markersDetailsBean));
            this.mFloorList.clear();
            this.mMarkerList.clear();
            this.mFloorList.addAll(markersDetailsBean.getValues().getFloors());
            this.mAllBuildAdapter.notifyDataSetChanged();
            this.mCurMarkerAdapter.notifyDataSetChanged();
        }
    }

    public void onRefresh() {
        getFloorsInfo();
    }

    public void onNavClick(MarkerBean markerBean) {
        CrossFloorNaviReqBean.MsgBean msgBean = new CrossFloorNaviReqBean.MsgBean();
        msgBean.setMarker_name(markerBean.getName());
        msgBean.setGoal_floor(this.mFloorName);
        msgBean.setMarker_pose(markerBean.getPose());
        OnSwitchPointSelectListener onSwitchPointSelectListener = this.mSwitchPointSelectListener;
        if (onSwitchPointSelectListener != null) {
            onSwitchPointSelectListener.onSelect(msgBean);
        }
        if (isAdded()) {
            dismissAllowingStateLoss();
        }
    }

    public void setSwitchPointCancelListener(OnSwitchPointCancelListener onSwitchPointCancelListener) {
        this.mSwitchPointCancelListener = onSwitchPointCancelListener;
    }

    public void setSwitchPointSelectListener(OnSwitchPointSelectListener onSwitchPointSelectListener) {
        this.mSwitchPointSelectListener = onSwitchPointSelectListener;
    }
}
