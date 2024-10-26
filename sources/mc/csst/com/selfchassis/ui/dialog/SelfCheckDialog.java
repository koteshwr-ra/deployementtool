package mc.csst.com.selfchassis.ui.dialog;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.blankj.utilcode.util.AdaptScreenUtils;
import com.blankj.utilcode.util.ThreadUtils;
import com.ciot.base.util.AppSpUtil;
import com.ciot.base.util.MyLogUtils;
import com.ciot.sentrymove.R;
import java.util.ArrayList;
import java.util.List;
import mc.csst.com.selfchassis.databinding.DialogSelfCheckBinding;
import mc.csst.com.selfchassis.ui.adapter.CheckAdapter;
import mc.csst.com.selfchassislibrary.bean.msg.SelfDiagnosisResponseBean;
import mc.csst.com.selfchassislibrary.chassis.SelfChassis;
import mc.csst.com.selfchassislibrary.utils.LanguageUtils;
import mc.csst.com.selfchassislibrary.utils.SelfChassisListenerUtils;
import mc.csst.com.selfchassislibrary.utils.eventbus.SelfChassisEventMsg;

public class SelfCheckDialog extends BaseDialog implements View.OnClickListener, SelfChassisListenerUtils.OnBaseSelfChassisListener {
    private static final String TAG = SelfCheckDialog.class.getSimpleName();
    private DialogSelfCheckBinding mBinding;
    private CheckAdapter mCheckAdapter;
    private final List<SelfDiagnosisResponseBean.ValuesBean.StatusBean> mCheckList = new ArrayList();
    OnDialogButtonOnClickListener mOnDialogButtonOnClickListener;

    public interface OnDialogButtonOnClickListener {
        void okBtn();
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

    public static SelfCheckDialog newInstance() {
        SelfCheckDialog selfCheckDialog = new SelfCheckDialog();
        selfCheckDialog.setSize(AdaptScreenUtils.pt2Px(1426.0f), AdaptScreenUtils.pt2Px(877.0f));
        return selfCheckDialog;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mBinding = (DialogSelfCheckBinding) DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.dialog_self_check, viewGroup, false);
        initView();
        initEvent();
        return this.mBinding.getRoot();
    }

    private void initView() {
        Context context = getContext();
        this.mBinding.buttons.btnSelectNegative.setVisibility(8);
        this.mCheckAdapter = new CheckAdapter(this.mCheckList);
        this.mBinding.checkRecycle.setLayoutManager(new LinearLayoutManager(context));
        this.mBinding.checkRecycle.setAdapter(this.mCheckAdapter);
        this.mBinding.checkRecycle.addItemDecoration(new RecyclerView.ItemDecoration() {
            public void onDraw(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
                super.onDraw(canvas, recyclerView, state);
            }
        });
        getSelfCheckInfo();
    }

    private void initEvent() {
        this.mBinding.buttons.btnSelectPositive.setOnClickListener(this);
        this.mBinding.checkSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            public void onRefresh() {
                SelfCheckDialog.this.getSelfCheckInfo();
            }
        });
    }

    /* access modifiers changed from: private */
    public void getSelfCheckInfo() {
        SelfChassis.getInstance().setSelfDiagnosisListener(this);
        SelfChassis.getInstance().serviceSelfDiagnosis();
        this.mBinding.checkSwipeRefresh.setRefreshing(true);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.btn_selectPositive) {
            dealOk();
        }
    }

    private void dealOk() {
        OnDialogButtonOnClickListener onDialogButtonOnClickListener = this.mOnDialogButtonOnClickListener;
        if (onDialogButtonOnClickListener != null) {
            onDialogButtonOnClickListener.okBtn();
        }
        dismiss();
    }

    public void setOnDialogButtonOnClickListener(OnDialogButtonOnClickListener onDialogButtonOnClickListener) {
        this.mOnDialogButtonOnClickListener = onDialogButtonOnClickListener;
    }

    public void onSelfChassisMsg(SelfChassisEventMsg selfChassisEventMsg) {
        SelfDiagnosisResponseBean selfDiagnosisResponseBean = (SelfDiagnosisResponseBean) selfChassisEventMsg.getData();
        String str = TAG;
        MyLogUtils.Logd(str, "onSelfChassisMsg diagnosisInfo:" + selfDiagnosisResponseBean);
        AppSpUtil.getInstance().getLanguage();
        ThreadUtils.runOnUiThread(new Runnable(selfDiagnosisResponseBean) {
            public final /* synthetic */ SelfDiagnosisResponseBean f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                SelfCheckDialog.this.lambda$onSelfChassisMsg$0$SelfCheckDialog(this.f$1);
            }
        });
    }

    public /* synthetic */ void lambda$onSelfChassisMsg$0$SelfCheckDialog(SelfDiagnosisResponseBean selfDiagnosisResponseBean) {
        List<SelfDiagnosisResponseBean.ValuesBean.StatusBean> status = selfDiagnosisResponseBean.getValues().getStatus();
        this.mBinding.checkSwipeRefresh.setRefreshing(false);
        this.mCheckList.clear();
        if (!(status == null || status.size() == 0)) {
            this.mCheckList.addAll(LanguageUtils.selfDiagnosisChangeByLocalLanguage(status));
        }
        this.mCheckAdapter.notifyDataSetChanged();
    }

    public void onDestroyView() {
        super.onDestroyView();
    }
}
