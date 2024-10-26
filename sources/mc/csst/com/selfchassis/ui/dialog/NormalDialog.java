package mc.csst.com.selfchassis.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.ciot.base.util.ContextUtil;
import com.ciot.sentrymove.R;

public class NormalDialog extends Dialog implements View.OnClickListener {
    private static NormalDialog mNormalDialog;
    private Button mBtCancel = null;
    private Button mBtOk = null;
    private TextView mMessage = null;
    private OnDialogListener mOnDialogListener;
    private TextView mTitle = null;
    private Button mTopBtCancel = null;

    public interface OnDialogListener {
        void isPositiveBtClick(boolean z);
    }

    public NormalDialog(Context context) {
        super(context);
    }

    public NormalDialog(Context context, int i) {
        super(context, i);
        initParms(context);
    }

    protected NormalDialog(Context context, boolean z, DialogInterface.OnCancelListener onCancelListener) {
        super(context, z, onCancelListener);
    }

    public static NormalDialog createDialog(Context context) {
        NormalDialog normalDialog = new NormalDialog(context, R.style.BaseDialog);
        mNormalDialog = normalDialog;
        normalDialog.getWindow().getAttributes().gravity = 17;
        return mNormalDialog;
    }

    private void initParms(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.dialog_normal, (ViewGroup) null);
        this.mTitle = (TextView) inflate.findViewById(R.id.title);
        this.mMessage = (TextView) inflate.findViewById(R.id.message);
        this.mBtCancel = (Button) inflate.findViewById(R.id.btn_selectNegative);
        this.mTopBtCancel = (Button) inflate.findViewById(R.id.btn_top_exit);
        this.mBtOk = (Button) inflate.findViewById(R.id.btn_selectPositive);
        this.mBtCancel.setOnClickListener(this);
        this.mBtOk.setOnClickListener(this);
        this.mTopBtCancel.setOnClickListener(this);
        setContentView(inflate);
        setCanceledOnTouchOutside(false);
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_selectPositive) {
            OnDialogListener onDialogListener = this.mOnDialogListener;
            if (onDialogListener != null) {
                onDialogListener.isPositiveBtClick(true);
            }
            dismiss();
        } else if (id == R.id.btn_selectNegative) {
            OnDialogListener onDialogListener2 = this.mOnDialogListener;
            if (onDialogListener2 != null) {
                onDialogListener2.isPositiveBtClick(false);
            }
            dismiss();
        } else if (id == R.id.btn_top_exit) {
            dismiss();
        }
    }

    public void setTitle(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            this.mTitle.setText(charSequence);
        } else {
            this.mTitle.setVisibility(4);
        }
    }

    public void setMessage(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            this.mMessage.setText(charSequence);
        } else {
            this.mMessage.setVisibility(4);
        }
    }

    public void setMessageTxtColor(int i) {
        this.mMessage.setTextColor(ContextUtil.getContext().getResources().getColor(i));
    }

    public void setBtCancel(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            this.mBtCancel.setText(charSequence);
        } else {
            this.mBtCancel.setVisibility(8);
        }
    }

    public void setShowTopExit(boolean z) {
        if (z) {
            this.mTopBtCancel.setVisibility(0);
        } else {
            this.mTopBtCancel.setVisibility(8);
        }
    }

    public void setBtOk(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            this.mBtOk.setText(charSequence);
        } else {
            this.mBtOk.setVisibility(8);
        }
    }

    public void setBtnCancleStyle() {
        this.mBtCancel.setBackground(ContextUtil.getContext().getDrawable(R.drawable.button_select));
        this.mBtCancel.setTextColor(ContextUtil.getContext().getResources().getColor(R.color.white));
    }

    public TextView getMessageTv() {
        return this.mMessage;
    }

    public void show() {
        getWindow().setFlags(8, 8);
        super.show();
        fullScreenImmersive();
        getWindow().clearFlags(8);
    }

    public void fullScreenImmersive() {
        getWindow().getDecorView().setSystemUiVisibility(5894);
    }

    public void setmOnDialogListener(OnDialogListener onDialogListener) {
        this.mOnDialogListener = onDialogListener;
    }
}
