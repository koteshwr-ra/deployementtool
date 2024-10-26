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
import java.util.List;

public abstract class MvvmLazyFragment<V extends ViewDataBinding, VM extends IMvvmBaseViewModel> extends Fragment implements IBaseView {
    protected boolean currentVisibleState = false;
    private boolean isShowedContent = false;
    protected boolean isViewCreated = false;
    protected String mFragmentTag = getClass().getSimpleName();
    protected boolean mIsFirstVisible = true;
    private LoadService mLoadService;
    protected View rootView = null;
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
        Log.d(this.mFragmentTag, " : onCreate");
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(this.mFragmentTag, " : onAttach");
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.rootView == null) {
            V inflate = DataBindingUtil.inflate(layoutInflater, getLayoutId(), viewGroup, false);
            this.viewDataBinding = inflate;
            this.rootView = inflate.getRoot();
        }
        this.isViewCreated = true;
        Log.d(this.mFragmentTag, " : onCreateView");
        return this.rootView;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        Log.d(this.mFragmentTag, " : onViewCreated");
        VM viewModel2 = getViewModel();
        this.viewModel = viewModel2;
        if (viewModel2 != null) {
            viewModel2.attachUi(this);
        }
        if (getBindingVariable() > 0) {
            this.viewDataBinding.setVariable(getBindingVariable(), this.viewModel);
            this.viewDataBinding.executePendingBindings();
        }
        if (!isHidden() && getUserVisibleHint()) {
            dispatchUserVisibleHint(true);
        }
    }

    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        String str = this.mFragmentTag;
        Log.d(str, "setUserVisibleHint: " + z);
        if (!this.isViewCreated) {
            return;
        }
        if (z && !this.currentVisibleState) {
            dispatchUserVisibleHint(true);
        } else if (!z && this.currentVisibleState) {
            dispatchUserVisibleHint(false);
        }
    }

    public void onHiddenChanged(boolean z) {
        String str = this.mFragmentTag;
        Log.d(str, "onHiddenChanged: " + z);
        super.onHiddenChanged(z);
        if (z) {
            dispatchUserVisibleHint(false);
        } else {
            dispatchUserVisibleHint(true);
        }
    }

    private void dispatchUserVisibleHint(boolean z) {
        String str = this.mFragmentTag;
        Log.d(str, "dispatchUserVisibleHint: " + z);
        if ((!z || !isParentInvisible()) && this.currentVisibleState != z) {
            this.currentVisibleState = z;
            if (z) {
                if (this.mIsFirstVisible) {
                    this.mIsFirstVisible = false;
                    onFragmentFirstVisible();
                }
                onFragmentResume();
                dispatchChildVisibleState(true);
                return;
            }
            onFragmentPause();
            dispatchChildVisibleState(false);
        }
    }

    private void dispatchChildVisibleState(boolean z) {
        String str = this.mFragmentTag;
        Log.d(str, "dispatchChildVisibleState " + z);
        List<Fragment> fragments = getChildFragmentManager().getFragments();
        if (fragments != null) {
            for (Fragment next : fragments) {
                if ((next instanceof MvvmLazyFragment) && !next.isHidden() && next.getUserVisibleHint()) {
                    ((MvvmLazyFragment) next).dispatchUserVisibleHint(z);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onFragmentPause() {
        Log.d(this.mFragmentTag, "onFragmentPause  真正的Pause 结束相关操作耗时");
    }

    /* access modifiers changed from: protected */
    public void onFragmentResume() {
        Log.d(this.mFragmentTag, "onFragmentResume 真正的Resume 开始相关操作耗时");
    }

    private boolean isParentInvisible() {
        Fragment parentFragment = getParentFragment();
        if (parentFragment instanceof MvvmLazyFragment) {
            return !((MvvmLazyFragment) parentFragment).isSupportVisible();
        }
        return false;
    }

    private boolean isSupportVisible() {
        return this.currentVisibleState;
    }

    public void onResume() {
        super.onResume();
        if (!this.mIsFirstVisible && !isHidden() && !this.currentVisibleState && getUserVisibleHint()) {
            dispatchUserVisibleHint(true);
        }
    }

    public void onPause() {
        super.onPause();
        if (this.currentVisibleState && getUserVisibleHint()) {
            dispatchUserVisibleHint(false);
        }
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.isViewCreated = false;
        Log.d(this.mFragmentTag, "onDestroyView");
    }

    public void onDestroy() {
        super.onDestroy();
        VM vm = this.viewModel;
        if (vm != null && vm.isUiAttach()) {
            this.viewModel.detachUi();
        }
        Log.d(this.mFragmentTag, "onDestroy");
    }

    public void onDetach() {
        super.onDetach();
        Log.d(this.mFragmentTag, "onDetach");
    }

    /* access modifiers changed from: protected */
    public void onFragmentFirstVisible() {
        Log.d(this.mFragmentTag, "onFragmentFirstVisible  第一次可见");
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
                MvvmLazyFragment.this.lambda$setLoadSir$596c0cf0$1$MvvmLazyFragment(view);
            }
        });
    }

    public /* synthetic */ void lambda$setLoadSir$596c0cf0$1$MvvmLazyFragment(View view) {
        onRetryBtnClick();
    }
}
