package mc.csst.com.selfchassis.ui.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.blankj.utilcode.util.AdaptScreenUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ciot.sentrymove.R;
import java.util.ArrayList;
import java.util.List;
import mc.csst.com.selfchassis.databinding.DialogChooseMarkerBinding;
import mc.csst.com.selfchassis.ui.adapter.ChoosePointWaitMarkerAdapter;
import mc.csst.com.selfchassislibrary.bean.msg.MarkerOperationGetMarkersResponseBean;
import mc.csst.com.selfchassislibrary.chassis.SelfChassis;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class ChoosePointWaitMarkerDialog extends BaseDialog implements View.OnClickListener {
    private DialogChooseMarkerBinding mBinding;
    /* access modifiers changed from: private */
    public ChoosePointWaitMarkerAdapter mMarkerAdapter;
    private final List<MarkerOperationGetMarkersResponseBean.ValuesBean.MarkersBean.WaypointsBean> mMarkerList = new ArrayList();
    private OnChooseMarkerListener mOnChooseMarkerListener;

    public interface OnChooseMarkerListener {
        void onChooseMarkerListener(ArrayList<MarkerOperationGetMarkersResponseBean.ValuesBean.MarkersBean.WaypointsBean> arrayList);
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

    public static ChoosePointWaitMarkerDialog newInstance() {
        ChoosePointWaitMarkerDialog choosePointWaitMarkerDialog = new ChoosePointWaitMarkerDialog();
        choosePointWaitMarkerDialog.setSize(AdaptScreenUtils.pt2Px(1110.0f), AdaptScreenUtils.pt2Px(600.0f));
        return choosePointWaitMarkerDialog;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mBinding = (DialogChooseMarkerBinding) DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.dialog_choose_marker, viewGroup, false);
        initView();
        initEvent();
        return this.mBinding.getRoot();
    }

    private void initView() {
        this.mBinding.titleTv.setText(R.string.txt_title_select_point_wait);
        Context context = getContext();
        this.mMarkerAdapter = new ChoosePointWaitMarkerAdapter(this.mMarkerList);
        this.mBinding.markerRecycle.setLayoutManager(new GridLayoutManager(context, 3));
        this.mBinding.markerRecycle.setAdapter(this.mMarkerAdapter);
        EventBus.getDefault().register(this);
        getMarkers();
    }

    private void initEvent() {
        this.mBinding.buttons.btnSelectNegative.setOnClickListener(this);
        this.mBinding.buttons.btnSelectPositive.setOnClickListener(this);
        this.mMarkerAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                ChoosePointWaitMarkerDialog.this.mMarkerAdapter.setCheckedItem(i);
            }
        });
        this.mBinding.markerSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            public void onRefresh() {
                ChoosePointWaitMarkerDialog.this.getMarkers();
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveSelfMarkerList(MarkerOperationGetMarkersResponseBean markerOperationGetMarkersResponseBean) {
        this.mBinding.markerSwipeRefresh.setRefreshing(false);
        this.mMarkerList.clear();
        List<MarkerOperationGetMarkersResponseBean.ValuesBean.MarkersBean.WaypointsBean> waypoints = markerOperationGetMarkersResponseBean.getValues().getMarkers().getWaypoints();
        for (int i = 0; i < waypoints.size(); i++) {
            if (waypoints.get(i).getBehavior_code() == 5) {
                this.mMarkerList.add(waypoints.get(i));
            }
        }
        this.mMarkerAdapter.notifyDataSetChanged();
    }

    /* access modifiers changed from: private */
    public void getMarkers() {
        SelfChassis.getInstance().sendGetMarkerList();
        this.mBinding.markerSwipeRefresh.setRefreshing(true);
    }

    public void setOnChooseMarkerListener(OnChooseMarkerListener onChooseMarkerListener) {
        this.mOnChooseMarkerListener = onChooseMarkerListener;
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_selectPositive) {
            dealOk();
        } else if (id == R.id.btn_selectNegative) {
            dealCancel();
        }
    }

    private void dealOk() {
        if (this.mOnChooseMarkerListener != null && this.mMarkerList.size() > 0) {
            this.mOnChooseMarkerListener.onChooseMarkerListener(this.mMarkerAdapter.getCheckedItems());
        }
        dismiss();
    }

    private void dealCancel() {
        dismiss();
    }

    public void onDestroyView() {
        EventBus.getDefault().unregister(this);
        super.onDestroyView();
    }
}
