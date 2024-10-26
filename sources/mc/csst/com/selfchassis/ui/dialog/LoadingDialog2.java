package mc.csst.com.selfchassis.ui.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import com.blankj.utilcode.util.AdaptScreenUtils;
import com.ciot.base.util.ContextUtil;
import com.ciot.sentrymove.R;
import mc.csst.com.selfchassis.databinding.DialogLoading2Binding;

public class LoadingDialog2 extends BaseDialog {
    private DialogLoading2Binding mBinding;
    private String mloadingText;
    /* access modifiers changed from: private */
    public OnCancelClickListener onCancelClickListener;

    public interface OnCancelClickListener {
        void onCancel();
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

    public static LoadingDialog2 newInstance() {
        return newInstance(ContextUtil.getContext().getString(R.string.dialog_loading_hint));
    }

    public static LoadingDialog2 newInstance(String str) {
        LoadingDialog2 loadingDialog2 = new LoadingDialog2();
        loadingDialog2.setLoadingText(str);
        loadingDialog2.setSize(AdaptScreenUtils.pt2Px(587.0f), AdaptScreenUtils.pt2Px(392.0f));
        return loadingDialog2;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mBinding = (DialogLoading2Binding) DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.dialog_loading2, viewGroup, false);
        initView();
        return this.mBinding.getRoot();
    }

    private void initView() {
        this.mBinding.loadingTv.setText(this.mloadingText);
        this.mBinding.buttonCancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (LoadingDialog2.this.onCancelClickListener != null) {
                    LoadingDialog2.this.onCancelClickListener.onCancel();
                }
            }
        });
    }

    public void setLoadingText(String str) {
        this.mloadingText = str;
    }

    public void updateLoadingText(String str) {
        if (getActivity() != null) {
            getActivity().runOnUiThread(new Runnable(str) {
                public final /* synthetic */ String f$1;

                {
                    this.f$1 = r2;
                }

                public final void run() {
                    LoadingDialog2.this.lambda$updateLoadingText$0$LoadingDialog2(this.f$1);
                }
            });
        }
    }

    public /* synthetic */ void lambda$updateLoadingText$0$LoadingDialog2(String str) {
        this.mBinding.loadingTv.setText(str);
    }

    public void setProgress(String str) {
        if (getActivity() != null) {
            getActivity().runOnUiThread(new Runnable(str) {
                public final /* synthetic */ String f$1;

                {
                    this.f$1 = r2;
                }

                public final void run() {
                    LoadingDialog2.this.lambda$setProgress$1$LoadingDialog2(this.f$1);
                }
            });
        }
    }

    public /* synthetic */ void lambda$setProgress$1$LoadingDialog2(String str) {
        this.mBinding.tvProgress.setVisibility(0);
        this.mBinding.tvProgress.setText(str);
    }

    public void setButtonVisible(boolean z) {
        if (getActivity() != null) {
            getActivity().runOnUiThread(new Runnable(z) {
                public final /* synthetic */ boolean f$1;

                {
                    this.f$1 = r2;
                }

                public final void run() {
                    LoadingDialog2.this.lambda$setButtonVisible$2$LoadingDialog2(this.f$1);
                }
            });
        }
    }

    public /* synthetic */ void lambda$setButtonVisible$2$LoadingDialog2(boolean z) {
        this.mBinding.buttonCancel.setVisibility(z ? 0 : 8);
    }

    public boolean isShow() {
        if (getDialog() != null) {
            return getDialog().isShowing();
        }
        return false;
    }

    public void setOnCancelClickListener(OnCancelClickListener onCancelClickListener2) {
        this.onCancelClickListener = onCancelClickListener2;
    }
}
