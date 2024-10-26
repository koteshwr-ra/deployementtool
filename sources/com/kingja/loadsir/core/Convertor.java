package com.kingja.loadsir.core;

import com.kingja.loadsir.callback.Callback;

public interface Convertor<T> {
    Class<? extends Callback> map(T t);
}
