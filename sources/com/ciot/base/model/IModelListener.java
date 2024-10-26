package com.ciot.base.model;

public interface IModelListener<T> extends IBaseModelListener {
    void onLoadFail(BaseModel baseModel, String str);

    void onLoadFinish(BaseModel baseModel, T t);
}
