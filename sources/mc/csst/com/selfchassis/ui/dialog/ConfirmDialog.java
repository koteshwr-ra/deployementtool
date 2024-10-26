package mc.csst.com.selfchassis.ui.dialog;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import com.ciot.base.util.ContextUtil;
import com.ciot.sentrymove.R;

public class ConfirmDialog extends BaseDialog {
    private static final String KEY_CANCEL_BTN_TXT = "cancelButton";
    private static final String KEY_IS_SHOW_CLOSE_BUTTON = "KEY_IS_SHOW_CLOSE_BUTTON";
    private static final String KEY_MESSAGE = "message";
    private static final String KEY_OK_BTN_TXT = "okButton";
    private static final String KEY_OTHER_BTN_TXT = "otherButton";
    private static final String KEY_TITLE = "title";
    protected ConstraintLayout bkg;
    protected LinearLayout boxButton;
    protected RelativeLayout boxCustom;
    protected TextView btnSelectNegative;
    protected TextView btnSelectOther;
    protected TextView btnSelectPositive;
    protected Button btnTopExit;
    protected String cancelButton;
    private Boolean isShowCloseButton;
    protected String message = "";
    protected String okButton = ContextUtil.getContext().getString(R.string.dialog_confirm);
    protected OnDialogButtonClickListener onCancelButtonClickListener;
    protected OnDialogButtonClickListener onOkButtonClickListener;
    protected OnDialogButtonClickListener onOtherButtonClickListener;
    protected String otherButton;
    protected String title = ContextUtil.getContext().getString(R.string.dialog_tip);
    protected TextView txtDialogTip;
    protected TextView txtDialogTitle;

    public interface OnDialogButtonClickListener {
        boolean onClick(View view);
    }

    public int setUpLayoutId() {
        return R.layout.dialog_select;
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

    public /* bridge */ /* synthetic */ View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return super.onCreateView(layoutInflater, viewGroup, bundle);
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

    public static ConfirmDialog build() {
        Bundle newBundle = newBundle("", "", "", "", "");
        ConfirmDialog confirmDialog = new ConfirmDialog();
        confirmDialog.setArguments(newBundle);
        return confirmDialog;
    }

    public static ConfirmDialog build(String str, String str2, String str3, String str4, String str5) {
        Bundle newBundle = newBundle(str, str2, str3, str4, str5);
        ConfirmDialog confirmDialog = new ConfirmDialog();
        confirmDialog.setArguments(newBundle);
        return confirmDialog;
    }

    public static ConfirmDialog build(String str, String str2) {
        Bundle newBundle = newBundle(str, str2, (String) null, (String) null, (String) null);
        ConfirmDialog confirmDialog = new ConfirmDialog();
        confirmDialog.setArguments(newBundle);
        return confirmDialog;
    }

    public static ConfirmDialog buildOther(String str, String str2, String str3) {
        Bundle newBundle = newBundle(str, (String) null, str3, (String) null, str2);
        ConfirmDialog confirmDialog = new ConfirmDialog();
        confirmDialog.setArguments(newBundle);
        return confirmDialog;
    }

    public static ConfirmDialog build(String str) {
        Bundle newBundle = newBundle((String) null, str, ContextUtil.getContext().getString(R.string.dialog_confirm), (String) null, (String) null);
        ConfirmDialog confirmDialog = new ConfirmDialog();
        confirmDialog.setArguments(newBundle);
        return confirmDialog;
    }

    public static ConfirmDialog buildDefault(String str, String str2) {
        ConfirmDialog confirmDialog = new ConfirmDialog();
        confirmDialog.setArguments(newBundle(str, str2, ContextUtil.getContext().getString(R.string.dialog_confirm), (String) null, (String) null));
        return confirmDialog;
    }

    public static ConfirmDialog buildDefault2(String str, String str2) {
        ConfirmDialog confirmDialog = new ConfirmDialog();
        confirmDialog.setArguments(newBundle(str, str2, ContextUtil.getContext().getString(R.string.dialog_confirm), ContextUtil.getContext().getString(R.string.dialog_cancle), (String) null));
        return confirmDialog;
    }

    public static ConfirmDialog build(String str, String str2, String str3) {
        Bundle newBundle = newBundle(str, str2, str3, (String) null, (String) null);
        ConfirmDialog confirmDialog = new ConfirmDialog();
        confirmDialog.setArguments(newBundle);
        return confirmDialog;
    }

    public static ConfirmDialog build(String str, String str2, String str3, String str4) {
        Bundle newBundle = newBundle(str, str2, str3, str4, (String) null);
        ConfirmDialog confirmDialog = new ConfirmDialog();
        confirmDialog.setArguments(newBundle);
        return confirmDialog;
    }

    public static ConfirmDialog build(String str, String str2, String str3, String str4, Boolean bool) {
        Bundle newBundle = newBundle(str, str2, str3, str4, (String) null);
        newBundle.putBoolean(KEY_IS_SHOW_CLOSE_BUTTON, bool.booleanValue());
        ConfirmDialog confirmDialog = new ConfirmDialog();
        confirmDialog.setArguments(newBundle);
        return confirmDialog;
    }

    public static Bundle newBundle(String str, String str2, String str3, String str4, String str5) {
        Bundle bundle = new Bundle();
        bundle.putString(KEY_TITLE, str);
        bundle.putString(KEY_MESSAGE, str2);
        bundle.putString(KEY_OK_BTN_TXT, str3);
        bundle.putString(KEY_CANCEL_BTN_TXT, str4);
        bundle.putString(KEY_OTHER_BTN_TXT, str5);
        return bundle;
    }

    public void setTitle(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.txtDialogTitle.setText(str);
        }
    }

