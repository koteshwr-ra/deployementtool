package com.ciot.base.base.mvvm;

public interface IBasePagingView extends IBaseView {
    void onLoadMoreEmpty();

    void onLoadMoreFailure(String str);
}
