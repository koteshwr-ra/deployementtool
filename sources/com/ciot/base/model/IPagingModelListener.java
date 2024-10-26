package com.ciot.base.model;

public interface IPagingModelListener<T> extends IBaseModelListener {
    void onLoadFail(BasePagingModel basePagingModel, String str, boolean z);

    void onLoadFinish(BasePagingModel basePagingModel, T t, boolean z, boolean z2);
}
