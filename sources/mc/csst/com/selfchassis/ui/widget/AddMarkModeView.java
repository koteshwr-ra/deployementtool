package mc.csst.com.selfchassis.ui.widget;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import com.blankj.utilcode.util.ClickUtils;
import com.blankj.utilcode.util.ThreadUtils;
import com.ciot.base.constant.NetConstant;
import com.ciot.base.util.ContextUtil;
import com.ciot.base.util.MyLogUtils;
import com.ciot.sentrymove.R;
import mc.csst.com.selfchassis.databinding.DialogAddMarkRightBinding;
import mc.csst.com.selfchassis.ui.dialog.LoadingDialog;
import mc.csst.com.selfchassis.ui.fragment.set.map.MapFragment;
import mc.csst.com.selfchassis.ui.widget.disc.PointRotateView;
import mc.csst.com.selfchassis.utils.MyToastUtils;
import mc.csst.com.selfchassis.utils.view.FlowRadioGroup;
import mc.csst.com.selfchassislibrary.bean.PointBean;
import mc.csst.com.selfchassislibrary.chassis.SelfChassis;
import mc.csst.com.selfchassislibrary.utils.SelfChassisListenerUtils;
import mc.csst.com.selfchassislibrary.utils.eventbus.SelfChassisEventMsg;

public class AddMarkModeView extends FrameLayout implements View.OnClickListener {
    private static final String KEY_T = "t";
    private static final String KEY_X = "x";
    private static final String KEY_Y = "y";
    /* access modifiers changed from: private */
    public static final String TAG = AddMarkModeView.class.getSimpleName();
    private FragmentManager fragmentManager;
    private boolean isChecking;
    private boolean isEditType;
    private DialogAddMarkRightBinding mBinding;
    /* access modifiers changed from: private */
    public LoadingDialog mLoadingDialog;
    /* access modifiers changed from: private */
    public SelfChassisListenerUtils.OnBaseSelfChassisListener mMarkCallBackListener;
    OnDialogButtonOnClickListener mOnDialogButtonOnClickListener;
    private String oldMarkerName;
    OnInsertMarkerListener onInsertMarkerListener;
    OnUpdatePositionListener onUpdatePositionListener;
    /* access modifiers changed from: private */
    public float step;
    /* access modifiers changed from: private */
    public float t;
    private volatile int tryGetPointNum;
    private float x;
    private float y;

    public interface OnDialogButtonOnClickListener {
        void cancelBtn();

        boolean okBtn(int i, String str, float f, float f2, float f3);
    }

    public interface OnInsertMarkerListener {
        void insertCancel();

        void insertSuccess(String str);
    }

    public interface OnUpdatePositionListener {
        void update(PointBean pointBean);
    }

    public static float appToChassis(float f) {
        return (float) (((double) (-f)) + 3.141592653589793d);
    }

    public static float chassisToApp(float f) {
        return (float) (((double) (-f)) + 3.141592653589793d);
    }

    static /* synthetic */ float access$316(AddMarkModeView addMarkModeView, float f) {
        float f2 = addMarkModeView.y + f;
        addMarkModeView.y = f2;
        return f2;
    }

    static /* synthetic */ float access$324(AddMarkModeView addMarkModeView, float f) {
        float f2 = addMarkModeView.y - f;
        addMarkModeView.y = f2;
        return f2;
    }

    static /* synthetic */ float access$516(AddMarkModeView addMarkModeView, float f) {
        float f2 = addMarkModeView.x + f;
        addMarkModeView.x = f2;
        return f2;
    }

    static /* synthetic */ float access$524(AddMarkModeView addMarkModeView, float f) {
        float f2 = addMarkModeView.x - f;
        addMarkModeView.x = f2;
        return f2;
    }

    public void setFragmentManager(FragmentManager fragmentManager2) {
        this.fragmentManager = fragmentManager2;
    }

    public AddMarkModeView(Context context) {
        this(context, (AttributeSet) null);
    }

