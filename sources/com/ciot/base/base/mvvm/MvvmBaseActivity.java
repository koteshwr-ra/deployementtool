package com.ciot.base.base.mvvm;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.ciot.base.loadsir.EmptyCallback;
import com.ciot.base.loadsir.ErrorCallback;
import com.ciot.base.loadsir.LoadingCallback;
import com.ciot.base.util.ToastUtil;
import com.ciot.base.viewmodel.IMvvmBaseViewModel;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;

public abstract class MvvmBaseActivity<V extends ViewDataBinding, VM extends IMvvmBaseViewModel> extends AppCompatActivity implements IBaseView {
    private boolean isShowedContent = false;
    protected LoadService mLoadService;
    protected V viewDataBinding;
    protected VM viewModel;

    /* access modifiers changed from: protected */
    public abstract int getBindingVariable();

    /* access modifiers changed from: protected */
    public abstract int getLayoutId();

    /* access modifiers changed from: protected */
    public abstract VM getViewModel();

    /* access modifiers changed from: protected */
    public abstract void onRetryBtnClick();

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        initViewModel();
        performDataBinding();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        VM vm = this.viewModel;
        if (vm != null && vm.isUiAttach()) {
            this.viewModel.detachUi();
        }
    }

    private void performDataBinding() {
        this.viewDataBinding = DataBindingUtil.setContentView(this, getLayoutId());
        VM vm = this.viewModel;
        if (vm == null) {
            vm = getViewModel();
        }
        this.viewModel = vm;
        if (getBindingVariable() > 0) {
            this.viewDataBinding.setVariable(getBindingVariable(), this.viewModel);
        }
        this.viewDataBinding.executePendingBindings();
    }

    private void initViewModel() {
        VM viewModel2 = getViewModel();
        this.viewModel = viewModel2;
        if (viewModel2 != null) {
            viewModel2.attachUi(this);
        }
    }

    public void setLoadSir(View view) {
        if (this.mLoadService == null) {
            this.mLoadService = LoadSir.getDefault().register(view, new Object() {
                public final void onReload(View view) {
                    MvvmBaseActivity.this.lambda$setLoadSir$596c0cf0$1$MvvmBaseActivity(view);
                }
            });
        }
    }

    public /* synthetic */ void lambda$setLoadSir$596c0cf0$1$MvvmBaseActivity(View view) {
        onRetryBtnClick();
    }

    public void showContent() {
        LoadService loadService = this.mLoadService;
        if (loadService != null) {
            this.isShowedContent = true;
            loadService.showSuccess();
        }
    }

    public void showLoading() {
        LoadService loadService = this.mLoadService;
        if (loadService != null) {
            loadService.showCallback(LoadingCallback.class);
        }
    }

    public void showEmpty() {
        LoadService loadService = this.mLoadService;
        if (loadService != null) {
            loadService.showCallback(EmptyCallback.class);
        }
    }

    public void showFailure(String str) {
        LoadService loadService = this.mLoadService;
        if (loadService == null) {
            return;
        }
        if (!this.isShowedContent) {
            loadService.showCallback(ErrorCallback.class);
        } else {
            ToastUtil.showShort(this, str);
        }
    }
}
