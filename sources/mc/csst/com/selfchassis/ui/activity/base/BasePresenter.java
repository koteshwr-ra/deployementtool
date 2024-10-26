package mc.csst.com.selfchassis.ui.activity.base;

import mc.csst.com.selfchassis.ui.activity.base.BaseView;

public class BasePresenter<V extends BaseView> {
    protected V mView;

    public void attachView(V v) {
        this.mView = v;
    }

    public void detachView() {
        this.mView = null;
    }

    public boolean isViewAttached() {
        return this.mView != null;
    }
}
