package mc.csst.com.selfchassis.ui.dialog;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import com.blankj.utilcode.util.AdaptScreenUtils;
import com.blankj.utilcode.util.ClickUtils;
import com.blankj.utilcode.util.ThreadUtils;
import com.ciot.base.constant.NetConstant;
import com.ciot.base.util.ContextUtil;
import com.ciot.base.util.MyLogUtils;
import com.ciot.sentrymove.R;
import mc.csst.com.selfchassis.databinding.DialogAddMarkBinding;
import mc.csst.com.selfchassis.ui.fragment.set.map.MapFragment;
import mc.csst.com.selfchassislibrary.chassis.SelfChassis;
import mc.csst.com.selfchassislibrary.utils.SelfChassisListenerUtils;
import mc.csst.com.selfchassislibrary.utils.eventbus.SelfChassisEventMsg;

public class AddMarkDialog extends BaseDialog implements View.OnClickListener {
    private static final String KEY_POINT_NAME = "pointName";
    private static final String KEY_POINT_TYPE = "pointType";
    private static final String KEY_T = "t";
    private static final String KEY_X = "x";
    private static final String KEY_Y = "y";
    /* access modifiers changed from: private */
    public static final String TAG = AddMarkDialog.class.getSimpleName();
    private boolean isEditType = false;
    private DialogAddMarkBinding mBinding;
    /* access modifiers changed from: private */
    public LoadingDialog mLoadingDialog = null;
    /* access modifiers changed from: private */
    public SelfChassisListenerUtils.OnBaseSelfChassisListener mMarkCallBackListener;
    OnDialogButtonOnClickListener mOnDialogButtonOnClickListener;
    private String oldMarkerName = "";
    OnInsertMarkerListener onInsertMarkerListener;
    private volatile int tryGetPointNum = 0;

    public interface OnDialogButtonOnClickListener {
        void cancelBtn();

        boolean okBtn(int i, String str, float f, float f2, float f3);
    }

    public interface OnInsertMarkerListener {
        void insertCancel();

        void insertSuccess(String str);
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

    public static AddMarkDialog newInstance(String str, String str2, String str3) {
        AddMarkDialog addMarkDialog = new AddMarkDialog();
        Bundle bundle = new Bundle();
        bundle.putString(KEY_X, str);
        bundle.putString(KEY_Y, str2);
        bundle.putString(KEY_T, str3);
        addMarkDialog.setSize(AdaptScreenUtils.pt2Px(1092.0f), AdaptScreenUtils.pt2Px(520.0f));
        addMarkDialog.setArguments(bundle);
        return newInstance(str, str2, str3, NetConstant.PAGE_ID_HOME, "");
    }

    public static AddMarkDialog newInstance(String str, String str2, String str3, String str4, String str5) {
        AddMarkDialog addMarkDialog = new AddMarkDialog();
        Bundle bundle = new Bundle();
        bundle.putString(KEY_X, str);
        bundle.putString(KEY_Y, str2);
        bundle.putString(KEY_T, str3);
        bundle.putString(KEY_POINT_TYPE, str4);
        bundle.putString(KEY_POINT_NAME, str5);
        addMarkDialog.setSize(AdaptScreenUtils.pt2Px(1092.0f), AdaptScreenUtils.pt2Px(520.0f));
        addMarkDialog.setArguments(bundle);
        return addMarkDialog;
    }

    private String null2zero(String str) {
        return TextUtils.isEmpty(str) ? NetConstant.PAGE_ID_HOME : str;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        getDialog().getWindow().setSoftInputMode(32);
        this.mBinding = (DialogAddMarkBinding) DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.dialog_add_mark, viewGroup, false);
        initData();
        ClickUtils.applyGlobalDebouncing((View) this.mBinding.buttons.btnSelectPositive, (View.OnClickListener) this);
        ClickUtils.applyGlobalDebouncing((View) this.mBinding.buttons.btnSelectNegative, (View.OnClickListener) this);
        super.onViewCreated(this.mBinding.getRoot(), bundle);
        getDialog().getWindow().setSoftInputMode(32);
        return this.mBinding.getRoot();
    }

