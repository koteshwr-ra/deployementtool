package com.ciot.base.base.mvvm;

public interface IBaseView {
    void showContent();

    void showEmpty();

    void showFailure(String str);

    void showLoading();
}