    public void setContent(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.txtDialogTip.setText(str);
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.title = arguments.getString(KEY_TITLE);
            this.message = arguments.getString(KEY_MESSAGE);
            this.okButton = arguments.getString(KEY_OK_BTN_TXT);
            this.cancelButton = arguments.getString(KEY_CANCEL_BTN_TXT);
            this.otherButton = arguments.getString(KEY_OTHER_BTN_TXT);
            this.isShowCloseButton = Boolean.valueOf(arguments.getBoolean(KEY_IS_SHOW_CLOSE_BUTTON));
        }
    }

    public void convertView(View view, BaseDialog baseDialog) {
        this.bkg = (ConstraintLayout) view.findViewById(R.id.bkg);
        this.txtDialogTitle = (TextView) view.findViewById(R.id.txt_dialog_title);
        this.txtDialogTip = (TextView) view.findViewById(R.id.txt_dialog_tip);
        this.boxCustom = (RelativeLayout) view.findViewById(R.id.box_custom);
        this.boxButton = (LinearLayout) view.findViewById(R.id.box_button);
        this.btnSelectNegative = (TextView) view.findViewById(R.id.btn_selectNegative);
        this.btnSelectOther = (TextView) view.findViewById(R.id.btn_selectOther);
        this.btnSelectPositive = (TextView) view.findViewById(R.id.btn_selectPositive);
        Button button = (Button) view.findViewById(R.id.btn_top_exit);
        this.btnTopExit = button;
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ConfirmDialog.this.dismiss();
            }
        });
        refreshView();
    }

    public void refreshView() {
        this.btnTopExit.setVisibility(this.isShowCloseButton.booleanValue() ? 0 : 8);
        if (this.txtDialogTitle != null) {
            if (TextUtils.isEmpty(this.title)) {
                this.txtDialogTitle.setVisibility(8);
            } else {
                this.txtDialogTitle.setVisibility(0);
                this.txtDialogTitle.setText(this.title);
            }
        }
        TextView textView = this.txtDialogTip;
        int i = 4;
        if (textView != null) {
            if (this.message == null) {
                textView.setVisibility(4);
            } else {
                textView.setVisibility(0);
                this.txtDialogTip.setText(this.message);
            }
        }
        TextView textView2 = this.btnSelectPositive;
        if (textView2 != null) {
            textView2.setText(this.okButton);
            TextView textView3 = this.btnSelectPositive;
            if (this.okButton != null) {
                i = 0;
            }
            textView3.setVisibility(i);
            this.btnSelectPositive.setOnClickListener(new View.OnClickListener() {
                public final void onClick(View view) {
                    ConfirmDialog.this.lambda$refreshView$0$ConfirmDialog(view);
                }
            });
        }
        if (this.btnSelectNegative != null) {
            if (TextUtils.isEmpty(this.cancelButton)) {
                this.btnSelectNegative.setVisibility(8);
            } else {
                this.btnSelectNegative.setText(this.cancelButton);
                this.btnSelectNegative.setOnClickListener(new View.OnClickListener() {
                    public final void onClick(View view) {
                        ConfirmDialog.this.lambda$refreshView$1$ConfirmDialog(view);
                    }
                });
            }
        }
        if (this.btnSelectOther != null) {
            if (!TextUtils.isEmpty(this.otherButton)) {
                this.btnSelectOther.setVisibility(0);
                this.btnSelectOther.setText(this.otherButton);
            }
            this.btnSelectOther.setOnClickListener(new View.OnClickListener() {
                public final void onClick(View view) {
                    ConfirmDialog.this.lambda$refreshView$2$ConfirmDialog(view);
                }
            });
        }
    }

    public /* synthetic */ void lambda$refreshView$0$ConfirmDialog(View view) {
        OnDialogButtonClickListener onDialogButtonClickListener = this.onOkButtonClickListener;
        if (onDialogButtonClickListener == null) {
            dismissAllowingStateLoss();
        } else if (!onDialogButtonClickListener.onClick(view)) {
            dismissAllowingStateLoss();
        }
    }

    public /* synthetic */ void lambda$refreshView$1$ConfirmDialog(View view) {
        OnDialogButtonClickListener onDialogButtonClickListener = this.onCancelButtonClickListener;
        if (onDialogButtonClickListener == null) {
            dismissAllowingStateLoss();
        } else if (!onDialogButtonClickListener.onClick(view)) {
            dismissAllowingStateLoss();
        }
    }

    public /* synthetic */ void lambda$refreshView$2$ConfirmDialog(View view) {
        OnDialogButtonClickListener onDialogButtonClickListener = this.onOtherButtonClickListener;
        if (onDialogButtonClickListener == null) {
            dismissAllowingStateLoss();
        } else if (!onDialogButtonClickListener.onClick(view)) {
            dismissAllowingStateLoss();
        }
    }

    public OnDialogButtonClickListener getOnOkButtonClickListener() {
        return this.onOkButtonClickListener;
    }

    public void setOnOkButtonClickListener(OnDialogButtonClickListener onDialogButtonClickListener) {
        this.onOkButtonClickListener = onDialogButtonClickListener;
    }

    public OnDialogButtonClickListener getOnCancelButtonClickListener() {
        return this.onCancelButtonClickListener;
    }

    public void setOnCancelButtonClickListener(OnDialogButtonClickListener onDialogButtonClickListener) {
        this.onCancelButtonClickListener = onDialogButtonClickListener;
    }

    public OnDialogButtonClickListener getOnOtherButtonClickListener() {
        return this.onOtherButtonClickListener;
    }

    public void setOnOtherButtonClickListener(OnDialogButtonClickListener onDialogButtonClickListener) {
        this.onOtherButtonClickListener = onDialogButtonClickListener;
    }
}
