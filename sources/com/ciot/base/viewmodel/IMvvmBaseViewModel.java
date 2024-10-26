package com.ciot.base.viewmodel;

public interface IMvvmBaseViewModel<V> {
    void attachUi(V v);

    void detachUi();

    V getPageView();

    boolean isUiAttach();
}