    private void initData() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            String null2zero = null2zero(arguments.getString(KEY_X));
            String null2zero2 = null2zero(arguments.getString(KEY_Y));
            String null2zero3 = null2zero(arguments.getString(KEY_T));
            int parseInt = Integer.parseInt(arguments.getString(KEY_POINT_TYPE));
            String string = arguments.getString(KEY_POINT_NAME);
            if (TextUtils.isEmpty(string)) {
                this.mBinding.dialogTitle.setText(ContextUtil.getContext().getString(R.string.add_mark_title));
            } else {
                this.isEditType = true;
                this.oldMarkerName = string;
                this.mBinding.dialogTitle.setText(ContextUtil.getContext().getString(R.string.edit_mark_title));
                this.mBinding.locationNameEt.setText(string);
            }
            if (parseInt == 0) {
                this.mBinding.pointTypeNormalRb.setChecked(true);
            } else if (parseInt == 3) {
                this.mBinding.pointTypeOutsideTheLiftRb.setChecked(true);
            } else if (parseInt == 4) {
                this.mBinding.pointTypeInTheLiftRb.setChecked(true);
            } else if (parseInt == 7) {
                this.mBinding.pointTypeGateRb.setChecked(true);
            } else if (parseInt == 8) {
                this.mBinding.pointTypeAccessControlRb.setChecked(true);
            } else if (parseInt == 11) {
                this.mBinding.pointTypeChargeRb.setChecked(true);
            } else if (parseInt == 50) {
                this.mBinding.pointTypeStopRb.setChecked(true);
            } else if (parseInt == 5) {
                this.mBinding.pointTypeWaitRb.setChecked(true);
            } else if (parseInt == 10) {
                this.mBinding.pointOutwardOfChargingPile.setChecked(true);
            }
            this.mBinding.xEt.setText(null2zero);
            this.mBinding.yEt.setText(null2zero2);
            this.mBinding.tEt.setText(null2zero3);
        }
        if (this.mLoadingDialog == null) {
            this.mLoadingDialog = LoadingDialog.newInstance();
        }
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_selectPositive) {
            ok();
        } else if (id == R.id.btn_selectNegative) {
            dealClose();
        }
    }

    private void dealClose() {
        OnDialogButtonOnClickListener onDialogButtonOnClickListener = this.mOnDialogButtonOnClickListener;
        if (onDialogButtonOnClickListener != null) {
            onDialogButtonOnClickListener.cancelBtn();
        }
        dismissAllowingStateLoss();
    }

    /* JADX WARNING: Removed duplicated region for block: B:41:0x00f6  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x0122  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x013e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void ok() {
        /*
            r13 = this;
            mc.csst.com.selfchassis.databinding.DialogAddMarkBinding r0 = r13.mBinding
            androidx.appcompat.widget.AppCompatEditText r0 = r0.xEt
            android.text.Editable r0 = r0.getText()
            java.lang.String r0 = r0.toString()
            mc.csst.com.selfchassis.databinding.DialogAddMarkBinding r1 = r13.mBinding
            androidx.appcompat.widget.AppCompatEditText r1 = r1.yEt
            android.text.Editable r1 = r1.getText()
            java.lang.String r1 = r1.toString()
            mc.csst.com.selfchassis.databinding.DialogAddMarkBinding r2 = r13.mBinding
            androidx.appcompat.widget.AppCompatEditText r2 = r2.tEt
            android.text.Editable r2 = r2.getText()
            java.lang.String r2 = r2.toString()
            mc.csst.com.selfchassis.databinding.DialogAddMarkBinding r3 = r13.mBinding
            androidx.appcompat.widget.AppCompatEditText r3 = r3.locationNameEt
            android.text.Editable r3 = r3.getText()
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 != 0) goto L_0x0049
            mc.csst.com.selfchassis.databinding.DialogAddMarkBinding r3 = r13.mBinding
            androidx.appcompat.widget.AppCompatEditText r3 = r3.locationNameEt
            android.text.Editable r3 = r3.getText()
            java.lang.Object r3 = java.util.Objects.requireNonNull(r3)
            android.text.Editable r3 = (android.text.Editable) r3
            java.lang.String r3 = r3.toString()
            java.lang.String r3 = r3.trim()
            goto L_0x004b
        L_0x0049:
            java.lang.String r3 = ""
        L_0x004b:
            java.lang.String r4 = "x"
            boolean r4 = r13.checkNullAndShowToast(r0, r4)
            if (r4 != 0) goto L_0x0162
            java.lang.String r4 = "y"
            boolean r4 = r13.checkNullAndShowToast(r1, r4)
            if (r4 != 0) goto L_0x0162
            java.lang.String r4 = "t"
            boolean r4 = r13.checkNullAndShowToast(r2, r4)
            if (r4 != 0) goto L_0x0162
            android.content.Context r4 = com.ciot.base.util.ContextUtil.getContext()
            r5 = 2131755733(0x7f1002d5, float:1.9142354E38)
            java.lang.String r4 = r4.getString(r5)
            boolean r4 = r13.checkNullAndShowToast(r3, r4)
            if (r4 == 0) goto L_0x0076
            goto L_0x0162
        L_0x0076:
            java.lang.String r0 = r13.checkValue(r0)
            java.lang.String r1 = r13.checkValue(r1)
            java.lang.String r10 = r13.checkValue(r2)
            mc.csst.com.selfchassis.databinding.DialogAddMarkBinding r2 = r13.mBinding
            androidx.appcompat.widget.AppCompatRadioButton r2 = r2.pointTypeNormalRb
            boolean r2 = r2.isChecked()
            r4 = 0
            if (r2 == 0) goto L_0x008f
        L_0x008d:
            r2 = 0
            goto L_0x00f2
        L_0x008f:
            mc.csst.com.selfchassis.databinding.DialogAddMarkBinding r2 = r13.mBinding
            androidx.appcompat.widget.AppCompatRadioButton r2 = r2.pointTypeOutsideTheLiftRb
            boolean r2 = r2.isChecked()
            if (r2 == 0) goto L_0x009b
            r2 = 3
            goto L_0x00f2
        L_0x009b:
            mc.csst.com.selfchassis.databinding.DialogAddMarkBinding r2 = r13.mBinding
            androidx.appcompat.widget.AppCompatRadioButton r2 = r2.pointTypeInTheLiftRb
            boolean r2 = r2.isChecked()
            if (r2 == 0) goto L_0x00a7
            r2 = 4
            goto L_0x00f2
        L_0x00a7:
            mc.csst.com.selfchassis.databinding.DialogAddMarkBinding r2 = r13.mBinding
            androidx.appcompat.widget.AppCompatRadioButton r2 = r2.pointTypeGateRb
            boolean r2 = r2.isChecked()
            if (r2 == 0) goto L_0x00b3
            r2 = 7
            goto L_0x00f2
        L_0x00b3:
            mc.csst.com.selfchassis.databinding.DialogAddMarkBinding r2 = r13.mBinding
            androidx.appcompat.widget.AppCompatRadioButton r2 = r2.pointTypeAccessControlRb
            boolean r2 = r2.isChecked()
            if (r2 == 0) goto L_0x00c0
            r2 = 8
            goto L_0x00f2
        L_0x00c0:
            mc.csst.com.selfchassis.databinding.DialogAddMarkBinding r2 = r13.mBinding
            androidx.appcompat.widget.AppCompatRadioButton r2 = r2.pointTypeChargeRb
            boolean r2 = r2.isChecked()
            if (r2 == 0) goto L_0x00cd
            r2 = 11
            goto L_0x00f2
        L_0x00cd:
            mc.csst.com.selfchassis.databinding.DialogAddMarkBinding r2 = r13.mBinding
            androidx.appcompat.widget.AppCompatRadioButton r2 = r2.pointTypeStopRb
            boolean r2 = r2.isChecked()
            if (r2 == 0) goto L_0x00da
            r2 = 50
            goto L_0x00f2
        L_0x00da:
            mc.csst.com.selfchassis.databinding.DialogAddMarkBinding r2 = r13.mBinding
            androidx.appcompat.widget.AppCompatRadioButton r2 = r2.pointTypeWaitRb
            boolean r2 = r2.isChecked()
            if (r2 == 0) goto L_0x00e6
            r2 = 5
            goto L_0x00f2
        L_0x00e6:
            mc.csst.com.selfchassis.databinding.DialogAddMarkBinding r2 = r13.mBinding
            androidx.appcompat.widget.AppCompatRadioButton r2 = r2.pointOutwardOfChargingPile
            boolean r2 = r2.isChecked()
            if (r2 == 0) goto L_0x008d
            r2 = 10
        L_0x00f2:
            mc.csst.com.selfchassis.ui.dialog.AddMarkDialog$OnDialogButtonOnClickListener r4 = r13.mOnDialogButtonOnClickListener
            if (r4 == 0) goto L_0x010b
            float r7 = java.lang.Float.parseFloat(r0)
            float r8 = java.lang.Float.parseFloat(r1)
            float r9 = java.lang.Float.parseFloat(r10)
            r5 = r2
            r6 = r3
            boolean r4 = r4.okBtn(r5, r6, r7, r8, r9)
            if (r4 != 0) goto L_0x010b
            return
        L_0x010b:
            r13.showLoadingDialog()
            mc.csst.com.selfchassis.ui.dialog.-$$Lambda$AddMarkDialog$roZeHPgOew8kQFEh7uPNwr7dIQ4 r4 = new mc.csst.com.selfchassis.ui.dialog.-$$Lambda$AddMarkDialog$roZeHPgOew8kQFEh7uPNwr7dIQ4
            r4.<init>(r3)
            r13.mMarkCallBackListener = r4
            mc.csst.com.selfchassislibrary.utils.SelfChassisListenerUtils r4 = mc.csst.com.selfchassislibrary.utils.SelfChassisListenerUtils.getInstance()
            mc.csst.com.selfchassislibrary.utils.SelfChassisListenerUtils$OnBaseSelfChassisListener r5 = r13.mMarkCallBackListener
            r4.addMarkersListeners(r5)
            boolean r4 = r13.isEditType
            if (r4 == 0) goto L_0x013e
            mc.csst.com.selfchassislibrary.chassis.SelfChassis r4 = mc.csst.com.selfchassislibrary.chassis.SelfChassis.getInstance()
            java.lang.String r5 = r13.oldMarkerName
            r4.sendDeleteMarker(r5)
            mc.csst.com.selfchassislibrary.utils.SelfChassisListenerUtils r11 = mc.csst.com.selfchassislibrary.utils.SelfChassisListenerUtils.getInstance()
            mc.csst.com.selfchassis.ui.dialog.AddMarkDialog$1 r12 = new mc.csst.com.selfchassis.ui.dialog.AddMarkDialog$1
            r4 = r12
            r5 = r13
            r6 = r3
            r7 = r2
            r8 = r0
            r9 = r1
            r4.<init>(r6, r7, r8, r9, r10)
            r11.setDeletePoiListener(r12)
            goto L_0x0162
        L_0x013e:
            mc.csst.com.selfchassislibrary.chassis.SelfChassis r4 = mc.csst.com.selfchassislibrary.chassis.SelfChassis.getInstance()
            float r7 = java.lang.Float.parseFloat(r0)
            float r8 = java.lang.Float.parseFloat(r1)
            float r9 = java.lang.Float.parseFloat(r10)
            r5 = r3
            r6 = r2
            r4.sendInsertMarkerByPose(r5, r6, r7, r8, r9)
            android.os.Handler r0 = new android.os.Handler
            r0.<init>()
            mc.csst.com.selfchassis.ui.dialog.AddMarkDialog$2 r1 = new mc.csst.com.selfchassis.ui.dialog.AddMarkDialog$2
            r1.<init>()
            r2 = 1000(0x3e8, double:4.94E-321)
            r0.postDelayed(r1, r2)
        L_0x0162:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: mc.csst.com.selfchassis.ui.dialog.AddMarkDialog.ok():void");
    }

    public /* synthetic */ void lambda$ok$0$AddMarkDialog(String str, SelfChassisEventMsg selfChassisEventMsg) {
        if (selfChassisEventMsg == null) {
            return;
        }
        if (selfChassisEventMsg.getData() != null) {
            closeLoadingDialog();
            OnInsertMarkerListener onInsertMarkerListener2 = this.onInsertMarkerListener;
            if (onInsertMarkerListener2 != null) {
                onInsertMarkerListener2.insertSuccess(str);
            }
        } else if (this.tryGetPointNum > 6) {
            this.tryGetPointNum = 0;
            closeLoadingDialog();
            OnInsertMarkerListener onInsertMarkerListener3 = this.onInsertMarkerListener;
            if (onInsertMarkerListener3 != null) {
                onInsertMarkerListener3.insertCancel();
            }
        } else {
            this.tryGetPointNum++;
            MyLogUtils.Logd(TAG, "尝试获取点位列表次数：" + this.tryGetPointNum);
            SelfChassis.getInstance().sendGetMarkerList();
        }
    }

    private String checkValue(String str) {
        return (TextUtils.isEmpty(str) || MapFragment.SLASH.equals(str)) ? NetConstant.PAGE_ID_HOME : str;
    }

    private void showLoadingDialog() {
        LoadingDialog loadingDialog = this.mLoadingDialog;
        if (loadingDialog != null && !loadingDialog.isAdded()) {
            this.mLoadingDialog.showAllowingStateLoss(getChildFragmentManager());
        }
    }

    private void closeLoadingDialog() {
        ThreadUtils.runOnUiThread(new Runnable() {
            public void run() {
                if (AddMarkDialog.this.mLoadingDialog != null && AddMarkDialog.this.mLoadingDialog.isAdded()) {
                    AddMarkDialog.this.mLoadingDialog.dismissAllowingStateLoss();
                }
                if (AddMarkDialog.this.isAdded()) {
                    AddMarkDialog.this.dismissAllowingStateLoss();
                }
                if (SelfChassisListenerUtils.getInstance().getMarkersListeners() != null) {
                    String access$100 = AddMarkDialog.TAG;
                    MyLogUtils.Logd(access$100, "getMarkersListeners():" + SelfChassisListenerUtils.getInstance().getMarkersListeners().size());
                    SelfChassisListenerUtils.getInstance().getMarkersListeners().remove(AddMarkDialog.this.mMarkCallBackListener);
                    String access$1002 = AddMarkDialog.TAG;
                    MyLogUtils.Logd(access$1002, "getMarkersListeners() remove:" + SelfChassisListenerUtils.getInstance().getMarkersListeners().size());
                }
            }
        });
    }

    public void setOnInsertMarkerListener(OnInsertMarkerListener onInsertMarkerListener2) {
        this.onInsertMarkerListener = onInsertMarkerListener2;
    }

    public void setOnDialogButtonOnClickListener(OnDialogButtonOnClickListener onDialogButtonOnClickListener) {
        this.mOnDialogButtonOnClickListener = onDialogButtonOnClickListener;
    }
}
