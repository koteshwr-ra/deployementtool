package com.ciot.base.base.mvvm;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import com.ciot.base.loadsir.EmptyCallback;
import com.ciot.base.loadsir.ErrorCallback;
import com.ciot.base.loadsir.LoadingCallback;
import com.ciot.base.util.ToastUtil;
import com.ciot.base.viewmodel.IMvvmBaseViewModel;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;

public abstract class MvvmBaseFragment<V extends ViewDataBinding, VM extends IMvvmBaseViewModel> extends Fragment implements IBaseView {
    private boolean isShowedContent = false;
    protected String mFragmentTag = getClass().getSimpleName();
    private LoadService mLoadService;
    protected V viewDataBinding;
    protected VM viewModel;

    public abstract int getBindingVariable();

    public abstract int getLayoutId();

    /* access modifiers changed from: protected */
    public abstract VM getViewModel();

    /* access modifiers changed from: protected */
    public void initParameters() {
    }

    /* access modifiers changed from: protected */
    public abstract void onRetryBtnClick();

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        initParameters();
        Log.d(this.mFragmentTag, "onCreate");
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.viewDataBinding = DataBindingUtil.inflate(layoutInflater, getLayoutId(), viewGroup, false);
        Log.d(this.mFragmentTag, " : onCreateView");
        return this.viewDataBinding.getRoot();
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        VM viewModel2 = getViewModel();
        this.viewModel = viewModel2;
        if (viewModel2 != null) {
            viewModel2.attachUi(this);
        }
        if (getBindingVariable() > 0) {
            this.viewDataBinding.setVariable(getBindingVariable(), this.viewModel);
            this.viewDataBinding.executePendingBindings();
        }
        Log.d(this.mFragmentTag, " : onViewCreated");
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(this.mFragmentTag, " : onAttach");
    }

    public void onResume() {
        super.onResume();
        Log.d(this.mFragmentTag, " : onResume");
    }

    public void onPause() {
        super.onPause();
        Log.d(this.mFragmentTag, " : onPause");
    }

    public void onDestroyView() {
        super.onDestroyView();
        Log.d(this.mFragmentTag, " : onDestroyView");
    }

    public void onDestroy() {
        super.onDestroy();
        VM vm = this.viewModel;
        if (vm != null && vm.isUiAttach()) {
            this.viewModel.detachUi();
        }
        Log.d(this.mFragmentTag, " : onDestroy");
    }

    public void onDetach() {
        super.onDetach();
        VM vm = this.viewModel;
        if (vm != null && vm.isUiAttach()) {
            this.viewModel.detachUi();
        }
        Log.d(this.mFragmentTag, " : onDetach");
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
            ToastUtil.showShort(getContext(), str);
        }
    }

    public void setLoadSir(View view) {
        this.mLoadService = LoadSir.getDefault().register(view, new Object() {
            public final void onReload(View view) {
                MvvmBaseFragment.this.lambda$setLoadSir$596c0cf0$1$MvvmBaseFragment(view);
            }
        });
    }

    public /* synthetic */ void lambda$setLoadSir$596c0cf0$1$MvvmBaseFragment(View view) {
        onRetryBtnClick();
    }
}
