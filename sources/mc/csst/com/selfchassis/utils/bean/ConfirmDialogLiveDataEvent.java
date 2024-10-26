package mc.csst.com.selfchassis.utils.bean;

import mc.csst.com.selfchassis.ui.dialog.ConfirmDialog;

public class ConfirmDialogLiveDataEvent {
    public static final String TAG = ConfirmDialogLiveDataEvent.class.getSimpleName();
    private String cancelStrBtn;
    private String message;
    private String okStrBtn;
    private ConfirmDialog.OnDialogButtonClickListener onCancelButtonClickListener;
    private ConfirmDialog.OnDialogButtonClickListener onOkButtonClickListener;
    private String title;

    public String getOkStrBtn() {
        return this.okStrBtn;
    }

    public void setOkStrBtn(String str) {
        this.okStrBtn = str;
    }

    public String getCancelStrBtn() {
        return this.cancelStrBtn;
    }

    public void setCancelStrBtn(String str) {
        this.cancelStrBtn = str;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public ConfirmDialog.OnDialogButtonClickListener getOnOkButtonClickListener() {
        return this.onOkButtonClickListener;
    }

    public void setOnOkButtonClickListener(ConfirmDialog.OnDialogButtonClickListener onDialogButtonClickListener) {
        this.onOkButtonClickListener = onDialogButtonClickListener;
    }

    public ConfirmDialog.OnDialogButtonClickListener getOnCancelButtonClickListener() {
        return this.onCancelButtonClickListener;
    }

    public void setOnCancelButtonClickListener(ConfirmDialog.OnDialogButtonClickListener onDialogButtonClickListener) {
        this.onCancelButtonClickListener = onDialogButtonClickListener;
    }
}