    public AddMarkModeView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public AddMarkModeView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mLoadingDialog = null;
        this.isEditType = false;
        this.oldMarkerName = "";
        this.tryGetPointNum = 0;
        this.isChecking = true;
        this.step = 0.1f;
        initView();
    }

    private void initView() {
        DialogAddMarkRightBinding dialogAddMarkRightBinding = (DialogAddMarkRightBinding) DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.dialog_add_mark_right, this, true);
        this.mBinding = dialogAddMarkRightBinding;
        ClickUtils.applyGlobalDebouncing((View) dialogAddMarkRightBinding.btnSelectPositive, (View.OnClickListener) this);
        ClickUtils.applyGlobalDebouncing((View) this.mBinding.btnSelectNegative, (View.OnClickListener) this);
        this.mBinding.flowRG1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public final void onCheckedChanged(RadioGroup radioGroup, int i) {
                AddMarkModeView.this.lambda$initView$0$AddMarkModeView(radioGroup, i);
            }
        });
        this.mBinding.flowRG2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public final void onCheckedChanged(RadioGroup radioGroup, int i) {
                AddMarkModeView.this.lambda$initView$1$AddMarkModeView(radioGroup, i);
            }
        });
        initClickToShowTextListener(this.mBinding.flowRG1);
        initClickToShowTextListener(this.mBinding.flowRG2);
        this.mBinding.pointRotateView.setPointRotateViewListener(new PointRotateView.PointRotateViewListener() {
            public void onRotate(float f) {
                float unused = AddMarkModeView.this.t = f;
                AddMarkModeView.this.updateValue();
                AddMarkModeView.this.updateListener();
            }

            public void onTop() {
                AddMarkModeView addMarkModeView = AddMarkModeView.this;
                AddMarkModeView.access$316(addMarkModeView, addMarkModeView.step);
                AddMarkModeView.this.updateValue();
                AddMarkModeView.this.updateListener();
            }

            public void onBottom() {
                AddMarkModeView addMarkModeView = AddMarkModeView.this;
                AddMarkModeView.access$324(addMarkModeView, addMarkModeView.step);
                AddMarkModeView.this.updateValue();
                AddMarkModeView.this.updateListener();
            }

            public void onLeft() {
                AddMarkModeView addMarkModeView = AddMarkModeView.this;
                AddMarkModeView.access$524(addMarkModeView, addMarkModeView.step);
                AddMarkModeView.this.updateValue();
                AddMarkModeView.this.updateListener();
            }

            public void onRight() {
                AddMarkModeView addMarkModeView = AddMarkModeView.this;
                AddMarkModeView.access$516(addMarkModeView, addMarkModeView.step);
                AddMarkModeView.this.updateValue();
                AddMarkModeView.this.updateListener();
            }
        });
        setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });
    }

    public /* synthetic */ void lambda$initView$0$AddMarkModeView(RadioGroup radioGroup, int i) {
        if (i != -1 && this.isChecking) {
            this.isChecking = false;
            this.mBinding.flowRG2.clearCheck();
        }
        this.isChecking = true;
    }

    public /* synthetic */ void lambda$initView$1$AddMarkModeView(RadioGroup radioGroup, int i) {
        if (i != -1 && this.isChecking) {
            this.isChecking = false;
            this.mBinding.flowRG1.clearCheck();
        }
        this.isChecking = true;
    }

    private void initClickToShowTextListener(FlowRadioGroup flowRadioGroup) {
        for (int i = 0; i < flowRadioGroup.getChildCount(); i++) {
            final RadioButton radioButton = (RadioButton) flowRadioGroup.getChildAt(i);
            radioButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    MyToastUtils.showShort(AddMarkModeView.this.getContext(), radioButton.getText());
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void updateListener() {
        PointBean pointBean = new PointBean();
        pointBean.setX(this.x);
        pointBean.setY(this.y);
        pointBean.setTheta(this.t);
        OnUpdatePositionListener onUpdatePositionListener2 = this.onUpdatePositionListener;
        if (onUpdatePositionListener2 != null) {
            onUpdatePositionListener2.update(pointBean);
        }
    }

    public void setData(float f, float f2, float f3) {
        setData(f, f2, f3, 0, "");
    }

    public void setData(float f, float f2, float f3, int i, String str) {
        String str2 = TAG;
        Log.d(str2, "setData: t: " + f3);
        this.x = f;
        this.y = f2;
        this.t = f3;
        if (TextUtils.isEmpty(str)) {
            this.mBinding.dialogTitle.setText(ContextUtil.getContext().getString(R.string.add_mark_title));
            this.mBinding.locationNameEt.setText(str);
        } else {
            this.isEditType = true;
            this.oldMarkerName = str;
            this.mBinding.dialogTitle.setText(ContextUtil.getContext().getString(R.string.edit_mark_title));
            this.mBinding.locationNameEt.setText(str);
        }
        updateValue();
        this.mBinding.pointRotateView.setTemp(f3);
        if (i == 0) {
            this.mBinding.pointTypeNormalRb.setChecked(true);
        } else if (i == 3) {
            this.mBinding.pointTypeOutsideTheLiftRb.setChecked(true);
        } else if (i == 4) {
            this.mBinding.pointTypeInTheLiftRb.setChecked(true);
        } else if (i == 7) {
            this.mBinding.pointTypeGateRb.setChecked(true);
        } else if (i == 8) {
            this.mBinding.pointTypeAccessControlRb.setChecked(true);
        } else if (i == 11) {
            this.mBinding.pointTypeChargeRb.setChecked(true);
        } else if (i == 50) {
            this.mBinding.pointTypeStopRb.setChecked(true);
        } else if (i == 5) {
            this.mBinding.pointTypeWaitRb.setChecked(true);
        } else if (i == 10) {
            this.mBinding.pointOutwardOfChargingPile.setChecked(true);
        }
        if (this.mLoadingDialog == null) {
            this.mLoadingDialog = LoadingDialog.newInstance();
        }
    }

    /* access modifiers changed from: private */
    public void updateValue() {
        this.mBinding.xEt.setText(String.valueOf(this.x));
        this.mBinding.yEt.setText(String.valueOf(this.y));
        this.mBinding.tEt.setText(String.valueOf(appToChassis(this.t)));
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
        setVisibility(8);
    }

    /* JADX WARNING: Removed duplicated region for block: B:41:0x00f6  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x0122  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x013e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void ok() {
        /*
            r13 = this;
            mc.csst.com.selfchassis.databinding.DialogAddMarkRightBinding r0 = r13.mBinding
            androidx.appcompat.widget.AppCompatTextView r0 = r0.xEt
            java.lang.CharSequence r0 = r0.getText()
            java.lang.String r0 = r0.toString()
            mc.csst.com.selfchassis.databinding.DialogAddMarkRightBinding r1 = r13.mBinding
            androidx.appcompat.widget.AppCompatTextView r1 = r1.yEt
            java.lang.CharSequence r1 = r1.getText()
            java.lang.String r1 = r1.toString()
            mc.csst.com.selfchassis.databinding.DialogAddMarkRightBinding r2 = r13.mBinding
            androidx.appcompat.widget.AppCompatTextView r2 = r2.tEt
            java.lang.CharSequence r2 = r2.getText()
            java.lang.String r2 = r2.toString()
            mc.csst.com.selfchassis.databinding.DialogAddMarkRightBinding r3 = r13.mBinding
            androidx.appcompat.widget.AppCompatEditText r3 = r3.locationNameEt
            android.text.Editable r3 = r3.getText()
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 != 0) goto L_0x0049
            mc.csst.com.selfchassis.databinding.DialogAddMarkRightBinding r3 = r13.mBinding
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
            mc.csst.com.selfchassis.databinding.DialogAddMarkRightBinding r2 = r13.mBinding
            androidx.appcompat.widget.AppCompatRadioButton r2 = r2.pointTypeNormalRb
            boolean r2 = r2.isChecked()
            r4 = 0
            if (r2 == 0) goto L_0x008f
        L_0x008d:
            r2 = 0
            goto L_0x00f2
        L_0x008f:
            mc.csst.com.selfchassis.databinding.DialogAddMarkRightBinding r2 = r13.mBinding
            androidx.appcompat.widget.AppCompatRadioButton r2 = r2.pointTypeOutsideTheLiftRb
            boolean r2 = r2.isChecked()
            if (r2 == 0) goto L_0x009b
            r2 = 3
            goto L_0x00f2
        L_0x009b:
            mc.csst.com.selfchassis.databinding.DialogAddMarkRightBinding r2 = r13.mBinding
            androidx.appcompat.widget.AppCompatRadioButton r2 = r2.pointTypeInTheLiftRb
            boolean r2 = r2.isChecked()
            if (r2 == 0) goto L_0x00a7
            r2 = 4
            goto L_0x00f2
        L_0x00a7:
            mc.csst.com.selfchassis.databinding.DialogAddMarkRightBinding r2 = r13.mBinding
            androidx.appcompat.widget.AppCompatRadioButton r2 = r2.pointTypeGateRb
            boolean r2 = r2.isChecked()
            if (r2 == 0) goto L_0x00b3
            r2 = 7
            goto L_0x00f2
        L_0x00b3:
            mc.csst.com.selfchassis.databinding.DialogAddMarkRightBinding r2 = r13.mBinding
            androidx.appcompat.widget.AppCompatRadioButton r2 = r2.pointTypeAccessControlRb
            boolean r2 = r2.isChecked()
            if (r2 == 0) goto L_0x00c0
            r2 = 8
            goto L_0x00f2
        L_0x00c0:
            mc.csst.com.selfchassis.databinding.DialogAddMarkRightBinding r2 = r13.mBinding
            androidx.appcompat.widget.AppCompatRadioButton r2 = r2.pointTypeChargeRb
            boolean r2 = r2.isChecked()
            if (r2 == 0) goto L_0x00cd
            r2 = 11
            goto L_0x00f2
        L_0x00cd:
            mc.csst.com.selfchassis.databinding.DialogAddMarkRightBinding r2 = r13.mBinding
            androidx.appcompat.widget.AppCompatRadioButton r2 = r2.pointTypeStopRb
            boolean r2 = r2.isChecked()
            if (r2 == 0) goto L_0x00da
            r2 = 50
            goto L_0x00f2
        L_0x00da:
            mc.csst.com.selfchassis.databinding.DialogAddMarkRightBinding r2 = r13.mBinding
            androidx.appcompat.widget.AppCompatRadioButton r2 = r2.pointTypeWaitRb
            boolean r2 = r2.isChecked()
            if (r2 == 0) goto L_0x00e6
            r2 = 5
            goto L_0x00f2
        L_0x00e6:
            mc.csst.com.selfchassis.databinding.DialogAddMarkRightBinding r2 = r13.mBinding
            androidx.appcompat.widget.AppCompatRadioButton r2 = r2.pointOutwardOfChargingPile
            boolean r2 = r2.isChecked()
            if (r2 == 0) goto L_0x008d
            r2 = 10
        L_0x00f2:
            mc.csst.com.selfchassis.ui.widget.AddMarkModeView$OnDialogButtonOnClickListener r4 = r13.mOnDialogButtonOnClickListener
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
            mc.csst.com.selfchassis.ui.widget.-$$Lambda$AddMarkModeView$9dXFbf7LPlDYiCr3kpRilNdIt2U r4 = new mc.csst.com.selfchassis.ui.widget.-$$Lambda$AddMarkModeView$9dXFbf7LPlDYiCr3kpRilNdIt2U
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
            mc.csst.com.selfchassis.ui.widget.AddMarkModeView$4 r12 = new mc.csst.com.selfchassis.ui.widget.AddMarkModeView$4
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
            mc.csst.com.selfchassis.ui.widget.AddMarkModeView$5 r1 = new mc.csst.com.selfchassis.ui.widget.AddMarkModeView$5
            r1.<init>()
            r2 = 1000(0x3e8, double:4.94E-321)
            r0.postDelayed(r1, r2)
        L_0x0162:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: mc.csst.com.selfchassis.ui.widget.AddMarkModeView.ok():void");
    }

    public /* synthetic */ void lambda$ok$2$AddMarkModeView(String str, SelfChassisEventMsg selfChassisEventMsg) {
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
            this.mLoadingDialog.showAllowingStateLoss(this.fragmentManager);
        }
    }

    private void closeLoadingDialog() {
        ThreadUtils.runOnUiThread(new Runnable() {
            public void run() {
                if (AddMarkModeView.this.mLoadingDialog != null && AddMarkModeView.this.mLoadingDialog.isAdded()) {
                    AddMarkModeView.this.mLoadingDialog.dismissAllowingStateLoss();
                }
                AddMarkModeView.this.setVisibility(8);
                if (SelfChassisListenerUtils.getInstance().getMarkersListeners() != null) {
                    String access$700 = AddMarkModeView.TAG;
                    MyLogUtils.Logd(access$700, "getMarkersListeners():" + SelfChassisListenerUtils.getInstance().getMarkersListeners().size());
                    SelfChassisListenerUtils.getInstance().getMarkersListeners().remove(AddMarkModeView.this.mMarkCallBackListener);
                    String access$7002 = AddMarkModeView.TAG;
                    MyLogUtils.Logd(access$7002, "getMarkersListeners() remove:" + SelfChassisListenerUtils.getInstance().getMarkersListeners().size());
                }
            }
        });
    }

    public void setOnUpdatePositionListener(OnUpdatePositionListener onUpdatePositionListener2) {
        this.onUpdatePositionListener = onUpdatePositionListener2;
    }

    public void setOnInsertMarkerListener(OnInsertMarkerListener onInsertMarkerListener2) {
        this.onInsertMarkerListener = onInsertMarkerListener2;
    }

    public void setOnDialogButtonOnClickListener(OnDialogButtonOnClickListener onDialogButtonOnClickListener) {
        this.mOnDialogButtonOnClickListener = onDialogButtonOnClickListener;
    }

    public boolean checkNullAndShowToast(String str, String str2) {
        if (!TextUtils.isEmpty(str)) {
            return false;
        }
        Context context = ContextUtil.getContext();
        MyToastUtils.showShort(context, str2 + ContextUtil.getContext().getResources().getString(R.string.tip_empty));
        return true;
    }
}
